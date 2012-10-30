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

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.utils.xml.XmlProperties;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;

public class WebModelerModelManager extends WorkflowModelManager
{
   private WebModelerConnectionManager manager;
   private ModelType saveModel;
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
      saveModel = model;
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

   public void save(URI uri) throws IOException
   {
      if (manager == null)
      {
         manager = (WebModelerConnectionManager) saveModel.getConnectionManager();
         if(manager == null)
         {
            manager = new WebModelerConnectionManager(model, strategy);            
         }
      }
      manager.save();

      super.save(uri);
   }

   public WebModelerConnectionManager getConnectionManager()
   {
      return manager;
   }

   public static ModelType loadModel(File modelXml) throws IOException
   {
      // optionally override default TraxFactory to get rid of a Xalan related bug of loosing namespace alias declarations
      final String ippTraxFactory = Parameters.instance().getString(
            XmlProperties.XSLT_TRANSFORMER_FACTORY);

      final String traxFactoryOverride = System.getProperty(TransformerFactory.class.getName());
      try
      {
         if ( !StringUtils.isEmpty(ippTraxFactory))
         {
            System.setProperty(TransformerFactory.class.getName(), ippTraxFactory);
         }

         WorkflowModelManager modelMgr = new WorkflowModelManager();

         modelMgr.load(modelXml);

         return modelMgr.getModel();
      }
      finally
      {
         if ( !StringUtils.isEmpty(ippTraxFactory) && !StringUtils.isEmpty(traxFactoryOverride))
         {
            System.setProperty(TransformerFactory.class.getName(),
                  traxFactoryOverride);
         }
      }
   }
}