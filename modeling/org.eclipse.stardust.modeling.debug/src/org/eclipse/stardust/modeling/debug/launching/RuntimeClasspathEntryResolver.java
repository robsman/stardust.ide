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

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IRuntimeClasspathEntryResolver;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.osgi.framework.Bundle;

public class RuntimeClasspathEntryResolver implements IRuntimeClasspathEntryResolver
{
   private static final String[][] carnot_libs = {
      {"org.eclipse.equinox.common", "/"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.debug.core", "/"}, //$NON-NLS-1$ //$NON-NLS-2$
      
      {"org.eclipse.stardust.ide.thirdparty.runtime.xml", "lib/commons-jxpath-1.3.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.thirdparty.runtime.xml", "lib/xercesImpl-2.9.0.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
//      {"org.eclipse.stardust.ide.thirdparty.runtime.xml", "lib/xalan-2.6.0.jar"},
      
      {"org.eclipse.stardust.ide.thirdparty.jaxws", "lib/webservices-api-1.5.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.thirdparty.jaxws", "lib/webservices-extra-api-1.5.jar"}, //$NON-NLS-1$ //$NON-NLS-2$

      {"org.eclipse.stardust.ide.thirdparty.jaxws.metro", "lib/webservices-rt-1.5.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.thirdparty.jaxws.metro", "lib/webservices-extra-1.5.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.thirdparty.jaxws.metro", "lib/webservices-tools-1.5.jar"}, //$NON-NLS-1$ //$NON-NLS-2$

      {"org.eclipse.stardust.ide.thirdparty.runtime.j2ee", "lib/ejb-2.1.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.thirdparty.runtime.j2ee", "lib/jms-1.1.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      
      {"org.eclipse.stardust.ide.engine.core", "lib/carnot-engine.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.engine.core", "lib/carnot-emf-xsd-integration.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.engine.core", "lib/ipp-engine-ws-metro.jar"}, //$NON-NLS-1$ //$NON-NLS-2$
      {"org.eclipse.stardust.ide.engine.core", "etc"}, //$NON-NLS-1$ //$NON-NLS-2$

      {"org.eclipse.stardust.modeling.debug", ".eclipse/bin", "/"}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   };

   public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(
         IRuntimeClasspathEntry entry, ILaunchConfiguration configuration)
         throws CoreException
   {
      return resolveEntry(entry);
   }

   public IRuntimeClasspathEntry[] resolveRuntimeClasspathEntry(
         IRuntimeClasspathEntry entry, IJavaProject project) throws CoreException
   {
      return resolveEntry(entry);
   }

   public IVMInstall resolveVMInstall(IClasspathEntry entry) throws CoreException
   {
      return null;
   }

   private IRuntimeClasspathEntry[] resolveEntry(IRuntimeClasspathEntry entry)
         throws CoreException
   {
      LinkedHashSet result = new LinkedHashSet();
      for (int i = 0; i < carnot_libs.length; i++)
      {
         addCarnotDependencies(result, carnot_libs[i]);
      }
      return (IRuntimeClasspathEntry[]) result.toArray(
            new IRuntimeClasspathEntry[result.size()]);
   }

   private void addCarnotDependencies(Set files, String[] elements) throws CoreException
   {
      Bundle bundle = Platform.getBundle(elements[0]);
      if (null == bundle)
      {
         throw new CoreException(new Status(IStatus.ERROR, Constants.ID_CWM_DEBUG_MODEL,
               0, MessageFormat.format(Debug_Messages.EXP_BundleHasNotBeenLoadedYet,
               new String[] {elements[0]}), null));
      }
      
      IPath systemPath = null;
      
      for (int idx = 1; idx < elements.length; ++idx)
      {
         URL entryUrl = bundle.getEntry(elements[idx]);
         if (null != entryUrl)
         {
            try
            {
               systemPath = Path.fromOSString(Platform.asLocalURL(entryUrl).getPath());
            }
            catch (IOException e)
            {
               throw new CoreException(new Status(IStatus.ERROR, Constants.ID_CWM_DEBUG_MODEL,
                     0, Debug_Messages.EXP_ErrorWhileExtendingBootpath, e));
            }
            break;
         }
      }
      
      if (systemPath == null)
      {
         Iterator elementsIterator = Arrays.asList(elements).iterator();
         elementsIterator.next(); // skip the bundle name
         throw new CoreException(new Status(IStatus.ERROR, Constants.ID_CWM_DEBUG_MODEL,
               0, MessageFormat.format(Debug_Messages.EXP_BundleDoesNotContain,
               new String[] {elements[0], StringUtils.join(elementsIterator, ", ")}), //$NON-NLS-1$
               null));
      }
      
      files.add(JavaRuntime.newArchiveRuntimeClasspathEntry(systemPath));
   }
}
