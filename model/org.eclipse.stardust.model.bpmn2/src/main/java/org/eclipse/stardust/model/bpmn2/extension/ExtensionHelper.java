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
package org.eclipse.stardust.model.bpmn2.extension;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;

/**
 * @author Simon Nikles
 *
 */
public class ExtensionHelper {

    private static final Internal USER_TASK_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_USER_TASK;
    private static final Internal SERVICE_TASK_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SERVICE_TASK;
    private static final Internal SUBPROCESS_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SUBPROCESS;
    private static final Internal SEQUENCE_FLOW_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW;
    private static final Internal START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_START_EVENT;
    private static final Internal TIMER_START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT;
    private static final Internal MESSAGE_START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT;
    private static final Internal APPLICATION_INTERFACE_TYPE = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_INTERFACE;
    private static final Internal RESOURCE_TYPE = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_RESOURCE;

    private static final Internal ATT_APPLICATION_ACCESS_POINT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF;
    private static final Internal ATT_TRIGGER_ACCESS_POINT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF;
    private static final Internal ATT_TRIGGER_PARAM_MAPPING = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__PARAMETER_MAPPING_OID;

    private static final Internal MODEL_ATT_OID = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__OID;
    private static final Internal MODEL_ATT_MODEL_OID = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__MODEL_OID;
    private static final Internal MODEL_ATT_VERSION = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__CARNOT_VERSION;
    private static final Internal MODEL_ATT_CREATED = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__CREATED;
    private static final Internal MODEL_ATT_VENDOR = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__VENDOR;
    private static final Internal MODEL_ATT_AUTHOR = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__AUTHOR;



    private static ExtensionHelper instance = null;

    private ExtensionHelper() {}
    public static ExtensionHelper getInstance() {
        if (instance == null) {
            instance = new ExtensionHelper();
        }
        return instance;
    }

    public void setStartEventExtension(StartEvent element,  StardustStartEventType extensionValue) {
        setExtension(element, extensionValue, START_EVENT_EXT);
    }

    public StardustStartEventType getStartEventExtension(StartEvent element) {
        return getFirstExtension(StardustStartEventType.class, element, START_EVENT_EXT);
    }

    public void setMessageStartEventExtension(StartEvent element, StardustMessageStartEventType extensionValue) {
        setExtension(element, extensionValue, MESSAGE_START_EVENT_EXT);
    }

    public StardustMessageStartEventType getMessageStartEventExtension(StartEvent element) {
        return getFirstExtension(StardustMessageStartEventType.class, element, MESSAGE_START_EVENT_EXT);
    }

    public void setTimerStartEventExtension(StartEvent element, StardustTimerStartEventType extensionValue) {
        setExtension(element, extensionValue, TIMER_START_EVENT_EXT);
    }

    public StardustTimerStartEventType getTimerStartEventExtension(StartEvent element) {
        return getFirstExtension(StardustTimerStartEventType.class, element, TIMER_START_EVENT_EXT);
    }

    public void setUserTaskExtension(UserTask element, StardustUserTaskType extensionValue) {
        setExtension(element, extensionValue, USER_TASK_EXT);
    }

    public StardustUserTaskType getUserTaskExtension(UserTask element) {
        return getFirstExtension(StardustUserTaskType.class, element, USER_TASK_EXT);
    }

    public void setServiceTaskExtension(ServiceTask serviceTask, StardustServiceTaskType serviceTaskExtension) {
        setExtension(serviceTask, serviceTaskExtension, SERVICE_TASK_EXT);
    }

    public StardustServiceTaskType getServiceTaskExtension(ServiceTask serviceTask) {
        return getFirstExtension(StardustServiceTaskType.class, serviceTask, SERVICE_TASK_EXT);
    }

    public void setSubprocessExtension(SubProcess element, StardustSubprocessType extensionValue) {
        setExtension(element, extensionValue, SUBPROCESS_EXT);
    }

    public StardustSubprocessType getSubprocessExtension(SubProcess element) {
        return getFirstExtension(StardustSubprocessType.class, element, SUBPROCESS_EXT);
    }

    public void setSequenceFlowExtension(SequenceFlow element, StardustSeqenceFlowType extensionValue) {
        setExtension(element, extensionValue, SEQUENCE_FLOW_EXT);
    }

    public StardustSeqenceFlowType getSequenceFlowExtension(SequenceFlow element) {
        return getFirstExtension(StardustSeqenceFlowType.class, element, SEQUENCE_FLOW_EXT);
    }

    public StardustInterfaceType getApplicationExtension(RootElement element) {
        StardustInterfaceType iface = getFirstExtension(StardustInterfaceType.class, element, APPLICATION_INTERFACE_TYPE);
        return iface;
    }

    public String getAssignmentAccessPointRef(Expression assignment) {
        return getString(assignment, ATT_APPLICATION_ACCESS_POINT);
    }

    public long getAssignmentParameterMappingOid(Assignment assignment) {
        return getLong(assignment, ATT_TRIGGER_PARAM_MAPPING);
    }

    public String getAssignmentTriggerAccessPointRef(Expression assignment) {
        return getString(assignment, ATT_TRIGGER_ACCESS_POINT);
    }

	public StardustResourceType getResourceExtension(Resource resource) {
		StardustResourceType stardustResource = getFirstExtension(StardustResourceType.class, resource, RESOURCE_TYPE);
//		if (stardustResource == null) return stardustResource;
//		ConditionalPerformerType conditionalPerformer = stardustResource.getStardustConditionalPerformer();
//		OrganizationType organization = stardustResource.getStardustOrganization();
//		RoleType role = stardustResource.getStardustRole();
//		if (conditionalPerformer != null) {
//			copyIdAndNameFromResourceToParticipant(resource, conditionalPerformer);
//		} else if (organization != null) {
//			copyIdAndNameFromResourceToParticipant(resource, organization);
//		} else if (role != null) {
//			copyIdAndNameFromResourceToParticipant(resource, role);
//		}
		return stardustResource;
	}

