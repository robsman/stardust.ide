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

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextExt;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class ServiceInterfaceUtil {

	private final Logger logger = Logger.getLogger(this.getClass());
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

	public ApplicationType getApplicationAndReportFailure(Task task, FlowElementsContainer container) {
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
		if (impl != null && impl instanceof AnyType) {
			String implId = "";
			if (((AnyType)impl).eIsProxy()) {
				try {
					implId = ((InternalEObject)((AnyType)impl)).eProxyURI().fragment();
				} catch (Exception e) {//URISyntaxException e) {
					logger.error(e.getMessage());
					failures.add("Stardust Implementation (Application) not resolved " + bpmnInterface + " in " + container);
				}
			} else {
				implId = ((AnyType)impl).getMixed().toString();
			}
			return CarnotModelQuery.findApplication(carnotModel, implId);
		} else if (impl instanceof Interface) {
			return CarnotModelQuery.findApplication(carnotModel, ((Interface) impl).getId());
		} else {
			failures.add("Stardust Implementation Reference (Application) not resolved " + bpmnInterface + " in " + container);
		}
		return null;
	}

	private Interface getServiceInterfaceAndReportFailure(BaseElement element, FlowElementsContainer container) {
		Interface bpmnInterface = bpmnquery.getInterfaceByOperationRef(element, container);
		if (null == bpmnInterface) failures.add("No Operation Interface found. (Element " + element.getClass().getName() + " " + element + " in " + container + ")");
		return bpmnInterface;
	}

//	public TriggerType getStartTriggerAndReportFailure(StartEvent event, EventDefinition eventDef, FlowElementsContainer container) {
//		StardustInterfaceExt stardustInterface = getStardustInterfaceAndReportFailure(eventDef, container);
//		if (stardustInterface == null)
//			return null;
//
//		StardustTriggerExt stardustTrigger = getStartTriggerAndReportFailure(stardustInterface, container);
//		if (stardustTrigger == null)
//			return null;
//
//		TriggerTypeType triggerType = getTriggerTypeAndReportFailure(stardustInterface);
//		if (triggerType == null)
//			return null;
//
//		TriggerType trigger = createTrigger(stardustTrigger);
//		convertAccessPoints(stardustTrigger, trigger);
//		trigger.setType(triggerType);
//
//		return trigger;
//	}

//	private TriggerType createTrigger(StardustTriggerExt trigger) {
//		TriggerType stardustTrigger = BpmPackageBuilder.F_CWM.createTriggerType();
//		stardustTrigger.setElementOid(trigger.elementOid);
//		stardustTrigger.setId(trigger.id);
//		stardustTrigger.setName(trigger.name);
//		return stardustTrigger;
//	}
//
//	private StardustInterfaceExt getStardustInterfaceAndReportFailure(EventDefinition eventDef, FlowElementsContainer container) {
//		Interface bpmnInterface = getServiceInterfaceAndReportFailure(eventDef, container);
//		if (bpmnInterface == null)
//			return null;
//		StardustInterfaceExt stardustInterface = ExtensionHelper2.getInstance().getApplicationExtension(bpmnInterface);
//		if (stardustInterface == null) {
//			failures.add("No Application definition found. (Event Definition " + eventDef + " in " + container + ")");
//			return null;
//		}
//		return stardustInterface;
//	}

//	private StardustTriggerExt getStartTriggerAndReportFailure(StardustInterfaceExt stardustInterface, FlowElementsContainer container) {
//		StardustTriggerExt trigger = stardustInterface.stardustTrigger;
//		if (trigger != null)
//			return trigger;
//
//		failures.add("Stardust Trigger Definition not found (stardust interface " + stardustInterface + ")");
//		return null;
//	}
//
//	private TriggerTypeType getTriggerTypeAndReportFailure(StardustInterfaceExt stardustInterface) {
//		TriggerTypeType triggerType = ModelUtils.findElementById(carnotModel.getTriggerType(), stardustInterface.applicationType); // getApplicationType());
//		if (triggerType == null) {
//			failures.add("Stardust Trigger Type not found (type " + stardustInterface.applicationType + " stardust interface " + stardustInterface + ")");
//			return null;
//		}
//		return triggerType;
//	}

	public void convertContexts(StardustApplicationExt application, ApplicationType stardustApp) {
		List<ContextType> contexts = new ArrayList<ContextType>();
		for(StardustContextExt ctxt : application.contexts) {
			ContextType stardustCtxt = BpmPackageBuilder.F_CWM.createContextType();
			Bpmn2StardustXPDLExtension.addAttributes(ctxt, stardustCtxt);
			contexts.add(stardustCtxt);
			ApplicationContextTypeType contextType = getContextType(ctxt.typeRef);
			stardustCtxt.setType(contextType);
		}
		stardustApp.getContext().addAll(contexts);
	}

	public void convertAccessPoints(StardustApplicationExt application, ApplicationType stardustApp) {
		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
		for(StardustAccessPointExt ap : application.accessPoints) {
			AccessPointType stardustAp = BpmPackageBuilder.F_CWM.createAccessPointType();

			Bpmn2StardustXPDLExtension.addAttributes(ap, stardustAp);

    		stardustAp.setElementOid(ap.elementOid);
    		stardustAp.setId(ap.id);
    		stardustAp.setName(ap.name);
    		stardustAp.setDirection(DirectionType.get(ap.direction));

			aptypes.add(stardustAp);
			DataTypeType type = CarnotModelQuery.getMetaDataType(carnotModel, ap.typeRef);
			stardustAp.setType(type);
		}
		stardustApp.getAccessPoint().addAll(aptypes);
	}

//	public void convertAccessPoints(StardustTriggerExt trigger, TriggerType stardustTrigger) {
//		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
//		for(StardustAccessPointExt ap : trigger.accessPoints) {
//			AccessPointType stardustAp = BpmPackageBuilder.F_CWM.createAccessPointType();
//
//			Bpmn2StardustXPDLExtension.addAttributes(ap, stardustAp);
//
//    		stardustAp.setElementOid(ap.elementOid);
//    		stardustAp.setId(ap.id);
//    		stardustAp.setName(ap.name);
//    		stardustAp.setDirection(DirectionType.get(ap.direction));
//
//			aptypes.add(stardustAp);
//			DataTypeType type = getMetaDataType(ap.typeRef);
//			stardustAp.setType(type);
//		}
//		stardustTrigger.getAccessPoint().addAll(aptypes);
//	}

//	private DataTypeType getMetaDataType(String typeRef) {
//		if (typeRef == null || typeRef.isEmpty()) return null;
//		return (DataTypeType)
//				ModelUtils.findIdentifiableElement(carnotModel, CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(), typeRef);
//	}

	private ApplicationContextTypeType getContextType(String typeRef) {
		if (typeRef == null || typeRef.isEmpty()) return null;
		return (ApplicationContextTypeType)
				ModelUtils.findIdentifiableElement(carnotModel, CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(), typeRef);
	}

}
