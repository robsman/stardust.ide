/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

import java.util.List;

import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataStoreDetailComposite extends DefaultDetailComposite {

	private AbstractPropertiesProvider referencePropertiesProvider;

	public StardustDataStoreDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDataStoreDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);

		StardustDataStoreType sdData = null;
		List<StardustDataStoreType> list = ModelDecorator.getAllExtensionAttributeValues(be, StardustDataStoreType.class);
		if (list.size() > 0) {
			sdData = list.get(0);
		} else {
			sdData = SdbpmnFactory.eINSTANCE.createStardustDataStoreType();
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustDataStore();
			ModelDecorator.addExtensionAttributeValue(be, feature, sdData, true);
		}
		StardustDataDetailComposite sdDataSection = new StardustDataDetailComposite(this, SWT.NONE);
		sdDataSection.setTitle("Stardust Type");
		sdDataSection.setBusinessObject(sdData);

		StardustDataStoreBpmnDetailComposite bpmnSection = new StardustDataStoreBpmnDetailComposite(this, SWT.NONE);
		bpmnSection.setTitle("Details");
		bpmnSection.setBusinessObject(be);

	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (object instanceof DataStore) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] { "id" };
					@Override
					public String[] getProperties() {
						return properties;
					}
				};
			}
			return propertiesProvider;
		}
		else if (object instanceof DataStoreReference) {
			if (referencePropertiesProvider == null) {
				referencePropertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] { "id", "name", "dataStoreRef" };
					@Override
					public String[] getProperties() {
						return properties;
					}
				};

			}
			return referencePropertiesProvider;
		}
		return null;
	}
}

