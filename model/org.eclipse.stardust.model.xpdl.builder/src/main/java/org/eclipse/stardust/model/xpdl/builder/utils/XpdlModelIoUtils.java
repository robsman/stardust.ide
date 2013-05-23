package org.eclipse.stardust.model.xpdl.builder.utils;

import java.io.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;

public class XpdlModelIoUtils
{
   public static ModelType loadModel(byte[] modelXml, ModelManagementStrategy strategy)
   {
      try
      {
         return loadModel(new ByteArrayInputStream(modelXml), strategy);
      }
      catch (IOException ex)
      {
         throw new InternalException("Error loading model.", ex);
      }
   }

   public static ModelType loadModel(InputStream modelXml,  ModelManagementStrategy strategy) throws IOException
   {
      WorkflowModelManager mgr = new WebModelerModelManager(strategy);
      mgr.load(URI.createURI("temp.xpdl"), modelXml);
      return mgr.getModel();
   }

   public static ModelType loadModel(File modelXml,  ModelManagementStrategy strategy) throws IOException
   {
      WorkflowModelManager mgr = new WebModelerModelManager(strategy);
      mgr.load(modelXml);
      return mgr.getModel();
   }

   public static byte[] saveModel(ModelType model)
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      WebModelerModelManager mgr = new WebModelerModelManager();
      mgr.setModel(model);
      try
      {
         mgr.save(URI.createURI(model.getId() + "." + XpdlUtils.EXT_XPDL), baos);
      }
      catch (IOException ex)
      {
         throw new InternalException("Error saving model '" + model.getId() + "'", ex);
      }
      return baos.toByteArray();
   }
}