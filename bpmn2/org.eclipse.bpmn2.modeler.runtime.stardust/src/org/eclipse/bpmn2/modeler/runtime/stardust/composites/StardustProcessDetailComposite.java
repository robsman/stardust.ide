package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.ProcessAttributes.ATTACHMENTS_UNIQUE_PER_ROOT;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.ProcessAttributes;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.CamelAttributes;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 *
 * @author Simon Nikles
 *
 */
public class StardustProcessDetailComposite extends DefaultDetailComposite {

	private static final String ATTACHMENT_PROPERTY = "supportsProcessAttachments";

	AttributeTypeBooleanEditor dmsByRefCheckbox = null;

	public StardustProcessDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustProcessDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);

		final StardustProcessType sdprocess = (StardustProcessType)be;
		if (null != sdprocess.getStardustAttributes()) {
			AttributeType dmsByRef = PropertyAdapterCommons.findAttributeType(sdprocess.getStardustAttributes(), ATTACHMENTS_UNIQUE_PER_ROOT.attributeName());
			dmsByRefCheckbox = new AttributeTypeBooleanEditor(this, dmsByRef);
			dmsByRefCheckbox.createControl(getAttributesParent(), ATTACHMENTS_UNIQUE_PER_ROOT.label());
			dmsByRefCheckbox.setVisible(sdprocess.isSupportsProcessAttachments());
		}
	}

	protected Composite bindProperty(EObject be, String property) {
		if (ATTACHMENT_PROPERTY.equals(property)) {
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustProcessType_SupportsProcessAttachments();
			String label = getBusinessObjectDelegate().getLabel(be, feature);
			final StardustProcessType sdProcess = (StardustProcessType)be;
			BooleanObjectEditor editor = new BooleanObjectEditor(this, be, feature) {
				@Override
				public Control createControl(Composite composite, String label) {
					Control ctrl = super.createControl(composite, label);
					button.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent evt) {
							if (null != dmsByRefCheckbox) {
								if (null != sdProcess.getStardustAttributes()) {
									dmsByRefCheckbox.setVisible(sdProcess.isSupportsProcessAttachments());
									update();
									getAttributesParent().update();
								}
							}
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent evt) {}
					});
					return ctrl;
				};
			};
			editor.createControl(getAttributesParent(), label);
		} else {
			return super.bindProperty(be, property);
		}
		return null;
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						ATTACHMENT_PROPERTY
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
}

