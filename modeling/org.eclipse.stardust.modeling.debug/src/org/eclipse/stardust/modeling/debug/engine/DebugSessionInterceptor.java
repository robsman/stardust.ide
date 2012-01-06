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

import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.engine.core.persistence.Session;
import org.eclipse.stardust.engine.core.persistence.jdbc.SessionFactory;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.ManagedResource;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.POJOSessionInterceptor;
import org.eclipse.stardust.engine.core.runtime.interceptor.MethodInterceptor;
import org.eclipse.stardust.engine.core.runtime.interceptor.MethodInvocation;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugSessionInterceptor}. 
 *  
 * @author sborn
 * @version $Revision$
 */
public class DebugSessionInterceptor implements MethodInterceptor
{
   private static final long serialVersionUID = 1L;

   private final String sessionName;

   public DebugSessionInterceptor(String sessionName)
   {
      this.sessionName = sessionName;
   }

   public Object invoke(MethodInvocation invocation) throws Throwable
   {
      Session session = SessionFactory.getSession(sessionName);

      List managedResources = new ArrayList(2);
      
      Map locals = new HashMap();
      locals.put(POJOSessionInterceptor.RESOURCES, managedResources);

      ParametersFacade.pushLayer(locals);
      try
      {
         Object result = invocation.proceed();
         session.save();

         for (Iterator i = managedResources.iterator(); i.hasNext();)
         {
            ((ManagedResource) i.next()).commit();
         }

         return result;
      }
      catch (Exception e)
      {
         session.rollback();

         for (Iterator i = managedResources.iterator(); i.hasNext();)
         {
            ((ManagedResource) i.next()).rollback();
         }

         throw e;
      }
      finally
      {
         ParametersFacade.popLayer();
      }
   }
}
