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
package org.eclipse.stardust.modeling.core.views.repository;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DeleteVersionAction extends RepositoryAction
{
   public DeleteVersionAction(IWorkbenchPart part)
   {
      super(part);
      setText(GEFMessages.DeleteAction_Label);
      setToolTipText(GEFMessages.DeleteAction_Tooltip);
      setId(ActionFactory.DELETE.getId());
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
      setDisabledImageDescriptor(sharedImages.getImageDescriptor(
            ISharedImages.IMG_TOOL_DELETE_DISABLED));
   }

   protected boolean calculateEnabled(ResourceInfo info, IFile file)
   {
      return true;
   }

   protected boolean supportsMultiSelection()
   {
      return true;
   }

   protected boolean run(IFile file)
   {
      try
      {
         file.delete(IResource.FORCE | IResource.KEEP_HISTORY, null);
      }
      catch (CoreException e)
      {
         MessageDialog.openError(getWorkbenchPart().getSite().getShell(), Diagram_Messages.MSG_DeleteVersion_Error,
            e.getStatus().toString());
      }
      return true;
   }
}
