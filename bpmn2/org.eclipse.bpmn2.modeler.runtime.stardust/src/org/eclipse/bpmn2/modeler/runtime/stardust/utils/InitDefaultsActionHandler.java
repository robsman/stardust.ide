package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Simon Nikles
 *
 */
public class InitDefaultsActionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		if (null != editor) {
			final Definitions definitions = ModelUtil.getDefinitions(editor.getBpmnDiagram());
			TransactionalEditingDomain domain = editor.getEditingDomain();
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					BpmnDefaultContentsUtil.addDefaultContents(definitions);
				}
			});
		}
		return null;
	}

}
