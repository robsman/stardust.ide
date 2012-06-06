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
package org.eclipse.stardust.modeling.core.editors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.EObjectInvocationHandler;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.IssueDelta;

public class ValidationIssueManager
{
   private final ListenerList validationEventListeners = new ListenerList();

   private final Map/* <Object, ValidationsStatus> */statusRegistry = new HashMap();

   public void addValidationEventListener(IValidationEventListener listener)
   {
      validationEventListeners.add(listener);
   }

   public void removeValidationEventListener(IValidationEventListener listener)
   {
      validationEventListeners.remove(listener);
   }
   
   public void pushCurrentStatus(IValidationEventListener listener)
   {
      fireIssuesUpdated(statusRegistry.keySet(), new Object[] {listener});
   }
   
   public IValidationStatus getStatus(IModelElement element)
   {
      ModelElementValidationStatus status = (ModelElementValidationStatus) statusRegistry.get(element);
      
      return (null != status) ? status : IValidationStatus.OK;
   }

   public void resetIssues(Issue[] issues)
   {
      Set affectedElements = new HashSet();

      // reset current status
      for (Iterator i = statusRegistry.entrySet().iterator(); i.hasNext();)
      {
         Map.Entry entry = (Map.Entry) i.next();
         ModelElementValidationStatus status = (ModelElementValidationStatus) entry.getValue();
         if (status.hasIssues() || status.hasChildrenWithIssues())
         {
            affectedElements.add(entry.getKey());
         }
      }
      statusRegistry.clear();

      // merge deltas into status
      for (int i = 0; i < issues.length; ++i )
      {
         Issue issue = issues[i];
         EObject modelElement = issue.getModelElement();
         
         if (modelElement != null)
         {
            if (modelElement instanceof Proxy && modelElement instanceof ModelType)
            {
               Proxy proxy = (Proxy) modelElement;
               InvocationHandler ih = Proxy.getInvocationHandler(proxy);
               if (ih instanceof EObjectInvocationHandler)
               {
                  EObject model = ((EObjectInvocationHandler) ih).getModel();
                  if(model instanceof ModelType)
                  {
                     modelElement = model;
                  }                     
               }
            }
            
            affectedElements.add(modelElement);
            
            ModelElementValidationStatus status = (ModelElementValidationStatus) statusRegistry.get(modelElement);
            if (null == status)
            {
               status = new ModelElementValidationStatus();
               statusRegistry.put(modelElement, status);
            }
            status.addIssue(issue);
         }
      }
      
      affectedElements.addAll(propagateChildStatus(affectedElements));

      // broadcast modifications
      fireIssuesUpdated(affectedElements, validationEventListeners.getListeners());
   }
   
   public void handleIssueUpdate(IssueDelta[] deltas)
   {
      Set affectedElements = new HashSet();

      // merge deltas into status
      for (int i = 0; i < deltas.length; ++i )
      {
         IssueDelta delta = deltas[i];
         
         if (delta.getTarget() instanceof EObject)
         {
            EObject element = (EObject) delta.getTarget();
            
            affectedElements.add(element);
            
            ModelElementValidationStatus status = (ModelElementValidationStatus) statusRegistry.get(element);
            if (null == status)
            {
               status = new ModelElementValidationStatus();
               statusRegistry.put(element, status);
            }
            
            List removedIssues = delta.getRemovedIssues();
            for (int j = 0; j < removedIssues.size(); j++ )
            {
               status.removeIssue((Issue) removedIssues.get(j));
            }

            List addedIssues = delta.getAddedIssues();
            for (int j = 0; j < addedIssues.size(); j++ )
            {
               status.addIssue((Issue) addedIssues.get(j));
            }

            // modified issue are assumed to be updated inline already
         }
      }
      
      affectedElements.addAll(propagateChildStatus(affectedElements));

      // broadcast modifications
      fireIssuesUpdated(affectedElements, validationEventListeners.getListeners());
   }

   private void fireIssuesUpdated(Set<?> affectedElements, final Object[] listeners)
   {
      for (Iterator<?> itr = affectedElements.iterator(); itr.hasNext();)
      {
         Object element = itr.next();
         IValidationStatus status = (ModelElementValidationStatus) statusRegistry.get(element);
         if (null == status)
         {
            status = IValidationStatus.OK;
         }

         if (element instanceof EObject)
         {
            for (int i = 0; i < listeners.length; i++ )
            {
               IValidationEventListener listener = (IValidationEventListener) listeners[i];
                  listener.onIssuesUpdated((EObject) element, status);
            }
         }               
      }
   }

   private Set<?> propagateChildStatus(Set<?> affectedElements)
   {
      // propagate along containment hierarchy
      Set<Object> updatedParents = CollectionUtils.newSet();
      for (Object target : affectedElements)
      {
         if ((target instanceof EObject) && statusRegistry.containsKey(target))
         {
            EObject element = (EObject) target;
            ModelElementValidationStatus elementStatus = (ModelElementValidationStatus) statusRegistry.get(element);

            // update previously affected parents (i.e. process definition after activity
            // was deleted); make copy to be safe against modifications
            List<?> oldParents = new ArrayList<Object>(elementStatus.getAffectedParents());
            for (int j = 0; j < oldParents.size(); j++ )
            {
               Object parent = oldParents.get(j);
               updatedParents.add(parent);
               updateChildElementStatus(parent, element, elementStatus);
            }

            ModelType model = ModelUtils.findContainingModel(element);
            if (model != null && model != element)
            {
               EObject parent = element;
               do
               {
                  parent = parent.eContainer();
                  if (!elementStatus.getAffectedParents().contains(parent))
                  {
                     updatedParents.add(parent);
                     updateChildElementStatus(parent, element, elementStatus);
                  }
               }
               while (parent != model);
            }
         }                     
      }
      return updatedParents;
   }

   private void updateChildElementStatus(Object parent, EObject child,
         ModelElementValidationStatus childStatus)
   {
      if (null != parent)
      {
         ModelElementValidationStatus parentStatus = (ModelElementValidationStatus) statusRegistry.get(parent);
         if (null == parentStatus)
         {
            parentStatus = new ModelElementValidationStatus();
            statusRegistry.put(parent, parentStatus);
         }
         parentStatus.updateChildStatus(child, childStatus);

         // associate parent to be able to update parent even after reparenting element
         if (childStatus.hasIssues())
         {
            childStatus.addAffectedParent(parent);
         }
         else
         {
            childStatus.removeAffectedParent(parent);
         }
      }
   }

   public boolean hasIssues()
   {
      for (Iterator iter = statusRegistry.values().iterator(); iter.hasNext();)
      {
         IValidationStatus status = (IValidationStatus) iter.next();
         if (!IValidationStatus.OK.equals(status))
         {
            return true;
         }
      }
      return false;
   }
}