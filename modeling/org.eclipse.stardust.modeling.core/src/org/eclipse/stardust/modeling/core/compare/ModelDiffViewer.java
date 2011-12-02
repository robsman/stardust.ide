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
package org.eclipse.stardust.modeling.core.compare;

import java.util.*;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;

import ag.carnot.base.StringUtils;

public class ModelDiffViewer extends StructureDiffViewer
{

   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.core.compare.ModelElementMergeViewerResources"; //$NON-NLS-1$

   private IStructureComparator left;

   private IStructureComparator right;

   private Action fCopyRightToLeftAction;

   private Action fNextAction;

   private Action fPreviousAction;

   private Action fAdditionFilterAction;

   private Action fDeletionFilterAction;

   private Action fChangeFilterAction;

   private Action fAllFilterAction;

   private boolean notifyChangeListner;

   protected boolean allFilter = true;

   protected boolean additionFilter = false;

   protected boolean deletionFilter = false;

   protected boolean changeFilter = false;

   protected HashMap leftModelElementOidMap;

   protected HashMap rightModelElementOidMap;

   private ResourceBundle bundle = null;

   public ModelDiffViewer(Composite parent, CompareConfiguration configuration,
         IWizardContainer container)
   {
      super(parent, configuration);
      setContentProvider(new ModelDiffViewerContentProvider());
      setSorter(new ModelViewerSorter());

      this.leftModelElementOidMap = new HashMap();
      this.rightModelElementOidMap = new HashMap();
   }

   public ModelDiffViewer(Composite parent, CompareConfiguration configuration)
   {
      this(parent, configuration, null);
   }

   public String getTitle()
   {
      return getBundle().getString("subtitle1"); //$NON-NLS-1$
   }

   protected void createToolItems(ToolBarManager toolbarManager)
   {
      toolbarManager.add(new Separator("filter")); //$NON-NLS-1$

      // reset all filter settings
      fAllFilterAction = new Action()
      {
         public void run()
         {
            ModelDiffViewer.this.allFilter = true;
            ModelDiffViewer.this.additionFilter = false;
            ModelDiffViewer.this.deletionFilter = false;
            ModelDiffViewer.this.changeFilter = false;
            ModelDiffViewer.this.refresh();
         }
      };

      fAllFilterAction.setText(getBundle().getString("action.allFilter.label")); //$NON-NLS-1$
      fAllFilterAction.setToolTipText(getBundle().getString("action.allFilter.tooltip")); //$NON-NLS-1$
      fAllFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("/icons/full/obj16/" //$NON-NLS-1$
                  + getBundle().getString("action.allFilter.image"))); //$NON-NLS-1$
      toolbarManager.appendToGroup("filter", fAllFilterAction); //$NON-NLS-1$

      // sets a filter to all additive changes
      fAdditionFilterAction = new Action()
      {
         public void run()
         {
            ModelDiffViewer.this.allFilter = false;
            ModelDiffViewer.this.additionFilter = true;
            ModelDiffViewer.this.deletionFilter = false;
            ModelDiffViewer.this.changeFilter = false;
            ModelDiffViewer.this.refresh();
         }
      };

