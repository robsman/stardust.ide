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

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.stardust.modeling.transformation.debug.common.BreakpointSelector;
import org.eclipse.stardust.modeling.transformation.debug.common.CommonConstants;
import org.eclipse.stardust.modeling.transformation.debug.common.SteppingManager;
import org.eclipse.stardust.modeling.transformation.debug.common.SteppingMode;
import org.eclipse.stardust.modeling.transformation.debug.model.JsDebugTarget;
import org.eclipse.stardust.modeling.transformation.debug.model.JsThread;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageBreakpointManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageTransformationLineBreakpoint;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageTransformationMappingBreakpoint;

import com.infinity.bpm.thirdparty.org.mozilla.javascript.Context;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.Scriptable;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.debug.DebugFrame;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.debug.DebuggableScript;


public class RhinoDebugFrame implements DebugFrame
{
   private static Object syncObject = new Object();
   
   private Context context;
   private DebuggableScript debuggableScript;
   private Scriptable scope;
   private Scriptable thisObj;
   private int currentLine = 0;
   
   private JsDebugTarget debugTarget;
   
   RhinoDebugFrame(Context context, DebuggableScript debuggableScript,
         JsDebugTarget debugTarget)
   {
      this.context = context;
      this.debuggableScript = debuggableScript;
      this.debugTarget = debugTarget;
   }
   
   public Context getContext()
   {
      return context;
   }

   public DebuggableScript getDebuggableScript()
   {
      return debuggableScript;
   }

   public JsDebugTarget getDebugTarget()
   {
      return debugTarget;
   }

   public int getCurrentLine()
   {
      return currentLine;
   }

   public Scriptable getScope()
   {
      return scope;
   }

   public Scriptable getThisObj()
   {
      return thisObj;
   }

   public void onDebuggerStatement(Context ctx)
   {      
   }
   
   public void onEnter(Context context, Scriptable scope, Scriptable thisObj,
         Object[] aobj)
   {
      if (debugTarget.getSteppingManager().requestedTermination())
      {
         return;
      }

      this.scope = scope;
      this.thisObj = thisObj;
      try
      {
         final SteppingManager steppingManager = debugTarget.getSteppingManager();
         debugTarget.addDebugFrame(this);

         // Only if this debug frame is the first one a mapping breakpoint can affect program execution. 
         IThread[] threads = debugTarget.getThreads();
         if (threads.length > 0 && ((JsThread)threads[0]).getDebugFrames().size() == 1)
         {
            if (existsEnabledBreakpoint(BreakpointSelector.MAPPING))
            {
               steppingManager.setMode(SteppingMode.STEP_INTO);
            }
         }

         steppingManager.recordEnter();
      }
      catch (DebugException e)
      {
         e.printStackTrace();
      }
   }

   public void onExit(Context context, boolean flag, Object obj)
   {
      if (debugTarget.getSteppingManager().requestedTermination())
      {
         return;
      }

      try
      {
         debugTarget.removeDebugFrame(this);
         debugTarget.getSteppingManager().recordExit();
      }
      catch (DebugException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void onLineChange(Context context, int lineNumber)
   {
      if (debugTarget.getSteppingManager().requestedTermination())
      {
         return;
      }

      try
      {
         currentLine = lineNumber;

         if (existsEnabledBreakpoint(BreakpointSelector.LINE))
         {
            debugTarget.notifyChangeInDebugFrame(this);
            suspend(DebugEvent.BREAKPOINT);
         }
         else if (debugTarget.getSteppingManager().canSuspend())
         {
            debugTarget.notifyChangeInDebugFrame(this);
            suspend(DebugEvent.STEP_END);
         }
      }
      catch (DebugException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void onExceptionThrown(Context context, Throwable throwable)
   {
      if (debugTarget.getSteppingManager().requestedTermination())
      {
         return;
      }

      throwable.printStackTrace();

      // TODO
      if (true/*do some property magic here*/)
      {
         suspend(DebugEvent.CLIENT_REQUEST, throwable);
      }
   }
   
   private boolean existsEnabledBreakpoint(BreakpointSelector selector)
   {
      boolean result = false;
      try
      {
         final MessageBreakpointManager breakpointManager = debugTarget.getBreakpointManager();
         if ( !breakpointManager.isEnabled())
         {
            return false;
         }
         
         for (Iterator iterator = breakpointManager.getBreakPoints()
               .iterator(); iterator.hasNext();)
         {
            IBreakpoint breakpoint = (IBreakpoint) iterator.next();
            if (breakpoint.isEnabled()
                  && MessageBreakpointManager.canHandleBreakpoint(breakpoint))
            {
               if (selector == BreakpointSelector.LINE
                     && breakpoint instanceof MessageTransformationLineBreakpoint)
               {
                  // TODO: Breakpoints have to be matched with JS file, not by line number only.

                  ILineBreakpoint jsBreakpoint = (ILineBreakpoint) breakpoint;
                  if (currentLine + 1 == jsBreakpoint.getLineNumber())
                  {
                     result = true;
                     break;
                  }
               }
               else if (selector == BreakpointSelector.MAPPING
                     && breakpoint instanceof MessageTransformationMappingBreakpoint)
               {
                  MessageTransformationMappingBreakpoint mtmBreakPoint = (MessageTransformationMappingBreakpoint) breakpoint;
                  if (debugTarget.getFieldPath().equals(mtmBreakPoint.getFieldPath()))
                  {
                     result = true;
                     break;
                  }
               }
            }

         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return result;
   }
   
   private void suspend(int detail)
   {
      suspend(detail, CommonConstants.NO_THROWABLE);
   }
   
   private void suspend(int detail, Throwable throwable)
   {
      debugTarget.getSteppingManager().reset();
      
      try
      {
         if (debugTarget.hasThreads())
         {
            IThread[] threads = debugTarget.getThreads();
            JsThread thread = (JsThread) threads[0];
            thread.setSuspendedByThrowable(throwable);
            thread.setSuspended(true);
            thread.fireSuspendEvent(detail);
            
            // Wait until resume or step
            suspendThreadExecution();
         }
      }
      catch (DebuggerTerminatedException e)
      {
         // let it be propagated ... this exception shows that termination was requested.
         throw e;
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   /**
    * @throws InterruptedException
    */
   private void suspendThreadExecution() throws InterruptedException
   {
      synchronized(syncObject)
      {
         syncObject.wait();
         if(debugTarget.getSteppingManager().requestedTermination())
         {
            context.setDebugger(null, null);
            throw new DebuggerTerminatedException("Debugger terminated."); //$NON-NLS-1$
         }
      }
   }
   
   /**
    * Has to be called after {@link #suspendThreadExecution()}. And it has to be called 
    * from another thread.
    */
   public void resumeThreadExecution()
   {
      resumeThreadExecution(SteppingMode.NONE);
   }
   
   /**
    * Has to be called after {@link #suspendThreadExecution()}. And it has to be called 
    * from another thread.
    * 
    * @param steppingMode defines the stepping 
    */
   public void resumeThreadExecution(SteppingMode steppingMode)
   {
      synchronized (syncObject)
      {
         debugTarget.getSteppingManager().setMode(steppingMode);
         syncObject.notify();
      }
   }
   
   /**
    * Exception which is thrown when debugger termination was requested. Since there
    * does not seem to exists any API in Rhino for debugger termination it has to
    * be done the hard way. 
    *
    * @author born
    */
   private static class DebuggerTerminatedException extends RuntimeException
   {
      private static final long serialVersionUID = 1L;

      public DebuggerTerminatedException(String message)
      {
         super(message);
      }
      
   }   
}