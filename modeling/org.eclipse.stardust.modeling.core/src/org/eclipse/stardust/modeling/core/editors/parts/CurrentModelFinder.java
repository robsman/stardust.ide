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
package org.eclipse.stardust.modeling.core.editors.parts;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.*;

/** 
 * Class that helps Views to know the current model
 * 
 * view must implement IPartListener 
 * to get notification when to check getModel()
 * 
 * @author Barry.Grotjahn
 * @version $Revision: $
 */
public class CurrentModelFinder
{
   private static CurrentModelFinder MODEL_FINDER = new CurrentModelFinder();
   
   private CurrentModelFinder() {}
   
   public static CurrentModelFinder getInstance()
   {
      return MODEL_FINDER;
   }

   public static ModelType getModel()
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      if (workbench != null)
      {
         IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
         if (workbenchWindow != null)
         {
            IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
            IEditorPart editorPart = workbenchPage.getActiveEditor();
            if (editorPart instanceof WorkflowModelEditor)
            {
               return ((WorkflowModelEditor) editorPart).getWorkflowModel();
            }
         }
      }
      return null;
   }

   public void addListener(IPartListener listener)
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      if (workbench != null)
      {
         IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
         if (workbenchWindow != null)
         {
            IPartService service = workbenchWindow.getPartService();
            service.addPartListener(listener);
         }
      }
   }
   
   public void removeListener(IPartListener listener)
   {
      IWorkbench workbench = PlatformUI.getWorkbench();
      if (workbench != null)
      {
         IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
         if (workbenchWindow != null)
         {
            IPartService service = workbenchWindow.getPartService();
            service.removePartListener(listener);
         }
      }
   }
}