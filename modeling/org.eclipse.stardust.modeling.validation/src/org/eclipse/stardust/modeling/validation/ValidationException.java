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
package org.eclipse.stardust.modeling.validation;

import org.eclipse.stardust.common.error.PublicException;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ValidationException extends PublicException
{
   private final Object source;

   public ValidationException(String message, Object source)
   {
      super(message);

      this.source = source;
   }

   public Object getSource()
   {
      return source;
   }
}
