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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.xsd.XSDComponent;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;


public class CwmXmlSave extends XMLSaveImpl
{
   public CwmXmlSave(XMLHelper helper)
   {
      super(helper);
   }

   public CwmXmlSave(Map<?, ?> options, XMLHelper helper, String encoding)
   {
      super(options, helper, encoding);
   }

   protected void saveIDRefSingle(EObject o, EStructuralFeature eFtr)
   {
      final boolean isElementIdRef = (eFtr.getEType() instanceof EClass)
            && CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement()
                  .isSuperTypeOf((EClass) eFtr.getEType())
            && (null != eFtr.getEAnnotation(ElementIdRefs.ANNOTATION_ID));
         
      if (isElementIdRef)
      {
         EAnnotation aEIdRef = eFtr.getEAnnotation(ElementIdRefs.ANNOTATION_ID);
         String refType = null;
         for (Iterator<Map.Entry<String, String>> aDetailsItr = aEIdRef.getDetails().iterator(); aDetailsItr.hasNext();)
         {
            Map.Entry<String, String> detail = aDetailsItr.next();
            if (refType == null && ElementIdRefs.ATTR_REF_TYPE.equals(detail.getKey()))
            {
               refType = detail.getValue();
            }
         }
         
         EObjectImpl value = (EObjectImpl) helper.getValue(o, eFtr);
         String id = getId(o, value, refType);
         if ( !toDOM)
         {
            String name = helper.getQName(eFtr);
            doc.addAttribute(name, id);
         }
         else
         {
            helper.populateNameInfo(nameInfo, eFtr);
            Attr attr = document.createAttributeNS(nameInfo.getNamespaceURI(),
                  nameInfo.getQualifiedName());
            attr.setNodeValue(id);
            ((Element) currentNode).setAttributeNodeNS(attr);
            handler.recordValues(attr, o, eFtr, value);
         }
      }
      else
      {
         super.saveIDRefSingle(o, eFtr);
      }
   }

   private static String getId(EObject eObject, EObject content, String refType)
   {
      String id = null;
      if (content.eIsProxy())
      {
         id = ((EObjectImpl) content).eProxyURI().toString();
      }
      else if (refType == null || ElementIdRefs.REF_TYPE_ID.equals(refType))
      {
         id = ((IIdentifiableElement) content).getId();
      }
      else if (content instanceof IModelElement && ElementIdRefs.REF_TYPE_OID.equals(refType))
      {
         id = Long.toString(((IModelElement) content).getElementOid());
      }
      /*Resource resource = content.eResource();
      Resource targetResource = eObject.eResource();
      // resource can be null if the element is linked from an external connection
      if (mustQualify(eObject, resource, targetResource))
      {
         URI resURI = resource.getURI();
         URI srcURI = targetResource.getURI();
         resURI = resURI.deresolve(srcURI, true, true, false);
         return resURI.toString() + "#" + id;
      }*/
      return id;
   }

   /*private static boolean mustQualify(EObject eObject, Resource resource, Resource targetResource)
   {
      if (eObject instanceof Extensible &&
            ExtendedAttributeUtil.getAttribute((Extensible) eObject, IConnectionManager.URI_ATTRIBUTE_NAME) != null)
      {
         return false;
      }
      if (eObject instanceof IExtensibleElement &&
            AttributeUtil.getAttribute((IExtensibleElement) eObject, IConnectionManager.URI_ATTRIBUTE_NAME) != null)
      {
         return false;
      }
      return resource != null && resource != targetResource;
   }*/


   //rpielmann: Fix for CRNT-16072 and CRNT-15215
   protected void processAttributeExtensions(EObject object)
   {
      if (object instanceof ModelType)
      {
         if (helper.getResource() != null)
         {
            if (helper.getResource().getURI().toString().endsWith(".cwm")) //$NON-NLS-1$
            {
               if (eObjectToExtensionMap != null)
               {
                  AnyType anyType = eObjectToExtensionMap.get(object);
                  if (anyType != null)
                  {
                     SimpleFeatureMapEntry toBeRemoved = null;
                     for (Iterator<FeatureMap.Entry> i = anyType.getAnyAttribute().iterator(); i.hasNext();)
                     {
                        Object o = i.next();
                        if (o instanceof SimpleFeatureMapEntry)
                        {
                           SimpleFeatureMapEntry s = (SimpleFeatureMapEntry) o;
                           if (s.getEStructuralFeature().getName().equalsIgnoreCase("xmlns")) //$NON-NLS-1$
                           {
                              if (s.getValue().equals("http://www.carnot.ag/workflowmodel/3.1")) //$NON-NLS-1$
                              {
                                 toBeRemoved = s;
                              }
                           }
                        }
                     }
                     if (toBeRemoved != null)
                     {
                        anyType.getAnyAttribute().remove(toBeRemoved);
                     }
                  }
               }
            }
         }
      }
      super.processAttributeExtensions(object);
   }
   
   protected void saveElement(EObject o, EStructuralFeature f)
   {
      if (o instanceof XSDComponent)
      {
         XSDComponent component = (XSDComponent) o;
         component.updateElement();
         if (toDOM)
         {
            currentNode.appendChild(document.importNode(component.getElement(), true));
         }
         else
         {
            Element element = component.getElement();
            saveElement(element);
         }
         return;
      }
      else if (o instanceof ExtendedAttributeType
            && ExtendedAttributeType.EXTERNAL_ANNOTATIONS_NAME.equals(((ExtendedAttributeType) o).getName()))
      {
         ((ExtendedAttributeType) o).getMixed().clear();
         ((ExtendedAttributeType) o).getGroup().clear();
         ((ExtendedAttributeType) o).getAny().clear();
      }
      super.saveElement(o, f);
   }

   private void saveElement(Element element)
   {
      boolean empty = true;
      doc.startElement(element.getTagName());
      NamedNodeMap attributes = element.getAttributes();
      for (int i = 0; i < attributes.getLength(); i++)
      {
         Attr attribute = (Attr) attributes.item(i);
         doc.addAttribute(attribute.getName(), attribute.getValue());
      }
      NodeList list = element.getChildNodes();
      for (int i = 0; i < list.getLength(); i++)
      {
         Node node = list.item(i);
         if (node instanceof Element)
         {
            empty = false;
            saveElement((Element) node);
         }
         else if (node instanceof Text)
         {
            empty = false;
            doc.addText(stripWhitespace(((Text) node).getData()));
         }
         else if (node instanceof CDATASection)
         {
            empty = false;
            doc.addCDATA(((CDATASection) node).getData());
         }
         else if (node instanceof Comment)
         {
            empty = false;
            doc.addComment(((Comment) node).getData());
         }
         else if (node instanceof Entity)
         {
            // ignore
         }
         else if (node instanceof ProcessingInstruction)
         {
            // ignore
         }
      }
      if (empty)
      {
         doc.endEmptyElement();
      }
      else
      {
         doc.endElement();
      }
   }

   private String stripWhitespace(String value)
   {
      int begin = 0;
      int end = value.length();
      while (end > begin && Character.isWhitespace(value.charAt(end - 1)))
      {
         end--;
      }
      if (end < 0)
      {
         return ""; //$NON-NLS-1$
      }
      while (begin < end && Character.isWhitespace(value.charAt(begin)))
      {
         begin++;
      }
      return value.substring(begin, end);
   }
}
