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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.cap.LaneUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


public class CopyAction extends SelectionAction
{
   private Clipboard clipboard;
   private WorkflowModelEditor editor;
   private Integer isValid;

   public CopyAction(WorkflowModelEditor editor)
   {
      super(editor);
      this.editor = editor;
      setId(ActionFactory.COPY.getId());
      setText(Diagram_Messages.LB_CopyElement);
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
      setToolTipText(Diagram_Messages.LB_CopyElement);
      setAccelerator(SWT.CTRL | 'C');      
      clipboard = Clipboard.getDefault();
   }

   protected boolean calculateEnabled()
   {
      // our selection comes from diagram OR outline
      List selection = getSelectedObjects();
      if(CopyPasteUtil.containsGateway(selection))
      {
         return false;
      }
      
      isValid = CopyPasteUtil.validateSelection(selection, false);
      if(isValid == null)
      {
         return false;
      }
      // copy lanes
      if(getSelectedObjects().get(0) instanceof LaneEditPart)
      {
         Map laneHierarchies = new HashMap();
         for(int i = 0; i < getSelectedObjects().size(); i++)
         {
            LaneEditPart child = (LaneEditPart) getSelectedObjects().get(i);
            LaneSymbol symbol = child.getLaneModel();
            List hierarchy = LaneUtils.createLaneHierarchy(symbol);
            // if one of the child lanes contains both lanes and other symbols
            if(hierarchy == null)
            {
               return false;
            }
            laneHierarchies.put(symbol, hierarchy);
         }
         for(int i = 0; i < getSelectedObjects().size(); i++)
         {
            LaneEditPart child = (LaneEditPart) getSelectedObjects().get(i);
            LaneSymbol symbol = child.getLaneModel();
            // iterate over hierarchies and check all hierarchies but the one for this symbol
            Iterator it = laneHierarchies.entrySet().iterator();
            while(it.hasNext())
            {               
               Map.Entry entry = (Entry) it.next();
               LaneSymbol checkLane = (LaneSymbol) entry.getKey();
               List checkHierarchy = (List) entry.getValue();
               if(!symbol.equals(checkLane))
               {
                  if(LaneUtils.containsLane(checkHierarchy, symbol))
                  {
                     return false;
                  }
               }               
            }            
         }
      }      
      return true;
   }

   public void run()
   {
      List contentList = CopyPasteUtil.createCopySet(isValid, getSelectedObjects(), editor, false);
      clipboard.setContents(contentList);
   }
}