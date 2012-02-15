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
package org.eclipse.stardust.engine.api.model;

import org.eclipse.stardust.common.StringKey;
import org.eclipse.stardust.model.API_Messages;


/** */
public class LoopType extends StringKey
{
   public static final LoopType Unknown = new LoopType(API_Messages.STR_Unknown);
   public static final LoopType None = new LoopType(API_Messages.STR_NoLoop);
   public static final LoopType While = new LoopType(API_Messages.STR_While);
   public static final LoopType Repeat = new LoopType(API_Messages.STR_Repeat);

   public static LoopType getKey(String id)
   {
      return (LoopType) getKey(LoopType.class, id);
   }

   /** */
   private LoopType(String id)
   {
      super(id, id);
   }
}
