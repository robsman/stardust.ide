/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.builder.utils;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;

public class NameIdUtilsExtension extends NameIdUtils
{
   /**
    * @return
    */
   public static String createIdFromName(Object container, EObject element, String base)
   {
      if(element instanceof IIdentifiableElement)
      {      
         if(element instanceof ModelType && !StringUtils.isEmpty(((IIdentifiableElement) element).getId()))
         {
            return ((IIdentifiableElement) element).getId();
         }
      }
      return NameIdUtils.createIdFromName(container, element, base);
   }
}