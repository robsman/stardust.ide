package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.CLASS_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.CONSTRUCTOR_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.METHOD_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.RETRY_ENABLE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.RETRY_INTERVAL;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.RETRY_NUMBER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.PlainJavaAppAttributes.VISIBILITY;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Initialization of Stardust Attributes for SpringBean applications.
 *
 * Moved and refactored from {@linkplain StardustInterfaceExtendedPropertiesAdapter}.
 *
 * @author Simon Nikles
 *
 */
public class PlainJavaModelProvider extends ApplicationModelMaintainer {

	private static final String ID_PREFIX = "JavaApp_";
	private static final String NAME_PREFIX = "JavaApp ";

	/**
	 * Creates the sdbpmn and carnot model object hierarchy for a PlainJava
	 * ApplicationType
	 *
	 * @param sdInterface the StardustInterfaceType object which is the
	 *            container for the model objects.
	 */
	public static void createPlainJavaApplicationModel(StardustInterfaceType sdInterface) {
		// first delete the previous StardustApplicationType
		removeApplicationModel(sdInterface);

		// and configure for a PlainJava StardustApplicationType
		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		sdApplication.setId(ID_PREFIX + generateAppTypeId());
		sdApplication.setName(NAME_PREFIX);
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(VISIBILITY.attributeName(), VISIBILITY.defaultVal(), VISIBILITY.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(CLASS_NAME.attributeName(), CLASS_NAME.defaultVal(), CLASS_NAME.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(METHOD_NAME.attributeName(), METHOD_NAME.defaultVal(), METHOD_NAME.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(CONSTRUCTOR_NAME.attributeName(), CONSTRUCTOR_NAME.defaultVal(), CONSTRUCTOR_NAME.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_ENABLE.attributeName(), RETRY_ENABLE.defaultVal(), RETRY_ENABLE.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_NUMBER.attributeName(), RETRY_NUMBER.defaultVal(), RETRY_NUMBER.dataType()));
		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(RETRY_INTERVAL.attributeName(), RETRY_INTERVAL.defaultVal(), RETRY_INTERVAL.dataType()));
		sdInterface.setStardustApplication(sdApplication);
	}

}
