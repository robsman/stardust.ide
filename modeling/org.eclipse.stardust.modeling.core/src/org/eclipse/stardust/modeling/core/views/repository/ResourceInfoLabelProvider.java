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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ResourceInfoLabelProvider extends LabelProvider implements IColorProvider
{
   public Color getForeground(Object element)
   {
      ResourceInfo info = (ResourceInfo) element;
      return info.isPseudoNode() ? ColorConstants.darkBlue :
             info.isVirtual() ?    ColorConstants.gray :
                                   ColorConstants.listForeground;
   }

   public Image getImage(Object element)
   {
      ResourceInfo info = (ResourceInfo) element;
      if (info.isPseudoNode())
      {
         return DiagramPlugin.getImage("/icons/full/obj16/open_version.gif"); //$NON-NLS-1$
      }
      else if (info.isVirtual())
      {
         return DiagramPlugin.getImage("/icons/full/obj16/broken_version.gif"); //$NON-NLS-1$
      }
      else if (info.hasChildren())
      {
         return DiagramPlugin.getImage("/icons/full/obj16/released_version.gif"); //$NON-NLS-1$
      }
      return DiagramPlugin.getImage("/icons/full/obj16/open_version.gif"); //$NON-NLS-1$
   }

   public Color getBackground(Object element)
   {
      return ColorConstants.listBackground;
   }
}
