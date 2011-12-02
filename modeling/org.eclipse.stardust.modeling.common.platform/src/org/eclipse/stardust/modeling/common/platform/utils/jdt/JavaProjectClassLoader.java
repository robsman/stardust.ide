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
package org.eclipse.stardust.modeling.common.platform.utils.jdt;

import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author rsauer
 * @version $Revision$
 */
public class JavaProjectClassLoader extends ClassLoader
{
   private URLClassLoader delegate;

   public JavaProjectClassLoader(ClassLoader delegate, IProject project)
   {
      super(delegate);

      if (null != project)
      {
         IJavaProject javaProject = JavaCore.create(project);
         if (javaProject.exists())
         {
            try
            {
               IPath wsRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();

               IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
               URL[] classPath = new URL[entries.length + 1];
               classPath[0] = wsRoot.append(javaProject.getOutputLocation())
                     .toFile()
                     .toURL();
               for (int i = 0; i < entries.length; i++ )
               {
                  IClasspathEntry entry = entries[i];
                  classPath[1 + i] = ((IClasspathEntry.CPE_SOURCE == entry.getEntryKind())
                        ? wsRoot.append(entry.getPath())
                        : entry.getPath()).toFile().toURL();
               }
               this.delegate = new URLClassLoader(classPath);
            }
            catch (Exception e)
            {
            }
         }
      }
   }

   protected URL findResource(String name)
   {
      URL result = null;

      if (null != delegate)
      {
         result = delegate.findResource(name);
      }

      return result;
   }

   protected Class<?> findClass(String name) throws ClassNotFoundException
   {
      Class<?> result = null;

      if (null != delegate)
      {
         try
         {
            result = delegate.loadClass(name);
         }
         catch (ClassNotFoundException cnfe)
         {
            throw new ClassNotFoundException(cnfe.getMessage());
         }
      }

      return result;
   }

}
