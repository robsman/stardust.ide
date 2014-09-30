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

package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.webapp;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationGenerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.widgets.Composite;

public class ExtWebApplicationDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private StardustInterfaceType sdInterface;
	
	public ExtWebApplicationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public ExtWebApplicationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();

		sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		StardustApplicationType sdApplication;
		sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		Composite accessPointsSection = this.createSectionComposite(this, "Access Points");
		StardustContextType appCtx = sdApplication.getContext1().size() > 0 ? sdApplication.getContext1().get(0) : null;
		
		if (null != appCtx) {
			AttributeType at;
			at = PropertyAdapterCommons.findAttributeType(appCtx, "carnot:engine:ui:externalWebApp:uri");
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, "External Webapplication URI");

			AccessPointListComposite inputParams = new ExtWebApplicationAccessPointListComposite(accessPointsSection, true, this);
			inputParams.bindList(appCtx, CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint());
			inputParams.setTitle("Inputs");

			AccessPointListComposite outputParams = new ExtWebApplicationAccessPointListComposite(accessPointsSection, false, this);
			outputParams.bindList(appCtx, CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint());
			outputParams.setTitle("Outputs");
		}
	}

	public void accessPointsChanged() {
		RecordingCommand command = new RecordingCommand(editingDomain) {				
			@Override
			protected void doExecute() {
				if (null != sdInterface.getStardustApplication() && 0 < sdInterface.getStardustApplication().getContext1().size()) {
					StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos(sdInterface.getStardustApplication().getContext1().get(0));
				}
			}
		};			
		editingDomain.getCommandStack().execute(command);
		refresh();		
	}

}