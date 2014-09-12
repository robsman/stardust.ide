package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles 
 * @author Bob Brodt
 */
public class StardustDataAssignmentDetailComposite extends DefaultDetailComposite {

	private StardustAssignmentExpressionDetailComposite fromComposite;
	private StardustAssignmentExpressionDetailComposite toComposite;

	public StardustDataAssignmentDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public StardustDataAssignmentDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	
	@Override
	protected void cleanBindings() {
		super.cleanBindings();
		fromComposite = null;
		toComposite = null;
	}
	
	@Override
	public void createBindings(final EObject be) {
		if (be instanceof Assignment) {			
			Assignment assignment = createMissingFromTo((Assignment) be);
			Expression from = assignment.getFrom();
			Expression to = assignment.getTo();
			if (null == fromComposite) {
				fromComposite =  new StardustAssignmentExpressionDetailComposite(this, SWT.NONE, StardustAssignmentExpressionDetailComposite.AssignmentPart.FROM_EXPR);
			}
			fromComposite.setBusinessObject(from);
			fromComposite.setTitle(Messages.StardustDataAssignmentDetailComposite_From_Title);
			if (null == toComposite) {
				toComposite = new StardustAssignmentExpressionDetailComposite(this, SWT.NONE, StardustAssignmentExpressionDetailComposite.AssignmentPart.TO_EXPR);
			}
			toComposite.setBusinessObject(to);	
			toComposite.setTitle(Messages.StardustDataAssignmentDetailComposite_To_Title);
		}
	}

	private Assignment createMissingFromTo(final Assignment assignment) {
		Expression from = assignment.getFrom();
		Expression to = assignment.getTo();
		final boolean addFrom = null == from;
		final boolean addTo = null == to;				
		if (addFrom || addTo) {
			RecordingCommand command = new RecordingCommand(editingDomain) {				
				@Override
				protected void doExecute() {
					if (addFrom) {
						Expression exp = createModelObject(FormalExpression.class);
						assignment.setFrom(exp);
					} 
					if (addTo) {
						Expression exp = createModelObject(FormalExpression.class);
						assignment.setTo(exp);
					}
				}
			};			
			editingDomain.getCommandStack().execute(command);
		}
		return assignment;
	}
}
