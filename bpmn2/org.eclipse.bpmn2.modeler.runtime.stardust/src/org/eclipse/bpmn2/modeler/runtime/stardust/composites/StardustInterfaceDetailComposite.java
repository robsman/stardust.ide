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

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.camel.CamelDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.java.PlainJavaDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms.JmsApplicationDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.spring.SpringBeanDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.webapp.ExtWebApplicationDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.TriggerAppTypeEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.jms.StardustJmsTriggerDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class StardustInterfaceDetailComposite extends DefaultDetailComposite {

	private ChangeableComboObjectEditor applicationTypeSelectionCombo;
	private ChangeableComboObjectEditor triggerTypeSelectionCombo;
	private Button applicationRadioButton;
	private Button triggerRadioButton;

	public StardustInterfaceDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustInterfaceDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		final StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		setTitle("Stardust Interface");
		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustInterfaceType_ApplicationType();

		boolean isTrigger = null != sdInterface.getStardustTrigger();
		Composite btnGrp = getToolkit().createComposite(getAttributesParent(), SWT.NONE);
		GridData typeGroupGridData = new GridData(SWT.LEFT, SWT.TOP, false, true, 3, 1);
		GridLayout layout = new GridLayout(2, false);
		btnGrp.setLayout(layout);
		btnGrp.setLayoutData(typeGroupGridData);

		applicationRadioButton = createRadioButton(btnGrp, InterfaceTypeEnum.APPLICATION.getLabel(), InterfaceTypeEnum.APPLICATION, !isTrigger, sdInterface);
		triggerRadioButton = createRadioButton(btnGrp, InterfaceTypeEnum.TRIGGER.getLabel(), InterfaceTypeEnum.TRIGGER, isTrigger, sdInterface);

		// create a details section for the selected application type
		DefaultDetailComposite details = null;

		if (isTrigger) {
			// We'll handle this ourselves: create a Combo editor which
			// will swap in different widgets based on the Combo selection
			triggerTypeSelectionCombo = new ChangeableComboObjectEditor(this, sdInterface, feature) {

				@Override
				public boolean setValue(final Object newValue) {
					final Object oldValue = getValue();
					if (super.setValue(newValue)) {
						if (oldValue != newValue) {
							setBusinessObject(sdInterface);
						}
						return true;
					}
					return false;
				}
			};

			triggerTypeSelectionCombo.createControl(getAttributesParent(), "Trigger Type");
			String appTypeStr = sdInterface.getApplicationType();
			TriggerAppTypeEnum triggerType = TriggerAppTypeEnum.forKey(appTypeStr);
			if (null == triggerType) return;

			switch(triggerType) {
			case JMS:
				details = new StardustJmsTriggerDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			default:
				break;
			}

		} else {
			// We'll handle this ourselves: create a Combo editor which
			// will swap in different widgets based on the Combo selection
			applicationTypeSelectionCombo = new ChangeableComboObjectEditor(this, sdInterface, feature) {

				@Override
				public boolean setValue(final Object newValue) {
					final Object oldValue = getValue();
					if (super.setValue(newValue)) {
						// "newValue" should be a String or null
						if (oldValue != newValue) {
							// This will force a rebuild of the entire Property
							// Sheet tab.
							setBusinessObject(sdInterface);
						}
						return true;
					}
					return false;
				}
			};
			applicationTypeSelectionCombo.createControl(getAttributesParent(), "Application Type");

			String appTypeStr = sdInterface.getApplicationType();
			ApplicationTypes appType = ApplicationTypes.forKey(appTypeStr);
			if (null == appType) return;

			switch(appType) {
			case WEBSERVICE:
				details = new WebServiceDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			case CAMELCONSUMER:
				details = new CamelDetailComposite(getAttributesParent(), SWT.NONE, appType);
				break;
			case CAMELPRODUCER_SEND:
				details = new CamelDetailComposite(getAttributesParent(), SWT.NONE, appType);
				break;
			case CAMELPRODUCER_SENDRECEIVE:
				details = new CamelDetailComposite(getAttributesParent(), SWT.NONE, appType);
				break;
			case PLAINJAVA:
				details = new PlainJavaDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			case SPRINGBEAN:
				details = new SpringBeanDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			case EXTERNAL_WEBAPP:
				details = new ExtWebApplicationDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			case JMS:
				details = new JmsApplicationDetailComposite(getAttributesParent(), SWT.NONE);
				break;
			case SESSIONBEAN:
			default:
				break;
			}

		}
		// rebuild the service-specific details section
		if (details != null)
			details.setBusinessObject(sdInterface);
	}

	protected Button createRadioButton(final Composite parent, String label, Object value, boolean checked, final StardustInterfaceType sdInterface) {
		final Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setBackground(parent.getBackground());
		button.setData( value );
		button.setSelection(checked);
		button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				InterfaceTypeEnum selection = null;
				if (applicationRadioButton.getSelection()) {
					selection = InterfaceTypeEnum.APPLICATION;
				} if (triggerRadioButton.getSelection()) {
					selection = InterfaceTypeEnum.TRIGGER;
				}
				if (InterfaceTypeEnum.APPLICATION.equals(selection)) {
					if (null == sdInterface.getStardustApplication()) {
						RecordingCommand command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								sdInterface.setApplicationType(null);
								sdInterface.setStardustTrigger(null);
								sdInterface.setStardustApplication(SdbpmnFactory.eINSTANCE.createStardustApplicationType());
							}
						};
						editingDomain.getCommandStack().execute(command);
					}
				} else {
					if (null == sdInterface.getStardustTrigger()) {
						RecordingCommand command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								sdInterface.setApplicationType(null);
								sdInterface.setStardustApplication(null);
								sdInterface.setStardustTrigger(SdbpmnFactory.eINSTANCE.createStardustTriggerType());
							}
						};
						editingDomain.getCommandStack().execute(command);
					}
				}
				setBusinessObject(sdInterface);
				getPropertySection().layout();
				getPropertySection().refresh();
				layout();
				redraw();
			}
		});
		return button;
	}

	private abstract class ChangeableComboObjectEditor extends ComboObjectEditor {

		public ChangeableComboObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
			super(parent, object, feature);
		}

		@Override
		public boolean setValue(final Object newValue) {
			return super.setValue(newValue);
		}

	}

}