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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ActivityUtil
{
   public static boolean isInteractive(ActivityType activity)
   {
      return isInteractive(activity, activity.getImplementation());
   }

   public static boolean isInteractive(ActivityType activity,
         ActivityImplementationType implementation)
   {
      if (ActivityImplementationType.MANUAL_LITERAL.equals(implementation))
      {
         return true;
      }
      if (ActivityImplementationType.APPLICATION_LITERAL.equals(implementation))
      {
         ApplicationType application = activity.getApplication();
         if (application != null)
         {
            return application.isInteractive();
         }
      }
      return false;
   }

   public static boolean isSubprocessActivity(ActivityType activity)
   {
      return ActivityImplementationType.SUBPROCESS_LITERAL.equals(activity
            .getImplementation());
   }

   public static boolean isRouteActivity(ActivityType activity)
   {
      return ActivityImplementationType.ROUTE_LITERAL
            .equals(activity.getImplementation());
   }

   public static boolean isApplicationActivity(ActivityType activity)
   {
      return ActivityImplementationType.APPLICATION_LITERAL.equals(activity
            .getImplementation());
   }

   public static List<ApplicationContextTypeType> getContextTypes(ActivityType activity, DirectionType direction)
   {
      return getContextTypes(activity, activity.getImplementation(), direction);
   }

   public static List<ApplicationContextTypeType> getContextTypes(ActivityType activity,
         ActivityImplementationType implementation, DirectionType direction)
   {
      List<ApplicationContextTypeType> contexts = CollectionUtils.newList();
      ModelType model = ModelUtils.findContainingModel(activity);
      if (isRouteActivity(activity) || isInteractive(activity, implementation))
      {
         addContext(PredefinedConstants.DEFAULT_CONTEXT, model, contexts);
      }
      if (!(isRouteActivity(activity) && DirectionType.IN_LITERAL.equals(direction))
            && !(isSubprocessActivity(activity) && !isDataMappingEnabled(
                  activity, DirectionType.IN_LITERAL.equals(direction))))
      {
         addContext(PredefinedConstants.ENGINE_CONTEXT, model, contexts);
      }
      if (isSubprocessActivity(activity) && activity.getImplementationProcess() != null
            && activity.getImplementationProcess().getFormalParameters() != null)
      {
         addContext(PredefinedConstants.PROCESSINTERFACE_CONTEXT, model, contexts);   
      }
      if (isApplicationActivity(activity))
      {
         ApplicationType application = activity.getApplication();
         if (application != null)
         {
            if (application.isInteractive())
            {
               model = ModelUtils.findContainingModel(application);
               for (ApplicationContextTypeType ctxType : model.getApplicationContextType())
               {
                  if (!isImplicitContext(ctxType.getId())
                        && containsCtxType(activity.getApplication(), ctxType))
                  {
                     contexts.add(ctxType);
                  }
               }
            }
            else
            {
               addContext(PredefinedConstants.APPLICATION_CONTEXT, model, contexts);
            }
         }
      }
      return contexts;
   }

   private static boolean containsCtxType(ApplicationType application,
         ApplicationContextTypeType appCtxType)
   {
      for (ContextType contextType : application.getContext())
      {
         if (appCtxType.equals(contextType.getType()))
         {
            return true;
         }
      }
      return false;
   }

   private static void addContext(String id, ModelType model,
         List<ApplicationContextTypeType> contexts)
   {
      ApplicationContextTypeType ctxType = (ApplicationContextTypeType) ModelUtils
            .findIdentifiableElement(model, CarnotWorkflowModelPackage.eINSTANCE
                  .getModelType_ApplicationContextType(), id);
      if (ctxType != null)
      {
         contexts.add(ctxType);
      }
   }

   public static ContextType getContext(ActivityType activity, String contextId)
   {
      ApplicationType application = activity.getApplication();
      if (application != null)
      {
         for (ContextType context : application.getContext())
         {
            if (CompareHelper.areEqual(context.getType().getId(), contextId))
            {
               return context;
            }
         }
      }
      return null;
   }

   public static List<DataMappingType> getDataMappings(ActivityType activity,
         boolean isIn, String contextId)
   {
      List<DataMappingType> dataMappings = CollectionUtils.newList();
      for (DataMappingType mapping : activity.getDataMapping())
      {
         if (contextId.equals(mapping.getContext())
               && (isIn == AccessPointUtil.isIn(mapping.getDirection())))
         {
            dataMappings.add(mapping);
         }
      }
      return dataMappings;
   }

   public static List<AccessPointType> getAccessPoints(ActivityType activity,
         boolean isIn, String contextId)
   {
      List<AccessPointType> accessPoints = CollectionUtils.newList();

      if (PredefinedConstants.ENGINE_CONTEXT.equals(contextId))
      {
         List<DataType> dataList = ModelUtils.findContainingModel(activity).getData();
         if (isIn)
         {
            if (isSubprocessActivity(activity))
            {
               for (DataType data : dataList)
               {
                  if (!data.isPredefined())
                  {
                     accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                           data.getId(), data.getName(),
                           AttributeUtil.getAttributeValue(data, getClassNameAtt(data)),
                           DirectionType.IN_LITERAL, true, null, data.getType()));
                  }
               }
            }
         }
         else
         {
            accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                  CarnotConstants.ACTIVITY_INSTANCE_ACCESSPOINT_ID,
                  CarnotConstants.ACTIVITY_INSTANCE_ACCESSPOINT_NAME,
                  "ag.carnot.workflow.runtime.ActivityInstance", DirectionType.OUT_LITERAL, //$NON-NLS-1$
                  true, null, ModelUtils.getDataType(activity, CarnotConstants.SERIALIZABLE_DATA_ID)));
            if (isSubprocessActivity(activity))
            {
               for (DataType data : dataList)
               {
                  accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        data.getId(), data.getName(),
                        AttributeUtil.getAttributeValue(data, getClassNameAtt(data)),
                        DirectionType.OUT_LITERAL, true, null, data.getType()));
               }
            }
         }
      }
      else if (PredefinedConstants.DEFAULT_CONTEXT.equals(contextId))
      {
         // default context has no access points
      }
      else if (PredefinedConstants.APPLICATION_CONTEXT.equals(contextId))
      {
         ApplicationType application = activity.getApplication();
         if (application != null)
         {
            accessPoints.addAll(getAccessPoints(getIntrinsicAccessPoints(application),
                  application.getAccessPoint(), isIn));
         }
      }
      else if (PredefinedConstants.PROCESSINTERFACE_CONTEXT.equals(contextId))
      {
         ProcessDefinitionType subprocess = activity.getImplementationProcess();
         if (subprocess != null)
         {
            FormalParametersType paramTypes = subprocess.getFormalParameters();
            if (paramTypes != null)
            {
               FormalParameterMappingsType mappingsType = subprocess.getFormalParameterMappings();
               accessPoints.addAll(ActivityUtil.getAccessPoints(paramTypes, mappingsType, activity, isIn));
            }
         }
      }
      else if (isInteractive(activity))
      {
         ContextType ctx = getContext(activity, contextId);
         if (ctx != null) // manual activities don't have a context
         {
            accessPoints.addAll(getAccessPoints(getIntrinsicAccessPoints(ctx), ctx
                  .getAccessPoint(), isIn));
         }
      }

      return accessPoints;
   }

   private static List<AccessPointType> getAccessPoints(FormalParametersType paramTypes,
         FormalParameterMappingsType mappingsType, ActivityType activity, boolean isIn)
   {
      List<AccessPointType> accessPoints = new ArrayList<AccessPointType>();
      for (FormalParameterType fpt : paramTypes.getFormalParameter())
      {
         ModeType mode = fpt.getMode();
         DataType dataType = mappingsType.getMappedData(fpt);
         if (dataType != null && (isIn && mode != ModeType.OUT || !isIn && mode != ModeType.IN))
         {
            accessPoints.add(AccessPointUtil.createAccessPoint(fpt, mappingsType.getMappedData(fpt)));
         }
      }      
      return accessPoints;
   }

   private static String getClassNameAtt(DataType data)
   {
      return (null == data.getType()) ? null : CarnotConstants.SERIALIZABLE_DATA_ID
            .equals(data.getType().getId())
            ? CarnotConstants.CLASS_NAME_ATT
            : CarnotConstants.ENTITY_BEAN_DATA_ID.equals(data.getType().getId())
                  ? CarnotConstants.REMOTE_INTERFACE_ATT
                  : CarnotConstants.PRIMITIVE_DATA_ID.equals(data.getType().getId())
                        ? CarnotConstants.TYPE_ATT
                        : null;
   }

   private static boolean isDataMappingEnabled(ActivityType activity, boolean isIn)
   {
      if (!isSubprocessActivity(activity))
      {
         return false;
      }
      boolean isCopyData = isIn ? AttributeUtil.getBooleanValue(activity,
            CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT) : false;

      return SubProcessModeType.SYNC_SEPARATE_LITERAL
            .equals(activity.getSubProcessMode())
            && !isCopyData;
   }

   public static List<AccessPointType> getExplicitAccessPoints(ActivityType activity, boolean isIn,
         String contextId)
   {
      List<AccessPointType> accessPoints = CollectionUtils.newList();

      if (PredefinedConstants.ENGINE_CONTEXT.equals(contextId) && !isIn)
      {
         accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
               CarnotConstants.ACTIVITY_INSTANCE_ACCESSPOINT_ID,
               CarnotConstants.ACTIVITY_INSTANCE_ACCESSPOINT_NAME,
               "ag.carnot.workflow.runtime.ActivityInstance", DirectionType.OUT_LITERAL, //$NON-NLS-1$
               true, null, ModelUtils.getDataType(activity,
                     CarnotConstants.SERIALIZABLE_DATA_ID)));
      }
      else if (PredefinedConstants.DEFAULT_CONTEXT.equals(contextId))
      {
         // default context has no access points
      }
      else if (PredefinedConstants.APPLICATION_CONTEXT.equals(contextId))
      {
         ApplicationType application = activity.getApplication();
         if (null != application)
         {
            accessPoints.addAll(getAccessPoints(Collections.<AccessPointType>emptyList(), application
                  .getAccessPoint(), isIn));
         }
      }
      else if (isInteractive(activity))
      {
         ContextType ctx = getContext(activity, contextId);
         if (ctx != null) // manual activities don't have a context
         {
            accessPoints.addAll(getAccessPoints(Collections.<AccessPointType>emptyList(), ctx
                  .getAccessPoint(), isIn));
         }
      }

      return accessPoints;
   }

   public static List<AccessPointType> getIntrinsicAccessPoints(ITypedElement application)
   {
      IMetaType type = application.getMetaType();
      if (type != null)
      {
         Map<String, IConfigurationElement> types = SpiExtensionRegistry.instance().getExtensions(
               type.getExtensionPointId());
         IConfigurationElement config = types.get(type.getId());
         if (config != null)
         {
            try
            {
               IAccessPointProvider provider = (IAccessPointProvider) config
                     .createExecutableExtension("accessPointProvider"); //$NON-NLS-1$
               if (provider != null)
               {
                  return provider.createIntrinsicAccessPoint((IModelElement) application);
               }
            }
            catch (CoreException e)
            {
               // todo: log
               // e.printStackTrace();
            }
         }
      }
      return Collections.emptyList();
   }

   private static Collection<AccessPointType> getAccessPoints(List<AccessPointType> intrinsicAccessPoints,
         List<AccessPointType> explicitAccessPoints, boolean isIn)
   {
      List<AccessPointType> accessPoints = CollectionUtils.newList();

      addAll(accessPoints, intrinsicAccessPoints, isIn);
      addAll(accessPoints, explicitAccessPoints, isIn);

      Collections.sort(accessPoints, new Comparator<AccessPointType>()
      {
         public int compare(AccessPointType lhs, AccessPointType rhs)
         {
            return lhs.getId() == null || rhs.getId() == null
                  ? -1 : CompareHelper.compare(lhs.getName(), rhs.getName());
         }
      });

      return accessPoints;
   }

   private static void addAll(List<AccessPointType> list, List<AccessPointType> accessPoint, boolean isIn)
   {
      for (AccessPointType ap : accessPoint)
      {
         if (AccessPointUtil.isBrowsable(ap) && isIn
         // todo (fh) remove usage of isBrowsable as
               // direction filter, see defect #4243
               || AccessPointUtil.isDirectionCompatible(ap, isIn))
         {
            list.add(ap);
         }
      }
   }

   public static void updateConnections(ActivityType activity)
   {
      // collect all data referred by the data mappings of this activity
      Set<DataType> data = CollectionUtils.newSet();
      for (DataMappingType dm : activity.getDataMapping())
      {
         if (dm.getData() != null)
         {
            data.add(dm.getData());
         }
      }
      // update connections for every diagram in the process
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      for (DiagramType diagram : process.getDiagram())
      {
         for (DataType dataType : data)
         {
            updateConnections(diagram, activity, dataType);
         }
         deleteConnections(diagram, activity, data);
      }
   }

   private static void deleteConnections(DiagramType diagram, ActivityType activity,
         Set<DataType> data)
   {
      List<DataMappingConnectionType> deleted = CollectionUtils.newList();
      // collect the list of data mapping connections to delete
      List symbols = DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_DataMappingConnection(), null);
      for (Iterator i = symbols.iterator(); i.hasNext();)
      {
         DataMappingConnectionType connection = (DataMappingConnectionType) i.next();
         DataSymbolType dataSymbol = connection.getDataSymbol();
         ActivitySymbolType activitySymbol = connection.getActivitySymbol();
         if (activitySymbol != null && activitySymbol.getActivity().equals(activity)
               && dataSymbol != null && !data.contains(dataSymbol.getData()))
         {
            deleted.add(connection);
         }
      }
      // actual deletion
      for (int i = 0; i < deleted.size(); i++)
      {
         DataMappingConnectionType connection = (DataMappingConnectionType) deleted
               .get(i);
         connection.setActivitySymbol(null);
         connection.setDataSymbol(null);
         ((ISymbolContainer) connection.eContainer()).getDataMappingConnection().remove(
               connection);
      }
   }

   private static void updateConnections(DiagramType diagram, ActivityType activity,
         DataType data)
   {
      // find if there is a connection already
      boolean found = false;
      List symbols = DiagramUtil.getSymbols(diagram, 
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_DataMappingConnection(), null);
      for (Iterator i = symbols.iterator(); i.hasNext();)
      {
         DataMappingConnectionType connection = (DataMappingConnectionType) i.next();
         DataSymbolType dataSymbol = connection.getDataSymbol();
         ActivitySymbolType activitySymbol = connection.getActivitySymbol();
         if (activitySymbol != null && activitySymbol.getActivity().equals(activity)
               && dataSymbol != null && dataSymbol.getData().equals(data))
         {
            // force refresh
            connection.setStyle(connection.getStyle());
            found = true;
         }
      }
      // create new connection
      if (!found)
      {
         for (Iterator i = DiagramUtil.getSymbols(diagram,
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
               activity).iterator(); i.hasNext();)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) i.next();
            DataSymbolType dataSymbol = (DataSymbolType) DiagramUtil.getClosestSymbol(
                  activitySymbol, CarnotWorkflowModelPackage.eINSTANCE
                        .getISymbolContainer_DataSymbol(), data);
            if (dataSymbol != null)
            {
               DataMappingConnectionType connection = CarnotWorkflowModelFactory.eINSTANCE
                     .createDataMappingConnectionType();
               connection.setActivitySymbol(activitySymbol);
               connection.setDataSymbol(dataSymbol);
               diagram.getDataMappingConnection().add(connection);
            }
         }
      }
   }

   public static boolean isImplicitContext(String id)
   {
      return CarnotConstants.DEFAULT_CONTEXT_ID.equals(id)
            || CarnotConstants.ENGINE_CONTEXT_ID.equals(id)
            || CarnotConstants.APPLICATION_CONTEXT_ID.equals(id)
            || CarnotConstants.PROCESSINTERFACE_CONTEXT_ID.equals(id);
   }
   
   public static List<ActivityType> getOutActivities(ActivityType activity)
   {
      List<ActivityType> list = CollectionUtils.newList();
      if (activity != null)
      {
         for (TransitionType transition : activity.getOutTransitions())
         {
            list.add(transition.getTo());
         }
      }
      return list;
   }

   public static boolean hasStartEvent(ActivityType activity)
   {
      for (INodeSymbol activitySymbol : activity.getSymbols())
      {
         for (TransitionConnectionType transition : ((ActivitySymbolType) activitySymbol).getInTransitions())
         {
            if (transition.getSourceNode() instanceof StartEventSymbol)
            {
               return true;
            }
         }
      }
      return false;
   }

   public static boolean hasEndEvent(ActivityType activity)
   {
      for (INodeSymbol activitySymbol : activity.getSymbols())
      {
         for (TransitionConnectionType transition : ((ActivitySymbolType) activitySymbol).getOutTransitions())
         {
            if (transition.getTargetNode() instanceof EndEventSymbol)
            {
               return true;
            }
         }
      }
      return false;
   }
}
