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
package org.eclipse.stardust.modeling.repository.common.descriptors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;

public class ReplaceModelElementDescriptor extends ModelElementDescriptor
{
   public ReplaceModelElementDescriptor(URI uri, IIdentifiableModelElement identifiable,
         String iconBundleId, String iconPath, boolean qualifyUri)
   {
      super(uri, identifiable,
            iconBundleId, iconPath, qualifyUri);
      alwaysReplace = true;
   }
}