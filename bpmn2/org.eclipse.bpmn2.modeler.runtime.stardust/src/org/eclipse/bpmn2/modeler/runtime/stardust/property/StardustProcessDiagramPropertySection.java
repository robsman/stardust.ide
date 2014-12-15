package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustProcessDiagramDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustProcessDiagramPropertySection extends DefaultPropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustProcessDiagramDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustProcessDiagramDetailComposite(parent,style);
	}

	@Override
	public EObject getBusinessObjectForSelection(ISelection selection) {

		EObject bo = super.getBusinessObjectForSelection(selection);
		System.out.println("BusinessObjectForSelection: " + bo);
		if (bo instanceof Process) {
			return bo;
		}
		else if (bo instanceof Participant) {
			return ((Participant)bo).getProcessRef();
		}

		return null;
	}
}
