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
package org.eclipse.stardust.modeling.core.utils;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


/**
 * @author rsauer
 * @version $Revision$
 */
public class ModelingUtils
{
   public static void attachModelElement(IModelElement modelElement, List target)
   {
      if (null != modelElement)
      {
         if ( !target.contains(modelElement))
         {
            target.add(modelElement);
         }

         setElementOid(modelElement);
      }
   }

   public static void setElementOid(IModelElement modelElement)
   {
      if (null != modelElement)
      {
         if ( !modelElement.isSetElementOid())
         {
            modelElement.setElementOid(ModelUtils.getElementOid(modelElement,
                  ModelUtils.findContainingModel(modelElement)));
         }
      }
   }
}