      fAdditionFilterAction.setText(this.bundle.getString("action.AdditionFilter.label")); //$NON-NLS-1$
      fAdditionFilterAction.setToolTipText(this.bundle
            .getString("action.AdditionFilter.tooltip")); //$NON-NLS-1$
      fAdditionFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("/icons/full/obj16/" //$NON-NLS-1$
                  + getBundle().getString("action.AdditionFilter.image"))); //$NON-NLS-1$

      toolbarManager.appendToGroup("filter", fAdditionFilterAction); //$NON-NLS-1$

      // sets a filter to all subtractive changes
      fDeletionFilterAction = new Action()
      {
         public void run()
         {
            ModelDiffViewer.this.allFilter = false;
            ModelDiffViewer.this.additionFilter = false;
            ModelDiffViewer.this.deletionFilter = true;
            ModelDiffViewer.this.changeFilter = false;
            ModelDiffViewer.this.refresh();
         }
      };

      fDeletionFilterAction.setText(getBundle().getString("action.DeletionFilter.label")); //$NON-NLS-1$
      fDeletionFilterAction.setToolTipText(getBundle().getString(
            "action.DeletionFilter.tooltip")); //$NON-NLS-1$
      fDeletionFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("/icons/full/obj16/" //$NON-NLS-1$
                  + getBundle().getString("action.DeletionFilter.image"))); //$NON-NLS-1$
      toolbarManager.appendToGroup("filter", fDeletionFilterAction); //$NON-NLS-1$

      // sets a filter to all changes
      fChangeFilterAction = new Action()
      {
         public void run()
         {
            ModelDiffViewer.this.allFilter = false;
            ModelDiffViewer.this.additionFilter = false;
            ModelDiffViewer.this.deletionFilter = false;
            ModelDiffViewer.this.changeFilter = true;
            ModelDiffViewer.this.refresh();
         }
      };

      fChangeFilterAction.setText(getBundle().getString("action.ChangeFilter.label")); //$NON-NLS-1$
      fChangeFilterAction.setToolTipText(getBundle().getString(
            "action.ChangeFilter.tooltip")); //$NON-NLS-1$
      fChangeFilterAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("/icons/full/obj16/" //$NON-NLS-1$
                  + getBundle().getString("action.ChangeFilter.image"))); //$NON-NLS-1$
      toolbarManager.appendToGroup("filter", fChangeFilterAction); //$NON-NLS-1$

      fCopyRightToLeftAction = new Action()
      {
         public void run()
         {
            // deactivates the redraw funtionality unless the operation is done
            getTree().setVisible(false);
            getTree().setRedraw(false);

            expandAll(); // TODO expand only all node below the selected

            copySelected(false);

            getTree().setRedraw(true);
            getTree().setVisible(true);
         }
      };
      Utilities
            .initAction(fCopyRightToLeftAction, super.getBundle(), "action.TakeRight."); //$NON-NLS-1$
      toolbarManager.appendToGroup("merge", fCopyRightToLeftAction); //$NON-NLS-1$

      fNextAction = new Action()
      {
         public void run()
         {
            navigate(true);
         }
      };

      Utilities.initAction(fNextAction, super.getBundle(), "action.NextDiff."); //$NON-NLS-1$
      toolbarManager.appendToGroup("navigation", fNextAction); //$NON-NLS-1$

      fPreviousAction = new Action()
      {
         public void run()
         {
            navigate(false);
         }
      };

      Utilities.initAction(fPreviousAction, super.getBundle(), "action.PrevDiff."); //$NON-NLS-1$
      toolbarManager.appendToGroup("navigation", fPreviousAction); //$NON-NLS-1$
   }

   protected void preDiffHook(IStructureComparator ancestor, IStructureComparator left,
         IStructureComparator right)
   {
      this.left = left;
      this.right = right;
   }

   /**
    * This method will be invoked if the left as well as the right side are not null. In
    * this case the node have children that have to copied.
    */
   protected void copySelected(boolean leftToRight)
   {
      ComparableModelElementNode lNode = (ComparableModelElementNode) left;
      ComparableModelElementNode rNode = (ComparableModelElementNode) right;

      if (lNode != null && rNode != null
            && (lNode.getETypedElement() == null || rNode.getETypedElement() == null))
      {
         List nodes = new ArrayList();

         ISelection selection = getSelection();
         if (selection instanceof StructuredSelection)
         {
            if (((StructuredSelection) selection).size() > 1)
            {
               for (Iterator _iterator = ((StructuredSelection) selection).iterator(); _iterator
                     .hasNext();)
               {
                  Item item = (Item) findItem(_iterator.next());
                  getItemsRecursive(item, nodes);
               }
            }
            else if (((StructuredSelection) selection).size() == 1)
            {
               DiffNode node = (DiffNode) ((StructuredSelection) selection)
                     .getFirstElement();

               getNodesRecursive(node, nodes);
            }
         }

         ImportSelectionRegistration.rigisterSelection(nodes, leftToRight);

         notifyChangeListner = false;

         for (int i = 0; i < nodes.size(); i++)
         {
            DiffNode diffNode = (DiffNode) nodes.get(i);

            copyOne(diffNode, leftToRight);

            if (i == nodes.size() - 1)
            {
               notifyChangeListner = true;
            }

            if (getStructureCreator() != null)
            {
               getStructureCreator().save(leftToRight ? right : left,
                     leftToRight ? right : left);
            }
         }
      }
      else
      {
         super.copySelected(leftToRight);
      }

   }

   private void getNodesRecursive(DiffNode node, List list)
   {
      if (node.hasChildren())
      {
         IDiffElement[] elements = node.getChildren();
         for (int i = 0; i < elements.length; i++)
         {
            DiffNode element = (DiffNode) elements[i];
            getNodesRecursive(element, list);
         }
      }
      else
      {
         list.add(node);
      }
   }

   /**
    * Method retrieves recursive all items within a tree trunk
    * 
    * @param item
    * @param list
    * @return
    */
   private List getItemsRecursive(Item item, List list)
   {
      Item[] items = getItems(item);
      if (items.length > 0)
      {
         for (int i = 0; i < items.length; i++)
         {
            list = getItemsRecursive(items[i], list);
         }
      }
      else
      {
         Object data = item.getData();
         if (data != null) // in some cases the item has no data
         {
            DiffNode node = (DiffNode) item.getData();
            getNodesRecursive(node, list);
         }
      }
      return list;
   }

   protected void contentChanged(IContentChangeNotifier changed)
   {
      if (notifyChangeListner)
      {
         super.contentChanged(changed);
      }
   }

   protected ResourceBundle getBundle()
   {
      if (bundle == null)
      {
         bundle = ResourceBundle.getBundle(BUNDLE_NAME);
      }
      return bundle;
   }

   class ModelDiffViewerContentProvider implements ITreeContentProvider
   {

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      // empty implementation
      }

      public boolean isDeleted(Object element)
      {
         return false;
      }

      public void dispose()
      {
         inputChanged(ModelDiffViewer.this, getInput(), null);
      }

      public Object getParent(Object element)
      {
         if (element instanceof IDiffElement)
            return ((IDiffElement) element).getParent();
         return null;
      }

      public final boolean hasChildren(Object element)
      {
         if (element instanceof IDiffContainer)
            return this.getChildren(element).length > 0 ? true : false;
         return false;
      }

      public final Object[] getChildren(Object element)
      {

         if (element instanceof IDiffContainer)
         {
            ArrayList filteredChildren = new ArrayList();

            Object[] children = ((IDiffContainer) element).getChildren();
            for (int i = 0; i < children.length; i++)
            {
               IDiffElement dElement = (IDiffElement) children[i];
               {
                  if (dElement instanceof DiffNode)
                  {
                     boolean isEmptyNode = false;
                     IDiffElement[] diffNodeChildren = ((DiffNode) dElement)
                           .getChildren();
                     if (diffNodeChildren.length == 1)
                     {
                        ComparableModelElementNode leftNode = (ComparableModelElementNode) ((DiffNode) diffNodeChildren[0])
                              .getLeft();
                        ComparableModelElementNode rightNode = (ComparableModelElementNode) ((DiffNode) diffNodeChildren[0])
                              .getRight();

                        if (leftNode != null && StringUtils.isEmpty(leftNode.getName())
                              || rightNode != null && StringUtils.isEmpty(rightNode.getName()))
                        {
                           isEmptyNode = true;
                        }
                     }
                     else
                     {
                        ComparableModelElementNode leftNode = (ComparableModelElementNode) ((DiffNode) dElement)
                              .getLeft();
                        ComparableModelElementNode rightNode = (ComparableModelElementNode) ((DiffNode) dElement)
                              .getRight();

                        if (leftNode != null && StringUtils.isEmpty(leftNode.getName())
                              || rightNode != null && StringUtils.isEmpty(rightNode.getName()))
                        {
                           isEmptyNode = true;
                        }
                     }

                     if (!isEmptyNode)
                     {

                        int kind = dElement.getKind();

                        if (additionFilter)
                        {
                           if (kind == Differencer.ADDITION
                                 || (kind == Differencer.CHANGE && diffNodeChildren.length > 0))
                           {
                              filteredChildren.add(dElement);
                           }
                        }
                        else if (deletionFilter)
                        {
                           if (kind == Differencer.DELETION
                                 || (kind == Differencer.CHANGE && diffNodeChildren.length > 0))
                           {
                              filteredChildren.add(dElement);
                           }
                        }
                        else if (changeFilter)
                        {
                           if (kind == Differencer.CHANGE)
                           {
                              filteredChildren.add(dElement);
                           }
                        }
                        else if (allFilter)
                        {
                           filteredChildren.add(dElement);
                        }
                     }
                  }

               }
            }
            return filteredChildren.toArray();
         }

         return new Object[0];
      }

      public Object[] getElements(Object element)
      {
         return getChildren(element);
      }
   }

}
