package org.eclipse.stardust.model.xpdl.builder.session;

import java.util.Collections;
import java.util.Stack;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class EditingSession
{
   private static final Logger trace = LogManager.getLogger(EditingSession.class);

   private final ModelType model;

   private final ChangeRecorder emfChangeRecorder = new ChangeRecorder();

   private final Stack<Modification> undoableModifications = new Stack<Modification>();

   private final Stack<Modification> redoableModifications = new Stack<Modification>();

   public EditingSession(ModelType model)
   {
      this.model = model;
   }

   public boolean isInEditMode()
   {
      return emfChangeRecorder.isRecording();
   }

   public boolean beginEdit()
   {
	  if ( !isInEditMode())
      {
         emfChangeRecorder.beginRecording(Collections.singleton(model));

         return isInEditMode();
      }
      else
      {
         return false;
      }
   }

   public boolean endEdit()
   {
	  if (isInEditMode())
      {
         ChangeDescription changeDescription = emfChangeRecorder.endRecording();
         if ( !redoableModifications.isEmpty())
         {
            redoableModifications.clear();
         }
         undoableModifications.push(new Modification(changeDescription));

         return !isInEditMode();
      }
      else
      {
         return false;
      }
   }

   public boolean canUndo()
   {
      return !undoableModifications.isEmpty()
            && undoableModifications.peek().canUndo();
   }

   public Modification getPendingUndo()
   {
      return !undoableModifications.isEmpty() ? undoableModifications.peek() : null;
   }

   public boolean canRedo()
   {
      return !redoableModifications.isEmpty()
            && redoableModifications.peek().canRedo();
   }

   public Modification getPendingRedo()
   {
      return !redoableModifications.isEmpty() ? redoableModifications.peek() : null;
   }

   public Modification undoLast()
   {
      int nUndos = 0;
      Modification change = null;

      if (canUndo())
      {
         change = undoableModifications.pop();
         change.undo();
         ++nUndos;
         redoableModifications.push(change);
      }

      //return 0 < nUndos;
      return change;
   }

   public Modification redoNext()
   {
      int nRedos = 0;
      Modification change = null;

      if (canRedo())
      {
         change = redoableModifications.pop();
         change.redo();
         ++nRedos;
         undoableModifications.push(change);
      }

      //return 0 < nRedos;
      return change;
   }

}
