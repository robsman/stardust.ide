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
package org.eclipse.stardust.modeling.core;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.marker.ActivityResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.ConditionalPerformerResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.DanglingReferencesResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.DataMappingResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.DataResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.DiagramResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.IResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.IdentifiableResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.ModelResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.OrganizationResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.ProcessDefinitionResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.ReferencingModelIDMismatchResolutionGenerator;
import org.eclipse.stardust.modeling.core.marker.TransitionResolutionGenerator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationMarkerService;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class MarkerResolutionGenerator implements IMarkerResolutionGenerator2
{
   private IResolutionGenerator[] generators = {
      new IdentifiableResolutionGenerator(),
      new ActivityResolutionGenerator(),
      new DataMappingResolutionGenerator(),
      new DiagramResolutionGenerator(),
      new ConditionalPerformerResolutionGenerator(),
      new TransitionResolutionGenerator(),
      new ProcessDefinitionResolutionGenerator(),
      new DataResolutionGenerator(),
      new OrganizationResolutionGenerator(),      
      new ModelResolutionGenerator(),
      new DanglingReferencesResolutionGenerator(),
      new ReferencingModelIDMismatchResolutionGenerator()
   };

   public boolean hasResolutions(IMarker marker)
   {
      try
      {
         if (ValidationPlugin.VALIDATION_MARKER_ID.equals(marker.getType()))
         {
            Issue issue = ValidationMarkerService.getInstance().resolveMapping(marker);
            if (issue == null)
            {
               return false;
            }
            ModelType model = ModelUtils.findContainingModel(issue.getModelElement());
            WorkflowModelEditor editor = getWorkflowModelEditor(model);
            if (editor != null)
            {
               for (int i = 0; i < generators.length; i++)
               {
                  IResolutionGenerator generator = generators[i];
                  if (generator.hasResolutions(editor, issue))
                  {
                     return true;
                  }
               }
            }
         }
      }
      catch (CoreException e)
      {
         // just ignore
      }
      return false;
   }

   public IMarkerResolution[] getResolutions(IMarker marker)
   {
      try
      {
         if (ValidationPlugin.VALIDATION_MARKER_ID.equals(marker.getType()))
         {
            Issue issue = ValidationMarkerService.getInstance().resolveMapping(marker);
            ModelType model = ModelUtils.findContainingModel(issue.getModelElement());
            WorkflowModelEditor editor = getWorkflowModelEditor(model);
            if (editor != null)
            {
               List<IMarkerResolution> list = CollectionUtils.newList();
               for (int i = 0; i < generators.length; i++)
               {
                  IResolutionGenerator generator = generators[i];
                  if (generator.hasResolutions(editor, issue))
                  {
                     generator.addResolutions(list, editor, issue);
                  }
               }
               return (IMarkerResolution[]) list.toArray(new IMarkerResolution[list.size()]);
            }
         }
      }
      catch (CoreException e)
      {
         // just ignore
      }
      return null;
   }

   public static WorkflowModelEditor getWorkflowModelEditor(ModelType model)
   {
      IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         IWorkbenchWindow window = windows[i];
         IWorkbenchPage[] pages = window.getPages();
         for (int j = 0; j < pages.length; j++)
         {
            IWorkbenchPage page = pages[j];
            IEditorReference[] editors = page.getEditorReferences();
            for (int k = 0; k < editors.length; k++)
            {
               IWorkbenchPart part = editors[k].getPart(false);
               if (part instanceof WorkflowModelEditor)
               {
                  if (model == ((WorkflowModelEditor) part).getWorkflowModel())
                  {
                     return (WorkflowModelEditor) part;
                  }
               }
            }
         }
      }
      return null;
   }
}