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
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class OpenVersionAction extends RepositoryAction
{
   public OpenVersionAction(IWorkbenchPart part)
   {
      super(part);
      setText(Diagram_Messages.LB_Repository_Open);
/*    setToolTipText(TaskListMessages.NewTask_tooltip);
      setImageDescriptor(MarkerUtil.getImageDescriptor("addtsk"));
      setDisabledImageDescriptor(MarkerUtil.getImageDescriptor("addtsk_disabled"));*/
   }

   protected boolean calculateEnabled(ResourceInfo info, IFile file)
   {
      return true;
   }

   protected boolean supportsMultiSelection()
   {
      return false;
   }

   protected boolean run(IFile file)
   {
      openEditor(file);
      return true;
   }
}
