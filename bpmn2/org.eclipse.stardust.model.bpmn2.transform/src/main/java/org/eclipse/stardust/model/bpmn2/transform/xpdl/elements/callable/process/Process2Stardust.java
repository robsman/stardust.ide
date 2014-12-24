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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.callable.process;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newModelDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Process;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class Process2Stardust extends AbstractElement2Stardust {

	public Process2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addProcess(Process process) {
		List<Documentation> docs = process.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);

		ProcessDefinitionType def =
				newProcessDefinition(carnotModel)
				.withIdAndName(process.getId(), process.getName())
				.withDescription(processDescription)
				.build();

		def = processExtensions(process, def);

		newModelDiagram(carnotModel).forProcess(def).build();
	}

	private ProcessDefinitionType processExtensions(Process process, ProcessDefinitionType def) {
		StardustProcessType sdProcess = ExtensionHelper.getInstance().getProcessExtension(process);
		
		if (null != sdProcess) {
			def.getAttribute().addAll(sdProcess.getStardustAttributes().getAttributeType());
			if (sdProcess.isSetSupportsProcessAttachments() && sdProcess.isSupportsProcessAttachments()) {
				def = createAttachmentDataPath(def);
			}
		}
		return def;
	}

	private ProcessDefinitionType createAttachmentDataPath(ProcessDefinitionType def) {

		DataType attachments = query.findVariable(DmsConstants.DATA_ID_ATTACHMENTS);
		if (null == attachments) {
			attachments = initAttachmentType(def);
		}

		DataPathType attachmentIn = CarnotWorkflowModelFactory.eINSTANCE.createDataPathType();
		DataPathType attachmentOut = CarnotWorkflowModelFactory.eINSTANCE.createDataPathType();

		attachmentIn.setDirection(DirectionType.IN_LITERAL);
		attachmentOut.setDirection(DirectionType.OUT_LITERAL);

		attachmentIn.setKey(false);
		attachmentOut.setKey(false);

		attachmentIn.setId(DmsConstants.PATH_ID_ATTACHMENTS);
		attachmentOut.setId(DmsConstants.PATH_ID_ATTACHMENTS);

		attachmentIn.setName("Process Attachments");
		attachmentOut.setName("Process Attachments");

		attachmentIn.setData(attachments);
		attachmentOut.setData(attachments);

		def.getDataPath().add(attachmentIn);
		def.getDataPath().add(attachmentOut);

		return def;
	}

	private DataType initAttachmentType(ProcessDefinitionType def) {
        DataType type = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
        DataTypeType dataTypeType = (DataTypeType) ModelUtils.findIdentifiableElement(carnotModel.getDataType(), "dmsDocumentList");
        type.setType(dataTypeType);
        type.setId(DmsConstants.DATA_ID_ATTACHMENTS);
        type.setName("Process Attachments");
        type.setPredefined(true);
        AttributeUtil.setBooleanAttribute(type, "carnot:engine:data:bidirectional", true);
        AttributeUtil.setAttribute(type, PredefinedConstants.CLASS_NAME_ATT, "java.util.List");
        carnotModel.getData().add(type);
		return type;
	}

}
