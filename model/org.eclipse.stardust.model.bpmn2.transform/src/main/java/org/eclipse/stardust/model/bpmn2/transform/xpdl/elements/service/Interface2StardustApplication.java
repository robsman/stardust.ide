/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.service;

import java.util.List;

import org.eclipse.bpmn2.Interface;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceExt;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class Interface2StardustApplication extends AbstractElement2Stardust {

    public Interface2StardustApplication(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addIInterface(Interface bpmnInterface) {
    	logger.info("Add service interface: " + bpmnInterface);

    	StardustInterfaceExt sdInterface = ExtensionHelper2.getInstance().getApplicationExtension(bpmnInterface);
    	if (sdInterface != null) {
    		if (null != sdInterface.stardustApplication) {
	    		addApplicationToModel(sdInterface);
    		} else if (null == sdInterface.stardustTrigger) { // triggers in stardust are defined within the process and thus transformed for each bpmn-event usage
    			logger.info("Interface has no stardust application or trigger definition ("+sdInterface+").");
    		}
    	} else {
    		logger.info("Interface has no stardust extension ("+sdInterface+").");
    	}
    }

    private void addApplicationToModel(StardustInterfaceExt sdInterface) {
    	StardustApplicationExt application = sdInterface.stardustApplication;
    	ServiceInterfaceUtil serviceUtil = new ServiceInterfaceUtil(carnotModel, null, failures);

    	ApplicationType stardustApp = BpmPackageBuilder.F_CWM.createApplicationType();
    	Bpmn2StardustXPDLExtension.addAttributes(application, stardustApp);

    	if (application != null) {
    		stardustApp.setElementOid(application.elementOid);
    		stardustApp.setId(application.id);
    		stardustApp.setName(application.name);

    		stardustApp.setInteractive(application.interactive);

    		setApplicationType(sdInterface, stardustApp);
    		serviceUtil.convertAccessPoints(application, stardustApp);
    		serviceUtil.convertContexts(application, stardustApp);
    		carnotModel.getApplication().add(stardustApp);
    	}
    }

	private void setApplicationType(StardustInterfaceExt sdInterface, ApplicationType application) {
    	String applicationTypeId = sdInterface.applicationType;
    	if (applicationTypeId != null && !applicationTypeId.isEmpty()) {
    		ApplicationTypeType applicationType = (ApplicationTypeType)ModelUtils.findIdentifiableElement(carnotModel,
    				 CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(), applicationTypeId);
    		if (applicationType != null) application.setType(applicationType);
    	}
	}



}
