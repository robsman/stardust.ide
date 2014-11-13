package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.bpmn2.modeler.ui.property.diagrams.DefinitionsPropertyComposite;
import org.eclipse.bpmn2.util.XmlExtendedMetadata;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDefinitionsPropertyComposite extends DefinitionsPropertyComposite {

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
			EStructuralFeature feature = ExtensionHelper.getInstance().getAnyAttributeFeature((Definitions)be, ExtensionHelper2.STARDUST_CARNOT_VERSION_PROPERTY);
			if (null == feature) {
				final Definitions defs = (Definitions)be;
				RecordingCommand command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						ExtensionHelper2.INSTANCE.setCarnotVersion(null, defs);
					}
				};
				editingDomain.getCommandStack().execute(command);
				feature = ExtensionHelper.getInstance().getAnyAttributeFeature((Definitions)be, ExtensionHelper2.STARDUST_CARNOT_VERSION_PROPERTY);
			}
			TextObjectEditor editor = new TextObjectEditor(this, be, feature);
			editor.createControl(attributesComposite, "Stardust / IPP Version (optional)");
		}
	}

}
