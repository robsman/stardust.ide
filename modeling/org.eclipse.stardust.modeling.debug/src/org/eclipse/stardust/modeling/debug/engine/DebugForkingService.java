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

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.Action;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.rt.IActionCarrier;
import org.eclipse.stardust.engine.core.persistence.jdbc.SessionFactory;
import org.eclipse.stardust.engine.core.runtime.beans.ActionCarrier;
import org.eclipse.stardust.engine.core.runtime.beans.ActionRunner;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingService;
import org.eclipse.stardust.engine.core.runtime.beans.InvocationManager;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.CallingInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.DebugInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.POJOExceptionHandler;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.PropertyLayerProviderInterceptor;
import org.eclipse.stardust.engine.core.runtime.removethis.EngineProperties;
import org.eclipse.stardust.modeling.debug.Constants;

/**
 * This class is adapted from
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugForkingService}.
 * 
 * @author sborn
 * @version $Revision$
 */
public class DebugForkingService implements ForkingService
{
   private final ActionRunner isolator;

   public DebugForkingService()
   {
      MyInvocationManager manager = new MyInvocationManager(new MyActionRunner());
      this.isolator = (ActionRunner) Proxy.newProxyInstance(ActionRunner.class
            .getClassLoader(), new Class[] {ActionRunner.class}, manager);
   }

   public Object isolate(Action action)
   {
      return isolator.execute(action);
   }

   public void fork(IActionCarrier action, boolean transacted)
   {
      if (transacted)
      {
         List successors = (List) Parameters.instance().get(EngineProperties.FORK_LIST);
         successors.add(action);
      }
      else
      {
         boolean isActivityThread = action instanceof ActivityThreadCarrier;
         
         Runnable runnable = new ManagedRunner(action.createAction(),
               new DebugForkingService(), isActivityThread);

         ThreadGroup group = (ThreadGroup) Parameters.instance().get(
               isActivityThread 
                     ? Constants.THREAD_GROUP_ACTIVITY_THREAD_PARAM
                     : Constants.THREAD_GROUP_HELPER_THREAD_PARAM);
         
         new Thread(group, runnable).start();
      }
      
   }

   private static class MyInvocationManager extends InvocationManager
   {
      /**
       * to make eclipse happy
       */
      private static final long serialVersionUID = 1L;

      public MyInvocationManager(Object serviceInstance)
      {
         super(serviceInstance, setupInterceptors());
      }

      private static List setupInterceptors()
      {
         List interceptors = new ArrayList();
         
         interceptors.add(new DebugInterceptor());
         interceptors.add(new PropertyLayerProviderInterceptor());
         interceptors.add(new DebugForkingInterceptor());
         interceptors.add(new DebugSessionInterceptor(SessionFactory.AUDIT_TRAIL));
         interceptors.add(new POJOExceptionHandler());
         interceptors.add(new CallingInterceptor());
         
         return interceptors;
      }
   }

   private static class MyActionRunner implements ActionRunner
   {
      public Object execute(Action action)
      {
         return action.execute();
      }
   }
}
