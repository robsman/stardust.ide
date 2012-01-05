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

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDResourceImpl.SchemaLocator;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

public class SchemaLocatorAdapter extends SchemaLocator
{
   public XSDSchema locateSchema(XSDSchema xsdSchema, String namespaceURI,
         String rawSchemaLocationURI, String resolvedSchemaLocationURI)
   {
      if (xsdSchema.eContainer() instanceof SchemaTypeType
            && !StringUtils.isEmpty(rawSchemaLocationURI) 
            && rawSchemaLocationURI.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
      {
         SchemaTypeType schemaType = (SchemaTypeType) xsdSchema.eContainer();
         TypeDeclarationType typeDeclaration = (TypeDeclarationType) schemaType.eContainer();
         TypeDeclarationsType declarations = (TypeDeclarationsType) typeDeclaration.eContainer();
         String typeId = rawSchemaLocationURI.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
         TypeDeclarationType sourceType = declarations.getTypeDeclaration(typeId);
         if(sourceType != null)
         {
            return sourceType.getSchema();            
         }
      }
      try
      {
         return super.locateSchema(xsdSchema, namespaceURI, rawSchemaLocationURI, resolvedSchemaLocationURI);
      }
      catch (Exception ex)
      {
         return null;
      }
   }
}