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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.ServiceInterfaceUtil;
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
    	StardustInterfaceType sdInterface = ExtensionHelper.getInstance().getApplicationExtension(bpmnInterface);
    	if (sdInterface != null) {
    		addApplicationToModel(sdInterface);
    	} else {
    		logger.info("Interface has no stardust application definition.");
    	}
    }

    private void addApplicationToModel(StardustInterfaceType sdInterface) {
    	StardustApplicationType application = sdInterface.getStardustApplication();
    	ServiceInterfaceUtil serviceUtil = new ServiceInterfaceUtil(carnotModel, null, failures);
    	if (application != null) {
    		EcoreUtil.Copier copier = new EcoreUtil.Copier(true, true);
    		StardustApplicationType copy =  (StardustApplicationType) copier.copy(application);
    		
    		setApplicationType(sdInterface, copy);
    		serviceUtil.convertAccessPoints(copy);
    		serviceUtil.convertContexts(copy);
    		carnotModel.getApplication().add(copy);
    	}
    }

	private void setApplicationType(StardustInterfaceType sdInterface, StardustApplicationType application) {
    	String applicationTypeId = sdInterface.getApplicationType();
    	if (applicationTypeId != null && !applicationTypeId.isEmpty()) {
    		if ("camelSpringProducerApplicationSendReceive".equals(applicationTypeId)) {
    			applicationTypeId = "camelSpringProducerApplication";
    			sdInterface.setApplicationType(applicationTypeId);    			
    		}
    		ApplicationTypeType applicationType = (ApplicationTypeType)ModelUtils.findIdentifiableElement(carnotModel,
    				 CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(), applicationTypeId);
    		if (applicationType != null) application.setType(applicationType);
    	}
	}



}
