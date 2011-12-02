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
package org.eclipse.stardust.modeling.core.spi.dataTypes.primitive;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;

import ag.carnot.base.Money;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PrimitiveDataInitializer implements IDataInitializer
{
   static final String[][] mappingsToType = {
      {boolean.class.getName(), "boolean"}, //$NON-NLS-1$
      {Boolean.class.getName(), "boolean"}, //$NON-NLS-1$
      {char.class.getName(), "char"}, //$NON-NLS-1$
      {Character.class.getName(), "char"}, //$NON-NLS-1$
      {byte.class.getName(), "byte"}, //$NON-NLS-1$
      {Byte.class.getName(), "byte"}, //$NON-NLS-1$
      {short.class.getName(), "short"}, //$NON-NLS-1$
      {Short.class.getName(), "short"}, //$NON-NLS-1$
      {int.class.getName(), "int"}, //$NON-NLS-1$
      {Integer.class.getName(), "int"}, //$NON-NLS-1$
      {long.class.getName(), "long"}, //$NON-NLS-1$
      {Long.class.getName(), "long"}, //$NON-NLS-1$
      {float.class.getName(), "float"}, //$NON-NLS-1$
      {Float.class.getName(), "float"}, //$NON-NLS-1$
      {double.class.getName(), "double"}, //$NON-NLS-1$
      {Double.class.getName(), "double"}, //$NON-NLS-1$
      {String.class.getName(), "String"}, //$NON-NLS-1$
      {Calendar.class.getName(), "Calendar"}, //$NON-NLS-1$
      {Money.class.getName(), "Money"}, //$NON-NLS-1$
      {Date.class.getName(), "Timestamp"}, //$NON-NLS-1$
   };

   public List initialize(DataType data, List attributes)
   {
      String typeName = null;
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (CarnotConstants.CLASS_NAME_ATT.equals(attribute.getName()))
         {
            String className = attribute.getValue();
            if (className != null && className.length() > 0)
            {
               for (int j = 0; j < mappingsToType.length; j++)
               {
                  if (mappingsToType[j][0].equals(className))
                  {
                     typeName = mappingsToType[j][1];
                     break;
                  }
               }
               if (typeName != null)
               {
                  break;
               }
            }
         }
      }
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(CarnotConstants.TYPE_ATT);
      attribute.setType("ag.carnot.workflow.spi.providers.data.java.Type"); //$NON-NLS-1$
      attribute.setValue(typeName == null ? "String" : typeName); //$NON-NLS-1$
      return Collections.singletonList(attribute);
   }
}
