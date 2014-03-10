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

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.dnd.SimpleObjectTransfer;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ui.ConnectionEditUtils;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Tree;

public class ModelElementTransferDropTargetListener
      extends AbstractTransferDropTargetListener
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private static XpdlPackage XPKG = XpdlPackage.eINSTANCE;

   private WorkflowModelEditor editor;

   private boolean isEnabled;

   private boolean isChild;

   public ModelElementTransferDropTargetListener(WorkflowModelEditor editor,
         EditPartViewer viewer)
   {
      super(viewer, ModelElementTransfer.getInstance());
      this.editor = editor;
   }

   protected void updateTargetRequest()
   {
      ((CreateSymbolRequest) getTargetRequest()).setLocation(getDropLocation());
   }

   protected void updateTargetEditPart()
   {
      super.updateTargetEditPart();
      if (getTargetEditPart() != null
            && getTargetEditPart().getModel() instanceof ISymbolContainer)
      {
         ((CreateSymbolRequest) getTargetRequest())
               .setSymbolContainer((ISymbolContainer) getTargetEditPart().getModel());
      }
   }

   protected Request createTargetRequest()
   {
      return new CreateSymbolRequest(editor, (ModelElementTransfer) getTransfer(),
            getViewer().getEditDomain());
   }

   protected void handleDragOver()
   {
      getCurrentEvent().detail = DND.DROP_COPY;
      super.handleDragOver();
   }

   public boolean isEnabled(DropTargetEvent event)
   {
      // a transition should not be dropped from outline to diagram
      Object object = ((SimpleObjectTransfer) getTransfer()).getObject();
      if (object instanceof TransitionType)
      {
         return false;
      }
      // check if an external reference to another model version already exists
      if (object instanceof IObjectDescriptor && object instanceof IObjectReference)
      {
         IObjectDescriptor descriptor = (IObjectDescriptor) object;
         String uri = descriptor.getURI().scheme().toString() + "://" //$NON-NLS-1$
               + descriptor.getURI().authority() + "/"; //$NON-NLS-1$
         ModelType model = editor.getWorkflowModel();
         ConnectionManager cm = editor.getConnectionManager();
         if (ConnectionEditUtils.mustLink(descriptor, cm)
               && ModelUtils.referenceToAnotherVersionExists(model, uri))
         {
            return false;
         }
      }
      CreateSymbolRequest request = (CreateSymbolRequest) getTargetRequest();
      isEnabled = super.isEnabled(event) && request.isEnabled();
      isChild = !isEnabled && isChildCreated();
      return isEnabled || isChild;
   }

   protected void handleDrop()
   {
      if (isChild)
      {
         final CreateSymbolRequest request = (CreateSymbolRequest) getTargetRequest();
         Object transferElement = ((ModelElementTransfer) getTransfer()).getObject();
         if (transferElement instanceof IObjectDescriptor)
         {
            request.setFactoryForDescriptor((IObjectDescriptor) transferElement);
         }
         else
         {
            request.setFactoryForProcess((ProcessDefinitionType) transferElement);
         }
      }
      super.handleDrop();
      refreshTree();
      editor.setFocus();
   }

   private void refreshTree()
   {
      Tree tree = (Tree) Reflect.getFieldValue(editor.getOutlinePage()
            .getOutlineTreeEditor(), "tree"); //$NON-NLS-1$
      if (tree != null)
      {
         if (tree.getItems().length > 0 && tree.getItem(0) != null)
         {
            if (tree.getItem(0).getData() instanceof ModelTreeEditPart)
            {
               ModelTreeEditPart editPart = (ModelTreeEditPart) tree.getItem(0).getData();
               editPart.refresh();
            }
         }
      }
   }

   private boolean isChildCreated()
   {
      Object transferElement = ((ModelElementTransfer) getTransfer()).getObject();
      if (transferElement instanceof IObjectDescriptor)
      {
         Object type = ((IObjectDescriptor) transferElement).getType();
         if (PKG.getProcessDefinitionType().equals(type))
         {
            return true;
         }
         if (XPKG.getTypeDeclarationType().equals(type))
         {
            return true;
         }
         if (PKG.getApplicationType().equals(type))
         {
            return true;
         }
         return false;
      }
      else
      {
         return (((ModelElementTransfer) getTransfer()).getObject() instanceof ProcessDefinitionType);
      }
   }
}