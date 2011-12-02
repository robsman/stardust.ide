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
package org.eclipse.stardust.modeling.repository.common.filters;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.modeling.repository.common.IFilter;


public class IdentifiableModelElementFilter implements IFilter
{
   private EClass[] objectTypes;
   private String idPattern;
   private String namePattern;

   public IdentifiableModelElementFilter(EClass[] objectTypes, String idPattern,
         String namePattern)
   {
      this.objectTypes = objectTypes;
      this.idPattern = idPattern;
      this.namePattern = namePattern;
   }
   
   public boolean accept(Object object)
   {
      if (object instanceof IIdentifiableModelElement)
      {
         IIdentifiableModelElement element = (IIdentifiableModelElement) object;
         return matchesTypes(element.eClass())
             && matchesPattern(idPattern, element.getId())
             && matchesPattern(namePattern, element.getName());
      }
      else if (object instanceof IObjectReference)
      {
         IObjectReference element = (IObjectReference) object;
         return matchesTypes((EClass) element.getType())
             && matchesPattern(idPattern, element.getId())
             && matchesPattern(namePattern, element.getName());
      }
      return false;
   }

   private boolean matchesTypes(EClass element)
   {
      if (objectTypes != null && objectTypes.length > 0)
      {
         for (int i = 0; i < objectTypes.length; i++)
         {
            if (objectTypes[i] != null && objectTypes[i].equals(element))
            {
               return true;
            }
         }
         return false;
      }
      return true;
   }

   private boolean matchesPattern(String pattern, String value)
   {
      return value.indexOf(pattern) >= 0;
   }
}
