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

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jdt.debug.core.IJavaThread;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.jdt.debug.eval.IAstEvaluationEngine;
import org.eclipse.jdt.debug.eval.IEvaluationListener;
import org.eclipse.jdt.debug.eval.IEvaluationResult;
import org.eclipse.jdt.internal.debug.core.JDIDebugPlugin;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.eclipse.jdt.internal.debug.ui.actions.ActionMessages;
import org.eclipse.jdt.internal.debug.ui.actions.EvaluateAction;
import org.eclipse.jdt.internal.debug.ui.actions.ExpressionInputDialog;
import org.eclipse.jdt.internal.debug.ui.actions.StringValueInputDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.modeling.debug.CWMExpressionInputDialog;
import org.eclipse.stardust.modeling.debug.launching.LaunchConfigUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.InvocationException;
import com.sun.jdi.ObjectReference;

public class CWMVariableValueEditor implements IWMVariableValueEditor
{

   /* (non-Javadoc)
    * @see org.eclipse.debug.ui.actions.IVariableValueEditor#editVariable(org.eclipse.debug.core.model.IVariable, org.eclipse.swt.widgets.Shell)
    */
   /* (non-Javadoc)
    * @see org.eclipse.stardust.modeling.debug.model.IWMVariableValueEditor#editVariable(org.eclipse.debug.core.model.IVariable, org.eclipse.swt.widgets.Shell)
    */
   public boolean editVariable(IVariable variable, Shell shell) {
       try {
           IJavaVariable javaVariable = (IJavaVariable) variable;
           String signature = javaVariable.getSignature();
           if ("Ljava/lang/String;".equals(signature)) { //$NON-NLS-1$
               StringValueInputDialog dialog= new CWMStringValueInputDialog(shell, javaVariable);
               if (dialog.open() == Window.OK) {
                   String result = dialog.getResult();
                   if (dialog.isUseLiteralValue()) {
                      variable.setValue(result);
                   } else {
                       setValue(variable, result);
                   }
               }
           } else {
               ExpressionInputDialog dialog= new CWMExpressionInputDialog(shell, javaVariable);
               if (dialog.open() == Window.OK) {
                   String result = dialog.getResult();
                   setValue(variable, result);
               }
           }
       } catch (DebugException e) {
           handleException(e);
       }
       return true;
   }

   /* (non-Javadoc)
    * @see org.eclipse.debug.ui.actions.IVariableValueEditor#saveVariable(org.eclipse.debug.core.model.IVariable, java.lang.String, org.eclipse.swt.widgets.Shell)
    */
   /* (non-Javadoc)
    * @see org.eclipse.stardust.modeling.debug.model.IWMVariableValueEditor#saveVariable(org.eclipse.debug.core.model.IVariable, java.lang.String, org.eclipse.swt.widgets.Shell)
    */
   public boolean saveVariable(IVariable variable, String expression, Shell shell) {
       IJavaVariable javaVariable = (IJavaVariable) variable;
       String signature= null;
       try {
           signature = javaVariable.getSignature();
          if ("Ljava/lang/String;".equals(signature)) { //$NON-NLS-1$
              return false;
          }
          setValue(variable, expression);
       } catch (DebugException e) {
           handleException(e);
       }
       return true;
   }

   /**
    * Evaluates the given expression and sets the given variable's value
    * using the result.
    *
    * @param variable the variable whose value should be set
    * @param expression the expression to evaluate
    * @throws DebugException if an exception occurs evaluating the expression
    *  or setting the variable's value
    */
   protected void setValue(final IVariable variable, final String expression) throws DebugException {
       IProgressService service= PlatformUI.getWorkbench().getProgressService();

       IRunnableWithProgress runnable = new IRunnableWithProgress() {
           public void run(IProgressMonitor monitor) throws InvocationTargetException {
               try {
               IValue newValue = evaluate(expression);
               if (newValue != null) {
                   variable.setValue(newValue);
               }
               } catch (DebugException de) {
                   throw new InvocationTargetException(de);
               }
           }
       };

       try {
           service.busyCursorWhile(runnable);
       } catch (InvocationTargetException e) {
        if (e.getTargetException() instanceof DebugException) {
               throw (DebugException)e.getTargetException();
           }
       } catch (InterruptedException e) {
       }
   }

   /**
    * Handles the given exception, which occurred during edit/save.
    */
   protected void handleException(DebugException e) {
       Throwable cause = e.getStatus().getException();
       if (cause instanceof InvalidTypeException) {
           IStatus status = new Status(IStatus.ERROR, JDIDebugUIPlugin.getUniqueIdentifier(), IDebugUIConstants.INTERNAL_ERROR, cause.getMessage(), null);
           JDIDebugUIPlugin.statusDialog(ActionMessages.JavaObjectValueEditor_3, status);
       } else {
           JDIDebugUIPlugin.statusDialog(e.getStatus());
       }
   }

   /**
    * Evaluates the given snippet. Reports any errors to the user.
    * @param stringValue the snippet to evaluate
    * @return the value that was computed or <code>null</code> if any errors occurred.
    */
   private IValue evaluate(String stringValue) throws DebugException {
       IAdaptable adaptable = DebugUITools.getDebugContext();
       IJavaStackFrame frame= (IJavaStackFrame) adaptable.getAdapter(IJavaStackFrame.class);
       return evaluate2(frame, stringValue);
   }

