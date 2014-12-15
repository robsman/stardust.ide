package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.bpmn2.modeler.ui.property.diagrams.DefinitionsPropertyComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDefinitionsPropertyComposite extends DefinitionsPropertyComposite {

	private EStructuralFeature featureCarnotVersion = ExtensionHelper.MODEL_ATT_CARNOT_VERSION;
	private EStructuralFeature featureModelVersion = ExtensionHelper.MODEL_ATT_MODEL_VERSION;

	public StardustDefinitionsPropertyComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustDefinitionsPropertyComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
		if (be instanceof Definitions) {
			final Definitions defs = (Definitions)be;

			initModelAttributes(defs);

			TextObjectEditor editor = new TextObjectEditor(this, be, featureCarnotVersion);
			editor.createControl(attributesComposite, Labels.model_stardust_target_version);

			editor = new TextObjectEditor(this, be, featureModelVersion);
			editor.createControl(attributesComposite, Labels.model_stardust_model_version);

		}
	}

	private void initModelAttributes(final Definitions defs) {

		final List<Object> carnotVersionValues = ModelDecorator.getAllExtensionAttributeValues(defs, featureCarnotVersion);
		final List<Object> modelVersionValues = ModelDecorator.getAllExtensionAttributeValues(defs, featureModelVersion);

		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				if (nullOrEmpty(modelVersionValues)) {
					ExtensionHelper.getInstance().setModelVersion(defs, "1");
				}
				if (nullOrEmpty(carnotVersionValues)) {
					ExtensionHelper.getInstance().setModelCarnotVersion(defs, "");
				}
			}
		};
		editingDomain.getCommandStack().execute(command);

	}

	private boolean nullOrEmpty(List<Object> list) {
		return null == list || list.isEmpty();
	}

}
