/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.spring;

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.ID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.BindableElementAttributes.OID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.BEAN_ID;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.CLASS_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.METHOD_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.RETRY_ENABLE;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.RETRY_INTERVAL;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.RETRY_NUMBER;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.SpringBeanAttributes.VISIBILITY;

import java.lang.reflect.Method;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MethodSelectionTextAndObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.StardustInterfaceSelectionObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint.IntrinsicJavaAccessPointInfo;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SpringBeanDetailComposite extends DefaultDetailComposite implements ModifyListener{

	public SpringBeanDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public SpringBeanDetailComposite(Composite parent, int style) {
		super(parent, style);
	}


	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();
		setTitle("Spring Bean Service Configuration");

		StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;

		StardustApplicationType sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, NAME);
		bindAttribute(sdApplication, ID);
		bindAttribute(sdApplication, OID);

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, VISIBILITY.attributeName());
		editor = new AttributeTypeComboEditor(this, at, VISIBILITY.choices());
		editor.createControl(parent, VISIBILITY.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, BEAN_ID.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, BEAN_ID.label());

		final AttributeType clsAt = PropertyAdapterCommons.findAttributeType(sdApplication, CLASS_NAME.attributeName());
		AttributeType methodAt = PropertyAdapterCommons.findAttributeType(sdApplication, METHOD_NAME.attributeName());

		MethodSelectionTextAndObjectEditor methodEditor = new MethodSelectionTextAndObjectEditor(this, sdInterface, methodAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), clsAt, false);

		// initialize method drop down menu
		StardustInterfaceSelectionObjectEditor importEditor = new StardustInterfaceSelectionObjectEditor(this,sdInterface,clsAt,CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		Text textCls = (Text)importEditor.createControl(parent, CLASS_NAME.label());
		Text textMeth = (Text)methodEditor.createControl(parent, METHOD_NAME.label());
		textCls.addModifyListener(this);
		textMeth.addModifyListener(this);

		at = PropertyAdapterCommons.findAttributeType(sdApplication, RETRY_ENABLE.attributeName());
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, RETRY_ENABLE.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, RETRY_NUMBER.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, RETRY_NUMBER.label());

		at = PropertyAdapterCommons.findAttributeType(sdApplication, RETRY_INTERVAL.attributeName());
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, RETRY_INTERVAL.label());

	}

	@Override
	public void modifyText(final ModifyEvent event) {
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(businessObject.eResource());
		BasicCommandStack commandStack = (BasicCommandStack) editingDomain.getCommandStack();
		commandStack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				StardustInterfaceType sdIntType = (StardustInterfaceType) businessObject;
				StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdIntType);

				final AttributeType clsAt = PropertyAdapterCommons.findAttributeType(sdIntType.getStardustApplication(), CLASS_NAME.attributeName());
				final AttributeType methodAt = PropertyAdapterCommons.findAttributeType(sdIntType.getStardustApplication(), METHOD_NAME.attributeName());
				Class<?> clazz = null;
				Method method = null;
				if (null != clsAt.getValue() && !clsAt.getValue().isEmpty()) {
					clazz = IntrinsicJavaAccessPointInfo.findClassInWorkspace(clsAt.getValue());
					if (null != clazz && null != methodAt.getValue() && !methodAt.getValue().isEmpty()) {
						method = IntrinsicJavaAccessPointInfo.decodeMethod(clazz, methodAt.getValue());
					}
					StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos((StardustInterfaceType) businessObject, method, null);
				}
			}
		});
	}

}
