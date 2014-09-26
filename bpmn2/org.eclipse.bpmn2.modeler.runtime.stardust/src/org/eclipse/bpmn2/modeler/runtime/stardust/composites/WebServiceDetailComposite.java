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

import static org.eclipse.stardust.model.xpdl.carnot.DirectionType.*;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.IntObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class WebServiceDetailComposite extends DefaultDetailComposite {

	public WebServiceDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public WebServiceDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();
		setTitle("Web Service Configuration");

		StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;

		StardustApplicationType sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		AttributeType at;
		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:visibility");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Public", "Private" });
		editor.createControl(parent, "Visibility");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsRuntime");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "jaxws", "axis" });
		editor.createControl(parent, "Implementation");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsdlUrl");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "WSDL URL");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsServiceName");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Service Name");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsPortName");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Port Name");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsOperationName");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Operation Name");

		at = PropertyAdapterCommons.findAttributeType(sdApplication, "carnot:engine:wsSoapProtocol");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "SOAP1.1Protocol", "SOAP1.2Protocol" });
		editor.createControl(parent, "SOAP Protocol");
		
		Composite accessPointsSection = this.createSectionComposite(this, "Access Points");
		
		for (AccessPointType ap : sdApplication.getAccessPoint()) {
			AccessPointTypeEditor ape = new AccessPointTypeEditor(this, ap);
			Composite section  = createSectionComposite(accessPointsSection, ap.getName());
			ape.createControl(section);
		}
	}
	
	public class AccessPointTypeEditor extends Composite {
		
		// Yuck :-p
		String attributeTypes[] = new String[] {
				"carnot:engine:className",
				"carnot:engine:browsable",
				"carnot:engine:dataType",
				"carnot:engine:transformation",
				"carnot:engine:separator",
				"carnot:engine:bidirectional",
		};
		AbstractDetailComposite detailsComposite;
		
		AccessPointType accessPoint;
		public AccessPointTypeEditor(AbstractDetailComposite parent, AccessPointType ap) {
			super(parent, SWT.NONE);
			accessPoint = ap;
			detailsComposite = parent;
		}
		
		public String getTitle() {
			String direction = "";
			switch(accessPoint.getDirection()) {
			case IN_LITERAL:
				direction = "Input";
				break;
			case INOUT_LITERAL:
				direction = "Input/Output";
				break;
			case OUT_LITERAL:
				direction = "Output";
				break;
			}
			String title = ModelUtil.toCanonicalString(direction + " " + accessPoint.getName());
			return title;
		}
		
		public void createControl(Composite parent) {
			if (parent.getParent() instanceof Section) {
				((Section)parent.getParent()).setText(getTitle());
			}
			ObjectEditor editor;

			editor = new IntObjectEditor(detailsComposite, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid());
			editor.createControl(parent, "Element ID");
			editor.setEditable(false);
			
			editor = new TextObjectEditor(detailsComposite, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
			editor.createControl(parent, "Name");
			editor.setEditable(false);

			for (String name : attributeTypes) {
				AttributeType at = PropertyAdapterCommons.findAttributeType(accessPoint, name);
				if (at!=null) {
					if ("boolean".equals(at.getType())) {
						editor = new AttributeTypeBooleanEditor(detailsComposite, at);
					}
					else {
						editor = new AttributeTypeTextEditor(detailsComposite, at);
					}
					editor.createControl(parent, ModelUtil.toCanonicalString(name));
				}
			}
		}
		
	}
}