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

import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

public abstract class DynamicToolEntry extends ToolEntry
{
   public DynamicToolEntry(String label, String description, ImageDescriptor iconSmall,
         ImageDescriptor iconLarge, Class tool)
   {
      super(label, description, iconSmall, iconLarge, tool);
   }

   public DynamicToolEntry(String label, String shortDesc, ImageDescriptor iconSmall,
         ImageDescriptor iconLarge)
   {
      super(label, shortDesc, iconSmall, iconLarge);
   }
}
