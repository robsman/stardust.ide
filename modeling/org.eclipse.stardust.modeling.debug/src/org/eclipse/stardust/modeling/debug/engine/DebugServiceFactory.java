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
package org.eclipse.stardust.modeling.debug.engine;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.common.Functor;
import org.eclipse.stardust.common.TransformingIterator;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.engine.api.model.*;
import org.eclipse.stardust.engine.api.runtime.DeploymentOptions;
import org.eclipse.stardust.engine.api.runtime.ParsedDeploymentUnit;
import org.eclipse.stardust.engine.api.runtime.Service;
import org.eclipse.stardust.engine.core.runtime.beans.*;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.ItemDescription;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.ItemLoader;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.ItemLocatorUtils;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.SecurityProperties;
import org.eclipse.stardust.engine.core.runtime.removethis.EngineProperties;
import org.eclipse.stardust.engine.core.spi.runtime.IServiceProvider;

/**
 * This class is adapted from
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugServiceFactory}.
 *
 * @author sborn
 * @version $Revision$
 */
public class DebugServiceFactory extends DefaultServiceFactory
{
   public static final String DEBUG_ACCOUNT = "workflow-debugger";  //$NON-NLS-1$

   private String password;
   private String username;
   private DebugActivityThreadContext activityThreadContext;

   public DebugServiceFactory(final List<IModel> models)
   {
      activityThreadContext = new DebugActivityThreadContext();

      Parameters.instance().set("ActivityThread.Context", activityThreadContext);  //$NON-NLS-1$
      Parameters.instance().set(EngineProperties.FORKING_SERVICE_HOME,
            new DebugForkingServiceFactory());

      AuditTrailPartitionBean debugPartition = new AuditTrailPartitionBean(DEBUG_ACCOUNT);
      UserDomainBean debugDomain = new UserDomainBean(DEBUG_ACCOUNT, debugPartition, null);

      Parameters.instance().set(SecurityProperties.CURRENT_PARTITION_OID, //
            new Short(debugPartition.getOID()));
      Parameters.instance().set(SecurityProperties.CURRENT_PARTITION, debugPartition);
      Parameters.instance().set(SecurityProperties.CURRENT_DOMAIN_OID, //
            new Long(debugDomain.getOID()));
      Parameters.instance().set(SecurityProperties.CURRENT_DOMAIN, debugDomain);

      ItemLocatorUtils.registerDescription(ModelManagerFactory.ITEM_NAME,
            new ItemDescription(new ItemLoader()
            {
               public Object load()
               {
                  return new ModelManagerBean(new DebugModelLoaderFactory(models));
               }
            }));

      UserRealmBean debugRealm = new UserRealmBean(DEBUG_ACCOUNT, DEBUG_ACCOUNT, debugPartition);
      UserBean debugUser = new UserBean(DEBUG_ACCOUNT, DEBUG_ACCOUNT, DEBUG_ACCOUNT, debugRealm);
      debugUser.setPassword(DEBUG_ACCOUNT);

      for (IModel model : models)
      {
         for (Iterator i = model.getAllWorkflowParticipants(); i.hasNext();)
         {
            IModelParticipant participant = (IModelParticipant) i.next();
            debugUser.addToParticipants(participant, null);
         }
      }

      Map credentials = new HashMap();
      credentials.put(SecurityProperties.CRED_USER, DEBUG_ACCOUNT);
      credentials.put(SecurityProperties.CRED_PASSWORD, DEBUG_ACCOUNT);
      setCredentials(credentials);
   }

   public <T extends Service> T getService(Class<T> type)
   {
      T result = getServiceFromPool(type);
      if (result != null)
      {
         return result;
      }

      IServiceProvider<T> provider = ServiceProviderFactory.findServiceProvider(type);
      InvocationManager manager = new DebugInvocationManager(provider.getInstance());
      result = (T) Proxy.newProxyInstance(type.getClassLoader(),
            new Class[] {type, ManagedService.class}, manager);
      putServiceToPool(type, result);

      Map props = new HashMap();
      props.put(SecurityProperties.PARTITION, DEBUG_ACCOUNT);
      props.put(SecurityProperties.REALM, DEBUG_ACCOUNT);
      props.put(SecurityProperties.DOMAIN, DEBUG_ACCOUNT);

      ((ManagedService) result).login(username, password, props);
      return result;
   }

   public void setCredentials(Map credentials)
   {
      username = (String) credentials.get(SecurityProperties.CRED_USER);
      password = (String) credentials.get(SecurityProperties.CRED_PASSWORD);
   }

   public void close()
   {
      super.close();
      ItemLocatorUtils.unregisterDescription(ModelManagerFactory.ITEM_NAME);
   }

   public DebugActivityThreadContext getActivityThreadContext()
   {
      return activityThreadContext;
   }

