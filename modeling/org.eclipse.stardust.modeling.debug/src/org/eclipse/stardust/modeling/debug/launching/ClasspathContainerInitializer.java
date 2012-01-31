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
package org.eclipse.stardust.modeling.debug.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.stardust.modeling.debug.Debug_Messages;

public class ClasspathContainerInitializer
      extends org.eclipse.jdt.core.ClasspathContainerInitializer
{
   public void initialize(IPath containerPath, IJavaProject project) throws CoreException
   {
      final IPath finalPath = containerPath;
      JavaCore.setClasspathContainer(
            containerPath,//
            new IJavaProject[] {project},
            new IClasspathContainer[] {new IClasspathContainer()
            {
               public IClasspathEntry[] getClasspathEntries()
               {
                  return new IClasspathEntry[0];
               }

               public String getDescription()
               {
                  return Debug_Messages.DSCR_InfinityDebugLibrary;
               }

               public int getKind()
               {
                  return IClasspathContainer.K_APPLICATION;
               }

               public IPath getPath()
               {
                  return finalPath;
               }
            }},//
            null);
   }
}
