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
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.StandardClasspathProvider;
import org.eclipse.stardust.modeling.debug.Constants;


public class RuntimeClasspathProvider extends StandardClasspathProvider
{
   public IRuntimeClasspathEntry[] computeUnresolvedClasspath(
         ILaunchConfiguration configuration) throws CoreException
   {
      IRuntimeClasspathEntry[] original = super.computeUnresolvedClasspath(configuration);
      IRuntimeClasspathEntry[] result = new IRuntimeClasspathEntry[original.length + 1];

      IRuntimeClasspathEntry entry = JavaRuntime.newRuntimeContainerClasspathEntry(
            Constants.PATH_DEBUG_CORE_LIBS_CP,
            IRuntimeClasspathEntry.USER_CLASSES);

      boolean added = false;
      int insertLocation = 0;
      for (int i = 0; i < original.length; i++)
      {
         result[insertLocation++] = original[i];
         if (!added && original[i].getPath().toString().startsWith(
               org.eclipse.jdt.launching.JavaRuntime.JRE_CONTAINER))
         {
            result[insertLocation++] = entry;
         }
      }
      return result;
   }

   public IRuntimeClasspathEntry[] resolveClasspath(IRuntimeClasspathEntry[] entries, ILaunchConfiguration configuration) throws CoreException
   {
      IRuntimeClasspathEntry[] resolved = super.resolveClasspath(entries, configuration);
      for (int i = 0; i < resolved.length; i++)
      {
         if (resolved[i].getClasspathProperty() == IRuntimeClasspathEntry.USER_CLASSES)
         {
//            resolved[i].setClasspathProperty(IRuntimeClasspathEntry.BOOTSTRAP_CLASSES);
         }
      }
      return resolved;
   }
}
