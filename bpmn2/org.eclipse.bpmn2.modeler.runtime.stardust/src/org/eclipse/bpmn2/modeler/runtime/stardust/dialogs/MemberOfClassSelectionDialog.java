/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.dialogs;

import java.net.MalformedURLException;
import java.util.List;

import org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint.IntrinsicJavaAccessPointInfo;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Gregor Gisler
 *
 */
public class MemberOfClassSelectionDialog extends ElementListSelectionDialog {

	public MemberOfClassSelectionDialog(Shell parentShell, BPMN2Editor editor, List<IMethod> members, boolean constructorSelection) {
		super(parentShell, MemberOfClassSelectionDialogLabelProvider.newProvider(constructorSelection));
		setElements(members.toArray());
		if (constructorSelection) {
			setTitle(Messages.StardustConstructorSelection_Title);
		}
		else {
			setTitle(Messages.StardustMethodSelection_Title);
		}
	}

	public static class MemberOfClassSelectionDialogLabelProvider implements ILabelProvider {

		private boolean constructorSelection;

		public MemberOfClassSelectionDialogLabelProvider(boolean constructorSelection) {
			super();
			this.constructorSelection = constructorSelection;
		}

		public static MemberOfClassSelectionDialogLabelProvider newProvider(boolean constructorSelection) {
			return new MemberOfClassSelectionDialogLabelProvider(constructorSelection);
		}

		@Override
		public void addListener(ILabelProviderListener arg0) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener arg0) {
		}

		@Override
		public Image getImage(Object arg0) {
			return null;
		}

		@Override
		public String getText(Object selectionElement) {
			String name = null != selectionElement ? selectionElement.toString() : "undefined";
			if (selectionElement instanceof IMethod) {
				try {
					if (constructorSelection) {
						name = IntrinsicJavaAccessPointInfo.encodeConstructor((IMethod)selectionElement);
					} else {
						name = IntrinsicJavaAccessPointInfo.encodeMethod((IMethod)selectionElement);
					}
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | MalformedURLException
						| CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return name; //null != selectionElement ? selectionElement.toString() : "";
		}

	}

}
