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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
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

      ModelBuilderTest.assignMissingElementOids(model);
   }

   @Test
   public void verifyStringVariable()
   {
      DataType aString = ModelUtils.findElementById(model.getData(), "aString");

      assertNotNull(aString);
      assertTrue(aString.isSetElementOid());
   }
   
   @Test
   public void verifyIdGeneration1()
   {
      ProcessDefinitionType pd = ModelUtils.findElementById(model.getProcessDefinition(), "TEST_PROCESS");
      assertNotNull("Process definition not found", pd);
      
      DataType aString = ModelUtils.findElementById(model.getData(), "aString");
      assertNotNull("Data not found", aString);
      
      ModelBuilderFacade mb = new ModelBuilderFacade();
      FormalParameterType param = mb.createPrimitiveParameter(pd, aString, "jumbo", "jet", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE, ModeType.IN);
      assertEquals("Id", "jet", param.getId());
      assertEquals("Name", "jet", param.getName());
   }

   @Test
   public void verifyIdGeneration2()
   {
      ProcessDefinitionType pd = ModelUtils.findElementById(model.getProcessDefinition(), "TEST_PROCESS");
      assertNotNull("Process definition not found", pd);
      
      DataType aString = ModelUtils.findElementById(model.getData(), "aString");
      assertNotNull("Data not found", aString);
      
      ModelBuilderFacade mb = new ModelBuilderFacade();
      FormalParameterType param = mb.createPrimitiveParameter(pd, aString, "jumbo", "  ", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE, ModeType.IN);
      assertEquals("Id", "jumbo", param.getId());
      assertEquals("Name", "jumbo", param.getName());
   }

   @Test
   public void verifyIdGeneration3()
   {
      ProcessDefinitionType pd = ModelUtils.findElementById(model.getProcessDefinition(), "TEST_PROCESS");
      assertNotNull("Process definition not found", pd);
      
      DataType aString = ModelUtils.findElementById(model.getData(), "aString");
      assertNotNull("Data not found", aString);
      
      ModelBuilderFacade mb = new ModelBuilderFacade();
      FormalParameterType param = mb.createPrimitiveParameter(pd, aString, " ", " ", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE, ModeType.IN);
      assertEquals("Id", "FormalParameter1", param.getId());
      assertEquals("Name", "FormalParameter 1", param.getName());
   }

   public static void assignMissingElementOids(ModelType model)
   {
      long maxElementOid = ModelUtils.getMaxUsedOid(model);

      if ( !model.isSetOid())
      {
         model.setOid(++maxElementOid);
      }

      for (TreeIterator<EObject> modelContents = model.eAllContents(); modelContents.hasNext(); )
      {
         EObject element = modelContents.next();
         if ((element instanceof IModelElement)
               && !((IModelElement) element).isSetElementOid())
         {
            ((IModelElement) element).setElementOid(++maxElementOid);
         }
      }
   }
}