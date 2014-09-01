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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.StardustInterfaceSelectionObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.widgets.Composite;

class SpringBeanDetailComposite extends DefaultDetailComposite {

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
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");
		
		AttributeType at;
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:visibility");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Public", "Private" });
		editor.createControl(parent, "Visibility");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:spring::beanId");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Spring Bean Id");

		AttributeType clsAt = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:className");
//		editor = new AttributeTypeTextEditor(this, at);
//		editor.createControl(parent, "Class Name");
		//AbstractDetailComposite parent, EObject object, EStructuralFeature feature
		//StardustJavaImport
		//CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid()
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:methodName");
		AttributeTypeTextEditor methodNameEditor = new AttributeTypeTextEditor(this, at);
		StardustInterfaceSelectionObjectEditor importEditor = new StardustInterfaceSelectionObjectEditor(this,sdInterface,clsAt,methodNameEditor,CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		
		importEditor.createControl(parent,"Class Selector");
		methodNameEditor.createControl(parent, "Method Name");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:enable");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Enable Retry");
	
	}
}
