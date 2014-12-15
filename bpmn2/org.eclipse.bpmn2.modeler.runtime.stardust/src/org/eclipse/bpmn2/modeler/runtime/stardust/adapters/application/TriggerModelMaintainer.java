package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application;

import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Base class for trigger model initializing/changing classes (ModelProviders)
 *
 * @author Simon Nikles
 *
 */
public class TriggerModelMaintainer {

	private static long triggerId = 1;

	public static void removeTriggerModel(StardustInterfaceType sdInterface) {
		boolean recreateEmptyTrigger = null != sdInterface.getStardustTrigger();
		StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdInterface);
		if (recreateEmptyTrigger) {
			sdInterface.setStardustTrigger(SdbpmnFactory.eINSTANCE.createStardustTriggerType());
		} else {
			sdInterface.setStardustTrigger(null);
		}
	}

	protected static long generateTriggerId() {
		return triggerId++;
	}


}
