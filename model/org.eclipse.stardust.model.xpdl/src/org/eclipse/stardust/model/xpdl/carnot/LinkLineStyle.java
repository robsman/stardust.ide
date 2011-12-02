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
 * A representation of the literals of the enumeration '<em><b>Link Line Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLinkLineStyle()
 * @model extendedMetaData="name='linkLineStyle_._type'"
 * @generated
 */
public enum LinkLineStyle implements Enumerator
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
    * The '<em><b>Normal</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NORMAL
    * @generated NOT
    * @ordered
    */
   NORMAL_LITERAL(0, "normal line", "normal line"),
   /**
    * The '<em><b>Short Strokes</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SHORT_STROKES
    * @generated NOT
    * @ordered
    */
   SHORT_STROKES_LITERAL(1, "short strokes", "short strokes"),
   /**
    * The '<em><b>Long Strokes</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #LONG_STROKES
    * @generated NOT
    * @ordered
    */
   LONG_STROKES_LITERAL(2, "long strokes", "long strokes");
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
    * The '<em><b>Normal</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Normal</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NORMAL_LITERAL
    * @model name="Normal"
    * @generated
    * @ordered
    */
   public static final int NORMAL = 0;

   /**
    * The '<em><b>Short Strokes</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Short Strokes</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SHORT_STROKES_LITERAL
    * @model name="ShortStrokes"
    * @generated
    * @ordered
    */
   public static final int SHORT_STROKES = 1;

   /**
    * The '<em><b>Long Strokes</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Long Strokes</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #LONG_STROKES_LITERAL
    * @model name="LongStrokes"
    * @generated
    * @ordered
    */
   public static final int LONG_STROKES = 2;

   /**
    * An array of all the '<em><b>Link Line Style</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final LinkLineStyle[] VALUES_ARRAY =
      new LinkLineStyle[]
      {
         UNKNOWN_LITERAL,
         NORMAL_LITERAL,
         SHORT_STROKES_LITERAL,
         LONG_STROKES_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Link Line Style</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<LinkLineStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Link Line Style</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkLineStyle get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkLineStyle result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Line Style</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkLineStyle getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkLineStyle result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Line Style</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkLineStyle get(int value)
   {
      switch (value)
      {
         case UNKNOWN: return UNKNOWN_LITERAL;
         case NORMAL: return NORMAL_LITERAL;
         case SHORT_STROKES: return SHORT_STROKES_LITERAL;
         case LONG_STROKES: return LONG_STROKES_LITERAL;
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
   private LinkLineStyle(int value, String name, String literal)
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
