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

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.core.model.utils.IdentifiableElement;


/**
 *
 */
public interface IProcessDefinition
      extends IViewable, IdentifiableElement, EventHandlerOwner
{
   IActivity findActivity(String id);

   IDataPath findDescriptor(String id);

   ITransition findTransition(String id);

   ITrigger findTrigger(String id);

   Iterator getAllActivities();

   Iterator getAllDiagrams();

   /**
    * Returns all activities using this process definition for their implementation
    */
   Iterator getAllImplementingActivities();

   Iterator getAllPossibleSubprocesses();

   /**
    * Retrieves all subprocesses of this process.
    * <p/>
    * The subrocess/superprocess association is a conceptual association; it is not used by
    * the workflow engine but for modeling, reporting and model validation.
    */
   Iterator getAllSubProcesses();

   /**
    * Retrieves all superprocesses of this process.
    * <p/>
    * The subrocess/superprocess association is a conceptual association; it is not used by
    * the workflow engine but for modeling, reporting and model validation.
    */
   Iterator getAllSuperProcesses();

   Iterator getAllTransitions();

   Iterator getAllTriggers();

   Iterator getAllDescriptors();

   IActivity getRootActivity();

   /**
    * Checks wether the model is consistent.
    */
   boolean isConsistent();

   Iterator getAllDataPaths();

   IDataPath findDataPath(String id, Direction direction);

   Iterator getAllOutDataPaths();

   Iterator getAllInDataPaths();

   Iterator getAllEventHandlers(String type);

   boolean hasEventHandlers(String type);
}
