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
package org.eclipse.stardust.modeling.modelimport;

import org.eclipse.osgi.util.NLS;

public class Import_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.modelimport.import-messages"; //$NON-NLS-1$

   public static  String MSG_LOCK_MODEL_REQUIRED;

   public static  String MSG_IMPORT_MODEL_ELEMENTS;

   private Import_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Import_Messages.class);
   }

   public static String DifferencesViewer_additionFilterText;

   public static String DifferencesViewer_additionFilterTooltip;

   public static String DifferencesViewer_allFilterActionText;

   public static String DifferencesViewer_allFilterActionTooltip;

   public static String DifferencesViewer_changedFilterText;

   public static String DifferencesViewer_changedFilterToolTip;

   public static String DifferencesViewer_deletionFilterText;

   public static String DifferencesViewer_deletionFilterTooltip;

   public static String DifferencesViewer_Title;

   public static String ImportModelWizardPage_WarningIdAlreadyExists;

   public static String MergeEditorInput_CheckingDuplicateOidsTaskName;

   public static String MergeEditorInput_ComparingTaskName;

   public static String MergeEditorInput_ERROR_MESSAGE_CONTINUE;

   public static String MergeEditorInput_ERROR_MESSAGE_HEADLINE;

   public static String MergeEditorInput_ERROR_MESSAGE_NEWOIDS;

   public static String MergeEditorInput_ERROR_MESSAGE_TITLE;

   public static String MergeEditorInput_LoadingTaskName;

   public static String MergeEditorInput_OpenCompareViewerTaskName;

   public static String MergeEditorInput_OpenEditorJobName;

   public static String MergeModelElementsWizardPage_Comment;

   public static String MergeModelElementsWizardPage_InfoDialogText;

   public static String MergeModelElementsWizardPage_InfoDialogTitle;

   public static String MergeModelElementsWizardPage_ERROR_DIALOG_TITLE;

   public static String MergeModelElementsWizardPage_Title;

   public static String MergeUtil_DUPLICATE_OIDS_SOURCE;

   public static String MergeUtil_DUPLICATE_OIDS_TARGET;

   public static String MergeUtil_MISSING_OIDS_SOURCE;

   public static String MergeUtil_MISSING_OIDS_TARGET;

   public static String MSG_InvalidSel;

   public static String MSG_SelectModel;

   public static String LB_ElementsImport;

   public static String NAME_ImportWiz;

   public static String TITLE_ImportWiz;

   public static String LB_Select;

   public static String DESC_ImportFile;

   public static String LB_Types;

   public static String MSG_Err;

   public static String DESC_CarnotFileImport;

   public static String LB_ModelTypes;

   public static String NAME_ImportCarnotProcessModelWiz;

   public static String NAME_CarnotWorkflowImportPage;

   public static String TASK_OpenFile;

   public static String MSG_RootModelWithId;

   public static String STR_SelectAll;

   public static String STR_DeselectAll;

   public static String STR_ImportFromRepository;

   public static String MSG_SelectImportDir;

   public static String MSG_SrcNotEmpty;

   public static String BTN_OverwriteModels;

   public static String BTN_CreateDirStructure;

   public static String MSG_InvalidSrc;

   public static String MSG_ImportProblems;

   public static String TITLE_Info;

   public static String MSG_NoFilesSelected;

   public static String LB_FromDir;

   public static String LB_FromXML;

   public static String STR_XmlFiles;

   public static String STR_XpdlFiles;

   public static String STR_ModFiles;

   public static String STR_AnyFile;

   public static String BTN_Browse;

   public static String CarnotAuditTrailSourceGroupProvider_1;

   public static String CarnotAuditTrailSourceGroupProvider_2;

   public static String CarnotAuditTrailSourceGroupProvider_3;

   public static String LB_TargetFile;

   public static String MSG_CouldNotCreateActivityGraphForProcess;

   public static String MSG_CouldNotCreateActivityGraph;

   public static String MSG_CreateTransitions;

   public static String MSG_CouldNotCreateFromInputStream;

   public static String MSG_CannotCreateDocFromNull;

   public static String MSG_ErrRetrievingChild;

}
