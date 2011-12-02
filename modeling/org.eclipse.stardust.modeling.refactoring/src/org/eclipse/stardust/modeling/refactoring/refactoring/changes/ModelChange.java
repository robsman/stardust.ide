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
package org.eclipse.stardust.modeling.refactoring.refactoring.changes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.refactoring.CollectSearchCandidatesVisitor;
import org.eclipse.stardust.modeling.refactoring.RefactoringUtils;
import org.eclipse.stardust.modeling.refactoring.WorkflowModelEditorsCollector;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;
import org.eclipse.stardust.modeling.refactoring.refactoring.Refactoring_Messages;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelChange extends CompositeChange
{
   private WorkflowModelEditor editor;
   private WorkflowModelManager manager;
   private IOException exception;
   private List operators;
   private HashMap containers = new HashMap();
   private EObjectLabelProvider labelProvider;

   public ModelChange(IFile file, Object element,
                      RefactoringArguments arguments) throws CoreException
   {
      super(file.getFullPath().toString());
      this.manager = new WorkflowModelManager();
      labelProvider = new EObjectLabelProvider(null);
      try
      {
         manager.load(URI.createPlatformResourceURI(file.getFullPath().toString()));
         operators = OperatorsRegistry.instance().populate(manager.getModel(),
            element, arguments);
         addChanges();
      }
      catch(IOException x)
      {
         exception = x;
      }
   }

   public ModelChange(WorkflowModelEditor editor, Object element, RefactoringArguments arguments)
   {
      super(((FileEditorInput) editor.getEditorInput()).getFile().getFullPath().toString());
      this.editor = editor;
      labelProvider = new EObjectLabelProvider(editor);
      operators = OperatorsRegistry.instance().populate(editor.getWorkflowModel(),
         element, arguments);
      addChanges();
   }

   private ModelChange(String name, WorkflowModelManager manager, List operators)
   {
      super(name);
      this.manager = manager;
      labelProvider = new EObjectLabelProvider(null);
      this.operators = operators;
      addChanges();
   }

   private ModelChange(WorkflowModelEditor editor, List operators)
   {
      super(((FileEditorInput) editor.getEditorInput()).getFile().getFullPath().toString());
      this.editor = editor;
      labelProvider = new EObjectLabelProvider(editor);
      this.operators = operators;
      addChanges();
   }

   private void addChanges()
   {
      for (int i = 0; i < operators.size(); i++)
      {
         Change change = (Change) operators.get(i);
         EObject object = (EObject) change.getModifiedElement();
         CompositeChange composite = getCompositeFor(object);
         composite.add(change);
      }
   }

   private CompositeChange getCompositeFor(EObject object)
   {
      EObject parent = object.eContainer();
      if (parent.equals(manager == null ? editor.getWorkflowModel() : manager.getModel()))
      {
         return this;
      }
      CompositeChange composite = (CompositeChange) containers.get(parent);
      if (composite == null)
      {
         CompositeChange parentComposite = getCompositeFor(parent);
         try
         {
            composite = new CompositeChange(labelProvider.getText(parent));
         }
         catch (NullPointerException npe)
         {
            System.out.println(npe);
         }
         parentComposite.add(composite);
         containers.put(parent, composite);
      }
      return composite;
   }

   public String getName()
   {
      StringBuffer sb = new StringBuffer();
      sb.append("Workflow Model:"); //$NON-NLS-1$
      ModelType model = manager == null ? editor.getWorkflowModel() : manager.getModel();
      if (model != null)
      {
         sb.append(' ').append(model.getName());
         String version = AttributeUtil.getAttributeValue(model, CarnotConstants.VERSION_ATT);
         if (version != null && version.length() > 0)
         {
            sb.append(" ").append(version); //$NON-NLS-1$
            if (version.indexOf('.') < 0)
            {
               try
               {
                  Long.parseLong(version);
                  sb.append(".0"); //$NON-NLS-1$
               }
               catch (Exception ex)
               {
                  // append ".0" only if version is a number.
               }
            }
         }
      }
      sb.append(" (").append(super.getName()).append(')'); //$NON-NLS-1$
      return sb.toString();
   }

   public void initializeValidationData(IProgressMonitor pm)
   {
   }

   public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException
   {
      return exception == null ? new RefactoringStatus()
         : RefactoringStatus.createErrorStatus(exception.getMessage());
   }

   public Change perform(final IProgressMonitor pm) throws CoreException
   {
      // todo (fh) this completely overwrite the perform, not very good...
      final List undoChanges = new ArrayList(operators.size());
      for (int i = 0; i < operators.size(); i++)
      {
         undoChanges.add(((Change) operators.get(i)).perform(pm));
      }

      if (!undoChanges.isEmpty())
      {
         try
         {
            if (manager == null)
            {
               editor.doSave(pm);
            }
            else
            {
               manager.save(URI.createPlatformResourceURI(super.getName()));
            }
         }
         catch(IOException x)
         {
            throw new CoreException(new Status(IStatus.ERROR,
               "ag.carnot.workflow.editor.jdt.refactoring", 0, //$NON-NLS-1$
               Refactoring_Messages.MSG_UnableToSave + super.getName(), x));
         }
      }
      return manager == null ? new ModelChange(editor, undoChanges) :
         new ModelChange(getName(), manager, undoChanges);
   }

   public Object getModifiedElement()
   {
      return URI.createPlatformResourceURI(super.getName());
   }

   public boolean isEmpty()
   {
      return operators == null || operators.isEmpty();
   }

   public static Change createChange(IProgressMonitor pm, String name, Object element, RefactoringArguments arguments) throws CoreException
   {
      IProject project = RefactoringUtils.getProject((IJavaElement) element);
      
      CompositeChange composite = new CompositeChange(name);
      WorkflowModelEditorsCollector omc = new WorkflowModelEditorsCollector();
      CollectSearchCandidatesVisitor candidateCollector = new CollectSearchCandidatesVisitor(project);
      
      ResourcesPlugin.getWorkspace().getRoot().accept(candidateCollector);
      List list = candidateCollector.getFiles();

      pm.beginTask(Refactoring_Messages.MSG_ParsingModels, list.size());
      try
      {
         for (Iterator i = list.iterator(); i.hasNext();)
         {
            IFile file = (IFile) i.next();
            if (pm.isCanceled())
            {
               throw new OperationCanceledException();
            }
            List openedModels = omc.getEditors(file);
            if (openedModels.isEmpty())
            {
               try
               {
                  ModelChange change = new ModelChange(file, element, arguments);
                  if (!change.isEmpty())
                  {
                     composite.add(change);
                  }
               }
               catch (Exception e)
               {
                  // oops
                  e.printStackTrace();
               }
            }
            else
            {
               for (int j = 0; j < openedModels.size(); j++)
               {
                  WorkflowModelEditor editor = (WorkflowModelEditor) openedModels.get(j);
                  ModelChange change = new ModelChange(editor, element, arguments);
                  if (!change.isEmpty())
                  {
                     composite.add(change);
                  }
               }
            }
            pm.worked(1);
         }
      }
      finally
      {
         omc.dispose();
         pm.done();
      }
      return composite;
   }
}