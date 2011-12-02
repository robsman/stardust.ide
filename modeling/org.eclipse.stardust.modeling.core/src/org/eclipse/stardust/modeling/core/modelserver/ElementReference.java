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
package org.eclipse.stardust.modeling.core.modelserver;

import org.eclipse.emf.ecore.EReference;

public class ElementReference
{

   private EReference elementType;
   private String elementId;

   public ElementReference(EReference elementType, String elementId)
   {
      this.elementType = elementType;
      this.elementId = elementId;
   }

   public EReference getElementType()
   {
      return elementType;
   }

   public String getElementId()
   {
      return elementId;
   }

}
