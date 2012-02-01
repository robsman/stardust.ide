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
 * A representation of the literals of the enumeration '<em><b>Link End Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLinkEndStyle()
 * @model extendedMetaData="name='linkEndStyle_._type'"
 * @generated
 */
public enum LinkEndStyle implements Enumerator
{
   /**
    * The '<em><b>Unknown</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #UNKNOWN
    * @generated NOT
    * @ordered
    */
   UNKNOWN_LITERAL(-1, "", ""), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>No Arrow</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NO_ARROW
    * @generated NOT
    * @ordered
    */
   NO_ARROW_LITERAL(0, "without Symbol", "without Symbol"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Open Triangle</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #OPEN_TRIANGLE
    * @generated NOT
    * @ordered
    */
   OPEN_TRIANGLE_LITERAL(1, "open Triangle", "open Triangle"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Empty Triangle</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #EMPTY_TRIANGLE
    * @generated NOT
    * @ordered
    */
   EMPTY_TRIANGLE_LITERAL(2, "empty Triangle", "empty Triangle"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Filled Triangle</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #FILLED_TRIANGLE
    * @generated NOT
    * @ordered
    */
   FILLED_TRIANGLE_LITERAL(3, "filled Triangle", "filled Triangle"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Empty Rhombus</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #EMPTY_RHOMBUS
    * @generated NOT
    * @ordered
    */
   EMPTY_RHOMBUS_LITERAL(4, "empty Rhombus", "empty Rhombus"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Filled Rhombus</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #FILLED_RHOMBUS
    * @generated NOT
    * @ordered
    */
   FILLED_RHOMBUS_LITERAL(5, "filled Rhombus", "filled Rhombus"); //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * The '<em><b>No Arrow</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>No Arrow</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NO_ARROW_LITERAL
    * @model name="NoArrow"
    * @generated
    * @ordered
    */
   public static final int NO_ARROW = 0;

   /**
    * The '<em><b>Open Triangle</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Open Triangle</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #OPEN_TRIANGLE_LITERAL
    * @model name="OpenTriangle"
    * @generated
    * @ordered
    */
   public static final int OPEN_TRIANGLE = 1;

   /**
    * The '<em><b>Empty Triangle</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Empty Triangle</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #EMPTY_TRIANGLE_LITERAL
    * @model name="EmptyTriangle"
    * @generated
    * @ordered
    */
   public static final int EMPTY_TRIANGLE = 2;

   /**
    * The '<em><b>Filled Triangle</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Filled Triangle</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #FILLED_TRIANGLE_LITERAL
    * @model name="FilledTriangle"
    * @generated
    * @ordered
    */
   public static final int FILLED_TRIANGLE = 3;

   /**
    * The '<em><b>Empty Rhombus</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Empty Rhombus</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #EMPTY_RHOMBUS_LITERAL
    * @model name="EmptyRhombus"
    * @generated
    * @ordered
    */
   public static final int EMPTY_RHOMBUS = 4;

   /**
    * The '<em><b>Filled Rhombus</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Filled Rhombus</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #FILLED_RHOMBUS_LITERAL
    * @model name="FilledRhombus"
    * @generated
    * @ordered
    */
   public static final int FILLED_RHOMBUS = 5;

   /**
    * An array of all the '<em><b>Link End Style</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final LinkEndStyle[] VALUES_ARRAY =
      new LinkEndStyle[]
      {
         UNKNOWN_LITERAL,
         NO_ARROW_LITERAL,
         OPEN_TRIANGLE_LITERAL,
         EMPTY_TRIANGLE_LITERAL,
         FILLED_TRIANGLE_LITERAL,
         EMPTY_RHOMBUS_LITERAL,
         FILLED_RHOMBUS_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Link End Style</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<LinkEndStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Link End Style</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkEndStyle get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkEndStyle result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link End Style</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkEndStyle getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkEndStyle result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link End Style</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkEndStyle get(int value)
   {
      switch (value)
      {
         case UNKNOWN: return UNKNOWN_LITERAL;
         case NO_ARROW: return NO_ARROW_LITERAL;
         case OPEN_TRIANGLE: return OPEN_TRIANGLE_LITERAL;
         case EMPTY_TRIANGLE: return EMPTY_TRIANGLE_LITERAL;
         case FILLED_TRIANGLE: return FILLED_TRIANGLE_LITERAL;
         case EMPTY_RHOMBUS: return EMPTY_RHOMBUS_LITERAL;
         case FILLED_RHOMBUS: return FILLED_RHOMBUS_LITERAL;
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
   private LinkEndStyle(int value, String name, String literal)
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
