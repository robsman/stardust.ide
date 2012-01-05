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
package org.eclipse.stardust.modeling.repository.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.utils.xml.XmlProperties;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;

public class ExtendedModelManager extends WorkflowModelManager
{
   private ConnectionManager manager;

   public ExtendedModelManager()
   {
      super();
   }

   public ModelType createModel(URI uri)
   {
      try
      {
         return super.createModel(uri);
      }
      finally
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
         }
      }
   }

   public void load(File file) throws IOException
   {
      try
      {
         super.load(file);
      }
      finally
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
            manager.resolve();
         }
      }
   }

   public void load(URI uri) throws IOException
   {
      try
      {
         super.load(uri);
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      finally
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
            manager.resolve();
         }
      }
   }

   public void load(URI uri, InputStream is) throws IOException
   {
      try
      {
         super.load(uri, is);
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      finally
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
            manager.resolve();
         }
      }
   }

   public void reload(URI uri) throws IOException
   {
      try
      {
         super.reload(uri);
      }
      finally
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
            manager.resolve();
         }
      }
   }

   public void save(URI uri) throws IOException
   {
      if (manager == null)
      {
         ModelType model = getModel();
         if (model != null)
         {
            manager = new ConnectionManager(model);
         }
      }
      else
      {
         manager.save();
      }
      super.save(uri);
   }

   public ConnectionManager getConnectionManager()
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