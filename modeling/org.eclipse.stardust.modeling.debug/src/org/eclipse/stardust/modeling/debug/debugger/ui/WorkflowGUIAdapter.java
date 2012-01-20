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
package org.eclipse.stardust.modeling.debug.debugger.ui;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.AccessPoint;
import org.eclipse.stardust.engine.api.model.Activity;
import org.eclipse.stardust.engine.api.model.ApplicationContext;
import org.eclipse.stardust.engine.api.model.DataMapping;
import org.eclipse.stardust.engine.api.model.ImplementationType;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityCompletionLog;
import org.eclipse.stardust.engine.api.runtime.ActivityInstance;
import org.eclipse.stardust.engine.api.runtime.WorkflowService;
import org.eclipse.stardust.engine.core.extensions.interactive.contexts.jfc.InteractiveApplicationInstance;
import org.eclipse.stardust.engine.core.pojo.data.JavaBeanAccessPathEvaluator;
import org.eclipse.stardust.engine.core.runtime.gui.ApplicationEventListener;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.AccessPathEvaluator;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

/**
 * Executes manual activities or applications (user defined)
 * being registered with the user's worklist and being executed in his JFC.
 * <p/>
 * All necessary workflow server communication is performed by this class.<p>
 * <p/>
 * An instance of this class may perform only one workflow session at a time.
 * An instance of this class may perform only one application at a time.
 *
 * @author mgille
 * @version $Revision$
 */
public class WorkflowGUIAdapter extends JInternalFrame
{
   private static final long serialVersionUID = 1L;
   
//   private DefaultInterpreter manualInterpreter;
   private Vector listeners;
   private InteractiveApplicationInstance applicationInstance;
   private ImplementationType implementationType;

   /**
    * Constructor.
    */
   public WorkflowGUIAdapter()
   {
      listeners = new Vector();
   }

   /**
    * Retrieves all registered Event Listeners.
    *
    * @return an {@link java.util.Enumeration} with instances of
    *         ag.carnot.workflow.ApplicationEventListener
    */
   public Enumeration getAllListeners()
   {
      return listeners.elements();
   }

   /**
    * Registers an event listener for this class.
    *
    * @param listener the listener object.
    */
   public void addToListeners(ApplicationEventListener listener)
   {
      listeners.add(listener);
   }

   /**
    * Informs all registered listeners about the application closing event.
    */
   public void notifyListenersOnApplicationClose()
   {
      for (Enumeration e = getAllListeners(); e.hasMoreElements();)
      {
         ((ApplicationEventListener) e.nextElement()).applicationClosed(null);
      }
   }

   /**
    * Cleans up external programs launched by this adapter.
    */
   public void cleanupExternalPrograms()
   {
      if (applicationInstance != null)
      {
         applicationInstance.cleanup();
      }
   }

   /**
    * Creates an application instance as specified in the model. Different
    * types of applictions are instanciated in different ways. Also processes the
    * in data mappings before.
    */
   public JPanel getPanel(WorkflowService service, ActivityInstance activityInstance)
   {
      if (activityInstance == null)
      {
         throw new PublicException(Internal_Debugger_Messages
               .getString("EXP_NoActivitySetOnWorkflowSession")); //$NON-NLS-1$
      }
      implementationType = activityInstance.getActivity().getImplementationType();

      if (implementationType == ImplementationType.Manual)
      {
         return getPanelForManualActivity(activityInstance.getActivity().getName());
      }
      else if (implementationType == ImplementationType.Application)
      {
         if (!activityInstance.getActivity().isInteractive())
         {
            return getPanelForNonInteractiveApplication();
         }
         else
         {
            ApplicationContext ctx = activityInstance.getActivity()
                  .getApplicationContext(PredefinedConstants.JFC_CONTEXT);
            if (ctx == null)
            {
               return getPanelForNonJFCApplication();
            }
            else
            {
               Iterator dataMappingDetails = ctx.getAllInDataMappings().iterator();
               Map inData = service.getInDataValues(activityInstance.getOID(),
                     PredefinedConstants.JFC_CONTEXT, null);

               String instanceClass = (String) ctx
                     .getTypeAttribute(PredefinedConstants.JFC_CONTEXT_INSTANCE_CLASS_ATT);
               applicationInstance = (InteractiveApplicationInstance) Reflect
                     .createInstance(instanceClass, new Class[] {ActivityInstance.class},
                           new Object[] {activityInstance});
               processInDataMappings(dataMappingDetails, inData, activityInstance);

               return applicationInstance.getPanel();
            }
         }
      }
      throw new InternalException(MessageFormat.format(Internal_Debugger_Messages
            .getString("EXP_UnsupportedActivityImplType"), //$NON-NLS-1$
            new Object[] {implementationType}));

   }

