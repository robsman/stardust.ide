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
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
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

   private static final int[] elementFeatureIds = {
      XpdlPackage.FORMAL_PARAMETER_TYPE__ID, XpdlPackage.FORMAL_PARAMETER_TYPE__NAME,
      XpdlPackage.FORMAL_PARAMETER_TYPE__MODE};

   @Before
   public void initCrossModeling()
   {
      strategy = new InMemoryModelManagementStrategy();

      ModelBuilderFacade facade = new ModelBuilderFacade(strategy);

      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      strategy.loadModels().add(consumerModel);
      strategy.loadModels().add(providerModel);

      //Participants
      facade.createRole(providerModel, "Administrator", "Administrator");
      facade.createRole(consumerModel, "Administrator", "Administrator");

      //Primitive Data
      localPrimitive = facade.createPrimitiveData(providerModel, "ProvidedPrimitive", "ProvidedPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      DataType implementingPrimitive = facade.createPrimitiveData(consumerModel, "ImplementingPrimitive", "ImplementingPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);


      //Type Declaration
      facade.createTypeDeclaration(providerModel, "ProvidedComposite", "ProvidedComposite");

      //Processes
      ProcessDefinitionType providedProcess = facade.createProcess(providerModel, "ProvidedProcess", "ProvidedProcess");
      ProcessDefinitionType providedProcess2 = facade.createProcess(providerModel, "ProvidedProcess2", "ProvidedProcess2");
      ProcessDefinitionType processInterface = facade.createProcess(providerModel, "ProcessInterface", "ProcessInterface");
      ProcessDefinitionType consumerProcess = facade.createProcess(consumerModel, "ConsumerProcess", "ConsumerProcess");
      ProcessDefinitionType implementingProcess = facade.createProcess(consumerModel, "ImplementingProcess", "ImplementingProcess");

      //Structured Data / Document Data
      localComposite = facade.createStructuredData(providerModel, "LocalComposite1", "LocalComposite1", "ProviderModel:ProvidedComposite");
      implementingComposite = facade.createStructuredData(consumerModel, "ProvidedComposite1", "ProvidedComposite1", "ProviderModel:ProvidedComposite");
      facade.createDocumentData(providerModel, "LocalDocument", "LocalDocument", "ProvidedComposite");

      //Process Interface (Creation)
      facade.createPrimitiveParameter(processInterface, localPrimitive, "FormalParameter1", "FormalParameter1", ModeType.IN);
      facade.createStructuredParameter(processInterface, localComposite, "FormalParameter2", "FormalParameter2", ModeType.IN);

      //Process Interface (Usage)
      facade.setProcessImplementation(processInterface, implementingProcess);
      facade.setFormalParameter(implementingProcess, "FormalParameter1", implementingPrimitive);
      facade.setFormalParameter(implementingProcess, "FormalParameter2", implementingComposite);

      //Applications
      facade.createApplication(providerModel, "WebService", "WebService", ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID);
      facade.createApplication(providerModel, "Message Transformation", "Message Transformation", ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID);

      //Activities
      ActivityType activity1 = facade.createActivity(providerModel, providedProcess2, "Manual", "ManualActivity1", "ManualActivity1", "ProviderModel:Administrator", null, null);
      facade.createActivity(providerModel, providedProcess2, "Application", "AppActivity1", "AppActivity1", null, "ProviderModel:WebService", null);
      facade.createActivity(consumerModel, consumerProcess, "Subprocess", "ProvidedProcess1", "ProvidedProcess1", null, null, "ProviderModel:ProvidedProcess");

      //Symbols
      LaneSymbol laneSymbol = facade.findLaneSymbolById(providedProcess2, "DefaultLane");
      facade.createActivitySymbol(providerModel, activity1, providedProcess2, laneSymbol.getId(), 40, 40, 180, 50);
      facade.createDataSymbol(providerModel, localComposite, providedProcess2, laneSymbol.getId(), 100, 100, 40, 80);

      //Search & Find
      searchedDataType1 = facade.findData("ProviderModel:ProvidedPrimitive");
      searchedDataType2 = facade.findData(providerModel, "ProvidedPrimitive");
      searchedApplicationType = facade.findApplication("ProviderModel:WebService");
      searchedProcess = facade.findProcessDefinition("ProviderModel:ProvidedProcess2");
      searchedType = facade.findTypeDeclaration("ProviderModel:ProvidedComposite");
      searchedRole = facade.findParticipant("ProviderModel:Administrator");


      //Store
      byte[] modelContent = XpdlModelIoUtils.saveModel(consumerModel);

      //Output
      System.out.println(new String(modelContent));
   }

   private void createPrimitiveParameter(ProcessDefinitionType processInterface)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();
      parameterType.setId("FormalParameter1");
      parameterType.setName("Formal Parameter 1");
      parameterType.setMode(ModeType.IN);

      DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      BasicTypeType basicType = xpdlFactory.createBasicTypeType();
      basicType.setType(TypeType.STRING_LITERAL);
      dataTypeType.setBasicType(basicType);
      parameterType.setDataType(dataTypeType);
      String typeId = localPrimitive.getType().getId();
      dataTypeType.setCarnotType(typeId);

      FormalParametersType parametersType = xpdlFactory.createFormalParametersType();
      parametersType.addFormalParameter(parameterType);
      processInterface.setFormalParameters(parametersType);

      FormalParameterMappingsType parameterMappingsType = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      parameterMappingsType.setMappedData(parameterType, localPrimitive);
      processInterface.setFormalParameterMappings(parameterMappingsType);
   }

   private void createStructuredParameter(ProcessDefinitionType processInterface)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();
      parameterType.setId("FormalParameter2");
      parameterType.setName("Formal Parameter 2");
      parameterType.setMode(ModeType.IN);

      DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      String typeId = localComposite.getType().getId();

      parameterType.setDataType(dataTypeType);
      dataTypeType.setCarnotType(typeId);

      DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
      declaredType.setId(AttributeUtil.getAttributeValue(localComposite, StructuredDataConstants.TYPE_DECLARATION_ATT));

      dataTypeType.setDeclaredType(declaredType);

      FormalParametersType parametersType = processInterface.getFormalParameters();
      parametersType.addFormalParameter(parameterType);
      processInterface.setFormalParameters(parametersType);

      FormalParameterMappingsType parameterMappingsType = processInterface.getFormalParameterMappings();
      parameterMappingsType.setMappedData(parameterType, localComposite);
      processInterface.setFormalParameterMappings(parameterMappingsType);
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

      assertThat(searchedApplicationType, not(is(nullValue())));
      assertThat(searchedApplicationType.getId(),is("WebService"));

      assertThat(searchedProcess, not(is(nullValue())));
      assertThat(searchedProcess.getId(),is("ProvidedProcess2"));

      assertThat(searchedType, not(is(nullValue())));
      assertThat(searchedType.getId(),is("ProvidedComposite"));

      assertThat(searchedRole, not(is(nullValue())));
      assertThat(searchedRole.getId(),is("Administrator"));

   }



}
