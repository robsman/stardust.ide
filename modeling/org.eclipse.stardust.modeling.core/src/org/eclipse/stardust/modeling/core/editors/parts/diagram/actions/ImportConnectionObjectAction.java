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

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;

public class ImportConnectionObjectAction extends SelectionAction
{
   public ImportConnectionObjectAction(WorkflowModelEditor part)
   {
      super(part);
      setId(ObjectRepositoryActivator.IMPORT_CONNECTION_OBJECT_ACTION);
      setText(Diagram_Messages.TXT_IMPORT);      
   }

   // when to view
   protected boolean calculateEnabled()
   {
      // check selection
      List<Object> models = getModels();
      if (models != null)
      {
         return !isConnectionImportByReference(models);
      }
      return false;
   }
   
   private boolean isConnectionImportByReference(List<Object> models)
   {
      try
      {
         for (Iterator<Object> i = getModels().iterator(); i.hasNext();)
         {
            Object o = i.next();
            if (o instanceof Proxy)
            {
               Proxy p = (Proxy) o;
               Object cm = Reflect.getFieldValue(p, "h"); //$NON-NLS-1$
               ConnectionManager cman = (ConnectionManager) Reflect.getFieldValue(cm,
                     "this$0"); //$NON-NLS-1$
               EObjectDescriptor desc = (EObjectDescriptor) Reflect.getFieldValue(cm,
                     "val$desc"); //$NON-NLS-1$
               Connection conn = cman.getConnection(desc.getURI().authority());
               if (conn.getAttribute("importByReference").equals("true")) //$NON-NLS-1$ //$NON-NLS-2$
               {
                  return true;
               }
            }
         }
         return false;
      }
      catch (Throwable t)
      {
         return false;
      }
   }

   private List<Object> getModels()
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
         ModelType model = ((IWorkflowModelEditor) getWorkbenchPart()).getWorkflowModel();
         IObjectDescriptor[] descriptors = (IObjectDescriptor[]) getModels().toArray(new IObjectDescriptor[0]);
         Command cmd = manager.linkObject(model, descriptors);
         execute(cmd);
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
