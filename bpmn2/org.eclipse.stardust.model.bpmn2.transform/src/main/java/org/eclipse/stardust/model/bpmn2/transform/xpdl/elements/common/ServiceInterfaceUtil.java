/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class ServiceInterfaceUtil {

	private final Logger logger = LogManager.getLogger(this.getClass());
	private final ModelType carnotModel;
	private final BpmnModelQuery bpmnquery;
	private final List<String> failures;

	public ServiceInterfaceUtil(ModelType carnotModel, BpmnModelQuery query, List<String> failures) {
		this.carnotModel = carnotModel;
		this.bpmnquery = query;
		this.failures = failures;
	}

	public ApplicationType getApplicationAndReportFailure(ServiceTask task, FlowElementsContainer container) {
		Interface bpmnInterface = getServiceInterfaceAndReportFailure(task, container);
		if (bpmnInterface == null) return null;
		return getApplicationAndReportFailure(bpmnInterface, container);
	}

	public ApplicationType getApplicationAndReportFailures(Event event, EventDefinition eventDef, FlowElementsContainer container) {
		Interface bpmnInterface = getServiceInterfaceAndReportFailure(eventDef, container);
		if (bpmnInterface == null) return null;
		return getApplicationAndReportFailure(bpmnInterface, container);
	}

	private ApplicationType getApplicationAndReportFailure(Interface bpmnInterface , FlowElementsContainer container) {
		Object impl = bpmnInterface.getImplementationRef();
//		StardustInterfaceType impl = ExtensionHelper.getInstance().getApplicationExtension(bpmnInterface);
		System.err.println(impl);
		if (null != impl && impl instanceof StardustInterfaceType) {
			System.out.println(EcoreUtil.getAllContents((StardustInterfaceType)impl, true));
			StardustApplicationType stardustApplication = ((StardustInterfaceType)impl).getStardustApplication();
			if (null != stardustApplication) {
				String implId = "";
				if (stardustApplication.eIsProxy()) {
					try {
						implId = ((InternalEObject)(stardustApplication)).eProxyURI().fragment();
					} catch (Exception e) {//URISyntaxException e) {
						logger.error(e.getMessage());
						failures.add("Stardust Implementation (Application) not resolved (failed to get id fragment) " + bpmnInterface + " in " + container);
					}
				} else {
					implId = stardustApplication.getId();
				}
				return CarnotModelQuery.findApplication(carnotModel, implId);
			} 
		} else {
			System.out.println(impl);
			failures.add("Stardust Implementation Reference (Application) not resolved " + bpmnInterface + " in " + container);
		}
		return null;
	}

	public TriggerType getStartTriggerAndReportFailure(StartEvent event, EventDefinition eventDef, FlowElementsContainer container) {
		StardustInterfaceType stardustInterface = getStardustInterfaceAndReportFailure(eventDef, container);
		if (stardustInterface == null)
			return null;

		StardustTriggerType stardustTrigger = getStartTriggerAndReportFailure(stardustInterface, container);
		if (stardustTrigger == null)
			return null;

		TriggerTypeType triggerType = getTriggerTypeAndReportFailure(stardustInterface);
		if (triggerType == null)
			return null;

		TriggerType trigger = (TriggerType) stardustTrigger;
		convertAccessPoints(stardustTrigger);
		trigger.setType(triggerType);

		return trigger;
	}

	private StardustInterfaceType getStardustInterfaceAndReportFailure(EventDefinition eventDef,
			FlowElementsContainer container) {
		Interface bpmnInterface = getServiceInterfaceAndReportFailure(eventDef, container);
		if (bpmnInterface == null)
			return null;
		StardustInterfaceType stardustInterface = ExtensionHelper.getInstance().getApplicationExtension(bpmnInterface);
		if (stardustInterface == null) {
			failures.add("No Application definition found. (Event Definition " + eventDef + " in " + container + ")");
			return null;
		}
		return stardustInterface;
	}

	private Interface getServiceInterfaceAndReportFailure(BaseElement element, FlowElementsContainer container) {
		Interface bpmnInterface = bpmnquery.getInterfaceByOperationRef(element, container);
		if (bpmnInterface != null)
			return bpmnInterface;
		failures.add("No Operation Interface found. (Element " + element.getClass().getName() + " " + element + " in " + container + ")");
		return null;
	}

//	private ApplicationTypeType getApplicationTypeAndReportFailure(StardustInterfaceType stardustInterface) {
//		ApplicationTypeType appType = XpdlModelUtils.findElementById(carnotModel.getApplicationType(),
//				stardustInterface.getApplicationType());
//		if (appType != null)
//			return appType;
//
//		failures.add("Stardust Application Type not found (type: " + stardustInterface.getApplicationType()
//				+ " stardust interface: " + stardustInterface + ")");
//		return null;
//	}

	private StardustTriggerType getStartTriggerAndReportFailure(StardustInterfaceType stardustInterface, FlowElementsContainer container) {
		StardustTriggerType trigger = stardustInterface.getStardustTrigger();
		if (trigger != null)
			return trigger;

		failures.add("Stardust Trigger Definition not found (stardust interface " + stardustInterface + ")");
		return null;
	}

	private TriggerTypeType getTriggerTypeAndReportFailure(StardustInterfaceType stardustInterface) {
		TriggerTypeType triggerType = ModelUtils.findElementById(carnotModel.getTriggerType(), stardustInterface.getApplicationType());
		if (triggerType == null) {
			failures.add("Stardust Trigger Type not found (type " + stardustInterface.getApplicationType()  + " stardust interface " + stardustInterface + ")");
			return null;
		}
		return triggerType;
	}

	public void convertContexts(StardustApplicationType application) {
		List<ContextType> contexts = new ArrayList<ContextType>();
		for(StardustContextType ctxt : application.getContext1()) {
			contexts.add(ctxt);
			ApplicationContextTypeType contextType = getContextType(ctxt.getTypeRef());
			ctxt.setType(contextType);
			convertContextAccessPoints(ctxt);
		}
		application.getContext().addAll(contexts);
	}

	private void convertContextAccessPoints(StardustContextType ctxt) {
		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
		for(AccessPointType ap : ctxt.getAccessPoint()) {
			if (ap instanceof StardustAccessPointType) {
				StardustAccessPointType sdap = (StardustAccessPointType)ap;
				DataTypeType type = getMetaDataType(sdap.getTypeRef());
				ap.setType(type);
			}
			aptypes.add(ap);
		}
		ctxt.getAccessPoint().addAll(aptypes);

	}

	public void convertAccessPoints(StardustApplicationType application) {
		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
		for(StardustAccessPointType ap : application.getAccessPoint1()) {
			aptypes.add(ap);
			DataTypeType type = getMetaDataType(ap.getTypeRef());
			ap.setType(type);
		}
		application.getAccessPoint().addAll(aptypes);
	}

	public void convertAccessPoints(StardustTriggerType application) {
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
