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
package org.eclipse.stardust.modeling.deploy;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.security.authentication.LoginFailedException;

import ag.carnot.gui.ErrorDialog;
import ag.carnot.workflow.model.IModel;
import ag.carnot.workflow.model.Inconsistency;
import ag.carnot.workflow.model.beans.DefaultConfigurationVariablesProvider;
import ag.carnot.workflow.model.beans.DefaultXMLReader;
import ag.carnot.workflow.model.beans.IConfigurationVariablesProvider;
import ag.carnot.workflow.model.xpdl.XpdlUtils;
import ag.carnot.workflow.runtime.CredentialProvider;
import ag.carnot.workflow.runtime.DeploymentElement;
import ag.carnot.workflow.runtime.DeploymentException;
import ag.carnot.workflow.runtime.DeploymentOptions;
import ag.carnot.workflow.runtime.ServiceFactory;
import ag.carnot.workflow.runtime.ServiceFactoryLocator;
import ag.carnot.workflow.runtime.beans.BpmRuntimeEnvironment;
import ag.carnot.workflow.runtime.beans.interceptors.PropertyLayerProviderInterceptor;
import ag.carnot.workflow.runtime.beans.removethis.SecurityProperties;
import ag.carnot.workflow.tools.common.DeploymentCallback;
import ag.carnot.workflow.tools.common.DeploymentUtils;

/**
 * @author rsauer
 * @version $Revision: 37089 $
 */
public class ModelDeploymentTool
{
   private static final Logger trace = LogManager.getLogger(ModelDeploymentTool.class);

   private ServiceFactory serviceFactory;
   private static boolean version = false;
   private static String user;
   private static String password;
   private static String realm;
   private static String partition;
   private static String domain;

