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
package org.eclipse.stardust.modeling.data.structured.properties;

import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.Utils;
import org.eclipse.stardust.engine.core.struct.XPathAnnotations;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDTypeDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DefaultValueModifier
{
   private static TypeDeclarationType declaration;
   private static boolean isInternalSchema;
   private static ExtendedAttributeType externalAnnotationAttribute;   
   private static String DEFAULTVALUE_ANNOTATION = "carnot:" + XPathAnnotations.DEFAULTVALUE_ANNOTATION; //$NON-NLS-1$
   
   public static void setDeclaration(TypeDeclarationType model)
   {
      declaration = model;
      isInternalSchema = TypeDeclarationUtils.isInternalSchema(declaration);      
      if(!isInternalSchema)
      {
         externalAnnotationAttribute = ExtendedAttributeUtil.getAttribute(declaration,
               ExtendedAttributeType.EXTERNAL_ANNOTATIONS_NAME);
      }
   }

   public static Object getValueForElement(XSDComponent element)
   {
      XSDAnnotation annotation;
      if (element instanceof XSDAttributeDeclaration)
      {
         XSDTypeDefinition typeDefinition = ((XSDAttributeDeclaration) element).getTypeDefinition();            
         if(typeDefinition == null || typeDefinition.getComplexType() != null)
         {
            return null;
         }         
         annotation = ((XSDAttributeDeclaration) element).getAnnotation();            
      }
      else if (element instanceof XSDElementDeclaration)
      {
         XSDTypeDefinition typeDefinition = ((XSDElementDeclaration) element).getTypeDefinition();            
         if(typeDefinition == null || typeDefinition.getComplexType() != null)
         {
            return null;
         }         
         annotation = ((XSDElementDeclaration) element).getAnnotation();         
      }
      else
      {
         return null;
      }
      
      if(annotation != null)
      {
         return getAnnotationValue(annotation);
      }      
      return null;
   }

   synchronized public static void setOrRemoveAnnotation(Object element, Object value)
   {
      boolean removeAnnotation = false;
      if(value == null
            || (value instanceof String) && StringUtils.isEmpty((String) value))
      {
         removeAnnotation = true;
      }
      
      XSDAnnotation annotation;
      if (element instanceof XSDAttributeDeclaration)
      {
         annotation = ((XSDAttributeDeclaration) element).getAnnotation();            
      }
      else if (element instanceof XSDElementDeclaration)
      {
         annotation = ((XSDElementDeclaration) element).getAnnotation();         
      }
      else
      {
         return;
      }
      if(annotation == null && !removeAnnotation)
      {
         annotation = createAnnotation(element, value);         
      }
      if(annotation != null)
      {
         modifyAnnotation(annotation, value, removeAnnotation, element);
      }
   }
   
   private static String getAnnotationValue(XSDAnnotation annotation)
   {
      Node defaultValueNode = null;
      Element appInfoElement = null;
      
      List<Element> appInfos = annotation.getApplicationInformation();      
      for (int i = 0; i < appInfos.size(); i++)
      {
         appInfoElement = (Element) appInfos.get(i);
         defaultValueNode = getChild(appInfoElement);
         if(defaultValueNode != null)
         {
            break;
         }
      }         
      if(defaultValueNode != null)
      {
         Node child = defaultValueNode.getFirstChild();
         while (child != null && child.getNodeType() != Node.TEXT_NODE)
         {
            child = child.getNextSibling();
         }
         Text textNode = null;
         if(child != null)
         {
            textNode = (Text) child;
         }
         if(textNode != null)
         {
            return textNode.getNodeValue();
         }
      }
      return null;
   }   
   
   private static void modifyAnnotation(XSDAnnotation annotation, 
         Object value, boolean removeAnnotation, Object element)
   {
      Node defaultValueNode = null;
      Element appInfoElement = null;
      int childrenCounter = 0;
      
      List<Element> appInfos = annotation.getApplicationInformation();      
      if (appInfos.isEmpty() && !removeAnnotation)
      {
         appInfoElement = annotation.createApplicationInformation(null);
         appInfos.add(appInfoElement);
         annotation.getElement().appendChild(appInfoElement);
      }  
      else
      {
         for (int i = 0; i < appInfos.size(); i++)
         {
            appInfoElement = (Element) appInfos.get(i);            
            if(removeAnnotation)
            {
               childrenCounter = getChildren(appInfoElement); 
            }            
            defaultValueNode = getChild(appInfoElement);
            if(defaultValueNode != null)
            {
               break;
            }            
         }         
      }
      
      if(removeAnnotation)
      {
         if(defaultValueNode != null)
         {
            if(childrenCounter == 1)
            {
               if(isInternalSchema)
               {
                  if (element instanceof XSDAttributeDeclaration)
                  {
                     ((XSDAttributeDeclaration) element).setAnnotation(null);
                  }
                  else if (element instanceof XSDElementDeclaration)
                  {
                     ((XSDElementDeclaration) element).setAnnotation(null); 
                  }
               }
               else
               {
                  if (externalAnnotationAttribute != null)
                  {
                     annotation = externalAnnotationAttribute.getExtendedAnnotation();
                     if(annotation != null)
                     {
                        declaration.setExtendedAttributes(null);
                     }         
                  }
               }
            }
            else
            {
               appInfoElement.removeChild(defaultValueNode);               
            }            
         }
         return;
      }      
      
      if(defaultValueNode != null)
      {
         Node child = defaultValueNode.getFirstChild();
         while (child != null && child.getNodeType() != Node.TEXT_NODE)
         {
            child = child.getNextSibling();
         }
         Text textNode = null;
         if(child != null)
         {
            textNode = (Text) child;
         }
         if(textNode == null)
         {
            textNode = (Text) defaultValueNode.getOwnerDocument().createTextNode((String) value);
            defaultValueNode.appendChild(textNode);
         }
         else
         {
            textNode.setNodeValue((String) value);            
         }         
      }
      else
      {
         Text textNode = null;
         Document document = appInfoElement.getOwnerDocument();      
         defaultValueNode = document.createElementNS(XPathAnnotations.IPP_ANNOTATIONS_NAMESPACE, DEFAULTVALUE_ANNOTATION);
         appInfoElement.appendChild(defaultValueNode);         
         textNode = defaultValueNode.getOwnerDocument().createTextNode((String) value);
         defaultValueNode.appendChild(textNode);
      }
   }

   public static int getChildren(Element appInfoElement)
   {
      NodeList children = appInfoElement.getChildNodes();
      return children.getLength();
   }      
   
   public static Node getChild(Element appInfoElement)
   {
      NodeList children = appInfoElement.getChildNodes();
      for (int i = 0; i < children.getLength(); i++)
      {
         Node node = children.item(i);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            if (Utils.getNodeName(node).equals(XPathAnnotations.DEFAULTVALUE_ANNOTATION))
            {
               return (Element) node;
            }
         }
      }
      return null;
   }
      
   private static XSDAnnotation createAnnotation(Object element, Object value)
   {      
      XSDAnnotation annotation = null;
      if(isInternalSchema)
      {
         annotation = XSDFactory.eINSTANCE.createXSDAnnotation();
         if (element instanceof XSDAttributeDeclaration)
         {
            ((XSDAttributeDeclaration) element).setAnnotation(annotation);
         }
         else if (element instanceof XSDElementDeclaration)
         {
            ((XSDElementDeclaration) element).setAnnotation(annotation); 
         }
      }
      else
      {
         if (externalAnnotationAttribute != null)
         {
            annotation = externalAnnotationAttribute.getExtendedAnnotation();
            if(annotation == null)
            {
               annotation = ExtensionFactory.eINSTANCE.createExtendedAnnotationType();
               externalAnnotationAttribute.setExtendedAnnotation((ExtendedAnnotationType) annotation);               
            }         
         }
      }
      return annotation;
   }   
   
   public static String getStringForElement(XSDComponent element)
   {
      Object value = getValueForElement(element);
      if(value != null)
      {
         return (String) value;
      }
      return null;
   }
}