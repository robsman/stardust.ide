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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.common.PropertyCommons.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class JmsApplicationDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private StardustInterfaceType sdInterface;

	public JmsApplicationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public JmsApplicationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();

		sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		final StardustApplicationType sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, Visibility.NAME);
		editor = new AttributeTypeComboEditor(this, at, Visibility.getOptionKeys());
		editor.createControl(parent, "Visibility");

		AttributeType direction = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:type");
		AttributeTypeComboEditor selEditor = new AttributeTypeComboEditor(this, direction, JmsDirectionEnum.getChoices());
		selEditor.createControl(parent, "Direction");
		selEditor.addSelectionListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				RecordingCommand command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						AttributeType direction = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:type");
						JmsDirectionEnum newDirectionValue = JmsDirectionEnum.forKey(direction.getValue());
						StardustInterfaceExtendedPropertiesAdapter.updateJmsApplicationModel(sdInterface, newDirectionValue);
						setBusinessObject(sdInterface);
					}
				};
				editingDomain.getCommandStack().execute(command);

			}
		});

		// keep defaults for
		//"carnot:engine:messageProvider"
		//"carnot:engine:messageAcceptor"
		JmsDirectionEnum directionValue = JmsDirectionEnum.forKey(direction.getValue());

		if (null != directionValue) {
			Composite accessPointsSection = this.createSectionComposite(this, "Access Points");

			if (JmsDirectionEnum.OUT.equals(directionValue) || JmsDirectionEnum.INOUT.equals(directionValue)) {
				at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:queueConnectionFactory.jndiName");
				editor = new AttributeTypeTextEditor(this, at);
				editor.createControl(parent, "JMS Connection Factory (jndi)");

				at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:queue.jndiName");
				editor = new AttributeTypeTextEditor(this, at);
				editor.createControl(parent, "JMS Queue (jndi)");

				AttributeType requestMsgType = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:requestMessageType");
				editor = new AttributeTypeComboEditor(this, requestMsgType, JmsMessageType.getChoices());
				editor.createControl(parent, "Request Message Type");

				at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:includeOidHeaders");
				editor = new AttributeTypeBooleanEditor(this, at);
				editor.createControl(parent, "Include Oid Headers");

				AccessPointListComposite inputParams = new JmsApplicationAccessPointListComposite(accessPointsSection, true, this);
				inputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
				inputParams.setTitle("Inputs");

			}
			if (JmsDirectionEnum.IN.equals(directionValue) || JmsDirectionEnum.INOUT.equals(directionValue)) {
				AttributeType responseMsgType = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:responseMessageType");
				editor = new AttributeTypeComboEditor(this, responseMsgType, JmsMessageType.getChoices());
				editor.createControl(parent, "Response Message Type");

				AccessPointListComposite outputParams = new JmsApplicationAccessPointListComposite(accessPointsSection, false, this);
				outputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
				outputParams.setTitle("Outputs");

			}
		}

	}

	public void accessPointsChanged() {
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