   public static void main(String[] args)
   {
      try
      {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (Exception e)
      {
         // ignore
      }

      trace.info(Internal_ExportMessages.getString("MSG_Starting")); //$NON-NLS-1$

      List<String> modelFiles = CollectionUtils.newList();

      for (int i = 0; i < args.length; i++)
      {
         if ("--filename".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            modelFiles.add(args[++i]);
         }
         else if ("--filename64".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            modelFiles.add(new String(Base64.decode(args[++i].getBytes())));
         }

         if ("--version".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            version = true;
         }
         if ("--user".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            user = new String(Base64.decode(args[++i].getBytes()));
         }
         if ("--password".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            password = new String(Base64.decode(args[++i].getBytes()));
         }
         if ("--realm".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            realm = new String(Base64.decode(args[++i].getBytes()));
         }
         if ("--domain".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            domain = new String(Base64.decode(args[++i].getBytes()));
         }
         if ("--partition".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            partition = new String(Base64.decode(args[++i].getBytes()));
         }
      }

      trace.info(Internal_ExportMessages.getString("MSG_DeployModel") + modelFiles); //$NON-NLS-1$

      // Activate below section if debugging is needed.
      /*trace.info("Waiting 30 sek for the debugger to connect...");
      try
      {
         Thread.sleep(30 * 1000);
      }
      catch (InterruptedException e)
      {
         trace.error("Interrupted !!!", e);
      }
      trace.info("Deploying...");*/

      if (!modelFiles.isEmpty())
      {
         new ModelDeploymentTool(modelFiles);
      }
      else
      {
         trace.error(Internal_ExportMessages.getString("MSG_NoModelSpecified")); //$NON-NLS-1$
      }
   }

   public ModelDeploymentTool(List<String> modelFiles)
   {
         ProgressDialog progress = ProgressDialog.showDialog(null,
               Internal_ExportMessages.getString("MSG_LoadingModel"), //$NON-NLS-1$
               ProgressDialog.ON_CANCEL_EXIT_WITH_MINUS_ONE);

      List<IModel> models = CollectionUtils.newList(modelFiles.size());
      try
      {
         for (String modelFile : modelFiles)
         {
            File file = new File(modelFile);
            final IConfigurationVariablesProvider confVarProvider = new DefaultConfigurationVariablesProvider();
            if (modelFile.endsWith(XpdlUtils.EXT_XPDL))
            {
               models.add(XpdlUtils.loadXpdlModel(file, confVarProvider, false));
            }
            else
            {
               models.add(new DefaultXMLReader(false, confVarProvider).importFromXML(file));
            }
         }
      }
      finally
      {
         progress.setVisible(false);
      }

      if (deployModel(modelFiles, models))
      {
         JOptionPane.showMessageDialog(null, Internal_ExportMessages.getString("MSG_ModelDeployed")); //$NON-NLS-1$
      }
      else
      {
         JOptionPane.showMessageDialog(null, Internal_ExportMessages.getString("MSG_ModelNotDeployed")); //$NON-NLS-1$
      }

      System.exit(0);
   }

   /**
    * Returns false, if the user vetoes on closing.
    */
   private boolean deployModel(List<String> modelFiles, List<IModel> models)
   {
      boolean deployed = false;
      BpmRuntimeEnvironment runtimeEnvironment = PropertyLayerProviderInterceptor.getCurrent();
      if (runtimeEnvironment == null)
      {
         runtimeEnvironment = new BpmRuntimeEnvironment(null);
         PropertyLayerProviderInterceptor.setCurrent(runtimeEnvironment);
      }
      try
      {
         Map<String, IModel> overrides = CollectionUtils.newMap();
         for (IModel model : models)
         {
            overrides.put(model.getId(), model);
         }
         runtimeEnvironment.setModelOverrides(overrides);
         for (IModel model : models)
         {
            List<Inconsistency> inconsistencies = model.checkConsistency();
            if (inconsistencies.size() > 0)
            {
               int dialogResult = JOptionPane.showConfirmDialog(null,
                  /*Internal_ExportMessages.getString("MSG_InconsistentVersion")*/ //$NON-NLS-1$
                     inconsistencies.get(0).getMessage() + " "
                     + Internal_ExportMessages.getString("MSG_Continue"), //$NON-NLS-1$
                  Internal_ExportMessages.getString("MSG_ModelVersionDeployment"), //$NON-NLS-1$
                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
               if (dialogResult != JOptionPane.OK_OPTION)
               {
                  return false;
               }
            }
         }
      }
      finally
      {
         runtimeEnvironment.setModelOverrides(null);
      }

      try
      {
         getServiceFactory();

         if (version)
         {
            try
            {
               List<DeploymentElement> units = RuntimeUtil.createDeploymentElements(modelFiles);
               DeploymentOptions options = new DeploymentOptions();
               DeploymentUtils.deployFromFiles(serviceFactory, new DeploymentCallback()
               {
                  public void reportErrors(List<Inconsistency> errors)
                  {
                  }

                  public boolean reportWarnings(List<Inconsistency> warnings)
                  {
                     return false;
                  }
               }, units, options);
               deployed = true;
            }
            catch (DeploymentException e)
            {
               e.printStackTrace();
            }
         }
         else if (DeployModelDialog.showDialog(serviceFactory, modelFiles, models, null))
         {
            deployed = true;
         }
      }
      catch (LoginFailedException e)
      {
         if (e.getReason() == LoginFailedException.LOGIN_CANCELLED)
         {
            return deployed;
         }
         JOptionPane.showMessageDialog(null, Internal_ExportMessages.getString("MSG_LoginFailed") //$NON-NLS-1$
               + e.getMessage());
      }
      catch (Exception x)
      {
         trace.warn("", x); //$NON-NLS-1$
         ErrorDialog.showDialog(null, "", x); //$NON-NLS-1$
      }
      finally
      {
         if (serviceFactory != null)
         {
            serviceFactory.close();
         }
      }

      return deployed;
   }

   private synchronized void getServiceFactory()
   {
      if (serviceFactory == null || ServiceFactoryLocator.hasMultipleIdentities())
      {
         if (serviceFactory != null)
         {
            serviceFactory.close();
         }

         if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password))
         {
            /*LoginDialog loginDialog = new LoginDialog(null);
            if (Dialog.OK == loginDialog.open())
            {
               user = loginDialog.getId();
               password = loginDialog.getPassword();
               partition = loginDialog.getPartitionId();
               domain = loginDialog.getDomainId();
               realm = loginDialog.getRealmId();
            }
            else
            {
               System.exit(1);
            }*/
            serviceFactory = ServiceFactoryLocator.get(CredentialProvider.SWING_LOGIN);
            return;
         }

         Map<String, String> credentials = CollectionUtils.newMap();
         credentials.put(SecurityProperties.CRED_USER, user);
         credentials.put(SecurityProperties.CRED_PASSWORD, password);
         if (!StringUtils.isEmpty(domain))
         {
               credentials.put(SecurityProperties.DOMAIN, domain);
            }
         if (!StringUtils.isEmpty(realm))
            {
               credentials.put(SecurityProperties.REALM, realm);
            }
         if (!StringUtils.isEmpty(partition))
            {
               credentials.put(SecurityProperties.PARTITION, partition);
            }
            serviceFactory = ServiceFactoryLocator.get(credentials);
         }
         }
}