package org.eclipse.stardust.modeling.modelimport.elements;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.compare.structuremergeviewer.IStructureCreator;
import org.eclipse.compare.structuremergeviewer.StructureDiffViewer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.modelimport.Import_Messages;
import org.eclipse.swt.widgets.Composite;

public final class DifferencesViewer extends StructureDiffViewer
{
   private static final String MERGE_CATEGORY = "merge"; //$NON-NLS-1$
   private static final String NAVIGATION_CATEGORY = "navigation"; //$NON-NLS-1$
   private static final String FILTER_CATEGORY = "filter"; //$NON-NLS-1$

   private static final String ICONS_FULL_OBJ16_PATH = "/icons/full/obj16/"; //$NON-NLS-1$
   private static final IPath ICONS_PATH = new Path("icons/full/"); //$NON-NLS-1$

   private Action additionFilterAction;
   private Action deletionFilterAction;
   private Action changeFilterAction;
   private Action allFilterAction;

   private DifferencesContentProvider contentProvider = new DifferencesContentProvider();

   private IStructureCreator creator = new IStructureCreator()
   {
      public String getContents(Object node, boolean ignoreWhitespace)
      {
         return ((ITypedElement) node).getName();
      }

      public String getName()
      {
         return Import_Messages.DifferencesViewer_Title;
      }

      public IStructureComparator getStructure(Object input)
      {
         return (IStructureComparator) input;
      }

      public IStructureComparator locate(Object path, Object input)
      {
         return null;
      }

      public void save(IStructureComparator node, Object input)
      {
         DifferencesViewer.this.input.setDirty(true);
      }
   };

   private MergeEditorInput input;

   private List<ICompareInput> processed = CollectionUtils.newList();

   public DifferencesViewer(Composite parent, MergeEditorInput input, CompareConfiguration configuration)
   {
      super(parent, configuration);
      setContentProvider(contentProvider);
      setStructureCreator(creator);
      setSorter(new ModelElementsSorter());
      this.input = input;
   }

   protected void copySelected(boolean leftToRight)
   {
      super.copySelected(leftToRight);
      MergeUtil.copyReferences();
      processed.clear();
      setSelection((ISelection) null);
      contentChanged(null);
      input.contentChanged();
   }

   protected void copyOne(ICompareInput node, boolean leftToRight)
   {
      for (int i = 0; i < processed.size(); i++)
      {
         if (processed.get(i) == node)
         {
            return;
         }
      }
      if (node instanceof IDiffContainer && ((IDiffContainer) node).hasChildren())
      {
         Object[] children = contentProvider.getChildren(node);
         for (int i = 0; i < children.length; i++)
         {
            if (children[i] instanceof ICompareInput)
            {
               copyOne((ICompareInput) children[i], leftToRight);
            }
         }
      }
      else
      {
         node.copy(leftToRight);
      }
      processed.add(node);
   }

   /**
    * Originally copied from {@link org.eclipse.compare.internal.Utilities#initAction(IAction, ResourceBundle, String)}.
    */
   public void initAction(IAction a, ResourceBundle bundle, String prefix)
   {
      String labelKey = "label"; //$NON-NLS-1$
      String tooltipKey = "tooltip"; //$NON-NLS-1$
      String imageKey = "image"; //$NON-NLS-1$
      String descriptionKey = "description"; //$NON-NLS-1$

      if (prefix != null && prefix.length() > 0)
      {
          labelKey = prefix + labelKey;
          tooltipKey = prefix + tooltipKey;
          imageKey = prefix + imageKey;
          descriptionKey = prefix + descriptionKey;
      }

      String label = getString(bundle, labelKey);
      a.setText(label == null ? labelKey : label);
      a.setToolTipText(getString(bundle, tooltipKey));
      a.setDescription(getString(bundle, descriptionKey));

      String relPath = getString(bundle, imageKey);
      if (relPath != null && relPath.trim().length() > 0)
      {
          String dPath;
          String ePath;

          if (relPath.indexOf("/") >= 0) //$NON-NLS-1$
          {
              String path = relPath.substring(1);
              dPath = 'd' + path;
              ePath = 'e' + path;
          }
          else
          {
              dPath = "dlcl16/" + relPath; //$NON-NLS-1$
              ePath = "elcl16/" + relPath; //$NON-NLS-1$
          }

          ImageDescriptor id = getImageDescriptor(dPath);  // we set the disabled image first (see PR 1GDDE87)
          if (id != null)
          {
              a.setDisabledImageDescriptor(id);
          }
          id = getImageDescriptor(ePath);
          if (id != null)
          {
              a.setImageDescriptor(id);
              a.setHoverImageDescriptor(id);
          }
      }
   }

