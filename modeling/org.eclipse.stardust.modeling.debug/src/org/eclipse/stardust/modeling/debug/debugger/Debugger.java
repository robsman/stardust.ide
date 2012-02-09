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
package org.eclipse.stardust.modeling.debug.debugger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.ObjectNotFoundException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.api.model.IActivity;
import org.eclipse.stardust.engine.api.model.IApplicationContextType;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataMapping;
import org.eclipse.stardust.engine.api.model.IDataType;
import org.eclipse.stardust.engine.api.model.IExternalPackage;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.ImplementationType;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityInstance;
import org.eclipse.stardust.engine.api.runtime.ProcessInstance;
import org.eclipse.stardust.engine.api.runtime.WorkflowService;
import org.eclipse.stardust.engine.core.model.beans.DefaultXMLReader;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.persistence.jdbc.SessionFactory;
import org.eclipse.stardust.engine.core.runtime.beans.BpmRuntimeEnvironment;
import org.eclipse.stardust.engine.core.runtime.beans.IActivityInstance;
import org.eclipse.stardust.engine.core.runtime.beans.IProcessInstance;
import org.eclipse.stardust.engine.core.runtime.beans.ModelManagerFactory;
import org.eclipse.stardust.engine.core.runtime.beans.ModelManagerLoader;
import org.eclipse.stardust.engine.core.runtime.beans.NullWatcher;
import org.eclipse.stardust.engine.core.runtime.beans.ProcessInstanceBean;
import org.eclipse.stardust.engine.core.runtime.beans.TransitionTokenBean;
import org.eclipse.stardust.engine.core.runtime.beans.interceptors.PropertyLayerProviderInterceptor;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.ItemDescription;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.ItemLocatorUtils;
import org.eclipse.stardust.engine.core.runtime.removethis.EngineProperties;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.TransitionTokenDigest;
import org.eclipse.stardust.modeling.debug.engine.DebugActivityThreadContext;
import org.eclipse.stardust.modeling.debug.engine.DebugServiceFactory;
import org.eclipse.stardust.modeling.debug.engine.DebugSession;
import org.eclipse.stardust.modeling.debug.engine.WorkflowCompletionWaiter;
import org.eclipse.stardust.modeling.debug.engine.WorkflowEventListener;

/**
 * @author sborn
 * @version $Revision$
 */
public class Debugger
{
   private static final String CARNOT_SPRING_DEBUGGING_CONTEXT_XML = "carnot-spring-debugging-context.xml"; //$NON-NLS-1$
   private static final Logger trace = LogManager.getLogger(Debugger.class);
   private static final String unsupportedImplType = Internal_Debugger_Messages.getString("MSG_UnsupportedActivityImplType"); //$NON-NLS-1$
   
   private static Object syncObject = new Object();
   
   private List<IModel> oldStyleModels;
   private IProcessDefinition oldStyleProcessDefinition;
   
   private DebugServiceFactory debugServiceFactory;
   private DebugActivityThreadContext activityThreadContext;
   private WorkflowEventListenerImpl workflowEventListener;
   
   private ThreadGroup helperThreadGroup;
   
   public static void main(String[] args)
   {
      Debugger instance = null;
      try
      {
         instance = new Debugger(new DebuggerArgument(args));
         Thread thread = new ProcessInstantiatorThread(instance);
         thread.start();
         
         synchronized(syncObject)
         {
            // This prevents Main thread termination 
            syncObject.wait();
         }
      }
      catch (Throwable x)
      {
         x.printStackTrace();
         throw new InternalException(Constants.EMPTY, x);
      }
      finally
      {
         if (null != instance)
         {
            instance.stop();
         }
      }
   }
   
