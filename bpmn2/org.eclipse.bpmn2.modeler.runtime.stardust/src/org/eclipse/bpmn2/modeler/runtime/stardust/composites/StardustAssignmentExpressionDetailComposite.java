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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.dialogs.DataPathSelectionDialog;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustDataPathProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.data.ExpressionDetailComposite;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author Simon Nikles
 *
 */
public class StardustAssignmentExpressionDetailComposite extends ExpressionDetailComposite {

	public enum AssignmentPart {
		FROM_EXPR,
		TO_EXPR
	}

	private AssignmentPart fromOrTo;

	public StardustAssignmentExpressionDetailComposite(Composite parent, int style, AssignmentPart fromOrTo) {
		super(parent, style);
		this.fromOrTo = fromOrTo;
	}

	@Override
	protected void bindAttribute(Composite parent, EObject object, EAttribute attribute, String label) {
		if ("language".equals(attribute.getName())) {
			if (null==parent) parent = getAttributesParent();
			if (null==label) label = getBusinessObjectDelegate().getLabel(object, attribute);
			TextObjectEditor editor = new TextObjectEditor(this,object,attribute) {
				protected Control createControl(Composite composite, String label, int style) {
					Control control = super.createControl(composite, label, style);
					setText("http://eclipse.org/stardust/DataMappingPath");
					setEditable(false);
					return control;
				}
			};
			editor.createControl(parent,label);

		} else if ("body".equals(attribute.getName())) {

			TextAndButtonObjectEditor editor = new TextAndButtonObjectEditor(this, object, attribute) {

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
					showDialog(buttonId, object, feature);
				}

				private void showDialog(int buttonId, EObject object, EStructuralFeature feature) {
					ItemDefinition itemDef = getItemDefinition();
					String selectedDataPath = "";
					List<String> paths = StardustDataPathProvider.INSTANCE.getDataPaths(itemDef);
					DataPathSelectionDialog dialog = new DataPathSelectionDialog(Display.getCurrent().getActiveShell(), BPMN2Editor.getActiveEditor(), paths);
					int open = dialog.open();
					if (DataPathSelectionDialog.OK == open) {
						Object[] result = dialog.getResult();
						selectedDataPath = null != result && result.length > 0 ? result[0].toString() : "";
						super.setText(selectedDataPath);
					}
				}

			};

			editor.createControl(parent,label);
			editor.setMultiLine(false);
			editor.setEditable(true);
		} else return;
	}

	public ItemDefinition getItemDefinition() {
		FormalExpression expression = (FormalExpression)super.businessObject;
		Assignment assignment = (Assignment)expression.eContainer();
		DataAssociation association = (DataAssociation) assignment.eContainer();
		ItemDefinition itemSubjectRef = null;
		if (AssignmentPart.FROM_EXPR.equals(fromOrTo)) {
			List<ItemAwareElement> sourceRef = association.getSourceRef();
			if (null == sourceRef || sourceRef.size() <= 0) return null;
			ItemAwareElement source = sourceRef.get(0);
			if (null == source.getItemSubjectRef()) return null;
			itemSubjectRef = source.getItemSubjectRef();
		} else {
			ItemAwareElement targetRef = association.getTargetRef();
			if (null == targetRef) return null;
			itemSubjectRef = targetRef.getItemSubjectRef();
		}
		if (null == itemSubjectRef) return null;
		return itemSubjectRef;
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"language",
						"body"
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}


}
