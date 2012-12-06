/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.defaults;

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.PRIMITIVE_DATA;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.extensions.triggers.manual.ManualTriggerValidator;
import org.eclipse.stardust.engine.core.pojo.app.PlainJavaAccessPointProvider;
import org.eclipse.stardust.engine.core.pojo.app.PlainJavaApplicationInstance;
import org.eclipse.stardust.engine.core.pojo.app.PlainJavaValidator;
import org.eclipse.stardust.engine.core.pojo.data.JavaBeanAccessPathEvaluator;
import org.eclipse.stardust.engine.core.pojo.data.PrimitiveAccessPathEvaluator;
import org.eclipse.stardust.engine.core.pojo.data.PrimitiveValidator;
import org.eclipse.stardust.engine.core.pojo.data.SerializableValidator;
import org.eclipse.stardust.engine.core.spi.extensions.model.AccessPointProvider;
import org.eclipse.stardust.engine.core.spi.extensions.model.ApplicationValidator;
import org.eclipse.stardust.engine.core.spi.extensions.model.DataValidator;
import org.eclipse.stardust.engine.core.spi.extensions.model.ExtendedDataValidator;
import org.eclipse.stardust.engine.core.spi.extensions.model.TriggerValidator;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.AccessPathEvaluator;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.ApplicationInstance;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.DataFilterExtension;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.DataLoader;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.ExtendedAccessPathEvaluator;
import org.eclipse.stardust.engine.core.spi.extensions.runtime.StatelessApplicationInstance;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataFilterExtension;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataLoader;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataXMLValidator;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataXPathEvaluator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentAccessPathEvaluator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentListAccessPathEvaluator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentListValidator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentValidator;
import org.eclipse.stardust.engine.extensions.ejb.SessionBeanValidator;
import org.eclipse.stardust.engine.extensions.ejb.app.SessionBeanAccessPointProvider;
import org.eclipse.stardust.engine.extensions.ejb.app.SessionBeanApplicationInstance;
import org.eclipse.stardust.engine.extensions.ejb.data.EntityBeanEvaluator;
import org.eclipse.stardust.engine.extensions.ejb.data.EntityBeanValidator;
import org.eclipse.stardust.engine.extensions.jaxws.app.WebserviceApplicationInstance;
import org.eclipse.stardust.engine.extensions.jaxws.app.WebserviceApplicationValidator;
import org.eclipse.stardust.engine.extensions.jms.app.JMSApplicationInstance;
import org.eclipse.stardust.engine.extensions.jms.app.JMSValidator;
import org.eclipse.stardust.engine.extensions.xml.data.XMLValidator;
import org.eclipse.stardust.engine.extensions.xml.data.XPathEvaluator;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;



public class DefaultTypesInitializer implements ModelInitializer
{

   public void initializeModel(ModelType model)
   {
      initializeDataTypes(model);
      initializeApplicationTypes(model);
      initializeInteractionContextTypes(model);
      initializeTriggerTypes(model);

      // TODO event conditions
      // TODO event actions
   }

   public void initializeDataTypes(ModelType model)
   {
      initializeDataType(model, PRIMITIVE_DATA, "Primitive Data",
            PrimitiveAccessPathEvaluator.class, PrimitiveValidator.class);

      initializeDataType(model, PredefinedConstants.SERIALIZABLE_DATA,
            "Serializable Data", JavaBeanAccessPathEvaluator.class,
            SerializableValidator.class);

      initializeDataType(model, PredefinedConstants.ENTITY_BEAN_DATA, "Entity Bean",
            EntityBeanEvaluator.class, EntityBeanValidator.class);

      initializeDataType(model, PredefinedConstants.PLAIN_XML_DATA, "XML Document",
            XPathEvaluator.class, XMLValidator.class);

      initializeDataType(model, PredefinedConstants.STRUCTURED_DATA, "Structured Data",
            StructuredDataXPathEvaluator.class, StructuredDataXMLValidator.class,
            StructuredDataFilterExtension.class, StructuredDataLoader.class);

      initializeDataType(model, PredefinedConstants.DOCUMENT_DATA, "Document",
            VfsDocumentAccessPathEvaluator.class, VfsDocumentValidator.class,
            StructuredDataFilterExtension.class, StructuredDataLoader.class);

      initializeDataType(model, "dmsDocumentList", "Document List",
            VfsDocumentListAccessPathEvaluator.class, VfsDocumentListValidator.class,
            StructuredDataFilterExtension.class, StructuredDataLoader.class);

   }