   public Debugger(DebuggerArgument arg) throws CoreException
   {
      // Workaround (rsauer): fixing #5261 (route activities with performers are to be accepted for analyst models)
      boolean analystView = "analyst".equals(arg.getViewType()); //$NON-NLS-1$
      Parameters.instance().setBoolean("Carnot.Internal.EnableDebuggerHacks", analystView); //$NON-NLS-1$
      
      List<String> deps = arg.getDependencyPaths();
      List<String> paths = CollectionUtils.newList(deps.size() + 1);
      paths.add(arg.getModelPath());
      paths.addAll(deps);
      
      oldStyleModels = CollectionUtils.newList(paths.size());
      for (String modelPath : paths)
      {
         System.err.println("Bootstrap: loading model from: " + modelPath); //$NON-NLS-1$
         File modelFile = new File(modelPath);
         try
         {
            IModel oldStyleModel = importModel(modelFile);
            if (analystView)
            {
               fixModel(oldStyleModel);
            }
            oldStyleModels.add(oldStyleModel);
         }
         catch(FileNotFoundException x)
         {
            throw new PublicException(Internal_Debugger_Messages.getString("MSG_FileForCurrentModelNotFound"), x); //$NON-NLS-1$
         }
      }
      
      oldStyleProcessDefinition = oldStyleModels.get(0).findProcessDefinition(arg.getProcessDefinitionId());
      if (null == oldStyleProcessDefinition)
      {
         throw new ObjectNotFoundException(MessageFormat.format(
               Internal_Debugger_Messages.getString("EXP_ProcessDefinitionNotFoundInModel"), //$NON-NLS-1$
               arg.getProcessDefinitionId()));
      }
      
      if (false == init())
      {
         throw new CoreException(new Status(IStatus.CANCEL, Constants.ID_CWM_DEBUG_MODEL,
               0, Internal_Debugger_Messages.getString("EXP_DebuggingCanceled"), null)); //$NON-NLS-1$
      }
   }
   
   private void fixModel(IModel model)
   {
      fixData(model);
      fixProcesses(model);
   }

   private void fixProcesses(IModel model)
   {
      for (Iterator i = model.getAllProcessDefinitions(); i.hasNext(); )
      {
         IProcessDefinition definition = (IProcessDefinition) i.next();
         fixActivities(model, definition);
      }
   }

   private void fixActivities(IModel model, IProcessDefinition definition)
   {
      for (Iterator i = definition.getAllActivities(); i.hasNext(); )
      {
         IActivity activity = (IActivity) i.next();
         ImplementationType implementation = activity.getImplementationType();
         if (!ImplementationType.SubProcess.equals(implementation))
         {
            activity.setImplementationType(activity.getPerformer() == null ?
                  ImplementationType.Route : ImplementationType.Manual);
         }
         if (activity.getAllDataMappings().hasNext())
         {
            fixDataMappings(model, activity);
         }
      }
   }

   private void fixDataMappings(IModel model, IActivity activity)
   {
      if (ImplementationType.Manual.equals(activity.getImplementationType()))
      {
         List contexts = new ArrayList();
         // collect contexts having mappings
         for (Iterator i = activity.getAllDataMappings(); i.hasNext(); )
         {
            IDataMapping mapping = (IDataMapping) i.next();
            if (mapping.getContext().equals(PredefinedConstants.DEFAULT_CONTEXT))
            {
               // nothing to fix, default context mappings already present.
               return;
            }
            if (!contexts.contains(mapping.getContext()))
            {
               contexts.add(mapping.getContext());
            }
         }
         for (Iterator i = model.getAllApplicationContextTypes(); i.hasNext(); )
         {
            IApplicationContextType type = (IApplicationContextType) i.next();
            if (contexts.contains(type.getId()))
            {
               replaceContext(activity, type.getId());
               return;
            }
         }
         replaceContext(activity, (String) contexts.get(0));
      }
   }

   private void replaceContext(IActivity activity, String id)
   {
      // in ENGINE_CONTEXT no change is necessary
      if (!PredefinedConstants.ENGINE_CONTEXT.equals(id))
      {
         for (Iterator i = activity.getAllDataMappings(); i.hasNext(); )
         {
            IDataMapping mapping = (IDataMapping) i.next();
            if (id.equals(mapping.getContext()))
            {
               mapping.setContext(PredefinedConstants.DEFAULT_CONTEXT);
            }
         }
      }
   }

