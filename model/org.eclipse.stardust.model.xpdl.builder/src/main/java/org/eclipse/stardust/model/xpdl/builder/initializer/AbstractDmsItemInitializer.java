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

import java.util.Arrays;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;


/**
 * @author rsauer
 * @version $Revision: 51288 $
 */
public abstract class AbstractDmsItemInitializer implements IDataInitializer
{
   protected abstract Class<?> getInterfaceType();
   
   public List<AttributeType> initialize(DataType data, List<AttributeType> attributes)
   {
      AttributeType attrClassName = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attrClassName.setName(CarnotConstants.CLASS_NAME_ATT);
      attrClassName.setValue(getInterfaceType().getName());

      AttributeType attrBidirectional = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attrBidirectional.setName(CarnotConstants.ENGINE_SCOPE + "data:bidirectional"); //$NON-NLS-1$
      attrBidirectional.setValue(Boolean.TRUE.toString());
      attrBidirectional.setType(Boolean.TYPE.getName());

      return Arrays.asList(new AttributeType[] {attrClassName, attrBidirectional});
   }
}
