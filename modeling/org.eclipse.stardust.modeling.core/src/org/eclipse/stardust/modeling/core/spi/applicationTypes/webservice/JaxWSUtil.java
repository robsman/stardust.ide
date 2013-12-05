/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.util.Iterator;
import java.util.Map;

import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.xml.namespace.QName;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.engine.core.struct.spi.StructDataTransformerKey;
import org.eclipse.stardust.engine.extensions.jaxws.app.WSConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.*;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;

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
      // try to find corresponding type for the part
      QName qname = part.getElementName();
      if (qname == null)
      {
         qname = part.getTypeName();
         if (qname == null)
         {
            return null;
         }
      }

      ModelType model = ModelUtils.findContainingModel(application);
      TypeDeclarationType type = findMatchingTypeDeclaration(qname, model);
      if (type == null)
      {
         ExternalPackages packages = model.getExternalPackages();
         if (packages != null)
         {
            for (ExternalPackage pkg : packages.getExternalPackage())
            {
               model = ModelUtils.getExternalModel(pkg);
               if (model != null)
               {
                  type = findMatchingTypeDeclaration(qname, model);
                  if (type != null)
                  {
                     break;
                  }
               }
            }
         }
      }
      return type;
   }

   public static TypeDeclarationType findMatchingTypeDeclaration(QName qname, ModelType model)
   {
      TypeDeclarationsType allTypeDeclarations = model.getTypeDeclarations();
      TypeDeclarationType typeDeclaration = allTypeDeclarations.getTypeDeclaration(qname.getLocalPart());
      if (typeDeclaration == null)
      {
         return null;
      }

      TypedXPath rootXPath = StructuredTypeUtils.getXPathMap(typeDeclaration).getRootXPath();
      if (!QNameUtil.toString(rootXPath.getXsdElementNs(), rootXPath.getXsdElementName()).equals(qname.toString()))
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
      String name = id+" ("+typeDeclaration.getName()+")"; //$NON-NLS-1$ //$NON-NLS-2$
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
         application.getAccessPoint().add(ap);
         StructuredTypeUtils.setStructuredAccessPointAttributes(ap, typeDeclaration, transformationType);
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
