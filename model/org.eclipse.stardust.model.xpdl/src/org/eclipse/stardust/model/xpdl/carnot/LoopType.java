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
package org.eclipse.stardust.model.xpdl.carnot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Loop Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLoopType()
 * @model extendedMetaData="name='loop_._type'"
 * @generated
 */
public enum LoopType implements Enumerator
{
   /**
    * The '<em><b>Unknown</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #UNKNOWN
    * @generated
    * @ordered
    */
   UNKNOWN_LITERAL(0, "Unknown", "Unknown"),
   /**
    * The '<em><b>None</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NONE
    * @generated NOT
    * @ordered
    */
   NONE_LITERAL(1, "No Loop", "No Loop"),
   /**
    * The '<em><b>While</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #WHILE
    * @generated
    * @ordered
    */
   WHILE_LITERAL(2, "While", "While"),
   /**
    * The '<em><b>Repeat</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #REPEAT
    * @generated
    * @ordered
    */
   REPEAT_LITERAL(3, "Repeat", "Repeat");
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * The '<em><b>Unknown</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #UNKNOWN_LITERAL
    * @model name="Unknown"
    * @generated
    * @ordered
    */
   public static final int UNKNOWN = 0;

   /**
    * The '<em><b>None</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NONE_LITERAL
    * @model name="None"
    * @generated
    * @ordered
    */
   public static final int NONE = 1;

   /**
    * The '<em><b>While</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>While</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #WHILE_LITERAL
    * @model name="While"
    * @generated
    * @ordered
    */
   public static final int WHILE = 2;

   /**
    * The '<em><b>Repeat</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Repeat</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #REPEAT_LITERAL
    * @model name="Repeat"
    * @generated
    * @ordered
    */
   public static final int REPEAT = 3;

   /**
    * An array of all the '<em><b>Loop Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final LoopType[] VALUES_ARRAY =
      new LoopType[]
      {
         UNKNOWN_LITERAL,
         NONE_LITERAL,
         WHILE_LITERAL,
         REPEAT_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Loop Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<LoopType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Loop Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LoopType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LoopType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Loop Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LoopType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LoopType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Loop Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LoopType get(int value)
   {
      switch (value)
      {
         case UNKNOWN: return UNKNOWN_LITERAL;
         case NONE: return NONE_LITERAL;
         case WHILE: return WHILE_LITERAL;
         case REPEAT: return REPEAT_LITERAL;
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final int value;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final String name;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final String literal;

   /**
    * Only this class can construct instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private LoopType(int value, String name, String literal)
   {
      this.value = value;
      this.name = name;
      this.literal = literal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getValue()
   {
     return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
     return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getLiteral()
   {
     return literal;
   }

   /**
    * Returns the literal value of the enumerator, which is its string representation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      return literal;
   }
}
