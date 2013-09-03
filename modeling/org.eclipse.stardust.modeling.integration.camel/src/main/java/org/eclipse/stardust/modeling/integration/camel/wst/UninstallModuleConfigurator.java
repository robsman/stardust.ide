package org.eclipse.stardust.modeling.integration.camel.wst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.stardust.ide.wst.common.ExternalLibrary;
import org.eclipse.stardust.ide.wst.common.IWebModuleConfigurator;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class UninstallModuleConfigurator implements IWebModuleConfigurator
{

   public void performConfiguration(ArtifactEdit artifact)
   {
      List<ExternalLibrary> libs = CamelLibraries.getExternalLibraries();
      List<IVirtualReference> refs = new ArrayList<IVirtualReference>(
            Arrays.asList(artifact.getComponent().getReferences()));
      
      for (ExternalLibrary lib : libs)
      {
         String libName = "var/" + lib.var + "/" + lib.extension; //$NON-NLS-1$ //$NON-NLS-2$

         for (Iterator<IVirtualReference> refItr = refs.iterator(); refItr.hasNext();)
         {
            IVirtualReference ref = refItr.next();
            IVirtualComponent component = ref.getReferencedComponent();
            if ((null != component) && libName.equals(component.getName()))
            {
               refItr.remove();
            }
         }
      }
      
      artifact.getComponent().setReferences(refs.toArray(new IVirtualReference[0]));
   }

   public void performConfiguration(WebArtifactEdit artifact)
   {
      // no configuration needed
   }

}
