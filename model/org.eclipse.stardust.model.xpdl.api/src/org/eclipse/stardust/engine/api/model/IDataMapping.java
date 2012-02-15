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

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.model.spi.AccessPoint;


/**
 * Describes the mapping between process data and activity application
 * parameters.
 */
public interface IDataMapping extends ModelElement
{
   String getId();

   String getName();

   public IData getData();

   public IActivity getActivity();

   public Direction getDirection();

   public String getDataPath();

   public String getActivityPath();

   /**
    * Returns the name of the unique access point belonging to the data mapping
    * at the application end point.
    * 
    * @return 
    */
   String getActivityAccessPointId();

   AccessPoint getActivityAccessPoint();

   String getContext();
}
