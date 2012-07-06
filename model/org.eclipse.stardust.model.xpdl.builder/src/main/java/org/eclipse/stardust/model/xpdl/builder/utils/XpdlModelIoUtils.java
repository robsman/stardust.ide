package org.eclipse.stardust.model.xpdl.builder.utils;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.utils.xml.XmlProperties;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.impl.DocumentRootImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;

/*
 * $Id: XpdlModelIoUtils.java 24348 2007-12-05 09:06:46Z rsauer $
 * (C) 2000 - 2007 CARNOT AG
 */

/**
 * @author rsauer
 * @version $Revision: 24348 $
 */
public class XpdlModelIoUtils
{
   //private static JcrModelManager manager;
   private static final Logger trace = LogManager.getLogger(XpdlModelIoUtils.class);

   public static final byte[] EMPTY_BUFFER = new byte[0];

   private static final int COPY_BUFFER_LENGHT = 16 * 1024;
   private static Map<String, JcrConnectionManager> map = new HashMap<String, JcrConnectionManager>();   
   private static Map<String, ModelType> modelMap = new HashMap<String, ModelType>();

   public static ModelType getModelById(String id)
   {
      return modelMap.get(id);
   }
   
   public static JcrConnectionManager getJcrConnectionManager(ModelType model)
   {
      if(modelMap.get(model.getId()) == null)
      {
         modelMap.put(model.getId(), model);
      }
      
      JcrConnectionManager manager = map.get(model.getId());
      if(manager == null)
      {
         manager = new JcrConnectionManager(model);
         map.put(model.getId(), manager);
      }
      
      return manager;
   }
   
   public static ModelType loadModel(byte[] modelXml)
   {
      try
      {
         ByteArrayInputStream modelXmlReader = new ByteArrayInputStream(modelXml);
         try
         {
            return loadModel(modelXmlReader);
         }
         finally
         {
            modelXmlReader.close();
         }
      }
      catch (IOException ioe)
      {
         trace.warn("Failed loading XPDL model from memory buffer.", ioe);

         return null;
      }
   }

   public static ModelType loadModel(InputStream modelXml) throws IOException
   {
      File tmpPackage = null;
      try
      {
         tmpPackage = copyToTmpXpdlFile(modelXml);

         return loadModel(tmpPackage);
      }
      finally
      {
         if (null != tmpPackage)
         {
            tmpPackage.delete();
         }
      }
   }

   public static ModelType loadModel(File modelXml) throws IOException
   {
      // optionally override default TraxFactory to get rid of a Xalan related bug of loosing namespace alias declarations
      final String ippTraxFactory = Parameters.instance().getString(
            XmlProperties.XSLT_TRANSFORMER_FACTORY);

      final String traxFactoryOverride = System.getProperty(TransformerFactory.class.getName());
      try
      {
         if ( !isEmpty(ippTraxFactory))
         {
            System.setProperty(TransformerFactory.class.getName(), ippTraxFactory);
         }

         WorkflowModelManager modelMgr = new JcrModelManager();
         
         try
         {
            modelMgr.load(modelXml);
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

         return modelMgr.getModel();
      }
      finally
      {
         if ( !isEmpty(ippTraxFactory) && !isEmpty(traxFactoryOverride))
         {
            System.setProperty(TransformerFactory.class.getName(),
                  traxFactoryOverride);
         }
      }
   }

   public static byte[] saveModel(ModelType model)
   {
      byte[] modelXml = EMPTY_BUFFER;

      File tmpModel = null;
      try
      {
         JcrModelManager modelMgr = new JcrModelManager();

         tmpModel = File.createTempFile("tmp-xpdl-model-", "." + XpdlUtils.EXT_XPDL);
         tmpModel.deleteOnExit();

         URI tmpModelUri = URI.createFileURI(tmpModel.getAbsolutePath());

         modelMgr.createModel(tmpModelUri);

         ModelType emptyModel = modelMgr.getModel();
         DocumentRootImpl xpdlRoot = (DocumentRootImpl) emptyModel.eContainer();
         xpdlRoot.setModel(model);

         modelMgr.setModel(model);
         modelMgr.save(tmpModelUri);

         FileInputStream fileInputStream = new FileInputStream(tmpModel);
         try
         {
            // not really save against real large files, but request was to store it into
            // a memory buffer either
            modelXml = readByteStream(fileInputStream);
         }
         finally
         {
            fileInputStream.close();
         }
      }
      catch (IOException ioe)
      {
         trace.warn("Failed saving XPDL model to memory buffer.", ioe);
      }
      finally
      {
         if (null != tmpModel)
         {
            tmpModel.delete();
         }
      }

      return modelXml;
   }

   public static byte[] readByteStream(InputStream isBytes) throws IOException
   {
      byte[] result = EMPTY_BUFFER;

      BufferedInputStream bytesReader = new BufferedInputStream(isBytes);
      try
      {
         // not really save against real large files, but request was to store it into
         // a memory buffer either

         int bufferSize = 0;

         List<byte[]> chunks = new LinkedList<byte[]>();
         while (0 < bytesReader.available())
         {
            byte[] chunk = new byte[bytesReader.available()];
            int nBytesRead = bytesReader.read(chunk, 0, chunk.length);

            if (0 == nBytesRead)
            {
               trace.warn(MessageFormat.format(
                     "Nothing was read from input stream although {0} bytes were claimed to be available.",
                     chunk.length));
               continue;
            }

            if (nBytesRead < chunk.length)
            {
               byte[] adjustedChunk = new byte[nBytesRead];
               System.arraycopy(chunk, 0, adjustedChunk, 0, nBytesRead);
               chunk = adjustedChunk;
            }

            chunks.add(chunk);
            bufferSize += chunk.length;
         }

         if (0 < bufferSize)
         {
            byte[] buffer = new byte[bufferSize];

            int pos = 0;
            for (int i = 0; i < chunks.size(); ++i)
            {
               byte[] chunk = chunks.get(i);

               System.arraycopy(chunk, 0, buffer, pos, chunk.length);
               pos += chunk.length;
            }

            result = buffer;
         }
      }
      finally
      {
         bytesReader.close();
      }

      return result;
   }

   private static File copyToTmpXpdlFile(InputStream modelContent) throws IOException
   {
      File tmpModel = File.createTempFile("tmp-xpdl-model-", "." + XpdlUtils.EXT_XPDL);
      tmpModel.deleteOnExit();

      BufferedOutputStream modelWriter = new BufferedOutputStream(new FileOutputStream(
            tmpModel));
      try
      {
         BufferedInputStream isReader = new BufferedInputStream(modelContent);
         try
         {
            byte[] buffer = new byte[COPY_BUFFER_LENGHT];
            while (0 < isReader.available())
            {
               int nBytesRead = isReader.read(buffer);
               modelWriter.write(buffer, 0, nBytesRead);
            }
         }
         finally
         {
            isReader.close();
         }
      }
      finally
      {
         modelWriter.close();
      }

      return tmpModel;
   }
   
   public static void clearModelsMap()
   {
      map.clear();
   }
}