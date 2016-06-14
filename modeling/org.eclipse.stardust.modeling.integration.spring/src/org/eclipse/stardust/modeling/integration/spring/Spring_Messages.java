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
package org.eclipse.stardust.modeling.integration.spring;

import org.eclipse.osgi.util.NLS;

public class Spring_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.spring.spring-messages"; //$NON-NLS-1$

   private Spring_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Spring_Messages.class);
   }

   public static String LB_AppCtxFile;

   public static String LB_Reload;

   public static String LB_DecBeans;

   public static String LB_BrowseBeans;

   public static String MSG_FailedLoadingBeanDef;
   
   public static String LB_SpringBeanApplicationClass;
}
