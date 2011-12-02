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
package org.eclipse.stardust.modeling.core.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


/**
 * List selection dialog form model elements of workflow model files. TODO: At the moment
 * only process definition elements are supported and fixed coded.
 * 
 * @author sborn
 * @version $Revision$
 */
public class DiagramSelectionDialog extends ElementListSelectionDialog
{
   private final IFile modelFile;

   private final WorkflowModelManager modelManager;

   private ModelType model;

   private boolean selectProcessDiagram = true;

   private ProcessDefinitionType processDefinition;

   /**
    * @param parentShell
    * @param modelFile
    * @param processDefinition
    * @param selectProcessDiagram
    * @param editor 
    */
   public DiagramSelectionDialog(Shell parentShell, IFile modelFile,
         ProcessDefinitionType processDefinition, boolean selectProcessDiagram, WorkflowModelEditor editor)
   {
      super(parentShell, new EObjectLabelProvider(editor));

      this.modelFile = modelFile;
      this.processDefinition = processDefinition;
      this.selectProcessDiagram = selectProcessDiagram;
      modelManager = new WorkflowModelManager();

      try
      {
         init();
      }
      catch (PartInitException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void init() throws PartInitException, CoreException
   {
      loadModel();

      List diagrams = new ArrayList();

      if (null != processDefinition)
      {
         diagrams = processDefinition.getDiagram();
      }
      else
      {
         diagrams.addAll(model.getDiagram());
         if (selectProcessDiagram)
         {
            for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
            {
               diagrams.addAll(((ProcessDefinitionType) iter.next()).getDiagram());
            }
         }
      }

      setElements(diagrams.toArray());
      setMultipleSelection(true);
   }

   private void loadModel() throws PartInitException, CoreException
   {
      if (modelFile.exists())
      {
         try
         {
            modelManager.load(URI.createPlatformResourceURI(modelFile.getFullPath()
                  .toString()));
         }
         catch (Exception e)
         {
            throw new PartInitException(Diagram_Messages.MSG_LoadingModelFailed, e);
         }

         model = modelManager.getModel();
         if (null == model)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
                  Diagram_Messages.MSG_LoadingModelFailed, null));
         }
      }
   }
}