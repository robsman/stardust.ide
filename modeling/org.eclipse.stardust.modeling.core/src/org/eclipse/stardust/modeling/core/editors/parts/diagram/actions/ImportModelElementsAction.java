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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ImportModelElementsAction extends SelectionAction
{
   private static final String IMPORT_MODEL_ELEMENTS_WIZARD = "org.eclipse.stardust.modeling.modelimport.ImportModelElementsWizard"; //$NON-NLS-1$

   public ImportModelElementsAction(WorkflowModelEditor editor)
   {
      super(editor);
      setId(DiagramActionConstants.IMPORT_MODEL_ELEMENTS);
      setText(Diagram_Messages.TXT_IMPORT_MD_ELEMENT);
   }

   protected boolean calculateEnabled()
   {
      if (getSelectedObjects().size() == 1 && getModel() != null)
      {
         IWizardRegistry registry = PlatformUI.getWorkbench().getImportWizardRegistry();
         IWizardDescriptor descriptor = registry.findWizard(IMPORT_MODEL_ELEMENTS_WIZARD);
         return descriptor != null;
      }
      return false;
   }

   public void run()
   {
      IWizardRegistry registry = PlatformUI.getWorkbench().getImportWizardRegistry();
      IWizardDescriptor descriptor = registry.findWizard(IMPORT_MODEL_ELEMENTS_WIZARD);
      try
      {
         IWorkbenchWizard wizard = descriptor.createWizard();
         wizard.init(PlatformUI.getWorkbench(),
               new StructuredSelection(getWorkbenchPart()));
         WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench()
               .getActiveWorkbenchWindow().getShell(), wizard);
         dialog.setBlockOnOpen(true);
         dialog.open();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
   }

   private ModelType getModel()
   {
      Object selection = getSelectedObjects().get(0);
      if (!(selection instanceof EditPart))
      {
         return null;
      }
      Object element = ((EditPart) selection).getModel();
      return (ModelType) (element instanceof ModelType ? element : null);
   }
}