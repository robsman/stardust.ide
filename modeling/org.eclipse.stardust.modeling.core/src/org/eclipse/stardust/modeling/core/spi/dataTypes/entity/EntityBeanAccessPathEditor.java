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
package org.eclipse.stardust.modeling.core.spi.dataTypes.entity;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.dataTypes.serializable.SerializableAccessPathEditor;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EntityBeanAccessPathEditor extends SerializableAccessPathEditor
{
   protected String getClassName(IExtensibleElement data)
   {
      String style = getVersionAttribute(data.getAttribute());
      return AttributeUtil.getAttributeValue(data, getPropertyName(style));
   }

   private String getPropertyName(String style)
   {
      return EntityBeanConstants.VERSION_3_X.equals(style)
         ? CarnotConstants.CLASS_NAME_ATT : CarnotConstants.REMOTE_INTERFACE_ATT;
   }

   private String getVersionAttribute(List attributes)
   {
      String style = EntityBeanConstants.VERSION_3_X;
      if (!attributes.isEmpty())
      {
         style = AttributeUtil.getAttributeValue(attributes, EntityBeanConstants.VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = EntityBeanConstants.VERSION_2_X;
         }
      }
      return style;
   }
}
