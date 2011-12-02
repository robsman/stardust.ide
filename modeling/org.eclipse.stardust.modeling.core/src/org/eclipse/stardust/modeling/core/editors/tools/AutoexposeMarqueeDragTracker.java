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
package org.eclipse.stardust.modeling.core.editors.tools;

import org.eclipse.gef.DragTracker;

public class AutoexposeMarqueeDragTracker extends AutoexposeMarqueeSelectionTool
      implements DragTracker
{
   protected void handleFinished()
   {}
   
   protected boolean handleDragInProgress()
   {
      updateAutoexposeHelper();
       return super.handleDragInProgress();
   }
}
