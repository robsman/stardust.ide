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

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.geometry.Rectangle;

public class DiagramLayer extends FreeformLayer
{

   public Rectangle getFreeformExtent()
   {
      Rectangle extent = super.getFreeformExtent();

      // reserve extra blank 1500px to the right and bottom
      
      extent.width += 2000;
      extent.height += 2000;
      
      return extent;
   }
   
}
