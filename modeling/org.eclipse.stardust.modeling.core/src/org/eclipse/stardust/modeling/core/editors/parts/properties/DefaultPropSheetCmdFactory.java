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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.core.editors.parts.PropertySourceFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class DefaultPropSheetCmdFactory implements IPropSheetCmdFactory
{
   public static final DefaultPropSheetCmdFactory INSTANCE = new DefaultPropSheetCmdFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      SetValueCommand setCommand = new SetValueCommand(descriptor.getDisplayName());
      setCommand.setTarget(target);
      setCommand.setPropertyId(descriptor.getId());
      setCommand.setPropertyValue(value);

      return setCommand;
   }

   public Command createResetValueCommand(IPropertyDescriptor descriptor,
         IPropertySource target)
   {
      ResetValueCommand restoreCmd = new ResetValueCommand();
      restoreCmd.setTarget(target);
      restoreCmd.setPropertyId(descriptor.getId());
      return restoreCmd;
   }

   public Command getSetCommand(EditPart part, EObject object,
         EStructuralFeature feature, Object value)
   {
      IPropertySource target = PropertySourceFactory.getPropertySource(part, object);
      IPropertyDescriptor[] descriptors = target.getPropertyDescriptors();

      for (int j = 0; j < descriptors.length; j++)
      {
         IPropertyDescriptor descriptor = descriptors[j];
         if (feature.equals(descriptor.getId())
               || descriptor.getId() instanceof BoundEObjectPropertyId
               && feature.equals(((BoundEObjectPropertyId) descriptor.getId()).getId()))
         {
            IPropSheetCmdFactory cmdFactory = null;
            if (descriptor instanceof IAdaptable)
            {
               cmdFactory = (IPropSheetCmdFactory) ((IAdaptable) descriptor)
                     .getAdapter(IPropSheetCmdFactory.class);
            }
            if (null == cmdFactory)
            {
               cmdFactory = INSTANCE;
            }
            return cmdFactory.createSetValueCommand(null, descriptor, target, value);
         }
      }

      return UnexecutableCommand.INSTANCE;
   }

   public static void addUpdateConnectionsCommand(CompoundCommand command,
         IIdentifiableModelElement identifiable, IIdentifiableModelElement newValue,
         IIdentifiableModelElement originalValue, EStructuralFeature connectionFeature,
         EStructuralFeature symbolFeature, EStructuralFeature endingFeature)
   {
      for (Iterator i = identifiable.getSymbols().iterator(); i.hasNext();)
      {
         INodeSymbol referenceSymbol = (INodeSymbol) i.next();
         Object obj = referenceSymbol.eGet(connectionFeature);
         if (obj instanceof IConnectionSymbol)
         {
            addUpdateConnectionsCommand(command, newValue, originalValue, symbolFeature,
                  endingFeature, (IConnectionSymbol) obj, referenceSymbol);
         }
         else if (obj instanceof List)
         {
            for (Iterator iter = ((List) obj).iterator(); iter.hasNext();)
            {
               IConnectionSymbol conn = (IConnectionSymbol) iter.next();
               addUpdateConnectionsCommand(command, newValue, originalValue,
                     symbolFeature, endingFeature, conn, referenceSymbol);
            }
         }
      }
   }

   private static void addUpdateConnectionsCommand(CompoundCommand command,
         IIdentifiableModelElement newValue, IIdentifiableModelElement originalValue,
         EStructuralFeature symbolFeature, EStructuralFeature endingFeature,
         IConnectionSymbol connection, INodeSymbol referenceSymbol)
   {
      if (connection != null)
      {
         if (newValue == null)
         {
            command.add(new DeleteConnectionSymbolCmd(connection));
         }
         else if (newValue != originalValue)
         {
            INodeSymbol endingSymbol = DiagramUtil.getClosestSymbol(referenceSymbol,
                  symbolFeature, newValue);
            if (endingSymbol == null)
            {
               command.add(new DeleteConnectionSymbolCmd(connection));
            }
            else
            {
               command.add(new SetValueCmd(connection, endingFeature, endingSymbol));
            }
         }
      }
   }
}
