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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.common.security.authentication.LoginFailedException;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.api.runtime.*;
import org.eclipse.stardust.engine.cli.common.DeploymentCallback;
import org.eclipse.stardust.engine.cli.common.DeploymentUtils;
import org.eclipse.stardust.engine.core.compatibility.gui.ErrorDialog;
import org.eclipse.stardust.engine.core.model.beans.DefaultConfigurationVariablesProvider;
import org.eclipse.stardust.engine.core.model.beans.DefaultXMLReader;
import org.eclipse.stardust.engine.core.model.beans.IConfigurationVariablesProvider;
import org.eclipse.stardust.engine.core.model.parser.info.ExternalPackageInfo;
import org.eclipse.stardust.engine.core.model.parser.info.ModelInfo;
import org.eclipse.stardust.engine.core.model.parser.info.ModelInfoRetriever;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.beans.BpmRuntimeEnvironment;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.PropertyLayerProviderInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.SecurityProperties;

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

      trace.info(Deploy_Messages.getString("MSG_Starting")); //$NON-NLS-1$

      List<File> modelFiles = CollectionUtils.newList();

      for (int i = 0; i < args.length; i++)
      {
         if ("--filename".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            modelFiles.add(new File(args[++i]));
         }
         else if ("--filename64".equals(args[i]) && ((i + 1) < args.length)) //$NON-NLS-1$
         {
            try
            {
               modelFiles.add(new File(new String(Base64.decode(args[++i].getBytes()), XpdlUtils.UTF8_ENCODING)));
            }
            catch (UnsupportedEncodingException e)
            {
               // should never happen since UTF-8 is standard supported on all java versions.
               e.printStackTrace();
            }
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

      trace.info(Deploy_Messages.getString("MSG_DeployModel") + modelFiles); //$NON-NLS-1$
      
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
         trace.error(Deploy_Messages.getString("MSG_NoModelSpecified")); //$NON-NLS-1$
      }
   }

   public ModelDeploymentTool(List<File> modelFiles)
   {
         ProgressDialog progress = ProgressDialog.showDialog(null,
               Deploy_Messages.getString("MSG_LoadingModel"), //$NON-NLS-1$
               ProgressDialog.ON_CANCEL_EXIT_WITH_MINUS_ONE);

      BpmRuntimeEnvironment runtimeEnvironment = PropertyLayerProviderInterceptor.getCurrent();
      List<IModel> models = CollectionUtils.newList(modelFiles.size());
      try
      {
         // 1. Collect model references.
         Map<String, File> fileMap = CollectionUtils.newMap();
         Map<String, ModelInfo> infoMap = CollectionUtils.newMap();
         for (File file : modelFiles)
         {
            try
            {
               ModelInfo info = ModelInfoRetriever.get(file);
               infoMap.put(info.id, info);
               fileMap.put(info.id, file);
            }
            catch (Exception e)
            {
               // TODO: (fh)
               e.printStackTrace();
            }
         }
         
         // 2. order models and prepare overrides
         if (runtimeEnvironment == null)
         {
            runtimeEnvironment = new BpmRuntimeEnvironment(null);
            PropertyLayerProviderInterceptor.setCurrent(runtimeEnvironment);
         }
         Map<String, IModel> overrides = CollectionUtils.newMap();
         runtimeEnvironment.setModelOverrides(overrides);
         for (String modelId : orderModels(infoMap))
         {
            IConfigurationVariablesProvider confVarProvider 
               = new DefaultConfigurationVariablesProvider();
            File file = fileMap.get(modelId);
            IModel model = null;
            if (file.getName().endsWith(XpdlUtils.EXT_XPDL))
            {
               model = XpdlUtils.loadXpdlModel(file, confVarProvider, false);
               
            }
            else
            {
               model = new DefaultXMLReader(false, confVarProvider).importFromXML(file);
            }
            models.add(model);
            overrides.put(modelId, model);
         }
      }
      finally
      {
//         runtimeEnvironment.setModelOverrides(null); why is that needed ?
         progress.setVisible(false);
      }

      if (deployModel(modelFiles, models))
      {
         JOptionPane.showMessageDialog(null, Deploy_Messages.getString("MSG_ModelDeployed")); //$NON-NLS-1$
      }
      else
      {
         JOptionPane.showMessageDialog(null, Deploy_Messages.getString("MSG_ModelNotDeployed")); //$NON-NLS-1$
      }

      System.exit(0);
   }

   private List<String> orderModels(Map<String, ModelInfo> infos)
   {
      List<String> orderedModelIds = CollectionUtils.newList();
      Set<ModelInfo> visited = CollectionUtils.newSet();
      for (ModelInfo info : infos.values())
      {
         addModel(orderedModelIds, info, infos, visited);
      }
      return orderedModelIds;
   }

   private void addModel(List<String> orderedModelIds, ModelInfo info, Map<String, ModelInfo> infos, Set<ModelInfo> visited)
   {
      if (info != null && !visited.contains(info))
      {
         visited.add(info);
         if (info.externalPackages != null)
         {
            for (ExternalPackageInfo ref : info.externalPackages)
            {
               addModel(orderedModelIds, infos.get(ref.href), infos, visited);
            }
         }
         orderedModelIds.add(info.id);
      }
   }

   /**
    * Returns false, if the user vetoes on closing.
    */
   private boolean deployModel(List<File> modelFiles, List<IModel> models)
   {
      boolean deployed = false;
      for (IModel model : models)
      {
         @SuppressWarnings("unchecked")
         List<Inconsistency> inconsistencies = model.checkConsistency();
         if (inconsistencies.size() > 0)
         {
            int dialogResult = JOptionPane.showConfirmDialog(null,
               /*Internal_ExportMessages.getString("MSG_InconsistentVersion")*/ //$NON-NLS-1$
                  inconsistencies.get(0).getMessage() + " "
                  + Deploy_Messages.getString("MSG_Continue"), //$NON-NLS-1$
                  Deploy_Messages.getString("MSG_ModelVersionDeployment"), //$NON-NLS-1$
               JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (dialogResult != JOptionPane.OK_OPTION)
            {
               return false;
            }
         }
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
         JOptionPane.showMessageDialog(null, Deploy_Messages.getString("MSG_LoginFailed") //$NON-NLS-1$
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