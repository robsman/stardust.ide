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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.CreateSubprocess;
import org.eclipse.stardust.modeling.core.editors.cap.DiagramMerger;
import org.eclipse.stardust.modeling.core.editors.cap.StoreObject;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.IWorkbenchPart;


public class CreateSubprocessFromSelectionAction extends DeleteAction
{
   private WorkflowModelEditor editor;
   private StoreObject storage;
   protected List copySet;

   public CreateSubprocessFromSelectionAction(IWorkbenchPart part)
   {
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setId(DiagramActionConstants.CREATE_SUBPROCESS_FROM_SELECTION);
      setText(Diagram_Messages.LB_CreateSubprocess);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif")); //$NON-NLS-1$
      setToolTipText(Diagram_Messages.LB_CreateSubprocess);
   }

   protected boolean calculateEnabled()
   {
      // validate selection
      List<Object> selection = getSelectedObjects();
      if(selection == null || selection.size() <= 0)
      {
         return false;
      }
      if(CopyPasteUtil.validateSelectionForSubprocess(selection))
      {
         AbstractNodeSymbolEditPart ep = (AbstractNodeSymbolEditPart) selection.get(0);
         EObject modelElement = (EObject) ep.getModel();
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel(modelElement));
         
         ModelServer server = editor.getModelServer();
         if(server != null && server.requireLock((EObject) ep.getModel()))
         {
            return false;
         }
         return true;
      }
      return false;
   }

   public void run()
   {
      List content = CopyPasteUtil.createCopySet(new Integer(CopyPasteUtil.SELECTION_GLOBAL_DIAGRAM), 
            getSelectedObjects(), editor, false);
      copySet = extractStorage(content);
      final EditPart targetEP = PoolLaneUtils.findTargetEditPart((WorkflowModelEditor) getWorkbenchPart());
      setPosition(targetEP);
      final LaneSymbol lane = getLane(targetEP);
      // add gateways if necessary
      List selection = getSelectedObjects();
      
      // cut      
      List symbols = new ArrayList();
      List modelSymbols = new ArrayList();
      filterSelection(symbols, modelSymbols, selection);

      CreateSubprocess createSubprocess = new CreateSubprocess(selection, storage, editor);
      createSubprocess.setLaneSymbol(lane);
      
      // start recording
      ChangeRecorder targetRecorder = new ChangeRecorder();
      targetRecorder.beginRecording(Collections.singleton(storage.getSourceModel()));
                  
      createSubprocess.deleteSymbols(symbols);
      createSubprocess.deleteElements(modelSymbols);
      
      createSubprocess.createElements();
      createSubprocess.updateStorage();
      createSubprocess.reconnectConnections();

      DiagramMerger util = new DiagramMerger(storage.getTargetModel(), copySet, storage, DiagramMerger.CREATE_SUBPROCESS);      
      util.merge();
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

   private void setPosition(EditPart targetEP)
   {
      if(targetEP.getModel() != null)
      {
         INodeSymbol model = (INodeSymbol) targetEP.getModel();
         Point location = new Point(model.getXPos(), model.getYPos());
         storage.setLocation(location);
      }
   }
   
   private LaneSymbol getLane(EditPart targetEP)
   {
      if(targetEP.getModel() != null)
      {
         INodeSymbol model = (INodeSymbol) targetEP.getModel();
         Point location = new Point(model.getXPos(), model.getYPos());
         storage.setLocation(location);
         if(model.eContainer() != null)
         {
            EObject container = model.eContainer();
            if(container instanceof LaneSymbol)
            {
               return (LaneSymbol) container;
            }
         }
      }
      return null;
   }
   
   private List extractStorage(List currentContent)
   {
      List copySet = new ArrayList();
      for(int i = 0; i < currentContent.size(); i++)
      {
         Object entry = currentContent.get(i);
         if(entry instanceof StoreObject)
         {
            storage = (StoreObject) entry;
         }
         else
         {
            copySet.add(entry);
         }
      }
      return copySet;
   }   
   
   public static void filterSelection(List symbols, List modelSymbols, List selectionList)
   {
      List gateways = new ArrayList();
      for(int i = 0; i < selectionList.size(); i++)
      {
         Object entry = selectionList.get(i); 
         EObject symbol = (EObject) ((AbstractEditPart) entry).getModel();              
         if(symbol instanceof AnnotationSymbolType
               || symbol instanceof TextSymbolType)
         {
            symbols.add(symbol);            
         }
         else if((symbol instanceof ActivitySymbolType 
               || symbol instanceof AbstractEventSymbol)
               && ((IModelElementNodeSymbol) symbol).getModelElement() != null)
         {
            modelSymbols.add(symbol);
            if(symbol instanceof ActivitySymbolType)
            {
               List gatewaySymbols = ((ActivitySymbolType) symbol).getGatewaySymbols();
               if(!gatewaySymbols.isEmpty())
               {
                  gateways.addAll(gatewaySymbols);
               }
            }            
         }
         else
         {
            symbols.add(symbol);
         }
      }
      for(int i = 0; i < gateways.size(); i++)
      {
         GatewaySymbol gatewaySymbol = (GatewaySymbol) gateways.get(i);
         if(!symbols.contains(gatewaySymbol))
         {
            symbols.add(gatewaySymbol);
         }
      }      
   }     
}