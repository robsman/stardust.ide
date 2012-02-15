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
package org.eclipse.stardust.engine.api.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A collection of constants containing predefined IDs and attribute names.
 *
 * @author purang
 */
public final class PredefinedConstants
{
   // attribute scopes
   public static final String MODEL_SCOPE = "carnot:model:"; //$NON-NLS-1$
   public static final String XPDL_SCOPE = "carnot:model:xpdl:"; //$NON-NLS-1$
   public static final String DD_SCOPE = "carnot:defdesk:"; //$NON-NLS-1$
   public static final String ED_SCOPE = "carnot:exdesk:"; //$NON-NLS-1$
   public static final String WEBEX_SCOPE = "carnot:webex:"; //$NON-NLS-1$
   public static final String ENGINE_SCOPE = "carnot:engine:"; //$NON-NLS-1$
   public static final String PWH_SCOPE = "carnot:pwh:"; //$NON-NLS-1$

   // predefined application type IDs
   public static final String SESSIONBEAN_APPLICATION = "sessionBean"; //$NON-NLS-1$
   public static final String PLAINJAVA_APPLICATION = "plainJava"; //$NON-NLS-1$
   public static final String JMS_APPLICATION = "jms"; //$NON-NLS-1$
   public static final String SPRINGBEAN_APPLICATION = "springBean"; //$NON-NLS-1$
   public static final String WS_APPLICATION = "webservice"; //$NON-NLS-1$
   public static final String WFXML_APPLICATION = "wfxml"; //$NON-NLS-1$

   // predefined data type IDs
   public static final String PRIMITIVE_DATA = "primitive"; //$NON-NLS-1$
   public static final String SERIALIZABLE_DATA = "serializable"; //$NON-NLS-1$
   public static final String ENTITY_BEAN_DATA = "entity"; //$NON-NLS-1$
   public static final String PLAIN_XML_DATA = "plainXML"; //$NON-NLS-1$
   public static final String HIBERNATE_DATA = "hibernate";    //$NON-NLS-1$
   public static final String STRUCTURED_DATA = "struct"; //$NON-NLS-1$
   // DMS
   public static final String DMS_DOCUMENT_DATA = "dms-document"; //$NON-NLS-1$
   public static final String DMS_DOCUMENT_SET_DATA = "dms-document-set"; //$NON-NLS-1$
   public static final String DOCUMENT_DATA = "dmsDocument"; //$NON-NLS-1$
   public static final String DOCUMENT_LIST_DATA = "dmsDocumentList"; //$NON-NLS-1$
   public static final String FOLDER_DATA = "dmsFolder";    //$NON-NLS-1$

   // predefined context type IDs
   public static final String DEFAULT_CONTEXT = "default"; //$NON-NLS-1$
   public static final String ENGINE_CONTEXT = "engine"; //$NON-NLS-1$
   public static final String PROCESSINTERFACE_CONTEXT = "processInterface"; //$NON-NLS-1$
   public static final String JFC_CONTEXT = "jfc"; //$NON-NLS-1$
   public static final String JSP_CONTEXT = "jsp"; //$NON-NLS-1$
   public static final String APPLICATION_CONTEXT = "application"; //$NON-NLS-1$

   // predefined action type Ids
   public static final String MAIL_ACTION = "mail"; //$NON-NLS-1$
   public static final String TRIGGER_ACTION = "trigger"; //$NON-NLS-1$
   public static final String EXCEPTION_ACTION = "exception"; //$NON-NLS-1$
   public static final String DELEGATE_ACTIVITY_ACTION = "delegateActivity"; //$NON-NLS-1$
   public static final String SCHEDULE_ACTIVITY_ACTION = "scheduleActivity"; //$NON-NLS-1$
   public static final String ABORT_PROCESS_ACTION = "abortProcess"; //$NON-NLS-1$
   public static final String COMPLETE_ACTIVITY_ACTION = "completeActivity"; //$NON-NLS-1$
   public static final String ACTIVATE_ACTIVITY_ACTION = "activateActivity"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION = "setData"; //$NON-NLS-1$
   public static final String EXCLUDE_USER_ACTION = "excludeUser"; //$NON-NLS-1$

   // predefined trigger type IDs
   public static final String MANUAL_TRIGGER = "manual"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER = "mail"; //$NON-NLS-1$
   public static final String JMS_TRIGGER = "jms"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER = "timer"; //$NON-NLS-1$

   // predefined condition type IDs
   public static final String TIMER_CONDITION = "timer"; //$NON-NLS-1$
   public static final String ACTIVITY_ON_ASSIGNMENT_CONDITION = "onAssignment"; //$NON-NLS-1$
   public static final String EXPRESSION_CONDITION = "expression"; //$NON-NLS-1$
   public static final String EXCEPTION_CONDITION = "exception"; //$NON-NLS-1$
   public static final String ACTIVITY_STATECHANGE_CONDITION = "statechange"; //$NON-NLS-1$
   public static final String PROCESS_STATECHANGE_CONDITION = "processStatechange"; //$NON-NLS-1$
   public static final String EXTERNAL_EVENT_CONDITION = "external"; //$NON-NLS-1$
   public static final String OBSERVER_EVENT_CONDITION = "observer"; //$NON-NLS-1$

   // predefined data
   public static final String LAST_ACTIVITY_PERFORMER = "LAST_ACTIVITY_PERFORMER"; //$NON-NLS-1$
   public static final String STARTING_USER = "STARTING_USER"; //$NON-NLS-1$
   public static final String CURRENT_USER = "CURRENT_USER"; //$NON-NLS-1$
   public static final String PROCESS_ID = "PROCESS_ID"; //$NON-NLS-1$
   public static final String PROCESS_PRIORITY = "PROCESS_PRIORITY"; //$NON-NLS-1$
   public static final String ROOT_PROCESS_ID = "ROOT_PROCESS_ID"; //$NON-NLS-1$
   public static final String CURRENT_DATE = "CURRENT_DATE"; //$NON-NLS-1$
   public static final String CURRENT_LOCALE = "CURRENT_LOCALE"; //$NON-NLS-1$
   public static final String CURRENT_MODEL = "CURRENT_MODEL"; //$NON-NLS-1$

   // predefined role and user
   public static final String ADMINISTRATOR_ROLE = "Administrator"; //$NON-NLS-1$
   public static final String MOTU = "motu"; //$NON-NLS-1$
   public static final String MOTU_FIRST_NAME = "Master"; //$NON-NLS-1$
   public static final String MOTU_LAST_NAME = "Of the Universe"; //$NON-NLS-1$
   
