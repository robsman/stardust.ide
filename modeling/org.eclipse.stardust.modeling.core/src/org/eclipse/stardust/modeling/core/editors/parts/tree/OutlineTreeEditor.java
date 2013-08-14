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
package org.eclipse.stardust.modeling.core.editors.parts.tree;

import java.util.Collections;

import org.eclipse.emf.common.ui.celleditor.ExtendedTreeEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ElUtils;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetTypeDeclarationIdCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class OutlineTreeEditor extends ExtendedTreeEditor
{   
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;   
   private static final XpdlPackage XPDL_PKG = XpdlPackage.eINSTANCE; 
   
   private EditPartViewer viewer;
   private TextCellEditor editor;
   private ICellEditorListener editorListener; 
   private EObject model;
   private String currentValue;
   
   public OutlineTreeEditor(Tree tree, EditPartViewer editPartViewer)
   {
      super(tree);
      viewer = editPartViewer;
   }
   
   public void setItem(TreeItem treeItem, Object model)
   {
      super.setItem(treeItem);
      this.model = (EObject) model;
      currentValue = getNameFromModel();
      createCellEditor(tree);
      
      Rectangle area = treeItem.getBounds();   
      if(editor != null)
      {
         editor.getControl().setBounds(area);
         editor.activate();
         editor.setValue(getNameFromModel());
         editor.getControl().setVisible(true);
         try
         {
            editor.setFocus();
         }
         catch(SWTException e)
         {
            
         }
      }
   }
      
   private void createCellEditor(Tree tree)
   {
      editor = new TextCellEditor(tree);
      editorListener = new ICellEditorListener()
      {
         public void applyEditorValue()
         {
            editItem(getItem());
         }

         public void cancelEditor()
         {
            bringDown();
         }

         public void editorValueChanged(boolean oldValidState, boolean newValidState)
         {
         }         
      };
      editor.addListener(editorListener);
   }
   
   private String getNameFromModel()
   {
      if(model instanceof IIdentifiableElement)
      {
         return ((IIdentifiableElement) model).getName();
      }
      else if(model instanceof DiagramType)
      {
         String diagramName = ((DiagramType) model).getName();
         if(diagramName == null)
         {
            return ""; //$NON-NLS-1$ 
         }
         return diagramName;
      }
      else if(model instanceof TypeDeclarationType)
      {
         return ((TypeDeclarationType) model).getName();
      }
      else if(model instanceof ExternalPackage)
      {
         return ((ExternalPackage) model).getName();
      }
      return ""; //$NON-NLS-1$
   }
   
   protected void editItem(TreeItem treeItem)
   {
      if(editor == null)
      {
         return;
      }
      String newValue = (String) editor.getValue();
      // must not be empty
      if(StringUtils.isEmpty(newValue.trim()))
      {
         bringDown();
         return;
      }
      // no change, nothing to do
      if(currentValue.equals(newValue))
      {
         bringDown();
         return;         
      }
      
      String computedId = null;
      if(!(model instanceof DiagramType))
      {
         computedId = NameIdUtils.createIdFromName(null, model, newValue);
      }
      
      EditDomain domain = viewer.getEditDomain();
      CompoundCommand command = new CompoundCommand();
      if(model instanceof IIdentifiableElement)         
      {
         if (model instanceof DataType)            
         {
            ChangeRecorder targetRecorder = new ChangeRecorder();
            targetRecorder.beginRecording(Collections.singleton(model.eContainer()));
            ElUtils.updateTransitionConditions((DataType)model, ((DataType) model).getId(), computedId);
            final ChangeDescription change = targetRecorder.endRecording();
            targetRecorder.dispose();
            
            Command cmd = new Command()
            {
               public void execute()
               {
               }

               public void undo()
               {
                  change.applyAndReverse();
               }

               public void redo()
               {
                  change.applyAndReverse();
               }
            };                        
            command.add(cmd);
         }
    
         Command cmd = new SetValueCmd(model, CWM_PKG.getIIdentifiableElement_Name(), newValue);
         command.add(cmd);

         if (GenericUtils.getAutoIdValue())
         {         
            Command cmdId = new SetValueCmd(model, CWM_PKG.getIIdentifiableElement_Id(), computedId);
            command.add(cmdId); 
         }         
      }
      else if(model instanceof DiagramType)
      {
         Command cmd = new SetValueCmd(model, CWM_PKG.getDiagramType_Name(), newValue);
         command.add(cmd);         
      }
      else if(model instanceof TypeDeclarationType)
      {
         Command cmd = new SetValueCmd(model, XPDL_PKG.getTypeDeclarationType_Name(), newValue);
         command.add(cmd);         

         if (GenericUtils.getAutoIdValue())
         {                  
            Command cmdId = new SetValueCmd(model, XPDL_PKG.getTypeDeclarationType_Id(), computedId);
            command.add(cmdId);
   
            SetTypeDeclarationIdCommand tdCmd = new SetTypeDeclarationIdCommand((TypeDeclarationType) model, computedId);
            command.add(tdCmd);
         }
      }
      else if (model instanceof ExternalPackage)
      {
         Command cmd = new SetValueCmd(model, XPDL_PKG.getExternalPackage_Name(), newValue);
         command.add(cmd);         
      }
      
      domain.getCommandStack().execute(command);      
      bringDown();
   }
   
   private void bringDown()
   {
      editor.removeListener(editorListener);
      editor.deactivate();
      editor.dispose();
      editor = null;
   }
}