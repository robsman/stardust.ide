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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Process;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author Simon Nikles
 *
 */
public class ProcessParam2Stardust extends AbstractElement2Stardust {

	public ProcessParam2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

    public void addProcessParams(Process process) {

        InputOutputSpecification ioSpec = process.getIoSpecification();
        if (ioSpec == null) return;
        List<DataInput> dataInputs = ioSpec.getDataInputs();
        List<DataOutput> dataOutput = ioSpec.getDataOutputs();

        List<DataInput> associatedDataInputs = new ArrayList<DataInput>();
        List<DataOutput> associatedDataOutputs = new ArrayList<DataOutput>();

        ProcessDefinitionType sdProcess = query.findProcessDefinition(process.getId());

        for (DataInput input : dataInputs) {
            if (!associatedDataInputs.contains(input)) {
                addInData(input, process, sdProcess);
            }
        }
        for (DataOutput output : dataOutput) {
            if (!associatedDataOutputs.contains(output)) {
                addOutData(output, process, sdProcess);
            }
        }
    }

    private void addInData(DataInput input, Process process, ProcessDefinitionType processDefinition) {
        DataType inVariable = query.findVariable(input.getId());
        if (inVariable == null) {
            inVariable = new Data2Stardust(carnotModel, failures).addDataInputVariable(input);
        }
    }

    private void addOutData(DataOutput output, Process process, ProcessDefinitionType processDefinition) {
        DataType inVariable = query.findVariable(output.getId());
        if (inVariable == null) {
            inVariable = new Data2Stardust(carnotModel, failures).addDataOutputVariable(output);
        }
    }

}
