/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA LLC
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.lang.reflect.Proxy;
import java.util.*;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xsd.XSDImport;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.EObjectProxyHandler;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.IdRefHandler;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.merge.LinkAttribute;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceEObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class ExternalReferenceUtils
{
   public static void createExternalReferenceToApplication(ActivityType activity,
         ApplicationType application, ModelType consumerModel, ModelType providerModel)
   {
      String fileConnectionId = WebModelerConnectionManager.createFileConnection(
            consumerModel, providerModel);

      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

      ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(uri,
            application, bundleId, null, true);

      AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME,
            descriptor.getURI().toString());

      IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
      idRef.setRef(application.getId());
      idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, consumerModel,
            providerModel));
      activity.setExternalRef(idRef);
      AttributeType uuidAttribute = AttributeUtil.getAttribute(
            (IIdentifiableModelElement) application, "carnot:model:uuid");
      if (uuidAttribute != null)
      {
         AttributeUtil.setAttribute((IIdentifiableModelElement) activity,
               "carnot:connection:uuid", uuidAttribute.getValue());
      }
      IdRefHandler.adapt(activity);
   }

   public static void createExternalReferenceToProcess(ActivityType activity,
         ProcessDefinitionType process, ModelType consumerModel, ModelType processModel)
   {

      String fileConnectionId = WebModelerConnectionManager.createFileConnection(
            consumerModel, processModel);

      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

      ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(uri,
            process, bundleId, null, true);

      AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME,
            descriptor.getURI().toString());
      if (processModel != null)
      {
         IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
         idRef.setRef(process.getId());
         idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, consumerModel,
               processModel));
         activity.setExternalRef(idRef);
         activity.setSubProcessMode(SubProcessModeType.SYNC_SEPARATE_LITERAL);

         AttributeType uuidAttribute = AttributeUtil.getAttribute(
               (IIdentifiableModelElement) process, "carnot:model:uuid");
         if (uuidAttribute != null)
         {
            AttributeUtil.setAttribute((IIdentifiableModelElement) activity,
                  "carnot:connection:uuid", uuidAttribute.getValue());
         }
         IdRefHandler.adapt(activity);
      }
   }

   public static void createExternalReferenceToDocument(DataType data,
         ModelType consumerModel, ModelType typeDeclarationModel,
         TypeDeclarationType typeDeclaration)
   {
      String fileConnectionId = WebModelerConnectionManager.createFileConnection(
            consumerModel, typeDeclarationModel);

      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

      ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(
            MergeUtils.createQualifiedUri(uri, typeDeclaration, true), data,
            typeDeclaration.getId(), typeDeclaration.getName(),
            typeDeclaration.getDescription(), bundleId, null);

      AttributeUtil
            .setAttribute(
                  data,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
      AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor
            .getURI().toString());
      ExternalReferenceType reference = XpdlFactory.eINSTANCE
            .createExternalReferenceType();
      if (typeDeclarationModel != null)
      {
         reference.setLocation(ImportUtils.getPackageRef(descriptor, consumerModel,
               typeDeclarationModel).getId());
      }
      reference.setXref(typeDeclaration.getId());

      String uuid = ExtendedAttributeUtil.getAttributeValue(
            typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
      if (uuid != null)
      {
         reference.setUuid(uuid);
      }

      data.setExternalReference(reference);
   }

   public static void createExternalReferenceToTypeDeclaration(DataType dataType,
         ModelType consumerModel, ModelType typeDeclarationModel,
         TypeDeclarationType typeDeclaration)
   {

      String fileConnectionId = WebModelerConnectionManager.createFileConnection(
            consumerModel, typeDeclarationModel);

      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

      ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(
            MergeUtils.createQualifiedUri(uri, typeDeclaration, true), dataType,
            typeDeclaration.getId(), typeDeclaration.getName(),
            typeDeclaration.getDescription(), bundleId, null);

      AttributeUtil
            .setAttribute(
                  dataType,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(dataType, "carnot:engine:data:bidirectional",
            true);

      AttributeUtil.setAttribute(dataType, IConnectionManager.URI_ATTRIBUTE_NAME,
            descriptor.getURI().toString());
      ExternalReferenceType reference = XpdlFactory.eINSTANCE
            .createExternalReferenceType();
      if (typeDeclarationModel != null)
      {
         reference.setLocation(ImportUtils.getPackageRef(descriptor, consumerModel,
               typeDeclarationModel).getId());
      }
      reference.setXref(typeDeclaration.getId());
      String uuid = ExtendedAttributeUtil.getAttributeValue(
            typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
      if (uuid != null)
      {
         reference.setUuid(uuid);
      }
      dataType.setExternalReference(reference);
   }

   public static void createStructReferenceForFormalParameter(
         FormalParameterType parameter,
         String declarationID, ModelType model, ModelType providerModel)
   {
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      ExternalReferenceUtils.updateReferences(model, providerModel);
      ExternalReferenceType extRef = xpdlFactory.createExternalReferenceType();
      extRef.setLocation(providerModel.getId());
      extRef.setXref(declarationID);

      TypeDeclarationType typeDeclaration = providerModel.getTypeDeclarations()
            .getTypeDeclaration(declarationID);
      String uuid = ExtendedAttributeUtil.getAttributeValue(
            typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
      if (uuid != null)
      {
         extRef.setUuid(uuid);
      }

      parameter.getDataType().setExternalReference(extRef);
   }

   public static void createStructReferenceForAccessPoint(AccessPointType accessPoint,
         String typeID, ModelType refModel)
   {
      TypeDeclarationType typeDeclaration = refModel.getTypeDeclarations()
            .getTypeDeclaration(typeID);
      if (typeDeclaration != null)
      {
         String uuid = ExtendedAttributeUtil.getAttributeValue(
               typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
         if (uuid != null)
         {
            AttributeUtil.setAttribute(accessPoint, "carnot:connection:uuid",
                  uuid);
         }
      }
   }

   public static void createStructReferenceForDocument(DataType data, String declarationID,
         ModelType model, ModelType providerModel)
   {
      TypeDeclarationType typeDeclaration = providerModel.getTypeDeclarations()
            .getTypeDeclaration(declarationID);

      if (typeDeclaration != null)
      {
         String fileConnectionId = WebModelerConnectionManager.createFileConnection(
               model, providerModel);

         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

         ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(
               MergeUtils.createQualifiedUri(uri, typeDeclaration, true), data,
               typeDeclaration.getId(), typeDeclaration.getName(),
               typeDeclaration.getDescription(), bundleId, null);

         AttributeUtil
               .setAttribute(
                     data,
                     "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
         AttributeUtil
               .setBooleanAttribute(data, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
         AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME,
               descriptor.getURI().toString());
         ExternalReferenceType reference = XpdlFactory.eINSTANCE
               .createExternalReferenceType();
         if (providerModel != null)
         {
            reference.setLocation(ImportUtils.getPackageRef(descriptor, model,
                  providerModel).getId());
         }
         reference.setXref(declarationID);
         String uuid = ExtendedAttributeUtil.getAttributeValue(
               typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
         if (uuid != null)
         {
            reference.setUuid(uuid);
         }
         data.setExternalReference(reference);
      }

   }

   public static void createStructReferenceForPrimitive(DataType data, ModelType model,
         String declarationID, ModelType typeDeclarationModel)
   {
      String fileConnectionId = WebModelerConnectionManager.createFileConnection(model,
            typeDeclarationModel);

      URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

      ExternalReferenceType reference = XpdlFactory.eINSTANCE
            .createExternalReferenceType();
      if (typeDeclarationModel != null)
      {
         reference.setLocation(getPackageRef(uri, model, typeDeclarationModel).getId());
         TypeDeclarationType typeDeclaration = typeDeclarationModel.getTypeDeclarations()
               .getTypeDeclaration(declarationID);
         if (typeDeclaration != null)
         {
            String uuid = ExtendedAttributeUtil.getAttributeValue(
                  typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
            if (uuid != null)
            {
               ModelBuilderFacade.setAttribute(data, "carnot:connection:uuid", uuid);
            }
         }
      }
      reference.setXref(declarationID);
      data.setExternalReference(reference);
   }


   public static void updateReferences(ModelType model, ModelType ref)
   {
      String connId = WebModelerConnectionManager.createFileConnection(model, ref);

      ExternalPackages packs = model.getExternalPackages();
      if (packs == null)
      {
         packs = XpdlFactory.eINSTANCE.createExternalPackages();
         model.setExternalPackages(packs);
      }
      if (packs.getExternalPackage(ref.getId()) == null)
      {
         ExternalPackage pack = XpdlFactory.eINSTANCE.createExternalPackage();
         pack.setId(ref.getId());
         pack.setName(ref.getName());
         pack.setHref(ref.getId());

         ExtendedAttributeUtil.setAttribute(pack, IConnectionManager.URI_ATTRIBUTE_NAME, "cnx://" + connId + "/");

         List<ExternalPackage> packList = packs.getExternalPackage();
         packList.add(pack);
      }
   }

   public static List<EObject> getExternalReferences(ModelType model,
         Connection connection)
   {
      String importString = connection.getAttribute("importByReference"); //$NON-NLS-1$
      if (importString != null && importString.equalsIgnoreCase("false")) //$NON-NLS-1$
      {
         return Collections.emptyList();
      }
      IConnectionManager connectionManager = model.getConnectionManager();
      String connectionId = connection.getId();
      List<EObject> list = CollectionUtils.newList();
      for (Iterator<EObject> i = model.eAllContents(); i.hasNext();)
      {
         EObject modelElement = i.next();
         checkExtensible(connectionManager, connectionId, list, modelElement);
         // (fh) special case, imports in embedded schemas

         if (modelElement instanceof XSDImport)
         {
            String location = ((XSDImport) modelElement).getSchemaLocation();
            if (location != null
                  && location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               QName qname = QName.valueOf(location
                     .substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length()));
               String namespace = qname.getNamespaceURI();
               if (!XMLConstants.NULL_NS_URI.equals(namespace)
                     && !namespace.equals(model.getId()))
               {
                  ExternalPackages packs = model.getExternalPackages();
                  if (packs != null)
                  {
                     ExternalPackage pack = packs.getExternalPackage(namespace);
                     if (pack != null)
                     {
                        String uri = ExtendedAttributeUtil.getAttributeValue(pack,
                              IConnectionManager.URI_ATTRIBUTE_NAME);
                        checkConnectionUsed(connectionManager, list, connectionId,
                              modelElement, uri == null ? null : URI.createURI(uri));
                     }
                  }
               }
            }
         }
         if (modelElement instanceof DataTypeType)
         {
            DataTypeType dataTypeType = (DataTypeType) modelElement;
            if (dataTypeType.getExternalReference() != null)
            {
               ExternalPackages packs = model.getExternalPackages();
               if (packs != null)
               {
                  ExternalPackage pack = packs.getExternalPackage(dataTypeType
                        .getExternalReference().getLocation());
                  if (pack != null)
                  {
                     String uri = ExtendedAttributeUtil.getAttributeValue(pack,
                           IConnectionManager.URI_ATTRIBUTE_NAME);
                     checkConnectionUsed(connectionManager, list, connectionId,
                           modelElement, uri == null ? null : URI.createURI(uri));
                  }
               }
            }
         }
         if (modelElement instanceof AccessPointType)
         {
            AccessPointType accessPoint = (AccessPointType) modelElement;
            String declaredType = AttributeUtil.getAttributeValue(accessPoint,
                  ModelerConstants.DATA_TYPE);
            if (declaredType != null && declaredType.indexOf("{") > 0)
            {
               String typeID = declaredType.substring(declaredType.indexOf("}") + 1);
               String refModelID = declaredType.substring(declaredType.indexOf("{") + 1,
                     declaredType.indexOf("}"));
               if (!typeID.equals(ModelerConstants.TO_BE_DEFINED))
               {
                  ExternalPackages packs = model.getExternalPackages();
                  if (packs != null)
                  {
                     ExternalPackage pack = packs.getExternalPackage(refModelID);
                     if (pack != null)
                     {
                        String uri = ExtendedAttributeUtil.getAttributeValue(pack,
                              IConnectionManager.URI_ATTRIBUTE_NAME);
                        checkConnectionUsed(connectionManager, list, connectionId,
                              modelElement, uri == null ? null : URI.createURI(uri));
                     }
                  }
               }
            }
         }
      }
      return list;
   }

   private static void checkExtensible(IConnectionManager connectionManager,
         String connectionId, List<EObject> list, EObject modelElement)
   {
      URI connectionUri = null;
      if (modelElement.eIsProxy())
      {
         URI proxyUri = ((InternalEObject) modelElement).eProxyURI();
         if (IConnectionManager.SCHEME.equals(proxyUri.scheme()))
         {
            connectionUri = proxyUri.trimSegments(proxyUri.segmentCount());
         }
      }
      else
      {
         String uri = null;
         if (modelElement instanceof IExtensibleElement)
         {
            uri = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
         }
         else if (modelElement instanceof Extensible)
         {
            uri = ExtendedAttributeUtil.getAttributeValue((Extensible) modelElement,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
         }
         if (uri != null)
         {
            connectionUri = URI.createURI(uri);
         }
      }
      checkConnectionUsed(connectionManager, list, connectionId, modelElement,
            connectionUri);
   }

   private static void checkConnectionUsed(IConnectionManager connectionManager,
         List<EObject> list, String connectionId, EObject modelElement, URI connectionUri)
   {
      if (connectionUri != null)
      {
         IConnection refConnection = connectionManager.findConnection(connectionUri);
         if (refConnection != null && connectionId.equals(refConnection.getId()))
         {
            list.add(modelElement);
         }
      }
   }

   public static void fixExternalReferences(Map<String, ModelType> models, ModelType model)
   {
      List<String> uris = ModelUtils.getURIsForExternalPackages(model);
      for (Iterator<String> i = uris.iterator(); i.hasNext();)
      {
         String uri = i.next();
         WebModelerConnectionManager cm = (WebModelerConnectionManager) model
               .getConnectionManager();
         Connection connection = (Connection) cm.findConnection(uri);
         List<EObject> references = getExternalReferences(model, (Connection) connection);
         ModelType modelType = ModelUtils.getReferencedModelByURI(model, uri);
         if (modelType != null)
         {
            ModelType refModel = models.get(modelType.getId());

            fixConnection(connection, refModel);

            for (Iterator<EObject> j = references.iterator(); j.hasNext();)
            {
               EObject ref = j.next();
               if (ref instanceof ExternalPackage)
               {
                  fixExternalPackage((ExternalPackage) ref, refModel);
               }
               if (ref instanceof ActivityType)
               {
                  fixActivity((ActivityType) ref, refModel);
               }
               if (ref instanceof DataType)
               {
                  fixData((DataType) ref, refModel);
               }
               if (ref instanceof DataTypeType)
               {
                  fixDataTypeType((DataTypeType) ref, refModel);
               }
               if (ref instanceof AccessPointType)
               {
                  fixAccessPoint((AccessPointType) ref, refModel);
               }
               /*
                * if (ref instanceof XSDImport) { fixXSDImport((XSDImport) ref, refModel);
                * }
                */
            }
         }
      }
   }

   private static void fixConnection(Connection connection, ModelType refModel)
   {
      String connectionUUID = connection.getAttribute("connectionUUID");
      if (null != connectionUUID)
      {
         AttributeType attribute = AttributeUtil.getAttribute(refModel,
               "carnot:model:uuid");
         if (null != attribute)
         {
            if (attribute.getValue().equals(connectionUUID))
            {
               String filename = connection.getAttribute("filename");
               if (null != filename)
               {
                  String lastSegment = null;
                  try
                  {
                     lastSegment = filename.substring(filename.lastIndexOf("/") + 1);
                  }
                  catch (Throwable t)
                  {
                     return;
                  }
                  if (null != lastSegment && null != refModel.eResource())
                  {
                     String realFilename = refModel.eResource().getURI().toString();
                     if (!lastSegment.equals(realFilename))
                     {
                        filename = filename.replaceAll(lastSegment, realFilename);
                        connection.setAttribute("filename", filename);
                     }
                  }
               }
            }
         }
      }
   }

   private static void fixExternalPackage(ExternalPackage ref, ModelType refModel)
   {
      if (!ref.getHref().equals(refModel.getId()))
      {
         ref.setHref(refModel.getId());
         ref.setId(refModel.getId());
         ref.setName(refModel.getName());
      }
   }

   private static void fixAccessPoint(AccessPointType accessPoint, ModelType refModel)
   {
      String declaredType = AttributeUtil.getAttributeValue(accessPoint,
            ModelerConstants.DATA_TYPE);
      if (declaredType != null)
      {
         String typeID = declaredType.substring(declaredType.indexOf("}") + 1);
         String refModelID = declaredType.substring(declaredType.indexOf("{") + 1, declaredType.indexOf("}"));
         AttributeType uuidAttribute = AttributeUtil.getAttribute(accessPoint,
               "carnot:connection:uuid");
         if (uuidAttribute != null)
         {
            TypeDeclarationType declaration = findTypeDeclarationModelUUID(refModel,
                  uuidAttribute.getAttributeValue());
            if (declaration != null)
            {
               if (!declaration.getId().equals(typeID) || !refModel.getId().equals(refModelID))
               {
                  declaredType = "typeDeclaration:{" + refModel.getId() + "}"
                        + declaration.getId();
                  AttributeUtil.setAttribute(accessPoint, ModelerConstants.DATA_TYPE,
                        declaredType);
               }
            }
         }
      }
   }

   private static void fixDataTypeType(DataTypeType dataTypeType, ModelType refModel)
   {
      ExternalReferenceType ref = dataTypeType.getExternalReference();
      if (ref != null && ref.getUuid() != null)
      {
         TypeDeclarationType declaration = findTypeDeclarationModelUUID(refModel,
               ref.getUuid());
         if (declaration != null)
         {
            if (!declaration.getId().equals(ref.getXref()))
            {
               ref.setXref(declaration.getId());
            }
         }
      }

   }

   private static void fixData(DataType data, ModelType refModel)
   {
      if (data.getType() != null)
      {
         if (data.getType().getId().equals("struct"))
         {
            ExternalReferenceType ref = data.getExternalReference();
            if (ref != null && ref.getUuid() != null)
            {
               TypeDeclarationType declaration = findTypeDeclarationModelUUID(refModel,
                     ref.getUuid());
               if (declaration != null)
               {
                  if (!declaration.getId().equals(ref.getXref()))
                  {
                     ref.setXref(declaration.getId());
                     AttributeType uriAttribute = AttributeUtil.getAttribute(
                           (IExtensibleElement) data, "carnot:connection:uri");
                     if (uriAttribute != null)
                     {
                        String uri = uriAttribute.getAttributeValue();
                        uri = uri.substring(0, uri.lastIndexOf("/")) + "/"
                              + declaration.getId();
                        AttributeUtil.setAttribute((IExtensibleElement) data,
                              "carnot:connection:uri", uri);
                     }
                  }
                  if (!refModel.getId().equals(ref.getLocation()))
                  {
                     ref.setLocation(refModel.getId());
                  }
               }
            }
         }
         if (data.getType().getId().equals("dmsDocument"))
         {
            ExternalReferenceType ref = data.getExternalReference();
            if (ref != null && ref.getUuid() != null)
            {
               TypeDeclarationType declaration = findTypeDeclarationModelUUID(refModel,
                     ref.getUuid());
               if (declaration != null)
               {
                  if (!declaration.getId().equals(ref.getXref()))
                  {
                     ref.setXref(declaration.getId());
                     AttributeType uriAttribute = AttributeUtil.getAttribute(
                           (IExtensibleElement) data, "carnot:connection:uri");
                     if (uriAttribute != null)
                     {
                        String uri = uriAttribute.getAttributeValue();
                        uri = uri.substring(0, uri.lastIndexOf("/")) + "/"
                              + declaration.getId();
                        AttributeUtil.setAttribute((IExtensibleElement) data,
                              "carnot:connection:uri", uri);
                        AttributeType metaDataAttribute = AttributeUtil.getAttribute(
                              (IExtensibleElement) data,
                              "carnot:engine:dms:resourceMetadataSchema");
                        if (metaDataAttribute != null)
                        {
                           String metaData = metaDataAttribute.getAttributeValue();
                           metaData = metaData.substring(0, metaData.indexOf("{")) + "{"
                                 + declaration.getId() + "}";
                           AttributeUtil.setAttribute((IExtensibleElement) data,
                                 "carnot:engine:dms:resourceMetadataSchema", metaData);

                        }
                     }
                  }
                  if (!refModel.getId().equals(ref.getLocation()))
                  {
                     ref.setLocation(refModel.getId());
                     AttributeType metaDataAttribute = AttributeUtil.getAttribute(
                           (IExtensibleElement) data,
                           "carnot:engine:dms:resourceMetadataSchema");
                     if (metaDataAttribute != null)
                     {
                        String metaData = metaDataAttribute.getAttributeValue();
                        metaData = refModel.getId()
                              + metaData.substring(metaData.indexOf("{"));
                        AttributeUtil.setAttribute((IExtensibleElement) data,
                              "carnot:engine:dms:resourceMetadataSchema", metaData);

                     }
                  }
               }
            }
         }
      }
   }

   private static void fixActivity(ActivityType activity, ModelType refModel)
   {

      if (activity.getImplementation().getLiteral()
            .equals(ActivityImplementationType.SUBPROCESS_LITERAL.getLiteral()))
      {
         fixSubprocessActivity(activity, refModel);
      }

      if (activity.getImplementation().getLiteral()
            .equals(ActivityImplementationType.APPLICATION_LITERAL.getLiteral()))
      {
         fixApplicationActivity(activity, refModel);
      }

   }

   private static void fixSubprocessActivity(ActivityType activity, ModelType refModel)
   {
      AttributeType uuidAttribute = AttributeUtil.getAttribute(
            (IIdentifiableModelElement) activity, "carnot:connection:uuid");
      if (uuidAttribute != null)
      {
         ProcessDefinitionType process = findProcessByModelUUID(refModel,
               uuidAttribute.getAttributeValue());
         if (process != null)
         {
            IdRef externalReference = activity.getExternalRef();
            if (!externalReference.getRef().equals(process.getId()))
            {
               externalReference.setRef(process.getId());
               AttributeType uriAttribute = AttributeUtil.getAttribute(
                     (IExtensibleElement) activity, "carnot:connection:uri");
               if (uriAttribute != null)
               {
                  String uri = uriAttribute.getAttributeValue();
                  uri = uri.substring(0, uri.lastIndexOf("/")) + "/" + process.getId();
                  AttributeUtil.setAttribute((IExtensibleElement) activity,
                        "carnot:connection:uri", uri);
               }
            }
            if (!externalReference.getPackageRef().getHref().equals(refModel.getId()))
            {
               externalReference.getPackageRef().setHref(refModel.getId());
               externalReference.getPackageRef().setId(refModel.getId());
               externalReference.getPackageRef().setName(refModel.getId());
            }
         }
      }
   }

   private static void fixApplicationActivity(ActivityType activity, ModelType refModel)
   {
      AttributeType uuidAttribute = AttributeUtil.getAttribute(
            (IIdentifiableModelElement) activity, "carnot:connection:uuid");
      if (uuidAttribute != null)
      {
         ApplicationType application = findApplicationByModelUUID(refModel,
               uuidAttribute.getAttributeValue());
         if (application != null)
         {
            IdRef externalReference = activity.getExternalRef();
            if (!externalReference.getRef().equals(application.getId()))
            {
               externalReference.setRef(application.getId());
               AttributeType uriAttribute = AttributeUtil.getAttribute(
                     (IExtensibleElement) activity, "carnot:connection:uri");
               if (uriAttribute != null)
               {
                  String uri = uriAttribute.getAttributeValue();
                  uri = uri.substring(0, uri.lastIndexOf("/")) + "/"
                        + application.getId();
                  AttributeUtil.setAttribute((IExtensibleElement) activity,
                        "carnot:connection:uri", uri);
               }
            }
            if (!externalReference.getPackageRef().getHref().equals(refModel.getId()))
            {
               externalReference.getPackageRef().setHref(refModel.getId());
               externalReference.getPackageRef().setId(refModel.getId());
               externalReference.getPackageRef().setName(refModel.getId());
            }
         }
      }
   }

   private static ApplicationType findApplicationByModelUUID(ModelType refModel,
         String uuid)
   {
      for (Iterator<ApplicationType> i = refModel.getApplication().iterator(); i
            .hasNext();)
      {
         ApplicationType application = i.next();
         AttributeType uuidAttribute = AttributeUtil.getAttribute(
               (IIdentifiableModelElement) application, "carnot:model:uuid");
         if (uuidAttribute != null)
         {
            if (uuidAttribute.getAttributeValue().equals(uuid))
            {
               return application;
            }
         }
      }
      return null;
   }

   private static ProcessDefinitionType findProcessByModelUUID(ModelType refModel,
         String uuid)
   {
      for (Iterator<ProcessDefinitionType> i = refModel.getProcessDefinition().iterator(); i
            .hasNext();)
      {
         ProcessDefinitionType process = i.next();
         AttributeType uuidAttribute = AttributeUtil.getAttribute(
               (IIdentifiableModelElement) process, "carnot:model:uuid");
         if (uuidAttribute != null)
         {
            if (uuidAttribute.getAttributeValue().equals(uuid))
            {
               return process;
            }
         }
      }
      return null;
   }

   private static TypeDeclarationType findTypeDeclarationModelUUID(ModelType refModel,
         String uuid)
   {
      for (Iterator<TypeDeclarationType> i = refModel.getTypeDeclarations()
            .getTypeDeclaration().iterator(); i.hasNext();)
      {
         TypeDeclarationType declaration = i.next();
         ExtendedAttributeType uuidAttribute = ExtendedAttributeUtil.getAttribute(
               declaration.getExtendedAttributes(), "carnot:model:uuid");
         if (uuidAttribute != null)
         {
            if (uuidAttribute.getValue().equals(uuid))
            {
               return declaration;
            }
         }
      }
      return null;
   }

   public static ExternalPackage getPackageRef(URI uri, ModelType targetModel, ModelType sourceModel)
   {
      LinkAttribute linkAttribute;
      XpdlFactory xFactory = XpdlFactory.eINSTANCE;
      String packageRef = sourceModel.getId();
      ExternalPackages packages = targetModel.getExternalPackages();
      if (packages == null)
      {
         packages = xFactory.createExternalPackages();
         targetModel.setExternalPackages(packages);
      }
      ExternalPackage pkg = packages.getExternalPackage(packageRef);
      if (pkg == null)
      {
         pkg = xFactory.createExternalPackage();
         pkg.setId(packageRef);
         pkg.setName(sourceModel.getName());
         pkg.setHref(packageRef);

         if (uri != null)
         {
            linkAttribute = new LinkAttribute(uri, false,
                  false, IConnectionManager.URI_ATTRIBUTE_NAME);
            linkAttribute.setLinkInfo(pkg, false);
         }

         packages.getExternalPackage().add(pkg);
      }
      return pkg;
   }

   public static boolean isModelReferenced(ModelType modelToCheck, Collection<ModelType> models)
   {
      for (EObject model : models)
      {
         if (model instanceof ModelType)
         {
            if(!modelToCheck.getId().equals(((ModelType) model).getId()))
            {
               List<String> uris = ModelUtils.getURIsForExternalPackages((ModelType) model);
               for (Iterator<String> i = uris.iterator(); i.hasNext();)
               {
                  String uri = i.next();
                  ModelType modelType = ModelUtils.getReferencedModelByURI((ModelType) model, uri);
                  if(modelType != null && modelToCheck.getId().equals(modelType.getId()))
                  {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public static List<ModelType> getReferingModels(String refModelID, Collection<ModelType> models)
   {
      List<ModelType> referingModels = new ArrayList<ModelType>();
      for (EObject model : models)
      {
         if (model instanceof ModelType)
         {
            if(!refModelID.equals(((ModelType) model).getId()))
            {
               List<String> uris = ModelUtils.getURIsForExternalPackages((ModelType) model);
               for (Iterator<String> i = uris.iterator(); i.hasNext();)
               {
                  String uri = i.next();
                  ModelType modelType = ModelUtils.getReferencedModelByURI((ModelType) model, uri);
                  if (modelType != null)
                  {
                     referingModels.add((ModelType) model);
                  }
               }
            }
         }
      }
      return referingModels;
   }

   public static TypeDeclarationType getTypeDeclarationFromProxy(DataType data)
   {
      TypeDeclarationType typeDeclaration = null;
      if (Proxy.getInvocationHandler(data) instanceof EObjectProxyHandler)
      {
         EObjectProxyHandler proxyHandler = (EObjectProxyHandler) Proxy
               .getInvocationHandler(data);
         data = (DataType) proxyHandler.getTarget();
         AttributeType attribute = AttributeUtil.getAttribute(data,
               StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (attribute != null)
         {
            String typeDeclID = attribute.getValue();
            ModelType refModel = ModelUtils.findContainingModel(data);
            typeDeclaration = refModel.getTypeDeclarations().getTypeDeclaration(
                  typeDeclID);
         }
      }
      return typeDeclaration;
   }

}
