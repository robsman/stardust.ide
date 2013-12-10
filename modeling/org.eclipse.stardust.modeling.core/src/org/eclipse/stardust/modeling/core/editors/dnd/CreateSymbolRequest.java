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
package org.eclipse.stardust.modeling.core.editors.dnd;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandHolder;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

import org.eclipse.ui.PlatformUI;

/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateSymbolRequest extends CreateRequest
{
   private WorkflowModelEditor editor;

   public CreateSymbolRequest(WorkflowModelEditor editor, ModelElementTransfer transfer,
         EditDomain editDomain)
   {
      this.editor = editor;
      Object transferElement = transfer.getObject();
      SymbolCreationFactory factory = null;
      factory = ModelElementSymbolCreationFactory.getFactory(transferElement);

      // here we need the modelelement
      factory.setEditor(editor);
      if(transferElement instanceof IIdentifiableModelElement
            || transferElement instanceof IObjectDescriptor)
      {
         factory.setTransferredModelElement(transferElement);
      }

      factory.setEditDomain(editDomain);
      setFactory(factory);
      @SuppressWarnings("unchecked")
      Map<Object, Object> extendedData = getExtendedData();
      extendedData.put(ModelElementTransfer.TYPE_NAME, transferElement);
   }

   public void setSymbolContainer(ISymbolContainer container)
   {
      if (getFactory() instanceof SymbolCreationFactory)
      {
         SymbolCreationFactory factory = (SymbolCreationFactory) getFactory();
         factory.setSymbolContainer(container);
      }
   }

   public ISymbolContainer getSymbolContainer()
   {
      return ((SymbolCreationFactory) getFactory()).getSymbolContainer();
   }

   public void setLocation(Point location)
   {
      super.setLocation(location);
      if (getFactory() instanceof SymbolCreationFactory)
      {
         SymbolCreationFactory factory = (SymbolCreationFactory) getFactory();
         factory.setLocation(location);
      }
   }

   public boolean isEnabled()
   {
      return ((SymbolCreationFactory) getFactory()).isEnabled();
   }

   public boolean isLockRequired()
   {
      return ((SymbolCreationFactory) getFactory()).isLockRequired();
   }

   // TODO: merge setFactoryForXXX and use generic emodeling
   private void setFactoryForProcess(final IObjectDescriptor descriptor, final ProcessDefinitionType process)
   {
      final String subprocessName = process == null ? descriptor.getLabel() : process.getName();
      IdFactory id = new IdFactory("Activity", //$NON-NLS-1$
            ActivityImplementationType.SUBPROCESS_LITERAL + Diagram_Messages.BASENAME_P2_Activity)
      {
         public String getName()
         {
            return PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION)
                  ? subprocessName
                  : super.getName();
         }
      };
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      final ModelType model = editor.getWorkflowModel();
      ConnectionManager cm = editor.getConnectionManager();
      final ProcessDefinitionType[] remoteProcess = new ProcessDefinitionType[] {process};
      if (process == null)
      {
         try
         {
            if (descriptor instanceof IObjectReference && cm.mustLink(descriptor))
            {
               remoteProcess[0] = ((IObjectReference) descriptor).getEObject();
            }
            else
            {
               Command cmd = cm.linkObject(model, new IObjectDescriptor[] {descriptor});
               if (cmd != null)
               {
                  command.add(cmd);
               }
               else
               {
                  command.add(UnexecutableCommand.INSTANCE);
               }
            }
         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      command.add(new CreateModelElementCommand(IContainedElementCommand.PROCESS, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivityType())
      {
         protected IModelElement createModelElement()
         {
            ActivityType activity = (ActivityType) super.createModelElement();
            if (activity != null)
            {
               activity.setImplementation(ActivityImplementationType.SUBPROCESS_LITERAL);
               List<ProcessDefinitionType> list = model.getProcessDefinition();
               ProcessDefinitionType subprocess = remoteProcess[0];
               boolean isLink = subprocess != null && process == null;
               if (subprocess == null)
               {
                  subprocess = (ProcessDefinitionType) ModelElementSymbolCreationFactory.findMatchingItem(descriptor, list.iterator());
               }
               if (subprocess != null)
               {
                  if (isLink)
                  {
                     AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
                     ModelType processModel = ModelUtils.findContainingModel(subprocess);
                     if (processModel != null)
                     {
                        IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
                        idRef.setRef(subprocess.getId());
                        idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, model, processModel));
                        activity.setExternalRef(idRef);
                        activity.setSubProcessMode(SubProcessModeType.SYNC_SEPARATE_LITERAL);
                     }
                  }
                  else
                  {
                     activity.setImplementationProcess(subprocess);
                  }
               }
            }
            return activity;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType()));
      setFactory(new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getActivitySymbolType()));
   }

   public void setFactoryForDescriptor(IObjectDescriptor descriptor)
   {
      Object type = descriptor.getType();
      if (CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType().equals(type))
      {
         setFactoryForProcess(descriptor, null);
      }
      if (XpdlPackage.eINSTANCE.getTypeDeclarationType().equals(type))
      {
         setFactoryForData(descriptor);
      }
      if (CarnotWorkflowModelPackage.eINSTANCE.getApplicationType().equals(type))
      {
         setFactoryForApplication(descriptor);
      }
   }

   private void setFactoryForApplication(final IObjectDescriptor descriptor)
   {
      IdFactory id = new IdFactory("Activity", //$NON-NLS-1$
            ActivityImplementationType.APPLICATION_LITERAL + Diagram_Messages.BASENAME_P2_Activity);
      CompoundDiagramCommand command = new CompoundDiagramCommand();
      final ModelType model = editor.getWorkflowModel();
      ConnectionManager cm = editor.getConnectionManager();
      final ApplicationType[] remoteApplication = new ApplicationType[] {null};
      try
      {
         if (descriptor instanceof IObjectReference && cm.mustLink(descriptor))
         {
            remoteApplication[0] = ((IObjectReference) descriptor).getEObject();
         }
         else
         {
            Command cmd = cm.linkObject(model, new IObjectDescriptor[] {descriptor});
            if (cmd != null)
            {
               command.add(cmd);
            }
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      command.add(new CreateModelElementCommand(IContainedElementCommand.PROCESS, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivityType())
      {
         protected IModelElement createModelElement()
         {
            ActivityType activity = (ActivityType) super.createModelElement();
            if (activity != null)
            {
               activity.setImplementation(ActivityImplementationType.APPLICATION_LITERAL);
               List<ApplicationType> list = model.getApplication();
               ApplicationType application = remoteApplication[0];
               boolean isLink = application != null;
               if (application == null)
               {
                  application = (ApplicationType) ModelElementSymbolCreationFactory.findMatchingItem(descriptor, list.iterator());
               }
               if (application != null)
               {
                  if (isLink)
                  {
                     AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
                     ModelType processModel = ModelUtils.findContainingModel(application);
                     if (processModel != null)
                     {
                        IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
                        idRef.setRef(application.getId());
                        idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, model, processModel));
                        activity.setExternalRef(idRef);
                     }
                  }
                  else
                  {
                     activity.setApplication(application);
                  }
               }
            }
            return activity;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType()));
      setFactory(new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getActivitySymbolType()));
   }

   private void setFactoryForData(final IObjectDescriptor descriptor)
   {
      final boolean javaEnumeration = TypeDeclarationUtils.isEnumeration((TypeDeclarationType) ((IObjectReference) descriptor).getEObject(), true);

      CompoundDiagramCommand command = new CompoundDiagramCommand();
      final ModelType model = editor.getWorkflowModel();
      ConnectionManager cm = editor.getConnectionManager();

      String name = Diagram_Messages.TXT_STRUCTURED_DATA;
      final org.eclipse.stardust.model.xpdl.carnot.DataTypeType[] struct =
         new org.eclipse.stardust.model.xpdl.carnot.DataTypeType[] {
            (org.eclipse.stardust.model.xpdl.carnot.DataTypeType) ModelUtils.findIdentifiableElement(
            model.getDataType(), PredefinedConstants.STRUCTURED_DATA)};
      if (struct[0] == null && !javaEnumeration)
      {
         Map<String, IConfigurationElement> dataExtensions = SpiExtensionRegistry.instance().getExtensions(
               CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);

         IConfigurationElement config = (IConfigurationElement) dataExtensions.get(PredefinedConstants.STRUCTURED_DATA);
         if (null != config)
         {
            name = config.getAttribute(SpiConstants.NAME);
            CreateMetaTypeCommand cmdCreateMetaType = new CreateMetaTypeCommand(config,
                  CarnotWorkflowModelPackage.eINSTANCE.getDataTypeType(),
                  new EStructuralFeature[] {})
            {
               @Override
               protected IModelElement createModelElement()
               {
                  struct[0] = (org.eclipse.stardust.model.xpdl.carnot.DataTypeType) super.createModelElement();
                  return struct[0];
               }

               @Override
               protected Object clone() throws CloneNotSupportedException
               {
                  return super.clone();
               }
            };
            command.add(cmdCreateMetaType);
         }
      }

      final TypeDeclarationType[] remoteDeclaration = new TypeDeclarationType[] {null};
      try
      {
         if (descriptor instanceof IObjectReference && cm.mustLink(descriptor))
         {
            remoteDeclaration[0] = ((IObjectReference) descriptor).getEObject();
         }
         else
         {
            Command cmd = cm.linkObject(model, new IObjectDescriptor[] {descriptor});
            if (cmd != null)
            {
               command.add(cmd);
            }
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      IdFactory id = new IdFactory(PredefinedConstants.STRUCTURED_DATA, name);
      command.add(new CreateModelElementCommand(IContainedElementCommand.MODEL, id,
            CarnotWorkflowModelPackage.eINSTANCE.getDataType())
      {
         protected IModelElement createModelElement()
         {
            DataType data = (DataType) super.createModelElement();
            if (data != null)
            {
               if(!javaEnumeration)
               {
                  data.setType(struct[0]);
               }
               else
               {
                  data.setType(GenericUtils.getDataTypeType(model, PredefinedConstants.PRIMITIVE_DATA));
                  AttributeUtil.setAttribute(data, PredefinedConstants.TYPE_ATT,
                        "org.eclipse.stardust.engine.core.pojo.data.Type", Type.Enumeration.getId()); //$NON-NLS-1$
                  AttributeUtil.setReference(data, StructuredDataConstants.TYPE_DECLARATION_ATT,
                        (TypeDeclarationType) ((IObjectReference) descriptor).getEObject());
               }

               AttributeUtil.setAttribute(data, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
               AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$

               TypeDeclarationsType declarations = model.getTypeDeclarations();
               List<TypeDeclarationType> list = Collections.emptyList();
               if (declarations != null)
               {
                  list = declarations.getTypeDeclaration();
               }
               TypeDeclarationType decl = remoteDeclaration[0];
               if (decl == null)
               {
                  decl = (TypeDeclarationType) ModelElementSymbolCreationFactory.findMatchingItem(descriptor, list.iterator());
                  if (decl != null)
                  {
                     AttributeUtil.setAttribute(data, StructuredDataConstants.TYPE_DECLARATION_ATT, decl.getId());
                  }
               }
               else
               {
                  AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
                  ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
                  ModelType processModel = ModelUtils.findContainingModel(decl);
                  if (processModel != null)
                  {
                     reference.setLocation(ImportUtils.getPackageRef(descriptor, model, processModel).getId());
                  }
                  reference.setXref(remoteDeclaration[0].getId());
                  data.setExternalReference(reference);
               }
            }
            return data;
         }
      });
      command.add(new CreateSymbolCommand(IContainedElementCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType()));
      setFactory(new CommandHolder(id, command, CarnotWorkflowModelPackage.eINSTANCE
            .getDataSymbolType()));
   }

   public void setFactoryForProcess(ProcessDefinitionType process)
   {
      setFactoryForProcess(null, process);
   }
}