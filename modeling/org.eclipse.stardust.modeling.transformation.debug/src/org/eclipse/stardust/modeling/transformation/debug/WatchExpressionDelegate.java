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
package org.eclipse.stardust.modeling.transformation.debug;

import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.core.model.IWatchExpressionDelegate;
import org.eclipse.debug.core.model.IWatchExpressionListener;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.stardust.engine.core.javascript.StructuredDataListAccessor;
import org.eclipse.stardust.engine.core.javascript.StructuredDataMapAccessor;
import org.eclipse.stardust.modeling.transformation.debug.model.JsStackFrame;
import org.eclipse.stardust.modeling.transformation.debug.model.JsValue;
import org.eclipse.stardust.modeling.transformation.debug.model.JsVariable;
import org.eclipse.stardust.modeling.transformation.debug.model.NullElementVariable;
import org.eclipse.stardust.modeling.transformation.debug.model.StructuredDataListVariable;
import org.eclipse.stardust.modeling.transformation.debug.model.StructuredDataMapVariable;

import com.infinity.bpm.thirdparty.org.mozilla.javascript.Context;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.ContextAction;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.Scriptable;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.debug.Debugger;

/**
 * Delegate class which is responsible for evaluation expression entered in the 
 * expressions view in context of the current selected stack frame.
 * 
 * @author born
 */
public class WatchExpressionDelegate implements IWatchExpressionDelegate
{
   
   public void evaluateExpression(final String expression, IDebugElement context,
         IWatchExpressionListener listener)
   {
      if (context instanceof JsStackFrame)
      {
         IWatchExpressionResult watchResult = evaluateExpression(expression,
               (JsStackFrame) context);
   
         listener.watchEvaluationFinished(watchResult);
      }
   
   }

   public static IWatchExpressionResult evaluateExpression(final String expression,
         final JsStackFrame frame)
   {
      return new JsWatchExpressionResult(frame, expression);
   }

   private static Object evaluateImpl(JsStackFrame frame, String expression,
         JsWatchExpressionResult watchExpressionResult)
   {
      Context jsContext = frame.getDebugFrame().getContext();
      Scriptable scope = frame.getDebugFrame().getScope();
   
      Debugger debugger = jsContext.getDebugger();
      if (null != debugger)
      {
         jsContext.setDebugger(null, null);
      }
   
      Object result = null;
      try
      {
         result = jsContext.evaluateString(scope, expression, expression, 1, null);
      }
      catch (RuntimeException x)
      {
         x.printStackTrace();
         watchExpressionResult.setException(new DebugException(new Status(Status.ERROR,
               PredefinedConstants.ID_KNITWARE_DEBUG_MODEL, x.getMessage(), x)));
      }
      finally
      {
         if (null != debugger)
         {
            jsContext.setDebugger(debugger, null);
         }
      }
   
      return result;
   }

   private static final class JsWatchExpressionResult implements IWatchExpressionResult
   {
      private final JsStackFrame frame;
      private final String expression;
      private boolean evaluated = false;
      private IValue value = null;
      private DebugException exception = null;
   
      private JsWatchExpressionResult(JsStackFrame frame, String expression)
      {
         this.frame = frame;
         this.expression = expression;
      }
   
      public IValue getValue()
      {
         if (evaluated)
         {
            return value;
         }
   
         try
         {
            if (null != frame)
            {
               final Object rawResult = Context.call(new ContextAction()
               {
                  public Object run(Context arg0)
                  {
                     try
                     {
                        return evaluateImpl(frame, expression,
                              JsWatchExpressionResult.this);
                     }
                     catch (Exception e)
                     {
                        e.printStackTrace();
                        return null;
                     }
                  }
               });
   
               IVariable variable = null;
               if (null == rawResult)
               {
                  variable = new NullElementVariable(frame, "xyz"); //$NON-NLS-1$
               }
               else if (rawResult instanceof StructuredDataMapAccessor)
               {
                  variable = new StructuredDataMapVariable(frame, null, "xyz", //$NON-NLS-1$
                        (StructuredDataMapAccessor) rawResult);
               }
               else if (rawResult instanceof StructuredDataListAccessor)
               {
                  variable = new StructuredDataListVariable(frame, null, "xyz", //$NON-NLS-1$
                        (StructuredDataListAccessor) rawResult);
               }
               else if (JsValue.isValidJsValue(rawResult))
               {
                  final boolean isPrimitive = rawResult.getClass().isPrimitive();
                  variable = new JsVariable(frame, null, "xyz", rawResult, isPrimitive); //$NON-NLS-1$
               }
   
               if (null != variable)
               {
                  try
                  {
                     value = variable.getValue();
                  }
                  catch (DebugException e)
                  {
                     exception = e;
                  }
               }
            }
         }
         finally
         {
            evaluated = true;
         }
         return value;
      }
   
      public boolean hasErrors()
      {
         return null != exception || null == getValue();
      }
   
      public String[] getErrorMessages()
      {
         if (hasErrors())
         {
            if (null != exception)
            {
               return new String[] { exception.getMessage() };
            }
            else
            {
               return new String[] { "(Watch expression not supported)" //$NON-NLS-1$
               };
            }
         }
         else
         {
            return new String[0];
         }
      }
   
      public String getExpressionText()
      {
         return expression;
      }
   
      public DebugException getException()
      {
         return exception;
      }
      
      private void setException(DebugException exception)
      {
         this.exception = exception;
      }
   }

}
