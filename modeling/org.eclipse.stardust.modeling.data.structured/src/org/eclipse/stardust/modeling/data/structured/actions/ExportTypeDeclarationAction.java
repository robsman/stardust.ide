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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.actions.ISpiAction;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.data.structured.wizards.ExportSchemaWizard;
import org.eclipse.swt.widgets.Shell;


public class ExportTypeDeclarationAction extends Action implements ISpiAction
{
   private WorkflowModelEditor editor;
   private TypeDeclarationType typeDeclaration;

   public void setConfiguration(IConfigurationElement config, WorkflowModelEditor editor,
         IStructuredSelection selection)
   {
      setId(config.getAttribute(SpiConstants.ID));
      setText(Structured_Messages.ExportTypeDeclarationAction_ActionLabel0);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("{org.eclipse.xsd.edit}icons/full/obj16/XSDSchema.gif")); //$NON-NLS-1$
      this.editor = editor;
      if (selection.size() == 1)
      {
         Object selectedObject = selection.getFirstElement();
         if (selectedObject instanceof TreeEditPart)
         {
            Object model = ((TreeEditPart) selectedObject).getModel();
            if (model instanceof TypeDeclarationType)
            {
               typeDeclaration = (TypeDeclarationType) model;
            }
         }
      }
   }

   public boolean isEnabled()
   {
      return typeDeclaration != null && typeDeclaration.getSchemaType() != null;
   }

   public void run()
   {
      ExportSchemaWizard wizard = new ExportSchemaWizard(typeDeclaration);
      Shell shell = editor.getSite().getShell();
      WizardDialog dialog = new WizardDialog(shell, wizard);
      dialog.setBlockOnOpen(true);
      dialog.open();
   }
}