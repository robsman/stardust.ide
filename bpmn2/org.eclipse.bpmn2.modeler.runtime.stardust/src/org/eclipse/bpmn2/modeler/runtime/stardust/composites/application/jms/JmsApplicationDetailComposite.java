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

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.ID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.OID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.DIRECTION;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.INCLUDE_OID_HEADERS;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.QUEUE_CONNECTION_FACTORY_JNDI;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.QUEUE_JNDI;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.REQUEST_MESSAGE_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.RESPONSE_MESSAGE_TYPE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.JMSApplicationAttributes.VISIBILITY;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.JMSApplicationModelProvider;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CommonAttributes.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
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
		bindAttribute(sdApplication, NAME);
		bindAttribute(sdApplication, ID);
		bindAttribute(sdApplication, OID);

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, VISIBILITY.attributeName());
		editor = new AttributeTypeComboEditor(this, at, VISIBILITY.choices());
		editor.createControl(parent, Visibility.LABEL);

		AttributeType direction = PropertyAdapterCommons.findAttributeType(sdApplication, DIRECTION.attributeName());
		AttributeTypeComboEditor selEditor = new AttributeTypeComboEditor(this, direction, JmsDirectionEnum.getChoices());
		selEditor.createControl(parent, DIRECTION.label());
		selEditor.addSelectionListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent changeEvent) {
				RecordingCommand command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						AttributeType direction = PropertyAdapterCommons.findAttributeType(sdApplication, DIRECTION.attributeName());
						JmsDirectionEnum newDirectionValue = JmsDirectionEnum.forKey(direction.getValue());
						JMSApplicationModelProvider.updateJmsApplicationModel(sdInterface, newDirectionValue);
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
			Composite accessPointsSection = this.createSectionComposite(this, Messages.composite_application_section_AccessPoints);

			if (JmsDirectionEnum.OUT.equals(directionValue) || JmsDirectionEnum.INOUT.equals(directionValue)) {
				at = PropertyAdapterCommons.findAttributeType(sdApplication, QUEUE_CONNECTION_FACTORY_JNDI.attributeName());
				editor = new AttributeTypeTextEditor(this, at);
				editor.createControl(parent, QUEUE_CONNECTION_FACTORY_JNDI.label());

				at = PropertyAdapterCommons.findAttributeType(sdApplication, QUEUE_JNDI.attributeName());
				editor = new AttributeTypeTextEditor(this, at);
				editor.createControl(parent, QUEUE_JNDI.label());

				AttributeType requestMsgType = PropertyAdapterCommons.findAttributeType(sdApplication, REQUEST_MESSAGE_TYPE.attributeName());
				editor = new AttributeTypeComboEditor(this, requestMsgType, REQUEST_MESSAGE_TYPE.choices());
				editor.createControl(parent, REQUEST_MESSAGE_TYPE.label());

				at = PropertyAdapterCommons.findAttributeType(sdApplication, INCLUDE_OID_HEADERS.attributeName());
				editor = new AttributeTypeBooleanEditor(this, at);
				editor.createControl(parent, INCLUDE_OID_HEADERS.label());

				AccessPointListComposite inputParams = new JmsApplicationAccessPointListComposite(accessPointsSection, true, this);
				inputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
				inputParams.setTitle(Messages.composite_application_section_AccessPoints_Inputs);

			}
			if (JmsDirectionEnum.IN.equals(directionValue) || JmsDirectionEnum.INOUT.equals(directionValue)) {
				AttributeType responseMsgType = PropertyAdapterCommons.findAttributeType(sdApplication, RESPONSE_MESSAGE_TYPE.attributeName());
				editor = new AttributeTypeComboEditor(this, responseMsgType, RESPONSE_MESSAGE_TYPE.choices());
				editor.createControl(parent, RESPONSE_MESSAGE_TYPE.label());

				AccessPointListComposite outputParams = new JmsApplicationAccessPointListComposite(accessPointsSection, false, this);
				outputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
				outputParams.setTitle(Messages.composite_application_section_AccessPoints_Outputs);

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