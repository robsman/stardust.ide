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
package org.eclipse.stardust.modeling.validation.util;

import org.eclipse.jdt.core.Flags;

public class FieldInfo
{
   private String fieldName;
   private String fieldType;
   private int flags = Flags.AccPublic;

   public FieldInfo(String fieldName, String fieldType)
   {
      this.fieldName = fieldName;
      this.fieldType = fieldType;      
   }

   public FieldInfo(String fieldName, String fieldType, int flags) {
	   this(fieldName, fieldType);
	   this.flags = flags;
   }
   
   public String getFieldName()
   {
      return fieldName;
   }

   public String getFieldType()
   {
      return fieldType;
   }
   
   public int getFlags() {
	   return flags;
   } 
   
   public boolean isPrivate() {
	  return ((flags & Flags.AccPrivate) != 0); 
   }
   
   public boolean isPublic() {
	   return ((flags & Flags.AccPublic) != 0); 
   }
   
   public boolean isEnum() {
	   return ((flags & Flags.AccEnum) != 0); 
   }
   
   public boolean isProtected() {
	   return ((flags & Flags.AccProtected) != 0); 
   }   

}