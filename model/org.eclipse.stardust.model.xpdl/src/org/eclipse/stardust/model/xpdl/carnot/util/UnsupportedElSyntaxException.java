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
package org.eclipse.stardust.model.xpdl.carnot.util;

import ag.carnot.error.PublicException;

/**
 * @author rsauer
 * @version $Revision: 24348 $
 */
public class UnsupportedElSyntaxException extends PublicException
{
   private static final long serialVersionUID = 1L;

   public UnsupportedElSyntaxException(String message, Throwable e)
   {
      super(message, e);
   }

   public UnsupportedElSyntaxException(String message)
   {
      super(message);
   }

   public UnsupportedElSyntaxException(Throwable e)
   {
      super(e);
   }

}
