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

import org.eclipse.stardust.common.StringKey;
import org.eclipse.stardust.model.API_Messages;


/**
 * The implementation type of an activity.
 *
 * @author ubirkemeyer
 * @version $Revision$
 */
public class ImplementationType extends StringKey
{
   private static final String[] defaultContexts = {
      PredefinedConstants.DEFAULT_CONTEXT,
      PredefinedConstants.ENGINE_CONTEXT
   };
   private static final String[] engineContext = {
      PredefinedConstants.ENGINE_CONTEXT
   };

   /**
    * An interactive activity which does not execute any application.
    */
   public static final ImplementationType Manual = new ImplementationType(
         "Manual", API_Messages.STR_Manual, defaultContexts); //$NON-NLS-1$
   /**
    * An activity which executes an application, either interactive or non-interactive.
    */
   public static final ImplementationType Application = new ImplementationType(
         "Application", API_Messages.STR_Application, null); //$NON-NLS-1$
   /**
    * An activity which contains a sub process.
    */
   public static final ImplementationType SubProcess = new ImplementationType(
         "Subprocess", API_Messages.STR_Subprocess, engineContext); //$NON-NLS-1$

   /**
    * An activity which is used for routing to other activities via the transitions.
    */
   public static final ImplementationType Route = new ImplementationType(
         "Route", API_Messages.STR_Route, engineContext); //$NON-NLS-1$

   private String[] contexts;

   private ImplementationType(String id, String name, String[] contexts)
   {
      super(id, name);
      this.contexts = contexts;
   }

   /**
    * Gets the identifiers of the predefined application contexts.
    *
    * @return an array containing the valid application contexts for this implementation type.
    */
   public String[] getContexts()
   {
      return contexts;
   }
}
