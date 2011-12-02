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

import java.util.*;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.handles.ConnectionEndHandle;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.handles.ConnectionStartHandle;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.decoration.IDecoratablePart;
import org.eclipse.stardust.modeling.core.decoration.IDecorationProvider;
import org.eclipse.stardust.modeling.core.editors.IDiagramEditorConstants;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractConnectionSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.IGraphicalObjectFigure;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.figures.TransitionConnectionFigure;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.PropertySourceFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.ConnectionBendpointEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.ConnectionSymbolComponentEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertySource;


public abstract class AbstractConnectionSymbolEditPart extends AbstractConnectionEditPart
      implements NotificationAdaptee, IHighliteableGraphicalObject, IDecoratablePart
{
   private NotificationAdapter notificationAdapter = null;

   private IPropertySource propertySource = null;

   private Color highliteBorderColor;

   private Color highliteFillColor;
   
   private Map decorations = new HashMap();
   // shows if the Connection is selected
   private int refreshMode;
   
   protected AbstractConnectionSymbolEditPart(IConnectionSymbol model)
   {
      setModel(model);
   }

   private boolean canRefresh(boolean isSource, AbstractConnectionSymbolFigure acsFigure)
   {
      Connection connection = (Connection) getFigure();
      if(acsFigure != null)
      {
         connection = acsFigure;
      }
      ConnectionAnchor anchor = null;
      if(isSource)
      {
         anchor = connection.getSourceAnchor();
      }
      else
      {
         anchor = connection.getTargetAnchor();         
      }
      if(anchor != null)
      {
         IFigure owner = anchor.getOwner();      
         if(owner != null && owner instanceof LaneFigure)
         {
            return false;
         }
      }
      return true;
   }   
   
   protected void refreshSourceAnchor()
   {
      if(canRefresh(true, null))
      {
         super.refreshSourceAnchor();         
      }
   }

   protected void refreshTargetAnchor()
   {
      if(canRefresh(false, null))
      {
         super.refreshTargetAnchor();         
      }
   }   
   
   public final IConnectionSymbol getConnectionSymbolModel()
   {
      return (IConnectionSymbol) getModel();
   }

   public void setHighliteBorderColor(Color color)
   {
      this.highliteBorderColor = color;
      refreshVisuals();
   }

   public void resetHighliteBorderColor()
   {
      setHighliteBorderColor(null);
   }

   public void setHighliteFillColor(Color color)
   {
      this.highliteFillColor = color;
      refreshVisuals();
   }

   public void resetHighliteFillColor()
   {
      setHighliteFillColor(null);
   }

   protected void createEditPolicies()
   {      
      if(!DiagramUtil.isDiagramModelElementProxy((EObject) getModel()))
      {         
         // Selection handle edit policy.
         // Makes the connection show a feedback, when selected by the user.
         installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
               new ConnectionEndpointEditPolicy()
         {
            // anchors connected to collapsed lanes must have fixed handles
            protected List createSelectionHandles()
            {
               List handles = super.createSelectionHandles();
               for(int i = 0; i < handles.size(); i++)
               {
                  ConnectionHandle handle = (ConnectionHandle) handles.get(i);
                  if(handle instanceof ConnectionStartHandle)
                  {
                     Connection connection = handle.getConnection();
                     ConnectionAnchor anchor = connection.getSourceAnchor();
                     if(anchor.getOwner() instanceof LaneFigure)
                     {
                        handle.setFixed(true);
                     }
                  }
                  if(handle instanceof ConnectionEndHandle)
                  {
                     Connection connection = handle.getConnection();
                     ConnectionAnchor anchor = connection.getTargetAnchor();
                     if(anchor.getOwner() instanceof LaneFigure)
                     {
                        handle.setFixed(true);
                     }                     
                  }
               }
               return handles;
            }            
         });
         // Allows the removal of the connection model element
         /*
          * installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
          * protected Command getDeleteCommand(GroupRequest request) { return new
          * ConnectionDeleteCommand(getCastedModel()); } });
          */
         installEditPolicy(EditPolicy.COMPONENT_ROLE,
               new ConnectionSymbolComponentEditPolicy());
         
      }
   }

   protected IFigure createFigure()
   {
      AbstractConnectionSymbolFigure connection = new AbstractConnectionSymbolFigure();
      connection.setRouting(getConnectionSymbolModel().getRouting().getValue());
      return connection;
   }
   
   public ConnectionAnchor getSourceConnectionAnchor() 
   {
      return super.getSourceConnectionAnchor();
   }
   
   public ConnectionAnchor getTargetConnectionAnchor() 
   {
      return super.getTargetConnectionAnchor();
   }   
   
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      IFigure figure = getFigure();
      if (figure instanceof IGraphicalObjectFigure)
      {
         IGraphicalObjectFigure goFigure = (IGraphicalObjectFigure) figure;
         goFigure.setBorderColor(highliteBorderColor);
         goFigure.setFillColor(highliteFillColor);
      }
      if (figure instanceof AbstractConnectionSymbolFigure)
      {
         AbstractConnectionSymbolFigure acsFigure = (AbstractConnectionSymbolFigure) figure;
         if(canRefresh(true, acsFigure))
         {
            acsFigure.setSourceAnchor(getSourceConnectionAnchor());
         }
         if(canRefresh(false, acsFigure))
         {
            acsFigure.setTargetAnchor(getTargetConnectionAnchor());
         }

         // the arrows (any decorations) should have the same color as the connection
         if(figure instanceof TransitionConnectionFigure) {
            if(highliteBorderColor != null) {
               ((TransitionConnectionFigure) figure).getTargetDecoration().setForegroundColor(highliteBorderColor);
               ((TransitionConnectionFigure) figure).getTargetDecoration().setBackgroundColor(highliteBorderColor);
            } else {
               // default color
               ((TransitionConnectionFigure) figure).getTargetDecoration().setForegroundColor(TransitionConnectionFigure.FG_COLOR);
               ((TransitionConnectionFigure) figure).getTargetDecoration().setBackgroundColor(TransitionConnectionFigure.FG_COLOR);
            }
         }
         if (RoutingType.SHORTEST_PATH_LITERAL.equals(getRouting()))
         {
            acsFigure.setRouting(AbstractConnectionSymbolFigure.ROUTING_SHORTEST_PATH);
            removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
         }
         else if (RoutingType.MANHATTAN_LITERAL.equals(getRouting()))
         {
            acsFigure.setRouting(AbstractConnectionSymbolFigure.ROUTING_MANHATTAN);
            // acsFigure.setRoutingConstraint(Collections.EMPTY_LIST);
            removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
            // installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
            // getConnectionBendpointEditPolicy());
         }
         else if (RoutingType.EXPLICIT_LITERAL.equals(getRouting()))
         {
            // TODO preserve existing bendpoints
            acsFigure.setRouting(AbstractConnectionSymbolFigure.ROUTING_EXPLICIT);

            if (!getConnectionSymbolModel().getCoordinates().isEmpty())
            {
               List bendpoints = new ArrayList(getConnectionSymbolModel()
                     .getCoordinates().size());
               for (Iterator i = getConnectionSymbolModel().getCoordinates().iterator(); i
                     .hasNext();)
               {
                  Coordinates p = (Coordinates) i.next();
                  bendpoints.add(new AbsoluteBendpoint((int) Math.round(p.getXPos()),
                        (int) Math.round(p.getYPos())));
               }
               acsFigure.setRoutingConstraint(bendpoints);
            }
            else
            {
               acsFigure.setRoutingConstraint(Collections.EMPTY_LIST);
            }

            // removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
                  getConnectionBendpointEditPolicy());
            if(refreshMode == 0)
            {
            	// when multiple bendpoints are selected, it must be unselected when the connection is no longer selected
				((ConnectionBendpointEditPolicy) getConnectionBendpointEditPolicy()).unSelectBendpoints();
            }
         }
         else
         {
            acsFigure.setRouting(AbstractConnectionSymbolFigure.ROUTING_DEFAULT);
            removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
         }
      }

      if (getConnectionSymbolModel() instanceof TransitionConnectionType)
      {
         String expression = null;
         TransitionConnectionType tSymbol = (TransitionConnectionType) getConnectionSymbolModel();
         TransitionType transition = tSymbol.getTransition();
         if (null != transition)
         {
            if (null != transition.getExpression())
            {
               expression = ModelUtils.getCDataString(transition.getExpression()
                     .getMixed());
               if ("true".equals(expression)) //$NON-NLS-1$
               {
                  expression = null;
               }
            }
            else
            {
               String condition = transition.getCondition();
               if (!"TRUE".equals(condition)) //$NON-NLS-1$
               {
                  expression = condition;
               }
            }
         }
         ((TransitionConnectionFigure) figure).setLabel(expression);
      }
   }

   protected RoutingType getRouting()
   {
      return getConnectionSymbolModel().getRouting();
   }

   private EditPolicy getConnectionBendpointEditPolicy()
   {
      RootEditPart root = getRoot();
      DiagramEditPart diagram = (DiagramEditPart) root.getContents();
      return diagram.getConnectionBendpointEditPolicy();
   }

   public void handleNotification(Notification notification)
   {
      if (notification.getNotifier() instanceof IConnectionSymbol)
      {
         int featureId = notification.getFeatureID(CarnotWorkflowModelPackage.class);
         switch (featureId)
         {
         case CarnotWorkflowModelPackage.ICONNECTION_SYMBOL__COORDINATES:
            Object rawValue = notification.getNewValue();
            if (null != rawValue)
            {
               if (rawValue instanceof List)
               {
                  List list = (List) rawValue;
                  for (Iterator iter = list.iterator(); iter.hasNext();)
                  {
                     addNotificationAdapter((Notifier) iter.next());
                  }
               }
               else
               {
                  addNotificationAdapter((Notifier) rawValue);
               }
            }

            rawValue = notification.getOldValue();
            if (null != rawValue)
            {
               if (rawValue instanceof List)
               {
                  List list = (List) rawValue;
                  for (Iterator iter = list.iterator(); iter.hasNext();)
                  {
                     removeNotificationAdapter((Notifier) iter.next());
                  }
               }
               else
               {
                  removeNotificationAdapter((Notifier) rawValue);
               }
            }
            break;

         default:
            break;
         }
      }
      checkTransitionExpressionAddedOrRemoved(notification);
      refreshVisuals();
   }

   private void checkTransitionExpressionAddedOrRemoved(Notification notification)
   {
      if (notification.getNotifier() instanceof TransitionType)
      {
         if (CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Expression().equals(
               notification.getFeature()))
         {
            if (notification.getNewValue() == null)
            {
               // expression removed
               if (null != notification.getOldValue())
               {
                  removeNotificationAdapter((XmlTextNode) notification.getOldValue());
               }
            }
            else
            {
               // expression added
               if (null != notification.getOldValue())
               {
                  removeNotificationAdapter((XmlTextNode) notification.getOldValue());
               }
               addNotificationAdapter((XmlTextNode) notification.getNewValue());
            }
         }
      }
   }

   public void activate()
   {
      if (!isActive())
      {
         super.activate();

         final IConnectionSymbol model = getConnectionSymbolModel();
         if (null != model)
         {
            model.eAdapters().add(getNotificationAdapter());

            for (Iterator coordinatesIter = model.getCoordinates().iterator(); coordinatesIter
                  .hasNext();)
            {
               addNotificationAdapter((Notifier) coordinatesIter.next());
            }

            cheackAddTransitionAdapters(model);
         }
      }
   }

   private void cheackAddTransitionAdapters(final IConnectionSymbol model)
   {
      if (model instanceof TransitionConnectionType)
      {
         TransitionType transition = ((TransitionConnectionType) model).getTransition();
         if (transition != null)
         {
            addNotificationAdapter(transition);
            XmlTextNode xmlText = transition.getExpression();
            if (xmlText != null)
            {
               addNotificationAdapter(xmlText);
            }
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         IConnectionSymbol model = getConnectionSymbolModel();
         if ((null == model)
               && (getNotificationAdapter().getTarget() instanceof IConnectionSymbol))
         {
            model = (IConnectionSymbol) getNotificationAdapter().getTarget();
         }
         if (null != model)
         {
            checkRemoveTransitionAdapters(model);

            for (Iterator coordinatesIter = model.getCoordinates().iterator(); coordinatesIter
                  .hasNext();)
            {
               removeNotificationAdapter((Notifier) coordinatesIter.next());
            }

            model.eAdapters().remove(getNotificationAdapter());
         }

         super.deactivate();
      }
   }

   private void checkRemoveTransitionAdapters(final IConnectionSymbol model)
   {
      if (model instanceof TransitionConnectionType)
      {
         TransitionType transition = ((TransitionConnectionType) model).getTransition();
         if (transition != null)
         {
            XmlTextNode xmlText = transition.getExpression();
            if (xmlText != null)
            {
               removeNotificationAdapter(xmlText);
            }
            removeNotificationAdapter(transition);
         }
      }
   }

   public Object getAdapter(Class key)
   {
      if (IPropertySource.class == key)
      {
         return getPropertySource();
      }
      else if (EditPart.class == key)
      {
         return this;
      }
      else if (IModelElement.class == key || IConnectionSymbol.class == key)
      {
         return getModel();
      }
      else
      {
         return super.getAdapter(key);
      }
   }

   protected NotificationAdapter getNotificationAdapter()
   {
      if (null == notificationAdapter)
      {
         this.notificationAdapter = new NotificationAdapter(this);
      }
      return notificationAdapter;
   }

   protected IPropertySource getPropertySource()
   {
      if (null == propertySource)
      {
         propertySource = PropertySourceFactory.getPropertySource(this,
               getConnectionSymbolModel());
      }
      return propertySource;
   }

   private void addNotificationAdapter(Notifier notifier)
   {
      notifier.eAdapters().add(getNotificationAdapter());
   }

   private void removeNotificationAdapter(Notifier notifier)
   {
      notifier.eAdapters().remove(getNotificationAdapter());
   }

   public Command getCommand(Request request)
   {
      Command command = super.getCommand(request);
      return command;
   }

   public void setSelected(int value)
   {
      refreshMode = value;
      super.setSelected(value);
      refreshVisuals();
   }

   public void applyDecoration(IDecorationProvider decoration)
   {
      IFigure decorationFigure = (IFigure) decorations.get(decoration.getId());
      if (null != decorationFigure)
      {
         if (null != decorationFigure.getParent())
         {
            decorationFigure.getParent().remove(decorationFigure);
         }
         decorations.remove(decoration.getId());
      }
      decorationFigure = decoration.createDecoration(getConnectionSymbolModel());
      if (null != decorationFigure)
      {
         decorations.put(decoration.getId(), decorationFigure);
         LayerManager manager = (LayerManager) getViewer().getEditPartRegistry().get(
               LayerManager.ID);
         IFigure decorationLayer = manager.getLayer(IDiagramEditorConstants.DECORATION_LAYER);
         if ((null != decorationLayer) && (null != getFigure()))
         {
            ConnectionLocator locator = decoration.createDecorationLocator(
                  getConnectionSymbolModel(), getConnectionFigure(), decorationFigure);
            if (null == locator)
            {
               locator = new MidpointLocator(getConnectionFigure(), 0);
            }
            decorationLayer.add(decorationFigure, locator);
            
            // TODO move into createFigure
            final IFigure theDecorationFigure = decorationFigure;
            getFigure().addFigureListener(new FigureListener()
            {
               public void figureMoved(IFigure source)
               {
                  theDecorationFigure.revalidate();
               }
            });
         }
      }
   }

   public void removeDecoration(IDecorationProvider decoration)
   {
      IFigure decorationFigure = (IFigure) decorations.get(decoration.getId());
      if (null != decorationFigure)
      {
         if (null != decorationFigure.getParent())
         {
            decorationFigure.getParent().remove(decorationFigure);
         }
         decorations.remove(decoration.getId());
         decoration.decorationRemoved(getConnectionSymbolModel(), decorationFigure);
      }
   }
}
