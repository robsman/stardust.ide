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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DynamicConnectionFactory;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.GatewayFigure;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IConnectionCommand;


public class GatewaySymbolEditPart extends AbstractNodeSymbolEditPart
{
   public GatewaySymbolEditPart(WorkflowModelEditor editor, GatewaySymbol model)
   {
      super(editor, model);
   }

   protected IFigure createFigure()
   {
      return new GatewayFigure(this);
   }

   protected List getModelSourceConnections()
   {
      return ((GatewaySymbol) getModel()).getOutTransitions();
   }

   protected List getModelTargetConnections()
   {
      List result;
      if (isShowingConnections())
      {
         result = new ArrayList(super.getModelTargetConnections());
         result.addAll(((GatewaySymbol) getModel()).getInTransitions());
      }
      else
      {
         result = Collections.EMPTY_LIST;
      }
      return result;
   }

   public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
   {
      if (connection.getModel() instanceof TransitionConnectionType)
      {
         TransitionConnectionType tc = (TransitionConnectionType) connection.getModel();
         return new TransitionConnectionAnchor(getFigure(), tc.getSourceAnchor());
      }
      else
      {
         return super.getSourceConnectionAnchor(connection);
      }
   }

   public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
   {
      if (connection.getModel() instanceof TransitionConnectionType)
      {
         TransitionConnectionType tc = (TransitionConnectionType) connection.getModel();
         return new TransitionConnectionAnchor(getFigure(), tc.getTargetAnchor());
      }
      else
      {
         return super.getTargetConnectionAnchor(connection);
      }
   }

   public ConnectionAnchor getSourceConnectionAnchor(final Request request)
   {
      if (request instanceof CreateConnectionRequest &&
            ((CreateConnectionRequest) request).getNewObject() instanceof DynamicConnectionFactory)
      {
         DynamicConnectionFactory factory = (DynamicConnectionFactory)
            ((CreateConnectionRequest) request).getNewObject();
         final Point location = ((CreateConnectionRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(), location);
         factory.setSourceAnchorType(anchor.getType());
         return anchor;
      }
      // todo: remove this branch
      else if (request instanceof CreateConnectionRequest
            && ((CreateConnectionRequest) request).getNewObjectType().equals(
                  CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType()))
      {
         IConnectionCommand cmd = (IConnectionCommand) ((CreateConnectionRequest) request)
               .getNewObject();
         final Point location = ((CreateConnectionRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(),
               location);
         cmd.setSourceAnchorType(anchor.getType());
         return anchor;
      }
      else if (request instanceof ReconnectRequest
            && ((ReconnectRequest) request).getConnectionEditPart().getModel() instanceof TransitionConnectionType)
      {
         final Point location = ((ReconnectRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(),
               location);
         request.getExtendedData().put(
               CarnotConstants.DIAGRAM_PLUGIN_ID + ".sourceAnchor", anchor.getType()); //$NON-NLS-1$
         return anchor;
      }
      else
      {
         return super.getSourceConnectionAnchor(request);
      }
   }

   public ConnectionAnchor getTargetConnectionAnchor(final Request request)
   {
      if (request instanceof CreateConnectionRequest &&
            ((CreateConnectionRequest) request).getNewObject() instanceof DynamicConnectionFactory)
      {
         DynamicConnectionFactory factory = (DynamicConnectionFactory)
            ((CreateConnectionRequest) request).getNewObject();
         final Point location = ((CreateConnectionRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(), location);
         factory.setTargetAnchorType(anchor.getType());
         return anchor;
      }
      // todo: remove this branch
      else if (request instanceof CreateConnectionRequest
            && ((CreateConnectionRequest) request).getNewObjectType().equals(
                  CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType()))
      {
         IConnectionCommand cmd = (IConnectionCommand) ((CreateConnectionRequest) request)
               .getNewObject();
         final Point location = ((CreateConnectionRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(),
               location);
         cmd.setTargetAnchorType(anchor.getType());
         return anchor;
      }
      else if (request instanceof ReconnectRequest
            && ((ReconnectRequest) request).getConnectionEditPart().getModel() instanceof TransitionConnectionType)
      {
         final Point location = ((ReconnectRequest) request).getLocation();
         TransitionConnectionAnchor anchor = new TransitionConnectionAnchor(getFigure(),
               location);
         request.getExtendedData().put(
               CarnotConstants.DIAGRAM_PLUGIN_ID + ".targetAnchor", anchor.getType()); //$NON-NLS-1$
         return anchor;
      }
      else
      {
         return super.getTargetConnectionAnchor(request);
      }
   }

   public void handleNotification(Notification notification)
   {
      int featureId = notification.getFeatureID(CarnotWorkflowModelPackage.class);
      switch (featureId)
      {
      case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__IN_TRANSITIONS:
         refreshTargetConnections();
         break;
      case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__OUT_TRANSITIONS:
         refreshSourceConnections();
         break;
      default:
         super.handleNotification(notification);
      }
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();

      GatewayFigure figure = (GatewayFigure) getFigure();

      JoinSplitType type = JoinSplitType.XOR_LITERAL;
      GatewaySymbol gateway = (GatewaySymbol) getModel();
      ActivitySymbolType symbol = gateway.getActivitySymbol();
      if (symbol != null)
      {
         ActivityType activity = (ActivityType) symbol.getModelElement();
         if (activity != null)
         {
            type = gateway.getFlowKind().getValue() == FlowControlType.JOIN ? activity
                  .getJoin() : activity.getSplit();
            figure.setToolTip(new Label(FlowControlType.JOIN == gateway.getFlowKind()
                  .getValue() ? MessageFormat.format(Diagram_Messages.TOOLTIP_JoinGateway,
                  new String[] {activity.getName()}) : FlowControlType.SPLIT == gateway
                  .getFlowKind().getValue() ? MessageFormat.format(Diagram_Messages.TOOLTIP_SplitGateway,
                  new String[] {activity.getName()}) : "")); //$NON-NLS-1$
         }
      }
      switch (type.getValue())
      {
      case JoinSplitType.AND:
         figure.setTypeIndicator(GatewayFigure.GATEWAY_TYPE_AND);
         break;
      default:
         figure.setTypeIndicator(GatewayFigure.GATEWAY_TYPE_XOR);
         break;
      }
      figure.setKind(gateway.getFlowKind().getValue());
   }
}
