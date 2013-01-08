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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.builder.strategy.InMemoryModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.junit.Before;
import org.junit.Test;

public class CrossModelSupportModelBuilderTest
{

   private InMemoryModelManagementStrategy strategy;
   private DataType searchedDataType1;
   private DataType searchedDataType2;

   private ApplicationType searchedApplicationType;
   private ProcessDefinitionType searchedProcess;
   private TypeDeclarationType searchedType;
   private IModelParticipant searchedRole;

   private static final Type[] TYPES = {
      Type.Calendar, Type.String, Type.Timestamp, Type.Boolean, Type.Byte, Type.Char,
      Type.Double, Type.Float, Type.Integer, Type.Long, Type.Short};

   List<Type> primitiveTypes = Arrays.asList(TYPES);

   List<Object> dataTypes = new ArrayList<Object>();
   private DataType localPrimitive;
   private DataType localComposite;
   private DataType implementingComposite;
   private DataType localConsumerComposite;
   private DataType localConsumerPrimitive;

   private static final int[] elementFeatureIds = {
      XpdlPackage.FORMAL_PARAMETER_TYPE__ID, XpdlPackage.FORMAL_PARAMETER_TYPE__NAME,
      XpdlPackage.FORMAL_PARAMETER_TYPE__MODE};

