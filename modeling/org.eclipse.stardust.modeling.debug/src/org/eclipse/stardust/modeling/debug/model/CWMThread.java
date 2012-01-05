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
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.ApplicationDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DataMappingDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.NamedValue;
import org.eclipse.stardust.modeling.debug.debugger.types.ProcessInstanceDigest;
import org.eclipse.stardust.modeling.debug.util.CollectionUtils;

public class CWMThread extends CWMDebugElement implements IThread 
{
   private static final Logger trace = LogManager.getLogger(CWMThread.class);
   
   private IBreakpoint[] breakpoints;
   private IThread javaThread;
   private ActivityInstanceDigest currentActivityInstance;
   private ProcessInstanceDigest rootProcessInstance;
   private boolean isCompletionThread = false;
   
   // communication debugging variable
   private IVariable reloadValuesVariable = null;
   
   public CWMThread(CWMDebugTarget target, IThread javaThread)
   {
      this(target, javaThread, false);
   }

   public CWMThread(CWMDebugTarget target, IThread javaThread, boolean isCompletionThread)
   {
      super(target);
      
      this.javaThread = javaThread;
      this.isCompletionThread = isCompletionThread;
      currentActivityInstance = null;

      trace.info(MessageFormat.format(
            Debug_Messages.CWMThread_MSG_CreateNewWorkflowThread, this, javaThread));
   }

   public IStackFrame[] getStackFrames() throws DebugException
   {
      List frameList = new ArrayList();
      List javaFrameList = Arrays.asList(javaThread.getStackFrames());
      final int maxFrameDepth = getMaxJavaFrameDepth();
      
      if (isCompletionThread())
      {
         IStackFrame javaStackFrame = javaFrameList.size() != 0
               ? (IStackFrame) javaFrameList.get(maxFrameDepth + 1)
               : null;
         frameList.add(new CWMStackFrame(this, rootProcessInstance, javaStackFrame));
      }
      else
      {
         Predicate predicate = null;
         if (null != currentActivityInstance)
         {
            predicate = new Predicate()
            {
               private int frameDepthCounter = 0;
               
               public boolean accept(Object o)
               {
                  if (frameDepthCounter <= maxFrameDepth)
                  {
                     ++frameDepthCounter;
                     return true;
                  }
                  
                  return false;
               }
            };
         }
         
         CollectionUtils.copy(frameList, javaFrameList, predicate);
         
         ActivityInstanceDigest activityInstance = currentActivityInstance;
         while (null != activityInstance)
         {
            IStackFrame javaStackFrame = javaFrameList.size() != 0
                  ? (IStackFrame) javaFrameList.get(maxFrameDepth + 1)
                  : null;
            frameList.add(new CWMStackFrame(this, activityInstance, javaStackFrame));
            activityInstance = activityInstance.getProcessInstance()
                  .getStartingActivityInstance();
         }
      }
      
      return (IStackFrame[]) frameList.toArray(new IStackFrame[frameList.size()]);
   }

