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
package org.eclipse.stardust.modeling.core.editors.tools;

public class CommandCanceledException extends RuntimeException
{
   /**
    * to make eclipse happy
    */
   private static final long serialVersionUID = 1L;

   public CommandCanceledException(String message)
   {
      super(message);
   }

   public CommandCanceledException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
