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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationInitializer;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class ApplicationCommandFactory extends DefaultPropSheetCmdFactory
{
   private final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   public static final ApplicationCommandFactory INSTANCE = new ApplicationCommandFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      if (null == value)
      {
         return createResetValueCommand(descriptor, target);
      }
      else
      {
         CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
         result.add(super.createSetValueCommand(entry, descriptor, target, value));
         addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target, value);
         return result.unwrap();
      }
   }

   public Command createResetValueCommand(IPropertyDescriptor descriptor,
         IPropertySource target)
   {
      CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
      result.add(super.createResetValueCommand(descriptor, target));
      addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target, null);
      return result.unwrap();
   }

   private void addUpdateCommand(CompoundCommand result, BoundEObjectPropertyId id,
                                 IPropertySource target, Object value)
   {
      EStructuralFeature feature = id.getId();
      if (feature.equals(CWM_PKG.getApplicationType_Type()))
      {
         addResetCommands(result, (ApplicationType) target.getEditableValue(),
            (ApplicationTypeType) value);
      }
   }

   private void addResetCommands(CompoundCommand command, ApplicationType data, ApplicationTypeType type)
   {
      List attributes = data.getAttribute();
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attr = (AttributeType) attributes.get(i);
         command.add(new DeleteValueCmd(data, attr, CWM_PKG.getIExtensibleElement_Attribute()));
      }
      IApplicationInitializer initializer = getInitializer(type);
      if (initializer != null)
      {
         attributes = initializer.initialize(data, attributes);
         if (attributes != null)
         {
            for (int i = 0; i < attributes.size(); i++)
            {
               AttributeType attribute = (AttributeType) attributes.get(i);
               command.add(new SetValueCmd(data,
                  CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute(),
                  attribute));
            }
         }
      }
   }

   private IApplicationInitializer getInitializer(ApplicationTypeType type)
   {
      if (type != null)
      {
         SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
         Map extensions = registry.getExtensions(CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);
         IConfigurationElement config = (IConfigurationElement) extensions.get(type.getId());
         if (config != null)
         {
            try
            {
               return (IApplicationInitializer)
                  config.createExecutableExtension("initializerClass"); //$NON-NLS-1$
            }
            catch (CoreException e)
            {
               //e.printStackTrace();
            }
            catch (ClassCastException cce)
            {
               // todo
            }
         }
      }
      return null;
   }

   public static Command getSetTypeCommand(ApplicationTypeType type, ApplicationType application)
   {
      //todo: (fh) although now it's not used, better to pass a valid EditPart
      return INSTANCE.getSetCommand(null, application,
             INSTANCE.CWM_PKG.getApplicationType_Type(), type);
   }
}
