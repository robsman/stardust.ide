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
package org.eclipse.stardust.modeling.debug.launching;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaMethodBreakpoint;
import org.eclipse.jdt.debug.core.JDIDebugModel;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.ObjectNotFoundException;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.Debugger;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.TransitionTokenDigest;
import org.eclipse.stardust.modeling.debug.engine.ManagedRunnerHelper;
import org.eclipse.stardust.modeling.debug.highlighting.HighlightManager;
import org.eclipse.stardust.modeling.debug.model.CWMDebugTarget;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.ValidatorRegistry;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.progress.UIJob;

/**
 * @author sborn
 * @version $Revision$
 */
public class LaunchDelegate extends JavaLaunchDelegate
{
   private static void abort(String message, Throwable e) throws CoreException
   {
      throw new CoreException(new Status(IStatus.ERROR, Constants.ID_CWM_DEBUG_MODEL, 0,
            message, e));
   }
   
   private static void validate(ILaunchConfiguration configuration) throws CoreException
   {
      // project
      String projectName = LaunchConfigUtils.getProjectName(configuration);
      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
      if (null == project)
      {
         abort(MessageFormat.format(Debug_Messages.MSG_ProjectDoesNotExist,
               new Object[] { projectName }), null);
      }
      
      // model file name
      String modelFile = LaunchConfigUtils.getModelFileName(configuration);
      IFile file = project.getFile(new Path(modelFile));
      if (!file.exists())
      {
         abort(MessageFormat.format(Debug_Messages.MSG_ModelFileDoesNotExist,
               file.getFullPath().toString()), null);
      }

      UiAccessor.loadModel(file, true);

      // TODO (sb): remove this necessity by loading model on demand
      if ( !UiAccessor.isModelLoaded(file))
      {
         abort(MessageFormat.format(Debug_Messages.MSG_ModelFileNeedsToBeLoaded,
               file.getFullPath().toString()), null);
      }

      // process definition
      String procDef = LaunchConfigUtils.getProcessDefinitionId(configuration);
      if (StringUtils.isEmpty(procDef))
      {
         abort(Debug_Messages.MSG_ProcessDefinitionUnspecified, null);
      }

      // TODO (sb): validate process definition

   }

   private static ProcessDefinitionType getProcessDefinition(
         ILaunchConfiguration configuration) throws CoreException
   {
      String procDefId = LaunchConfigUtils.getProcessDefinitionId(configuration);
      Assert.isNotEmpty(procDefId, Debug_Messages.MSG_ProcessDefIdEmpty);

      boolean found = false;
      ProcessDefinitionType processDefinition = null;

      IEditorPart editPart = UiAccessor.getActiveEditPart();
      if (null != editPart && editPart instanceof WorkflowModelEditor)
      {
         WorkflowModelEditor editor = (WorkflowModelEditor) editPart;

         ModelType model = editor.getWorkflowModel();

         EList pdList = model.getProcessDefinition();
         for (Iterator i = pdList.iterator(); i.hasNext();)
         {
            processDefinition = (ProcessDefinitionType) i.next();
            if (processDefinition.getId().equals(procDefId))
            {
               found = true;
               break;
            }
         }
      }

      if ( !found)
      {
         throw new ObjectNotFoundException(MessageFormat.format(
               Debug_Messages.MSG_ProcessDefWithIdNotFound, procDefId));
      }

      return processDefinition;
   }
   
