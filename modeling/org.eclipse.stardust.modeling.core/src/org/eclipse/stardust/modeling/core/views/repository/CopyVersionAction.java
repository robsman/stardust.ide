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

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CopyVersionAction extends CreateVersionAction
{
   private String newId;

   private String originalId;

   public CopyVersionAction(IWorkbenchPart part)
   {
      super(part, -1);
      setText(Diagram_Messages.LB_ACTION_CopyVersion);
      /*
       * setToolTipText(TaskListMessages.NewTask_tooltip);
       * setImageDescriptor(MarkerUtil.getImageDescriptor("addtsk"));
       * setDisabledImageDescriptor(MarkerUtil.getImageDescriptor("addtsk_disabled"));
       */
   }

   protected boolean calculateEnabled()
   {
      originalId = null;
      return super.calculateEnabled();
   }

   protected boolean calculateEnabled(ResourceInfo info, IFile file)
   {
      if (originalId == null)
      {
         originalId = info.getId();
      }
      else
      {
         if (!originalId.equals(info.getId()))
         {
            return false;
         }
      }
      return true;
   }

   protected boolean supportsMultiSelection()
   {
      return true;
   }

   public void run()
   {
      newId = null;
      super.run();
   }

   protected boolean updateModel(IFile file, ModelType model)
   {
      if (newId == null)
      {
         newId = getNewIdProposal(model.getId());
         InputDialog dialog = new InputDialog(getShell(),
               Diagram_Messages.LB_ACTION_CopyVersion, Diagram_Messages.MSG_EnterNewID,
               newId, new IInputValidator()
               {
                  public String isValid(String newText)
                  {
                     if (newText == null || newText.length() == 0)
                     {
                        return Diagram_Messages.MSG_NoId;
                     }
                     if (existsId(newText))
                     {
                        return MessageFormat
                              .format(
                                    Diagram_Messages.MSG_ANOTHER_MODEL_HIERARCHY_WITH_ID_ALREADY_EXISTS,
                                    new Object[] {newText});
                     }
                     return null;
                  }
               });
         if (dialog.open() == 0)
         {
            newId = dialog.getValue();
         }
         else
         {
            return false;
         }
      }
      model.setId(newId);
      basicFileName = newId;
      return true;
   }

   private String getNewIdProposal(String id)
   {
      int ix = 0;
      while (true)
      {
         String proposal = ix == 0 ? id + "_copy" : id + ix + "_copy"; //$NON-NLS-1$ //$NON-NLS-2$
         if (!existsId(proposal))
         {
            return proposal;
         }
         ix++;
      }
   }

   private boolean existsId(String proposal)
   {
      VersionRepository repository = (VersionRepository) getWorkbenchPart();
      ProjectContentProvider provider = repository.getContentProvider();
      ResourceInfo root = provider.getRoot();
      if (root != null)
      {
         if (root.findChild(proposal) != null)
         {
            return true;
         }
      }
      return false;
   }
}
