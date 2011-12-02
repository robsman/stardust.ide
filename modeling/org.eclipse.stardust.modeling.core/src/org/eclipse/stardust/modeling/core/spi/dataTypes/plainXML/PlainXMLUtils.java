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
package org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDParser;
import org.eclipse.xsd.util.XSDResourceImpl;

import ag.carnot.base.StringUtils;
import ag.carnot.bpm.rt.data.structured.ClientXPathMap;
import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.emfxsd.XPathFinder;
import ag.carnot.utils.xml.XmlUtils;
import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author rsauer
 * @version $Revision$
 */
public class PlainXMLUtils
{   
   public static IXPathMap getXPathMap (DataType data)
   {
      String elementName = AttributeUtil.getAttributeValue(data,
            PredefinedConstants.PLAINXML_TYPE_ID_ATT);
            
      XSDSchema schema = getXMLDataTypeSchema(data);
            
      if(schema != null)
      {         
         Set /*<TypedXPath>*/ allXPaths = null;
         if(!StringUtils.isEmpty(elementName))
         {
            allXPaths = XPathFinder.findAllXPaths(schema, elementName, false);
            return new ClientXPathMap(allXPaths);                                    
         }
         else
         {
/*            
            Set xPaths = null;
            List xsdTypeDefinitions = schema.getTypeDefinitions();
            for(int i = 0; i < xsdTypeDefinitions.size(); i++)
            {
               XSDTypeDefinition xsdTypeDefinition = (XSDTypeDefinition) xsdTypeDefinitions.get(i);               
               elementName = xsdTypeDefinition.getName();
               
               if(allXPaths == null)
               {
                  allXPaths = XPathFinder.findAllXPaths(schema, elementName);                  
               }
               else
               {
                  xPaths = XPathFinder.findAllXPaths(schema, elementName);                  
               }
               if(xPaths != null && !xPaths.isEmpty())
               {
                  allXPaths.addAll(xPaths);
               }               
            }
            
            List xsdElementDeclarations = schema.getElementDeclarations();
            for(int i = 0; i < xsdElementDeclarations.size(); i++)
            {
               XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration) xsdElementDeclarations.get(i);
               elementName = xsdElementDeclaration.getName();
               xPaths = XPathFinder.findAllXPaths(schema, elementName);
               
               if(allXPaths == null)
               {
                  allXPaths = XPathFinder.findAllXPaths(schema, elementName);                  
               }
               else
               {
                  xPaths = XPathFinder.findAllXPaths(schema, elementName);                  
               }
               if(xPaths != null && !xPaths.isEmpty())
               {
                  allXPaths.addAll(xPaths);
               }               
            }
            if(allXPaths != null)
            {
               return new ClientXPathMap(allXPaths);               
            }
*/            
         }
      }
      return null;
   }
      
   public static XSDSchema getXMLDataTypeSchema(DataType data)
   {
      String schemaType = AttributeUtil.getAttributeValue(data,
            PredefinedConstants.PLAINXML_SCHEMA_TYPE_ATT);
      String schemaUrl = AttributeUtil.getAttributeValue(data,
            PredefinedConstants.PLAINXML_SCHEMA_URL_ATT);
      if(schemaUrl != null)
      {
         schemaUrl.trim();
      }
      if(schemaType == null)
      {
         return null;
      }

      if(schemaType.equals(PredefinedConstants.PLAINXML_SCHEMA_TYPE_NONE)
            || StringUtils.isEmpty(schemaUrl))
      {
         return null;
      }
            
      // if starts with '/' (see docu if local files are allowed here)      
      String resolvedUri = XmlUtils.resolveResourceUri(schemaUrl);
      if(schemaType.equals(PredefinedConstants.PLAINXML_SCHEMA_TYPE_XSD))
      {
         try
         {
            XSDParser parser = new XSDParser();
            parser.parse(resolvedUri);
            XSDSchema schema = parser.getSchema();
            return schema;
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      if(schemaType.equals(PredefinedConstants.PLAINXML_SCHEMA_TYPE_WSDL))
      {                  
         HashMap options = new HashMap();
         //options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
          
         URI uri = !resolvedUri.toLowerCase().startsWith("http://")  //$NON-NLS-1$
               ? URI.createPlatformResourceURI(resolvedUri, true)
               : URI.createURI(resolvedUri);
         XSDResourceImpl resource = new XSDResourceImpl(uri);
         try
         {
            resource.load(options);
            List l = resource.getContents();
            for (int i = 0; i < l.size(); i++)
            {
               EObject eObject = (EObject) l.get(i);
               if (eObject instanceof XSDSchema)
               {
                  return (XSDSchema) eObject;
               }
            }
         }
         catch (IOException e1)
         {
            // e1.printStackTrace();
         }         
      }      
      return null;
   }   
}