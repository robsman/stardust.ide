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
package org.eclipse.stardust.model.xpdl.builder;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.builder.activity.AbstractActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmInteractiveApplicationActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmManualActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmRouteActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmSubProcessActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.datamapping.BpmDataMappingBuilder;
import org.eclipse.stardust.model.xpdl.builder.datamapping.BpmInDataMappingBuilder;
import org.eclipse.stardust.model.xpdl.builder.datamapping.BpmOutDataMappingBuilder;
import org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder;
import org.eclipse.stardust.model.xpdl.builder.eventaction.BpmExcludeUserEventActionBuilder;
import org.eclipse.stardust.model.xpdl.builder.eventhandler.BpmActivityAssignmentEventHandlerBuilder;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.builder.participant.BpmConditionalPerformerBuilder;
import org.eclipse.stardust.model.xpdl.builder.participant.BpmOrganizationBuilder;
import org.eclipse.stardust.model.xpdl.builder.participant.BpmRoleBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmCamelTriggerBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmDataPathBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmManualTriggerBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmProcessDefinitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.AbstractTransitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmActivitySequenceBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmConditionalTransitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.transition.BpmOtherwiseTransitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.builder.variable.*;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;



public abstract class BpmModelBuilder
{
   public static void assignMissingElementOids(ModelType model)
   {
      long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);

      if ( !model.isSetOid())
      {
         model.setOid(++maxElementOid);
      }

