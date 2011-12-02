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
package org.eclipse.stardust.modeling.data.structured.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.actions.ISpiAction;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.data.structured.wizards.ImportFromSchemaWizard;
import org.eclipse.swt.widgets.Shell;


public class ImportTypeDeclarationsAction extends Action implements ISpiAction
{
   private IStructuredSelection selection;

   private WorkflowModelEditor editor;

   protected List<?> getSelectedObjects()
   {
      return selection instanceof IStructuredSelection
         ? ((IStructuredSelection) selection).toList()
         : Collections.EMPTY_LIST;
   }

   public void setConfiguration(IConfigurationElement config, WorkflowModelEditor editor,
         IStructuredSelection selection)
   {
      setId(config.getAttribute(SpiConstants.ID));
      setText(Structured_Messages.ImportTypeDeclarationsAction_ActionLabel);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("{org.eclipse.xsd.edit}icons/full/obj16/XSDSchema.gif")); //$NON-NLS-1$
      this.editor = editor;
      this.selection = selection;
   }

   public boolean isEnabled()
   {
      if (getSelectedObjects().size() != 1)
      {
         return false;
      }
      Object selection = getSelectedObjects().get(0);
      return selection instanceof TreeEditPart
            && ((TreeEditPart) selection).getModel() instanceof TypeDeclarationsType;
   }

   private TypeDeclarationsType getTypeDeclarationsType()
   {
      Object selection = getSelectedObjects().get(0);
      return (TypeDeclarationsType)((TreeEditPart) selection).getModel();
   }

   public void run()
   {
      ImportFromSchemaWizard wizard = new ImportFromSchemaWizard(getTypeDeclarationsType());
      Shell shell = editor.getSite().getShell();
      WizardDialog dialog = new WizardDialog(shell, wizard);
      dialog.setBlockOnOpen(true);
      if (dialog.open() == Window.OK)
      {
         editor.getEditDomain().getCommandStack().execute(wizard.getCommand());
      }
   }
}