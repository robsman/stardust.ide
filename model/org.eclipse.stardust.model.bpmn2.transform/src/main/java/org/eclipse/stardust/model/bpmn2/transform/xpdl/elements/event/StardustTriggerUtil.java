package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustEventDefinitionExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerExt;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmCamelTriggerBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class StardustTriggerUtil {

	private final Logger logger = Logger.getLogger(this.getClass());
	private final ModelType carnotModel;
	private final BpmnModelQuery bpmnquery;
	private final List<String> failures;

	public StardustTriggerUtil(ModelType carnotModel, BpmnModelQuery query, List<String> failures) {
		this.carnotModel = carnotModel;
		this.bpmnquery = query;
		this.failures = failures;
	}

	public TriggerType getStartTriggerAndReportFailure(ProcessDefinitionType processDef, StartEvent event, EventDefinition eventDef, FlowElementsContainer container) {
		StardustInterfaceExt stardustInterface = getStardustInterfaceAndReportFailure(eventDef, container);
		if (stardustInterface == null)
			return null;

		StardustTriggerExt stardustTrigger = getStartTriggerAndReportFailure(stardustInterface, container);
		if (stardustTrigger == null)
			return null;

		TriggerTypeType triggerType = getTriggerTypeAndReportFailure(stardustInterface);
//		if (triggerType == null)
//			return null;
		TriggerType trigger = null;
		if (null == triggerType) {
			if (null == stardustInterface.applicationType) return null;
			if (ModelerConstants.CAMEL_TRIGGER_TYPE_ID.equals(stardustInterface.applicationType)) {
				trigger = BpmCamelTriggerBuilder.newCamelTrigger(processDef)
					.withIdAndName(stardustTrigger.id, stardustTrigger.name)
					.build();
				Bpmn2StardustXPDLExtension.addAttributes(stardustTrigger, trigger);
				convertAccessPoints(stardustTrigger, trigger);
			} else {
				return null;
			}
		} else {
			trigger = createTrigger(stardustTrigger);
			convertAccessPoints(stardustTrigger, trigger);
			trigger.setType(triggerType);
		}
		return trigger;
	}

	public void convertAccessPoints(StardustTriggerExt trigger, TriggerType stardustTrigger) {
		List<AccessPointType> aptypes = new ArrayList<AccessPointType>();
		if (null == trigger || null == trigger.accessPoints) return;
		for(StardustAccessPointExt ap : trigger.accessPoints) {
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
		stardustTrigger.getAccessPoint().addAll(aptypes);
	}

	private StardustTriggerExt getStartTriggerAndReportFailure(StardustInterfaceExt stardustInterface, FlowElementsContainer container) {
		StardustTriggerExt trigger = stardustInterface.stardustTrigger;
		if (trigger != null)
			return trigger;

		failures.add("Stardust Trigger Definition not found (stardust interface " + stardustInterface + ")");
		return null;
	}

	private TriggerTypeType getTriggerTypeAndReportFailure(StardustInterfaceExt stardustInterface) {
		TriggerTypeType triggerType = ModelUtils.findElementById(carnotModel.getTriggerType(), stardustInterface.applicationType); // getApplicationType());
		if (triggerType == null) {
			failures.add("Stardust Trigger Type not found (type " + stardustInterface.applicationType + " stardust interface " + stardustInterface + ")");
			return null;
		}
		return triggerType;
	}

	private TriggerType createTrigger(StardustTriggerExt trigger) {
		TriggerType stardustTrigger = BpmPackageBuilder.F_CWM.createTriggerType();
		stardustTrigger.setElementOid(trigger.elementOid);
		stardustTrigger.setId(trigger.id);
		stardustTrigger.setName(trigger.name);
		return stardustTrigger;
	}

	private StardustInterfaceExt getStardustInterfaceAndReportFailure(EventDefinition eventDef, FlowElementsContainer container) {
		Interface bpmnInterface = getServiceInterfaceByOperationRef(eventDef, container);
		if (bpmnInterface == null) bpmnInterface = getServiceInferfaceByExtensionRef(eventDef, container);
		if (bpmnInterface == null)
			return null;
		StardustInterfaceExt stardustInterface = ExtensionHelper2.getInstance().getApplicationExtension(bpmnInterface);
		if (stardustInterface == null) {
			failures.add("No Application definition found. (Event Definition " + eventDef + " in " + container + ")");
			return null;
		}
		return stardustInterface;
	}

	private Interface getServiceInterfaceByOperationRef(BaseElement element, FlowElementsContainer container) {
		Interface bpmnInterface = bpmnquery.getInterfaceByOperationRef(element, container);
//		if (null == bpmnInterface)
//			failures.add("No Operation Interface found. (Element " + element.getClass().getName() + " " + element + " in " + container + ")");
		return bpmnInterface;
	}

	private Interface getServiceInferfaceByExtensionRef(BaseElement element, FlowElementsContainer container) {
		StardustEventDefinitionExt ext = ExtensionHelper2.getInstance().getCoreExtension(StardustEventDefinitionExt.class, element);
		if (null != ext.stardustTriggerInterfaceRef) {
			Definitions definitions = BpmnModelQuery.getDefinitions(container);
			return bpmnquery.findInterfaceById(definitions, ext.stardustTriggerInterfaceRef);
		}
		return null;
	}


}
