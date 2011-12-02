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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class SetAttributeReferenceCmd extends Command
{
   private EObject value;
   private EObject undoValue;
   private AttributeType attribute;

   public SetAttributeReferenceCmd(AttributeType parent, EObject value)
   {
      this.attribute = parent;
      this.value = value;
   }

   public void execute()
   {
      redo();
   }

   public void redo()
   {
      undoValue = AttributeUtil.getReferenceElement(attribute);
      AttributeUtil.setReference(attribute, value);
   }

   public void undo()
   {
      AttributeUtil.setReference(attribute, undoValue);
   }

   public EObject getValue()
   {
      return value;
   }
   
   public EObject getUndoValue()
   {
      return undoValue;
   }      
}