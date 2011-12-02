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
package org.eclipse.stardust.modeling.authorization.propertypages;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.authorization.AuthorizationAspectPlugin;
import org.eclipse.stardust.modeling.authorization.Permission;
import org.eclipse.swt.graphics.Image;


public class PermissionLabelProvider extends LabelProvider
{
   public Image getImage(Object element)
   {
      return AuthorizationAspectPlugin.getDefault().getImage("icons/authorization.gif"); //$NON-NLS-1$
   }

   public String getText(Object element)
   {
      Permission permission = (Permission) element;
      String label = permission.getName();
      if (label == null)
      {
         label = permission.getId();
      }
      return label == null ? "" : label; //$NON-NLS-1$
   }
}
