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
package org.eclipse.stardust.modeling.templates;

import org.eclipse.osgi.util.NLS;

public class Templates_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.templates.templates-messages"; //$NON-NLS-1$

   public static String CSL_CONTAINMENT_FEATURE_NOT_FOUND_FOR;

   public static String CSL_INCOMPATIBLE_CONTAINER;

   public static String ERROR_PROVIDE_A_NUMBER_OF_ACTIVITIES;

   public static String EXC_NOT_SUPPORTED;

   public static String TITLE_APPLY;

   public static String TXT_COPY_OF;

   public static String TXT_TEMPLATE_LIBRARY;

   public static String Could_not_find_resource;

   public static String LB_VersionRepository_Refresh;

   public static String The_class_is_not_a_valid_classifier;

   public static String The_datatype_is_not_a_valid_classifier;

   public static String The_value_is_not_a_valid_enumerator_of;

   public static String MANUAL_ACTIVITY;

   public static String APPLICATION_ACTIVITY;

   public static String ROUTE_ACTIVITY;

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Templates_Messages.class);
   }

   private Templates_Messages()
   {}
}
