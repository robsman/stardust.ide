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

import org.eclipse.stardust.engine.core.model.utils.Connection;
import org.eclipse.stardust.engine.core.model.utils.IdentifiableElement;

/**
 *
 */
public interface ITransition extends Connection, IdentifiableElement
{
   /**
    *
    */
   public String getCondition();

   /**
    * Forces the process engine to spawn a workflow thread.
    */
   public boolean getForkOnTraversal();

   /**
    * @return The process definition, the activity belongs to.
    */
   public IProcessDefinition getProcessDefinition();

   /**
    *
    */
   public IActivity getFromActivity();

   /**
    *
    */
   public IActivity getToActivity();
}
