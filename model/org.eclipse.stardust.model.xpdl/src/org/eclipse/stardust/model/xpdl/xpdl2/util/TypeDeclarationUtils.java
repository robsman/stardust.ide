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
package org.eclipse.stardust.model.xpdl.xpdl2.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.xsd.*;
import org.eclipse.xsd.impl.XSDImportImpl;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;

public class TypeDeclarationUtils
{
   public static final int XPDL_TYPE = 0;

   public static final int SIMPLE_TYPE = 1;

   public static final int COMPLEX_TYPE = 2;

   public static ThreadLocal<URIConverter> defaultURIConverter = new ThreadLocal<URIConverter>();

   public static void updateTypeDefinition(TypeDeclarationType declaration, String newId, String previousId)
   {
      ModelType model = ModelUtils.findContainingModel(declaration);
      XSDSchema clone = declaration.getSchema();
      Map<String, String> prefixes = clone.getQNamePrefixToNamespaceMap();

      List<String> addPrefixes = new ArrayList<String>();
      Set<Map.Entry<String, String>> set = new HashSet<Map.Entry<String, String>>(prefixes.entrySet());

      for (Map.Entry<String, String> entry : set)
      {
         if (!entry.getKey().equals("xsd")) //$NON-NLS-1$
         {
            // elements that needs to be set with the new TypeDeclarationType
            Set<XSDElementDeclaration> elements = CollectionUtils.newSet();

            String value = entry.getValue();
            int idx = value.lastIndexOf("/") + 1; //$NON-NLS-1$
            // TypeDeclarationType
            String elementName = value.substring(idx, value.length());

            // we could use the key for the check

            // references to old TypeDeclarationType
            if (elementName.equals(previousId))
            {
               // remove old namespace
               TypeDeclarationUtils.removeNameSpace(clone, elementName, model.getId());

               String prefix = TypeDeclarationUtils.computePrefix(newId, clone.getQNamePrefixToNamespaceMap().keySet());
               if (!addPrefixes.contains(prefix))
               {
                  String nameSpace = computeTargetNamespace(model, newId);
                  addPrefixes.add(prefix);
                  clone.getQNamePrefixToNamespaceMap().put(prefix, nameSpace);
               }

               // search for elements
               TypeDeclarationUtils.findElementsForType(declaration, elements, previousId);
               for (XSDElementDeclaration elementDeclaration : elements)
               {
                  elementDeclaration.setTypeDefinition(null);
               }

               XSDTypeDefinition definition = TypeDeclarationUtils.getTypeDefinition(model.getTypeDeclarations(), newId);
               if (definition != null)
               {
                  for (XSDElementDeclaration elementDeclaration : elements)
                  {
                     elementDeclaration.setTypeDefinition(definition);
                  }
               }
            }
         }
      }
      clone.updateElement(true);
   }

   public static boolean fixImport(TypeDeclarationType typeDeclaration, String newId, String previousId)
   {
      boolean match = false;

      XpdlTypeType type = typeDeclaration.getDataType();
      if (type instanceof SchemaTypeType)
      {
         XSDSchema schema = ((SchemaTypeType) type).getSchema();

         List<XSDImport> xsdImports = getImports(schema);
         if (xsdImports != null)
         {
            for (XSDImport xsdImport : xsdImports)
            {
               if (xsdImport.getSchemaLocation().startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
               {
                  int idx = xsdImport.getSchemaLocation().lastIndexOf(StructuredDataConstants.URN_INTERNAL_PREFIX)
                        + StructuredDataConstants.URN_INTERNAL_PREFIX.length();
                  String elementName = xsdImport.getSchemaLocation().substring(idx,
                        xsdImport.getSchemaLocation().length());

                  if (elementName.equals(previousId))
                  {
                     match = true;
                     xsdImport.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + newId);
                     xsdImport.setNamespace(computeTargetNamespace(ModelUtils.findContainingModel(typeDeclaration), newId));
                  }
               }
            }
         }
      }
      return match;
   }

