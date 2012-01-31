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
package org.eclipse.stardust.modeling.deploy;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmCoreLibrariesClasspathContainer;
import org.eclipse.stardust.modeling.common.projectnature.classpath.CarnotToolClasspathProvider;
import org.eclipse.ui.PlatformUI;

public class DeployUtil
{
   public static boolean deployModel(List<IResource> resources, String carnotHome, String carnotWork)
   {
      boolean deployed = false;

      if (null != resources && !resources.isEmpty())
      {
         try
         {
            IProject project = getCommonProject(resources);

            ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
            ILaunchConfigurationType type = manager
                  .getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
            ILaunchConfigurationWorkingCopy wc = type.newInstance(null,
                  "Infinity Process Model Deployment"); //$NON-NLS-1$
            wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project
                  .getName());
            wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH_PROVIDER,
                  ModelingCoreActivator.ID_CARNOT_TOOL_CP_PROVIDER);
            wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
                  ModelDeploymentTool.class.getName());
            // Activate if debugging deployment is needed.
            // String debug =
            // " -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000";
            // String debug =
            // " -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000";
            wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
                  "-Xms50m -Xmx256m"); //$NON-NLS-1$
            // "-Xms50m -Xmx256m" + debug);
                        
            boolean version = PlatformUI.getPreferenceStore().getBoolean(BpmProjectNature.PREFERENCE_DEPLOY_version);
            
            String realm = PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_realm);            
            String partition = PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_partition);            
            String user = PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_id);            
            String password = PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_password);            
            String domain = PlatformUI.getPreferenceStore().getString(BpmProjectNature.PREFERENCE_DEPLOY_domain);                     
            
            StringBuilder programAttributes = new StringBuilder();
            boolean separator = false;
            for (IResource resource : resources)
            {
               addArgument(programAttributes, "filename64", resource.getLocation().toOSString(), true, separator);             //$NON-NLS-1$
               separator = true;
            }
            if (version)
            {
               addArgument(programAttributes, "version", Boolean.TRUE.toString(), false, true); //$NON-NLS-1$ //$NON-NLS-2$
            }
            
            if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password))
            {
               addArgument(programAttributes, "user", user, true, true); //$NON-NLS-1$            
               addArgument(programAttributes, "password", password, true, true); //$NON-NLS-1$            
               
               if (!StringUtils.isEmpty(realm))
               {
                  addArgument(programAttributes, "realm", realm, true, true); //$NON-NLS-1$            
               }
               
               if (!StringUtils.isEmpty(partition))
               {
                  addArgument(programAttributes, "partition", partition, true, true); //$NON-NLS-1$            
               }
               
               if (!StringUtils.isEmpty(domain))
               {
                  addArgument(programAttributes, "domain", domain, true, true); //$NON-NLS-1$            
               }
            }
            
            wc.setAttribute(
                        IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, programAttributes.toString());
                        
            wc.setAttribute(CarnotToolClasspathProvider.ATTR_HOME_LOCATION, carnotHome);
            wc.setAttribute(CarnotToolClasspathProvider.ATTR_WORK_LOCATION, carnotWork);

            wc.setAttribute(CarnotToolClasspathProvider.ATTR_EXTRA_LOCATION,
                  BpmCoreLibrariesClasspathContainer.getLibraryLocation(
                        DeployPlugin.PLUGIN_ID, new String[] {"bin", ""}).toString()); //$NON-NLS-1$ //$NON-NLS-2$

            ILaunchConfiguration config = wc.doSave();
            ILaunch toolLaunch = config.launch(ILaunchManager.RUN_MODE, null);

            deployed = (0 < toolLaunch.getProcesses().length);

            config.delete();
            wc.delete();
         }
         catch (CoreException e)
         {
            // TODO
            e.printStackTrace();
         }
      }
      return deployed;
   }

   private static void addArgument(StringBuilder programAttributes, String name,
         String value, boolean encode, boolean separator)
   {
      if (separator)
      {
         programAttributes.append(' '); //$NON-NLS-1$
      }
      programAttributes.append("--"); //$NON-NLS-1$
      programAttributes.append(name); //$NON-NLS-1$
      programAttributes.append(' ');
      programAttributes.append(encode ? new String(Base64.encode(value.getBytes())) : value);
   }

   private static IProject getCommonProject(List<IResource> resources) throws CoreException
   {
      IProject project = null;
      for (IResource resource : resources)
      {
         IProject prj = resource.getProject();
         if (prj == null)
         {
            throw new CoreException(new Status(IStatus.ERROR, DeployPlugin.PLUGIN_ID,
                  DeployPlugin.INVALID_PROJECT_CODE, MessageFormat.format(
                        Deploy_Messages
                              .getString("MSG_RESOURCE_NOT_PART_OF_PROJECT"), resource), //$NON-NLS-1$
                  null));
         }
         if (project == null)
         {
            project = prj;
         }
         else if (project != prj)
         {
            throw new CoreException(new Status(IStatus.ERROR, DeployPlugin.PLUGIN_ID,
                  DeployPlugin.INVALID_PROJECT_CODE,
                  Deploy_Messages.getString("MSG_REOURCES_DIFFERENT_PROJECTS"), //$NON-NLS-1$
                  null));
         }
      }
      return project;
   }
}