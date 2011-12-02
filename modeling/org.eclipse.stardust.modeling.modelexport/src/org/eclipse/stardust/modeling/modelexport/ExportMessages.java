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

public class ExportMessages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.modelexport.export_messages"; //$NON-NLS-1$

   private ExportMessages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, ExportMessages.class);
   }

   public static String ExportCarnotModelWizardPage_OverwriteQuestionString;

   public static String ExportCarnotModelWizardPage_OverwriteQuestionTitle;

   public static String LB_CarnotHome;

   public static String LB_CarnotWorkspace;

   public static String BTN_Browse;

   public static String LB_Select;

   public static String DESC_DeployModelEnvironment;

   public static String LB_ToDir;

   public static String Btn_Browse;

   public static String DESC_ModelsToExport;

   public static String TITLE_DeployModelWiz;

   public static String STR_Unspecified;

   public static String STR_ValidFrom;

   public static String STR_ValidTo;

   public static String LB_Options;

   public static String BTN_DeployVersion;

   public static String BTN_Overwrite;

   public static String BTN_OverwriteActiveModel;

   public static String BTN_IgnoreWarn;

   public static String BTN_InitDisabled;

   public static String BTN_Deselect;

   public static String TITLE_Select;

   public static String DESC_DeployedModels;

   public static String DESC_ExportModel;   

   public static String DESC_ModelToOverwrite;

   public static String DESC_PredecessorModel;

   public static String LB_DeployedModels;

   public static String LB_ActiveModel;

   public static String LB_ModelName;

   public static String LB_ModelId;

   public static String LB_ModelOID;

   public static String LB_ValidFrom;

   public static String LB_ValidTo;

   public static String MSG_SaveModelsBeforeExporting;

   public static String TITLE_SaveModels;
   
   public static String MSG_NoDirectory;
   
   public static String MSG_DirectoryDoesNotExist;
   
   public static String MSG_NoModelSelected;
   
   public static String MSG_InvalidResource;
}