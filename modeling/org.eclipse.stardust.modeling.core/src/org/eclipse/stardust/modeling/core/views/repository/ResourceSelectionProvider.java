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
package org.eclipse.stardust.modeling.core.views.repository;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.*;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ResourceSelectionProvider implements ISelectionProvider, ISelectionChangedListener
{
   private ArrayList listeners = new ArrayList();
   private TreeViewer viewer;

   public ResourceSelectionProvider(TreeViewer viewer)
   {
      this.viewer = viewer;
   }

   public void addSelectionChangedListener(ISelectionChangedListener listener)
   {
      if (listeners == null)
      {
         listeners = new ArrayList();
         viewer.addSelectionChangedListener(this);
      }
      listeners.add(listener);
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      SelectionChangedEvent newEvent = new SelectionChangedEvent(this,
         convertSelection((IStructuredSelection) event.getSelection()));
      for (int i = 0; i < listeners.size(); i++)
      {
         ISelectionChangedListener listener = (ISelectionChangedListener) listeners.get(i);
         listener.selectionChanged(newEvent);
      }
   }

   public ISelection getSelection()
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      return convertSelection(selection);
   }

   public void removeSelectionChangedListener(
      ISelectionChangedListener listener)
   {
      if (listeners != null)
      {
         listeners.remove(listener);
         if (listeners.isEmpty())
         {
            listeners = null;
            viewer.removeSelectionChangedListener(this);
         }
      }
   }

   public void setSelection(ISelection selection)
   {
      // selection cannot be set!
   }

   private ISelection convertSelection(IStructuredSelection selection)
   {
      if (!selection.isEmpty())
      {
         Object[] objects = new Object[selection.size()];
         int c = 0;
         for (Iterator i = selection.iterator(); i.hasNext(); c++)
         {
            objects[c] = i.next();
            if (objects[c] instanceof ResourceInfo)
            {
               ResourceInfo info = (ResourceInfo) objects[c];
               if (info.getFile() != null)
               {
                  IProject project = (IProject) viewer.getInput();
                  IPath path = new Path(info.getFile());
                  IResource resource = project.findMember(path.removeFirstSegments(1));
                  if (resource != null)
                  {
                     objects[c] = resource;
                  }
               }
            }
         }
         return new StructuredSelection(objects);
      }
      return selection;
   }
}
