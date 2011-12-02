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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ValidationIssueManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelOutlinePage;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.RepositoryPackage;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PartInitException;


public class ModelTreeEditPart extends AbstractEObjectTreeEditPart
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private ChildCategoryNode.Spec catApplications;
   private ChildCategoryNode.Spec catData;
   private ChildCategoryNode.Spec catParticipants;
   private ChildCategoryNode.Spec catConnections;

   protected ModelTreeEditPart(WorkflowModelEditor editor, ModelType model, String iconPath)
   {
      super(editor, model, iconPath, new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ProcessDefinition(),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_Diagram(),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_LinkType()});

      IIdentifiableModelElement parent = ModelUtils.getIdentifiableModelProxy(model, ModelType.class);
      ValidationIssueManager manager = ((WorkflowModelEditor) getEditor()).getIssueManager();
      
      catApplications = new ChildCategoryNode.Spec(parent,
            Diagram_Messages.LB_Applications, editor.getIconFactory().getIconFor(PKG_CWM.getApplicationType()),
            new EStructuralFeature[] {PKG_CWM.getModelType_Application()});
      catData = new ChildCategoryNode.Spec(parent,
            Diagram_Messages.DATA_LABEL, editor.getIconFactory().getIconFor(PKG_CWM.getDataType()),
            new EStructuralFeature[] {PKG_CWM.getModelType_Data()});      
      catParticipants = new ChildCategoryNode.Spec(parent,
            Diagram_Messages.LB_Participants, editor.getIconFactory().getIconFor(PKG_CWM.getIModelParticipant()),
            new EStructuralFeature[] {
                  PKG_CWM.getModelType_Organization(), PKG_CWM.getModelType_Role(),
                  PKG_CWM.getModelType_ConditionalPerformer(),
                  PKG_CWM.getModelType_Modeler()});
      manager.addValidationEventListener(catApplications);      
      manager.addValidationEventListener(catData);      
      manager.addValidationEventListener(catParticipants);
      if (hasConnectionExtensions())
      {
         catConnections = new ChildCategoryNode.Spec(editor.getConnectionManager().getRepository(),
            Diagram_Messages.CONNECTION_LABEL, ObjectRepositoryActivator.getIcon(),
            new EStructuralFeature[] {
                  RepositoryPackage.eINSTANCE.getRepository_Connection()});
         manager.addValidationEventListener(catConnections);
      }
   }

   private boolean hasConnectionExtensions()
   {
      Map<String, IConfigurationElement> map = SpiExtensionRegistry.instance().getExtensions(ObjectRepositoryActivator.PLUGIN_ID,
            ObjectRepositoryActivator.CONNECTION_EXTENSION_POINT_ID);
      return map != null && !map.isEmpty();
   }

   protected void createEditPolicies()
   {
      super.createEditPolicies();
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
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
      return ((ModelType) getModel()).getName();
   }

   protected List<?> getModelChildren()
   {
      List<Object> result = new ArrayList<Object>();

      if (hasConnectionExtensions())
      {
         result.add(catConnections);
      }
      ExternalPackages externalPackages = ((ModelType) getModel()).getExternalPackages();
      if (externalPackages != null && !externalPackages.getExternalPackage().isEmpty())
      {
         result.add(externalPackages);
      }
      result.add(((ModelType) getModel()).getTypeDeclarations());
      result.add(catApplications);
      result.add(catData);
      result.add(catParticipants);

      List<?> modelChildren = super.getModelChildren();
      result.addAll(modelChildren);

      return result;
   }

   @SuppressWarnings("unchecked")
   public Object getAdapter(Class key)
   {
      if (key.equals(IModelElement.class))
      {
         return ModelUtils.getIdentifiableModelProxy((ModelType) getModel(), ModelType.class);
      }
      return super.getAdapter(key);
   }

   public void handleNotification(Notification n)
   {
      super.handleNotification(n);
      // when replacing Objects via Collision merge 
      // we must close all affected Diagrams 
      if (n != null && PKG_CWM.getModelType_ProcessDefinition().equals(n.getFeature()))
      {
         if(n.getEventType() == Notification.SET)
         {
            ProcessDefinitionType process = (ProcessDefinitionType) n.getOldValue();
            EList<DiagramType> diagrams = process.getDiagram();
            for (DiagramType diagramType : diagrams)
            {
               getEditor().closeDiagramPage(diagramType);            
            }
         }
      }      
      else if (n != null && PKG_CWM.getModelType_Diagram().equals(n.getFeature()))
      {
         if(n.getEventType() == Notification.SET)
         {
            DiagramType diagram = (DiagramType) n.getOldValue();
            DiagramType activeDiagram = getEditor().getActiveDiagram();
            getEditor().closeDiagramPage(diagram);            
            
            if(activeDiagram != null && activeDiagram.equals(diagram))
            {
               try
               {
                  getEditor().showDiagramPage((DiagramType) n.getNewValue());
               }
               catch (PartInitException e)
               {
                  // do nothing
               }               
            }
         }         
      }
   }
}