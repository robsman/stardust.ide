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
package org.eclipse.stardust.modeling.modelimport.elements;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;


public class Root
{
   private ModelType model;
   
   private ConnectionManager manager;

   public Root(WorkflowModelManager wfm)
   {
      model = wfm.getModel();
      if (wfm instanceof ExtendedModelManager)
      {
         manager = ((ExtendedModelManager) wfm).getConnectionManager();
      }
      else
      {
         manager = new ConnectionManager(model);
         manager.resolve();
      }
   }

   public ModelType getModel()
   {
      return model;
   }

   public ConnectionManager getConnectionManager()
   {
      return manager;
   }
}
