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
package org.eclipse.stardust.modeling.common.ui;

import org.eclipse.osgi.util.NLS;

public class UI_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.common.ui.ui-messages"; //$NON-NLS-1$

   private UI_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, UI_Messages.class);
   }

   public static String BOX_ALWAYS_DEPLOY_NEW_VERSION;
public static String BpmUiActivator_noLicenseIsPresent;
   public static String LB_ReportFormat;
   public static String STR_HomeLocation;
   public static String LB_HomeLocation;
   public static String BTN_Browse;
   public static String STR_WorkLocation;

   public static String LB_WorkLocation;

   public static String STR_EnableCarnotBpm;

   public static String WorkbenchPreferencePage_autoSubprocessNameGeneration;

   public static String WorkbenchPreferencePage_autoValidation;

   public static String WorkbenchPreferencePage_DefaultDiagramMode;

   public static String WorkbenchPreferencePage_DiagramMode_Off;

   public static String WorkbenchPreferencePage_DiagramMode_On;

   public static String WorkbenchPreferencePage_EnableSnapToGridLabel;

   public static String WorkbenchPreferencePage_ViewForkOnTraversalLabel;   
   
   public static String WorkbenchPreferencePage_licenseFilePath;

   public static String WorkbenchPreferencePage_autoIdGeneration;

   public static String WorkbenchPreferencePage_ModelingDirectionLabel;

   public static String WorkbenchPreferencePage_SnapGridLabel;

   public static String WorkbenchPreferencePage_SnapGridPixelLabel;

   public static String WorkbenchPreferencePage_SnapGridValidationErrorMessage;

   public static String WorkbenchPreferencePage_VerticalDirection;

   public static String WorkbenchPreferencePage_HorizontalDirection;

   public static String LB_SwitchAutomaticallyActivityType;

   public static String LB_SwitchFocusMode;

   public static String LB_Element;   
   
   public static String LB_PropertyDialog;

   public static String LB_EditBox;   
   
   public static String LB_Always;

   public static String LB_Never;

   public static String LB_ShowWarning;

   public static String LB_AutoDistribute;

   public static String LB_SnapLastSymbol;

   public static String LB_SnapAllSymbols;

   public static String LB_Prompt;
   
   public static String LB_DefaultSplitType;   
   
   public static String LB_DefaultJoinType;

   public static String LB_AND;
   
   public static String LB_XOR;

   public static String LBL_TXT_DOMAIN;
public static String LBL_TXT_ID;
public static String LBL_TXT_PARTITION;
public static String LBL_TXT_PASSWORD;
public static String LBL_TXT_REALM;
public static String WorkbenchPreferencePage_VisibleGridFactorLabel;

   public static String CollisionPreferencePage_Update;      
   
   public static String CollisionPreferencePage_Refresh;   
   
   public static String CollisionPreferencePage_Retry;   

   public static String CollisionPreferencePage_enableUpdate;   
   
   public static String CollisionPreferencePage_enableRefresh;   
   
   public static String CollisionPreferencePage_enableRetry;   
   
   public static String CollisionPreferencePage_refreshRate;   
   
   public static String CollisionPreferencePage_retryRate;   
   
   public static String CollisionPreferencePage_refreshRateValidation;   
   
   public static String MultiPackageModelingPreferencePage_Public;
   
   public static String MultiPackageModelingPreferencePage_Private;
   
   public static String MultiPackageModelingPreferencePage_Visibility;
public static String PANEL_DEPLOY_MD;
}