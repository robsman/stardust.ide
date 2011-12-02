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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationInitializer;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateTypedModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;


public class CreateApplicationAction extends SelectionAction
{

   private IConfigurationElement config;

   private ApplicationType application;

   public CreateApplicationAction(IConfigurationElement config, WorkflowModelEditor part)
   {
      super(part);
      this.config = config;
      if (config == null)
      {
         setId(DiagramActionConstants.CREATE_GENERIC_APPLICATION);
         setText(Diagram_Messages.LB_EXTMENU_NewApplication);
         setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/application.gif")); //$NON-NLS-1$
      }
      else
      {
         setId(DiagramActionConstants.CREATE_APPLICATION + config.getAttribute(SpiConstants.ID));
         setText(config.getAttribute(SpiConstants.NAME));
         setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
      }
   }

   protected boolean calculateEnabled()
   {
      return (config == null ^ !DiagramPlugin.isBusinessView(
         (WorkflowModelEditor) getWorkbenchPart()))
         && getSelectedObjects().size() == 1
         && (getModel() != null || isApplicationCategoryNode());
   }

   private boolean isApplicationCategoryNode()
   {
      Object selection = getSelectedObjects().get(0);
      return selection instanceof ChildCategoryNode
            && Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
            .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Application());
   }

   public void run()
   {
      execute(createCommand());
      CreationUtils.showInOutlineAndEdit(application);
   }

   private CompoundDiagramCommand createCommand()
   {
      IdFactory id = new IdFactory(config == null ? "application" : config.getAttribute(SpiConstants.ID), //$NON-NLS-1$
            config == null ? Diagram_Messages.NodeCreationFactory_BASENAME_Application : config.getAttribute(SpiConstants.NAME));
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      if (config != null)
      {
         command.add(new CreateMetaTypeCommand(config, CarnotWorkflowModelPackage.eINSTANCE
               .getApplicationTypeType(),
               new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE
                     .getApplicationTypeType_Synchronous()}));
      }
      command.add(new CreateTypedModelElementCommand(IContainedElementCommand.MODEL, id,
            config == null ? null : config.getAttribute(SpiConstants.ID),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(),
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationType())
      {

         protected IModelElement createModelElement()
         {
            ApplicationType element = (ApplicationType) super.createModelElement();
            IApplicationInitializer initializer = getInitializer(element.getType());
            if (initializer != null)
            {
               List attributes = initializer.initialize(element, new ArrayList());
               if (attributes != null)
               {
                  element.getAttribute().addAll(attributes);
               }
            }
            application = element;
            return element;
         }
      });

      command.setParent((ModelType) (getModel() == null
            ? ((EditPart) getSelectedObjects().get(0)).getParent().getModel()
            : getModel()));

      return command;
   }

   private IApplicationInitializer getInitializer(ApplicationTypeType type)
   {
      if (type != null)
      {
         SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
         Map extensions = registry
               .getExtensions(CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);
         IConfigurationElement config = (IConfigurationElement) extensions.get(type
               .getId());
         if (config != null)
         {
            try
            {
               return (IApplicationInitializer) config
                     .createExecutableExtension("initializerClass"); //$NON-NLS-1$
            }
            catch (CoreException e)
            {
               // e.printStackTrace();
            }
            catch (ClassCastException cce)
            {
               // todo
            }
         }
      }
      return null;
   }

   private ModelType getModel()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof EditPart)
      {
         Object model = ((EditPart) selection).getModel();
         if (model instanceof ModelType)
         {
            return (ModelType) model;
         }
      }
      return null;
   }
}