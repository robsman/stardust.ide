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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

public final class ConversionUtils
{
   public static Direction convert(DirectionType src)
   {
      return DirectionType.IN_LITERAL.equals(src)
            ? Direction.IN
            : DirectionType.OUT_LITERAL.equals(src)
                  ? Direction.OUT
                  : DirectionType.INOUT_LITERAL.equals(src) ? Direction.IN_OUT : null;
   }

   public static DirectionType convert(Direction src)
   {
      return Direction.IN.equals(src)
            ? DirectionType.IN_LITERAL
            : Direction.OUT.equals(src)
                  ? DirectionType.OUT_LITERAL
                  : Direction.IN_OUT.equals(src) ? DirectionType.INOUT_LITERAL : null;
   }

   private ConversionUtils()
   {
   }
}
