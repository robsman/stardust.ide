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
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.decoration.DecorationUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractSwimlaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DiagramComponentEditPolicy;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.ui.PlatformUI;


public abstract class AbstractSwimlaneEditPart extends AbstractNodeSymbolEditPart
{
   public AbstractSwimlaneEditPart(WorkflowModelEditor editor, ISwimlaneSymbol swimlane)
   {
      super(editor, swimlane);
   }

   public ISwimlaneSymbol getSwimlaneModel()
   {
      return (ISwimlaneSymbol) getModel();
   }

   public AbstractSwimlaneFigure getSwimlaneFigure()
   {
      return (AbstractSwimlaneFigure) getFigure();
   }

   public IFigure createFeedbackFigure()
   {
      return null;
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DiagramComponentEditPolicy());

      //installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);      
      installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$            
   }

   protected List getModelChildren()
   {
      // TODO handle default lane content 
      return getSwimlaneModel().getChildLanes();
   }

   public void handleNotification(Notification notification)
   {
      switch (notification.getFeatureID(ISwimlaneSymbol.class))
      {
         case CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES :
            refreshChildren();            
            break;
         default :
            super.handleNotification(notification);
            break;
      }
   }
   
   protected void addChildVisual(EditPart childEditPart, int index)
   {
      super.addChildVisual(childEditPart, index);
      DecorationUtils.applyDecorations(childEditPart, decorators);
   }

   protected void removeChildVisual(EditPart childEditPart)
   {
      DecorationUtils.removeDecorations(childEditPart, decorators);
      super.removeChildVisual(childEditPart);
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();
      getSwimlaneFigure().setName(getSwimlaneModel().getName());
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
      else
      {
         result = super.getAdapter(key);
      }
      return result;
   }
}