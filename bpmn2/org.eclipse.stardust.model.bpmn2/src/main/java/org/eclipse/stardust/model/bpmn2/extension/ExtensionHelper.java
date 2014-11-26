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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.util.XmlExtendedMetadata;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;
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
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;

/**
 * @author Simon Nikles
 *
 */
public class ExtensionHelper {

	public static final String STARDUST_EXTENSION_NAMESPACE = "http://www.eclipse.org/stardust/model/bpmn2/sdbpmn";
	public static final String STARDUST_EXTENSION_PREFIX = "sdbpmn";
	public static final String STARDUST_ACCESSPOINT_ID = "sdbpmn:accesspoint";
	//public static final String STARDUST_SYNTHETIC_ITEMDEF = "syntheticItemDefinition";


	public static final String NS_URI_STARDUST = "http://www.eclipse.org/stardust/model/bpmn2/sdbpmn";  //"http://www.eclipse.org/stardust";
	public static final String NS_URI_STARDUST_SHORT = "http://eclipse.org/stardust";
	public static final String NS_PREFIX_STARDUST = "stardust";

    private static final Internal USER_TASK_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_USER_TASK;
    private static final Internal SERVICE_TASK_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SERVICE_TASK;
    private static final Internal SUBPROCESS_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SUBPROCESS;
    private static final Internal SEQUENCE_FLOW_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW;
    private static final Internal START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_START_EVENT;
    private static final Internal TIMER_START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT;
    private static final Internal MESSAGE_START_EVENT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT;

    private static final Internal DATA_STORE_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_DATA_STORE;
    private static final Internal DATA_OBJECT_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_DATA_OBJECT;

    private static final Internal APPLICATION_INTERFACE_TYPE = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_INTERFACE;
    private static final Internal RESOURCE_TYPE = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_RESOURCE;

    private static final Internal PROCESS_EXT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_PROCESS;

    @SuppressWarnings("unused")
	private static final Internal GENERAL_ATTRIBUTES_TYPE = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_ATTRIBUTES;

    @SuppressWarnings("unused")
	private static final Internal ATT_APPLICATION_ACCESS_POINT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF;
    private static final Internal ATT_TRIGGER_ACCESS_POINT = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF;
    private static final Internal ATT_TRIGGER_PARAM_MAPPING = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__PARAMETER_MAPPING_OID;

