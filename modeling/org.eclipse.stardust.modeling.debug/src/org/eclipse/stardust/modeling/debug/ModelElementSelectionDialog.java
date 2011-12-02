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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


/**
 * List selection dialog form model elements of workflow model files.
 * TODO: At the moment only process definition elements are supported and fixed coded.
 * 
 * @author sborn
 * @version $Revision$
 */
public class ModelElementSelectionDialog extends ElementListSelectionDialog
{
   private final IFile modelFile;
   private final Class[] elementTypeFilter;
   private final WorkflowModelManager modelManager;
   
   private ModelType model; 

   /**
    * @param parentShell
    * @param modelFile
    * @param elementTypeFilter not supported yet
    */
   public ModelElementSelectionDialog(Shell parentShell, IFile modelFile, Class[] elementTypeFilter)
   {
      super(parentShell, new EObjectLabelProvider(null));
      
      this.modelFile = modelFile;
      this.elementTypeFilter = elementTypeFilter;
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
      
      EList processDefinitions = model.getProcessDefinition();
      setElements(processDefinitions.toArray());
   }

   private void loadModel() throws PartInitException, CoreException
   {
      if (modelFile.exists())
      {
         try
         {
            modelManager.load(URI.createPlatformResourceURI(modelFile.getFullPath().toString()));
         }
         catch (Exception e)
         {
            throw new PartInitException(Debug_Messages.EXP_FailedLoadingModel, e);
         }

         model = modelManager.getModel();
         if (null == model)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
                  Debug_Messages.EXP_ErrorLoadingNetwork, null));
         }
      }
   }
}