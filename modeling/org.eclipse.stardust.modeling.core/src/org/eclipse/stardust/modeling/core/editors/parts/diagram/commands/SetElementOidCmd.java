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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class SetElementOidCmd extends Command
{
   private final IModelElement modelElement;

   private Long elementOidBackup;

   public SetElementOidCmd(IModelElement element)
   {
      this.modelElement = element;
   }

   public void execute()
   {
      if (null != modelElement)
      {
         this.elementOidBackup = modelElement.isSetElementOid() ? new Long(
               modelElement.getElementOid()) : null;
      }

      redo();
   }

   public void redo()
   {
      if (null != modelElement)
      {
         if ( !modelElement.isSetElementOid())
         {
            modelElement.setElementOid(ModelUtils.getElementOid(modelElement,
                  ModelUtils.findContainingModel(modelElement)));
         }
      }
   }

   public void undo()
   {
      if (null != modelElement)
      {
         if (null != elementOidBackup)
         {
            modelElement.setElementOid(elementOidBackup.longValue());
         }
         else
         {
            modelElement.unsetElementOid();
         }
      }
   }
}