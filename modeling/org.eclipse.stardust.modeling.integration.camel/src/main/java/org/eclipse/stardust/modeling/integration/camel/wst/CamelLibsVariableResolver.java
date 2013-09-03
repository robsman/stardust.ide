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
package org.eclipse.stardust.modeling.integration.camel.wst;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.jdt.core.ClasspathVariableInitializer;
import org.eclipse.stardust.ide.wst.common.ExternalLibrary;
import org.eclipse.stardust.ide.wst.common.utils.LibraryProviderUtils;
import org.eclipse.stardust.ide.wst.common.utils.VariableUtils;
import org.osgi.framework.Bundle;

public class CamelLibsVariableResolver extends ClasspathVariableInitializer
   implements IDynamicVariableResolver
{
   public static final String BUNDLE_ID = "org.eclipse.stardust.modeling.integration.camel"; //$NON-NLS-1$
   public static final String CAMEL_3RD_PARTY_BUNDLE_ID = "org.eclipse.stardust.ide.thirdparty.camel"; //$NON-NLS-1$
   
   public static final String VAR_CAMEL_LIBS = "IPP_CAMEL_PORTAL_LIBS"; //$NON-NLS-1$
   public static final String VAR_3RD_PARTY_CAMEL_LIBS = "IPP_3RD_PARTY_CAMEL_LIBS"; //$NON-NLS-1$
   
   public String resolveValue(IDynamicVariable variable, String argument) throws CoreException
   {
      if (VAR_CAMEL_LIBS.equals(variable.getName()))
      {
         return VariableUtils.resolveDynamicVariableFromBundleRoot(variable,
               Platform.getBundle(BUNDLE_ID));
      }
      else if(VAR_3RD_PARTY_CAMEL_LIBS.equals(variable.getName()))
      {
         return VariableUtils.resolveDynamicVariableFromBundleRoot(variable,
               get3rdPartyPluginBundle());
      }
         
      return ""; //$NON-NLS-1$
   }

   @Override
   public void initialize(String variable)
   {
      if (VAR_CAMEL_LIBS.equals(variable))
      {
         VariableUtils.initializeClasspathVariableFromBundleRoot(variable,
               Platform.getBundle(BUNDLE_ID));
      }
      else if(VAR_3RD_PARTY_CAMEL_LIBS.equals(variable))
      {
         VariableUtils.initializeClasspathVariableFromBundleRoot(variable, 
               get3rdPartyPluginBundle());
      }
   }

   private Bundle get3rdPartyPluginBundle()
   {
      return Platform.getBundle(CAMEL_3RD_PARTY_BUNDLE_ID);
   }
   
   public static ExternalLibrary resolveCamelLibrary(String jarBaseName, boolean is3rdParty)
   {
      if(is3rdParty)
      {
         return LibraryProviderUtils.resolveExternalLibrary(VAR_3RD_PARTY_CAMEL_LIBS,
               CAMEL_3RD_PARTY_BUNDLE_ID, jarBaseName);         
      }
      else
      {
         return LibraryProviderUtils.resolveExternalLibrary(VAR_CAMEL_LIBS,
               BUNDLE_ID, jarBaseName);         
      }
   }
}
