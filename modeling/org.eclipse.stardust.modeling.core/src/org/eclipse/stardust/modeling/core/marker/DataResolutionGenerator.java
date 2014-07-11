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
package org.eclipse.stardust.modeling.core.marker;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.UpgradeDataAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;

public class DataResolutionGenerator implements IResolutionGenerator
{
   private IAction upgradeAction;

   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (notJavaBoundEnumerationIssue(issue))
      {
         return true;
      }

      if (isPrimitiveDefaultValueIssue(issue))
      {
         return true;
      }

      EObject element = issue.getModelElement();
      if (element instanceof DataType)
      {
         upgradeAction = getAction(editor, element, DiagramActionConstants.DATA_UPGRADE);
         if (upgradeAction != null && upgradeAction.isEnabled())
         {
            return true;
         }
      }
      return false;
   }

   private boolean notJavaBoundEnumerationIssue(Issue issue)
   {
      EObject element = issue.getModelElement();
      if (element instanceof DataType
         && PredefinedConstants.PRIMITIVE_DATA.equals(((DataType) element).getType().getId())
         && Type.Enumeration.getId().equals(AttributeUtil.getAttributeValue((DataType) element, PredefinedConstants.TYPE_ATT)))
      {
         EObject ref = AttributeUtil.getIdentifiable((IExtensibleElement) element, StructuredDataConstants.TYPE_DECLARATION_ATT);
         if(ref != null && ref instanceof TypeDeclarationType)
         {
            return TypeDeclarationUtils.isEnumeration((TypeDeclarationType) ref, false);
         }
      }

      return false;
   }

   private boolean isPrimitiveDefaultValueIssue(Issue issue)
   {
      EObject element = issue.getModelElement();
      return element instanceof DataType
            && !element.eIsProxy()
            && PredefinedConstants.DEFAULT_VALUE_ATT.equals(issue.getFeature())
            && ((DataType) element).getType() != null
            && PredefinedConstants.PRIMITIVE_DATA.equals(((DataType) element).getType().getId())
            && Type.Enumeration.getId().equals(AttributeUtil.getAttributeValue((DataType) element, PredefinedConstants.TYPE_ATT));
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor, final Issue issue)
   {
      if (isPrimitiveDefaultValueIssue(issue))
      {
         DataType data = (DataType) issue.getModelElement();
         TypeDeclarationType decl = StructuredTypeUtils.getTypeDeclaration(data);
         Object[] facets = StructuredTypeUtils.getFacets(decl);
         for (int i = 0; i < facets.length; i++)
         {
            final String value = facets[i].toString();
            Action action = new SelectionAction(editor)
            {
               public void run()
               {
                  DataType data = (DataType) issue.getModelElement();
                  Command cmd;
                  AttributeType attribute = AttributeUtil.getAttribute(data, PredefinedConstants.DEFAULT_VALUE_ATT);
                  if (attribute == null)
                  {
                     attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
                     attribute.setName(PredefinedConstants.DEFAULT_VALUE_ATT);
                     attribute.setValue(value);
                     cmd = new SetValueCmd(data, CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute(), attribute);
                  }
                  else
                  {
                     cmd = new SetValueCmd(attribute, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(), value);
                  }
                  if (cmd != null)
                  {
                     execute(cmd);
                  }
               }

               @Override
               protected boolean calculateEnabled()
               {
                  return value != null;
               }
            };
            action.setText("Set the default value to '" + value + "'.");
            list.add(new MarkerResolution(action, DiagramPlugin.getImage(
                  editor.getIconFactory().getIconFor(issue.getModelElement()))));
         }
      }
      else if (notJavaBoundEnumerationIssue(issue))
      {
         Action action = new SelectionAction(editor)
         {
            public void run()
            {
               DataType data = (DataType) issue.getModelElement();
               ModelType model = ModelUtils.findContainingModel(data);

               Command cmd = null;
               AttributeType attribute = AttributeUtil.getAttribute(data, PredefinedConstants.DEFAULT_VALUE_ATT);
               if (attribute != null)
               {
                  cmd = new DeleteValueCmd(attribute.eContainer(), attribute, attribute.eContainingFeature());
               }
               if (cmd != null)
               {
                  execute(cmd);
               }

               attribute = AttributeUtil.getAttribute(data, PredefinedConstants.TYPE_ATT);
               if (attribute != null)
               {
                  cmd = new DeleteValueCmd(attribute.eContainer(), attribute, attribute.eContainingFeature());
               }
               if (cmd != null)
               {
                  execute(cmd);
               }

               cmd = new SetValueCmd(data, CarnotWorkflowModelPackage.eINSTANCE.getDataType_Type(), GenericUtils.getDataTypeType(model, PredefinedConstants.STRUCTURED_DATA));
               if (cmd != null)
               {
                  execute(cmd);
               }
            }

            @Override
            protected boolean calculateEnabled()
            {
               return true;
            }
         };

         action.setText(Diagram_Messages.LB_ACTION_ChangeDataType);
         list.add(new MarkerResolution(action, DiagramPlugin.getImage(
               editor.getIconFactory().getIconFor(issue.getModelElement()))));
      }
      else
      {
         list.add(new MarkerResolution(upgradeAction));
      }
   }

   private IAction getAction(final WorkflowModelEditor editor,
         final EObject element, String actionId)
   {
      if (actionId == DiagramActionConstants.DATA_UPGRADE)
      {
         return new UpgradeDataAction(editor)
         {
            protected ISelection getSelection()
            {
               EditPart editPart = editor.findEditPart(element);
               return editPart == null ? StructuredSelection.EMPTY : new StructuredSelection(editPart);
            }
         };
      }
      return null;
   }
}