	public void setResourceExtension(Resource resource, StardustResourceType stardustResource) {
		ConditionalPerformerType conditionalPerformer = stardustResource.getStardustConditionalPerformer();
		OrganizationType organization = stardustResource.getStardustOrganization();
		RoleType role = stardustResource.getStardustRole();
		if (conditionalPerformer != null) {
			if (stardustResource.getStardustConditionalPerformer().getData() != null) {
				String dataId = stardustResource.getStardustConditionalPerformer().getData().getId();
				stardustResource.setDataId(dataId);
				stardustResource.getStardustConditionalPerformer().setData(null);
			}
			switchIdAndNameFromParticipantToResource(resource, conditionalPerformer);
		} else if (organization != null) {
			switchIdAndNameFromParticipantToResource(resource, organization);
		} else if (role != null) {
			switchIdAndNameFromParticipantToResource(resource, role);
		}
		setExtension(resource, stardustResource, RESOURCE_TYPE);
	}

	private void switchIdAndNameFromParticipantToResource(Resource resource, IIdentifiableElement participant) {
		resource.setId(participant.getId());
		resource.setName(participant.getName());
//		participant.setId("");
//		participant.setName("");
	}

	private void copyIdAndNameFromResourceToParticipant(Resource resource, IIdentifiableElement participant) {
		participant.setId(resource.getId());
		participant.setName(resource.getName());
		resource.setId(participant.getId());
		resource.setName(participant.getName());
	}

    public void setModelAttributes(Definitions element, StardustModelType values) {
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_AUTHOR, values.getAuthor()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_VERSION, values.getCarnotVersion()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_CREATED, values.getCreated()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_MODEL_OID, values.getModelOID()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_OID, values.getOid()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_VENDOR, values.getVendor()));
    }

    public StardustModelType getModelAttributes(Definitions element) {
        FeatureMap attributes = element.getAnyAttribute();
        StardustModelType modelvalues = SdbpmnFactory.eINSTANCE.createStardustModelType();
        modelvalues.setAuthor(getString(attributes, MODEL_ATT_AUTHOR));
        modelvalues.setCarnotVersion(getString(attributes, MODEL_ATT_VERSION));
        modelvalues.setCreated(getDate(attributes, MODEL_ATT_CREATED));
        modelvalues.setModelOID(getInt(attributes, MODEL_ATT_MODEL_OID));
        modelvalues.setOid(getLong(attributes, MODEL_ATT_OID));
        modelvalues.setVendor(getString(attributes, MODEL_ATT_VENDOR));
        return modelvalues;
    }

    private <T> T getFirstExtension(Class<T> type, BaseElement element, Internal feature) {
        @SuppressWarnings("unchecked")
        List<T> extensionList = (List<T>)getExtension(element, feature);
        T taskExtension = extensionList != null && extensionList.size() > 0 ? extensionList.get(0) : null;
        return taskExtension;
    }

    private Object getExtension(BaseElement element, Internal feature) {
        for (ExtensionAttributeValue extensionAttributeValue : element.getExtensionValues()) {
            FeatureMap extensionElements = extensionAttributeValue.getValue();
            Object extensionElement = extensionElements.get(feature, true);
            if (extensionElement != null) return extensionElement;
        }
        return null;
    }

    private void setExtension(BaseElement element, Object extensionValue, Internal feature) {
        ExtensionAttributeValue extensionElement;
        if (element.getExtensionValues() != null && element.getExtensionValues().size() > 0) {
            extensionElement = element.getExtensionValues().get(0);
        } else {
            extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
        }
        FeatureMap.Entry extensionElementEntry = createFeatureEntry(feature, extensionValue);
        element.getExtensionValues().add(extensionElement);
        extensionElement.getValue().add(extensionElementEntry);
    }

    private FeatureMap.Entry createFeatureEntry(Internal feature, Object value) {
        return new SimpleFeatureMapEntry(feature, value);
    }

    private long getLong(FeatureMap attributes, Internal attribute) {
        try {
            long val = Long.parseLong(attributes.get(attribute, true).toString());
            return val;
        } catch (NumberFormatException e) {}
          catch (NullPointerException e) {}
        return 0;
    }

    private long getLong(BaseElement element, Internal attribute) {
        return getLong(element.getAnyAttribute(), attribute);
    }

    private BigInteger getInt(FeatureMap attributes, Internal attribute) {
        BigInteger empty = BigInteger.ZERO;
        try {
            BigInteger val = (BigInteger)(attributes.get(attribute, true));
            if (val==null) return empty;
            return val;
        } catch (Exception e) {}
        return empty;
    }

    private XMLGregorianCalendar getDate(FeatureMap attributes, Internal attribute) {
        XMLGregorianCalendar empty = new XMLCalendar(new Date(), XMLCalendar.DATETIME);
        try {
            XMLGregorianCalendar val = (XMLGregorianCalendar)(attributes.get(attribute, true));
            if (val==null) return empty;
            return val;
        } catch (Exception e) {}
        return empty;
    }

    private String getString(BaseElement element, Internal attribute) {
        return getString(element.getAnyAttribute(), attribute);
    }

    private String getString(FeatureMap attributes, Internal attribute) {
        String empty = "";
        try {
            String val = (String)(attributes.get(attribute, true));
            if (val==null) return empty;
            return val;
        } catch (Exception e) {}
        return empty;
    }
}
