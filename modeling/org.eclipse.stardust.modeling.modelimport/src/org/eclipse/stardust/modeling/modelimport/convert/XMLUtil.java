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
package org.eclipse.stardust.modeling.modelimport.convert;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.modeling.modelimport.Import_Messages;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * 
 * @author kberberich
 * @version $Revision$
 */
public class XMLUtil
{
   private static final Logger trace = LogManager.getLogger(XMLUtil.class);

   public static Node getChildByName(Node parent, String name)
   {
      NodeList childdren;

      try
      {
         childdren = parent.getChildNodes();

         for (int i = 0; i < childdren.getLength(); i++)
         {
            if (name.equals(childdren.item(i).getNodeName()))
            {
               return childdren.item(i);
            }
         }
      }
      catch (Exception e)
      {
         trace.warn(MessageFormat.format(Import_Messages.MSG_ErrRetrievingChild,
               new Object[] {name, parent}), e);
      }

      return null;
   }

   public static List<Node> getChildrenByName(Node parent, String name)
   {
      List<Node> retVal = CollectionUtils.newList();
      NodeList children;

      try
      {
         children = parent.getChildNodes();

         for (int i = 0; i < children.getLength(); i++)
         {
            if (name.equals(children.item(i).getNodeName()))
            {
               retVal.add(children.item(i));
            }
         }
      }
      catch (Exception e)
      {
         trace.warn(MessageFormat.format(Import_Messages.MSG_ErrRetrievingChild,
               new Object[] {name, parent}), e);
      }

      return retVal;
   }

   public static String getNamedAttribute(Node node, String attributeName)
   {
      NamedNodeMap attributes;
      String name = null;
      attributes = node.getAttributes();

      for (int i = 0; i < attributes.getLength(); i++)
      {
         if (attributes.item(i).getNodeName() == null)
         {
            continue;
         }
         else if (attributes.item(i).getNodeName().equals(attributeName))
         {
            name = attributes.item(i).getNodeValue();
         }
      }

      return name;
   }

   public static List<Element> getElementsByName(Node node, String name)
   {
      List<Element> elements = CollectionUtils.newList();
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++)
      {
         Node child = children.item(i);
         if (child instanceof Element && name.equals(child.getNodeName()))
         {
            elements.add((Element) child);
         }
      }
      return elements;
   }
}
