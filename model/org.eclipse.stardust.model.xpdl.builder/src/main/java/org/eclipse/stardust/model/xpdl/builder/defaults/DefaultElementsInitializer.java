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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveVariable;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public class DefaultElementsInitializer implements ModelInitializer
{

   public void initializeModel(ModelType model)
   {
      /*newRole(model).withId(PredefinedConstants.ADMINISTRATOR_ROLE)
            .withName("Administrator")
            .withDescription("In charge of all workflow administration activities.")
            .build();*/

      newPrimitiveVariable(model).withIdAndName(PredefinedConstants.PROCESS_ID,
            "Process OID")
            .ofType(Long.class)
            .build();

      newPrimitiveVariable(model).withIdAndName(PredefinedConstants.ROOT_PROCESS_ID,
            "Root Process OID")
            .ofType(Long.class)
            .build();

      newPrimitiveVariable(model).withIdAndName(PredefinedConstants.PROCESS_PRIORITY,
            "Process Priority")
            .ofType(Integer.class)
            .build();      
   }

}
