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
import org.eclipse.emf.ecore.EObject;

public class ReplaceEObjectDescriptor extends EObjectDescriptor
{
   public ReplaceEObjectDescriptor(URI uri, EObject eObject, String id, String name,
         String description, String iconBundleId, String iconPath)
   {
      super(uri, eObject, id, name, description, iconBundleId, iconPath);
      alwaysReplace = true;      
   }
}