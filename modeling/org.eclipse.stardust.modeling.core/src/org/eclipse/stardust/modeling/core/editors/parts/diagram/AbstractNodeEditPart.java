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

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.PropertySourceFactory;
import org.eclipse.ui.views.properties.IPropertySource;


public abstract class AbstractNodeEditPart extends AbstractGraphicalEditPart
      implements NotificationAdaptee, NodeEditPart
{
   private NotificationAdapter notificationAdapter = null;

   private IPropertySource propertySource = null;

   private WorkflowModelEditor editor;

   public AbstractNodeEditPart(WorkflowModelEditor editor, EObject model)
   {
      setModel(model);
      this.editor = editor;
   }

   public void handleNotification(Notification notification)
   {
      refreshVisuals();
   }

   public void activate()
   {
      if (!isActive())
      {
         super.activate();

         final EObject model = getCastedModel();
         if (null != model)
         {
            model.eAdapters().add(getNotificationAdapter());
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         final EObject model = getCastedModel();
         if (null != model)
         {
            model.eAdapters().remove(getNotificationAdapter());
         }
         else if (null != getNotificationAdapter().getTarget())
         {
            getNotificationAdapter().getTarget().eAdapters().remove(
                  getNotificationAdapter());
         }

         super.deactivate();
      }
   }

   public Object getAdapter(Class key)
   {
      if (EditPart.class == key)
      {
         return this;
      }
      else if (IPropertySource.class == key)
      {
         return getPropertySource();
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
         propertySource = PropertySourceFactory.getPropertySource(this, getCastedModel());
      }
      return propertySource;
   }

   private EObject getCastedModel()
   {
      return (EObject) getModel();
   }

   public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ConnectionAnchor getSourceConnectionAnchor(Request request)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ConnectionAnchor getTargetConnectionAnchor(Request request)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }
   
   public void addNotify()
   {
      super.addNotify();
      getViewer().select(this);
      
      
      // necessary otherwise loading of a new model does not work (Bacardi Project)
      DiagramType diagram = null;
      if(getModel() instanceof DiagramType)
      {
         diagram = (DiagramType) getParent();
      }
      else
      {                  
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) getModel());      
      }
      if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
      {
         if(getParent() instanceof LaneEditPart)
         {
            ((LaneEditPart) getParent()).refreshContent();
         }
      }
   }

   /**
    * @return IconFactory if {@link #getEditor()} returns not null, 
    *                      otherwise {@link IconFactory#getDefault()}. 
    */
   protected IconFactory getIconFactory()
   {
      IconFactory iconFactory = IconFactory.getDefault();
   
      WorkflowModelEditor editor = getEditor();
      if (null != editor)
      {
         iconFactory = editor.getIconFactory();
      }
   
      return iconFactory;
   }
}