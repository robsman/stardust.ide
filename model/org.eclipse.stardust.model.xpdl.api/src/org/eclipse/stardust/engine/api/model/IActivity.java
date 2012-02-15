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
import java.util.Set;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.model.spi.AccessPoint;


/**
 *
 */
public interface IActivity extends IViewable, EventHandlerOwner
{
   ImplementationType getImplementationType();

   LoopType getLoopType();

   String getLoopCondition();

   JoinSplitType getJoinType();

   JoinSplitType getSplitType();

   boolean getAllowsAbortByPerformer();

   /**
    * @return The process definition, the activity belongs to.
    */
   IProcessDefinition getProcessDefinition();

   /**
    * @return The (sub)process definition, the activity uses
    *         for implementation.
    */
   IProcessDefinition getImplementationProcessDefinition();

   /**
    * 
    */
   SubProcessModeKey getSubProcessMode();

   /**
    *
    */
   Iterator getAllInTransitions();

   /**
    *
    */
   Iterator getAllOutTransitions();

   /**
    *
    */
   IModelParticipant getPerformer();

   /**
    *
    */
   IApplication getApplication();

   Iterator getAllDataMappings();

   /**
    * Returns an iterator with all IN and INOUT data mappings.
    */
   Iterator getAllInDataMappings();

   /**
    * Returns an iterator with all OUT and INOUT data mappings.
    */
   Iterator getAllOutDataMappings();

   IDataMapping findDataMappingById(String id, Direction direction, String context);

   /**
    * Retrieves a data mapping for the data <code>data</code>.
    */
   Iterator findDataMappings(IData data, Direction direction);

   /**
    * Checks, wether this activity may be suspended to a worklist.
    */
   boolean isInteractive();

   Set getApplicationOutDataMappingAccessPoints();

   AccessPoint getAccessPoint(String context, String id);

   Iterator findExceptionHandlers(IData data);

   Iterator getAllContexts();

   IApplicationContext getContext(String id);

   boolean isHibernateOnCreation();

   Iterator getAllEventHandlers(String type);

   boolean hasEventHandlers(String type);
}
