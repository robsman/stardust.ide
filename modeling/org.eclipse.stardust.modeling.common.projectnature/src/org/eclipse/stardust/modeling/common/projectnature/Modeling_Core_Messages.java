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

public class Modeling_Core_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.common.projectnature.modeling-core-messages"; //$NON-NLS-1$

   private Modeling_Core_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Modeling_Core_Messages.class);
   }

   public static String MSG_BundleNotLoaded;

   public static String MSG_FailedResolvingBundle;

   public static String MSG_BundleNotContain;
}
