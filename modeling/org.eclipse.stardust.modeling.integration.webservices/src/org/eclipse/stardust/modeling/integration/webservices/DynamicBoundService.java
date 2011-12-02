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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import com.infinity.bpm.rt.integration.webservices.WSConstants50;

public class DynamicBoundService implements Service
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private Definition definition;

   private Map<Object, Port> ports;

   public DynamicBoundService(Definition definition)
   {
      this.definition = definition;
   }

   public void addExtensibilityElement(ExtensibilityElement extensibilityelement)
   {
      throw new UnsupportedOperationException("addExtensibilityElement"); //$NON-NLS-1$
   }

   public void addPort(Port port)
   {
      throw new UnsupportedOperationException("addPort"); //$NON-NLS-1$
   }

   public Element getDocumentationElement()
   {
      return null;
   }

   public List<ExtensibilityElement> getExtensibilityElements()
   {
      throw new UnsupportedOperationException("getExtensibilityElements"); //$NON-NLS-1$
   }

   public Port getPort(String name)
   {
      return getPort(QName.valueOf(name));
   }

   public Port getPort(QName name)
   {
      return getPorts().get(name);
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public Map<Object, Port> getPorts()
   {
      if (ports == null)
      {
         ports = new HashMap<Object, Port>();
         Map<Object, Binding> bindings = definition.getBindings();
         for (Iterator<Map.Entry<Object, Binding>> i = bindings.entrySet().iterator(); i.hasNext();)
         {
            Map.Entry<Object, Binding> entry = i.next();
            Binding binding = entry.getValue();
            ports.put(entry.getKey(), new BindingWrapper(binding));
         }
      }
      return ports;
   }

   public QName getQName()
   {
      return WSConstants50.DYNAMIC_BOUND_SERVICE_QNAME;
   }

   public void setDocumentationElement(Element element)
   {
      throw new UnsupportedOperationException("setDocumentationElement"); //$NON-NLS-1$
   }

   public void setQName(QName qname)
   {
      throw new UnsupportedOperationException("setQName"); //$NON-NLS-1$
   }

   public Port removePort(String arg0)
   {
      throw new UnsupportedOperationException("removePort"); //$NON-NLS-1$
   }

   public Object getExtensionAttribute(QName arg0)
   {
      throw new UnsupportedOperationException("getExtensionAttribute"); //$NON-NLS-1$
   }

   public Map<QName, Object> getExtensionAttributes()
   {
      throw new UnsupportedOperationException("getExtensionAttributes"); //$NON-NLS-1$
   }

   public List<String> getNativeAttributeNames()
   {
      throw new UnsupportedOperationException("getNativeAttributeNames"); //$NON-NLS-1$
   }

   public void setExtensionAttribute(QName arg0, Object arg1)
   {
      throw new UnsupportedOperationException("setExtensionAttribute"); //$NON-NLS-1$
   }

   public ExtensibilityElement removeExtensibilityElement(ExtensibilityElement arg0)
   {
      throw new UnsupportedOperationException("removeExtensibilityElement"); //$NON-NLS-1$
   }
}