    private static final Internal MODEL_ATT_OID = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__OID;
    private static final Internal MODEL_ATT_MODEL_OID = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__MODEL_OID;
    public static final Internal MODEL_ATT_CARNOT_VERSION = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__CARNOT_VERSION;
    public static final Internal MODEL_ATT_MODEL_VERSION = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__MODEL_VERSION;
    private static final Internal MODEL_ATT_CREATED = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__CREATED;
    private static final Internal MODEL_ATT_VENDOR = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__VENDOR;
    private static final Internal MODEL_ATT_AUTHOR = (Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__AUTHOR;

    //private static final Internal XSD_SCHEMA = (Internal)XSDPackage.Literals.XSD_CONCRETE_COMPONENT__SCHEMA;


    private static final Map<Class<?>, EClass> classToEClassMap = new HashMap<Class<?>, EClass>();

    private static ExtensionHelper instance = null;

    private ExtensionHelper() {}
    public static ExtensionHelper getInstance() {
        if (instance == null) {
            instance = new ExtensionHelper();
        }
        return instance;
    }

//    public String getExtensionAttributeValue(BaseElement element, String attributeName) {
//        ExtendedMetaData metadata = XmlExtendedMetadata.INSTANCE;
//
//        ExtensionAttributeValue extensionAttributes = getOrCreate(
//              ExtensionAttributeValue.class, element.getExtensionValues());
//        FeatureMap extensions = extensionAttributes.getValue();
//
//        Object currentValue = null;
//        for (FeatureMap.Entry extension : extensionAttributes.getValue())
//        {
//           if (isInFilter(extension.getEStructuralFeature(), attributeName, NS_URI_STARDUST))
//           {
//              currentValue = extension.getValue();
//              break;
//           }
//        }
//        return null != currentValue ? currentValue.toString() : "";
//    }

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

    public StardustTimerStartEventType getEventDefinitionExtensionAttributes(EventDefinition element) {
        return getFirstExtension(StardustTimerStartEventType.class, element, TIMER_START_EVENT_EXT);
    }

    public void setUserTaskExtension(UserTask element, StardustUserTaskType extensionValue) {
        setExtension(element, extensionValue, USER_TASK_EXT);
    }

    public StardustUserTaskType getUserTaskExtension(UserTask element) {
        return getFirstExtension(StardustUserTaskType.class, element, USER_TASK_EXT);
    }

//    public void setModelExtension(Definitions element, StardustModelType extensionValue) {
//        setExtension(element, extensionValue, MODEL_EXT);
//    }
//
//    public StardustModelType getModelExtension(Definitions element) {
//        return getFirstExtension(StardustModelType.class, element, MODEL_EXT);
//    }

	public StardustUserTaskType getGlobalUserTaskExtension(GlobalUserTask globalTask) {
		return getFirstExtension(StardustUserTaskType.class, globalTask, USER_TASK_EXT);
	}

    public void setServiceTaskExtension(ServiceTask serviceTask, StardustServiceTaskType serviceTaskExtension) {
        setExtension(serviceTask, serviceTaskExtension, SERVICE_TASK_EXT);
    }

    public StardustServiceTaskType getServiceTaskExtension(ServiceTask serviceTask) {
        return getFirstExtension(StardustServiceTaskType.class, serviceTask, SERVICE_TASK_EXT);
    }

    public StardustDataStoreType getDataStoreExtension(DataStore dataStore) {
        return getFirstExtension(StardustDataStoreType.class, dataStore, DATA_STORE_EXT);
    }

    public StardustServiceTaskType getDataObjectExtension(DataObject dataObject) {
        return getFirstExtension(StardustServiceTaskType.class, dataObject, DATA_OBJECT_EXT);
    }

	public StardustProcessType getProcessExtension(Process process) {
		return getFirstExtension(StardustProcessType.class, process, PROCESS_EXT);
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

//    public String getAssignmentAccessPointRef(Expression assignment) {
//        return getString(assignment, ATT_APPLICATION_ACCESS_POINT);
//    }

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

	@SuppressWarnings("unused")
	private void copyIdAndNameFromResourceToParticipant(Resource resource, IIdentifiableElement participant) {
		participant.setId(resource.getId());
		participant.setName(resource.getName());
		resource.setId(participant.getId());
		resource.setName(participant.getName());
	}

    public void setModelAttributes(Definitions element, StardustModelType values) {
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_AUTHOR, values.getAuthor()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_CARNOT_VERSION, values.getCarnotVersion()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_CREATED, values.getCreated()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_MODEL_OID, values.getModelOID()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_OID, values.getOid()));
        element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_VENDOR, values.getVendor()));
    }

    public void setModelCarnotVersion(Definitions element, String version) {
    	element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_CARNOT_VERSION, version));
    }

    public void setModelVersion(Definitions element, String version) {
    	element.getAnyAttribute().add(createFeatureEntry(MODEL_ATT_MODEL_VERSION, version));
    }

    public String getModelVersion(Definitions element) {
    	return getString(element, MODEL_ATT_MODEL_VERSION);
    }

    public StardustModelType getModelAttributes(Definitions element) {
        FeatureMap attributes = element.getAnyAttribute();
        StardustModelType modelvalues = SdbpmnFactory.eINSTANCE.createStardustModelType();
        modelvalues.setAuthor(getString(attributes, MODEL_ATT_AUTHOR));
        modelvalues.setCarnotVersion(getString(attributes, MODEL_ATT_CARNOT_VERSION));
        modelvalues.setCreated(getDate(attributes, MODEL_ATT_CREATED));
        modelvalues.setModelOID(getInt(attributes, MODEL_ATT_MODEL_OID));
        modelvalues.setOid(getLong(attributes, MODEL_ATT_OID));
        modelvalues.setVendor(getString(attributes, MODEL_ATT_VENDOR));
        return modelvalues;
    }

