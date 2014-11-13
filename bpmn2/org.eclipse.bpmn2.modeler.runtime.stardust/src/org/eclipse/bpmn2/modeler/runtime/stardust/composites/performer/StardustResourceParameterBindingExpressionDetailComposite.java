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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import java.util.List;

import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceParameter;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.dialogs.DataPathSelectionDialog;
import org.eclipse.bpmn2.modeler.runtime.stardust.dialogs.DataSelectionDialog;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.data.ExpressionDetailComposite;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * @author Simon Nikles
 *
 */
public class StardustResourceParameterBindingExpressionDetailComposite extends ExpressionDetailComposite implements ModifyListener {

	public StardustResourceParameterBindingExpressionDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustResourceParameterBindingExpressionDetailComposite(Composite parent, int style) {
		super(parent, style);
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
					Object selectedData = "";
					DataSelectionDialog dialog = new DataSelectionDialog(Display.getCurrent().getActiveShell(), BPMN2Editor.getActiveEditor(), object);
					int open = dialog.open();
					if (DataPathSelectionDialog.OK == open) {
						Object[] result = dialog.getResult();
						selectedData = null != result && result.length > 0 ? result[0] : null;
						if (selectedData instanceof ItemAwareElement) {
							// for some reason, the class is currenlty not always matched as instance of item aware...
							super.setText(((ItemAwareElement)selectedData).getId());
						}
						super.setText("");
					}
				}

			};
			editor.createControl(parent,label);
			editor.setMultiLine(false);
			editor.setEditable(true);
			((Text)editor.getControl()).addModifyListener(this);

		} else if ("evaluatesToTypeRef".equals(attribute.getName())){
			TextObjectEditor editor = new TextObjectEditor(this,object,attribute) {
				protected Control createControl(Composite composite, String label, int style) {
					setEditable(false);
					Control control = super.createControl(composite, label, style);
					return control;
				}
			};
			editor.createControl(parent,label);
		}
	}

	@Override
	public void modifyText(ModifyEvent evt) {
		RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				FormalExpression expression = (FormalExpression)getBusinessObject();
				ResourceParameterBinding binding = (ResourceParameterBinding)((DefaultDetailComposite)getParent()).getBusinessObject();
				ResourceRole resRole = (ResourceRole)binding.eContainer();
				if (null == binding || null == expression) {
					return;
				}
				ResourceParameter param = binding.getParameterRef();
					expression.setEvaluatesToTypeRef(null != param ? param.getType() : null);
				if (null == resRole) {
					return;
				}
				Resource resource = resRole.getResourceRef();
				List<StardustResourceType> ext = ModelDecorator.getAllExtensionAttributeValues(resource, StardustResourceType.class);
				if (null != ext && ext.size() > 0) {
					StardustResourceType sdResource = ext.get(0);
					if (null == sdResource) {
						return;
					}
					ConditionalPerformerType conditionalPerformer = sdResource.getStardustConditionalPerformer();
					//OrganizationType org = sdResource.getStardustOrganization();
					if ("data".equals(param.getName())) {
						sdResource.setDataId(expression.getBody());
					} else if ("dataPath".equals(param.getName())) {
						if (null != conditionalPerformer) {
							conditionalPerformer.setDataPath(expression.getBody());
						}
					} else if ("realmDataPath".equals(param.getName())) {
						if (null != conditionalPerformer) {
							AttributeType at = PropertyAdapterCommons.findAttributeType(conditionalPerformer, "carnot:engine:conditionalPerformer:realmData");
							if (null != at) {
								at.setValue(expression.getBody());
							}
						}
					} else if ("realmDataPath".equals(param.getName())) {
						if (null != conditionalPerformer) {
							AttributeType at = PropertyAdapterCommons.findAttributeType(conditionalPerformer, "carnot:engine:conditionalPerformer:realmDataPath");
							if (null != at) {
								at.setValue(expression.getBody());
							}
						}
					}
				}
			}
		};
		editingDomain.getCommandStack().execute(command);
	}


}