   public void initializeApplicationTypes(ModelType model)
   {
      initializeApplicationType(model, PredefinedConstants.SESSIONBEAN_APPLICATION,
            "Session Bean Application", SessionBeanAccessPointProvider.class,
            SessionBeanApplicationInstance.class, SessionBeanValidator.class);

      initializeApplicationType(model, PredefinedConstants.PLAINJAVA_APPLICATION,
            "Plain Java Application", PlainJavaAccessPointProvider.class,
            PlainJavaApplicationInstance.class, PlainJavaValidator.class);

      initializeApplicationType(model, PredefinedConstants.JMS_APPLICATION,
            "JMS Application", null, JMSApplicationInstance.class, JMSValidator.class);

      initializeApplicationType(model, PredefinedConstants.WS_APPLICATION,
            "Web Service Application", null, WebserviceApplicationInstance.class,
            WebserviceApplicationValidator.class);
   }

   public void initializeInteractionContextTypes(ModelType model)
   {
      initializeInteractionContextType(model, PredefinedConstants.DEFAULT_CONTEXT,
            "Default Context", false, true);

      initializeInteractionContextType(model, PredefinedConstants.ENGINE_CONTEXT,
            "Engine Context", true, false);

      initializeInteractionContextType(model, PredefinedConstants.EXTERNALWEBAPP_CONTEXT,
            "External Web Application", true, false);

      initializeInteractionContextType(model, PredefinedConstants.APPLICATION_CONTEXT,
            "Noninteractive Application Context", true, false);

      // TODO
   }

   public void initializeTriggerTypes(ModelType model)
   {
      initializeTriggerType(model, PredefinedConstants.MANUAL_TRIGGER, "Manual Trigger",
            false, ManualTriggerValidator.class);

      // TODO
   }

   public void initializeDataType(ModelType model, String typeId, String typeName,
         Class<? > clsEvaluator, Class<? > clsValidator, Class<? > ... clsExtensions)
   {
      if ( !AccessPathEvaluator.class.isAssignableFrom(clsEvaluator)
            && !ExtendedAccessPathEvaluator.class.isAssignableFrom(clsEvaluator))
      {
         throw new IllegalArgumentException("Unsupported data evaluator type: "
               + clsEvaluator);
      }
      if ( !DataValidator.class.isAssignableFrom(clsValidator)
            && !ExtendedDataValidator.class.isAssignableFrom(clsValidator))
      {
         throw new IllegalArgumentException("Unsupported data validator type: "
               + clsValidator);
      }

      if (null == XpdlModelUtils.findElementById(model.getDataType(), typeId))
      {
         DataTypeType typeDef = BpmPackageBuilder.F_CWM.createDataTypeType();
         typeDef.setId(typeId);
         typeDef.setName(typeName);
         typeDef.setIsPredefined(true);

         AttributeUtil.setAttribute(typeDef, PredefinedConstants.EVALUATOR_CLASS_ATT,
               clsEvaluator.getName());
         AttributeUtil.setAttribute(typeDef, PredefinedConstants.VALIDATOR_CLASS_ATT,
               clsValidator.getName());
         if (null != clsExtensions)
         {
            for (Class<? > clsExtension : clsExtensions)
            {
               if (DataFilterExtension.class.isAssignableFrom(clsExtension))
               {
                  AttributeUtil.setAttribute(typeDef,
                        PredefinedConstants.DATA_FILTER_EXTENSION_ATT,
                        clsExtension.getName());
               }
               else if (DataLoader.class.isAssignableFrom(clsExtension))
               {
                  AttributeUtil.setAttribute(typeDef,
                        PredefinedConstants.DATA_LOADER_ATT, clsExtension.getName());
               }
               else
               {
                  throw new IllegalArgumentException("Unrecognized data type extension: "
                        + clsExtension);
               }
            }
         }

         model.getDataType().add(typeDef);
      }
   }

   public void initializeApplicationType(ModelType model, String typeId, String typeName,
         Class<? extends AccessPointProvider> clsApProvider, Class<? > clsAppDriver,
         Class<? extends ApplicationValidator> clsValidator)
   {
      if ( !ApplicationInstance.class.isAssignableFrom(clsAppDriver)
            && !StatelessApplicationInstance.class.isAssignableFrom(clsAppDriver))
      {
         throw new IllegalArgumentException("Unsupported application instance type: "
               + clsAppDriver);
      }

      if (null == XpdlModelUtils.findElementById(model.getApplicationType(), typeId))
      {
         ApplicationTypeType typeDef = BpmPackageBuilder.F_CWM.createApplicationTypeType();
         typeDef.setId(typeId);
         typeDef.setName(typeName);
         typeDef.setIsPredefined(true);

         if (null != clsApProvider)
         {
            AttributeUtil.setAttribute(typeDef,
                  PredefinedConstants.ACCESSPOINT_PROVIDER_ATT, clsApProvider.getName());
         }
         AttributeUtil.setAttribute(typeDef,
               PredefinedConstants.APPLICATION_INSTANCE_CLASS_ATT, clsAppDriver.getName());
         AttributeUtil.setAttribute(typeDef, PredefinedConstants.VALIDATOR_CLASS_ATT,
               clsValidator.getName());

         model.getApplicationType().add(typeDef);
      }
   }

