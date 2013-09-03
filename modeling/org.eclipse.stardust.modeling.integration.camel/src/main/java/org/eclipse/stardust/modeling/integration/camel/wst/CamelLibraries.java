package org.eclipse.stardust.modeling.integration.camel.wst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.ide.wst.common.ExternalLibrary;

public class CamelLibraries
{
   public static List<ExternalLibrary> getExternalLibraries()
   {
      List<ExternalLibrary> libs = new ArrayList<ExternalLibrary>();

      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("ipp-camel.jar", false)); //$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-core.jar", true)); //$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-spring.jar", true)); //$NON-NLS-1$
      //libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-restlet.jar", true));
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-jms.jar", true));//$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-mail.jar", true));//$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-quartz.jar", true));//$NON-NLS-1$
//      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("jackson-core-asl.jar", true));//$NON-NLS-1$
//      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("jackson-mapper-asl.jar", true));//$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("camel-script.jar", true));//$NON-NLS-1$
      libs.add(CamelLibsVariableResolver.resolveCamelLibrary("bsf-all.jar", true));//$NON-NLS-1$
      return Collections.unmodifiableList(libs);
   }
}
