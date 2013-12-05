/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.defaults;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public class DefaultElementsInitializer implements ModelInitializer
{

   public void initializeModel(ModelType model)
   {
      ModelBuilderFacade facade = new ModelBuilderFacade();

      DataType data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_ID, "Process OID",
            ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);
      data = facade.createPrimitiveData(model, PredefinedConstants.ROOT_PROCESS_ID,
            "Root Process OID", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);      
      data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_PRIORITY,
            "Process Priority", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);      
      data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_LOCALE,
            "Current Locale", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);      
      data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_DATE, "Current Date",
            ModelerConstants.DATE_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);      
   }
}