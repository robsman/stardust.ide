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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.ExtensionProviderUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;

import ag.carnot.bpm.rt.data.structured.ClientXPathMap;
import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;
import ag.carnot.bpm.rt.data.structured.emfxsd.ClasspathUriConverter;
import ag.carnot.bpm.rt.data.structured.emfxsd.XPathFinder;
import ag.carnot.bpm.rt.data.structured.spi.ISchemaTypeProvider;

import com.infinity.bpm.rt.integration.data.dms.DmsConstants;
import com.infinity.bpm.rt.integration.data.dms.emfxsd.DmsSchemaProvider;

/**
 * @author rsauer
 * @version $Revision$
 */
public class StructuredTypeUtils
{
   public static final Pattern TRANSFORMATION_PATTERN = Pattern.compile("(DOM) *\\((.*)\\)");
   
   public static IXPathMap getXPathMap(DataType data)
   {
      String dataTypeId = data.getType().getId();
      if (dataTypeId.equals(StructuredDataConstants.STRUCTURED_DATA))
      {
         // user-defined structured data
         TypeDeclarationType typeDeclarationType = getTypeDeclaration(data);
         if (typeDeclarationType == null)
         {
            throw new PublicException("No type declaration specified for data '"
               + data.getId() + "'.");
         }
         return getXPathMap(typeDeclarationType);
      }
      else
      {
         // build-in schema
         TypeDeclarationType metadataTypeDeclaration = getCustomMetadataType(data, ModelUtils.findContainingModel(data));

         XSDNamedComponent metadataXsdComponent = null;
         if (metadataTypeDeclaration != null)
         {
            metadataXsdComponent = TypeDeclarationUtils.findElementOrTypeDeclaration(metadataTypeDeclaration);
         }
         
         Map parameters = CollectionUtils.newMap();
         parameters.put(DmsSchemaProvider.PARAMETER_METADATA_TYPE, metadataXsdComponent);

         for (Iterator<ISchemaTypeProvider.Factory> i = ExtensionProviderUtils.getExtensionProviders(
               ISchemaTypeProvider.Factory.class).iterator(); i.hasNext();)
         {
            ISchemaTypeProvider.Factory stpFactory = i.next();
            
            ISchemaTypeProvider provider = stpFactory.getSchemaTypeProvider(dataTypeId);
            if (null != provider)
            {
               Set result = provider.getSchemaType(dataTypeId, parameters);
               
               if (null != result)
               {
                  return new ClientXPathMap(result);
               }
            }
         }
         throw new InternalException("Could not find predefined XPaths for data type '"
               + dataTypeId
               + "'. Check if schema providers are configured correctly.");
      }
   }

   public static TypeDeclarationType getTypeDeclaration(DataType data)
   {
      ExternalReferenceType ref = data.getExternalReference();
      if (ref == null)
      {
         return (TypeDeclarationType) AttributeUtil.getIdentifiable(
            data,  StructuredDataConstants.TYPE_DECLARATION_ATT);
      }
      else
      {
         ModelType model = ModelUtils.findContainingModel(data);
         ExternalPackages packages = model.getExternalPackages();
         ExternalPackage pkg = packages == null ? null : packages.getExternalPackage(ref.getLocation());
         IConnectionManager manager = model.getConnectionManager();
         ModelType externalModel = manager == null ? null : manager.find(pkg);
         if (externalModel != null)
         {
            TypeDeclarationsType declarations = externalModel.getTypeDeclarations();
            if (declarations != null)
            {
               return declarations.getTypeDeclaration(ref.getXref());
            }
         }
      }
      return null;
   }
   
   private static TypeDeclarationType getCustomMetadataType(IExtensibleElement element, ModelType model)
   {
      if (null != element)
      {
         final String metadataSchema = AttributeUtil.getAttributeValue(element,
               DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
         
         if (!StringUtils.isEmpty(metadataSchema))
         {
            return model.getTypeDeclarations().getTypeDeclaration(metadataSchema);
         }
      }

      return null;
   }

   public static IXPathMap getXPathMap (ModelType modelType, String declaredTypeId)
   {
      TypeDeclarationType t = modelType.getTypeDeclarations().getTypeDeclaration(declaredTypeId);
      return getXPathMap(t);
   }

   public static IXPathMap getXPathMap (TypeDeclarationType typeDeclaration)
   {
      Set /*<TypedXPath>*/ allXPaths;
      XSDSchema schema = typeDeclaration.getSchema();
      if (schema != null)
      {
         XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(typeDeclaration);
         allXPaths = XPathFinder.findAllXPaths(schema, component);
      }
      else
      {
         throw new RuntimeException(
               "Neither external reference not schema type is set in the type declaration for '"
                     + typeDeclaration.getId() + "'.");
      }
      return new ClientXPathMap(allXPaths);
   }

   public static XSDSchema loadExternalSchemaFromClasspath(String schemaLocation)
   {
      ResourceSet resourceSet = new ResourceSetImpl();
      
      HashMap options = new HashMap();
      options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
       
      resourceSet.setURIConverter(new ClasspathUriConverter());
      Resource resource = resourceSet.createResource(URI.createURI(ClasspathUriConverter.CLASSPATH_SCHEME+":/"+schemaLocation));
      try
      {
         resource.load(options);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
      
      List l = resource.getContents();
      return (XSDSchema) l.get(0);  
   }

   public static void setStructuredAccessPointAttributes(IExtensibleElement accessPoint, TypeDeclarationType type)
   {
      setStructuredAccessPointAttributes(accessPoint, type, null);
   }
   
   public static void setStructuredAccessPointAttributes(IExtensibleElement accessPoint, TypeDeclarationType type, String transformationType)
   {
      AttributeUtil.setReference(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT, type);
      if ( !StringUtils.isEmpty(transformationType))
      {
         AttributeUtil.setAttribute(accessPoint, StructuredDataConstants.TRANSFORMATION_ATT, transformationType);
      }
      AttributeUtil.setAttribute(accessPoint, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); 
      AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:bidirectional", true);
   }
   
   public static boolean isValidDomAccessPath(DataType dataType, String accessPath)
   {      
      //Remove enclosing DOM() from access path
      accessPath = accessPath.substring(4);
      accessPath = accessPath.substring(0, accessPath.length() - 1);      
      IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(dataType); 
      boolean listOrPrimitive = (StructuredDataXPathUtils.canReturnList(accessPath.toString(), xPathMap) || StructuredDataXPathUtils.returnsSinglePrimitive(accessPath.toString(), xPathMap));
      return !listOrPrimitive;
   }
   
   public static TypeDeclarationType getResourceTypeDeclaration()
   {
      TypeDeclarationType typeDeclaration = XpdlFactory.eINSTANCE
            .createTypeDeclarationType();
      typeDeclaration.setName("<default>");
      typeDeclaration.setId(DmsSchemaProvider.RESOURCE_PROPERTY_COMPLEX_TYPE_NAME);
      SchemaTypeType schemaType = XpdlFactory.eINSTANCE.createSchemaTypeType();
      schemaType.setSchema(StructuredTypeUtils
            .loadExternalSchemaFromClasspath(DmsConstants.MONTAUK_SCHEMA_XSD));
      typeDeclaration.setSchemaType(schemaType);
      return typeDeclaration;
   }
   
   private StructuredTypeUtils()
   {
      // utility class
   }

}
