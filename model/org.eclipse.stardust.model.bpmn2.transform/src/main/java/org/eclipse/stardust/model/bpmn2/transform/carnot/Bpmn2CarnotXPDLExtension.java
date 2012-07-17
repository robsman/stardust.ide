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
package org.eclipse.stardust.model.bpmn2.transform.carnot;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;

/**
 * @author Simon Nikles
 *
 */
public class Bpmn2CarnotXPDLExtension {

	public static void addStartEventExtensions(StartEvent event, TriggerType trigger) {
		StardustStartEventType extension = ExtensionHelper.getInstance().getStartEventExtension(event);
		if (extension!=null) trigger.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
	}

	public static void addMessageStartEventExtensions(StartEvent event, TriggerType trigger) {
		StardustMessageStartEventType extension = ExtensionHelper.getInstance().getMessageStartEventExtension(event);
		if (extension == null) return;
		trigger.getAccessPoint().addAll(extension.getAccessPoint());
		trigger.getParameterMapping().addAll(extension.getParameterMapping());
		trigger.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
	}
	
	public static void addTimerStartEventExtensions(StartEvent event, TriggerType trigger) {
		StardustTimerStartEventType extension = ExtensionHelper.getInstance().getTimerStartEventExtension(event);
		if (extension != null)
			trigger.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
	}

	/**
	 * Defaults to JMS-Trigger
	 * @param event
	 * @param carnotModel
	 * @return
	 */
	public static TriggerTypeType getMessageStartEventTriggerType(StartEvent event, ModelType carnotModel) {
		StardustMessageStartEventType extension = ExtensionHelper.getInstance().getMessageStartEventExtension(event);
		if (extension != null) {
			String type = extension.getType();
			return XpdlModelUtils.findElementById(carnotModel.getTriggerType(), type);
		} else {
			return XpdlModelUtils.findElementById(carnotModel.getTriggerType(), PredefinedConstants.JMS_TRIGGER);
		}
	}

	public static void addUserTaskExtensions(UserTask task, ActivityType activity) {
		StardustUserTaskType taskExt = ExtensionHelper.getInstance().getUserTaskExtension(task);
		if (taskExt == null) return;
		activity.setAllowsAbortByPerformer(taskExt.isAllowsAbortByPerformer());
		activity.setHibernateOnCreation(taskExt.isHibernateOnCreation());
		activity.setElementOid(Long.parseLong(taskExt.getElementOid()));
		activity.getDataMapping().addAll(taskExt.getDataMapping());
		activity.getEventHandler().addAll(taskExt.getEventHandler());		
	}

	public static void addModelExtensions(Definitions definitions, ModelType carnotModel) {
		StardustModelType modelValues = ExtensionHelper.getInstance().getModelAttributes(definitions);
		if (modelValues == null) return;
		carnotModel.setCarnotVersion(modelValues.getCarnotVersion());
		carnotModel.setAuthor(modelValues.getAuthor());
		carnotModel.setCreated(modelValues.getCreated().toXMLFormat());
		carnotModel.setModelOID(Math.max(modelValues.getModelOID().intValue(), 1));
		if (modelValues.getOid() > 0) carnotModel.setOid(modelValues.getOid());
		carnotModel.setVendor(modelValues.getVendor());		
	}

}
