/*******************************************************************************
 * Copyright (c) 2011, 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors.cap;

import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

public class ReferenceValueDialog extends Dialog
{
   // initial dialog size
   private static final int MIN_WIDTH = 400;
   private static final int MIN_HEIGHT = 400;

   public static final Object[] EMPTY_ARRAY = new Object[0];
   // identifiers for Cell editor
   public static final String NAME_COLUMN = "Name";  //$NON-NLS-1$
   public static final String ID_COLUMN = "ID";  //$NON-NLS-1$
   // the viewer in the dialog
   private CheckboxTreeViewer viewer;
   // Content for the viewer
   private InputContainer input;
   private boolean initFlag = true;
   private NameIDCache localNameIdCache;

   public ReferenceValueDialog(Shell shell, InputContainer referenceValueInput, NameIDCache localNameIdCache)
   {
      super(shell);
      setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
      this.input = referenceValueInput;
      this.localNameIdCache = localNameIdCache;
   }

   public Point getInitialSize()
   {
      return new Point(MIN_WIDTH, MIN_HEIGHT);
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(Diagram_Messages.ReferenceValueDialog_Title);
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      String info = Diagram_Messages.ReferenceValueDialog_Info;
      FormBuilder.createLabel(composite, info, 1);

      Tree tree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);

      // the headlines of the columns
      String[] columnProperties = new String[] {
            NAME_COLUMN, ID_COLUMN};

      // checkboxes to check and uncheck elements
      viewer = new CheckboxTreeViewer(tree);

      viewer.setColumnProperties(columnProperties);
      TableUtil.createColumns(tree, columnProperties);
      TableUtil.setInitialColumnSizes(tree, new int[] {61, 39});

      // only one editor for one column, so we need 2 (one for name, one for id)
      viewer.setCellModifier(new CellModifier(this, viewer));
      TextCellEditor[] editors = {new TextCellEditor(tree), new TextCellEditor(tree)};
      viewer.setCellEditors(editors);
      viewer.setLabelProvider(new TableLabelProvider());

      viewer.setContentProvider(new ContentProvider());

      // add a listener for the checkboxes, checks/unchecks also children
      viewer.addCheckStateListener(new ICheckStateListener()
      {
         public void checkStateChanged(CheckStateChangedEvent event)
         {
            Object element = event.getElement();

            // check or uncheck children
            if (element instanceof ICheck)
            {
               ((ICheck) element).setChecked(event.getChecked(), localNameIdCache);
            }
            viewer.setSubtreeChecked(element, event.getChecked());

            // everything must be updated - model may have changed
            Object[] elements = viewer.getCheckedElements();
            TreePath[] treePaths = viewer.getExpandedTreePaths();
            viewer.refresh();
            viewer.expandAll();
            viewer.setExpandedTreePaths(treePaths);
            viewer.setCheckedElements(elements);

            // if we now have a duplicate Id, disable button
            Button okButton = getButton(IDialogConstants.OK_ID);
            if (okButton != null)
            {
               okButton.setEnabled(!input.getContainer().hasDuplicateIds());
            }
         }
      });
      viewer.setUseHashlookup(true);
      // must be all checked, model
      input.getContainer().setChecked(true, null);
      viewer.setInput(input);
      viewer.setAllChecked(true);
      // expand tree
      viewer.expandAll();
      // the viewer is not setting all checkboxes, so we will do it with this flag
      initFlag = false;

      return composite;
   }

   // modifier for the unchecked name/id entries
   class CellModifier implements ICellModifier
   {
      ReferenceValueDialog dialog;

      public CellModifier(ReferenceValueDialog dialog, CheckboxTreeViewer viewer)
      {
         this.dialog = dialog;
      }

      public boolean canModify(Object element, String property)
      {
         return (ID_COLUMN.equals(property) || NAME_COLUMN.equals(property))
               && (element instanceof ContentDecorator)
               && !((ContentDecorator) element).isChecked();
      }

      public Object getValue(Object element, String property)
      {
         if (element instanceof ContentDecorator)
         {
            Object content = ((ContentDecorator) element).getContent();
            if (content instanceof Map.Entry)
            {
               content = ((Map.Entry<?, ?>) content).getValue();
            }
            if (content instanceof IIdentifiableElement)
            {
               if (ID_COLUMN.equals(property))
               {
                  return ((IIdentifiableElement) content).getId();
               }
               else if (NAME_COLUMN.equals(property))
               {
                  return ((IIdentifiableElement) content).getName();
               }
            }
         }
         return null;
      }

      public void modify(Object element, String property, Object value)
      {
         if (value == null)
         {
            return;
         }

         ContentDecorator contentDecorator = null;
         if (element instanceof TreeItem)
         {
            contentDecorator = (ContentDecorator) ((TreeItem) element).getData();
         }

         if (contentDecorator != null)
         {
            Object content = ((ContentDecorator) contentDecorator).getContent();
            if (content instanceof Map.Entry)
            {
               content = ((Map.Entry<?, ?>) content).getValue();
            }
            if (content instanceof IIdentifiableElement)
            {
               if (ID_COLUMN.equals(property))
               {
                  ((IIdentifiableElement) content).setId((String) value);
               }
               else if (NAME_COLUMN.equals(property))
               {
                  ((IIdentifiableElement) content).setName((String) value);
               }
               viewer.update(contentDecorator, null);
            }
         }

         // if we have a duplicate Id the ok button must be disabled
         Button okButton = getButton(IDialogConstants.OK_ID);
         if (okButton != null)
         {
            okButton.setEnabled(!input.getContainer().hasDuplicateIds());
         }
      }
   };

   // the content provider for the Viewer
   class ContentProvider implements ITreeContentProvider
   {
      public Object[] getElements(Object inputElement)
      {
         return getChildren(inputElement);
      }

      public Object[] getChildren(Object parentElement)
      {
         if (parentElement instanceof InputContainer)
         {
            return new Object[] {((InputContainer) parentElement).getContainer()};
         }
         else if (parentElement instanceof IContainerTypes)
         {
            return ((IContainerTypes) parentElement).getContent().toArray();
         }
         // has no children
         return EMPTY_ARRAY;
      }

      public Object getParent(Object element)
      {
         return null;
      }

      public boolean hasChildren(Object element)
      {
         Object[] children = getChildren(element);
         return children.length > 0;
      }

      public void dispose()
      {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }
   }

   class TableLabelProvider implements ITableLabelProvider, ITableColorProvider
   {
      public String getColumnText(Object element, int index)
      {
         // label for the parents 'All', 'All DataTypes'
         if (element instanceof IContainerTypes)
         {
            if (index == 0)
            {
               return ((IContainerTypes) element).getLabel();
            }
         }
         else if (element instanceof ContentDecorator)
         {
            Object content = ((ContentDecorator) element).getContent();
            if (content instanceof Map.Entry)
            {
               content = ((Map.Entry<?, ?>) content).getValue();
            }
            if (content instanceof IIdentifiableElement)
            {
               switch (index)
               {
               case 0: return ((IIdentifiableElement) content).getName();
               case 1: return ((IIdentifiableElement) content).getId();
               }
            }
         }
         return "";
      }

      // is called before getColumnText
      public Color getForeground(Object element, int index)
      {
         if (initFlag)
         {
            viewer.setChecked(element, true);
         }
         Map.Entry<?, ?> entry = null;
         ContentDecorator contentDecorator = null;
         boolean isName = false;
         // label for the parents 'All', 'All DataTypes'
         if (element instanceof IContainerTypes)
         {
            return null;
         }
         else if (element instanceof ContentDecorator)
         {
            contentDecorator = (ContentDecorator) element;
            Object content = contentDecorator.getContent();
            if (content instanceof Map.Entry)
            {
               entry = (Map.Entry<?, ?>) content;
               content = ((Map.Entry<?, ?>) content).getValue();
            }
            if (content instanceof IIdentifiableElement)
            {
               isName = index == 0;
            }
            // checked elements will not be used (copy by reference)
            if (contentDecorator.isChecked())
            {
               contentDecorator.setDuplicate(null);
               return ColorConstants.black;
            }
            if (entry != null)
            {
               Integer result = localNameIdCache.checkElement(entry);
               contentDecorator.setDuplicate(result);
               if (result != null)
               {
                  switch (result)
                  {
                  case NameIDCache.DUPLICATE_ID:
                     if (!isName)
                     {
                        return ColorConstants.red;
                     }
                     break;
                  case NameIDCache.DUPLICATE_NAME:
                     if (isName)
                     {
                        return ColorConstants.red;
                     }
                     break;
                  case NameIDCache.DUPLICATE_BOTH:
                     return ColorConstants.red;
                  }
               }
            }
         }
         return ColorConstants.black;
      }

      public boolean isLabelProperty(Object element, String property)
      {
         return false;
      }

      public Color getBackground(Object element, int columnIndex)
      {
         return null;
      }

      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }

      public void addListener(ILabelProviderListener listener)
      {
      }

      public void dispose()
      {
      }

      public void removeListener(ILabelProviderListener listener)
      {
      }
   }
}