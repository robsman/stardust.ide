package org.eclipse.stardust.model.xpdl.builder.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.stardust.common.error.ObjectNotFoundException;
import org.eclipse.stardust.engine.api.runtime.BpmRuntimeError;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.IntermediateEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;

public class XPDLFinderUtils
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   /**
    * @param parentLaneSymbol
    * @param annotationOId
    * @return
    */
   public static AnnotationSymbolType findAnnotationSymbol(LaneSymbol parentLaneSymbol,
         Long annotationOId)
   {
      Iterator<AnnotationSymbolType> annotationIterator = parentLaneSymbol
            .getAnnotationSymbol().iterator();

      while (annotationIterator.hasNext())
      {
         AnnotationSymbolType annSymbol = annotationIterator.next();
         if (annotationOId.equals(annSymbol.getElementOid()))
         {
            return annSymbol;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static ProcessDefinitionType findProcessDefinition(ModelType model, String id)
   {
      for (ProcessDefinitionType processDefinition : model.getProcessDefinition())
      {
         if (processDefinition.getId().equals(id))
         {
            return processDefinition;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return application
    */
   public static ApplicationType findApplication(ModelType model, String id)
   {
      for (ApplicationType application : model.getApplication())
      {
         if (application.getId().equals(id))
         {
            return application;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static ApplicationTypeType findApplicationTypeType(ModelType model, String id)
   {
      for (ApplicationTypeType applicationType : model.getApplicationType())
      {
         if (applicationType.getId().equals(id))
         {
            return applicationType;
         }
      }

      // TODO Temporary
      if (id.equals(ModelerConstants.CAMEL_CONSUMER_APPLICATION_TYPE_ID))
      {
         ApplicationTypeType applicationMetaType = ModelUtils.findIdentifiableElement(
               model.getApplicationType(),
               ModelerConstants.CAMEL_CONSUMER_APPLICATION_TYPE_ID);

         if (null == applicationMetaType)
         {
            CarnotWorkflowModelFactory F_CWM = CarnotWorkflowModelFactory.eINSTANCE;

            applicationMetaType = F_CWM.createApplicationTypeType();
            applicationMetaType
                  .setId(ModelerConstants.CAMEL_CONSUMER_APPLICATION_TYPE_ID);
            applicationMetaType.setName("Camel Consumer Application");
            applicationMetaType.setIsPredefined(true);
            applicationMetaType.setSynchronous(false);

            AttributeUtil
                  .setAttribute(
                        applicationMetaType,
                        "carnot:engine:accessPointProvider",
                        "org.eclipse.stardust.engine.extensions.camel.app.CamelProducerSpringBeanAccessPointProvider");
            AttributeUtil
                  .setAttribute(
                        applicationMetaType,
                        "carnot:engine:applicationInstance",
                        "org.eclipse.stardust.engine.extensions.camel.app.CamelProducerSpringBeanApplicationInstance");
            AttributeUtil
                  .setAttribute(applicationMetaType, "carnot:engine:validator",
                        "org.eclipse.stardust.engine.extensions.camel.app.CamelProducerSpringBeanValidator");

            model.getApplicationType().add(applicationMetaType);

            return applicationMetaType;
         }
      }
      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static TriggerTypeType findTriggerType(ModelType model, String id)
   {
      for (TriggerTypeType triggerType : model.getTriggerType())
      {
         if (triggerType.getId().equals(id))
         {
            return triggerType;
         }
      }

      // TODO Temporary

      if (id.equals("camel"))
      {
         TriggerTypeType triggerMetaType = ModelUtils.findIdentifiableElement(
               model.getTriggerType(), ModelerConstants.CAMEL_TRIGGER_TYPE_ID);

         if (null == triggerMetaType)
         {
            CarnotWorkflowModelFactory F_CWM = CarnotWorkflowModelFactory.eINSTANCE;

            triggerMetaType = F_CWM.createTriggerTypeType();
            triggerMetaType.setId(ModelerConstants.CAMEL_TRIGGER_TYPE_ID);
            triggerMetaType.setName("Camel Trigger");
            triggerMetaType.setIsPredefined(true);
            triggerMetaType.setPullTrigger(false);

            AttributeUtil
                  .setAttribute(triggerMetaType, "carnot:engine:validator",
                        "org.eclipse.stardust.engine.extensions.camel.trigger.validation.CamelTriggerValidator");
            AttributeUtil
                  .setAttribute(triggerMetaType, "carnot:engine:runtimeValidator",
                        "org.eclipse.stardust.engine.extensions.camel.trigger.validation.CamelTriggerValidator");

            model.getTriggerType().add(triggerMetaType);

            return triggerMetaType;
         }
      }
      else if (id.equals("scan"))
      {
         TriggerTypeType triggerMetaType = ModelUtils.findIdentifiableElement(
               model.getTriggerType(), ModelerConstants.SCAN_TRIGGER_TYPE_ID);

         if (null == triggerMetaType)
         {
            CarnotWorkflowModelFactory F_CWM = CarnotWorkflowModelFactory.eINSTANCE;

            triggerMetaType = F_CWM.createTriggerTypeType();

            triggerMetaType.setId(ModelerConstants.SCAN_TRIGGER_TYPE_ID);
            triggerMetaType.setName("Scan Trigger");
            triggerMetaType.setIsPredefined(true);
            triggerMetaType.setPullTrigger(false);

            AttributeUtil
                  .setAttribute(triggerMetaType, "carnot:engine:validator",
                        "org.eclipse.stardust.engine.core.extensions.triggers.manual.ManualTriggerValidator");

            model.getTriggerType().add(triggerMetaType);

            return triggerMetaType;
         }
      }

      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static ApplicationContextTypeType findApplicationContextTypeType(
         ModelType model, String id)
   {
      for (ApplicationContextTypeType applicationContextType : model
            .getApplicationContextType())
      {
         if (applicationContextType.getId().equals(id))
         {
            return applicationContextType;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static TypeDeclarationType findTypeDeclaration(ModelType model, String id)
   {
      TypeDeclarationType typeDeclaration = model.getTypeDeclarations()
            .getTypeDeclaration(id);
      if (typeDeclaration == null)
      {
         throw new ObjectNotFoundException(
               BpmRuntimeError.MDL_UNKNOWN_TYPE_DECLARATION_ID.raise(id));
      }
      return typeDeclaration;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static DataTypeType findDataType(ModelType model, String id)
   {
      for (DataTypeType dataType : model.getDataType())
      {
         if (dataType.getId().equals(id))
         {
            return dataType;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static DataType findData(ModelType model, String id)
   {
      for (DataType data : model.getData())
      {
         if (data.getId().equals(id))
         {
            return data;
         }
      }
      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static IModelParticipant findParticipant(ModelType model, String id)
   {
      for (RoleType role : model.getRole())
      {
         if (role.getId().equals(id))
         {
            return role;
         }
      }

      for (OrganizationType organization : model.getOrganization())
      {
         if (organization.getId().equals(id))
         {
            return organization;
         }
      }

      for (ConditionalPerformerType conditionalPerformer : model
            .getConditionalPerformer())
      {
         if (conditionalPerformer.getId().equals(id))
         {
            return conditionalPerformer;
         }
      }
      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static DataSymbolType findDataSymbol(DiagramType diagram, long oid)
   {
      for (DataSymbolType dataSymbol : diagram.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol childLaneSymbol : poolSymbol.getChildLanes())
         {
            DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

            if (dataSymbol != null)
            {
               return dataSymbol;
            }
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public static DataSymbolType findDataSymbolRecursively(LaneSymbol laneSymbol, long oid)
   {
      for (DataSymbolType dataSymbol : laneSymbol.getDataSymbol())
      {
         if (dataSymbol.getElementOid() == oid)
         {
            return dataSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         DataSymbolType dataSymbol = findDataSymbolRecursively(childLaneSymbol, oid);

         if (dataSymbol != null)
         {
            return dataSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param processDefinition
    * @param id
    * @return
    */
   public static ActivityType findActivity(ProcessDefinitionType processDefinition,
         String id)
   {
      for (ActivityType activity : processDefinition.getActivity())
      {
         if (activity.getId().equals(id))
         {
            return activity;
         }
      }
      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static ActivitySymbolType findActivitySymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingActivitySymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return findActivitySymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static LaneSymbol findLaneContainingActivitySymbol(DiagramType diagram, long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
                  laneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   public static LaneSymbol findLaneContainingActivitySymbolRecursively(
         LaneSymbol laneSymbol, long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingActivitySymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public static ActivitySymbolType findActivitySymbol(LaneSymbol laneSymbol, long oid)
   {
      for (ActivitySymbolType activitySymbol : laneSymbol.getActivitySymbol())
      {
         if (activitySymbol.getElementOid() == oid)
         {
            return activitySymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static AnnotationSymbolType findAnnotationSymbol(DiagramType diagram, long oid)
   {
      LaneSymbol laneSymbol = findLaneContainingAnnotationSymbol(diagram, oid);

      if (laneSymbol != null)
      {
         return XPDLFinderUtils.findAnnotationSymbol(laneSymbol, oid);
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   private static LaneSymbol findLaneContainingAnnotationSymbol(DiagramType diagram,
         long oid)
   {
      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol containingLaneSymbol = findLaneContainingAnnotationSymbolRecursively(
                  laneSymbol, oid);

            if (containingLaneSymbol != null)
            {
               return containingLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    * @param laneSymbol
    * @param oid
    * @return
    */
   public static LaneSymbol findLaneContainingAnnotationSymbolRecursively(
         LaneSymbol laneSymbol, long oid)
   {
      for (AnnotationSymbolType annotationSymbol : laneSymbol.getAnnotationSymbol())
      {
         if (annotationSymbol.getElementOid() == oid)
         {
            return laneSymbol;
         }
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol containingLaneSymbol = findLaneContainingAnnotationSymbolRecursively(
               childLaneSymbol, oid);

         if (containingLaneSymbol != null)
         {
            return containingLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param model
    * @param id
    * @return
    */
   public static LaneSymbol findLaneInProcess(ProcessDefinitionType processDefinition,
         String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getChildLanes())
         {
            LaneSymbol foundLaneSymbol = findLaneRecursively(laneSymbol, id);

            if (foundLaneSymbol != null)
            {
               return foundLaneSymbol;
            }
         }
      }

      return null;
   }

   /**
    *
    * @param laneSymbol
    * @param id
    * @return
    */
   public static LaneSymbol findLaneRecursively(LaneSymbol laneSymbol, String id)
   {
      if (laneSymbol.getId().equals(id))
      {
         return laneSymbol;
      }

      for (LaneSymbol childLaneSymbol : laneSymbol.getChildLanes())
      {
         LaneSymbol foundLaneSymbol = findLaneRecursively(childLaneSymbol, id);

         if (foundLaneSymbol != null)
         {
            return foundLaneSymbol;
         }
      }

      return null;
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static StartEventSymbol findStartEventSymbol(DiagramType diagram, long oid)
   {
      return findSymbolRecursively(oid, StartEventSymbol.class, diagram,
            PKG_CWM.getISymbolContainer_StartEventSymbols());
   }

   /**
    *
    * @param oid
    * @param diagram
    * @return
    */
   public static <S extends IGraphicalObject> S findSymbolRecursively(long oid,
         Class<S> symbolType, DiagramType diagram, EReference containmentFeature)
   {
      S symbol = findSymbol(oid, symbolType, (ISymbolContainer) diagram,
            containmentFeature);
      if (null != symbol)
      {
         return symbol;
      }

      for (PoolSymbol poolSymbol : diagram.getPoolSymbols())
      {
         symbol = findSymbolRecursively(oid, symbolType, poolSymbol, containmentFeature);
         if (null != symbol)
         {
            return symbol;
         }
      }

      return null;
   }

   public static <S extends IGraphicalObject> S findSymbol(long oid, Class<S> symbolType,
         ISymbolContainer container, EReference containmentFeature)
   {
      if (containmentFeature.isMany())
      {
         @SuppressWarnings("unchecked")
         List< ? extends IGraphicalObject> containedSymbols = (List< ? extends IGraphicalObject>) container
               .eGet(containmentFeature);
         for (IGraphicalObject symbol : containedSymbols)
         {
            if (symbol.getElementOid() == oid)
            {
               return symbolType.cast(symbol);
            }
         }
      }
      else
      {
         IGraphicalObject containedSymbol = (IGraphicalObject) container
               .eGet(containmentFeature);
         if ((null != containedSymbol) && (containedSymbol.getElementOid() == oid))
         {
            return symbolType.cast(containedSymbol);
         }
      }

      return null;
   }

   /**
    *
    * @param oid
    * @param container
    * @return
    */
   public static <S extends IGraphicalObject, C extends ISymbolContainer & ISwimlaneSymbol> S findSymbolRecursively(
         long oid, Class<S> symbolType, C container, EReference containmentFeature)
   {
      S symbol = XPDLFinderUtils.findSymbol(oid, symbolType, (ISymbolContainer) container,
            containmentFeature);
      if (null != symbol)
      {
         return symbol;
      }

      if (INodeSymbol.class.isAssignableFrom(symbolType))
      {
         // only node symbols will be stored at lane level
         for (LaneSymbol childLaneSymbol : container.getChildLanes())
         {
            symbol = findSymbolRecursively(oid, symbolType, childLaneSymbol,
                  containmentFeature);
            if (null != symbol)
            {
               return symbol;
            }
         }
      }
      return null;
   }

   /**
    *
    * @param processDefinition
    * @param oid
    * @return
    */
   public static TransitionConnectionType findTransitionConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      DiagramType diagram = processDefinition.getDiagram().get(0);

      TransitionConnectionType connection = XPDLFinderUtils.findSymbolRecursively(oid,
            TransitionConnectionType.class, diagram,
            PKG_CWM.getISymbolContainer_TransitionConnection());
      if (null != connection)
      {
         return connection;
      }

      return null;
   }

   /**
    *
    * @param processDefinition
    * @param oid
    * @return
    */
   public static DataMappingConnectionType findDataMappingConnectionByModelOid(
         ProcessDefinitionType processDefinition, long oid)
   {
      for (DataMappingConnectionType dataMappingConnectionType : processDefinition
            .getDiagram().get(0).getDataMappingConnection())
      {
         if (dataMappingConnectionType.getElementOid() == oid)
         {
            return dataMappingConnectionType;
         }
      }

      // TODO Support multiple pools

      for (DataMappingConnectionType dataMappingConnectionType : processDefinition
            .getDiagram().get(0).getPoolSymbols().get(0).getDataMappingConnection())
      {
         if (dataMappingConnectionType.getElementOid() == oid)
         {
            return dataMappingConnectionType;
         }
      }

      return null;
      //throw new ObjectNotFoundException("Could not find " + oid + ".");
   }

   /**
    *
    * @param poolSymbol
    * @param oid
    * @return
    */
   public static LaneSymbol findLaneSymbolById(ProcessDefinitionType processDefinition,
         String id)
   {
      for (PoolSymbol poolSymbol : processDefinition.getDiagram().get(0).getPoolSymbols())
      {
         for (LaneSymbol laneSymbol : poolSymbol.getLanes())
         {

            // TODO Recursion

            if (laneSymbol.getId().equals(id))
            {
               return laneSymbol;
            }
         }
      }
      return null;
   }

   /**
    *
    * @param container
    * @param oid
    * @return
    */
   public static EndEventSymbol findEndEventSymbol(ISymbolContainer container, long oid)
   {
      return XPDLFinderUtils.findSymbol(oid, EndEventSymbol.class, container,
            PKG_CWM.getISymbolContainer_EndEventSymbols());
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public static StartEventSymbol findStartEventSymbol(LaneSymbol laneSymbol, long oid)
   {
      return XPDLFinderUtils.findSymbol(oid, StartEventSymbol.class, laneSymbol,
            PKG_CWM.getISymbolContainer_StartEventSymbols());
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static IntermediateEventSymbol findIntermediateEventSymbol(DiagramType diagram,
         long oid)
   {
      return XPDLFinderUtils.findSymbolRecursively(oid, IntermediateEventSymbol.class,
            diagram, PKG_CWM.getISymbolContainer_IntermediateEventSymbols());
   }

   /**
    *
    * @param laneSymbol
    * @param oid
    * @return
    */
   public static IntermediateEventSymbol findIntermediateEventSymbol(
         LaneSymbol laneSymbol, long oid)
   {
      return XPDLFinderUtils.findSymbol(oid, IntermediateEventSymbol.class, laneSymbol,
            PKG_CWM.getISymbolContainer_IntermediateEventSymbols());
   }

   /**
    *
    * @param diagram
    * @param oid
    * @return
    */
   public static EndEventSymbol findEndEventSymbol(DiagramType diagram, long oid)
   {
      return XPDLFinderUtils.findSymbolRecursively(oid, EndEventSymbol.class, diagram,
            PKG_CWM.getISymbolContainer_EndEventSymbols());
   }

}
