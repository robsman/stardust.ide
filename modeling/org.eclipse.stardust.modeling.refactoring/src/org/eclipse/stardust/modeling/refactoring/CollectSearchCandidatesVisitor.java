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
package org.eclipse.stardust.modeling.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;

/**
 * @author sborn
 * @version $Revision$
 */
public class CollectSearchCandidatesVisitor implements IResourceVisitor
{
   public static final String[] CARNOT_EXTENSIONS = {"xpdl", "cwm"}; //$NON-NLS-1$ //$NON-NLS-2$

   List files = new ArrayList();
   IProject project = null;
   
   public CollectSearchCandidatesVisitor(IProject project)
   {
      this.project = project;
   }

   public boolean visit(IResource resource) throws CoreException
   {
      boolean result = false;
      
      if (resource instanceof IWorkspaceRoot || resource instanceof IFolder)
      {
         result = true;
      }
      else if (resource instanceof IProject && resource.isAccessible())
      {
         result = true;
      }
      else if (resource instanceof IFile)
      {
         if(project.equals(((IResource) resource).getProject()))
         {
            IFile file = (IFile) resource;
            String extension = file.getFileExtension();
            for (int i = 0; i < CARNOT_EXTENSIONS.length; i++)
            {
               if (CARNOT_EXTENSIONS[i].equalsIgnoreCase(extension))
               {
                  result = true;
                  files.add(file);
                  break;
               }
            }
         }
      }      
      return result;
   }

   public List getFiles()
   {
      return Collections.unmodifiableList(files);
   }
}