   public static final String SYSTEM = "system_carnot_engine"; //$NON-NLS-1$
   public static final String SYSTEM_FIRST_NAME = SYSTEM;
   public static final String SYSTEM_LAST_NAME = SYSTEM;
   
   public static final String SYSTEM_REALM = SYSTEM;
   
   // predefined default partition
   public static final String DEFAULT_PARTITION_ID = "default"; //$NON-NLS-1$
   
   // predefined default user realm
   public static final String DEFAULT_REALM_ID = "carnot"; //$NON-NLS-1$
   public static final String DEFAULT_REALM_NAME = "CARNOT"; //$NON-NLS-1$
   
   // model attribute names
   public static final String XPDL_EXTENDED_ATTRIBUTES = XPDL_SCOPE + "extendedAttributes"; //$NON-NLS-1$

   // engine attribute names
   public static final String ACCEPTOR_CLASS_ATT = ENGINE_SCOPE + "jmsAcceptor"; //$NON-NLS-1$

   public static final String CLASS_NAME_ATT = ENGINE_SCOPE + "className"; //$NON-NLS-1$
   public static final String PRIMARY_KEY_ATT = ENGINE_SCOPE + "primaryKey"; //$NON-NLS-1$
   public static final String IS_LOCAL_ATT = ENGINE_SCOPE + "isLocal"; //$NON-NLS-1$
   public static final String JNDI_PATH_ATT = ENGINE_SCOPE + "jndiPath"; //$NON-NLS-1$
   public static final String ASYNCHRONOUS_ATT = ENGINE_SCOPE + "asynchronous"; //$NON-NLS-1$

   public static final String HOME_INTERFACE_ATT = ENGINE_SCOPE + "homeInterface"; //$NON-NLS-1$
   public static final String REMOTE_INTERFACE_ATT = ENGINE_SCOPE + "remoteInterface"; //$NON-NLS-1$
   public static final String METHOD_NAME_ATT = ENGINE_SCOPE + "methodName"; //$NON-NLS-1$
   public static final String CREATE_METHOD_NAME_ATT = ENGINE_SCOPE + "createMethodName"; //$NON-NLS-1$
   
   public static final String CONDITIONAL_PERFORMER_KIND = ENGINE_SCOPE + "conditionalPerformer:kind"; //$NON-NLS-1$
   public static final String CONDITIONAL_PERFORMER_KIND_USER = "user"; //$NON-NLS-1$
   public static final String CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT = "modelParticipant"; //$NON-NLS-1$
   public static final String CONDITIONAL_PERFORMER_KIND_USER_GROUP = "userGroup"; //$NON-NLS-1$
   public static final String CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT_OR_USER_GROUP = "modelParticipantOrUserGroup"; //$NON-NLS-1$
   
   public static final String CONDITIONAL_PERFORMER_REALM_DATA = ENGINE_SCOPE + "conditionalPerformer:realmData"; //$NON-NLS-1$
   public static final String CONDITIONAL_PERFORMER_REALM_DATA_PATH = ENGINE_SCOPE + "conditionalPerformer:realmDataPath"; //$NON-NLS-1$

   // common for generic WebService application and WfXML application types.
   public static final String AUTHENTICATION_ATT = ENGINE_SCOPE + "wsAuthentication"; //$NON-NLS-1$
   public static final String BASIC_AUTHENTICATION = "basic"; //$NON-NLS-1$
   public static final String WS_SECURITY_AUTHENTICATION = "ws-security"; //$NON-NLS-1$
   public static final String AUTHENTICATION_VARIANT_ATT = ENGINE_SCOPE + "wsAuthenticationVariant"; //$NON-NLS-1$
   public static final String WS_SECURITY_VARIANT_PASSWORD_TEXT = "passwordText"; //$NON-NLS-1$
   public static final String WS_SECURITY_VARIANT_PASSWORD_DIGEST = "passwordDigest"; //$NON-NLS-1$
   public static final String AUTHENTICATION_ID = ENGINE_SCOPE + "authentication"; //$NON-NLS-1$

   public static final String ENDPOINT_REFERENCE_ID = ENGINE_SCOPE + "endpointReference"; //$NON-NLS-1$
   public static final String WS_ENDPOINT_REFERENCE_ID = ENGINE_SCOPE + "endpointReference";   // deprecated //$NON-NLS-1$
   public static final String WS_AUTHENTICATION_ID = ENGINE_SCOPE + "authentication"; // deprecated //$NON-NLS-1$

   public static final String WS_WSDL_URL_ATT = ENGINE_SCOPE + "wsdlUrl"; //$NON-NLS-1$
   public static final String WS_SERVICE_NAME_ATT = ENGINE_SCOPE + "wsServiceName"; //$NON-NLS-1$
   public static final String WS_PORT_NAME_ATT = ENGINE_SCOPE + "wsPortName"; //$NON-NLS-1$
   public static final String WS_OPERATION_NAME_ATT = ENGINE_SCOPE + "wsOperationName"; //$NON-NLS-1$
   public static final String WS_OPERATION_STYLE_ATT = ENGINE_SCOPE + "wsStyle"; //$NON-NLS-1$
   public static final String WS_OPERATION_USE_ATT = ENGINE_SCOPE + "wsUse"; //$NON-NLS-1$
   public static final String WS_IMPLEMENTATION_ATT = ENGINE_SCOPE + "wsImplementation"; //$NON-NLS-1$
   public static final String WS_GENERIC_IMPLEMENTATION = "generic"; //$NON-NLS-1$
   public static final String WS_CARNOT_IMPLEMENTATION = "carnot"; //$NON-NLS-1$
   public static final String WS_MAPPING_ATTR_PREFIX = ENGINE_SCOPE + "mapping:"; //$NON-NLS-1$
   public static final String WS_TEMPLATE_ATTR_PREFIX = ENGINE_SCOPE + "template:"; //$NON-NLS-1$
   public static final String WS_SERVICE_ENDPOINT_ATT = ENGINE_SCOPE + "wsEndpointAddress"; //$NON-NLS-1$
   public static final String WS_SOAP_ACTION_ATT = ENGINE_SCOPE + "wsSoapAction"; //$NON-NLS-1$
   public static final String WS_PARAMETER_NAME_ATT = ENGINE_SCOPE + "wsParameterName:"; //$NON-NLS-1$
   public static final String WS_PARAMETER_TYPE_ATT = ENGINE_SCOPE + "wsParameterType:"; //$NON-NLS-1$
   public static final String WS_PARAMETER_STYLE_ATT = ENGINE_SCOPE + "wsParameterStyle:"; //$NON-NLS-1$
   public static final String WS_RESULT_SUFFIX = "result"; //$NON-NLS-1$
   public static final String WS_FAULT_NAME_ATT = ENGINE_SCOPE + "wsFaultName:"; //$NON-NLS-1$
   public static final String WS_FAULT_TYPE_ATT = ENGINE_SCOPE + "wsFaultType:"; //$NON-NLS-1$
   public static final String WS_FAULT_STYLE_ATT = ENGINE_SCOPE + "wsFaultStyle:"; //$NON-NLS-1$

