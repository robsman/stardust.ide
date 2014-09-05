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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter.ApplicationTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StardustInterfaceDetailComposite extends DefaultDetailComposite {

	public StardustInterfaceDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustInterfaceDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		final StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		setTitle("Stardust Interface");
		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustInterfaceType_ApplicationType();
		// We'll handle this ourselves: create a Combo editor which
		// will swap in different widgets based on the Combo selection
		ObjectEditor editor = new ComboObjectEditor(this, sdInterface, feature) {

			@Override
			protected boolean setValue(final Object newValue) {
				final Object oldValue = getValue();
				if (super.setValue(newValue)) {
					// "newValue" should be a String or null
					if (oldValue != newValue) {
						// This will force a rebuild of the entire Property
						// Sheet tab.
						setBusinessObject(sdInterface);
					}
					return true;
				}
				return false;
			}

		};

		editor.createControl(getAttributesParent(), "Application Type");

		// create a details section for the selected application type
		DefaultDetailComposite details = null;
		String appTypeStr = sdInterface.getApplicationType();
		ApplicationTypes appType = ApplicationTypes.forKey(appTypeStr);
		if (null == appType) return;
		
		switch(appType) {
		case WEBSERVICE:
			details = new WebServiceDetailComposite(getAttributesParent(), SWT.NONE);
			break;
		case CAMELCONSUMER:
			details = new CamelDetailComposite(getAttributesParent(), SWT.NONE,false);
			break;
		case CAMELPRODUCER:
			details = new CamelDetailComposite(getAttributesParent(), SWT.NONE,true);
			break;
		case PLAINJAVA:
			details = new PlainJavaDetailComposite(getAttributesParent(), SWT.NONE);
			break;
		case SPRINGBEAN:
			details = new SpringBeanDetailComposite(getAttributesParent(), SWT.NONE);
			break;			
		case JMS:
		case SESSIONBEAN:
		default:
			break;
		}
		
		// rebuild the service-specific details section
		if (details != null)
			details.setBusinessObject(sdInterface);
	}
}