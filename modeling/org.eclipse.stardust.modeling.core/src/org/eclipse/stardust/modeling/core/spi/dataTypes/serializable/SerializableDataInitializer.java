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
package org.eclipse.stardust.modeling.core.spi.dataTypes.serializable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SerializableDataInitializer implements IDataInitializer
{
   public List initialize(DataType data, List attributes)
   {
      String className = null;
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (CarnotConstants.CLASS_NAME_ATT.equals(attribute.getName()))
         {
            className = attribute.getValue();
         }
         else if (CarnotConstants.REMOTE_INTERFACE_ATT.equals(attribute.getName()))
         {
            className = attribute.getValue();
         }
         else if (CarnotConstants.TYPE_ATT.equals(attribute.getName()))
         {
            try
            {
               Class type = Reflect.getClassFromAbbreviatedName(attribute.getValue());
               if (null != type)
               {
                  className = type.getName();
               }
            }
            catch (InternalException e)
            {
               // ignore
            }
         }

         if ( !StringUtils.isEmpty(className))
         {
            break;
         }
      }
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(CarnotConstants.CLASS_NAME_ATT);
      attribute.setValue(className == null ? Serializable.class.getName() : className);
      return Collections.singletonList(attribute);
   }
}
