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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.StandardClasspathProvider;
import org.eclipse.stardust.common.StringUtils;

/**
 * @author rsauer
 * @version $Revision$
 */
public class CarnotToolClasspathProvider extends StandardClasspathProvider
{
   public static final String ATTR_HOME_LOCATION = "CARNOT_HOME"; //$NON-NLS-1$

   public static final String ATTR_WORK_LOCATION = "CARNOT_WORK"; //$NON-NLS-1$

   public static final String ATTR_EXTRA_LOCATION = "CARNOT_EXTRA_LOCATION"; //$NON-NLS-1$

   public IRuntimeClasspathEntry[] computeUnresolvedClasspath(
         ILaunchConfiguration configuration) throws CoreException
   {
      List entries = new ArrayList(
            Arrays.asList(super.computeUnresolvedClasspath(configuration)));
      
      String homeLocation = configuration.getAttribute(ATTR_HOME_LOCATION, (String) null);
      if ( !StringUtils.isEmpty(homeLocation))
      {
         entries.add(JavaRuntime.newRuntimeContainerClasspathEntry(BpmClasspathUtils.encodeClasspathEntryHint(
               CarnotHomeLocationClasspathContainer.PATH_CARNOT_HOME_LOCATION_CP,
               homeLocation), IRuntimeClasspathEntry.USER_CLASSES));
      }
      else
      {
         entries.add(JavaRuntime.newRuntimeContainerClasspathEntry(
               BpmCoreLibrariesClasspathContainer.PATH_BPM_CORE_LIBS_CP,
               IRuntimeClasspathEntry.USER_CLASSES));
         entries.add(JavaRuntime.newRuntimeContainerClasspathEntry(
               BpmToolLibrariesClasspathContainer.PATH_BPM_TOOL_LIBS_CP,
               IRuntimeClasspathEntry.USER_CLASSES));
      }

      String workLocation = configuration.getAttribute(ATTR_WORK_LOCATION, (String) null);
      if ( !StringUtils.isEmpty(workLocation))
      {
         entries.add(JavaRuntime.newRuntimeContainerClasspathEntry(BpmClasspathUtils.encodeClasspathEntryHint(
               CarnotWorkLocationClasspathContainer.PATH_CARNOT_WORK_LOCATION_CP,
               workLocation), IRuntimeClasspathEntry.USER_CLASSES));
         // add jars from lib/spring
         File homeDir = new File(homeLocation);
         File springJtaLibDir = new File(homeDir, "lib/spring"); //$NON-NLS-1$
         IPath[] springLibs = BpmClasspathUtils.findFilesInFolder(springJtaLibDir);
         IRuntimeClasspathEntry[] springLibsClasspathEntries = BpmClasspathUtils.createRtClasspathEntries(springLibs);
         entries.addAll(Arrays.asList(springLibsClasspathEntries));
         //entries.addAll(Arrays.asList(BpmClasspathUtils.getCarnotWorkRtClasspathEntries(workLocation)));
      }

      String extraLocation = configuration.getAttribute(ATTR_EXTRA_LOCATION, (String) null);
      if ( !StringUtils.isEmpty(extraLocation))
      {
         entries.add(JavaRuntime.newArchiveRuntimeClasspathEntry(new Path(extraLocation)));
      }

      return (IRuntimeClasspathEntry[]) entries.toArray(BpmClasspathUtils.RT_CLASSPATH_ENTRY_ARRAY);
   }
}
