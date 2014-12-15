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

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_DEFAULT_VALUE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.ACCESSPOINT_LOCATION;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.IntObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsLocationEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class AccessPointTypeDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private AccessPointChangeListener listener;

	public AccessPointTypeDetailComposite(Composite parent, AccessPointChangeListener listener) {
		super(parent, SWT.NONE);
		this.listener = listener;
	}

	protected Hashtable<String, Object> getDatatypeComboChoice() {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();
		for (AcessPointDataTypes type : AcessPointDataTypes.values()) {
			choices.put(type.getDisplayName(), type.getKey());
		}
		return choices;
	}

	@Override
	public void createBindings(EObject be) {
		final StardustAccessPointType accessPoint = (StardustAccessPointType) be;
		Composite parent = getAttributesParent();
		ObjectEditor editor;

		editor = new IntObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id());
		editor.createControl(parent, Labels.element_Id);
		editor.setEditable(false);

		editor = new TextObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
		editor.createControl(parent, Labels.element_Name);
		editor.setEditable(true);

		AttributeType jmsLocationAt = PropertyAdapterCommons.findAttributeType(accessPoint, ACCESSPOINT_LOCATION.attributeName());
		if (null != jmsLocationAt) {
			editor = new AttributeTypeComboEditor(this, jmsLocationAt, JmsLocationEnum.getChoices());
			editor.createControl(parent, ACCESSPOINT_LOCATION.label());
		}

		AttributeType defaultValueAt = PropertyAdapterCommons.findAttributeType(accessPoint, ACCESSPOINT_DEFAULT_VALUE.attributeName());
		if (null != defaultValueAt) {
			editor = new AttributeTypeTextEditor(this, defaultValueAt);
			editor.createControl(parent, ACCESSPOINT_DEFAULT_VALUE.label());
		}

		ComboObjectEditor objectEditor= new ComboObjectEditor(this, accessPoint, SdbpmnPackage.eINSTANCE.getStardustAccessPointType_TypeRef()) {

			@Override
			protected boolean setValue(final Object newValue) {
				final Object oldValue = getValue();
				if (super.setValue(newValue)) {
					if (oldValue != newValue) {
						setBusinessObject(accessPoint);
						accessPointsChanged();
					}
					return true;
				}
				return false;
			}

			@Override
			protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
				if (choices==null) {
					choices = getDatatypeComboChoice();
				}
				return choices;
			}

		};
		objectEditor.createControl(this, Messages.composite_application_section_AccessPoint_select_dataType);

		AbstractDetailComposite subComposite = null;
		String apTypeStr = accessPoint.getTypeRef();
		AcessPointDataTypes typeCategory = AcessPointDataTypes.forKey(apTypeStr);
		if (null == typeCategory) return;

		switch(typeCategory) {
		case PRIMITIVE_TYPE:
			subComposite = new PrimitiveTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		case SERIALIZABLE_TYPE:
			subComposite = new SerializableTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		case STRUCT_TYPE:
			subComposite = new StructuredTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		default:
			break;
		}

		// rebuild the service-specific details section
		if (subComposite != null)
			subComposite.setBusinessObject(accessPoint);
	}

	@Override
	public void accessPointsChanged() {
		if (null != listener) listener.accessPointsChanged();
	}
}