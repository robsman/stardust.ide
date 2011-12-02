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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ConnectionBendpointsCommand;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class ConnectionRoutingCommandFactory extends DefaultPropSheetCmdFactory
{
   private final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   public static final ConnectionRoutingCommandFactory INSTANCE = new ConnectionRoutingCommandFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      if ((target.getEditableValue() instanceof IConnectionSymbol)
            && ((null == value) || (RoutingType.DEFAULT_LITERAL.equals(value))))
      {
         return createResetValueCommand(descriptor, target);
      }
      else
      {
         CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());

         if ((target.getEditableValue() instanceof IConnectionSymbol)
               && (value instanceof RoutingType))
         {
            IConnectionSymbol connection = (IConnectionSymbol) target.getEditableValue();
            switch (((RoutingType) value).getValue())
            {
            case RoutingType.DEFAULT:
            case RoutingType.SHORTEST_PATH:
            case RoutingType.MANHATTAN:
               result.add(ConnectionBendpointsCommand.removeAllBendpoints(connection));
               break;
            case RoutingType.EXPLICIT:
               // TODO preserve current bendpoints
               break;
            default:
            // TODO warn
            }
         }

         result.add(super.createSetValueCommand(entry, descriptor, target, value));
         return result.unwrap();
      }
   }

   public Command createResetValueCommand(IPropertyDescriptor descriptor,
         IPropertySource target)
   {
      CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());

      if ((target.getEditableValue() instanceof IConnectionSymbol))
      {
         result.add(ConnectionBendpointsCommand.removeAllBendpoints((IConnectionSymbol) target.getEditableValue()));
      }
      result.add(super.createResetValueCommand(descriptor, target));

      return result.unwrap();
   }

   public static Command getSetRoutingCommand(RoutingType implType, IConnectionSymbol connection)
   {
      //todo: (fh) although now it's not used, better to pass a valid EditPart
      return INSTANCE.getSetCommand(null, connection,
             INSTANCE.CWM_PKG.getIConnectionSymbol_Routing(), implType);
   }
}
