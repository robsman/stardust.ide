package org.eclipse.stardust.model.xpdl.builder.session;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;

public class Modification
{
   private static final Logger trace = LogManager.getLogger(Modification.class);

   private final String id;

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
      return changeDescription.getObjectsToAttach();
   }

   public Collection<EObject> changedObjects()
   {
      return changeDescription.getObjectChanges().keySet();
   }

   public Collection<EObject> removedObjects()
   {
      return changeDescription.getObjectsToDetach();
   }

   public ChangeDescription getChangeDescription()
   {
	   return changeDescription;
   }

   private enum State
   {
      UNDOABLE, REDOABLE,
   }
}
