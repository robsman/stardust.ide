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
package org.eclipse.stardust.modeling.core.editors;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandHolder;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateTypedModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.ui.PlatformUI;


public class NodeCreationFactory
{
   // todo (fh) reuse Create*Actions
   public static CreationFactory getEventFactory(final IConfigurationElement config,
         boolean isEnding)
   {
      IdFactory id = new IdFactory(config == null
            ? isEnding ? "EndEvent" : "StartEvent" : config.getAttribute("id"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            config == null
                  ? isEnding ? "End Event" : "Start Event" : config.getAttribute("name")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      CompoundDiagramCommand compound = new CompoundDiagramCommand();
      if (config != null)
      {
         compound.add(new CreateMetaTypeCommand(config,
               CarnotWorkflowModelPackage.eINSTANCE.getTriggerTypeType(),
               new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE
                     .getTriggerTypeType_PullTrigger()}));

         compound.add(new CreateTypedModelElementCommand(
               IContainedElementCommand.PROCESS, id,
               config.getAttribute("id"), //$NON-NLS-1$
               CarnotWorkflowModelPackage.eINSTANCE.getModelType_TriggerType(),
               CarnotWorkflowModelPackage.eINSTANCE.getTriggerType()));
      }
      final EClass ref = isEnding ? CarnotWorkflowModelPackage.eINSTANCE
            .getEndEventSymbol() : CarnotWorkflowModelPackage.eINSTANCE
            .getStartEventSymbol();
      compound.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id, ref));