   @Before
   public void initCrossModeling()
   {
      strategy = new InMemoryModelManagementStrategy();

      ModelBuilderFacade facade = new ModelBuilderFacade(strategy);

      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      providerModel.setConnectionManager(new WebModelerConnectionManager(providerModel, strategy));
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      consumerModel.setConnectionManager(new WebModelerConnectionManager(consumerModel, strategy));
      strategy.registerModel(consumerModel);
      strategy.registerModel(providerModel);

      //Participants
      facade.createRole(providerModel, "Administrator", "Administrator");
      facade.createRole(consumerModel, "Administrator", "Administrator");

      //Primitive Data
      localPrimitive = facade.createPrimitiveData(providerModel, "ProvidedPrimitive", "ProvidedPrimitive", ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE);
      localConsumerPrimitive = facade.createPrimitiveData(consumerModel, "ConsumerPrimitive", "ConsumerPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      facade.updatePrimitiveData(localConsumerPrimitive, Type.Integer.getId());
      DataType implementingPrimitive = facade.createPrimitiveData(consumerModel, "ImplementingPrimitive", "ImplementingPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);

      //Type Declaration
      facade.createTypeDeclaration(providerModel, "ProvidedComposite", "ProvidedComposite");
      facade.createTypeDeclaration(consumerModel, "ConsumerComposite", "ConsumerComposite");

      //Processes
      ProcessDefinitionType providedProcess = facade.createProcess(providerModel, "ProvidedProcess", "ProvidedProcess");
      ProcessDefinitionType providedProcess2 = facade.createProcess(providerModel, "ProvidedProcess2", "ProvidedProcess2");
      ProcessDefinitionType processInterface = facade.createProcess(providerModel, "ProcessInterface", "ProcessInterface");
      ProcessDefinitionType consumerProcess = facade.createProcess(consumerModel, "ConsumerProcess", "ConsumerProcess");
      ProcessDefinitionType implementingProcess = facade.createProcess(consumerModel, "ImplementingProcess", "ImplementingProcess");

      //Structured Data / Document Data
      localComposite = facade.createStructuredData(providerModel, "LocalComposite1", "LocalComposite1", "ProviderModel:ProvidedComposite");
      implementingComposite = facade.createStructuredData(consumerModel, "ProvidedComposite1", "ProvidedComposite1", "ProviderModel:ProvidedComposite");
      localConsumerComposite = facade.createStructuredData(consumerModel, "ConsumerComposite1", "ConsumerComposite1", "ConsumerModel:ConsumerComposite");
      facade.updateStructuredDataType(localConsumerComposite, "ProviderModel:ProvidedComposite");
      DataType localDocument = facade.createDocumentData(providerModel, "LocalDocument", "LocalDocument", "ProvidedComposite");
      DataType test = facade.createDocumentData(consumerModel, "ConsumerDocument", "ConsumerDocument", "ConsumerComposite");
      facade.updateDocumentDataType(test, "ProviderModel:ProvidedComposite");

      //Process Interface (Creation)
      facade.createPrimitiveParameter(processInterface, localPrimitive, "FormalParameter1", "FormalParameter1", ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE, ModeType.IN);
      facade.createStructuredParameter(processInterface, localComposite, "FormalParameter2", "FormalParameter2", "ProviderModel:ProvidedComposite", ModeType.IN);
      facade.createDocumentParameter(processInterface, localDocument, "FormalParameter3", "FormalParameter3", "ProviderModel:ProvidedComposite", ModeType.IN);

      //Process Interface (Usage)
      facade.setProcessImplementation(processInterface, implementingProcess);
      facade.setFormalParameter(implementingProcess, "FormalParameter1", implementingPrimitive);
      facade.setFormalParameter(implementingProcess, "FormalParameter2", implementingComposite);

      //Applications
      facade.createApplication(providerModel, "WebServiceApp", "WebServiceApp", ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID);
      facade.createApplication(providerModel, "CamelApp", "CamelApp", ModelerConstants.CAMEL_APPLICATION_TYPE_ID);

      ApplicationType mta = facade.createApplication(providerModel, "MyMTA", "MyMTA", ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID);
      facade.createPrimitiveAccessPoint(mta, "InputString1", "InputString", Type.String.getId(), "IN");
      facade.createPrimitiveAccessPoint(mta, "OuputString1", "OutputString", Type.String.getId(), "OUT");
      facade.createStructuredAccessPoint(mta, "InputStruct1", "InputStruct", "ProviderModel:ProvidedComposite", "IN");
      facade.createStructuredAccessPoint(mta, "OutputStruct1", "OutputStruct", "ProviderModel:ProvidedComposite", "OUT");


      ApplicationType externalWebApp = facade.createApplication(providerModel, "UI MashUp", "UI MashUp", ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      ContextType cty = facade.getApplicationContext(externalWebApp, ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      facade.createPrimitiveAccessPoint(cty, "NewPrimitive", "NewPrimitive", Type.String.getId() , "IN");
      facade.createStructuredAccessPoint(cty, "NewStruct", "NewStruct", "ProviderModel:ProvidedComposite", "IN");

      ApplicationType consumedExtWebApp = facade.createApplication(consumerModel, "UI MashUp", "UI MashUp", ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      facade.createStructuredAccessPoint(consumedExtWebApp, "NewStruct", "NewStruct", "ProviderModel:ProvidedComposite", "IN");

      //Activities
      ActivityType activity1 = facade.createActivity(providerModel, providedProcess2, "Task", "Manual", "ManualActivity1", "ManualActivity1", "ProviderModel:Administrator", null, null);
      facade.createActivity(consumerModel, consumerProcess, "Subprocess", null, "ProvidedProcess1", "ProvidedProcess1", null, null, "ProviderModel:ProvidedProcess");

      //Symbols
      LaneSymbol laneSymbol = facade.findLaneSymbolById(providedProcess2, "DefaultLane");
      facade.createActivitySymbol(providerModel, activity1, providedProcess2, laneSymbol.getId(), 40, 40, 180, 50);
      facade.createDataSymbol(providerModel, localComposite, providedProcess2, laneSymbol.getId(), 100, 100, 40, 80);

      //Search & Find
      searchedDataType1 = facade.findData("ProviderModel:ProvidedPrimitive");
      searchedDataType2 = facade.findData(providerModel, "ProvidedPrimitive");
      searchedProcess = facade.findProcessDefinition("ProviderModel:ProvidedProcess2");
      searchedType = facade.findTypeDeclaration("ProviderModel:ProvidedComposite");
      searchedRole = facade.findParticipant("ProviderModel:Administrator");



      try
      {
         //Thread.sleep(5000);
         byte[] modelContent = XpdlModelIoUtils.saveModel(providerModel);
         //Thread.sleep(5000);
         System.out.println(new String(modelContent));
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      //Output

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

   @Test
   public void verifySearchAndFind()
   {
      assertThat(searchedDataType1, not(is(nullValue())));
      assertThat(searchedDataType1.getId(),is("ProvidedPrimitive"));

      assertThat(searchedDataType2, not(is(nullValue())));
      assertThat(searchedDataType2.getId(),is("ProvidedPrimitive"));

      assertThat(searchedProcess, not(is(nullValue())));
      assertThat(searchedProcess.getId(),is("ProvidedProcess2"));

      assertThat(searchedType, not(is(nullValue())));
      assertThat(searchedType.getId(),is("ProvidedComposite"));

      assertThat(searchedRole, not(is(nullValue())));
      assertThat(searchedRole.getId(),is("Administrator"));

   }



}