   /* (non-Javadoc)
    * @see org.eclipse.stardust.modeling.debug.model.IWMVariableValueEditor#evaluate2(org.eclipse.jdt.debug.core.IJavaStackFrame, java.lang.String)
    */
   public IValue evaluate2(IJavaStackFrame frame, String stringValue)
         throws DebugException
   {
      if (frame != null) {
           IJavaThread thread = (IJavaThread) frame.getThread();
           IJavaProject project= getProject(frame);
           if (project != null) {
               final IEvaluationResult[] results= new IEvaluationResult[1];
               IAstEvaluationEngine engine = JDIDebugPlugin.getDefault().getEvaluationEngine(project, (IJavaDebugTarget) thread.getDebugTarget());
               IEvaluationListener listener= new IEvaluationListener() {
                   public void evaluationComplete(IEvaluationResult result) {
                       synchronized (CWMVariableValueEditor.this) {
                           results[0]= result;
                           CWMVariableValueEditor.this.notifyAll();
                       }
                   }
               };
           synchronized(this) {
                   engine.evaluate(stringValue, frame, listener, DebugEvent.EVALUATION_IMPLICIT, false);
              try {
                 this.wait();
              } catch (InterruptedException e) {
              }
           }
           IEvaluationResult result= results[0];
           if (result == null) {
               return null;
           }
           if (result.hasErrors()) {
               DebugException exception = result.getException();
               StringBuffer buffer = new StringBuffer();
               if (exception == null) {
                  String[] messages = result.getErrorMessages();
                  for (int i = 0; i < messages.length; i++) {
                           buffer.append(messages[i]).append("\n "); //$NON-NLS-1$
                       }
               } else {
                 buffer.append(EvaluateAction.getExceptionMessage(exception));
               }
               IStatus status= new Status(IStatus.ERROR, JDIDebugUIPlugin.getUniqueIdentifier(), IStatus.ERROR, buffer.toString(), null);
               throw new DebugException(status);
           }
           return result.getValue();
           }
       }
       return null;
   }

   /**
    * Copied from {@link org.eclipse.jdt.internal.debug.ui.actions.EvaluateAction#getExceptionMessage(Throwable)}
    */
  protected String getExceptionMessage(Throwable exception) {
     if (exception instanceof CoreException) {
        CoreException ce = (CoreException)exception;
        Throwable throwable= ce.getStatus().getException();
        if (throwable instanceof com.sun.jdi.InvocationException) {
           return getInvocationExceptionMessage((com.sun.jdi.InvocationException)throwable);
        } else if (throwable instanceof CoreException) {
           // Traverse nested CoreExceptions
           return getExceptionMessage(throwable);
        }
        return ce.getStatus().getMessage();
     }
     String message= MessageFormat.format(ActionMessages.Evaluate_error_message_direct_exception, new Object[] { exception.getClass() });
     if (exception.getMessage() != null) {
        message= MessageFormat.format(ActionMessages.Evaluate_error_message_exception_pattern, new Object[] { message, exception.getMessage() });
     }
     return message;
  }

  /**
   * Returns a message for the exception wrapped in an invocation exception
   */
  protected String getInvocationExceptionMessage(com.sun.jdi.InvocationException exception) {
        InvocationException ie= exception;
        ObjectReference ref= ie.exception();
        return MessageFormat.format(ActionMessages.Evaluate_error_message_wrapped_exception, new Object[] { ref.referenceType().name() });
  }

   /**
    * Return the project associated with the given stack frame. (copied from
    * {@link org.eclipse.jdt.internal.debug.ui.JavaWatchExpressionDelegate#getProject(IJavaStackFrame)}
    * , but still version eclipse32. This is necessary since otherwise with version
    * eclipse33 java project was not found).
    */
  private IJavaProject getProject(IJavaStackFrame javaStackFrame) {
     ILaunch launch = javaStackFrame.getLaunch();
     if (launch == null) {
        return null;
     }

     //-------------------- start: eclipse32 code
     ILaunchConfiguration launchConfig = launch.getLaunchConfiguration();
     String projectName;
     try
     {
        projectName = LaunchConfigUtils.getProjectName(launchConfig);
     }
     catch (CoreException e)
     {
        e.printStackTrace();
        return null;
     }

     if (null != projectName)
     {
        return getJavaProject(projectName);
     }
     //-------------------- end: eclipse32 code

     ISourceLocator locator= launch.getSourceLocator();
     if (locator == null) {
        return null;
     }

     Object sourceElement = locator.getSourceElement(javaStackFrame);
     if (!(sourceElement instanceof IJavaElement) && sourceElement instanceof IAdaptable) {
        sourceElement = ((IAdaptable)sourceElement).getAdapter(IJavaElement.class);
     }
     if (sourceElement instanceof IJavaElement) {
        return ((IJavaElement) sourceElement).getJavaProject();
     }
     return null;
  }

  private IJavaProject getJavaProject(String name)
  {
     IJavaProject javaProject = null;
     IWorkspaceRoot wspRoot = ResourcesPlugin.getWorkspace().getRoot();

     if (null != wspRoot)
     {
        IProject rawProject = wspRoot.getProject(name);
        if (null != rawProject)
        {
           javaProject = JavaCore.create(rawProject);
        }
     }

     return javaProject;
  }
}
