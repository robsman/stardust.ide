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
package org.eclipse.stardust.modeling.core.marker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;

/**
 * @author fherinean
 * @version $Revision$
 */
public class MarkerResolution implements IMarkerResolution2
{
   private IAction action;
   private Image image;

   public MarkerResolution(IAction action)
   {
      this.action = action;
   }

   public MarkerResolution(IAction action, Image image)
   {
      this.action = action;
      this.image = image;
   }

   @Override
   public String getLabel()
   {
      return action.getText();
   }

   @Override
   public String getDescription()
   {
      return action.getDescription();
   }

   @Override
   public Image getImage()
   {
      return image;
   }

   @Override
   public void run(IMarker marker)
   {
      action.run();
   }
}