   private void fixData(IModel model)
   {
      IDataType primitive = null;
      IDataType serializable = null;
      
      for (Iterator i = model.getAllDataTypes(); i.hasNext(); )
      {
         IDataType type = (IDataType) i.next();
         if (PredefinedConstants.PRIMITIVE_DATA.equals(type.getId()) && primitive == null)
         {
            primitive = type;
         }
         if (PredefinedConstants.SERIALIZABLE_DATA.equals(type.getId()) && serializable == null)
         {
            serializable = type;
         }
      }
      
      for (Iterator i = model.getAllData(); i.hasNext(); )
      {
         IData data = (IData) i.next();
         if (data.getType() == null)
         {
            String hint = (String) data.getAttribute(CarnotConstants.DATA_TYPE_HINT_ATT);
            if (CarnotConstants.TEXT_HINT.equals(hint) && primitive != null)
            {
               trace.debug("Setting data type primitive string for " + data); //$NON-NLS-1$
               data.setDataType(primitive);
               data.setAttribute(PredefinedConstants.TYPE_ATT,
                     org.eclipse.stardust.engine.core.pojo.data.Type.String);
               data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                  Boolean.TRUE);
            }
            else if (CarnotConstants.NUMERIC_HINT.equals(hint) && primitive != null)
            {
               trace.debug("Setting data type primitive double for " + data); //$NON-NLS-1$
               data.setDataType(primitive);
               data.setAttribute(PredefinedConstants.TYPE_ATT,
                     org.eclipse.stardust.engine.core.pojo.data.Type.Double);
               data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                  Boolean.TRUE);
            }
            else if (CarnotConstants.COMPLEX_HINT.equals(hint) && serializable != null)
            {
               trace.debug("Setting data type serializable for " + data); //$NON-NLS-1$
               data.setDataType(serializable);
               data.setAttribute(PredefinedConstants.CLASS_NAME_ATT,
                     "java.io.Serializable"); //$NON-NLS-1$
               data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                     Boolean.FALSE);
            }
            else if (primitive != null)
            {
               trace.debug("Setting data type primitive string for " + data); //$NON-NLS-1$
               data.setDataType(primitive);
               data.setAttribute(PredefinedConstants.TYPE_ATT,
                     org.eclipse.stardust.engine.core.pojo.data.Type.String);
               data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                  Boolean.FALSE);
            }
            else if (serializable != null)
            {
               trace.debug("Setting data type serializable for " + data); //$NON-NLS-1$
               data.setDataType(serializable);
               data.setAttribute(PredefinedConstants.CLASS_NAME_ATT,
                     "java.io.Serializable"); //$NON-NLS-1$
               data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                     Boolean.FALSE);
            }
            else
            {
               trace.warn("No suitable data type found for " + data); //$NON-NLS-1$
            }
         }
         else
         {
            List inc = new ArrayList();
            data.checkConsistency(inc);
            for (Iterator iter = inc.iterator(); iter.hasNext();)
            {
               Inconsistency element = (Inconsistency) iter.next();
               if (element.getSeverity() == Inconsistency.ERROR)
               {
                  if (primitive.equals(data.getType()))
                  {
                     data.setAttribute(PredefinedConstants.TYPE_ATT,
                           org.eclipse.stardust.engine.core.pojo.data.Type.String);
                     data.setAttribute(PredefinedConstants.BROWSABLE_ATT,
                        Boolean.FALSE);
                  }
                  else if (serializable.equals(data.getType()))
                  {
                     data.setAttribute(PredefinedConstants.CLASS_NAME_ATT,
                        "java.io.Serializable"); //$NON-NLS-1$
                  }
                  else if (data.getAttribute(PredefinedConstants.CLASS_NAME_ATT) != null)
                  {
                     data.setDataType(serializable);
                  }
                  else
                  {
                     data.setDataType(serializable);
                     data.setAttribute(PredefinedConstants.CLASS_NAME_ATT,
                           "java.io.Serializable"); //$NON-NLS-1$
                  }
                  break;
               }
            }
         }
      }
   }

   public boolean init()
   {
      try
      {
         // simulating bootstrapping, but without needing a license (see EngineService.init())
         ItemLocatorUtils.registerDescription(ModelManagerFactory.ITEM_NAME,
               new ItemDescription(new ModelManagerLoader(),
                     Parameters.instance().getString(
                           EngineProperties.WATCHER_PROPERTY, NullWatcher.class.getName())));

         // keep as char array to make reverse engineering a bit harder
         Parameters.instance().setBoolean(
               String.valueOf(new char[] {
                     'E', 'n', 'g', 'i', 'n', 'e', '.', 'B', 'o', 'o', 't', 's', 't',
                     'r', 'a', 'p', 'p', 'e', 'd'}), true);
         
         Parameters.instance().set(Constants.CURRENT_DEBUGGER_PARAM, this);
         
         helperThreadGroup = new ThreadGroup(Constants.THREAD_GROUP_HELPER_THREAD);
         Parameters.instance().set(Constants.THREAD_GROUP_HELPER_THREAD_PARAM,
               helperThreadGroup);
         Parameters.instance().set(Constants.THREAD_GROUP_ACTIVITY_THREAD_PARAM,
               new ThreadGroup(Constants.THREAD_GROUP_ACTIVITY_THREAD));
         Parameters.instance().set(Constants.THREAD_GROUP_ON_COMPLETION_THREAD_PARAM,
               new ThreadGroup(Constants.THREAD_GROUP_ON_COMPLETION_THREAD));
         
         DebugSession debugSession = new DebugSession();
         Parameters.instance().set(
               SessionFactory.AUDIT_TRAIL + SessionFactory.DS_SESSION_SUFFIX,
               debugSession);

         // bootstrap external packages
         BpmRuntimeEnvironment rtEnv = (BpmRuntimeEnvironment) ParametersFacade.pushLayer(
               Parameters.instance(), PropertyLayerProviderInterceptor.BPM_RT_ENV_LAYER_FACTORY,
               CollectionUtils.<String, Object>newMap());
         Map<String, IModel> overrides = CollectionUtils.newMap();
         for (IModel oldStyleModel : oldStyleModels)
         {
            overrides.put(oldStyleModel.getId(), oldStyleModel);
         }
         rtEnv.setModelOverrides(overrides);
         PropertyLayerProviderInterceptor.setCurrent(rtEnv);
         for (IModel model : oldStyleModels)
         {
            List<IExternalPackage> packages = model.getExternalPackages();
            if (packages != null)
            {
               for (IExternalPackage pkg : packages)
               {
                  IModel ref = pkg.getReferencedModel();
                  System.err.println("Bootstrap: " + model + " uses " + ref); //$NON-NLS-1$ //$NON-NLS-2$
               }
            }
         }
         
         debugServiceFactory = new DebugServiceFactory(oldStyleModels);
         Parameters.instance().set(Constants.CURRENT_SERVICE_FACTORY_PARAM,
               debugServiceFactory);
         activityThreadContext = debugServiceFactory.getActivityThreadContext();
         
         workflowEventListener = new WorkflowEventListenerImpl();
         activityThreadContext.addToWorkflowEventListeners(workflowEventListener);
         
         initSpringEnvironment();
      }
      catch (Exception e)
      {
         try
         {
            stop();
         }
         catch (Exception ex)
         {
            trace.warn(Internal_Debugger_Messages.getString("MSG_UnexpectedExceptionWhileStoppingDebugger"), ex); //$NON-NLS-1$
         }
         
         throw new InternalException(e);
      }
      
      return true;
   }
   
   public void addWorkflowEventListener(WorkflowEventListener listener)
   {
      activityThreadContext.addToWorkflowEventListeners(listener);
   }
   
   public void start()
   {
      try
      {
         ProcessInstance processInstance;

         WorkflowService workflowService = debugServiceFactory.getWorkflowService();
         try
         {
            IModel model = oldStyleModels.get(0);
            String qualifiedId = '{' + model.getId() + '}' + oldStyleProcessDefinition.getId();
            System.err.println("Bootstrap: starting process " + qualifiedId); //$NON-NLS-1$
            processInstance = workflowService.startProcess(qualifiedId, null, false);
         }
         finally
         {
            debugServiceFactory.release(workflowService);
         }
         
         if (null != processInstance)
         {
            IProcessInstance rootProcInst = ProcessInstanceBean.findByOID(processInstance
                  .getRootProcessInstanceOID());
            
            Runnable runnable = new WorkflowCompletionWaiter(this, rootProcInst);
            ThreadGroup group = (ThreadGroup) Parameters.instance().get(
                  Constants.THREAD_GROUP_ON_COMPLETION_THREAD_PARAM);
            new Thread(group, runnable).start();
         }
      }
      catch (Exception e)
      {
         try
         {
            stop();
         }
         catch (Exception ex)
         {
            trace.warn(Internal_Debugger_Messages.getString("MSG_UnexpectedExceptionWhileStoppingDebugger"), ex); //$NON-NLS-1$
         }
         throw new PublicException(e);
      }
   }
   
   public void stop()
   {
      if (debugServiceFactory != null)
      {
         debugServiceFactory.close();
      }
   }
   
   /**
    * Completes the application. Either calls the complete method of a user defined
    * GUI or closes an ActiveX application.
    */
   public static void completeActivityInstance(WorkflowService service, ActivityInstance activityInstance)
   {
      Assert.isNotNull(service);
      
      ImplementationType type = activityInstance.getActivity().getImplementationType();

      if (type == ImplementationType.Manual)
      {
         service.complete(activityInstance.getOID(),
               PredefinedConstants.DEFAULT_CONTEXT,
               processManualOutDataMappings(activityInstance.getActivity()
                     .getApplicationContext(PredefinedConstants.DEFAULT_CONTEXT)
                     .getAllOutDataMappings().iterator()));
      }
      // TODO (sb): implement!
      /*else if (type == ImplementationType.Application)
      {
         if (!activityInstance.getActivity().isInteractive())
         {
            return;
         }
         else
         {
            Activity activity = activityInstance.getActivity();
            ApplicationContext ctx = activity.getApplicationContext(
                  PredefinedConstants.JFC_CONTEXT);
            if (ctx == null)
            {
               JOptionPane.showMessageDialog(null, "The activity can not be completed "
                  + "because it doesn't have a valid JFC context.");
               return;
            }
            Map result = applicationInstance.invoke(getUsedAccessPoints(
               ctx.getAllOutDataMappings().iterator()));
            session.complete(activityInstance.getOID(), PredefinedConstants.JFC_CONTEXT,
               processOutDataMappings(result,ctx.getAllOutDataMappings().iterator()));
         }
      }*/
      else
      {
         throw new InternalException(MessageFormat.format(
               unsupportedImplType, new Object[] { type }));
      }
   }
   
   /**
    * @see WorkflowEventListenerImpl#getNextActivityInstance()
    */
   public IActivityInstance getNextActivityInstance()
   {
      return workflowEventListener.getNextActivityInstance();
   }
   
   public static List getAllDataMappings(ActivityInstance activityInstance)
   {
      if (activityInstance == null)
      {
         throw new PublicException(Internal_Debugger_Messages.getString("EXP_NoActivityInstanceSetOnWorkflowSession")); //$NON-NLS-1$
      }
      
      ImplementationType implementationType = activityInstance.getActivity()
            .getImplementationType();

      if (implementationType == ImplementationType.Manual)
      {
         return activityInstance.getActivity().getApplicationContext(
               PredefinedConstants.DEFAULT_CONTEXT).getAllDataMappings();
      }
      
      throw new InternalException(MessageFormat.format(unsupportedImplType,
            new Object[] { implementationType }));
   }
   
   public Map getInDataValues(IActivityInstance activityInstance)
   {
      WorkflowService workflowService = debugServiceFactory.getWorkflowService();
      try
      {
         return workflowService.getInDataValues(activityInstance.getOID(),
               PredefinedConstants.DEFAULT_CONTEXT, null);   
      }
      finally
      {
         debugServiceFactory.release(workflowService);
      }
   }

   /**
    * @return Returns the helperThreadGroup.
    */
   public ThreadGroup getHelperThreadGroup()
   {
      return helperThreadGroup;
   }
   
   /**
    * Retrieves the application out parameters and sends them to the server.
    * If no output parameters are specified, no call is made to the server.
    */
   private static Map processManualOutDataMappings(Iterator outMappings)
   {
      // TODO (sb): implement!
      //Map values = manualInterpreter.getValues();
      Map outData = new HashMap();
   
      /*while (outMappings.hasNext())
      {
         DataMapping mapping = (DataMapping) outMappings.next();
         outData.put(mapping.getId(), values.get(mapping.getId()));
      }*/
      return outData;
   }
   
   private void initSpringEnvironment()
   {
      boolean springInitiated = false;
      
      try
      {
         boolean hasSpringContext = false;
         
         Class clsSpringUtils = null;
         try
         {
            clsSpringUtils = Class.forName("ag.carnot.workflow.runtime.spring.SpringUtils"); //$NON-NLS-1$
            File file = new File(CARNOT_SPRING_DEBUGGING_CONTEXT_XML);
            if (file.exists())
            {
               hasSpringContext = true;
            }
         }
         catch (Exception e)
         {
            // ignore it
         }

         if (hasSpringContext)
         {
            Parameters.instance().set("Carnot.Spring.ApplicationContextFile", //$NON-NLS-1$
                  CARNOT_SPRING_DEBUGGING_CONTEXT_XML);
            
            Method mthdGetAppContext = clsSpringUtils.getDeclaredMethod(
                  "getApplicationContext", new Class[0]); //$NON-NLS-1$
            if (null != mthdGetAppContext)
            {
               Object context = mthdGetAppContext.invoke(null, new Object [0]);
               if (null != context)
               {
                  Parameters.instance().set(
                        "ag.carnot.workflow.runtime.spring.applicationContext", context); //$NON-NLS-1$

                  springInitiated = true;
               }
            }
         }
      }
      catch (Exception e)
      {
         trace.warn("", e); //$NON-NLS-1$
      }
      
      if (springInitiated)
      {
         trace.info("Spring RT support is available."); //$NON-NLS-1$
      }
      else
      {
         trace.info("Spring RT support is not available."); //$NON-NLS-1$
      }
   }

   private IModel importModel(File modelFile) throws FileNotFoundException
   {
      if (modelFile.getName().endsWith(XpdlUtils.EXT_XPDL))
      {
         return XpdlUtils.loadXpdlModel(modelFile);
      }
      else
      {
         return new DefaultXMLReader(true).importFromXML(new FileInputStream(modelFile));
      }
   }

   public static class ProcessInstantiatorThread extends Thread
   {
      private Debugger debugger;
      
      public ProcessInstantiatorThread(Debugger debugger)
      {
         super(debugger.getHelperThreadGroup(), "ProcessInstantiator");  //$NON-NLS-1$
         this.debugger = debugger;
      }
      
      public void run()
      {
         debugger.start();
      }
   }
   
   /**
    * This class implements the WorkflowEventListener interface and precomputes the 
    * neccessary detailed values for communication with the debugging engine.
    * 
    * @author sborn
    * @version $Revision$
    */
   /**
    * @author sborn
    * @version $Revision$
    */
   public static class WorkflowEventListenerImpl implements WorkflowEventListener
   {
      public static String performedTransitionMethodName = "performedTransition"; //$NON-NLS-1$
      public static String appendedToWorklistMethodName = "appendedToWorklist"; //$NON-NLS-1$
      public static String startedActivityInstanceMethodName = "startedActivityInstance"; //$NON-NLS-1$
      public static String completedActivityInstanceMethodName = "completedActivityInstance"; //$NON-NLS-1$
      
      private Map/*<Thread, List<IActivityInstance>>*/ threadMap;
      
      public WorkflowEventListenerImpl()
      {
         threadMap = new HashMap();
      }
      
      /**
       * DO NOT CHANGE THE NAMES OF THE PARAMETERS!!!
       */
      private void performedTransition(TransitionTokenDigest transToken)
      {
         // This method suspends the current thread which will signal this 
         // action and can be handled by the implementation.
         // The thread will be resumed afterwards.
      }

      /**
       * DO NOT CHANGE THE NAMES OF THE PARAMETERS!!!
       */
      private void appendedToWorklist(ActivityInstanceDigest activityInstance)
      {
         // This method suspends the current thread which will signal this 
         // action and can be handled by the implementation.
         // The thread will be resumed afterwards.
      }

      /**
       * DO NOT CHANGE THE NAMES OF THE PARAMETERS!!!
       */
      private void startedActivityInstance(ActivityInstanceDigest activityInstance)
      {
         // This method suspends the current thread which will signal this 
         // action and can be handled by the implementation.
         // The thread will be resumed afterwards.
      }

      /**
       * DO NOT CHANGE THE NAMES OF THE PARAMETERS!!!
       */
      private void completedActivityInstance(ActivityInstanceDigest activityInstance)
      {
         // This method suspends the current thread which will signal this 
         // action and can be handled by the implementation.
         // The thread will be resumed afterwards.
      }
      
      private void recordActivityInstance(IActivityInstance activityInstance)
      {
         Thread thread = Thread.currentThread();
         
         synchronized (threadMap)
         {
            List activityInstances = (List) threadMap.get(thread);
            if (null == activityInstances)
            {
               activityInstances = new ArrayList();
               threadMap.put(thread, activityInstances);
            }
            
            activityInstances.add(activityInstance);
         }
      }
      
      private void deleteActivityInstance(IActivityInstance activityInstance)
      {
         Thread currentThread = Thread.currentThread();

         synchronized (threadMap)
         {
            List activityInstances = (List) threadMap.get(currentThread);
            if (null == activityInstances ||
                  !activityInstances.contains(activityInstance))
            {
               // Has this activity instance been recorded by another thread?
               List temp = null;
               for (Iterator iter = threadMap.entrySet().iterator(); iter.hasNext();)
               {
                  Map.Entry entry = (Map.Entry) iter.next();
                  temp = (List) entry.getValue();

                  if (temp.contains(activityInstance))
                  {
                     Thread creatingThread = (Thread) entry.getKey();
                     trace.info(MessageFormat.format(
                           Internal_Debugger_Messages.getString("MSG_ActivityInstanceCompletedByDifferentThread"), //$NON-NLS-1$
                           new Object[] { activityInstance, currentThread, creatingThread }));
                     activityInstances = temp;
                     break;
                  }
               }
            }
            
            if (null != activityInstances)
            {
               activityInstances.remove(activityInstance);
            }
         }
      }
      
      /**
       * This method returns the next uncompleted activity instance for the current thread.
       * 
       * @return null if no further activity instance exists.
       * @throws InternalException if more than one uncompleted activity instance exists.
       */
      public IActivityInstance getNextActivityInstance()
      {
         Thread thread = Thread.currentThread();
         IActivityInstance ai = null;
         
         synchronized (threadMap)
         {
            List activityInstances = (List) threadMap.get(thread);
            if (null != activityInstances && activityInstances.size() > 0)
            {
               // Get last created activity instance of this thread.
               // More than one activated activity instances should only happen for 
               // sub process activities with execution type: "Shared Sync".
               ai = (IActivityInstance) activityInstances.get(activityInstances.size() - 1);
            }
         }
         
         return ai;
      }
      
      public void performedTransition(TransitionTokenBean transitionToken)
      {
         performedTransition(new TransitionTokenDigest(transitionToken));
      }

      public void appendedToWorklist(IActivityInstance activityInstance)
      {
         appendedToWorklist(new ActivityInstanceDigest(activityInstance));
      }

      public void startedActivityInstance(IActivityInstance activityInstance)
      {
         recordActivityInstance(activityInstance);
         startedActivityInstance(new ActivityInstanceDigest(activityInstance));
      }

      public void completedActivityInstance(IActivityInstance activityInstance)
      {
         deleteActivityInstance(activityInstance);
         completedActivityInstance(new ActivityInstanceDigest(activityInstance));
      }
   }

   public void finish()
   {
      synchronized (syncObject)
      {
         syncObject.notifyAll();
      }
   }
}
