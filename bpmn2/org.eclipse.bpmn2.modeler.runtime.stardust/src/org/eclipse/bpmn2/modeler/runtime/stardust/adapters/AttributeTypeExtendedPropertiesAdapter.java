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

import java.util.Arrays;
import java.util.List;

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


	private final static List<String> readonlyOnes = Arrays.asList();
//	"carnot:engine:conditionalPerformer:realmData",
//	"carnot:engine:conditionalPerformer:realmDataPath");
	/**
	 * @param adapterFactory
	 * @param object
	 */
	public AttributeTypeExtendedPropertiesAdapter(AdapterFactory adapterFactory, AttributeType object) {
		super(adapterFactory, object);

		EStructuralFeature feature = CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value();

    	setFeatureDescriptor(feature,
			new FeatureDescriptor<AttributeType>(this,object,feature) {

    			final List<String> multiliners = Arrays.asList("carnot:engine:camel::consumerRoute",
															   "carnot:engine:camel::routeEntries",
															   "carnot:engine:camel::additionalSpringBeanDefinitions");

    			@Override
    	   		protected void internalSet(AttributeType sdStardustTimerStartEvent, EStructuralFeature feature, Object value, int index) {
    				// Whenever this AttributeType's "value" feature changes,
    				// the framework will call this FeatureDescriptor's internalSet() inside
    				// an EMF transaction.
    				if (value instanceof java.util.Date) {
    					value = ((java.util.Date) value).getTime();
    					value = value.toString();
    					// or more generically: if (value!=null && !(value instanceof String)) value = value.toString();
    				}
    				super.internalSet(object, feature, value, index);
    			}

				@Override
				public boolean isMultiLine() {
					if (null != object && multiliners.contains(object.getName())) return true;
					return super.isMultiLine();
				}

    	});
	}

	@Override
	public Object getProperty(EStructuralFeature feature, String prop) {
		if (UI_CAN_EDIT.equals(prop)) {
			if (null == getTarget()) return super.getProperty(feature, prop);
			if (readonlyOnes.contains(((AttributeType)getTarget()).getName())) return Boolean.FALSE;
		}
		return super.getProperty(feature, prop);
	}

}
