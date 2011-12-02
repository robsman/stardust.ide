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
package org.eclipse.stardust.modeling.integration.webservices;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.xml.namespace.QName;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.QNameUtil;

import ag.carnot.base.StringUtils;
import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.bpm.rt.data.structured.TypedXPath;
import ag.carnot.bpm.rt.data.structured.spi.StructDataTransformerKey;
import ag.carnot.workflow.spi.providers.applications.ws.WSConstants;

public final class JaxWSUtil
{
   private static final String EMPTY_STRING = ""; //$NON-NLS-1$

   // to prevent instantiation
   private JaxWSUtil()
   {}

   public static void createAccessPoint(Map<String , AccessPointType> rawAccessPoints,
         ApplicationType application, String id, String name, DirectionType direction,
         DataTypeType type, String className)
   {
      AccessPointType ap = null;
      if (rawAccessPoints != null)
      {
         ap = rawAccessPoints.get(id);
      }
      if (ap == null)
      {
         ap = AccessPointUtil.createAccessPoint(id, name, direction, type);
         ap.setElementOid(ModelUtils.getElementOid(ap, (ModelType) application.eContainer()));
         application.getAccessPoint().add(ap);
      }
      else
      {
         rawAccessPoints.remove(id);
         ap.setName(name);
         ap.setType(type);
         ap.setDescription(null);
         ap.getAttribute().clear();
      }
      if (className != null)
      {
         AttributeUtil.setAttribute(ap, CarnotConstants.CLASS_NAME_ATT, className);
      }
      if (DirectionType.IN_LITERAL.equals(direction))
      {
         AttributeUtil.setAttribute(ap, CarnotConstants.BROWSABLE_ATT, "boolean", //$NON-NLS-1$
               Boolean.TRUE.toString());
      }
   }

   public static void createAccessPoint(ApplicationType application, Part part,
         String bindingStyle, DirectionType direction, DataTypeType serializable,
         DataTypeType plainXML, Map<String , AccessPointType> rawAccessPoints,
         JaxWSOutlineSynchronizer synchronizer)
   {
      String className = EMPTY_STRING;
   
      if (!StringUtils.isEmpty(bindingStyle) && !"message".equals(bindingStyle)) //$NON-NLS-1$
      {
         if (synchronizer != null)
         {
            className = synchronizer.getMapping(part);
         }
         if (className.length() == 0)
         {
            className = JaxWSResource.getDefaultMappedClass(JaxWSResource.getType(part));
            // className must not be null
            if (className == null)
            {
               className = EMPTY_STRING;
            }
         }
      }
   
      DataTypeType type = className.length() == 0 ? plainXML : serializable;
      String name = part.getName();
      
      createAccessPoint(rawAccessPoints, application, name, name, direction,
            type, className);
      
      createStructAccessPoint(rawAccessPoints, application, name+WSConstants.STRUCT_POSTFIX, direction, part);
   }

   public static TypeDeclarationType findMatchingTypeDeclaration(ApplicationType application, Part part)
   {
      // try to find corresponsing type for the part
      TypeDeclarationsType allTypeDeclarations = ModelUtils.findContainingModel(application).getTypeDeclarations();
      QName qname = part.getElementName();
      if (qname == null)
      {
         qname = part.getTypeName();
         if (qname == null)
         {
            return null;
         }
      }
      TypeDeclarationType typeDeclaration = allTypeDeclarations.getTypeDeclaration(qname.getLocalPart());
      if (typeDeclaration == null)
      {
         return null;
      }
      
      TypedXPath rootXPath = StructuredTypeUtils.getXPathMap(typeDeclaration).getRootXPath();
      if (!QNameUtil.toString(rootXPath.getXsdElementNs(), rootXPath.getXsdElementName())
            .equals(qname.toString()))
      {
         return null;
      }

      return typeDeclaration;
   }
   
   private static void createStructAccessPoint(
         Map<String, AccessPointType> rawAccessPoints, ApplicationType application,
         String id, DirectionType direction, Part part)
   {
      TypeDeclarationType typeDeclaration = findMatchingTypeDeclaration(application, part);
      
      if (typeDeclaration == null)
      {
         // not found or too many found, this is handled by WebServicePropertyPage.validateOperations()
         return;
      }
      
      AccessPointType ap = null;
      String name = id+" ("+typeDeclaration.getName()+")";
      DataTypeType dataType = ModelUtils.getDataType(application, StructuredDataConstants.STRUCTURED_DATA);
      if (rawAccessPoints != null)
      {
         ap = rawAccessPoints.get(id);
      }
      
      String transformationType = null;
      if (direction.equals(DirectionType.IN_LITERAL) || direction.equals(DirectionType.INOUT_LITERAL))
      {
         // force IN/INOUT structured data values to be transformed to DOM
         transformationType = StructDataTransformerKey.DOM;
      }
      
      if (ap == null)
      {
         ap = AccessPointUtil.createAccessPoint(id, name, direction,
               dataType);
         ap.setElementOid(ModelUtils.getElementOid(ap, (ModelType) application.eContainer()));
         StructuredTypeUtils.setStructuredAccessPointAttributes(ap, typeDeclaration, transformationType);
         List<AccessPointType> accessPoints = application.getAccessPoint();
         accessPoints.add(ap);
      }
      else
      {
         rawAccessPoints.remove(id);
         ap.setName(name);
         ap.setType(dataType);
         ap.setDescription(null);
         ap.getAttribute().clear();
         StructuredTypeUtils.setStructuredAccessPointAttributes(ap, typeDeclaration, transformationType);
      }
   }

   public static void createAccessPoints(ApplicationType application, Message message,
         String bindingStyle, DirectionType direction, DataTypeType serializable,
         DataTypeType plainXML, Map<String , AccessPointType> rawAccessPoints,
         JaxWSOutlineSynchronizer synchronizer)
   {
      @SuppressWarnings("unchecked")
      Iterator<Part> i = message.getOrderedParts(null).iterator();
      while (i.hasNext())
      {
         Part part = i.next();
         createAccessPoint(application, part, bindingStyle, direction,
               serializable, plainXML, rawAccessPoints, synchronizer);
      }
   }
}
