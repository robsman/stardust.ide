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
package org.eclipse.stardust.modeling.core;

import org.eclipse.osgi.util.NLS;

public class Diagram_Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.core.diagram-messages"; //$NON-NLS-1$

   private Diagram_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Diagram_Messages.class);
   }

   public static String DEFAULT_CURRENCY;

   public static String MSG_Replace_performer_with_performer_for_activity;

   public static String DESC_CurrentUser;

   public static String DESC_CurrentDate;

   public static String LB_ModelDiagram;

   public static String LB_SelectTypes;

   public static String LBL_ALL_ELEMENTS;

   public static String LBL_CARNOT_AG;

   public static String LBL_COMMENT;

   public static String LBL_CONNECTION;

   public static String LBL_EXTERNAL_MD_INFORMATION;

   public static String LBL_EXTERNAL_PCK;

   public static String LBL_FILE_CONNECTION;

   public static String LBL_FILTER;

   public static String LBL_HAS_NO_ID;

   public static String LBL_ID;

   public static String LBL_IMP_MD_ID;

   public static String LBL_INVOCATION_TYPE;

   public static String LBL_LOCKED;

   public static String LBL_MD_ID;

   public static String LBL_MD_MUST_BE_UPGRADE;

   public static String LBL_MECHANISM;

   public static String LBL_NAME;

   public static String LBL_NULL;

   public static String LBL_NULL_LOCKED_BY_ONE;

   public static String LBL_PLEASE_PROVIDE_A_CATEGORY;

   public static String LBL_PLEASE_PROVIDE_A_DIRECTION;

   public static String LBL_REFERENCES;

   public static String LBL_TXT_DEFAULT_PRIORITY;

   public static String LBL_TXT_PLEASE_PROVIDE_A_DATA;

   public static String LBL_TXT_PLEASE_PROVIDE_A_DATATYPE;

   public static String LBL_TXT_PLEASE_PROVIDE_A_ID;

   public static String LBL_TXT_PLEASE_PROVIDE_A_NAME;

   public static String LBL_VARIABLES;

   public static String LBL_VARIANT;

   public static String MSG_DeleteConnectionResolution;

   public static String MSG_PASTE_NOT_POSSIBLE;

   public static String MSG_ProjectNotOpen;

   public static String NewWorkflowDiagramWizardPage_MSG_FolderMustNotBeEmpty;

   public static String PeriodVerifier_PATTERN;

   public static String TXT_WITH_AN_ARBITRARY_LITERAL;

   public static String TXT_WITH_AN_EMPTY_LITERAL;

   public static String TXT_WITH_THE_DEFAULT_VALUE_OF_THE_VARIABLE_TO_BE_REMOVED;

   public static String TXT_WorkflowModelValidation;

   public static String TXT_WR;

   public static String MSG_ProcessDefinition;

   public static String MSG_FolderMustExist;

   public static String MSG_WrongFileExtension;

   public static String MSG_Activity;

   public static String MSG_DataPath;

   public static String MSG_EventHandler;

   public static String MSG_Trigger;

   public static String MSG_Transition;

   public static String MSG_EventAction;

   public static String MSG_BindAction;

   public static String MSG_BOX_RMS_OPERATION;

   public static String MSG_UnbindAction;

   public static String MSG_DataMapping;

   public static String MSG_Application;

   public static String MSG_ConditionalPerformer;

   public static String MSG_Modeler;

   public static String BUT_JAXWS_CXF;

   public static String MSG_Organization;

   public static String MSG_Role;

   public static String MSG_Data;

   public static String ERR_Error;

   public static String BASENAME_Administrator;

   public static String DESC_InChargeAdministrationActivities;

   public static String DESC_LastActivityPerformer;

   public static String DESC_StartingUser;

   public static String DESC_ProcessOID;

   public static String DESC_RootProcessOID;

   public static String DESC_CurrentLocale;

   public static String DESC_CurrentModel;

   public static String DESC_CarnotWizard;

   public static String NAME_StartingUser;

   public static String NAME_RootProcessOID;

   public static String NAME_CurrentLocale;

   public static String NAME_CurrentModel;

   public static String NAME_DefaultDiagram;

   public static String MSG_AnotherFileAlreadyExists;

   public static String MSG_SelectNewFileContainer;

   public static String MSG_ModelFileNameMustBeValid;

   public static String TASK_ANALYZING_DIFFERENCES;

   public static String TASK_Creating;

   public static String TASK_LOCK_ALL_ELEMENTS;

   public static String TASK_LOCK_ELEMENTS;

   public static String TITLE_NewCarnotWorkflowModel;

   public static String LB_ModelAndId;

   public static String LB_ModelAndName;

   public static String LB_NewWorkflowModel;

   public static String LB_DESC_Description;

   public static String LB_Folder;

   public static String TXT_Browse;

   public static String TXT_AdvancedRight;

   public static String TXT_Author;

   public static String TXT_FILE_CONNECTION_SELECTION;

   public static String TXT_FileName;

   public static String TXT_AdvancedLeft;

   public static String MSG_ValidModelIDMustBeSpecified;

   public static String MSG_ValidModelNameMustBeSpecified;

   public static String NAME_LastActivityPerformer;

   public static String NAME_CurrentUser;

   public static String NAME_ProcessOID;

   public static String NAME_CurrentDate;

   public static String TASK_OpeningFileForEditing;

   public static String TASK_PREPARING_FOR_COMMIT;

   public static String TASK_PREPARING_FOR_REVERT;

   public static String TASK_PREPARING_FOR_UPDATE;

   public static String TASK_READING_LOCAL_MD;

   public static String TASK_READING_REMONTE_MD;

   public static String TASK_READING_REMOTE_MD;

   public static String TASK_REMOTE_MD;

   public static String TASK_REVERT_CHANGES;

   public static String TASK_UNLOCK_ALL;

   public static String NAME_RenameViewAction;

   public static String NAME_DeleteViewAction;

   public static String NAME_AddViewAction;

   public static String LB_TraverseView_Pop;

   public static String LB_TraverseView_Path;

   public static String LB_TraverseView_LinkedObjects;

   public static String DESC_ExportDiagram;

   public static String MENU_ExportAllDiagrams;

   public static String TITLE_SelectFileAndImageFormat;

   public static String LABEL_SelectFile;

   public static String BUT_AXIS;

   public static String BUT_BOX_REST;

   public static String BUT_BOX_SOAP;

   public static String BUT_CLOSE_EDITOR;

   public static String BUT_CUT;

   public static String BUT_DOM;

   public static String BUT_EJB_DREI_X;

   public static String BUT_EJB_ZWEI_X;

   public static String BUT_JAX;

   public static String BUT_RESET;

   public static String BUT_SEARCH;

   public static String BUT_STANDARD_API;

   public static String BUT_TXT_GENERATE_WSDL;

   public static String BUT_TXT_OK;

   public static String BUT_UPRGADE_NOW;

   public static String BUT_XOM;

   public static String BUTTON_Browse;

   public static String GROUP_ImageFormat;

   public static String MENU_SelectDirectoryAndImageFormat;

   public static String LABEL_SelectDirectory;

   public static String LB_Action_DefineStartActivity;

   public static String LB_ACTION_SetData;

   public static String LB_ACTION_SetDataPath;

   public static String LB_ACTION_NoLoop;

   public static String LB_ACTION_ChangeDataType;

   public static String LB_ACTION_DelDataMapping;

   public static String LB_ACTION_AdjustReferenceID;

   public static String MSG_LoadingModelFailed;

   public static String WIZARD_ExportModel;

   public static String WIZARD_LB_Process;

   public static String WIZARD_LB_Diagram;

   public static String WIZARD_LB_ToFile;

   public static String LB_NoDescriptor;

   public static String WIZARD_LB_Browse;

   public static String LB_KeyDescriptor;

   public static String WIZARD_LB_Select;

   public static String WIZARD_DESCRIPTION_Export;

   public static String TITLE_ModelDialog;

   public static String MSG_SelectProcess;

   public static String WIZARD_MSG_SelectDiagram;

   public static String WIZARD_LB_FileFormat;

   public static String MSG_DeleteVersion_Error;

   public static String MSG_DIA_ALL_SYMBOLS;

   public static String MSG_DIA_CHANGING_MODEL_IS_NOT_RECOMMENDED;

   public static String MSG_DIA_CLOSEST_ONLY;

   public static String MSG_DIA_DEPRECATED_MD_FORMAT_DETECTED;

   public static String MSG_DIA_DO_YOU_WANT_TO_UPDATE_MD_TO_CURRENT_SPECS_NULL;

   public static String MSG_DIA_ELEMENT_NULL_ONE_MUST_BE_LOCKED_FIRST;

   public static String MSG_DIA_INVALID_ID_NULL_ONE;

   public static String MSG_DIA_MODEL_NULL_WAS_CREATED_USING_A_NEWER_VERSION_OF_THE_MODELER_ONE;

   public static String MSG_DIA_MOVE_SYMBOLS;

   public static String MSG_DIA_NONE;

   public static String MSG_DIA_REPOSITORY_CONNECTION;

   public static String MSG_DIA_RETRIEVING_WSDL;

   public static String MSG_DIA_SET_PERFORMANCER;

   public static String MSG_DIA_SET_PERFORMER;

   public static String MSG_DIA_THIS_ACTION_WILL_CAUSE_OVERLAPPING_OF_SYMBOLS_DO_YOU_WANT_TO_CONTINUE;

   public static String MSG_DIA_THIS_OPERATION_REQUIRES_THE_MD_TO_BE_LOCKED_YOU_MUST_LOCK_THE_MD_TO_PROCEED;

   public static String MSG_DIA_THIS_OPERATION_REQUIRES_THE_WHOLE_MD_TO_BE_LOCKED_YOU_MUST_LOCK_THE_WHOLE_MD_TO_PROCEED;

   public static String MSG_DIA_TXT_IMPORT_MD_ELEMENT;

   public static String MSG_DIA_UNKNOWN_VERSION_FOR_MD_NULL;

   public static String MSG_DIA_WSDL;

   public static String MSG_DO_YOU_WANT_TO_UPDATE_MD_TO_VERSION_ONE;

   public static String LB_Versionize;

   public static String LB_CreateSuccesorVersion;

   public static String MSG_Error;

   public static String MSG_RESOURCE_CONTAINS_NO_MODEL;

   public static String MSG_UNABLE_TO_FIND_XPDL_EXPORT_STYLESHEET;

   public static String LB_Repository_Project;

   public static String LB_Repository_SelectionProject;

   public static String LB_Repository_Open;

   public static String LB_VersionRepository_StickyProject;

   public static String LB_VersionRepository_Project;

   public static String LB_VersionRepository_Refresh;

   public static String MENU_Repository_PopUp;

   public static String JOB_RepositoryUpdate;

   public static String MSG_ErrorUpdateRepository;

   public static String MSG_ClearingProject;

   public static String MSG_FailedLoadingModel;

   public static String LB_ACTION_CopyVersion;

   public static String MSG_EnterNewID;

   public static String MSG_NoId;

   public static String Title_FileExists;

   public static String LB_Find;

   public static String BTN_Search;

   public static String TITEL_Search;

   public static String LB_Result;

   public static String LB_Reference;

   public static String LB_References;

   public static String LB_SearchQuery;

   public static String LB_Search;

   public static String TITLE_InvalidDir;

   public static String MSG_INVALID_SCOPED_PARTICIPANT;

   public static String MSG_InvalidDir;

   public static String LB_Type;

   public static String LB_Path;

   public static String MSG_InvalidIndex;

   public static String LB_DetailsFor;

   public static String LB_All;

   public static String LB_Indexed;

   public static String COL_ATTRIBUTE;

   public static String COL_Element;

   public static String COL_MD;

   public static String COL_MD_ELEMENT;

   public static String COL_Type;

   public static String LB_Item;

   public static String LB_Items;

   public static String SplitJoinDialog_Title;

   public static String SplitJoinDialog_Save;

   public static String BASENAME_Annotation;

   public static String BIND_NoPropertyPagesConnectionPartOf;

   public static String BIND_NoPropertyPagesConnectionPerformedBy;

   public static String BIND_NoPropertyPagesExecutedBy;

   public static String BIND_NoPropertyPagesGenericLink;

   public static String BIND_NoPropertyPagesSubprocessOf;

   public static String BIND_NoPropertyPagesWorksFor;

   public static String BIND_NoPropertyPagesTeamLead;

   public static String BIND_NoPropertyPagesAnnotationSymbol;

   public static String BIND_NoPropertyPagesTextSymbol;

   public static String BIND_NoPropertyPagesSymbolGroup;

   public static String BIND_NoPropertyPagesTriggersConnection;

   public static String BIND_NoPropertyPagesRefersToConnection;

   public static String BTN_Browse;

   public static String ConnectAction_Label;

   public static String ConnectAction_ToolTip;

   public static String ConnectAction_WorksForConnectionType;

   public static String ConnectAction_TeamLeadConnectionType;

   public static String DIA_DO_YOU_WANT_TO_CREATE_CONNECTION_TO_ALL_SYMBOLS_NOR_TO_THE_CLOSEST_CLOSSEST_SYMBOL_ONLY;

   public static String DIA_MULTIPLE_CONNECTION_DETECTED;

   public static String DIA_REPOSITORY_CONNECTION;

   public static String DIA_THIS_OPERATION_REQUIRES_THE_MD_TO_BE_LOCKED_YOU_MUST_LOCK_THE_MD_TO_PROCEED;

   public static String DIA_TITLE_ELEMENT_SELE;

   public static String DIAGRAM_MODE_OFF;

   public static String DIAGRAM_MODE_ON;

   public static String LB_CopyElement;

   public static String LB_GroupModelElements;

   public static String CreateDataAction_LB_NewData;

   public static String DESC_PALETTEDRAWER_Activities;

   public static String DESC_PALETTEDRAWER_ProcessLifecycle;

   public static String DiagramEditor_PAGENAME_UnnamedProcess;

   public static String LB_Applications;

   public static String LB_CutElement;

   public static String LB_PALETTEDRAWER_ProcessLifecycle;

   public static String LB_Participants;

   public static String LB_PasteElement;

   public static String LB_SHRINK_TO_FIT;

   public static String LicensePage_Expiration;

   public static String LicensePage_Heading;

   public static String LicensePage_LicensePath;

   public static String LicensePage_Product;

   public static String LicensePage_ProductWithRelease;

   public static String LicensePage_ReferToCarnotPreferencePage;

   public static String LicensePage_ValidCPUs;

   public static String NodeCreationFactory_BASENAME_Application;

   public static String NodeCreationFactory_BASENAME_Data;

   public static String DiagramEditor_PAGENAME_UnnamedDiagram;

   public static String BASENAME_Organization;

   public static String BASENAME_ConditionalPerformer;

   public static String BASENAME_InteractiveApplication;

   public static String BASENAME_P2_Activity;

   public static String BASENAME_ProcessDefinition;

   public static String DIAGRAM_NAME_Default;

   public static String BASENAME_Modeler;

   public static String BASENAME_Pool;

   public static String BASENAME_Lane;

   public static String BASENAME_Text;

   public static String ERR_OpenError;

   public static String ERR_duringOpeningTheEditor;

   public static String MSG_CreateFile;

   public static String MSG_Confirm_P1_TheFile;

   public static String MSG_Confirm_P2_doesntExist;

   public static String ERR_DuringSave;

   public static String ERR_WorkflowModelCouldNotBeSaved;

   public static String ERR_CurrentModelCouldNotBeSaved;

   public static String EX_SpecifiedInputNotValidModel;

   public static String EX_FailedLoadingModel;

   public static String EX_ErrorLoadingNetwork;

   public static String ResetSubprocessAction_Lb_NoSubprocess;

   public static String SetDefaultSizeAction_Label;

   public static String TASKNAME_Saving;

   public static String ERR_NoModelManagerFound;

   public static String ERR_NULL_SCHEMA_FOR_DECL_NULL;

   public static String ERR_writingFile;

   public static String LB_ACTION_RemoveSymbol;

   public static String LB_SUBMENU_NewActivity;

   public static String LB_SUBMENU_NewParticipant;

   public static String LB_EXTMENU_NewData;

   public static String LB_EXTMENU_NewApplication;

   public static String LB_EXTMENU_NewInteractiveApplication;

   public static String LB_EXTMENU_NewTrigger;

   public static String LB_SUBMENU_Align;

   public static String LB_SUBMENU_Distribute;

   public static String MENU_Manager_View;

   public static String LB_PALETTEGROUP_Controls;

   public static String LB_PALETTEDRAWER_Connections;

   public static String LB_PALETTEDRAWER_Items;

   public static String LB_TOOLENTRY_ProcessDefintion;

   public static String DESC_TOOLENTRY_CreatesNewWorkflow;

   public static String LB_PALETTEDRAWER_Activities;

   public static String LB_TOOLENTRY_Route;

   public static String DESC_TOOLENTRY_CreatesNewRouteActivity;

   public static String LB_TOOLENTRY_Manual;

   public static String DESC_TOOLENTRY_CreatesNewManualActivity;

   public static String LB_TOOLENTRY_Application;

   public static String DESC_TOOLENTRY_CreatesNewApplicationActivity;

   public static String LB_TOOLENTRY_Subprocess;

   public static String DESC_TOOLENTRY_CreatesNewSubprocessActivity;

   public static String LB_TOOLENTRY_Pool;

   public static String DESC_TOOLENTRY_CreatesNewPool;

   public static String LB_TOOLENTRY_Lane;

   public static String DESC_TOOLENTRY_CreatesNewLane;

   public static String LB_PALETTESTACK_Annotations;

   public static String DESC_PALETTESTACK_CreateAnnotation;

   public static String LB_TOOLENTRY_Annotation;

   public static String DESC_TOOLENTRY_CreatesNewAnnotation;

   public static String LB_TOOLENTRY_Text;

   public static String DESC_TOOLENTRY_CreatesNewTextField;

   public static String LB_PALETTESTACK_Contexts;

   public static String DESC_PALETTESTACK_CreateInteractiveApplications;

   public static String DESC_TOOLENTRY_P1_CreatesNew;

   public static String DESC_TOOLENTRY_P2_interactiveApplication;

   public static String LB_PALETTESTACK_Applications;

   public static String DESC_PALETTESTACK_CreateNoninteractiveApplications;

   public static String LB_TOOLENTRY_P1_CreatesNew;

   public static String LB_TOOLENTRY_P2_application;

   public static String LB_TOOLENTRY_StartEvent;

   public static String DESC_TOOLENTRY_CreatesNewStartEvent;

   public static String LB_TOOLENTRY_EndEvent;

   public static String DESC_TOOLENTRY_CreatesNewEndEvent;

   public static String LB_TOOLENTRY_P2_trigger;

   public static String LB_PALETTESTACK_Data;

   public static String DESC_PALETTESTACK_CreateData;

   public static String LB_TOOLENTRY_P2_workflowData;

   public static String LB_PALETTESTACK_Participants;

   public static String DESC_PALETTESTACK_Participants;

   public static String LB_TOOLENTRY_Role;

   public static String DESC_TOOLENTRY_CreatesNewWorkflowRole;

   public static String LB_TOOLENTRY_Organization;

   public static String DESC_TOOLENTRY_CreatesNewWorkflowOrganization;

   public static String LB_TOOLENTRY_ConditionalPerformer;

   public static String DESC_TOOLENTRY_CreatesNewWorkflowCondPerformer;

   public static String DIAGRAM_CreateSymbol;

   public static String BASENAME_Symbol;

   public static String TXT_MENU_MANAGER_JoinBehavior;

   public static String TXT_MENU_MANAGER_SplitBehavior;

   public static String TYPE_NAME_ElementToSymbolTransfer;

   public static String MSG_EDITOR_unidentifiedModelElement;

   public static String ERR_UnsupportedPropertyId;

   public static String LB_ID;

   public static String LB_Name;

   public static String LB_AllowsAbort;

   public static String LB_InitiallyHibernate;

   public static String LB_Description;

   public static String B_Apply;

   public static String RELOAD_CONNECTIONS_LABEL;

   public static String TXT_AvailableConnections;

   public static String TXT_NewModeler;

   public static String TXT_ValidateModel;

   public static String TXT_NewProcessDefinition;

   public static String TXT_DefaultLaneParticipant;

   public static String TXT_SEL_MD_ELEMENTS;

   public static String TXT_SERVICE_NULL;

   public static String TXT_SET_TEAM_LEADER;

   public static String TXT_SetDefaultParticipant;

   public static String REQ_DISTRIBUTE;

   public static String DISTRIBUTE_HORIZONTAL_LABEL;

   public static String DISTRIBUTE_VERTICAL_LABEL;

   public static String GROUP_SYMBOLS_LABEL;

   public static String UNGROUP_SYMBOLS_LABEL;

   public static String MSG_PropertyPages;

   public static String BIND_NoPropertyPages;

   public static String BIND_Properties;

   public static String TXT_NewConditionalPerformer;

   public static String BASENAME_Gateway;

   public static String BASENAME_TransitionConnection;

   public static String TXT_SimplifyDiagram;

   public static String TXT_UpdateDiagram;

   public static String TXT_CloseDiagram;

   public static String TXT_COLLABORATIVE_MODELING_FEED;

   public static String TXT_CONTAINS_EXTERNAL_REF;

   public static String TXT_NewOrganizaton;

   public static String TXT_NewDiagram;

   public static String DIAGRAM_NAME_Diagram;

   public static String TXT_Activity;

   public static String BASENAME_Activity;

   public static String TXT_DeleteAll;

   public static String MSG_ConfirmDelete;

   public static String MSG_SureToDeleteElement;

   public static String MSG_UpdateDiagram;

   public static String MSG_OpenDiagramAction1_Diagram;

   public static String MSG_OpenDiagramAction2_isInconsistent;

   public static String MSG_OpenDiagramAction3_WantToUpdate;

   public static String TXT_ShowDiagram;

   public static String TXT_ShowDefaultDiagram;

   public static String TXT_ShowSubprocessDiagram;

   public static String TXT_STRUCTURED_DATA;

   public static String TXT_STRUCTURED_TYPE;

   public static String TXT_SUBSTITUTE_ALL_OCCURRENCES_OF_THE_VARIABLE_TO_BE_REMOVED;

   public static String TXT_NewRole;

   public static String BASENAME_Role;

   public static String EX_FailedObtainingParentToll;

   public static String EX_MissingNodeModelContainer;

   public static String EX_MustNotInsertBendpoint;

   public static String EX_MustNotMoveBendpoint;

   public static String EX_MustNotRemoveBendpoint;

   public static String EX_TargetNeitherNodeNorConnection;

   public static String EX_TargetNotContained;

   public static String EX_isNeitherNodeNorConnectionSymbol;

   public static String EX_MissingNodeSymbolContainer;

   public static String EX_MissingSymbolContainer;

   public static String EX_RUNTIME_FoundMultipleFeatures;

   public static String BASENAME_Transition;

   public static String EX_CLASSCAST_ExpectionCompareRectangles;

   public static String EXC_ALREADY_LOCKED_BY;

   public static String EXC_CANNOT_FIND_ASSOCIATED_WORKFLOW_MD_EDITOR;

   public static String EXC_CANNOT_NOT_DELETE_LOCK_FOLDER;

   public static String EXC_CANNOT_PARSE_DTD_FILE;

   public static String EXC_CANNOT_PARSE_WSDL_FILE;

   public static String EXC_CANNOT_PARSE_XSD_FILE;

   public static String EXC_CANNOT_RETRIEVE_TYPE_PARAMETERS;

   public static String EXC_CANNOT_SEARCH_AVAILABLE_TYPES;

   public static String EXC_ERROR_INITIALIZING_REPOSITORY_PROVIDER_FOR;

   public static String EXC_JOB_FAILED;

   public static String EXC_ORGANIZATION;

   public static String EXC_PROJECT_IS_NOT_SHARED;

   public static String EXC_UNABLE_TO_FIND_LOCK_FOLDER;

   public static String EXC_UNABLE_TO_FIND_XPDL_EXPORT_STYLESHEET;

   public static String EXC_UNABLE_TO_READ_REMONTE_MD;

   public static String EXC_UNSUPPORTED_REPOSITORY_PROVIDER_NO_EXTENSION_FOUND_FOR;

   public static String EXC_UPDATE_NEEDED_FOR;

   public static String EXC_USER_CANCELED_COMMIT;

   public static String EXC_USER_CANCELLED;

   public static String LB_GatewayOfActivity;

   public static String LB_CMD_Activity;

   public static String LB_CMD_Data;

   public static String LB_CMD_Trigger;

   public static String LB_CMD_Application;

   public static String LB_CMD_Organization;

   public static String LB_CMD_Role;

   public static String LB_CMD_ConditionalPerformer;

   public static String LB_CMD_P1_OfSource;

   public static String LB_CMD_TransitionOfSource;

   public static String LB_CMD_P2_Target;

   public static String LB_CMD_GatewayConnectionOf;

   public static String LB_CMD_P1_source;

   public static String LB_CMD_P2_target;

   public static String CONN_NAME_Triggers;

   public static String CONN_NAME_DataMapping;

   public static String CONN_NAME_ExecutedBy;

   public static String CONN_NAME_Performs;

   public static String CONN_NAME_TeamLead;

   public static String CONN_NAME_WorksFor;

   public static String CONN_NAME_PartOf;

   public static String CATEGORY_BASE;

   public static String CATEGORY_DIAGRAM;

   public static String CATEGORY_ALL;

   public static String DISPL_NAME_ElementOID;

   public static String DISPL_NAME_ID;

   public static String DISPL_NAME_Name;

   public static String DISPL_NAME_JoinMode;

   public static String CATEGEGORY_FlowControl;

   public static String DISPL_NAME_SplitMode;

   public static String DISPL_NAME_LoopMode;

   public static String DISPL_NAME_LoopCondition;

   public static String CATEGORY_FlowControl;

   public static String DISPL_NAME_Implementation;

   public static String DISPL_NAME_Participant;

   public static String DISPL_NAME_Text;

   public static String DISPL_NAME_SymbolElementOID;

   public static String DISPL_NAME_Width;

   public static String DISPL_NAME_Height;

   public static String DISPL_NAME_Routing;

   public static String DATA_LABEL;

   public static String COL_NAME_Name;

   public static String COL_NAME_Type;

   public static String TXT_MENU_MANAGER_Implementation;

   public static String ID_Activity;

   public static String ID_ConditionalPerformer;

   public static String ID_Modeler;

   public static String ID_Organization;

   public static String ID_ProcessDefinition;

   public static String ID_Role;

   public static String ID_TransitionConnection;

   public static String LB_ACTION_CreateActivityGraph;

   public static String MSG_ACTION_CreateActivityGraph;

   public static String MSG_AN_EXTERNAL_REF_TO_THE_MD_REF_BY_THE_SEL_FILE_CONNECTION_ALREADY_EXISTS;

   public static String WorkflowModelEditorContextMenuProvider_DIAGRAM_MODE;

   public static String WorkflowModelEditorContextMenuProvider_ORIENTATION;

   public static String WorkflowModelEditorContextMenuProvider_Routing;

   public static String LB_EXTMENU_New;

   public static String Btn_Browse;

   public static String WorkflowModelEditorContextMenuProvider_TXT_MENU_MANAGER_SetType;

   public static String WorkflowModelEditorContextMenuProvider_TXT_MENU_MANAGER_Subprocess;

   public static String CreateSubprocessAction_LB_NewSubprocess;

   public static String WorkflowModelEditorPaletteFactory_ConnectToolDescription;

   public static String WorkflowModelEditorPaletteFactory_ConnectToolLabel;

   public static String WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateApplication;

   public static String WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateData;

   public static String WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Application;

   public static String WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Data;

   public static String LB_Browse;

   public static String LB_Changed;

   public static String MSG_ResourceChanged;

   public static String LB_PropertyType;

   public static String LB_NewLinkType;

   public static String JOB_TypeSearch;

   public static String PageTitle_ProcessInterface;

   public static String TABLE_Column_Name;

   public static String TABLE_DESCRIPTION;

   public static String TABLE_NAME;

   public static String TABLE_SERVICE;

   public static String TABLE_SUBMITTING_ORGNAZATION;

   public static String LB_ToolTip_Error;

   public static String LB_ToolTip_Warning;

   public static String ToolTip_Outline;

   public static String ToolTip_Graphical;

   public static String MSG_FileExists;

   public static String LB_ShowInDiagram;

   public static String LB_ShowInOutline;

   public static String LB_CopySymbol;

   public static String LB_PasteSymbol;

   public static String LB_CreateSubprocess;

   public static String LB_SearchAction;

   public static String TOOL_TIP_TXT_Search;

   public static String LB_CreateOrganizationHierarchy;

   public static String LB_Activity;

   public static String LB_CreateActivity;

   public static String LB_UpgradeModel;

   public static String MSG_UpgradeModel;

   public static String LB_UpgradeData;

   public static String TXT_ForwardAction;

   public static String TITLE_ChangeToManualActivity;

   public static String MSG_ChangeImplTypeActivity;

   public static String LB_RememberDecision;

   public static String LB_SnapToGrid;

   public static String TITLE_SnapToGrid;

   public static String LB_LastSelectedSymbol;

   public static String LB_AllMovedSymbols;

   public static String LB_UpdateProcessDiagram;

   public static String LB_EDITOR_References;

   public static String TOOLTIP_JoinGateway;

   public static String TOOLTIP_SplitGateway;

   public static String CONNECTION_LABEL;

   public static String STRUCTURED_DATA_LABEL;

   public static String TYPE_DECLARATION_LABEL;

   public static String BIND_PropertyPagesDataMappingConnection;

   public static String LB_CleanupModel;

   public static String LB_CommitModelElement;

   public static String LB_ShareModel;

   public static String LB_UnshareModel;

   public static String LB_UpdateModel;

   public static String LB_Refresh;

   public static String LB_UnusedModelElements;

   public static String LB_LOCK;

   public static String LB_LOCK_ALL;

   public static String LB_UNLOCK_ALL;

   public static String MSG_UNLOCK_ALL;

   public static String LB_REVERT_CHANGES;

   public static String MSG_LOCKED_BY_OTHER;

   public static String MSG_NO_LOCKING_NOT_SHARED;

   public static String MSG_NOT_LOCKABLE;

   public static String MSG_SAVE_MODEL_NEEDED;

   public static String MSG_LOCK_NEEDED;

   public static String MSG_FLUSH_COMMAND_STACK;

   public static String MSG_MODEL_ALREADY_SHARED;

   public static String MSG_NO_CHANGES;

   public static String MSG_NO_LOCKS;

   public static String MSG_NO_COMMIT_TEXT;

   public static String LB_REFRESH_STATUS_JOB;

   public static String LB_CREATE_CACHE_JOB;

   public static String LB_VCS_FEED_VIEW_REVISION;

   public static String LB_VCS_FEED_VIEW_DATE;

   public static String LB_VCS_FEED_VIEW_AUTHOR;

   public static String LB_VCS_FEED_VIEW_EVENT;

   public static String LB_VCS_FEED_VIEW_COMMENT;

   public static String LB_DeployModel;

   public static String ActivityControllingPropertyPage_IncludeLB;

   public static String ActivityControllingPropertyPage_NotInclude_LB;

   public static String ActivityControllingPropertyPage_ResourcePerformanceCalculation_LB;

   public static String LanePropertyPage_SetParticipantInformation;

   public static String UncheckButton;

   public static String DataTypeHintPage_ComplexLabel;

   public static String DataTypeHintPage_GroupLabel;

   public static String DataTypeHintPage_NumericLabel;

   public static String DataTypeHintPage_TextLabel;

   public static String LB_Measure;

   public static String LB_FORMBUILDER_ActionType;

   public static String LB_FORMBUILDER_LoopCondition;

   public static String LB_TITLE_Join;

   public static String LB_TITLE_Loop;

   public static String LB_TITLE_Split;

   public static String LB_TargetMeasureQuantity;

   public static String LB_Difficulty;

   public static String LB_TargetProcessingTime;

   public static String LB_TargetExecutionTime;

   public static String LB_TargetIdleTime;

   public static String LB_TargetWaitingTime;

   public static String LB_TargetQueueDepth;

   public static String LB_TargetCostPerExecution;

   public static String LB_TargetCostPerSecond;

   public static String LB_OverdueThreshold;

   public static String BASENAME_InDataMapping;

   public static String BASENAME_OutDataMapping;

   public static String CHECKBOX_AllowsAbortByParticipant;

   public static String CHECKBOX_HibernateInitially;

   public static String PARTICIPANT_LABEL;

   public static String FORMBUILDER_LB_Participants;

   public static String LABEL_SyncShared;

   public static String LABEL_AsyncSeparate;

   public static String FORMBUILDER_LB_ExecutionType;

   public static String FORMBUILDER_LB_Subprocess;

   public static String TXT_IMPORT;

   public static String TXT_IMPORT_MD_ELEMENT;

   public static String TXT_NAME;

   public static String TXT_PRIMITIVE_DATA;

   public static String TXT_DELETE;

   public static String TXT_DELETE_VARIABLE;

   public static String TXT_DIAGRAM;

   public static String TXT_DOCUMENT;

   public static String TXT_DOCUMENT_LIST;

   public static String TXT_EXTERNAL_MD_REFERENCES_PROPERTY_PAGE;

   public static String TXT_GENERATE_CLASSES;

   public static String ControllingAttribute_LB_CostCenter;

   public static String ControllingAttribute_LB_ActualCostPerSecond;

   public static String INTERACTIVE_LABEL;

   public static String COL_NAME_Context;

   public static String COL_NAME_Mode;

   public static String LB_QueueJNDI;

   public static String LB_MessageType;

   public static String LB_IncludeOIDHeaderFields;

   public static String COL_TXT_TRANSITION_CONDITION;

   public static String COL_TXT_DESCRIPTION;

   public static String B_Browse;

   public static String LB_HomeInterfaceJNDIPath;

   public static String LB_CompletionMethod;

   public static String LB_CreationMethod;

   public static String B_Load;

   public static String LB_Service;

   public static String LB_Operation;

   public static String LB_Style;

   public static String LB_Use;

   public static String LB_Implementation;

   public static String LB_Mechanism;

   public static String LB_Variant;

   public static String COL_TXT_XML_TEXT_NODE;

   public static String LB_User;

   public static String LB_Organization_Role;

   public static String LB_UserGroup;

   public static String LB_OrganizationRoleOrUserGroup;

   public static String LB_DataPath;

   public static String CONSUME;

   public static String LOG;

   public static String AUTO_BIND;

   public static String LB_Data;

   public static String LB_MetaType;

   public static String LB_AccessPoint;

   public static String LB_AccessPointPath;

   public static String COL_NAME_DefaultValue;

   public static String LB_Direction;

   public static String LB_Descriptor;

   public static String B_Add;

   public static String B_AddEventAction;

   public static String B_AddBindAction;

   public static String B_AddUnbindAction;

   public static String B_Delete;

   public static String ACT_UnbindAction;

   public static String ACT_DESC_UnbindAction;

   public static String ACT_BindAction;

   public static String ACT_DESC_BindAction;

   public static String ACT_EventAction;

   public static String ACT_DESC_EventAction;

   public static String COL_NAME_Description;

   public static String DESC_autobound;

   public static String DESC_logHandler;

   public static String DESC_consumedOnMatch;

   public static String B_MoveUp;

   public static String B_MoveDown;

   public static String COL_NAME_Id;

   public static String COL_NAME_Condition;

   public static String COL_NAME_Fork;

   public static String TA_Description;

   public static String LB_ElementOID;

   public static String LB_ShowName;

   public static String LB_ShowRole;

   public static String LB_LineColor;

   public static String LB_LineType;

   public static String LB_StartSymbol;

   public static String LB_EndSymbol;

   public static String LB_OID;

   public static String LB_ValidFrom;

   public static String LB_CostCenter;

   public static String LB_CostDriver;

   public static String LB_TargetCostDriverQuantity;

   public static String MEASURE_UNIT_EUR;

   public static String BASENAME_DataPath;

   public static String BASENAME_FormalParameter;

   public static String LB_WorkingWeeksPerYear;

   public static String LB_ActualCostPerMinute;

   public static String LB_TargetWorktimePerDay;

   public static String LB_TargetWorktimePerWeek;

   public static String CHECKBOX_ForkOnTraversal;

   public static String LB_ConditionType;

   public static String LB_ConditionExpression;

   public static String LB_Cardinality;

   public static String ID_DataPath;

   public static String ID_FormalParameter;

   public static String COL_NAME_Activity;

   public static String LB_Password;

   public static String LINK_TYPE_LB_Activity;

   public static String LINK_TYPE_LB_Data;

   public static String TLINK_TYPE_LB_Role;

   public static String LINK_TYPE_LB_Process;

   public static String LINK_TYPE_LB_Transition;

   public static String LINK_TYPE_LB_Organization;

   public static String LINK_TYPE_LB_Name;

   public static String LINK_TYPE_LB_SourceType;

   public static String LINK_TYPE_LB_SourceRole;

   public static String LINK_TYPE_LB_SourceCardinality;

   public static String LINK_TYPE_LB_TargetType;

   public static String LINK_TYPE_LB_TargetRole;

   public static String LINK_TYPE_LB_TargetCardinality;

   public static String LB_PageUpdate;

   public static String BTN_AutoId;

   public static String LB_UserRealm;

   public static String LB_Oid_Id;

   public static String LB_User_Oid_Id;

   public static String LB_Department_Oid;

   public static String LINK_TYPE_LB_Participant;

   public static String LB_ACTIVITY_IS_AUXILIARY;

   public static String LB_PROCESS_IS_AUXILIARY;

   public static String LB_APPLICATION_RETRY_NUMBER;

   public static String LB_APPLICATION_RETRY_TIME;

   public static String LB_APPLICATION_RETRY_ENABLE;

   public static String COMBOBOX_Implement_Process_Interface;

   public static String COMBOBOX_Provide_Process_Interface;

   public static String COMBOBOX_No_Process_Interface;

   public static String CHECKBOX_Visibility;

   public static String POPUP_FILECONNECTION_ADD_EXTERNAL_MODEL_REFERENCE;

   public static String LABEL_EXTERNAL_MODEL_REFERENCES;

   public static String LB_PROCESSINTERFACE_FORMAL_PARAMETERS;

   public static String LB_PROCESSINTERFACE_REMOTE_INVOCATION;

   public static String ComparableModelElementNode_noName;

   public static String ComparableModelElementNode_root;

   public static String RANDOM_USER;

   public static String PARTICIPANT;

   public static String CURRENT_USER;

   public static String DEFAULT_PERFORMER;

   public static String LB_SPI_Type;

   public static String LB_Class;

   public static String LB_ClassName;

   public static String COL_NAME_Parts;

   public static String CONSTANT_RADIO;

   public static String COL_NAME_XMLType;

   public static String COL_NAME_Mapped;

   public static String COL_NAME_JavaType;

   public static String COL_NAME_Location;

   public static String COL_PROCESS;

   public static String COL_VALUE;

   public static String LB_Location;

   public static String LB_Template;

   public static String LB_JNDIPath;

   public static String LB_Protocol;

   public static String LB_Parameter;

   public static String LB_Server;

   public static String LB_XMLName;

   public static String LB_XOR;

   public static String LB_JSPURL;

   public static String DATA_PATH;

   public static String DATA_RADIO;

   public static String DATA;

   public static String DAYS;

   public static String EMAIL;

   public static String RUNTIME_MESSAGE;

   public static String PREDEFINED_MESSAGE;

   public static String PROVIDE_ERROR_DUPLICATE_VARIABLE;

   public static String PROVIDE_ERROR_IS_NOT_A_VALID_VARIABLE_NAME;

   public static String PROVIDE_ERROR_IS_NOT_A_VALID_VARIABLE_NAME_TYPE;

   public static String PROVIDE_ERROR_PROVIDE_A_VALID_NAME_FOR_NEW_VARIABLE;

   public static String ADDRESS;

   public static String MODEL_PARTICIPANT;

   public static String MODEL_ProcessInterface_InvalidForExternalInvocation;

   public static String CONTENT;

   public static String SUBJECT;

   public static String DEFAULT_SUBJECT;

   public static String ScopeLabel;

   public static String SEND;

   public static String SECS;

   public static String SEEN;

   public static String LB_AccessPath;

   public static String LB_Addressing;

   public static String LB_AND;

   public static String TRIGGER;

   public static String LB_ConnectionFactoryJNDI;

   public static String LB_HomeInterfaceClass;

   public static String LB_VAR_UsernamePassword;

   public static String LB_SPI_MessageProviderTypeClass;

   public static String LB_MessageAcceptor;

   public static String LB_SchemaType;

   public static String LB_Periodical;

   public static String LB_Namespace;

   public static String LB_PartName;

   public static String LB_Exception;

   public static String LB_Constructor;

   public static String LB_ElementName;

   public static String LB_Periodicity;

   public static String LB_DefaultValue;

   public static String LB_SPI_LocalBinding;

   public static String LB_HomeInterface;

   public static String LB_BodyPredicate;

   public static String LB_MailboxAction;

   public static String LB_StopTimestamp;

   public static String LB_IMPL_GenericResource;

   public static String NAME_PROVIDER_DefaultProvider;

   public static String BASENAME_Content;

   public static String BASENAME_InAccessPoint;

   public static String BASENAME_OutAccessPoint;

   public static String LB_Id;

   public static String NAME_ACCEPTOR_Default;

   public static String NAME_ACCESSPOINT_Exception;

   public static String NAME_ACCESSPOINT_TimeStamp;

   public static String NAME_ACCESSPOINT_Content;

   public static String LB_RemoteInterface;

   public static String LB_RemoteInterfaceClass;

   public static String LB_MECH_WSSecurity;

   public static String LB_PrimaryKeyClass;

   public static String CHECKBOX_LocalBinding;

   public static String CHECKBOX_Required;

   public static String LB_SenderPredicates;

   public static String LB_SubjectPredicate;

   public static String LB_CandidateMails;

   public static String LB_IMPL_CarnotSpecific;

   public static String LB_MECH_HttpBasicAuthorization;

   public static String LB_TypeDeclarationURL;

   public static String LB_VAR_UsernamePasswordDigest;

   public static String AP_WSAddressingEndpointReference;

   public static String AP_Authentication;

   public static String B_Test;

   public static String ERR_CannotLoadWSDLFile;

   public static String CHECKBOX_IncludeWSAddressingEPR;

   public static String LB_Authentication;

   public static String LB_StartTimestamp;

   public static String CONFIG_Parts;

   public static String ELEMENT_Input;

   public static String ELEMENT_Output;

   public static String ELEMENT_Faults;

   public static String ELEMENT_TypeMappings;

   public static String ELEMENT_XMLTemplates;

   public static String B_RADIO_CompletedEvent;

   public static String B_RADIO_application;

   public static String B_RADIO_suspended;

   public static String B_RADIO_hibernated;

   public static String B_RADIO_interrupted;

   public static String B_RADIO_completed;

   public static String B_RADIO_StateChangeEvent;

   public static String SOURCE_STATE;

   public static String INTERRUPTED;

   public static String ABORTED;

   public static String COMPLETED;

   public static String ACTIVE;

   public static String TARGET_STATE;

   public static String GROUP_TargetState;

   public static String B_RADIO_created;

   public static String B_RADIO_aborted;

   public static String BOX_COPY_ALL_DATA;

   public static String BOX_GENERATE_AUTOMATIC_ID;

   public static String BOX_PROCESS_IMPLEMENTS;

   public static String BOX_SUBPROCESS_IS_RESOLVED_AT_RUNTIME;

   public static String BOX_TXT_APPLY_TO_ALL_INVALID_ID_S;

   public static String BOX_TXT_APPLY_TO_ALL_MISSING_ID_S;

   public static String BOX_TXT_APPLY_TO_ALL_SIMILAR_ISSUES_IN_THIS_MD;

   public static String BOX_TXT_PREFIX_ID_WITH;

   public static String BOX_TXT_REPLACE_INVALID_CHARACTERS_WITH;

   public static String GROUP_SourceState;

   public static String GROUP_ServerSettings;

   public static String GROUP_MailSettings;

   public static String USE;

   public static String MINS;

   public static String HOURS;

   public static String MONTHS;

   public static String YEARS;

   public static String SHORT_HOURS;

   public static String SHORT_MINUTES;

   public static String SHORT_SECONDS;

   public static String PERIOD;

   public static String MSG_TestURL;

   public static String MSG_THIS_OPERATION_MAY_RESULT_IN_DANGLING_REFE;

   public static String MSG_THIS_OPERATION_WILL_REMOVE_ALL_REFERENCES_TO_NULL_FROM_MODEL;

   public static String MSG_TXT_ID_CONTAINS_INVALID_CHARACTERS;

   public static String MSG_TXT_ID_IS_NOT_VALID;

   public static String MSG_SuccessfullyConnectedTo;

   public static String MSG_ConnectionTo;

   public static String MSG_Failed;

   public static String TXT_LINK;

   public static String TXT_OLD_MD_FORMAT;

   public static String TXT_REFERENCE_EXISTS;

   public static String TXT_REFRESH;

   public static String TXT_REMOVE_PERFORMER_NULL_FROM_ACTIVITY_ONE;

   public static String TXT_REPLACE_PERFORMER_NULL_WITH_PERFORMER_ONE_FOR_ACTIVITY_TWO;

   public static String ERR_CannotRetrieveSchemaElement;

   public static String ERROR_DIA_CANNOT_START_BUNDLE;

   public static String ERROR_DIA_CONVERSION_FAILED;

   public static String ERROR_DIA_ERROR;

   public static String ERROR_DIA_EXC;

   public static String ERROR_MSG_DATA_PATH_MUST_RESOLVE_TO_A_STRING_VALUE;

   public static String ERROR_MSG_SELECTED_DATA;

   public static String ERROR_MSG_VALUE_MUST_BE_NUMERIC_BETWEEN_1_AND_10;

   public static String ERROR_MSG_VALUE_MUST_BE_NUMERIC_BETWEEN_1_AND_60;

   public static String ERROR_UNSUPPORTED_MD_VERSION;

   public static String SIMPLE_DATE_FORMAT;

   public static String REMOVE;

   public static String LEAVE;

   public static String NEW_MAILS;

   public static String NOT_SEEN;

   public static String EXISTING;

   public static String PARAMETER_MAPPING_TABLE_LABEL;

   public static String TXT_undefined;

   public static String HIBERNATED;

   public static String SUSPENDED;

   public static String INTENDED_STATE_CHANGE;

   public static String PlainJavaPropertyPage_LB_Plain_Java;

   public static String PlainJavaPropertyPage_LB_Method;

   public static String PlainJavaPropertyPage_LB_Constructor;

   public static String SessionBeanPropertyPage_Remote_IF;

   public static String SessionBeanPropertyPage_Home_IF;

   public static String SessionBeanPropertyPage_Completion_Method;

   public static String SessionBeanPropertyPage_Create_Method;

   public static String WSMappingPropertyPage_Class;

   public static String ExceptionPropertyPage_Class;

   public static String JfcPropertyPage_Class;

   public static String JfcPropertyPage_Method;

   public static String EntityBeanPropertyPage_RemoteIF;

   public static String EntityBeanPropertyPage_HomeIF;

   public static String EntityBeanPropertyPage_PK;

   public static String SerializablePropertyPage_AutoInstantiateLabel;

   public static String SerializablePropertyPage_CannotFetchConstructorsErrorMessage;

   public static String SerializablePropertyPage_Class;

   public static String SerializablePropertyPage_ParameterColumnLabel;

   public static String SerializablePropertyPage_ParametersTableLabel;

   public static String SerializablePropertyPage_TypeColumnLabel;

   public static String SEND_TO_WORKLIST;

   public static String LB_MailAction_UserPerformer;

   public static String LB_BeanId;

   public static String MSG_CannotCreateImageBuffer;

   public static String MSG_CannotDrawDiagram;

   public static String MSG_EncoderCreated;

   public static String InputContainer_GlobalElements_All;

   public static String InputContainer_GlobalElements_All_Applications;

   public static String InputContainer_GlobalElements_All_Data;

   public static String InputContainer_GlobalElements_All_Participants;

   public static String NameIdDialog_ID;

   public static String NameIdDialog_Name;

   public static String NameIdDialog_Title_DuplicateID;

   public static String NameIdDialog_Title_DuplicateName;

   public static String NameIdDialog_Title_DuplicateNameID;

   public static String NameIdDialog_Warning_IdEmpty;

   public static String NameIdDialog_Warning_IdExists;

   public static String NameIdDialog_Warning_NameEmpty;

   public static String NameIdDialog_Warning_NameExists;

   public static String ReferenceValueDialog_Info;

   public static String ReferenceValueDialog_Title;

   public static String FileCopyMessage_Title;

   public static String FileUpdateMessage_Title;

   public static String FileCopyMessage_Text;

   public static String BOX_ORGANIZATION_SUPPORTS_DEPARTMENT;

   public static String MSG_REMOVE_PERFORMER_FROM_MANUAL_TRIGGER;

   public static String MSG_RPLACE_PERFORMER_WITH_PERFORMER_FOR_TRIGGER;

   public static String MSG_INVALID_JAXP_SETUP;

   public static String MSG_ANOTHER_MODEL_HIERARCHY_WITH_ID_ALREADY_EXISTS;

   public static String LBL_SELECT;

   public static String NAME_ProcessPriority;

   public static String DESC_ProcessPriority;

   public static String LBL_Incoming_Transitions;

   public static String LBL_Outgoing_Transitions;

   public static String CONFIGURATION_VARIABLE_NEW;

   public static String LBL_CATEGORY;

   public static String LBL_DATA_TYPE;

   public static String LBL_RootHierarchy;

   public static String LBL_SubHierarchy;

   public static String LBL_Participant;

   public static String QUALITY_CONTROL_LABEL;

   public static String QUALITY_CONTROL_CODES_LABEL;

   public static String QUALITY_CONTROL_ACTIVITY;

   public static String QUALITY_CONTROL_PARTICIPANT;

   public static String QUALITY_CONTROL_PROBABILITY;

   public static String QUALITY_CONTROL_FORMULA;

   public static String QUALITY_CONTROL_PROBABILITY_VALIDATION;

   public static String QUALITY_ASSURANCE_PERFORMER_VALIDATION;

   public static String QUALITY_CONTROL_CODE;

   public static String QUALITY_CONTROL_CODE_AVAILABLE;

   public static String QUALITY_CONTROL_DESCRIPTION;

   public static String QUALITY_CONTROL_VALIDATION_CODE;

   public static String QUALITY_CONTROL_VALIDATION_DUPLICATE;

   public static String QUALITY_CONTROL_VALIDATION_EMPTY;

   public static String CRITICALITY_TARGET_EXECUTION_TIME;

   public static String CRITICALITY_INITIAL_CRITICALITY_LOW;

   public static String CRITICALITY_INITIAL_CRITICALITY_MEDIUM;

   public static String CRITICALITY_INITIAL_CRITICALITY_HIGH;

   public static String CRITICALITY_MULTIPLE_TARGET_EXECUTION_LOW;

   public static String CRITICALITY_MULTIPLE_TARGET_EXECUTION_MEDIUM;

   public static String CRITICALITY_MULTIPLE_TARGET_EXECUTION_HIGH;

   public static String LB_OPEN_REFERENCED_MODEL;

   public static String MSG_ProviderFileNotExists;

   public static String LBL_AUDITTRAIL_PERSISTENCE;

   public static String LBL_SUPPORTS_RELOCATION;

   public static String LBL_IS_RELOCATION_TARGET;

   public static String RELOCATION_TRANSITION;

   public static String WebServiceResource_ErrorMessage;

   public static String WebServicePropertyPage_WsdlUrlLabel;

   public static String WebServicePropertyPage_PortLabel;

   public static String WebServicePropertyPage_StyleLabel;

   public static String WebServicePropertyPage_UseLabel;

   public static String WebServicePropertyPage_ProtocolLabel;

   public static String WebServicePropertyPage_ImplementationLabel;

   public static String WebServicePropertyPage_GenericResourceLabel;

   public static String WebServicePropertyPage_InfinitySpecificLabel;

   public static String WebServicePropertyPage_HttpBasicAuthorizationLabel;

   public static String WebServicePropertyPage_UsernamePasswordLabel;

   public static String WebServicePropertyPage_UsernamePasswordDigestLabel;

   public static String WebServicePropertyPage_AuthenticationAccessPointName;

   public static String WebServicePropertyPage_IncludeAddressingCheckBoxLabel;

   public static String WebServicePropertyPage_RequiredCheckBoxLabel;

   public static String WebServicePropertyPage_WsSecurityLabel;

   public static String WebServicePropertyPage_LoadButtonLabel;

   public static String WebServicePropertyPage_OperationLabel;

   public static String WebServicePropertyPage_MechanismLabel;

   public static String WebServicePropertyPage_EndpointLabel;

   public static String WebServicePropertyPage_LoadErrorMessage;

   public static String WebServicePropertyPage_AddressingLabel;

   public static String WebServicePropertyPage_AuthenticationLabel;

   public static String WebServicePropertyPage_ErrorDialogTitle;

   public static String WebServicePropertyPage_BindingLabel;

   public static String WebServicePropertyPage_EndpointAddressAccessPointName;

   public static String WebServicePropertyPage_WsAddressingEndpointReferenceAccessPointName;

   public static String WebServicePropertyPage_ServiceLabel;

   public static String WebServicePropertyPage_VariantLabel;

   public static String WebServicePropertyPage_UnknownPartValidationError;

   public static String WebServicePropertyPage_TypeNotFoundForInputPart;

   public static String WebServicePropertyPage_TypeNotFoundForOutputPart;

   public static String WebServicePropertyPage_WSDL;

   public static String WebServicePropertyPage_Generate_Classes;

   public static String WebServicePropertyPage_Retrieving_WSDL;

   public static String LB_Convert_Gateways;

   public static String LBL_Formula;

   public static String MSG_INVALID_ACTIVITY_NETWORK;

   public static String LBL_Properties;
}