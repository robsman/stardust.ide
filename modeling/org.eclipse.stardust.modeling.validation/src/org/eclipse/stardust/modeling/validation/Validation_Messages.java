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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Validation_Messages
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.validation.validation-messages"; //$NON-NLS-1$

   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
         .getBundle(BUNDLE_NAME);

   private Validation_Messages()
   {}

   public static String DefaultModelValidator_MissingAdministrator = getString("DefaultModelValidator_MissingAdministrator"); //$NON-NLS-1$

   public static String DelegateActivity_InvalidParticipantId = getString("DelegateActivity_InvalidParticipantId"); //$NON-NLS-1$

   public static String DelegateActivity_MissingParticipantId = getString("DelegateActivity_MissingParticipantId"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_MultipleTransitions = getString("ERR_ACTIVITY_MultipleTransitions"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_SubProcessMode = getString("ERR_ACTIVITY_SubProcessMode"); //$NON-NLS-1$

   public static String ERR_ACTIVITYNoApplication = getString("ERR_ACTIVITYNoApplication"); //$NON-NLS-1$

   public static String MODEL_EmptyId = getString("MODEL_EmptyId"); //$NON-NLS-1$

   public static String MODEL_EmptyModelName = getString("MODEL_EmptyModelName"); //$NON-NLS-1$

   public static String MODEL_InvalidValidFrom = getString("MODEL_InvalidValidFrom"); //$NON-NLS-1$

   public static String MODEL_InvalidValidTo = getString("MODEL_InvalidValidTo"); //$NON-NLS-1$

   public static String MODEL_TooLongId = getString("MODEL_TooLongId"); //$NON-NLS-1$

   public static String MODEL_ValidFromAfterValidTo = getString("MODEL_ValidFromAfterValidTo"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_NeverUsed = getString("MODEL_ConfigurationVariable_NeverUsed"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_Invalid = getString("MODEL_ConfigurationVariable_Invalid"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_NoDefaultValue = getString("MODEL_ConfigurationVariable_NoDefaultValue"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_NotAllowed = getString("MODEL_ConfigurationVariable_NotAllowed"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_NAME = getString("MODEL_ConfigurationVariable_NAME"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_ID = getString("MODEL_ConfigurationVariable_ID"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_TypeDec = getString("MODEL_ConfigurationVariable_TypeDec"); //$NON-NLS-1$

   public static String MODEL_ConfigurationVariable_Description = getString("MODEL_ConfigurationVariable_Description"); //$NON-NLS-1$

   public static String MODEL_Connection_NoConnection = getString("MODEL_Connection_NoConnection"); //$NON-NLS-1$

   public static String MODEL_ReferencedModel_NoModel = getString("MODEL_ReferencedModel_NoModel"); //$NON-NLS-1$

   public static String MODEL_ExternalPackage_IDConflict = getString("MODEL_ExternalPackage_IDConflict"); //$NON-NLS-1$

   public static String MODEL_ReferencedType_NotFound = getString("MODEL_ReferencedType_NotFound"); //$NON-NLS-1$

   public static String MODEL_ReferencedType_NotVisible = getString("MODEL_ReferencedType_NotVisible"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_NotValid = getString("MODEL_ProcessInterface_NotValid"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_InvalidForExternalInvocation = getString("MODEL_ProcessInterface_InvalidForExternalInvocation"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_ParameterMissing = getString("MODEL_ProcessInterface_ParameterMissing"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_ParameterPending = getString("MODEL_ProcessInterface_ParameterPending"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_NoMapping = getString("MODEL_ProcessInterface_NoMapping"); //$NON-NLS-1$

   public static String MODEL_ProcessInterface_IncompatibleTypes = getString("MODEL_ProcessInterface_IncompatibleTypes"); //$NON-NLS-1$

   public static String MSG_ClassCanNotBeResolved = getString("MSG_ClassCanNotBeResolved"); //$NON-NLS-1$

   public static String MSG_NoTypeDeclarationFound = getString("MSG_NoTypeDeclarationFound"); //$NON-NLS-1$

   public static String MSG_NoTypeDeclarationDefined = getString("MSG_NoTypeDeclarationDefined"); //$NON-NLS-1$

   public static String MSG_NoEnumerationDefaultValue = getString("MSG_NoEnumerationDefaultValue"); //$NON-NLS-1$

   public static String MSG_InvalidEnumerationDefaultValue = getString("MSG_InvalidEnumerationDefaultValue"); //$NON-NLS-1$

   public static String MSG_ClassNotSerilizable = getString("MSG_ClassNotSerilizable"); //$NON-NLS-1$

   public static String MSG_ConstructorNotVisible = getString("MSG_ConstructorNotVisible"); //$NON-NLS-1$

   public static String MSG_DataHasNoType = getString("MSG_DataHasNoType"); //$NON-NLS-1$

   public static String MSG_EntityBean_InvalidEjbTypeSignature = getString("MSG_EntityBean_InvalidEjbTypeSignature"); //$NON-NLS-1$

   public static String MSG_InvalidProcess = getString("MSG_InvalidProcess"); //$NON-NLS-1$

   public static String MSG_MethodNotVisible = getString("MSG_MethodNotVisible"); //$NON-NLS-1$

   public static String MSG_NoDataMappingSymbols = getString("MSG_NoDataMappingSymbols"); //$NON-NLS-1$

   public static String MSG_NoExecutedBySymbols = getString("MSG_NoExecutedBySymbols"); //$NON-NLS-1$

   public static String MSG_NoPerformsSymbols = getString("MSG_NoPerformsSymbols"); //$NON-NLS-1$

   public static String MSG_NoReceiverType = getString("MSG_NoReceiverType"); //$NON-NLS-1$

   public static String MSG_NoReceivingParticipant = getString("MSG_NoReceivingParticipant"); //$NON-NLS-1$

   public static String MSG_NoEmailAddress = getString("MSG_NoEmailAddress"); //$NON-NLS-1$

   public static String MSG_NoAccessPointSpecified = getString("MSG_NoAccessPointSpecified"); //$NON-NLS-1$

   public static String MSG_NoProcessSelected = getString("MSG_NoProcessSelected"); //$NON-NLS-1$

   public static String MSG_NoDataSpecified = getString("MSG_NoDataSpecified"); //$NON-NLS-1$

   public static String MSG_JavaClassNotSpecified = getString("MSG_JavaClassNotSpecified"); //$NON-NLS-1$

   public static String MSG_CompletionMethodNotSpecified = getString("MSG_CompletionMethodNotSpecified"); //$NON-NLS-1$

   public static String MSG_CantFindMethodInClass = getString("MSG_CantFindMethodInClass"); //$NON-NLS-1$

   public static String MSG_ConstructorNotSpecified = getString("MSG_ConstructorNotSpecified"); //$NON-NLS-1$

   public static String MSG_CouldntFindConstructor = getString("MSG_CouldntFindConstructor"); //$NON-NLS-1$

   public static String MSG_NoRefersToSymbols = getString("MSG_NoRefersToSymbols"); //$NON-NLS-1$

   public static String MSG_NoTransitionSymbols = getString("MSG_NoTransitionSymbols"); //$NON-NLS-1$

   public static String MSG_NoWorksForSymbols = getString("MSG_NoWorksForSymbols"); //$NON-NLS-1$

   public static String MSG_OverriddenLaneParticipant = getString("MSG_OverriddenLaneParticipant"); //$NON-NLS-1$

   public static String MSG_ParameterHasNoId = getString("MSG_ParameterHasNoId"); //$NON-NLS-1$

   public static String MSG_NoLocationForParameter = getString("MSG_NoLocationForParameter"); //$NON-NLS-1$

   public static String MSG_NoValidTypeForParameter = getString("MSG_NoValidTypeForParameter"); //$NON-NLS-1$

   public static String MSG_DuplicateIdUsed = getString("MSG_DuplicateIdUsed"); //$NON-NLS-1$

   public static String MSG_NoTypeMappingDefined = getString("MSG_NoTypeMappingDefined"); //$NON-NLS-1$

   public static String MSG_PROCDEF_DisconnectedActivityGraph = getString("MSG_PROCDEF_DisconnectedActivityGraph"); //$NON-NLS-1$

   public static String MSG_SessionBean_InvalidEjbTypeSignature = getString("MSG_SessionBean_InvalidEjbTypeSignature"); //$NON-NLS-1$

   public static String MSG_XMLTypeHasInvalidMapping = getString("MSG_XMLTypeHasInvalidMapping"); //$NON-NLS-1$

   public static String MSG_TemplateIsInvalid = getString("MSG_TemplateIsInvalid"); //$NON-NLS-1$

   public static String MSG_WSDL_URLIsInvalid = getString("MSG_WSDL_URLIsInvalid"); //$NON-NLS-1$

   public static String MSG_PropertyNotSet = getString("MSG_PropertyNotSet"); //$NON-NLS-1$

   public static String MSG_InterfaceNotSpecified = getString("MSG_InterfaceNotSpecified"); //$NON-NLS-1$

   public static String MSG_MethodNotSpecified = getString("MSG_MethodNotSpecified"); //$NON-NLS-1$

   public static String MSG_CouldntFindMethodInClass = getString("MSG_CouldntFindMethodInClass"); //$NON-NLS-1$

   public static String MSG_TargetStateIsSameWithSource = getString("MSG_TargetStateIsSameWithSource"); //$NON-NLS-1$

   public static String MSG_IsNotException = getString("MSG_IsNotException"); //$NON-NLS-1$

   public static String MSG_InvalidDataMapping = getString("MSG_InvalidDataMapping"); //$NON-NLS-1$

   public static String MSG_InvalidDataPath = getString("MSG_InvalidDataPath"); //$NON-NLS-1$

   public static String MSG_MissingDataPath = getString("MSG_MissingDataPath"); //$NON-NLS-1$

   public static String MSG_InvalidDataSpecified = getString("MSG_InvalidDataSpecified"); //$NON-NLS-1$

   public static String MSG_TimerConditionHasToHave = getString("MSG_TimerConditionHasToHave"); //$NON-NLS-1$

   public static String MSG_NoPeriodSpecified = getString("MSG_NoPeriodSpecified"); //$NON-NLS-1$

   public static String MSG_JFC_UnspecifiedClass = getString("MSG_JFC_UnspecifiedClass"); //$NON-NLS-1$

   public static String MSG_JFC_CouldntFindClass = getString("MSG_JFC_CouldntFindClass"); //$NON-NLS-1$

   public static String MSG_JFC_UnspecifiedCompletionMethod = getString("MSG_JFC_UnspecifiedCompletionMethod"); //$NON-NLS-1$

   public static String MSG_JFC_CouldntFindCompletionMethod = getString("MSG_JFC_CouldntFindCompletionMethod"); //$NON-NLS-1$

   public static String MSG_JSP_UndefinedHtmlPath = getString("MSG_JSP_UndefinedHtmlPath"); //$NON-NLS-1$

   public static String MSG_PRIMITIVE_UnspecifiedType = getString("MSG_PRIMITIVE_UnspecifiedType"); //$NON-NLS-1$

   public static String MSG_TIMERTRIGGER_UnspecifiedStart = getString("MSG_TIMERTRIGGER_UnspecifiedStart"); //$NON-NLS-1$

   public static String MSG_TIMERTRIGGER_UnspecifiedUsername = getString("MSG_TIMERTRIGGER_UnspecifiedUsername"); //$NON-NLS-1$

   public static String MSG_TIMERTRIGGER_UnspecifiedPassword = getString("MSG_TIMERTRIGGER_UnspecifiedPassword"); //$NON-NLS-1$

   public static String MSG_TIMERTRIGGER_UnspecifiedServerName = getString("MSG_TIMERTRIGGER_UnspecifiedServerName"); //$NON-NLS-1$

   public static String MSG_TIMERTRIGGER_UnspecifiedProtocol = getString("MSG_TIMERTRIGGER_UnspecifiedProtocol"); //$NON-NLS-1$

   public static String MSG_JMSTRIGGER_UnspecifiedMessageType = getString("MSG_JMSTRIGGER_UnspecifiedMessageType"); //$NON-NLS-1$

   public static String MSG_JMSTRIGGER_ParameterNoId = getString("MSG_JMSTRIGGER_ParameterNoId"); //$NON-NLS-1$

   public static String MSG_JMSTRIGGER_NoLocationSpecified = getString("MSG_JMSTRIGGER_NoLocationSpecified"); //$NON-NLS-1$

   public static String MSG_JMSTRIGGER_NoValidTypeForParameter = getString("MSG_JMSTRIGGER_NoValidTypeForParameter"); //$NON-NLS-1$

   public static String MSG_Trigger_UnspecifiedParticipant = getString("MSG_Trigger_UnspecifiedParticipant"); //$NON-NLS-1$

   public static String MSG_Scantrigger_UnspecifiedParticipant = getString("MSG_Scantrigger_UnspecifiedParticipant"); //$NON-NLS-1$

   public static String MSG_Scantrigger_NoDocumentDataSpecified = getString("MSG_Scantrigger_NoDocumentDataSpecified"); //$NON-NLS-1$

   public static String MSG_Trigger_InvalidParticipant = getString("MSG_Trigger_InvalidParticipant"); //$NON-NLS-1$

   public static String MSG_DuplicateDataId = getString("MSG_DuplicateDataId"); //$NON-NLS-1$

   public static String MSG_DuplicateProcessDefinitionId = getString("MSG_DuplicateProcessDefinitionId"); //$NON-NLS-1$

   public static String MSG_NoStartActivity = getString("MSG_NoStartActivity"); //$NON-NLS-1$

   public static String MSG_MultipleSartActivities = getString("MSG_MultipleSartActivities"); //$NON-NLS-1$

   public static String MSG_NoActivity = getString("MSG_NoActivity"); //$NON-NLS-1$

   public static String MSG_TRIGGER_DuplicateId = getString("MSG_TRIGGER_DuplicateId"); //$NON-NLS-1$

   public static String MSG_TRIGGER_NoTypeSet = getString("MSG_TRIGGER_NoTypeSet"); //$NON-NLS-1$

   public static String MSG_NoDataSpecifiedByParameterMapping = getString("MSG_NoDataSpecifiedByParameterMapping"); //$NON-NLS-1$

   public static String MSG_NoParameterSpecifiedByParameterMapping = getString("MSG_NoParameterSpecifiedByParameterMapping"); //$NON-NLS-1$

   public static String MSG_ParameterMappingInvalidTypeConversion = getString("MSG_ParameterMappingInvalidTypeConversion"); //$NON-NLS-1$

   public static String ERR_ActionHasNoType = getString("ERR_ActionHasNoType"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoActivitySet = getString("MSG_DATAMAPPING_NoActivitySet"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NotSupportedByActivity = getString("MSG_DATAMAPPING_NotSupportedByActivity"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoDataSet = getString("MSG_DATAMAPPING_NoDataSet"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoUniqueId = getString("MSG_DATAMAPPING_NoUniqueId"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_ContextInvalid = getString("MSG_DATAMAPPING_ContextInvalid"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_ApplicationInvalid = getString("MSG_DATAMAPPING_ApplicationInvalid"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_DataNotOfCorrectType = getString("MSG_DATAMAPPING_DataNotOfCorrectType"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_ApplicationAccessPointWarning = getString("MSG_DATAMAPPING_ApplicationAccessPointWarning"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoApplicationAccessPointSet = getString("MSG_DATAMAPPING_NoApplicationAccessPointSet"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoValidDataPath = getString("MSG_DATAMAPPING_NoValidDataPath"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_NoContextSet = getString("MSG_DATAMAPPING_NoContextSet"); //$NON-NLS-1$

   public static String MSG_DATAMAPPING_ContextUndefined = getString("MSG_DATAMAPPING_ContextUndefined"); //$NON-NLS-1$

   public static String ERR_ROLE_DuplicateId = getString("ERR_ROLE_DuplicateId"); //$NON-NLS-1$

   public static String ERR_ROLE_Administrator = getString("ERR_ROLE_Administrator"); //$NON-NLS-1$

   public static String ERR_ROLE_WorksForManagerOf = getString("ERR_ROLE_WorksForManagerOf"); //$NON-NLS-1$

   public static String ERR_ROLE_WorksFor = getString("ERR_ROLE_WorksFor"); //$NON-NLS-1$

   public static String ERR_ROLE_ManagerOf = getString("ERR_ROLE_ManagerOf"); //$NON-NLS-1$

   public static String ERR_Trigger_InvalidScopedParticipant = getString("ERR_Trigger_InvalidScopedParticipant"); //$NON-NLS-1$

   public static String MSG_COND_PERFORMER_DuplicateId = getString("MSG_COND_PERFORMER_DuplicateId"); //$NON-NLS-1$

   public static String MSG_COND_PERFORMER_NoDataSet = getString("MSG_COND_PERFORMER_NoDataSet"); //$NON-NLS-1$

   public static String MSG_COND_PERFORMER_UnsupportedType = getString("MSG_COND_PERFORMER_UnsupportedType"); //$NON-NLS-1$

   public static String ERR_DATAPATH_DuplicateId = getString("ERR_DATAPATH_DuplicateId"); //$NON-NLS-1$

   public static String ERR_DATAPATH_NotValidIdentifier = getString("ERR_DATAPATH_NotValidIdentifier"); //$NON-NLS-1$

   public static String ERR_DATAPATH_NoNameSpecified = getString("ERR_DATAPATH_NoNameSpecified"); //$NON-NLS-1$

   public static String ERR_DATAPATH_NoDataSpecified = getString("ERR_DATAPATH_NoDataSpecified"); //$NON-NLS-1$

   public static String ERR_MODELER_DuplicateId = getString("ERR_MODELER_DuplicateId"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_DuplicateId = getString("ERR_ACTIVITY_DuplicateId"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoPerformerSet = getString("ERR_ACTIVITY_NoPerformerSet"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_QualityAssurancePerformer = getString("ERR_ACTIVITY_QualityAssurancePerformer"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_PerformerWronglySet = getString("ERR_ACTIVITY_PerformerWronglySet"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoImplementationProcess = getString("ERR_ACTIVITY_NoImplementationProcess"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_MultipleIncomingTransitions = getString("ERR_ACTIVITY_MultipleIncomingTransitions"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_MultipleOutgoingTransitions = getString("ERR_ACTIVITY_MultipleOutgoingTransitions"); //$NON-NLS-1$

   public static String ERR_APPLICATION_DuplicateId = getString("ERR_APPLICATION_DuplicateId"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_DiagramNameIsEmpty = getString("MSG_DIAGRAM_DiagramNameIsEmpty"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_NoRequiredGatewaySymbols = getString("MSG_DIAGRAM_NoRequiredGatewaySymbols"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_MultipleOutgoingTransitions = getString("MSG_DIAGRAM_MultipleOutgoingTransitions"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_InvalidConnectionTarget = getString("MSG_DIAGRAM_InvalidConnectionTarget"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_NoJoinTypeNONE = getString("MSG_DIAGRAM_NoJoinTypeNONE"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_MultipleIncomingTransitions = getString("MSG_DIAGRAM_MultipleIncomingTransitions"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_InvalidConnectionSource = getString("MSG_DIAGRAM_InvalidConnectionSource"); //$NON-NLS-1$

   public static String MSG_DIAGRAM_NoSplitTypeNONE = getString("MSG_DIAGRAM_NoSplitTypeNONE"); //$NON-NLS-1$

   public static String ERR_EVENTHANDLER_DuplicateId = getString("ERR_EVENTHANDLER_DuplicateId"); //$NON-NLS-1$

   public static String ERR_EVENTHANDLER_NoConditionType = getString("ERR_EVENTHANDLER_NoConditionType"); //$NON-NLS-1$

   public static String ERR_ORGANIZATION_DuplicateId = getString("ERR_ORGANIZATION_DuplicateId"); //$NON-NLS-1$

   public static String ERR_ORGANIZATION_PartOf = getString("ERR_ORGANIZATION_PartOf"); //$NON-NLS-1$

   public static String ERR_ORGANIZATION_InvalidScopeData = getString("ERR_ORGANIZATION_InvalidScopeData"); //$NON-NLS-1$

   public static String ERR_ELEMENT_EmptyId = getString("ERR_ELEMENT_EmptyId"); //$NON-NLS-1$

   public static String ERR_ELEMENT_InvalidId = getString("ERR_ELEMENT_InvalidId"); //$NON-NLS-1$

   public static String ERR_ELEMENT_IdLength = getString("ERR_ELEMENT_IdLength"); //$NON-NLS-1$

   public static String MSG_ELEMENT_EmptyName = getString("MSG_ELEMENT_EmptyName"); //$NON-NLS-1$

   public static String ERR_NotAJavaDataType = getString("ERR_NotAJavaDataType"); //$NON-NLS-1$

   public static String ERR_InvalidJavaBeanAccessPathType = getString("ERR_InvalidJavaBeanAccessPathType"); //$NON-NLS-1$

   public static String ERR_JDT_MethodEmpty = getString("ERR_JDT_MethodEmpty"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_UndefinedLoopType = getString("ERR_ACTIVITY_UndefinedLoopType"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_UndefinedStandardLoop = getString("ERR_ACTIVITY_UndefinedStandardLoop"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_InvalidMultiInstanceLoop = getString("ERR_ACTIVITY_InvalidMultiInstanceLoop"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoStandardLoopExecutionType = getString("ERR_ACTIVITY_NoStandardLoopExecutionType"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoLoopCondition = getString("ERR_ACTIVITY_NoLoopCondition"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_InvalidLoopCondition = getString("ERR_ACTIVITY_InvalidLoopCondition"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoMultiInstanceLoopOrderingType = getString("ERR_ACTIVITY_NoMultiInstanceLoopOrderingType"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_IncompatibleSubProcessMode = getString("ERR_ACTIVITY_IncompatibleSubProcessMode"); //$NON-NLS-1$

   public static String ERR_ACTIVITY_NoInputLoopDataSpecified = getString("ERR_ACTIVITY_NoInputLoopDataSpecified"); //$NON-NLS-1$

   public static String BridgeObject_assignmentNotCompatible = getString("BridgeObject_assignmentNotCompatible"); //$NON-NLS-1$

   public static String CSL_ERR_UNABLE_TO_RESOLVE_CL = getString("CSL_ERR_UNABLE_TO_RESOLVE_CL"); //$NON-NLS-1$

   public static String JavaDataTypeUtils_segmentProcessingError = getString("JavaDataTypeUtils_segmentProcessingError"); //$NON-NLS-1$

   public static String MSG_MissingException = getString("MSG_MissingException"); //$NON-NLS-1$

   public static String Path_InvalidSegment = getString("Path_InvalidSegment"); //$NON-NLS-1$

   public static String SerializableValidator_NoDefaultConstructorMessage = getString("SerializableValidator_NoDefaultConstructorMessage"); //$NON-NLS-1$

   public static String SessionBean_IncompatibleCreatedType = getString("SessionBean_IncompatibleCreatedType"); //$NON-NLS-1$

   public static String SessionBean30Validator_MissingAnnotation = getString("SessionBean30Validator_MissingAnnotation"); //$NON-NLS-1$

   public static String TimerTrigger_InvalidPeriodicity = getString("TimerTrigger_InvalidPeriodicity"); //$NON-NLS-1$

   public static String TXT_ID = getString("TXT_ID"); //$NON-NLS-1$

public static String TXT_NAME = getString("TXT_NAME"); //$NON-NLS-1$

public static String Validation_MSG_JNDIPathNotSpecified = getString("Validation_MSG_JNDIPathNotSpecified"); //$NON-NLS-1$

   public static String MSG_NoRemoteIF = getString("MSG_NoRemoteIF"); //$NON-NLS-1$

   public static String MSG_NoHomeIF = getString("MSG_NoHomeIF"); //$NON-NLS-1$

   public static String MSG_NoPrimaryKey = getString("MSG_NoPrimaryKey"); //$NON-NLS-1$

   public static String MSG_NoJNDI = getString("MSG_NoJNDI"); //$NON-NLS-1$

   public static String MSG_NoClass = getString("MSG_NoClass"); //$NON-NLS-1$

   public static String ERR_ACTIONTYPE_DuplicateId = getString("ERR_ACTIONTYPE_DuplicateId"); //$NON-NLS-1$

   public static String MailActionValidator_MSG_NoCorrectEmailAddress = getString("MailActionValidator_MSG_NoCorrectEmailAddress"); //$NON-NLS-1$

   public static String EventHandlerValidator_MSG_DEFAULT_VALUE_PERIOD = getString("EventHandlerValidator_MSG_DEFAULT_VALUE_PERIOD"); //$NON-NLS-1$

   public static String EventHandlerValidator_MSG_NO_PERIOD_VALUE = getString("EventHandlerValidator_MSG_NO_PERIOD_VALUE"); //$NON-NLS-1$

   public static String MSG_EmptyTransitionCond = getString("MSG_EmptyTransitionCond"); //$NON-NLS-1$

   public static String MSG_TransitionCondUpdate = getString("MSG_TransitionCondUpdate"); //$NON-NLS-1$

   public static String MSG_InvalidJavaScriptTransitionCondition = getString("MSG_InvalidJavaScriptTransitionCondition"); //$NON-NLS-1$

   public static String MSG_InvalidJavaScriptMessageTransformation = getString("MSG_InvalidJavaScriptMessageTransformation"); //$NON-NLS-1$

   public static String MSG_MissingExternalClass = getString("MSG_MissingExternalClass"); //$NON-NLS-1$

   public static String MSG_InvalidExternalReference = getString("MSG_InvalidExternalReference"); //$NON-NLS-1$

   public static String MSG_SyntaxInvalidTransitionCond = getString("MSG_SyntaxInvalidTransitionCond"); //$NON-NLS-1$

   public static String MSG_SemanticInvalidTransitionCond = getString("MSG_SemanticInvalidTransitionCond"); //$NON-NLS-1$

   public static String MSG_InvalidSymbol = getString("MSG_InvalidSymbol"); //$NON-NLS-1$

   public static String LB_SearchingTypes = getString("LB_SearchingTypes"); //$NON-NLS-1$

   public static String MSG_UnableToResolve = getString("MSG_UnableToResolve"); //$NON-NLS-1$

   public static String MSG_AmbiguousDeclaration = getString("MSG_AmbiguousDeclaration"); //$NON-NLS-1$

   public static String SetDataActionValidator_MSG_InvalidParam = getString("SetDataActionValidator_MSG_InvalidParam"); //$NON-NLS-1$

   public static String MSG_WorkflowUser = getString("MSG_WorkflowUser"); //$NON-NLS-1$

   public static String MSG_NoBeanId = getString("MSG_NoBeanId"); //$NON-NLS-1$

   public static String Msg_XORSplitANDJoinBlock = getString("Msg_XORSplitANDJoinBlock"); //$NON-NLS-1$

   public static String Msg_PotentialDeadlock = getString("Msg_PotentialDeadlock"); //$NON-NLS-1$

   public static String MSG_StartEventSymbolWrongConnected = getString("MSG_StartEventSymbolWrongConnected"); //$NON-NLS-1$

   public static String ERR_Invalid_TeamLeadConnection = getString("ERR_Invalid_TeamLeadConnection"); //$NON-NLS-1$

   public static String EXC_NOT_IMPLEMENTED = getString("EXC_NOT_IMPLEMENTED"); //$NON-NLS-1$

   public static String WR_IS_A_RESERVED_IDENTIFIER = getString("WR_IS_A_RESERVED_IDENTIFIER"); //$NON-NLS-1$

   public static String WR_MAPPING_INCONSISTENT_DATA_MAPPING_ID = getString("WR_MAPPING_INCONSISTENT_DATA_MAPPING_ID"); //$NON-NLS-1$

   public static String MSG_REMOVED_TRANSFORMATION = getString("MSG_REMOVED_TRANSFORMATION"); //$NON-NLS-1$

   public static String WR_MAPPING_NO_IN_DATA_MAPPING_SPECIFIED = getString("WR_MAPPING_NO_IN_DATA_MAPPING_SPECIFIED"); //$NON-NLS-1$

   public static String WR_MAPPING_NO_IN_DATA_MAPPING_WITH_ID_STELLE_NULL_FOUND = getString("WR_MAPPING_NO_IN_DATA_MAPPING_WITH_ID_STELLE_NULL_FOUND"); //$NON-NLS-1$

   public static String WR_MD_HAS_AN_OLDER_VERSION = getString("WR_MD_HAS_AN_OLDER_VERSION"); //$NON-NLS-1$

   public static String MSG_KEY_DESCRIPTOR_PRIMITIVE_STRUCTURED = getString("MSG_KEY_DESCRIPTOR_PRIMITIVE_STRUCTURED"); //$NON-NLS-1$

   public static String MSG_KEY_DESCRIPTOR_PRIMITIVE = getString("MSG_KEY_DESCRIPTOR_PRIMITIVE"); //$NON-NLS-1$

   public static String MSG_KEY_DESCRIPTOR_INDEXED_PERSISTENT = getString("MSG_KEY_DESCRIPTOR_INDEXED_PERSISTENT"); //$NON-NLS-1$

   public static String MSG_KEY_DESCRIPTOR_NO_SCHEMA = getString("MSG_KEY_DESCRIPTOR_NO_SCHEMA"); //$NON-NLS-1$

   public static String MSG_KEY_DESCRIPTOR_NO_DESCRIPTOR = getString("MSG_KEY_DESCRIPTOR_NO_DESCRIPTOR"); //$NON-NLS-1$

   public static String MSG_PERSISTENCE_OPTION_NOT_ALLOWED = getString("MSG_PERSISTENCE_OPTION_NOT_ALLOWED"); //$NON-NLS-1$

   public static String MSG_TypeDeclarationNotJavaBoundEnum = getString("MSG_TypeDeclarationNotJavaBoundEnum"); //$NON-NLS-1$

   private static String getString(String key)
   {
      try
      {
         return RESOURCE_BUNDLE.getString(key);
      }
      catch (MissingResourceException e)
      {
         return '!' + key + '!';
      }
   }
}