package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;

/**
 * @author Simon Nikles
 *
 */
public class StardustConditionalPerformerPropertiesAdapter extends ExtendedPropertiesAdapter<ConditionalPerformerType> {

	public StardustConditionalPerformerPropertiesAdapter(AdapterFactory adapterFactory, ConditionalPerformerType performer) {
		super(adapterFactory, performer);

		EStructuralFeature dataFeature = CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_Data();
		setProperty(dataFeature, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(dataFeature, UI_IS_MULTI_CHOICE, Boolean.FALSE);
		setProperty(dataFeature, UI_CAN_EDIT, Boolean.TRUE);

		EStructuralFeature dataPathFeature = CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_DataPath();
		setProperty(dataPathFeature, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(dataPathFeature, UI_IS_MULTI_CHOICE, Boolean.FALSE);
		setProperty(dataPathFeature, UI_CAN_EDIT, Boolean.TRUE);
	}
}
