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
package org.eclipse.stardust.modeling.debug.model.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.swt.graphics.Image;

/**
 * Tree representation of structured data
 */
public class StructuredTreeLabelProvider implements ILabelProvider
{

   public void addListener(ILabelProviderListener listener)
   {
      // ignore 
   }

   public void dispose()
   {
      // ignore
   }

   public boolean isLabelProperty(Object element, String property)
   {
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {
      // ignore
   }

   public Image getImage(Object element)
   {
      // TODO (ab) different images for complex and primitive types / enumerations / list items
      return null;
   }

   public String getText(Object element)
   {
      if (element == null)
      {
         return "null"; //$NON-NLS-1$
      }
      else
      {
         StructuredValue value = (StructuredValue)element;
         
         TypedXPath typedXPath = value.getXPath();
         if (StructuredDataXPathUtils.isRootXPath(typedXPath.getXPath()))
         {
            return typedXPath.getXsdTypeName(); 
         }
         else
         {
            String elementName = StructuredDataXPathUtils.getLastXPathPart(typedXPath.getXPath());
            if (typedXPath.isList() && value.getParent() != null)
            {
               // find out the position (1-based) of this element inside the list
               Map parentMap = (Map)value.getParent().getData();
               List list = (List) parentMap.get(elementName);
               int position = list.indexOf(value.getData()) + 1;
               return elementName + " ["+position+"]"; //$NON-NLS-1$ //$NON-NLS-2$
            }
            else
            {
               return elementName;
            }
         }
          
      }
   }

}
