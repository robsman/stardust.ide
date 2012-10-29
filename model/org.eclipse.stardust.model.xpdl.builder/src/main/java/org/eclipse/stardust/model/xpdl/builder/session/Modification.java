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

import static java.util.Collections.unmodifiableCollection;
import static org.eclipse.stardust.common.CollectionUtils.isEmpty;
import static org.eclipse.stardust.common.CollectionUtils.newHashMap;
import static org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.findContainingModel;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class Modification
{
   private final String id;

   private final EditingSession session;

   private Map<String, String> metadata = newHashMap();

   private final ChangeDescription changeDescription;

   private State state;

   private final Set<EObject> modifiedElements = new CopyOnWriteArraySet<EObject>();

   private final Set<EObject> addedElements = new CopyOnWriteArraySet<EObject>();

   private final Set<EObject> removedElements = new CopyOnWriteArraySet<EObject>();

   public Modification(EditingSession session, ChangeDescription changeDescription)
   {
      this.id = UUID.randomUUID().toString();
      this.session = session;
      this.changeDescription = changeDescription;
      this.state = State.UNDOABLE;

      normalizeChangeSet();
   }

   public String getId()
   {
      return id;
   }

   public EditingSession getSession()
   {
      return session;
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

         normalizeChangeSet();
      }
   }

   public void redo()
   {
      if (canRedo())
      {
         changeDescription.applyAndReverse();
         this.state = State.UNDOABLE;

         normalizeChangeSet();
      }
   }

   public Collection<EObject> getAddedElements()
   {
      return unmodifiableCollection(addedElements);
   }

   public Collection<EObject> getModifiedElements()
   {
      return unmodifiableCollection(modifiedElements);
   }

   public Collection<EObject> getRemovedElements()
   {
      return unmodifiableCollection(removedElements);
   }

   public ChangeDescription getChangeDescription()
   {
      return changeDescription;
   }

   private void normalizeChangeSet()
   {
      modifiedElements.clear();
      addedElements.clear();
      removedElements.clear();

      // modified
      collectChangedElements(changeDescription.getObjectChanges().keySet(),
            modifiedElements);
      for (EObject candidate : changeDescription.getObjectsToDetach())
      {
         if ((null != findContainingModel(candidate))
               && !isModelOrModelElement(candidate))
         {
            // report any change to a non-element sub-object as modification of the
            // containing parent element
            modifiedElements.add(determineChangedElement(candidate));
         }
      }
      // removed objects will automatically be reported as modifications of their
      // container

      // added
      for (EObject candidate : changeDescription.getObjectsToDetach())
      {
         if ((null == findContainingModel(candidate)) || isModelOrModelElement(candidate))
         {
            addedElements.add(candidate);
         }
      }

      // removed
      for (EObject candidate : changeDescription.getObjectsToAttach())
      {
         if (isModelOrModelElement(candidate))
         {
            removedElements.add(candidate);
         }
      }

      modifiedElements.removeAll(addedElements);
      modifiedElements.removeAll(removedElements);
   }

   private void collectChangedElements(Collection<EObject> candidates, Set<EObject> result)
   {
      for (EObject changedObject : candidates)
      {
         result.add(determineChangedElement(changedObject));
      }
   }

   public EObject determineChangedElement(EObject changedObject)
   {
      EObject element = changedObject;
      while ((null != element) && !isModelOrModelElement(element))
      {
         element = element.eContainer();
      }
      return (null != element) ? element : changedObject;
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

   // TODO refactor to a more restricted interface
   public boolean wasModified(EObject eObject, EStructuralFeature eFeature)
   {
      EList<FeatureChange> changes = Modification.this.getChangeDescription()
            .getObjectChanges()
            .get(eObject);
      if ( !isEmpty(changes))
      {
         for (FeatureChange change : changes)
         {
            if (eFeature == change.getFeature())
            {
               return true;
            }
         }
      }
      return false;
   }

   public <T extends EObject> T findContainer(EObject element, Class<T> containerType)
   {
      EObject currentElement = element;
      while (null != currentElement)
      {
         EObject currentContainer = currentElement.eContainer();
         if ((currentContainer instanceof ChangeDescription)
               && getRemovedElements().contains(currentElement)
               && (getChangeDescription() instanceof ChangeDescriptionImpl))
         {
            // substitute with real container (the one containing the element before it was detached)
            currentContainer = ((ChangeDescriptionImpl) getChangeDescription()).getOldContainer(currentElement);
         }

         if (containerType.isInstance(currentContainer))
         {
            return containerType.cast(currentContainer);
         }
         else
         {
            // navigate one level up
            currentElement = currentContainer;
         }
      }

      return null;
   }

   public void markUnmodified(EObject element)
   {
      // this is safe as sets are copy-on-write
      modifiedElements.remove(element);
      addedElements.remove(element);
      removedElements.remove(element);
   }

   public void markAlsoModified(EObject element)
   {
      // this is safe as sets are copy-on-write
      modifiedElements.add(element);

      addedElements.remove(element);
      removedElements.remove(element);
   }

   public void markAlsoAdded(EObject element)
   {
      // this is safe as sets are copy-on-write
      addedElements.add(element);

      modifiedElements.remove(element);
      removedElements.remove(element);
   }

   public void markAlsoRemoved(EObject element)
   {
      // this is safe as sets are copy-on-write
      removedElements.add(element);

      modifiedElements.remove(element);
      addedElements.remove(element);
   }

}
