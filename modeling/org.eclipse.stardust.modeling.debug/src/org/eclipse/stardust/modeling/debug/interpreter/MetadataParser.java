/*
 * $Id: MetadataParser.java 9658 2006-12-21 11:36:26Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.util.Hashtable;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parser for metadata XML files. Parses well-formed XML files compliant with
 * the DTD <code>Metadata.dtd</code> provided with CFW or extensions of this DTD.<p>
 * Creates a hierarchy of metaobjects including instances of <code>DataGroup</code>, <code>Data</code>,
 * <code>TableData</code> and <code>Column</code>.<p>
 * The Requires a minimum DTD as listed below.
 *  <p><pre>
 *  <?xml encoding="ISO-8859-1"?>
 *  <!ELEMENT DATA_GROUP (DATA_GROUP | DATA | TABLE_DATA)*>
 *  <!ATTLIST DATA_GROUP id CDATA #REQUIRED>
 *  <!ATTLIST DATA_GROUP label CDATA #IMPLIED>
 *  <!ATTLIST DATA_GROUP type CDATA #IMPLIED>
 *  <!ATTLIST DATA_GROUP row CDATA #IMPLIED>
 *  <!ATTLIST DATA_GROUP column CDATA #IMPLIED>
 *  <!ATTLIST DATA_GROUP border (true|false) "false">
 *  <p>
 *  <!ELEMENT DATA EMPTY>
 *  <!ATTLIST DATA id CDATA #REQUIRED>
 *  <!ATTLIST DATA label CDATA #REQUIRED>
 *  <!ATTLIST DATA type CDATA #REQUIRED>
 *  <!ATTLIST DATA mandatory (true|false) "false">
 *  <!ATTLIST DATA readonly (true|false) "false">
 *  <!ATTLIST DATA length CDATA #IMPLIED>
 *  <!ATTLIST DATA default CDATA #IMPLIED>
 *  <!ATTLIST DATA row CDATA #IMPLIED>
 *  <!ATTLIST DATA column CDATA #IMPLIED>
 *  <!ATTLIST DATA depends_on CDATA #IMPLIED>
 *  <!ATTLIST DATA tooltip CDATA #IMPLIED>
 *  <p>
 *  <!ELEMENT TABLE_DATA (COLUMN+)>
 *  <!ATTLIST TABLE_DATA id CDATA #REQUIRED>
 *  <!ATTLIST TABLE_DATA label CDATA #IMPLIED>
 *  <!ATTLIST TABLE_DATA readonly CDATA #IMPLIED>
 *  <!ATTLIST TABLE_DATA row CDATA #IMPLIED>
 *  <!ATTLIST TABLE_DATA column CDATA #IMPLIED>
 *  <!ATTLIST TABLE_DATA tooltip CDATA #IMPLIED>
 *  <p>
 *  <!ELEMENT COLUMN EMPTY>
 *  <!ATTLIST COLUMN id CDATA #REQUIRED>
 *  <!ATTLIST COLUMN label CDATA #REQUIRED>
 *  <!ATTLIST COLUMN type CDATA #REQUIRED>
 *  <!ATTLIST COLUMN mandatory (true|false) "false">
 *  <!ATTLIST COLUMN readonly (true|false) "false">
 *  <!ATTLIST COLUMN length CDATA #IMPLIED>
 *  <!ATTLIST COLUMN row CDATA #IMPLIED>
 *  <!ATTLIST COLUMN column CDATA #IMPLIED>
 *  <!ATTLIST COLUMN depends_on CDATA #IMPLIED>
 *  <!ATTLIST COLUMN tooltip CDATA #IMPLIED>
 * </pre><p>
 * Arbitrary attributes can be added to the elements. These will be put in
 * property lists of the element class <code>DataGroup</code>, <code>Data</code>,
 * <code>TableData</code> and <code>Column</code>.
 * <p>
 * <b>Example:</b>
 * <p>
 * If you add the line<p>
 *
 *  <pre><!ATTLIST DATA_GROUP spacken CDATA #IMPLIED></pre><p>
 * to the element <code>DATA_GROUP</code> of the file <code>Metadata.dtd</code>,
 * you can specifiy an XML file like<p>
 * <pre><DATA_GROUP id="Bearbeiten" type="Panel" spacken="Bla">
 *    <DATA_GROUP border="false" column="1" id="BearbPanelLinks1" row="1" type="Panel">
 *    ... </pre><p>
 * The attribute <code>spacken</code> can be retrieved by
 * <p>
 *  <pre>
 * Data data = ...;
 * String string = data.getString("spacken");
 * </pre>
 * The classes <code>DataGroup</code>, <code>Data</code>,
 * <code>TableData</code> and <code>Column</code> also provide shortcut methods to
 * convert attribute value directly to Java data types (e.g. <code>Integer</code>).<p>
 * <b>Example:</b>
 * <p>
 * <pre><DATA_GROUP id="Bearbeiten" type="Panel" spacken="27">
 *    <DATA_GROUP border="false" column="1" id="BearbPanelLinks1" row="1" type="Panel">
 *    ... </pre><p>
 * and
 * <p>
 *  <pre>
 * Data data = ...;
 * int i = data.getInteger("spacken");
 * </pre>
 */
