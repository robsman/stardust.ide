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
package org.eclipse.stardust.modeling.model.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.model.i18n.messages"; //$NON-NLS-1$

   public static String I18NBundlesModelPropertyPage_DeleteButtonLabel;

   public static String I18NBundlesModelPropertyPage_DeleteConfirmationDialogTitle;

   public static String I18NBundlesModelPropertyPage_DeleteConfirmationMessage;

   public static String I18NBundlesModelPropertyPage_NoSourceFolderMessage;

   public static String I18NBundlesPropertyPage_Bundle_Basename_Label;

   public static String I18NBundlesPropertyPage_NoLanguageBundles;

   public static String I18NBundlesValidator_MISSING_VALUE;

   public static String PropertyValuesEditor_NLS;

   public static String PropertyValuesEditor_Value;
   
   public static String PropertiesList_PropertyName;

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   private Messages()
   {}
}
