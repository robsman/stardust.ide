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
package org.eclipse.stardust.modeling.transformation.messaging.modeling;

import java.util.Calendar;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.impl.AccessPointTypeImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.integration.dms.data.DmsTypeUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MTAClassLoader;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class MessageTransformationUtils
{
   private IJavaProject javaProject;
   private IModelElement modelElement;
   private MTAClassLoader mtaClassLoader;

   public MessageTransformationUtils(IProject project, IModelElement modelElement,
         ModelType modelType)
   {
      javaProject = JavaCore.create(project);
      this.modelElement = modelElement;
      this.mtaClassLoader = new MTAClassLoader(javaProject);
   }

   public MessageTransformationUtils()
   {
   }

   public boolean classExists(String className)
   {	    	  	  
      return ((Class< ? >) mtaClassLoader.findClass(className) != null);	  
   }

   public StructAccessPointType createStructAccessPoint(String id, String name,
         DirectionType direction, DataTypeType type, TypedXPath typedXPath, IXPathMap xPathMap)
   {
      StructAccessPointType result = new StructAccessPointType(typedXPath, xPathMap);
      result.setId(id);
      result.setName(name);
      result.setType(type);
      result.setDirection(direction);
      return result;
   }

   public AccessPointType createPrimitiveAccessPoint(IIdentifiableModelElement declaringType,
         String messageName, DirectionType direction)
   {
      Class<?> clazz = null; 
      String type = AttributeUtil.getAttributeValue(declaringType, PredefinedConstants.TYPE_ATT);
      if (declaringType instanceof DataTypeType)
      {
         type = messageName;
      }
      clazz = getClassForType(type);    
      AccessPointType accessPoint = DmsTypeUtils.createPrimitiveAccessPointType(messageName, clazz, direction, modelElement); 
      AttributeUtil.setAttribute(accessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", type); //$NON-NLS-1$
      return accessPoint;
   }

   public AccessPointType createSerializableAccessPoint(TypeInfo declaringType,
         String messageName, DirectionType direction)
   {
      try
      {	    	  	    	  
         AccessPointType apt = DmsTypeUtils.createSerializableAccessPointType(messageName, declaringType.getFullName(), direction, modelElement);	  	    	  
         return apt;               
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      return null;
   }   

   public AccessPointType createStructAccessPoint(EObject declaringType,
         String messageName, DirectionType direction, boolean includePathInName)
   {
      TypeDeclarationType typeDeclaration = declaringType instanceof TypeDeclarationType
            ? (TypeDeclarationType) declaringType
            : StructuredTypeUtils.getStructuredAccessPointTypeDeclaration((StructAccessPointType) declaringType);
      IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(typeDeclaration);
      DataTypeType structDataType = ModelUtils.getDataType(modelElement, StructuredDataConstants.STRUCTURED_DATA);
      
      AccessPointType accessPoint = createStructAccessPoint(messageName, messageName,
            direction, structDataType, xPathMap.getRootXPath(), xPathMap);

      // (fh) Workaround to ensure that the access point has a parent
      // before setting the reference to the type declaration.
      ((AccessPointTypeImpl) accessPoint).setFakeContainer((IAccessPointOwner) modelElement);
      StructuredTypeUtils.setStructuredAccessPointAttributes(accessPoint, typeDeclaration);
      ((AccessPointTypeImpl) accessPoint).setFakeContainer(null);
      String path = AttributeUtil.getAttribute(accessPoint, "carnot:engine:dataType").getValue(); //$NON-NLS-1$
      accessPoint.setName(includePathInName ? messageName + " (" //$NON-NLS-1$
            + parseName(path) + ")" : messageName); //$NON-NLS-1$;
      return accessPoint;
   }   

   private String parseName(String path)
   {
      if (path.startsWith("typeDeclaration:")) //$NON-NLS-1$ 
      { 
         path = path.replaceAll("typeDeclaration:", ""); //$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("{", "");//$NON-NLS-1$ //$NON-NLS-2$
         path = path.replace("}", " / "); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return path;
   }

   public Class<?> getClassForType(String type)
   {       
      if (type.equalsIgnoreCase("long")) { //$NON-NLS-1$
         return Long.class;
      }
      if (type.equalsIgnoreCase("long")) { //$NON-NLS-1$
         return Short.class;
      }
      if (type.equalsIgnoreCase("float")) { //$NON-NLS-1$
         return Float.class;
      }
      if (type.equalsIgnoreCase("double")) { //$NON-NLS-1$
         return Double.class;
      }   
      if (type.equalsIgnoreCase("byte")) { //$NON-NLS-1$
         return Byte.class;
      }
      if (type.equalsIgnoreCase("Calendar")) { //$NON-NLS-1$
         return Calendar.class;
      }       
      if (type.equalsIgnoreCase("Timestamp")) { //$NON-NLS-1$
         return Calendar.class;
      }    
      if (type.equalsIgnoreCase("int")) { //$NON-NLS-1$
         return Integer.class;
      }
      if (type.equalsIgnoreCase("java.lang.Integer")) { //$NON-NLS-1$
         return Integer.class;
      }
      if (type.equals("String")) { //$NON-NLS-1$
         return String.class;
      }
      return String.class;
   }


   public boolean isPrimitive(DataType data)
   {      
      if (data.getType() != null && data.getType().getId().equalsIgnoreCase("primitive"))  //$NON-NLS-1$
      {
         return true;
      }
      return false;
   }

   public boolean isSerializable(DataType data)
   {
      if (data.getType() != null && data.getType().getId().equalsIgnoreCase("serializable"))  //$NON-NLS-1$
      {
         return true;
      }
      return false;
   }   

   public AccessPointType extractStructAccessPoints(DataTypeType structuredDataType,
         AccessPointType accessPoint)
   {
      TypeDeclarationType typeDeclaration = StructuredTypeUtils.getStructuredAccessPointTypeDeclaration(
            (AccessPointType) accessPoint);
      if (typeDeclaration != null)
      {
         IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(typeDeclaration);
         StructAccessPointType structAP = createStructAccessPoint(accessPoint.getId(),
               accessPoint.getName(), accessPoint.getDirection(),
               structuredDataType, xPathMap.getRootXPath(), xPathMap);

         // (fh) Workaround to ensure that the access point has a parent
         // before setting the reference to the type declaration.
         ((AccessPointTypeImpl) structAP).setFakeContainer((IAccessPointOwner) modelElement);
         StructuredTypeUtils.setStructuredAccessPointAttributes(structAP, typeDeclaration);
         ((AccessPointTypeImpl) structAP).setFakeContainer(null);
         
         return structAP;
      }
      return null;
   }

   public  AccessPointType extractPrimitiveAccessPoints(DataTypeType structuredDataType,
         AccessPointType accessPoint)
   {
      AccessPointType primAccessPoint = DmsTypeUtils.createPrimitiveAccessPointType(
            accessPoint.getId(), String.class,
            accessPoint.getDirection(), accessPoint);
      AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", //$NON-NLS-1$
            AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue());
      return primAccessPoint;
   }

   public AccessPointType extractSerializableAccessPoints(DataTypeType structuredDataType,
         AccessPointType accessPoint)
   {
      String className = AttributeUtil.getAttributeValue(accessPoint, "carnot:engine:className");	 //$NON-NLS-1$
      AccessPointType primAccessPoint = DmsTypeUtils.createSerializableAccessPointType(
            accessPoint.getId(), className,
            accessPoint.getDirection(), accessPoint);
      //AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue());
      return primAccessPoint;
   }

   public AccessPointType createSerializableAccessPoints(IModelElement modelElement, String className, String id)
   {	      
      AccessPointType serializableAccessPoint = DmsTypeUtils.createSerializableAccessPointType(
            id, className,
            DirectionType.OUT_LITERAL, modelElement);	            
      return serializableAccessPoint;	                   
   }


   public boolean isPrimitive(AccessPointType apt)
   {
      return apt != null && (!(apt instanceof StructAccessPointType))
            && apt.getType() != null && apt.getType().getId() != null && apt.getType().getId().startsWith("prim"); //$NON-NLS-1$
   }
}