   // attributes
   public static final String WFXML_SCOPE = ENGINE_SCOPE + "wfxml:"; //$NON-NLS-1$
   public static final String WFXML_IS_CARNOT_ATT = WFXML_SCOPE + "isCarnot"; //$NON-NLS-1$
   public static final String WFXML_RESOURCE_ATT = WFXML_SCOPE + "resource"; //$NON-NLS-1$
   public static final String WFXML_OPERATION_SCOPE = WFXML_SCOPE + "operation:"; //$NON-NLS-1$
   public static final String WFXML_OPERATION_ATT = WFXML_SCOPE + "operation"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_ID = "endpoint"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_SCOPE = WFXML_SCOPE + WFXML_ENDPOINT_ID + ":"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_ADDRESS_ATT = "address"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_SERVICE_NAME_ATT = "service:name"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_SERVICE_PORT_ATT = "service:port"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_PORT_TYPE_ATT = "port:type"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_PARAMETER_NAME_SCOPE = WFXML_ENDPOINT_SCOPE + "parameter:name:"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_PARAMETER_VALUE_SCOPE = WFXML_ENDPOINT_SCOPE + "parameter:value:"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_PROPERTY_NAME_SCOPE = WFXML_ENDPOINT_SCOPE + "property:name:"; //$NON-NLS-1$
   public static final String WFXML_ENDPOINT_PROPERTY_VALUE_SCOPE = WFXML_ENDPOINT_SCOPE + "property:value:"; //$NON-NLS-1$

   public static final String FLAVOR_ATT = ENGINE_SCOPE + "flavor"; //$NON-NLS-1$
   public static final String BROWSABLE_ATT = ENGINE_SCOPE + "browsable"; //$NON-NLS-1$

   public static final String TARGET_TIMESTAMP_ATT = ENGINE_SCOPE + "targetTime"; //$NON-NLS-1$
   public static final String WORKFLOW_EXPRESSION_ATT = ENGINE_SCOPE + "expression"; //$NON-NLS-1$
   public static final String TRIGGER_ACTION_PROCESS_ATT = ENGINE_SCOPE + "processDefinition"; //$NON-NLS-1$

   public static final String MANUAL_TRIGGER_PARTICIPANT_ATT = ENGINE_SCOPE + "participant"; //$NON-NLS-1$
   public static final String TIMER_PERIOD_ATT = ENGINE_SCOPE + "period"; //$NON-NLS-1$

   public static final String MAIL_ACTION_RECEIVER_TYPE_ATT = ENGINE_SCOPE + "receiverType"; //$NON-NLS-1$
   public static final String MAIL_ACTION_RECEIVER_ATT = ENGINE_SCOPE + "receiver"; //$NON-NLS-1$
   public static final String MAIL_ACTION_ADDRESS_ATT = ENGINE_SCOPE + "emailAddress"; //$NON-NLS-1$
   public static final String MAIL_ACTION_BODY_TEMPLATE_ATT = ENGINE_SCOPE + "mailBodyTemplate"; //$NON-NLS-1$
   public static final String MAIL_ACTION_BODY_DATA_ATT = ENGINE_SCOPE + "mailBodyData"; //$NON-NLS-1$
   public static final String MAIL_ACTION_BODY_DATA_PATH_ATT = ENGINE_SCOPE + "mailBodyDataPath"; //$NON-NLS-1$

   public static final String TARGET_STATE_ATT = ENGINE_SCOPE + "targetState"; //$NON-NLS-1$
   public static final String TARGET_WORKLIST_ATT = ENGINE_SCOPE + "targetWorklist"; //$NON-NLS-1$
   public static final String TARGET_PARTICIPANT_ATT = ENGINE_SCOPE + "target"; //$NON-NLS-1$
   public static final String PULL_EVENT_EMITTER_ATT = ENGINE_SCOPE + "pullEventEmitter"; //$NON-NLS-1$
   public static final String CONDITION_CONDITION_CLASS_ATT = ENGINE_SCOPE + "condition"; //$NON-NLS-1$
   public static final String CONDITION_BINDER_CLASS_ATT = ENGINE_SCOPE + "binder"; //$NON-NLS-1$

   public static final String ACTION_CLASS_ATT = ENGINE_SCOPE + "action"; //$NON-NLS-1$
   public static final String VALIDATOR_CLASS_ATT = ENGINE_SCOPE + "validator"; //$NON-NLS-1$
   public static final String EVALUATOR_CLASS_ATT = ENGINE_SCOPE + "evaluator"; //$NON-NLS-1$
   public static final String ACCESSPOINT_PROVIDER_ATT = ENGINE_SCOPE + "accessPointProvider"; //$NON-NLS-1$
   public static final String APPLICATION_INSTANCE_CLASS_ATT = ENGINE_SCOPE + "applicationInstance"; //$NON-NLS-1$
   public static final String PULL_TRIGGER_EVALUATOR_ATT = ENGINE_SCOPE + "pullTriggerEvaluator"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_ATTRIBUTE_NAME_ATT = ENGINE_SCOPE + "attributeName"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_ATTRIBUTE_PATH_ATT = ENGINE_SCOPE + "attributePath"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_DATA_ID_ATT = ENGINE_SCOPE + "dataId"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_DATA_PATH_ATT = ENGINE_SCOPE + "dataPath"; //$NON-NLS-1$
   public static final String EXCEPTION_CLASS_ATT = ENGINE_SCOPE + "exceptionName"; //$NON-NLS-1$
   public static final String TYPE_ATT = ENGINE_SCOPE + "type"; //$NON-NLS-1$
   public static final String DEFAULT_VALUE_ATT = ENGINE_SCOPE + "defaultValue"; //$NON-NLS-1$

