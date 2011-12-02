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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;


public class DiagramScrollingGraphicalViewer extends ScrollingGraphicalViewer
{
   public void reveal(EditPart part)
   {
      if (getFigureCanvas() != null)
      {
         if(!(part instanceof LaneEditPart))
         {
            super.reveal(part);            
         }
      }
   }
}
