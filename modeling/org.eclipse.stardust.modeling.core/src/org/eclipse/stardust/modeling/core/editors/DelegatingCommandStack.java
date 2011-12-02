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

import java.util.EventObject;

import org.eclipse.gef.commands.*;
import org.eclipse.jface.util.ListenerList;

public class DelegatingCommandStack extends CommandStack
      implements CommandStackListener, CommandStackEventListener
{
   private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

   /** the current command stack */
   private CommandStack currentCommandStack;
   
   private ListenerList eventListeners = new ListenerList();

   public CommandStack getCurrentCommandStack()
   {
      return currentCommandStack;
   }

   public void setCurrentCommandStack(CommandStack stack)
   {
      if (currentCommandStack != stack)
      {
         if (null != currentCommandStack)
         {
            currentCommandStack.removeCommandStackEventListener(this);
            currentCommandStack.removeCommandStackListener(this);
         }
         // set new command stack
         this.currentCommandStack = stack;
         // watch new command stack
         currentCommandStack.addCommandStackEventListener(this);
         currentCommandStack.addCommandStackListener(this);
         // the command stack changed
         notifyListeners();
         stackChanged(new CommandStackEvent(this, null, CommandStack.POST_EXECUTE));
      }
   }

   public boolean canRedo()
   {
      return (null == currentCommandStack) ? false : currentCommandStack.canRedo();
   }

   public boolean canUndo()
   {
      return (null == currentCommandStack) ? false : currentCommandStack.canUndo();
   }

   public void dispose()
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.dispose();
      }
   }

   public void execute(Command command)
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.execute(command);
      }
   }

   public void flush()
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.flush();
      }
   }

   public Object[] getCommands()
   {
      return (null == currentCommandStack)
            ? EMPTY_OBJECT_ARRAY
            : currentCommandStack.getCommands();
   }

   public Command getRedoCommand()
   {
      return (null == currentCommandStack)
            ? UnexecutableCommand.INSTANCE
            : currentCommandStack.getRedoCommand();
   }

   public Command getUndoCommand()
   {
      return (null == currentCommandStack)
            ? UnexecutableCommand.INSTANCE
            : currentCommandStack.getUndoCommand();
   }

   public int getUndoLimit()
   {
      return (null == currentCommandStack) ? -1 : currentCommandStack.getUndoLimit();
   }

   public boolean isDirty()
   {
      return (null == currentCommandStack) ? false : currentCommandStack.isDirty();
   }

   public void markSaveLocation()
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.markSaveLocation();
      }
   }

   public void redo()
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.redo();
      }
   }

   public void setUndoLimit(int undoLimit)
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.setUndoLimit(undoLimit);
      }
   }

   public void undo()
   {
      if (null != currentCommandStack)
      {
         currentCommandStack.undo();
      }
   }

   public void addCommandStackEventListener(CommandStackEventListener listener)
   {
      this.eventListeners.add(listener);
   }

   public void removeCommandStackEventListener(CommandStackEventListener listener)
   {
      this.eventListeners.remove(listener);
   }

   public String toString()
   {
      return "DelegatingCommandStack(" + currentCommandStack + ")"; //$NON-NLS-1$ //$NON-NLS-2$
   }

   public void stackChanged(CommandStackEvent event)
   {
      final Object[] listeners = eventListeners.getListeners();
      for (int i = 0; i < listeners.length; i++ )
      {
         ((CommandStackEventListener) listeners[i]).stackChanged(event);
      }
   }

   public void commandStackChanged(EventObject event)
   {
      notifyListeners();
   }
}