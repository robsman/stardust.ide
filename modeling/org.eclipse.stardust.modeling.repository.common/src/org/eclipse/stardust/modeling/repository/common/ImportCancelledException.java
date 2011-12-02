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
package org.eclipse.stardust.modeling.repository.common;

/**
 * Marker class to signal that an import operation should be cancelled either as a result
 * of an exception or user interaction.
 * 
 * @author herinean
 * @version $Revision$
 */
public class ImportCancelledException extends RuntimeException
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public ImportCancelledException()
   {
      // TODO Auto-generated constructor stub
   }

   public ImportCancelledException(Exception rootCause)
   {
      super(rootCause);
   }
}
