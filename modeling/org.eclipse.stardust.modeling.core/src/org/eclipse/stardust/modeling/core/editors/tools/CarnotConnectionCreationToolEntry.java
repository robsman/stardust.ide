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

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;

public class CarnotConnectionCreationToolEntry extends ConnectionCreationToolEntry
{
   public CarnotConnectionCreationToolEntry(String label, String shortDesc,
         CreationFactory factory, ImageDescriptor iconSmall, ImageDescriptor iconLarge)
   {
      super(label, shortDesc, factory, iconSmall, iconLarge);

      // install custom tool implementation
      setToolClass(CarnotConnectionCreationTool.class);
   }
}
