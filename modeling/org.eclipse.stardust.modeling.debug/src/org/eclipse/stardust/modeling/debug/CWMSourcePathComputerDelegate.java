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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.WorkspaceSourceContainer;
import org.eclipse.stardust.modeling.debug.launching.LaunchConfigUtils;


public class CWMSourcePathComputerDelegate implements ISourcePathComputerDelegate
{
   public ISourceContainer[] computeSourceContainers(ILaunchConfiguration configuration,
         IProgressMonitor monitor) throws CoreException
   {
      String modelPath = LaunchConfigUtils.getModelFileName(configuration);
      ISourceContainer sourceContainer = null;

      if (null != modelPath)
      {
         IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
               new Path(modelPath));

         if (null != resource)
         {
            IContainer container = resource.getParent();

            if (IResource.PROJECT == container.getType())
            {
               sourceContainer = new ProjectSourceContainer((IProject) container, false);
            }
            else if (IResource.FOLDER == container.getType())
            {
               sourceContainer = new FolderSourceContainer(container, false);
            }
         }
      }

      if (null == sourceContainer)
      {
         sourceContainer = new WorkspaceSourceContainer();
      }

      return new ISourceContainer[] { sourceContainer };
   }
}
