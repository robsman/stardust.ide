/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.integration.dms.data;

import java.util.Set;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.ClientXPathMap;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.engine.core.struct.emfxsd.XPathFinder;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.emfxsd.DmsSchemaProvider;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPathEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DmsTypeUtils
{
   
   public static StructAccessPointType newVersioningAccessPoint(ModelType model, String id, DirectionType direction)
   {
      IXPathMap xPathMap = new ClientXPathMap(declareVersioningSchema());
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);

      result.setId(id);
      result.setName("versioning"); //$NON-NLS-1$
      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), StructuredDataConstants.STRUCTURED_DATA));
      result.setDirection(direction);
      
      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }

      return result;
   }
   
   public static StructAccessPointType newDmsDocumentAccessPoint(ModelType model,
         DirectionType direction)
   {
      return newDmsDocumentAccessPoint(model, null, direction);
   }
   
   public static StructAccessPointType newDmsDocumentAccessPoint(ModelType model,
         String id, DirectionType direction)
   {
      return newDmsDocumentAccessPoint(model, id, null, direction);
   }
   
   public static StructAccessPointType newDmsDocumentAccessPoint(ModelType model,
         String id, IExtensibleElement element, DirectionType direction)
   {
      // TODO (ab) extension point
      IXPathMap xPathMap = new ClientXPathMap(DmsSchemaProvider.declareDocumentSchema(getCustomMetadataType(element, model)));
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);

      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT));
      result.setDirection(direction);

      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }

      if ( !StringUtils.isEmpty(id))
      {
         result.setId(id);
         result.setName(id);
      }

      return result;
   }
   
   public static StructAccessPointType newDmsDocumentListAccessPoint(ModelType model,
         String id, DirectionType direction)
   {
      return newDmsDocumentListAccessPoint(model, id, null, direction);
   }
   
   public static StructAccessPointType newDmsDocumentListAccessPoint(ModelType model,
         String id, IExtensibleElement element, DirectionType direction)
   {
      // TODO (ab) extension point
      IXPathMap xPathMap = new ClientXPathMap(DmsSchemaProvider.declareDocumentListSchema(getCustomMetadataType(element, model)));
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);

      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST));
      result.setDirection(direction);

      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }
      
      if ( !StringUtils.isEmpty(id))
      {
         result.setId(id);
         result.setName(id);
      }
      
      return result;
   }
   
   public static StructAccessPointType newDmsFolderListAccessPoint(ModelType model,
         String id, DirectionType direction)
   {
      return newDmsFolderListAccessPoint(model, id, null, direction);
   }
   
   public static StructAccessPointType newDmsFolderListAccessPoint(ModelType model,
         String id, IExtensibleElement element, DirectionType direction)
   {
      // TODO (ab) extension point
      IXPathMap xPathMap = new ClientXPathMap(DmsSchemaProvider.declareFolderListSchema(getCustomMetadataType(element, model)));
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);

      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_FOLDER_LIST));
      result.setDirection(direction);

      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }
      
      if ( !StringUtils.isEmpty(id))
      {
         result.setId(id);
         result.setName(id);
      }
      
      return result;
   }
   
   public static StructAccessPointType newDmsFolderInfoAccessPoint(ModelType model,
         String id, DirectionType direction)
   {
      return newDmsFolderInfoAccessPoint(model, id, null, direction);
   }

   public static StructAccessPointType newDmsFolderInfoAccessPoint(ModelType model, String id,
         IExtensibleElement element, DirectionType direction)
   {
      // TODO (ab) extension point
      IXPathMap xPathMap = new ClientXPathMap(DmsSchemaProvider.declareFolderInfoSchema(getCustomMetadataType(element, model)));
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);

      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_FOLDER));
      result.setDirection(direction);

      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }
      
      if ( !StringUtils.isEmpty(id))
      {
         result.setId(id);
         result.setName(id);
      }
      
      return result;
   }
   
   public static StructAccessPointType newDmsFolderAccessPoint(ModelType model,
         DirectionType direction)
   {
      return newDmsFolderAccessPoint(model, null, null, direction);
   }

   public static StructAccessPointType newDmsFolderAccessPoint(ModelType model,
         String id, DirectionType direction)
   {
      return newDmsFolderAccessPoint(model, id, null, direction);
   }

   public static StructAccessPointType newDmsFolderAccessPoint(ModelType model, String id,
         IExtensibleElement element, DirectionType direction)
   {
      // TODO (ab) extension point
      IXPathMap xPathMap = new ClientXPathMap(DmsSchemaProvider.declareFolderSchema(getCustomMetadataType(element, model)));
      StructAccessPointType result = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);
      
      result.setType((DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), DmsConstants.DATA_TYPE_DMS_FOLDER));
      result.setDirection(direction);

      if ((DirectionType.IN_LITERAL == direction)
            || (DirectionType.INOUT_LITERAL == direction))
      {
         AttributeUtil.setBooleanAttribute(result, PredefinedConstants.BROWSABLE_ATT, true);
         AttributeUtil.setBooleanAttribute(result, CarnotConstants.ENGINE_SCOPE
               + "data:bidirectional", true); //$NON-NLS-1$
      }
      
      if ( !StringUtils.isEmpty(id))
      {
         result.setId(id);
         result.setName(id);
      }
      
      return result;
   }
   
   private static XSDNamedComponent getCustomMetadataType(IExtensibleElement element, ModelType model)
   {
      if (null != element)
      {
         final String metadataTypeId = AttributeUtil.getAttributeValue(element,
               DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
         
         if ( !StringUtils.isEmpty(metadataTypeId))
         {
            TypeDeclarationType metadataTypeDeclaration = ModelUtils.getTypeDeclaration(element, metadataTypeId);
            if (metadataTypeDeclaration != null)
            {
               return TypeDeclarationUtils.findElementOrTypeDeclaration(metadataTypeDeclaration);
            }
         }
      }

      return null;
   }

   public static IAccessPathEditor getDmsStructuredDataEditor(ModelType model,
         IExtensibleElement data)
   {
      StructAccessPathEditor delegate = new StructAccessPathEditor();
      return delegate;
   }
   
   public static ModelType findModelFromContext(ITypedElement context)
   {
      ModelType model = ModelUtils.findContainingModel(context);
      if ((null == model) && (context instanceof AccessPointType)
            && (null != ((AccessPointType) context).getType()))
      {
         model = ModelUtils.findContainingModel(((AccessPointType) context).getType());
      }
      
      return model;
   }
   
   public static AccessPointType createPrimitiveAccessPointType(String id, Class<?> clazz, DirectionType direction, IModelElement element)
   {
      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.PRIMITIVE_DATA_ID);
      AccessPointType ap = AccessPointUtil.createAccessPoint(id, id, direction,
            serializable);
      AttributeUtil.setAttribute(ap, PredefinedConstants.TYPE_ATT, clazz.getName());
      return ap;
   }
   
   public static AccessPointType createSerializableAccessPointType(String id, Class<?> clazz, DirectionType direction, IModelElement element)
   {
      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.SERIALIZABLE_DATA_ID);
      AccessPointType ap = AccessPointUtil.createAccessPoint(id, id, direction,
            serializable);
      AttributeUtil.setAttribute(ap, PredefinedConstants.CLASS_NAME_ATT, clazz.getName());
      return ap;
   }
   
   public static AccessPointType createSerializableAccessPointType(String id, String className, DirectionType direction, IModelElement element)
   {
      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.SERIALIZABLE_DATA_ID);
      AccessPointType ap = AccessPointUtil.createAccessPoint(id, id, direction,
            serializable);
      AttributeUtil.setAttribute(ap, PredefinedConstants.CLASS_NAME_ATT, className);
      return ap;
   }   
      
   private static Set<TypedXPath> declareVersioningSchema()
   {
      XSDSchema xsdSchema = StructuredTypeUtils.loadExternalSchemaFromClasspath(DmsConstants.MONTAUK_SCHEMA_XSD);
      return XPathFinder.findAllXPaths(xsdSchema, DmsSchemaProvider.VERSIONING_COMPLEX_TYPE_NAME, false);
   }

   public static TypeDeclarationType getResourceTypeDeclaration()
   {
      return StructuredTypeUtils.getResourceTypeDeclaration();
   }

   private DmsTypeUtils()
   {
      
   }
   
}
