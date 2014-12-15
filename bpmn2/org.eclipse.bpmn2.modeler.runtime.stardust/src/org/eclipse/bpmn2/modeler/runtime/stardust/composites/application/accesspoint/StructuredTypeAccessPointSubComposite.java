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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.STRUCTURED_DATA_TYPE;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown.DatatypeChangeListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StructuredTypeAccessPointSubComposite extends AbstractDetailComposite implements DatatypeChangeListener, AccessPointChangeListener {

	private AccessPointChangeListener listener;

	public StructuredTypeAccessPointSubComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StructuredTypeAccessPointSubComposite(Composite parent, int style, AccessPointChangeListener listener) {
		super(parent, style);
		this.listener = listener;
	}

	@Override
	public void createBindings(EObject be) {
		StardustAccessPointType apType = (StardustAccessPointType)be;
		Composite parent = this.getAttributesParent();

		AttributeType at = PropertyAdapterCommons.findAttributeType(apType, STRUCTURED_DATA_TYPE.attributeName());
		ObjectEditor editor = new MappedStardustDatatypeDropdown(this, at, AcessPointDataTypes.STRUCT_TYPE, this);
		editor.createControl(parent, STRUCTURED_DATA_TYPE.label());

		setTitle(AcessPointDataTypes.STRUCT_TYPE.getDisplayName());
	}

	@Override
	public void comboChanged() {
		accessPointsChanged();
	}

	@Override
	public void accessPointsChanged() {
		if (null != listener) listener.accessPointsChanged();
	}

}