public class MetadataParser extends HandlerBase
{
   private static final Logger trace = LogManager.getLogger(MetadataParser.class);

   private SAXParser parser;
   private Stack dataGroupStack;
   private DataGroup topGroup;
   private TableData currentTable;

   /**
    * Instantiate the processor.
    */
   public MetadataParser()
   {
      try
      {
         parser = XmlUtils.newSaxParserFactory(false).newSAXParser();

         parser.getParser().setDocumentHandler(this);
         parser.getParser().setErrorHandler(this);

         topGroup = null;
         dataGroupStack = new Stack();
      }
      catch (ParserConfigurationException e)
      {
         throw new InternalException("Invalid JAXP setup.", e);
      }
      catch (SAXException e)
      {
         throw new InternalException("SAX error.", e);
      }
   }

   /**
    * Method called for each xml element found.
    *
    * @param String data element name
    * @param AttributeList attributes associated with data element
    */
   public void startElement(String tag, AttributeList atts) throws SAXException
   {
      int i;

      String id = null;
      String label = null;
      String type = null;
      String rowString = null;
      byte row = 0;
      byte column = 0;
      short length = 0;
      String columnString = null;
      String mandatoryString = null;
      String readonlyString = null;
      String defaultValue = null;
      String lengthString = null;
      String tooltip = null;
      Hashtable properties = null;

      if (tag.compareTo("DATA_GROUP") == 0)
      {
         trace.debug( "Processing data group ...");

         for (i = 0; i < atts.getLength(); i++)
         {
            if (atts.getName(i).compareTo("id") == 0)
            {
               id = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("label") == 0)
            {
               label = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("type") == 0)
            {
               type = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("row") == 0)
            {
               rowString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("column") == 0)
            {
               columnString = atts.getValue(i);
            }
            else
            {
               // Put all other attributes on the properties list

               if (properties == null)
               {
                  properties = new Hashtable();
               }

               properties.put(atts.getName(i), atts.getName(i));

               trace.debug( "Property " + atts.getName(i) + " set to " + atts.getValue(i));
            }
         }

         try
         {
            row = Byte.parseByte(rowString);
            column = Byte.parseByte(columnString);

            trace.debug( "R/C: " + row + " " + column);
         }
         catch (NumberFormatException x)
         {
         }

         DataGroup dataGroup;

         if (topGroup == null)
         {
            topGroup = dataGroup = new DataGroup(label);
         }
         else
         {
            dataGroup = ((DataGroup) dataGroupStack.peek()).createSubGroup(label);
         }

         dataGroupStack.push(dataGroup);

         dataGroup.setID(id);
         dataGroup.setLabel(label);
         dataGroup.setType(type);
         dataGroup.setRow(row);
         dataGroup.setColumn(column);
         dataGroup.setProperties(properties);

         return;
      }
      else if (tag.compareTo("DATA") == 0)
      {
         trace.debug( "Processing data definition ...");

         for (i = 0; i < atts.getLength(); i++)
         {
            if (atts.getName(i).compareTo("id") == 0)
            {
               id = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("label") == 0)
            {
               label = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("type") == 0)
            {
               type = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("mandatory") == 0)
            {
               mandatoryString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("readonly") == 0)
            {
               readonlyString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("length") == 0)
            {
               lengthString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("default") == 0)
            {
               defaultValue = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("row") == 0)
            {
               rowString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("column") == 0)
            {
               columnString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("tooltip") == 0)
            {
               tooltip = atts.getValue(i);
            }
            else
            {
               // Put all other attributes on the properties list

               if (properties == null)
               {
                  properties = new Hashtable();
               }

               properties.put(atts.getName(i), atts.getName(i));
            }
         }

         try
         {
            row = Byte.parseByte(rowString);
            column = Byte.parseByte(columnString);
         }
         catch (NumberFormatException x)
         {
         }

         Data data = ((DataGroup) dataGroupStack.peek()).createData(Integer.TYPE, label, true, true);

         data.setID(id);

         if (label.equals("null"))
         {
            label = null;
         }

         data.setLabel(label);
         data.setRow(row);
         data.setColumn(column);

         if (readonlyString.equals("true"))
         {
            data.setReadonly(true);
         }
         else
         {
            data.setReadonly(false);
         }

         if (mandatoryString.equals("true"))
         {
            data.setMandatory(true);
         }
         else
         {
            data.setMandatory(false);
         }

         data.setProperties(properties);

         return;
      }
      else if (tag.compareTo("TABLE_DATA") == 0)
      {
         trace.debug( "Processing table data ...");

         for (i = 0; i < atts.getLength(); i++)
         {
            if (atts.getName(i).compareTo("id") == 0)
            {
               id = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("label") == 0)
            {
               label = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("readonly") == 0)
            {
               readonlyString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("row") == 0)
            {
               rowString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("column") == 0)
            {
               columnString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("tooltip") == 0)
            {
               tooltip = atts.getValue(i);
            }
            else
            {
               // Put all other attributes on the properties list

               if (properties == null)
               {
                  properties = new Hashtable();
               }

               properties.put(atts.getName(i), atts.getName(i));
            }
         }

         currentTable = ((DataGroup) dataGroupStack.peek()).createTableData(label, true, true);

         currentTable.setProperties(properties);

         return;
      }
      else if (tag.compareTo("COLUMN") == 0)
      {
         trace.debug( "Processing column ...");

         for (i = 0; i < atts.getLength(); i++)
         {
            if (atts.getName(i).compareTo("id") == 0)
            {
               id = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("label") == 0)
            {
               label = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("type") == 0)
            {
               type = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("mandatory") == 0)
            {
               mandatoryString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("readonly") == 0)
            {
               readonlyString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("length") == 0)
            {
               lengthString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("row") == 0)
            {
               rowString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("column") == 0)
            {
               columnString = atts.getValue(i);
            }
            else if (atts.getName(i).compareTo("tooltip") == 0)
            {
               tooltip = atts.getValue(i);
            }
            else
            {
               // Put all other attributes on the properties list

               if (properties == null)
               {
                  properties = new Hashtable();
               }

               properties.put(atts.getName(i), atts.getName(i));
            }
         }

         if (currentTable == null)
         {
            throw new SAXException("No current table defined.");
         }

         Column tableColumn = currentTable.createColumn(Integer.TYPE, label, true, true);

         tableColumn.setProperties(properties);

         return;
      }
   }

