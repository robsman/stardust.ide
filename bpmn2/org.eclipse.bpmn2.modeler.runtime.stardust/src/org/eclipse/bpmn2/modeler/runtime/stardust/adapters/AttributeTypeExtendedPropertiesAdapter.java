/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
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

package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;

/**
 *
 */
public class AttributeTypeExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<AttributeType> {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public AttributeTypeExtendedPropertiesAdapter(AdapterFactory adapterFactory, AttributeType object) {
		super(adapterFactory, object);
		
		EStructuralFeature feature = CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value();
		
    	setFeatureDescriptor(feature,
			new FeatureDescriptor<AttributeType>(this,object,feature) {
    			@Override
    	   		protected void internalSet(AttributeType sdStardustTimerStartEvent, EStructuralFeature feature, Object value, int index) {
    				// Whenever this AttributeType's "value" feature changes,
    				// the framework will call this FeatureDescriptor's internalSet() inside
    				// an EMF transaction.
    				if (value instanceof java.util.Date) {
    					value = value.toString();
    					// or more generically: if (value!=null && !(value instanceof String)) value = value.toString();
    				}
    				super.internalSet(object, feature, value, index);
    			}
    	});
	}

}
