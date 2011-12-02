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

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.PoolFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DiagramComponentEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerLayoutEditPolicy;


public class PoolEditPart extends AbstractSwimlaneEditPart
{
   public PoolEditPart(WorkflowModelEditor editor, PoolSymbol pool)
   {
      super(editor, pool);
   }

   public PoolSymbol getPoolModel()
   {
      return (PoolSymbol) getModel();
   }

   public PoolFigure getPoolFigure()
   {
      return (PoolFigure) getFigure();
   }

   protected IFigure createFigure()
   {
      return new PoolFigure(this);
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DiagramComponentEditPolicy());
      installEditPolicy(EditPolicy.CONTAINER_ROLE, new SymbolContainerEditPolicy());
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new SymbolContainerLayoutEditPolicy());

      //installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
      //installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$      
   }

   protected List getModelChildren()
   {
      List nodes = new ArrayList(getPoolModel().getNodes().size()
            + getPoolModel().getChildLanes().size());

      nodes.addAll(getPoolModel().getChildLanes());
      nodes.addAll(getPoolModel().getActivitySymbol());
      nodes.addAll(getPoolModel().getAnnotationSymbol());
      nodes.addAll(getPoolModel().getApplicationSymbol());
      nodes.addAll(getPoolModel().getConditionalPerformerSymbol());
      nodes.addAll(getPoolModel().getDataSymbol());
      nodes.addAll(getPoolModel().getEndEventSymbols());
      nodes.addAll(getPoolModel().getProcessInterfaceSymbols());
      nodes.addAll(getPoolModel().getGatewaySymbol());
      nodes.addAll(getPoolModel().getGroupSymbol());
      nodes.addAll(getPoolModel().getIntermediateEventSymbols());
      nodes.addAll(getPoolModel().getModelerSymbol());
      nodes.addAll(getPoolModel().getOrganizationSymbol());
      nodes.addAll(getPoolModel().getProcessSymbol());
      nodes.addAll(getPoolModel().getRoleSymbol());
      nodes.addAll(getPoolModel().getStartEventSymbols());
      nodes.addAll(getPoolModel().getTextSymbol());

      return nodes;
   }

   public void handleNotification(Notification notification)
   {
      switch (notification.getFeatureID(PoolSymbol.class))
      {
      case CarnotWorkflowModelPackage.POOL_SYMBOL__LANES:
      case CarnotWorkflowModelPackage.POOL_SYMBOL__NODES:
         refreshChildren();
         break;
      default:
         super.handleNotification(notification);
         break;
      }
   }
}