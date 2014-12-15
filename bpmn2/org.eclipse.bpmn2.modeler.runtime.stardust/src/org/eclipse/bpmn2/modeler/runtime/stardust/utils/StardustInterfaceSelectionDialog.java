/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013 Red Hat, Inc.
 * All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import org.eclipse.bpmn2.modeler.ui.property.dialogs.DefaultSchemaImportDialog;
import org.eclipse.bpmn2.modeler.ui.property.dialogs.SchemaImportDialog;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * This is a specialized Java Type Import dialog for importing BPMN2 Interface definitions.
 * It extends the default import dialog by adding a checkbox that allows the user to select
 * whether or not Process variables (a.k.a. BPMN2 "Property" elements) will be created.
 *
 * @author Bob Brodt
 * @author Gregor Gisler
 *
 */
public class StardustInterfaceSelectionDialog extends DefaultSchemaImportDialog {

	boolean createVariables = false;
	boolean includeConstructors = false;
	CheckboxTableViewer methodsTable;
	IMethod[] selectedMethods = new IMethod[0];

	public StardustInterfaceSelectionDialog() {
		super(Display.getDefault().getActiveShell(), SchemaImportDialog.ALLOW_JAVA);
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);

		Composite tableComposite = new Composite(contents, SWT.NONE);
		GridLayout layout = new GridLayout(3,false);
		layout.marginWidth = 0;
		tableComposite.setLayout(layout);
		tableComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));

		return contents;
	}

	public IType getIType() {
		Object result[] = getResult();
		if (result!=null && result.length == 1 && result[0] instanceof IType) {
			return (IType) result[0];
		}
		return null;
	}

	@Override
	protected void computeResult() {
		super.computeResult();

		if (methodsTable!=null) {
			Object[] checked = methodsTable.getCheckedElements();
			selectedMethods = new IMethod[checked.length];
			for (int i=0; i<checked.length; ++i)
				selectedMethods[i] = (IMethod) checked[i];
		}
		else
			selectedMethods = new IMethod[0];
	}

}
