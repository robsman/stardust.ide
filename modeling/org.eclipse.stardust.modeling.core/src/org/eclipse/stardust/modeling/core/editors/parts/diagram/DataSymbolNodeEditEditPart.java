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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import java.util.Collections;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ElUtils;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.DataSymbolFigure;

public class DataSymbolNodeEditEditPart extends AbstractModelElementNodeSymbolEditPart
{
   @Override
   public Command getCommand(Request request)
   {
      Command command = super.getCommand(request);      
      if (command instanceof CompoundCommand && request instanceof DirectEditRequest) {
         return transitionConditionChangeCommand(request, command);
      }
      return command;            
   }

   private Command transitionConditionChangeCommand(Request request, Command command)
   {
      try {
         DirectEditRequest editRequest = (DirectEditRequest)request;
         String newValue = editRequest.getCellEditor().getValue().toString();
         DataSymbolType dataSymbol = (DataSymbolType)this.getModel();
         DataType dataType = (DataType) dataSymbol.getModelElement();
         String computedId = NameIdUtils.createIdFromName(null, dataType, newValue);
         ModelType model = (ModelType) this.getEditor().getModel();
         String oldValue = dataType.getId();        
         ChangeRecorder targetRecorder = new ChangeRecorder();
         targetRecorder.beginRecording(Collections.singleton(model.eContainer()));
         ElUtils.updateTransitionConditions(dataType, oldValue, computedId);
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
         ((CompoundCommand)command).add(cmd); 
         return command;            
      } catch (Throwable t) {
         return super.getCommand(request);
      }
   }

   public DataSymbolNodeEditEditPart(WorkflowModelEditor editor,
         IModelElementNodeSymbol model, EStructuralFeature[] features,
         EStructuralFeature[] features2)
   {
      super(editor, model, DataSymbolFigure.class, features, features2);
   }

   protected IFigure createFigure()
   {
      DataSymbolType symbolModel = (DataSymbolType) getModel();
      DataSymbolFigure f = new DataSymbolFigure(getIconFactory().getIconFor(symbolModel));
      f.setLocation(new Point(symbolModel.getXPos(), symbolModel.getYPos()));
      f.setName(symbolModel.getModelElement() != null ? symbolModel.getModelElement()
            .getId() : null);
      return f;
   }
}