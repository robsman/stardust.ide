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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditorPaletteFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.PlatformUI;


public class DiagramModeAction extends Action
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   
   private DiagramType diagram;
   private EditDomain domain; 
   private DiagramModeType actualMode;

   public DiagramModeAction(String mode, DiagramType diagram, EditDomain domain, boolean classicMode)
   {
      super(mode);
      this.diagram = diagram;      
      this.domain = domain;
      if (classicMode)
      {
         actualMode = DiagramModeType.MODE_400_LITERAL;
      }
      else
      {
         actualMode = DiagramModeType.MODE_450_LITERAL;
      }
   }
   
   public void run()
   {  
      CompoundCommand command = new CompoundCommand();
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();      
      final DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor.getCurrentPage();
      if(actualMode.equals(DiagramModeType.MODE_450_LITERAL))
      {
         // move children if children have negative positions
         PoolSymbol poolSymbol = DiagramUtil.getDefaultPool(diagram);
         if(poolSymbol != null && !poolSymbol.getNodes().isEmpty())
         {
            int[] checkNegative = new int[] {0, 0};
            PoolLaneUtils.checkForNegativePositions(poolSymbol, checkNegative);
            if(checkNegative[0] < 0 || checkNegative[1] < 0)
            {
               checkNegative[0] = - checkNegative[0];
               checkNegative[1] = - checkNegative[1];
               EditPart editPart = diagramEditorPage.findEditPart(diagram);
               command.add(PoolLaneUtils.moveAllChildren(editPart, checkNegative));
            }
         }
      }
      Command cmd = new SetValueCmd(diagram, CWM_PKG
            .getDiagramType_Mode(), actualMode)
      {
         public void redo()
         {
            super.redo();
            updatePalette(getValue());
         }
         public void undo()
         {
            super.undo();
            updatePalette(getUndoValue());
         }
         
         public void updatePalette(Object value)
         {
            WorkflowModelEditorPaletteFactory.setDiagramModeType((DiagramModeType) value);
            WorkflowModelEditorPaletteFactory.updatePalette(diagramEditorPage);
            WorkflowModelEditorPaletteFactory.setDiagramModeType(null);
         }         
      };
      command.add(cmd);  
      command.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            CompoundCommand cmd = new CompoundCommand();
            PoolSymbol poolSymbol = DiagramUtil.getDefaultPool(diagram);
            EditPart editPart = diagramEditorPage.findEditPart(diagram);
            Rectangle newSize = PoolLaneUtils.checkPoolSize((DiagramEditPart) editPart);
            if(newSize != null)
            {
               cmd.add(new SetValueCmd(poolSymbol, CWM_PKG.getINodeSymbol_Width(), new Integer(newSize.width)));
               cmd.add(new SetValueCmd(poolSymbol, CWM_PKG.getINodeSymbol_Height(), new Integer(newSize.height)));               
               return cmd;
            }            
            return null;
         }
      });
      domain.getCommandStack().execute(command);      
   }

   public DiagramModeType getDiagramMode()
   {
      return actualMode;
   }
}