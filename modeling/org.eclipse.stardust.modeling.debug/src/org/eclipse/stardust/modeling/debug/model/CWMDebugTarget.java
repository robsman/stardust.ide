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
package org.eclipse.stardust.modeling.debug.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaMethodBreakpoint;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jdt.debug.core.IJavaThread;
import org.eclipse.stardust.common.OneElementIterator;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.ui.ICWMDebugTarget;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.highlighting.HighlightState;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.Debugger;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DebugVariableUtils;
import org.eclipse.stardust.modeling.debug.debugger.types.JavaTypeValueFactory;
import org.eclipse.stardust.modeling.debug.debugger.types.ProcessInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.TransitionTokenDigest;
import org.eclipse.stardust.modeling.debug.engine.ManagedRunnerHelper;
import org.eclipse.stardust.modeling.debug.highlighting.HighlightManager;
import org.eclipse.stardust.modeling.debug.util.CollectionUtils;
import org.eclipse.stardust.modeling.debug.views.WorklistView;

public class CWMDebugTarget extends CWMDebugElement
      implements ICWMDebugTarget, IDebugTarget
{
   private static final Logger trace = LogManager.getLogger(CWMDebugTarget.class);

   private IDebugTarget javaTarget;

   private String sourceName;

   private WorklistManager worklistManager;

   private List transTokens = new ArrayList();

   private List threadList = new ArrayList();

   private IJavaThread onCompletionThread = null;

   private IDebugEventSetListener workflowEventDelegater;

   private IDebugEventSetListener workflowThreadHandler;

   private WorkflowEventCallback workflowEventCallback;

   private WorkflowModelEditor editor;

   private boolean analystMode;

   public CWMDebugTarget(WorkflowModelEditor editor, IDebugTarget javaTarget,
         ProcessDefinitionType pd, boolean analystMode)
         throws CoreException
   {
      super(null);
      
      this.target = this;
      this.javaTarget = javaTarget;
      
      this.editor = editor;
      this.analystMode = analystMode;

      workflowEventDelegater = new WorkflowEventDelegater();
      workflowThreadHandler = new WorkflowThreadHandler();
      worklistManager = new WorklistManager(this);

      DebugPlugin.getDefault().addDebugEventListener(workflowEventDelegater);
      DebugPlugin.getDefault().addDebugEventListener(workflowThreadHandler);
      // DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);

      workflowEventCallback = new WorkflowEventCallback();
   }

   public String getSourceName()
   {
      return sourceName;
   }

   public void setSourceName(String sourceName)
   {
      this.sourceName = sourceName;
   }

   public IProcess getProcess()
   {
      return javaTarget.getProcess();
   }

   public IThread[] getThreads() throws DebugException
   {
      List javaThreads = Arrays.asList(javaTarget.getThreads());
      List concatenatedThreads;

      synchronized (threadList)
      {
         concatenatedThreads = new ArrayList();

         CollectionUtils.copy(concatenatedThreads, javaThreads, new Predicate()
         {
            public boolean accept(Object o)
            {
               try
               {
                  IJavaThread javaThread = (IJavaThread) o;

                  if (Constants.THREAD_GROUP_ACTIVITY_THREAD.equals(javaThread
                        .getThreadGroupName())
                        || Constants.THREAD_GROUP_HELPER_THREAD.equals(javaThread
                              .getThreadGroupName())
                        || Constants.THREAD_GROUP_ON_COMPLETION_THREAD.equals(javaThread
                              .getThreadGroupName()))
                  {
                     return false;
                  }

                  return true;
               }
               catch (DebugException e)
               {
                  throw new InternalException(e);
               }
            }
         });

         CollectionUtils.copy(concatenatedThreads, threadList, null);
      }

      if (null != onCompletionThread && null != getWorkflowThread(onCompletionThread))
      {
         CollectionUtils.copy(concatenatedThreads, new OneElementIterator(
               onCompletionThread), null);
      }

      return (IThread[]) concatenatedThreads.toArray(new IThread[0]);
   }

   public boolean hasThreads() throws DebugException
   {
      synchronized (threadList)
      {
         return !threadList.isEmpty() || javaTarget.hasThreads();
      }
   }

   public String getName() throws DebugException
   {
      return Debug_Messages.CWM_DEBUG_NAME_CARNOTWorkflowDebugger;
   }

   public boolean supportsBreakpoint(IBreakpoint breakpoint)
   {
      return javaTarget.supportsBreakpoint(breakpoint);
   }

   public ILaunch getLaunch()
   {
      return javaTarget.getLaunch();
   }

   public boolean canTerminate()
   {
      return javaTarget.canTerminate();
   }

   public boolean isTerminated()
   {
      return javaTarget.isTerminated();
   }

   public void terminate() throws DebugException
   {
      cleanup();
      javaTarget.terminate();
   }

   private void cleanup()
   {
      try
      {
         HighlightManager.getDefault().removeAllHighlightables();
         worklistManager.reset();
      }
      finally
      {
         DebugPlugin.getDefault().removeDebugEventListener(workflowEventDelegater);
         DebugPlugin.getDefault().removeDebugEventListener(workflowThreadHandler);
      }
   }

   public boolean canResume()
   {
      return javaTarget.canResume();// !isTerminated() && isSuspended();
   }

   public boolean canSuspend()
   {
      return javaTarget.canSuspend();// !isTerminated() && !isSuspended();
   }

   public boolean isSuspended()
   {
      return javaTarget.isSuspended();// suspended;
   }

   public void resume() throws DebugException
   {
      // suspended = false;
      javaTarget.resume();
      // fireResumeEvent(DebugEvent.CLIENT_REQUEST);
   }

   public void suspend() throws DebugException
   {
      javaTarget.suspend();
   }

   public boolean canDisconnect()
   {
      return javaTarget.canDisconnect();
   }

   public void disconnect() throws DebugException
   {
      javaTarget.disconnect();
   }

   public boolean isDisconnected()
   {
      return javaTarget.isDisconnected();
   }

   public boolean supportsStorageRetrieval()
   {
      return javaTarget.supportsStorageRetrieval();
   }

   public IMemoryBlock getMemoryBlock(long startAddress, long length)
         throws DebugException
   {
      return javaTarget.getMemoryBlock(startAddress, length);
   }

   public void breakpointAdded(IBreakpoint breakpoint)
   {
      /*
       * try { if (breakpoint instanceof IJavaBreakpoint) { IJavaBreakpoint javaBreakpoint =
       * (IJavaBreakpoint) breakpoint; if (IJavaBreakpoint.SUSPEND_VM !=
       * javaBreakpoint.getSuspendPolicy()) {
       */
      javaTarget.breakpointAdded(breakpoint);
      /*
       * } } } catch (CoreException e) { throw new InternalException(e); }
       */
   }

   public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      /*
       * try { if (breakpoint instanceof IJavaBreakpoint) { IJavaBreakpoint javaBreakpoint =
       * (IJavaBreakpoint) breakpoint; if (IJavaBreakpoint.SUSPEND_VM !=
       * javaBreakpoint.getSuspendPolicy()) {
       */
      javaTarget.breakpointChanged(breakpoint, delta);
      /*
       * } } } catch (CoreException e) { throw new InternalException(e); }
       */
   }

   public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta)
   {
      javaTarget.breakpointRemoved(breakpoint, delta);
   }

   private CWMThread getWorkflowThread(IJavaThread javaThread)
   {
      synchronized (threadList)
      {
         for (Iterator i = threadList.iterator(); i.hasNext();)
         {
            CWMThread thread = (CWMThread) i.next();

            if (thread.getJavaThread().equals(javaThread))
            {
               return thread;
            }
         }
      }

      return null;
   }

   public void updateSuspendedActivityInstances()
   {
      List result = new ArrayList();
      synchronized (threadList)
      {
         for (Iterator i = threadList.iterator(); i.hasNext();)
         {
            CWMThread thread = (CWMThread) i.next();
            if (thread.isSuspended())
            {
               ActivityInstanceDigest activityInstance = thread
                     .getCurrentActivityInstance();
               if (null != activityInstance)
               {
                  activityInstance.setThread(thread);
                  result.add(activityInstance);
               }
            }
         }
      }
      worklistManager.set(result);
   }

   private class WorkflowEventDelegater implements IDebugEventSetListener
   {
      public void handleDebugEvents(DebugEvent[] events)
      {
         for (int eventIdx = 0; eventIdx < events.length; ++eventIdx)
         {
            DebugEvent event = events[eventIdx];
            Object source = event.getSource();
            if (source instanceof IJavaThread)
            {
               int kind = event.getKind();
               if ((kind & (DebugEvent.SUSPEND | DebugEvent.BREAKPOINT)) != 0)
               {
                  IJavaThread javaThread = (IJavaThread) source;
                  IBreakpoint[] breakpoints = javaThread.getBreakpoints();
                  for (int bpIdx = 0; bpIdx < breakpoints.length; ++bpIdx)
                  {
                     IBreakpoint bp = breakpoints[bpIdx];

                     // TODO: getTypeName returns a pattern. Do matching to the
                     // pattern,
                     // because the pattern is not necessarily equal to the class name.
                     if (bp instanceof IJavaMethodBreakpoint)
                     {
                        IJavaMethodBreakpoint jmbp = (IJavaMethodBreakpoint) bp;
                        try
                        {
                           String typeName = jmbp.getTypeName();
                           if (Debugger.WorkflowEventListenerImpl.class.getName().equals(typeName))
                           {
                              IStackFrame[] frames = javaThread.getStackFrames();
                              if (frames.length != 0)
                              {
                                 IJavaStackFrame currentStackFrame = (IJavaStackFrame) frames[0];
                                 try
                                 {
                                    IVariable[] vars = currentStackFrame.getVariables();
                                    String methodName = jmbp.getMethodName();
                                    try
                                    {
                                       if (Debugger.WorkflowEventListenerImpl.performedTransitionMethodName.equals(methodName))
                                       {
                                          TransitionTokenDigest details = JavaTypeValueFactory.createInstance("transToken", vars); //$NON-NLS-1$
                                          workflowEventCallback.performedTransition(details);
                                       }
                                       else
                                       {
                                          ActivityInstanceDigest details = null;
                                          if (Debugger.WorkflowEventListenerImpl.startedActivityInstanceMethodName.equals(methodName))
                                          {
                                             details = JavaTypeValueFactory.createInstance("activityInstance", vars); //$NON-NLS-1$
                                             workflowEventCallback.startedActivityInstance(details);
                                          }
                                          else if (Debugger.WorkflowEventListenerImpl.completedActivityInstanceMethodName
                                                .equals(methodName))
                                          {
                                             details = JavaTypeValueFactory.createInstance("activityInstance", vars); //$NON-NLS-1$
                                             workflowEventCallback.completedActivityInstance(details);
                                          }
                                          else if (Debugger.WorkflowEventListenerImpl.appendedToWorklistMethodName
                                                .equals(methodName))
                                          {
                                             details = JavaTypeValueFactory.createInstance("activityInstance", vars); //$NON-NLS-1$
                                             workflowEventCallback.appendedToWorklist(details);
                                          }
                                          trace.info(MessageFormat.format(Debug_Messages.CWM_DEBUG_MSG_CurrentActivityInstanceDigest, details));
                                          if (null != details)
                                          {
                                             CWMThread thread = getWorkflowThread(javaThread);
                                             if (null != thread)
                                             {
                                                thread.setCurrentActivityInstance(details);
                                             }
                                          }
                                       }
                                    }
                                    catch (RuntimeException e)
                                    {
                                       e.printStackTrace();
                                       throw e;
                                    }
                                 }
                                 finally
                                 {
                                    trace.info(MessageFormat.format(
                                          Debug_Messages.CWM_DEBUG_MSG_ResumeJavaThread,
                                          new Object[] {javaThread}));
                                    javaThread.resume();
                                 }
                              }
                           }
                        }
                        catch (DebugException x)
                        {
                           throw new InternalException(x);
                        }
                        catch (CoreException x)
                        {
                           throw new InternalException(x);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private class WorkflowThreadHandler implements IDebugEventSetListener
   {
      IDebugTarget javaThreadTarget;

      public void handleDebugEvents(DebugEvent[] events)
      {
         try
         {
            for (int eventIdx = 0; eventIdx < events.length; ++eventIdx)
            {
               if (events[eventIdx].getSource() instanceof IJavaThread)
               {
                  IJavaThread javaThread = (IJavaThread) events[eventIdx].getSource();

                  if (Constants.THREAD_GROUP_ACTIVITY_THREAD.equals(javaThread
                        .getThreadGroupName()))
                  {
                     switch (events[eventIdx].getKind())
                     {
                     case DebugEvent.CREATE:
                        onCreate(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.TERMINATE:
                        onTerminate(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.RESUME:
                        onResume(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.SUSPEND:
                        onSuspend(javaThread, events[eventIdx]);
                        break;
                     }
                  }
                  else if (Constants.THREAD_GROUP_ON_COMPLETION_THREAD.equals(javaThread
                        .getThreadGroupName()))
                  {
                     switch (events[eventIdx].getKind())
                     {
                     case DebugEvent.CREATE:
                        onCreateCompletionThread(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.TERMINATE:
                        onTerminate(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.RESUME:
                        onResume(javaThread, events[eventIdx]);
                        break;

                     case DebugEvent.SUSPEND:
                        onSuspend(javaThread, events[eventIdx]);
                        break;
                     }
                  }
               }
               if (events[eventIdx].getKind() == DebugEvent.TERMINATE)
               {
                  if (isTerminated() && !hasThreads())
                  {
                     cleanup();
                  }
               }
            }
         }
         catch (DebugException x)
         {
            throw new InternalException(x);
         }
      }

      private void onCreateCompletionThread(IJavaThread javaThread, DebugEvent event)
      {
         if (null != onCompletionThread)
         {
            onCompletionThread = javaThread;
            onCreate(javaThread, event);
            onCompletionThread = null;
         }
         else
         {
            onCompletionThread = javaThread;
         }
      }

      private void onCreate(IJavaThread javaThread, DebugEvent event)
      {
         if (javaThreadTarget == null)
         {
            javaThreadTarget = javaThread.getDebugTarget();
         }
         if (javaThread.getDebugTarget().equals(javaThreadTarget))
         {
            CWMThread thread = new CWMThread(CWMDebugTarget.this, javaThread,
                  onCompletionThread == javaThread);

            synchronized (threadList)
            {
               threadList.add(thread);
            }

            thread.fireCreationEvent();
         }
      }

      private void onTerminate(IJavaThread javaThread, DebugEvent event)
      {
         try
         {
            synchronized (threadList)
            {
               CWMThread thread = getWorkflowThread(javaThread);

               if (null != thread)
               {
                  threadList.remove(thread);
                  thread.fireTerminateEvent();

                  if (threadList.isEmpty())
                  {
                     if (null != onCompletionThread)
                     {
                        onCompletionThread.resume();
                     }
                     else
                     {
                        terminate();
                     }
                  }
               }
            }
         }
         catch (DebugException e)
         {
            throw new InternalException(e);
         }
      }

      private void onResume(IJavaThread javaThread, DebugEvent event)
      {
         CWMThread thread = getWorkflowThread(javaThread);

         if (null != thread)
         {
            thread.fireResumeEvent(event.getDetail());

            updateSuspendedActivityInstances();
         }
      }

      private void onSuspend(IJavaThread javaThread, DebugEvent event)
      {
         if (event.isEvaluation())
         {
            // if evaluation is not ignored then the content of the
            // variables view will flicker or even vanish (Workaround)
            return;
         }

         CWMThread thread = getWorkflowThread(javaThread);

         if (null != thread)
         {
            try
            {
               IBreakpoint[] breakpoints = javaThread.getBreakpoints();
               for (int idx = 0; idx < breakpoints.length; ++idx)
               {
                  IBreakpoint breakpoint = breakpoints[idx];

                  if (breakpoint instanceof IJavaMethodBreakpoint)
                  {
                     IJavaMethodBreakpoint javaBreakpoint = (IJavaMethodBreakpoint) breakpoint;

                     if (isSuspendThreadBreakpoint(javaBreakpoint))
                     {
                        IStackFrame[] frames = javaThread.getStackFrames();
                        if (frames.length > 1)
                        {
                           // The variables in question are not located on the
                           // current stack frame (index 0) but on the stack frame
                           // before (for instance for
                           // ManagedRunner.communicateWithSuspendedActivityInstance(...))
                           IJavaStackFrame currentStackFrame = (IJavaStackFrame) frames[1];
                           IVariable[] vars = currentStackFrame.getVariables();

                           if (null != thread && thread.isCompletionThread())
                           {
                              ProcessInstanceDigest processInstance = JavaTypeValueFactory.createInstance("processInstance", vars); //$NON-NLS-1$
                              thread.setRootProcessInstance(processInstance);
                           }
                           else
                           {
                              ActivityInstanceDigest activityInstance = JavaTypeValueFactory.createInstance("activityInstance", vars); //$NON-NLS-1$
                              IVariable reloadValues = DebugVariableUtils.findVariable("reloadValues", vars); //$NON-NLS-1$
                              thread.setCurrentActivityInstance(activityInstance);
                              thread.setReloadValuesVariable(reloadValues);

                              updateSuspendedActivityInstances();
                           }
                        }
                     }
                  }
               }

               thread.fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
            }
            catch (CoreException e)
            {
               throw new InternalException(e);
            }
         }
      }

      private boolean isSuspendThreadBreakpoint(IJavaMethodBreakpoint javaBreakpoint)
            throws CoreException
      {
         String managedRunnerHelperClassName = ManagedRunnerHelper.class.getName();
         String suspendThreadMethodName = ManagedRunnerHelper.suspendThreadMethodName;

         // TODO: getTypeName returns a pattern. Do matching to the pattern,
         // because the pattern is not necessarily equal to the class name.
         return ((managedRunnerHelperClassName.equals(javaBreakpoint.getTypeName())) && suspendThreadMethodName
               .equals(javaBreakpoint.getMethodName()));
      }
   }

   private class WorkflowEventCallback
   {
      public void performedTransition(TransitionTokenDigest details)
      {
         HighlightManager.getDefault().setHighlightState(
               details,
               details.isConsumed()
                     ? HighlightState.DONE_LITERAL
                     : HighlightState.ACTIVE_LITERAL);

         transTokens.add(details);
         trace.info(MessageFormat.format(Debug_Messages.CWM_DEBUG_MSG_Performed,
               new Object[] {details}));
      }

      public void startedActivityInstance(ActivityInstanceDigest details)
      {
         HighlightManager.getDefault().setHighlightState(details,
               HighlightState.ACTIVE_LITERAL);

         worklistManager.add(details);
         trace.info(MessageFormat.format(Debug_Messages.CWM_DEBUG_MSG_Started,
               new Object[] {details}));
      }

      public void completedActivityInstance(ActivityInstanceDigest details)
      {
         HighlightManager.getDefault().setHighlightState(details,
               HighlightState.DONE_LITERAL);

         worklistManager.remove(details);
         trace.info(MessageFormat.format(Debug_Messages.CWM_DEBUG_MSG_Completed,
               new Object[] {details}));
      }

      public void appendedToWorklist(ActivityInstanceDigest details)
      {
//         worklistManager.add(details);
      }
   }

   public static class WorklistManager
   {
      private Set worklist = new HashSet();
      private Set views = new HashSet();
      private CWMDebugTarget target;

      public WorklistManager(CWMDebugTarget target)
      {
         this.target = target;
      }

      private void reset()
      {
         worklist.clear();
         refreshViews();
         views.clear();
      }

      public void set(List result)
      {
         worklist.clear();
         worklist.addAll(result);
         refreshViews();
      }

      private void refreshViews()
      {
         for (Iterator i = views.iterator(); i.hasNext();)
         {
            WorklistView view = (WorklistView) i.next();
            view.refresh(this);
         }
      }

      public void add(ActivityInstanceDigest activityInstance)
      {
         if (activityInstance.isInteractive())
         {
            worklist.remove(activityInstance);
            worklist.add(activityInstance);
            refreshViews();
         }
      }

      public void remove(ActivityInstanceDigest activityInstance)
      {
         if (worklist.remove(activityInstance))
         {
            refreshViews();
         }
      }

      public Iterator getWorklist()
      {
         return worklist.iterator();
      }

      public void addWorklistView(WorklistView view)
      {
         views.add(view);
         view.refresh(this);
      }

      public void removeWorklistView(WorklistView view)
      {
         views.remove(view);
      }

      public CWMDebugTarget getTarget()
      {
         return target;
      }
   }

   public IWorkflowModelEditor getEditor()
   {
      return editor;
   }

   public boolean isAnalystMode()
   {
      return analystMode;
   }

   public WorklistManager getWorklistManager()
   {
      return worklistManager;
   }
}
