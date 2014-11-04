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

import java.util.Collections;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.FilteredStardustDatatypeDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.FilteredStardustDatatypeDropdown.Filter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataStoreBpmnDetailComposite extends DefaultDetailComposite {

	public StardustDataStoreBpmnDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDataStoreBpmnDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
	}

	@Override
	protected void bindReference(Composite parent, EObject object, EReference reference) {
		if ("itemSubjectRef".equals(reference.getName())) {
			StardustDataStoreType sdObject = null;
			StardustDataStoreTypeEnum type = null;
			List<StardustDataStoreType> list = ModelDecorator.getAllExtensionAttributeValues(object, StardustDataStoreType.class);
			if (list.size() > 0) {
				sdObject = list.get(0);
				if (null != sdObject) {
					String typeKey = sdObject.getType();
					if (null != typeKey) {
						type = StardustDataStoreTypeEnum.forKey(typeKey);
					}
				}
			}
			if (null == type) {
				super.bindReference(parent, object, reference);
				return;
			}
			FilteredStardustDatatypeDropdown combo = null;
			switch (type) {
			case DOCUMENT:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.CLASS_OR_STRUCTURED, Collections.singletonList(StardustDataStoreTypeEnum.DOCUMENT.getDefaultClass()), true);
				break;
			case DOCUMENT_LIST:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.CLASS_OR_STRUCTURED, Collections.singletonList(StardustDataStoreTypeEnum.DOCUMENT_LIST.getDefaultClass()), true);
				break;
			case DOCUMENT_FOLDER:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.CLASS_OR_STRUCTURED, Collections.singletonList(StardustDataStoreTypeEnum.DOCUMENT_FOLDER.getDefaultClass()), true);
				break;
			case DOCUMENT_FOLDER_LIST:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.CLASS_OR_STRUCTURED, Collections.singletonList(StardustDataStoreTypeEnum.DOCUMENT_FOLDER_LIST.getDefaultClass()), true);
				break;
			default:
				break;
			}
			if (null != combo) {
				combo.createControl(parent,"Structure Reference");
			}
		} else {
			super.bindReference(parent, object, reference);
		}
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] { "id", "name", "itemSubjectRef" };
				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
}

