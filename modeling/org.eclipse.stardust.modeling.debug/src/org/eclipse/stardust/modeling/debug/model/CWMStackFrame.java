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

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DataMappingDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DataValueDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.ProcessInstanceDigest;


public class CWMStackFrame extends CWMDebugElement implements IStackFrame
{
   private CWMThread thread;
   private ActivityInstanceDigest activityInstance;
   private ProcessInstanceDigest processInstance;
   private IJavaVariable[] variables;
   private IStackFrame javaStackFrame; 

   /**
    * Constructs a stack frame in the given thread with the given frame data.
    * 
    * @param thread
    * @param dataValues
    *           frame data
    */
   public CWMStackFrame(CWMThread thread, ActivityInstanceDigest activityInstance,
         IStackFrame javaStackFrame)
   {
      this(thread, activityInstance, activityInstance.getProcessInstance(),
            javaStackFrame);
   }
   
   public CWMStackFrame(CWMThread thread, ProcessInstanceDigest processInstance,
         IStackFrame javaStackFrame)
   {
      this(thread, null, processInstance, javaStackFrame);
   }
   
   private CWMStackFrame(CWMThread thread, ActivityInstanceDigest activityInstance,
         ProcessInstanceDigest processInstance, IStackFrame javaStackFrame)
   {
      super((CWMDebugTarget) thread.getDebugTarget());
      this.thread = thread;
      this.activityInstance = activityInstance;
      this.processInstance = processInstance;
      this.javaStackFrame = javaStackFrame;

      initVariables();
   }
   
   public IStackFrame getJavaStackFrame()
   {
      return javaStackFrame;
   }

   public ActivityInstanceDigest getActivityInstance()
   {
      return activityInstance;
   }

   public IThread getThread()
   {
      return thread;
   }

   public IVariable[] getVariables() throws DebugException
   {
      return variables;
   }

   public boolean hasVariables() throws DebugException
   {
      return variables.length > 0;
   }

   public int getLineNumber() throws DebugException
   {
      return -1;
   }

   public int getCharStart() throws DebugException
   {
      return -1;
   }

   public int getCharEnd() throws DebugException
   {
      return -1;
   }

   public String getName() throws DebugException
   {
      String name = null;
      
      if (null != activityInstance)
      {
         name = MessageFormat.format(Debug_Messages.CWMStack_MSG_ActivityInstanceOID,
               activityInstance.getOid(),
               activityInstance.getActivityId(),
               activityInstance.getProcessDefinitionId());
      }
      else if (null != processInstance)
      {
         name = MessageFormat.format(Debug_Messages.MSG_ProcessOID,
               processInstance.getOid());
      }
      else
      {
         name = Debug_Messages.CWMStack_ActivityInstanceUnknown;
      }
      
      return name;
   }

   public IRegisterGroup[] getRegisterGroups() throws DebugException
   {
      return new IRegisterGroup[0];
   }

   public boolean hasRegisterGroups() throws DebugException
   {
      return false;
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
      return thread.isStepping();
   }

   public void stepInto() throws DebugException
   {
      thread.stepInto();
   }

   public void stepOver() throws DebugException
   {
      thread.stepOver();
   }

   public void stepReturn() throws DebugException
   {
      thread.stepReturn();
   }

   public boolean canResume()
   {
      return thread.canResume();
   }

   public boolean canSuspend()
   {
      return thread.canSuspend();
   }

   public boolean isSuspended()
   {
      return thread.isSuspended();
   }

   public void resume() throws DebugException
   {
      thread.resume();
   }

   public void suspend() throws DebugException
   {
      thread.suspend();
   }

   public boolean canTerminate()
   {
      return thread.canTerminate();
   }

   public boolean isTerminated()
   {
      return thread.isTerminated();
   }

   public void terminate() throws DebugException
   {
      thread.terminate();
   }
   
   public Object getAdapter(Class adapter)
   {
/*      try
      {
*/         if (IJavaStackFrame.class.equals(adapter))
         {
            return javaStackFrame; //new JavaStackFrameFacade(this);
         }
/*      }
      catch (DebugException e)
      {
         trace.warn(e);
         return null;
      }
*/      
      return super.getAdapter(adapter);
   }

   private void initVariables()
   {
      DataMappingDigest[] dataMappings = null;
      DataValueDigest[] dataValues = null;
   
      if (null != activityInstance)
      {
         dataMappings = activityInstance.getDataMappings();
      }
      
      if (null != processInstance)
      {
         dataValues = processInstance.getDataValues();
      }
   
      if (null == dataMappings)
      {
         dataMappings = new DataMappingDigest[0];
      }
   
      if (null == dataValues)
      {
         dataValues = new DataValueDigest[0];
      }
   
      variables = new IJavaVariable[dataMappings.length + dataValues.length];
      for (int i = 0; i < dataMappings.length; ++i)
      {
         // TODO: change return value type from IVariable to IJavaVariable?
         variables[i] = (IJavaVariable) dataMappings[i].getDataField()
               .getWritebackVariable();
         
         // TODO: this should be settable on creation of JavaVariableDecorator 
         if (variables[i] instanceof JavaVariableDecorator)
         {
            JavaVariableDecorator decorator = (JavaVariableDecorator) variables[i];
            decorator.setSupportsModification(dataMappings[i].supportsValueModification());
         }
      }
      int offset = dataMappings.length;
      for (int i = 0; i < dataValues.length; ++i)
      {
         // TODO: change return value type from IVariable to IJavaVariable?
         variables[offset + i] = (IJavaVariable) dataValues[i].getDataField()
               .getWritebackVariable();
         
         // TODO: this should be settable on creation of JavaVariableDecorator 
         if (variables[i] instanceof JavaVariableDecorator)
         {
            JavaVariableDecorator decorator = (JavaVariableDecorator) variables[i];
            decorator.setSupportsModification(dataValues[i].supportsValueModification());
         }
      }
   }
}