   // @todo (france, ub): should be converted to a period
   public static final String TIMER_TRIGGER_START_TIMESTAMP_ATT = ENGINE_SCOPE + "startTime"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER_PERIODICITY_ATT = ENGINE_SCOPE + "periodicity"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER_STOP_TIMESTAMP_ATT = ENGINE_SCOPE + "stopTime"; //$NON-NLS-1$

   public static final String MAIL_TRIGGER_USER_ATT = ENGINE_SCOPE + "user"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_SERVER_ATT = ENGINE_SCOPE + "host"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_PASSWORD_ATT = ENGINE_SCOPE + "password"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_PROTOCOL_ATT = ENGINE_SCOPE + "protocol"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_FLAGS_ATT = ENGINE_SCOPE + "mailFlags"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_PREDICATE_SENDER_ATT = ENGINE_SCOPE + "mailSenderPredicate"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_PREDICATE_SUBJECT_ATT = ENGINE_SCOPE + "mailSubjectPredicate"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_PREDICATE_BODY_ATT = ENGINE_SCOPE + "selectorPredicate"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_MAILBOX_ACTION_ATT = ENGINE_SCOPE + "mailboxAction"; //$NON-NLS-1$

   public static final String EXCEPTION_ATT = ENGINE_SCOPE + "exception"; //$NON-NLS-1$

   public static final String SOURCE_STATE_ATT = ENGINE_SCOPE + "sourceState"; //$NON-NLS-1$

   public static final String OBSERVER_STATE_CHANGE_EVENT_ATT = ENGINE_SCOPE + "stateChange"; //$NON-NLS-1$
   public static final String ENDPOINT_REFERENCE_ATT = ENGINE_SCOPE + "endpointReference"; //$NON-NLS-1$
   public static final String RESULT_DATA_ATT = ENGINE_SCOPE + "resultData"; //$NON-NLS-1$
   public static final String OBSERVER_NOTIFICATION_TYPE = ENGINE_SCOPE + "notificationType"; //$NON-NLS-1$

   // exdesk attribute names
   public static final String RUNTIME_PANEL_ATT = ED_SCOPE + "runtimePanel"; //$NON-NLS-1$
   // @todo (france, ub): very questionable -->
   public static final String JFC_CONTEXT_INSTANCE_CLASS_ATT = ED_SCOPE + "instance"; //$NON-NLS-1$

   // webex attribute names
   public static final String HTML_PATH_ATT = WEBEX_SCOPE + "htmlPath"; //$NON-NLS-1$


   // defdesk attribute names
   public static final String ACCESSPATH_EDITOR_ATT = DD_SCOPE + "accessPathEditor"; //$NON-NLS-1$
   public static final String PANEL_CLASS_ATT = DD_SCOPE + "panel"; //$NON-NLS-1$

   // predefined implementation classes and icons
   public static final String PRIMITIVE_PANEL_CLASS = "ag.carnot.workflow.spi.providers.data.java.PrimitivePropertiesEditor"; //$NON-NLS-1$
   public static final String PRIMITIVE_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.PrimitiveAccessPathEvaluator"; //$NON-NLS-1$
   public static final String PRIMITIVE_ACCESSPATH_EDITOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.POJOAccessPathEditor"; //$NON-NLS-1$
   public static final String PRIMITIVE_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.PrimitiveValidator"; //$NON-NLS-1$
   public static final String PRIMITIVE_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/data/java/images/primitive_data.gif"; //$NON-NLS-1$

   public static final String SERIALIZABLE_PANEL_CLASS = "ag.carnot.workflow.spi.providers.data.java.SerializablePropertiesEditor"; //$NON-NLS-1$
   public static final String SERIALIZABLE_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.JavaBeanAccessPathEvaluator"; //$NON-NLS-1$
   public static final String SERIALIZABLE_ACCESSPATH_EDITOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.POJOAccessPathEditor"; //$NON-NLS-1$
   public static final String SERIALIZABLE_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.SerializableValidator"; //$NON-NLS-1$
   public static final String SERIALIZABLE_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/data/java/images/serializable_data.gif"; //$NON-NLS-1$

   public static final String ENTITYBEAN_PANEL_CLASS = "ag.carnot.workflow.spi.providers.data.entitybean.EntityBeanPropertiesEditor"; //$NON-NLS-1$
   public static final String ENTITYBEAN_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.data.entitybean.EntityBeanEvaluator"; //$NON-NLS-1$
   public static final String ENTITYBEAN_ACCESSPATH_EDITOR_CLASS = "ag.carnot.workflow.spi.providers.data.java.POJOAccessPathEditor"; //$NON-NLS-1$
   public static final String ENTITYBEAN_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.data.entitybean.EntityBeanValidator"; //$NON-NLS-1$
   public static final String ENTITYBEAN_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/data/entitybean/icon.gif"; //$NON-NLS-1$

   public static final String PLAINXML_PANEL_CLASS = "ag.carnot.workflow.spi.providers.data.plainxml.XMLDocumentPropertiesEditor"; //$NON-NLS-1$
   public static final String PLAINXML_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.data.plainxml.XPathEvaluator"; //$NON-NLS-1$
   public static final String PLAINXML_ACCESSPATH_EDITOR_CLASS = "ag.carnot.workflow.spi.providers.data.plainxml.XPathEditor"; //$NON-NLS-1$
   public static final String PLAINXML_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.data.plainxml.XMLValidator"; //$NON-NLS-1$
   public static final String PLAINXML_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/data/plainxml/icon.gif"; //$NON-NLS-1$

   public static final String SESSIONBEAN_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.sessionbean.SessionBeanApplicationInstance"; //$NON-NLS-1$
   public static final String SESSIONBEAN_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.sessionbean.SessionBeanValidator"; //$NON-NLS-1$
   public static final String SESSIONBEAN_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.sessionbean.SessionBeanApplicationPanel"; //$NON-NLS-1$
   public static final String SESSIONBEAN_ACCESSPOINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.applications.sessionbean.SessionBeanAccessPointProvider"; //$NON-NLS-1$
   public static final String SESSIONBEAN_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/sessionbean/icon.gif"; //$NON-NLS-1$

