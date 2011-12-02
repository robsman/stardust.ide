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
package org.eclipse.stardust.modeling.core.search.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;

import ag.carnot.base.StringUtils;

public class ResultViewTreeEditPartFactory implements EditPartFactory
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;
   
   private static final EStructuralFeature[] processChildren = {
      PKG_CWM.getProcessDefinitionType_Diagram(),
      PKG_CWM.getProcessDefinitionType_Activity(),
      PKG_CWM.getProcessDefinitionType_DataPath(),
      PKG_CWM.getProcessDefinitionType_Trigger(),
      PKG_CWM.getProcessDefinitionType_Transition(),
   };
   
   private WorkflowModelEditor editor;
   private ResultViewFilter filter = null;   
   private String rootLabel;

   public ResultViewTreeEditPartFactory(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public void setFilter(ResultViewFilter resultViewFilter)
   {
      filter = resultViewFilter;
   }   

   public void setRootLabel(String rootLabel)
   {
      this.rootLabel = rootLabel;
   }      
   
   public EditPart createEditPart(EditPart context, final Object model)
   {
      if(null == model)
      {
         return null;
      }
      else if (model instanceof ChildCategoryNode.Spec)
      {
         if (Diagram_Messages.LB_Applications.equals(((ChildCategoryNode.Spec) model).label))
         {
            return new ChildCategoryNode(editor, (ChildCategoryNode.Spec) model)
            {               
               protected List getModelChildren()
               {
                  return filter.getApplications();
               }               
            };            
         }
         else if (Diagram_Messages.DATA_LABEL.equals(((ChildCategoryNode.Spec) model).label))
         {
            return new ChildCategoryNode(editor, (ChildCategoryNode.Spec) model)
            {
               protected List getModelChildren()
               {
                  return filter.getDatas();
               }               
            };                        
         }
         else if (Diagram_Messages.LB_Participants.equals(((ChildCategoryNode.Spec) model).label))
         {
            return new ChildCategoryNode(editor, (ChildCategoryNode.Spec) model)
            {
               protected List getModelChildren()
               {
                  return filter.getParticipants();
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
            }
            protected List getModelChildren()
            {
               List result = new ArrayList();
               result.add(((DocumentRoot) getModel()).getModel());
               return result;
            }
         };
      }
      else if (model instanceof ActivityType
            || model instanceof ApplicationType
            || model instanceof IModelParticipant
            || model instanceof DataType
            || model instanceof DataPathType
            || model instanceof EventHandlerType
            || model instanceof RoleType
            || model instanceof TriggerType
            || model instanceof TransitionType
            || model instanceof EventActionTypeType
            || model instanceof ApplicationTypeType
            || model instanceof ModelerType
            || model instanceof DataTypeType
            || model instanceof LinkTypeType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
               (IIdentifiableModelElement) model, editor.getIconFactory().getIconFor((EObject) model));
      }
      else if (model instanceof INodeSymbol)
      {
         return new AbstractEObjectTreeEditPart(editor,
               (EObject) model, editor.getIconFactory().getIconFor((EObject) model))         
         {
            public String getLabel()
            {
               String name = ""; //$NON-NLS-1$
               if (model instanceof IModelElementNodeSymbol)
               {
                  IIdentifiableModelElement modelElement = ((IModelElementNodeSymbol) model).getModelElement();
                  name = modelElement.getName();
                  if (StringUtils.isEmpty(name))
                  {
                     name = modelElement.getId();
                  }
               }
               return name;
            }            
         };
      }
      else if (model instanceof ProcessDefinitionType)
      {
         return new IdentifiableModelElementTreeEditPart(editor,
            (ProcessDefinitionType) model, editor.getIconFactory().getIconFor((ProcessDefinitionType) model),
            processChildren)
         {
            protected List getModelChildren()
            {
               return filter.getProcessChildren((ProcessDefinitionType) model);
            }            
            
            protected void createEditPolicies()
            {
               if(!filter.isProcessProxy((ProcessDefinitionType) model))
               {
                  super.createEditPolicies();
               }
            }               
         };
      }
      else if (model instanceof DiagramType)
      {
         return new AbstractEObjectTreeEditPart(editor,
            (DiagramType) model, editor.getIconFactory().getIconFor((DiagramType) model))
         {
            protected void createEditPolicies()
            {
               if(!filter.isDiagramProxy((DiagramType) model))
               {
                  super.createEditPolicies();
               }               
            }            
            
            protected List getModelChildren()
            {
               return filter.getDiagramChildren((DiagramType) model);
            }

            public String getLabel()
            {
               String name = ((DiagramType) getModel()).getName();
               return !StringUtils.isEmpty(name)
                     ? name
                     : Diagram_Messages.DiagramEditor_PAGENAME_UnnamedDiagram;
            }

            public Object getAdapter(Class key)
            {
               if (IModelElement.class.equals(key))
               {
                  return getModel();
               }
               return super.getAdapter(key);
            }
         };
      }
      else if (model instanceof ModelType)
      {
         return new ResultViewModelTreeEditPart(editor, (ModelType) model,
               editor.getIconFactory().getIconFor((ModelType) model), filter)
         {
            public String getLabel()
            {
               if(rootLabel != null)
               {
                  return rootLabel;
               }
               return ((ModelType) model).getName();
            }            
         };
      }
      if (model instanceof TypeDeclarationsType)
      {         
         return new AbstractEObjectTreeEditPart(editor, (TypeDeclarationsType) model, 
               editor.getIconFactory().getIconFor((TypeDeclarationsType) model),
               new EStructuralFeature [] {XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration()})
         {
            protected List getModelChildren()
            {
               return filter.getTypeDeclarations();
            }

            public String getLabel()
            {
               return Diagram_Messages.STRUCTURED_DATA_LABEL;
            }
         };
      }
      if (model instanceof TypeDeclarationType)
      {         
         return new AbstractEObjectTreeEditPart(editor, (TypeDeclarationType) model,
               editor.getIconFactory().getIconFor((TypeDeclarationType) model))
         {
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