   private String getString(ResourceBundle bundle, String labelKey)
   {
      try
      {
         return bundle.getString(labelKey);
      }
      catch (Exception ex)
      {
         return null;
      }
   }

   private ImageDescriptor getImageDescriptor(String dPath)
   {
      String name = ICONS_PATH.append(dPath).toString();
      URL url = CompareUI.getPlugin().getBundle().getEntry(name);
      return url == null ? null : ImageDescriptor.createFromURL(url);
   }

   protected void createToolItems(ToolBarManager toolbarManager)
   {
      // todo: update actions on selection/navigation
      Action copyLeftToRightAction = new Action()
      {
         public void run()
         {
            copySelected(true);
         }
      };
      initAction(copyLeftToRightAction, getBundle(), "action.TakeLeft."); //$NON-NLS-1$
      toolbarManager.appendToGroup(MERGE_CATEGORY, copyLeftToRightAction);

/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/

      Action nextAction = new Action()
      {
         public void run()
         {
            navigate(true);
         }
      };
      initAction(nextAction, getBundle(), "action.NextDiff."); //$NON-NLS-1$
      toolbarManager.appendToGroup(NAVIGATION_CATEGORY, nextAction);

      Action previousAction = new Action()
      {
         public void run()
         {
            navigate(false);
         }
      };
      initAction(previousAction, getBundle(), "action.PrevDiff."); //$NON-NLS-1$
      toolbarManager.appendToGroup(NAVIGATION_CATEGORY, previousAction);



      toolbarManager.add(new Separator(FILTER_CATEGORY));

      // reset all filter settings
      allFilterAction = new Action("all", IAction.AS_RADIO_BUTTON) //$NON-NLS-1$
      {
         public void run()
         {
            contentProvider.setAllFilter();
            DifferencesViewer.this.refresh();
         }
      };
      allFilterAction.setText(Import_Messages.DifferencesViewer_allFilterActionText);
      allFilterAction.setToolTipText(Import_Messages.DifferencesViewer_allFilterActionTooltip);
      allFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor(ICONS_FULL_OBJ16_PATH
                  + "all_changes.gif")); //$NON-NLS-1$
      toolbarManager.appendToGroup(FILTER_CATEGORY, allFilterAction);
      // default
      allFilterAction.setChecked(true);

      // sets a filter to all additive changes
      additionFilterAction = new Action("addition", IAction.AS_RADIO_BUTTON) //$NON-NLS-1$
      {
         public void run()
         {
            contentProvider.setAdditionFilter();
            //DifferencesViewer.this.changeActionStyle(Differencer.ADDITION);
            DifferencesViewer.this.refresh();
         }
      };
      additionFilterAction.setText(Import_Messages.DifferencesViewer_additionFilterText);
      additionFilterAction.setToolTipText(Import_Messages.DifferencesViewer_additionFilterTooltip);
      additionFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor(ICONS_FULL_OBJ16_PATH
                  + "additive_changes.gif")); //$NON-NLS-1$

      toolbarManager.appendToGroup(FILTER_CATEGORY, additionFilterAction);

      // sets a filter to all subtractive changes
      deletionFilterAction = new Action("deletion", IAction.AS_RADIO_BUTTON) //$NON-NLS-1$
      {
         public void run()
         {
            contentProvider.setDeletionFilter();
            DifferencesViewer.this.refresh();
         }
      };

      deletionFilterAction.setText(Import_Messages.DifferencesViewer_deletionFilterText);
      deletionFilterAction.setToolTipText(Import_Messages.DifferencesViewer_deletionFilterTooltip);
      deletionFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor(ICONS_FULL_OBJ16_PATH
                  + "subtractive_changes.gif")); //$NON-NLS-1$
      toolbarManager.appendToGroup(FILTER_CATEGORY, deletionFilterAction);

      // sets a filter to all changes
      changeFilterAction = new Action("change", IAction.AS_RADIO_BUTTON) //$NON-NLS-1$
      {
         public void run()
         {
            contentProvider.setChangeFilter();
            DifferencesViewer.this.refresh();
         }
      };

      changeFilterAction.setText(Import_Messages.DifferencesViewer_changedFilterText);
      changeFilterAction.setToolTipText(Import_Messages.DifferencesViewer_changedFilterToolTip);
      changeFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor(ICONS_FULL_OBJ16_PATH
                  + "update_changes.gif")); //$NON-NLS-1$
      toolbarManager.appendToGroup(FILTER_CATEGORY, changeFilterAction);
   }
}