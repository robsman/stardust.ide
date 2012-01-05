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

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.stardust.common.CollectionUtils;

public class MessageBreakpointManager implements IBreakpointListener
{
   private final IBreakpointManager breakpointManager;
   private final Set breakpoints = CollectionUtils.newHashSet();

   public MessageBreakpointManager()
   {
      breakpointManager = DebugPlugin.getDefault().getBreakpointManager();
      breakpointManager.addBreakpointListener(this);
      addBreakpoints(breakpointManager.getBreakpoints());
   }

   public void breakpointAdded(IBreakpoint breakpoint)
   {
      addBreakpoints(new IBreakpoint[] { breakpoint });
   }

   public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      // TODO
   }

   public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      removeBreakpoints(new IBreakpoint[] { breakpoint });
   }
   
   public Set getBreakPoints()
   {
      return Collections.unmodifiableSet(breakpoints);
   }
   
   public boolean isBreakpointAvailable(String fieldPath)
   {
      for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();)
      {
         IBreakpoint rawBreakpoint = (IBreakpoint) iterator.next();
         if (rawBreakpoint instanceof MessageTransformationMappingBreakpoint)
         {
            MessageTransformationMappingBreakpoint breakpoint = (MessageTransformationMappingBreakpoint) rawBreakpoint;
            if (breakpoint.getFieldPath().equals(fieldPath))
            {
               return true;
            }
         }
      }

      return false;
   }
   
   public boolean isEnabled()
   {
      return breakpointManager.isEnabled();
   }

   private void addBreakpoints(IBreakpoint[] breakpoints)
   {
      for (int i = 0; i < breakpoints.length; i++)
      {
         IBreakpoint breakpoint = breakpoints[i];
         if (canHandleBreakpoint(breakpoint))
         {
            this.breakpoints.add(breakpoint);
         }
      }
   }

   private void removeBreakpoints(IBreakpoint[] breakpoints)
   {
      for (int i = 0; i < breakpoints.length; i++)
      {
         IBreakpoint breakpoint = breakpoints[i];
         if (canHandleBreakpoint(breakpoint))
         {
            this.breakpoints.remove(breakpoint);
         }
      }
   }

   public static boolean canHandleBreakpoint(IBreakpoint breakpoint)
   {
      return breakpoint instanceof MessageTransformationMappingBreakpoint;
   }

}
