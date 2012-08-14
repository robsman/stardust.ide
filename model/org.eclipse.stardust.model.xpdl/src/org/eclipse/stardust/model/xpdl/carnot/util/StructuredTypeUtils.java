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
import java.text.MessageFormat;
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
import org.eclipse.stardust.engine.core.struct.*;
import org.eclipse.stardust.engine.core.struct.emfxsd.ClasspathUriConverter;
import org.eclipse.stardust.engine.core.struct.emfxsd.XPathFinder;
import org.eclipse.stardust.engine.core.struct.spi.ISchemaTypeProvider;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.emfxsd.DmsSchemaProvider;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;

/**
 * @author rsauer
 * @version $Revision$
 */
public class StructuredTypeUtils
{
   public static final Pattern TRANSFORMATION_PATTERN = Pattern.compile("(DOM) *\\((.*)\\)"); //$NON-NLS-1$
   
   public static IXPathMap getXPathMap(DataType data)
   {
      String dataTypeId = data.getType().getId();
      if (dataTypeId.equals(StructuredDataConstants.STRUCTURED_DATA))
      {
         // user-defined structured data
         TypeDeclarationType typeDeclarationType = getTypeDeclaration(data);
         if (typeDeclarationType == null)
         {
        	 String message = Model_Messages.EXC_NO_TYPE_DECLARATION_SPECIFIED_FOR_DATA_NULL;
            throw new PublicException( MessageFormat.format(message, new Object[]{data.getId()}));
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
         
         Map<Object, Object> parameters = CollectionUtils.newMap();
         parameters.put(DmsSchemaProvider.PARAMETER_METADATA_TYPE, metadataXsdComponent);

         for (Iterator<ISchemaTypeProvider.Factory> i = ExtensionProviderUtils.getExtensionProviders(
               ISchemaTypeProvider.Factory.class).iterator(); i.hasNext();)
         {
            ISchemaTypeProvider.Factory stpFactory = i.next();
            
            ISchemaTypeProvider provider = stpFactory.getSchemaTypeProvider(dataTypeId);
            if (null != provider)
            {
               Set<?> result = provider.getSchemaType(dataTypeId, parameters);
               if (null != result)
               {
                  return new ClientXPathMap(result);
               }
            }
         }
         String message = Model_Messages.EXC_COULD_NOT_FIND_PREDEFINED_XPATHS_FOR_DATA_TYPE_NULL;
         throw new InternalException(MessageFormat.format(message, new Object[]{dataTypeId}));
      }
   }

   public static TypeDeclarationType getTypeDeclaration(DataType data)
   {
      TypeDeclarationType typeDeclaration = null;
      ModelType model = ModelUtils.findContainingModel(data);
      ExternalReferenceType ref = data.getExternalReference();
      if (ref == null)
      {
         typeDeclaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
            data,  StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (typeDeclaration != null)
         {
            ModelType declarationModel = ModelUtils.findContainingModel(typeDeclaration);
            if (model != declarationModel)
            {
               typeDeclaration = getTypeDeclaration(model, declarationModel.getId(), typeDeclaration.getId());
      }
         }
      }
      else
      {
         typeDeclaration = getTypeDeclaration(model, ref.getLocation(), ref.getXref());
      }
      return typeDeclaration;
   }

   private static TypeDeclarationType getTypeDeclaration(ModelType model,
         String packageId, String typeDeclarationId)
   {
         ExternalPackages packages = model.getExternalPackages();
      ExternalPackage pkg = packages == null ? null : packages.getExternalPackage(packageId);
         IConnectionManager manager = model.getConnectionManager();
         ModelType externalModel = manager == null ? null : manager.find(pkg);
         if (externalModel != null)
         {
            TypeDeclarationsType declarations = externalModel.getTypeDeclarations();
            if (declarations != null)
            {
            return declarations.getTypeDeclaration(typeDeclarationId);
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
      Set<TypedXPath> allXPaths;
      XSDSchema schema = typeDeclaration.getSchema();
      if (schema != null)
      {
         XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(typeDeclaration);
         allXPaths = XPathFinder.findAllXPaths(schema, component);
      }
      else
      {	
    	 String message = Model_Messages.EXC_NEITHER_EXTERNAL_REFERENCE_NOR_SCHEME_TYPE_IS_SET_FOR_NULL;
         throw new RuntimeException(MessageFormat.format(message, new Object[]{typeDeclaration.getId()}));
      }
      return new ClientXPathMap(allXPaths);
   }

   public static XSDSchema loadExternalSchemaFromClasspath(String schemaLocation)
   {
      ResourceSet resourceSet = new ResourceSetImpl();
      
      Map<Object, Object> options = CollectionUtils.newMap();
      options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
       
      resourceSet.setURIConverter(new ClasspathUriConverter());
      Resource resource = resourceSet.createResource(URI.createURI(ClasspathUriConverter.CLASSPATH_SCHEME+":/"+schemaLocation)); //$NON-NLS-1$
      try
      {
         resource.load(options);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
      
      List<?> l = resource.getContents();
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
      AttributeUtil.setAttribute(accessPoint, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR);  //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
   }
   
   public static TypeDeclarationType getStructuredAccessPointTypeDeclaration(IExtensibleElement accessPoint)
   {
      return (TypeDeclarationType) AttributeUtil.getIdentifiable(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
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
      typeDeclaration.setName("<default>"); //$NON-NLS-1$
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