   /**
    * Invokes a manual activity and initializes it's parameters.
    *
    * @param name
    * 
    * @return
    */
   private JPanel getPanelForManualActivity(String name)
   {
      JPanel panel = new JPanel();
      panel.add(new JLabel(Internal_Debugger_Messages.getString("LABEL_ManualActivityInstancesShouldBehandledByEclipse"))); //$NON-NLS-1$
      return panel;
   }

   /**
    *
    */
   private JPanel getPanelForNonInteractiveApplication()
   {
      return new JPanel();
   }

   /**
    *
    */
   private JPanel getPanelForNonJFCApplication()
   {
      JPanel panel = new JPanel();
      panel.add(new JLabel(Internal_Debugger_Messages.getString("LB_InteractiviActivityPanelHasNoJfcContext"))); //$NON-NLS-1$
      return panel;
   }

   /**
    * Uses the dereferentiation pathes provided in <code>inParameters</code>
    * to set  application parameters.
    */
   private void processInDataMappings(Iterator mappingDetails, Map inData,
                                      ActivityInstance activityInstance)
   {
      while (mappingDetails.hasNext())
      {
         DataMapping mapping = (DataMapping) mappingDetails.next();
         Object value = inData.get(mapping.getId());

         AccessPoint accessPoint = mapping.getApplicationAccessPoint();
         if (accessPoint == null)
         {
            throw new PublicException(MessageFormat.format(
                  Internal_Debugger_Messages.getString("EXP_InvalidDataMappingForActivityInProcess"), //$NON-NLS-1$
                  mapping.getId(), activityInstance.getActivity().getId(), activityInstance.getProcessDefinitionId()));
         }
         String apID = accessPoint.getId();
         if (StringUtils.isEmpty(mapping.getApplicationPath()))
         {
            applicationInstance.setInAccessPointValue(apID, value);
         }
         else
         {
            Object accessPointValue = applicationInstance.getOutAccessPointValue(apID);
            // todo: (france, fh) fix the evaluator
            AccessPathEvaluator evaluator = /*(AccessPathEvaluator)
                  Reflect.createInstance(bla.getAccessPathEvaluatorClass());*/
                  new JavaBeanAccessPathEvaluator();
            // @todo (france, ub): fix. get the access point and pass the attributes
            evaluator.evaluate(null, accessPointValue, mapping.getApplicationPath(), value);
         }
      }
   }

   /**
    * Completes the application. Either calls the complete method of a user defined
    * GUI or closes an ActiveX application.
    */
   public void completeApplication(WorkflowService session, ActivityInstance activityInstance)
   {
      completeApplication(session, activityInstance, 0);
   }

   /**
    * Completes the application. Either calls the complete method of a user defined
    * GUI or closes an ActiveX application.
    */
   public ActivityInstance completeApplication(WorkflowService session, ActivityInstance activityInstance, int flags)
   {
      ActivityInstance result = null;
      
      ImplementationType type = activityInstance.getActivity().getImplementationType();

      if (type == ImplementationType.Manual)
      {
         ActivityCompletionLog log = session.complete(activityInstance.getOID(),
               PredefinedConstants.DEFAULT_CONTEXT,
               processManualOutDataMappings(activityInstance.getActivity()
                     .getApplicationContext(PredefinedConstants.DEFAULT_CONTEXT)
                     .getAllOutDataMappings()
                     .iterator()), flags);

         result = log.getNextForUser();
      }
      else if (type == ImplementationType.Application)
      {
         if (!activityInstance.getActivity().isInteractive())
         {
            return null;
         }
         else
         {
            Activity activity = activityInstance.getActivity();
            ApplicationContext ctx = activity.getApplicationContext(
                  PredefinedConstants.JFC_CONTEXT);
            if (ctx == null)
            {
               JOptionPane.showMessageDialog(null, Internal_Debugger_Messages.getString("MSG_ActivityCannotBeCompletedSinceNoValidJfcContext")); //$NON-NLS-1$
               return null;
            }
            Map outData = applicationInstance.invoke(getUsedAccessPoints(
               ctx.getAllOutDataMappings().iterator()));
            ActivityCompletionLog log = session.complete(activityInstance.getOID(),
                  PredefinedConstants.JFC_CONTEXT, processOutDataMappings(outData,
                        ctx.getAllOutDataMappings().iterator()), flags);
            
            result = log.getNextForUser();
         }
      }
      else
      {
         throw new InternalException(MessageFormat.format(
               Internal_Debugger_Messages.getString("EXP_UnsupportedActivityImplType"), new Object[] { type })); //$NON-NLS-1$
      }
      
      return result;
   }

