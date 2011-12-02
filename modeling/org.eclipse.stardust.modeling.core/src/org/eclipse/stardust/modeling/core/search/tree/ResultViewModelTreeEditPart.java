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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;


public class ResultViewModelTreeEditPart extends AbstractEObjectTreeEditPart
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private ChildCategoryNode.Spec catApplications;
   private ChildCategoryNode.Spec catData;
   private ChildCategoryNode.Spec catParticipants;

   private ResultViewFilter filter = null;   
   
   protected ResultViewModelTreeEditPart(WorkflowModelEditor editor, ModelType model, String iconPath, ResultViewFilter filter)
   {
      super(editor, model, iconPath, new EStructuralFeature[] {
            PKG_CWM.getModelType_ProcessDefinition(),
            PKG_CWM.getModelType_Diagram(),
            PKG_CWM.getModelType_LinkType()});
      this.filter = filter;
      
      IIdentifiableModelElement parent = ModelUtils.getIdentifiableModelProxy(model, ModelType.class);
      
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
   }

   protected void createEditPolicies()
   {
      super.createEditPolicies();
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
   }
   
   public String getLabel()
   {
      return ((ModelType) getModel()).getName();
   }

   protected List getModelChildren()
   {
      List result = new ArrayList();

      result.add(((ModelType) getModel()).getTypeDeclarations());
      result.add(catApplications);
      result.add(catData);
      result.add(catParticipants);
      
      result.addAll(filter.getModelChildren());

      return result;
   }

   public Object getAdapter(Class key)
   {
      if (key.equals(IModelElement.class))
      {
         return ModelUtils.getIdentifiableModelProxy((ModelType) getModel(), ModelType.class);
      }
      return super.getAdapter(key);
   }
}