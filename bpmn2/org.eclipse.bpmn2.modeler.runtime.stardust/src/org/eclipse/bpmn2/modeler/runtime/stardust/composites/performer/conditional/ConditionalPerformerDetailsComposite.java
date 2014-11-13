package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.conditional;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer.ConditionalPerformerResultEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.common.PropertyCommons.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class ConditionalPerformerDetailsComposite extends DefaultDetailComposite implements ISelectionChangedListener {

	public ConditionalPerformerDetailsComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public ConditionalPerformerDetailsComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {

		ConditionalPerformerType performer = (ConditionalPerformerType)be;
		StardustResourceType sdResource = (StardustResourceType)performer.eContainer();

		initConditionalPerformerAttributes(performer);

		AttributeType at;
		ObjectEditor editor = null;
		at = PropertyAdapterCommons.findAttributeType(performer, Visibility.NAME);
		editor = new AttributeTypeComboEditor(this, at, Visibility.getOptionKeys());
		editor.createControl(getAttributesParent(), "Visibility");

		at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind");
		AttributeTypeComboEditor choiceCombo = new AttributeTypeComboEditor(this, at, ConditionalPerformerResultEnum.getChoices());
		choiceCombo.createControl(getAttributesParent(), "Kind");
		choiceCombo.addSelectionListener(this);

		editor = new TextObjectEditor(this, sdResource, SdbpmnPackage.eINSTANCE.getStardustResourceType_DataId());
		editor.createControl(getAttributesParent(), "Data Id");

		super.createBindings(be);

		if (null != choiceCombo.getValue()) {
			ConditionalPerformerResultEnum kind = ConditionalPerformerResultEnum.forKey(choiceCombo.getValue().toString());
			if (ConditionalPerformerResultEnum.USER.equals(kind)) {
				at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmData");
				editor = new AttributeTypeTextEditor(this, at);
				editor.setEditable(false);
				editor.createControl(getAttributesParent(), "Realm Data");

				at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmDataPath");
				editor = new AttributeTypeTextEditor(this, at);
				editor.setEditable(false);
				editor.createControl(getAttributesParent(), "Realm Data Path");
			}
		}
	}

	private void initConditionalPerformerAttributes(final ConditionalPerformerType performer) {
		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				performer.setIsUser(false);
				if (null == PropertyAdapterCommons.findAttributeType(performer, Visibility.NAME)) {
					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType(Visibility.NAME, Visibility.PUBLIC.getKey(), null));
				}
				if (null == PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind")) {
					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:conditionalPerformer:kind", "", null));
				}
				if (null == PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind")) {
					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:conditionalPerformer:kind", "", null));
				} else {
					AttributeType at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind");
					if (ConditionalPerformerResultEnum.USER.getKey().equals(at.getValue())) {
						performer.setIsUser(true);

						if (null == PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmData")) {
							performer.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:conditionalPerformer:realmData", "", null));
						}
						if (null == PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmDataPath")) {
							performer.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:conditionalPerformer:realmDataPath", "", null));
						}
					}
				}
			}
		};
		editingDomain.getCommandStack().execute(command);
	}


	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
//						"data", //$NON-NLS-1$
						"dataPath"
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}

//	public void nocreateBindings(EObject be) {
//
//		ConditionalPerformerType performer = (ConditionalPerformerType)be;
//
//		AttributeType at;
//		ObjectEditor editor = null;
//
//		at = PropertyAdapterCommons.findAttributeType(performer, Visibility.NAME);
//		editor = new AttributeTypeComboEditor(this, at, Visibility.getOptionKeys());
//		editor.createControl(getParent(), "Visibility");
//
//		at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind");
//		AttributeTypeComboEditor choiceCombo = new AttributeTypeComboEditor(this, at, ConditionalPerformerResultEnum.getChoices());
//		choiceCombo.createControl(getParent(), "Kind");
//		choiceCombo.addSelectionListener(this);
//
//		if (null != choiceCombo.getValue()) {
//			ConditionalPerformerResultEnum kind = ConditionalPerformerResultEnum.forKey(choiceCombo.getValue().toString());
//			if (ConditionalPerformerResultEnum.USER.equals(kind)) {
//				at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmData");
//				editor = new AttributeTypeTextEditor(this, at);
//				editor.setEditable(false);
//				editor.createControl(getParent(), "Realm Data");
//
//				at = PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:realmDataPath");
//				editor = new AttributeTypeTextEditor(this, at);
//				editor.setEditable(false);
//				editor.createControl(getParent(), "Realm Data Path");
//			}
//		}
//
//	}

	@Override
	public void selectionChanged(SelectionChangedEvent arg0) {
		cleanBindings();
		setBusinessObject(businessObject);

		try {
			getPropertySection().getSectionRoot().redrawPage();
			getPropertySection().getTabbedPropertySheetPage().getCurrentTab().refresh();
		} catch (Exception e) {
			// maybe none selected
		}
	}



}
