package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.osgi.util.NLS;

/**
 * @author Simon Nikles
 *
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.runtime.stardust.composites.messages";

	public static String StardustDataAssignmentDetailComposite_From_Title;
	public static String StardustDataAssignmentDetailComposite_To_Title;

	public static String compositeTitle_camelConsumerServiceConfiguration;
	public static String compositeTitle_camelProducerServiceConfiguration;
	public static String compositeTitle_camelProducerServiceConfigurationIO;

	public static String composite_trigger_section_AccessPoints;
	public static String composite_trigger_section_AccessPoints_Outputs;

	public static String composite_application_section_AccessPoints;
	public static String composite_application_section_AccessPoints_Inputs;
	public static String composite_application_section_AccessPoints_Outputs;
	public static String composite_application_section_AccessPoints_col_PrimitiveType;
	public static String composite_application_section_AccessPoint_select_primitiveData;
	public static String composite_application_section_AccessPoint_select_structData;
	public static String composite_application_section_AccessPoint_select_serializableData;

	public static String composite_application_section_AccessPoint_select_dataType;

	public static String composite_resource_conditionalPerformer;
	public static String composite_resource_performer;

	public static String composite_startEvent_trigger_radio_Api;
	public static String composite_startEvent_trigger_radio_Manual;

	public static String composite_startEvent_toggle_noTransform;


	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

}
