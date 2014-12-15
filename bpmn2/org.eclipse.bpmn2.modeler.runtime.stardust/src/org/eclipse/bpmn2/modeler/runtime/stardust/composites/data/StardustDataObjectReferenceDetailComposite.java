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

import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataObjectReferenceDetailComposite extends DefaultDetailComposite {

	private AbstractPropertiesProvider propertiesProvider;

	public StardustDataObjectReferenceDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustDataObjectReferenceDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (object instanceof DataObjectReference) {
			if (propertiesProvider == null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] {"id", "name"};

					@Override
					public String[] getProperties() {
						return properties;
					}
				};

			}
			return propertiesProvider;
		}
		return null;
	}

}