   public static final String PLAINJAVA_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.plainjava.PlainJavaApplicationInstance"; //$NON-NLS-1$
   public static final String PLAINJAVA_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.plainjava.PlainJavaValidator"; //$NON-NLS-1$
   public static final String PLAINJAVA_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.plainjava.PlainJavaApplicationPanel"; //$NON-NLS-1$
   public static final String PLAINJAVA_ACCESSPOINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.applications.plainjava.PlainJavaAccessPointProvider"; //$NON-NLS-1$
   public static final String PLAINJAVA_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/plainjava/icon.gif"; //$NON-NLS-1$

   public static final String JMS_APPLICATION_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.jms.JMSApplicationInstance"; //$NON-NLS-1$
   public static final String JMS_APPLICATION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.jms.JMSValidator"; //$NON-NLS-1$
   public static final String JMS_APPLICATION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.jms.JMSApplicationPanel"; //$NON-NLS-1$
   public static final String JMS_APPLICATION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/jms/icon.gif"; //$NON-NLS-1$

   public static final String WS_APPLICATION_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.ws.WebserviceApplicationInstance"; //$NON-NLS-1$
   public static final String WS_APPLICATION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.ws.WebserviceApplicationValidator"; //$NON-NLS-1$
   public static final String WS_APPLICATION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.ws.gui.WebserviceApplicationPanel"; //$NON-NLS-1$
   public static final String WS_APPLICATION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/ws/gui/icon.gif"; //$NON-NLS-1$

   public static final String WFXML_APPLICATION_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.wfxml.WfXMLApplicationInstance"; //$NON-NLS-1$
   public static final String WFXML_APPLICATION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.wfxml.WfXMLApplicationValidator"; //$NON-NLS-1$
   public static final String WFXML_APPLICATION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.wfxml.gui.WfXMLApplicationPanel"; //$NON-NLS-1$
   public static final String WFXML_APPLICATION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/wfxml/gui/icon.gif"; //$NON-NLS-1$

   public static final String JFC_CONTEXT_ACCESSPOINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.contexts.jfc.JFCAccessPointProvider"; //$NON-NLS-1$
   public static final String JFC_CONTEXT_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.contexts.jfc.JFCValidator"; //$NON-NLS-1$
   public static final String JFC_CONTEXT_PANEL_CLASS = "ag.carnot.workflow.spi.providers.contexts.jfc.JFCContextTypePanel"; //$NON-NLS-1$
   public static final String JFC_CONTEXT_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.contexts.jfc.JFCApplicationInstance"; //$NON-NLS-1$
   public static final String JFC_CONTEXT_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/contexts/jfc/icon.gif"; //$NON-NLS-1$

   public static final String DEFAULT_EVENT_EMITTER_CLASS = "ag.carnot.workflow.spi.runtime.DefaultPullEventEmitter"; //$NON-NLS-1$
   public static final String DEFAULT_EVENT_BINDER_CLASS = "ag.carnot.workflow.spi.runtime.DefaultEventBinder"; //$NON-NLS-1$

   public static final String EXCEPTION_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.exception.SetExceptionAction"; //$NON-NLS-1$
   public static final String EXCEPTION_ACTION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.exception.SetExceptionActionPropertiesPanel"; //$NON-NLS-1$

   public static final String SET_DATA_ACTION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.setdata.SetDataActionPropertiesPanel"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.actions.setdata.SetDataActionValidator"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.setdata.SetDataAction"; //$NON-NLS-1$
   public static final String SET_DATA_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/setdata/icon.gif"; //$NON-NLS-1$

   public static final String JSP_CONTEXT_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.contexts.jsp.JSPValidator"; //$NON-NLS-1$
   public static final String JSP_CONTEXT_PANEL_CLASS = "ag.carnot.workflow.spi.providers.contexts.jsp.JSPContextTypePanel"; //$NON-NLS-1$
   public static final String JSP_CONTEXT_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/contexts/jsp/icon.gif"; //$NON-NLS-1$

   public static final String MANUAL_TRIGGER_PANEL_CLASS = "ag.carnot.workflow.spi.providers.triggers.manual.ManualTriggerPanel"; //$NON-NLS-1$
   public static final String MANUAL_TRIGGER_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.manual.ManualTriggerValidator"; //$NON-NLS-1$
   public static final String MANUAL_TRIGGER_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/triggers/manual/icon.gif"; //$NON-NLS-1$

   public static final String JMS_TRIGGER_PANEL_CLASS = "ag.carnot.workflow.spi.providers.triggers.jms.JMSTriggerPanel"; //$NON-NLS-1$
   public static final String JMS_TRIGGER_MESSAGEACCEPTOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.jms.DefaultTriggerMessageAcceptor"; //$NON-NLS-1$
   public static final String JMS_TRIGGER_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.jms.JMSTriggerValidator"; //$NON-NLS-1$
   public static final String JMS_TRIGGER_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/triggers/jms/icon.gif"; //$NON-NLS-1$

   public static final String MAIL_TRIGGER_PANEL_CLASS = "ag.carnot.workflow.spi.providers.triggers.mail.MailTriggerPanel"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.mail.MailTriggerValidator"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.mail.MailTriggerEvaluator"; //$NON-NLS-1$
   public static final String MAIL_TRIGGER_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/triggers/mail/icon.gif"; //$NON-NLS-1$

   public static final String TIMER_TRIGGER_PANEL_CLASS = "ag.carnot.workflow.spi.providers.triggers.timer.TimerTriggerPanel"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.timer.TimerTriggerEvaluator"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.triggers.timer.TimerTriggerValidator"; //$NON-NLS-1$
   public static final String TIMER_TRIGGER_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/triggers/timer/icon.gif"; //$NON-NLS-1$

   public static final String TIMER_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.PeriodPropertiesPanel"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimerValidator"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_RULE_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimeStampCondition"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_RUNTIME_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimerbasedRuntimeBindPanel"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_BINDER_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimeStampBinder"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_EMITTER_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimeStampEmitter"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_ACCESSPOINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.conditions.timer.TimerAccessPointProvider"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/timer/icon.gif"; //$NON-NLS-1$

   public static final String TRIGGER_ACTION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.trigger.TriggerProcessActionPanel"; //$NON-NLS-1$
   public static final String TRIGGER_ACTION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.actions.trigger.TriggerActionValidator"; //$NON-NLS-1$
   public static final String TRIGGER_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.trigger.TriggerProcessAction"; //$NON-NLS-1$
   public static final String TRIGGER_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/trigger/icon.gif"; //$NON-NLS-1$

