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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.carnot.impl.CarnotWorkflowModelPackageImpl;
import org.eclipse.stardust.model.xpdl.util.ModelOidUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.w3c.dom.Document;

public class WorkflowModelManager
{
   static final String PROXY_ATT = "proxy"; //$NON-NLS-1$

   private static final String EXT_CWM = "cwm"; //$NON-NLS-1$

   private static final String PROTOCOL_VCS = "vcs"; //$NON-NLS-1$

   protected Resource resource = null;

   private static CarnotWorkflowModelFactory cwmFactory = null;

   protected ModelType model = null;

   private ModelOidUtil modelOidUtil;

   public ModelOidUtil getModelOidUtil()
   {
      return modelOidUtil;
   }

   public WorkflowModelManager()
   {
   }

   public WorkflowModelManager(Resource resource)
   {
      this.resource = resource;
   }

   public Resource getResource(URI resourceUri)
   {
      if (null == resource)
      {
         ResourceSet resSet = getResourceSet();
         resSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
         resource = resSet.getResource(resourceUri, true);
      }
      return resource;
   }

   public Resource getResource(URI resourceUri, boolean loadOnDemand)
   {
      if (null == resource)
      {
         ResourceSet resSet = getResourceSet();
         resSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
         resource = resSet.getResource(resourceUri, loadOnDemand);
         if (resource == null)
         {
            resource = resSet.createResource(resourceUri);
         }
      }
      return resource;
   }

   private Resource createResource(URI uri)
   {
      if (null == resource)
      {
         ResourceSet resSet = getResourceSet();
         resource = resSet.createResource(uri);
      }
      return resource;
   }

   protected ResourceSet getResourceSet()
   {
      // this will cascade the initialization of all dependent packages
      // and is a noop of the package was already initialized
      CarnotWorkflowModelPackageImpl.init();

      Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
      Map<String, Object> extensionMap = reg.getExtensionToFactoryMap();
      if (!extensionMap.containsKey(EXT_CWM))
      {
         extensionMap.put(EXT_CWM, new CarnotWorkflowModelResourceFactoryImpl());
      }
      if (!extensionMap.containsKey(XpdlUtils.EXT_XPDL))
      {
         extensionMap.put(XpdlUtils.EXT_XPDL, new CarnotWorkflowModelResourceFactoryImpl());
      }
      Map<String, Object> contentTypeMap = reg.getProtocolToFactoryMap();
      if (!contentTypeMap.containsKey(PROTOCOL_VCS))
      {
         contentTypeMap.put(PROTOCOL_VCS, new CarnotWorkflowModelResourceFactoryImpl());
      }
      // Obtain a new resource set
      return new ResourceSetImpl();
   }

   /**
    * Returns the factory associated with the model. Object creation are made through that
    * factory.
    */
   static public CarnotWorkflowModelFactory getFactory()
   {
      if (cwmFactory == null)
      {
         cwmFactory = CarnotWorkflowModelFactory.eINSTANCE;
      }
      return cwmFactory;
   }

   /**
    * Creates a new workflow model.
    */
   public ModelType createModel(URI uri)
   {
      createResource(uri);

      CarnotWorkflowModelFactory cwmFactory = getFactory();
      DocumentRoot documentRoot = cwmFactory.createDocumentRoot();
      resource.getContents().add(documentRoot);

      documentRoot.setModel(cwmFactory.createModelType());
      model = documentRoot.getModel();
      resolve(model);

      return model;
   }

   /**
    * Loads the content of the model from the file.
    *
    * @param uri
    */
   public void load(URI uri) throws IOException
   {
      load(uri, null);
   }

   /**
    * Loads the content of the model from the file.
    *
    * @param path
    */
   public void load(File file) throws IOException
   {
      load(URI.createFileURI(file.getAbsolutePath()));
   }

   public void load(URI uri, InputStream is) throws IOException
   {
      getResource(uri, false);
      doLoad(is);
   }

   protected void doLoad(InputStream is) throws IOException
   {
      Map<String, Object> options = CollectionUtils.newMap();
      options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
      // options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
      if (is == null)
      {
         resource.load(options);
      }
      else
      {
         try
         {
            resource.load(is, options);
         }
         catch (Exception e)
         {
            throw new IOException(e);
         }
      }
      // List errors = resource.getErrors();
      // List warnings = resource.getWarnings();
      fixNamespaces();
   }

   private void fixNamespaces()
   {
      EObject obj = ((EObject) resource.getContents().get(0));
      if (obj instanceof DocumentRoot)
      {
         EMap<String, String> map = ((DocumentRoot) obj).getXMLNSPrefixMap();
         String xpdlKey = null;
         List<String> namespaces = CollectionUtils.newList();
         List<String> duplicatedNSKeys = CollectionUtils.newList();
         // get namespaces for all keys with no underscore, find duplicate namespaces
         Set<String> keySet = map.keySet();
         for (String nsKey : keySet)
         {
            if (!nsKey.startsWith("_")) //$NON-NLS-1$
            {
               if (checkNamespace(map, namespaces, duplicatedNSKeys, nsKey) && xpdlKey == null)
               {
                  xpdlKey = nsKey;
               }
            }
         }
         // get namespaces for all keys with underscore, find duplicate namespaces
         for (String nsKey : keySet)
         {
            if (nsKey.startsWith("_")) //$NON-NLS-1$
            {
               if (checkNamespace(map, namespaces, duplicatedNSKeys, nsKey) && xpdlKey == null)
               {
                  xpdlKey = nsKey;
               }
            }
         }
         // remove duplicate namespaces
         for (String nsKey : duplicatedNSKeys)
         {
            map.removeKey(nsKey);
         }
         // set xpdl package prefix
         if (xpdlKey != null)
         {
            map.remove(xpdlKey);
         }
         map.put(XpdlPackage.eNS_PREFIX, XpdlPackage.eNS_URI);
      }
   }

