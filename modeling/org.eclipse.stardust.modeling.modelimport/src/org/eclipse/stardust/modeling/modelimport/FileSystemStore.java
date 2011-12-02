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
package org.eclipse.stardust.modeling.modelimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FileSystemStore
{
   private static final String VALID_FROM_ATT = "validFrom"; //$NON-NLS-1$
   private static final String VALID_TO_ATT = "validTo"; //$NON-NLS-1$
   private static final String DEPLOYMENT_TIME_ATT = "deploymentTime"; //$NON-NLS-1$
   private static final String DEPLOYMENT_COMMENT_ATT = "comment"; //$NON-NLS-1$
   private static final String OID_ATT = "oid"; //$NON-NLS-1$
   private static final String PUBLIC = "public"; //$NON-NLS-1$
   private static final String NAME_ATT = "name"; //$NON-NLS-1$
   private static final String DESCRIPTION_ATT = "description"; //$NON-NLS-1$
   private static final String ID_ATT = "id"; //$NON-NLS-1$
   private static final String VERSION_COUNT_ATT = "versionCount"; //$NON-NLS-1$
   private static final String VERSION_ATT = "version"; //$NON-NLS-1$
   private static final String PRIVATE = "private"; //$NON-NLS-1$
   private static final String OWNER_ATT = "owner"; //$NON-NLS-1$
   private static final String RELEASED_ATT = "released"; //$NON-NLS-1$
   private static final String RELEASE_TIME_ATT = "releaseTime"; //$NON-NLS-1$
   private static final String BOOT_FILE = "repository.boot"; //$NON-NLS-1$

   private File repositoryDirectory;
   private ArrayList rootModels;
   private IImportStructureProvider provider;

   public FileSystemStore(File repositoryDirectory, IImportStructureProvider provider)
   {
      this.repositoryDirectory = repositoryDirectory;
      this.provider = provider;
   }

   public void loadRepository() throws IOException, ParserConfigurationException
   {
      if (rootModels == null)
      {
         rootModels = new ArrayList();
      }
      else
      {
         rootModels.clear();
      }
      File file = new File(repositoryDirectory, BOOT_FILE);
      if (file.exists())
      {
         Document document = readDocument(file);
         Element documentElement = document.getDocumentElement();
         NodeList nodelist = documentElement.getChildNodes();
         for (int i = 0; i < nodelist.getLength(); i++)
         {
            Node node = nodelist.item(i);
            if (PUBLIC.equals(node.getNodeName()))
            {
               Element element = (Element) node;
               ModelNode modelnode = attachRootModel(
                  element.getAttribute(ID_ATT), element.getAttribute(NAME_ATT),
                  element.getAttribute(VERSION_COUNT_ATT));
               attachPublicAttributes(modelnode, element);
               loadVersions(modelnode, (Element) node);
            }
         }
      }
   }

   public static Document readDocument(File file)
      throws ParserConfigurationException, IOException
   {
      DocumentBuilder documentbuilder = newDomBuilder();
      try
      {
         InputSource inputsource = new InputSource(new FileInputStream(file));
         return documentbuilder.parse(inputsource);
      }
      catch (SAXException saxexception)
      {
         throw new IOException(saxexception.getMessage());
      }
   }

   public static DocumentBuilder newDomBuilder()
      throws ParserConfigurationException
   {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(false);
      factory.setValidating(false);
      DocumentBuilder documentbuilder = factory.newDocumentBuilder();
      documentbuilder.setErrorHandler(new DefaultHandler());
      return documentbuilder;
   }

   public ModelNode attachRootModel(String id, String name, String versionCount)
      throws IOException
   {
      if (findRootModel(id) != null)
      {
         throw new IOException(ImportMessages.MSG_RootModelWithId + id + "'."); //$NON-NLS-1$
      }
      else
      {
         ModelNode modelnodebean = new ModelNode(this, id, name, "1", versionCount); //$NON-NLS-1$
         rootModels.add(modelnodebean);
         return modelnodebean;
      }
   }

   public ModelNode findRootModel(String id)
   {
      for (Iterator iterator = rootModels.iterator(); iterator.hasNext();)
      {
         ModelNode modelnode = (ModelNode) iterator.next();
         if (modelnode.getId().equals(id))
            return modelnode;
      }

      return null;
   }

   private void attachPublicAttributes(ModelNode modelnode, Element element)
   {
      modelnode.setDescription(element.getAttribute(DESCRIPTION_ATT));
      modelnode.setValidFrom(element.getAttribute(VALID_FROM_ATT));
      modelnode.setValidTo(element.getAttribute(VALID_TO_ATT));
      modelnode.setDeploymentComment(element.getAttribute(DEPLOYMENT_COMMENT_ATT));
      modelnode.setDeploymentTime(element.getAttribute(DEPLOYMENT_TIME_ATT));
      modelnode.setModelOID(element.getAttribute(OID_ATT));
      if ("true".equalsIgnoreCase(element.getAttribute(RELEASED_ATT))) //$NON-NLS-1$
      {
         String releaseTime = element.getAttribute(RELEASE_TIME_ATT);
         modelnode.release(releaseTime);
      }
   }

   private void loadVersions(ModelNode modelnode, Element element)
   {
      NodeList nodelist = element.getChildNodes();
      for (int i = 0; i < nodelist.getLength(); i++)
      {
         Node node = nodelist.item(i);
         if (PUBLIC.equals(node.getNodeName()))
         {
            Element child = (Element) node;
            ModelNode childnode = modelnode.attachPublicVersion(
               child.getAttribute(ID_ATT), child.getAttribute(NAME_ATT),
               child.getAttribute(VERSION_ATT), child.getAttribute(VERSION_COUNT_ATT));
            attachPublicAttributes(childnode, child);
            loadVersions(childnode, (Element) node);
         }
         else if (PRIVATE.equals(node.getNodeName()))
         {
            Element child = (Element) node;
            modelnode.attachPrivateVersion(child.getAttribute(OWNER_ATT),
               child.getAttribute(VERSION_ATT));
         }
      }

   }

   public Object[] getRootModels()
   {
      return rootModels.toArray();
   }

   public String getFullName(ModelNode modelnode)
   {
      return getFile(modelnode).toString();
   }

   public File getFile(ModelNode modelnode)
   {
      return new File(repositoryDirectory, modelnode.getId() + "_" + modelnode.getFullVersion() + ".mod"); //$NON-NLS-1$ //$NON-NLS-2$
   }
}
