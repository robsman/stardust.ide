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
package org.eclipse.stardust.modeling.core.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class FileEditorInputTracker extends FileResourceChangeListener
{
   private final IEditorPart editor;

   private final ListenerList listeners;

   public FileEditorInputTracker(IEditorPart editor)
   {
      this.editor = editor;

      this.listeners = new ListenerList();
   }

   public IFile getFile()
   {
      return ((FileEditorInput) editor.getEditorInput()).getFile();
   }

   public void addChangeVisitor(IResourceDeltaVisitor visitor)
   {
      listeners.add(visitor);
   }

   public void removeChangeVisitor(IResourceDeltaVisitor visitor)
   {
      listeners.remove(visitor);
   }

   public boolean fileChanged(IResourceDelta delta)
   {
      boolean result = false;

      Object[] listeners = this.listeners.getListeners();
      for (int i = 0; i < listeners.length; i++ )
      {
         try
         {
            result |= ((IResourceDeltaVisitor) listeners[i]).visit(delta);
         }
         catch (CoreException e)
         {
            // TODO: handle exception
         }
      }
      return result;
   }

}
