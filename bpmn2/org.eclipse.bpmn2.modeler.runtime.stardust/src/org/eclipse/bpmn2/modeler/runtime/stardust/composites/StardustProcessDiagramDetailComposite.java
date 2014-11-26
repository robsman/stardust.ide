package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons.createAttributeType;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.ProcessAttributes.ATTACHMENTS_UNIQUE_PER_ROOT;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.ui.property.diagrams.ProcessDiagramDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 *
 * @author Simon Nikles
 *
 */
public class StardustProcessDiagramDetailComposite extends ProcessDiagramDetailComposite {


	public StardustProcessDiagramDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustProcessDiagramDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);

		StardustProcessType sdProcess = null;

		List<StardustProcessType> list = ModelDecorator.getAllExtensionAttributeValues(be, StardustProcessType.class);
		if (list.size() > 0) {
			sdProcess = list.get(0);
		} else {

			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustProcess();
			sdProcess = SdbpmnFactory.eINSTANCE.createStardustProcessType();
			StardustAttributesType sdAttributes = SdbpmnFactory.eINSTANCE.createStardustAttributesType();
			AttributeType attachmentsPerRoot =
					createAttributeType(ATTACHMENTS_UNIQUE_PER_ROOT.attributeName(),
										ATTACHMENTS_UNIQUE_PER_ROOT.defaultVal(),
										ATTACHMENTS_UNIQUE_PER_ROOT.dataType());
			sdAttributes.getAttributeType().add(attachmentsPerRoot);
			sdProcess.setStardustAttributes(sdAttributes);

			ModelDecorator.addExtensionAttributeValue(be, feature, sdProcess, true);

		}

		StardustProcessDetailComposite sdProcessSection = new StardustProcessDetailComposite(this, SWT.NONE);
		sdProcessSection.setBusinessObject(sdProcess);
		sdProcessSection.setTitle(Messages.composite_process_stardust_process_title);

	}

}