   public static final String MAIL_ACTION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.mail.SendmailActionPanel"; //$NON-NLS-1$
   public static final String MAIL_ACTION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.actions.mail.MailActionValidator"; //$NON-NLS-1$
   public static final String MAIL_ACTION_RULE_CLASS = "ag.carnot.workflow.spi.providers.actions.mail.SendmailAction"; //$NON-NLS-1$
   public static final String MAIL_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/mail/icon.gif"; //$NON-NLS-1$

   public static final String EXCEPTION_CONDITION_ACCESS_POINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.conditions.exception.ExceptionConditionAccessPointProvider"; //$NON-NLS-1$
   public static final String EXCEPTION_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.exception.ExceptionConditionPropertiesPanel"; //$NON-NLS-1$
   public static final String EXCEPTION_CONDITION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.conditions.exception.ExceptionConditionValidator"; //$NON-NLS-1$
   public static final String EXCEPTION_CONDITION_RULE_CLASS = "ag.carnot.workflow.spi.providers.conditions.exception.ExceptionCondition"; //$NON-NLS-1$
   public static final String EXCEPTION_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/exception/icon.gif"; //$NON-NLS-1$

   public static final String DELEGATE_ACTIVITY_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.delegate.DelegateEventAction"; //$NON-NLS-1$
   public static final String DELEGATE_ACTIVITY_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.delegate.DelegateEventActionPanel"; //$NON-NLS-1$
   public static final String DELEGATE_ACTIVITY_RUNTIME_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.delegate.DelegateEventActionRuntimePanel"; //$NON-NLS-1$
   public static final String DELEGATE_ACTIVITY_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/delegate/icon.gif"; //$NON-NLS-1$

   public static final String SCHEDULE_ACTIVITY_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.schedule.ScheduleEventAction"; //$NON-NLS-1$
   public static final String SCHEDULE_ACTIVITY_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.schedule.ScheduleEventActionPanel"; //$NON-NLS-1$
   public static final String SCHEDULE_ACTIVITY_RUNTIME_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.schedule.ScheduleEventActionRuntimePanel"; //$NON-NLS-1$
   public static final String SCHEDULE_ACTIVITY_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/schedule/icon.gif"; //$NON-NLS-1$

   public static final String EXPRESSION_CONDITION_CLASS = "ag.carnot.workflow.spi.providers.conditions.expression.ExpressionCondition"; //$NON-NLS-1$
   public static final String EXPRESSION_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.expression.ExpressionConditionPropertiesPanel"; //$NON-NLS-1$
   public static final String EXPRESSION_CONDITION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.conditions.expression.ExpressionConditionValidator"; //$NON-NLS-1$
   public static final String EXPRESSION_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/expression/icon.gif"; //$NON-NLS-1$

   public static final String STATECHANGE_CONDITION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.conditions.statechange.StatechangeConditionValidator"; //$NON-NLS-1$
   public static final String ACTIVITY_STATECHANGE_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.statechange.StatechangeConditionPropertiesPanel"; //$NON-NLS-1$
   public static final String ACTIVITY_STATECHANGE_CONDITION_RULE_CLASS = "ag.carnot.workflow.spi.providers.conditions.statechange.StatechangeCondition"; //$NON-NLS-1$
   public static final String ACTIVITY_STATECHANGE_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/statechange/icon.gif"; //$NON-NLS-1$

   public static final String PROCESS_STATECHANGE_CONDITION_RULE_CLASS = "ag.carnot.workflow.spi.providers.conditions.statechange.ProcessStatechangeCondition"; //$NON-NLS-1$
   public static final String PROCESS_STATECHANGE_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.statechange.ProcessStatechangeConditionPropertiesPanel"; //$NON-NLS-1$
   public static final String PROCESS_STATECHANGE_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/statechange/icon.gif"; //$NON-NLS-1$

   public static final String ACTIVITY_ON_ASSIGNMENT_CONDITION_PANEL_CLASS = "ag.carnot.workflow.spi.providers.conditions.assignment.AssignmentConditionPropertiesPanel"; //$NON-NLS-1$
   public static final String ACTIVITY_ON_ASSIGNMENT_CONDITION_ACCESS_POINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.conditions.assignment.AssignmentConditionAccessPointProvider"; //$NON-NLS-1$
   public static final String ACTIVITY_ON_ASSIGNMENT_CONDITION_RULE_CLASS = "ag.carnot.workflow.spi.providers.conditions.assignment.AssignmentCondition"; //$NON-NLS-1$
   public static final String ACTIVITY_ON_ASSIGNMENT_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/assignment/icon.gif"; //$NON-NLS-1$

   public static final String ACTIVATE_ACTIVITY_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.awake.AwakeActivityEventAction";; //$NON-NLS-1$
   public static final String ACTIVATE_ACTIVITY_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/awake/icon.gif"; //$NON-NLS-1$

   public static final String ABORT_PROCESS_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.abort.AbortProcessEventAction"; //$NON-NLS-1$
   public static final String ABORT_PROCESS_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/abort/icon.gif"; //$NON-NLS-1$

   public static final String NOTIFY_OBSERVERS_ACTION_CLASS = "ag.carnot.workflow.wfxml.spi.actions.NotifyObserversEventAction"; //$NON-NLS-1$
   public static final String NOTIFY_OBSERVERS_ACTION_ICON_LOCATION = "/ag/carnot/workflow/wfxml/spi/actions/icon.gif"; //$NON-NLS-1$

   public static final String COMPLETE_ACTIVITY_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.complete.CompleteActivityEventAction"; //$NON-NLS-1$
   public static final String COMPLETE_ACTIVITY_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/complete/icon.gif"; //$NON-NLS-1$

   public static final String EXCLUDE_USER_ACTION_CLASS = "ag.carnot.workflow.spi.providers.actions.excludeuser.ExcludeUserAction"; //$NON-NLS-1$
   public static final String EXCLUDE_USER_PANEL_CLASS = "ag.carnot.workflow.spi.providers.actions.excludeuser.ExcludeUserActionPanel"; //$NON-NLS-1$
   public static final String EXCLUDE_USER_ACTION_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.actions.excludeuser.ExcludeUserActionValidator"; //$NON-NLS-1$
   public static final String EXCLUDE_USER_ACTION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/actions/excludeuser/icon.gif"; //$NON-NLS-1$

   public static final String EXTERNAL_CONDITION_CLASS = "ag.carnot.workflow.spi.providers.conditions.simplepush.PushCondition"; //$NON-NLS-1$
   public static final String EXTERNAL_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/conditions/simplepush/icon.gif"; //$NON-NLS-1$

