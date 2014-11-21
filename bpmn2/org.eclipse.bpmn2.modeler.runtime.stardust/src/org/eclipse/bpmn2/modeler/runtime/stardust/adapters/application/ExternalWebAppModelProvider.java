package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ExternalWebAppAttributes.VISIBILITY;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ExternalWebAppAttributes.WEBAPP_URI;

import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Initialization of External Webapp Application Type and its Attributes.
 *
 * @author Simon Nikles
 *
 */
public class ExternalWebAppModelProvider extends ApplicationModelMaintainer {

	private static final String ID_PREFIX = "ExternalWebApplicationApp_";
	private static final String NAME_PREFIX = "ExternalWebApplicationApp ";

	public static void createExternalWebappApplicationModel(StardustInterfaceType sdInterface) {
		removeApplicationModel(sdInterface);

		StardustApplicationType sdApplication = SdbpmnFactory.eINSTANCE.createStardustApplicationType();
		StardustContextType contextType = SdbpmnFactory.eINSTANCE.createStardustContextType();
		contextType.setTypeRef(ApplicationTypes.EXTERNAL_WEBAPP.getKey());
		sdApplication.getContext1().add(contextType);
		long appNr = generateAppTypeId();
		sdApplication.setId(ID_PREFIX + appNr);
		sdApplication.setName(NAME_PREFIX + appNr);
		sdApplication.setInteractive(true);

		sdApplication.getAttribute().add(PropertyAdapterCommons.createAttributeType(VISIBILITY.attributeName(), "", null));
		contextType.getAttribute().add(PropertyAdapterCommons.createAttributeType(WEBAPP_URI.attributeName(), "", null));

		sdInterface.setStardustApplication(sdApplication);
	}

}
