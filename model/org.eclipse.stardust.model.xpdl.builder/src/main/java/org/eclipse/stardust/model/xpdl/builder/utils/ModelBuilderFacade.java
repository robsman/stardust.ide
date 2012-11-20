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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newCamelApplication;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newConditionalPerformer;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newDocumentVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newExternalWebApplication;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newMessageTransformationApplication;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOrganization;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveAccessPoint;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newStructVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newStructuredAccessPoint;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newWebserviceApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

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
import org.eclipse.stardust.model.xpdl.builder.initializer.DataStructInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.DmsDocumentInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.PrimitiveDataInitializer;
import org.eclipse.stardust.model.xpdl.builder.initializer.SerializableDataInitializer;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmDocumentVariableBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmStructVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceEObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;

public class ModelBuilderFacade
{
   private ModelManagementStrategy modelManagementStrategy;

   public ModelBuilderFacade(ModelManagementStrategy modelManagementStrategy)
   {
      this.modelManagementStrategy = modelManagementStrategy;
   }

   public ModelBuilderFacade()
   {
      // TODO Auto-generated constructor stub
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
      organization.setTeamLead(role);
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
      EList<OrganizationType> orgs = model.getOrganization();
      if (null != orgs)
      {
         for (OrganizationType org : orgs)
         {
            for (ParticipantType pt : org.getParticipant())
            {
               if (participant.equals(pt.getParticipant()))
               {
                  belongsTo.add(org);
               }
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

      structuredDataType.setId(typeID);
      structuredDataType.setName(typeName);

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

      return structuredDataType;
   }

   public FormalParameterType createPrimitiveParameter(
         ProcessDefinitionType processInterface, DataType data, String id, String name,
         String primitiveTypeID, ModeType mode)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();
      parameterType.setId(id);
      parameterType.setName(name);
      parameterType.setMode(mode);

      FormalParametersType parametersType = processInterface.getFormalParameters();

      if (parametersType == null)
      {
         parametersType = xpdlFactory.createFormalParametersType();
      }

      parametersType.addFormalParameter(parameterType);
      processInterface.setFormalParameters(parametersType);

      FormalParameterMappingsType parameterMappingsType = processInterface.getFormalParameterMappings();

      if (parameterMappingsType == null)
      {
         parameterMappingsType = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      }

      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = XpdlFactory.eINSTANCE.createDataTypeType();
      BasicTypeType basicType = xpdlFactory.createBasicTypeType();
      basicType.setType(getPrimitiveType(primitiveTypeID));
      dataTypeType.setBasicType(basicType);
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
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();

      parameterType.setId(id);
      parameterType.setName(name);
      parameterType.setMode(mode);

      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      String typeId = PredefinedConstants.STRUCTURED_DATA;

      parameterType.setDataType(dataTypeType);
      dataTypeType.setCarnotType(typeId);

      DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
      // declaredType.setId(AttributeUtil.getAttributeValue(data,
      // StructuredDataConstants.TYPE_DECLARATION_ATT));

      declaredType.setId(stripFullId(structTypeFullID));

      dataTypeType.setDeclaredType(declaredType);

      FormalParametersType parametersType = processInterface.getFormalParameters();

      if (parametersType == null)
      {
         parametersType = xpdlFactory.createFormalParametersType();
      }
      parametersType.addFormalParameter(parameterType);
      processInterface.setFormalParameters(parametersType);

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
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();

      parameterType.setId(id);
      parameterType.setName(name);
      parameterType.setMode(mode);

      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataTypeType = xpdlFactory.createDataTypeType();
      String typeId = ModelerConstants.DOCUMENT_DATA_TYPE_KEY;

      parameterType.setDataType(dataTypeType);
      dataTypeType.setCarnotType(typeId);

      DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
      // declaredType.setId(AttributeUtil.getAttributeValue(data,
      // StructuredDataConstants.TYPE_DECLARATION_ATT));

      declaredType.setId(stripFullId(structTypeFullID));

      dataTypeType.setDeclaredType(declaredType);

      FormalParametersType parametersType = processInterface.getFormalParameters();

      if (parametersType == null)
      {
         parametersType = xpdlFactory.createFormalParametersType();
      }
      parametersType.addFormalParameter(parameterType);
      processInterface.setFormalParameters(parametersType);

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

   public AccessPoint createJavaAccessPoint(String id, String name,
         String clazz, Direction direction, boolean browsable, Object characteristics)
   {
      if(clazz == null)
      {
         clazz = "java.util.Map";
      }

      return JavaDataTypeUtils.createIntrinsicAccessPoint(id, name, clazz, direction, browsable,
            characteristics);
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
      return newStructuredAccessPoint(application).withIdAndName(id, name)
            .withType(structTypeFullID)
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

      data = structVariable.withIdAndName(dataID, dataName)
            .ofType(this.stripFullId(typeFullID))
            .build();

      return data;
   }

   public void convertDataType(DataType data, String targetTypeID)
   {
      if (data.getType().getId().equals(targetTypeID))
      {
         return;
      }
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
            AttributeUtil.setAttribute(data,
                  StructuredDataConstants.TYPE_DECLARATION_ATT, declarationID);
         }
         else
         {
            String fileConnectionId = WebModelerConnectionManager.createFileConnection(
                  model, typeDeclarationModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(
                  MergeUtils.createQualifiedUri(uri, typeDeclaration, true), data,
                  typeDeclaration.getId(), typeDeclaration.getName(),
                  typeDeclaration.getDescription(), bundleId, null);

            AttributeUtil.setAttribute(
                  data,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
            AttributeUtil.setBooleanAttribute(data,
                  "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
            AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME,
                  descriptor.getURI().toString());
            ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
            if (typeDeclarationModel != null)
            {
               reference.setLocation(ImportUtils.getPackageRef(descriptor, model,
                     typeDeclarationModel).getId());
            }
            reference.setXref(declarationID);
            data.setExternalReference(reference);
         }
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
         String declarationID = stripFullId(typeFullID);
         TypeDeclarationType typeDeclaration = this.findTypeDeclaration(typeFullID);

         if (sourceModelID.equals(model.getId()))
         {
            AttributeUtil.setAttribute(data, "carnot:engine:dms:resourceMetadataSchema",
                  declarationID);
         }
         else
         {
            String fileConnectionId = WebModelerConnectionManager.createFileConnection(
                  model, typeDeclarationModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(
                  MergeUtils.createQualifiedUri(uri, typeDeclaration, true), data,
                  typeDeclaration.getId(), typeDeclaration.getName(),
                  typeDeclaration.getDescription(), bundleId, null);

            AttributeUtil.setAttribute(
                  data,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
            AttributeUtil.setBooleanAttribute(data,
                  "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
            AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME,
                  descriptor.getURI().toString());
            ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
            if (typeDeclarationModel != null)
            {
               reference.setLocation(ImportUtils.getPackageRef(descriptor, model,
                     typeDeclarationModel).getId());
            }
            reference.setXref(declarationID);
            data.setExternalReference(reference);
         }
      }
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
   public DataType createPrimitiveData(ModelType model, String dataID, String dataName,
         String primitiveTypeID)
   {
      DataType data;
      long maxOID = XpdlModelUtils.getMaxUsedOid(model);
      Type type = null;

      if (primitiveTypeID.equals(ModelerConstants.STRING_PRIMITIVE_DATA_TYPE))
      {
         type = Type.String;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DATE_PRIMITIVE_DATA_TYPE))
      {
         type = Type.Calendar;
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

      data = newPrimitiveVariable(model).withIdAndName(dataID, dataName)
            .ofType(type)
            .build();
      data.setElementOid(++maxOID);

      return data;
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
   public DataType importData(ModelType model, String dataFullID)
   {
      DataType data = null;
      String dataModelId = getModelId(dataFullID);
      ModelType dataModel = getModelManagementStrategy().getModels().get(dataModelId);
      try
      {
         data = findData(dataModel, stripFullId(dataFullID));
      }
      catch (ObjectNotFoundException ex)
      {

      }

      if (data == null && dataFullID.endsWith(DmsConstants.DATA_ID_ATTACHMENTS))
      {
         createProcessAttachementData(model);
      }

      if (!dataModelId.equals(model.getId()))
      {
         String fileConnectionId = WebModelerConnectionManager.createFileConnection(
               model, dataModel);

         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

         ModelType loadModel = getModelManagementStrategy().loadModel(
               dataModelId + ".xpdl");
         DataType dataCopy = findData(loadModel, stripFullId(dataFullID));
         if (dataCopy == null)
         {
            ElementCopier copier = new ElementCopier(loadModel, null);
            dataCopy = (DataType) copier.copy(data);
         }

         ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
               uri, dataCopy, bundleId, null, true);
         PepperIconFactory iconFactory = new PepperIconFactory();
         descriptor.importElements(iconFactory, model, true);
         data = findData(model, stripFullId(dataFullID));
      }
      return data;
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
    * @return lane created
    */
   public LaneSymbol createLane(ModelType model, ProcessDefinitionType processDefinition,
         String participantFullID, String laneID, String laneName, String orientationID,
         int xProperty, int yProperty, int widthProperty, int heightProperty)
   {
      long maxOid = XpdlModelUtils.getMaxUsedOid(model);

      LaneSymbol laneSymbol = AbstractElementBuilder.F_CWM.createLaneSymbol();

      processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getChildLanes()
            .add(laneSymbol);

      laneSymbol.setElementOid(++maxOid);

      laneSymbol.setId(laneID);
      laneSymbol.setName(laneName);
      laneSymbol.setXPos(xProperty);
      laneSymbol.setYPos(yProperty);
      laneSymbol.setWidth(widthProperty);
      laneSymbol.setHeight(heightProperty);

      if (orientationID.equals(ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL))
      {
         laneSymbol.setOrientation(OrientationType.HORIZONTAL_LITERAL);
      }
      else
      {
         laneSymbol.setOrientation(OrientationType.VERTICAL_LITERAL);
      }

      if (participantFullID != null)
      {

         String participantModelID = getModelId(participantFullID);
         if (StringUtils.isEmpty(participantModelID))
         {
            participantModelID = model.getId();
         }
         ModelType participantModel = model;
         if ( !participantModelID.equals(model.getId()))
         {
            participantModel = getModelManagementStrategy().getModels().get(
                  participantModelID);
         }

         IModelParticipant modelParticipant = findParticipant(
               getModelManagementStrategy().getModels().get(participantModelID),
               stripFullId(participantFullID));

         if ( !participantModelID.equals(model.getId()))
         {
            String fileConnectionId = WebModelerConnectionManager.createFileConnection(
                  model, participantModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ModelType loadModel = getModelManagementStrategy().loadModel(
                  participantModelID + ".xpdl");
            IModelParticipant participantCopy = findParticipant(loadModel,
                  stripFullId(participantFullID));
            if (participantCopy == null)
            {
               ElementCopier copier = new ElementCopier(loadModel, null);
               participantCopy = (IModelParticipant) copier.copy(modelParticipant);
            }

            ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
                  uri, participantCopy, bundleId, null, true);
            PepperIconFactory iconFactory = new PepperIconFactory();
            descriptor.importElements(iconFactory, model, true);
            modelParticipant = findParticipant(model, stripFullId(participantFullID));
         }

         laneSymbol.setParticipant(modelParticipant);
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
      long maxOID = XpdlModelUtils.getMaxUsedOid(model);

      ActivitySymbolType activitySymbol = AbstractElementBuilder.F_CWM.createActivitySymbolType();
      LaneSymbol parentLaneSymbol = findLaneInProcess(processDefinition, parentLaneID);

      activitySymbol.setElementOid(++maxOID);
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
      long maxOID = XpdlModelUtils.getMaxUsedOid(model);

      AnnotationSymbolType annotationSymbol = AbstractElementBuilder.F_CWM.createAnnotationSymbolType();
      LaneSymbol parentLaneSymbol = findLaneInProcess(processDefinition, parentLaneID);

      annotationSymbol.setElementOid(++maxOID);
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
    * @param parentLaneSymbol
    * @param annotationOId
    * @return
    */
   public AnnotationSymbolType findAnnotationSymbol(LaneSymbol parentLaneSymbol,
         Long annotationOId)
   {
      Iterator<AnnotationSymbolType> annotationIterator = parentLaneSymbol.getAnnotationSymbol()
            .iterator();

      while (annotationIterator.hasNext())
      {
         AnnotationSymbolType annSymbol = annotationIterator.next();
         if (annotationOId.equals(annSymbol.getElementOid()))
         {
            return annSymbol;
         }
      }
      return null;
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
      long maxOID = XpdlModelUtils.getMaxUsedOid(model);

      DataSymbolType dataSymbol = AbstractElementBuilder.F_CWM.createDataSymbolType();
      LaneSymbol parentLaneSymbol = findLaneInProcess(processDefinition, parentLaneID);

      dataSymbol.setElementOid(++maxOID);

      dataSymbol.setData(data);

      processDefinition.getDiagram().get(0).getDataSymbol().add(dataSymbol);
      data.getDataSymbols().add(dataSymbol);
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
      if (applicationTypeID.equalsIgnoreCase(ModelerConstants.CAMEL_APPLICATION_TYPE_ID))
      {
         return newCamelApplication(model).withIdAndName(applicationID, applicationName)
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
    * @param activityTypeID
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
         ProcessDefinitionType processDefinition, String activityTypeID,
         String activityID, String activityName, String participantFullID,
         String applicationFullID, String subProcessFullID)
   {
      long maxOid = XpdlModelUtils.getMaxUsedOid(model) + 1;

      ActivityType activity = null;

      if (ModelerConstants.MANUAL_ACTIVITY.equals(activityTypeID))
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
      }
      else if (ModelerConstants.APPLICATION_ACTIVITY.equals(activityTypeID))

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

         activity = applicationActivity.withIdAndName(activityID, activityName)
               .invokingApplication(
                     getApplication(applicationModel.getId(),
                           stripFullId(applicationFullID)))
               .build();
         // }
      }
      else if (ModelerConstants.SUBPROCESS_ACTIVITY.equals(activityTypeID))
      {

         String stripFullId = getModelId(subProcessFullID);
         if (StringUtils.isEmpty(stripFullId))
         {
            stripFullId = model.getId();
         }

         ProcessDefinitionType subProcessDefinition = findProcessDefinition(
               getModelManagementStrategy().getModels().get(stripFullId),
               stripFullId(subProcessFullID));
         ModelType subProcessModel = ModelUtils.findContainingModel(subProcessDefinition);

         BpmSubProcessActivityBuilder subProcessActivity = newSubProcessActivity(processDefinition);
         subProcessActivity.setSubProcessModel(subProcessModel);

         activity = subProcessActivity.withIdAndName(model.getId(), activityName)
               .invokingProcess(
                     findProcessDefinition(
                           getModelManagementStrategy().getModels().get(
                                 subProcessModel.getId()), stripFullId(subProcessFullID)))
               .build();
      }
      else
      {
         activity = newRouteActivity(processDefinition).withIdAndName(activityID,
               activityName).build();
      }
      activity.setElementOid(maxOid + 1);
      return activity;
   }

   /**
    * Creates a process.
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
      long maxOid = XpdlModelUtils.getMaxUsedOid(model);
      diagram.setElementOid(++maxOid);
      diagram.setName("Diagram 1");

      PoolSymbol poolSymbol = AbstractElementBuilder.F_CWM.createPoolSymbol();

      diagram.getPoolSymbols().add(poolSymbol);

      poolSymbol.setElementOid(++maxOid);
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

      laneSymbol.setElementOid(++maxOid);
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
    * @param model
    * @param id
    * @return
    */
   public ProcessDefinitionType findProcessDefinition(ModelType model, String id)
   {
      for (ProcessDefinitionType processDefinition : model.getProcessDefinition())
      {
         if (processDefinition.getId().equals(id))
         {
            return processDefinition;
         }
      }

      throw new ObjectNotFoundException("Process Definition " + id + " does not exist.");
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
      return findProcessDefinition(findModel(modelId), id);
   }

   /**
    *
    * @param model
    * @param id
    * @return application
    */
   public ApplicationType findApplication(ModelType model, String id)
   {
      for (ApplicationType application : model.getApplication())
      {
         if (application.getId().equals(id))
         {
            return application;
         }
      }

      throw new ObjectNotFoundException("Application " + id + " does not exist.");
   }

   /**
    *
    * @param model
    * @param fullApplicationID
    * @return application
    */
   public ApplicationType findApplication(String fullApplicationID)
   {
      String modelID = this.getModelId(fullApplicationID);
      String applicationID = stripFullId(fullApplicationID);
      ModelType model = findModel(modelID);
      for (ApplicationType application : model.getApplication())
      {
         if (application.getId().equals(applicationID))
         {
            return application;
         }
      }

      throw new ObjectNotFoundException("Application " + fullApplicationID
            + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public ApplicationTypeType findApplicationTypeType(ModelType model, String id)
   {
      for (ApplicationTypeType applicationType : model.getApplicationType())
      {
         if (applicationType.getId().equals(id))
         {
            return applicationType;
         }
      }

      throw new ObjectNotFoundException("Application type " + id + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public ApplicationContextTypeType findApplicationContextTypeType(ModelType model,
         String id)
   {
      for (ApplicationContextTypeType applicationContextType : model.getApplicationContextType())
      {
         if (applicationContextType.getId().equals(id))
         {
            return applicationContextType;
         }
      }

      throw new ObjectNotFoundException("Application Context type " + id
            + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public TypeDeclarationType findTypeDeclaration(ModelType model, String id)
   {
      for (TypeDeclarationType typeDeclaration : model.getTypeDeclarations()
            .getTypeDeclaration())
      {
         if (typeDeclaration.getId().equals(id))
         {
            return typeDeclaration;
         }
      }

      throw new ObjectNotFoundException("Type declaration " + id + " does not exist.");
   }

   /**
    *
    * @param fullTypeID
    * @return typeDeclaration
    */
   public TypeDeclarationType findTypeDeclaration(String fullTypeID)
   {
      String modelID = this.getModelId(fullTypeID);
      String typeID = stripFullId(fullTypeID);
      ModelType model = findModel(modelID);
      for (TypeDeclarationType typeDeclaration : model.getTypeDeclarations()
            .getTypeDeclaration())
      {
         if (typeDeclaration.getId().equals(typeID))
         {
            return typeDeclaration;
         }
      }

      throw new ObjectNotFoundException("Type declaration " + fullTypeID
            + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public DataTypeType findDataType(ModelType model, String id)
   {
      for (DataTypeType dataType : model.getDataType())
      {
         if (dataType.getId().equals(id))
         {
            return dataType;
         }
      }

      throw new ObjectNotFoundException("Data type " + id + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public DataType findData(ModelType model, String id)
   {
      for (DataType data : model.getData())
      {
         if (data.getId().equals(id))
         {
            return data;
         }
      }

      throw new ObjectNotFoundException("Data " + id + " does not exist.");
   }

   /**
    *
    * @param model
    * @param dataFullID
    * @return data
    */
   public DataType findData(String dataFullID)
   {
      String modelID = this.getModelId(dataFullID);
      String dataID = stripFullId(dataFullID);
      ModelType model = findModel(modelID);
      for (DataType data : model.getData())
      {
         if (data.getId().equals(dataID))
         {
            return data;
         }
      }

      throw new ObjectNotFoundException("Data " + dataFullID + " does not exist.");
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public IModelParticipant findParticipant(ModelType model, String id)
   {
      for (RoleType role : model.getRole())
      {
         if (role.getId().equals(id))
         {
            return role;
         }
      }

      for (OrganizationType organization : model.getOrganization())
      {
         if (organization.getId().equals(id))
         {
            return organization;
         }
      }

      for (ConditionalPerformerType conditionalPerformer : model.getConditionalPerformer())
      {
         if (conditionalPerformer.getId().equals(id))
         {
            return conditionalPerformer;
         }
      }
      throw new ObjectNotFoundException("Participant " + id + " does not exist.");
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

      throw new ObjectNotFoundException("Participant " + fullParticipantID
            + " does not exist.");
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public DataSymbolType findDataSymbol(DiagramType diagram, long oid)
   {
      for (DataSymbolType dataSymbol : diagram.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

            if (dataSymbol != null)
            {
               return dataSymbol;
            }
         }
      }

      throw new ObjectNotFoundException("Data Symbol " + oid + " does not exist.");
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public DataSymbolType findDataSymbolRecursively(LaneSymbol laneSymbol, long oid)
   {
      for (DataSymbolType dataSymbol : laneSymbol.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

         if (dataSymbol != null)
         {
            return dataSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param processDefinition
    * @param id
    * @return
    */
   public ActivityType findActivity(ProcessDefinitionType processDefinition, String id)
   {
      for (ActivityType activity : processDefinition.getActivity())
      {
         if (activity.getId().equals(id))
         {
            return activity;
         }
      }

      throw new ObjectNotFoundException("Activity " + id + " does not exist.");
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public ActivitySymbolType findActivitySymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingActivitySymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findActivitySymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public ActivitySymbolType findActivitySymbol(LaneSymbol laneSymbol, long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return activitySymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
                  laneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   public LaneSymbol findLaneContainingActivitySymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public AnnotationSymbolType findAnnotationSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingAnnotationSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findAnnotationSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   private LaneSymbol findLaneContainingAnnotationSymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingAnnotationSymbolRecursively(
                  laneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    * @param laneSymbol
    * @param oid
    * @return
    */
   private LaneSymbol findLaneContainingAnnotationSymbolRecursively(
         LaneSymbol laneSymbol, long oid)
   {
      for (AnnotationSymbolType annotationSymbol : laneSymbol.getAnnotationSymbol())
      {
         if (annotationSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingAnnotationSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public LaneSymbol findLaneInProcess(ProcessDefinitionType processDefinition, String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol foundLaneSymbol = findLaneRecursively(laneSymbol, id);

            if (foundLaneSymbol != null)
            {
               return foundLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public StartEventSymbol findStartEventSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingStartEventSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findStartEventSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public StartEventSymbol findStartEventSymbol(LaneSymbol laneSymbol, long oid)
   {
      for (StartEventSymbol startEventSymbol : laneSymbol.getStartEventSymbols())
      {
         if (startEventSymbol.getElementOid() == oid)
         {
            return startEventSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingStartEventSymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingStartEventSymbolRecursively(
                  childLaneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingStartEventSymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (StartEventSymbol startEventSymbol : laneSymbol.getStartEventSymbols())
      {
         if (startEventSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingStartEventSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public EndEventSymbol findEndEventSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingEndEventSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findEndEventSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public EndEventSymbol findEndEventSymbol(LaneSymbol laneSymbol, long oid)
   {
      for (EndEventSymbol endEventSymbol : laneSymbol.getEndEventSymbols())
      {
         if (endEventSymbol.getElementOid() == oid)
         {
            return endEventSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingEndEventSymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingEndEventSymbolRecursively(
                  childLaneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneContainingEndEventSymbolRecursively(LaneSymbol laneSymbol,
         long oid)
   {
      for (EndEventSymbol endEventSymbol : laneSymbol.getEndEventSymbols())
      {
         if (endEventSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingEndEventSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param id
    * @return
    */
   public LaneSymbol findLaneRecursively(LaneSymbol laneSymbol, String id)
   {
      if (laneSymbol.getId().equals(id))
      {
         return laneSymbol;
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol foundLaneSymbol = findLaneRecursively(childLaneSymbol, id);

         if (foundLaneSymbol != null)
         {
            return foundLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbol(
         ProcessDefinitionType processDefinition, ActivitySymbolType activitySymbol)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol foundLaneSymbol = findLaneContainingActivitySymbolRecursively(
                  laneSymbol, activitySymbol);

            if (foundLaneSymbol != null)
            {
               return foundLaneSymbol;
            }
         }
      }

      throw new ObjectNotFoundException(
            "Lane symbol containing Activity Symbol for Activity "
                  + activitySymbol.getActivity().getId() + " cannot be found.");
   }

   /**
    *
    * @param laneSymbol
    * @param id
    * @return
    */
   public LaneSymbol findLaneContainingActivitySymbolRecursively(LaneSymbol laneSymbol,
         ActivitySymbolType activitySymbol)
   {
      if (laneSymbol.getActivitySymbol().contains(activitySymbol))
      {
         return laneSymbol;
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol foundLaneSymbol = findLaneContainingActivitySymbolRecursively(
               childLaneSymbol, activitySymbol);

         if (foundLaneSymbol != null)
         {
            return foundLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param processDefinition
    * @param oid
    * @return
    */
   public TransitionConnectionType findTransitionConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      for (TransitionConnectionType transitionConnection : processDefinition.getDiagram()
            .get(0)
            .getTransitionConnection())
      {
         if (transitionConnection.getElementOid() == oid)
         {
            return transitionConnection;
         }
      }

      // Lookup transition in first/default pool

      // TODO Support multiple pools

      for (TransitionConnectionType transitionConnection : processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getTransitionConnection())
      {
         if (transitionConnection.getElementOid() == oid)
         {
            return transitionConnection;
         }
      }

      throw new ObjectNotFoundException("Could not find Transition " + oid + ".");
   }

   /**
    *
    * @param processDefinition
    * @param oid
    * @return
    */
   public DataMappingConnectionType findDataMappingConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      for (DataMappingConnectionType dataMappingConnectionType : processDefinition.getDiagram()
            .get(0)
            .getDataMappingConnection())
      {
         if (dataMappingConnectionType.getElementOid() == oid)
         {
            return dataMappingConnectionType;
         }
      }

      // TODO Support multiple pools

      for (DataMappingConnectionType dataMappingConnectionType : processDefinition.getDiagram()
            .get(0)
            .getPoolSymbols()
            .get(0)
            .getDataMappingConnection())
      {
         if (dataMappingConnectionType.getElementOid() == oid)
         {
            return dataMappingConnectionType;
         }
      }

      throw new ObjectNotFoundException("Could not find " + oid + ".");
   }

   /**
    *
    * @param processDefinition
    * @param oid
    * @return
    */
   public PoolSymbol findPoolSymbolByElementOid(ProcessDefinitionType processDefinition,
         long oid)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         if (poolSymbol.getElementOid() == oid)
         {
            return poolSymbol;
         }
      }

      throw new ObjectNotFoundException("Could not find Pool Symbol with OID " + oid
            + ".");
   }

   /**
    *
    * @param poolSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneSymbolByElementOid(PoolSymbol poolSymbol, long oid)
   {
      for (LaneSymbol laneSymbol : poolSymbol.getLanes())
      {
         if (laneSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      throw new ObjectNotFoundException("Could not find Lane Symbol with OID " + oid
            + ".");
   }

   /**
    *
    * @param poolSymbol
    * @param oid
    * @return
    */
   public LaneSymbol findLaneSymbolById(ProcessDefinitionType processDefinition, String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getLanes())
         {

            // TODO Recursion

            if (laneSymbol.getId().equals(id))
            {
               return laneSymbol;
            }
         }
      }

      throw new ObjectNotFoundException("Could not find Lane Symbol with ID " + id + ".");
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
    * TODO Replace by Eclipse modeler logic
    *
    * @param name
    * @return
    */
   public String createIdFromName(String name)
   {
      StringBuilder idBuilder = new StringBuilder(name.length());
      boolean firstWord = true;
      boolean newWord = true;
      for (int i = 0; i < name.length(); ++i)
      {
         char nameChar = name.charAt(i);
         if (Character.isLetterOrDigit(nameChar))
         {
            if (newWord && !firstWord)
            {
               // append underscore for each first illegal character
               idBuilder.append('_');
            }
            idBuilder.append(Character.toUpperCase(nameChar));
            firstWord &= false;
            newWord = false;
         }
         else
         {
            newWord = true;
         }
      }

      return idBuilder.toString();
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
         type = TypeType.STRING_LITERAL;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DATE_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.DATETIME_LITERAL;
      }
      else if (primitiveTypeID.equals(ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.INTEGER_LITERAL;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.FLOAT_LITERAL;
      }
      else if (primitiveTypeID.equals(ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.FLOAT_LITERAL;
      }
      else if (primitiveTypeID.equals(ModelerConstants.BOOLEAN_PRIMITIVE_DATA_TYPE))
      {
         type = TypeType.BOOLEAN_LITERAL;
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

      try
      {
         contextTypeType = findApplicationContextTypeType(model, contextId);
      }
      catch (ObjectNotFoundException x)
      {
         // Excpected
      }

      if (contextTypeType == null)
      {
         contextTypeType = AbstractElementBuilder.F_CWM.createApplicationContextTypeType();

         if (ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("External Web Application");
            contextTypeType.setId(ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
            contextTypeType.setElementOid(++maxElementOid);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.APPLICATION_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Noninteractive Application Context");
            contextTypeType.setId(ModelerConstants.APPLICATION_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
            contextTypeType.setElementOid(++maxElementOid);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.ENGINE_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Engine Context");
            contextTypeType.setId(ModelerConstants.ENGINE_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
            contextTypeType.setElementOid(++maxElementOid);
            model.getApplicationContextType().add(contextTypeType);
         }
         else if (ModelerConstants.DEFAULT_CONTEXT_TYPE_KEY.equals(contextId))
         {
            contextTypeType.setName("Default Context");
            contextTypeType.setId(ModelerConstants.DEFAULT_CONTEXT_TYPE_KEY);
            contextTypeType.setIsPredefined(true);
            long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
            contextTypeType.setElementOid(++maxElementOid);
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

   private String parseName(String path)
   {
      if (path.startsWith("typeDeclaration:")) //$NON-NLS-1$
      {
         path = path.replaceAll("typeDeclaration:", ""); //$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("{", "");//$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("}", " / "); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return path;
   }

   public ApplicationType setApplication(ActivityType activity, String applicationFullId)
   {
      ApplicationType application = getApplication(getModelId(applicationFullId),
            stripFullId(applicationFullId));

      ModelType model = ModelUtils.findContainingModel(activity);
      ModelType applicationModel = ModelUtils.findContainingModel(application);

      if (model.equals(applicationModel))
      {
         activity.setApplication(application);
      }
      else
      {
         String fileConnectionId = WebModelerConnectionManager.createFileConnection(
               model, applicationModel);

         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

         ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
               uri, application, bundleId, null, true);

         AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME,
               descriptor.getURI().toString());

         IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
         idRef.setRef(application.getId());
         idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, model,
               applicationModel));
         activity.setExternalRef(idRef);
      }
      return application;
   }

   public void setAttribute(Object element, String name, String value)
   {
      if (element instanceof Extensible)
      {
         ExtendedAttributeUtil.setAttribute((Extensible) element, name, value);
      }
      if (element instanceof IExtensibleElement)
      {
         AttributeUtil.setAttribute((IExtensibleElement) element, name, value);
      }
   }

   public void setBooleanAttribute(Object element, String name, boolean value)
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
         if (attributeType.getValue().equalsIgnoreCase("true")
               || attributeType.getValue().equalsIgnoreCase("false"))
         {
            return true;
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
                  if (importString != null && importString.equalsIgnoreCase("false")) //$NON-NLS-1$
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

   public void updateTeamLead(OrganizationType organization, String participantFullID)
   {
      // Set team leader to null if participantFullID is set to "TO_BE_DEFINED"
      if (ModelerConstants.TO_BE_DEFINED.equals(participantFullID))
      {
         organization.setTeamLead(null);
         return;
      }

      ModelType model = ModelUtils.findContainingModel(organization);
      if (participantFullID != null)
      {

         String participantModelID = getModelId(participantFullID);
         if (StringUtils.isEmpty(participantModelID))
         {
            participantModelID = model.getId();
         }
         ModelType participantModel = model;
         if ( !participantModelID.equals(model.getId()))
         {
            participantModel = getModelManagementStrategy().getModels().get(
                  participantModelID);
         }

         IModelParticipant modelParticipant = findParticipant(
               getModelManagementStrategy().getModels().get(participantModelID),
               stripFullId(participantFullID));

         if ( !participantModelID.equals(model.getId()))
         {
            String fileConnectionId = WebModelerConnectionManager.createFileConnection(
                  model, participantModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ModelType loadModel = getModelManagementStrategy().loadModel(
                  participantModelID + ".xpdl");
            IModelParticipant participantCopy = findParticipant(loadModel,
                  stripFullId(participantFullID));
            if (participantCopy == null)
            {
               ElementCopier copier = new ElementCopier(loadModel, null);
               participantCopy = (IModelParticipant) copier.copy(modelParticipant);
            }

            ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(
                  uri, participantCopy, bundleId, null, true);
            PepperIconFactory iconFactory = new PepperIconFactory();
            descriptor.importElements(iconFactory, model, true);
            modelParticipant = findParticipant(model, stripFullId(participantFullID));
         }
         organization.setTeamLead((RoleType) modelParticipant);
      }
   }

   public void setModified(ModelType modelType, String modified)
   {
      AttributeUtil.setAttribute(modelType, ModelerConstants.ATTRIBUTE_MODIFIED, modified);
   }

   public void setModified(ModelType modelType, Date modified)
   {
      // TODO I18N
      final String MODIFIED_DATE_FORMAT = "dd MMM; yyyy KK:mm:ss a";
      DateFormat format = new SimpleDateFormat(MODIFIED_DATE_FORMAT);
      AttributeUtil.setAttribute(modelType, ModelerConstants.ATTRIBUTE_MODIFIED,
            format.format(modified));
   }

   public String getModified(ModelType modelType)
   {
      AttributeType attribute = AttributeUtil.getAttribute(modelType,
            ModelerConstants.ATTRIBUTE_MODIFIED);
      if (attribute != null)
      {
         return attribute.getValue();
      }
      return "unknown";
   }

   public ModelType createModel(String modelID, String modelName)
   {
      ModelType model = newBpmModel().withIdAndName(modelID, modelName).build();
      model.setConnectionManager(new WebModelerConnectionManager(model,
            this.modelManagementStrategy));
      return model;
   }

   public DataType createProcessAttachementData(ModelType model)
   {
      DataTypeType dataTypeType = (DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);
      DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
      data.setId(DmsConstants.DATA_ID_ATTACHMENTS);
      data.setName("Process Attachments"); //$NON-NLS-1$
      data.setType(dataTypeType);
      data.setElementOid(ModelUtils.getElementOid(data, model));

      model.getData().add(data);

      AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT,
            List.class.getName());
      AttributeUtil.setAttribute(data, CarnotConstants.ENGINE_SCOPE
            + "data:bidirectional", //$NON-NLS-1$
            Boolean.TYPE.getName(), Boolean.TRUE.toString());

      return data;
   }

   public ParameterMappingType createParameterMapping(TriggerType trigger,
         String dataFullID, String dataPath)
   {
      ModelType model = ModelUtils.findContainingModel(trigger);
      long maxOID = XpdlModelUtils.getMaxUsedOid(model);
      ParameterMappingType mappingType = CarnotWorkflowModelFactory.eINSTANCE
            .createParameterMappingType();
      mappingType.setData(findData(dataFullID));
      mappingType.setDataPath(dataPath);
      mappingType.setElementOid(++maxOID);
      trigger.getParameterMapping().add(mappingType);
      return mappingType;
   }
}