   public static String computeTargetNamespace(ModelType model, String id)
   {
      return computeTargetNamespace(model.getId(), id);
   }

   public static String computeTargetNamespace(String modelId, String id)
   {
      return "http://www.infinity.com/bpm/model/" + encode(modelId) + "/" + encode(id); //$NON-NLS-1$ //$NON-NLS-2$
   }

   private static String encode(String id)
   {
      try
      {
         id = new java.net.URI(id).toASCIIString();
      }
      catch (URISyntaxException e)
      {
      }
      return id;
   }

   public static List<TypeDeclarationType> filterTypeDeclarations(List<TypeDeclarationType> declarations, int type)
   {
      List<TypeDeclarationType> result = new ArrayList<TypeDeclarationType>();
      for (TypeDeclarationType declaration : declarations)
      {
         if (type == getType(declaration))
         {
            result.add(declaration);
         }
      }
      return result;
   }

   public static XSDNamedComponent findElementOrTypeDeclaration(TypeDeclarationType declaration)
   {
      return findElementOrTypeDeclaration(declaration, declaration.getId());
   }

   public static XSDNamedComponent findElementOrTypeDeclaration(TypeDeclarationType declaration, String id)
   {
      XpdlTypeType type = declaration.getDataType();
      XSDSchema schema = declaration.getSchema();
      if (schema != null)
      {
         if (type instanceof SchemaTypeType)
         {
            return findElementOrTypeDeclaration(schema, id, schema.getTargetNamespace(), true);
         }
         if (type instanceof ExternalReferenceType)
         {
            ExternalReferenceType reference = (ExternalReferenceType) type;
            return findElementOrTypeDeclaration(schema, QNameUtil.parseLocalName(reference.getXref()),
                  QNameUtil.parseNamespaceURI(reference.getXref())/* reference.getNamespace() */, false);
         }
      }
      return null;
   }

   public static XSDNamedComponent findElementOrTypeDeclaration(XSDSchema schema, String localName, String namespace,
         boolean returnFirstIfNoMatch)
   {
      if (schema == null)
      {
         return null;
      }
      XSDNamedComponent decl = null;
      List<XSDElementDeclaration> elements = schema.getElementDeclarations();
      List<XSDTypeDefinition> types = schema.getTypeDefinitions();
      if (localName != null)
      {
         // scan all elements to find the one with the name matching the id.
         for (XSDElementDeclaration element : elements)
         {
            if (localName.equals(element.getName()) && CompareHelper.areEqual(namespace, element.getTargetNamespace()))
            {
               decl = element;
               break;
            }
         }
         if (decl == null)
         {
            // scan all types now
            for (XSDTypeDefinition type : types)
            {
               if (localName.equals(type.getName()) && CompareHelper.areEqual(namespace, type.getTargetNamespace()))
               {
                  decl = type;
                  break;
               }
            }
         }
      }
      if (decl == null && returnFirstIfNoMatch)
      {
         if (elements.size() == 1)
         {
            decl = elements.get(0);
         }
         else if (elements.isEmpty() && types.size() == 1)
         {
            decl = types.get(0);
         }
      }
      return decl;
   }

