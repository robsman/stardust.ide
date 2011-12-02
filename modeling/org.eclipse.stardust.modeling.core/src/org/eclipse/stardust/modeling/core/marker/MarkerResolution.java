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
import org.eclipse.ui.IMarkerResolution;

/**
 * @author fherinean
 * @version $Revision$
 */
public class MarkerResolution implements IMarkerResolution
{
   private IAction action;

   public MarkerResolution(IAction action)
   {
      this.action = action;
   }

   public String getLabel()
   {
      return action.getText();
   }

   public void run(IMarker marker)
   {
      action.run();
   }
}
