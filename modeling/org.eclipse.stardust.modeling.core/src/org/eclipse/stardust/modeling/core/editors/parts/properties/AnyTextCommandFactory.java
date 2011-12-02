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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public class AnyTextCommandFactory extends DefaultPropSheetCmdFactory
{
   public static final AnyTextCommandFactory INSTANCE = new AnyTextCommandFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      if (null == value)
      {
         return createResetValueCommand(descriptor, target);
      }
      else
      {
         final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
         final CarnotWorkflowModelFactory CWM_FTY = CarnotWorkflowModelFactory.eINSTANCE;

         EReference reference = (EReference) descriptor.getId();
         if (reference.getEReferenceType().equals(CWM_PKG.getTextType()))
         {
            TextType text = CWM_FTY.createTextType();
            ModelUtils.setCDataString(text.getMixed(), (String) value);
            return super.createSetValueCommand(entry, descriptor, target, text);
         }

         if (reference.getEReferenceType().equals(CWM_PKG.getDescriptionType()))
         {
            DescriptionType description = CWM_FTY.createDescriptionType();
            ModelUtils.setCDataString(description.getMixed(), (String) value);
            return super.createSetValueCommand(entry, descriptor, target, description);
         }

         return UnexecutableCommand.INSTANCE;
      }
   }
}
