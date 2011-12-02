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

import org.eclipse.gef.dnd.SimpleObjectTransfer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class ModelElementTransfer extends SimpleObjectTransfer
{
   public static final String TYPE_NAME = Diagram_Messages.TYPE_NAME_ElementToSymbolTransfer;

   private static ModelElementTransfer TRANSFER = null;

   private static final int TYPE_ID = registerType(TYPE_NAME);

   private ModelElementTransfer()
   {}

   public static ModelElementTransfer getInstance()
   {
      if (TRANSFER == null)
         TRANSFER = new ModelElementTransfer();
      return TRANSFER;
   }

   protected int[] getTypeIds()
   {
      return new int[] {TYPE_ID};
   }

   protected String[] getTypeNames()
   {
      return new String[] {TYPE_NAME};
   }

}
