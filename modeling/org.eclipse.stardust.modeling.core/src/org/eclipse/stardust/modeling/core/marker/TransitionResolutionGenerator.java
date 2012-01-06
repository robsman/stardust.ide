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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;

import ag.carnot.workflow.el.DataTypeResolver;
import ag.carnot.workflow.el.JsConverter;

public class TransitionResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      EObject element = issue.getModelElement();
      if (element instanceof TransitionType &&
            CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Expression().equals(issue.getFeature()))
      {
         TransitionType transition = (TransitionType) element;
         XmlTextNode expression = transition.getExpression();
         String expressionValue = ModelUtils.getCDataString(expression.getMixed());
         if (expressionValue != null && expressionValue.startsWith(PredefinedConstants.CARNOT_EL_PREFIX))
         {
            return true;
         }
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor, final Issue issue)
   {
      EObject element = issue.getModelElement();
      if (element instanceof TransitionType &&
            CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Expression().equals(issue.getFeature()))
      {
         list.add(new MarkerResolution(new Action("Convert to ECMAScript") //$NON-NLS-1$
         {
            public void run()
            {
               TransitionType transition = (TransitionType) issue.getModelElement();
               XmlTextNode expression = transition.getExpression();
               String condition = ModelUtils.getCDataString(expression.getMixed());
               if (condition.startsWith(PredefinedConstants.CARNOT_EL_PREFIX))
               {
                  final ModelType model = editor.getWorkflowModel();
                  JsConverter converter = new JsConverter(new DataTypeResolver()
                  {
                     public String resolveDataType(String dataId)
                     {
                        DataType data = (DataType) ModelUtils.findElementById(model.getData(), dataId);
                        if (data == null)
                        {
                           return null;
                        }
                        DataTypeType type = data.getType();
                        if (type == null)
                        {
                           return null;
                        }
                        return type.getId();
                     }
                  });
                  String jsCondition = converter.convert(condition.substring(
                        PredefinedConstants.CARNOT_EL_PREFIX.length()));
                  if (converter.isSuccessfull())
                  {
                     expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
                     ModelUtils.setCDataString(expression.getMixed(), jsCondition, true);            
                     SetValueCmd cmd = new SetValueCmd(transition,
                        CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Expression(),
                        expression);
                     editor.getEditDomain().getCommandStack().execute(cmd);
                  }
                  else
                  {
                     Exception failReason = converter.getFailReason();
                     ErrorDialog.openError(editor.getSite().getShell(), Diagram_Messages.ERROR_DIA_CONVERSION_FAILED, failReason.getMessage(),
                           new Status(IStatus.WARNING,
                                 DiagramPlugin.getDefault().getBundle().getSymbolicName(), failReason.getMessage(),
                                 failReason));
                  }
               }
            }
         }));
      }
   }
}
