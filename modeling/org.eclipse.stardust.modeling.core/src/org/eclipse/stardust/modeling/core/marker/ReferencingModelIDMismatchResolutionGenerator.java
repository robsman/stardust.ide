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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;


public class ReferencingModelIDMismatchResolutionGenerator
      implements IResolutionGenerator
{

   public void addResolutions(List<IMarkerResolution> list,
         final WorkflowModelEditor editor, final Issue issue)
   {
      if (issue.getFeature().equals(XpdlPackage.eINSTANCE.getExternalPackage_Href()))
      {
         list.add(new MarkerResolution(new Action(
               Diagram_Messages.LB_ACTION_AdjustReferenceID)
         {
            public void run()
            {
               ExternalPackage externalPackage = (ExternalPackage) issue
                     .getModelElement();
               ModelType referencingModel = editor.getWorkflowModel();
               IConnectionManager connectionManager = referencingModel
                     .getConnectionManager();
               ModelType referencedModel = connectionManager.find(externalPackage);
               SetValueCmd cmd = new SetValueCmd(externalPackage, XpdlPackage.eINSTANCE
                     .getExternalPackage_Href(), referencedModel.getId());
               editor.getEditDomain().getCommandStack().execute(cmd);
               cmd = new SetValueCmd(externalPackage, XpdlPackage.eINSTANCE
                     .getExternalPackage_Id(), referencedModel.getId());
               editor.getEditDomain().getCommandStack().execute(cmd);
               cmd = new SetValueCmd(externalPackage, XpdlPackage.eINSTANCE
                     .getExternalPackage_Name(), referencedModel.getName());
               editor.getEditDomain().getCommandStack().execute(cmd);
            }
         }));
      }
   }

   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getModelElement() instanceof ExternalPackage)
      {
         ExternalPackage externalPackage = (ExternalPackage) issue.getModelElement();
         List<IModelElement> modelElements = ModelUtils.findPackageReferingModelElements(
               editor.getWorkflowModel(), externalPackage);
         for (Iterator<IModelElement> i = modelElements.iterator(); i.hasNext();)
         {
            IModelElement modelElement = i.next();
            if (!editor.getModelServer().requireLock(modelElement))
            {
               if (issue.getFeature().equals(
                     XpdlPackage.eINSTANCE.getExternalPackage_Href()))
               {
                  ModelType referencingModel = editor.getWorkflowModel();
                  IConnectionManager connectionManager = referencingModel
                        .getConnectionManager();
                  ModelType referencedModel = connectionManager.find(externalPackage);
                  if (referencedModel != null
                        && !referencedModel.getId().equals(externalPackage.getHref()))
                  {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }

}
