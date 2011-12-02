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
package org.eclipse.stardust.modeling.common.projectnature;

import org.eclipse.osgi.util.NLS;

public class ModelingCoreMessages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.common.projectnature.modeling_core_messages"; //$NON-NLS-1$

   private ModelingCoreMessages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, ModelingCoreMessages.class);
   }

   public static String MSG_BundleNotLoaded;

   public static String MSG_FailedResolvingBundle;

   public static String MSG_BundleNotContain;
}
