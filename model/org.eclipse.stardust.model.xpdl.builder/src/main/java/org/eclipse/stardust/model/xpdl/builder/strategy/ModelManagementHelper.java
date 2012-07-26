/*******************************************************************************
 * Copyright (c) 2011, 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class ModelManagementHelper
{

   private static ModelManagementHelper instance = null;

   private ModelManagementStrategy strategy;

   public ModelManagementHelper()
   {
      super();
   }

   public static synchronized ModelManagementHelper getInstance()
   {
      if (instance == null)
      {
         instance = new ModelManagementHelper();
      }
      return instance;
   }

   public synchronized void setModelManagementStrategy(ModelManagementStrategy strategy)
   {
      this.strategy = strategy;
   }

   public synchronized ModelManagementStrategy getModelManagementStrategy()   
   {
      if (strategy == null) {
        //Default Strategy?
      }
      return strategy;
   }

   public synchronized ModelType loadModel(String id)
   {
      return strategy.loadModel(id);
   }

}
