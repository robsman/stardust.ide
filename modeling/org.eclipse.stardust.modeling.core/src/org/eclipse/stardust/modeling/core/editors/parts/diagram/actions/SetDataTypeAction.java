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

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DataCommandFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetDataTypeAction extends Action
{
   private DataType data;
   private EditDomain domain;
   private DataTypeType type;

   public SetDataTypeAction(DataTypeType type, DataType data, EditDomain domain)
   {
      super(type.getName());
      this.type = type;
      this.data = data;
      this.domain = domain;
   }

   public void run()
   {
      Command command = DataCommandFactory.getSetTypeCommand(type, data);
      domain.getCommandStack().execute(command);
   }

   public DataTypeType getType()
   {
      return type;
   }
}
