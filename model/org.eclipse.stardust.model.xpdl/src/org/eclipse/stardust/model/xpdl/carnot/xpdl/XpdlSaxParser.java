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
package org.eclipse.stardust.model.xpdl.carnot.xpdl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelMessages;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import ag.carnot.workflow.model.xpdl.XpdlUtils;

public class XpdlSaxParser extends SAXParser
{
   private static final Logger trace = LogManager.getLogger(XpdlSaxParser.class);
   
   private static Transformer xpdl2cwmTransformer;

   private final SAXParser cwmParser;

   public XpdlSaxParser(SAXParser cwmParser)
   {
      this.cwmParser = cwmParser;
   }

   public XMLReader getXMLReader() throws SAXException
   {
      return cwmParser.getXMLReader();
   }

   public Parser getParser() throws SAXException
   {
      return cwmParser.getParser();
   }

   public boolean isNamespaceAware()
   {
      return cwmParser.isNamespaceAware();
   }

   public boolean isValidating()
   {
      return cwmParser.isValidating();
   }

   public Object getProperty(String name) throws SAXNotRecognizedException,
         SAXNotSupportedException
   {
      return cwmParser.getProperty(name);
   }

   public void setProperty(String name, Object value) throws SAXNotRecognizedException,
         SAXNotSupportedException
   {
      cwmParser.setProperty(name, value);
   }

   public void parse(String uri, DefaultHandler dh) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(uri, dh);
   }

   public void parse(File f, DefaultHandler dh) throws SAXException, IOException
   {
      parse(new InputSource(new FileInputStream(f)), dh);
   }

   public void parse(InputSource is, DefaultHandler dh) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl

      final URL xsdURL = XpdlUtils.getXpdl_10_Schema();
      if (null == xsdURL)
      {
         throw new InternalException(ModelMessages.MSG_UNABLE_TO_FIND_XPDL_IMPORT + XpdlUtils.XPDL_1_0_XSD); //$NON-NLS-1$
      }

      // by default, this will return Aelfred
      XMLReader xpdlReader = XmlUtils.newXmlReader(false);
      xpdlReader.setErrorHandler(new ParseErrorHandler());
      xpdlReader.setEntityResolver(new EntityResolver()
      {
         public InputSource resolveEntity(String publicId, String systemId)
               throws SAXException, IOException
         {
            if ((null != systemId)
                  && (XpdlUtils.XPDL_1_0_XSD_URL.equals(systemId) //
                        || XpdlUtils.NS_XPDL_1_0.equals(systemId)))
            {
               return new InputSource(xsdURL.openStream());
            }
            return null;
         }
      });

      SAXResult result = new SAXResult();
      result.setHandler(dh);

      // need to override context class loader so XpdlUtils extension class is accessible
      // from Xalan
      ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
      try
      {
         Thread.currentThread().setContextClassLoader(XpdlSaxParser.class.getClassLoader());
      
         Transformer xpdlTransformer = getXpdlTransformer();
         xpdlTransformer.transform(new SAXSource(xpdlReader, is), result);
      }
      catch (TransformerException e)
      {
         throw new PublicException(ModelMessages.MSG_FAILED_LOADING_MODEL, e); //$NON-NLS-1$
      }
      finally
      {
         // restoring previous context class loader
         Thread.currentThread().setContextClassLoader(cclBackup);
      }
   }

   public void parse(InputStream is, DefaultHandler dh) throws SAXException, IOException
   {
      parse(new InputSource(is), dh);
   }

   public void parse(InputStream is, DefaultHandler dh, String systemId)
         throws SAXException, IOException
   {
      // TODO use systemId ?
      // TODO parse from transformed xpdl
      parse(new InputSource(is), dh);
   }

   public void parse(File f, HandlerBase hb) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(f, hb);
   }

   public void parse(InputSource is, HandlerBase hb) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(is, hb);
   }

   public void parse(InputStream is, HandlerBase hb) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(is, hb);
   }

   public void parse(InputStream is, HandlerBase hb, String systemId)
         throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(is, hb, systemId);
   }

   public void parse(String uri, HandlerBase hb) throws SAXException, IOException
   {
      // TODO parse from transformed xpdl
      cwmParser.parse(uri, hb);
   }

   private static class ParseErrorHandler implements ErrorHandler
   {

	public void warning(SAXParseException exception) throws SAXException
      {
         trace.warn(formatParseException(ModelMessages.MSG_WARN, exception)); //$NON-NLS-1$
      }

      public void error(SAXParseException exception) throws SAXException
      {
         trace.error(formatParseException(ModelMessages.MSG_ERR, exception)); //$NON-NLS-1$
      }

      public void fatalError(SAXParseException exception) throws SAXException
      {
         trace.error(formatParseException(ModelMessages.MSG_FATAL_ERR, exception)); //$NON-NLS-1$
      }

      private String formatParseException(String label, SAXParseException e)
      {
         StringBuffer buffer = new StringBuffer(100);

         buffer.append(label).append(" (").append(e.getLineNumber()).append(", ").append( //$NON-NLS-1$ //$NON-NLS-2$
               e.getColumnNumber()).append(") "); //$NON-NLS-1$

         buffer.append(e.getMessage());

         return buffer.toString();
      }
   }
   
   private synchronized Transformer getXpdlTransformer()
   {
      if (null == xpdl2cwmTransformer)
      {
         final URL xsltURL = XpdlUtils.getXpdl2CarnotStylesheet();
         if (null == xsltURL)
         {
            throw new InternalException(ModelMessages.MSG_UNABLE_TO_FIND_XPDL_IMPORT); //$NON-NLS-1$
         }

         try
         {
            TransformerFactory transformerFactory = XmlUtils.newTransformerFactory();
            try
            {
               xpdl2cwmTransformer = transformerFactory.newTransformer(new StreamSource(
                     xsltURL.openStream()));
            }
            catch (IOException e)
            {
               throw new PublicException(ModelMessages.MSG_UNABLE_TO_LOAD_XPDL_IMPORT, e); //$NON-NLS-1$
            }

            xpdl2cwmTransformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
            xpdl2cwmTransformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
            xpdl2cwmTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", //$NON-NLS-1$
                  Integer.toString(3));
            xpdl2cwmTransformer.setOutputProperty(OutputKeys.ENCODING, XpdlUtils.UTF8_ENCODING);

            xpdl2cwmTransformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS,
                  "description annotationSymbol expression"); //$NON-NLS-1$
         }
         catch (TransformerConfigurationException e)
         {
            throw new PublicException(ModelMessages.MSG_INVALID_JAXP_SETUP, e); //$NON-NLS-1$
         }
      }
      
      return xpdl2cwmTransformer;
   }
}
