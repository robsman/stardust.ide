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
    * @see #STRING_VALUE
    * @generated
    * @ordered
    */
   STRING(0, "STRING", "STRING"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>FLOAT</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #FLOAT_VALUE
    * @generated
    * @ordered
    */
   FLOAT(1, "FLOAT", "FLOAT"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>INTEGER</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #INTEGER_VALUE
    * @generated
    * @ordered
    */
   INTEGER(2, "INTEGER", "INTEGER"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>REFERENCE</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #REFERENCE_VALUE
    * @generated
    * @ordered
    */
   REFERENCE(3, "REFERENCE", "REFERENCE"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>DATETIME</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #DATETIME_VALUE
    * @generated
    * @ordered
    */
   DATETIME(4, "DATETIME", "DATETIME"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>BOOLEAN</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #BOOLEAN_VALUE
    * @generated
    * @ordered
    */
   BOOLEAN(5, "BOOLEAN", "BOOLEAN"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>PERFORMER</b></em>' literal object.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #PERFORMER_VALUE
    * @generated
    * @ordered
    */
   PERFORMER(6, "PERFORMER", "PERFORMER"); //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * The '<em><b>STRING</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STRING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #STRING
    * @model
    * @generated
    * @ordered
    */
	public static final int STRING_VALUE = 0;

   /**
    * The '<em><b>FLOAT</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOAT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #FLOAT
    * @model
    * @generated
    * @ordered
    */
	public static final int FLOAT_VALUE = 1;

   /**
    * The '<em><b>INTEGER</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #INTEGER
    * @model
    * @generated
    * @ordered
    */
	public static final int INTEGER_VALUE = 2;

   /**
    * The '<em><b>REFERENCE</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REFERENCE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #REFERENCE
    * @model
    * @generated
    * @ordered
    */
	public static final int REFERENCE_VALUE = 3;

   /**
    * The '<em><b>DATETIME</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATETIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #DATETIME
    * @model
    * @generated
    * @ordered
    */
	public static final int DATETIME_VALUE = 4;

   /**
    * The '<em><b>BOOLEAN</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BOOLEAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #BOOLEAN
    * @model
    * @generated
    * @ordered
    */
	public static final int BOOLEAN_VALUE = 5;

   /**
    * The '<em><b>PERFORMER</b></em>' literal value.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERFORMER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @see #PERFORMER
    * @model
    * @generated
    * @ordered
    */
	public static final int PERFORMER_VALUE = 6;

   /**
    * An array of all the '<em><b>Type Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private static final TypeType[] VALUES_ARRAY =
		new TypeType[]
      {
         STRING,
         FLOAT,
         INTEGER,
         REFERENCE,
         DATETIME,
         BOOLEAN,
         PERFORMER,
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
         case STRING_VALUE: return STRING;
         case FLOAT_VALUE: return FLOAT;
         case INTEGER_VALUE: return INTEGER;
         case REFERENCE_VALUE: return REFERENCE;
         case DATETIME_VALUE: return DATETIME;
         case BOOLEAN_VALUE: return BOOLEAN;
         case PERFORMER_VALUE: return PERFORMER;
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
