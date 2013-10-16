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

import org.eclipse.stardust.engine.core.preferences.configurationvariables.ConfigurationVariableScope;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class VariableContextHelper
{

   private static VariableContextHelper instance = null;

   private Map<String, VariableContext> contextMap = new HashMap<String, VariableContext>();

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
      if (modelType != null)
      {
         contextMap.put(modelType.getId(), new VariableContext());
      }
   }

   public synchronized void removeContext(ModelType modelType)
   {
      if (modelType != null)
      {
         contextMap.remove(modelType.getId());
      }
   }

   public synchronized VariableContext getContext(ModelType modelType)
   {
      if (modelType == null)
      {
         return null;
      }
      return contextMap.get(modelType.getId());
   }

   public synchronized VariableContext getContext(IModelElement element)
   {
      ModelType modelType = ModelUtils.findContainingModel(element);
      if (modelType != null)
      {
         VariableContext context = contextMap.get(modelType.getId());
         // If this is a referenced model a corresponding context does not exist -->
         // create on the fly
         if (context == null)
         {
            createContext(modelType);
         }
         return contextMap.get(modelType.getId());
      }
      return null;
   }

   public synchronized void updateContextID(ModelType modelType, String newID)
   {
      if (modelType != null && contextMap.get(modelType.getId()) != null)
      {
         VariableContext context = contextMap.remove(modelType.getId());
         contextMap.put(newID, context);
      }
   }

   public synchronized void storeVariables(ModelType workflowModel, boolean save)
   {
      if (workflowModel != null)
      {
         createContext(workflowModel);
         getContext(workflowModel).initializeVariables(workflowModel);
         getContext(workflowModel).refreshVariables(workflowModel);
         if (save)
         {
            getContext(workflowModel).saveVariables();
         }
      }
   }

   public static String getName(String name)
   {
      String[] parts = name.split(":"); //$NON-NLS-1$
      return parts[parts.length - 1];      
   }
   
   public static String getType(String name)
   {
      String[] parts = name.split(":"); //$NON-NLS-1$
      if(parts.length == 1)
      {
         return ConfigurationVariableScope.String.name();
      }      
      
      return parts[0];      
   }        
}