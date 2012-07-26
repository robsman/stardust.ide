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
import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.DEFAULT_CONTEXT;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newInDataMapping;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOutDataMapping;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newTransition;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Before;
import org.junit.Test;


public class ModelBuilderTest
{

   private ModelType model;

   @Before
   public void initSimpleModel()
   {
      this.model = newBpmModel().withName("Test Model").build();

      DataType aString = newPrimitiveVariable(model).withId("aString")
            .ofType(Type.String)
            .build();

      ProcessDefinitionType testProcess = newProcessDefinition(model).withIdAndName(
            "TEST_PROCESS", "Test Process").build();

      newManualTrigger(testProcess).accessibleTo(ADMINISTRATOR_ROLE).build();
      ActivityType activity1 = newManualActivity(testProcess).withId("Activity1")
            .havingDefaultPerformer(ADMINISTRATOR_ROLE)
            .build();
      ActivityType activity2 = newManualActivity(testProcess).withId("Activity2")
            .havingDefaultPerformer(ADMINISTRATOR_ROLE)
            .build();
      newTransition(testProcess).betweenActivities(activity1, activity2).build();

      newOutDataMapping(activity1).inContext(DEFAULT_CONTEXT).toVariable(aString).build();
      newInDataMapping(activity2).inContext(DEFAULT_CONTEXT)
            .fromVariable(aString)
            .build();

      BpmModelBuilder.assignMissingElementOids(model);
   }

   @Test
   public void verifyStringVariable()
   {
      DataType aString = XpdlModelUtils.findElementById(model.getData(), "aString");

      assertNotNull(aString);
      assertTrue(aString.isSetElementOid());
   }

}
