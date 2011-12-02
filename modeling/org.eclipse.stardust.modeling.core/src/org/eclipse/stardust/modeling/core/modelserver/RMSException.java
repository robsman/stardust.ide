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
package org.eclipse.stardust.modeling.core.modelserver;

public class RMSException extends Exception
{
   public static final int DEFAULT = 0;
   
   public static final int CONNECTION_ERROR = 1;
   
   public static final int UNLOCK_NOT_ALLOWED = 2;
   public static final int ALREADY_LOCKED = 3;
   
   public static final int FILE_OUT_OF_DATE = 4;
   public static final int FILE_NOT_EXISTS = 5;   
   
   public static final int ALREADY_SHARED = 6;      

   public static final int CANCELED = 7;         

   public static final int CREATE_LOCK_FILES_FAILED = 8;            
   
   private int type = 0;
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public int getType()
   {
      return type;
   }

   public RMSException(RMSException e)
   {
      super(e.getMessage());
      type = e.getType();
   }   
   
   public RMSException(String message, int type)
   {
      super(message);
      this.type = type;
   }
}