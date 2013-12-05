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
package org.eclipse.stardust.model.xpdl.builder;

import static junit.framework.Assert.assertNotNull;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newDocumentAccessPoint;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.junit.Before;
import org.junit.Test;


public class TriggerTest
{

   private ModelType model;

   @Before
   public void initSimpleModel()
   {
      this.model = newBpmModel().withName("Test Model").build();

      ProcessDefinitionType testProcess = newProcessDefinition(model).withIdAndName(
            "TEST_PROCESS", "Test Process").build();

      // TODO: replace by scan trigger 
      TriggerType trigger = newManualTrigger(testProcess).accessibleTo(ADMINISTRATOR_ROLE).build();
      AccessPointType aPoint = newDocumentAccessPoint(trigger).withIdAndName("aPoint", "aPoint")
         .withDirection(DirectionType.IN_LITERAL.getName())
         .build();

      ModelBuilderTest.assignMissingElementOids(model);
   }

   @Test
   public void verifyStringVariable()
   {
      ProcessDefinitionType aProcess = ModelUtils.findElementById(model.getProcessDefinition(), "TEST_PROCESS");
      DataTypeType dataTypeType = new ModelBuilderFacade().findDataType(model,
            PredefinedConstants.DOCUMENT_DATA);
           
      EList<TriggerType> triggers = aProcess.getTrigger();
      TriggerType aTrigger = triggers.get(0);
      
      assertNotNull(aProcess);
      assertNotNull(aTrigger);
      
      EList<AccessPointType> accessPoint = aTrigger.getAccessPoint();
      AccessPointType anAccessPoint = accessPoint.get(0);
      
      assertNotNull(anAccessPoint);
      assertEquals(anAccessPoint.getType(), dataTypeType);      
   }
}