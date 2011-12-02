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
package org.eclipse.stardust.modeling.debug.engine;

import org.eclipse.stardust.modeling.debug.debugger.Debugger;
import org.eclipse.stardust.modeling.debug.debugger.types.ProcessInstanceDigest;

import ag.carnot.workflow.runtime.ProcessInstanceState;
import ag.carnot.workflow.runtime.beans.IProcessInstance;

public class WorkflowCompletionWaiter implements Runnable
{
   private Debugger debugger;
   private IProcessInstance processInstance;

   private boolean isSuspended = true;

   public WorkflowCompletionWaiter(Debugger debugger, IProcessInstance processInstance)
   {
      this.debugger = debugger;
      this.processInstance = processInstance;
   }

   public void run()
   {
      do
      {
         ManagedRunnerHelper.suspendThread();
         isSuspended = (processInstance.getState() == ProcessInstanceState.Aborted)
               || (processInstance.getState() == ProcessInstanceState.Completed)
               ? false
               : true;
      }
      while (isSuspended);

      Runnable runnable = new WorkflowCompletionRunner();
      new Thread(runnable).start();
   }

   private class WorkflowCompletionRunner implements Runnable
   {
      public void run()
      {
         ProcessInstanceDigest pi = new ProcessInstanceDigest(processInstance);
         communicateWithSuspendedProcessInstance(pi);
         debugger.finish();
      }

      private void communicateWithSuspendedProcessInstance(
            ProcessInstanceDigest processInstance)
      {
         // Do not remove this method call! It suspends the current thread.
         ManagedRunnerHelper.suspendThread();
      }
   }
}