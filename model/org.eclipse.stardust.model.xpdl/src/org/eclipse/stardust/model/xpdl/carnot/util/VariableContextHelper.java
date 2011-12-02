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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public class VariableContextHelper
{

   private static VariableContextHelper instance = null;

   private Map<ModelType, VariableContext> contextMap = new HashMap<ModelType, VariableContext>();

   public VariableContextHelper()
   {
      super();
   }

   public static synchronized VariableContextHelper getInstance()
   {
      if (instance == null)
      {
         instance = new VariableContextHelper();
      }
      return instance;
   }

   public synchronized void clear()
   {
      contextMap.clear();
   }

   public synchronized void createContext(ModelType modelType)
   {
      contextMap.put(modelType, new VariableContext());
   }

   public synchronized void removeContext(ModelType modelType)
   {
      contextMap.remove(modelType);
   }

   public synchronized VariableContext getContext(ModelType modelType)
   {
      return contextMap.get(modelType);
   }

   public synchronized VariableContext getContext(IModelElement element)
   {
      ModelType modelType = ModelUtils.findContainingModel(element);
      if (modelType != null)
      {
         VariableContext context = contextMap.get(modelType);
         // If this is a referenced model a corresponding context does not exist -->
         // create on the fly
         if (context == null)
         {
            createContext(modelType);
         }
         return contextMap.get(modelType);
      }
      return null;
   }

}