   private void initializeInteractionContextType(ModelType model, String typeId,
         String typeName, boolean hasApplicationPath, boolean hasMappingId)
   {
      if (null == XpdlModelUtils.findElementById(model.getApplicationContextType(), typeId))
      {
         ApplicationContextTypeType typeDef = BpmPackageBuilder.F_CWM.createApplicationContextTypeType();
         typeDef.setId(typeId);
         typeDef.setName(typeName);
         typeDef.setIsPredefined(true);

         typeDef.setHasApplicationPath(hasApplicationPath);
         typeDef.setHasMappingId(hasMappingId);

         long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
         typeDef.setElementOid(++maxElementOid);

         model.getApplicationContextType().add(typeDef);
      }
   }


   public void initializeTriggerType(ModelType model, String typeId, String typeName, boolean isPull,
         Class<? extends TriggerValidator> clsValidator)
   {
      if (null == XpdlModelUtils.findElementById(model.getApplicationType(), typeId))
      {
         TriggerTypeType typeDef = BpmPackageBuilder.F_CWM.createTriggerTypeType();
         typeDef.setId(typeId);
         typeDef.setName(typeName);
         typeDef.setPullTrigger(isPull);
         typeDef.setIsPredefined(true);

         AttributeUtil.setAttribute(typeDef, PredefinedConstants.VALIDATOR_CLASS_ATT,
               clsValidator.getName());

         model.getTriggerType().add(typeDef);
      }
   }

   private static final String[] defaultContextTypes = {
         PredefinedConstants.DEFAULT_CONTEXT, PredefinedConstants.ENGINE_CONTEXT,
         PredefinedConstants.APPLICATION_CONTEXT, PredefinedConstants.JFC_CONTEXT,
         PredefinedConstants.JSP_CONTEXT};

   private static final String[] defaultTriggerTypes = {
         PredefinedConstants.MANUAL_TRIGGER, PredefinedConstants.JMS_TRIGGER,
         PredefinedConstants.MAIL_TRIGGER, PredefinedConstants.TIMER_TRIGGER};

   private static final String[] defaultConditionTypes = {
         PredefinedConstants.TIMER_CONDITION, PredefinedConstants.EXCEPTION_CONDITION,
         PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION,
         PredefinedConstants.PROCESS_STATECHANGE_CONDITION,
         PredefinedConstants.ACTIVITY_ON_ASSIGNMENT_CONDITION,
   // removed due to #4244 PredefinedConstants.OBSERVER_EVENT_CONDITION
   };

   private static final String[] defaultActionTypes = {
         PredefinedConstants.TRIGGER_ACTION, PredefinedConstants.MAIL_ACTION,
         PredefinedConstants.ABORT_PROCESS_ACTION,
         PredefinedConstants.NOTIFY_OBSERVERS_ACTION,
         PredefinedConstants.COMPLETE_ACTIVITY_ACTION,
         PredefinedConstants.ACTIVATE_ACTIVITY_ACTION,
         PredefinedConstants.DELEGATE_ACTIVITY_ACTION,
         PredefinedConstants.SCHEDULE_ACTIVITY_ACTION,
         PredefinedConstants.EXCLUDE_USER_ACTION, PredefinedConstants.SET_DATA_ACTION};

   private void createDefaultTypes(ModelType model)
   {
      addMetaTypes(
            model,
            defaultContextTypes,
            CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType_HasMappingId(),
                  CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType_HasApplicationPath()});
      addMetaTypes(
            model,
            defaultTriggerTypes,
            CarnotConstants.TRIGGER_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getTriggerTypeType(),
            new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE.getTriggerTypeType_PullTrigger()});
      addMetaTypes(
            model,
            defaultConditionTypes,
            CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_Implementation(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_ActivityCondition(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType_ProcessCondition()});
      addMetaTypes(
            model,
            defaultActionTypes,
            CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_ActivityAction(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_ProcessAction(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_SupportedConditionTypes(),
                  CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType_UnsupportedContexts()});
   }

   private void addMetaTypes(ModelType model, String[] ids, String extensionPointId,
         EClass type, EStructuralFeature[] features)
   {
      // Map extensions = SpiExtensionRegistry.instance().getExtensions(extensionPointId);
      // for (int i = 0; i < ids.length; i++ )
      // {
      // IConfigurationElement config = (IConfigurationElement) extensions.get(ids[i]);
      // CreateMetaTypeCommand command = new CreateMetaTypeCommand(config, type,
      // features);
      // command.setParent(model);
      // command.execute();
      // }
   }
}
