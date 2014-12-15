package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;

/**
 * @author Simon Nikles
 *
 */
public class StardustUserTaskPropertiesAdapter  extends ExtendedPropertiesAdapter<StardustUserTaskType> {

	public StardustUserTaskPropertiesAdapter(AdapterFactory adapterFactory, StardustUserTaskType object) {
		super(adapterFactory, object);
    	EStructuralFeature ref = SdbpmnPackage.eINSTANCE.getStardustUserTaskType_InteractiveApplicationRef();
    	setProperty(ref, UI_CAN_CREATE_NEW, Boolean.FALSE);
    	setProperty(ref, UI_CAN_EDIT, Boolean.FALSE);
    	setProperty(ref, UI_CAN_SET_NULL, Boolean.TRUE);
		setProperty(ref, UI_IS_MULTI_CHOICE, Boolean.TRUE);
    	setFeatureDescriptor(ref, new InteractiveApplicationRefFeatureDescriptor(this,object,ref));
	}

}

