/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Part;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.wsdl.extensions.soap12.SOAP12Body;
import javax.wsdl.extensions.soap12.SOAP12Operation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.XMLConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPConstants;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;

/**
 * @author fherinean
 * @version $Revision: 57229 $
 */
public class JaxWSResource
{
   private static final String CANNOT_PARSE_WSDL_FILE = Diagram_Messages.WebServiceResource_ErrorMessage;

   // JAXB 2.1 final release, December 11, 2006
   private static HashMap<String, String> jaxbDefaultMappings = new HashMap<String, String>();
   static
   {
      // since these classes are used for specifying java access points,
      // we specify for all primitive types the wrapper type.
      jaxbDefaultMappings.put("string", String.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("integer", BigInteger.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("int", Integer.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("long", Long.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("short", Short.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("decimal", BigDecimal.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("float", Float.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("double", Double.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("boolean", Boolean.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("byte", Byte.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("QName", QName.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("base64Binary", byte.class.getName() + "[]"); //$NON-NLS-1$ //$NON-NLS-2$
      jaxbDefaultMappings.put("hexBinary", byte.class.getName() + "[]"); //$NON-NLS-1$ //$NON-NLS-2$
      // optional types
      jaxbDefaultMappings.put("unsignedInt", Long.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("unsignedShort", Integer.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("unsignedByte", Short.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("anySimpleType", Object.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("NOTATION", QName.class.getName()); //$NON-NLS-1$
      // JAXP 1.3 datatypes
      jaxbDefaultMappings.put("dateTime", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("time", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("date", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("gYearMonth", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("gYear", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("gMonthDay", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("gDay", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("gMonth", XMLGregorianCalendar.class.getName()); //$NON-NLS-1$
      jaxbDefaultMappings.put("duration", Duration.class.getName()); //$NON-NLS-1$
   }

   public static String getDefaultMappedClass(QName type)
   {
      String javaName = null;
      if (XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(type.getNamespaceURI()))
      {
         String localName = type.getLocalPart();
         javaName = (String) jaxbDefaultMappings.get(localName);
      }
      return javaName;
   }

   public static Definition getDefinition(String wsdlURI)
   {
      try
      {
         String wsdl = XmlUtils.resolveResourceUri(wsdlURI);
         return getDefinition(wsdl, newDocument(wsdl));
      }
      catch (Exception e)
      {
         throw new PublicException(CANNOT_PARSE_WSDL_FILE, e);
      }
   }

   public static Definition getDefinition(String wsdlURI, InputSource source)
   {
      try
      {
         return getDefinition(wsdlURI, newDocument(source));
      }
      catch (Exception e)
      {
         throw new PublicException(CANNOT_PARSE_WSDL_FILE, e);
      }
   }

   public static Definition getDefinition(String wsdlURI, Document document)
   {
      try
      {
         WSDLFactory factory = WSDLFactory.newInstance();
         WSDLReader reader = factory.newWSDLReader();
         return reader.readWSDL(wsdlURI, document);
      }
      catch (WSDLException e)
      {
         throw new PublicException(CANNOT_PARSE_WSDL_FILE, e);
      }
   }

   public static Document newDocument(String uri)
      throws ParserConfigurationException, SAXException, IOException
   {
      return newDocument(new InputSource(uri));
   }

   public static Document newDocument(InputSource source)
      throws ParserConfigurationException, SAXException, IOException
   {
      Document doc = XmlUtils.parseSource(source, null);
      // Close the stream
      if (source.getByteStream() != null)
      {
         source.getByteStream().close();
      }
      else if (source.getCharacterStream() != null)
      {
         source.getCharacterStream().close();
      }
      return doc;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public static String getBindingStyle(Binding binding)
   {
      String style = null;
      if (binding != null)
      {
         style = "message"; //$NON-NLS-1$
         List<ExtensibilityElement> extensions = binding.getExtensibilityElements();
         for (int i = 0; i < extensions.size(); i++)
         {
            ExtensibilityElement element = extensions.get(i);
            if (element instanceof SOAPBinding)
            {
               style = ((SOAPBinding) element).getStyle();
               break;
            }
            else if (element instanceof SOAP12Binding)
            {
               style = ((SOAP12Binding) element).getStyle();
               break;
            }
         }
      }
      return style;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public static String getSoapActionUri(BindingOperation operation)
   {
      String soapAction = null;
      if (operation != null)
      {
         List<ExtensibilityElement> extensions = operation.getExtensibilityElements();
         for (int i = 0; i < extensions.size(); i++)
         {
            ExtensibilityElement element = extensions.get(i);
            if (element instanceof SOAPOperation)
            {
               soapAction = ((SOAPOperation) element).getSoapActionURI();
               break;
            }
            else if (element instanceof SOAP12Operation)
            {
               soapAction = ((SOAP12Operation) element).getSoapActionURI();
               break;
            }
         }
      }
      return soapAction;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public static String getOperationStyle(BindingOperation operation)
   {
      String style = null;
      if (operation != null)
      {
         List<ExtensibilityElement> extensions = operation.getExtensibilityElements();
         for (int i = 0; i < extensions.size(); i++)
         {
            ExtensibilityElement element = extensions.get(i);
            if (element instanceof SOAPOperation)
            {
               style = ((SOAPOperation) element).getStyle();
               break;
            }
            else if (element instanceof SOAP12Operation)
            {
               style = ((SOAP12Operation) element).getStyle();
               break;
            }
         }
      }
      return style;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public static String getOperationProtocol(BindingOperation operation)
   {
      String soapProtocol = SOAPConstants.DEFAULT_SOAP_PROTOCOL;
      if (operation != null)
      {
         List<ExtensibilityElement> extensions = operation.getExtensibilityElements();
         for (int i = 0; i < extensions.size(); i++)
         {
            ExtensibilityElement element = extensions.get(i);
            if (element instanceof SOAPOperation)
            {
               soapProtocol = SOAPConstants.SOAP_1_1_PROTOCOL;
               break;
            }
            else if (element instanceof SOAP12Operation)
            {
               soapProtocol = SOAPConstants.SOAP_1_2_PROTOCOL;
               break;
            }
         }
      }
      return soapProtocol;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   public static String getOperationUse(BindingOperation operation)
   {
      String use = null;
      if (operation != null)
      {
         List<ExtensibilityElement> extensions = null;
         BindingInput input = operation.getBindingInput();
         if (input != null)
         {
            extensions = input.getExtensibilityElements();
         }
         else
         {
            BindingOutput output = operation.getBindingOutput();
            if (output != null)
            {
               extensions = output.getExtensibilityElements();
            }
         }
         if (extensions != null)
         {
            for (int i = 0; i < extensions.size(); i++)
            {
               ExtensibilityElement element = extensions.get(i);
               if (element instanceof SOAPBody)
               {
                  use = ((SOAPBody) element).getUse();
                  break;
               }
               else if (element instanceof SOAP12Body)
               {
                  use = ((SOAP12Body) element).getUse();
                  break;
               }
            }
         }
      }
      return use;
   }

   public static QName getType(Part part)
   {
      QName type = part.getTypeName();
      if (type == null)
      {
         type = part.getElementName();
      }
      return type;
   }
}
