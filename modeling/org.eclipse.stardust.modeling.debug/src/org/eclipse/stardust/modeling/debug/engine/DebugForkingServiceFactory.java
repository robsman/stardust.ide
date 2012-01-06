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

import org.eclipse.stardust.common.rt.IJobManager;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingService;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingServiceFactory;
import org.eclipse.stardust.engine.core.runtime.beans.ForkingServiceJobManager;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugForkingServiceFactory}.
 * 
 * @author sborn
 * @version $Revision$
 */
public class DebugForkingServiceFactory implements ForkingServiceFactory
{
   public DebugForkingServiceFactory()
   {
   }
   
   public ForkingService get()
   {
      return new DebugForkingService();
   }

   public IJobManager getJobManager()
   {
      return new ForkingServiceJobManager(get());
   }

   public void release(ForkingService service)
   {
   }

   public void release(IJobManager jobManager)
   {
      if (jobManager instanceof ForkingServiceJobManager)
      {
         release(((ForkingServiceJobManager) jobManager).getForkingService());
      }
   }
   
}