   public static TypeDeclarationType findTypeDeclarationByLocation(TypeDeclarationsType declarations, String location)
   {
      if (declarations == null || location == null)
      {
         return null;
      }
      if (location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
      {
         String typeId = location.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
         return (TypeDeclarationType) ModelUtils.findElementById(declarations.getTypeDeclaration(), typeId);
      }
      return null;
   }

   public static XSDSchema getSchema(String location, String namespaceURI) throws IOException
   {
      HashMap<Object, Object> options = new HashMap<Object, Object>();
      options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

      URI uri = null;

      if (Platform.isRunning())
      {
         uri = !location.toLowerCase().startsWith("http://") //$NON-NLS-1$
               ? URI.createPlatformResourceURI(location, true)
               : URI.createURI(location);
      }
      else
      {
         uri = URI.createURI(location);
      }

      ResourceSet resourceSet = XSDSchemaImpl.createResourceSet();
      // make sure we can load schemas in WSDL documents.
      Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
      extensionToFactoryMap.put("wsdl", extensionToFactoryMap.get("xsd"));
      URIConverter converter = defaultURIConverter.get();
      if (converter != null)
      {
         resourceSet.setURIConverter(converter);
      }
      Resource resource = resourceSet.createResource(URI.createURI("*.xsd"));
      resource.setURI(uri);
      resource.load(options);

      boolean hasSchema = false;
      for (Object eObject : resource.getContents())
      {
         if (eObject instanceof XSDSchema)
         {
            hasSchema = true;
            XSDSchema schema = (XSDSchema) eObject;
            if (CompareHelper.areEqual(namespaceURI, schema.getTargetNamespace()))
            {
               resolveImports(schema);
               return schema;
            }
         }
      }
      // no schema matching the namespaceURI found, so try a second round by searching
      // through imports.
      // this is indirect resolving, so it will return the first schema that has an import
      // for the namespaceURI
      if (hasSchema)
      {
         for (Object eObject : resource.getContents())
         {
            if (eObject instanceof XSDSchema)
            {
               XSDSchema schema = (XSDSchema) eObject;
               for (XSDSchemaContent item : schema.getContents())
               {
                  if (item instanceof XSDImportImpl)
                  {
                     XSDImportImpl directive = (XSDImportImpl) item;
                     XSDSchema ref = directive.importSchema();
                     if (ref != null && CompareHelper.areEqual(namespaceURI, ref.getTargetNamespace()))
                     {
                        resolveImports(schema);
                        return schema;
                     }
                  }
               }
            }
         }
      }
      return null;
   }

   public static boolean isInternalSchema(TypeDeclarationType declaration)
   {
      XpdlTypeType type = declaration.getDataType();
      if (type instanceof SchemaTypeType)
      {
         return true;
      }
      if (type instanceof ExternalReferenceType)
      {
         String externalUrl = ((ExternalReferenceType) type).getLocation();
         return externalUrl != null && externalUrl.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX);
      }
      return false;
   }

   public static XSDSimpleTypeDefinition getSimpleType(TypeDeclarationType declaration)
   {
      XSDNamedComponent component = findElementOrTypeDeclaration(declaration);
      if (component instanceof XSDElementDeclaration)
      {
         component = ((XSDElementDeclaration) component).getTypeDefinition();
      }
      return component instanceof XSDSimpleTypeDefinition ? (XSDSimpleTypeDefinition) component : null;
   }

   public static XSDComplexTypeDefinition getComplexType(TypeDeclarationType declaration)
   {
      XSDNamedComponent component = findElementOrTypeDeclaration(declaration);
      if (component instanceof XSDElementDeclaration)
      {
         component = ((XSDElementDeclaration) component).getTypeDefinition();
      }
      return component instanceof XSDComplexTypeDefinition ? (XSDComplexTypeDefinition) component : null;
   }

   public static int getType(TypeDeclarationType declaration)
   {
      XSDNamedComponent component = findElementOrTypeDeclaration(declaration);
      if (component instanceof XSDElementDeclaration)
      {
         component = ((XSDElementDeclaration) component).getTypeDefinition();
      }
      if (component instanceof XSDSimpleTypeDefinition)
      {
         return SIMPLE_TYPE;
      }
      if (component instanceof XSDComplexTypeDefinition)
      {
         return COMPLEX_TYPE;
      }
      return XPDL_TYPE;
   }

   public static String computePrefix(String name, Set<String> usedPrefixes)
   {
      String prefix;
      if (name != null)
      {
         name = name.trim();
      }
      if (name == null || name.length() == 0)
      {
         prefix = "p"; //$NON-NLS-1$
      }
      else
      {
         // prefix is first 3 character + ending digits if any.
         int pos = name.length();
         while (pos > 0 && Character.isDigit(name.charAt(pos - 1)))
         {
            pos--;
         }
         int nch = 3;
         if (nch > pos)
         {
            nch = pos;
         }
         prefix = name.substring(0, nch) + name.substring(pos);
      }
      if (usedPrefixes.contains(prefix))
      {
         int counter = 1;
         while (usedPrefixes.contains(prefix + '_' + counter))
         {
            counter++;
         }
         prefix = prefix + '_' + counter;
      }
      return prefix.toLowerCase();
   }

