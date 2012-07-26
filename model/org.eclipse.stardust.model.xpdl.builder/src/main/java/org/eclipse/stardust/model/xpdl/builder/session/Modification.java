/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.session;

import static org.eclipse.stardust.common.CollectionUtils.newHashMap;
import static org.eclipse.stardust.common.CollectionUtils.newHashSet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class Modification
{
   private final String id;

   private Map<String, String> metadata = newHashMap();

   private final ChangeDescription changeDescription;

   private State state;

   public Modification(ChangeDescription changeDescription)
   {
      this.id = UUID.randomUUID().toString();
      this.changeDescription = changeDescription;
      this.state = State.UNDOABLE;
   }

   public String getId()
   {
      return id;
   }

   public Map<String, String> getMetadata()
   {
      return metadata;
   }

   public boolean canUndo()
   {
      return (null != changeDescription) && (State.UNDOABLE == state);
   }

   public boolean canRedo()
   {
      return (null != changeDescription) && (State.REDOABLE == state);
   }

   public void undo()
   {
      if (canUndo())
      {
         changeDescription.applyAndReverse();
         this.state = State.REDOABLE;
      }
   }

   public void redo()
   {
      if (canRedo())
      {
         changeDescription.applyAndReverse();
         this.state = State.UNDOABLE;
      }
   }

   public Collection<EObject> addedObjects()
   {
      Set<EObject> result = newHashSet();
      for (EObject candidate : changeDescription.getObjectsToDetach())
      {
         if (isModelOrModelElement(candidate))
         {
            result.add(candidate);
         }
      }

      return result;
   }

   public Collection<EObject> changedObjects()
   {
      Set<EObject> result = newHashSet();

      collectChangedElements(changeDescription.getObjectChanges().keySet(), result);
      collectChangedElements(changeDescription.getObjectsToDetach(), result);
      collectChangedElements(changeDescription.getObjectsToAttach(), result);

      return result;
   }

   public Collection<EObject> removedObjects()
   {
      Set<EObject> result = newHashSet();
      for (EObject candidate : changeDescription.getObjectsToAttach())
      {
         if (isModelOrModelElement(candidate))
         {
            result.add(candidate);
         }
      }

      return result;
   }

   public ChangeDescription getChangeDescription()
   {
	   return changeDescription;
   }

   private void collectChangedElements(Collection<EObject> candidates, Set<EObject> result)
   {
      for (EObject changedObject : candidates)
      {
         while ( !isModelOrModelElement(changedObject))
         {
            changedObject = changedObject.eContainer();
         }
         result.add(changedObject);
      }
   }

   private boolean isModelOrModelElement(EObject changedObject)
   {
      return (changedObject instanceof ModelType)
      || (changedObject instanceof IModelElement)
      || (changedObject instanceof org.eclipse.stardust.model.xpdl.xpdl2.Extensible);
   }

   private enum State
   {
      UNDOABLE, REDOABLE,
   }
}
