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
package org.eclipse.stardust.modeling.core.editors.figures;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.handles.BendpointMoveHandle;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DiagramConnectionBendpointTracker;
import org.eclipse.swt.graphics.Color;


// A BendpointHandle that is used to move an existing bendpoint (and/or select a bendpoint)
// One Handle per Bendpoint
public class BendpointSelectionMoveHandle extends BendpointMoveHandle {
   private boolean selected = false;

   private List selectedHandles;

   public BendpointSelectionMoveHandle(ConnectionEditPart connEP, int bendPointIndex,
         int i)
   {
      super(connEP, bendPointIndex, i);
   }

   // the presentation of the current Bendpoint 
   protected Color getFillColor()
   {
      return selected ? ColorConstants.red : ColorConstants.black;
   }

   protected Color getBorderColor()
   {
      return selected ? ColorConstants.white : ColorConstants.white;
   }

   // here the selection or unselection of the bendoint is done
   protected DragTracker createDragTracker()
   {
      ConnectionBendpointTracker tracker;

      // the tracker 
      tracker = new DiagramConnectionBendpointTracker((ConnectionEditPart) getOwner(),
            selectedHandles, this)
      {
         // select or unselect the current bendpoint
         protected boolean handleButtonUp(int button)
         {
            if (!selected && getCurrentInput().isControlKeyDown())
            {
               selectedHandles.add(handle);
               selected = true;
            } else if (selected && getCurrentInput().isControlKeyDown()) {
               selectedHandles.remove(handle);
               selected = false;
            }
            repaint();

            return super.handleButtonUp(button);
         }
      };

      tracker.setType(RequestConstants.REQ_MOVE_BENDPOINT);
      tracker.setDefaultCursor(getCursor());
      return tracker;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelectedHandles(List selectedHandles)
   {
      this.selectedHandles = selectedHandles;
   }
}