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
package org.eclipse.stardust.modeling.core.editors.parts.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelOutlinePage;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.swt.widgets.TreeItem;

import ag.carnot.base.StringUtils;

public class IdentifiableModelElementTreeEditPart
      extends AbstractEObjectTreeEditPart
{
   public IdentifiableModelElementTreeEditPart(WorkflowModelEditor editor, 
         IIdentifiableModelElement model, String iconPath)
   {
      super(editor, model, iconPath);
   }

   public IdentifiableModelElementTreeEditPart(WorkflowModelEditor editor, 
         IIdentifiableModelElement model, String iconPath, 
         EStructuralFeature[] childrenFeatures)
   {
      super(editor, model, iconPath, childrenFeatures);
   }

   protected void createEditPolicies()
   {
      super.createEditPolicies();
      // check for predefined data
      if(!(getModel() instanceof DataType 
            && ((DataType) getModel()).isPredefined()))
      {
         installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
               new DirectEditPolicy()
         {
            protected Command getDirectEditCommand(DirectEditRequest request)
            {
               return null;
            }
   
            protected void showCurrentEditValue(DirectEditRequest request)
            {
            }         
         });      
      }
   }
   
   public void performRequest(Request req)
   {
      if (req.getType() == REQ_DIRECT_EDIT)
      {
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser((EObject) getModel());
         if (lockedByCurrentUser == null || lockedByCurrentUser.equals(Boolean.TRUE))
         {
            WorkflowModelOutlinePage outline = (WorkflowModelOutlinePage) getEditor().getOutlinePage();
            TreeItem treeItem = (TreeItem) getWidget();
            OutlineTreeEditor editor = outline.getOutlineTreeEditor();
            editor.setItem(treeItem, getModel());
            return;
         }
         ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);
         return;
      }
      super.performRequest(req);
   }
   
   public String getLabel()
   {
      IIdentifiableModelElement element = (IIdentifiableModelElement) getModel();
      String name = element.getName();
      if (StringUtils.isEmpty(name))
      {
         name = element.getId();
      }
      if (StringUtils.isEmpty(name) && element instanceof EObjectImpl && element.eIsProxy())
      {
         name = ((EObjectImpl) element).eProxyURI().toString();
      }
      return StringUtils.isEmpty(name) ? Diagram_Messages.MSG_EDITOR_unidentifiedModelElement : name;
   }

   public Object getAdapter(Class key)
   {
      if (IIdentifiableModelElement.class.equals(key) ||
          IIdentifiableElement.class.equals(key) ||
          IModelElement.class.equals(key))
      {
         return getModel();
      }
      return super.getAdapter(key);
   }

   protected void refreshVisuals()
   {
      setIconPath(getEditor().getIconFactory().getIconFor((IIdentifiableModelElement) getModel()));
      super.refreshVisuals();
   }
}