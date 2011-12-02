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
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public abstract class FileResourceChangeListener implements IResourceChangeListener
{
   private TargetFileFilter filter = new TargetFileFilter();

   public abstract IFile getFile();

   public abstract boolean fileChanged(IResourceDelta delta);

   public void resourceChanged(IResourceChangeEvent event)
   {
      IResourceDelta delta = event.getDelta();
      try
      {
         if (null != delta)
         {
            delta.accept(filter);
         }
      }
      catch (CoreException exception)
      {
         // TODO
         exception.printStackTrace();
      }
   }

   private class TargetFileFilter implements IResourceDeltaVisitor
   {
      public boolean visit(IResourceDelta delta)
      {
         boolean result = true;

         if ((null != delta) && delta.getResource().equals(getFile()))
         {
            result = FileResourceChangeListener.this.fileChanged(delta);
         }

         return result;
      }
   }
}