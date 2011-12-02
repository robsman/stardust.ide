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
package org.eclipse.stardust.modeling.repository.common.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ui.parts.tree.ConnectionTreeEditPart;
import org.eclipse.stardust.modeling.repository.common.ui.parts.tree.ObjectDescriptorTreeEditPart;


public class TreeEditPartAdapter implements IAdapterFactory
{
   // Superclass is not parameterized
   @SuppressWarnings("unchecked")
   public Object getAdapter(Object adaptableObject, Class adapterType)
   {
      if (adapterType.equals(TreeEditPart.class))
      {
         if (adaptableObject instanceof IObjectDescriptor)
         {
            return new ObjectDescriptorTreeEditPart((IObjectDescriptor) adaptableObject);
         }
         if (adaptableObject instanceof Connection)
         {
            return new ConnectionTreeEditPart((Connection) adaptableObject);
         }
      }
      return null;
   }

   // Superclass is not parameterized
   @SuppressWarnings("unchecked")
   public Class[] getAdapterList()
   {
      return new Class[] {TreeEditPart.class};
   }
}