      return new CommandHolder(id, compound, ref);
   }
   
   public static CreationFactory getProcessInterfaceFactory(final IConfigurationElement config)
	   {
	      IdFactory id = new IdFactory(config == null
	            ? "ProcessInterface" : config.getAttribute("id"),   //$NON-NLS-1$ //$NON-NLS-2$
	            config == null
	                  ? "ProcessInterface" : config.getAttribute("name"));  //$NON-NLS-1$ //$NON-NLS-2$
	      CompoundDiagramCommand compound = new CompoundDiagramCommand();
	      FormalParametersType fmt = XpdlPackage.eINSTANCE.getXpdlFactory().createFormalParametersType();
	      SetValueCmd command = new SetValueCmd(IContainedElementCommand.PROCESS,  CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_FormalParameters(), fmt); //int parentLevel, EStructuralFeature feature, Object object
	      compound.add(command);
	      final EClass ref = CarnotWorkflowModelPackage.eINSTANCE.getPublicInterfaceSymbol();
	      compound.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id, ref));
	      return new CommandHolder(id, compound, ref);
	   }

   public static CreationFactory getDataFactory(final IConfigurationElement config)
   {
      IdFactory id = new IdFactory(config == null ? "data" : config.getAttribute("id"), //$NON-NLS-1$ //$NON-NLS-2$
            config == null ? Diagram_Messages.NodeCreationFactory_BASENAME_Data : config
                  .getAttribute("name")); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      if (config != null)
      {
         command.add(new CreateMetaTypeCommand(config,
               CarnotWorkflowModelPackage.eINSTANCE.getDataTypeType(),
               new EStructuralFeature[] {}));
      }
      command.add(new CreateTypedModelElementCommand(IContainedElementCommand.MODEL,
            id,
            config == null ? null : config.getAttribute("id"), //$NON-NLS-1$
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            CarnotWorkflowModelPackage.eINSTANCE.getDataType())
      {

         protected IModelElement createModelElement()
         {
            DataType element = (DataType) super.createModelElement();
            IDataInitializer initializer = ModelUtils.getInitializer(element.getType());
            if (initializer != null)
            {
               List<AttributeType> attributes = initializer.initialize(element, Collections.EMPTY_LIST);
               if (attributes != null)
               {
                  element.getAttribute().addAll(attributes);
               }
            }
            return element;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getDataSymbolType());
   }

   public static CreationFactory getRoleFactory()
   {
      IdFactory id = new IdFactory("Role", Diagram_Messages.BASENAME_Role); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getRoleType()));
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getRoleSymbolType());
   }

   public static CreationFactory getOrganizationFactory()
   {
      IdFactory id = new IdFactory("Organization", Diagram_Messages.BASENAME_Organization); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationType()));
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getOrganizationSymbolType());
   }

   public static CreationFactory getConditionalPerformerFactory()
   {
      IdFactory id = new IdFactory(
            "ConditionalPerformer", Diagram_Messages.BASENAME_ConditionalPerformer); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType()));
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getConditionalPerformerSymbolType());
   }

   public static CreationFactory getApplicationFactory(final IConfigurationElement config)
   {
      IdFactory id = new IdFactory(config == null
            ? "application" : config.getAttribute("id"),  //$NON-NLS-1$ //$NON-NLS-2$
            config == null
                  ? Diagram_Messages.NodeCreationFactory_BASENAME_Application
                  : config.getAttribute("name"));  //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      if (config != null)
      {
         command.add(new CreateMetaTypeCommand(config,
               CarnotWorkflowModelPackage.eINSTANCE.getApplicationTypeType(),
               new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE
                     .getApplicationTypeType_Synchronous()}));
      }
      command.add(new CreateTypedModelElementCommand(IContainedElementCommand.MODEL,
            id,
            config == null ? null : config.getAttribute("id"), //$NON-NLS-1$
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(),
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationType()));
      // todo (fh) initializer ?
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getApplicationSymbolType());
   }

   public static CreationFactory getContextFactory(final IConfigurationElement config)
   {
      IdFactory id = new IdFactory(
            "interactive", Diagram_Messages.BASENAME_InteractiveApplication); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateMetaTypeCommand(config, CarnotWorkflowModelPackage.eINSTANCE
            .getApplicationContextTypeType(), new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE
                  .getApplicationContextTypeType_HasMappingId(),
            CarnotWorkflowModelPackage.eINSTANCE
                  .getApplicationContextTypeType_HasApplicationPath()}));
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationType())
      {
         protected IModelElement createModelElement()
         {
            // todo: do not set oids now but at model saving time.
            ApplicationType application = (ApplicationType) super.createModelElement();
            application.setInteractive(true);
            CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
            ContextType context = factory.createContextType();
            context.setType(getApplicationContextType(getModel(), config
                  .getAttribute("id"))); //$NON-NLS-1$
            application.getContext().add(context);
            return application;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getApplicationSymbolType());
   }

   private static ApplicationContextTypeType getApplicationContextType(ModelType model,
         String id)
   {
      return (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(),
            id);
   }

   public static CreationFactory getActivityFactory(
         final ActivityImplementationType implementation)
   {
      IdFactory id = new IdFactory("Activity", implementation == null //$NON-NLS-1$
            ? "Activity" //$NON-NLS-1$
            : ModelUtils.getActivityImplementationTypeText(implementation)
                  + Diagram_Messages.BASENAME_P2_Activity);
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.PROCESS, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivityType())
      {
         protected IModelElement createModelElement()
         {
            ActivityType activity = (ActivityType) super.createModelElement();
            if (activity != null)
            {
               activity.setImplementation(implementation);
               if (ActivityImplementationType.SUBPROCESS_LITERAL.equals(implementation))
               {
                  activity.setSubProcessMode(SubProcessModeType.SYNC_SHARED_LITERAL);
               }
            }
            return activity;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getActivitySymbolType());
   }

   public static CreationFactory getProcessDefinitionFactory()
   {
      IdFactory id = new IdFactory(
            "ProcessDefinition", Diagram_Messages.BASENAME_ProcessDefinition); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType())
      {
         protected IModelElement createModelElement()
         {
            ProcessDefinitionType process = (ProcessDefinitionType) super
                  .createModelElement();
            CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
            DiagramType diagram = factory.createDiagramType();
            diagram.setName(Diagram_Messages.DIAGRAM_NAME_Default);
            diagram.setElementOid(ModelUtils.getElementOid(diagram, getModel()) + 1);            
            diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(
                    PlatformUI.getPreferenceStore().getString(
                          BpmProjectNature.PREFERENCE_MODELING_DIRECTION)) ?
                    OrientationType.VERTICAL_LITERAL : OrientationType.HORIZONTAL_LITERAL);
            DiagramModeType defaultMode;
            if (PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_CLASSIC_MODE))
            {
               defaultMode = DiagramModeType.MODE_400_LITERAL;
            }
            else
            {
               defaultMode = DiagramModeType.MODE_450_LITERAL;
            }
            diagram.setMode(defaultMode);            
            process.getDiagram().add(diagram);
            PoolSymbol pool = DiagramUtil.createDefaultPool(null);
            pool.setElementOid(ModelUtils.getElementOid(pool, getModel()) + 2);
            diagram.getPoolSymbols().add(pool);
            return process;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getProcessSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getProcessSymbolType());
   }

   public static CreationFactory getModelerFactory()
   {
      IdFactory id = new IdFactory("Modeler", Diagram_Messages.BASENAME_Modeler); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getModelerType()));
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getModelerSymbolType()));
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getModelerSymbolType());
   }

   public static CreationFactory getPoolFactory()
   {
      IdFactory id = new IdFactory("Pool", Diagram_Messages.BASENAME_Pool); //$NON-NLS-1$
      IContainedElementCommand command = new CreateSymbolCommand(IDiagramCommand.DIAGRAM,
            id, CarnotWorkflowModelPackage.eINSTANCE.getPoolSymbol());
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getPoolSymbol());
   }

   public static CreationFactory getLaneFactory()
   {
      IdFactory id = new IdFactory("Lane", Diagram_Messages.BASENAME_Lane); //$NON-NLS-1$
      IContainedElementCommand command = new CreateSymbolCommand(IDiagramCommand.POOL,
            id, CarnotWorkflowModelPackage.eINSTANCE.getLaneSymbol());
      return new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getLaneSymbol());
   }

   public static CreationFactory getTextFactory(EditPartRegistry registry)
   {
      return createAndEditSymbol(Diagram_Messages.BASENAME_Text,
            CarnotWorkflowModelPackage.eINSTANCE.getTextSymbolType(), registry);
   }

   public static CreationFactory getAnnotationFactory(EditPartRegistry registry)
   {
      return createAndEditSymbol(Diagram_Messages.BASENAME_Annotation,
            CarnotWorkflowModelPackage.eINSTANCE.getAnnotationSymbolType(), registry);
   }

   private static CreationFactory createAndEditSymbol(String base, EClass eClass,
         final EditPartRegistry registry)
   {
      final Object[] model = new Object[1];
      IdFactory id = new IdFactory(base, base);
      final CreateSymbolCommand symCmd = new CreateSymbolCommand(
            IContainedElementCommand.PARENT, id, eClass)
      {
         protected IModelElement createModelElement()
         {
            model[0] = super.createModelElement();
            return (IModelElement) (model[0]);
         }
      };

      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(symCmd);
      command.add(new Command()
      {
         public void execute()
         {
            Request request = new Request(RequestConstants.REQ_DIRECT_EDIT);
            EditPart editPart = registry.findEditPart(model[0]);
            editPart.performRequest(request);
         }

         public void redo()
         {
         // intentionally empty
         }
      });
      return new CommandHolder(id, command, eClass);
   }
}