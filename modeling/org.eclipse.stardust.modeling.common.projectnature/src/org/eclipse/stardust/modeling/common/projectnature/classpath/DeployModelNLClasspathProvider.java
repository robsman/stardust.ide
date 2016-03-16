package org.eclipse.stardust.modeling.common.projectnature.classpath;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.*;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry2;
import org.eclipse.jdt.launching.JavaRuntime;
import org.osgi.framework.Bundle;

/**
 * The ModelDeploymentTool is started within eclipse as an java application containing its
 * own classpath. To provide internationalization of message property bundles containing
 * in fragments of plugin org.eclipse.stardust.modeling.deploy.nl the fragments and if
 * exist the src folders of these fragments have to be added to classpath.
 * 
 */
public class DeployModelNLClasspathProvider extends CarnotToolClasspathProvider
{
   private static final String TOMCAT_RUNTIME_TARGET = "org.eclipse.jst.server.core.container/org.eclipse.jst.server.tomcat.runtimeTarget"; //$NON-NLS-1$

   private static final String SRC_FOLDER = "src"; //$NON-NLS-1$

   private static final String MODELING_DEPLOY_NL_PLUGIN = "org.eclipse.stardust.modeling.deploy"; //$NON-NLS-1$

   @Override
   public IRuntimeClasspathEntry[] computeUnresolvedClasspath(
         ILaunchConfiguration configuration) throws CoreException
   {

      List<IRuntimeClasspathEntry> entries = new ArrayList<IRuntimeClasspathEntry>(
            Arrays.asList(super.computeUnresolvedClasspath(configuration)));
      removeTomcatLibraries(configuration, entries);

      Bundle deployBundle = Platform.getBundle(MODELING_DEPLOY_NL_PLUGIN);
      Bundle[] fragments = Platform.getFragments(deployBundle);
      if (fragments != null)
      {
         for (int i = 0; i < fragments.length; i++)
         {
            Bundle fragment = fragments[i];
            URI location = null;
            try
            {
               URL fragmentURL = FileLocator.toFileURL(fragment.getEntry("/")); //$NON-NLS-1$
               location = URIUtil.toURI(fragmentURL);
               if (fragment.getEntry(SRC_FOLDER) != null)
               {
                  location = location.resolve(SRC_FOLDER);
               }
               Path path = new Path(URIUtil.toFile(location).getAbsolutePath());
               IRuntimeClasspathEntry entry = JavaRuntime.newArchiveRuntimeClasspathEntry(path);
               entries.add(entry);
            }
            catch (URISyntaxException e)
            {
               e.printStackTrace();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
         }
      }
      return (IRuntimeClasspathEntry[]) entries.toArray(new IRuntimeClasspathEntry[0]);
   }

   /**
    * The ModelDeploymentTool is started with its own classpath. Currently there are a lot
    * of classpath entries. If there is a long installation path of ipp model deployment
    * within RAD environment results in "file name too long" error on windows operating
    * system.
    */
   private void removeTomcatLibraries(ILaunchConfiguration configuration,
         List<IRuntimeClasspathEntry> entries) throws CoreException
   {
      IRuntimeClasspathEntry defaultProjectEntry = null;
      IRuntimeClasspathEntry[] newRuntimeClasspathEntries = new IRuntimeClasspathEntry[0];
      for (IRuntimeClasspathEntry runtimeClasspathEntry : entries)
      {
         if (runtimeClasspathEntry instanceof IRuntimeClasspathEntry2)
         {
            defaultProjectEntry = runtimeClasspathEntry;
            IRuntimeClasspathEntry[] runtimeClasspathEntries = ((IRuntimeClasspathEntry2) runtimeClasspathEntry)
                  .getRuntimeClasspathEntries(configuration);
            if (runtimeClasspathEntries != null)
            {
               newRuntimeClasspathEntries = new IRuntimeClasspathEntry[runtimeClasspathEntries.length];
               for (int i = 0, j = 0; i < runtimeClasspathEntries.length; i++)
               {
                  IRuntimeClasspathEntry classpathEntry = runtimeClasspathEntries[i];
                  IPath rawPath = classpathEntry.getPath();
                  String path = rawPath.toString();
                  if (!path.startsWith(TOMCAT_RUNTIME_TARGET))
                  {
                     newRuntimeClasspathEntries[j] = runtimeClasspathEntries[i];
                     j++;
                  }
               }
            }
         }
      }
      entries.remove(defaultProjectEntry);
      for (int i = 0; i < newRuntimeClasspathEntries.length; i++)
      {
         IRuntimeClasspathEntry runtimeClasspathEntry = newRuntimeClasspathEntries[i];
         if (runtimeClasspathEntry != null)
         {
            entries.add(runtimeClasspathEntry);
         }
      }
   }
}
