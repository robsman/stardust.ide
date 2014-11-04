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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustStartEventDetailComposite extends DefaultDetailComposite {

	private Button manualTriggerRadioButton;
	private Button apiTriggerRadioButton;
	private Map<String, String> definedPerformerResources;
	private static final String API_TRIGGER = "API";
	private static final String MANUAL_TRIGGER = "MANUAL";

	public StardustStartEventDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
		System.out
				.println("StardustStartEventDetailComposite.StardustStartEventDetailComposite()");
	}

	public StardustStartEventDetailComposite(Composite parent, int style) {
		super(parent, style);
		System.out
				.println("StardustStartEventDetailComposite.StardustStartEventDetailComposite()");
	}

	@Override
	public void createBindings(EObject be) {
		StartEvent startEvent = (StartEvent)be;

		final List<StardustStartEventType> startExtensions = ModelDecorator.getAllExtensionAttributeValues(be, StardustStartEventType.class);
		boolean isManual = startExtensions.size() > 0;

		Composite btnGrp = getToolkit().createComposite(getAttributesParent(), SWT.NONE);
		GridData typeGroupGridData = new GridData(SWT.LEFT, SWT.TOP, false, true, 3, 1);
		GridLayout layout = new GridLayout(2, false);
		btnGrp.setLayout(layout);
		btnGrp.setLayoutData(typeGroupGridData);

		apiTriggerRadioButton = createRadioButton(btnGrp, "API Trigger", API_TRIGGER, !isManual, startEvent);
		manualTriggerRadioButton = createRadioButton(btnGrp, "Manual Trigger", MANUAL_TRIGGER, isManual, startEvent);

		if (isManual) {
			StardustStartEventType triggerExt = startExtensions.get(0);
			if (null != triggerExt) {
				ObjectEditor editor = null;
				if (null == triggerExt.getStardustAttributes()) {
					deleteStardustStartEventExtension(startEvent);
					triggerExt = createStardustManualStartEventExtension(startEvent);
				}
				AttributeType at = PropertyAdapterCommons.findAttributeType(triggerExt.getStardustAttributes(), "carnot:engine:participant");
				editor = new AttributeTypeComboEditor(this, at, getDefinedPerformerResources());
				editor.createControl(this, "Performer");
			}
		} else {

		}
		redrawPage();

		System.out.println("StardustStartEventDetailComposite.createBindings()");
	}

	private Map<String, String> getDefinedPerformerResources() {
		if (null == definedPerformerResources) {
			List<Resource> resources = ModelUtil.getAllRootElements(ModelUtil.getDefinitions(businessObject), Resource.class);
			definedPerformerResources = new HashMap<String, String>();
			for (Resource res : resources) {
				definedPerformerResources.put(res.getName(), res.getId());
			}
		}
		return definedPerformerResources;
	}

	@Override
	protected void createEmptyLabel(EObject be) {

	}

	protected Button createRadioButton(final Composite parent, String label, Object value, boolean checked, final StartEvent startEvent) {
		final Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setBackground(parent.getBackground());
		button.setData( value );
		button.setSelection(checked);
		button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String selection = null;
				if (manualTriggerRadioButton.getSelection()) {
					selection = MANUAL_TRIGGER;
				} if (apiTriggerRadioButton.getSelection()) {
					selection = API_TRIGGER;
				}
				if (MANUAL_TRIGGER.equals(selection)) {
					createStardustManualStartEventExtension(startEvent);
					setBusinessObject(startEvent);
				} else {
					deleteStardustStartEventExtension(startEvent);
					setBusinessObject(startEvent);
				}
				getPropertySection().refresh();
				layout();
				redraw();
			}
		});
		return button;
	}

	public StardustStartEventType createStardustManualStartEventExtension(final StartEvent startEvent) {
		final List<StardustStartEventType> startExtensions = ModelDecorator.getAllExtensionAttributeValues(startEvent, StardustStartEventType.class);
		final StardustStartEventType eventExt = SdbpmnFactory.eINSTANCE.createStardustStartEventType();
		if (startExtensions.size() <= 0) {
			RecordingCommand command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					eventExt.setStardustAttributes(SdbpmnFactory.eINSTANCE.createStardustAttributesType());
					EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustStartEvent();
					eventExt.getStardustAttributes().getAttributeType().add(PropertyAdapterCommons.createAttributeType("carnot:engine:participant", "", "String"));
					ModelDecorator.addExtensionAttributeValue(startEvent, feature, eventExt);
				}
			};
			editingDomain.getCommandStack().execute(command);
			return eventExt;
		} else {
			return startExtensions.get(0);
		}
	}

	public void deleteStardustStartEventExtension(final StartEvent startEvent) {
		final List<StardustStartEventType> startExtensions = ModelDecorator.getAllExtensionAttributeValues(startEvent, StardustStartEventType.class);
		if (startExtensions.size() > 0) {
			RecordingCommand command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					for (StardustStartEventType eventExt : startExtensions) {
						EcoreUtil.remove(eventExt);
					}
				}
			};
			editingDomain.getCommandStack().execute(command);
		}
	}

}
