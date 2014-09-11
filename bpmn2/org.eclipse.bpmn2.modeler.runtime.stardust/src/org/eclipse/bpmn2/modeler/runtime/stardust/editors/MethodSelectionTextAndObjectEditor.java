package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import java.net.MalformedURLException;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.dialogs.MemberOfClassSelectionDialog;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.CompositeUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.IntrinsicJavaAccessPointInfo;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MethodSelectionTextAndObjectEditor extends TextAndButtonObjectEditor {
	
	private final AttributeType clsAt;
	private boolean withConstructors = false;

	public MethodSelectionTextAndObjectEditor(AbstractDetailComposite parent, StardustInterfaceType sdInterface, EObject object, EStructuralFeature feature, AttributeType clsAt, boolean withConstructors) {
		super(parent, object, feature);
		this.clsAt = clsAt;
		this.withConstructors = withConstructors;
	}

	@Override
	protected Control createControl(Composite composite, String label, int style) {
		setMultiLine(false);
		Control control = super.createControl(composite, label, style);
		text.setEditable(true);
		setEditable(true);
		return control;
	}

	@Override
	protected void buttonClicked(int buttonId) {
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object.eResource());
		BasicCommandStack commandStack = (BasicCommandStack) editingDomain.getCommandStack();
		commandStack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				try {
					showDialog(object, feature);
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | MalformedURLException
						| CoreException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void showDialog(EObject object, EStructuralFeature feature) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		IType type = IntrinsicJavaAccessPointInfo.findTypeInWorkspace(clsAt.getValue());
		String selectedMethodStr = "";
		IMethod selectedMethod = null;
		CompositeUtil cu = new CompositeUtil();
		Shell shell = Display.getCurrent().getActiveShell();
		BPMN2Editor editor = BPMN2Editor.getActiveEditor();
		List<IMethod> methodsAndConstructors = cu.getAllMethodsAndConstructors(type, withConstructors);
		MemberOfClassSelectionDialog dialog = new MemberOfClassSelectionDialog(shell, editor, methodsAndConstructors, withConstructors);
		int open = dialog.open();
		if (MemberOfClassSelectionDialog.OK == open) {
			Object[] result = dialog.getResult();
			if (null != result && result.length > 0) {
				selectedMethod = (IMethod) result[0];
				if (null != selectedMethod) {
					if (withConstructors) {
						selectedMethodStr = IntrinsicJavaAccessPointInfo.encodeConstructor(selectedMethod);
					} else {
						selectedMethodStr = IntrinsicJavaAccessPointInfo.encodeMethod(selectedMethod);
					}
				}
			}
			super.setText(selectedMethodStr);
		}
	}
}