   public static final String OBSERVER_CONDITION_RULE_CLASS = "ag.carnot.workflow.wfxml.spi.conditions.ObserverNotificationCondition"; //$NON-NLS-1$
   public static final String OBSERVER_CONDITION_PANEL_CLASS = "ag.carnot.workflow.wfxml.spi.conditions.ObserverNotificationPropertiesPanel"; //$NON-NLS-1$
   public static final String OBSERVER_CONDITION_VALIDATOR_CLASS = "ag.carnot.workflow.wfxml.spi.conditions.ObserverNotificationValidator"; //$NON-NLS-1$
   public static final String OBSERVER_CONDITION_ACCESS_POINT_PROVIDER_CLASS = "ag.carnot.workflow.wfxml.spi.conditions.ObserverNotificationAccessPointProvider"; //$NON-NLS-1$
   public static final String OBSERVER_CONDITION_ICON_LOCATION = "/ag/carnot/workflow/wfxml/spi/conditions/icon.gif"; //$NON-NLS-1$

   // random constants
   // @todo (france, ub): exploit in the queryservice
   public static final int ACTIVE_MODEL = -10;
   public static final int LAST_DEPLOYED_MODEL = -20;
   public static final int ALIVE_MODELS = -30;
   public static final int ALL_MODELS = -40;

   // unsorted

   public static final String CONSTRUCTOR_NAME_ATT = ENGINE_SCOPE + "constructorName"; //$NON-NLS-1$
   public static final String ICON_ATT = DD_SCOPE + "icon"; //$NON-NLS-1$
   public static final String SAP_R3_DATA = "sapr3data"; //$NON-NLS-1$
   public static final String SAP_DATA_EVALUATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.sap.data.SapAccessPathEvaluator"; //$NON-NLS-1$
   public static final String SAP_DATA_ACCESSPATH_EDITOR_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.sap.data.SapAccessPathEditor"; //$NON-NLS-1$
   public static final String SAP_DATA_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.sap.data.SapDataTypePropertiesEditor"; //$NON-NLS-1$
   public static final String SAP_DATA_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.sap.data.SapDataValidator"; //$NON-NLS-1$
   public static final String SAP_DATA_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/jca/sap/data/icon.gif"; //$NON-NLS-1$
   public static final String SAP_R3_APPLICATION = "sapr3application"; //$NON-NLS-1$
   public static final String CCI_VALIDATOR_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.DefaultCCIValidator"; //$NON-NLS-1$
   public static final String CCI_PANEL_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.gui.DefaultCCIApplicationPropertiesPanel"; //$NON-NLS-1$
   public static final String CCI_APPLICATION_INSTANCE_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.DefaultCCIApplicationInstance"; //$NON-NLS-1$
   public static final String CCI_WRAPPER_CLASS_ATT = ENGINE_SCOPE + "CCIWrapper"; //$NON-NLS-1$
   public static final String SAP_CCI_WRAPPER_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.sap.application.SapCCIWrapper"; //$NON-NLS-1$
   public static final String SAP_CCI_ICON_LOCATION = "/ag/carnot/workflow/spi/providers/applications/jca/sap/application/icon.gif"; //$NON-NLS-1$
   public static final String CCI_ACCESSPOINT_PROVIDER_CLASS = "ag.carnot.workflow.spi.providers.applications.jca.DefaultCCIAccessPointProvider"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_USE_DATA_ATT = ENGINE_SCOPE + "useData"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_DATA_ATT = ENGINE_SCOPE + "data"; //$NON-NLS-1$
   public static final String TIMER_CONDITION_DATA_PATH_ATT = ENGINE_SCOPE + "dataPath"; //$NON-NLS-1$
   public static final String VALID_FROM_ATT = ENGINE_SCOPE + "validFrom"; //$NON-NLS-1$
   public static final String VALID_TO_ATT = ENGINE_SCOPE + "validTo"; //$NON-NLS-1$
   public static final String DEPLOYMENT_COMMENT_ATT = ENGINE_SCOPE + "deploymentComment"; //$NON-NLS-1$

   public static final String EVENT_ACCESS_POINT = ENGINE_SCOPE + "eventScope"; //$NON-NLS-1$
   public static final String MESSAGE_TYPE_ATT = ENGINE_SCOPE + "messageType"; //$NON-NLS-1$

   public static final String QUEUE_CONNECTION_FACTORY_NAME_PROPERTY = ENGINE_SCOPE + "queueConnectionFactory.jndiName"; //$NON-NLS-1$
   public static final String QUEUE_NAME_PROPERTY = ENGINE_SCOPE + "queue.jndiName"; //$NON-NLS-1$
   public static final String MESSAGE_PROVIDER_PROPERTY = ENGINE_SCOPE + "messageProvider"; //$NON-NLS-1$
   // @todo (france, ub): usage of this property is probably a misuse because the acceptor is hardwired to the application type??
   public static final String MESSAGE_ACCEPTOR_PROPERTY = ENGINE_SCOPE + "messageAcceptor"; //$NON-NLS-1$
   public static final String JMS_LOCATION_PROPERTY = ENGINE_SCOPE + "jms.location"; //$NON-NLS-1$
   public static final String REQUEST_MESSAGE_TYPE_PROPERTY = ENGINE_SCOPE + "requestMessageType"; //$NON-NLS-1$
   public static final String RESPONSE_MESSAGE_TYPE_PROPERTY = ENGINE_SCOPE + "responseMessageType"; //$NON-NLS-1$
   public static final String INCLUDE_OID_HEADERS_PROPERTY = ENGINE_SCOPE + "includeOidHeaders"; //$NON-NLS-1$
   public static final String EXCLUDE_PERFORMER = ENGINE_SCOPE + "excludePerformer"; //$NON-NLS-1$
   public static final String EXCLUDED_PERFORMER_DATA = ENGINE_SCOPE + "excludedPerformerData"; //$NON-NLS-1$
   public static final String EXCLUDED_PERFORMER_DATAPATH = ENGINE_SCOPE + "excludedPerformerDataPath";; //$NON-NLS-1$
   public static final String ACTIVITY_INSTANCE_ACCESSPOINT = "activityInstance"; //$NON-NLS-1$

