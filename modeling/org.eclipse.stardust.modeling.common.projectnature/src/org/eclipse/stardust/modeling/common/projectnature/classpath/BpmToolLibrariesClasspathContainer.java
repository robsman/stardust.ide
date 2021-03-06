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

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;
import org.eclipse.stardust.modeling.common.projectnature.Modeling_Core_Messages;
import org.osgi.framework.Bundle;

/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmToolLibrariesClasspathContainer implements IClasspathContainer
{
   public static final Path PATH_BPM_TOOL_LIBS_CP = new Path(
         ModelingCoreActivator.ID_BPM_TOOL_LIBS_CP);

   public static final IClasspathEntry[] NO_CLASSPATH_ENTRIES = new IClasspathEntry[0];

   public IClasspathEntry[] getClasspathEntries()
   {
      List entries = new ArrayList();

      addLibraryEntry(entries, "org.eclipse.stardust.ide.thirdparty.jgoodies.forms", //$NON-NLS-1$
            "lib/forms.jar"); //$NON-NLS-1$

      return (IClasspathEntry[]) entries.toArray(NO_CLASSPATH_ENTRIES);
   }

   public int getKind()
   {
      return IClasspathContainer.K_APPLICATION;
   }

   public String getDescription()
   {
      return "Infinity BPM Tool Libraries"; //$NON-NLS-1$
   }

   public IPath getPath()
   {
      return PATH_BPM_TOOL_LIBS_CP;
   }

   private void addLibraryEntry(List entries, String bundleId, String element)
   {
      try
      {
         IPath libraryLocation = getLibraryLocation(bundleId, element);
         if (null != libraryLocation)
         {
            entries.add(JavaCore.newLibraryEntry(libraryLocation, null, null));
         }
      }
      catch (InternalException e)
      {
         // TODO ignoring element
      }
   }

   private IPath getLibraryLocation(String bundleId, String element)
   {
      return getLibraryLocation(bundleId, new String[] {element});
   }

   public static IPath getLibraryLocation(String bundleId, String[] elements)
   {
      Bundle bundle = Platform.getBundle(bundleId);
      if (null == bundle)
      {
         throw new InternalException((MessageFormat.format(
               Modeling_Core_Messages.MSG_BundleNotLoaded, new String[] {bundleId})));
      }

      String systemPath = ""; //$NON-NLS-1$

      boolean found = false;
      for (int idx = 0; idx < elements.length; ++idx)
      {
         URL entryUrl = bundle.getEntry(elements[idx]);
         if (null != entryUrl)
         {
            try
            {
               systemPath = Platform.asLocalURL(entryUrl).getPath();
               found = true;
               break;
            }
            catch (IOException e)
            {
               // TODO
               System.out.println(Modeling_Core_Messages.MSG_FailedResolvingBundle + e.getMessage());
            }
         }
      }

      if ( !found)
      {
         throw new InternalException(
               (MessageFormat.format(Modeling_Core_Messages.MSG_BundleNotContain,
                     new String[] {
                           bundleId,
                           StringUtils.join(Arrays.asList(elements).iterator(), ", ")}))); //$NON-NLS-1$
      }
      return new Path(systemPath);
   }
}