      for (TreeIterator<EObject> modelContents = model.eAllContents(); modelContents.hasNext(); )
      {
         EObject element = modelContents.next();
         if ((element instanceof IModelElement)
               && !((IModelElement) element).isSetElementOid())
         {
            ((IModelElement) element).setElementOid(++maxElementOid);
         }
      }
   }

   public static BpmPackageBuilder newBpmModel()
   {
      return BpmPackageBuilder.newModel();
   }

   public static PrimitiveAccessPointBuilder newPrimitiveAccessPoint(IAccessPointOwner anOwner)
   {
      return PrimitiveAccessPointBuilder.newAccessPoint(anOwner);
   }

   public static StructAccessPointBuilder newStructuredAccessPoint(IAccessPointOwner anOwner)
   {
      return StructAccessPointBuilder.newAccessPoint(anOwner);
   }

   public static DocumentAccessPointBuilder newDocumentAccessPoint(IAccessPointOwner anOwner)
   {
      return DocumentAccessPointBuilder.newAccessPoint(anOwner);
   }
      
   public static CamelApplicationBuilder newCamelApplication(ModelType model)
   {
      return CamelApplicationBuilder.newCamelApplication(model);
   }

   public static WebserviceApplicationBuilder newWebserviceApplication(ModelType model)
   {
      return WebserviceApplicationBuilder.newWebserviceApplication(model);
   }

   public static MessageTransformationApplicationBuilder newMessageTransformationApplication(ModelType model)
   {
      return MessageTransformationApplicationBuilder.newMessageTransformationApplication(model);
   }

   public static ExternalWebApplicationApplicationBuilder newExternalWebApplication(ModelType model)
   {
      return ExternalWebApplicationApplicationBuilder.newExternalWebApplication(model);
   }

   public static BpmPrimitiveVariableBuilder<Object> newPrimitiveVariable()
   {
      return BpmPrimitiveVariableBuilder.newPrimitiveVariable();
   }

   public static BpmPrimitiveVariableBuilder<Object> newPrimitiveVariable(ModelType model)
   {
      return BpmPrimitiveVariableBuilder.newPrimitiveVariable(model);
   }

   public static BpmStructVariableBuilder newStructVariable(ModelType model)
   {
      return BpmStructVariableBuilder.newStructVariable(model);
   }

   public static BpmDocumentVariableBuilder newDocumentVariable(ModelType model)
   {
      return BpmDocumentVariableBuilder.newDocumentVariable(model);
   }

   public static BpmRoleBuilder newRole(ModelType model)
   {
      return BpmRoleBuilder.newRole(model);
   }

   public static BpmOrganizationBuilder newOrganization(ModelType model)
   {
      return BpmOrganizationBuilder.newOrganization(model);
   }

   public static BpmConditionalPerformerBuilder newConditionalPerformer(ModelType model)
   {
      return BpmConditionalPerformerBuilder.newConditionalPerformer(model);
   }

   public static BpmProcessDefinitionBuilder newProcessDefinition()
   {
      return BpmProcessDefinitionBuilder.newProcessDefinition();
   }

   public static BpmProcessDefinitionBuilder newProcessDefinition(ModelType model)
   {
      return BpmProcessDefinitionBuilder.newProcessDefinition(model);
   }

   public static BpmManualTriggerBuilder newManualTrigger()
   {
      return BpmManualTriggerBuilder.newManualTrigger();
   }

   public static BpmManualTriggerBuilder newManualTrigger(ProcessDefinitionType process)
   {
      return BpmManualTriggerBuilder.newManualTrigger(process);
   }

   public static BpmCamelTriggerBuilder newCamelTrigger(ProcessDefinitionType process)
   {
      return BpmCamelTriggerBuilder.newCamelTrigger(process);
   }

   public static BpmDataPathBuilder newDataPath(ProcessDefinitionType process)
   {
      return BpmDataPathBuilder.newDataPath(process);
   }

   public static BpmDataPathBuilder newDescriptor(ProcessDefinitionType process)
   {
      return BpmDataPathBuilder.newDescriptor(process);
   }

   public static BpmDataPathBuilder newInDataPath(ProcessDefinitionType process)
   {
      return BpmDataPathBuilder.newInDataPath(process);
   }

   public static BpmDataPathBuilder newOutDataPath(ProcessDefinitionType process)
   {
      return BpmDataPathBuilder.newOutDataPath(process);
   }

   public static BpmRouteActivityBuilder newRouteActivity(ProcessDefinitionType process)
   {
      return AbstractActivityBuilder.newRouteActivity(process);
   }

   public static BpmManualActivityBuilder newManualActivity()
   {
      return AbstractActivityBuilder.newManualActivity();
   }

   public static BpmManualActivityBuilder newManualActivity(ProcessDefinitionType process)
   {
      return AbstractActivityBuilder.newManualActivity(process);
   }

   public static BpmApplicationActivityBuilder newApplicationActivity(ProcessDefinitionType process)
   {
      return AbstractActivityBuilder.newApplicationActivity(process);
   }

   public static BpmInteractiveApplicationActivityBuilder newInteractiveApplicationActivity(ProcessDefinitionType process)
   {
      return AbstractActivityBuilder.newInteractiveApplicationActivity(process);
   }

   public static BpmSubProcessActivityBuilder newSubProcessActivity(ProcessDefinitionType process)
   {
      return AbstractActivityBuilder.newSubProcessActivity(process);
   }

   public static BpmDataMappingBuilder newDataMapping(ActivityType activity)
   {
      return BpmDataMappingBuilder.newDataMapping(activity);
   }

   public static BpmInDataMappingBuilder newInDataMapping()
   {
      return BpmInDataMappingBuilder.newInDataMapping();
   }

   public static BpmInDataMappingBuilder newInDataMapping(ActivityType activity)
   {
      return BpmInDataMappingBuilder.newInDataMapping(activity);
   }

   public static BpmOutDataMappingBuilder newOutDataMapping()
   {
      return BpmOutDataMappingBuilder.newOutDataMapping();
   }

   public static BpmOutDataMappingBuilder newOutDataMapping(ActivityType activity)
   {
      return BpmOutDataMappingBuilder.newOutDataMapping(activity);
   }

   public static BpmConditionalTransitionBuilder newTransition()
   {
      return AbstractTransitionBuilder.newTransition();
   }

   public static BpmConditionalTransitionBuilder newTransition(
         ProcessDefinitionType process)
   {
      return AbstractTransitionBuilder.newTransition(process);
   }

   public static BpmOtherwiseTransitionBuilder newOtherwiseTransition()
   {
      return AbstractTransitionBuilder.newOtherwiseTransition();
   }

   public static BpmOtherwiseTransitionBuilder newOtherwiseTransition(
         ProcessDefinitionType process)
   {
      return AbstractTransitionBuilder.newOtherwiseTransition(process);
   }

   public static BpmActivitySequenceBuilder newActivitySequence()
   {
      return AbstractTransitionBuilder.newActivitySequence();
   }

   public static BpmActivityAssignmentEventHandlerBuilder newActivityAssignmentHandler(
         ActivityType activity)
   {
      return BpmActivityAssignmentEventHandlerBuilder.newActivityAssignmentHandler(activity);
   }

   public static BpmExcludeUserEventActionBuilder newExcludeUserAction(
         EventHandlerType handler)
   {
      return BpmExcludeUserEventActionBuilder.newExcludeUserAction(handler);
   }

   public static BpmDiagramBuilder newProcessDiagram(ProcessDefinitionType process)
   {
      return BpmDiagramBuilder.newProcessDiagram(process);
   }

   public static BpmDiagramBuilder newModelDiagram(ModelType model)
   {
      return BpmDiagramBuilder.newModelDiagram(model);
   }
}