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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;


public class LinkConnectionObjectAction extends SelectionAction
{
   public LinkConnectionObjectAction(WorkflowModelEditor part)
   {
      super(part);
      setId(ObjectRepositoryActivator.PLUGIN_ID + '.' + "link"); //$NON-NLS-1$
      setText(Diagram_Messages.TXT_LINK);      
   }

   // when to view
   protected boolean calculateEnabled()
   {
      // check selection
      if(getModels() != null)
      {
         return true;
      }
      return false;
   }   
   
   private List getModels()
   {
      if(getSelectedObjects().size() == 0) return null;
      List selection = getSelectedObjects();
      Object entry;
      ArrayList result = new ArrayList();
      for (int i = 0; i < selection.size(); i++) 
      {
         entry = getSelectedObjects().get(i);
         if (entry instanceof EditPart)
         {
            Object model = ((EditPart) entry).getModel();
            if (model instanceof IObjectDescriptor)
            {
               IObjectDescriptor descriptor = (IObjectDescriptor) model;
               if (descriptor.getType() instanceof EClass/* &&
                     ((EClass) descriptor.getType()).getEPackage().getNsURI().equals(
                           CarnotWorkflowModelPackage.eINSTANCE.getNsURI())*/)
               result.add(model);
            }
            else
            {
               return null;
            }
         }
      }
      return result.size() == 0 ? null : result;
   }   

   // when command is executed
   public void run()
   {
      ConnectionManager manager = (ConnectionManager) getWorkbenchPart().getAdapter(
            ConnectionManager.class);
      try
      {
         IWorkflowModelEditor editor = (IWorkflowModelEditor) getWorkbenchPart();
         ModelType model = editor.getWorkflowModel();
         
         ArrayList result = (ArrayList) getModels();
         
         // call the manager with the selection (and connection? )
         Command cmd;
            cmd = manager.linkObject(model, 
                  (IObjectDescriptor[]) result.toArray(new IObjectDescriptor[0]));
            execute(cmd);
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
