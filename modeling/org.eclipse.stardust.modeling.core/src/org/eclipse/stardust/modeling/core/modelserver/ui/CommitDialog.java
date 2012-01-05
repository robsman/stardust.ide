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
package org.eclipse.stardust.modeling.core.modelserver.ui;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.modelserver.CommitUtil;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.ui.ModelContainer.Container;
import org.eclipse.stardust.modeling.core.ui.IModelElementContainer;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class CommitDialog extends Dialog
{         
   private WorkflowModelEditor editor;
   private ModelContainer input;
   private Container container;
   private CommitUtil commitUtil;
   
   private EObject[] preCheckedElements;      
   private List<EObject> checkedElements;
   private boolean isValidCommitString = false;
   
   // initial dialog size
   private static final int MIN_WIDTH = 450;
   private static final int MIN_HEIGHT = 600;         
   
   public static final Object[] EMPTY_ARRAY = new Object[0];
   // identifiers for Cell editor
   public static final String ELEMENTS_COLUMN = "Elements";  //$NON-NLS-1$
   public static final String MERGE_COLUMN = "Merge";  //$NON-NLS-1$
   public static final String KEEPLOCK = "Keep Lock";  //$NON-NLS-1$
   
   // the viewer in the dialog
   private CheckboxTreeViewer viewer;
   
   private LabeledText commitText;   
   private String commitString;   
   
   private Button keepLockscheckBox;
   private boolean keepLocks = false;
   
   private ModifyListener commitTextListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         // validate
         validateCommitText();
      }
   };
   
   public CommitDialog(WorkflowModelEditor editor, CommitUtil util)
   {      
      super(editor.getSite().getShell());
      commitUtil = util;
      setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);      
      this.editor = editor;
      
      input = new ModelContainer(editor);
      container = input.getContainer();
   }

   /**
    * prechecked elements
    * 
    * @param lockables
    */
   public void setSelection(EObject[] selection)
   {
      preCheckedElements = selection;      
   }
   
   public void create()
   {
      super.create();
      validateSelection(viewer.getCheckedElements()); 
   }
   
   public Point getInitialSize() 
   {
      return new Point(MIN_WIDTH, MIN_HEIGHT);
   }   
   
   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(Diagram_Messages.LB_CommitModelElement);
   }   

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      
      FormBuilder.createLabel(composite, Diagram_Messages.MSG_FLUSH_COMMAND_STACK, 1);
      
      Tree tree = new Tree(composite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);
      
      // the headlines of the columns
      String[] columnPropertiesCommit = new String[] {
            MERGE_COLUMN};
      
      viewer = new CheckboxTreeViewer(tree);      
      viewer.setColumnProperties(columnPropertiesCommit);
      TableUtil.createColumns(tree, columnPropertiesCommit);
      TableUtil.setInitialColumnSizes(tree, new int[] {100});

      
      
      viewer.setLabelProvider(new TableLabelProvider());      
      viewer.setContentProvider(new ContentProvider());    

      keepLockscheckBox = FormBuilder.createCheckBox(composite, KEEPLOCK);      
      keepLockscheckBox.setSelection(false);
      
      commitText = FormBuilder.createLabeledTextArea(composite, Diagram_Messages.LBL_COMMENT);
      commitText.getText().addModifyListener(commitTextListener);

      commitUtil.setCommitContent(container);
      
      // add a listener for the checkboxes, checks/unchecks also children
      viewer.addCheckStateListener(new ICheckStateListener() {
         public void checkStateChanged(CheckStateChangedEvent event) 
         {
            Object element = event.getElement();    
            if(element instanceof IModelElementContainer)
            {
               ((IModelElementContainer) element).setChecked(event.getChecked());
               // view recursive
               viewer.setSubtreeChecked(element, event.getChecked());
            }            
            // everything must be updated - model may have changed
            Object[] elements = viewer.getCheckedElements();
            TreePath[] treePaths = viewer.getExpandedTreePaths();
            viewer.refresh();
            viewer.expandAll();
            viewer.setExpandedTreePaths(treePaths);
            viewer.setCheckedElements(elements);
            validateSelection(elements); 
         }
      });      
      
      viewer.setUseHashlookup(true);            
      viewer.setInput(input);            
      viewer.expandAll();
      
      if (!commitUtil.getUnChangedElements().isEmpty())
      {
         viewer.setGrayedElements(commitUtil.getUnChangedElements().toArray());      
      }
      if (preCheckedElements != null && preCheckedElements.length != 0)
      {
         viewer.setCheckedElements(preCheckedElements);         
      }            
      validateSelection(viewer.getCheckedElements()); 
      
      return composite;
   }   
   
   private void validateCommitText()
   {
      String commitString = commitText.getText().getText();
      String validateString = commitString.trim();
      if(StringUtils.isEmpty(validateString)
            || validateString.length() < 10
            || validateString.length() > 100)
      {
         isValidCommitString = false;
      } 
      else
      {
         isValidCommitString = true;         
      }
      validateDialog();
   }
   
   private void validateSelection(Object[] elements)
   {
      // only changed, new or removed elements are valid selections
      checkedElements = commitUtil.getEObjectsFromSelection(elements);      
      validateDialog();
   }

   private void validateDialog()
   {
      if(!isValidCommitString)
      {
         commitText.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         commitText.getLabel().setToolTipText(Diagram_Messages.MSG_NO_COMMIT_TEXT);         
      }
      else
      {
         commitText.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      
      Button okButton = getButton(IDialogConstants.OK_ID);
      if (okButton != null)
      {
         if(checkedElements.isEmpty() || !isValidCommitString)
         {
            okButton.setEnabled(false);
         }
         else
         {
            okButton.setEnabled(true);
         }
      }
   }
   
   public List<EObject> getCheckedElements()
   {
      return checkedElements;
   }

   public boolean getKeepLocks()
   {
      return keepLocks;
   }

   public String getCommitString()
   {
      return commitString;
   }

   protected void okPressed()
   {      
      commitString = commitText.getText().getText();
      keepLocks = keepLockscheckBox.getSelection();
      super.okPressed();
   }
   
   /////
      
   // the content provider for the Viewer
   class ContentProvider implements ITreeContentProvider
   {      
      public Object[] getElements(Object inputElement)
      {
         return getChildren(inputElement);
      }
      
      public Object[] getChildren(Object parentElement)
      {
         if(parentElement instanceof ModelContainer)
         {
            return new Object[] {((ModelContainer) parentElement).getContainer()};            
         }
         if(parentElement instanceof IModelElementContainer)
         {
            return ((IModelElementContainer) parentElement).getContent().toArray();
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
         String returnValue = "";    //$NON-NLS-1$
         if(index == 0)
         {
            if (element instanceof IModelElementContainer)
            {               
               return ((IModelElementContainer) element).getLabel();
            }
            if(element instanceof EObject)
            {
               return GenericUtils.getElementId((EObject) element);
            }                     
         }
         return returnValue;
      }
      
      public boolean isLabelProperty(Object element, String property)
      {
         return false;
      }
      
      public Image getColumnImage(Object element, int columnIndex)
      {
         if(columnIndex == 0)
         {         
            if(element instanceof EObject)
            {            
               String iconPath = editor.getIconFactory().getIconFor((EObject) element);
               Image image = DiagramPlugin.getDefault().getImageManager().getImage(iconPath);               
               
               Image imageWithOverlay = ModelServerUtils.getIconWithOverlay(commitUtil, (EObject) element, image);
               if(imageWithOverlay != null)
               {
                  return imageWithOverlay;
               }
               
               return image;
            }     
            else if(element instanceof IModelElementContainer)
            {
               return ((IModelElementContainer) element).getImage();
            }
         }
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

      public Color getBackground(Object element, int columnIndex)
      {
         return null;
      }

      public Color getForeground(Object element, int columnIndex)
      {
         if(element instanceof EObject
               && !commitUtil.hasChanged((EObject) element)
               && !commitUtil.isRemovedElement((EObject) element)
               && !commitUtil.isNewElement((EObject) element))
         {
            return ColorConstants.gray;            
         }         
         return ColorConstants.black;         
      }            
   }
}