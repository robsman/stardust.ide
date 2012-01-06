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
import java.util.List;

import org.eclipse.stardust.engine.core.persistence.jdbc.SessionFactory;
import org.eclipse.stardust.engine.core.runtime.beans.InvocationManager;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.CallingInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.DebugInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.LoginInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.POJOExceptionHandler;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.PropertyLayerProviderInterceptor;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugInvocationManager}. 
 * 
 * @author sborn
 * @version $Revision$
 */
public class DebugInvocationManager extends InvocationManager
{
   /**
    * to make eclipse happy
    */
   private static final long serialVersionUID = 1L;

   public DebugInvocationManager(Object service)
   {
      super(service, setupInterceptors());
   }

   private static List setupInterceptors()
   {
      List interceptors = new ArrayList();
      
      interceptors.add(new DebugInterceptor());
      interceptors.add(new PropertyLayerProviderInterceptor());
      interceptors.add(new DebugForkingInterceptor());
      interceptors.add(new DebugSessionInterceptor(SessionFactory.AUDIT_TRAIL));
      interceptors.add(new LoginInterceptor());
      interceptors.add(new POJOExceptionHandler());
      interceptors.add(new CallingInterceptor());
      
      return interceptors;
   }
}