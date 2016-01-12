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
package org.eclipse.stardust.modeling.data.structured.annotations;

import org.eclipse.stardust.engine.core.struct.Utils;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFeature;
import org.w3c.dom.*;

public class GenericElementAdapter
{
   private Element adapted;

   public GenericElementAdapter(Element adapted)
   {
      this.adapted = adapted;
   }

   public GenericElementAdapter getChild(String name, String namespace)
   {
      NodeList children = adapted.getChildNodes();
      for (int i = 0; i < children.getLength(); i++)
      {
         Node node = children.item(i);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            if (name.equals(Utils.getNodeName(node)))
            {
               // TODO: consider the namespace
               return new GenericElementAdapter((Element) node);
            }
         }
      }
      return null;
   }

   public boolean removeChild(String name, String namespace)
   {
      NodeList children = adapted.getChildNodes();
      for (int i = 0; i < children.getLength(); i++)
      {
         Node node = children.item(i);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            if (name.equals(Utils.getNodeName(node)))
            {
               // TODO: consider the namespace
               adapted.removeChild(node);
               return true;
            }
         }
      }
      return false;
   }

   public GenericElementAdapter createChild(String name, String namespace,
         String defaultPrefix)
   {
      Document document = adapted.getOwnerDocument();
      String prefix = getPrefix(namespace, defaultPrefix);
      String qualifiedName = prefix == null ? name : prefix + ':' + name;
      Element child = document.createElementNS(namespace, qualifiedName);
      adapted.appendChild(child);
      return new GenericElementAdapter(child);
   }

   public Attr getAttribute(String name)
   {
      return adapted.getAttributeNode(name);
   }

   public Attr createAttribute(String name)
   {
      Document document = adapted.getOwnerDocument();
      Attr attribute = document.createAttribute(name);
      adapted.setAttributeNode(attribute);
      return attribute;
   }

   public String getValue()
   {
      StringBuffer sb = new StringBuffer();
      Node child = adapted.getFirstChild();
      while (child != null)
      {
         if (child.getNodeType() == Node.TEXT_NODE)
         {
            sb.append(((Text) child).getNodeValue());
         }
         child = child.getNextSibling();
      }
      return sb.length() == 0 ? null : sb.toString().trim();
   }

   public void setValue(String value)
   {
      Node child = adapted.getFirstChild();
      while (child != null && child.getNodeType() != Node.TEXT_NODE)
      {
         adapted.removeChild(child);
         child = child.getNextSibling();
      }
      if (child == null)
      {
         child = adapted.getOwnerDocument().createTextNode(value);
         adapted.appendChild(child);
      }
      else
      {
         ((Text) child).setNodeValue(value);
         child = child.getNextSibling();
         while (child != null && child.getNodeType() != Node.TEXT_NODE)
         {
            adapted.removeChild(child);
            child = child.getNextSibling();
         }
      }
   }

   public void removeAttribute(String name, String namespace)
   {
      adapted.removeAttributeNS(namespace, name);
   }

   public String getPrefix(String namespace, String defaultPrefix)
   {
      if (namespace == null)
      {
         return null;
      }
      Node node = adapted;
      while (node != null)
      {
         if (namespace.equals(node.getNamespaceURI()))
         {
            return node.getPrefix();
         }
         node = node.getParentNode();
      }
      return defaultPrefix;
   }

   public static XSDAnnotation getAnnotation(XSDFeature element)
   {
      if (element instanceof XSDElementDeclaration)
      {
         return ((XSDElementDeclaration) element).getAnnotation();
      }
      if (element instanceof XSDAttributeDeclaration)
      {
         return ((XSDAttributeDeclaration) element).getAnnotation();
      }
      return null;
   }

   public static void setAnnotation(XSDFeature element, XSDAnnotation annotation)
   {
      if (element instanceof XSDElementDeclaration)
      {
         ((XSDElementDeclaration) element).setAnnotation(annotation);
      }
      if (element instanceof XSDAttributeDeclaration)
      {
         ((XSDAttributeDeclaration) element).setAnnotation(annotation);
      }
   }
}
