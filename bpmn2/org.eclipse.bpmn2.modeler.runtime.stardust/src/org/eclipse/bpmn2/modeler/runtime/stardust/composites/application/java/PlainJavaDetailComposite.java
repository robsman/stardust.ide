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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.common.PropertyCommons.Visibility;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MethodSelectionTextAndObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.StardustInterfaceSelectionObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint.IntrinsicJavaAccessPointInfo;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PlainJavaDetailComposite extends DefaultDetailComposite implements ModifyListener {


	public PlainJavaDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public PlainJavaDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();
		setTitle("Plain Java Service Configuration");

		StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;

		StardustApplicationType sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");
		
		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, Visibility.NAME);
		editor = new AttributeTypeComboEditor(this, at, Visibility.getOptionKeys());
		editor.createControl(parent, "Visibility");
		

		final AttributeType clsAt = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:className");
		final AttributeType methodAt = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:methodName");
		
		MethodSelectionTextAndObjectEditor methodEditor = new MethodSelectionTextAndObjectEditor(this, sdInterface, methodAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), clsAt, false);

		AttributeType constrAt = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:constructorName");
		MethodSelectionTextAndObjectEditor constructorEditor = new MethodSelectionTextAndObjectEditor(this, sdInterface, constrAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), clsAt, true);
		
		StardustInterfaceSelectionObjectEditor importEditor = new StardustInterfaceSelectionObjectEditor(this, sdInterface, clsAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		Text textCls = (Text)importEditor.createControl(parent,"Class Selector");
		Text textMeth = (Text)methodEditor.createControl(parent,"Method");		
		Text textConst = (Text)constructorEditor.createControl(parent,"Constructor");
		textCls.addModifyListener(this);
		textMeth.addModifyListener(this);
		textConst.addModifyListener(this);				
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:enable");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Enable Retry");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:number");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Number of Retries");
		
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "synchronous:retry:time");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Time between Retries (seconds)");

	}

	@Override
	public void modifyText(ModifyEvent arg0) {
		System.out.println("Constructor has been changed (Source): " + arg0.getSource());
		System.out.println("Constructor has been changed (toString): " + arg0.toString());
		// Clear existing ItemDefinition
		
		StardustInterfaceType sdIntType = (StardustInterfaceType) businessObject;
		StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdIntType);
		
		final AttributeType clsAt = PropertyAdapterCommons.findAttributeType(sdIntType.getStardustApplication(), "carnot:engine:className");
		final AttributeType constrAt = PropertyAdapterCommons.findAttributeType(sdIntType.getStardustApplication(), "carnot:engine:constructorName");		
		final AttributeType methodAt = PropertyAdapterCommons.findAttributeType(sdIntType.getStardustApplication(), "carnot:engine:methodName");
		
		Class<?> clazz = IntrinsicJavaAccessPointInfo.findClassInWorkspace(clsAt.getValue());
		Method method = IntrinsicJavaAccessPointInfo.decodeMethod(clazz, methodAt.getValue());
		Constructor<?> constr = IntrinsicJavaAccessPointInfo.decodeConstructor(clazz, constrAt.getValue());

		StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos((StardustInterfaceType) businessObject, method, constr );
	}
}