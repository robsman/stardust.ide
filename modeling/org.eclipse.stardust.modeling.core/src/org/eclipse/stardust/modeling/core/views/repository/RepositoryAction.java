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
package org.eclipse.stardust.modeling.core.views.repository;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class RepositoryAction extends SelectionAction
{
   public RepositoryAction(IWorkbenchPart part)
   {
      super(part);
      setLazyEnablementCalculation(false);
   }

   protected boolean calculateEnabled()
   {
      VersionRepository repository = (VersionRepository) getWorkbenchPart();
      ProjectContentProvider provider = repository.getContentProvider();
      List selection = getSelectedObjects();
      if (supportsMultiSelection())
      {
         boolean result = false;
         for (int i = 0; i < selection.size(); i++)
         {
            Object o = selection.get(i);
            if (o instanceof IFile)
            {
               IFile file = (IFile) o;
               ResourceInfo info = provider.findResource(file);
               if (info != null)
               {
                  if (calculateEnabled(info, file))
                  {
                     result = true;
                  }
                  else
                  {
                     return false;
                  }
               }
            }
         }
         return result;
      }
      else
      {
         if (selection.size() == 1 && selection.get(0) instanceof IFile)
         {
            IFile file = (IFile) selection.get(0);
            ResourceInfo info = provider.findResource(file);
            if (info != null)
            {
               return calculateEnabled(info, file);
            }
         }
      }
      return false;
   }

   protected ResourceInfo findResource(IFile file)
   {
      VersionRepository repository = (VersionRepository) getWorkbenchPart();
      ProjectContentProvider provider = repository.getContentProvider();
      return provider.findResource(file);
   }

   protected abstract boolean calculateEnabled(ResourceInfo info, IFile file);

   protected abstract boolean supportsMultiSelection();

   protected abstract boolean run(IFile file);

   public void run()
   {
      List objects = getSelectedObjects();
      for (int i = 0; i < objects.size(); i++)
      {
         Object o = objects.get(i);
         if (o instanceof IFile)
         {
            if (!run((IFile) o))
            {
               break;
            }
         }
      }
      update();
   }

   protected void openEditor(final IFile file)
   {
      getShell().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage();
            try
            {
               IDE.openEditor(page, file, true);
            }
            catch (PartInitException e)
            {
            }
         }
      });
   }

   protected Shell getShell()
   {
      return getWorkbenchPart().getSite().getShell();
   }

   protected ModelType create(IFile file) throws CoreException
   {
      ModelType model = null;
      WorkflowModelManager modelManager = new WorkflowModelManager();

      if (file.exists())
      {
         try
         {
            modelManager.load(URI.createPlatformResourceURI(
               file.getFullPath().toString()));
         }
         catch (Exception e)
         {
            throw new PartInitException(Diagram_Messages.EX_FailedLoadingModel, e);
         }

         model = modelManager.getModel();
         if (null == model)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
                  Diagram_Messages.EX_ErrorLoadingNetwork, null));
         }
      }
      return model;
   }
}
