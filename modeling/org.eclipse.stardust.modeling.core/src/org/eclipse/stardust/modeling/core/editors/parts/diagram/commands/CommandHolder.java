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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CommandHolder implements CreationFactory
{
   private IContainedElementCommand command;

   private EClass eClass;

   private IdFactory id;

   public CommandHolder(IdFactory id, IContainedElementCommand command, EClass eClass)
   {
      this.id = id;
      this.command = command;
      this.eClass = eClass;
   }

   public Object getNewObject()
   {
      if (id != null)
      {
         id.setReferingElement(null);
      }
      return command.duplicate();
   }

   public Object getObjectType()
   {
      return eClass;
   }
}
