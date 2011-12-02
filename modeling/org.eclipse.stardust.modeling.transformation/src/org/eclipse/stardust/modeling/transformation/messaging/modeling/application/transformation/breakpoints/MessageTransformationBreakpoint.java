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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.Breakpoint;

public class MessageTransformationBreakpoint extends Breakpoint
{
   protected static final String MODEL_ID = "org.eclipse.stardust.modeling.transformation.debug"; //$NON-NLS-1$

   public String getModelIdentifier()
   {
      // TODO This is defined in the debug plugin.....
      return MODEL_ID;
   }

   protected void registerBreakpoint() throws CoreException
   {
      DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(this);
   }
}
