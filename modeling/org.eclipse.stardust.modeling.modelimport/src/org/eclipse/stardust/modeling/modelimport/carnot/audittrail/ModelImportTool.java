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
package org.eclipse.stardust.modeling.modelimport.carnot.audittrail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.security.authentication.LoginFailedException;
import org.eclipse.stardust.engine.api.runtime.CredentialProvider;
import org.eclipse.stardust.engine.api.runtime.ServiceFactory;
import org.eclipse.stardust.engine.api.runtime.ServiceFactoryLocator;
import org.eclipse.stardust.engine.core.compatibility.gui.ErrorDialog;
import org.eclipse.stardust.engine.core.model.removethis.AuditTrailModelsDialog;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;

/**
 * @author rsauer
 * @version $Revision$
 */
public class ModelImportTool
{
   private static final Logger trace = LogManager.getLogger(ModelImportTool.class);

   private static final long INVALID_MODEL_OID = -1;

   private ServiceFactory serviceFactory;

   public static void main(String[] args)
   {
      trace.info(Internal_ImportMessages.getString("MSG_Starting")); //$NON-NLS-1$

      String modelFile = null;

      for (int i = 0; i < args.length; i++ )
      {
         if ("--filename".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            modelFile = args[++i];
         }
         else if ("--filename64".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            modelFile = new String(Base64.decode(args[++i].getBytes()));
         }
      }
      
      trace.info(Internal_ImportMessages.getString("MSG_ImportingModel") + modelFile); //$NON-NLS-1$

      if ( !StringUtils.isEmpty(modelFile))
      {
         new ModelImportTool(modelFile);
      }
      else
      {
         trace.error(Internal_ImportMessages.getString("MSG_NoModelSpecified")); //$NON-NLS-1$
         
         System.exit(-1);
      }
   }

   public ModelImportTool(String modelFile)
   {
      int exitCode = -1;
      
      if (importModel(modelFile))
      {
         exitCode = 1;

         JOptionPane.showMessageDialog(null, Internal_ImportMessages.getString("MSG_ModelImported")); //$NON-NLS-1$
      }
      
      System.exit(exitCode);
   }

   private boolean importModel(String modelFile)
   {
      boolean imported = false;
      
      try
      {
         getServiceFactory();

         if (AuditTrailModelsDialog.showDialog(serviceFactory, null))
         {
            long oid = AuditTrailModelsDialog.getOIDForSelectedModel();

            if (oid != INVALID_MODEL_OID)
            {
               String xmlString = serviceFactory.getQueryService().getModelAsXML(oid);
               
               if ( !modelFile.endsWith(XpdlUtils.EXT_XPDL))
               {
                  xmlString = XpdlUtils.convertXpdl2Carnot(xmlString);
               }
               
               try
               {
                  BufferedWriter writer = new BufferedWriter(new FileWriter(modelFile));
                  writer.write(xmlString);
                  writer.close();
                  
                  imported = true;
               }
               catch (IOException e)
               {
                  imported = false;
               }
            }
         }

         serviceFactory.close();
      }
      catch (LoginFailedException e)
      {
         if (e.getReason() == LoginFailedException.LOGIN_CANCELLED)
         {
            return imported;
         }
         JOptionPane.showMessageDialog(null, Internal_ImportMessages.getString("MSG_LoginFailed") + e.getMessage()); //$NON-NLS-1$
      }
      catch (Exception x)
      {
         trace.warn("", x); //$NON-NLS-1$
         ErrorDialog.showDialog(null, "", x); //$NON-NLS-1$
      }
      
      return imported;
   }

   private void getServiceFactory()
   {
      if (serviceFactory == null || ServiceFactoryLocator.hasMultipleIdentities())
      {
         if (serviceFactory != null)
         {
            serviceFactory.close();
         }
         serviceFactory = ServiceFactoryLocator.get(CredentialProvider.SWING_LOGIN);
      }
   }
}
