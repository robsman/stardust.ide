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
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolGroupLayoutEditPolicy;


public class SymbolGroupEditPart extends AbstractNodeSymbolEditPart
{
   public SymbolGroupEditPart(WorkflowModelEditor editor, GroupSymbolType model)
   {
      super(editor, model);
   }

   protected void createEditPolicies()
   {
      super.createEditPolicies();

      installEditPolicy(EditPolicy.CONTAINER_ROLE, new SymbolContainerEditPolicy());
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new SymbolGroupLayoutEditPolicy());
   }

   protected List getModelChildren()
   {
      List result = new ArrayList();

      for (Iterator i = getGroupSymbol().getNodes().valueListIterator(); i.hasNext();)
      {
         result.add(i.next());
      }

      return result;
   }

   public List getChildren()
   {
      if (super.getChildren().isEmpty())
      {
         return super.getChildren();
      }
      else
      {
         return super.getChildren();
      }
   }

   protected IFigure createFigure()
   {
      Shape figure = new RectangleFigure();
      figure.setOpaque(false);
      figure.setFill(false);
      figure.setOutline(false);

      figure.setLayoutManager(new XYLayout());

      return figure;
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();

      Point offset = new Point(getGroupSymbol().getXPos(), getGroupSymbol().getYPos());
      Rectangle groupBounds = null;
      
      GraphicalEditPart parent = (GraphicalEditPart) this.getParent();
      
      for (Iterator i = getGroupSymbol().getNodes().valueListIterator(); i.hasNext();)
      {
         INodeSymbol node = (INodeSymbol) i.next();
         Point loc = new Point(node.getXPos(), node.getYPos());

         Dimension dim = new Dimension(-1, -1);
         if (node.isSetWidth())
         {
            dim.width = node.getWidth();
         }
         if (node.isSetHeight())
         {
            dim.height = node.getHeight();
         }

         Rectangle nodeBounds = new Rectangle(loc, dim);
         if (null != groupBounds)
         {
            if (!nodeBounds.isEmpty())
            {
               groupBounds = groupBounds.getUnion(nodeBounds);
            }
            else
            {
               groupBounds.union(nodeBounds.getTopLeft());
            }
         }
         else
         {
            groupBounds = nodeBounds;
         }
      }

      if ((null != parent) && (null != groupBounds))
      {
         groupBounds.translate(offset);
         groupBounds.setSize(-1, -1);
         ((GraphicalEditPart) parent).setLayoutConstraint(this, getFigure(), groupBounds);
      }  
   }
   
   public void refreshGroupChildren()
   {
      for (Iterator i = getChildren().iterator(); i.hasNext();)
      {
         EditPart element = (EditPart) i.next();
         element.refresh();
      }      
   }   

   public void handleNotification(Notification notification)
   {
      int type = notification.getEventType();
      int featureId = notification.getFeatureID(CarnotWorkflowModelPackage.class);
      for (Iterator i = getChildren().iterator(); i.hasNext();)
      {
         EditPart element = (EditPart) i.next();
         if(element instanceof SymbolGroupEditPart)
         {
            ((SymbolGroupEditPart) element).handleNotification(notification);
         }
         element.refresh();
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

         default:
            super.handleNotification(notification);
         }
      }
      else
      {
         super.handleNotification(notification);
      }
   }

   private GroupSymbolType getGroupSymbol()
   {
      return (GroupSymbolType) getModel();
   }
}