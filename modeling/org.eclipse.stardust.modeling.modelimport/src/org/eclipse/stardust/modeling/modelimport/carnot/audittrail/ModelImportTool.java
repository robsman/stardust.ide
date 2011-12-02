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

import ag.carnot.base.Base64;
import ag.carnot.base.StringUtils;
import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;
import ag.carnot.gui.ErrorDialog;
import ag.carnot.security.authentication.LoginFailedException;
import ag.carnot.workflow.model.xpdl.XpdlUtils;
import ag.carnot.workflow.runtime.CredentialProvider;
import ag.carnot.workflow.runtime.ServiceFactory;
import ag.carnot.workflow.runtime.ServiceFactoryLocator;
import ag.carnot.workflow.tools.defdesk.AuditTrailModelsDialog;

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
