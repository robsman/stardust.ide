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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import org.eclipse.stardust.model.xpdl.builder.strategy.InMemoryModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.utils.MBFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;

public class CrossModelSupportModelBuilderTest
{

   private InMemoryModelManagementStrategy strategy;

   @Before
   public void initCrossModeling()
   {
      strategy = new InMemoryModelManagementStrategy();
      
      MBFacade facade = new MBFacade(strategy);

      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      strategy.loadModels().add(consumerModel);
      strategy.loadModels().add(providerModel);
      
      //Participants
      facade.createRole(providerModel, "Administrator", "Administrator");
      facade.createRole(consumerModel, "Administrator", "Administrator");
      
      //Primitive Data
      facade.createPrimitiveData(providerModel, "ProvidedPrimitive", "ProvidedPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      
      //Type Declaration      
      facade.createTypeDeclaration(providerModel, "ProvidedComposite", "ProvidedComposite");
      
      //Processes
      ProcessDefinitionType providedProcess = facade.createProcess(providerModel, "ProvidedProcess", "ProvidedProcess");
      ProcessDefinitionType providedProcess2 = facade.createProcess(providerModel, "ProvidedProcess2", "ProvidedProcess2");      
      ProcessDefinitionType consumerProcess = facade.createProcess(consumerModel, "ConsumerProcess", "ConsumerProcess");
      
      
      //Structured Data / Document Data
      facade.createStructuredData(providerModel, "LocalComposite1", "LocalComposite1", "ProviderModel:ProvidedComposite");
      facade.createStructuredData(consumerModel, "ProvidedComposite1", "ProvidedComposite1", "ProviderModel:ProvidedComposite");     
      facade.createDocumentData(providerModel, "LocalDocument", "LocalDocument", "ProvidedComposite");
      
      //Applications     
      facade.createApplication(providerModel, "WebService", "WebService", ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID);
      facade.createApplication(providerModel, "Message Transformation", "Message Transformation", ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID);
      
      //Activities      
      ActivityType activity1 = facade.createActivity(providerModel, providedProcess2, "Manual", "ProviderModel:Administrator", "ManualActivity1", "ManualActivity1", null, null);            
      facade.createActivity(providerModel, providedProcess2, "Application", null, "AppActivity1", "AppActivity1", "ProviderModel:WebService", null);      
      facade.createActivity(consumerModel, consumerProcess, "Subprocess", null, "ProvidedProcess1", "ProvidedProcess1", null, "ProviderModel:ProvidedProcess");
      
      //Symbols
      LaneSymbol laneSymbol = facade.findLaneSymbolById(providedProcess2, "DefaultLane");
      facade.createActivitySymbol(providerModel, providedProcess2, laneSymbol.getId(), 40, 40, 180, 50, activity1);
      
      
      //Store
      byte[] modelContent = XpdlModelIoUtils.saveModel(providerModel);
      
      //Output
      System.out.println(new String(modelContent));
   }

   @Test
   public void verifyExternalPackagesForSingleConnection()
   {
      ModelType model = strategy.loadModel("ConsumerModel");

      ExternalPackages externalPackages = model.getExternalPackages();
      assertThat(externalPackages, not(is(nullValue())));
      assertThat(externalPackages.getExternalPackage().size(), is(1));

      ExternalPackage externalPackage = externalPackages.getExternalPackage().get(0);
      assertThat(externalPackage.getHref(), is("ProviderModel"));
      assertThat(externalPackage.getId(), is("ProviderModel"));
      assertThat(externalPackage.getName(), is("ProviderModel"));
   }



}
