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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * @author fherinean
 * @version $Revision$
 */
public class JavaApplicationInitializer implements IApplicationInitializer
{
   private String sourceName;

   private String targetName;

   public JavaApplicationInitializer(String sourceName, String targetName)
   {
      this.sourceName = sourceName;
      this.targetName = targetName;
   }

   /**
    * Gets all attributes having the same name like source or target name, gets the values
    * of them and creates new attributes with the target name and the retrieved values.
    * Gets also all method name attributes.
    * 
    * @param data
    *           The application type.
    * @param attributes
    * @return All {@link AttributeType}s which are filtered from the given attributes
    *         list.
    */
   public List initialize(ApplicationType data, List attributes)
   {
      List result = null;
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (sourceName.equals(attribute.getName())
               || targetName.equals(attribute.getName()))
         {
            String className = attribute.getValue();
            if (className != null && className.length() > 0)
            {
               result = addAttribute(result, targetName, className);
            }
         }
         else if (CarnotConstants.METHOD_NAME_ATT.equals(attribute.getName()))
         {
            String methodName = attribute.getValue();
            if (methodName != null && methodName.length() > 0)
            {
               result = addAttribute(result, CarnotConstants.METHOD_NAME_ATT, methodName);
            }
         }
         else if (CarnotConstants.CREATE_METHOD_NAME_ATT.equals(attribute.getName()))
         {
            String methodName = attribute.getValue();
            if (methodName != null && methodName.length() > 0)
            {
               result = addAttribute(result, CarnotConstants.CREATE_METHOD_NAME_ATT, methodName);
            }
         }
      }
      return result;
   }

   /**
    * Creates a new attribute with the given name and value and adds it to the result
    * list.
    * 
    * @param result
    *           All created attributes.
    * @param name
    *           The name of the new attribute.
    * @param value
    *           The value of the new attribute.
    * @return The list with all created attributes.
    */
   private List addAttribute(List result, String name, String value)
   {
      if (result == null)
      {
         result = new ArrayList();
      }
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE
            .createAttributeType();
      attribute.setName(name);
      attribute.setValue(value);
      result.add(attribute);
      return result;
   }
}