//    private <T> T getFirstExtension(Class<T> type, BaseElement element, Internal feature) {
//        @SuppressWarnings("unchecked")
//        List<T> extensionList = (List<T>)getExtension(element, feature);
//        T taskExtension = extensionList != null && extensionList.size() > 0 ? extensionList.get(0) : null;
//        return taskExtension;
//    }

    @SuppressWarnings("unchecked")
	private <T> T getFirstExtension(Class<T> type, BaseElement element, Internal feature) {
        for (ExtensionAttributeValue extensionAttributeValue : element.getExtensionValues()) {
            FeatureMap extensionElements = extensionAttributeValue.getValue();
            for (Entry extEntry : extensionElements) {
            	if (null != extEntry && extEntry.getEStructuralFeature().equals(feature))
	            return (T)extEntry.getValue();
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
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

    public void setExtension(ItemDefinition object, XSDSchema schema) {
    	setExtensionValue(object, "schema", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, schema);
    	//http://www.w3.org/2000/xmlns/
    }

    public static void setExtensionValue(BaseElement object, String tag, String nsUri, Object value)
    {
       ExtendedMetaData metadata = XmlExtendedMetadata.INSTANCE;

       ExtensionAttributeValue extensionAttributes = getOrCreate(
             ExtensionAttributeValue.class, object.getExtensionValues());
       FeatureMap extensions = extensionAttributes.getValue();

       Object currentValue = null;
       for (FeatureMap.Entry extension : extensionAttributes.getValue())
       {
          if (isInFilter(extension.getEStructuralFeature(), tag, nsUri))
          {
             currentValue = extension.getValue();
             break;
          }
       }

       // create extension element type
       EStructuralFeature extensionElementType = metadata.demandFeature(nsUri,
             tag, true, true);
       extensionElementType.setChangeable(true);

       if (null != currentValue)
       {
          extensions.list(extensionElementType).remove(currentValue);
       }

       if (null != value)
       {
          extensions.add(extensionElementType, value);
       }
       else
       {
          if (extensions.isEmpty())
          {
             object.getExtensionValues().remove(extensionAttributes);
          }
       }
    }

    private static boolean isInFilter(EStructuralFeature eStructuralFeature, String tag)
    {
       return isInFilter(eStructuralFeature, tag, NS_URI_STARDUST);
    }

    private static boolean isInFilter(EStructuralFeature eStructuralFeature, String tag, String nsUri)
    {
       String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(eStructuralFeature);
       if (nsUri.equals(extensionNs))
       {
          if ((null != tag) && tag.equals(eStructuralFeature.getName()))
          {
             return true;
          }
       }
       return false;
    }

    public static void setExtensionValue(BaseElement object, String tag, Object value)
    {
       setExtensionValue(object, tag, NS_URI_STARDUST, value);
    }

    private static <T extends EObject> T getOrCreate(Class<T> cls, List<T> elements)
    {
       if (elements.isEmpty())
       {
          EClass ecls = classToEClassMap.get(cls);
          if (ecls == null)
          {
             ecls = scanForEClass(cls, Bpmn2Package.eINSTANCE.getEClassifiers());
             if (ecls == null)
             {
                return null;
             }
          }

          @SuppressWarnings("unchecked")
          T element = (T) Bpmn2Factory.eINSTANCE.create(ecls);

          elements.add(element);
       }

       return elements.get(0);
    }

    private static EClass scanForEClass(Class<?> cls, List<EClassifier> classifiers)
    {
       String clsName = cls.getSimpleName();
       for (EClassifier classifier : classifiers)
       {
          if (cls.getName().equals(classifier.getInstanceClassName())
                || clsName.equals(classifier.getName()) && (classifier instanceof EClass))
          {
             return (EClass) classifier;
          }
       }

       return null;
    }

    public EStructuralFeature createSdbpmnAnyAttributeAccessorFeature(String name) {
    	EStructuralFeature attributeAccessor = XmlExtendedMetadata.INSTANCE.demandFeature(NS_URI_STARDUST, name, false, false);
		attributeAccessor.setChangeable(true);
		return attributeAccessor;
    }

    public void setAnyAttribute(BaseElement element, String name, String value) {
    	EStructuralFeature attributeAccessor = null;
    	Object oldValue = null;
    	for (Iterator<FeatureMap.Entry> i = element.getAnyAttribute().iterator(); i.hasNext(); )
    	{
    		FeatureMap.Entry extension = i.next();
    		if (isInFilter(extension.getEStructuralFeature(), name))
    		{
    			attributeAccessor = extension.getEStructuralFeature();
    			oldValue = extension.getValue();
    			break;
    		}
    	}

    	if (null == attributeAccessor)
    	{
    		attributeAccessor = XmlExtendedMetadata.INSTANCE.demandFeature(NS_URI_STARDUST, name, false, false);
    		attributeAccessor.setChangeable(true);
    	}

    	if (null == value || value.isEmpty())
    	{
    		if (null != oldValue)
    		{
    			element.getAnyAttribute().list(attributeAccessor).remove(oldValue);
    		}
    	}
    	else
    	{
    		element.getAnyAttribute().set(attributeAccessor, value);
    	}
    }

    public EStructuralFeature getAnyAttributeFeature(BaseElement element, String name) {
    	EStructuralFeature attributeAccessor = null;
    	for (Iterator<FeatureMap.Entry> i = element.getAnyAttribute().iterator(); i.hasNext(); )
    	{
    		FeatureMap.Entry extension = i.next();
    		if (isInFilter(extension.getEStructuralFeature(), name))
    		{
    			attributeAccessor = extension.getEStructuralFeature();
    			break;
    		}
    	}
    	return attributeAccessor;
    }

    public void setAnyAttribute(BaseElement element, String name, Object value) {
    	EStructuralFeature attributeAccessor = null;
    	Object oldValue = null;
    	for (Iterator<FeatureMap.Entry> i = element.getAnyAttribute().iterator(); i.hasNext(); )
    	{
    		FeatureMap.Entry extension = i.next();
    		if (isInFilter(extension.getEStructuralFeature(), name))
    		{
    			attributeAccessor = extension.getEStructuralFeature();
    			oldValue = extension.getValue();
    			break;
    		}
    	}

    	if (null == attributeAccessor)
    	{
    		attributeAccessor = XmlExtendedMetadata.INSTANCE.demandFeature(NS_URI_STARDUST, name, false, false);
    		attributeAccessor.setChangeable(true);
    	}

    	if (null == value)
    	{
    		if (null != oldValue)
    		{
    			element.getAnyAttribute().list(attributeAccessor).remove(oldValue);
    		}
    	}
    	else
    	{
    		element.getAnyAttribute().set(attributeAccessor, value);
    	}
    }

}
