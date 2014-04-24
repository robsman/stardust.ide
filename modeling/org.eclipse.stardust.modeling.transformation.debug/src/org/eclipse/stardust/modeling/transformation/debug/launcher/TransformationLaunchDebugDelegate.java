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
package org.eclipse.stardust.modeling.transformation.debug.launcher;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.modeling.transformation.debug.debugger.RhinoDebugger;
import org.eclipse.stardust.modeling.transformation.debug.model.JsDebugTarget;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.launch.TransformationLaunchDelegate;

import org.mozilla.javascript.Context;



public class TransformationLaunchDebugDelegate extends TransformationLaunchDelegate
{
   private JsDebugTarget debugTarget = null;

   protected void initOnDebug(ILaunch launch, IProject project,
         ApplicationType applicationType) throws CoreException
   {
      RhinoDebugger debugger = new RhinoDebugger();

      Context context = getJsManager().getContext();
      context.setDebugger(debugger, null);
      context.setGeneratingDebug(true);

      debugTarget = new JsDebugTarget(launch, debugger);
      debugTarget.setApplicationType(applicationType);
      debugTarget.setProject(project);

      launch.addDebugTarget(debugTarget);
   }

   protected boolean initBeforeNextScript(String fieldPath) throws CoreException
   {
      if (debugTarget.getSteppingManager().requestedTermination())
      {
         return false;
      }
      else
      {
         debugTarget.setFieldPath(fieldPath);
         return true;
      }
   }
}
