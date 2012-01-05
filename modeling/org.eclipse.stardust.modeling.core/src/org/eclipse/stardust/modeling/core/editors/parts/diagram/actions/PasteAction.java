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
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.AbstractMerger;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.DiagramMerger;
import org.eclipse.stardust.modeling.core.editors.cap.OutlineMerger;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


public class PasteAction extends AbstractPasteAction
{
   public PasteAction(WorkflowModelEditor editor)
   {
      super(editor);
      setId(ActionFactory.PASTE.getId());      
      setText(Diagram_Messages.LB_PasteElement);
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
      setToolTipText(Diagram_Messages.LB_PasteElement);
      setAccelerator(SWT.CTRL | 'V');
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
            return !storage.isCopySymbols();            
         }        
         return isEnabled;
      }
      // user wishes to copy and paste between outline                
      // here we can have also diagram process or model diagram (what may contain more)
      isEnabled = isValidForOutline();
      if(isEnabled)
      {
         return !storage.isCopySymbols();            
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
      // user wishes to copy and paste between outline
      if(isValid.intValue() == CopyPasteUtil.SELECTION_OUTLINE)
      {
         storage.setTargetProcess(CopyPasteUtil.isProcessCategoryNode(getSelectedObjects().get(0)));
         util = new OutlineMerger(targetModel, copySet, storage);      
      }
      // user wishes to copy and paste between diagrams
      else
      {
         EObject targetObject = getTargetObject();
         if(targetObject != null)
         {
            ProcessDefinitionType targetProcess = ModelUtils.findContainingProcess(targetObject);
            if(targetProcess != null)
            {
               storage.setTargetProcess(targetProcess);
            }            
         }
         util = new DiagramMerger(targetModel, copySet, storage, DiagramMerger.CREATE_ALL);      
      }
      util.merge();
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