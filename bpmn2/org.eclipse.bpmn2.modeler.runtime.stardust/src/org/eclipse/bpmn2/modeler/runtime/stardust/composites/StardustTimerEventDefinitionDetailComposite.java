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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeDateTimeEditor;
import org.eclipse.bpmn2.modeler.ui.property.events.TimerEventDefinitionDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 *
 */
public class StardustTimerEventDefinitionDetailComposite extends TimerEventDefinitionDetailComposite {

	/**
	 * @param parent
	 * @param style
	 */
	public StardustTimerEventDefinitionDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public StardustTimerEventDefinitionDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	
	/**
	 * @return TimeCycleButton - it needs to be made accessible for subsequent use in Subclass
	 */
	public Button getTimeCycleButton() {
		return super.timeCycleButton; 
	}
	
	/**
	 * @return TimerType - it needs to be made accessible for subsequent use in Subclass
	 */
	public TimerType getTimerType() {
		return super.timerType; 
	}
	
	@Override
	public void createBindings(EObject be) {
		// do all of the standard BPMN2 Interface stuff...		
		super.createBindings(be);
		try {
//			System.out.println("Activator.start()");
//			IntrinsicJavaAccesspointInfo.main(new String[]{});; // TODO REMOVE
			Definitions definitions = ModelUtil.getDefinitions(be);
			for (RootElement root : definitions.getRootElements()) {
				if (root instanceof Process) {
					for (FlowElement flow : ((Process)root).getFlowElements()) {
						if (flow instanceof Activity) {
							List<DataInputAssociation> inputAssociations = ((Activity)flow).getDataInputAssociations();
							System.out.println("data inputs: ");
							for (DataInput input : ((Activity) flow).getIoSpecification().getDataInputs()) {
								System.out.println("canonical: " + ModelUtil.toCanonicalString(input));
							}
							if (inputAssociations.size() > 0) {
								List<ItemAwareElement> itemAwareElements = ModelUtil.getItemAwareElements(inputAssociations);
								System.out.println("item aware elements: ");
								for (ItemAwareElement e : itemAwareElements) {
									System.out.println(e);
									System.out.println("canonical: " + ModelUtil.toCanonicalString(e));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {}

		// ...and then the StardustTimerEvent extension
		StardustTimerStartEventType sdTimerEvent = null;
		// Here "be" is an org.eclipse.bpmn2.TimerEventDefinition object. If the object
		// already has a StardustTimerStartEvent extension object, then use it.
		List<StardustTimerStartEventType> list = ModelDecorator.getAllExtensionAttributeValues(be,
				StardustTimerStartEventType.class);
		
		AttributeType at = null;
		if (list.size() > 0) {
			// it should have only one TimerEvent
			sdTimerEvent = list.get(0);
			// Fetch the Carnot AttributeType that may be contained in the attributes list
			// this should be the "stopTime" attribute and it should be the ONLY attribute
			// in sdTimerEvent (big assumption here!)
			at = StardustEventDefinitionPropertySection.findAttributeType(
					sdTimerEvent, "carnot:engine:stopTime");
			if (at==null) {
				// if the attribute does not exist (not likely, but better to be safe)
				// then create a new one and use InsertionAdapters to add it to the
				// sdTimerEvent's attributes container when its value changes.
				at = StardustInterfaceExtendedPropertiesAdapter.createAttributeType("carnot:engine:stopTime", "", null);
				// create a StardustAttributes container
				StardustAttributesType sdAttributesType = SdbpmnFactory.eINSTANCE.createStardustAttributesType();
				// for the sdTimerEvent
				InsertionAdapter.add(sdTimerEvent, SdbpmnPackage.eINSTANCE.getStardustTimerStartEventType_StardustAttributes(), sdAttributesType);
				// and add the stopTime attribute to that container.
				// These InsertionAdapters will fire in sequence, so that when the stopTime
				// attribute's "value" changes, it will first cause it to be inserted into the
				// sdAttributesType container, then the sdAttributesType container will be inserted
				// into sdTimerEvent.
				InsertionAdapter.add(sdAttributesType, SdbpmnPackage.eINSTANCE.getStardustAttributesType_AttributeType(), at);
			}
		} else {
			// Otherwise, use the SdbpmnFactory to create a new one.
			sdTimerEvent= SdbpmnFactory.eINSTANCE.createStardustTimerStartEventType();
			// also create the Carnot attribute that will hold the "stop time" value
			// and insert it into sdTimerEvent's attributes list. Note that this will
			// not cause any changes to our Resource yet, since the sdTimerEvent is
			// not yet contained in our Resource. Hence, no EMF Transaction is required.
			at = StardustInterfaceExtendedPropertiesAdapter.createAttributeType("carnot:engine:stopTime", "", null);
			// create a StardustAttributes container
			StardustAttributesType sdAttributesType = SdbpmnFactory.eINSTANCE.createStardustAttributesType();
			// for the sdTimerEvent
			sdTimerEvent.setStardustAttributes(sdAttributesType);
			// and add the stopTime attribute to that container.
			sdAttributesType.getAttributeType().add(at);
			// This is the feature that will hold the StardustTimerStarteEvent
			// instance object within the org.eclipse.bpmn2.TimerEventDefinition
			// extensionValues container.
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustTimerStartEvent();
			// Add the new object to the TimerEvents's extensionValues.
			// Note the use of the last parameter ("true") to use an
			// {@link InsertionAdapter} to add the object.
			ModelDecorator.addExtensionAttributeValue(be, feature, sdTimerEvent, true);
		}
		
		// Create the DateTime Object Editor to enter the stopTime:
		final ObjectEditor editor = new AttributeTypeDateTimeEditor(this, at);
		editor.createControl(getAttributesParent(), "Stardust Stop Time");
		
		// Selection listener code for the "Time Cycle" button - when in the GUI the interval
		// the DateTimeEditor shall be shown.
		editor.setVisible(getTimerType()==TimerType.TIMECYCLE);
		
		getTimeCycleButton().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (getTimeCycleButton().getSelection()) {
					editor.setVisible(true);
				}
				else {
					editor.setVisible(false);
				}
			}			
		});
	}
}
