/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.flowelement;

import java.util.Hashtable;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.itemaware.StardustItemDefinitionRefFeatureDescriptor;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.FlowElementPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

public class StardustFlowElementPropertiesAdapter<T extends FlowElement> extends FlowElementPropertiesAdapter<T> {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public StardustFlowElementPropertiesAdapter(AdapterFactory adapterFactory, T object) {
		super(adapterFactory, object);

		if (object instanceof ItemAwareElement) {
			final EStructuralFeature f = Bpmn2Package.eINSTANCE.getItemAwareElement_ItemSubjectRef();
			setProperty(f, UI_IS_MULTI_CHOICE, Boolean.TRUE);
	    	setFeatureDescriptor(f, new StardustItemDefinitionRefFeatureDescriptor<T>(this, object, f) {

	    		@Override
	    		public Hashtable<String, Object> getChoiceOfValues() {
					return super.getChoiceOfValues();
	    		}
		
	    	});
		}		
	}

}
