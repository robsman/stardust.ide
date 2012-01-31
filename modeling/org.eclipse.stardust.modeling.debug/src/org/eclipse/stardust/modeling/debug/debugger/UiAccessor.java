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
package org.eclipse.stardust.modeling.debug.debugger;

import java.text.MessageFormat;
import java.util.Set;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DataMappingDigest;
import org.eclipse.stardust.modeling.debug.model.CWMDebugTarget;
import org.eclipse.stardust.modeling.debug.model.ManualApplicationDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

public class UiAccessor
{
   public static boolean isModelLoaded(IFile file)
   {
      IEditorPart p = getActiveEditPart();
   
      if (null != p && p instanceof IWorkflowModelEditor)
      {
         FileEditorInput input = (FileEditorInput) p.getEditorInput();
         IFile currentFile = input.getFile();
   
         return currentFile.equals(file);
      }
      
      return false;
   }

   public static WorkflowModelEditor loadModel(IFile file, boolean activate)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      ModelLoader loader = new ModelLoader(workbench, file, activate);
      
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(loader);
      PartInitException x = loader.getException();
      
      if (null != x)
      {
         throw new InternalException(
               MessageFormat.format(Debug_Messages.EXP_ErrorOccuredWhileLoadingModelFile, file.getName()), x);
      }
      return loader.getEditor();
   }

   public static IViewPart findWorkbenchView(String viewId)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      WorkbenchViewRetriever workbenchViewRetriever = new WorkbenchViewRetriever(
            workbench, viewId);
      
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(workbenchViewRetriever);
      IViewPart view = workbenchViewRetriever.getViewPart();
      
      return view;
   }

   /**
    * Gets the currently active edit part.
    * 
    * @return The EditPart or <code>null</code>
    */
   public static IEditorPart getActiveEditPart()
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      EditorPartRetriever editorPartRetriever = new EditorPartRetriever(workbench);
      
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(editorPartRetriever);
      IEditorPart p = editorPartRetriever.getEditorPart();
      
      return p;
   }

   public static IWorkbenchWindow getActiveWorkbenchWindow()
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      ActiveWorkbenchWindowReceiver receiver = new ActiveWorkbenchWindowReceiver(
            workbench);
      
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(receiver);
      return receiver.getWorkbenchWindow();
   }
   
   public static WorkflowModelEditor activateDiagram(WorkflowModelEditor editor, DiagramType diagram) throws CoreException
   {
      if (editor == null)
      {
         IEditorPart p = getActiveEditPart();
         if (p instanceof WorkflowModelEditor)
         {
            editor = (WorkflowModelEditor) p;
         }
      }
   
      if (editor != null)
      {
         final IWorkbench workbench = PlatformUI.getWorkbench();
         DiagramActivator diagramActivator = new DiagramActivator(workbench, editor, diagram);
         
         // syncExec() will block the current thread as long as Runnable.run()
         // has not been executed.
         workbench.getDisplay().syncExec(diagramActivator);
         CoreException x = diagramActivator.getException();
         
         if (null != x)
         {
            throw x;
         }
      }
      
      return editor;
   }
   
   public static EditPart findEditPart(WorkflowModelEditor editor, Object model)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      EditPartFinder finder = new EditPartFinder(editor, model);

      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(finder);
      return finder.getEditPart();
   }
   
   public static boolean openQuestionDialog(String title, String message)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      QuestionDialogExecutor executor = new QuestionDialogExecutor(workbench,
            title, message);
      
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(executor);
      return executor.getResult();
   }

   public static int openManualActivityDialog(ActivityInstanceDigest ai,
         DataMappingDigest[] dataMappings)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      ManualActivityDialogExecutor executor = new ManualActivityDialogExecutor(workbench,
            ai, dataMappings);

      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      workbench.getDisplay().syncExec(executor);
      return executor.getResult();
   }
   
   private static final class EditPartFinder implements Runnable
   {
      private final WorkflowModelEditor editor;
      private final Object model;
      private EditPart editPart;
      
      private EditPartFinder(WorkflowModelEditor editor, Object model)
      {
         this.editor = editor;
         this.model = model;
      }
   
      public void run()
      {
         editPart = editor.findEditPart(model);
      }

      public EditPart getEditPart()
      {
         return editPart;
      }
   }

   private static final class DiagramActivator implements Runnable
   {
      private final IWorkbench workbench;
      private final WorkflowModelEditor editor;
      private final DiagramType diagram;
      private CoreException exception;
   
      private DiagramActivator(IWorkbench workbench, WorkflowModelEditor editor,
            DiagramType diagram)
      {
         this.workbench = workbench;
         this.editor = editor;
         this.diagram = diagram;
         this.exception = null;
      }
   
      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null && 
               editor.equals(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                     .getActivePage().getActiveEditor()))
         {
            try
            {
               editor.showDiagramPage(diagram);
            }
            catch (CoreException x)
            {
               exception = x;
            }
         }
      }
      
      public CoreException getException()
      {
         return exception;
      }
   }

   private static final class ActiveWorkbenchWindowReceiver implements Runnable
   {
      private final IWorkbench workbench;
      private IWorkbenchWindow window;
   
      private ActiveWorkbenchWindowReceiver(IWorkbench workbench)
      {
         this.workbench = workbench;
         this.window = null;
      }
   
      public void run()
      {
         window = workbench.getActiveWorkbenchWindow();
      }
      
      public IWorkbenchWindow getWorkbenchWindow()
      {
         return window;
      }
   }

   private static final class ModelLoader implements Runnable
   {
      private final IWorkbench workbench;
      private IFile file;
      private boolean activate;
      private PartInitException exception;
      private WorkflowModelEditor editor;
   
      private ModelLoader(IWorkbench workbench, IFile file, boolean activate)
      {
         this.workbench = workbench;
         this.file = file;
         this.activate = activate;
      }
   
      public void run()
      {
         editor = null;
         /* inspired by JavaUI.openInEditor(...) */
         IWorkbenchWindow wbw = workbench.getActiveWorkbenchWindow();
         IWorkbenchPage wbp = wbw.getActivePage();
         try
         {
            IEditorPart part = IDE.openEditor(wbp, file, activate);
            if (part instanceof WorkflowModelEditor)
            {
               editor = (WorkflowModelEditor) part;
            }
         }
         catch (PartInitException x)
         {
            exception = x;
         }
      }
   
      public PartInitException getException()
      {
         return exception;
      }

      public WorkflowModelEditor getEditor()
      {
         return editor;
      }
   }

   private static final class EditorPartRetriever implements Runnable
   {
      private final IWorkbench workbench;
      private IEditorPart editorPart;
   
      private EditorPartRetriever(IWorkbench workbench)
      {
         super();
         this.workbench = workbench;
         this.editorPart = null;
      }
   
      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null)
         {
            editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage().getActiveEditor();
         }
      }
   
      public IEditorPart getEditorPart()
      {
         return editorPart;
      }
   }

   private static final class WorkbenchViewRetriever implements Runnable
   {
      private final IWorkbench workbench;
      private final String viewId;
      private IViewPart viewPart;
   
      private WorkbenchViewRetriever(IWorkbench workbench, String viewId)
      {
         super();
         this.workbench = workbench;
         this.viewId = viewId;
         this.viewPart = null;
      }
   
      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null)
         {
            viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage().findView(viewId);
         }
      }
   
      public IViewPart getViewPart()
      {
         return viewPart;
      }
   }

   private static final class QuestionDialogExecutor implements Runnable
   {
      private final IWorkbench workbench;
      private final String title;
      private final String message;
      
      private boolean result;
   
      private QuestionDialogExecutor(IWorkbench workbench, String title, String message)
      {
         super();
         this.workbench = workbench;
         this.title = title;
         this.message = message;
      }
   
      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null)
         {
            result = MessageDialog.openQuestion(null, title, message);
         }
      }
   
      public boolean getResult()
      {
         return result;
      }
   }

   private static final class ManualActivityDialogExecutor implements Runnable
   {
      private final IWorkbench workbench;

      private final ActivityInstanceDigest ai;
      private final DataMappingDigest[] dataMappings;
      
      private ManualApplicationDialog dlg;
      private int result;
   
      private ManualActivityDialogExecutor(IWorkbench workbench,
            ActivityInstanceDigest ai, DataMappingDigest[] dataMappings)
      {
         this.workbench = workbench;

         this.ai = ai;
         this.dataMappings = dataMappings;
      }
   
      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null)
         {
            this.dlg = new ManualApplicationDialog(window.getShell(), ai, dataMappings);
            dlg.setBlockOnOpen(true);
            result = dlg.open();
         }
      }
   
      public int getResult()
      {
         return result;
      }
   }

   public static void activateDiagramForProcess(CWMDebugTarget target, String processDefinitionId)
   {
      QName qname = QName.valueOf(processDefinitionId);
      String namespace = qname.getNamespaceURI();
      WorkflowModelEditor editor = getEditor(target, namespace);
      ModelType model = editor.getWorkflowModel();
      ProcessDefinitionType processDefinition = (ProcessDefinitionType)
         ModelUtils.findIdentifiableElement(model.getProcessDefinition(), qname.getLocalPart());
      if (processDefinition.getDiagram().size() > 0)
      {
         DiagramType diagram = (DiagramType) processDefinition.getDiagram().get(0);
         try
         {
            activateDiagram(editor, diagram);
         }
         catch (CoreException e)
         {
            // ignore
            // e.printStackTrace();
         }
      }
   }

   private static WorkflowModelEditor getEditor(CWMDebugTarget target, String namespace)
   {
      IWorkflowModelEditor editor = target.getEditor();
      ModelType model = editor.getWorkflowModel();
      if (!(editor instanceof WorkflowModelEditor) || !namespace.equals(model.getId()))
      {
         editor = getEditorForModel(CollectionUtils.<String>newSet(), model, namespace);
         if (editor == null)
         {
            throw new RuntimeException(MessageFormat.format(
                  Debug_Messages.EXP_CannotFindEditorForModelNamespace,
                  new Object[] { namespace }));
         }
      }
      return (WorkflowModelEditor) editor;
   }

   public static WorkflowModelEditor getEditorForModel(Set<String> visited, ModelType model, String namespace)
   {
      String location = ModelUtils.getLocation(model);
      if (!visited.contains(location))
      {
         visited.add(location);
         ExternalPackages packs = model.getExternalPackages();
         if (packs != null)
         {
            for (ExternalPackage pack : packs.getExternalPackage())
            {
               if (namespace.equals(pack.getHref()))
               {
                  ModelType externalModel = ModelUtils.getExternalModel(pack);
                  if (externalModel != null)
                  {
                     Path path = new Path(ModelUtils.getLocation(externalModel));
                     IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                     IFile file = path.isAbsolute() ? root.getFileForLocation(path) : root.getFile(path);
                     if (file.exists())
                     {
                        return loadModel(file, true);
                     }
                  }
               }
            }
            for (ExternalPackage pack : packs.getExternalPackage())
            {
               ModelType externalModel = ModelUtils.getExternalModel(pack);
               if (externalModel != null)
               {
                  WorkflowModelEditor editor = getEditorForModel(visited, externalModel, namespace);
                  if (editor != null)
                  {
                     return editor;
                  }
               }
            }
         }
      }
      return null;
   }
}