   public static void updateImports(XSDSchema xsdSchema, String oldTargetNamespace, String oldId, String id)
   {
      for (XSDSchemaDirective directive : xsdSchema.getReferencingDirectives())
      {
         directive.setSchemaLocation(xsdSchema.getSchemaLocation());
         if (directive instanceof XSDImport)
         {
            ((XSDImport) directive).setNamespace(xsdSchema.getTargetNamespace());
         }
         XSDSchema referencingSchema = directive.getSchema();
         List<String> toRemove = CollectionUtils.newList();
         Map<String, String> prefixes = referencingSchema.getQNamePrefixToNamespaceMap();
         List<XSDImport> imports = TypeDeclarationUtils.getImports(referencingSchema);
         if (imports != null && !imports.isEmpty())
         {
            for (XSDImport xsdImport : imports)
            {
               if (xsdImport.getSchemaLocation().endsWith(":" + oldId)) //$NON-NLS-1$
               {
                  xsdImport.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + id);
               }
            }
         }
         // referencingSchema.getContents()
         String newPrefix = computePrefix(id, prefixes.keySet());
         prefixes.put(newPrefix, xsdSchema.getTargetNamespace());
         for (Map.Entry<String, String> entry : prefixes.entrySet())
         {
            if (!newPrefix.equals(entry.getKey()) && oldTargetNamespace.equals(entry.getValue()))
            {
               toRemove.add(entry.getKey());
            }
         }
         for (String prefix : toRemove)
         {
            prefixes.remove(prefix);
         }
      }
   }

   // we can have more than one XSDImport
   public static List<XSDImport> getImports(XSDSchema schema)
   {
      List<XSDImport> xsdImports = new ArrayList<XSDImport>();
      List<XSDSchemaContent> contents = schema.getContents();
      Iterator<XSDSchemaContent> it = contents.iterator();
      while (it.hasNext())
      {
         XSDSchemaContent content = (XSDSchemaContent) it.next();
         if (content instanceof XSDImport)
         {
            xsdImports.add((XSDImport) content);
         }
      }
      if (!xsdImports.isEmpty())
      {
         return xsdImports;
      }
      return null;
   }

   public static boolean hasImport(XSDSchema schema, TypeDeclarationType type)
   {
      for (XSDSchemaContent content : schema.getContents())
      {
         if (content instanceof XSDImport)
         {
            String location = ((XSDImport) content).getSchemaLocation();
            if (location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               String typeId = location.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
               if (typeId.equals(type.getId()))
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   public static XSDImport removeImport(XSDSchema schema, XSDSchema importedSchema)
   {
      XSDImport removeImport = null;
      for (XSDSchemaContent content : schema.getContents())
      {
         if (content instanceof XSDImport)
         {
            XSDImport xsdImport = (XSDImport) content;
            if (xsdImport.getResolvedSchema() == importedSchema
                  && xsdImport.getSchemaLocation().startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               removeImport = xsdImport;
               break;
            }
         }
      }
      if (removeImport != null)
      {
         schema.getContents().remove(removeImport);
      }

      return removeImport;
   }

   public static XSDTypeDefinition getTypeDefinition(TypeDeclarationsType declarations, String name)
   {
      TypeDeclarationType td = declarations.getTypeDeclaration(name);
      if (td != null)
      {
         XSDSchema schema = td.getSchema();
         if (schema != null)
         {
            for (XSDTypeDefinition definition : schema.getTypeDefinitions())
            {
               if (definition.getName().equals(name))
               {
                  return definition;
               }
            }
         }
      }
      return null;
   }

   public static void findElementsForType(TypeDeclarationType declaration, Set<XSDElementDeclaration> elements,
         String elementName)
   {
      XSDComplexTypeDefinition complexType = getComplexType(declaration);
      if (complexType != null)
      {
         visit(complexType, elements, elementName);
      }
   }

   public static void visit(XSDComplexTypeDefinition complexType, Set<XSDElementDeclaration> elements,
         String elementName)
   {
      XSDComplexTypeContent content = complexType.getContent();
      if (content instanceof XSDParticle)
      {
         visit((XSDParticle) content, elements, elementName);
      }
   }

   public static void visit(XSDParticle particle, Set<XSDElementDeclaration> elements, String elementName)
   {
      XSDParticleContent particleContent = particle.getContent();
      if (particleContent instanceof XSDModelGroupDefinition)
      {
         //
      }
      else if (particleContent instanceof XSDTerm)
      {
         visit((XSDTerm) particleContent, elements, elementName);
      }
   }

   public static void visit(XSDTerm term, Set<XSDElementDeclaration> elements, String elementName)
   {
      if (term instanceof XSDElementDeclaration)
      {
         visit((XSDElementDeclaration) term, elements, elementName);
      }
      else if (term instanceof XSDModelGroup)
      {
         visit((XSDModelGroup) term, elements, elementName);
      }
   }

   public static void visit(XSDModelGroup group, Set<XSDElementDeclaration> elements, String elementName)
   {
      for (XSDParticle xsdParticle : group.getContents())
      {
         visit(xsdParticle, elements, elementName);
      }
   }

   public static void visit(XSDElementDeclaration element, Set<XSDElementDeclaration> elements, String elementName)
   {
      XSDTypeDefinition type = element.getAnonymousTypeDefinition();
      if (type instanceof XSDComplexTypeDefinition)
      {
         visit((XSDComplexTypeDefinition) type, elements, elementName);
      }
      else if (type == null)
      {
         type = element.getType();
         if (type != null)
         {
            String qName = type.getQName();
            if (elementName.equals(qName))
            {
               elements.add(element);
            }
         }
      }
   }

   public static void resolveImports(XSDSchema schema)
   {
      List<XSDSchemaContent> contents = schema.getContents();
      for (XSDSchemaContent item : contents)
      {
         if (item instanceof XSDImportImpl)
         {
            // force schema resolving.
            // it's a noop if the schema is already resolved.
            ((XSDImportImpl) item).importSchema();
         }
      }
   }

   public static void removeNameSpace(XSDSchema schema, String oldDefName, String modelId)
   {
      String targetNameSpace = computeTargetNamespace(modelId, oldDefName);

      String prefix = getNamespacePrefix(schema, targetNameSpace);
      if (prefix != null)
      {
         schema.getQNamePrefixToNamespaceMap().remove(prefix);
      }
   }

   public static String getNamespacePrefix(XSDSchema schema, String targetNameSpace)
   {
      String prefix = null;
      for (Map.Entry<String, String> entry : schema.getQNamePrefixToNamespaceMap().entrySet())
      {
         if (entry.getValue().equals(targetNameSpace))
         {
            prefix = entry.getKey();
            break;
         }
      }
      return prefix;
   }

   public static void collectAllNamespaces(XSDSchema schema, Map<String, String> qNamePrefixToNamespaceMap)
   {
      List<XSDImport> imports = TypeDeclarationUtils.getImports(schema);
      if(imports != null)
      {
         for(XSDImport xsdImport : imports)
         {
            if (xsdImport.getSchemaLocation().startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               XSDSchema importetSchema = xsdImport.getResolvedSchema();
               if(importetSchema != null)
               {
                  String targetNamespace = importetSchema.getTargetNamespace();
                  String namespacePrefix = TypeDeclarationUtils.getNamespacePrefix(importetSchema, targetNamespace);

                  if(!qNamePrefixToNamespaceMap.containsValue(targetNamespace))
                  {
                     qNamePrefixToNamespaceMap.put(namespacePrefix, targetNamespace);
                     collectAllNamespaces(importetSchema, qNamePrefixToNamespaceMap);
                  }
               }
            }
         }
      }
   }
}