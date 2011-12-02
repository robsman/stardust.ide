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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;

import ag.carnot.base.Base64;
import ag.carnot.base.StringUtils;
import ag.carnot.error.InternalException;

/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmClasspathUtils
{
   public static final IPath[] IPATH_ARRAY = new IPath[0];

   public static final IClasspathEntry[] CLASSPATH_ENTRY_ARRAY = new IClasspathEntry[0];

   public static final IRuntimeClasspathEntry[] RT_CLASSPATH_ENTRY_ARRAY = new IRuntimeClasspathEntry[0];
   
   public static IJavaProject getJavaProject(IProject project)
   {
      return JavaCore.create(project);
   }

   public static void addBpmCoreLibsContainer(IProject project)
   {
      IJavaProject javaProject = getJavaProject(project);
      if (null != javaProject)
      {
         try
         {
            boolean containsBpmCoreLibsCpEntry = false;

            List cpEntries = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
            for (Iterator i = cpEntries.iterator(); i.hasNext();)
            {
               IClasspathEntry entry = (IClasspathEntry) i.next();
               if (entry.getPath().equals(
                     BpmCoreLibrariesClasspathContainer.PATH_BPM_CORE_LIBS_CP))
               {
                  containsBpmCoreLibsCpEntry = true;
               }
            }

            if ( !containsBpmCoreLibsCpEntry)
            {
               int idxJreContainer = -1;

               for (int i = 0; i < cpEntries.size(); ++i)
               {
                  IClasspathEntry entry = (IClasspathEntry) cpEntries.get(i);
                  if ((IClasspathEntry.CPE_CONTAINER == entry.getEntryKind())
                        && entry.getPath().toString().startsWith(
                              org.eclipse.jdt.launching.JavaRuntime.JRE_CONTAINER))
                  {
                     idxJreContainer = i;
                     break;
                  }
               }

               IClasspathEntry bpmCoreLibsEntry = JavaCore.newContainerEntry(
                     BpmCoreLibrariesClasspathContainer.PATH_BPM_CORE_LIBS_CP, false);

               if ( -1 != idxJreContainer)
               {
                  cpEntries.add(1 + idxJreContainer, bpmCoreLibsEntry);
               }
               else
               {
                  cpEntries.add(bpmCoreLibsEntry);
               }

               javaProject.setRawClasspath(
                     (IClasspathEntry[]) cpEntries.toArray(CLASSPATH_ENTRY_ARRAY), null);
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
   
   public static boolean hasBpmSpringLibsContainer(IProject project)
   {
      boolean containsBpmSpringLibsCpEntry = false;
      
      IJavaProject javaProject = JavaCore.create(project);
      if (null != javaProject)
      {
         try
         {
            List cpEntries = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
            for (Iterator i = cpEntries.iterator(); i.hasNext();)
            {
               IClasspathEntry entry = (IClasspathEntry) i.next();
               if (entry.getPath().equals(
                     BpmSpringLibrariesClasspathContainer.PATH_BPM_SPRING_LIBS_CP))
               {
                  containsBpmSpringLibsCpEntry = true;
               }
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      return containsBpmSpringLibsCpEntry;
   }

   public static void addBpmSpringLibsContainer(IProject project)
   {
      IJavaProject javaProject = JavaCore.create(project);
      if (null != javaProject)
      {
         try
         {
            boolean containsBpmSpringLibsCpEntry = false;

            List cpEntries = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
            for (Iterator i = cpEntries.iterator(); i.hasNext();)
            {
               IClasspathEntry entry = (IClasspathEntry) i.next();
               if (entry.getPath().equals(
                     BpmSpringLibrariesClasspathContainer.PATH_BPM_SPRING_LIBS_CP))
               {
                  containsBpmSpringLibsCpEntry = true;
               }
            }

            if ( !containsBpmSpringLibsCpEntry)
            {
               int idxBpmCoreContainer = -1;

               for (int i = 0; i < cpEntries.size(); ++i)
               {
                  IClasspathEntry entry = (IClasspathEntry) cpEntries.get(i);
                  if ((IClasspathEntry.CPE_CONTAINER == entry.getEntryKind())
                        && entry.getPath().toString().startsWith(
                              ModelingCoreActivator.ID_BPM_CORE_LIBS_CP))
                  {
                     idxBpmCoreContainer = i;
                     break;
                  }
               }

               IClasspathEntry bpmSpringLibsEntry = JavaCore.newContainerEntry(
                     BpmSpringLibrariesClasspathContainer.PATH_BPM_SPRING_LIBS_CP, false);

               if ( -1 != idxBpmCoreContainer)
               {
                  cpEntries.add(1 + idxBpmCoreContainer, bpmSpringLibsEntry);
               }
               else
               {
                  cpEntries.add(bpmSpringLibsEntry);
               }

               javaProject.setRawClasspath(
                     (IClasspathEntry[]) cpEntries.toArray(CLASSPATH_ENTRY_ARRAY), null);
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   public static void removeBpmCoreLibsContainer(IProject project)
   {
      IJavaProject javaProject = JavaCore.create(project);
      if (null != javaProject)
      {
         try
         {
            boolean containedBpmCoreLibsCpEntry = false;

            List cpEntries = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
            for (Iterator i = cpEntries.iterator(); i.hasNext();)
            {
               IClasspathEntry entry = (IClasspathEntry) i.next();
               if (entry.getPath().equals(
                     BpmCoreLibrariesClasspathContainer.PATH_BPM_CORE_LIBS_CP))
               {
                  containedBpmCoreLibsCpEntry = true;
                  i.remove();
               }
            }

            if (containedBpmCoreLibsCpEntry)
            {
               javaProject.setRawClasspath(
                     (IClasspathEntry[]) cpEntries.toArray(CLASSPATH_ENTRY_ARRAY), null);
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   public static void removeBpmSpringLibsContainer(IProject project)
   {
      IJavaProject javaProject = JavaCore.create(project);
      if (null != javaProject)
      {
         try
         {
            boolean containedBpmSpringLibsCpEntry = false;

            List cpEntries = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
            for (Iterator i = cpEntries.iterator(); i.hasNext();)
            {
               IClasspathEntry entry = (IClasspathEntry) i.next();
               if (entry.getPath().equals(
                     BpmSpringLibrariesClasspathContainer.PATH_BPM_SPRING_LIBS_CP))
               {
                  containedBpmSpringLibsCpEntry = true;
                  i.remove();
               }
            }

            if (containedBpmSpringLibsCpEntry)
            {
               javaProject.setRawClasspath(
                     (IClasspathEntry[]) cpEntries.toArray(CLASSPATH_ENTRY_ARRAY), null);
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   public static IClasspathEntry[] getCarnotHomeClasspathEntries(String baseFolder)
   {
      return createClasspathEntries(findCarnotHomeClasspathEntries(baseFolder, true));
   }

   public static IRuntimeClasspathEntry[] getCarnotHomeRtClasspathEntries(
         String baseFolder)
   {
      return createRtClasspathEntries(findCarnotHomeClasspathEntries(baseFolder, true));
   }

   public static IClasspathEntry[] getCarnotWorkClasspathEntries(String baseFolder)
   {
      return createClasspathEntries(findCarnotWorkClasspathEntries(baseFolder, false));
   }

   public static IRuntimeClasspathEntry[] getCarnotWorkRtClasspathEntries(
         String baseFolder)
   {
      return createRtClasspathEntries(findCarnotWorkClasspathEntries(baseFolder, true));
   }

   public static IPath[] findCarnotHomeClasspathEntries(String baseFolder,
         boolean includeExt)
   {
      List entries = new ArrayList();

      File homeDir = new File(baseFolder);

      if (homeDir.exists() && homeDir.canRead())
      {
         File libDir = new File(homeDir, "lib"); //$NON-NLS-1$
         entries.addAll(Arrays.asList(findFilesInFolder(libDir)));
         
         if (includeExt)
         {
            entries.addAll(Arrays.asList(findFilesInFolder(new File(libDir, "ext")))); //$NON-NLS-1$
         }
      }

      return (IPath[]) entries.toArray(IPATH_ARRAY);
   }

   public static IPath[] findCarnotWorkClasspathEntries(String baseFolder,
         boolean includeEtc)
   {
      List entries = new ArrayList();

      File workDir = new File(baseFolder);

      if (workDir.exists() && workDir.canRead())
      {
         File libDir = new File(workDir, "lib"); //$NON-NLS-1$
         entries.addAll(Arrays.asList(findFilesInFolder(libDir)));
      }

      if (includeEtc)
      {
         entries.add(new Path(new File(workDir, "etc").getAbsolutePath())); //$NON-NLS-1$
      }

      return (IPath[]) entries.toArray(IPATH_ARRAY);
   }

   public static String retrieveEncodedClasspathEntryHint(IPath entryPath)
   {
      String result = null;

      if (1 < entryPath.segmentCount())
      {
         IPath path = entryPath.removeFirstSegments(1);
         try
         {
            result = new String(Base64.decode(path.toString().getBytes()));
         }
         catch (InternalException e)
         {
            // TODO
         }
      }

      return result;
   }

   public static IPath encodeClasspathEntryHint(IPath firstSegment, String hint)
   {
      String encodedHint = null;
      try
      {
         encodedHint = new String(Base64.encode(hint.getBytes()));
      }
      catch (InternalException e)
      {
         // TODO
      }

      return !StringUtils.isEmpty(encodedHint)
            ? firstSegment.append(encodedHint)
            : firstSegment;
   }

   protected static IPath[] findFilesInFolder(File folder)
   {
      List result = new ArrayList();

      if (folder.exists() && folder.canRead() && folder.isDirectory())
      {
         File[] files = folder.listFiles();
         for (int i = 0; i < files.length; i++ )
         {
            File file = files[i];
            if (file.exists() && file.canRead() && file.isFile())
            {
               result.add(new Path(file.getAbsolutePath()));
            }
         }
      }

      return (IPath[]) result.toArray(IPATH_ARRAY);
   }

   private static IClasspathEntry[] createClasspathEntries(IPath[] paths)
   {
      IClasspathEntry[] result = new IClasspathEntry[paths.length];

      for (int i = 0; i < paths.length; i++ )
      {
         result[i] = JavaCore.newLibraryEntry(paths[i], null, null);
      }
      return result;
   }

   protected static IRuntimeClasspathEntry[] createRtClasspathEntries(IPath[] paths)
   {
      IRuntimeClasspathEntry[] result = new IRuntimeClasspathEntry[paths.length];

      for (int i = 0; i < paths.length; i++ )
      {
         result[i] = JavaRuntime.newArchiveRuntimeClasspathEntry(paths[i]);
      }
      return result;
   }
}
