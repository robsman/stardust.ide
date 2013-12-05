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
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.ModelOidUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.AbstractMerger;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.DiagramMerger;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


public class PasteSymbolAction extends AbstractPasteAction
{
   public PasteSymbolAction(IWorkbenchPart part)
   {
      super(part);
      setText(Diagram_Messages.LB_PasteSymbol);
      setToolTipText(Diagram_Messages.LB_PasteSymbol);
      setId(DiagramActionConstants.PASTESYMBOL);      
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
      setDisabledImageDescriptor(sharedImages
            .getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
      clipboard = Clipboard.getDefault();
   }

   protected boolean calculateEnabled()
   {
      boolean isEnabled = false;
      if(!isValidSelection())
      {
         return false;
      }            
      // user wishes to copy and paste between diagrams
      if(isValid.intValue() != CopyPasteUtil.SELECTION_OUTLINE)
      {
         isEnabled = isValidForDiagram();
         if(isEnabled)
         {
            isEnabled = storage.isCopySymbols();            
         }            
      }
      if(isEnabled)
      {
         // must be same model
         if(!storage.getSourceModel().equals(targetModel))
         {
            return false;
         }
         // must be same process
         if(isValid.intValue() == CopyPasteUtil.SELECTION_PROCESS_DIAGRAM)
         {
            EObject targetObject = getTargetObject();
            if(targetObject != null)
            {
               ProcessDefinitionType targetProcess = ModelUtils.findContainingProcess(targetObject);
               if(targetProcess != null && targetProcess.equals(storage.getSourceProcess()))
               {
                  return true;
               }
            }
         } 
         if(isValid.intValue() == CopyPasteUtil.SELECTION_MODEL_DIAGRAM)
         {
            return true;            
         }         
      }
      return false;
   }
   
   public void run()
   {
      EObject checkSelection = CopyPasteUtil.getEObjectFromSelection(getSelectedObjects().get(0));
      if (!(checkSelection instanceof ModelType))
      {
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(checkSelection);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);         
            return;
         }                     
      }      
      
      if(storage.getSourceModel().equals(targetModel))
      {
         storage.setSameModel(true);
      }
      else
      {
         storage.setSameModel(false);         
      }
      
      ChangeRecorder targetRecorder = new ChangeRecorder();
      targetRecorder.beginRecording(Collections.singleton(targetModel));

      Integer isValid = CopyPasteUtil.validateSelection(copySet, false);
      AbstractMerger util;
      // user wishes to copy and paste between diagrams
      EObject targetObject = getTargetObject();
      if(targetObject != null)
      {
         ProcessDefinitionType targetProcess = ModelUtils.findContainingProcess(targetObject);
         if(targetProcess != null)
         {
            storage.setTargetProcess(targetProcess);
         }            
      }
      util = new DiagramMerger(targetModel, copySet, storage, DiagramMerger.CREATE_SYMBOLS);      
      
      WorkflowModelEditor targetEditor = GenericUtils.getWorkflowModelEditor(targetModel);
      ModelOidUtil modelOidUtil = null;
      if(targetEditor != null)
      {
         modelOidUtil = targetEditor.getModelManager().getModelOidUtil();            
      }      
      try
      {
         if(modelOidUtil != null)
         {
            modelOidUtil.setCopyPaste(true);
         }
         util.merge();
      }
      catch (Exception e)
      {
      }
      finally
      {
         if(modelOidUtil != null)
         {
            modelOidUtil.setCopyPaste(false);
         }         
      }      
      
      // execute command only if there are changes
      if (util.modelChanged())
      {
         CompoundCommand cmd = new CompoundCommand();
         // Diagram paste
         if(isValid.intValue() != CopyPasteUtil.SELECTION_OUTLINE)
         {
            if(copySet.get(0) instanceof LaneSymbol)
            {
               copyLanes = true;
            }                        
            final EditPart targetEditPart = storage.getTargetEditPart();
            if(targetEditPart instanceof LaneEditPart
                  || targetEditPart instanceof PoolEditPart)
            {               
               if(copyLanes)
               {
                  cmd.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) targetEditPart, new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE));
                     }               
                  });                     
               }
               else
               {
                  cmd.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) targetEditPart);
                     }
                  });                  
               }
               // here children of siblings must be ordered (if there are any)   
               cmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.reorderSiblings(targetEditPart, null);
                  }
               });
            }
         }
         
         final ChangeDescription change = targetRecorder.endRecording();
         targetRecorder.dispose();
         cmd.add(new Command()
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
         });
         getCommandStack().execute(cmd);
      }
      else
      {
         // stop recording
         if (targetRecorder.isRecording())
         {
            final ChangeDescription changes = targetRecorder.endRecording();
            targetRecorder.dispose();
            changes.apply();
         }
      }
   }
}