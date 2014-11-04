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

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.FilteredStardustDatatypeDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.FilteredStardustDatatypeDropdown.Filter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType;
import org.eclipse.swt.widgets.Composite;

/**
 * BPMN default attributes below Stardust type (category) selection.
 *
 * @author Simon Nikles
 *
 */
public class StardustDataObjectBpmnDetailComposite  extends DefaultDetailComposite {

	public StardustDataObjectBpmnDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDataObjectBpmnDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
	}

	@Override
	protected void bindReference(Composite parent, EObject object, EReference reference) {
		if ("itemSubjectRef".equals(reference.getName())) {
			StardustDataObjectType sdObject = null;
			StardustDataObjectTypeEnum type = null;
			List<StardustDataObjectType> list = ModelDecorator.getAllExtensionAttributeValues(object, StardustDataObjectType.class);
			if (list.size() > 0) {
				sdObject = list.get(0);
				if (null != sdObject) {
					String typeKey = sdObject.getType();
					if (null != typeKey) {
						type = StardustDataObjectTypeEnum.forKey(typeKey);
					}
				}
			}
			if (null == type) {
				super.bindReference(parent, object, reference);
				return;
			}
			FilteredStardustDatatypeDropdown combo = null;
			switch (type) {
			case PRIMITIVE:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.PRIMITIVE, null, true);
				break;
			case STRUCTURED:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.STRUCTURED, null, true);
				break;
			case SERIALIZABLE:
				combo = new FilteredStardustDatatypeDropdown(this, object, reference, Filter.CLASS, null, true);
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
	protected void bindAttribute(Composite parent, EObject object, EAttribute attribute, String label) {
		super.bindAttribute(parent, object, attribute, label);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] { "isCollection", "itemSubjectRef" };

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
}
