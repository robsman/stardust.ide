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
 * A representation of the literals of the enumeration '<em><b>Routing Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRoutingType()
 * @model extendedMetaData="name='routing_._type'"
 * @generated
 */
public enum RoutingType implements Enumerator
{
   /**
    * The '<em><b>Default</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #DEFAULT
    * @generated
    * @ordered
    */
   DEFAULT_LITERAL(0, "Default", "Default"),
   /**
    * The '<em><b>Shortest Path</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SHORTEST_PATH
    * @generated
    * @ordered
    */
   SHORTEST_PATH_LITERAL(1, "ShortestPath", "ShortestPath"),
   /**
    * The '<em><b>Manhattan</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #MANHATTAN
    * @generated
    * @ordered
    */
   MANHATTAN_LITERAL(2, "Manhattan", "Manhattan"),
   /**
    * The '<em><b>Explicit</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #EXPLICIT
    * @generated
    * @ordered
    */
   EXPLICIT_LITERAL(3, "Explicit", "Explicit");
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The '<em><b>Default</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Default</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #DEFAULT_LITERAL
    * @model name="Default"
    * @generated
    * @ordered
    */
   public static final int DEFAULT = 0;

   /**
    * The '<em><b>Shortest Path</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Shortest Path</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SHORTEST_PATH_LITERAL
    * @model name="ShortestPath"
    * @generated
    * @ordered
    */
   public static final int SHORTEST_PATH = 1;

   /**
    * The '<em><b>Manhattan</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Manhattan</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #MANHATTAN_LITERAL
    * @model name="Manhattan"
    * @generated
    * @ordered
    */
   public static final int MANHATTAN = 2;

   /**
    * The '<em><b>Explicit</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Explicit</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #EXPLICIT_LITERAL
    * @model name="Explicit"
    * @generated
    * @ordered
    */
   public static final int EXPLICIT = 3;

   /**
    * An array of all the '<em><b>Routing Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final RoutingType[] VALUES_ARRAY =
      new RoutingType[]
      {
         DEFAULT_LITERAL,
         SHORTEST_PATH_LITERAL,
         MANHATTAN_LITERAL,
         EXPLICIT_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Routing Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<RoutingType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Routing Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static RoutingType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         RoutingType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Routing Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static RoutingType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         RoutingType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Routing Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static RoutingType get(int value)
   {
      switch (value)
      {
         case DEFAULT: return DEFAULT_LITERAL;
         case SHORTEST_PATH: return SHORTEST_PATH_LITERAL;
         case MANHATTAN: return MANHATTAN_LITERAL;
         case EXPLICIT: return EXPLICIT_LITERAL;
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
   private RoutingType(int value, String name, String literal)
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
