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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.ui.PlatformUI;


public class LaneFigure extends AbstractSwimlaneFigure
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   
   // on creation the pool is expanded
   private boolean collapsed = false;
   // size of collapsed Lane
   private static int collapsedSize = 75;
   private IFigure button = null;  
      
   public LaneFigure(LaneEditPart part)
   {
      super(part);      
      collapsed = ((ISwimlaneSymbol) part.getLaneModel()).isCollapsed();                    
      button = new IconFigure("icons/figures/min.gif"); //$NON-NLS-1$
      if(collapsed)
      {
         ((IconFigure) button).setIconPath("icons/figures/max.gif"); //$NON-NLS-1$
      }      
      addMouseListener(new MyMouseLister());                       
      setBorder(new CompoundBorder(new SwimlaneBorder(getLaneEditPart().getLaneModel(), button),
            new MarginBorder(0, 3, 0, 3)));
   }

   // return collapsed size, if snap2grid is on, get the nearest size to snap
   public static int getCollapsedSize(EditPart part)
   {      
      if(part == null || part.getParent() == null)
      {         
         DiagramEditorPage diagramEditorPage = (DiagramEditorPage) ((WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getCurrentPage();      
         part = diagramEditorPage.findEditPart(diagramEditorPage.getDiagram());         
      }
      if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) part) != null)
      {
         return SnapGridUtils.getNextSnapSize(collapsedSize);
      }      
      return collapsedSize;
   }   

   public void setCollapsed(boolean collapsed)
   {
      this.collapsed = collapsed;
      if(collapsed)
      {
         ((IconFigure) button).setIconPath("icons/figures/max.gif"); //$NON-NLS-1$
      }
      else
      {
         ((IconFigure) button).setIconPath("icons/figures/min.gif"); //$NON-NLS-1$
      }    
   }
   
   public boolean isCollapsed()
   {
      if(collapsed)
      {
         return true;
      }
      return false;
   }   
   
   public Rectangle getBounds() {      
      Rectangle laneBounds = bounds.getCopy();
      LaneEditPart laneEP = getLaneEditPart();  
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) laneEP.getModel());
      if(diagram == null)
      {
         return laneBounds;
      }
      OrientationType direction = diagram.getOrientation();
      if(isCollapsed())
      {
         if (OrientationType.HORIZONTAL_LITERAL.equals(direction))
         {
            laneBounds.height = getCollapsedSize(getLaneEditPart());
         }
         else
         {
            laneBounds.width = getCollapsedSize(getLaneEditPart());            
         }         
      }   
      return laneBounds;
   }   

   public LaneEditPart getLaneEditPart()
   {
      return (LaneEditPart) getEditPart();
   }
   
   class MyMouseLister implements MouseListener
   {
      public void mouseDoubleClicked(MouseEvent me)
      {
      }

      public void mousePressed(MouseEvent me)
      {   
         // only left button
         if (me.button != 1)
         {
            return;
         }         
         Rectangle mouseBounds = button.getBounds();            
         Point eventLocation = me.getLocation();
         if (!((eventLocation.x > mouseBounds.x && eventLocation.x < mouseBounds.x + mouseBounds.width)
               && (eventLocation.y > mouseBounds.y && eventLocation.y < mouseBounds.y + mouseBounds.height)))
         {               
            return;
         }
         
         WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();         
         
         LaneSymbol symbol = getLaneEditPart().getLaneModel();
         EObject container = ModelUtils.findContainingProcess(symbol);
         if (container != null)
         {
            if (editor.getModelServer().requireLock(container))
            {
               return;
            }
         }
         
         // set collapsed state 
         setCollapsed(!isCollapsed());
                  
         CompoundCommand command = new CompoundCommand();
         SetValueCmd setValueCmd = new SetValueCmd(getLaneEditPart().getLaneModel(), 
               CWM_PKG.getISwimlaneSymbol_Collapsed(), collapsed);
         command.add(setValueCmd);         
         
         PoolLaneUtils.setResizeFlags(-1);
         Command changeContainer = PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) getLaneEditPart().getParent(), new Integer(PoolLaneUtils.CHILD_LANES_SAME_SIZE));
         command.add(changeContainer);
         editor.getEditDomain().getCommandStack().execute(command);            
      }

      public void mouseReleased(MouseEvent me)
      {
      }      
   }   
}