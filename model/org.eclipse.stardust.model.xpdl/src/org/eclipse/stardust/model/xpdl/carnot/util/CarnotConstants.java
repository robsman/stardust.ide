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
package org.eclipse.stardust.model.xpdl.carnot.util;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public interface CarnotConstants
{
   // implicit access points
   String ACTIVITY_INSTANCE_ACCESSPOINT_ID = "activityInstance"; //$NON-NLS-1$
   String ACTIVITY_INSTANCE_ACCESSPOINT_NAME = "Activity Instance"; //$NON-NLS-1$

   String ENGINE_SCOPE = PredefinedConstants.ENGINE_SCOPE;
   String CLASS_NAME_ATT = ENGINE_SCOPE + "className"; //$NON-NLS-1$
   String METHOD_NAME_ATT = ENGINE_SCOPE + "methodName"; //$NON-NLS-1$
   String CONSTRUCTOR_NAME_ATT = ENGINE_SCOPE + "constructorName"; //$NON-NLS-1$
   String CREATE_METHOD_NAME_ATT = ENGINE_SCOPE + "createMethodName"; //$NON-NLS-1$
   String ENTITY_MANAGER_SOURCE_ATT = ENGINE_SCOPE + "emSource"; //$NON-NLS-1$
   String TYPE_ATT = ENGINE_SCOPE + "type"; //$NON-NLS-1$
   String DEFAULT_VALUE_ATT = ENGINE_SCOPE + "defaultValue"; //$NON-NLS-1$
   String FLAVOR_ATT = ENGINE_SCOPE + "flavor"; //$NON-NLS-1$
   String BROWSABLE_ATT = ENGINE_SCOPE + "browsable"; //$NON-NLS-1$
   String HOME_INTERFACE_ATT = ENGINE_SCOPE + "homeInterface"; //$NON-NLS-1$
   String IS_LOCAL_ATT = ENGINE_SCOPE + "isLocal"; //$NON-NLS-1$
   String JNDI_PATH_ATT = ENGINE_SCOPE + "jndiPath"; //$NON-NLS-1$
   String PRIMARY_KEY_ATT = ENGINE_SCOPE + "primaryKey"; //$NON-NLS-1$
   String PRIMARY_KEY_TYPE_ATT = ENGINE_SCOPE + "primaryKeyType"; //$NON-NLS-1$
   String PRIMARY_KEY_ELEMENTS_ATT = ENGINE_SCOPE + "primaryKeyElements"; //$NON-NLS-1$
   String REMOTE_INTERFACE_ATT = ENGINE_SCOPE + "remoteInterface"; //$NON-NLS-1$
   String SCHEMA_TYPE_ATT = ENGINE_SCOPE + "schemaType"; //$NON-NLS-1$
   String SCHEMA_URL_ATT = ENGINE_SCOPE + "schemaURL"; //$NON-NLS-1$
   String STATE_CHANGE_ATT = ENGINE_SCOPE + "stateChange"; //$NON-NLS-1$
   String EXCEPTION_NAME_ATT = ENGINE_SCOPE + "exceptionName"; //$NON-NLS-1$
   String SOURCE_STATE_ATT = ENGINE_SCOPE + "sourceState"; //$NON-NLS-1$
   String TARGET_STATE_ATT = ENGINE_SCOPE + "targetState"; //$NON-NLS-1$
   String TIMER_PERIOD_ATT = PredefinedConstants.TIMER_PERIOD_ATT;
   String TIMER_USE_DATA_ATT = PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT;
   String TARGET_ATT = ENGINE_SCOPE + "target"; //$NON-NLS-1$
   String JMS_LOCATION_ATT = ENGINE_SCOPE + "jms.location"; //$NON-NLS-1$
   String EXCLUDED_PERFORMER_DATA_ATT = ENGINE_SCOPE + "excludedPerformerData"; //$NON-NLS-1$
   String EXCLUDED_PERFORMER_DATA_PATH_ATT = ENGINE_SCOPE + "excludedPerformerDataPath"; //$NON-NLS-1$
   String TARGET_WORKLIST_ATT = ENGINE_SCOPE + "targetWorklist"; //$NON-NLS-1$
   String WORKFLOW_RUNTIME = "ag.carnot.workflow.runtime."; //$NON-NLS-1$
   String WORKFLOW_SPI_PROVIDER = "ag.carnot.workflow.spi.providers."; //$NON-NLS-1$
   String ACTIVITY_INSTANCE_STATE_ATT = WORKFLOW_RUNTIME + "ActivityInstanceState"; //$NON-NLS-1$
   String PROCESS_INSTANCE_STATE_ATT = WORKFLOW_RUNTIME + "ProcessInstanceState"; //$NON-NLS-1$
   String TARGET_WORKLIST_TYPE_ATT = WORKFLOW_SPI_PROVIDER + "actions.delegate.TargetWorklist"; //$NON-NLS-1$
   String START_ACTIVITY_ATTR = ENGINE_SCOPE + "controlflow.startActivity"; //$NON-NLS-1$
   String END_ACTIVITY_ATTR = ENGINE_SCOPE + "controlflow.endActivity"; //$NON-NLS-1$
   String MAPPING_PREFIX = ENGINE_SCOPE + "mapping:"; //$NON-NLS-1$
   String TEMPLATE_PREFIX = ENGINE_SCOPE + "template:"; //$NON-NLS-1$
   String VERSION_ATT = ENGINE_SCOPE + "version"; //$NON-NLS-1$
   String ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT = ENGINE_SCOPE + "subprocess:copyAllData"; //$NON-NLS-1$

   // extension point ids
   String ACTION_TYPES_EXTENSION_POINT_ID = "actionTypes"; //$NON-NLS-1$
   String APPLICATION_TYPES_EXTENSION_POINT_ID = "applicationTypes"; //$NON-NLS-1$
   String CONDITION_TYPES_EXTENSION_POINT_ID = "conditionTypes"; //$NON-NLS-1$
   String CONTEXT_TYPES_EXTENSION_POINT_ID = "contextTypes"; //$NON-NLS-1$
   String DATA_TYPES_EXTENSION_POINT_ID = "dataTypes"; //$NON-NLS-1$
   String MESSAGE_ACCEPTORS_EXTENSION_POINT_ID = "acceptorProviders"; //$NON-NLS-1$
   String MESSAGE_PROVIDERS_EXTENSION_POINT_ID = "messageProviders"; //$NON-NLS-1$
   String TRIGGER_MESSAGE_ACCEPTORS_EXTENSION_POINT_ID = "triggerMessageAcceptors"; //$NON-NLS-1$
   String TRIGGER_TYPES_EXTENSION_POINT_ID = "triggerTypes"; //$NON-NLS-1$
   String RESOURCE_RESOLVER_EXTENSION_POINT_ID = "resourceResolver"; //$NON-NLS-1$

   String DIAGRAM_PLUGIN_ID = "org.eclipse.stardust.modeling.core"; //$NON-NLS-1$
   String STARDUST_XPDL_PLUGIN_ID = "org.eclipse.stardust.model.xpdl"; //$NON-NLS-1$
   String CARNOT_PLUGIN_ID = "org.eclipse.stardust.model.xpdl.carnot"; //$NON-NLS-1$

   String PRIMITIVE_DATA_ID = PredefinedConstants.PRIMITIVE_DATA;
   String SERIALIZABLE_DATA_ID = PredefinedConstants.SERIALIZABLE_DATA;
   String ENTITY_BEAN_DATA_ID = PredefinedConstants.ENTITY_BEAN_DATA;
   String HIBERNATE_DATA_ID = PredefinedConstants.HIBERNATE_DATA;
   String PLAIN_XML_DATA_ID = PredefinedConstants.PLAIN_XML_DATA;
   String STRUCTURED_DATA_ID = PredefinedConstants.STRUCTURED_DATA;

   String PLAIN_JAVA_APPLICATION_ID = PredefinedConstants.PLAINJAVA_APPLICATION;
   String SESSION_BEAN_APPLICATION_ID = PredefinedConstants.SESSIONBEAN_APPLICATION;
   String JMS_APPLICATION_ID = PredefinedConstants.JMS_APPLICATION;
   String SPRING_BEAN_APPLICATION_ID = PredefinedConstants.SPRINGBEAN_APPLICATION;
   String WEB_SERVICE_APPLICATION_ID = PredefinedConstants.WS_APPLICATION;

   // implicit context ids
   String DEFAULT_CONTEXT_ID = PredefinedConstants.DEFAULT_CONTEXT;
   String ENGINE_CONTEXT_ID = PredefinedConstants.ENGINE_CONTEXT;
   String APPLICATION_CONTEXT_ID = PredefinedConstants.APPLICATION_CONTEXT;
   String PROCESSINTERFACE_CONTEXT_ID = PredefinedConstants.PROCESSINTERFACE_CONTEXT;

   // explicit context ids
   String CASABAC_CONTEXT_ID = "Casabac"; //$NON-NLS-1$
   String JFC_CONTEXT_ID = PredefinedConstants.JFC_CONTEXT;
   String JSP_CONTEXT_ID = PredefinedConstants.JSP_CONTEXT;
   String JSF_CONTEXT_ID = PredefinedConstants.JSF_CONTEXT;   

   String JMS_TRIGGER_ID = PredefinedConstants.JMS_TRIGGER;
   String MANUAL_TRIGGER_ID = PredefinedConstants.MANUAL_TRIGGER;
   String MAIL_TRIGGER_ID = PredefinedConstants.MAIL_TRIGGER;
   String TIMER_TRIGGER_ID = PredefinedConstants.TIMER_TRIGGER;

   String EXCEPTION_CONDITION_ID = "exception"; //$NON-NLS-1$

   String SET_DATA_ACTION_ID = PredefinedConstants.SET_DATA_ACTION;
   String SEND_MAIL_ACTION_ID = PredefinedConstants.MAIL_ACTION;
   String EXCLUDE_USER_ACTION_ID = PredefinedConstants.EXCLUDE_USER_ACTION;
   String DATA_TYPE_HINT_ATT = ENGINE_SCOPE + "typeHint"; //$NON-NLS-1$
   String NUMERIC_HINT = "numeric"; //$NON-NLS-1$
   String COMPLEX_HINT = "complex"; //$NON-NLS-1$
   String TEXT_HINT = "text"; //$NON-NLS-1$
   
   String AUTO_ID_GENERATION = "auto_id_generation"; //$NON-NLS-1$   
}