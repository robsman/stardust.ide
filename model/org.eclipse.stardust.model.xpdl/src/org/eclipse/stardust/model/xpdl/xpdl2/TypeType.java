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
package org.eclipse.stardust.model.xpdl.xpdl2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeType()
 * @model extendedMetaData="name='Type_._type'"
 * @generated
 */
public enum TypeType implements Enumerator
{
   /**
    * The '<em><b>STRING</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #STRING
    * @generated
    * @ordered
    */
   STRING_LITERAL(0, "STRING", "STRING"),
   /**
    * The '<em><b>FLOAT</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #FLOAT
    * @generated
    * @ordered
    */
   FLOAT_LITERAL(1, "FLOAT", "FLOAT"),
   /**
    * The '<em><b>INTEGER</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #INTEGER
    * @generated
    * @ordered
    */
   INTEGER_LITERAL(2, "INTEGER", "INTEGER"),
   /**
    * The '<em><b>REFERENCE</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #REFERENCE
    * @generated
    * @ordered
    */
   REFERENCE_LITERAL(3, "REFERENCE", "REFERENCE"),
   /**
    * The '<em><b>DATETIME</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #DATETIME
    * @generated
    * @ordered
    */
   DATETIME_LITERAL(4, "DATETIME", "DATETIME"),
   /**
    * The '<em><b>BOOLEAN</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #BOOLEAN
    * @generated
    * @ordered
    */
   BOOLEAN_LITERAL(5, "BOOLEAN", "BOOLEAN"),
   /**
    * The '<em><b>PERFORMER</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #PERFORMER
    * @generated
    * @ordered
    */
   PERFORMER_LITERAL(6, "PERFORMER", "PERFORMER");
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The '<em><b>STRING</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STRING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #STRING_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int STRING = 0;

   /**
    * The '<em><b>FLOAT</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOAT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #FLOAT_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int FLOAT = 1;

   /**
    * The '<em><b>INTEGER</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #INTEGER_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int INTEGER = 2;

   /**
    * The '<em><b>REFERENCE</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REFERENCE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #REFERENCE_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int REFERENCE = 3;

   /**
    * The '<em><b>DATETIME</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATETIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #DATETIME_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int DATETIME = 4;

   /**
    * The '<em><b>BOOLEAN</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BOOLEAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #BOOLEAN_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int BOOLEAN = 5;

   /**
    * The '<em><b>PERFORMER</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERFORMER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #PERFORMER_LITERAL
    * @model
    * @generated
    * @ordered
    */
	public static final int PERFORMER = 6;

   /**
    * An array of all the '<em><b>Type Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private static final TypeType[] VALUES_ARRAY =
		new TypeType[]
      {
         STRING_LITERAL,
         FLOAT_LITERAL,
         INTEGER_LITERAL,
         REFERENCE_LITERAL,
         DATETIME_LITERAL,
         BOOLEAN_LITERAL,
         PERFORMER_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Type Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final List<TypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Type Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static TypeType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         TypeType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Type Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static TypeType getByName(String name) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         TypeType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Type Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static TypeType get(int value) {
      switch (value)
      {
         case STRING: return STRING_LITERAL;
         case FLOAT: return FLOAT_LITERAL;
         case INTEGER: return INTEGER_LITERAL;
         case REFERENCE: return REFERENCE_LITERAL;
         case DATETIME: return DATETIME_LITERAL;
         case BOOLEAN: return BOOLEAN_LITERAL;
         case PERFORMER: return PERFORMER_LITERAL;
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
	private TypeType(int value, String name, String literal) {
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
