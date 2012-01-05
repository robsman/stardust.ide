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
package org.eclipse.stardust.modeling.debug.debugger;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.debug.Constants;

/**
 * @author sborn
 * @version $Revision$
 */
public class DebuggerArgument
{
   private String modelPath;
   private List<String> dependencyPaths;
   private String processDefinitionId;
   private String viewType;
   
   public DebuggerArgument(String[] args) throws CoreException
   {
      dependencyPaths = CollectionUtils.newList();
      if (args != null)
      {
         for (String arg : args)
         {
            if (arg.startsWith(Constants.CMDLINE_ARG_MODEL_FILE))
            {
               modelPath = arg.substring(Constants.CMDLINE_ARG_MODEL_FILE.length());
            }
            else if (arg.startsWith(Constants.CMDLINE_ARG_DEPENDENCY_FILE))
            {
               dependencyPaths.add(arg.substring(Constants.CMDLINE_ARG_DEPENDENCY_FILE.length()));
            }
            else if (arg.startsWith(Constants.CMDLINE_ARG_PROCESS_DEFINITION_ID))
            {
               processDefinitionId = arg.substring(Constants.CMDLINE_ARG_PROCESS_DEFINITION_ID.length());
            }
            else if (arg.startsWith(Constants.CMDLINE_ARG_VIEW_TYPE))
            {
               viewType = arg.substring(Constants.CMDLINE_ARG_VIEW_TYPE.length());
            }
         }
      }
   }
   
   public String getModelPath()
   {
      return modelPath;
   }

   public List<String> getDependencyPaths()
   {
      return dependencyPaths;
   }

   public String getProcessDefinitionId()
   {
      return processDefinitionId;
   }

   public String getViewType()
   {
      return viewType;
   }
}