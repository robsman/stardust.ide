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
package org.eclipse.stardust.modeling.core.highlighting;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

/**
 * @author sborn
 * @version $Revision$
 */
public class ColorFactory implements IColorFactory
{
   private final Color defaultColor;
   
   public ColorFactory(Color defaultColor)
   {
      this.defaultColor = defaultColor;
   }
   
   public Color createColor(HighlightState highlightState)
   {
      Color color;
      
      switch (highlightState.getValue())
      {
         case HighlightState.DEFAULT:
            color = defaultColor;
            break;
            
         case HighlightState.ACTIVE:
            color = ColorConstants.yellow;
            break;

         case HighlightState.SELECTED:
            color = ColorConstants.blue;
            break;

         case HighlightState.DONE:
            color = ColorConstants.green;
            break;

         case HighlightState.BROKEN:
            color = ColorConstants.red;
            break;
            
         default:
            color = defaultColor;
            break;
      }
      
      return color;
   }
}
