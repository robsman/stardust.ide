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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;

public class MessageTransformationMappingBreakpoint extends
      MessageTransformationBreakpoint
{
   private static final String MTM_FIELD_PATH = "MTM.FieldPath"; //$NON-NLS-1$
   // The format of a "fully qualified marker-id" seems to be: plugin-id.marker-id
   private static final String BREAKPOINT_MARKER = "org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationMappingBreakpointMarker"; //$NON-NLS-1$
   private String fieldPath;
   
   public MessageTransformationMappingBreakpoint()
   {
      super();
   }

   public MessageTransformationMappingBreakpoint(final IResource resource,
         final String fieldPath)
   {
      super();
      this.fieldPath = fieldPath;

      final Map attributes = new HashMap();
      IWorkspaceRunnable body = new IWorkspaceRunnable()
      {
         public void run(IProgressMonitor monitor) throws CoreException
         {
            // A new breakpoint with marker is created.
            IMarker marker = resource
                  .createMarker(BREAKPOINT_MARKER); //$NON-NLS-1$
            attributes.put(IBreakpoint.ID, getModelIdentifier());
            attributes.put(IBreakpoint.ENABLED, new Boolean(true));
            attributes.put(MTM_FIELD_PATH, fieldPath);
            attributes.put(IMarker.LINE_NUMBER, new Integer(0));
            attributes.put(IMarker.CHAR_START, new Integer(0));
            attributes.put(IMarker.CHAR_END, new Integer(0));
            marker.setAttributes(attributes);
            setMarker(marker);

            registerBreakpoint();
         }
      };

      try
      {
         ResourcesPlugin.getWorkspace().run(body, null);
      }
      catch (CoreException ce)
      {
         ce.printStackTrace();
      }
   }
   
   public String getFieldPath()
   {
      return fieldPath;
   }
   
   @Override
   public void setMarker(IMarker marker) throws CoreException
   {
      super.setMarker(marker);
      fieldPath = (String)marker.getAttribute(MTM_FIELD_PATH);
   }

   /**
    * @param resource
    * @param fieldPath
    * @return true if an existing breakpoint toggled off
    * 
    */
   public static boolean toggleBreakpointOff(final IResource resource,
         final String fieldPath)
   {
      try
      {
         if (null != resource)
         {
            IMarker[] markers = resource.findMarkers(BREAKPOINT_MARKER, true,
                  IResource.DEPTH_INFINITE);
            for (int idx = 0; idx < markers.length; idx++)
            {
               IMarker marker = markers[idx];
               if (fieldPath.equals(marker.getAttribute(MTM_FIELD_PATH)))
               {
                  // find breakpoint for marker
                  IBreakpointManager breakpointManager = DebugPlugin.getDefault()
                        .getBreakpointManager();
                  IBreakpoint[] breakpoints = breakpointManager.getBreakpoints(MODEL_ID);
                  for (int idx2 = 0; idx2 < breakpoints.length; idx2++)
                  {
                     IBreakpoint breakpoint = breakpoints[idx2];
                     if (marker.equals(breakpoint.getMarker()))
                     {
                        breakpoint.delete();
                        return true;
                     }
                  }
               }
            }
         }
      }
      catch (CoreException x)
      {
         x.printStackTrace();
      }

      return false;
   }
}
