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

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

/**
 * @author Simon Nikles
 *
 */
public class TaskDataFlow2Stardust extends AbstractElement2Stardust {

    public TaskDataFlow2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addDataFlows(Activity activity, FlowElementsContainer container) {
        ActivityType sdActivity = query.findActivity(activity, container);
        if (sdActivity == null) {
            failures.add("STARDUST-ACTIVITY NOT FOUND " + activity.getId() + " " + activity.getName() + " in "  + container.getId() );
            return;
        }
        InputOutputSpecification ioSpec = activity.getIoSpecification();
        List<DataInputAssociation> inputAssociations = activity.getDataInputAssociations();
        List<DataOutputAssociation> outputAssociations = activity.getDataOutputAssociations();
        List<DataInput> dataInputs = ioSpec.getDataInputs();
        List<DataOutput> dataOutput = ioSpec.getDataOutputs();

        List<DataInput> associatedDataInputs = new ArrayList<DataInput>();
        List<DataOutput> associatedDataOutputs = new ArrayList<DataOutput>();

        if (inputAssociations != null && inputAssociations.size() > 0) {
            for (DataInputAssociation assocIn : inputAssociations) {
                if (!hasValidSourceAndTarget(assocIn, activity, container)) continue;
                DataInput input = addInDataMapping(assocIn, sdActivity, container);
                if (input != null) associatedDataInputs.add(input);
            }
        }
        if (outputAssociations != null && outputAssociations.size() > 0) {
            for (DataOutputAssociation assocOut : outputAssociations) {
                if (!hasValidSourceAndTarget(assocOut, activity, container)) continue;
                DataOutput output = addOutDataMapping(assocOut, sdActivity, container);
                if (output != null) associatedDataOutputs.add(output);
            }
        }
        for (DataInput input : dataInputs) {
            if (!associatedDataInputs.contains(input)) {
                addInDataMappingWithoutAssociation(input, sdActivity, container);
            }
        }
        for (DataOutput output : dataOutput) {
            if (!associatedDataOutputs.contains(output)) {
                addOutDataMappingWithoutAssociation(output, sdActivity, container);
            }
        }
    }

