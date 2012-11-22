package org.eclipse.stardust.modeling.common.projectnature.classpath;

import java.net.URI;
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
   private static final String TOMCAT_RUNTIME_TARGET = "org.eclipse.jst.server.core.container/org.eclipse.jst.server.tomcat.runtimeTarget";

   private static final String SRC_FOLDER = "src";

   private static final String MODELING_DEPLOY_NL_PLUGIN = "org.eclipse.stardust.modeling.deploy";

   @Override
   public IRuntimeClasspathEntry[] computeUnresolvedClasspath(
         ILaunchConfiguration configuration) throws CoreException
   {

      List<IRuntimeClasspathEntry> entries = new ArrayList<IRuntimeClasspathEntry>(
            Arrays.asList(super.computeUnresolvedClasspath(configuration)));
      removeTomcatLibraries(configuration, entries);

      Bundle deployBundle = Platform.getBundle(MODELING_DEPLOY_NL_PLUGIN);
      Bundle[] fragments = Platform.getFragments(deployBundle);
      for (int i = 0; i < fragments.length; i++)
      {
         boolean hasSrcFolder = false;
         if (fragments[i].getEntry(SRC_FOLDER) != null)
         {
            hasSrcFolder = true;
         }
         StringBuilder fragmentLocation = new StringBuilder();
         fragmentLocation.append(fragments[i].getLocation());
         if (hasSrcFolder)
         {
            fragmentLocation.append(SRC_FOLDER);
         }
         URI location = URI.create(fragmentLocation.toString());
         String schemeSpecificPart = location.getSchemeSpecificPart();
         location = URI.create(schemeSpecificPart);
         Path path = new Path(URIUtil.toFile(location).getAbsolutePath());
         IRuntimeClasspathEntry entry = JavaRuntime.newArchiveRuntimeClasspathEntry(path);
         entries.add(entry);
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