   /**
    * Method called for each xml element ended.
    *
    * @param String data element name
    */
   public void endElement(String tag) throws SAXException
   {
      if (tag.compareTo("DATA_GROUP") == 0)
      {
         dataGroupStack.pop();
      }
      else if (tag.compareTo("TABLE") == 0)
      {
         currentTable = null;
      }
   }

   /**
    * Method called when XML file is read to completion.
    */
   public void endDocument()
   {
   }

   /**
    * Catches warning SAXParseExceptions this code sends exception to stdio and
    * allows class to continue
    */
   public void warning(SAXParseException e) throws SAXException
   {
      System.err.println("Warning at (file " + e.getSystemId() + ", line " +
            e.getLineNumber() + ", char " + e.getColumnNumber() + "): " + e.getMessage());
   }

   /**
    * Catches error SAXParseExceptions this code causes exception to continue.
    */
   public void error(SAXParseException e) throws SAXException
   {
      throw new SAXException("Error at (file " + e.getSystemId() + ", line " +
            e.getLineNumber() + ", char " + e.getColumnNumber() + "): " + e.getMessage());
   }

   /**
    * Catches fatal SAXParseExceptions this code causes exception to continue.
    */
   public void fatalError(SAXParseException e) throws SAXException
   {
      throw new SAXException("Fatal Error at (file " + e.getSystemId() +
            ", line " + e.getLineNumber() + ", char " + e.getColumnNumber() + "): " + e.getMessage());
   }

   /**
    *
    */
   public DataGroup parse(String filePath)
   {
      try
      {
         parser.parse(filePath, new DefaultHandler());

         return topGroup;
      }
      catch (Exception x)
      {
         throw new PublicException(x.getMessage());
      }
   }
}
