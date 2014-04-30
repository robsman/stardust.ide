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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractLabeledIconFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.EventFigure;
import org.eclipse.stardust.modeling.core.editors.figures.IIconFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ILabeledFigure;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ReloadConnectionsAction;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;

public class AbstractModelElementNodeSymbolEditPart
      extends AbstractNodeSymbolEditPart
{
   private NotificationAdapter modelElementNotificationAdapter;

   private Class figureClass;
   private EStructuralFeature[] sourceConnectionFeatures;
   private EStructuralFeature[] targetConnectionFeatures;

   public Command getCommand(Request request)
   {
      if (RequestConstants.REQ_CONNECTION_START.equals(request.getType())
            || RequestConstants.REQ_CONNECTION_END.equals(request.getType())
            || ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(request.getType()))
      {
         Object model = getModel();

         if (model instanceof INodeSymbol)
         {
            ModelServer modelServer = getEditor().getModelServer();
            if (modelServer != null && modelServer.requireLock((INodeSymbol) model))
            {
               return null;
            }
         }
      }
      return super.getCommand(request);
   }

   public AbstractModelElementNodeSymbolEditPart(WorkflowModelEditor editor, IModelElementNodeSymbol model)
   {
      super(editor, model);
   }

   public AbstractModelElementNodeSymbolEditPart(WorkflowModelEditor editor, IModelElementNodeSymbol model,
      Class figureClass,
      EStructuralFeature[] sourceConnectionFeatures,
      EStructuralFeature[] targetConnectionFeatures)
   {
      super(editor, model);
      setFigureClass(figureClass);
      setSourceConnectionFeatures(sourceConnectionFeatures);
      setTargetConnectionFeatures(targetConnectionFeatures);
   }

   protected void refreshFigure(IFigure figure)
   {
      super.refreshFigure(figure);

      if (figure instanceof ILabeledFigure)
      {
         IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) getModel();
         String name = null;
         IIdentifiableModelElement element = symbol.getModelElement();
         if (element != null)
         {
            name = element.getName();
            if (StringUtils.isEmpty(name))
            {
               name = element.getId();
            }
         }
         else
         {
            name = (null != symbol.getModelElement())
                  ? symbol.getModelElement().getId()
                  : null;
         }
         if (StringUtils.isEmpty(name) && element instanceof EObjectImpl && element.eIsProxy())
         {
            name = ((EObjectImpl) element).eProxyURI().toString();
         }
         if (StringUtils.isEmpty(name))
         {
            name = Diagram_Messages.MSG_EDITOR_unidentifiedModelElement;
         }
         ((ILabeledFigure) figure).setName(name);

         if ((element instanceof ActivityType)
               && (getFigure() instanceof ActivitySymbolFigure))
         {
            ActivityType activity = (ActivityType) element;
            if (null != activity)
            {
               ActivitySymbolFigure activityFigure = (ActivitySymbolFigure) figure;
               activityFigure.setLoopActivity(getLoopType(activity), isSequential(activity));
               activityFigure.setSubProcActivity(ActivityImplementationType.SUBPROCESS_LITERAL
                     .equals(activity.getImplementation()));
               activityFigure.setEventHandlerType(!activity.getEventHandler().isEmpty(), activity);
            }
         }
         if ((element instanceof TriggerType)
               && (getFigure() instanceof EventFigure))
         {
            TriggerType trigger = (TriggerType) element;
            if(trigger != null)
            {
               EventFigure eventFigure = (EventFigure) figure;
               eventFigure.setText(trigger.getName() == null ? trigger.getId() : trigger.getName());
            }
         }
      }

      if (figure instanceof IIconFigure)
      {
         ((IIconFigure) figure).setIconPath(getIconFactory()
               .getIconFor(getModelElement()));
      }
   }

   private boolean isSequential(ActivityType activity)
   {
      org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop = activity.getLoop();
      return loop == null || loop.getLoopType() != LoopTypeType.MULTI_INSTANCE || (
            loop.getLoopMultiInstance() != null && loop.getLoopMultiInstance().getMIOrdering() != MIOrderingType.PARALLEL);
   }

   private LoopTypeType getLoopType(ActivityType activity)
   {
      org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop = activity.getLoop();
      if (loop == null)
      {
         LoopType loopType = activity.getLoopType();
         if (loopType != null && (loopType == LoopType.REPEAT_LITERAL || loopType == LoopType.WHILE_LITERAL))
         {
            return LoopTypeType.STANDARD;
         }
      }
      else
      {
         return loop.getLoopType();
      }
      return null;
   }

   protected EStructuralFeature getDirectEditFeature()
   {
      if (getModelElement() instanceof IIdentifiableModelElement)
      {
         // check if predefined
         if(getModel() instanceof IModelElementNodeSymbol
               && ((IModelElementNodeSymbol) getModel()).getModelElement() != null
               && ((IModelElementNodeSymbol) getModel()).getModelElement() instanceof DataType
               && ((DataType) ((IModelElementNodeSymbol) getModel()).getModelElement()).isPredefined())
         {
            return null;
         }
         else
         {
            return CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name();
         }
      }
      return super.getDirectEditFeature();
   }

   public Object getAdapter(Class key)
   {
      Object result = null;

      if (IModelElementNodeSymbol.class.equals(key) || IModelElement.class.equals(key))
      {
         result = getModel();
      }
      else
      {
         result = super.getAdapter(key);
      }
      return result;
   }

   public void activate()
   {
      if ( !isActive())
      {
         super.activate();

         final IModelElement model = getModelElement();
         if (null != model)
         {
            model.eAdapters().add(getModelElementNotificationAdapter());
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         final IModelElement model = getModelElement();
         if (null != model)
         {
            model.eAdapters().remove(getModelElementNotificationAdapter());
         }
         else if (null != getModelElementNotificationAdapter().getTarget())
         {
            getModelElementNotificationAdapter().getTarget().eAdapters().remove(
                  getModelElementNotificationAdapter());
         }

         super.deactivate();
      }
   }

   protected NotificationAdapter getModelElementNotificationAdapter()
   {
      if (null == modelElementNotificationAdapter)
      {
         this.modelElementNotificationAdapter = new NotificationAdapter(this);
      }
      return modelElementNotificationAdapter;
   }

   private IModelElement getModelElement()
   {
      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) getModel();
      return symbol == null ? null : symbol.getModelElement();
   }

   protected IFigure createFigure()
   {
      if (figureClass != null)
      {
         IModelElementNodeSymbol symbolModel = (IModelElementNodeSymbol) getModel();

         try
         {
            Figure f = (Figure) figureClass.newInstance();
            f.setLocation(new Point(symbolModel.getXPos(), symbolModel.getYPos()));
            if (f instanceof ILabeledFigure)
            {
               ((ILabeledFigure) f).setName((null != symbolModel.getModelElement())
                     ? symbolModel.getModelElement().getId()
                     : null);
            }
            if (f instanceof AbstractLabeledIconFigure)
            {
               ((AbstractLabeledIconFigure) f).setIconPath(getIconFactory().getIconFor(
                     (EObject) getModel()));
            }
            return f;
         }
         catch (Exception e)
         {
         }
      }
      return null;
   }

   public void handleNotification(Notification notification)
   {
      EStructuralFeature eFtr = (EStructuralFeature) notification.getFeature();

      if (null != sourceConnectionFeatures)
      {
         for (int i = 0; i < sourceConnectionFeatures.length; i++)
         {
            if (eFtr == sourceConnectionFeatures[i])
            {
               refreshSourceConnections();
               return;
            }
         }
      }
      if (null != targetConnectionFeatures)
      {
         for (int i = 0; i < targetConnectionFeatures.length; i++)
         {
            if (eFtr == targetConnectionFeatures[i])
            {
               refreshTargetConnections();
               return;
            }
         }
      }
      super.handleNotification(notification);
   }

   public void setFigureClass(Class figureClass)
   {
      this.figureClass = figureClass;
   }

   protected List getModelSourceConnections()
   {
      return isShowingConnections() ? getConnections(sourceConnectionFeatures,
            super.getModelSourceConnections()) : Collections.EMPTY_LIST;
   }

   protected List getModelTargetConnections()
   {
      return isShowingConnections() ? getConnections(targetConnectionFeatures,
            super.getModelTargetConnections()) : Collections.EMPTY_LIST;
   }

   private List getConnections(EStructuralFeature[] connectionFeatures, List superConnections)
   {
      List result = new ArrayList();
      if (connectionFeatures != null)
      {
         IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) getModel();
         for (int i = 0; i < connectionFeatures.length; i++)
         {
            Object connection = symbol.eGet(connectionFeatures[i]);
            if (connection instanceof Collection)
            {
               result.addAll((Collection) connection);
            }
            else if (connection != null)
            {
               result.add(connection);
            }
         }
      }
      result.addAll(superConnections);
      return result;
   }

   public void setSourceConnectionFeatures(EStructuralFeature[] sourceConnectionFeatures)
   {
      this.sourceConnectionFeatures = sourceConnectionFeatures;
   }

   public void setTargetConnectionFeatures(EStructuralFeature[] targetConnectionFeatures)
   {
      this.targetConnectionFeatures = targetConnectionFeatures;
   }
}
