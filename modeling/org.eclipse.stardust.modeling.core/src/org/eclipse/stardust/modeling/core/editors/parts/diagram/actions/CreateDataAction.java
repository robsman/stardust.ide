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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
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
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateTypedModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;


public class CreateDataAction extends SelectionAction
{
   private IConfigurationElement config;
   protected DataType data;

   public CreateDataAction(IConfigurationElement config, WorkflowModelEditor part)
   {
      super(part);
      this.config = config;
      if (config == null)
      {
         setId(DiagramActionConstants.CREATE_GENERIC_DATA);
         setText(Diagram_Messages.CreateDataAction_LB_NewData);
         setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/data.gif")); //$NON-NLS-1$
      }
      else
      {
         setId(DiagramActionConstants.CREATE_DATA + config.getAttribute(SpiConstants.ID));
         setText(config.getAttribute(SpiConstants.NAME));
         setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
      }
   }

   protected boolean calculateEnabled()
   {
      return (config != null
         && getSelectedObjects().size() == 1 && (getModel() != null || isDataCategoryNode()));
   }

   private boolean isDataCategoryNode()
   {
      Object selection = getSelectedObjects().get(0);
      if ((selection instanceof ChildCategoryNode)
            && (Arrays.asList(((ChildCategoryNode) selection).getChildrenFeatures())
                  .contains(CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data())))
      {
         return true;
      }
      return false;
   }

   public void run()
   {
      execute(createCommand());
      CreationUtils.showInOutlineAndEdit(data);
   }

   private Command createCommand()
   {
      IdFactory id = new IdFactory(config == null ? "data" : config.getAttribute(SpiConstants.ID), //$NON-NLS-1$
            config == null ? Diagram_Messages.NodeCreationFactory_BASENAME_Data : config.getAttribute(SpiConstants.NAME));
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      if (config != null)
      {
         command.add(new CreateMetaTypeCommand(config, CarnotWorkflowModelPackage.eINSTANCE
            .getDataTypeType(), new EStructuralFeature[] {}));
      }
      command.add(new CreateTypedModelElementCommand(IContainedElementCommand.MODEL, id,
            config == null ? null : config.getAttribute(SpiConstants.ID),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            CarnotWorkflowModelPackage.eINSTANCE.getDataType())
      {
         protected IModelElement createModelElement()
         {
            DataType element = (DataType) super.createModelElement();
            IDataInitializer initializer = ModelUtils.getInitializer(element.getType());
            if (initializer != null)
            {
               List<AttributeType> attributes = initializer.initialize(element,
                     Collections.<AttributeType>emptyList());
               if (attributes != null)
               {
                  element.getAttribute().addAll(attributes);
               }
            }
            data = element;
            return element;
         }
      });
      command.setParent((ModelType) (getModel() == null
            ? ((EditPart) getSelectedObjects().get(0)).getParent().getModel()
            : getModel()));

      return command;
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