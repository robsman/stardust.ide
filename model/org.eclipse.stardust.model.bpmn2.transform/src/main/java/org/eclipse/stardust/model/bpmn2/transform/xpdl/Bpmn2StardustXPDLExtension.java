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
package org.eclipse.stardust.model.bpmn2.transform.xpdl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.ConfigurableElementExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.ModelElementExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustActivityExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectOrStoreExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustEventDefinitionExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustScriptTaskExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskExt;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.StardustTriggerUtil;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Simon Nikles
 *
 */
public class Bpmn2StardustXPDLExtension {
	protected static final Logger logger = Logger.getLogger(Bpmn2StardustXPDLExtension.class);

//    public static void addStartEventExtensions(StartEvent event, TriggerType trigger) {
//        StardustStartEventType extension = ExtensionHelper.getInstance().getStartEventExtension(event);
//        if (extension!=null) trigger.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
//    }

//    public static void addTimerStartEventExtensions(StartEvent event, TriggerType trigger) {
//        StardustTimerStartEventType extension = ExtensionHelper.getInstance().getTimerStartEventExtension(event);
//        logger.debug("Bpmn2StardustXPDLExtension.addTimerStartEventExtensions() " + extension);
//        if (extension != null)
//            trigger.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
//    }

    public static String stripFullId(String fullId)
    {
       String[] ids = fullId.split(":");
       return ids[ids.length - 1];
    }

    public static void addUserTaskExtensions(CarnotModelQuery query, UserTask task, ActivityType activity) {
        StardustUserTaskExt taskExt = ExtensionHelper2.getInstance().getUserTaskExtension(task);
        if (null == taskExt) return;

        addActivityExtensions(taskExt, activity);
        activity.setAllowsAbortByPerformer(taskExt.allowsAbortByPerformer);
        activity.setApplication(getApplication(query, taskExt));

        logger.debug("Bpmn2StardustXPDLExtension.addUserTaskExtensions() Application: " + activity.getApplication());
    }

	public static void addScriptTaskExtensions(CarnotModelQuery query, ScriptTask task, ActivityType activity) {
		StardustScriptTaskExt taskExt = ExtensionHelper2.getInstance().getScriptTaskExtension(task);
		if (null == taskExt) return;

		addActivityExtensions(taskExt, activity);
		activity.setApplication(getApplication(query, taskExt));
	}

    public static void addGlobalUserTaskExtensions(CarnotModelQuery query, GlobalUserTask task, ActivityType activity) {
        StardustUserTaskExt taskExt = ExtensionHelper2.getInstance().getGlobalUserTaskExtension(task);
        if (null == taskExt) return;

        addActivityExtensions(taskExt, activity);
        activity.setAllowsAbortByPerformer(taskExt.allowsAbortByPerformer);
        activity.setApplication(getApplication(query, taskExt));

        logger.debug("Bpmn2StardustXPDLExtension.addUserTaskExtensions() Application: " + activity.getApplication());
  }

    public static void addCommonElementExtensions(ModelElementExt ext, IIdentifiableModelElement element) {
    	if (null != ext.elementOid) element.setElementOid(ext.elementOid);
    	addAttributes(ext, element);
    }

    /**
     * Adapted from org.eclipse.stardust.ui.web.modeler.marshaling.ModelElementUnmarshaller.storeAttributes(...)
     */
    public static void addAttributes(ConfigurableElementExt ext, EObject element) {
    	JsonObject attributes = ext.attributes;
    	if (null == attributes) return;
    	for (Map.Entry<String, ? > entry : attributes.entrySet()) {
    		String key = entry.getKey();
            JsonElement jsonValue = attributes.get(key);
            if (jsonValue.isJsonNull()) {
            	ModelBuilderFacade.setAttribute(element, key, null);
            	continue;
            }
            if (!jsonValue.isJsonPrimitive()) continue;

            if (jsonValue.isJsonPrimitive() && jsonValue.getAsJsonPrimitive().isBoolean())
            {
               ModelBuilderFacade.setBooleanAttribute(element, key, jsonValue.getAsBoolean());
            } else {
               String stringValue = jsonValue.getAsString();
               if (key.equals("carnot:engine:delay"))
               {
            	   continue; // bpmn handled
               }
               else if (key.equals(PredefinedConstants.VALID_FROM_ATT))
               {
                  ModelBuilderFacade.setTimestampAttribute((IExtensibleElement) element, key, stringValue);
               }
               else
               {
                  ModelBuilderFacade.setAttribute(element, key, stringValue);
               }
            }
    	}
    }

    private static void addActivityExtensions(StardustActivityExt ext, ActivityType activity) {
    	addCommonElementExtensions(ext, activity);
    	activity.setHibernateOnCreation(ext.hibernateOnCreation);
    	// TODO what about ext.eventHandlers? - activity.getEventHandler().addAll(taskExt.getEventHandler());
    }

    public static void addDatastoreExtensions(CarnotModelQuery query, DataStore store, DataType data) {
    	StardustDataObjectOrStoreExt dataExt = ExtensionHelper2.getInstance().getDataExtension(store);
    	if (null == dataExt) return;
    	addCommonElementExtensions(dataExt, data);

    	logger.debug("Bpmn2StardustXPDLExtension.addDatastoreExtensions() " + dataExt.dataType);
    }

    private static ApplicationType getApplication(CarnotModelQuery query, StardustUserTaskExt taskExt) {
        String appRef = taskExt.interactiveApplicationRef;
        if (appRef == null || appRef.isEmpty()) return null;
		return query.findApplication(appRef);
	}

    private static ApplicationType getApplication(CarnotModelQuery query, StardustScriptTaskExt taskExt) {
        String appRef = taskExt.scriptApplicationRef;
        if (appRef == null || appRef.isEmpty()) return null;
		return query.findApplication(appRef);
	}

	public static void addModelExtensionDefaults(Definitions definitions, ModelType carnotModel) {
        if (carnotModel.getCreated().isEmpty()) carnotModel.setCreated(DateFormat.getInstance().format(new Date()));
        if (carnotModel.getModelOID() <= 0) carnotModel.setModelOID(0);
        if (carnotModel.getOid() <= 0) carnotModel.setOid(0);
        if (carnotModel.getVendor().isEmpty()) carnotModel.setVendor(definitions.getExporter());
        if (carnotModel.getName() == null || carnotModel.getName().isEmpty()) carnotModel.setName("Unnamed");
    }

    public static void addModelExtensions(Definitions definitions, ModelType carnotModel) {
//        StardustModelType modelValues = ExtensionHelper.getInstance().getModelAttributes(definitions);
//        if (modelValues == null) return;
//        if (!modelValues.getCarnotVersion().isEmpty()) carnotModel.setCarnotVersion(modelValues.getCarnotVersion());
//        if (!modelValues.getAuthor().isEmpty()) carnotModel.setAuthor(modelValues.getAuthor());
//        if (modelValues.getCreated() != null) carnotModel.setCreated(modelValues.getCreated().toXMLFormat());
//        if (modelValues.getModelOID().intValue() > 0) carnotModel.setModelOID(Math.max(modelValues.getModelOID().intValue(), 1));
//        if (modelValues.getOid() > 0) carnotModel.setOid(modelValues.getOid());
//        carnotModel.setVendor(modelValues.getVendor());
    }

}
