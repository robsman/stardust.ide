/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.stardust.model.xpdl.builder.utils;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.utils.xml.XmlProperties;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;

public class WebModelerModelManager extends WorkflowModelManager
{
   private static final String TRAX_KEY = TransformerFactory.class.getName();
   
   private WebModelerConnectionManager manager;
   private ModelManagementStrategy strategy;

   public WebModelerModelManager()
   {
      super();
   }

   public WebModelerModelManager(ModelManagementStrategy strategy)
   {
      this.strategy = strategy;
   }

   public void setModel(ModelType model)
   {
      this.model = model;
      resource = this.model.eResource();
   }

   @Override
   public void resolve(ModelType model)
   {
      if (model != null && model.getId() != null)
      {
         manager = (WebModelerConnectionManager) model.getConnectionManager();
         if (manager == null)
         {
            manager = new WebModelerConnectionManager(model, strategy);
         }
         manager.resolve();
      }
      super.resolve(model);
   }

   @Override
   public void save(URI uri, OutputStream os) throws IOException
   {
      if (resource == null)
      {
         // create resource and attach model
         getResource(uri, false);
         
         CarnotWorkflowModelFactory cwmFactory = getFactory();
         DocumentRoot documentRoot = cwmFactory.createDocumentRoot();
         resource.getContents().add(documentRoot);

         documentRoot.setModel(model);
         resolve(model);
      }

      super.save(uri, os);
   }

   protected void doSave(OutputStream os) throws IOException
   {
      if (manager == null)
      {
         manager = (WebModelerConnectionManager) model.getConnectionManager();
         if(manager == null)
         {
            manager = new WebModelerConnectionManager(model, strategy);            
         }
      }
      manager.save();

      super.doSave(os);
   }

   public WebModelerConnectionManager getConnectionManager()
   {
      return manager;
   }

   @Override
   protected void doLoad(InputStream is) throws IOException
   {
      // optionally override default TraxFactory to get rid of a Xalan related bug of loosing namespace alias declarations
      final String ippTraxFactory = Parameters.instance().getString(XmlProperties.XSLT_TRANSFORMER_FACTORY);
      final String traxFactoryOverride = System.getProperty(TRAX_KEY);
      try
      {
         if (!isEmpty(ippTraxFactory))
         {
            System.setProperty(TRAX_KEY, ippTraxFactory);
         }
   
         try
         {
            super.doLoad(is);
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      finally
      {
         if (!isEmpty(ippTraxFactory))
         {
            if (isEmpty(traxFactoryOverride))
            {
               System.clearProperty(TRAX_KEY);
            }
            else
            {
               System.setProperty(TRAX_KEY, traxFactoryOverride);
            }
         }
      }
   }
}