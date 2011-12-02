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

import org.eclipse.ui.IWorkbenchPart;

public class SelectAllAction extends org.eclipse.gef.ui.actions.SelectAllAction
{
   public SelectAllAction(IWorkbenchPart part)
   {
      super(part);
   }

   protected boolean calculateEnabled()
   {
      
      return false;
   }
   

   // not as command, check also if we have pools and lanes
   public void run()
   {
      
   }
}