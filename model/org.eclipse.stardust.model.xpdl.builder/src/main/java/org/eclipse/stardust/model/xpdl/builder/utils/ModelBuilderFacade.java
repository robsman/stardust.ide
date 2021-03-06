/*******************************************************************************
 * Copyright (c) 2011, 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.utils;

import static org.eclipse.stardust.common.StringUtils.isEmpty;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.xsd.*;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.ObjectNotFoundException;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.JavaDataTypeUtils;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.spi.extensions.model.AccessPoint;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmSubProcessActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.EObjectProxyHandler;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.IdRefHandler;
import org.eclipse.stardust.model.xpdl.builder.exception.ModelerErrorClass;
import org.eclipse.stardust.model.xpdl.builder.exception.ModelerException;
import org.eclipse.stardust.model.xpdl.builder.initializer.DataStructInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.DmsDocumentInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.PrimitiveDataInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.SerializableDataInitializer;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.variable.*;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IdFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;

public class ModelBuilderFacade
{
   private static final String TIMESTAMP_TYPE = "Timestamp"; //$NON-NLS-1$

   private ModelManagementStrategy modelManagementStrategy;

   public ModelBuilderFacade(ModelManagementStrategy modelManagementStrategy)
   {
      this.modelManagementStrategy = modelManagementStrategy;
   }

   public ModelBuilderFacade()
   {
      // default constructor
   }

   public ModelManagementStrategy getModelManagementStrategy()
   {
      return modelManagementStrategy;
   }

   /**
    * Sets the team leader for an organization.
    *
    * @param organization
    * @param role
    * @return
    */
   public void setTeamLeader(OrganizationType organization, RoleType role)
   {
      // Add previous team lead back as a member
      if (null != organization.getTeamLead())
      {
         addOrganizationParticipant(organization, organization.getTeamLead());
      }

      organization.setTeamLead(role);

      // Remove team lead from the member list
      if (null != role)
      {
         List<ParticipantType> participants = organization.getParticipant();
         ParticipantType removeType = null;
         for (Iterator<ParticipantType> i = participants.iterator(); i.hasNext();)
         {
            ParticipantType participant = i.next();
            if (participant.getParticipant().getId().equals(role.getId()))
            {
               removeType = participant;
            }
         }
         if (removeType != null)
         {
            participants.remove(removeType);
         }
      }
   }

   /**
    * Adds a participant to an organization.
    *
    * @param organization
    * @param participant
    * @return
    */
   public void addOrganizationParticipant(OrganizationType organization,
         IModelParticipant participant)
   {
      ParticipantType participantType = AbstractElementBuilder.F_CWM.createParticipantType();
      participantType.setParticipant(participant);
      organization.getParticipant().add(participantType);
   }

   /**
    * Returns a list of organizations a participants belongs to.
    *
    * @param model
    * @param participant
    * @return list of organizations
    */
   public static List<OrganizationType> getParentOrganizations(ModelType model,
         IModelParticipant participant)
   {
      List<OrganizationType> belongsTo = new ArrayList<OrganizationType>();
      for (OrganizationType org : model.getOrganization())
      {
         for (ParticipantType pt : org.getParticipant())
         {
            if (participant.equals(pt.getParticipant()))
            {
               belongsTo.add(org);
            }
         }
      }
      return belongsTo;
   }

   /**
    * Created a type declaration.
    *
    * @param model
    *           model to create the type declaration in
    * @param typeID
    *           id of the type
    * @param typeName
    *           name of the type
    *
    * @return type declaration created
    */
   public TypeDeclarationType createTypeDeclaration(ModelType model, String typeID,
         String typeName)
   {
      TypeDeclarationType structuredDataType = XpdlFactory.eINSTANCE.createTypeDeclarationType();
      structuredDataType.setName(typeName);

      if (StringUtils.isEmpty(typeID))
      {
         typeID = NameIdUtilsExtension.createIdFromName(typeName);
      }
      structuredDataType.setId(typeID);

      SchemaTypeType schema = XpdlFactory.eINSTANCE.createSchemaTypeType();
      structuredDataType.setSchemaType(schema);

      XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

      xsdSchema.getQNamePrefixToNamespaceMap().put(XSDPackage.eNS_PREFIX,
            XMLResource.XML_SCHEMA_URI);
      xsdSchema.setSchemaForSchemaQNamePrefix(XSDPackage.eNS_PREFIX);

      xsdSchema.setTargetNamespace(TypeDeclarationUtils.computeTargetNamespace(model,
            structuredDataType.getId()));
      String prefix = TypeDeclarationUtils.computePrefix(structuredDataType.getId(),
            xsdSchema.getQNamePrefixToNamespaceMap().keySet());
      xsdSchema.getQNamePrefixToNamespaceMap()
            .put(prefix, xsdSchema.getTargetNamespace());
      xsdSchema.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX
            + structuredDataType.getId());
      schema.setSchema(xsdSchema);

      XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
      xsdComplexTypeDefinition.setName(structuredDataType.getId());
      XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
      XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
      particle.setContent(modelGroup);
      modelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
      xsdComplexTypeDefinition.setContent(particle);
      xsdSchema.getContents().add(xsdComplexTypeDefinition);

      XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      xsdElementDeclaration.setName(structuredDataType.getId());
      xsdElementDeclaration.setTypeDefinition(xsdComplexTypeDefinition);
      xsdSchema.getContents().add(xsdElementDeclaration);

      // propagate ns-prefix mappings to DOM
      schema.getSchema().updateElement(true);

      model.getTypeDeclarations().getTypeDeclaration().add(structuredDataType);

      setAttribute(structuredDataType, PredefinedConstants.MODEL_ELEMENT_UUID, UUID.randomUUID().toString());



      return structuredDataType;
   }

   public FormalParameterType createPrimitiveParameter(
         ProcessDefinitionType processInterface, DataType data, String id, String name,
         String primitiveTypeID, ModeType mode)
   {
      return createPrimitiveParameter(processInterface, data, id, name, primitiveTypeID, mode, null);
   }

   public FormalParameterType createPrimitiveParameter(
         ProcessDefinitionType processInterface, DataType data, String id, String name,
         String primitiveTypeID, ModeType mode, String structTypeFullID)
   {
      String refModelId = null;
      String structTypeId = null;
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();

      FormalParameterType parameterType = createFormalParameter(processInterface, id, name, mode, xpdlFactory);

      FormalParameterMappingsType parameterMappingsType = processInterface.getFormalParameterMappings();

      if (parameterMappingsType == null)
      {
         parameterMappingsType = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      }

      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = XpdlFactory.eINSTANCE.createDataTypeType();

      // For Primitive ENUM, create DeclaredTypeType
      if (StringUtils.isNotEmpty(structTypeFullID))
      {
         String[] splittedIds = structTypeFullID.split(":");
         if (splittedIds.length > 1)
         {
            refModelId = splittedIds[0];
            structTypeId = splittedIds[1];
         }
         else
         {
            structTypeId = splittedIds[0];
         }
         DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
         declaredType.setId(structTypeId);
         dataTypeType.setDeclaredType(declaredType);
      }
      else
      {
         BasicTypeType basicType = xpdlFactory.createBasicTypeType();
         if ( !StringUtils.isEmpty(primitiveTypeID))
         {
            basicType.setType(getPrimitiveType(primitiveTypeID));
         }
         dataTypeType.setBasicType(basicType);
      }
      parameterType.setDataType(dataTypeType);
      String typeId = PredefinedConstants.PRIMITIVE_DATA;
      dataTypeType.setCarnotType(typeId);

      if (data != null)
      {
         parameterMappingsType.setMappedData(parameterType, data);
      }

      processInterface.setFormalParameterMappings(parameterMappingsType);
      return parameterType;
   }

   public void setDataForFormalParameter(ProcessDefinitionType processInterface,
         String parameterID, DataType data)
   {
      FormalParameterType parameterType = processInterface.getFormalParameters()
            .getFormalParameter(parameterID);
      if (parameterType != null)
      {
         processInterface.getFormalParameterMappings().setMappedData(parameterType, data);
      }
   }

   public FormalParameterType createStructuredParameter(
         ProcessDefinitionType processInterface, DataType data, String id, String name,
         String structTypeFullID, ModeType mode)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      FormalParameterType parameterType = createFormalParameter(processInterface, id, name, mode, xpdlFactory);
      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      String typeId = PredefinedConstants.STRUCTURED_DATA;

      parameterType.setDataType(dataTypeType);
      dataTypeType.setCarnotType(typeId);

      String refModelId = null;
      String structTypeId = null;
      if (structTypeFullID == null)
      {
         structTypeFullID = "TO_BE_DEFINED";
      }
      String[] splittedIds = structTypeFullID.split(":");
      if (splittedIds.length > 1)
      {
         refModelId = splittedIds[0];
         structTypeId = splittedIds[1];
      }
      else
      {
         structTypeId = splittedIds[0];
      }

      ModelType model = ModelUtils.findContainingModel(processInterface);
      if (refModelId == null || model != null && refModelId.equals(model.getId()))
      {
         DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
         declaredType.setId(structTypeId);
         dataTypeType.setDeclaredType(declaredType);
      }
      else if (model != null)
      {
         ModelType providerModel = findModel(refModelId);
         ExternalReferenceUtils.createStructReferenceForFormalParameter(parameterType,
               structTypeId, model, providerModel);
      }

      FormalParameterMappingsType parameterMappingsType = processInterface.getFormalParameterMappings();

      if (parameterMappingsType == null)
      {
         parameterMappingsType = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      }

      if (data != null)
      {
         parameterMappingsType.setMappedData(parameterType, data);
      }

      processInterface.setFormalParameterMappings(parameterMappingsType);

      return parameterType;
   }


   public FormalParameterType createDocumentParameter(
         ProcessDefinitionType processInterface, DataType data, String id, String name,
         String structTypeFullID, ModeType mode)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();

      FormalParameterType parameterType = createFormalParameter(processInterface, id, name, mode, xpdlFactory);

      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      String typeId = ModelerConstants.DOCUMENT_DATA_TYPE_KEY;

      parameterType.setDataType(dataTypeType);
      dataTypeType.setCarnotType(typeId);

      String refModelId = null;
      String structTypeId = null;
      if (structTypeFullID == null)
      {
         structTypeFullID = "TO_BE_DEFINED";
      }
      String[] splittedIds = structTypeFullID.split(":");
      if (splittedIds.length > 1)
      {
         refModelId = splittedIds[0];
         structTypeId = splittedIds[1];
      }
      else
      {
         structTypeId = splittedIds[0];
      }

      ModelType model = ModelUtils.findContainingModel(processInterface);
      if (refModelId == null || model != null && refModelId.equals(model.getId()))
      {
         DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
         declaredType.setId(structTypeId);
         dataTypeType.setDeclaredType(declaredType);
      }
      else if (model != null)
      {
         ModelType providerModel = findModel(refModelId);
         ExternalReferenceUtils.createStructReferenceForFormalParameter(parameterType,
               structTypeId, model, providerModel);
      }

      FormalParameterMappingsType parameterMappingsType = processInterface.getFormalParameterMappings();

      if (parameterMappingsType == null)
      {
         parameterMappingsType = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      }

      if (data != null)
      {
         parameterMappingsType.setMappedData(parameterType, data);
      }

      processInterface.setFormalParameterMappings(parameterMappingsType);

      return parameterType;
   }

   private FormalParameterType createFormalParameter(ProcessDefinitionType processInterface, String id, String name,
         ModeType mode, XpdlFactory xpdlFactory)
   {
      boolean forceSuffix = false;
      if (StringUtils.isEmpty(name.trim()))
      {
         if (StringUtils.isEmpty(id.trim()))
         {
            name = "FormalParameter";
            forceSuffix = true;
         }
         else
         {
            name = id;
         }
      }

      FormalParametersType parametersType = processInterface.getFormalParameters();
      if (parametersType == null)
      {
         parametersType = xpdlFactory.createFormalParametersType();
         processInterface.setFormalParameters(parametersType);
      }

      FormalParameterType parameterType = parametersType.getFormalParameter(id);

      if (parameterType == null)
      {
         parameterType = xpdlFactory.createFormalParameterType();
         parametersType.addFormalParameter(parameterType);
      }

      IdFactory idFactory = new IdFactory(null, name.trim(),
            XpdlPackage.eINSTANCE.getFormalParameterType(),
            XpdlPackage.eINSTANCE.getFormalParameterType_Id(),
            XpdlPackage.eINSTANCE.getFormalParameterType_Name());
      List<FormalParameterType> existing = CollectionUtils.copyList(parametersType.getFormalParameter());
      existing.remove(parameterType);
      idFactory.computeNames(existing, forceSuffix);
      id = idFactory.getId();
      name = idFactory.getName();

      if (!id.equals(parameterType.getId()))
      {
         parameterType.setId(id);
      }
      if (!name.equals(parameterType.getName()))
      {
         parameterType.setName(name);
      }
      if (!CompareHelper.areEqual(mode, parameterType.getMode()))
      {
         parameterType.setMode(mode);
      }

      return parameterType;
   }

   public AccessPoint createJavaAccessPoint(String id, String name, String clazz,
         Direction direction, boolean browsable, Object characteristics)
   {
      if (clazz == null)
      {
         clazz = "java.util.Map";
      }

      return JavaDataTypeUtils.createIntrinsicAccessPoint(id, name, clazz, direction,
            browsable, characteristics);
   }

   public AccessPointType createPrimitiveAccessPoint(IAccessPointOwner application,
         String id, String name, String primaryDataTypeID, String direction)
   {
      return newPrimitiveAccessPoint(application).withIdAndName(id, name)
            .withType(primaryDataTypeID)
            .withDirection(direction)
            .build();
   }

   public AccessPointType createStructuredAccessPoint(IAccessPointOwner application,
         String id, String name, String structTypeFullID, String direction)
   {
      AccessPointType accessPoint = newStructuredAccessPoint(application)
            .withIdAndName(id, name).withType(structTypeFullID).withDirection(direction)
            .build();
      String declaredType = AttributeUtil.getAttributeValue(accessPoint,
            ModelerConstants.DATA_TYPE);
      if (declaredType != null)
      {
         if (declaredType.indexOf("{") > 0)
         {
            String refModelID = declaredType.substring(declaredType.indexOf("{") + 1,
                  declaredType.indexOf("}"));
            String typeID = declaredType.substring(declaredType.indexOf("}") + 1);
            if (!typeID.equals(ModelerConstants.TO_BE_DEFINED))
            {
               ModelType refModel = findModel(refModelID);
               if (refModel != null)
               {
                  ExternalReferenceUtils.createStructReferenceForAccessPoint(accessPoint, typeID, refModel);
               }
            }
         }
      }
      return accessPoint;
   }

   public AccessPointType createDocumentAccessPoint(IAccessPointOwner application,
         String id, String name, String direction)
   {
      return newDocumentAccessPoint(application).withIdAndName(id, name)
            .withDirection(direction)
            .build();
   }

   public void setProcessImplementation(ProcessDefinitionType processInterface,
         ProcessDefinitionType processImplementation)
   {
      ModelType interfaceModel = ModelUtils.findContainingModel(processInterface);
      ModelType implementationModel = ModelUtils.findContainingModel(processImplementation);
      ExternalPackage packageRef = implementationModel.getExternalPackages()
            .getExternalPackage(interfaceModel.getId());

      IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
      idRef.setRef(processInterface.getId());
      idRef.setPackageRef(packageRef);
      processImplementation.setExternalRef(idRef);
      IdRefHandler.adapt(processImplementation);

      FormalParameterMappingsType parameterMappings = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      FormalParametersType referencedParametersType = processInterface.getFormalParameters();
      FormalParametersType formalParameters = XpdlFactory.eINSTANCE.createFormalParametersType();
      for (Iterator<FormalParameterType> i = referencedParametersType.getFormalParameter()
            .iterator(); i.hasNext();)
      {
         FormalParameterType referencedParameterType = i.next();
         FormalParameterType parameterType = ModelUtils.cloneFormalParameterType(
               referencedParameterType, null);
         formalParameters.addFormalParameter(parameterType);
         parameterMappings.setMappedData(parameterType, null);
      }
      processImplementation.setFormalParameters(formalParameters);
      processImplementation.setFormalParameterMappings(parameterMappings);
   }

   public void setFormalParameter(ProcessDefinitionType implementingProcess,
         String parameterID, DataType dataType)
   {
      FormalParameterType formalParameterType = implementingProcess.getFormalParameters()
            .getFormalParameter(parameterID);
      implementingProcess.getFormalParameterMappings().setMappedData(formalParameterType,
            dataType);
   }

   /**
    * Created a data of type <b>Document</b>.
    *
    * @param model
    *           model to create the document data in
    * @param dataID
    *           id of the data
    * @param dataName
    *           name of the data
    * @param typeDeclarationID
    *           id of the type declaration assigned to the document
    *
    * @return document data created
    */
   public DataType createDocumentData(ModelType model, String dataID, String dataName,
         String typeDeclarationID)
   {
      DataType data;
      ModelType typeDeclarationModel = null;
      String sourceModelID = null;

      BpmDocumentVariableBuilder documentVariable = newDocumentVariable(model);
      if ( !StringUtils.isEmpty(typeDeclarationID))
      {
         sourceModelID = getModelId(typeDeclarationID);
         if (sourceModelID != null)
         {
            typeDeclarationModel = getModelManagementStrategy().getModels().get(
                  sourceModelID);
         }

         typeDeclarationID = stripFullId(typeDeclarationID);
         documentVariable.setTypeDeclarationModel(typeDeclarationModel);
         documentVariable.setTypeDeclaration(typeDeclarationID);
      }

      data = documentVariable.withIdAndName(dataID, dataName).build();

      return data;
   }

   public DataType createDocumentListData(ModelType model, String dataID, String dataName,
         String typeDeclarationID)
   {
      BpmDocumentListVariableBuilder documentVariable = newDocumentListVariable(model);
      if (!StringUtils.isEmpty(typeDeclarationID))
      {
         String sourceModelID = getModelId(typeDeclarationID);
         if (sourceModelID != null)
         {
            documentVariable.setTypeDeclarationModel(getModelManagementStrategy().getModels().get(sourceModelID));
         }
         documentVariable.setTypeDeclaration(stripFullId(typeDeclarationID));
      }
      return documentVariable.withIdAndName(dataID, dataName).build();
   }

   /**
    * Created a data of type <b>Structured Type</b>.
    *
    * <p>
    * The <i>typeFullID</i> id is provided as <b>ModelID:TypedeclarationID</b>.
    * </p>
    *
    * @param model
    *           model to create the document data in
    * @param dataID
    *           id of the data
    * @param dataName
    *           name of the data
    * @param typeFullID
    *           full qualified id of the type declaration assigned to the document
    *
    * @return structured data created
    */
   public DataType createStructuredData(ModelType model, String dataID, String dataName,
         String typeFullID)
   {
      DataType data;
      String sourceModelID = getModelId(typeFullID);
      ModelType typeDeclarationModel = getModelManagementStrategy().getModels().get(
            sourceModelID);

      BpmStructVariableBuilder structVariable = newStructVariable(model);
      structVariable.setTypeDeclarationModel(typeDeclarationModel);

      String[] ids = typeFullID.split(":");
      String typeValue = null;
      if (ids.length > 1)
      {
         typeValue = this.stripFullId(typeFullID);
      }

      data = structVariable.withIdAndName(dataID, dataName).ofType(typeValue).build();

      return data;
   }

   public void convertDataType(DataType data, String targetTypeID)
   {
      if (data.getType().getId().equals(targetTypeID))
      {
         return;
      }

      data.getAttribute().clear();
      ModelType model = ModelUtils.findContainingModel(data);
      data.setType(ModelUtils.findIdentifiableElement(model.getDataType(), targetTypeID));
      IDataInitializer init = getInitializer(targetTypeID);
      init.initialize(data, data.getAttribute());
   }

   /**
    * Update the type of a primitive data.
    *
    * @param data
    *           the primitive data to update
    * @param primitiveTypeID
    *           the id of the primitive type to assign
    *
    * @return
    */
   public void updatePrimitiveData(DataType data, String primitiveTypeID)
   {
      AttributeUtil.setAttribute(data, PredefinedConstants.TYPE_ATT,
            Type.class.getName(), primitiveTypeID);
   }

   /**
    *
    * @param dataJson
    * @param data
    */
   public void updateTypeForPrimitive(DataType data, String typeFullID)
   {
      ModelType model = ModelUtils.findContainingModel(data);
      String declaredTypeID = null;
      String sourceModelID = getModelId(typeFullID);
      String declarationID = stripFullId(typeFullID);
      if (sourceModelID.equals(model.getId()))
      {
         declaredTypeID = declarationID;
      }
      else
      {
         declaredTypeID = "typeDeclaration:{" + sourceModelID + "}" + declarationID;
         ModelType typeDeclarationModel = getModelManagementStrategy().getModels().get(
               sourceModelID);

         ExternalReferenceUtils.createStructReferenceForPrimitive(data, model,
               declarationID, typeDeclarationModel);
      }
      AttributeUtil.setAttribute(data, ModelerConstants.DATA_TYPE, declaredTypeID);
   }


   /**
    * Update the type declaration a structured data refers to.
    *
    * <p>
    * The <i>typeFullID</i> id is provided as <b>ModelID:TypedeclarationID</b>.
    * </p>
    *
    * @param data
    *           the structured data to update
    * @param typeFullID
    *           full qualified id of the type declaration to assign to the data
    *
    * @return
    */
   public void updateStructuredDataType(DataType data, String typeFullID)
   {
      ModelType model = ModelUtils.findContainingModel(data);
      String sourceModelID = getModelId(typeFullID);
      ModelType typeDeclarationModel = getModelManagementStrategy().getModels().get(
            sourceModelID);
      if (typeDeclarationModel != null)
      {
         String declarationID = stripFullId(typeFullID);
         TypeDeclarationType typeDeclaration = this.findTypeDeclaration(typeFullID);

         if (sourceModelID.equals(model.getId()))
         {
            data.setExternalReference(null);
            AttributeType attribute = AttributeUtil.setAttribute(data,
                  StructuredDataConstants.TYPE_DECLARATION_ATT, declarationID);
            ModelUtils.setReference(attribute, model, "struct");
            AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, null);
         }
         else
         {
            ExternalReferenceUtils.createExternalReferenceToTypeDeclaration(data, model,
                  typeDeclarationModel, typeDeclaration);
         }
      }
      else if(typeFullID == null || typeFullID.equals(ModelerConstants.TO_BE_DEFINED))
      {
         data.setExternalReference(null);
         AttributeUtil.setAttribute(data,
               StructuredDataConstants.TYPE_DECLARATION_ATT, null);
         AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
   }

   public void updateDocumentDataType(DataType data, String typeFullID)
   {
      ModelType model = ModelUtils.findContainingModel(data);
      String sourceModelID = getModelId(typeFullID);
      ModelType typeDeclarationModel = getModelManagementStrategy().getModels().get(
            sourceModelID);

      if (typeDeclarationModel != null)
      {
         String qualifiedId = null;
         String declarationID = stripFullId(typeFullID);
         TypeDeclarationType typeDeclaration = this.findTypeDeclaration(typeFullID);

         if (sourceModelID.equals(model.getId()))
         {
            data.setExternalReference(null);
            AttributeType attribute = AttributeUtil.setAttribute(data,
                  DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, declarationID);
            ModelUtils.setReference(attribute, model, "struct");
            AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, null);
            qualifiedId = typeDeclaration.getId();
         }
         else
         {
            ExternalReferenceUtils.createExternalReferenceToDocument(data, model, typeDeclarationModel,  typeDeclaration);
            qualifiedId = typeDeclarationModel.getId() + "{" + typeDeclaration.getId() + "}";
         }

         AttributeUtil.setAttribute(data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, qualifiedId);
      }
   }


   /**
    * Created a entity data.
    *
    * @param model
    *           model to create the data in
    * @param dataID
    *           id of the data
    * @param dataName
    *           name of the data
    *
    * @return entity data created
    */
   public DataType createEntityData(ModelType model, String dataID, String dataName,
         String className, String value)
   {
      BpmEntityVariableBuilder<Object> entityVariable = newEntityVariable(model);
      entityVariable.withIdAndName(dataID, dataName);

      return entityVariable.build();
   }

   /**
    * Created a serializable data.
    *
    * @param model
    *           model to create the data in
    * @param dataID
    *           id of the data
    * @param dataName
    *           name of the data
    * @param serializable class
    *           id of class
    *
    * @return serializable data created
    */
   public DataType createSerializableData(ModelType model, String dataID, String dataName,
         String className, String value)
   {

      BpmSerializableVariableBuilder<Object> serializableVariable = newSerializableVariable(model);
      serializableVariable.withIdAndName(dataID, dataName);
      serializableVariable.ofClass(className, value);

      return serializableVariable.build();
   }

   /**
    * Created a primitive data.
    *
    * <p>
    * As <b>primitiveTypeID</b> might be set:
    * </p>
    * <p>
    * </p>
    * <li>ModelerConstants.STRING_PRIMITIVE_DATA_TYPE</li> <li>
    * ModelerConstants.DATE_PRIMITIVE_DATA_TYPE</li> <li>
    * ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE</li> <li>
    * ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE</li> <li>
    * ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE</li>
    * <ul>
    * </ul>
    *
    * @param model
    *           model to create the data in
    * @param dataID
    *           id of the data
    * @param dataName
    *           name of the data
    * @param primitiveTypeID
    *           id of the data
    *
    * @return primitive data created
    */
   @SuppressWarnings("deprecation")
   public DataType createPrimitiveData(ModelType model, String dataID, String dataName,
         String primitiveTypeID)
   {
      DataType data;
      Type type = null;

      if (primitiveTypeID.equals(ModelerConstants.STRING_PRIMITIVE_DATA_TYPE))
      {
         type = Type.String;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DATE_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Calendar;
      }
      else if (primitiveTypeID.equals(ModelerConstants.CALENDAR_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Calendar;
      }
      else if (primitiveTypeID.equals(ModelerConstants.TIMESTAMP_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Timestamp;
      }
      else if (primitiveTypeID.equals(ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Integer;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Double;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Money;
      }
      else if (primitiveTypeID.equals(ModelerConstants.ENUM_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Enumeration;
      }

      data = newPrimitiveVariable(model).withIdAndName(dataID, dataName)
            .ofType(type)
            .build();

      return data;
   }

   public DataType createData(ModelType model, DataTypeType type, String id,
         String name, String description)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      DataType data = factory.createDataType();
      data.setId(id);
      data.setName(name);
      data.setType(type);
      data.setDescription(ModelUtils.createDescription(description));
      data.setPredefined(true);
      model.getData().add(data);
      return data;
   }

   public void createAttribute(DataType data, String name, String type, String value)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      AttributeType attribute = factory.createAttributeType();
      attribute.setName(name);
      if (type != null)
      {
         attribute.setType(type);
      }
      attribute.setValue(value);
      data.getAttribute().add(attribute);
   }

   /**
    * Imports a data into an existing model. The model to import the data from is
    * qualified by a full qualified id of the data. If the data exists in a model which
    * differs from the model to import the data in, then a file connection is established
    * and a reference is created. If the data exists locally in the model this is not
    * necessary.
    *
    * <p>
    * The <i>dataFullID</i> id provided as <b>ModelID:DataID</b>.
    * </p>
    *
    * @param model
    *           model to import a data in
    * @param dataFullID
    *           full qualified id of the data to be imported
    *
    * @return local or referenced data
    */
   public DataType importData(ModelType model, String dataFullID) throws IllegalArgumentException
   {
      DataType data = null;
      String dataModelId = getModelId(dataFullID);
      ModelType dataModel = getModelManagementStrategy().getModels().get(dataModelId);

      data = XPDLFinderUtils.findData(dataModel, stripFullId(dataFullID));

      // TODO: check for local data ?

      if (data == null && dataFullID.endsWith(DmsConstants.DATA_ID_ATTACHMENTS))
      {
         data = createProcessAttachementData(model);
      }

      if (model != dataModel)
      {
         DataType consumerData = XPDLFinderUtils.findData(model, stripFullId(dataFullID));
         if (consumerData != null)
         {
            if (!dataModelId.equals(model.getId()))
            {
               if(isExternalReference(consumerData))
               {
                  URI proxyUri = ((InternalEObject) consumerData).eProxyURI();

                  if (proxyUri == null)
                  {
                     String uri = AttributeUtil.getAttributeValue(consumerData, IConnectionManager.URI_ATTRIBUTE_NAME);
                     try
                     {
                        proxyUri = URI.createURI(uri);
                     }
                     catch (Exception ex)
                     {
                     }
                  }

                  ModelType referencedModel = ModelUtils.getModelByProxyURI(model, proxyUri);
                  if(referencedModel != null)
                  {
                     if (ModelUtils.hasCircularDependency(model.getId(), referencedModel))
                     {
                        throw new ModelerException(ModelerErrorClass.CIRCULAR_DEPENDENCY);
                     }
                     if(!referencedModel.getId().equals(dataModelId))
                     {
                        throw new IllegalArgumentException("Data with same Id already exists.");
                     }
                  }
               }
            }

            return consumerData;
         }
      }

      if (!dataModelId.equals(model.getId()))
      {
         if (ModelUtils.hasCircularDependency(model.getId(), dataModel))
         {
            throw new ModelerException(ModelerErrorClass.CIRCULAR_DEPENDENCY);
         }
         data = EObjectProxyHandler.importElement(model, data);
      }
      return data;
   }

   public IModelParticipant importParticipant(ModelType model, String participantFullId)
   {
      IModelParticipant participant = null;
      String participantModelId = getModelId(participantFullId);
      String participantId = stripFullId(participantFullId);
      ModelType participantModel = getModelManagementStrategy().getModels().get(participantModelId);

      participant = XPDLFinderUtils.findParticipant(participantModel, participantId);

      if (!participantModelId.equals(model.getId()))
      {
         IModelParticipant localParticipant = null;
         try
         {
            localParticipant = XPDLFinderUtils.findParticipant(model, participantId);
         }
         catch (ObjectNotFoundException e)
         {
            // Ignore
         }
         if (localParticipant == null)
         {
            if (ModelUtils.hasCircularDependency(model.getId(), participantModel))
            {
               throw new ModelerException(ModelerErrorClass.CIRCULAR_DEPENDENCY);
            }
            localParticipant = EObjectProxyHandler.importElement(model, participant);
         }
         else
         {
            // TODO: check...
         }
         participant = localParticipant;
      }
      return participant;
   }

   /**
    * Creates lane in a diagram
    *
    * <p>
    * The <i>participantFullID</i> id is provided as <b>ModelID:ParticipantID</b>.
    * </p>
    *
    * <p>
    * As <b>orientation</b> might be set:
    * </p>
    * <p>
    * </p>
    * <li>ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL</li> <li>
    * ModelerConstants.DIAGRAM_FLOW_ORIENTATION_VERTICAL</li>
    * <ul>
    * </ul>
    *
    * @param model
    *           model to create the lane in
    * @param processDefinition
    *           process definition to create the lane in
    * @param participantFullID
    *           full qualified id of the participant assigned to the lane
    * @param laneID
    *           id of the lane
    * @param laneName
    *           name of the lane
    * @param orientationID
    *           orientation of the lane
    * @param xProperty
    *           x position
    * @param yProperty
    *           y position
    * @param widthProperty
    *           width
    * @param heightProperty
    *           height
    * @param parentSymbol
    * @return lane created
    */
   public LaneSymbol createLane(ModelType model, ProcessDefinitionType processDefinition,
         String participantFullID, String laneID, String laneName, String orientationID,
         int xProperty, int yProperty, int widthProperty, int heightProperty,
         PoolSymbol parentSymbol)
   {
      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();
      laneSymbol.setName(laneName);

      if (StringUtils.isEmpty(laneID))
      {
         laneID = NameIdUtilsExtension.createIdFromName(null, laneSymbol);
      }
      laneSymbol.setId(laneID);

      parentSymbol.getLanes().add(laneSymbol);
      laneSymbol.setParentPool(parentSymbol);

      processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getChildLanes()
            .add(laneSymbol);

      laneSymbol.setXPos(xProperty);
      laneSymbol.setYPos(yProperty);
      laneSymbol.setWidth(widthProperty);
      laneSymbol.setHeight(heightProperty);

      if (orientationID != null
            && orientationID.equals(ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL))
      {
         laneSymbol.setOrientation(OrientationType.HORIZONTAL_LITERAL);
      }
      else
      {
         laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);
      }

      if (participantFullID != null)
      {
         LaneParticipantUtil.setParticipant(laneSymbol, importParticipant(model, participantFullID));
      }
      return laneSymbol;
   }

   /**
    * Create an activity diagram symbol
    *
    * @param model
    *           model to create the symbol in
    * @param activity
    *           activity for which the symbol is created
    * @param processDefinition
    *           process definition to create the symbol in
    * @param parentLaneID
    *           id of the lane to create the symbol in
    * @param xProperty
    *           x position
    * @param yProperty
    *           y position
    * @param widthProperty
    *           width
    * @param heightProperty
    *           height
    * @return activity symbol
    */
   public ActivitySymbolType createActivitySymbol(ModelType model, ActivityType activity,
         ProcessDefinitionType processDefinition, String parentLaneID, int xProperty,
         int yProperty, int widthProperty, int heightProperty)
   {
      ActivitySymbolType activitySymbol = AbstractElementBuilder.F_CWM.createActivitySymbolType();
      LaneSymbol parentLaneSymbol = XPDLFinderUtils.findLaneInProcess(processDefinition, parentLaneID);

      activitySymbol.setXPos(xProperty - parentLaneSymbol.getXPos());
      activitySymbol.setYPos(yProperty - parentLaneSymbol.getYPos());
      activitySymbol.setWidth(widthProperty);
      activitySymbol.setHeight(heightProperty);
      activitySymbol.setActivity(activity);
      activity.getActivitySymbols().add(activitySymbol);

      processDefinition.getDiagram().get(0).getActivitySymbol().add(activitySymbol);
      parentLaneSymbol.getActivitySymbol().add(activitySymbol);
      return activitySymbol;
   }

   /**
    * Create Annotation Symbol
    *
    * @param model
    * @param processDefinition
    * @param parentLaneID
    * @param xProperty
    * @param yProperty
    * @param widthProperty
    * @param heightProperty
    * @param content
    * @return
    */
   public AnnotationSymbolType createAnnotationSymbol(ModelType model,
         ProcessDefinitionType processDefinition, String parentLaneID, int xProperty,
         int yProperty, int widthProperty, int heightProperty, String content)
   {
      AnnotationSymbolType annotationSymbol = AbstractElementBuilder.F_CWM.createAnnotationSymbolType();
      LaneSymbol parentLaneSymbol = XPDLFinderUtils.findLaneInProcess(processDefinition, parentLaneID);

      annotationSymbol.setXPos(xProperty - parentLaneSymbol.getXPos());
      annotationSymbol.setYPos(yProperty - parentLaneSymbol.getYPos());
      annotationSymbol.setWidth(widthProperty);
      annotationSymbol.setHeight(heightProperty);

      if (StringUtils.isNotEmpty(content))
      {
         TextType text = AbstractElementBuilder.F_CWM.createTextType();
         text.getMixed().add(FeatureMapUtil.createRawTextEntry(content));
         annotationSymbol.setText(text);
      }

      processDefinition.getDiagram().get(0).getAnnotationSymbol().add(annotationSymbol);
      parentLaneSymbol.getAnnotationSymbol().add(annotationSymbol);

      return annotationSymbol;
   }



   /**
    * Create a data diagram symbol
    *
    * @param model
    *           model to create the symbol in
    * @param data
    *           data for which the symbol is created
    * @param processDefinition
    *           process definition to create the symbol in
    * @param parentLaneID
    *           id of the lane to create the symbol in
    * @param xProperty
    *           x position
    * @param yProperty
    *           y position
    * @param widthProperty
    *           width
    * @param heightProperty
    *           height
    * @return data symbol
    */
   public DataSymbolType createDataSymbol(ModelType model, DataType data,
         ProcessDefinitionType processDefinition, String parentLaneID, int xProperty,
         int yProperty, int widthProperty, int heightProperty)
   {
      DataSymbolType dataSymbol = AbstractElementBuilder.F_CWM.createDataSymbolType();
      LaneSymbol parentLaneSymbol = XPDLFinderUtils.findLaneInProcess(processDefinition, parentLaneID);

      dataSymbol.setData(data);

      processDefinition.getDiagram().get(0).getDataSymbol().add(dataSymbol);
      dataSymbol.setXPos(xProperty - parentLaneSymbol.getXPos());
      dataSymbol.setYPos(yProperty - parentLaneSymbol.getYPos());
      dataSymbol.setWidth(widthProperty);
      dataSymbol.setHeight(heightProperty);

      parentLaneSymbol.getDataSymbol().add(dataSymbol);
      return dataSymbol;
   }

   /**
    * Creates a role.
    *
    * @param model
    *           The model to create the role in.
    * @param roleID
    *           id of the role
    * @param roleName
    *           name of the role
    * @return role created
    */
   public RoleType createRole(ModelType model, String roleID, String roleName)
   {
      RoleType role = newRole(model).withIdAndName(roleID, roleName).build();
      return role;
   }

   /**
    * @param model
    * @param conditionalPerformerID
    * @param conditionalPerformerName
    * @return
    */
   public ConditionalPerformerType createConditionalPerformer(ModelType model,
         String conditionalPerformerID, String conditionalPerformerName)
   {
      ConditionalPerformerType conditionalPerformer = newConditionalPerformer(model).withIdAndName(
            conditionalPerformerID, conditionalPerformerName)
            .build();
      return conditionalPerformer;
   }

   /**
    * Creates an organization.
    *
    * @param model
    *           The model to create the organization in.
    * @param orgID
    *           id of the organization
    * @param orgName
    *           name of the organization
    * @return organization created
    */
   public OrganizationType createOrganization(ModelType model, String orgID,
         String orgName)
   {
      OrganizationType org = newOrganization(model).withIdAndName(orgID, orgName).build();
      return org;
   }

   /**
    * Creates an application.
    *
    * <p>
    * As <b>applicationTypeID</b> might be set:
    * </p>
    * <p>
    * </p>
    * <li>ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID</li> <li>
    * ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID</li> <li>
    * ModelerConstants.CAMEL_APPLICATION_TYPE</li> <li>
    * ModelerConstants.MAIL_APPLICATION_TYPE_ID</li> <li>
    * ModelerConstants.INTERACTIVE_APPLICATION_TYPE_KEY</li> <li>
    * ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY</li>
    * <ul>
    * </ul>
    *
    * @param model
    *           model to create the application in.
    * @param applicationID
    *           id of the application
    * @param applicationName
    *           name of the application
    * @param applicationTypeID
    *           id of the application type to be set
    * @return application created
    */
   public ApplicationType createApplication(ModelType model, String applicationID,
         String applicationName, String applicationTypeID)
   {
      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID))
      {
         return newMessageTransformationApplication(model).withIdAndName(applicationID,
               applicationName).build();

      }
      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY))
      {
         return newExternalWebApplication(model).withIdAndName(applicationID,
               applicationName).build();
      }

      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID))
      {
         return newWebserviceApplication(model).withIdAndName(applicationID,
               applicationName).build();
      }

      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.DECORATOR_APP_CONTEXT_TYPE_KEY))
      {
         return newDecoratorApplication(model).withIdAndName(applicationID,
               applicationName).build();
      }

      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.CAMEL_APPLICATION_TYPE_ID))
      {
         return newCamelApplication(model).withIdAndName(applicationID, applicationName)
               .build();
      }
      // This application is "hidden" and implicitly created when for a task activity the
      // task type is changed to "Rules"
      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.DROOLS_APPLICATION_TYPE_ID))
      {
         IdFactory idFactory = new IdFactory(applicationID, applicationName);
         idFactory.computeNames(model.getApplication(), true);
         applicationID = idFactory.getId();
         applicationName = idFactory.getName();
         return newDroolsApplication(model).withIdAndName(applicationID, applicationName)
               .build();
      }
      return null;
   }

   /**
    * Creates an activity.
    *
    * <p>
    * As <b>activityTypeID</b> might be set:
    * </p>
    * <p>
    * </p>
    * <li>ModelerConstants.MANUAL_ACTIVITY</li> <li>ModelerConstants.APPLICATION_ACTIVITY</li>
    * <li>ModelerConstants.SUBPROCESS_ACTIVITY</li>
    * <ul>
    * </ul>
    *
    * <p>
    * The <b>xxxFullID</b> are provided like that:
    * </p>
    * <p>
    * </p>
    * <li>The <i>participantFullID</i> id provided as <b>ModelID:ParticipantID</b>.</p></li>
    * <li>The <i>applicationFullID</i> id provided as <b>ModelID:ApplicationID</b>.</p></li>
    * <li>The <i>subProcessFullID</i> id provided as <b>ModelID:ProcessID</b>.</p></li>
    * <ul>
    * </ul>
    *
    * @param model
    *           model to create the activity in.
    * @param processDefinition
    *           process definition to create the activity in.
    * @param activityType
    *           id of the activity type
    * @param activityID
    *           id of the activity
    * @param activityName
    *           name of the activity
    * @param participantFullID
    *           full qualified id of the participant to be assigned <i>(MANUAL_ACTIVITY
    *           only)</i>
    * @param applicationFullID
    *           full qualified id of the application to be assigned
    *           <i>(APPLICATION_ACTIVITY only)</i>
    * @param subProcessFullID
    *           full qualified id of the process to be assigned <i>(SUBPROCESS_ACTIVITY
    *           only)</i>
    * @return activity created
    */
   public ActivityType createActivity(ModelType model,
         ProcessDefinitionType processDefinition, String activityType, String taskType,
         String activityID, String activityName, String participantFullID,
         String applicationFullID, String subProcessFullID)
   {
      ActivityType activity = null;

      if (ModelerConstants.TASK_ACTIVITY.equals(activityType))
      {
         setAttribute(activity, ModelerConstants.TASK_TYPE, taskType);

         if (ModelerConstants.NONE_TASK_KEY.equals(taskType))
         {
            activity = newRouteActivity(processDefinition).withIdAndName(activityID,
                  activityName).build();

            // TODO Redundant?

            activity.setImplementation(ActivityImplementationType.ROUTE_LITERAL);
         }
         else if (ModelerConstants.MANUAL_TASK_KEY.equals(taskType))
         {
            if (participantFullID != null)
            {
               activity = newManualActivity(processDefinition).withIdAndName(activityID,
                     activityName)
                     .havingDefaultPerformer(stripFullId(participantFullID))
                     .build();
            }
            else
            {
               activity = newManualActivity(processDefinition).withIdAndName(activityID,
                     activityName).build();
            }

            // TODO Redundant?

            activity.setImplementation(ActivityImplementationType.MANUAL_LITERAL);
         }
         else if (ModelerConstants.USER_TASK_KEY.equals(taskType)
               || ModelerConstants.SERVICE_TASK_KEY.equals(taskType)
               || ModelerConstants.SCRIPT_TASK_KEY.equals(taskType)
               || ModelerConstants.SEND_TASK_KEY.equals(taskType)
               || ModelerConstants.RECEIVE_TASK_KEY.equals(taskType)
               || ModelerConstants.RULE_TASK_KEY.equals(taskType))
         {
            String stripFullId_ = getModelId(applicationFullID);

            if (StringUtils.isEmpty(stripFullId_))
            {
               stripFullId_ = model.getId();
            }

            ApplicationType application = getApplication(stripFullId_,
                  stripFullId(applicationFullID));
            ModelType applicationModel = ModelUtils.findContainingModel(application);
            BpmApplicationActivityBuilder applicationActivity = newApplicationActivity(processDefinition);
            applicationActivity.setApplicationModel(applicationModel);

            BpmApplicationActivityBuilder activityBuilder = applicationActivity.withIdAndName(
                  activityID, activityName);
            if (null != application)
            {
               activityBuilder.invokingApplication(application);
            }
            activity = activityBuilder.build();

            if (ModelerConstants.USER_TASK_KEY.equals(taskType)
                  && participantFullID != null)
            {
               activity.setPerformer(importParticipant(model, participantFullID));
            }
         }
      }
      else if (ModelerConstants.SUBPROCESS_ACTIVITY.equals(activityType))
      {
         String stripFullId = getModelId(subProcessFullID);
         if (StringUtils.isEmpty(stripFullId))
         {
            stripFullId = model.getId();
         }

         ProcessDefinitionType subProcessDefinition = XPDLFinderUtils.findProcessDefinition(
               getModelManagementStrategy().getModels().get(stripFullId),
               stripFullId(subProcessFullID));
         ModelType subProcessModel = ModelUtils.findContainingModel(subProcessDefinition);

         BpmSubProcessActivityBuilder subProcessActivity = newSubProcessActivity(processDefinition);
         subProcessActivity.setSubProcessModel(subProcessModel);

         activity = subProcessActivity.withIdAndName(activityID, activityName)
               .invokingProcess(
                     XPDLFinderUtils.findProcessDefinition(
                           getModelManagementStrategy().getModels().get(
                                 subProcessModel.getId()), stripFullId(subProcessFullID)))
               .build();
      }

      return activity;
   }

   /**
    * Creates a process.
    *
    * @deprecated
    *
    * @param model
    *           model to create the process in
    * @param processID
    *           id of the process
    * @param processName
    *           name of the process
    * @return process created
    */
   public ProcessDefinitionType createProcess(ModelType model, String processName,
         String processID)
   {
      ProcessDefinitionType processDefinition = newProcessDefinition(model).withIdAndName(
            processID, processName)
            .build();

      // Create diagram bits too

      DiagramType diagram = AbstractElementBuilder.F_CWM.createDiagramType();
      diagram.setMode(DiagramModeType.MODE_450_LITERAL);
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL);
      diagram.setName("Diagram 1");

      PoolSymbol poolSymbol = AbstractElementBuilder.F_CWM.createPoolSymbol();

      diagram.getPoolSymbols().add(poolSymbol);

      poolSymbol.setXPos(0);
      poolSymbol.setYPos(0);
      poolSymbol.setWidth(500);
      poolSymbol.setHeight(600);
      poolSymbol.setName("DEFAULT_POOL");
      poolSymbol.setId("DEFAULT_POOL");
      poolSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();

      poolSymbol.getChildLanes().add(laneSymbol);
      laneSymbol.setParentPool(poolSymbol);

      laneSymbol.setId(ModelerConstants.DEF_LANE_ID);
      laneSymbol.setName(ModelerConstants.DEF_LANE_NAME);
      laneSymbol.setXPos(10);
      laneSymbol.setYPos(10);
      laneSymbol.setWidth(480);
      laneSymbol.setHeight(580);
      laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      processDefinition.getDiagram().add(diagram);

      return processDefinition;
   }

   /**
    *
    * @param model
    * @param id
    * @param name
    * @param defaultLaneName
    * @param defaultPoolName
    */
   public ProcessDefinitionType createProcess(ModelType model, String id, String name,
         String defaultLaneName, String defaultPoolName)
   {
      ProcessDefinitionType processDefinition = newProcessDefinition(model).withIdAndName(
            id, name)
            .build();

      // Create diagram bits too

      DiagramType diagram = AbstractElementBuilder.F_CWM.createDiagramType();

      diagram.setMode(DiagramModeType.MODE_450_LITERAL);
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL);
      diagram.setName("Diagram 1");

      PoolSymbol poolSymbol = AbstractElementBuilder.F_CWM.createPoolSymbol();

      diagram.getPoolSymbols().add(poolSymbol);
      poolSymbol.setXPos(0);
      poolSymbol.setYPos(0);
      poolSymbol.setWidth(ModelerConstants.DEFAULT_SWIMLANE_WIDTH + 34);
      poolSymbol.setHeight(670);
      poolSymbol.setName(defaultPoolName);
      poolSymbol.setId("_default_pool__1");
      poolSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();

      poolSymbol.getChildLanes().add(laneSymbol);
      laneSymbol.setParentPool(poolSymbol);

      laneSymbol.setId(ModelerConstants.DEF_LANE_ID);
      laneSymbol.setName(defaultLaneName);

      // Setting the x,y for default swimlane
      // TODO - Move this code to javascript
      laneSymbol.setXPos(12);
      laneSymbol.setYPos(32);
      laneSymbol.setWidth(ModelerConstants.DEFAULT_SWIMLANE_WIDTH);
      laneSymbol.setHeight(poolSymbol.getHeight() - 70);
      laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);

      processDefinition.getDiagram().add(diagram);

      return processDefinition;
   }

   /**
    *
    * @return
    */
   public DataPathType createDataPath()
   {
      return AbstractElementBuilder.F_CWM.createDataPathType();
   }

   /**
    *
    * @param modelId
    * @return
    */
   public ModelType findModel(String modelId)
   {
      return getModelManagementStrategy().getModels().get(modelId);
   }


   /**
    *
    * @param processFullID
    * @return processDefinition
    */
   public ProcessDefinitionType findProcessDefinition(String processFullID)
   {
      String modelID = this.getModelId(processFullID);
      String processID = stripFullId(processFullID);
      ModelType model = findModel(modelID);
      for (ProcessDefinitionType processDefinition : model.getProcessDefinition())
      {
         if (processDefinition.getId().equals(processID))
         {
            return processDefinition;
         }
      }

      throw new ObjectNotFoundException("Process Definition " + processFullID
            + " does not exist.");
   }

   /**
    *
    * @param modelId
    * @param id
    * @return
    */
   public ProcessDefinitionType getProcessDefinition(String modelId, String id)
   {
      return XPDLFinderUtils.findProcessDefinition(findModel(modelId), id);
   }






   /**
    *
    * @param fullTypeID
    * @return typeDeclaration
    */
   public TypeDeclarationType findTypeDeclaration(String fullTypeID)
   {
      if (fullTypeID.startsWith("typeDeclaration:{"))
      {
         fullTypeID = fullTypeID.split(":")[1];
         fullTypeID = fullTypeID.replaceAll("\\{", "");
         fullTypeID = fullTypeID.replaceAll("\\}", ":");
      }
      String[] ids = fullTypeID.split(":");
      if (ids.length > 1)
      {
         return XPDLFinderUtils.findTypeDeclaration(findModel(ids[0]), ids[1]);
      }
      return null;
   }

   /**
    *
    * @param model
    * @param fullParticipantID
    * @return participant
    */
   public IModelParticipant findParticipant(String fullParticipantID)
   {
      String modelID = this.getModelId(fullParticipantID);
      String participantID = stripFullId(fullParticipantID);
      ModelType model = findModel(modelID);
      for (RoleType role : model.getRole())
      {
         if (role.getId().equals(participantID))
         {
            return role;
         }
      }

      for (OrganizationType organization : model.getOrganization())
      {
         if (organization.getId().equals(participantID))
         {
            return organization;
         }
      }

      for (ConditionalPerformerType conditionalPerformer : model.getConditionalPerformer())
      {
         if (conditionalPerformer.getId().equals(participantID))
         {
            return conditionalPerformer;
         }
      }

      throw new ObjectNotFoundException("Participant " + fullParticipantID
            + " does not exist.");
   }

   /**
    *
    * @param modelId
    * @param appId
    * @return
    */
   public ApplicationType getApplication(String modelId, String appId)
   {
      ModelType model = getModelManagementStrategy().getModels().get(modelId);
      List<ApplicationType> apps = model.getApplication();
      for (ApplicationType a : apps)
      {
         if (a.getId().equals(appId))
         {
            return a;
         }
      }

      return null;
   }

   /**
    *
    * @param model
    * @param modelElement
    * @return
    */
   public String createFullId(ModelType model, EObject modelElement)
   {
      // TODO Change to {modelId}elementId
      if (null != model && null != modelElement)
      {
         if (modelElement instanceof IIdentifiableModelElement)
         {
            return model.getId() + ":"
                  + ((IIdentifiableModelElement) modelElement).getId();
         }
         if (modelElement instanceof TypeDeclarationType)
         {
            return model.getId() + ":" + ((TypeDeclarationType) modelElement).getId();
         }
      }
      return null;
   }

   /**
    * TODO Auxiliary method while cross-model references are not supported
    *
    * @param fullId
    * @return
    */
   public String stripFullId(String fullId)
   {
      String[] ids = fullId.split(":");

      return ids[ids.length - 1];
   }

   /**
    * Retrieves the model ID of a full ID (e.g. ModelA for ModelA:CreateCustomer).
    *
    * @param fullId
    * @return
    */
   public String getModelId(String fullId)
   {
      String[] ids = fullId.split(":");

      return ids[0];
   }

   private IDataInitializer getInitializer(String dataTypeTypeID)
   {
      if (dataTypeTypeID.equals(PredefinedConstants.PRIMITIVE_DATA))
      {
         return new PrimitiveDataInitializer();
      }
      if (dataTypeTypeID.equals(PredefinedConstants.STRUCTURED_DATA))
      {
         return new DataStructInitializer();
      }
      if (dataTypeTypeID.equals(PredefinedConstants.SERIALIZABLE_DATA))
      {
         return new SerializableDataInitializer();
      }
      if (dataTypeTypeID.equals(PredefinedConstants.DOCUMENT_DATA))
      {
         return new DmsDocumentInitializer();
      }
      return null;
   }

   private TypeType getPrimitiveType(String primitiveTypeID)
   {
      TypeType type = null;
      if (primitiveTypeID.equals(ModelerConstants.STRING_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.STRING;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DATE_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.DATETIME;
      }
      else if (primitiveTypeID.equals("int"))
      {
         type = TypeType.INTEGER;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.FLOAT;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.FLOAT;
      }
      else if (primitiveTypeID.equals(ModelerConstants.BOOLEAN_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.BOOLEAN;
      }
      else if (primitiveTypeID.equals(ModelerConstants.CALENDAR_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.DATETIME;
      }
      else if (primitiveTypeID.equals(ModelerConstants.LONG_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.INTEGER;
      }

      return type;
   }

   public ContextType getApplicationContext(ApplicationType application,
         String contextTypeKey)
   {
      for (Iterator<ContextType> i = application.getContext().iterator(); i.hasNext();)
      {
         ContextType contextType = i.next();
         if (contextType.getType().getId().equals(contextTypeKey))
         {
            return contextType;
         }
      }
      return null;
   }

   public ContextType createApplicationContext(ApplicationType application,
         String contextId)
   {
      ModelType model = ModelUtils.findContainingModel(application);
      ContextType context = AbstractElementBuilder.F_CWM.createContextType();
      ApplicationContextTypeType contextTypeType = null;

      contextTypeType = XPDLFinderUtils.findApplicationContextTypeType(model, contextId);

      if (contextTypeType == null)
      {
         contextTypeType = AbstractElementBuilder.F_CWM.createApplicationContextTypeType();

         if (ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("External Web Application");
            contextTypeType.setId(ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.APPLICATION_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Noninteractive Application Context");
            contextTypeType.setId(ModelerConstants.APPLICATION_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.ENGINE_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Engine Context");
            contextTypeType.setId(ModelerConstants.ENGINE_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.DEFAULT_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Default Context");
            contextTypeType.setId(ModelerConstants.DEFAULT_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            model.getApplicationContextType().add(contextTypeType);
         }
      }

      context.setType(contextTypeType);
      application.getContext().add(context);

      return context;
   }

   public Class<? > getClassForType(String type)
   {
      if (type.equalsIgnoreCase("long")) { //$NON-NLS-1$
         return Long.class;
      }
      if (type.equalsIgnoreCase("long")) { //$NON-NLS-1$
         return Short.class;
      }
      if (type.equalsIgnoreCase("float")) { //$NON-NLS-1$
         return Float.class;
      }
      if (type.equalsIgnoreCase("double")) { //$NON-NLS-1$
         return Double.class;
      }
      if (type.equalsIgnoreCase("byte")) { //$NON-NLS-1$
         return Byte.class;
      }
      if (type.equalsIgnoreCase("Calendar")) { //$NON-NLS-1$
         return Calendar.class;
      }
      if (type.equalsIgnoreCase("Timestamp")) { //$NON-NLS-1$
         return Calendar.class;
      }
      if (type.equalsIgnoreCase("int")) { //$NON-NLS-1$
         return Integer.class;
      }
      if (type.equalsIgnoreCase("java.lang.Integer")) { //$NON-NLS-1$
         return Integer.class;
      }
      if (type.equals("String")) { //$NON-NLS-1$
         return String.class;
      }
      return String.class;
   }

   /*private String parseName(String path)
   {
      if (path.startsWith("typeDeclaration:")) //$NON-NLS-1$
      {
         path = path.replaceAll("typeDeclaration:", ""); //$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("{", "");//$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("}", " / "); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return path;
   }*/

   public ProcessDefinitionType setSubProcess(ActivityType activity, String processFullId)
   {
      ProcessDefinitionType process = this.getProcessDefinition(
            getModelId(processFullId), stripFullId(processFullId));

      ModelType model = ModelUtils.findContainingModel(activity);
      ModelType processModel = ModelUtils.findContainingModel(process);

      if (model.equals(processModel))
      {
         IdRefHandler.cleanup(activity);
         activity.setImplementationProcess(process);
      }
      else
      {
         ExternalReferenceUtils.createExternalReferenceToProcess(activity, process, model, processModel);
      }
      return process;
   }

   public ApplicationType setApplication(ActivityType activity, String applicationFullId)
   {
      ApplicationType application = getApplication(getModelId(applicationFullId),
            stripFullId(applicationFullId));

      ModelType model = ModelUtils.findContainingModel(activity);
      ModelType applicationModel = ModelUtils.findContainingModel(application);

      if (model.equals(applicationModel))
      {
         IdRefHandler.cleanup(activity);
         activity.setApplication(application);
      }
      else
      {
         ExternalReferenceUtils.createExternalReferenceToApplication(activity, application, model, applicationModel);
      }
      return application;
   }

   public static void setAttribute(Object element, String name, String value)
   {
      if (element instanceof Extensible)
      {
         ExtendedAttributeUtil.setAttribute((Extensible) element, name, value);
      }
      if (element instanceof IExtensibleElement)
      {
         try {
         AttributeUtil.setAttribute((IExtensibleElement) element, name, value);
         }
         catch (Throwable t) {

         }
      }
   }

   public static void setTimestampAttribute(IExtensibleElement element, String name, String value)
   {
      AttributeUtil.setAttribute((IExtensibleElement) element, name, TIMESTAMP_TYPE, value);
   }

   public Date getTimestampAttribute(IExtensibleElement element, String name)
   {
      Date date = null;
      String attributeValue = AttributeUtil.getAttributeValue(element, name);
      if (attributeValue != null)
      {
         try
         {
            date = DateUtils.getNonInteractiveDateFormat().parse(attributeValue);
         }
         catch (ParseException e)
         {
         }
      }

      return date;
   }

   public static void setBooleanAttribute(Object element, String name, boolean value)
   {
      if (element instanceof Extensible)
      {
         ExtendedAttributeUtil.setBooleanAttribute((Extensible) element, name, value);
      }
      if (element instanceof IExtensibleElement)
      {
         AttributeUtil.setBooleanAttribute((IExtensibleElement) element, name, value);
      }
   }

   public boolean getBooleanAttribute(Object element, String name)
   {
      if (element instanceof Extensible)
      {
         return ExtendedAttributeUtil.getBooleanValue((Extensible) element, name);
      }
      if (element instanceof IExtensibleElement)
      {
         return AttributeUtil.getBooleanValue((IExtensibleElement) element, name);
      }
      return false;
   }

   @SuppressWarnings("rawtypes")
   public List getAttributes(Object element)
   {
      if (element instanceof Extensible)
      {
         if (((Extensible) element).getExtendedAttributes() != null)
         {
            return ((Extensible) element).getExtendedAttributes().getExtendedAttribute();
         }
      }
      if (element instanceof IExtensibleElement)
      {
         return ((IExtensibleElement) element).getAttribute();
      }
      return new ArrayList();
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   public List getAttributes(Object element, boolean excludeBoolean, boolean booleanOnly)
   {
      if (excludeBoolean == booleanOnly && booleanOnly == true)
      {
         return new ArrayList();
      }
      List attributes = getAttributes(element);
      if (excludeBoolean == booleanOnly && booleanOnly == false)
      {
         return attributes;
      }
      List filteredAttributes = new ArrayList();
      for (Iterator<Object> i = attributes.iterator(); i.hasNext();)
      {
         Object attribute = i.next();
         if ((booleanOnly && isBooleanAttribute(attribute))
               || (excludeBoolean && !isBooleanAttribute(attribute)))
         {
            filteredAttributes.add(attribute);
         }
      }
      return filteredAttributes;
   }

   /**
    *
    * @param element
    * @param attributeName
    * @return
    */
   public Object getAttribute(Object element, String attributeName)
   {
      for (Object attribute : getAttributes(element))
      {
         if (getAttributeName(attribute).equals(attributeName))
         {
            return attribute;
         }
      }

      return null;
   }

   public String getAttributeName(Object attribute)
   {
      if (attribute instanceof ExtendedAttributeType)
      {
         return ((ExtendedAttributeType) attribute).getName();
      }
      if (attribute instanceof AttributeType)
      {
         return ((AttributeType) attribute).getName();
      }
      return null;
   }

   public String getAttributeValue(Object attribute)
   {
      if (attribute instanceof ExtendedAttributeType)
      {
         return ((ExtendedAttributeType) attribute).getValue();
      }
      if (attribute instanceof AttributeType)
      {
         return ((AttributeType) attribute).getValue();
      }
      return null;
   }

   public boolean isBooleanAttribute(Object attribute)
   {
      if (attribute instanceof ExtendedAttributeType)
      {
         ExtendedAttributeType attributeType = (ExtendedAttributeType) attribute;
         if (((ExtendedAttributeType) attribute).getValue() != null)
         {
            if (attributeType.getValue().equalsIgnoreCase("true")
                  || attributeType.getValue().equalsIgnoreCase("false"))
            {
               return true;
            }
         }
      }
      if (attribute instanceof AttributeType)
      {
         AttributeType attributeType = (AttributeType) attribute;
         if (attributeType.getType() != null
               && attributeType.getType().equalsIgnoreCase("Boolean"))
         {
            return true;
         }
      }
      return false;
   }

   public boolean isExternalReference(EObject modelElement)
   {
      if (modelElement != null)
      {
         if (modelElement.eIsProxy())
         {
            return true;
         }
         if (modelElement instanceof DataType)
         {
            DataType dataType = (DataType) modelElement;
            if (dataType.eIsProxy())
            {
               return true;
            }

            if ((dataType.getType() != null)
                  && (dataType.getType().getId().equalsIgnoreCase(PredefinedConstants.DOCUMENT_DATA)))
            {
               return false;
            }
            if ((dataType.getType() != null)
                  && (dataType.getType().getId().equalsIgnoreCase(PredefinedConstants.STRUCTURED_DATA)))
            {
               return false;
            }
         }
         if (modelElement instanceof IExtensibleElement)
         {
            if (AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
                  IConnectionManager.URI_ATTRIBUTE_NAME) != null)
            {
               String uri = AttributeUtil.getAttributeValue(
                     (IExtensibleElement) modelElement,
                     IConnectionManager.URI_ATTRIBUTE_NAME);
               ModelType model = ModelUtils.findContainingModel(modelElement);
               if (model == null)
               {
                  return false;
               }
               Connection connection = (Connection) model.getConnectionManager()
                     .findConnection(uri);
               if (connection != null)
               {
                  String importString = connection.getAttribute("importByReference"); //$NON-NLS-1$
                  if (importString == null
                        || (importString != null && importString
                              .equalsIgnoreCase("false"))) //$NON-NLS-1$
                  {
                     return false;
                  }
               }
               return true;
            }
         }
         if (modelElement instanceof Extensible)
         {
            if (ExtendedAttributeUtil.getAttributeValue((Extensible) modelElement,
                  IConnectionManager.URI_ATTRIBUTE_NAME) != null)
            {
               String uri = ExtendedAttributeUtil.getAttributeValue(
                     (Extensible) modelElement, IConnectionManager.URI_ATTRIBUTE_NAME);
               ModelType model = ModelUtils.findContainingModel(modelElement);
               if (model == null)
               {
                  return false;
               }
               Connection connection = (Connection) model.getConnectionManager()
                     .findConnection(uri);
               if (connection != null)
               {
                  String importString = connection.getAttribute("importByReference"); //$NON-NLS-1$
                  if (importString != null && importString.equalsIgnoreCase("false")) //$NON-NLS-1$
                  {
                     return false;
                  }
               }
               return true;
            }
         }
      }
      return false;
   }

   /**
    *
    * @param typeDeclaration
    * @return
    */
   public boolean isEnumerationJavaBound(TypeDeclarationType typeDeclaration)
   {
      try
      {
         if (null != typeDeclaration && null != typeDeclaration.getExtendedAttributes())
         {
            for (ExtendedAttributeType extendedAttrType : typeDeclaration.getExtendedAttributes()
                  .getExtendedAttribute())
            {
               if (extendedAttrType.getName().equals(PredefinedConstants.CLASS_NAME_ATT))
               {
                  return true;
               }
            }
         }
      }
      catch (Exception e)
      {
         return false;
      }
      return false;
   }

   public void updateTeamLead(OrganizationType organization, String participantFullID)
   {
      // Set team leader to null if participantFullID is set to "TO_BE_DEFINED"
      if (ModelerConstants.TO_BE_DEFINED.equals(participantFullID))
      {
         setTeamLeader(organization, null);
         return;
      }

      ModelType model = ModelUtils.findContainingModel(organization);
      if (participantFullID != null)
      {
         setTeamLeader(organization, (RoleType) importParticipant(model, participantFullID));
      }
   }

   public void setModified(ModelType modelType, String modified)
   {
      AttributeUtil.setAttribute(modelType, ModelerConstants.ATTRIBUTE_MODIFIED, modified);
   }

   public void setModified(ModelType modelType, Date modified)
   {
      AttributeUtil.setAttribute(modelType, ModelerConstants.ATTRIBUTE_MODIFIED,
            new Date().toString());
   }

   public String getModified(ModelType modelType)
   {
      AttributeType attribute = AttributeUtil.getAttribute(modelType,
            ModelerConstants.ATTRIBUTE_MODIFIED);
      if (attribute != null)
      {
         return convertDate(attribute.getValue());
      }
      return "unknown";
   }

   public ModelType createModel(String modelID, String modelName)
   {
      Map<String, ModelType> models = this.modelManagementStrategy.getModels(false);
      List<EObject> ids = new ArrayList<EObject>(models.values());

      ModelType model = newBpmModel().withIdAndName(modelID, modelName).build();
      model.setConnectionManager(new WebModelerConnectionManager(model,
            this.modelManagementStrategy));

      if (StringUtils.isEmpty(modelID))
      {
         modelID = NameIdUtilsExtension.createIdFromName(modelName, ids);
      }
      model.setId(modelID);

      return model;
   }

   public DataType createProcessAttachementData(ModelType model)
   {
      DataTypeType dataTypeType = (DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);
      DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
      data.setId(DmsConstants.DATA_ID_ATTACHMENTS);
      ResourceBundle rb = ResourceBundle.getBundle("bpm-modeler-messages"); //$NON-NLS-1$
      String name = rb.getString("modeler.processDefinition.propertyPages.processAttachments.heading"); //$NON-NLS-1$
      data.setName(name);
      data.setType(dataTypeType);
      model.getData().add(data);

      AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT,
            List.class.getName());
      AttributeUtil.setAttribute(data, CarnotConstants.ENGINE_SCOPE
            + "data:bidirectional", //$NON-NLS-1$
            Boolean.TYPE.getName(), Boolean.TRUE.toString());

      return data;
   }

   public ParameterMappingType createParameterMapping(TriggerType trigger,
         String parameter, String dataFullID, String dataPath)
   {
      ParameterMappingType mappingType = CarnotWorkflowModelFactory.eINSTANCE.createParameterMappingType();
      mappingType.setParameter(parameter);
      DataType dt = importData(ModelUtils.findContainingModel(trigger), dataFullID);
      mappingType.setData(dt);
      if (dataPath != null)
      {
         mappingType.setDataPath(dataPath);
      }
      trigger.getParameterMapping().add(mappingType);
      return mappingType;
   }

   public String convertDate(String date)
   {
      ResourceBundle rb = ResourceBundle.getBundle("portal-common-messages"); //$NON-NLS-1$
      String format = rb.getString("portalFramework.formats.defaultDateTimeFormat"); //$NON-NLS-1$

      if (format == null)
      {
         format = "MM/dd/yy hh:mm a"; //$NON-NLS-1$
      }

      String pattern = "E MMM dd HH:mm:ss zzz yyyy"; //$NON-NLS-1$
      SimpleDateFormat instance = new SimpleDateFormat(pattern, Locale.ROOT);

      try
      {
         Date parse = instance.parse(date);
         SimpleDateFormat sdf = new SimpleDateFormat();
         sdf.applyPattern(format);
         date = sdf.format(parse);
      }
      catch (ParseException e)
      {
      }

      return date;
   }

   /**
    *
    * @param processDefinition
    * @param sourceActivity
    * @param targetActivity
    * @param controlFlowJson
    * @param transitionOid
    * @return
    */
   public TransitionType createTransition(ProcessDefinitionType processDefinition,
         ActivityType sourceActivity, ActivityType targetActivity, String id,
         String name, String description, boolean otherwise, String condition)
   {
      TransitionType transition = AbstractElementBuilder.F_CWM.createTransitionType();

      processDefinition.getTransition().add(transition);

      transition.setFrom(sourceActivity);
      transition.setTo(targetActivity);

      if (isEmpty(id))
      {
         id = modelManagementStrategy.uuidMapper().map(transition);
      }

      transition.setId(id);
      transition.setName(name);

      if (otherwise)
      {
         transition.setCondition(ModelerConstants.OTHERWISE_KEY);
      }
      else
      {
         transition.setCondition(ModelerConstants.CONDITION_KEY);

         if (StringUtils.isEmpty(condition))
         {
            condition = "true";
         }
         XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
         ModelUtils.setCDataString(expression.getMixed(), condition, true);
         transition.setExpression(expression);
      }

      // TODO Implement

      setDescription(transition, description);

      return transition;
   }

   /**
    *
    * @param processDefinition
    * @param sourceActivitySymbol
    * @param targetActivitySymbol
    * @param transition
    * @param connectionOid
    * @param fromAnchorOrientation
    * @param toAnchorOrientation
    * @return
    */
   public TransitionConnectionType createTransitionSymbol(
         ProcessDefinitionType processDefinition, IFlowObjectSymbol sourceActivitySymbol,
         IFlowObjectSymbol targetActivitySymbol, TransitionType transition,
         String fromAnchorOrientation, String toAnchorOrientation)
   {
      TransitionConnectionType transitionConnection = AbstractElementBuilder.F_CWM.createTransitionConnectionType();

      if (null != transition)
      {
         transition.getTransitionConnections().add(transitionConnection);
         transitionConnection.setTransition(transition);
      }

      transitionConnection.setSourceNode(sourceActivitySymbol);
      transitionConnection.setTargetNode(targetActivitySymbol);
      transitionConnection.setSourceAnchor(fromAnchorOrientation);
      transitionConnection.setTargetAnchor(toAnchorOrientation);

      // TODO Obtain pool from call

      processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getTransitionConnection()
            .add(transitionConnection);

      return transitionConnection;
   }

   /**
    *
    * @param sourceActivitySymbol
    * @param targetActivitySymbol
    * @throws JSONException
    */
   public TransitionConnectionType createControlFlowConnection(ProcessDefinitionType processDefinition,
         ActivitySymbolType sourceActivitySymbol,
         ActivitySymbolType targetActivitySymbol, String id, String name,
         String description, boolean otherwise, String condition, String fromAnchor,
         String toAnchor)
   {
      TransitionType transition = createTransition(processDefinition,
            sourceActivitySymbol.getActivity(), targetActivitySymbol.getActivity(), id,
            name, description, otherwise, condition);

      return createTransitionSymbol(processDefinition, sourceActivitySymbol,
            targetActivitySymbol, transition, fromAnchor, toAnchor);
   }

   // TODO Homogenize the next two methods
   /**
    *
    * @param processDefinition
    * @param activitySymbol
    * @param dataSymbol
    * @param direction
    * @param fromAnchor
    * @param toAnchor
    */
   public DataMappingConnectionType createDataFlowConnection(
         ProcessDefinitionType processDefinition, ActivitySymbolType activitySymbol,
         DataSymbolType dataSymbol, DirectionType direction, String fromAnchor,
         String toAnchor, String context, String activityAccessPointId)
   {
      DataType data = dataSymbol.getData();
      ActivityType activity = activitySymbol.getActivity();

      DataMappingType dataMapping = AbstractElementBuilder.F_CWM.createDataMappingType();
      DataMappingConnectionType dataMappingConnection = AbstractElementBuilder.F_CWM.createDataMappingConnectionType();

      String id = data.getId();

      dataMapping.setId(id);
      dataMapping.setName(id);
      dataMapping.setDirection(direction);
      dataMapping.setData(data);
      modelManagementStrategy.uuidMapper().map(dataMapping);

      if (context.equalsIgnoreCase(PredefinedConstants.DEFAULT_CONTEXT))
      {
         context = getDefaultContext(activity, direction);
      }

      dataMapping.setContext(context);

      if (activityAccessPointId != null)
      {
         dataMapping.setApplicationAccessPoint(activityAccessPointId);
      }

      activity.getDataMapping().add(dataMapping);

      // TODO Obtain pool from call

      processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getDataMappingConnection()
            .add(dataMappingConnection);

      dataMappingConnection.setActivitySymbol(activitySymbol);
      dataMappingConnection.setDataSymbol(dataSymbol);
      activitySymbol.getDataMappings().add(dataMappingConnection);
      dataSymbol.getDataMappings().add(dataMappingConnection);
      dataMappingConnection.setSourceAnchor(fromAnchor);
      dataMappingConnection.setTargetAnchor(toAnchor);

      if (null == dataMapping.getApplicationAccessPoint()
            && activity.getImplementation().getLiteral().equals("Application"))
      {
         ApplicationType application = activity.getApplication();
         if (null != application.getType()
               && application.getType().getId().equals("webservice"))
         {
            String dataId = null;
            if (application != null && application.getAccessPoint() != null)
            {
               for (AccessPointType accPoints : activity.getApplication()
                     .getAccessPoint())
               {
                  if (accPoints.getDirection().getValue() == DirectionType.OUT)
                  {
                     if (accPoints.getType().equals(data.getType()))
                     {
                        dataId = accPoints.getId();
                        break;
                     }
                  }
               }
            }
            if (null != dataId)
            {
               dataMapping.setContext(PredefinedConstants.APPLICATION_CONTEXT);
               dataMapping.setApplicationAccessPoint(dataId);
            }
         }
      }
      return dataMappingConnection;
   }


   public String getDefaultContext(ActivityType activity, DirectionType direction)
   {
      List<ApplicationContextTypeType> contextTypes = ActivityUtil.getContextTypes(
            activity, direction);
      if (!contextTypes.isEmpty())
      {
         return contextTypes.get(0).getId();
      }
      return PredefinedConstants.DEFAULT_CONTEXT;
   }

   /**
    * Create a Data Mapping Connection with zero, one or two Data Mappings.
    *
    * @param processDefinition
    * @param activitySymbol
    * @param dataSymbol
    * @param inputContextId
    * @param inputAccessPointId
    * @param outputContextId
    * @param outputAccessPointId
    * @param fromAnchor
    * @param toAnchor
    * @return
    */
   public DataMappingConnectionType createDataFlowConnection(
         ProcessDefinitionType processDefinition, ActivitySymbolType activitySymbol,
         DataSymbolType dataSymbol, String inputContextId, String inputAccessPointId,
         String outputContextId, String outputAccessPointId, String fromAnchor,
         String toAnchor)
   {

      DataType data = dataSymbol.getData();
      ActivityType activity = activitySymbol.getActivity();

      DataMappingType dataMapping;

      if (inputAccessPointId != null)
      {
         dataMapping = AbstractElementBuilder.F_CWM.createDataMappingType();

         dataMapping.setId(data.getId());
         dataMapping.setName(data.getName());
         dataMapping.setDirection(DirectionType.IN_LITERAL);
         dataMapping.setData(data);
         dataMapping.setContext(inputContextId);
         dataMapping.setApplicationAccessPoint(inputAccessPointId);

         activity.getDataMapping().add(dataMapping);
      }

      if (outputAccessPointId != null)
      {
         dataMapping = AbstractElementBuilder.F_CWM.createDataMappingType();

         dataMapping.setId(data.getId());
         dataMapping.setName(data.getName());
         dataMapping.setDirection(DirectionType.OUT_LITERAL);
         dataMapping.setData(data);
         dataMapping.setContext(outputContextId);
         dataMapping.setApplicationAccessPoint(outputAccessPointId);

         activity.getDataMapping().add(dataMapping);
      }

      DataMappingConnectionType dataMappingConnection = AbstractElementBuilder.F_CWM.createDataMappingConnectionType();

      // TODO Obtain pool from call

      processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getDataMappingConnection()
            .add(dataMappingConnection);

      dataMappingConnection.setActivitySymbol(activitySymbol);
      dataMappingConnection.setDataSymbol(dataSymbol);
      activitySymbol.getDataMappings().add(dataMappingConnection);
      dataSymbol.getDataMappings().add(dataMappingConnection);
      dataMappingConnection.setSourceAnchor(fromAnchor);
      dataMappingConnection.setTargetAnchor(toAnchor);

      return dataMappingConnection;
   }

   /**
    *
    * @param application
    * @param context
    * @param direction
    * @return
    */
   public AccessPointType findFirstApplicationAccessPointForType(
         ApplicationType application, DirectionType direction)
   {
      for (AccessPointType accessPoint : application.getAccessPoint())
      {
         if (accessPoint.getDirection().equals(direction))
         {
            return accessPoint;
         }
      }

      return null;
   }

   public boolean isReadOnly(EObject element)
   {
      if (element != null && element instanceof ModelType)
      {
         AttributeType attribute = AttributeUtil.getAttribute((ModelType) element,
               "stardust:security:hash");
         if ((attribute != null) && (attribute.getValue() != null)
               && (attribute.getValue().length() > 0))
         {
            return true;
         }

      }
      return false;
   }


   /**
    * @param element
    * @param description
    */
   public static void setDescription(IIdentifiableModelElement element, String description)
   {
      if (StringUtils.isNotEmpty(description))
      {
         DescriptionType dt = AbstractElementBuilder.F_CWM.createDescriptionType();
         dt.getMixed().add(FeatureMapUtil.createRawTextEntry(description));
         element.setDescription(dt);
      }
   }
}