   public Map processApplicationOutDataMappings(WorkflowService session, ActivityInstance activityInstance)
   {
      return processApplicationOutDataMappings(session, activityInstance, 0);
   }

   private Map processApplicationOutDataMappings(WorkflowService session, ActivityInstance activityInstance, int flags)
   {
      Map result = new HashMap();

      ImplementationType type = activityInstance.getActivity().getImplementationType();

      if (type == ImplementationType.Application)
      {
         if (activityInstance.getActivity().isInteractive())
         {
            Activity activity = activityInstance.getActivity();
            ApplicationContext ctx = activity
                  .getApplicationContext(PredefinedConstants.JFC_CONTEXT);
            if (ctx == null)
            {
               JOptionPane.showMessageDialog(null, Internal_Debugger_Messages
                     .getString("MSG_ActivityCannotBeCompletedSinceNoValidJfcContext")); //$NON-NLS-1$
               return null;
            }

            Map outAccessPoints = applicationInstance.invoke(getUsedAccessPoints(ctx
                  .getAllOutDataMappings().iterator()));
            result = processOutDataMappings(outAccessPoints, ctx.getAllOutDataMappings()
                  .iterator());
         }
      }
      else
      {
         throw new InternalException(MessageFormat.format(Internal_Debugger_Messages
               .getString("EXP_UnsupportedActivityImplType"), new Object[] {type})); //$NON-NLS-1$
      }

      return result;
   }

   private Iterator getUsedAccessPoints(Iterator outMappings)
   {
      HashSet result = new HashSet();
      while (outMappings.hasNext())
      {
         DataMapping dataMapping = (DataMapping) outMappings.next();
         AccessPoint accessPoint = dataMapping.getApplicationAccessPoint();
         if (accessPoint != null)
         {
            result.add(accessPoint);
         }
      }
      return result.iterator();
   }

   /**
    * Retrieves the application out parameters and sends them to the server.
    * If no output parameters are specified, no call is made to the server.
    */
   private HashMap processOutDataMappings(Map outAccessPoints, Iterator outMappings)
   {
      HashMap outData = new HashMap();

      while (outMappings.hasNext())
      {
         DataMapping mapping = (DataMapping) outMappings.next();

         AccessPoint ap = mapping.getApplicationAccessPoint();
         if (ap==null)
         {
            // @todo/hiob (ub): this should never happen?
            continue;
         }
         String apID = ap.getId();
         Object outAccessPoint = outAccessPoints.get(apID);
         if (outAccessPoint == null)
         {
            // @todo/hiob (ub): this should never happen?
            continue;
         }

         // todo: (france, fh) fix the evaluator
         AccessPathEvaluator evaluator = /*(AccessPathEvaluator)
               Reflect.createInstance(bla.getAccessPathEvaluatorClass());*/
               new JavaBeanAccessPathEvaluator();
         // @todo (france, ub): fix. get the access point and pass the attributes
         Object value = evaluator.evaluate(null, outAccessPoint, mapping.getApplicationPath());

         outData.put(mapping.getId(), value);
      }
      return outData;
   }

   /**
    * Retrieves the application out parameters and sends them to the server.
    * If no output parameters are specified, no call is made to the server.
    */
   public Map processManualOutDataMappings(Iterator outMappings)
   {
      Map values = null; //manualInterpreter.getValues();
      Map outData = new HashMap();

      while (outMappings.hasNext())
      {
         DataMapping mapping = (DataMapping) outMappings.next();
         outData.put(mapping.getId(), values.get(mapping.getId()));
      }
      return outData;
   }
}

