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
package org.eclipse.stardust.model.xpdl.carnot.merge;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;


public class UUIDUtils
{   
   public static final String UUID_ATTR = "infinity:vcs:uuid"; //$NON-NLS-1$;

   public static String getUUID(EObject element)
   {
      if (element instanceof IExtensibleElement)
      {
         return AttributeUtil.getAttributeValue((IExtensibleElement) element, UUID_ATTR);
      }
      else if (element instanceof TypeDeclarationType)
      {
         return ExtendedAttributeUtil.getAttributeValue((TypeDeclarationType) element, UUID_ATTR);
      }
      return null;
   }
   
   public static String setUUID(EObject element)
   {
      String uuid = EcoreUtil.generateUUID();
      return setUUID(element, uuid);
   }
   
   public static String setUUID(EObject element, String uuid)
   {
      boolean deliver = element.eDeliver();
      element.eSetDeliver(false);
      try
      {
         if (element instanceof IExtensibleElement)
         {
            AttributeUtil.setAttribute((IExtensibleElement) element, UUID_ATTR, uuid);
         }
         else if (element instanceof TypeDeclarationType)
         {
            ExtendedAttributeUtil.setAttribute((TypeDeclarationType) element, UUID_ATTR, uuid);
         }
         else
         {
            return null;
         }
         return uuid;
      }
      finally
      {
         element.eSetDeliver(deliver);
      }
   }
   
   public static void unsetUUID(EObject element)
   {
      boolean deliver = element.eDeliver();
      element.eSetDeliver(false);
      try
      {
         if (element instanceof IExtensibleElement)
         {
            AttributeUtil.setAttribute((IExtensibleElement) element, UUID_ATTR, null);
         }
         else if (element instanceof TypeDeclarationType)
         {
            ExtendedAttributeUtil.setAttribute((TypeDeclarationType) element, UUID_ATTR, null);
         }
      }
      finally
      {
         element.eSetDeliver(deliver);
      }
   }   
}