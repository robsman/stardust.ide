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
package org.eclipse.stardust.modeling.data.structured.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.dialogs.FileSystemElement;

public class ResourceFilter extends ViewerFilter
{
   protected String[] fExtensions;

   public ResourceFilter(String[] extensions)
   {
      fExtensions = extensions;
   }

   public boolean isFilterProperty(Object element, Object property)
   {
      return false;
   }
   
   public boolean select(Viewer viewer, Object parent, Object element)
   {
      if (element instanceof FileSystemElement)
      {
         String name = ((FileSystemElement) element).getFileNameExtension();
         if (fExtensions.length == 0)
         {
            // assume that we don't want to filter any files based on
            // extension
            return true;
         }
         for (int i = 0; i < fExtensions.length; i++)
         {
            if (name.equals(fExtensions[i].substring(1)))
            {
               return true;
            }
         }
         return false;
      }
      if (element instanceof IFile)
      {
         String name = ((IFile) element).getName();
         if (fExtensions.length == 0)
         {
            // assume that we don't want to filter any files based on
            // extension
            return true;
         }
         for (int i = 0; i < fExtensions.length; i++)
         {
            if (name.endsWith(fExtensions[i]))
            {
               return true;
            }
         }
         return false;
      }
      else if (element instanceof IContainer)
      {
         // containers always visible
         return true;
      }
      return false;
   }
}
