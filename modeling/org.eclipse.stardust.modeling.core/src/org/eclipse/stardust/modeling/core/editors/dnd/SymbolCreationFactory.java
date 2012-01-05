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
package org.eclipse.stardust.modeling.core.editors.dnd;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;


public abstract class SymbolCreationFactory implements CreationFactory
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private static XpdlPackage XPKG = XpdlPackage.eINSTANCE;
   
   protected boolean reloadConnections = true;
   
   protected Object modelElement;

   protected Point dropLocation;

   protected ISymbolContainer symbolContainer;

   protected EditDomain editDomain;

   protected WorkflowModelEditor editor;
   
   private boolean lockRequired;

   public boolean isLockRequired()
   {
      return lockRequired;
   }

   public Object getModelElement()
   {
      return modelElement;
   }

   public void setReloadConnections(boolean reloadConnections)
   {
      this.reloadConnections = reloadConnections;
   }

   public void setTransferredModelElement(Object modelElement)
   {
      this.modelElement = modelElement;
   }

   public void setLocation(Point dropLocation)
   {
      this.dropLocation = dropLocation;
   }

   public void setSymbolContainer(ISymbolContainer symbolContainer)
   {
      this.symbolContainer = symbolContainer;
   }
   
   public ISymbolContainer getSymbolContainer()
   {
      return symbolContainer;
   }

   public void setEditDomain(EditDomain editDomain)
   {
      this.editDomain = editDomain;
   }

   public Object getObjectType()
   {
      return IGraphicalObject.class;
   }

   public boolean isEnabled()
   {
      if (symbolContainer != null)
      {
         ISymbolContainer container = symbolContainer;
         while (!(container instanceof DiagramType)
               && container.eContainer() instanceof ISymbolContainer)
         {
            container = (ISymbolContainer) container.eContainer();
         }
         if (container instanceof DiagramType)
         {
            if (modelElement instanceof EObject)
            {
               if (!editor.requireLock((EObject) container))
               {
                  if (modelElement instanceof ProcessDefinitionType
                        || modelElement instanceof IObjectDescriptor
                        && PKG.getProcessDefinitionType().equals(
                              ((IObjectDescriptor) modelElement).getType()))
                  {
                     ModelType model = editor.getWorkflowModel();
                     return model.getDiagram().contains(container) &&
                        !editor.getConnectionManager().mustLink((IObjectDescriptor) modelElement);
                  }
                  if (modelElement instanceof IObjectDescriptor
                        && XPKG.getTypeDeclarationType().equals(
                              ((IObjectDescriptor) modelElement).getType()))
                  {
                     return false;
                  }
                  if (modelElement instanceof IObjectDescriptor
                        && PKG.getApplicationType().equals(
                              ((IObjectDescriptor) modelElement).getType()))
                  {
                     ModelType model = editor.getWorkflowModel();
                     return model.getDiagram().contains(container) &&
                        !editor.getConnectionManager().mustLink((IObjectDescriptor) modelElement);
                  }
                  ProcessDefinitionType process = ModelUtils
                        .findContainingProcess((EObject) modelElement);
                  return process == null || container.eContainer() == process
                        || container.eContainer() == ((EObject) modelElement).eContainer();
               }
               else
               {
                  lockRequired = true;         
               }
            }
         }
      }
      return false;
   }

   public void setEditor(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }
}