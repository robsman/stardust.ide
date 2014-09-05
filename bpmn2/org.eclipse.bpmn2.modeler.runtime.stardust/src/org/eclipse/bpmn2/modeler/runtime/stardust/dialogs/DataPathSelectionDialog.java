package org.eclipse.bpmn2.modeler.runtime.stardust.dialogs;

import java.util.List;

import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Simon Nikles
 *
 */
public class DataPathSelectionDialog extends ElementListSelectionDialog {
	
	public DataPathSelectionDialog(Shell parentShell, BPMN2Editor editor, List<String> paths) {
		super(parentShell, DataPathSelectionDialogLabelProvider.newProvider());
		setElements(paths.toArray());
		setTitle(Messages.StardustDataMappingDialog_Title);
	}

	public static class DataPathSelectionDialogLabelProvider implements ILabelProvider {

		public static DataPathSelectionDialogLabelProvider newProvider() {
			return new DataPathSelectionDialogLabelProvider();
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
			System.out.println("####################### " + selectionElement);
			System.out
					.println("DataPathSelectionDialog.DataPathSelectionDialogLabelProvider.getText()");
			return null != selectionElement ? selectionElement.toString() : "";
		}
		
	}

}
