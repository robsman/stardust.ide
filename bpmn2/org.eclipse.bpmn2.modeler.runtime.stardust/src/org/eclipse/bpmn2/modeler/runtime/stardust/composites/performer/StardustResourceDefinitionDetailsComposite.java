package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.DATA;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.DATA_PATH;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.REALM_DATA;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.ResourceDataMappingAttributeNames.REALM_DATA_PATH;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust.STRING;

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceParameter;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.conditional.ConditionalPerformerDetailsComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
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
public class StardustResourceDefinitionDetailsComposite extends DefaultDetailComposite implements ISelectionChangedListener {

	private static final String PREFIX_DELIM = ":";

	Button roleRadioButton = null;
	Button organisationRadioButton = null;
	Button conditionalRadioButton = null;

	public StardustResourceDefinitionDetailsComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustResourceDefinitionDetailsComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
//		super.createBindings(be);

		StardustResourceType sdResource = (StardustResourceType)be;

		Composite btnGrp = getToolkit().createComposite(getAttributesParent(), SWT.NONE);
		GridData typeGroupGridData = new GridData(SWT.LEFT, SWT.TOP, false, true, 3, 1);
		GridLayout layout = new GridLayout(2, false);
		btnGrp.setLayout(layout);
		btnGrp.setLayoutData(typeGroupGridData);

		PerformerTypeEnum currentType = getType(sdResource);

		roleRadioButton = createRadioButton(btnGrp, PerformerTypeEnum.ROLE.getLabel(), PerformerTypeEnum.ROLE, true, PerformerTypeEnum.ROLE.equals(currentType), sdResource);
		organisationRadioButton = createRadioButton(btnGrp, PerformerTypeEnum.ORGANISATION.getLabel(), PerformerTypeEnum.ORGANISATION, false, PerformerTypeEnum.ORGANISATION.equals(currentType),sdResource);
		conditionalRadioButton = createRadioButton(btnGrp, PerformerTypeEnum.CONDITIONAL.getLabel(), PerformerTypeEnum.CONDITIONAL, true, PerformerTypeEnum.CONDITIONAL.equals(currentType), sdResource);

