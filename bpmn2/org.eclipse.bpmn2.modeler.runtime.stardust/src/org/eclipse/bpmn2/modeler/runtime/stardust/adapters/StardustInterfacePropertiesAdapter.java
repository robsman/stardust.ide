package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Read only implementationRef, no selection button (values are set via stardust application implementation).
 * 
 * @author Simon Nikles
 *
 */
public class StardustInterfacePropertiesAdapter extends ExtendedPropertiesAdapter<Interface> { //extends InterfacePropertiesAdapter {

	public StardustInterfacePropertiesAdapter(AdapterFactory adapterFactory, Interface object) {
		super(adapterFactory, object);
    	EStructuralFeature feature = Bpmn2Package.eINSTANCE.getInterface_ImplementationRef();
    	setProperty(feature, UI_CAN_CREATE_NEW, Boolean.FALSE);
    	setProperty(feature, UI_CAN_EDIT, Boolean.FALSE);
		setProperty(feature, UI_IS_MULTI_CHOICE, Boolean.FALSE);		
	}

}
