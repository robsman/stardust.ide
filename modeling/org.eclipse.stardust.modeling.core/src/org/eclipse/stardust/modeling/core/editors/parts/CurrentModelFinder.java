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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartService;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchWindow;


/** 
 * Class that helps Views to know the current model
 * 
 * view must implement IPartListener 
 * to get notification when to check getModel()
 * 
 * @author Barry.Grotjahn
 * @version $Revision: $
 */
public class CurrentModelFinder implements IPageListener
{
   private static CurrentModelFinder MODEL_FINDER = null;
   
   private WorkflowModelEditor editor;
   private WorkbenchWindow workbenchWindow;
   private WorkbenchPage workbenchPage;
   private PartService service;   
   
   private List partListeners = new ArrayList();
   
   private CurrentModelFinder()
   {
      workbenchWindow = (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      workbenchWindow.addPageListener(this);      
      workbenchPage = (WorkbenchPage) workbenchWindow.getActivePage();      
      if(workbenchPage != null)
      {
         service = workbenchPage.getPartService();         
      }
   }
   
   public static CurrentModelFinder getInstance()
   {
      if(MODEL_FINDER == null)
      {
         MODEL_FINDER = new CurrentModelFinder();
      }
      return MODEL_FINDER;
   }

   public ModelType getModel()
   {
      if(workbenchPage == null)
      {
         return null;
      }
      
      IEditorPart editorPart = workbenchPage.getActiveEditor();
      if (editorPart instanceof WorkflowModelEditor)
      {
         return ((WorkflowModelEditor) editorPart).getWorkflowModel();
      }
      
      return null;
   }

   public void pageActivated(IWorkbenchPage page)
   {
      workbenchPage = (WorkbenchPage) page;
      service = workbenchPage.getPartService();
      
      addToPage(null);
   }

   public void pageClosed(IWorkbenchPage page)
   {      
      if(page.equals(workbenchPage))
      {
         workbenchPage = null;         
      }
   }

   public void pageOpened(IWorkbenchPage page)
   {
   }
   
   public void addListener(IPartListener listener)
   {
      if(partListeners.contains(listener))
      {
         return;
      }
      partListeners.add(listener);
      if(service != null)
      {
         addToPage(listener);
      }
   }
   
   public void removeListener(IPartListener listener)
   {
      if(!partListeners.contains(listener))
      {
         return;
      }
      partListeners.remove(listener);      
      if(service != null)
      {
         removeFromPage(listener);
      }
   }

   private void addToPage(IPartListener addListener)
   {
      if(addListener != null)
      {
         service.addPartListener(addListener);
         return;
      }
      
      for (Iterator i = partListeners.iterator(); i.hasNext();)
      {
         IPartListener listener = (IPartListener) i.next();    
         service.addPartListener(listener);                  
      }
   }   
   
   private void removeFromPage(IPartListener removeListener)
   {
      if(removeListener != null)
      {
         service.removePartListener(removeListener);
         return;
      }
      
      for (Iterator i = partListeners.iterator(); i.hasNext();)
      {
         IPartListener listener = (IPartListener) i.next();    
         service.removePartListener(listener);                  
      }
   }
}