		switch (currentType) {
		case ROLE:
			break;
		case ORGANISATION:
			break;
		case CONDITIONAL:
			ConditionalPerformerType performer = sdResource.getStardustConditionalPerformer();

			ConditionalPerformerDetailsComposite conditionalPerformerSection = new ConditionalPerformerDetailsComposite(this, SWT.NONE);
			conditionalPerformerSection.setBusinessObject(performer);
			conditionalPerformerSection.setTitle(Messages.composite_resource_conditionalPerformer);

		default:
			break;
		}
	}

	private PerformerTypeEnum getType(StardustResourceType sdResource) {
		if (null == sdResource) return PerformerTypeEnum.ROLE;
		if (null != sdResource.getStardustConditionalPerformer()) {
			return PerformerTypeEnum.CONDITIONAL;
		}
		if (null != sdResource.getStardustOrganization()) {
			return PerformerTypeEnum.ORGANISATION;
		}
		return PerformerTypeEnum.ROLE;
	}

	protected Button createRadioButton(final Composite parent, String label, Object value, boolean visible, boolean checked, final StardustResourceType sdResource) {
		final Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setBackground(parent.getBackground());
		button.setData( value );
		button.setSelection(checked);
		button.setVisible(visible);
		button.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				PerformerTypeEnum selection = null;
				if (roleRadioButton.getSelection()) {
					selection = PerformerTypeEnum.ROLE;
				} else if (organisationRadioButton.getSelection()) {
					selection = PerformerTypeEnum.ORGANISATION;
				} else if (conditionalRadioButton.getSelection()) {
					selection = PerformerTypeEnum.CONDITIONAL;
				}
				if (PerformerTypeEnum.ROLE.equals(selection)) {
					if (null == sdResource.getStardustRole()) {
						RecordingCommand command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								sdResource.setStardustRole(CarnotWorkflowModelFactory.eINSTANCE.createRoleType());
								sdResource.setStardustOrganization(null);
								sdResource.setStardustConditionalPerformer(null);
							}
						};
						editingDomain.getCommandStack().execute(command);
					}
				} else if (PerformerTypeEnum.ORGANISATION.equals(selection)) {
					if (null == sdResource.getStardustOrganization()) {
						RecordingCommand command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								sdResource.setStardustRole(null);
								sdResource.setStardustOrganization(CarnotWorkflowModelFactory.eINSTANCE.createOrganizationType());
								sdResource.setStardustConditionalPerformer(null);
							}
						};
						editingDomain.getCommandStack().execute(command);
					}
				} else if (PerformerTypeEnum.CONDITIONAL.equals(selection)) {
					if (null == sdResource.getStardustConditionalPerformer()) {
						RecordingCommand command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								cleanupParameterMappings((Resource)sdResource.eContainer().eContainer());
								ConditionalPerformerType performer = CarnotWorkflowModelFactory.eINSTANCE.createConditionalPerformerType();
								ModelUtil.setID(performer, sdResource.eResource());
								sdResource.setStardustRole(null);
								sdResource.setStardustOrganization(null);
								sdResource.setStardustConditionalPerformer(performer);
								createConditionalPerformerParameterMappings((Resource)sdResource.eContainer().eContainer(), sdResource);
							}
						};
						editingDomain.getCommandStack().execute(command);
					}
				}
				setBusinessObject(sdResource);
				getPropertySection().getSectionRoot().redrawPage();
			}
		});
		return button;
	}

	private void cleanupParameterMappings(Resource resource) {
		if (null != resource && null != resource.getResourceParameters()) {
			resource.getResourceParameters().clear();
		}
	}

	/**
	 * for possible future use: transform using bpmn2 parameters (e.g. create a conditional performer for each actual mapping from a task)
	 */
	private void createConditionalPerformerParameterMappings(Resource resource, StardustResourceType sdResource) {
		resource.getResourceParameters().clear();

		ResourceParameter data = Bpmn2Factory.eINSTANCE.createResourceParameter();
		ResourceParameter dataPath = Bpmn2Factory.eINSTANCE.createResourceParameter();
		ResourceParameter realmData = Bpmn2Factory.eINSTANCE.createResourceParameter();
		ResourceParameter realmDataPath = Bpmn2Factory.eINSTANCE.createResourceParameter();

		ItemDefinition def = findPrimitiveString(resource);

		data.setId(DATA.internalName());
		data.setIsRequired(true);
		data.setName(DATA.label());
		data.setType(def);

		dataPath.setId(DATA_PATH.internalName());
		dataPath.setIsRequired(true);
		dataPath.setName(DATA_PATH.label());
		dataPath.setType(def);

		realmData.setId(REALM_DATA.internalName());
		realmData.setIsRequired(true);
		realmData.setName(REALM_DATA.label());
		realmData.setType(def);

		realmDataPath.setId(REALM_DATA_PATH.internalName());
		realmDataPath.setIsRequired(true);
		realmDataPath.setName(REALM_DATA_PATH.label());
		realmDataPath.setType(def);

		/*
		resource.getResourceParameters().add(data);
		resource.getResourceParameters().add(dataPath);

		ConditionalPerformerType performer = sdResource.getStardustConditionalPerformer();
		if (null != sdResource && null != performer && performer.isIsUser()) {
			resource.getResourceParameters().add(realmData);
			resource.getResourceParameters().add(realmDataPath);
		}*/

	}

	private ItemDefinition findPrimitiveString(Resource resource) {
		Definitions definitions = ModelUtil.getDefinitions(resource);
		String xsdPrefix = NamespaceUtil.getPrefixForNamespace(resource.eResource(), XSDType2Stardust.XML_SCHEMA_URI);
		String stringType = xsdPrefix.concat(PREFIX_DELIM).concat(STRING.getName());
		List<ItemDefinition> itemDefs = ModelUtil.getAllRootElements(definitions, ItemDefinition.class);
		for (ItemDefinition def : itemDefs) {
			if (null != def.getStructureRef() && def.getStructureRef().toString().equals(stringType)) {
				return def;
			}
		}
		ItemDefinition str = Bpmn2Factory.eINSTANCE.createItemDefinition();
		str.setStructureRef(xsdPrefix.concat(PREFIX_DELIM).concat(STRING.getName()));
		ModelUtil.setID(str);
		definitions.getRootElements().add(str);
		return str;
	}

	protected void createEmptyLabel(EObject be) {

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
