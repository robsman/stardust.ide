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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.engine.core.runtime.beans.ActionCarrier;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingService;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingServiceFactory;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.SecurityProperties;
import org.eclipse.stardust.engine.core.runtime.interceptor.MethodInterceptor;
import org.eclipse.stardust.engine.core.runtime.interceptor.MethodInvocation;
import org.eclipse.stardust.engine.core.runtime.removethis.EngineProperties;
import org.eclipse.stardust.modeling.debug.Constants;

public class DebugForkingInterceptor implements MethodInterceptor
{
   /**
    * to make eclipse happy 
    */
   private static final long serialVersionUID = 1L;

   public DebugForkingInterceptor()
   {
   }

   public Object invoke(MethodInvocation invocation) throws Throwable
   {
      List forkList = new ArrayList(5);

      Map locals = new HashMap();
      locals.put(EngineProperties.FORK_LIST, forkList);

      ParametersFacade.pushLayer(locals);
      try
      {
         Object result = invocation.proceed();
   
         if ( !forkList.isEmpty())
         {
            for (Iterator i = forkList.iterator(); i.hasNext();)
            {
               ActionCarrier order = (ActionCarrier) i.next();
   
               ForkingServiceFactory factory = (ForkingServiceFactory) invocation.getParameters()
                     .get(EngineProperties.FORKING_SERVICE_HOME);
               ForkingService service = factory.get();
               
               boolean isActivityThread = order instanceof ActivityThreadCarrier;
               
               Runnable runnable = new ManagedRunner(SecurityProperties.getUser(),
                     order.createAction(), service, isActivityThread);
               
               ThreadGroup group = (ThreadGroup) Parameters.instance().get(
                     isActivityThread 
                           ? Constants.THREAD_GROUP_ACTIVITY_THREAD_PARAM
                           : Constants.THREAD_GROUP_HELPER_THREAD_PARAM);
               
               new Thread(group, runnable).start();
            }
         }
         
         return result;
      }
      finally
      {
         ParametersFacade.popLayer();
      }
   }
}
