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
package org.eclipse.stardust.modeling.transformation.debug.debugger;

import org.eclipse.stardust.modeling.transformation.debug.model.JsDebugTarget;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.debug.DebugFrame;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;


public class RhinoDebugger implements Debugger
{
   private JsDebugTarget debugTarget = null;

   public DebugFrame getFrame(Context context, DebuggableScript debuggableScript)
   {
      return new RhinoDebugFrame(context, debuggableScript, debugTarget);
   }

   public void handleCompilationDone(Context context,
         DebuggableScript debuggableScript, String s)
   {
   }

   public void attachDebugTarget(JsDebugTarget debugTarget)
   {
      this.debugTarget = debugTarget;
   }
}