package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels;

import org.eclipse.osgi.util.NLS;

/**
 * Labels for Stardust Attributes
 *
 * @author Simon Nikles
 *
 */
public class Labels extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.labels";

	public static String element_Name;
	public static String element_Id;

	public static String common_Visibility;

	public static String camel_invocationType;
	public static String camel_invocationPattern;
	public static String camel_camelContextId;
	public static String camel_supportMultipleAccessPoints;
	public static String camel_transactedRoute;
	public static String camel_consumerRoute;
	public static String camel_processContextHeaders;
	public static String camel_routeEntries;
	public static String camel_inBodyAccessPoint;
	public static String camel_outBodyAccessPoint;
	public static String camel_additionalSpringBeanDefinitions;

	public static String synchronous_retry_enable;
	public static String synchronous_retry_number;
	public static String synchronous_retry_time;

	public static String jms_Direction;
	public static String jms_queueConnectionFactory_jndiName;
	public static String jms_requestMessageType;
	public static String jms_responseMessageType;
	public static String jms_includeOidHeaders;
	public static String jms_queue_jndiName;
	public static String jms_messageProvider;
	public static String jms_messageAcceptor;
	public static String jms_messageType;
	public static String jms_accesspointLocation;
	public static String jms_accesspointDefaultValue;

	public static String accessPoint_root;
	public static String accessPoint_primitive_type;
	public static String accessPoint_serializable_className;
	public static String accessPoint_structured_dataType;
	public static String accessPoint_path_separator;
	public static String accessPoint_bidirectional;

	public static String spring_beanId;
	public static String spring_className;
	public static String spring_methodName;

	public static String java_className;
	public static String java_methodName;
	public static String java_constructorName;

	public static String webapp_uri;

	public static String resource_data;
	public static String resource_dataPath;
	public static String resource_realmData;
	public static String resource_realmDataPath;

	public static String conditionalPerformerKind;

	public static String trigger_type_jms;

	public static String stardust_participant;

	public static String model_stardust_target_version;
	public static String model_stardust_model_version;

	public static String process_attachment_unique_per_root;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Labels.class);
	}

	private Labels() {
	}

}
