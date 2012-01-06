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
import java.util.Iterator;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.javascript.AbstractStructuredDataAccessPointAdapter;
import org.eclipse.stardust.engine.core.javascript.StructuredDataListAccessor;
import org.eclipse.stardust.engine.core.javascript.StructuredDataMapAccessor;
import org.eclipse.stardust.engine.extensions.transformation.runtime.transformation.MessageTransformationScope;
import org.eclipse.stardust.modeling.transformation.debug.common.CommonConstants;
import org.eclipse.stardust.modeling.transformation.debug.debugger.RhinoDebugFrame;

import com.infinity.bpm.thirdparty.org.mozilla.javascript.Context;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.ContextAction;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.Scriptable;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.ScriptableObject;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.debug.DebuggableScript;

public class JsStackFrame extends JsDebugElement implements IStackFrame
{
   private JsThread thread;
   private RhinoDebugFrame debugFrame;

   /**
    * Constructs a stack frame in the given thread with the given frame data.
    * 
    * @param thread
    * @param dataValues
    *           frame data
    */
   public JsStackFrame(JsThread thread, RhinoDebugFrame debugFrame)
   {
      super((JsDebugTarget) thread.getDebugTarget());
      this.thread = thread;
      this.debugFrame = debugFrame;
   }
   
   public RhinoDebugFrame getDebugFrame()
   {
      return debugFrame;
   }
   
   public IThread getThread()
   {
      return thread;
   }
   
   public IVariable[] getVariables()
   {
      return (IVariable[]) Context.call(new ContextAction()
      {
         public Object run(Context arg0)
         {
            try
            {
               return getVariablesActionImpl();
            }
            catch (Exception e)
            {
               e.printStackTrace();
               return JsVariable.NO_VARS;
            }
         }
      });
   }

   public boolean hasVariables() throws DebugException
   {
      return getVariables().length > 0;
   }

   public int getLineNumber() throws DebugException
   {
      return debugFrame.getCurrentLine();
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
      String name = debugFrame.getDebuggableScript().getFunctionName();
      if (StringUtils.isEmpty(name))
      {
         name = "Script entry";
      }

      name = name + " [line: " + getLineNumber() + "]";

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
      return thread.canStepInto();
   }

   public boolean canStepOver()
   {
      return thread.canStepOver();
   }

   public boolean canStepReturn()
   {
      return thread.canStepReturn();
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
      // TODO
      return super.getAdapter(adapter);
   }
   
   @Override
   public boolean equals(Object obj)
   {
      if (null == obj)
      {
         return false;
      }

      if (this == obj)
      {
         return true;
      }

      if (obj instanceof JsStackFrame)
      {
         JsStackFrame stackFrame = (JsStackFrame) obj;

         try
         {
            return (getDebugTarget() == stackFrame.getDebugTarget()
                  && getName().equals(stackFrame.getName()) && getLineNumber() == stackFrame
                  .getLineNumber());
         }
         catch (DebugException e)
         {
            e.printStackTrace();
            return false;
         }
      }

      return false;
   }

   private IVariable[] getVariablesActionImpl() throws DebugException
   {
      List vars = new ArrayList();
   
      if (debugFrame.getThisObj() != null)
      {
         vars.add(new JsVariable(this, null, "this", debugFrame.getThisObj()));
      }
   
      Scriptable scope = debugFrame.getScope();
      DebuggableScript debuggableScript = debugFrame.getDebuggableScript();
      if (scope != null && debuggableScript != null)
      {
         for (int i = 0; i < debuggableScript.getParamAndVarCount(); i++)
         {
            String name = debuggableScript.getParamOrVarName(i);
   
            Object rawValue = ScriptableObject.getProperty(scope, name);
   
            if (JsValue.isValidJsValue(rawValue))
            {
               if (rawValue instanceof AbstractStructuredDataAccessPointAdapter)
               {
                  addStructDataVariable(vars, name,
                        (AbstractStructuredDataAccessPointAdapter) rawValue);
               }
               else
               {
                  vars.add(new JsVariable(this, null, name, rawValue));
               }
            }
         }
         
         if (scope instanceof MessageTransformationScope)
         {
            MessageTransformationScope mtScope = (MessageTransformationScope) scope;
   
            List messageAdapters = new ArrayList(mtScope.getInputMessagAdapters()
                  .keySet());
            messageAdapters.addAll(mtScope.getOutputMessagAdapters().keySet());
   
            for (Iterator iterator = messageAdapters.iterator(); iterator.hasNext();)
            {
               String name = (String) iterator.next();
   
               AbstractStructuredDataAccessPointAdapter rawValue = (AbstractStructuredDataAccessPointAdapter) ScriptableObject
                     .getProperty(scope, name);
   
               if (JsValue.isValidJsValue(rawValue))
               {
                  addStructDataVariable(vars, name, rawValue);
               }
            }
         }
      }
      
      if (CommonConstants.NO_THROWABLE != thread.getSuspendedByThrowable())
      {
         IStackFrame[] stackFrames = thread.getStackFrames();
         if (this.equals(stackFrames[0]))
         {
            vars.add(new JsExceptionVariable(this, thread.getSuspendedByThrowable()));
         }
      }
   
      if (vars.size() == 0)
      {
         return new IVariable[0];
      }
   
      return (IVariable[]) vars.toArray(new IVariable[vars.size()]);
   }

   /**
    * @param vars
    * @param name
    * @param rawValue
    */
   private void addStructDataVariable(List vars, String name,
         AbstractStructuredDataAccessPointAdapter rawValue)
   {
      if (rawValue instanceof StructuredDataMapAccessor)
      {
         vars.add(new StructuredDataMapVariable(this, null, name, 
               (StructuredDataMapAccessor) rawValue));
      }
      else
      {
         vars.add(new StructuredDataListVariable(this, null, name,
               (StructuredDataListAccessor) rawValue));
      }
   }
}
