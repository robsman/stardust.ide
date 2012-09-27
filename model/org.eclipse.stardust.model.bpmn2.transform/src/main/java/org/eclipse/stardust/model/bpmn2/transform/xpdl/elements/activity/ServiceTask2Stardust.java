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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;

import java.util.List;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class ServiceTask2Stardust extends AbstractElement2Stardust {

	public ServiceTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addServiceTask(ServiceTask task, FlowElementsContainer container) {
		logger.info("Add service task: " + task);
		ProcessDefinitionType processDef = getProcessOrReportFailure(task, container);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
		BpmApplicationActivityBuilder builder =
				newApplicationActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr);
		setInvokedApplication(task, builder, container);
		builder.build();
	}

	private void setInvokedApplication(ServiceTask task, BpmApplicationActivityBuilder builder, FlowElementsContainer container) {
		ApplicationType application = getApplication(task, container);
		if (application != null) {
			builder.setApplicationModel(carnotModel);
			builder.invokingApplication(application);
			logger.info("setInvokedApplication: " + application);
		}
	}

	private ApplicationType getApplication(ServiceTask task, FlowElementsContainer container) {
		Interface bpmnInterface = getServiceInterface(task, container);
		logger.debug("Interface " + bpmnInterface);
		if (bpmnInterface != null) {
			logger.debug("Interface implementation " + bpmnInterface.getImplementationRef());
			Object impl = bpmnInterface.getImplementationRef();
			if (impl != null && impl instanceof AnyType) {
				String implId = "";
				if (((AnyType)impl).eIsProxy()) {
					try {
						implId = ((InternalEObject)((AnyType)impl)).eProxyURI().fragment();
					} catch (Exception e) {//URISyntaxException e) {
						logger.error(e.getMessage());
						failures.add("Implementation (Application) not resolved " + task + " in " + container);
					}
				} else {
					implId = ((AnyType)impl).getMixed().toString();
				}
				return query.findApplication(implId);
			}
		}
		return null;
	}

	private Interface getServiceInterface(ServiceTask task, FlowElementsContainer container) {
		Operation operation = task.getOperationRef();
		if (operation.eIsProxy()) operation = Bpmn2ProxyResolver.resolveOperationProxy(operation, container);
		logger.debug("ServiceTask2Stardust.getServiceInterface() operation=" + operation);
		if (operation != null) {
			return (Interface) operation.eContainer();
		}
		return null;
	}

}