    private DataInput addInDataMapping(DataInputAssociation assocIn, ActivityType activity, FlowElementsContainer container) {
        ItemAwareElement associationTarget = assocIn.getTargetRef();
        ItemAwareElement associationSource = getFirstAssociationSource(assocIn);
        if (associationSource instanceof DataObjectReference)
            associationSource = ((DataObjectReference)associationSource).getDataObjectRef();

        if (associationSource instanceof DataStoreReference) {
            associationSource = ((DataStoreReference)associationSource).getDataStoreRef();
        	if (associationSource.eIsProxy()) {
        		associationSource = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)associationSource, BpmnModelQuery.getModelDefinitions(container));
        	}
        }

        DataInput dataInput = associationTarget instanceof DataInput ? (DataInput)associationTarget : null;
        if (null == associationSource) {
        	failures.add("DATA INPUT ASSOCIATION HAS NO TARGET association id: " + assocIn.getId() + " to Activity " + activity.getId() + " " + activity.getName()  + " in "  + container.getId() );
        	return dataInput;
        }

        DataType fromVariable = query.findVariable(associationSource.getId());
        if (fromVariable == null) failures.add("DATA INPUT ASSOCIATION STARDUST VARIABLE NOT FOUND " + associationTarget.getId() + " to Activity " + activity.getId() + " " + activity.getName()  + " in "  + container.getId() );

        if (hasAssignment(assocIn)) {
            logger.debug("DataInputAssociation has assignment " + assocIn);
            addInDataMappingFromAssignments(activity, assocIn, dataInput, fromVariable);
        } else {
            logger.debug("DataInputAssociation without assignment " + assocIn);
            DataMappingType mapping = buildInDataMapping(activity, assocIn.getId(), getDataMappingName(dataInput, assocIn), fromVariable, "", "");
            addDataPathFromTransformationExpression(mapping, assocIn);
        }
        return dataInput;
    }

    private DataOutput addOutDataMapping(DataOutputAssociation assocOut, ActivityType activity, FlowElementsContainer container) {
        ItemAwareElement associationSource = getFirstAssociationSource(assocOut);
        ItemAwareElement associationTarget = assocOut.getTargetRef();
        if (associationTarget instanceof DataObjectReference) associationTarget = ((DataObjectReference)associationTarget).getDataObjectRef();
        if (associationTarget instanceof DataStoreReference) {
        	associationTarget = ((DataStoreReference)associationTarget).getDataStoreRef();
        	if (associationTarget.eIsProxy()) {
        		associationTarget = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)associationTarget, BpmnModelQuery.getModelDefinitions(container));
        	}
        }

        DataOutput dataOutput = associationSource instanceof DataOutput ? (DataOutput)associationSource : null;
        if (null == associationTarget) {
        	failures.add("DATA OUTPUT ASSOCIATION TARGET NOT SET id: " + assocOut.getId() + " from Activity " + activity.getId() + " " + activity.getName()  + " in "  + container.getId() );
        	return dataOutput;
        }

        DataType toVariable = query.findVariable(associationTarget.getId());
        if (toVariable == null) failures.add("DATA OUTPUT ASSOCIATION STARDUST VARIABLE NOT FOUND " + associationTarget.getId() + " from Activity " + activity.getId() + " " + activity.getName()  + " in "  + container.getId() );

        if (hasAssignment(assocOut)) {
            logger.debug("DataOutputAssociation has assignment " + assocOut);
            addOutDataMappingFromAssignments(activity, assocOut, dataOutput, toVariable);
        } else {
            logger.debug("DataInputAssociation without assignment " + assocOut);
            DataMappingType mapping = buildOutDataMapping(activity, assocOut.getId(), getDataMappingName(dataOutput, assocOut), toVariable, "","");
            addDataPathFromTransformationExpression(mapping, assocOut);
        }
        return dataOutput;
    }

    private void addInDataMappingFromAssignments(ActivityType activity, DataInputAssociation assocIn, DataInput dataInput, DataType fromVariable) {
        logger.debug("addInDataMappingFromAssignments " + activity);
        for (Assignment assign : assocIn.getAssignment()) {
            Expression fromExpression = assign.getFrom();
            String assingmentId = assign.getId();
            Expression toExpression = assign.getTo();

            //String applicationAccessPoint = ExtensionHelper.getInstance().getAssignmentAccessPointRef(toExpression);
            String applicationAccessPoint = ExtensionHelper2.getInstance().getAssignmentAccessPointRef(toExpression);
            String applicationAccessPath = getExpressionValue(toExpression);

            String mappingId = assocIn.getId() + "_" + assingmentId;
            DataMappingType mapping = buildInDataMapping(activity, mappingId, getDataMappingName(dataInput, assocIn), fromVariable, applicationAccessPoint, applicationAccessPath);
            String fromExpressionValue = getExpressionValue(fromExpression);
            mapping.setDataPath(fromExpressionValue);
        }
    }

    private void addOutDataMappingFromAssignments(ActivityType activity, DataOutputAssociation assocOut, DataOutput dataOutput, DataType toVariable) {
        logger.debug("addOutDataMappingFromAssignments " + activity);
        for (Assignment assign : assocOut.getAssignment()) {
            Expression fromExpression = assign.getFrom();
            Expression toExpression = assign.getTo();
            String assingmentId = assign.getId();
            String mappingId = assocOut.getId() + "_" + assingmentId;

            String applicationAccessPoint = ExtensionHelper2.getInstance().getAssignmentAccessPointRef(fromExpression);
			if (isEmpty(applicationAccessPoint)) {
				// TODO TO-JSON-EXT
				applicationAccessPoint = ""; //ExtensionHelper.getInstance().getAssignmentAccessPointRef(fromExpression);
			}
            String applicationAccessPath = getExpressionValue(fromExpression);

            DataMappingType mapping = buildOutDataMapping(activity, mappingId, getDataMappingName(dataOutput, assocOut), toVariable, applicationAccessPoint, applicationAccessPath);

            String toExpressionValue = getExpressionValue(toExpression);
            mapping.setDataPath(toExpressionValue);
        }
    }
    private void addInDataMappingWithoutAssociation(DataInput input, ActivityType activity, FlowElementsContainer container) {
        DataType inVariable = query.findVariable(input.getId());
        if (inVariable == null) {
            inVariable = new Data2Stardust(carnotModel, failures).addDataInputVariable(input);
        }
        buildInDataMapping(activity, input.getId(), input.getName(), inVariable, "", "");
    }

    private void addOutDataMappingWithoutAssociation(DataOutput output, ActivityType activity, FlowElementsContainer container) {
        DataType outVariable = query.findVariable(output.getId());
        if (outVariable == null) {
            outVariable = new Data2Stardust(carnotModel, failures).addDataOutputVariable(output);
        }
        buildOutDataMapping(activity, output.getId(), output.getName(), outVariable, "","");
    }

    private DataMappingType buildInDataMapping(ActivityType activity, String id, String name, DataType fromVariable, String accessPointId, String path) {
    	String context = getDataFlowContext(activity);
        return BpmModelBuilder.newInDataMapping(activity)
                .withIdAndName(id, name)
                .fromVariable(fromVariable)
                .inContext(context)
                .toApplicationAccessPoint(accessPointId, path)
                .build();
    }

    private DataMappingType buildOutDataMapping(ActivityType activity, String id, String name, DataType toVariable, String accessPointId, String path) {
    	String context = getDataFlowContext(activity);
    	return BpmModelBuilder.newOutDataMapping(activity)
                .withIdAndName(id, name)
                .toVariable(toVariable)
                .inContext(context)
    	 		.fromApplicationAccessPoint(accessPointId, path)
    	 		.build();
    }

    private void addDataPathFromTransformationExpression(DataMappingType mapping, DataAssociation assoc) {
        // TODO HANDLE EXPRESSION LANGUAGE
        if (assoc.getTransformation() != null) {
            String expr = "";
            if (assoc.getTransformation().getBody() != null && !assoc.getTransformation().getBody().isEmpty()) {
                expr = assoc.getTransformation().getBody();
                logger.debug("Set Datapath from Expression-Body value: " + expr + " (" + assoc + ")");
            } else if (assoc.getTransformation().getMixed() != null )  {
                 expr = ModelUtils.getCDataString(assoc.getTransformation().getMixed());
                 logger.debug("Set Datapath from Mixed value: " + expr + " (" + assoc + ")");
            }
            mapping.setDataPath(expr);
        }
    }

    private ItemAwareElement getFirstAssociationSource(DataAssociation assoc) {
        if (assoc.getSourceRef() != null) {
            for (ItemAwareElement source : assoc.getSourceRef()) {
                if (source != null) return source;
            }
        }
        return null;
    }

    public static boolean hasAssignment(DataAssociation assoc) {
        return (assoc.getAssignment() != null && assoc.getAssignment().size() > 0);
    }

    private boolean hasValidSourceAndTarget(DataAssociation assoc, Activity activity, FlowElementsContainer container) {
        boolean valid = true;
        ItemAwareElement associationTarget = assoc.getTargetRef();
        ItemAwareElement associationSource = getFirstAssociationSource(assoc);
        if (associationTarget == null) {
            failures.add("DATA ASSOCIATION TARGET NOT SET " + assoc.getId() + " Activity " + assoc.getId() + " " + activity.getName()  + " in "  + container.getId() );
            valid = false;
        }
        if (associationSource == null) {
            failures.add("DATA ASSOCIATION SOURCE NOT SET " + assoc.getId() + " Activity " + assoc.getId() + " " + activity.getName()  + " in "  + container.getId() );
            valid = false;
        }
        if (associationSource instanceof DataObjectReference) associationSource = ((DataObjectReference)associationSource).getDataObjectRef();
        if (associationSource instanceof DataStoreReference) {
        	associationSource = ((DataStoreReference)associationSource).getDataStoreRef();
        	if (associationTarget.eIsProxy()) {
        		associationTarget = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)associationSource, BpmnModelQuery.getModelDefinitions(container));
        	}
        }

        if (associationSource == null) {
            failures.add("DATA ASSOCIATION SOURCE NOT VALID " + assoc.getId() + " Activity " + activity.getId() + " " + activity.getName()  + " in "  + container.getId() );
            valid = false;
        }
        return valid;
    }

    private String getDataMappingName(DataOutput dataOutput, DataAssociation association) {
        boolean validName = dataOutput != null && dataOutput.getName() != null && !dataOutput.getName().isEmpty();
        String name = validName ? dataOutput.getName() : association.getId();
        return name;
    }

    private String getDataMappingName(DataInput dataInput, DataAssociation association) {
        boolean validName = dataInput != null && dataInput.getName() != null && !dataInput.getName().isEmpty();
        String name = validName ? dataInput.getName() : association.getId();
        return name;
    }

    private String getExpressionValue(Expression expression) {
        if (expression instanceof FormalExpression) {
            logger.debug("Assignment formal expression: " + expression);
            return ((FormalExpression) expression).getBody();
        }
        logger.debug("Assignment 'informal' expression: " + expression);
        return  DocumentationTool.getInformalExpressionValue(expression);
    }

    private String getDataFlowContext(ActivityType activity) {
    	if (ActivityImplementationType.APPLICATION_LITERAL.equals(activity.getImplementation())) {
    		ApplicationType app = activity.getApplication();
    		if (app != null) {
    			if (app.getContext() != null && app.getContext().size() > 0) {
    				return app.getContext().get(0).getType().getId();
    			}
    			else {
    				return PredefinedConstants.APPLICATION_CONTEXT;
    			}
    		}
    	} else if (ActivityImplementationType.SUBPROCESS_LITERAL.equals(activity.getImplementation())) {
    		return PredefinedConstants.ENGINE_CONTEXT;
    	}

    	return PredefinedConstants.DEFAULT_CONTEXT;
    }

    public static String getInDataMappingId(DataInput dataInput, DataInputAssociation inputAssociation) {
    	if (inputAssociation != null) {
    		return inputAssociation.getId();
    	}
    	return dataInput.getId();
    }

    public static String getOutDataMappingId(DataOutput dataOutput, DataOutputAssociation outputAssociation) {
    	if (outputAssociation != null) {
    		return outputAssociation.getId();
    	}
    	return dataOutput.getId();
    }
}
