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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.xpdl.XpdlSaxParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CwmXmlLoad extends XMLLoadImpl
{
   public CwmXmlLoad(XMLHelper helper)
   {
      super(helper);
   }

   protected DefaultHandler makeDefaultHandler()
   {
      return new CwmXmlHandler(resource, helper, options);
   }

   protected SAXParser makeParser() throws ParserConfigurationException, SAXException
   {
      SAXParser parser = null;
      
      // TODO rsauer always going through XPDL transformation?
      if (isXpdl())
      {
         // make parser getting fed from transformation result xpdl2carnot.xslt
         SAXParserFactory f = XmlUtils.newSaxParserFactory(false);
         parser = new XpdlSaxParser(f.newSAXParser());
      }
      else
      {
         parser = super.makeParser();
      }
      return parser;
   }

   private boolean isXpdl()
   {
      if (resource != null)
      {
         URI uri = resource.getURI();
         String fileExtension = uri.fileExtension();
         return null != fileExtension && fileExtension.endsWith(XpdlUtils.EXT_XPDL);
      }
      return false;
   }
}
