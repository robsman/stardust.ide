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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.jms;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsApplicationAccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsMessageType;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;

public class StardustJmsTriggerDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private StardustInterfaceType sdInterface;

	public StardustJmsTriggerDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustJmsTriggerDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();

		sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		final StardustTriggerType trigger = sdInterface.getStardustTrigger();
		bindAttribute(trigger, "name");
		bindAttribute(trigger, "id");
		bindAttribute(trigger, "elementOid");

		AttributeType responseMsgType = PropertyAdapterCommons.findAttributeType(trigger, "carnot:engine:messageType");
		editor = new AttributeTypeComboEditor(this, responseMsgType, JmsMessageType.getChoices());
		editor.createControl(parent, "Message Type");

		Composite accessPointsSection = this.createSectionComposite(this, "Access Points");
		AccessPointListComposite outputParams = new JmsApplicationAccessPointListComposite(accessPointsSection, false, this);
		trigger.getAccessPoint1();
		outputParams.bindList(trigger, SdbpmnPackage.eINSTANCE.getStardustTriggerType_AccessPoint1());
		outputParams.setTitle("Outputs");

	}

	@Override
	public void accessPointsChanged() {
		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos(sdInterface.getStardustTrigger());
			}
		};
		editingDomain.getCommandStack().execute(command);
		refresh();
	}


}
