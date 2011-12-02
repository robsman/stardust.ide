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

import java.util.Map;

import org.eclipse.gef.commands.Command;

public class SetMapValueCmd extends Command
{
   private Object value;
   private Object undoValue;
   private Object key;
   private Map map;

   public SetMapValueCmd(Map map, Object key, Object value)
   {
      this.map = map;
      this.key = key;
      this.value = value;
   }

   public void execute()
   {
      redo();
   }

   public void redo()
   {
      undoValue = value == null ? map.remove(key) : map.put(key, value);
   }

   public void undo()
   {
      if (undoValue == null)
      {
         map.remove(key);
      }
      else
      {
         map.put(key, undoValue);
      }
   }
}