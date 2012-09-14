/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.utils;

import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class ModelerConstants
{
   public static final String DATA_ID_PROPERTY = "dataId";
   public static final String DATA_NAME_PROPERTY = "dataName";
   public static final String DATA_FULL_ID_PROPERTY = "dataFullId";
   public static final String DATA_PROPERTY = "data";
   public static final String PRIMITIVE_DATA_TYPE_KEY = "primitive";
   public static final String PRIMITIVE_TYPE = "primitiveType";
   public static final String STRING_PRIMITIVE_DATA_TYPE = "string";
   public static final String DATE_PRIMITIVE_DATA_TYPE = "date";
   public static final String INTEGER_PRIMITIVE_DATA_TYPE = "integer";
   public static final String BOOLEAN_PRIMITIVE_DATA_TYPE = "boolean";
   public static final String DOUBLE_PRIMITIVE_DATA_TYPE = "double";
   public static final String DECIMAL_PRIMITIVE_DATA_TYPE = "decimal";
   public static final String STRUCTURED_DATA_TYPE_KEY = "struct";
   public static final String DOCUMENT_DATA_TYPE_KEY = "dmsDocument";
   public static final String MANUAL_ACTIVITY = "Manual";
   public static final String APPLICATION_ACTIVITY = "Application";
   public static final String SUBPROCESS_ACTIVITY = "Subprocess";
   public static final String ACTIVITY_TYPE = "activityType";
   public static final String MODEL_ELEMENT_PROPERTY = "modelElement";
   public static final String PARTICIPANT_FULL_ID = "participantFullId";
   public static final String SUBPROCESS_ID = "subprocessFullId";
   public static final String APPLICATION_FULL_ID_PROPERTY = "applicationFullId";
   public static final String NAME_PROPERTY = "name";
   public static final String ID_PROPERTY = "id";
   public static final String DEF_LANE_ID = "DefaultLane";
   public static final String DEF_LANE_NAME = "Default Lane";
   public static final String ORIENTATION_PROPERTY = "orientation";
   public static final String DIAGRAM_FLOW_ORIENTATION_HORIZONTAL = "DIAGRAM_FLOW_ORIENTATION_HORIZONTAL";
   public static final String DIAGRAM_FLOW_ORIENTATION_VERTICAL = "DIAGRAM_FLOW_ORIENTATION_VERTICAL";
   public static final String NULL_VALUE = "null";
   public static final String DIRECTORY_MODE = "DIRECTORY_MODE";
   public static final String SINGLE_FILE_MODE = "SINGLE_FILE_MODE";
   public static final String TYPE_PROPERTY = "type";
   public static final String FILE_NAME = "fileName";
   public static final String FILE_PATH = "filePath";
   public static final String ATTRIBUTES_PROPERTY = "attributes";
   public static final String OID_PROPERTY = "oid";
   public static final String UUID_PROPERTY = "uuid";
   public static final String MODEL_UUID_PROPERTY = "modelUUID";
   public static final String PARENT_UUID_PROPERTY = "parentUUID";
   public static final String NEW_OBJECT_PROPERTY = "newObject";
   public static final String OLD_OBJECT_PROPERTY = "oldObject";
   public static final String X_PROPERTY = "x";
   public static final String Y_PROPERTY = "y";
   public static final String WIDTH_PROPERTY = "width";
   public static final String HEIGHT_PROPERTY = "height";
   public static final String DESCRIPTION_PROPERTY = "description";
   public static final String MODEL_ID_PROPERTY = "modelId";
   public static final String PARENT_SYMBOL_ID_PROPERTY = "parentSymbolId";
   public static final String ACTIVITIES_PROPERTY = "activities";
   public static final String GATEWAYS_PROPERTY = "gateways";
   public static final String EVENTS_PROPERTY = "events";
   public static final String ACTIVITY_KEY = "activity";
   public static final String PROCESS_KEY = "process";
   public static final String MODEL_KEY = "model";
   public static final String APPLICATION_KEY = "application";
   public static final String ACTIVITY_SYMBOLS = "activitySymbols";
   public static final String GATEWAY_SYMBOLS = "gatewaySymbols";
   public static final String APPLICATION_TYPE_PROPERTY = "applicationType";
   public static final String ACCESS_POINTS_PROPERTY = "accessPoints";
   public static final String IN_ACCESS_POINT_KEY = "IN_ACCESS_POINT";
   public static final String OUT_ACCESS_POINT_KEY = "OUT_ACCESS_POINT";
   public static final String INOUT_ACCESS_POINT_KEY = "INOUT_ACCESS_POINT";
   public static final String ACCESS_POINT_TYPE_PROPERTY = "accessPointType";
   public static final String PRIMITIVE_ACCESS_POINT_KEY = "PRIMITIVE_ACCESS_POINT";
   public static final String DATA_STRUCTURE_ACCESS_POINT_KEY = "DATA_STRUCTURE_ACCESS_POINT";
   public static final String JAVA_CLASS_ACCESS_POINT_KEY = "JAVA_CLASS_ACCESS_POINT";
   public static final String ANY_ACCESS_POINT_KEY = "ANY_ACCESS_POINT";
   public static final String CONNECTION = "connection";
   public static final String DIRECTION_PROPERTY = "direction";
   public static final String CONTROL_FLOW_LITERAL = "controlFlow";
   public static final String DATA_FLOW_LITERAL = "dataFlow";
   public static final String ACTIVITY_ID_PROPERTY = "activityId";
   public static final String FROM_ANCHOR_POINT_ORIENTATION_PROPERTY = "fromAnchorPointOrientation";
   public static final String TO_ANCHOR_POINT_ORIENTATION_PROPERTY = "toAnchorPointOrientation";
   public static final int UNDEFINED_ORIENTATION_KEY = -1;
   public static final int NORTH_KEY = 0;
   public static final int EAST_KEY = 1;
   public static final int SOUTH_KEY = 2;
   public static final int WEST_KEY = 3;
   public static final String GATEWAY = "gateway";
   public static final String GATEWAY_ACTIVITY = "Gateway";
   public static final String GATEWAY_TYPE_PROPERTY = "gatewayType";
   public static final String AND_GATEWAY_TYPE = "and";
   public static final String XOR_GATEWAY_TYPE = "xor";
   public static final String OR_GATEWAY_TYPE = "or";
   public static final String EVENT_KEY = "event";
   public static final String EVENT_SYMBOLS = "eventSymbols";
   public static final String EVENT_TYPE_PROPERTY = "eventType";
   public static final String START_EVENT = "startEvent";
   public static final String STOP_EVENT = "stopEvent";
   public static final String DATA = "data";
   public static final String DATA_SYMBOLS = "dataSymbols";
   public static final String STRUCTURED_DATA_TYPE_FULL_ID_PROPERTY = "structuredDataTypeFullId";
   public static final String TYPE_DECLARATION_PROPERTY = "typeDeclaration";
   public static final String CONNECTIONS_PROPERTY = "connections";
   public static final String CONTROL_FLOWS_PROPERTY = "controlFlows";
   public static final String DATA_FLOWS_PROPERTY = "dataFlows";
   public static final String CONDITION_EXPRESSION_PROPERTY = "conditionExpression";
   public static final String IN_DATA_MAPPING_PROPERTY = "inDataMapping";
   public static final String OUT_DATA_MAPPING_PROPERTY = "outDataMapping";
   public static final String DATA_PATH_PROPERTY = "dataPath";
   public static final String APPLICATION_PATH_PROPERTY = "applicationPath";
   public static final String OTHERWISE_PROPERTY = "otherwise";
   public static final String CONDITION_KEY = "CONDITION";
   public static final String OTHERWISE_KEY = "OTHERWISE";
   public static final String POOL_SYMBOLS = "poolSymbols";
   public static final String LANE_SYMBOLS = "laneSymbols";
   public static final String FROM_MODEL_ELEMENT_OID = "fromModelElementOid";
   public static final String FROM_MODEL_ELEMENT_TYPE = "fromModelElementType";
   public static final String TO_MODEL_ELEMENT_OID = "toModelElementOid";
   public static final String TO_MODEL_ELEMENT_TYPE = "toModelElementType";
   public static final String WEB_SERVICE_APPLICATION_TYPE_ID = "webservice";
   public static final String MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID = "messageTransformationBean";
   public static final String CAMEL_APPLICATION_TYPE_ID = "camelBean";
   public static final String MAIL_APPLICATION_TYPE_ID = "mailBean";
   public static final String INTERACTIVE_APPLICATION_TYPE_KEY = "interactive";
   public static final String CONTEXTS_PROPERTY = "contexts";
   public static final String JSF_CONTEXT_TYPE_KEY = "jsf";
   public static final String EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY = "externalWebApp";
   public static final String PARTICIPANT_TYPE_PROPERTY = "participantType";
   public static final String ROLE_PARTICIPANT_TYPE_KEY = "roleParticipant";
   public static final String TEAM_LEADER_KEY = "isTeamLeader";
   public static final String CHILD_PARTICIPANTS_KEY = "childParticipants";
   public static final String ORGANIZATION_PARTICIPANT_TYPE_KEY = "organizationParticipant";
   public static final String CONDITIONAL_PERFORMER_PARTICIPANT_TYPE_KEY = "conditionalPerformerParticipant";

   // @deprecated
   public static final int POOL_LANE_MARGIN = 5;
   // @deprecated
   public static final int POOL_SWIMLANE_TOP_BOX_HEIGHT = 20;
   /* Half the size of the review why this adjustment is needed start event symbol used in Pepper
    * TODO - may need to be handled on the client side down the line.
    * @deprecated */
   public static final int START_END_SYMBOL_LEFT_OFFSET = 12;
   // Added to set symbol type at Marshaller same as client side
   public static final String SWIMLANE_SYMBOL = "swimlaneSymbol";
   public static final String ACTIVITY_SYMBOL = "activitySymbol";
   public static final String GATEWAY_SYMBOL = "gateSymbol";
   public static final String EVENT_SYMBOL = "eventSymbol";
   public static final String DATA_SYMBOL = "dataSymbol";
   public static final String DESCRIPTOR_PROPERTY = "descriptor";
   public static final String KEY_DESCRIPTOR_PROPERTY = "keyDescriptor";
   public static final String DATA_TYPE_PROPERTY = "dataType";
   public static final String PRIMITIVE_DATA_TYPE_PROPERTY = "primitiveDataType";
   public static final String DEFAULT_PRIORITY_PROPERTY = "defaultPriority";
   public static final String FORK_ON_TRAVERSAL_PROPERTY = "forkOnTraversal";
   public static final String DATA_PATHES_PROPERTY = "dataPathes";
   public static final String BINDING_DATA_PATH_PROPERTY = "dataPath";
   public static final String BINDING_DATA_FULL_ID_PROPERTY = "dataFullId";
   public static final String CONTEXT_PROPERTY = "context";
   public static final String PROCESS_INTERFACE_TYPE_PROPERTY = "processInterfaceType";
   public static final String NO_PROCESS_INTERFACE_KEY = "noInterface";
   public static final String PROVIDES_PROCESS_INTERFACE_KEY = "providesProcessInterface";
   public static final String IMPLEMENTS_PROCESS_INTERFACE_KEY = "implementsProcessInterface";
   public static final String FORMAL_PARAMETERS_PROPERTY = "formalParameters";
   public static final String TEAM_LEAD_FULL_ID_PROPERTY = "teamLeadFullId";
   public static final String SUBPROCESS_MODE_PROPERTY = "subprocessMode";
   public static final String SYNC_SEPARATE_KEY = "synchSeparate";
   public static final String ASYNC_SEPARATE_KEY = "asynchSeparate";
   public static final String SYNC_SHARED_KEY = "synchShared";
   public static final String IN_PARAMETER_KEY = "in";
   public static final String INOUT_PARAMETER_KEY = "inOut";
   public static final String OUT_PARAMETER_KEY = "out";
   public static final String ACCESS_POINT_ID_PROPERTY = "accessPointId";
   public static final String ACCESS_POINT_CONTEXT_PROPERTY = "accessPointId";
}
