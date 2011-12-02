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

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.modeling.transformation.debug.PredefinedConstants;
import org.eclipse.stardust.modeling.transformation.debug.common.SteppingManager;
import org.eclipse.stardust.modeling.transformation.debug.debugger.RhinoDebugFrame;
import org.eclipse.stardust.modeling.transformation.debug.debugger.RhinoDebugger;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageBreakpointManager;


import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;

public class JsDebugTarget extends JsDebugElement
      implements IDebugTarget
{
   private static final Logger trace = LogManager.getLogger(JsDebugTarget.class);

   private final ILaunch launch;
   private final RhinoDebugger jsDebugger;
   
   private boolean isTerminated = false;
   private boolean isSuspended = false;
   
   private String sourceName;
   private JsThread thread = null;
   
   private ApplicationType applicationType;
   private String fieldPath;
   private IProject project;
   
   private SteppingManager steppingManager = new SteppingManager();

   //private IDebugEventSetListener debugEventHandler;
   
   private MessageBreakpointManager breakpointManager;

   public JsDebugTarget(ILaunch launch, RhinoDebugger jsDebugger) throws CoreException
   {
      super(null);

      this.target = this;
      this.launch = launch;
      this.jsDebugger = jsDebugger;
      jsDebugger.attachDebugTarget(this);

      //debugEventHandler = new DebugEventHandler();
      //DebugPlugin.getDefault().addDebugEventListener(debugEventHandler);
      
      breakpointManager = new MessageBreakpointManager();
   }
   
   public void addDebugFrame(RhinoDebugFrame debugFrame) throws DebugException
   {
      if ( !hasThreads())
      {
         thread = new JsThread(this);
      }

      thread.addDebugFrame(debugFrame);
   }
   
   public void removeDebugFrame(RhinoDebugFrame debugFrame) throws DebugException
   {
      if (hasThreads())
      {
         thread.removeDebugFrame(debugFrame);
         if ( !thread.hasDebugFrames())
         {
            thread.fireTerminateEvent();
            thread = null;
         }
      }
   }
   
   public void notifyChangeInDebugFrame(RhinoDebugFrame debugFrame) throws DebugException
   {
      if ( hasThreads())
      {
         thread.notifyChangeInDebugFrame(debugFrame);
      }
   }
   
   public SteppingManager getSteppingManager()
   {
      return steppingManager;
   }
   
   public String getSourceName()
   {
      return sourceName;
   }

   public void setSourceName(String sourceName)
   {
      this.sourceName = sourceName;
   }
   
   public void setApplicationType(ApplicationType applicationType)
   {
      this.applicationType = applicationType;
   }
   
   public ApplicationType getApplicationType()
   {
      return applicationType;
   }
   
   public void setFieldPath(String fieldPath)
   {
      this.fieldPath = fieldPath;
   }
   
   public String getFieldPath()
   {
      return fieldPath;
   }
   
   public void setProject(IProject project)
   {
      this.project = project;
   }
   
   public IProject getProject()
   {
      return project;
   }

   public IProcess getProcess()
   {
      return null;// TODO
   }

   public IThread[] getThreads() throws DebugException
   {
      return hasThreads() ? new IThread[] { thread } : new IThread[0];
   }

   public boolean hasThreads() throws DebugException
   {
      return hasThreadsNoThrow();
   }
   
   private boolean hasThreadsNoThrow()
   {
      return null != thread;
   }

   public String getName() throws DebugException
   {
      return "Infinity Message Transformation";
   }

   public boolean supportsBreakpoint(IBreakpoint breakpoint)
   {
      return MessageBreakpointManager.canHandleBreakpoint(breakpoint);
   }

   public ILaunch getLaunch()
   {
      return launch;
   }

   public boolean canTerminate()
   {
      return !isTerminated();
   }

   public boolean isTerminated()
   {
      return isTerminated; // TODO
   }

   public void terminate() throws DebugException
   {
      cleanup();
      
      if(hasThreads())
      {
         thread.terminate();
         thread = null;
      }
      isTerminated = true;
      fireTerminateEvent();
      // TODO
   }

   public boolean canResume()
   {
      if (hasThreadsNoThrow())
      {
         return thread.canResume();
      }
      return false;
   }

   public boolean canSuspend()
   {
      if (hasThreadsNoThrow())
      {
         return thread.canSuspend();
      }
      return false;
   }

   public boolean isSuspended()
   {
      return false; // TODO
   }

   public void resume() throws DebugException
   {
      if (hasThreadsNoThrow())
      {
         thread.resume();
         fireResumeEvent(DebugEvent.CLIENT_REQUEST);
      }
   }

   public void suspend() throws DebugException
   {
      if (hasThreadsNoThrow())
      {
         thread.suspend();
         fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
      }
   }

   public boolean canDisconnect()
   {
      return false;
   }

   public void disconnect() throws DebugException
   {
      // cannot disconnect.
   }

   public boolean isDisconnected()
   {
      return false;
   }

   public boolean supportsStorageRetrieval()
   {
      return false;
   }

   public IMemoryBlock getMemoryBlock(long startAddress, long length)
         throws DebugException
   {
      throw new DebugException(new Status(IStatus.ERROR,
            PredefinedConstants.ID_KNITWARE_DEBUG_MODEL, "Feature not supported."));
   }
   
   // Breakpoint management

   public void breakpointAdded(IBreakpoint breakpoint)
   {
      // done by class MessageBreakpointManager
   }

   public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      // done by class MessageBreakpointManager
   }

   public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      // done by class MessageBreakpointManager
   }
   
   public MessageBreakpointManager getBreakpointManager()
   {
      return breakpointManager;
   }

   private void cleanup()
   {
      try
      {
         // TODO
      }
      finally
      {
         //DebugPlugin.getDefault().removeDebugEventListener(debugEventHandler);
      }
   }
   
   /*
   private class DebugEventHandler implements IDebugEventSetListener
   {
      public void handleDebugEvents(DebugEvent[] events)
      {
         for (int idx = 0; idx < events.length; idx++)
         {
            DebugEvent debugEvent = events[idx];
            if(debugEvent.getSource() instanceof JsThread)
            {
               JsThread thread = (JsThread) debugEvent.getSource();
               if(thread.isSuspended() && debugEvent.getKind() != DebugEvent.SUSPEND)
               {
                  // TODO: handle resume or step events
                  int x = 0;
               }
            }
         }
      }
   }*/
}
