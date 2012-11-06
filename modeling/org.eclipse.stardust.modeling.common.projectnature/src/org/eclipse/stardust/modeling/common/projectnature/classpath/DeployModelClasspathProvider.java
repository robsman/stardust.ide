package org.eclipse.stardust.modeling.common.projectnature.classpath;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.osgi.framework.Bundle;

/**
 * The ModelDeploymentTool is started within eclipse as an java application containing its
 * own classpath. To provide internationalization of message property bundles containing
 * in fragments of plugin org.eclipse.stardust.modeling.deploy.nl the src folders of
 * these fragments have to be added to classpath.
 * 
 */
public class DeployModelClasspathProvider extends CarnotToolClasspathProvider
{
   private static final String SRC_FOLDER = "src";

   private static final String MODELING_DEPLOY_NL_PLUGIN = "org.eclipse.stardust.modeling.deploy";

   @Override
   public IRuntimeClasspathEntry[] computeUnresolvedClasspath(
         ILaunchConfiguration configuration) throws CoreException
   {
      Bundle deployBundle = Platform.getBundle(MODELING_DEPLOY_NL_PLUGIN);
      List<IRuntimeClasspathEntry> entries = new ArrayList<IRuntimeClasspathEntry>(
            Arrays.asList(super.computeUnresolvedClasspath(configuration)));
      Bundle[] fragments = Platform.getFragments(deployBundle);
      for (int i = 0; i < fragments.length; i++)
      {
         URI location = URI.create(fragments[i].getLocation() + SRC_FOLDER);
         String schemeSpecificPart = location.getSchemeSpecificPart();
         location = URI.create(schemeSpecificPart);
         Path path = new Path(URIUtil.toFile(location).getAbsolutePath());
         IRuntimeClasspathEntry entry = JavaRuntime.newArchiveRuntimeClasspathEntry(path);
         entries.add(entry);
      }
      return (IRuntimeClasspathEntry[]) entries.toArray(new IRuntimeClasspathEntry[0]);
   }
}
