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
package org.eclipse.stardust.modeling.transformation.debug.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.stardust.modeling.transformation.debug.common.CommonConstants;
import org.eclipse.stardust.modeling.transformation.debug.common.SteppingMode;
import org.eclipse.stardust.modeling.transformation.debug.debugger.RhinoDebugFrame;


public class JsThread extends JsDebugElement implements IThread 
{
   private IBreakpoint[] breakpoints;
   private Throwable suspendedByThrowable = CommonConstants.NO_THROWABLE;
   
   private boolean isTerminated = false;
   private boolean isSuspended = false;
   private boolean isStepping = false;
   
   private final List<RhinoDebugFrame> debugFrames = new ArrayList<RhinoDebugFrame>();
   private final Map<RhinoDebugFrame,IStackFrame> framesMap = new HashMap<RhinoDebugFrame, IStackFrame>();
   
   public JsThread(JsDebugTarget target)
   {
      super(target);
      fireCreationEvent();
   }
   
   public void addDebugFrame(RhinoDebugFrame debugFrame)
   {
      this.debugFrames.add(debugFrame);
      final JsStackFrame stackFrame = new JsStackFrame(this, debugFrame);
      this.framesMap.put(debugFrame, stackFrame);
   }
   
   public void removeDebugFrame(RhinoDebugFrame debugFrame)
   {
      debugFrames.remove(debugFrame);
      framesMap.remove(debugFrame);
   }
   
   public void notifyChangeInDebugFrame(RhinoDebugFrame debugFrame)
   {
      // Is there a better way to inform the debug framework of line changes than
      // destroying and re-creating it?
      JsStackFrame stackFrame = (JsStackFrame) framesMap.get(debugFrame);
      if (null != stackFrame)
      {
         stackFrame.fireTerminateEvent();
         stackFrame.fireCreationEvent();
      }
   }
   
   
   public Throwable getSuspendedByThrowable()
   {
      return suspendedByThrowable;
   }

   public IStackFrame[] getStackFrames() throws DebugException
   {
      IStackFrame[] stackFrames;

      if (hasStackFrames())
      {
         int idx = debugFrames.size();
         stackFrames = new IStackFrame[idx];
         for (Iterator iterator = debugFrames.iterator(); iterator.hasNext();)
         {
            --idx;
            RhinoDebugFrame debugFrame = (RhinoDebugFrame) iterator.next();
            stackFrames[idx] = framesMap.get(debugFrame);
         }
      }
      else
      {
         stackFrames = new IStackFrame[0];
      }

      return stackFrames;
   }

   public boolean hasStackFrames() throws DebugException
   {
      return (isSuspended() || isStepping()) && hasDebugFrames();
   }
   
   public List<RhinoDebugFrame> getDebugFrames()
   {
      return debugFrames;
   }
   
   public boolean hasDebugFrames()
   {
      return !debugFrames.isEmpty();
   }

   public int getPriority() throws DebugException
   {
      return 0;
   }

   public IStackFrame getTopStackFrame() throws DebugException
   {
      IStackFrame[] frames = getStackFrames();
      if (frames.length > 0)
      {
         return frames[0];
      }
      return null;
   }

   public String getName() throws DebugException
   {
      String message = "Thread"; //$NON-NLS-1$
      
      if(CommonConstants.NO_THROWABLE != suspendedByThrowable)
      {
         message += " (suspended by exception)"; //$NON-NLS-1$
      }
      
      if(isStepping())
      {
         message += " (Stepping)"; //$NON-NLS-1$
      }
      
      return message; 
   }

   public IBreakpoint[] getBreakpoints()
   {
      if (breakpoints == null)
      {
         return new IBreakpoint[0];
      }
      return breakpoints;
   }

   public boolean canResume()
   {
      return isSuspended();
   }

   public boolean canSuspend()
   {
      return !isSuspended();
   }

   public boolean isSuspended()
   {
      return isSuspended;
   }

   public void resume() throws DebugException
   {
      resetSuspend();
      fireResumeEvent(DebugEvent.CLIENT_REQUEST);

      int debugFrameSize = debugFrames.size();
      if (0 != debugFrameSize)
      {
         debugFrames.get(debugFrameSize - 1).resumeThreadExecution();
      }
   }

   public void suspend() throws DebugException
   {
      setSuspended(true);
      fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
      
      JsDebugTarget debugTarget = (JsDebugTarget) getDebugTarget();
      debugTarget.getSteppingManager().setMode(SteppingMode.BREAK);
   }
   
   public void setSuspended(boolean isSuspended)
   {
      this.isSuspended = isSuspended;
      isStepping = false;
   }

   public void setSuspendedByThrowable(Throwable throwable) throws DebugException
   {
      suspendedByThrowable = throwable;
   }

   public boolean canStepInto()
   {
      return isSuspended();
   }

   public boolean canStepOver()
   {
      return isSuspended();
   }

   public boolean canStepReturn()
   {
      return isSuspended();
   }

   public boolean isStepping()
   {
      return isStepping;
   }

   public void stepInto() throws DebugException
   {
      doStep(SteppingMode.STEP_INTO);
   }

   public void stepOver() throws DebugException
   {
      doStep(SteppingMode.STEP_OVER);
   }

   public void stepReturn() throws DebugException
   {
      doStep(SteppingMode.STEP_OUT);
   }
   
   public boolean canTerminate()
   {
      return !isTerminated();
   }

   public boolean isTerminated()
   {
      return isTerminated;
   }

   public void terminate() throws DebugException
   {
      isTerminated = true;
      int debugFrameSize = debugFrames.size();
      if (0 != debugFrameSize)
      {
         debugFrames.get(debugFrameSize - 1).resumeThreadExecution(SteppingMode.TERMINATE);
      }
      fireTerminateEvent();
      //getDebugTarget().terminate();
   }

   public void fireResumeEvent(int detail)
   {
      super.fireResumeEvent(detail);
   }

   public void fireSuspendEvent(int detail)
   {
      super.fireSuspendEvent(detail);
   }

   private void doStep(SteppingMode mode)
   {
      isStepping = true;
      resetSuspend();
      
      switch(mode)
      {
         case STEP_INTO:
            fireResumeEvent(DebugEvent.STEP_INTO);
            break;
         case STEP_OUT:
            fireResumeEvent(DebugEvent.STEP_RETURN);
            break;
         case STEP_OVER:
            fireResumeEvent(DebugEvent.STEP_OVER);
      }
   
      int debugFrameSize = debugFrames.size();
      if (0 != debugFrameSize)
      {
         debugFrames.get(debugFrameSize - 1).resumeThreadExecution(mode);
      }
   }
   
   private void resetSuspend()
   {
      isSuspended = false;
      suspendedByThrowable = CommonConstants.NO_THROWABLE;
   }
}
