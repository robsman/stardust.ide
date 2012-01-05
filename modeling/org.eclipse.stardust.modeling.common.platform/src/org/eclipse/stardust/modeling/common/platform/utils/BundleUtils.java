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
package org.eclipse.stardust.modeling.common.platform.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.stardust.common.StringUtils;
import org.osgi.framework.Bundle;

public class BundleUtils
{
   public static IPath getBundleLocation(String bundleId)
   {
      Bundle bundle = Platform.getBundle(bundleId);

      return (null != bundle) ? getBundleLocation(bundle) : null;
   }

   public static IPath getBundleLocation(Bundle bundle)
   {
      IPath result = null;

      if (bundle != null)
      {
         URL local = null;
         try
         {
            local = /*Platform.asLocalURL*/FileLocator.toFileURL(bundle.getEntry("/")); //$NON-NLS-1$
         }
         catch (IOException e)
         {
            result = null;
         }
         String fullPath = new File(local.getPath()).getAbsolutePath();

         result = Path.fromOSString(fullPath);
      }

      return result;
   }

   public static String resolveLibrary(String  bundleId, String baseName)
   {
      IPath resolvedLib = resolveLibraryLocation(bundleId, baseName);
      return (null != resolvedLib) ? resolvedLib.lastSegment() : null;
   }
   
   public static IPath resolveLibraryLocation(String  bundleId, String baseName)
   {
      IPath resolvedFile = null;

      IPath root = getBundleLocation(bundleId);
      if (null != root)
      {
         if (baseName.endsWith(".jar")) //$NON-NLS-1$
         {
            baseName = baseName.substring(0, baseName.length() - ".jar".length()); //$NON-NLS-1$
         }
         
         IPath libRoot = root.append("lib").addTrailingSeparator(); //$NON-NLS-1$
         resolvedFile = resolveFile(libRoot, baseName, ".jar"); //$NON-NLS-1$
         if(resolvedFile == null)
         {
            resolvedFile = resolveFile(root.addTrailingSeparator(), baseName, ".jar"); //$NON-NLS-1$
         }
      }
      return resolvedFile;
   }

   public static IPath resolveFile(IPath root, String baseName, String extension)
   {
      IPath result = null;

      File rootFolder = root.toFile();
      if (rootFolder.isDirectory())
      {
         String[] files = rootFolder.list();
         for (int i = 0; i < files.length; i++ )
         {
            if (files[i].startsWith(baseName) && !StringUtils.isEmpty(extension)
                  && files[i].endsWith(extension) && !files[i].endsWith("-src" + extension)) //$NON-NLS-1$
            {
               String suffix = files[i].substring(baseName.length());

               // assert suffix is likely to be a version identifier
               if (extension.equals(suffix)
                     || ((suffix.startsWith("-") || suffix.startsWith("_")) //$NON-NLS-1$ //$NON-NLS-2$
                           && Character.isDigit(suffix.charAt(1))))
               {
                  result = Path.fromOSString(new File(rootFolder, files[i]).getAbsolutePath());
                  break;
               }
            }
         }
      }

      return result;
   }

}
