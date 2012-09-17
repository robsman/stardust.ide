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

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.RootElement;

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



}