   public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
         IProgressMonitor monitor) throws CoreException
   {
      if (mode.equals(ILaunchManager.DEBUG_MODE))
      {
         HighlightManager.getDefault();
         validate(configuration);

         ProcessDefinitionType pd = getProcessDefinition(configuration);
         WorkflowModelEditor editor = UiAccessor.activateDiagram(null, (DiagramType) pd.getDiagram().get(0));

         ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();

         boolean analystMode = DiagramPlugin.isBusinessView(editor);
         
         if (null != editor)
         {
            boolean hasIssues = hasIssues(editor,
                  DiagramPlugin.getViewAsPerspectiveId(null));
            boolean hasAnalystIssues = hasIssues(editor,
                  "ag.carnot.workflow.modeler.businessModelingPerspective"); //$NON-NLS-1$

            if (hasIssues && !analystMode && !hasAnalystIssues)
            {
               int choice = showYesNoCancel(editor.getSite().getShell(),
                     Debug_Messages.LaunchDelegate_WARNING,
                     Debug_Messages.LaunchDelegate_ModelInconsistencies,
                     Debug_Messages.LaunchDelegate_MSG_SuggestAnalystSession, true);
               
               if (0 == choice)
               {
                  analystMode = true;
                  setMode(editor, analystMode);
               }
               else if (2 == choice)
               {
                  lm.removeLaunch(launch);
                  return;
               }
            }
            else if (hasIssues)
            {
               int choice = showYesCancel(editor.getSite().getShell(),
                     Debug_Messages.LaunchDelegate_WARNING,
                     Debug_Messages.LaunchDelegate_ModelInconsistencies,
                     Debug_Messages.LaunchDelegate_MSG_InconsistentModel, true);

               if (1 == choice)
               {
                  lm.removeLaunch(launch);
                  return;
               }
            }
         }

         IDebugTarget[] targets = lm.getDebugTargets();
         for (int i = 0; i < targets.length; i++)
         {
            if (targets[i] instanceof CWMDebugTarget)
            {
               CWMDebugTarget cwmTarget = (CWMDebugTarget) targets[i];
               if (!cwmTarget.isTerminated())
               {
                  if (cwmTarget.getEditor().equals(editor))
                  {
                     showMessage(editor.getSite().getShell(),
                           Debug_Messages.LaunchDelegate_ERROR,
                           Debug_Messages.LaunchDelegate_CanNotLaunch,
                           Debug_Messages.LaunchDelegate_MSG_OneSessionPerEditor, false);
                     lm.removeLaunch(launch);
                     return;
                  }
                  if (analystMode != cwmTarget.isAnalystMode())
                  {
                     showMessage(editor.getSite().getShell(),
                        Debug_Messages.LaunchDelegate_ERROR,
                        Debug_Messages.LaunchDelegate_CanNotLaunch,
                        MessageFormat.format(Debug_Messages.LaunchDelegate_MSG_IncompatibleSessions,
                           getModeName(analystMode), getModeName(cwmTarget.isAnalystMode())),
                        false);
                     lm.removeLaunch(launch);
                     return;
                  }
               }
            }
         }
         
         DebugPlugin.getDefault().addDebugEventListener(
               new CommunicationBreakpointInitializer());
         
         final boolean finalAnalystMode = analystMode;
         final ILaunchConfiguration finalConfiguration = configuration;
         ILaunchConfiguration cfg = (ILaunchConfiguration) Proxy.newProxyInstance(
               getClass().getClassLoader(),
               new Class[] {ILaunchConfiguration.class}, new InvocationHandler()
         {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
               Object result = method.invoke(finalConfiguration, args);
               if ("getAttribute".equals(method.getName()))
               {
                  if (IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS.equals(args[0]))
                  {
                     StringBuilder sb = new StringBuilder();
                     sb.append(result);
                     sb.append(' ');
                     sb.append('"');
                     sb.append(Constants.CMDLINE_ARG_VIEW_TYPE);
                     sb.append(finalAnalystMode ? "analyst" : "developer"); //$NON-NLS-1$ //$NON-NLS-2$
                     sb.append('"');
                     List<String> deps = LaunchConfigUtils.getDependencies(finalConfiguration);
                     if (deps != null)
                     {
                        for (String dep : deps)
                        {
                           sb.append(' ');
                           sb.append('"');
                           sb.append(Constants.CMDLINE_ARG_DEPENDENCY_FILE);
                           sb.append(dep);
                           sb.append('"');
                        }
                     }
                     result = sb.toString();
                  }
               }
               return result;
            }
         });
         
         super.launch(cfg, mode, launch, monitor);

         IDebugTarget javaTarget = launch.getDebugTarget();
         final CWMDebugTarget cwmTarget = new CWMDebugTarget(editor, javaTarget, pd, analystMode);
         cwmTarget.setSourceName(LaunchConfigUtils.getModelFileName(configuration));

         launch.removeDebugTarget(javaTarget);
         launch.addDebugTarget(cwmTarget);
      }
   }

   private void setMode(final WorkflowModelEditor editor, final boolean analystMode)
   {
      UIJob job = new UIJob("Change Mode")
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            BpmUiActivator.getDefault().setAnalystMode(editor.getSite().getPage(), analystMode);
            return Status.OK_STATUS;
         }
      };
      job.schedule();
      try
      {
         job.join();
      }
      catch (InterruptedException e)
      {
//         e.printStackTrace();
      }
   }

   private String getModeName(boolean analystMode)
   {
      return analystMode ? Debug_Messages.LaunchDelegate_MODE_Analyst : Debug_Messages.LaunchDelegate_MODE_Developer;  
   }

   private boolean hasIssues(WorkflowModelEditor editor, String perspective)
   {
      ValidationJob job = new ValidationJob(perspective,
            editor.getWorkflowModel());
      job.schedule();
      try
      {
         job.join();
      }
      catch (InterruptedException e)
      {
      }
      boolean hasIssues = job.hasIssues();
      return hasIssues;
   }

   private boolean showMessage(final Shell shell, String name,
         final String title, final String message, final boolean confirm)
   {
      final boolean[] result = new boolean[] {true};
      UIJob job = new UIJob(name)
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            if (confirm)
            {
               result[0] = MessageDialog.openConfirm(shell, title, message);
            }
            else
            {
               MessageDialog.openError(shell, title, message);
            }
            return Status.OK_STATUS;
         }
      };
      job.schedule();
      try
      {
         job.join();
      }
      catch (InterruptedException e)
      {
//         e.printStackTrace();
      }
      return result[0];
   }
   
   private int showYesNoCancel(final Shell shell, String name,
         final String title, final String message, final boolean confirm)
   {
      final int[] result = new int[1];
      UIJob job = new UIJob(name)
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            if (confirm)
            {
               MessageDialog dialog = new MessageDialog(shell, title, null, // accept
                     message, MessageDialog.QUESTION, new String[] {
                           IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL,
                           IDialogConstants.CANCEL_LABEL}, 0); // yes is the default
               result[0] = dialog.open();
            }
            else
            {
               MessageDialog.openError(shell, title, message);
            }
            return Status.OK_STATUS;
         }
      };
      job.schedule();
      try
      {
         job.join();
      }
      catch (InterruptedException e)
      {
//         e.printStackTrace();
      }
      return result[0];
   }
   
   private int showYesCancel(final Shell shell, String name,
         final String title, final String message, final boolean confirm)
   {
      final int[] result = new int[1];
      UIJob job = new UIJob(name)
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            if (confirm)
            {
               MessageDialog dialog = new MessageDialog(shell, title, null, // accept
                     message, MessageDialog.QUESTION, new String[] {
                           IDialogConstants.YES_LABEL, IDialogConstants.CANCEL_LABEL}, 0); // yes is the default
               result[0] = dialog.open();
            }
            else
            {
               MessageDialog.openError(shell, title, message);
            }
            return Status.OK_STATUS;
         }
      };
      job.schedule();
      try
      {
         job.join();
      }
      catch (InterruptedException e)
      {
//         e.printStackTrace();
      }
      return result[0];
   }
   
   /**
    * This debug event listener is responsible for communication breakpoint installation.
    * It removes itself after its work is done.
    *  
    * @author sborn
    * @version $Revision$
    */
   private static class CommunicationBreakpointInitializer implements IDebugEventSetListener
   {
      private static IJavaMethodBreakpoint createBreakpoint(IDebugTarget target,
            IResource resource, Class type, String methodName, String signature)
            throws CoreException
      {
         // TODO: do not create a new one if this breakpoint already exists 
         IJavaMethodBreakpoint bp = JDIDebugModel.createMethodBreakpoint(
               resource, 
               type.getName(), 
               methodName, 
               signature, 
               true, false,   // entry?, exit?
               false,         // native methods only
               -1, -1, -1,    // Marker position and character range
               0,             // hit count
               false,         // register with breakpoint manager
               null           // map with attributes or null
         );
         
         bp.setPersisted(false);
         target.breakpointAdded(bp);
         
         return bp;
      }
      
      private String jniVoidSignature()
      {
         return "V"; //$NON-NLS-1$
      }
      
      private String jniSignature(Class type)
      {
         if (null == type)
         {
            return jniVoidSignature();
         }

         StringBuffer signature = new StringBuffer();
         if (type.isArray())
         {
            signature.append('['); 
         }

         // TODO: primitive type arrays (i.e. boolean[]) do not work with this implementation
         if (boolean.class.equals(type))
         {
            signature.append('Z'); 
         }
         else if (byte.class.equals(type))
         {
            signature.append('B'); 
         }
         else if (char.class.equals(type))
         {
            signature.append('C'); 
         }
         else if (double.class.equals(type))
         {
            signature.append('D'); 
         }
         else if (float.class.equals(type))
         {
            signature.append('F'); 
         }
         else if (int.class.equals(type))
         {
            signature.append('I'); 
         }
         else if (long.class.equals(type))
         {
            signature.append('J'); 
         }
         else if (short.class.equals(type))
         {
            signature.append('S'); 
         }
         else
         {
            signature
                  .append('L')
                  .append(StringUtils.replace(type.getName(), ".", "/")) //$NON-NLS-1$ //$NON-NLS-2$
                  .append(';');
         }

         return signature.toString();
      }
      
      public void handleDebugEvents(DebugEvent[] events)
      {
         for (int i = 0; i < events.length; i++)
         {
            DebugEvent event = events[i];
            if (event.getKind() == DebugEvent.CREATE
                  && event.getSource() instanceof IJavaDebugTarget)
            {
               IJavaDebugTarget target = (IJavaDebugTarget) event.getSource();
               ILaunch launch = target.getLaunch();
               if (launch != null)
               {
                  ILaunchConfiguration configuration = launch.getLaunchConfiguration();
                  if (configuration != null)
                  {
                     try
                     {
                        IDebugTarget javaTarget = launch.getDebugTarget();

                        // Add a few breakpoints for communication reasons
                        String project = LaunchConfigUtils.getProjectName(configuration);
                        String modelFile = LaunchConfigUtils.getModelFileName(configuration);
                        IResource modelResource = ResourcesPlugin.getWorkspace()
                              .getRoot().getProject(project).findMember(modelFile);

                        String signature = MessageFormat.format("({0}){1}", //$NON-NLS-1$
                              jniSignature(TransitionTokenDigest.class),
                              jniVoidSignature());
                        createBreakpoint(javaTarget, modelResource,
                              Debugger.WorkflowEventListenerImpl.class, Debugger.WorkflowEventListenerImpl.performedTransitionMethodName, signature); 

                        signature = MessageFormat.format("({0}){1}", //$NON-NLS-1$
                              jniSignature(ActivityInstanceDigest.class),
                              jniVoidSignature());
                        createBreakpoint(javaTarget, modelResource,
                              Debugger.WorkflowEventListenerImpl.class, Debugger.WorkflowEventListenerImpl.startedActivityInstanceMethodName, 
                              signature);
                        createBreakpoint(javaTarget, modelResource,
                              Debugger.WorkflowEventListenerImpl.class, Debugger.WorkflowEventListenerImpl.completedActivityInstanceMethodName, 
                              signature);

                        signature = MessageFormat.format("(){0}", //$NON-NLS-1$
                              jniVoidSignature());
                        createBreakpoint(javaTarget, modelResource,
                              ManagedRunnerHelper.class, ManagedRunnerHelper.suspendThreadMethodName,
                              signature);
                        
                        DebugPlugin.getDefault().removeDebugEventListener(this);
                     }
                     catch (CoreException e)
                     {
                     }
                  }
               }
            }
         }
      }
   }
   
   private static class ValidationJob extends Job
   {
      private Map filters = new HashMap();
      private ModelType model;
      private Issue[] issues;

      public ValidationJob(String perspectiveId, ModelType model)
      {
         super(Diagram_Messages.TXT_WorkflowModelValidation);
         this.model = model; 
         if (perspectiveId != null)
         {
            filters.put("perspectiveType", perspectiveId); //$NON-NLS-1$
         }
      }

      protected IStatus run(IProgressMonitor monitor)
      {
         ValidationService vs = ValidationPlugin.getDefault().getValidationService(); 

         try
         {
            ValidatorRegistry.setFilters(filters);
            vs.setProgressMonitor(monitor);
            
            issues = vs.validateModel(model);
   
            if (monitor.isCanceled())
            {
               return Status.CANCEL_STATUS;
            }
         }
         catch (Exception ex)
         {
            ex.printStackTrace();
         }
         finally
         {
            ValidatorRegistry.setFilters(null);
            vs.setProgressMonitor(null);
         }

         return Status.OK_STATUS;
      }
      
      public boolean hasIssues()
      {
         return issues != null && issues.length > 0;
      }
   }
}
