package org.eclipse.bpmn2.modeler.runtime.stardust.dialogs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Simon Nikles
 *
 */
public class DataSelectionDialog extends ElementListSelectionDialog {

	private EObject owner;

	public DataSelectionDialog(Shell parentShell, BPMN2Editor editor, EObject owner) {
		super(parentShell, DataSelectionDialogLabelProvider.newProvider());
		this.owner = owner;
		setElements(getValues().toArray());
	}

	private List<EObject> getValues() {
		List<EObject> values = new ArrayList<EObject>();
		EObject container = ModelUtil.getContainer(owner);

		values.addAll( ModelUtil.collectAncestorObjects(container, "properties", new Class[] {Activity.class}) ); //$NON-NLS-1$
		values.addAll( ModelUtil.collectAncestorObjects(container, "properties", new Class[] {Process.class}) ); //$NON-NLS-1$
		values.addAll( ModelUtil.collectAncestorObjects(container, "properties", new Class[] {Event.class}) ); //$NON-NLS-1$
		values.addAll( ModelUtil.collectAncestorObjects(container, "dataStore", new Class[] {DocumentRoot.class}) ); //$NON-NLS-1$
		values.addAll( ModelUtil.collectAncestorObjects(container, "flowElements", new Class[] {FlowElementsContainer.class}, new Class[] {ItemAwareElement.class})); //$NON-NLS-1$
		return values;
	}

	public static class DataSelectionDialogLabelProvider implements ILabelProvider {

		public static DataSelectionDialogLabelProvider newProvider() {
			return new DataSelectionDialogLabelProvider();
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
			if (selectionElement instanceof ItemAwareElement) {
				try {
					Method m =  selectionElement.getClass().getDeclaredMethod("getName");
					if (null != m) {
						return m.invoke(selectionElement).toString();
					}
				} catch (Exception e) {}
				return ((ItemAwareElement)selectionElement).getId();
			}
			return ""; //null != selectionElement ? selectionElement.get : "";
		}

	}

}
