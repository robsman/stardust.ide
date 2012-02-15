/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.engine.api.model;

import java.util.Iterator;

import org.eclipse.stardust.common.config.Version;
import org.eclipse.stardust.engine.core.model.utils.RootElement;
import org.eclipse.stardust.model.diagram.Diagram;


/**
 * @author mgille
 */
public interface IModel extends RootElement
{
   /**
    *
    */
   IModeler authentify(String id, String password);

   /**
    *
    */
   IApplication findApplication(String id);

   /**
    *
    */
   IData findData(String id);

   /**
    *
    */
   Diagram findDiagram(String id);

   /**
    *
    */
   ILinkType findLinkType(String id);

   /**
    *
    */
   IModelParticipant findParticipant(String id);

   /**
    *
    */
   IProcessDefinition findProcessDefinition(String id);

   /**
    *
    */
   Iterator getAllApplications();

   /**
    *
    */
   int getApplicationsCount();

   /**
    *
    */
   Iterator getAllData();

   /**
    *
    */
   Iterator getAllDiagrams();

   /**
    *
    */
   int getDiagramsCount();

   /**
    * Returns all participants participating in workflow execution and modeling
    * that are organizations.
    *
    * @see #getAllParticipants
    */
   Iterator getAllOrganizations();

   /**
    * Returns all participants participating in workflow execution and modeling
    * that are roles.
    *
    * @see #getAllParticipants
    */
   Iterator getAllRoles();

   /**
    * Returns all participants participating in workflow execution and modeling.
    * Currently roles, organizations and modelers.
    *
    * @see #getAllParticipants
    */
   Iterator getAllParticipants();

   /**
    * Returns all participants participating in workflow execution.
    * Currently roles and organizations.
    *
    * @see #getAllOrganizations
    * @see #getAllParticipants
    */
   Iterator getAllWorkflowParticipants();

   /**
    *
    */
   Iterator getAllProcessDefinitions();

   /**
    *
    */
   int getProcessDefinitionsCount();

   /**
    *
    */
   Iterator getAllTopLevelParticipants();

   /**
    *
    */
   int getModelersCount();

   /**
    *
    */
   int getRolesCount();

   /**
    *
    */
   int getOrganizationsCount();

   /**
    *
    */
   int getConditionalPerformersCount();

   /**
    *
    */
   int getDataCount();

   /**
    * Retrieves all (predefined and user defined) link types for the model version
    */
   Iterator getAllLinkTypes();

   /**
    * Retrieves all link types whose first or second type is the class provided
    * by <tt>type</tt>.
    */
   Iterator getAllLinkTypesForType(Class type);

   Iterator getAllViews();

   IApplicationType findApplicationType(String id);

   Iterator getAllApplicationTypes();

   IDataType findDataType(String id);

   Iterator getAllDataTypes();

   IApplicationContextType findApplicationContextType(String id);

   Iterator getAllApplicationContextTypes();

   ITriggerType findTriggerType(String id);

   Iterator getAllTriggerTypes();

   Iterator getAllEventConditionTypes();

   IEventConditionType findEventConditionType(String id);

   IEventActionType findEventActionType(String id);

   Iterator getAllEventActionTypes();

   Iterator getAllModelers();

   Iterator getAllConditionalPerformers();

   Version getCarnotVersion();
}
