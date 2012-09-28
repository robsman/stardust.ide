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
package org.eclipse.stardust.modeling.validation;

import org.eclipse.osgi.util.NLS;

public class Validation_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.validation.validation-messages"; //$NON-NLS-1$

   private Validation_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Validation_Messages.class);
   }

   public static String DefaultModelValidator_MissingAdministrator;

   public static String DelegateActivity_InvalidParticipantId;

   public static String DelegateActivity_MissingParticipantId;

   public static String ERR_ACTIVITY_MultipleTransitions;

   public static String ERR_ACTIVITY_SubProcessMode;

   public static String ERR_ACTIVITYNoApplication;

   public static String MODEL_EmptyId;

   public static String MODEL_EmptyModelName;

   public static String MODEL_InvalidValidFrom;

   public static String MODEL_InvalidValidTo;

   public static String MODEL_TooLongId;

   public static String MODEL_ValidFromAfterValidTo;

   public static String MODEL_ConfigurationVariable_NeverUsed;

   public static String MODEL_ConfigurationVariable_Invalid;

   public static String MODEL_ConfigurationVariable_NoDefaultValue;

   public static String MODEL_ConfigurationVariable_NotAllowed;

   public static String MODEL_ConfigurationVariable_NAME;

   public static String MODEL_ConfigurationVariable_ID;

   public static String MODEL_ConfigurationVariable_TypeDec;

   public static String MODEL_ConfigurationVariable_Description;

   public static String MODEL_Connection_NoConnection;

   public static String MODEL_ReferencedModel_NoModel;

   public static String MODEL_ExternalPackage_IDConflict;

   public static String MODEL_ReferencedType_NotFound;

   public static String MODEL_ReferencedType_NotVisible;

   public static String MODEL_ProcessInterface_NotValid;

   public static String MODEL_ProcessInterface_InvalidForExternalInvocation;

   public static String MODEL_ProcessInterface_ParameterMissing;

   public static String MODEL_ProcessInterface_ParameterPending;

   public static String MODEL_ProcessInterface_NoMapping;

   public static String MODEL_ProcessInterface_IncompatibleTypes;

   public static String MSG_ClassCanNotBeResolved;

   public static String MSG_ClassNotSerilizable;

   public static String MSG_ConstructorNotVisible;

   public static String MSG_DataHasNoType;

   public static String MSG_EntityBean_InvalidEjbTypeSignature;

   public static String MSG_InvalidProcess;

   public static String MSG_MethodNotVisible;

   public static String MSG_NoDataMappingSymbols;

   public static String MSG_NoExecutedBySymbols;

   public static String MSG_NoPerformsSymbols;

   public static String MSG_NoReceiverType;

   public static String MSG_NoReceivingParticipant;

   public static String MSG_NoEmailAddress;

   public static String MSG_NoAccessPointSpecified;

   public static String MSG_NoProcessSelected;

   public static String MSG_NoDataSpecified;

   public static String MSG_JavaClassNotSpecified;

   public static String MSG_CompletionMethodNotSpecified;

   public static String MSG_CantFindMethodInClass;

   public static String MSG_ConstructorNotSpecified;

   public static String MSG_CouldntFindConstructor;

   public static String MSG_NoRefersToSymbols;

   public static String MSG_NoTransitionSymbols;

   public static String MSG_NoWorksForSymbols;

   public static String MSG_OverriddenLaneParticipant;

   public static String MSG_ParameterHasNoId;

   public static String MSG_NoLocationForParameter;

   public static String MSG_NoValidTypeForParameter;

   public static String MSG_DuplicateIdUsed;

   public static String MSG_NoTypeMappingDefined;

   public static String MSG_PROCDEF_DisconnectedActivityGraph;

   public static String MSG_SessionBean_InvalidEjbTypeSignature;

   public static String MSG_XMLTypeHasInvalidMapping;

   public static String MSG_TemplateIsInvalid;

   public static String MSG_WSDL_URLIsInvalid;

   public static String MSG_PropertyNotSet;

   public static String MSG_InterfaceNotSpecified;

   public static String MSG_MethodNotSpecified;

   public static String MSG_CouldntFindMethodInClass;

   public static String MSG_TargetStateIsSameWithSource;

   public static String MSG_IsNotException;

   public static String MSG_InvalidDataMapping;

   public static String MSG_InvalidDataPath;

   public static String MSG_MissingDataPath;

   public static String MSG_InvalidDataSpecified;

   public static String MSG_TimerConditionHasToHave;

   public static String MSG_NoPeriodSpecified;

   public static String MSG_JFC_UnspecifiedClass;

   public static String MSG_JFC_CouldntFindClass;

   public static String MSG_JFC_UnspecifiedCompletionMethod;

   public static String MSG_JFC_CouldntFindCompletionMethod;

   public static String MSG_JSP_UndefinedHtmlPath;

   public static String MSG_PRIMITIVE_UnspecifiedType;

   public static String MSG_TIMERTRIGGER_UnspecifiedStart;

   public static String MSG_TIMERTRIGGER_UnspecifiedUsername;

   public static String MSG_TIMERTRIGGER_UnspecifiedPassword;

   public static String MSG_TIMERTRIGGER_UnspecifiedServerName;

   public static String MSG_TIMERTRIGGER_UnspecifiedProtocol;

   public static String MSG_JMSTRIGGER_UnspecifiedMessageType;

   public static String MSG_JMSTRIGGER_ParameterNoId;

   public static String MSG_JMSTRIGGER_NoLocationSpecified;

   public static String MSG_JMSTRIGGER_NoValidTypeForParameter;

   public static String MSG_Trigger_UnspecifiedParticipant;

   public static String MSG_Scantrigger_UnspecifiedParticipant;

   public static String MSG_Scantrigger_NoDocumentDataSpecified;

   public static String MSG_Trigger_InvalidParticipant;

   public static String MSG_DuplicateDataId;

   public static String MSG_DuplicateProcessDefinitionId;

   public static String MSG_NoStartActivity;

   public static String MSG_MultipleSartActivities;

   public static String MSG_NoActivity;

   public static String MSG_TRIGGER_DuplicateId;

   public static String MSG_TRIGGER_NoTypeSet;

   public static String MSG_NoDataSpecifiedByParameterMapping;

   public static String MSG_NoParameterSpecifiedByParameterMapping;

   public static String MSG_ParameterMappingInvalidTypeConversion;

   public static String ERR_ActionHasNoType;

   public static String MSG_DATAMAPPING_NoActivitySet;

   public static String MSG_DATAMAPPING_NotSupportedByActivity;

   public static String MSG_DATAMAPPING_NoDataSet;

   public static String MSG_DATAMAPPING_NoUniqueId;

   public static String MSG_DATAMAPPING_ContextInvalid;

   public static String MSG_DATAMAPPING_ApplicationInvalid;

   public static String MSG_DATAMAPPING_DataNotOfCorrectType;

   public static String MSG_DATAMAPPING_ApplicationAccessPointWarning;

   public static String MSG_DATAMAPPING_NoApplicationAccessPointSet;

   public static String MSG_DATAMAPPING_NoValidDataPath;

   public static String MSG_DATAMAPPING_NoContextSet;

   public static String MSG_DATAMAPPING_ContextUndefined;

   public static String ERR_ROLE_DuplicateId;

   public static String ERR_ROLE_Administrator;

   public static String ERR_ROLE_WorksForManagerOf;

   public static String ERR_ROLE_WorksFor;

   public static String ERR_ROLE_ManagerOf;

   public static String ERR_Trigger_InvalidScopedParticipant;

   public static String MSG_COND_PERFORMER_DuplicateId;

   public static String MSG_COND_PERFORMER_NoDataSet;

   public static String MSG_COND_PERFORMER_UnsupportedType;

   public static String ERR_DATAPATH_DuplicateId;

   public static String ERR_DATAPATH_NotValidIdentifier;

   public static String ERR_DATAPATH_NoNameSpecified;

   public static String ERR_DATAPATH_NoDataSpecified;

   public static String ERR_MODELER_DuplicateId;

   public static String ERR_ACTIVITY_DuplicateId;

   public static String ERR_ACTIVITY_NoPerformerSet;

   public static String ERR_ACTIVITY_QualityAssurancePerformer;

   public static String ERR_ACTIVITY_PerformerWronglySet;

   public static String ERR_ACTIVITY_NoImplementationProcess;

   public static String ERR_ACTIVITY_MultipleIncomingTransitions;

   public static String ERR_ACTIVITY_MultipleOutgoingTransitions;

   public static String ERR_APPLICATION_DuplicateId;

   public static String MSG_DIAGRAM_DiagramNameIsEmpty;

   public static String MSG_DIAGRAM_NoRequiredGatewaySymbols;

   public static String MSG_DIAGRAM_MultipleOutgoingTransitions;

   public static String MSG_DIAGRAM_InvalidConnectionTarget;

   public static String MSG_DIAGRAM_NoJoinTypeNONE;

   public static String MSG_DIAGRAM_MultipleIncomingTransitions;

   public static String MSG_DIAGRAM_InvalidConnectionSource;

   public static String MSG_DIAGRAM_NoSplitTypeNONE;

   public static String ERR_EVENTHANDLER_DuplicateId;

   public static String ERR_EVENTHANDLER_NoConditionType;

   public static String ERR_ORGANIZATION_DuplicateId;

   public static String ERR_ORGANIZATION_PartOf;

   public static String ERR_ORGANIZATION_InvalidScopeData;

   public static String ERR_ELEMENT_EmptyId;

   public static String ERR_ELEMENT_InvalidId;

   public static String ERR_ELEMENT_IdLength;

   public static String MSG_ELEMENT_EmptyName;

   public static String ERR_NotAJavaDataType;

   public static String ERR_InvalidJavaBeanAccessPathType;

   public static String ERR_JDT_MethodEmpty;

   public static String ERR_ACTIVITY_NoLoopCondition;

   public static String ERR_ACTIVITY_InvalidLoopCondition;

   public static String BridgeObject_assignmentNotCompatible;

