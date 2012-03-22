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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.*;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelOutlinePage;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.swt.widgets.TreeItem;

public class WorkflowModelTreeEditPartFactory implements EditPartFactory
{
   private static final EStructuralFeature[] processChildren = {
      CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Diagram(),
      CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Activity(),
      CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_DataPath(),
      CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Trigger(),
      CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Transition(),
//      CarnotWorkflowModelPackage.eINSTANCE.getIEventHandlerOwner_EventHandler()
   };
   private WorkflowModelEditor editor;

   public WorkflowModelTreeEditPartFactory(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public EditPart createEditPart(EditPart context, final Object model)
   {
      if(null == model)
      {
         return null;
      }
      else if (model instanceof ChildCategoryNode.Spec)
      {
         if (Diagram_Messages.LB_Participants.equals(((ChildCategoryNode.Spec) model).label))
         {
            // special treatments for participants
            return new ChildCategoryNode(editor, (ChildCategoryNode.Spec) model)
            {
               @SuppressWarnings("rawtypes")
               protected List getModelChildren()
               {
                  @SuppressWarnings("unchecked")
                  List<Object> children = super.getModelChildren();
                  List<Object> toRemove = CollectionUtils.newList();
                  // filter out elements that do have parents
                  for (int i = 0; i < children.size(); i++)
                  {
                     Object child = children.get(i);
                     if (child instanceof OrganizationType)
                     {
                        OrganizationType organization = (OrganizationType) child;
                        List participants = organization.getParticipant();
                        for (int j = 0; j < participants.size(); j++)
                        {
                           ParticipantType ref = (ParticipantType) participants.get(j);
                           toRemove.add(ref.getParticipant());
                        }
                     }
                     // as of 4.0 strategy, modelers have no longer any role
                     // and only IModelParticipants will show up in the tree
                     else if (!(child instanceof IModelParticipant))
                     {
                        toRemove.add(child);
                     }
                  }
                  children.removeAll(toRemove);
                  return children;
               }
            };
         }
         else
         {
            return new ChildCategoryNode(editor, (ChildCategoryNode.Spec) model);
         }
      }
      else if (model instanceof DocumentRoot)
      {
         return new AbstractEObjectTreeEditPart(editor, (DocumentRoot) model)
         {
            protected void createEditPolicies()
            {
               super.createEditPolicies();

               // If this editpart is the root content of the viewer, then disallow
               // removal
               if (getParent() instanceof RootEditPart)
               {
                  installEditPolicy(EditPolicy.COMPONENT_ROLE,
                        new RootComponentEditPolicy());
               }
            }

            @SuppressWarnings("rawtypes")
            protected List getModelChildren()
            {
               return Collections.singletonList(((DocumentRoot) getModel()).getModel());
            }
         };
      }
      else if (model instanceof ActivityType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ActivityType) model, editor.getIconFactory().getIconFor((ActivityType) model));
      }
      else if (model instanceof ApplicationType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ApplicationType) model, editor.getIconFactory().getIconFor((ApplicationType) model));
      }
      else if (model instanceof ConditionalPerformerType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ConditionalPerformerType) model, editor.getIconFactory().getIconFor((ConditionalPerformerType) model));
      }
      else if (model instanceof DataType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (DataType) model, editor.getIconFactory().getIconFor((DataType) model));
      }
      else if (model instanceof DataPathType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (DataPathType) model, editor.getIconFactory().getIconFor((DataPathType) model));
      }
      else if (model instanceof DiagramType)
      {
         return new AbstractEObjectTreeEditPart(editor,
            (DiagramType) model, editor.getIconFactory().getIconFor((DiagramType) model))
         {
            protected void createEditPolicies()
            {
               super.createEditPolicies();
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
               String name = ((DiagramType) getModel()).getName();
               return !StringUtils.isEmpty(name)
                     ? name
                     : Diagram_Messages.DiagramEditor_PAGENAME_UnnamedDiagram;
            }

            public Object getAdapter(@SuppressWarnings("rawtypes") Class key)
            {
               if (IModelElement.class.equals(key))
               {
                  return getModel();
               }
               return super.getAdapter(key);
            }
         };
      }
      else if (model instanceof EventHandlerType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (EventHandlerType) model, editor.getIconFactory().getIconFor((EventHandlerType) model));
      }
      else if (model instanceof ModelType)
      {
         return new ModelTreeEditPart(editor, (ModelType) model,
               editor.getIconFactory().getIconFor((ModelType) model));
      }
      else if (model instanceof ModelerType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ModelerType) model, editor.getIconFactory().getIconFor((ModelerType) model));
      }
      else if (model instanceof OrganizationType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (OrganizationType) model, editor.getIconFactory().getIconFor((OrganizationType) model))
         {
            @SuppressWarnings("rawtypes")
            protected List getModelChildren()
            {
               OrganizationType organization = (OrganizationType) getModel();
               List<ParticipantType> participants = organization.getParticipant();
               List<IModelParticipant> list = CollectionUtils.newList();
               for (int i = 0; i < participants.size(); i++)
               {
                  ParticipantType ref = participants.get(i);
                  if (null != ref.getParticipant())
                  {
                     list.add(ref.getParticipant());
                  }
               }
               Collections.sort(list, ModelUtils.IDENTIFIABLE_COMPARATOR);
               return list;
            }

            public void handleNotification(Notification n)
            {
               try
               {
                  super.handleNotification(n);
               }
               catch (NullPointerException e)
               {
               }
               
               switch (n.getEventType())
               {
                  case Notification.ADD:
                  case Notification.ADD_MANY:
                  case Notification.REMOVE:
                  case Notification.REMOVE_MANY:
                     ChildCategoryNode category = getCategory();
                     if (category != null)
                     {
                        category.handleNotification(n);
                     }
               }
            }

            private ChildCategoryNode getCategory()
            {
               EditPart parent = getParent();
               while (parent != null && !(parent instanceof ChildCategoryNode))
               {
                  parent = parent.getParent();
               }
               return (ChildCategoryNode) parent;
            }
         };
      }
      else if (model instanceof ProcessDefinitionType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ProcessDefinitionType) model, editor.getIconFactory().getIconFor((ProcessDefinitionType) model),
            processChildren)
         {
            @Override
            protected boolean accept(Object o)
            {
               if (o instanceof TransitionType &&
                     PredefinedConstants.RELOCATION_TRANSITION_ID.equals(((TransitionType) o).getId()))
               {
                  return false;
               }
               return super.accept(o);
            }
         };
      }
      else if (model instanceof RoleType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (RoleType) model, editor.getIconFactory().getIconFor((RoleType) model));
      }
      else if (model instanceof TriggerType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (TriggerType) model, editor.getIconFactory().getIconFor((TriggerType) model));
      }
      else if (model instanceof TransitionType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (TransitionType) model, editor.getIconFactory().getIconFor((TransitionType) model));
      }

      // types
      else if (model instanceof EventActionTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (EventActionTypeType) model, editor.getIconFactory().getIconFor((EventActionTypeType) model));
      }
      else if (model instanceof ApplicationTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ApplicationTypeType) model, editor.getIconFactory().getIconFor((ApplicationTypeType) model));
      }
      else if (model instanceof DataTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (DataTypeType) model, editor.getIconFactory().getIconFor((DataTypeType) model));
      }
      else if (model instanceof ApplicationContextTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ApplicationContextTypeType) model, editor.getIconFactory().getIconFor((ApplicationContextTypeType) model));
      }
      else if (model instanceof LinkTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (LinkTypeType) model, editor.getIconFactory().getIconFor((LinkTypeType) model));
      }
      else if (model instanceof TriggerTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (TriggerTypeType) model, editor.getIconFactory().getIconFor((TriggerTypeType) model));
      }
      else if (model instanceof ExternalPackages)
      {         
         return new AbstractEObjectTreeEditPart(editor, (ExternalPackages) model, 
               editor.getIconFactory().getIconFor((ExternalPackages) model),
               new EStructuralFeature [] {XpdlPackage.eINSTANCE.getExternalPackages_ExternalPackage()})
         {
            public String getLabel()
            {
               return Diagram_Messages.LABEL_EXTERNAL_MODEL_REFERENCES;
            }
         };
      }
      else if (model instanceof ExternalPackage)
      {
         return new AbstractEObjectTreeEditPart(editor, (ExternalPackage) model,
               editor.getIconFactory().getIconFor((ExternalPackage) model))
         {
            protected void createEditPolicies()
            {
               super.createEditPolicies();
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
            
            public void performRequest(Request req)
            {
               if (req.getType() == REQ_DIRECT_EDIT)
               {
                  Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser((EObject) editor.getModel());
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
               ExternalPackage decl = (ExternalPackage) model;
               String label = decl.getName();
               if (label == null)
               {
                  label = decl.getId();
               }
               return label == null ? Diagram_Messages.LBL_EXTERNAL_PCK : label;
            }
         };
      }
      else if (model instanceof TypeDeclarationsType)
      {         
         return new AbstractEObjectTreeEditPart(editor, (TypeDeclarationsType) model, 
               editor.getIconFactory().getIconFor((TypeDeclarationsType) model),
               new EStructuralFeature [] {XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration()})
         {
            public String getLabel()
            {
               return Diagram_Messages.STRUCTURED_DATA_LABEL;
            }
         };
      }
      else if (model instanceof TypeDeclarationType)
      {         
         return new AbstractEObjectTreeEditPart(editor, (TypeDeclarationType) model,
               editor.getIconFactory().getIconFor((TypeDeclarationType) model))
         {
            protected void createEditPolicies()
            {
               super.createEditPolicies();
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
               TypeDeclarationType decl = (TypeDeclarationType) model;
               String label = decl.getName();
               if (label == null)
               {
                  label = decl.getId();
               }
               return label == null ? Diagram_Messages.TYPE_DECLARATION_LABEL : label;
            }
         };
      }
      return (EditPart) Platform.getAdapterManager().getAdapter(model, TreeEditPart.class);
   }   
}