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
package org.eclipse.stardust.modeling.audittrail;

import org.eclipse.stardust.common.error.PublicException;

/**
 * @author rsauer
 * @version $Revision$
 */
public class AuditTrailManagementException extends PublicException
{
   private static final long serialVersionUID = 1L;

   public AuditTrailManagementException(String message, Throwable e)
   {
      super(message, e);
   }

   public AuditTrailManagementException(String message)
   {
      super(message);
   }

   public AuditTrailManagementException(Throwable e)
   {
      super(e);
   }
}
