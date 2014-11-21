package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.conditional;

import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer.ConditionalPerformerResultEnum.USER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.DATA;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.DATA_PATH;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.performer.ConditionalPerformerAttributes.PERFORMER_KIND;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.performer.ConditionalPerformerAttributes.USER_REALM_DATA;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.performer.ConditionalPerformerAttributes.USER_REALM_DATA_PATH;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.performer.ConditionalPerformerAttributes.VISIBILITY;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer.ConditionalPerformerResultEnum;
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
		at = PropertyAdapterCommons.findAttributeType(performer, VISIBILITY.attributeName());
		editor = new AttributeTypeComboEditor(this, at, VISIBILITY.choices());
		editor.createControl(getAttributesParent(), VISIBILITY.label());

		at = PropertyAdapterCommons.findAttributeType(performer, PERFORMER_KIND.attributeName());
		AttributeTypeComboEditor choiceCombo = new AttributeTypeComboEditor(this, at, PERFORMER_KIND.choices());
		choiceCombo.createControl(getAttributesParent(), PERFORMER_KIND.label());
		choiceCombo.addSelectionListener(this);

		editor = new TextObjectEditor(this, sdResource, SdbpmnPackage.eINSTANCE.getStardustResourceType_DataId());
		editor.createControl(getAttributesParent(), DATA.label());

		super.createBindings(be);

		if (null != choiceCombo.getValue()) {
			ConditionalPerformerResultEnum kind = ConditionalPerformerResultEnum.forKey(choiceCombo.getValue().toString());
			if (USER.equals(kind)) {
				at = PropertyAdapterCommons.findAttributeType(performer, USER_REALM_DATA.attributeName());
				editor = new AttributeTypeTextEditor(this, at);
				editor.setEditable(false);
				editor.createControl(getAttributesParent(), USER_REALM_DATA.label());

				at = PropertyAdapterCommons.findAttributeType(performer, USER_REALM_DATA_PATH.attributeName());
				editor = new AttributeTypeTextEditor(this, at);
				editor.setEditable(false);
				editor.createControl(getAttributesParent(), USER_REALM_DATA_PATH.label());
			}
		}
	}

	private void initConditionalPerformerAttributes(final ConditionalPerformerType performer) {
		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				performer.setIsUser(false);
				if (null == PropertyAdapterCommons.findAttributeType(performer, VISIBILITY.attributeName())) {
					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType(VISIBILITY.attributeName(), VISIBILITY.defaultVal(), VISIBILITY.dataType()));
				}
				if (null == PropertyAdapterCommons.findAttributeType(performer, PERFORMER_KIND.attributeName())) {
					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType(PERFORMER_KIND.attributeName(), PERFORMER_KIND.defaultVal(), PERFORMER_KIND.dataType()));
//				}
//				if (null == PropertyAdapterCommons.findAttributeType(performer, "carnot:engine:conditionalPerformer:kind")) {
//					performer.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:conditionalPerformer:kind", "", null));
				} else {
					AttributeType at = PropertyAdapterCommons.findAttributeType(performer, PERFORMER_KIND.attributeName());
					if (ConditionalPerformerResultEnum.USER.getKey().equals(at.getValue())) {
						performer.setIsUser(true);

						if (null == PropertyAdapterCommons.findAttributeType(performer, USER_REALM_DATA.attributeName())) {
							performer.getAttribute().add(PropertyAdapterCommons.createAttributeType(USER_REALM_DATA.attributeName(), USER_REALM_DATA.defaultVal(), USER_REALM_DATA.dataType()));
						}
						if (null == PropertyAdapterCommons.findAttributeType(performer, USER_REALM_DATA_PATH.attributeName())) {
							performer.getAttribute().add(PropertyAdapterCommons.createAttributeType(USER_REALM_DATA_PATH.attributeName(), USER_REALM_DATA_PATH.defaultVal(), USER_REALM_DATA_PATH.dataType()));
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
						DATA_PATH.internalName()
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}

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
