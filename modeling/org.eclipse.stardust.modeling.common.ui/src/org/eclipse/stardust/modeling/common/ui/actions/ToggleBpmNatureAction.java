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
package org.eclipse.stardust.modeling.common.ui.actions;

import java.util.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import ag.carnot.base.StringUtils;

/**
 * @author rsauer
 * @version $Revision$
 */
public class ToggleBpmNatureAction extends ActionDelegate
      implements IWorkbenchWindowActionDelegate
{
   public static final String NATURE_ID = ModelingCoreActivator.PLUGIN_ID
         + ".carnotBusinessProcessManagement"; //$NON-NLS-1$

   private final Set<IProject> projects = new HashSet<IProject>();

   public void init(IWorkbenchWindow window)
   {
   }

   public void selectionChanged(IAction action, ISelection selection)
   {
      updateSelectedProjects(selection);

      boolean enabled = false;
      boolean checked = false;

      if (1 == projects.size())
      {
         enabled = true;

         IProject project = projects.iterator().next();
         try
         {
            checked = project.isOpen() && project.hasNature(NATURE_ID);
         }
         catch (CoreException e)
         {
            enabled = false;

            // TODO trace
         }
      }

      action.setEnabled(enabled);
      action.setChecked(checked);
   }

   public void run(IAction action)
   {
      // TODO Auto-generated method stub
      for (Iterator<IProject> i = projects.iterator(); i.hasNext();)
      {
         IProject project = (IProject) i.next();

         if (project.isOpen())
         {
            try
            {
               IProjectDescription prjDescr = project.getDescription();

               List<String> ids = new ArrayList<String>(prjDescr.getNatureIds().length + 1);
               ids.addAll(Arrays.asList(prjDescr.getNatureIds()));

               if (ids.contains(NATURE_ID))
               {
                  ids.remove(NATURE_ID);
               }
               else
               {
                  // insert before Java Nature or nature overlay will not been shown
                  ids.add(0, NATURE_ID);
               }

               prjDescr.setNatureIds(ids.toArray(StringUtils.EMPTY_STRING_ARRAY));
               
               project.setDescription(prjDescr, null);
            }
            catch (CoreException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

         }
      }
   }

   private void updateSelectedProjects(ISelection selection)
   {
      projects.clear();
      if (selection instanceof IStructuredSelection)
      {
         for (Iterator<?> i = ((IStructuredSelection) selection).iterator(); i.hasNext();)
         {
            Object item = i.next();
            if ( !(item instanceof IResource) && (item instanceof IAdaptable))
            {
               item = ((IAdaptable) item).getAdapter(IResource.class);
            }

            if ( !(item instanceof IProject) && (item instanceof IResource))
            {
               item = ((IResource) item).getProject();
            }

            if (item instanceof IProject)
            {
               projects.add((IProject) item);
            }
         }
      }
   }

}
