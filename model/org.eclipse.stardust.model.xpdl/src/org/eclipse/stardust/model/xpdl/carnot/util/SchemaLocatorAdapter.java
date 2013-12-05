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

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDResourceImpl.SchemaLocator;

public class SchemaLocatorAdapter extends SchemaLocator
{
   public XSDSchema locateSchema(XSDSchema xsdSchema, String namespaceURI,
         String rawSchemaLocationURI, String resolvedSchemaLocationURI)
   {
      if (xsdSchema.eContainer() instanceof SchemaTypeType
            && !StringUtils.isEmpty(rawSchemaLocationURI) 
            && rawSchemaLocationURI.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
      {
         ModelType model = ModelUtils.findContainingModel(xsdSchema);
         if (model != null)
         {
            QName qname = QName.valueOf(rawSchemaLocationURI.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length()));
            String modelId = qname.getNamespaceURI();
            String typeId = qname.getLocalPart();
            if (XMLConstants.NULL_NS_URI != modelId && !model.getId().equals(modelId))
            {
               ExternalPackages packages = model.getExternalPackages();
               if (packages != null)
               {
                  ExternalPackage pack = packages.getExternalPackage(modelId);
                  if (pack != null)
                  {
                     ModelType extModel = ModelUtils.getExternalModel(pack);
                     if (extModel != null)
                     {
                        model = extModel;
                     }
                  }
               }
            }
            return findSchema(model, typeId);
         }
      }
      try
      {
         return super.locateSchema(xsdSchema, namespaceURI, rawSchemaLocationURI, resolvedSchemaLocationURI);
      }
      catch (Exception ex)
      {
         // (fh) ignore ? or log ?
      }
      return null;
   }

   private XSDSchema findSchema(ModelType model, String typeId)
   {
      TypeDeclarationsType declarations = model.getTypeDeclarations();
      if (declarations != null)
      {
         TypeDeclarationType sourceType = declarations.getTypeDeclaration(typeId);
         if (sourceType != null)
         {
            return sourceType.getSchema();            
         }
      }
      return null;
   }
}