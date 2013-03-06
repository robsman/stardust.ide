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

import org.eclipse.jdt.core.Signature;

/**
 * @author fherinean
 * @version $Revision$
 */
public class MethodInfo
{
   private String label;
   private String encoded;
   private boolean constructor;
   private final boolean isAccessible;

   private boolean usedForObjectCreation;
   private String[] paramNames;
   private String returnName;
   private String[] parameterTypes;
   private String returnType;
   private boolean hasReturn;
   private boolean isPrimitiveReturn;
   private boolean isArrayReturn;

   public MethodInfo(boolean constructor, char[] name, char[] signature,
         boolean isAccessible)
   {
      this(constructor, new String(name), getParameterSignatures(signature),
            getParameterTypes(signature), getReturnSignature(signature),
            getReturnType(signature), isAccessible);
   }

   private static String getReturnType(char[] signature)
   {
      char[] result = Signature.getReturnType(signature);
      return new String(Signature.toString(new String(result)));
   }

   private static String getReturnSignature(char[] signature)
   {
      char[] result = Signature.getReturnType(signature);
      return new String(result);
   }

   private static String[] getParameterTypes(char[] signature)
   {
      char[][] params = Signature.getParameterTypes(signature);
      String[] types = new String[params.length];
      for (int i = 0; i < types.length; i++)
      {
         types[i] = new String(Signature.toString(new String(params[i])));
      }
      return types;
   }

   private static String[] getParameterSignatures(char[] signature)
   {
      char[][] params = Signature.getParameterTypes(signature);
      String[] signatures = new String[params.length];
      for (int i = 0; i < signatures.length; i++)
      {
         signatures[i] = new String(params[i]);
      }
      return signatures;
   }

   public MethodInfo(boolean constructor, String name, String[] parameterSignatures,
         String[] parameterTypes, String returnSignature, String returnType, boolean isAccessible)
   {
      this.constructor = constructor;
      this.usedForObjectCreation = constructor;
      this.isAccessible = isAccessible;

      StringBuffer nameBuffer = new StringBuffer();
      StringBuffer encodedBuffer = new StringBuffer();
      nameBuffer.append(name).append('(');
      encodedBuffer.append(name).append('(');
      for (int i = 0; i < parameterSignatures.length; i++)
      {
         if (i > 0)
         {
            nameBuffer.append(", "); //$NON-NLS-1$
            encodedBuffer.append(',');
         }
         nameBuffer.append(Signature.getSignatureSimpleName(parameterSignatures[i]));
         encodedBuffer.append(parameterTypes[i]);
      }
      nameBuffer.append(")"); // : ").append(Signature.getSignatureSimpleName(result)); //$NON-NLS-1$
      encodedBuffer.append(")"); //$NON-NLS-1$
      this.label = nameBuffer.toString();
      encoded = encodedBuffer.toString();
      
      paramNames = new String[parameterSignatures.length];
      for (int i = 0; i < parameterSignatures.length; i++)
      {
         paramNames[i] = new String(Signature.getSignatureSimpleName(parameterSignatures[i]));
      }
      returnName = new String(Signature.getSignatureSimpleName(returnSignature));
      
      hasReturn = returnSignature.length() > 1 || returnSignature.charAt(0) != Signature.C_VOID;
      isPrimitiveReturn = Signature.getTypeSignatureKind(returnSignature) == Signature.BASE_TYPE_SIGNATURE;
      isArrayReturn = Signature.getTypeSignatureKind(returnSignature) == Signature.ARRAY_TYPE_SIGNATURE;

      this.parameterTypes = parameterTypes;
      this.returnType = returnType;
   }

   /**
    * This always will return true when isConstrutor() is true. Otherwise it will return
    * the previously with setUsedForObjectCreation set value.
    * 
    * @return
    */
   public boolean isUsedForObjectCreation()
   {
      return usedForObjectCreation;
   }

   /**
    * Set whether this method will be used for object creation. If isConstructor() is
    * true, this method will leave the value on true.
    * 
    * @param usedForObjectCreation
    */
   public void setUsedForObjectCreation(boolean usedForObjectCreation)
   {
      if (!isConstructor())
      {
         this.usedForObjectCreation = usedForObjectCreation;
      }
   }

   public String getLabel()
   {
      return label;
   }

   public String getEncoded()
   {
      return encoded;
   }

   public int getParameterCount()
   {
      return parameterTypes.length;
   }

   public String getParameterType(int index)
   {
      return parameterTypes[index];
   }

   public String getReturnType()
   {
      return returnType;
   }

   public boolean hasReturn()
   {
      return hasReturn;
   }

   public boolean isArrayReturn()
   {
      return isArrayReturn;
   }

   public boolean isPrimitiveReturn()
   {
      return isPrimitiveReturn;
   }

   public String getParameterName(int index)
   {
      return paramNames[index];
   }

   public String getReturnName()
   {
      return returnName;
   }

   public boolean isConstructor()
   {
      return constructor;
   }

   public boolean isAccessible()
   {
      return isAccessible;
   }

   public String toString()
   {
      return label;
   }
}