   public boolean hasStackFrames() throws DebugException
   {
      return isSuspended();
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
      String message;
      
      if (isCompletionThread())
      {
         if (null != rootProcessInstance)
         {
            message = MessageFormat.format(
                  Debug_Messages.CWMThread_MSG_RootProcessInstance,
                  rootProcessInstance.getProcDefId());
         }
         else
         {
            message = MessageFormat.format(
                  Debug_Messages.CWMThread_MSG_NoRootProcessInstance,
                  javaThread.getName());
         }
      }
      else if (null != currentActivityInstance)
      {
         message = MessageFormat.format(Debug_Messages.CWMThread_MSG_Activity,
                  currentActivityInstance.getActivityId(),
                  currentActivityInstance.getProcessDefinitionId());
      }
      else
      {
         message = MessageFormat.format(
               Debug_Messages.CWMThread_MSG_NoCurrentActivity,
               javaThread.getName());
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
      return javaThread.isSuspended();
   }

   public void resume() throws DebugException
   {
      if (null != currentActivityInstance)
      {
         UiAccessor.activateDiagramForProcess((CWMDebugTarget) getDebugTarget(),
               currentActivityInstance.getProcessInstance().getProcDefId());
      }

      if ( !reloadValues())
      {
         if (false == performManualApplication())
         {
            return;
         }
      }

      javaThread.resume();
   }

   public void suspend() throws DebugException
   {
      javaThread.suspend();
   }

   public boolean canStepInto()
   {
      return false;
   }

   public boolean canStepOver()
   {
      return false;
   }

   public boolean canStepReturn()
   {
      return false;
   }

   public boolean isStepping()
   {
      return javaThread.isStepping();
   }

   public void stepInto() throws DebugException
   {
      if (0 <= getMaxJavaFrameDepth())
      {
         javaThread.stepInto();
      }
      else
      {
         javaThread.resume();
      }
   }

   public void stepOver() throws DebugException
   {
      if (0 <= getMaxJavaFrameDepth())
      {
         javaThread.stepOver();
      }
      else
      {
         javaThread.resume();
      }
   }

   public void stepReturn() throws DebugException
   {
      if (0 <= getMaxJavaFrameDepth())
      {
         javaThread.stepReturn();
      }
      else
      {
         javaThread.resume();
      }
   }

   public boolean canTerminate()
   {
      return !isTerminated();
   }

   public boolean isTerminated()
   {
      return javaThread.isTerminated();
   }

   public void terminate() throws DebugException
   {
      javaThread.terminate();
      fireTerminateEvent();
   }

   public void fireResumeEvent(int detail)
   {
      super.fireResumeEvent(detail);
   }

   public void fireSuspendEvent(int detail)
   {
      super.fireSuspendEvent(detail);
   }

   /**
    * @return Returns the currentActivityInstance.
    */
   public ActivityInstanceDigest getCurrentActivityInstance()
   {
      return currentActivityInstance;
   }

   /**
    * @param currentActivityInstance The currentActivityInstance to set.
    */
   public void setCurrentActivityInstance(ActivityInstanceDigest currentActivityInstance)
   {
      trace.info(MessageFormat.format(
            Debug_Messages.CWMThread_MSG_SetCurrentActivityInstance, new Object[] {
                  currentActivityInstance, this }));
      this.currentActivityInstance = currentActivityInstance;
   }

   public ProcessInstanceDigest getRootProcessInstance()
   {
      return rootProcessInstance;
   }

   public void setRootProcessInstance(ProcessInstanceDigest rootProcessInstance)
   {
      this.rootProcessInstance = rootProcessInstance;
   }

   public boolean isCompletionThread()
   {
      return isCompletionThread;
   }

   public IVariable getReloadValuesVariable()
   {
      return reloadValuesVariable;
   }

   public void setReloadValuesVariable(IVariable reloadValues)
   {
      this.reloadValuesVariable = reloadValues;
   }

   public IThread getJavaThread()
   {
      return javaThread;
   }
   
   private boolean reloadValues()
   {
      try
      {
         if (null != reloadValuesVariable)
         {
            return Boolean.valueOf(reloadValuesVariable.getValue().getValueString()).booleanValue();
         }
      }
      catch (DebugException e)
      {
         e.printStackTrace();
      }
      
      return false;
   }

   private int getMaxJavaFrameDepth()
   {
      int maxFrameDepth = -1;
      ApplicationDigest application = (null != currentActivityInstance)
            ? currentActivityInstance.getApplication()
            : null;
   
      if (null != application && !isCompletionThread())
      {
         NamedValue[] properties = application.getProperties();
         String entryTypeName = null;
   
         for (int idx = 0; idx < properties.length; ++idx)
         {
            if ("carnot:engine:className".equals(properties[idx].getName())) //$NON-NLS-1$
            {
               entryTypeName = properties[idx].getValue();
               break;
            }
         }
   
         if (StringUtils.isEmpty(entryTypeName))
         {
            return maxFrameDepth;
         }
   
         try
         {
            IStackFrame[] javaStackFrames = javaThread.getStackFrames();
            for (int idx = javaStackFrames.length - 1; idx > -1; --idx)
            {
               IJavaStackFrame javaStackFrame = (IJavaStackFrame) javaStackFrames[idx];
   
               if (javaStackFrame.getDeclaringTypeName().equals(entryTypeName))
               {
                  maxFrameDepth = idx;
                  break;
               }
            }
         }
         catch (DebugException e)
         {
            throw new InternalException(e);
         }
      }
   
      return maxFrameDepth;
   }

   private boolean performManualApplication() throws DebugException
   {
      if (null != currentActivityInstance
            && ActivityInstanceDigest.IMPL_TYPE_MANUAL.equals(currentActivityInstance
                  .getImplementationTypeId()))
      {
         DataMappingDigest[] dataMappings = currentActivityInstance.getDataMappings();

         List dataMappingVariables = new ArrayList();
         for (int idx = 0; idx < dataMappings.length; ++idx)
         {
            dataMappingVariables.add(dataMappings[idx]);
         }

         int result = UiAccessor.openManualActivityDialog(currentActivityInstance,
               (DataMappingDigest[]) dataMappingVariables
                     .toArray(new DataMappingDigest[dataMappingVariables.size()]));
         if (Window.CANCEL == result)
         {
            return false;
         }
      }

      return true;
   }
}
