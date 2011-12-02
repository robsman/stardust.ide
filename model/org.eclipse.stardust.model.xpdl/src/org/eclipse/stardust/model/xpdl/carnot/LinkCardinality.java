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
 * A representation of the literals of the enumeration '<em><b>Link Cardinality</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLinkCardinality()
 * @model extendedMetaData="name='linkCardinality_._type'"
 * @generated
 */
public enum LinkCardinality implements Enumerator
{
   /**
    * The '<em><b>Unknown</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #UNKNOWN
    * @generated NOT
    * @ordered
    */
   UNKNOWN_LITERAL(-1, "", ""),
   /**
    * The '<em><b>One</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ONE
    * @generated NOT
    * @ordered
    */
   ONE_LITERAL(0, "*:1", "*:1"),
   /**
    * The '<em><b>Many</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #MANY
    * @generated NOT
    * @ordered
    */
   MANY_LITERAL(1, "*:N", "*:N");
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
   public static final int UNKNOWN = -1;

   /**
    * The '<em><b>One</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ONE_LITERAL
    * @model name="One"
    * @generated
    * @ordered
    */
   public static final int ONE = 0;

   /**
    * The '<em><b>Many</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Many</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #MANY_LITERAL
    * @model name="Many"
    * @generated
    * @ordered
    */
   public static final int MANY = 1;

   /**
    * An array of all the '<em><b>Link Cardinality</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final LinkCardinality[] VALUES_ARRAY =
      new LinkCardinality[]
      {
         UNKNOWN_LITERAL,
         ONE_LITERAL,
         MANY_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Link Cardinality</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<LinkCardinality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Link Cardinality</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkCardinality get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkCardinality result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Cardinality</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkCardinality getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkCardinality result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Cardinality</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkCardinality get(int value)
   {
      switch (value)
      {
         case UNKNOWN: return UNKNOWN_LITERAL;
         case ONE: return ONE_LITERAL;
         case MANY: return MANY_LITERAL;
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
   private LinkCardinality(int value, String name, String literal)
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
