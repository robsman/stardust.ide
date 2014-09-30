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
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
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
			setTitle("Camel Consumer Service Configuration (receive)");			
		} else if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			setTitle("Camel Producer Service Configuration (send)");			
		} else {
			setTitle("Camel Producer Service Configuration (send/receive)");
		}
		
		sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		StardustApplicationType sdApplication;
		sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::camelContextId");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Context Id");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:visibility");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Public", "Private" });
		editor.createControl(parent, "Visibility");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::invocationType");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Synchronous", "Asynchronous" });
		editor.createControl(parent, "Invocation Type");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:enable");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Enable Retry");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:number");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Number of Retries");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:time");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Time between Retries (seconds)");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::supportMultipleAccessPoints");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Multiple Access Points");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::transactedRoute");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Transacted Route");
		
		if (camelTypes.equals(ApplicationTypes.CAMELCONSUMER) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) {
			at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::consumerRoute");
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, "Camel Consumer Route");
		}
			
		if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::processContextHeaders");
			editor = new AttributeTypeBooleanEditor(this, at);
			editor.createControl(parent, "Include Process Context Headers in Producer Route ");			
				
			at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::routeEntries");
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, "Camel Producer Route");
		}
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::inBodyAccessPoint");
		inAccessPointDropdown = new StardustAccesspointDropdown(this, at, sdApplication, DirectionType.IN_LITERAL);
		inAccessPointDropdown.createControl(parent, "Body Input Access Point");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::outBodyAccessPoint");
		outAccessPointDropdown = new StardustAccesspointDropdown(this, at, sdApplication, DirectionType.OUT_LITERAL);
		outAccessPointDropdown.createControl(parent, "Body Output Access Point");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:camel::additionalSpringBeanDefinitions");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Additional Spring Bean Definitions");

		// AccesPointsSection for Input and Output AccessPoint definitions
		Composite accessPointsSection = this.createSectionComposite(this, "Access Points");
		
		// create two lists, one for Input and one for Output Access Points
		AccessPointListComposite inputParams = new CamelAccessPointListComposite(accessPointsSection, true, this);
		inputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		inputParams.setTitle("Inputs");

		AccessPointListComposite outputParams = new CamelAccessPointListComposite(accessPointsSection, false, this);
		outputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		outputParams.setTitle("Outputs");
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