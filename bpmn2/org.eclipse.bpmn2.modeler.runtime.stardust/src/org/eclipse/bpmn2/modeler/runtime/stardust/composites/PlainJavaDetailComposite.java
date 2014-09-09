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
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MethodSelectionTextAndObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.StardustInterfaceSelectionObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.widgets.Composite;

class PlainJavaDetailComposite extends DefaultDetailComposite {

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
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:visibility");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Public", "Private" });
		editor.createControl(parent, "Visibility");
		

		final AttributeType clsAt = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:className");
		final AttributeType methodAt = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:methodName");
		
		MethodSelectionTextAndObjectEditor methodEditor = new MethodSelectionTextAndObjectEditor(this, sdInterface, methodAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), clsAt, false);

		AttributeType constrAt = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:constructorName");
		MethodSelectionTextAndObjectEditor constructorEditor = new MethodSelectionTextAndObjectEditor(this, sdInterface, constrAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), clsAt, true);
		
		StardustInterfaceSelectionObjectEditor importEditor = new StardustInterfaceSelectionObjectEditor(this, sdInterface, clsAt, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		importEditor.createControl(parent,"Class Selector");
		methodEditor.createControl(parent,"Method");		
		constructorEditor.createControl(parent,"Constructor");	
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:enable");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Enable Retry");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:number");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Number of Retries");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:time");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Time between Retries (seconds)");

	}
}