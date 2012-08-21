/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA 
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Port;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

public class BindingWrapper implements Port
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private Binding binding;

   public BindingWrapper(Binding binding)
   {
      this.binding = binding;
   }

   public void addExtensibilityElement(ExtensibilityElement extensibilityelement)
   {
      throw new UnsupportedOperationException("addExtensibilityElement"); //$NON-NLS-1$
   }

   public Binding getBinding()
   {
      return binding;
   }

   public Element getDocumentationElement()
   {
      throw new UnsupportedOperationException("getDocumentationElement"); //$NON-NLS-1$
   }

   public List<ExtensibilityElement> getExtensibilityElements()
   {
      throw new UnsupportedOperationException("getExtensibilityElements"); //$NON-NLS-1$
   }

   public String getName()
   {
      return binding.getQName().getLocalPart();
   }

   public QName getQName()
   {
      return binding.getQName();
   }

   public void setBinding(Binding binding)
   {
      throw new UnsupportedOperationException("setBinding"); //$NON-NLS-1$
   }

   public void setDocumentationElement(Element element)
   {
      throw new UnsupportedOperationException("setDocumentationElement"); //$NON-NLS-1$
   }

   public void setName(String s)
   {
      throw new UnsupportedOperationException("setName"); //$NON-NLS-1$
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
