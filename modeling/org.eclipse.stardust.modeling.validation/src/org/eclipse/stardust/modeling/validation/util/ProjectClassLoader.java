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
package org.eclipse.stardust.modeling.validation.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class ProjectClassLoader extends ClassLoader
{
   private IProject project;
   private ClassLoader delegate;
   private String resource;

   public ProjectClassLoader(ClassLoader delegate, IProject project, String resource)
   {
      this.delegate = delegate;
      this.project = project;
      this.resource = resource;
   }

   protected URL findResource(String name)
   {
      URL result = delegate.getResource(name);
      if (result == null && name.equals(resource))
      {
    	 List classpath = resolveClasspath(project, new HashSet());
         URLClassLoader cl = new URLClassLoader((URL[]) classpath.toArray(new URL[0]));
         result = cl.findResource(name);
      }
      return result;
   }

   private List resolveClasspath(IProject project, Set compareStrings)
   {
      List classpath = new ArrayList();
      IJavaProject javaProject = JavaCore.create(project);
      if (javaProject.exists())
      {
         IPath projectPath = project.getFullPath();
         IPath projectLocation = project.getLocation().removeLastSegments(projectPath.segmentCount());
         try
         {
			IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
			// we are guaranteed that no variables or containers are present in the classpath entries
			for (int i = 0; i < entries.length; i++)
			{
			   if (entries[i].getEntryKind() == IClasspathEntry.CPE_PROJECT)
			   {
				  // add recursively the entries from the referenced projects
				  classpath.addAll(resolveClasspath(findProject(entries[i].getPath()), compareStrings));
			   }
			   else
			   {
			      IPath entryPath = entries[i].getPath();
			      if (projectPath.isPrefixOf(entryPath))
			      {
			    	 // if it's a project relative location, prepend it with the project location
			    	 entryPath = projectLocation.append(entryPath);
			      }
			      addClasspathEntry(classpath, compareStrings, entryPath);
			   }
			}
		 }
         catch (JavaModelException e)
		 {
//			e.printStackTrace();
		 }
      }
	  return classpath;
   }

   private void addClasspathEntry(List classpath, Set compareStrings, IPath entryPath)
   {
	  try
	  {
		 URL newEntry = entryPath.toFile().toURL();
		 String newEntryString = newEntry.toString();
		 if (!compareStrings.contains(newEntryString))
		 {
			classpath.add(newEntry);
			compareStrings.add(newEntryString);
		 }
	  }
	  catch (MalformedURLException e)
	  {
//		 e.printStackTrace();
	  }
   }

   private IProject findProject(IPath path)
   {
	  return ResourcesPlugin.getWorkspace().getRoot().getProject(path.lastSegment());
   }
}
