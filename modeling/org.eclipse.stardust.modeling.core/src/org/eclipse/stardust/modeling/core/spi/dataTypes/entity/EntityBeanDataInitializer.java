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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EntityBeanDataInitializer implements IDataInitializer
{
   public List initialize(DataType data, List attributes)
   {
      List newAttributes = new ArrayList();
      String style = getSetVersionAttribute(newAttributes, attributes);
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (CarnotConstants.CLASS_NAME_ATT.equals(attribute.getName()))
         {
            String className = attribute.getValue();
            if (className != null && className.length() > 0)
            {
               attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
               attribute.setName(getPropertyName(style));
               attribute.setValue(className);
               newAttributes.add(attribute);
            }
         }
      }
      return newAttributes;
   }

   private String getPropertyName(String style)
   {
      return EntityBeanConstants.VERSION_3_X.equals(style)
         ? CarnotConstants.CLASS_NAME_ATT : CarnotConstants.REMOTE_INTERFACE_ATT;
   }

   private String getSetVersionAttribute(List newAttributes, List attributes)
   {
      String style = EntityBeanConstants.VERSION_3_X;
      if (attributes.isEmpty())
      {
         // new created apps are 3.0
         AttributeUtil.setAttribute(newAttributes, EntityBeanConstants.VERSION_ATT, style);
      }
      else
      {
         style = AttributeUtil.getAttributeValue(attributes, EntityBeanConstants.VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = EntityBeanConstants.VERSION_2_X;
            AttributeUtil.setAttribute(newAttributes, EntityBeanConstants.VERSION_ATT, style);
         }
      }
      return style;
   }
}
