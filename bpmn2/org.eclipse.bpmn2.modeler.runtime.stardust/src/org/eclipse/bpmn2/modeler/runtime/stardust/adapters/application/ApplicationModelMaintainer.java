package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Base class for application model initializing/changing classes (ModelProviders)
 *
 * @author Simon Nikles
 *
 */
public class ApplicationModelMaintainer {

	private static long appTypeId = 1;

	public static void removeApplicationModel(StardustInterfaceType sdInterface) {
		boolean recreateEmptyApp = null != sdInterface.getStardustApplication();
		StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdInterface);
		if (recreateEmptyApp) {
			sdInterface.setStardustApplication(SdbpmnFactory.eINSTANCE.createStardustApplicationType());
		} else {
			sdInterface.setStardustApplication(null);
		}
	}

	public static long generateAppTypeId() {
		return appTypeId++;
	}

}
