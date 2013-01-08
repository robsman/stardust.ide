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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.ConvertGatewayUtil;

import org.eclipse.ui.IWorkbenchPart;

public class ConvertGatewayAction extends SelectionAction
{
   public ConvertGatewayAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.CONVERT_GATEWAYS);
      setText(Diagram_Messages.LB_Convert_Gateways);
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1
         && (getProcess() != null || getModel() != null);
   }

   public void run()
   {
      EObject element = null;      
      ProcessDefinitionType process = getProcess();
      if (process != null)
      {
         element = (EObject) process;
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(process);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);  
            return;
         }
         if(ConvertGatewayUtil.findStartActivity(process) == null)
         {
            ModelServerUtils.showMessageBox(Diagram_Messages.MSG_INVALID_ACTIVITY_NETWORK);  
            return;                           
         }         
      }

      ModelType model = getModel();
      if (model != null)
      {
         element = (EObject) model;         
         for (ProcessDefinitionType process_ : model.getProcessDefinition())
         {
            Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(process_);
            if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
            {
               ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);  
               return;
            }    
            if(!process_.getActivity().isEmpty() && ConvertGatewayUtil.findStartActivity(process_) == null)
            {
               ModelServerUtils.showMessageBox(Diagram_Messages.MSG_INVALID_ACTIVITY_NETWORK);  
               return;               
            }
         }         
      }
      
      ChangeRecorder targetRecorder = new ChangeRecorder();
      targetRecorder.beginRecording(Collections.singleton(ModelUtils.findContainingModel(element)));
      
      ConvertGatewayUtil util = new ConvertGatewayUtil(element);
      util.convert();
      
      if(util.isModified())
      {
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
         getCommandStack().execute(cmd);         
      }
   }

   private ProcessDefinitionType getProcess()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ProcessDefinitionType)
         {
            if(!((ProcessDefinitionType) model).getActivity().isEmpty())
            {
               return (ProcessDefinitionType) model;               
            }
         }
      }
      return null;
   }

   private ModelType getModel()
   {
      Object selection = getSelectedObjects().get(0);
      ModelType model_ = null;
      
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ModelType)
         {
            model_ = (ModelType) model;
         }
      }
      
      if(model_ != null)
      {
         if(model_.getProcessDefinition().isEmpty())
         {
            return null;
         }
         
         for(ProcessDefinitionType process : model_.getProcessDefinition())
         {
            if(!process.getActivity().isEmpty())
            {
               return model_;
            }            
         }
      }      
      
      return null;
   }   
}