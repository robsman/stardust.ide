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

import java.util.List;

import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


public class CopySymbolAction extends SelectionAction
{
   private Clipboard clipboard;
   private WorkflowModelEditor editor;
   private Integer isValid;   
   
   public CopySymbolAction(IWorkbenchPart part)
   {      
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setText(Diagram_Messages.LB_CopySymbol);
      setToolTipText(Diagram_Messages.LB_CopySymbol);
      setId(DiagramActionConstants.COPYSYMBOL);
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
      setDisabledImageDescriptor(sharedImages
            .getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));      
      clipboard = Clipboard.getDefault();      
   }
   
   protected boolean calculateEnabled()
   {
      List selection = getSelectedObjects();
      isValid = CopyPasteUtil.validateSelection(selection, false);
      if(isValid != null && isValid.intValue() != CopyPasteUtil.SELECTION_OUTLINE)
      {
         return true;
      }
      return false;
   }

   public void run()
   {
      // maybe we set a flag in StoreObject that this is copy symbol content
      List contentList = CopyPasteUtil.createCopySet(isValid, getSelectedObjects(), editor, true);
      clipboard.setContents(contentList);
   }
}