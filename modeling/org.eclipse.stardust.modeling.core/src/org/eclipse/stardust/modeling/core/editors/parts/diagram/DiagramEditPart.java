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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.*;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.decoration.DecorationUtils;
import org.eclipse.stardust.modeling.core.decoration.IDecoratablePart;
import org.eclipse.stardust.modeling.core.decoration.IDecorationProvider;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.DiagramLayer;
import org.eclipse.stardust.modeling.core.editors.figures.SymbolContainerLayout;
import org.eclipse.stardust.modeling.core.editors.figures.routers.DiagramShortestPathConnectionRouter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.ConnectionBendpointEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DiagramComponentEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerLayoutEditPolicy;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.ui.PlatformUI;


public class DiagramEditPart extends AbstractGraphicalEditPart
      implements Adapter, IDecoratablePart
{
   private EditPolicy connectionBendpointEditPolicy;

   private Notifier target;

   private PoolEditPart poolDelegate;
   
   private HashSet decorations = new HashSet();

   private boolean viewPool;
   private DiagramType diagram;
   
   public void setViewPool()
   {
      if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
      {
         viewPool = true;
      } 
      else
      {
         viewPool = false;         
      }
      refreshChildren();
   }
   
   public DiagramEditPart(WorkflowModelEditor editor, DiagramType model)
   {
      diagram = model;
      viewPool = diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL);    
      
      PoolSymbol pool = DiagramUtil.getDefaultPool(model);
      if (pool != null)
      {
         poolDelegate = new PoolEditPart(editor, pool)
         {
            protected void refreshChildren()
            {
               DiagramEditPart.this.refreshChildren();
            }
         };
      }      
      setModel(model);
   }

   public PoolEditPart getPoolDelegate()
   {
      return poolDelegate;
   }
   
   protected void addChildVisual(EditPart childEditPart, int index)
   {
      super.addChildVisual(childEditPart, index);
      DecorationUtils.applyDecorations(childEditPart, decorations);
   }

   protected void removeChildVisual(EditPart childEditPart)
   {
      DecorationUtils.removeDecorations(childEditPart, decorations);
      super.removeChildVisual(childEditPart);
   }

   public void activate()
   {
      if (!isActive())
      {
         hookIntoModel(getCastedModel());
         if (poolDelegate != null)
         {
            poolDelegate.activate();
         }
         super.activate();
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         unhookFromModel(getCastedModel());
         if (poolDelegate != null)
         {
            poolDelegate.deactivate();
         }
         super.deactivate();
      }
   }

   protected EditPolicy getConnectionBendpointEditPolicy()
   {
      if (connectionBendpointEditPolicy == null)
      {
         connectionBendpointEditPolicy = new ConnectionBendpointEditPolicy();
      }
      return connectionBendpointEditPolicy;
   }

   protected void createEditPolicies()
   {
      if(!DiagramUtil.isDiagramModelElementProxy((EObject) getModel()))
      {
         installEditPolicy(EditPolicy.COMPONENT_ROLE, new DiagramComponentEditPolicy());
         installEditPolicy(EditPolicy.CONTAINER_ROLE, new SymbolContainerEditPolicy());
         // handles constraint changes (e.g. moving and/or resizing) of model elements
         // and creation of new model elements
         installEditPolicy(EditPolicy.LAYOUT_ROLE, new SymbolContainerLayoutEditPolicy());
   
         installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
      }
   }

   protected IFigure createFigure()
   {
      Figure f = new DiagramLayer();
      f.setBorder(new MarginBorder(3));
      f.setLayoutManager(new SymbolContainerLayout());

      DiagramShortestPathConnectionRouter router = new DiagramShortestPathConnectionRouter(f);
      ConnectionLayer connLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
      connLayer.setConnectionRouter(router);

      return f;
   }

   private DiagramType getCastedModel()
   {
      return (DiagramType) getModel();
   }

   protected List getModelChildren()
   {
      List nodes = new ArrayList(getCastedModel().getNodes().size());
      if (poolDelegate != null && !viewPool)
      {
         nodes.addAll(poolDelegate.getModelChildren());
      }
      else
      {
         nodes.addAll(getCastedModel().getPoolSymbols());
         nodes.addAll(getCastedModel().getActivitySymbol());
         nodes.addAll(getCastedModel().getAnnotationSymbol());
         nodes.addAll(getCastedModel().getApplicationSymbol());
         nodes.addAll(getCastedModel().getConditionalPerformerSymbol());
         nodes.addAll(getCastedModel().getDataSymbol());
         nodes.addAll(getCastedModel().getEndEventSymbols());
         nodes.addAll(getCastedModel().getProcessInterfaceSymbols());
         nodes.addAll(getCastedModel().getGatewaySymbol());
         nodes.addAll(getCastedModel().getGroupSymbol());
         nodes.addAll(getCastedModel().getIntermediateEventSymbols());
         nodes.addAll(getCastedModel().getModelerSymbol());
         nodes.addAll(getCastedModel().getOrganizationSymbol());
         nodes.addAll(getCastedModel().getProcessSymbol());
         nodes.addAll(getCastedModel().getRoleSymbol());
         nodes.addAll(getCastedModel().getStartEventSymbols());
         nodes.addAll(getCastedModel().getTextSymbol());
      }
      return nodes;
   }

   public Object getAdapter(Class key)
   {
      Object result = null;

      if (SnapToHelper.class.equals(key))
      {
         List snapStrategies = new ArrayList(3);
         // snapToGeometry should be always available 
         if (Boolean.TRUE.equals(getViewer().getProperty(
               SnapToGeometry.PROPERTY_SNAP_ENABLED)))
         {
            snapStrategies.add(new SnapToGeometry(this));
         }         
         if(PlatformUI.getPreferenceStore()
               .getBoolean(BpmProjectNature.PREFERENCE_SNAP_GRID_MODE))
         {
            if (Boolean.TRUE.equals(getViewer().getProperty(
                  RulerProvider.PROPERTY_RULER_VISIBILITY)))
            {
               snapStrategies.add(new SnapToGuides(this));
            }
            if (Boolean.TRUE.equals(getViewer()
                  .getProperty(SnapToGrid.PROPERTY_GRID_ENABLED)))
            {
               snapStrategies.add(new SnapCenterToGrid(this));
            }            
         }
         if (1 == snapStrategies.size())
         {
            return snapStrategies.get(0);
         }
         else if (1 < snapStrategies.size())
         {
            result = new CompoundSnapToHelper((SnapToHelper[]) snapStrategies
                  .toArray(new SnapToHelper[snapStrategies.size()]));
         }
      }
      else if (IModelElement.class.equals(key))
      {
         return getModel();
      }
      else
      {
         result = super.getAdapter(key);
      }
      return result;
   }

   public void applyDecoration(IDecorationProvider decoration)
   {
      // decorate children
      DecorationUtils.applyDecoration(decoration, getChildren());
      decorations.add(decoration);
   }

   public void removeDecoration(IDecorationProvider decoration)
   {
      // remove decoration from children
      DecorationUtils.removeDecoration(decoration, getChildren());
      decorations.remove(decoration);
   }

   private void hookIntoModel(EObject model)
   {
      if (model != null)
      {
         model.eAdapters().add(this);
      }
   }

   private void unhookFromModel(EObject model)
   {
      if (model != null)
      {
         model.eAdapters().remove(this);
      }
   }

   public void notifyChanged(Notification notification)
   {
      int type = notification.getEventType();
      int featureId = notification.getFeatureID(CarnotWorkflowModelPackage.class);
      
      PoolSymbol poolSymbol = DiagramUtil.getDefaultPool((DiagramType) getModel());
      switch (featureId)
      {      
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE :
            setViewPool();
            refresh();
            
            List children = new ArrayList();
            if(viewPool)
            {               
               if(!getChildren().isEmpty())
               {
                  children = ((PoolEditPart) getChildren().get(0)).getChildren();                  
               }
            }
            else
            {
               children = getChildren();
            }            
            for (Iterator i = children.iterator(); i.hasNext();)
            {
               AbstractNodeSymbolEditPart element = (AbstractNodeSymbolEditPart) i.next();
               if(element instanceof SymbolGroupEditPart)
               {
                  ((SymbolGroupEditPart) element).refreshGroupChildren();
               }
            }
            break;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION :
            if(poolSymbol != null)
            {
               viewPool = false;  
               // height and width must be exchanged
               int height = ((INodeSymbol) poolSymbol).getHeight();
               int width = ((INodeSymbol) poolSymbol).getWidth();
               ((INodeSymbol) poolSymbol).setHeight(width);
               ((INodeSymbol) poolSymbol).setWidth(height);  
               refresh();            
               setViewPool();               
            }
            refresh();
            break;
      } 
      
      if (type == Notification.ADD || type == Notification.REMOVE)
      {
         switch (featureId)
         {
         // TODO
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__TRANSITION_CONNECTION:
            for (Iterator i = getChildren().iterator(); i.hasNext();)
            {
               EditPart element = (EditPart) i.next();
               element.refresh();
            }

         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ACTIVITY_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ANNOTATION_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__APPLICATION_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__CONDITIONAL_PERFORMER_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__DATA_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__END_EVENT_SYMBOLS:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__PROCESS_INTERFACE_SYMBOLS:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__GROUP_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__GATEWAY_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__INTERMEDIATE_EVENT_SYMBOLS:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODELER_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORGANIZATION_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__PROCESS_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ROLE_SYMBOL:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__START_EVENT_SYMBOLS:
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__TEXT_SYMBOL:

            refreshChildren();
            break;
         }
      }
   }

   public Notifier getTarget()
   {
      return target;
   }

   public void setTarget(Notifier newTarget)
   {
      this.target = newTarget;
   }

   public boolean isAdapterForType(Object type)
   {
      return (getModel().getClass() == type);
   }
}