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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.camel;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.StardustAccesspointDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.swt.widgets.Composite;

public class CamelDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private ApplicationTypes camelTypes;
	private StardustAccesspointDropdown inAccessPointDropdown = null;
	private StardustAccesspointDropdown outAccessPointDropdown = null;
	private StardustInterfaceType sdInterface;

	public CamelDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public CamelDetailComposite(Composite parent, int style, ApplicationTypes camelType) {
		super(parent, style);
		camelTypes = camelType;
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();

		if (camelTypes.equals(ApplicationTypes.CAMELCONSUMER)) {
			setTitle(Messages.compositeTitle_camelConsumerServiceConfiguration);
		} else if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			setTitle(Messages.compositeTitle_camelProducerServiceConfiguration);
		} else {
			setTitle(Messages.compositeTitle_camelProducerServiceConfigurationIO);
		}

		sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		StardustApplicationType sdApplication;
		sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.CONTEXT_ID.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, CamelAttributes.CONTEXT_ID.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.VISIBILITY.attributeName());
		editor = new AttributeTypeComboEditor(this, at, CamelAttributes.VISIBILITY.optionKeys());
		editor.createControl(parent, CamelAttributes.VISIBILITY.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.INVOCATION_TYPE.attributeName());
		editor = new AttributeTypeComboEditor(this, at, CamelAttributes.INVOCATION_TYPE.optionKeys());
		editor.createControl(parent, CamelAttributes.INVOCATION_TYPE.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.RETRY_ENABLE.attributeName());
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, CamelAttributes.RETRY_ENABLE.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.RETRY_NUMBER.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, CamelAttributes.RETRY_NUMBER.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.RETRY_INTERVAL.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, CamelAttributes.RETRY_INTERVAL.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.MULTIPLE_ACCESSPOINTS.attributeName());
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, CamelAttributes.MULTIPLE_ACCESSPOINTS.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.TRANSACTED_ROUTE.attributeName());
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, CamelAttributes.TRANSACTED_ROUTE.label());

		if (camelTypes.equals(ApplicationTypes.CAMELCONSUMER) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) {
			at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.CONSUMER_ROUTE.attributeName());
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, CamelAttributes.CONSUMER_ROUTE.label());
		}

		if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.CONTEXT_HEADERS.attributeName());
			editor = new AttributeTypeBooleanEditor(this, at);
			editor.createControl(parent, CamelAttributes.CONTEXT_HEADERS.label());

			at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.PRODUCER_ROUTE.attributeName());
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, CamelAttributes.PRODUCER_ROUTE.label());
		}

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.IN_BODY_ACCESSPOINTS.attributeName());
		inAccessPointDropdown = new StardustAccesspointDropdown(this, at, sdApplication, DirectionType.IN_LITERAL);
		inAccessPointDropdown.createControl(parent, CamelAttributes.IN_BODY_ACCESSPOINTS.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.OUT_BODY_ACCESSPOINTS.attributeName());
		outAccessPointDropdown = new StardustAccesspointDropdown(this, at, sdApplication, DirectionType.OUT_LITERAL);
		outAccessPointDropdown.createControl(parent, CamelAttributes.OUT_BODY_ACCESSPOINTS.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, CamelAttributes.SPRING_BEANS.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, CamelAttributes.SPRING_BEANS.label());

		// AccesPointsSection for Input and Output AccessPoint definitions
		Composite accessPointsSection = this.createSectionComposite(this, Messages.composite_application_section_AccessPoints);

		// create two lists, one for Input and one for Output Access Points
		AccessPointListComposite inputParams = new CamelAccessPointListComposite(accessPointsSection, true, this);
		inputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		inputParams.setTitle(Messages.composite_application_section_AccessPoints_Inputs);

		AccessPointListComposite outputParams = new CamelAccessPointListComposite(accessPointsSection, false, this);
		outputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		outputParams.setTitle(Messages.composite_application_section_AccessPoints_Outputs);
	}

	public void accessPointsChanged() {
		if (null != inAccessPointDropdown) inAccessPointDropdown.forceReload();
		if (null != inAccessPointDropdown) outAccessPointDropdown.forceReload();
		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos(sdInterface.getStardustApplication());
			}
		};
		editingDomain.getCommandStack().execute(command);
		refresh();
	}

}