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
package org.eclipse.stardust.model.xpdl.builder.initializer;

import java.util.List;

import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class DataStructInitializer implements IDataInitializer
{
   public List initialize(DataType data, List<AttributeType> attributes)
   {
      AttributeUtil.setAttribute(data, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
      //If attributes originally from DocumentData
      String typeDecl = AttributeUtil.getAttributeValue(attributes, "carnot:engine:dms:resourceMetadataSchema");
      if (typeDecl != null) {
         AttributeType attrTypeName = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
         attrTypeName.setName(StructuredDataConstants.TYPE_DECLARATION_ATT);
         attrTypeName.setValue(typeDecl);
         data.getAttribute().add(attrTypeName);
      }
      return null;
   }
}