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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DiagramComponentEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerLayoutEditPolicy;
import org.eclipse.stardust.modeling.core.utils.LaneConnections;


public class LaneEditPart extends AbstractSwimlaneEditPart
{
   public LaneEditPart(WorkflowModelEditor editor, LaneSymbol lane)
   {
      super(editor, lane);
      // register for notification if name of participant gets changed
      if (lane.getParticipant() != null)
      {
         lane.getParticipant().eAdapters().add(getNotificationAdapter());
      }
   }

   public LaneSymbol getLaneModel()
   {
      return (LaneSymbol) getModel();
   }

   public LaneFigure getLaneFigure()
   {
      return (LaneFigure) getFigure();
   }

   protected IFigure createFigure()
   {
      return new LaneFigure(this);
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DiagramComponentEditPolicy());
      installEditPolicy(EditPolicy.CONTAINER_ROLE, new SymbolContainerEditPolicy());
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new SymbolContainerLayoutEditPolicy());

      //installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
      //installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$      
   }

   public List getModelChildren()
   {
      List nodes = new ArrayList(getLaneModel().getNodes().size()
            + getLaneModel().getChildLanes().size());

      nodes.addAll(getLaneModel().getChildLanes());

      nodes.addAll(getLaneModel().getActivitySymbol());
      nodes.addAll(getLaneModel().getAnnotationSymbol());
      nodes.addAll(getLaneModel().getApplicationSymbol());
      nodes.addAll(getLaneModel().getConditionalPerformerSymbol());
      nodes.addAll(getLaneModel().getDataSymbol());
      nodes.addAll(getLaneModel().getEndEventSymbols());
      nodes.addAll(getLaneModel().getGatewaySymbol());
      nodes.addAll(getLaneModel().getGroupSymbol());
      nodes.addAll(getLaneModel().getIntermediateEventSymbols());
      nodes.addAll(getLaneModel().getModelerSymbol());
      nodes.addAll(getLaneModel().getOrganizationSymbol());
      nodes.addAll(getLaneModel().getProcessSymbol());
      nodes.addAll(getLaneModel().getRoleSymbol());
      nodes.addAll(getLaneModel().getStartEventSymbols());
      nodes.addAll(getLaneModel().getTextSymbol());
      nodes.addAll(getLaneModel().getProcessInterfaceSymbols());

      return nodes;
   }

   public void handleNotification(Notification notification)
   {
      switch (notification.getFeatureID(LaneSymbol.class))
      {
         case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED :
            getLaneFigure().setCollapsed(getLaneModel().isCollapsed()); 
            refreshFigure(getLaneFigure());
            refreshContent();
            break;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT :
            Object oldValue = notification.getOldValue();
            Object newValue = notification.getNewValue();
            if(oldValue != null)
            {
               ((EObject) oldValue).eAdapters().remove(getNotificationAdapter());
            }
            if(newValue != null)
            {
               ((EObject) newValue).eAdapters().add(getNotificationAdapter());               
            }
            getLaneFigure().refresh();
            break;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES :            
         case CarnotWorkflowModelPackage.LANE_SYMBOL__NODES :
            refreshChildren();
            break;
            
         default :
            super.handleNotification(notification);
            break;
      }
   }

   protected void refreshFigure(IFigure figure)
   {
      super.refreshFigure(figure);
   }

   public void refreshContent()
   {
      setLaneChildren();
      switchLaneConnections();      
   }
   
   private void setLaneChildren()
   {
      boolean childrenVisible = false;
      if(getLaneFigure().isCollapsed())
      {
         childrenVisible = false;
      }
      else
      {
         childrenVisible = true;
      }
      List children = getLaneFigure().getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         Object child = children.get(i);
         if(child instanceof Figure)
         {
            ((Figure) child).setVisible(childrenVisible);
         }
      }      
   }
   
   public void switchLaneConnections()
   {
      boolean parentCollapsed = false;
      EditPart parentEP = getParent();
      while(parentEP != null)
      {
         if(parentEP instanceof LaneEditPart)
         {
            if(((LaneEditPart) parentEP).getLaneModel().isCollapsed())
            {
               parentCollapsed = true;
               break;
            }
         }
         parentEP = parentEP.getParent();
      }
      
      if(!parentCollapsed)
      {         
         LaneConnections laneConnections = new LaneConnections(this, getLaneFigure().isCollapsed());
         laneConnections.handleConnections();         
      }
   }   
}