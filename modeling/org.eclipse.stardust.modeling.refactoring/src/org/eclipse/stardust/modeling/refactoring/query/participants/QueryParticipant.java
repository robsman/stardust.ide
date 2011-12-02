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
package org.eclipse.stardust.modeling.refactoring.query.participants;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.ui.search.ElementQuerySpecification;
import org.eclipse.jdt.ui.search.IMatchPresentation;
import org.eclipse.jdt.ui.search.IQueryParticipant;
import org.eclipse.jdt.ui.search.ISearchRequestor;
import org.eclipse.jdt.ui.search.QuerySpecification;
import org.eclipse.search.ui.text.Match;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.refactoring.CollectSearchCandidatesVisitor;
import org.eclipse.stardust.modeling.refactoring.WorkflowModelEditorsCollector;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;


public class QueryParticipant implements IQueryParticipant
{
   private IMatchPresentation presentation;

   public int estimateTicks(QuerySpecification specification)
   {
      return 0;
   }

   public IMatchPresentation getUIParticipant()
   {
      if (presentation == null)
      {
         presentation = new MatchPresentation();
      }
      return presentation;
   }

   public void search(ISearchRequestor requestor, QuerySpecification querySpecification,
         IProgressMonitor monitor) throws CoreException
   {
      WorkflowModelEditorsCollector omc = new WorkflowModelEditorsCollector();
      try
      {
         if (querySpecification instanceof ElementQuerySpecification)
         {
            IJavaElement element =
               ((ElementQuerySpecification) querySpecification).getElement();
            if (element instanceof IType || element instanceof IMethod || element instanceof IPackageFragment)
            {
               switch (querySpecification.getLimitTo())
               {
                  case IJavaSearchConstants.REFERENCES:
                  case IJavaSearchConstants.ALL_OCCURRENCES:
                     IJavaSearchScope scope = querySpecification.getScope();
                     IPath[] paths = scope.enclosingProjectsAndJars();
                     for (int i = 0; i < paths.length; i++)
                     {
                        IPath path = paths[i];
                        if (path.segmentCount() == 1)
                        {
                           IProject project = getWorkspace().getRoot().getProject(
                              path.segment(0));
                           if (project != null && project.isOpen())
                           {
                              searchProject(requestor, project, element, monitor, omc);
                           }
                        }
                     }
               }
            }
         }
      }
      finally
      {
         omc.dispose();
      }
   }

   private void searchProject(ISearchRequestor requestor, final IProject project,
         IJavaElement element, IProgressMonitor monitor, WorkflowModelEditorsCollector omc) throws CoreException
   {
      CollectSearchCandidatesVisitor candidateCollector = new CollectSearchCandidatesVisitor(project);
      project.accept(candidateCollector);
      List files = candidateCollector.getFiles();

      for (int i = 0; i < files.size(); i++)
      {
         IFile file = (IFile) files.get(i);
         List openedModels = omc.getEditors(file);
         if (openedModels.isEmpty())
         {
            ModelType model = create(file);
            if (model != null)
            {
               addModel(file, model, element, requestor);
            }
         }
         else
         {
            for (int j = 0; j < openedModels.size(); j++)
            {
               WorkflowModelEditor editor = (WorkflowModelEditor) openedModels.get(j);
               addModel(file, editor.getWorkflowModel(), element, requestor);
            }
         }
      }
   }

   private void addModel(IFile file, ModelType model, IJavaElement element, ISearchRequestor requestor)
   {
      List matches = OperatorsRegistry.instance().search(file, model, element);
      for (int k = 0; k < matches.size(); k++)
      {
         Match match = (Match) matches.get(k);
         requestor.reportMatch(match);
      }
   }

   private ModelType create(IFile file)
   {
      ModelType model = null;
      WorkflowModelManager modelManager = new WorkflowModelManager();

      if (file.exists())
      {
         try
         {
            modelManager.load(URI.createPlatformResourceURI(
               file.getFullPath().toString()));
            model = modelManager.getModel();
         }
         catch (Exception e)
         {
            // just ignore
         }
      }
      return model;
   }

   private IWorkspace getWorkspace()
   {
      return ResourcesPlugin.getWorkspace();
   }
}