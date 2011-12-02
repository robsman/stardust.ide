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
package org.eclipse.stardust.modeling.core.editors.cap;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


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
      viewer.addCheckStateListener(new ICheckStateListener() {
         public void checkStateChanged(CheckStateChangedEvent event) 
         {
            Object element = event.getElement();    
            // check or uncheck children
            if (event.getChecked()) 
            {
               // here the elements also has to be checked 
               if(element instanceof ICheck)
               {
                  // model recursive - may change name and id
                  ((ICheck) element).setChecked(true, localNameIdCache);
               }
               // view recursive
               viewer.setSubtreeChecked(element, true);
            }
            else if(!event.getChecked())
            {
               if(element instanceof ICheck)
               {
                  // model recursive
                  ((ICheck) element).setChecked(false, localNameIdCache);
               }
               viewer.setSubtreeChecked(element, false);              
            }
            
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
               // scan input container if input is valid (no duplicate Ids)
               if(!input.getContainer().hasDuplicateIds())
               {
                  okButton.setEnabled(true);
               }  
               else
               {
                  okButton.setEnabled(false);                  
               }
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
         if(property == ReferenceValueDialog.NAME_COLUMN)
         {
            if(element instanceof ContentDecorator)
            {
               if(!((ContentDecorator) element).isChecked())
               {
                  return true;                  
               }
            }
         }
         else if(property == ReferenceValueDialog.ID_COLUMN)
         {
            if(element instanceof ContentDecorator)
            {
               if(!((ContentDecorator) element).isChecked())
               {
                  return true;                  
               }
            }
         }         
         return false;
      }

      public Object getValue(Object element, String property)
      {
         if(property == ReferenceValueDialog.NAME_COLUMN)
         {
            if(element instanceof ContentDecorator)
            {
               Object content = ((ContentDecorator) element).getContent();
               if(content instanceof Map.Entry)
               {
                  content = ((Map.Entry) content).getValue();                  
               }
               if(content instanceof IIdentifiableElement)
               {
                  return ((IIdentifiableElement) content).getName();
               }
            }            
         }
         else if(property == ReferenceValueDialog.ID_COLUMN)
         {
            if(element instanceof ContentDecorator)
            {
               Object content = ((ContentDecorator) element).getContent();
               if(content instanceof Map.Entry)
               {
                  content = ((Map.Entry) content).getValue();                  
               } 
               if(content instanceof IIdentifiableElement)
               {
                  return ((IIdentifiableElement) content).getId();
               }
            }            
         }         
         return null;
      }

      public void modify(Object element, String property, Object value)
      {
         if(value == null) 
         {
            return;
         }
         ContentDecorator contentDecorator = null;
         if(element instanceof TreeItem)
         {
            contentDecorator = (ContentDecorator) ((TreeItem) element).getData();
         }
         Object content = ((ContentDecorator) contentDecorator).getContent();
                  
         if(property == ReferenceValueDialog.NAME_COLUMN)
         {            
            if(content instanceof Map.Entry)
            {
               content = ((Map.Entry) content).getValue();                  
            }    
            if(content instanceof IIdentifiableElement)
            {
               ((IIdentifiableElement) content).setName((String) value);
            }
         }
         else if(property == ReferenceValueDialog.ID_COLUMN)
         {
            if(content instanceof Map.Entry)
            {
               content = ((Map.Entry) content).getValue();                  
            }
            if(content instanceof IIdentifiableElement)
            {
               ((IIdentifiableElement) content).setId((String) value);
            }
         }         
         viewer.update(contentDecorator, null);
         
         // if we have a duplicate Id the ok button must be disabled
         Button okButton = getButton(IDialogConstants.OK_ID);
         if (okButton != null)
         {
            // scan input container if input is valid (no duplicate Ids)
            if(!input.getContainer().hasDuplicateIds())
            {
               okButton.setEnabled(true);
            }  
            else
            {
               okButton.setEnabled(false);                  
            }
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
         if(parentElement instanceof InputContainer)
         {
            return new Object[] {((InputContainer) parentElement).getContainer()};
         }
         else if(parentElement instanceof IContainerTypes)
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
         if(children.equals(EMPTY_ARRAY))
         {
            return false;            
         }
         return true;
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
         String returnValue = "";          //$NON-NLS-1$
         // label for the parents 'All', 'All DataTypes'
         if (element instanceof IContainerTypes)
         {               
            if(index == 0)
            {
               return ((IContainerTypes) element).getLabel();
            }
         }
         else if(element instanceof ContentDecorator)
         {
            Object content = ((ContentDecorator) element).getContent();
            if(content instanceof Map.Entry)
            {
               content = ((Map.Entry) content).getValue();                  
            }
            if(content instanceof IIdentifiableElement)
            {
               if(index == 0)
               {                  
                  returnValue = ((IIdentifiableElement) content).getName();
               }
               else if(index == 1)
               {                  
                  returnValue = ((IIdentifiableElement) content).getId();
               }                  
            }
         }
         return returnValue;
      }

      // is called before getColumnText
      public Color getForeground(Object element, int index)
      {
         if(initFlag)
         {
            viewer.setChecked(element, true);               
         }
         Entry entry = null;   
         ContentDecorator contentDecorator = null;         
         boolean isName = false;
         // label for the parents 'All', 'All DataTypes'
         if (element instanceof IContainerTypes)
         {               
            return null;
         }
         else if(element instanceof ContentDecorator)
         {
            contentDecorator = (ContentDecorator) element;
            Object content = contentDecorator.getContent();
            if(content instanceof Map.Entry)
            {
               entry = (Entry) content;
               content = ((Map.Entry) content).getValue();                  
            }
            if(content instanceof IIdentifiableElement)
            {
               if(index == 0)
               {                
                  isName = true;
               }
               else if(index == 1)
               {                  
                  isName = false;
               }                  
            }
            // checked elements will not be used (copy by reference)
            if(contentDecorator.isChecked())
            {
               contentDecorator.setDuplicate(null);
               return ColorConstants.black;               
            }            
            if(entry != null)
            {
               Integer result = localNameIdCache.checkElement(entry);
               contentDecorator.setDuplicate(result);
               if(result != null)
               {
                  if(result.intValue() == NameIDCache.DUPLICATE_BOTH)
                  {
                     return ColorConstants.red;
                  }
                  else if(result.intValue() == NameIDCache.DUPLICATE_NAME)
                  {
                     if(isName)
                     {
                        return ColorConstants.red;                        
                     }
                  }
                  else if(result.intValue() == NameIDCache.DUPLICATE_ID)
                  {
                     if(!isName)
                     {
                        return ColorConstants.red;                        
                     }                     
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