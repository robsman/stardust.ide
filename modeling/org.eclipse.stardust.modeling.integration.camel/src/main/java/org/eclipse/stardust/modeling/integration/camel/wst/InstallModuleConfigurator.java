package org.eclipse.stardust.modeling.integration.camel.wst;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.ide.wst.common.ExternalLibrary;
import org.eclipse.stardust.ide.wst.common.IWebModuleConfigurator;
import org.eclipse.stardust.ide.wst.common.utils.ArtifactUtils;
import org.eclipse.stardust.ide.wst.common.utils.FacetSetupUtils;
import org.eclipse.stardust.ide.wst.facet.portal.IPPPortalBundleActivator;
import org.eclipse.stardust.ide.wst.facet.portal.IPPPortalFacet;
import org.eclipse.stardust.modeling.common.platform.utils.BundleUtils;
import org.eclipse.wst.common.componentcore.ArtifactEdit;

public class InstallModuleConfigurator implements IWebModuleConfigurator
{

   public void performConfiguration(ArtifactEdit artifact)
   {
      List<ExternalLibrary> libs = CamelLibraries.getExternalLibraries();
      
      for (ExternalLibrary lib : libs)
      {
         ArtifactUtils.addWebLibraryFromVar(artifact, lib.var + "/" + lib.extension); //$NON-NLS-1$
      }
      
      IProject project = artifact.getProject();
      IPath srcFolder = BundleUtils.getBundleLocation(CamelLibsVariableResolver.BUNDLE_ID);
      
      Map<String, String> properties = CollectionUtils.newMap();
      properties.put("templatesDir", srcFolder.append("template").toOSString()); //$NON-NLS-1$ //$NON-NLS-2$
      properties.put("toDir", project.getFolder(IPPPortalFacet.PORTAL_ROOT) //$NON-NLS-1$
            .getLocation()
            .toOSString());
      properties.put("prjLocalPath", project.getFolder(IPPPortalFacet.PORTAL_ROOT) //$NON-NLS-1$
            .getFullPath()
            .toOSString());
      properties.put("projectName", project.getName()); //$NON-NLS-1$
      
      FacetSetupUtils.executeAntTarget(project, srcFolder.append("tools/templates.xml"), //$NON-NLS-1$
            "copyTemplates", properties); //$NON-NLS-1$
   }

   public void performConfiguration(WebArtifactEdit artifact)
   {
      // no configuration needed
   }

}