public static String CSL_ERR_UNABLE_TO_RESOLVE_CL;

   public static String JavaDataTypeUtils_segmentProcessingError;

   public static String MSG_MissingException;

   public static String Path_InvalidSegment;

   public static String SerializableValidator_NoDefaultConstructorMessage;

   public static String SessionBean_IncompatibleCreatedType;

   public static String SessionBean30Validator_MissingAnnotation;

   public static String TimerTrigger_InvalidPeriodicity;

   public static String TXT_ID;

public static String TXT_NAME;

public static String Validation_MSG_JNDIPathNotSpecified;

   public static String MSG_NoRemoteIF;

   public static String MSG_NoHomeIF;

   public static String MSG_NoPrimaryKey;

   public static String MSG_NoJNDI;

   public static String MSG_NoClass;

   public static String ERR_ACTIONTYPE_DuplicateId;

   public static String MailActionValidator_MSG_NoCorrectEmailAddress;

   public static String EventHandlerValidator_MSG_DEFAULT_VALUE_PERIOD;

   public static String EventHandlerValidator_MSG_NO_PERIOD_VALUE;

   public static String MSG_EmptyTransitionCond;

   public static String MSG_TransitionCondUpdate;

   public static String MSG_InvalidJavaScriptTransitionCondition;

   public static String MSG_InvalidJavaScriptMessageTransformation;

   public static String MSG_MissingExternalClass;

   public static String MSG_InvalidExternalReference;

   public static String MSG_SyntaxInvalidTransitionCond;

   public static String MSG_SemanticInvalidTransitionCond;

   public static String MSG_InvalidSymbol;

   public static String LB_SearchingTypes;

   public static String MSG_UnableToResolve;

   public static String MSG_AmbiguousDeclaration;

   public static String SetDataActionValidator_MSG_InvalidParam;

   public static String MSG_WorkflowUser;

   public static String MSG_NoBeanId;

   public static String Msg_XORSplitANDJoinBlock;

   public static String MSG_StartEventSymbolWrongConnected;

   public static String ERR_Invalid_TeamLeadConnection;

   public static String EXC_NOT_IMPLEMENTED;

   public static String WR_IS_A_RESERVED_IDENTIFIER;

   public static String WR_MAPPING_INCONSISTENT_DATA_MAPPING_ID;

   public static String MSG_REMOVED_TRANSFORMATION;

   public static String WR_MAPPING_NO_IN_DATA_MAPPING_SPECIFIED;

   public static String WR_MAPPING_NO_IN_DATA_MAPPING_WITH_ID_STELLE_NULL_FOUND;

   public static String WR_MD_HAS_AN_OLDER_VERSION;

   public static String MSG_KEY_DESCRIPTOR_PRIMITIVE_STRUCTURED;

   public static String MSG_KEY_DESCRIPTOR_PRIMITIVE;

   public static String MSG_KEY_DESCRIPTOR_INDEXED_PERSISTENT;

   public static String MSG_KEY_DESCRIPTOR_NO_SCHEMA;

   public static String MSG_KEY_DESCRIPTOR_NO_DESCRIPTOR;

   public static String MSG_PERSISTENCE_OPTION_NOT_ALLOWED;

}