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

import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.graphics.Image;

import ag.carnot.base.CompareHelper;

/**
 * @author fherinean
 * @version $Revision$
 */
public class IconFigure extends AbstractIconFigure implements IIconFigure
{
   private String iconPath;

   public IconFigure(String iconPath)
   {
      this.iconPath = iconPath;
   }
   
   public void setIconPath(String iconPath)
   {
      if ( !CompareHelper.areEqual(this.iconPath, iconPath))
      {
         this.iconPath = iconPath;
         resetIcon();
      }
   }
   
   protected Image doLoadIcon()
   {
      return DiagramPlugin.getImage(iconPath);
   }
}
