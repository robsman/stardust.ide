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

import java.util.Arrays;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;


public class CreateInteractiveApplicationAction extends SelectionAction
{
   private IConfigurationElement config;

   private ApplicationType intApplication;

   public CreateInteractiveApplicationAction(IConfigurationElement config,
         WorkflowModelEditor part)
   {
      super(part);
      this.config = config;
      setId(DiagramActionConstants.CREATE_INTERACTIVE_APPLICATION
            + config.getAttribute(SpiConstants.ID));
      setText(config.getAttribute(SpiConstants.NAME));
      setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1
         && (getModel() != null || isApplicationCategoryNode());
   }

   private boolean isApplicationCategoryNode()
   {
      Object selection = getSelectedObjects().get(0);
      if ((selection instanceof ChildCategoryNode)
            && (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
                  .contains(CarnotWorkflowModelPackage.eINSTANCE
                        .getModelType_Application())))
      {
         return true;
      }
      return false;
   }

   public void run()
   {
      execute(createCommand());
      CreationUtils.showInOutlineAndEdit(intApplication);
   }

   private Command createCommand()
   {
      IdFactory idFactory = new IdFactory(
            "interactive", Diagram_Messages.BASENAME_InteractiveApplication); //$NON-NLS-1$
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      command.add(new CreateMetaTypeCommand(config, CarnotWorkflowModelPackage.eINSTANCE
            .getApplicationContextTypeType(), new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE
                  .getApplicationContextTypeType_HasMappingId(),
            CarnotWorkflowModelPackage.eINSTANCE
                  .getApplicationContextTypeType_HasApplicationPath()}));
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL,
            idFactory, CarnotWorkflowModelPackage.eINSTANCE.getApplicationType())
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
            intApplication = application;
            return application;
         }
      });

      command.setParent((ModelType) (getModel() == null
            ? ((EditPart) getSelectedObjects().get(0)).getParent().getModel()
            : getModel()));

      return command;
   }

   private static ApplicationContextTypeType getApplicationContextType(ModelType model,
         String id)
   {
      return (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(),
            id);
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