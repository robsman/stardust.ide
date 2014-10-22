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
import java.util.Map;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.model.bpmn2.extension.DataMappingPathHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.DataMappingPathHelper.AccessPointPathInfo;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;

/**
 * @author Simon Nikles
 *
 */
public class StartEventDataFlow2Stardust extends AbstractElement2Stardust {

    public StartEventDataFlow2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addDataFlows(StartEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(event, container);
		if (processDef == null) return;

        TriggerType sdTrigger = CarnotModelQuery.findTrigger(processDef, event.getId());
        if (sdTrigger == null) {
            failures.add("Stardust-Trigger not found " + event.getId() + " " + event.getName() + " in "  + container.getId() );
            return;
        }

        List<DataOutputAssociation> outputAssociations = event.getDataOutputAssociation();
        //List<DataOutput> dataOutputs = event.getDataOutputs();
        List<DataOutput> associatedDataOutputs = new ArrayList<DataOutput>();

        if (outputAssociations != null && outputAssociations.size() > 0) {
            for (DataOutputAssociation assocOut : outputAssociations) {
                if (!hasValidSourceAndTarget(assocOut, event, container)) continue;
                DataOutput output = addParameterMapping(assocOut, sdTrigger, container, predefinedDataForId);
                if (output != null) associatedDataOutputs.add(output);
            }
        }
    }

    private DataOutput addParameterMapping(DataOutputAssociation assocOut, TriggerType trigger, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
        ItemAwareElement associationSource = getFirstAssociationSource(assocOut);
        ItemAwareElement associationTarget = assocOut.getTargetRef();
        if (associationTarget instanceof DataObjectReference) associationTarget = ((DataObjectReference)associationTarget).getDataObjectRef();

        DataOutput dataOutput = associationSource instanceof DataOutput ? (DataOutput)associationSource : null;
        DataType toVariable = query.findVariable(associationTarget.getId(), predefinedDataForId);
        if (toVariable == null) failures.add("DATA OUTPUT ASSOCIATION STARDUST VARIABLE NOT FOUND " + associationTarget.getId() + " from Activity " + trigger.getId() + " " + trigger.getName()  + " in "  + container.getId() );

        if (hasAssignment(assocOut)) {
            logger.debug("DataOutputAssociation has assignment " + assocOut);
            addOutDataMappingFromAssignments(trigger, assocOut, dataOutput, toVariable);
        } else {
            failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent DataOutput without assignment" + assocOut);
            return null;
        }
        return dataOutput;
    }

    private void addOutDataMappingFromAssignments(TriggerType trigger, DataOutputAssociation assocOut, DataOutput dataOutput, DataType toVariable) {
        logger.debug("addOutDataMappingFromAssignments " + trigger);
        for (Assignment assign : assocOut.getAssignment()) {
            Expression fromExpression = assign.getFrom();
            Expression toExpression = assign.getTo();

//            String triggerAccessPoint = ExtensionHelper.getInstance().getAssignmentTriggerAccessPointRef(fromExpression);
//            String triggerAccessPath = getExpressionValue(fromExpression);

            AccessPointPathInfo resolveDataPath = DataMappingPathHelper.INSTANCE.resolveAccessPointPath(getExpressionValue(fromExpression));
            String triggerAccessPoint = resolveDataPath.getAccessPointId();
            String triggerAccessPath = resolveDataPath.getAccessPointPath();


            String toExpressionValue = getExpressionValue(toExpression);
            long oid = ExtensionHelper.getInstance().getAssignmentParameterMappingOid(assign);
            ParameterMappingType mapping = buildParameterMapping(trigger, oid, toVariable, toExpressionValue, triggerAccessPoint, triggerAccessPath);
            mapping.setDataPath(toExpressionValue);
            trigger.getParameterMapping().add(mapping);
        }
    }

    private ParameterMappingType buildParameterMapping(TriggerType trigger, long oid, DataType data, String dataPath, String param, String paramPath) {
    	ParameterMappingType mapping = AbstractElementBuilder.F_CWM.createParameterMappingType();
    	mapping.setElementOid(oid);
    	mapping.setData(data);
    	mapping.setDataPath(dataPath);
    	mapping.setParameter(param);
    	mapping.setParameterPath(paramPath);
    	return mapping;
    }

    private ItemAwareElement getFirstAssociationSource(DataAssociation assoc) {
        if (assoc.getSourceRef() != null) {
            for (ItemAwareElement source : assoc.getSourceRef()) {
                if (source != null) return source;
            }
        }
        return null;
    }

    private boolean hasAssignment(DataAssociation assoc) {
        return (assoc.getAssignment() != null && assoc.getAssignment().size() > 0);
    }

    private boolean hasValidSourceAndTarget(DataAssociation assoc, Event event, FlowElementsContainer container) {
        boolean valid = true;
        ItemAwareElement associationTarget = assoc.getTargetRef();
        ItemAwareElement associationSource = getFirstAssociationSource(assoc);
        if (associationTarget == null) {
            failures.add("DATA ASSOCIATION TARGET NOT SET " + assoc.getId() + " Event " + event.getId() + " " + event.getName()  + " in "  + container.getId() );
            valid = false;
        }
        if (associationSource == null) {
            failures.add("DATA ASSOCIATION SOURCE NOT SET " + assoc.getId() + " Event " + event.getId() + " " + event.getName()  + " in "  + container.getId() );
            valid = false;
        }
        if (associationSource instanceof DataObjectReference) associationSource = ((DataObjectReference)associationSource).getDataObjectRef();
        if (associationSource == null) {
            failures.add("DATA ASSOCIATION SOURCE NOT VALID " + assoc.getId() + " Event " + event.getId() + " " + event.getName()  + " in "  + container.getId() );
            valid = false;
        }
        return valid;
    }

    private String getExpressionValue(Expression expression) {
        if (expression instanceof FormalExpression) {
            logger.debug("Assignment formal expression: " + expression);
            return ((FormalExpression) expression).getBody();
        }
        logger.debug("Assignment 'informal' expression: " + expression);
        return  DocumentationTool.getInformalExpressionValue(expression);
    }

}
