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
package org.eclipse.stardust.modeling.core.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.*;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EventHandlingTableContentProvider
      extends EventHandlingNotificationAdapter
      implements ITreeContentProvider
{
   private final String conditionTypeId;
   private TreeViewer viewer;

   public EventHandlingTableContentProvider(String conditionTypeId)
   {
      this.conditionTypeId = conditionTypeId;
   }

   public Object[] getChildren(Object parentElement)
   {
      if (parentElement instanceof EventHandlerType)
      {
         EventHandlerType handler = (EventHandlerType) parentElement;
         ArrayList result = new ArrayList();
         addActions(result, handler.getBindAction());
         addActions(result, handler.getEventAction());
         addActions(result, handler.getUnbindAction());
         return result.toArray();
      }
      return new Object[0];
   }

   private void addActions(List result, List actions)
   {
      for (Iterator i = actions.iterator(); i.hasNext();)
      {
         result.add(i.next());
      }
   }

   public Object getParent(Object element)
   {
      return element instanceof AbstractEventAction ?
            ((EObject) element).eContainer() : null;
   }

   public boolean hasChildren(Object element)
   {
      return element instanceof EventHandlerType && (
            !((EventHandlerType) element).getBindAction().isEmpty()
         || !((EventHandlerType) element).getEventAction().isEmpty()
         || !((EventHandlerType) element).getUnbindAction().isEmpty());
   }

   public Object[] getElements(Object inputElement)
   {
      ArrayList result = new ArrayList();
      IEventHandlerOwner owner = (IEventHandlerOwner) inputElement;
      List list = owner.getEventHandler();
      for (Iterator i = list.iterator(); i.hasNext();)
      {
         EventHandlerType handler = (EventHandlerType) i.next();
         EventConditionTypeType type = handler.getType();
         if (type != null && conditionTypeId.equals(type.getId()))
         {
            result.add(handler);
         }
      }
      return result.toArray();
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      dispose();
      init((IEventHandlerOwner) newInput);
      this.viewer = (TreeViewer) viewer;
   }

   public void actionChanged(AbstractEventAction action, String property)
   {
      viewer.update(action, new String[] {property});
   }

   public void actionAdded(AbstractEventAction action)
   {
      viewer.refresh(action.eContainer());
   }

   public void actionMoved(AbstractEventAction action)
   {
      viewer.refresh(action.eContainer());
   }

   public void actionRemoved(AbstractEventAction action)
   {
      viewer.refresh(action.eContainer());
   }

   public void handlerChanged(EventHandlerType handler, String property)
   {
      viewer.update(handler, new String[] {property});
   }

   public void handlerAdded(EventHandlerType handler)
   {
      viewer.refresh();
   }

   public void handlerMoved(EventHandlerType handler)
   {
      viewer.refresh();
   }

   public void handlerRemoved(EventHandlerType handler)
   {
      viewer.refresh();
   }
}