   public static final class DebugModelLoaderFactory extends AbstractModelLoaderFactory
   {
      private List<IModel> models;

      public DebugModelLoaderFactory(List<IModel> models)
      {
         this.models = models;
      }

      public ModelLoader instance(short partitionOid)
      {
         return new DebugModelLoader(models, partitionOid);
      }
   }

   private static final class DebugModelLoader implements ModelLoader
   {
      private List<IModel> models;
      private short partitionOid;

      private DebugModelLoader(List<IModel> models, short partitionOid)
      {
         this.models = models;
         this.partitionOid = partitionOid;
      }

      public void loadRuntimeOidRegistry(IRuntimeOidRegistry rtOidRegistry)
      {
         for (IModel model : models)
         {
            loadRuntimeOidRegistry(rtOidRegistry, model);
         }
      }

      private void loadRuntimeOidRegistry(IRuntimeOidRegistry rtOidRegistry, IModel model)
      {
         // debug mode runtime OID is just the model element's element OIDs
         // modified by partitionOid.

         // load data runtime OIDs
         for (IData data : model.getData())
         {
            rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.DATA,
                  RuntimeOidUtils.getFqId(data),
                  getPartitionAwareRtOid(data.getElementOID()));
         }

         // load model participant runtime OIDs
         for (IModelParticipant participant : model.getParticipants())
         {
            if (!(participant instanceof IModeler))
            {
               rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.PARTICIPANT,
                     RuntimeOidUtils.getFqId(participant),
                     getPartitionAwareRtOid(participant.getElementOID()));
            }
         }

         // load process definition runtime OIDs
         for (IProcessDefinition process : model.getProcessDefinitions())
         {
            rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.PROCESS,
                  RuntimeOidUtils.getFqId(process),
                  getPartitionAwareRtOid(process.getElementOID()));

            // load trigger runtime OIDs
            for (ITrigger trigger : process.getTriggers())
            {
               rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.TRIGGER,
                     RuntimeOidUtils.getFqId(trigger),
                     getPartitionAwareRtOid(trigger.getElementOID()));
            }

            // load activity runtime OIDs
            for (IActivity activity : process.getActivities())
            {
               rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.ACTIVITY,
                     RuntimeOidUtils.getFqId(activity),
                     getPartitionAwareRtOid(activity.getElementOID()));

               // load event handler runtime OIDs
               for (IEventHandler handler : activity.getEventHandlers())
               {
                  rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.EVENT_HANDLER,
                        RuntimeOidUtils.getFqId(handler),
                        getPartitionAwareRtOid(handler.getElementOID()));
               }
            }

            // load transition runtime OIDs
            for (ITransition transition : process.getTransitions())
            {
               rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.TRANSITION,
                     RuntimeOidUtils.getFqId(transition),
                     getPartitionAwareRtOid(transition.getElementOID()));
            }

            // load event handler runtime OIDs
            for (IEventHandler handler : process.getEventHandlers())
            {
               rtOidRegistry.registerRuntimeOid(IRuntimeOidRegistry.EVENT_HANDLER,
                     RuntimeOidUtils.getFqId(handler),
                     getPartitionAwareRtOid(handler.getElementOID()));
            }
         }
      }

      private long getPartitionAwareRtOid(int elementOid)
      {
         return elementOid
               + ((partitionOid - 1l) << RuntimeOidRegistry.PARTITION_PART_SHIFT);
      }

      public Iterator loadModels()
      {
         return new TransformingIterator<IModel, ModelPersistorBean>(models.iterator(), new Functor<IModel, ModelPersistorBean>()
         {
            int currentModelOid = 1;

            public ModelPersistorBean execute(final IModel source)
            {
               final int modelOid = currentModelOid++;
               return new ModelPersistorBean()
               {
                  private static final long serialVersionUID = 1L;

                  public IModel fetchModel()
                  {
                     source.setModelOID(modelOid);
                     return source;
                  }

                  public IAuditTrailPartition getPartition()
                  {
                     return SecurityProperties.getPartition();
                  }

                  public long getPredecessorOID()
                  {
                     return models.indexOf(source) - 1;
                  }

                  public long getModelOID()
                  {
                     return modelOid;
                  }

                  public String getId()
                  {
                     return source.getId();
                  }

                  public long getOID()
                  {
                     return modelOid;
                  }
               };
            }
         });
      }

      public void deployModel(List<ParsedDeploymentUnit> units,
            DeploymentOptions options, IRuntimeOidRegistry rtOidRegistry)
      {
         throw new UnsupportedOperationException();
      }

      public void modifyModel(ParsedDeploymentUnit unit, DeploymentOptions options,
            IRuntimeOidRegistry rtOidRegistry)
      {
         throw new UnsupportedOperationException();
      }
   }
}
