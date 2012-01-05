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
package org.eclipse.stardust.modeling.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author fherinean
 * @version $Revision$
 */
public class WorkflowModelEditorsCollector
{
   private HashMap models;

   public WorkflowModelEditorsCollector()
   {
      models = new HashMap();
      IWorkbench workbench = PlatformUI.getWorkbench();
      IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         IWorkbenchWindow window = windows[i];
         IWorkbenchPage[] pages = window.getPages();
         for (int j = 0; j < pages.length; j++)
         {
            IWorkbenchPage page = pages[j];
            IEditorReference[] references = page.getEditorReferences();
            if (references != null)
            {
               for (int k = 0; k < references.length; k++)
               {
                  IEditorReference reference = references[k];
                  try
                  {
                     IEditorInput input = reference.getEditorInput();
                     if (input instanceof FileEditorInput)
                     {
                        IFile file = ((FileEditorInput) input).getFile();
                        IEditorPart part = reference.getEditor(false);
                        if (part instanceof WorkflowModelEditor)
                        {
                           ModelType model = ((WorkflowModelEditor) part).
                              getWorkflowModel();
                           if (model != null)
                           {
                              ArrayList opened = (ArrayList) models.get(file);
                              if (opened == null)
                              {
                                 opened = new ArrayList();
                                 models.put(file, opened);
                              }
                              opened.add(part);
                           }
                        }
                     }
                  }
                  catch (PartInitException e)
                  {
                     //e.printStackTrace();
                  }
               }
            }
         }
      }
   }

   public void dispose()
   {
      models.clear();
      models = null;
   }

   public List getEditors(IFile file)
   {
      ArrayList list = (ArrayList) models.get(file);
      return list == null ? Collections.EMPTY_LIST : list;
   }
}