   private boolean checkNamespace(EMap<String, String> map, List<String> namespaces, List<String> duplicatedNSKeys, String nsKey)
   {
      String ns = map.get(nsKey);
      if (namespaces.contains(ns))
      {
         duplicatedNSKeys.add(nsKey);
      }
      else
      {
         namespaces.add(ns);
      }
      return XpdlPackage.eNS_URI.equals(ns);
   }

   /**
    * reloads the content of the model from the file.
    *
    * @param path
    */
   public void reload(URI uri) throws IOException
   {
      getResource(uri, false).unload();
      load(uri);
   }

   /**
    * Saves the content of the model to the file.
    *
    * @param path
    */
   public void save(URI uri) throws IOException
   {
      save(uri, null);
   }
   
   public void save(File file) throws IOException
   {
      save(URI.createFileURI(file.getAbsolutePath()));
   }

   public void save(URI uri, OutputStream os) throws IOException
   {
      getResource(uri, false);

      // get resource from cached model
      // resource = model.eContainer().eResource();
      
      if ((null != resource) && !CompareHelper.areEqual(resource.getURI(), uri))
      {
         resource.setURI(uri);
      }

      doSave(os);
   }
   
   protected void doSave(OutputStream os) throws IOException
   {
      Map<String, Object> options = CollectionUtils.newMap();
      // options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
      options.put(XMLResource.OPTION_ENCODING, XMLConstants.ENCODING_ISO_8859_1);

      URI uri = resource.getURI();
      if ((null != uri.fileExtension())
            && uri.fileExtension().endsWith(XpdlUtils.EXT_XPDL)
            && (resource instanceof CarnotWorkflowModelResourceImpl))
      {
         Document domCwm = ((XMLResource) resource).save(null, options, null);
         if (null != domCwm)
         {
            // TODO transform to XPDL
            Source xsltSource;
            try
            {
               final URL xsltURL = XpdlUtils.getCarnot2XpdlStylesheet();
               if (xsltURL == null)
               {
                  throw new InternalException("Unable to find XPDL export stylesheet."); //$NON-NLS-1$
               }
               xsltSource = new StreamSource(xsltURL.openStream());
            }
            catch (IOException e)
            {
               throw new PublicException(Model_Messages.EXC_INVALID_JAXP_SETUP, e);
            }

            // need to override context class loader so XpdlUtils extension class is
            // accessible from Xalan
            ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
            try
            {
               Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

               StreamResult target = new StreamResult(os == null
                     ? ((CarnotWorkflowModelResourceImpl) resource).getNewOutputStream()
                     : os);

               TransformerFactory transformerFactory = XmlUtils.newTransformerFactory();
               Transformer xpdlTrans = transformerFactory.newTransformer(xsltSource);

               XmlUtils.transform(new DOMSource(domCwm), xpdlTrans, target, null, 3,
                     XpdlUtils.UTF8_ENCODING);
            }
            catch (TransformerConfigurationException e)
            {
               throw new PublicException(Model_Messages.EXC_INVALID_JAXP_SETUP, e);
            }
            finally
            {
               // restoring previous context class loader
               Thread.currentThread().setContextClassLoader(cclBackup);
            }
         }
      }
      else
      {
         if (os == null)
         {
            resource.save(options);
         }
         else
         {
            resource.save(os, options);
         }
      }
   }

   /**
    * Gets the top level model.
    */
   public ModelType getModel()
   {
      if (null == model && resource != null)
      {
         /*
         ModelType cachedModel = WorkspaceManager.getInstance().getModel(resource.getURI());
         if(cachedModel != null)
         {
            model = cachedModel;
            
            long maxUsedOid = ModelUtils.getMaxUsedOid(model);
            modelOidUtil = ModelOidUtil.register(model, maxUsedOid, resource);
            resolve(model);
            return model;
         }
         */
         
         EList<EObject> l = resource.getContents();
         Iterator<EObject> i = l.iterator();
         while (i.hasNext())
         {
            EObject o = i.next();
            if (o instanceof DocumentRoot)
            {
               model = ((DocumentRoot) o).getModel();
            }
         }
         if (model != null)
         {
            resolve(model);
         }
      }
      
      // WorkspaceManager.getInstance().addModel(resource.getURI(), model);
      
      return model;
   }

   public void resolve(ModelType model)
   {
      long maxUsedOid = ModelUtils.getMaxUsedOid(model);
      modelOidUtil = ModelOidUtil.register(model, maxUsedOid, resource);

      // resolve string-id references in attributes
      ModelUtils.resolve(model, model);
      
      // WorkspaceManager instance = WorkspaceManager.getInstance();
      // instance.resolve(model);
   }
}