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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;

public class BoundEObjectPropertyId
{
   private final EObject object;
   private final EStructuralFeature id;
   private final EditPart part;

   public BoundEObjectPropertyId(EObject object, EStructuralFeature id, EditPart part)
   {
      this.object = object;
      this.id = id;
      this.part = part;
   }

   public EObject getObject()
   {
      return object;
   }

   public EStructuralFeature getId()
   {
      return id;
   }

   public EditPart getPart()
   {
      return part;
   }
}
