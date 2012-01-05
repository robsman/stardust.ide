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
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.integration.dms.data.DmsTypeUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MTAClassLoader;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

import ag.carnot.workflow.model.PredefinedConstants;


public class MessageTransformationUtils
{
   private IProject project;
   private IJavaProject javaProject;
   private IModelElement modelElement;
   private ModelType modelType;
   private MTAClassLoader mtaClassLoader;
   
	
	
   public MessageTransformationUtils(IProject project, IModelElement modelElement,
		ModelType modelType) {
	super();
	this.project = project;
	javaProject = JavaCore.create(project);
	this.modelElement = modelElement;
	this.modelType = modelType;
	this.mtaClassLoader = new MTAClassLoader(javaProject);
}

public MessageTransformationUtils() {
		super();
   }

   public boolean classExists(String className) {	    	  	  
	  Class clazz = mtaClassLoader.findClass(className);		  
	  return (clazz != null);	  
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
	      } catch (Throwable t) {
	         t.printStackTrace();
	      }
	      return null;
	   }   

   public AccessPointType createStructAccessPoint(EObject declaringType,
         String messageName, DirectionType direction)
   {
      TypeDeclarationType typeDeclaration = null;
      if (declaringType instanceof TypeDeclarationType) {      
         typeDeclaration = (TypeDeclarationType) declaringType;
      } else {
         typeDeclaration = ModelUtils.getTypeDeclaration(modelElement, ((AccessPointType)declaringType).getId());
      }
      AccessPointType accessPoint;
      IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(modelType,
                typeDeclaration.getId());

        accessPoint = this
                .createStructAccessPoint(messageName, messageName + " (" //$NON-NLS-1$
                        + typeDeclaration.getId() + ")", //$NON-NLS-1$
                        direction, ModelUtils.getDataType(
                              modelElement,
                                StructuredDataConstants.STRUCTURED_DATA),
                        xPathMap.getRootXPath(), xPathMap);
        StructuredTypeUtils.setStructuredAccessPointAttributes(accessPoint,
                typeDeclaration);
      return accessPoint;
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
      String declaredTypeId = AttributeUtil.getAttributeValue(
            accessPoint,
            StructuredDataConstants.TYPE_DECLARATION_ATT);
      TypeDeclarationType typeDeclaration = ModelUtils
            .getTypeDeclaration((IModelElement) modelElement,
                    declaredTypeId);
      if (typeDeclaration != null) {
        IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(
                modelType, typeDeclaration.getId());
        if (accessPoint.getDirection().equals(
                DirectionType.IN_LITERAL)) {
            StructAccessPointType structAP = this
                    .createStructAccessPoint(accessPoint.getId(),
                            accessPoint.getName(),
                            DirectionType.IN_LITERAL,
                            structuredDataType, xPathMap
                                    .getRootXPath(), xPathMap);
            AttributeUtil
                    .setReference(
                            structAP,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            StructuredTypeUtils.setStructuredAccessPointAttributes(structAP,
                    typeDeclaration);
            return structAP;
        } else {
            StructAccessPointType structAP = this
                    .createStructAccessPoint(accessPoint.getId(),
                            accessPoint.getName(),
                            DirectionType.OUT_LITERAL,
                            structuredDataType, xPathMap
                                    .getRootXPath(), xPathMap);
            AttributeUtil
                    .setReference(
                            structAP,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            StructuredTypeUtils.setStructuredAccessPointAttributes(structAP,
                    typeDeclaration);
            return structAP;
        }
      }
      return null;
   }
   
   public  AccessPointType extractPrimitiveAccessPoints(DataTypeType structuredDataType,
         AccessPointType accessPoint)
   {
        if (accessPoint.getDirection().equals(
                DirectionType.IN_LITERAL)) {
           AccessPointType primAccessPoint = DmsTypeUtils.createPrimitiveAccessPointType(
                 accessPoint.getId(), String.class,
                 DirectionType.IN_LITERAL, accessPoint);
            AttributeUtil
                    .setReference(
                          primAccessPoint,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue()); //$NON-NLS-1$
            return primAccessPoint;
        } else {
           AccessPointType primAccessPoint = DmsTypeUtils.createPrimitiveAccessPointType(
                 accessPoint.getId(), String.class,
                 DirectionType.OUT_LITERAL, accessPoint);
            AttributeUtil
                    .setReference(
                          primAccessPoint,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue()); //$NON-NLS-1$
            return primAccessPoint;
        }             
   }

   public AccessPointType extractSerializableAccessPoints(DataTypeType structuredDataType,
         AccessPointType accessPoint)
   {
	   	String className = AttributeUtil.getAttributeValue(accessPoint, "carnot:engine:className");	 //$NON-NLS-1$
	    if (accessPoint.getDirection().equals(
                DirectionType.IN_LITERAL)) {

           AccessPointType primAccessPoint = DmsTypeUtils.createSerializableAccessPointType(
                 accessPoint.getId(), className,
                 DirectionType.IN_LITERAL, accessPoint);
            AttributeUtil
                    .setReference(
                          primAccessPoint,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            //AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue());
            return primAccessPoint;
        } else {
           AccessPointType primAccessPoint = DmsTypeUtils.createSerializableAccessPointType(
                 accessPoint.getId(), className,
                 DirectionType.OUT_LITERAL, accessPoint);
            AttributeUtil
                    .setReference(
                          primAccessPoint,
                            StructuredDataConstants.TYPE_DECLARATION_ATT,
                            AttributeUtil
                                    .getIdentifiable(
                                            accessPoint,
                                            StructuredDataConstants.TYPE_DECLARATION_ATT));
            //AttributeUtil.setAttribute(primAccessPoint, PredefinedConstants.TYPE_ATT, "ag.carnot.workflow.spi.providers.data.java.Type", AttributeUtil.getAttribute(accessPoint, PredefinedConstants.TYPE_ATT).getValue());
            return primAccessPoint;
        }             
   }
   
   public AccessPointType createSerializableAccessPoints(IModelElement modelElement, String className, String id)
	   {	      
          AccessPointType serializableAccessPoint = DmsTypeUtils.createSerializableAccessPointType(
	                 id, className,
	                 DirectionType.OUT_LITERAL, modelElement);	            
	       return serializableAccessPoint;	                   
	   }
   
   
   public boolean isPrimitive(AccessPointType apt) {
      if (apt != null && (!(apt instanceof StructAccessPointType)) && apt.getType() != null && apt.getType().getId() != null && apt.getType().getId().startsWith("prim")) { //$NON-NLS-1$
         return true;
      }
      return false;
   }


   

}
