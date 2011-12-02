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
package org.eclipse.stardust.modeling.debug.launching;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.debugger.Debugger;


public class LaunchConfigUtils
{
   private static String trimToNull(String value)
   {
      String trimmedValue = value.trim();
      
      if (trimmedValue.length() == 0)
      {
         trimmedValue = null;
      }
      
      return trimmedValue;
   }
   
   public static String getModelFileName(ILaunchConfiguration configuration)
         throws CoreException
   {
      return configuration
            .getAttribute(Constants.ATTR_CWM_MODEL_FILE_PATH, (String) null);
   }

   public static void setModelFileName(ILaunchConfigurationWorkingCopy configuration,
         String modelFileName)
   {
      configuration.setAttribute(Constants.ATTR_CWM_MODEL_FILE_PATH,
            trimToNull(modelFileName));
   }

   public static String getProjectName(ILaunchConfiguration configuration)
         throws CoreException
   {
      return configuration.getAttribute(
            IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, (String) null);
   }

   public static void setProjectName(ILaunchConfigurationWorkingCopy configuration,
         String modelFileName)
   {
      configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
            trimToNull(modelFileName));
   }

   public static String getProcessDefinitionId(ILaunchConfiguration configuration)
         throws CoreException
   {
      return configuration.getAttribute(Constants.ATTR_CWM_PROCESS_DEFINITION_ID,
            (String) null);
   }

   public static void setProcessDefinitionId(ILaunchConfigurationWorkingCopy configuration,
         String modelFileName)
   {
      configuration.setAttribute(Constants.ATTR_CWM_PROCESS_DEFINITION_ID,
            trimToNull(modelFileName));
   }

   public static void setProgramArguments(ILaunchConfigurationWorkingCopy configuration,
         String project, String modelFile, String processDefinitionId)
   {
      configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
         MessageFormat.format("\"{0}{1}\" \"{2}{3}\"", //$NON-NLS-1$
            Constants.CMDLINE_ARG_MODEL_FILE, ModelUtils.getFileSystemPath(trimToNull(project), trimToNull(modelFile)),
            Constants.CMDLINE_ARG_PROCESS_DEFINITION_ID, trimToNull(processDefinitionId)));
   }

   private LaunchConfigUtils()
   {
      // utility class
   }

   public static void setDefaultAttributes(ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
            Debugger.class.getName());
      configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_STOP_IN_MAIN,
            false);
      configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH_PROVIDER,
            Constants.ID_RUNTIME_CLASSPATH_PROVIDER);
      try
      {
         if (configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null) == null)
         {
            configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
               "-Dcarnot.log.type=stdout");
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public static List<String> getDependencies(ILaunchConfiguration config) throws CoreException
   {
      return config.getAttribute(Constants.ATTR_CWM_MODEL_DEPENDENCIES, (List<String>) null);
   }

   public static void setDependencies(ILaunchConfigurationWorkingCopy wc, List<String> dependencies)
   {
      if (dependencies == null)
      {
         wc.removeAttribute(Constants.ATTR_CWM_MODEL_DEPENDENCIES);
      }
      else
      {
         wc.setAttribute(Constants.ATTR_CWM_MODEL_DEPENDENCIES, dependencies);
      }
   }
}
