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
package org.eclipse.stardust.modeling.modelexport;

import org.eclipse.osgi.util.NLS;

public class Export_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.modelexport.export-messages"; //$NON-NLS-1$

   private Export_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Export_Messages.class);
   }

   public static String ExportCarnotModelWizardPage_OverwriteQuestionString;

   public static String ExportCarnotModelWizardPage_OverwriteQuestionTitle;

   public static String BTN_Browse;

   public static String LB_Select;

   public static String DESC_DeployModelEnvironment;

   public static String LB_ToDir;

   public static String Btn_Browse;

   public static String DESC_ModelsToExport;

   public static String TITLE_DeployModelWiz;

   public static String STR_Unspecified;

   public static String DESC_ExportModel;   

   public static String MSG_SaveModelsBeforeExporting;

   public static String TITLE_SaveModels;
   
   public static String MSG_NoDirectory;
   
   public static String MSG_DirectoryDoesNotExist;
   
   public static String MSG_NoModelSelected;
   
   public static String MSG_InvalidResource;
   
   public static String LB_ADVANCED;

   public static String BTN_CUSTOM_CARNOT_HOME;

   public static String BTN_CUSTOM_CARNOT_WORKSPACE;

}