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
package org.eclipse.stardust.modeling.core.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ValidationIssueManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.ValidatorRegistry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class VerifyingChangeRecorder extends ChangeRecorder
{
   private final ValidationJob validationTask;

   private final ValidationIssueManager issueManager;

   private ModelType model;

   private IModelElement element;

   private IModelElementNodeSymbol nodeSymbol;

   public VerifyingChangeRecorder(WorkflowModelEditor editor, ValidationIssueManager issueManager)
   {
      this.validationTask = new ValidationJob(DiagramPlugin.getViewAsPerspectiveId(editor));

      this.issueManager = issueManager;
   }

   public void beginRecording(ModelType model, IModelElement element)
   {
      this.model = model;
      if (element instanceof IModelElementNodeSymbol)
      {
         this.nodeSymbol = (IModelElementNodeSymbol) element;
         this.element = nodeSymbol.getModelElement();
      }
      else
      {
         this.nodeSymbol = null;
         this.element = element;
      }
      super.beginRecording(Collections.singleton(model));

      performElementValidation(false);
   }

   public ChangeDescription endRecording()
   {
      ChangeDescription result = super.endRecording();
      dispose();

      this.nodeSymbol = null;
      this.element = null;
      this.model = null;

      return result;
   }

   public void dispose()
   {
      validationTask.cancel();

      super.dispose();
   }

   protected void handleFeature(EStructuralFeature feature, EReference containment,
         Notification notification, EObject eObject)
   {
      // actually record the change
	   
	  //This is a workaround for bug within the XSD stuff (CRNT-13177) 
	  if (feature.getContainerClass() != null && feature.getContainerClass().getName().equalsIgnoreCase("org.eclipse.xsd.XSDEnumerationFacet")) { //$NON-NLS-1$
		  if (feature.getEType() != null) 
		  {
			  feature.getEType().setInstanceClass(String.class);  
		  }		  
	  }
      super.handleFeature(feature, containment, notification, eObject);

      // trigger delayed revalidation, to fold multiple changes into one validation job
      performElementValidation(true);
   }

   public void performElementValidation(boolean delayed)
   {
      validationTask.cancel();
      validationTask.schedule(delayed ? 500 : 0);
   }

   private class ValidationJob extends Job
   {
      private Map filters = new HashMap();

      public ValidationJob(String perspectiveId)
      {
         super(Diagram_Messages.TXT_WorkflowModelValidation);
         if (perspectiveId != null)
         {
            filters.put("perspectiveType", perspectiveId); //$NON-NLS-1$
         }
      }

      protected IStatus run(IProgressMonitor monitor)
      {
         Issue[] issues = Issue.ISSUE_ARRAY;
         Issue[] modelIssues = Issue.ISSUE_ARRAY;

         ValidationService vs = ValidationService.getInstance(); 

         try
         {
            ValidatorRegistry.setFilters(filters);
            vs.setProgressMonitor(monitor);

            if (null != model)
            {
               modelIssues = vs.validateModel(model);
            }
            
            if (null != element)
            {
               issues = vs.validateModelElement(element);
   
               if (null != nodeSymbol)
               {
                  Issue[] symbolIssues = vs.validateModelElement(nodeSymbol);
   
                  if (0 < symbolIssues.length)
                  {
                     Issue[] allIssues = new Issue[issues.length + symbolIssues.length];
                     System.arraycopy(issues, 0, allIssues, 0, issues.length);
                     System.arraycopy(symbolIssues, 0, allIssues, issues.length,
                           symbolIssues.length);
   
                     issues = allIssues;
                  }
               }
            }

            if(modelIssues.length > 0 && element instanceof ModelType)
            {
               Issue[] allIssues = new Issue[issues.length + modelIssues.length];
               System.arraycopy(issues, 0, allIssues, 0, issues.length);
               System.arraycopy(modelIssues, 0, allIssues, issues.length,
                     modelIssues.length);

               issues = allIssues;               
            }          
   
            if (monitor.isCanceled())
            {
               return Status.CANCEL_STATUS;
            }
            if (null != issueManager)
            {
               final Issue[] issuesToBePropagated = issues;
   
               Display display;
               if (PlatformUI.isWorkbenchRunning())
               {
                  display = PlatformUI.getWorkbench().getDisplay();
               }
               else
               {
                  display = Display.getCurrent();
               }
   
               if (null != display)
               {
                  display.syncExec(new Runnable()
                  {
                     public void run()
                     {
                        issueManager.resetIssues(issuesToBePropagated);
                     }
                  });
               }
               else
               {
                  issueManager.resetIssues(issuesToBePropagated);
               }
            }
         }
         finally
         {
            ValidatorRegistry.setFilters(null);
            vs.setProgressMonitor(null);
         }

         return Status.OK_STATUS;
      }
   }
}