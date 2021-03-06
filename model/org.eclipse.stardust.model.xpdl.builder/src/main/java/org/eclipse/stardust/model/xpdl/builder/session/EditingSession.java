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

import static java.util.Collections.unmodifiableSet;
import static org.eclipse.stardust.common.CollectionUtils.newHashSet;

import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;

import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;

public class EditingSession
{
   private static final Logger trace = LogManager.getLogger(EditingSession.class);

   private final String id;

   private final Set<EObject> models = newHashSet();

   private ChangeRecorder emfChangeRecorder = null;

   private final Stack<Modification> undoableModifications = new Stack<Modification>();

   private final Stack<Modification> redoableModifications = new Stack<Modification>();

   private boolean keepChangeRecorder;

   public EditingSession()
   {
      this(UUID.randomUUID().toString());
   }

   public EditingSession(String id)
   {
      this.id = id;
   }

   public String getId()
   {
      return id;
   }

   public boolean isTrackingModel(EObject model)
   {
      return models.contains(model);
   }

   public Set<EObject> getTrackedModels()
   {
      return unmodifiableSet(models);
   }

   public void reset()
   {
      if (isInEditMode())
      {
         endEdit(false);
      }
      clearUndoRedoStack();

      models.clear();
   }

   public void trackModel(EObject model)
   {
      if ( !models.contains(model))
      {
         models.add(model);
      }
   }

   public boolean isInEditMode()
   {
      return (null != emfChangeRecorder) && emfChangeRecorder.isRecording();
   }


   public boolean beginEdit()
   {
      keepChangeRecorder = true;
      if (!isInEditMode())
      {
         if (this.emfChangeRecorder == null)
         {
            this.emfChangeRecorder = new ChangeRecorder()
            {
               @Override
               protected boolean isOrphan(EObject eObject)
               {
                  // the models being watched should never be considered orphans
                  return !models.contains(eObject) && super.isOrphan(eObject);
               }

               public ChangeDescription endRecording()
               {
                 if (isRecording())
                 {
                   setRecording(keepChangeRecorder);
                   consolidateChanges();
                   return getChangeDescription();
                 }
                 return null;
               }
            };
            this.emfChangeRecorder.setResolveProxies(true);
            this.emfChangeRecorder.beginRecording(models);
         }
         return isInEditMode();
      }
      else
      {
         return false;
      }
   }

   public boolean endEdit(boolean keepCR)
   {
      if (isInEditMode())
      {
         this.keepChangeRecorder = keepCR;

         ChangeDescription changeDescription = emfChangeRecorder.endRecording();
         if (!this.keepChangeRecorder)
         {
            emfChangeRecorder.dispose();
            this.emfChangeRecorder = null;
         }

         if (!redoableModifications.isEmpty())
         {
            redoableModifications.clear();
         }

         if (this.keepChangeRecorder)
         {
            undoableModifications.push(new Modification(this, changeDescription));
         }

         return !isInEditMode();
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
         emfChangeRecorder.dispose();
         this.emfChangeRecorder = null;

         if ( !redoableModifications.isEmpty())
         {
            redoableModifications.clear();
         }
         undoableModifications.push(new Modification(this, changeDescription));

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
      Modification change = null;

      if (canUndo())
      {
         change = undoableModifications.pop();
         change.undo();
         redoableModifications.push(change);
      }
      return change;
   }

   public Modification redoNext()
   {
      Modification change = null;
      if (canRedo())
      {
         change = redoableModifications.pop();
         change.redo();
         undoableModifications.push(change);
      }
      return change;
   }

   public void clearUndoRedoStack()
   {
      undoableModifications.clear();
      redoableModifications.clear();
   }

}