   public static final String SOURCE_USER_ATT = ENGINE_SCOPE + "sourceUser"; //$NON-NLS-1$
   public static final String TARGET_USER_ATT = ENGINE_SCOPE + "targetUser"; //$NON-NLS-1$
   public static final String VERSION_ATT = ENGINE_SCOPE + "version"; //$NON-NLS-1$
   public static final String REVISION_ATT = ENGINE_SCOPE + "revision"; //$NON-NLS-1$
   public static final String IS_RELEASED_ATT = ENGINE_SCOPE + "released"; //$NON-NLS-1$
   public static final String RELEASE_STAMP = ENGINE_SCOPE + "releaseStamp"; //$NON-NLS-1$
   public static final String DEPLOYMENT_TIME_ATT = ENGINE_SCOPE + "deploymentStamp"; //$NON-NLS-1$
   public static final String PREDECESSOR_ATT = ENGINE_SCOPE + "predecessor"; //$NON-NLS-1$
   public static final String IS_DISABLED_ATT = ENGINE_SCOPE + "disabled"; //$NON-NLS-1$
   public static final String PLAIN_WEB_SERVICEFACTORY_CLASS = "ag.carnot.web.PlainWebServiceFactory"; //$NON-NLS-1$
   public static final String REMOTE_WEB_SERVICEFACTORY_CLASS = "ag.carnot.web.RemoteWebServiceFactory"; //$NON-NLS-1$
   public static final String POJO_SERVICEFACTORY_CLASS = "ag.carnot.workflow.runtime.beans.POJOServiceFactory"; //$NON-NLS-1$
   public static final String INTERNAL_CREDENTIALPROVIDER_CLASS = "ag.carnot.workflow.runtime.InternalCredentialProvider"; //$NON-NLS-1$
   public static final String DEFAULT_SERVICEFACTORY_POOL_CLASS = "ag.carnot.workflow.runtime.beans.ThreadLocalServiceFactoryPool"; //$NON-NLS-1$
//   public static final String DEFAULT_SERVICEFACTORY_POOL_CLASS = "ag.carnot.workflow.runtime.beans.DefaultServiceFactoryPool";

   public static final String SUBPROCESS_ACTIVITY_COPY_ALL_DATA_ATT = ENGINE_SCOPE + "subprocess:copyAllData"; //$NON-NLS-1$

   // Controlling / Warehouse
   public static final String PWH_MEASURE = PWH_SCOPE + "measure"; //$NON-NLS-1$
   public static final String PWH_TARGET_MEASURE_QUANTITY = PWH_SCOPE + "targetMeasureQuantity"; //$NON-NLS-1$
   public static final String PWH_DIFFICULTY = PWH_SCOPE + "difficulty"; //$NON-NLS-1$
   public static final String PWH_TARGET_PROCESSING_TIME = PWH_SCOPE + "targetProcessingTime"; //$NON-NLS-1$
   public static final String PWH_TARGET_EXECUTION_TIME = PWH_SCOPE + "targetExecutionTime"; //$NON-NLS-1$
   public static final String PWH_TARGET_IDLE_TIME = PWH_SCOPE + "targetIdleTime"; //$NON-NLS-1$
   public static final String PWH_TARGET_WAITING_TIME = PWH_SCOPE + "targetWaitingTime"; //$NON-NLS-1$
   public static final String PWH_TARGET_QUEUE_DEPTH = PWH_SCOPE + "targetQueueDepth"; //$NON-NLS-1$
   public static final String PWH_TARGET_COST_PER_EXECUTION = PWH_SCOPE + "targetCostPerExecution"; //$NON-NLS-1$
   public static final String PWH_TARGET_COST_PER_SECOND = PWH_SCOPE + "targetCostPerSecond"; //$NON-NLS-1$
   public static final String PWH_COST_DRIVER = PWH_SCOPE + "costDriver"; //$NON-NLS-1$
   public static final String PWH_TARGET_COST_DRIVER_QUANTITY = PWH_SCOPE + "costDriverQuantity"; //$NON-NLS-1$
//   public static final String PWH_PROCESS_CATEGORY = PWH_SCOPE + "processCategory";
   public static final String PWH_WORKING_WEEKS_PER_YEAR = PWH_SCOPE + "workingWeeksPerYear"; //$NON-NLS-1$
   public static final String PWH_ACTUAL_COST_PER_MINUTE = PWH_SCOPE + "actualCostPerMinute"; //$NON-NLS-1$
   public static final String PWH_TARGET_WORK_TIME_PER_DAY = PWH_SCOPE + "targetWorkTimePerDay"; //$NON-NLS-1$
   public static final String PWH_TARGET_WORK_TIME_PER_WEEK = PWH_SCOPE + "targetWorkTimePerWeek"; //$NON-NLS-1$
   public static final String PWH_COST_CENTER = PWH_SCOPE + "costCenter"; //$NON-NLS-1$
   public static final String PWH_ACTUAL_COST_PER_SECOND = PWH_SCOPE + "actualCostPerSecond"; //$NON-NLS-1$

   public static final String PLAINXML_SCHEMA_TYPE_ATT = ENGINE_SCOPE + "schemaType"; //$NON-NLS-1$
   public static final String PLAINXML_SCHEMA_TYPE_NONE = "none"; //$NON-NLS-1$
   public static final String PLAINXML_SCHEMA_TYPE_DTD = "dtd"; //$NON-NLS-1$
   public static final String PLAINXML_SCHEMA_TYPE_XSD = "xsd"; //$NON-NLS-1$
   public static final String PLAINXML_SCHEMA_TYPE_WSDL = "wsdl"; //$NON-NLS-1$

   public static final String PLAINXML_SCHEMA_URL_ATT = ENGINE_SCOPE + "schemaURL"; //$NON-NLS-1$
   public static final String PLAINXML_TYPE_ID_ATT = ENGINE_SCOPE + "typeId"; //$NON-NLS-1$
   public static final String WFXML_EPR_PARAMETER = "parameter"; //$NON-NLS-1$
   public static final String WFXML_EPR_PROPERTY = "property"; //$NON-NLS-1$
   
   private static final String[] META_DATA_IDS_ARRAY = {STARTING_USER, ROOT_PROCESS_ID, CURRENT_USER,
      LAST_ACTIVITY_PERFORMER, CURRENT_DATE, PROCESS_ID, PROCESS_PRIORITY, CURRENT_LOCALE, CURRENT_MODEL};
   
   public static final List META_DATA_IDS = Collections.unmodifiableList(Arrays.asList(META_DATA_IDS_ARRAY));
   
   
   private PredefinedConstants() {
      //disallow instance creation
   }
   
}
