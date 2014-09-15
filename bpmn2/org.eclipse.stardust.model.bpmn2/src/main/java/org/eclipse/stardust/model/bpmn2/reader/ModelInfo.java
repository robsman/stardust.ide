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
package org.eclipse.stardust.model.bpmn2.reader;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Simon Nikles
 *
 */
public class ModelInfo {

    public ModelInfo() {
    }

    public static List<PartnerRole> getParnterRolesOfParticipant(Definitions definitions, Participant participant) {
        List<PartnerRole> roles = new ArrayList<PartnerRole>();
        for (RootElement root : definitions.getRootElements()) {
            if (root instanceof PartnerRole && ((PartnerRole)root).getParticipantRef().contains(participant)) {
                roles.add((PartnerRole)root);
            }
        }
        return roles;
    }

    public static List<PartnerEntity> getParnterEntitiesOfParticipant(Definitions definitions, Participant participant) {
        List<PartnerEntity> entities = new ArrayList<PartnerEntity>();
        for (RootElement root : definitions.getRootElements()) {
            if (root instanceof PartnerEntity && ((PartnerEntity)root).getParticipantRef().contains(participant)) {
                entities.add((PartnerEntity)root);
            }
        }
        return entities;
    }

    public static ItemDefinition getItemDef(Definitions bpmnDefs, String id) {
        if (bpmnDefs == null) return null;
        for (RootElement root : bpmnDefs.getRootElements()) {
            if (root.getId().equals(id) && root instanceof ItemDefinition) return (ItemDefinition) root;
        }
        return null;
    }

    public static Definitions getDefinitions(BaseElement baseElement) {
        if (baseElement == null) return null;
        EObject parent = baseElement.eContainer();
        while (parent != null && !(parent instanceof Definitions)) {
            parent = parent.eContainer();
        }

        if (parent != null) return (Definitions)parent;
        return null;
    }

    private static FlowElementsContainer getContainer(FlowElement element) {
        if (element != null) {
            EObject container = element.eContainer();
            if (container != null && container instanceof FlowElementsContainer) {
                return (FlowElementsContainer) container;
            }
        }
        return null;
    }

    public static List<DataObjectReference> getDataObjectReferencesTo(DataObject dataObject) {
        List<DataObjectReference> refs = new ArrayList<DataObjectReference>();
        FlowElementsContainer container = (FlowElementsContainer)getContainer(dataObject);
        if (container != null) {
            for (FlowElement e : container.getFlowElements()) {
                if (e instanceof DataObjectReference) {
                    DataObjectReference ref = ((DataObjectReference) e);
                    DataObject obj = ref.getDataObjectRef();
                    if (obj != null && obj.equals(dataObject)) {
                        refs.add(ref);
                    }
                }
            }
        }
        return refs;
    }

}
