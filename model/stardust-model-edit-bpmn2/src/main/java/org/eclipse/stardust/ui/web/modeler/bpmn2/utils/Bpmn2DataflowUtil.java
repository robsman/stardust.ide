package org.eclipse.stardust.ui.web.modeler.bpmn2.utils;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ItemAwareElement;

public class Bpmn2DataflowUtil {

    public static boolean hasValidSourceAndTarget(DataAssociation assoc, Activity activity, FlowElementsContainer container) {

    	ItemAwareElement associationTarget = assoc.getTargetRef();
        ItemAwareElement associationSource = getFirstAssociationSource(assoc);

        if (associationTarget == null || associationSource == null) return false;

        if (associationSource instanceof DataObjectReference) associationSource = ((DataObjectReference)associationSource).getDataObjectRef();
        if (associationSource instanceof DataStoreReference) {
        	associationSource = ((DataStoreReference)associationSource).getDataStoreRef();
        	if (associationSource.eIsProxy()) {
        		associationSource = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)associationSource, ModelInfo.getDefinitions(container));
        	}
        }
        if (associationTarget instanceof DataStoreReference) {
        	associationTarget = ((DataStoreReference)associationTarget).getDataStoreRef();
        	if (associationTarget.eIsProxy()) {
        		associationTarget = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)associationSource, ModelInfo.getDefinitions(container));
        	}
        }

        if (associationTarget == null || associationSource == null) return false;
        return true;
    }

    public static ItemAwareElement getFirstAssociationSource(DataAssociation assoc) {
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

    public static String getDataMappingName(DataInput dataInput, DataAssociation association) {
        boolean validName = dataInput != null && dataInput.getName() != null && !dataInput.getName().isEmpty();
        String name = validName ? dataInput.getName() : association.getId();
        return name;
    }

}
