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
package org.eclipse.stardust.modeling.common.projectnature.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IRuntimeClasspathEntryResolver;
import org.eclipse.jdt.launching.IVMInstall;

import ag.carnot.base.Base64;
import ag.carnot.error.InternalException;

/**
 * @author rsauer
 * @version $Revision$
 */
public class CarnotWorkRuntimeClasspathEntryResolver
      implements IRuntimeClasspathEntryResolver
{
   public CarnotWorkRuntimeClasspathEntryResolver()
   {
   }

   public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(
         IRuntimeClasspathEntry entry, ILaunchConfiguration configuration)
         throws CoreException
   {
      return resolveRuntimeClasspathEntry(entry);
   }

   public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(
         IRuntimeClasspathEntry entry, IJavaProject project) throws CoreException
   {
      return resolveRuntimeClasspathEntry(entry);
   }

   public IVMInstall resolveVMInstall(IClasspathEntry entry) throws CoreException
   {
      return null;
   }

   private IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(
         IRuntimeClasspathEntry entry) throws CoreException
   {
      IPath path = entry.getPath().removeFirstSegments(1);

      IRuntimeClasspathEntry[] result = BpmClasspathUtils.RT_CLASSPATH_ENTRY_ARRAY;

      try
      {
         String decodedWorkFolder = new String(Base64.decode(path.toString().getBytes()));

         result = BpmClasspathUtils.getCarnotWorkRtClasspathEntries(decodedWorkFolder);
      }
      catch (InternalException e)
      {
         // TODO
      }

      return result;
   }
}
