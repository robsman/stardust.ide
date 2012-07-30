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

import static org.eclipse.stardust.common.CollectionUtils.newHashSet;

import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class EditingSession
{
   private static final Logger trace = LogManager.getLogger(EditingSession.class);

   private final String id;

   private final Set<ModelType> models = newHashSet();

   private final ChangeRecorder emfChangeRecorder = new ChangeRecorder();

   private final Stack<Modification> undoableModifications = new Stack<Modification>();

   private final Stack<Modification> redoableModifications = new Stack<Modification>();

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

   public boolean isTrackingModel(ModelType model)
   {
      return models.contains(model);
   }

   public void trackModel(ModelType model)
   {
      if ( !models.contains(model))
      {
         models.add(model);
      }
   }

   public boolean isInEditMode()
   {
      return emfChangeRecorder.isRecording();
   }

   public boolean beginEdit()
   {
      if ( !isInEditMode())
      {
         emfChangeRecorder.beginRecording(models);

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
