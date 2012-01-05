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
package org.eclipse.stardust.modeling.common.ui.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class PerspectiveTester extends PropertyTester
{
   public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
   {
      if ("perspectiveType".equals(property)) //$NON-NLS-1$
      {
         IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
         if (activeWindow != null)
         {
            IWorkbenchPage activePage = activeWindow.getActivePage();
            if (activePage != null)
            {
               IPerspectiveDescriptor perspective = activePage.getPerspective();
               if (perspective != null)
               {
                  return CompareHelper.areEqual(perspective.getId(), expectedValue);
               }
            }
         }
      }
      return false;
   }
}
