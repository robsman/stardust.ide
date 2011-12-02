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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.osgi.util.NLS;

public class Debug_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.debug.debug_messages"; //$NON-NLS-1$

   private Debug_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Debug_Messages.class);
   }

   public static String LaunchAction_Name;
   public static String LaunchAction_ToolTip;
   public static String LaunchDelegate_CanNotLaunch;
   public static String LaunchDelegate_ERROR;
   public static String LaunchDelegate_MODE_Analyst;
   public static String LaunchDelegate_MODE_Developer;
   public static String LaunchDelegate_ModelInconsistencies;
   public static String LaunchDelegate_MSG_IncompatibleSessions;
   public static String LaunchDelegate_MSG_InconsistentModel;
   public static String LaunchDelegate_MSG_OneSessionPerEditor;
   public static String LaunchDelegate_MSG_SuggestAnalystSession;
   public static String LaunchDelegate_WARNING;
   public static String MSG_COND_isNoArrayType;
   public static String MSG_TransitionTokenDigest_ToString;
   public static String MSG_ActivityInstanceDigest_ToString;
   public static String EXP_VariableDoesNotExist;
   public static String EXP_ErrorOccuredWhileLoadingModelFile;
   public static String MSG_CONS_InstanceNeccessary;
   public static String TESTCHANGEKEY;
   public static String MSG_SelectWorkflowModelInProject;
   public static String TXT_ProjectHasToBeSpecified;
   public static String TXT_ModelFileHasToBeSpecified;
   public static String TITLE_CarnotWorkflowModel;
   public static String MSG_SelectProcessDefinitionFromModelFile;
   public static String LB_Project;
   public static String LB_Model;
   public static String LB_ProcessDefinition;
   public static String LB_Browse;
   public static String ERR_SpecifyProject;
   public static String ERR_SpecifiedProjectDoesNotExist;
   public static String ERR_SpecifyModelFile;
   public static String ERR_SpecifiedModelFileDoesNotExist;
   public static String ERR_SpecifyProcessDefinitionID;
   public static String EXP_BundleHasNotBeenLoadedYet;
   public static String EXP_BundleDoesNotContain;
   public static String EXP_ErrorWhileExtendingBootpath;
   public static String CWM_DEBUG_NAME_CARNOTWorkflowDebugger;
   public static String CWM_DEBUG_MSG_CurrentActivityInstanceDigest;
   public static String CWM_DEBUG_MSG_ResumeJavaThread;
   public static String CWM_DEBUG_MSG_Performed;
   public static String CWM_DEBUG_MSG_Started;
   public static String CWM_DEBUG_MSG_Completed;
   public static String CWMStack_MSG_ActivityInstanceOID;
   public static String CWMStack_ActivityInstanceUnknown;
   public static String CWMThread_MSG_CreateNewWorkflowThread;
   public static String CWMThread_MSG_Activity;
   public static String CWMThread_MSG_NoCurrentActivity;
   public static String CWMThread_MSG_SetCurrentActivityInstance;
   public static String EXP_FailedLoadingModel;
   public static String EXP_ErrorLoadingNetwork;
   public static String MSG_ProjectDoesNotExist;
   public static String MSG_ModelFileDoesNotExist;
   public static String MSG_ModelFileNeedsToBeLoaded;
   public static String MSG_ProcessDefinitionUnspecified;
   public static String MSG_ProcessDefIdEmpty;
   public static String MSG_ProcessDefWithIdNotFound;
   public static String MSG_ProcessInstanceDigest_ToString;
   public static String ASSERT_ValueCarrierMayNotBeNull;
   public static String ASSERT_ListOfActivityInstancesMayNotBeNull;
   public static String BUTTON_ClearContent;
   public static String TOOLTIP_ClearContent;
   public static String BUTTON_ClearEmptyPerformers;
   public static String TOOLTIP_ClearEmptyPerformers;
   public static String MSG_ActivityInstanceCannotBeResumedFromWorklist;
   public static String BUTTON_ActivateFromWorklist;
   public static String TOOLTIP_ActivateFromWorklist;
   public static String TITLE_CARNOTWorklist;
   public static String WorklistContentFormat;
   public static String CWMThread_MSG_RootProcessInstance;
   public static String CWMThread_MSG_NoRootProcessInstance;
   public static String MSG_ProcessOID;
   public static String MSG_SelectType;
}
