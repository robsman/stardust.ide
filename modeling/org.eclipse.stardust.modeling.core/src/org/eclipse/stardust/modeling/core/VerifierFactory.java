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
package org.eclipse.stardust.modeling.core;

import ag.carnot.workflow.spi.providers.data.java.Type;

/**
 * @author fherinean
 * @version $Revision$
 */
public class VerifierFactory
{
   public static Verifier byteVerifier = new LongVerifier(
           Verifier.BYTE, Byte.MIN_VALUE, Byte.MAX_VALUE);

   public static Verifier shortVerifier = new LongVerifier(
           Verifier.SHORT, Short.MIN_VALUE, Short.MAX_VALUE);

   public static Verifier intVerifier = new LongVerifier(
           Verifier.INT, Integer.MIN_VALUE, Integer.MAX_VALUE);

   public static Verifier longVerifier = new LongVerifier(
           Verifier.LONG, Long.MIN_VALUE, Long.MAX_VALUE);

   public static Verifier floatVerifier = new DoubleVerifier(
           Verifier.FLOAT, -Float.MAX_VALUE, Float.MAX_VALUE);

   public static Verifier doubleVerifier = new DoubleVerifier(
           Verifier.DOUBLE, -Double.MAX_VALUE, Double.MAX_VALUE);

   public static Verifier moneyVerifier = new DoubleVerifier(
           Verifier.MONEY, -Double.MAX_VALUE, Double.MAX_VALUE);

   public static Verifier getVerifier(Type type)
   {
      Verifier result = null;
      if (Type.Byte.equals(type))
      {
         result = byteVerifier;
      }
      else if (Type.Short.equals(type))
      {
         result = shortVerifier;
      }
      else if (Type.Integer.equals(type))
      {
         result = intVerifier;
      }
      else if (Type.Long.equals(type))
      {
         result = longVerifier;
      }
      else if (Type.Float.equals(type))
      {
         result = floatVerifier;
      }
      else if (Type.Double.equals(type))
      {
         result = doubleVerifier;
      }
      return result;
   }
}
