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
package org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.wsdl.gen.Parser;
import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.impl.dtd.XML11DTDProcessor;
import org.apache.xerces.impl.dtd.XMLElementDecl;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ag.carnot.workflow.model.beans.XMLConstants;

public final class SchemaUtils
{
   public static List getDTDSchemaElements(String dtdURL)
   {
      try
      {
         List symbols = new ArrayList();
         XML11DTDProcessor scanner = new XML11DTDProcessor();
         URL sourceURL = new URL(dtdURL);
         DTDGrammar grammar = (DTDGrammar) scanner.loadGrammar(
               getXMLInputSource(sourceURL));
         XMLElementDecl decl = new XMLElementDecl();
         int ix = grammar.getFirstElementDeclIndex();
         while (ix >= 0)
         {
            grammar.getElementDecl(ix, decl);
            String uri = decl.name.uri;
            String local = decl.name.localpart;
            String prefix = decl.name.prefix;
            int px = local.indexOf(':');
            if (prefix == null && px > 0)
            {
               prefix = local.substring(0, px);
               local = local.substring(px + 1);
            }
            QName qname = new QName(uri, local/*, prefix*/);
            symbols.add(qname);
            ix = grammar.getNextElementDeclIndex(ix);
         }
         return symbols;
      }
      catch (Exception e)
      {
         throw new PublicException(Diagram_Messages.EXC_CANNOT_PARSE_DTD_FILE, e);
      }
   }
   
   public static List getXSDSchemaElements(String xsdURL)
   {
      /*
      <definitions targetNamespace="url"
         xmlns="http://schemas.xmlsoap.org/wsdl/"
         xmlns:xsd="http://www.w3.org/2001/XMLSchema">

         <types>
            <xsd:schema targetNamespace="url">
               <xsd:import namespace="url" schemaLocation="url"/>
            </xsd:schema>
         </types>
      <definitions>
*/
      // wrap the xsd in a wsdl document like in the example above, then parse
      Document doc = XmlUtils.newDocument();

      org.w3c.dom.Element def = doc.createElementNS(
            XMLConstants.NS_WSDL_1_1, "definitions"); //$NON-NLS-1$
      def.setAttribute("targetNamespace", xsdURL); //$NON-NLS-1$
      def.setAttribute("xmlns", XMLConstants.NS_WSDL_1_1); //$NON-NLS-1$
      def.setAttribute("xmlns:xsd", XMLConstants.NS_XSD_2001); //$NON-NLS-1$
      doc.appendChild(def);

      org.w3c.dom.Element typ = doc.createElementNS(
            XMLConstants.NS_WSDL_1_1, "types"); //$NON-NLS-1$
      def.appendChild(typ);

      org.w3c.dom.Element schema = doc.createElementNS(
            XMLConstants.NS_XSD_2001, "schema"); //$NON-NLS-1$
      schema.setPrefix("xsd"); //$NON-NLS-1$
      schema.setAttribute("targetNamespace", xsdURL); //$NON-NLS-1$
      typ.appendChild(schema);

      org.w3c.dom.Element imp = doc.createElementNS(
            XMLConstants.NS_XSD_2001, "import"); //$NON-NLS-1$
      imp.setPrefix("xsd"); //$NON-NLS-1$
      imp.setAttribute("namespace", xsdURL); //$NON-NLS-1$
      imp.setAttribute("schemaLocation", xsdURL); //$NON-NLS-1$
      schema.appendChild(imp);

      Parser parser = new Parser();
      try
      {
         parser.run(xsdURL, doc);
      }
      catch (Exception e)
      {
         throw new PublicException(Diagram_Messages.EXC_CANNOT_PARSE_XSD_FILE, e);
      }
      return Arrays.asList(parser.getSymbolTable().getElementIndex().keySet().toArray());
   }
   
   public static List getWSDLSchemaElements(String wsdlURL) throws PublicException
   {
      Parser parser = new Parser();
      try
      {
         parser.run(wsdlURL);
      }
      catch (Exception e)
      {
         throw new PublicException(Diagram_Messages.EXC_CANNOT_PARSE_WSDL_FILE, e);
      }
      return Arrays.asList(parser.getSymbolTable().getElementIndex().keySet().toArray());
   }

   private SchemaUtils()
   {
      // utility class
   }

   private static XMLInputSource getXMLInputSource(URL sourceURL) throws IOException
   {
      InputSource source = new InputSource(sourceURL.openStream());
      XMLInputSource xmlIS = new XMLInputSource(null, sourceURL.toExternalForm(), null);
      xmlIS.setByteStream(source.getByteStream());
      xmlIS.setCharacterStream(source.getCharacterStream());
      xmlIS.setEncoding(source.getEncoding());
      return xmlIS;
   }
}
