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

      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      strategy.loadModels().add(consumerModel);
      strategy.loadModels().add(providerModel);
      MBFacade.getInstance(strategy).createRole(providerModel, "Adminitrator", "Administrator");
      MBFacade.getInstance().createRole(consumerModel, "Adminitrator", "Administrator");
      MBFacade.getInstance().createPrimitiveData(providerModel, "ProvidedPrimitive",
            "ProvidedPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      MBFacade.getInstance().createTypeDeclaration(providerModel, "ProvidedComposite",
            "ProvidedComposite");
      ProcessDefinitionType providedProcess = MBFacade.getInstance().createProcess(providerModel,
            "ProvidedProcess", "ProvidedProcess");
      ProcessDefinitionType consumerProcess = MBFacade.getInstance().createProcess(consumerModel,
            "ConsumerProcess", "ConsumerProcess");
      MBFacade.getInstance().createStructuredData(consumerModel, "ProviderModel", "ProvidedComposite1",
            "ProvidedComposite1", "ProvidedComposite");
      long maxOid = XpdlModelUtils.getMaxUsedOid(consumerModel);
      MBFacade.getInstance().createActivity("ConsumerModel", consumerProcess, "Subprocess", null,
            "ProvidedProcess1", "ProvidedProcess1", null,
            "ProviderModel:ProvidedProcess", maxOid);
      byte[] modelContent = XpdlModelIoUtils.saveModel(consumerModel);
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
