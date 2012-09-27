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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Interface;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
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
    	if (application != null) {
    		setApplicationType(sdInterface, application);
    		convertAccessPoints(application);
    		convertContexts(application);
    		carnotModel.getApplication().add(application);
    	}
    }

	private void setApplicationType(StardustInterfaceType sdInterface, StardustApplicationType application) {
    	String applicationTypeId = sdInterface.getApplicationType();
    	if (applicationTypeId != null && !applicationTypeId.isEmpty()) {
    		ApplicationTypeType applicationType = (ApplicationTypeType)ModelUtils.findIdentifiableElement(carnotModel,
    				 CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(), applicationTypeId);
    		if (applicationType != null) application.setType(applicationType);
    	}
	}

	private void convertContexts(StardustApplicationType application) {
		List<ContextType> contexts = new ArrayList<ContextType>();
		for(StardustContextType ctxt : application.getContext1()) {
			contexts.add(ctxt);
			ApplicationContextTypeType contextType = getContextType(ctxt.getTypeRef());
			ctxt.setType(contextType);
		}
		application.getContext().addAll(contexts);
	}

	private void convertAccessPoints(StardustApplicationType application) {
		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
		for(StardustAccessPointType ap : application.getAccessPoint1()) {
			aptypes.add(ap);
			DataTypeType type = getMetaDataType(ap.getTypeRef());
			ap.setType(type);
		}
		application.getAccessPoint().addAll(aptypes);
	}

	private DataTypeType getMetaDataType(String typeRef) {
		if (typeRef == null || typeRef.isEmpty()) return null;
		return (DataTypeType)
				ModelUtils.findIdentifiableElement(carnotModel, CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(), typeRef);
	}

	private ApplicationContextTypeType getContextType(String typeRef) {
		if (typeRef == null || typeRef.isEmpty()) return null;
		return (ApplicationContextTypeType)
				ModelUtils.findIdentifiableElement(carnotModel, CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(), typeRef);
	}


}
