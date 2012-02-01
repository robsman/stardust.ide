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
 * A representation of the literals of the enumeration '<em><b>Link Color</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLinkColor()
 * @model extendedMetaData="name='linkColor_._type'"
 * @generated
 */
public enum LinkColor implements Enumerator
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
    * The '<em><b>Black</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #BLACK
    * @generated NOT
    * @ordered
    */
   BLACK_LITERAL(0, "black", "black"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Dark Blue</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #DARK_BLUE
    * @generated NOT
    * @ordered
    */
   DARK_BLUE_LITERAL(1, "dark blue", "dark blue"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Dark Gray</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #DARK_GRAY
    * @generated NOT
    * @ordered
    */
   DARK_GRAY_LITERAL(2, "dark gray", "dark gray"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Blue</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #BLUE
    * @generated NOT
    * @ordered
    */
   BLUE_LITERAL(3, "blue", "blue"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Light Gray</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #LIGHT_GRAY
    * @generated NOT
    * @ordered
    */
   LIGHT_GRAY_LITERAL(4, "light gray", "light gray"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Red</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #RED
    * @generated NOT
    * @ordered
    */
   RED_LITERAL(5, "red", "red"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Yellow</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #YELLOW
    * @generated NOT
    * @ordered
    */
   YELLOW_LITERAL(6, "yellow", "yellow"); //$NON-NLS-1$ //$NON-NLS-2$
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
    * The '<em><b>Black</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Black</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #BLACK_LITERAL
    * @model name="Black"
    * @generated
    * @ordered
    */
   public static final int BLACK = 0;

   /**
    * The '<em><b>Dark Blue</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Dark Blue</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #DARK_BLUE_LITERAL
    * @model name="DarkBlue"
    * @generated
    * @ordered
    */
   public static final int DARK_BLUE = 1;

   /**
    * The '<em><b>Dark Gray</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Dark Gray</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #DARK_GRAY_LITERAL
    * @model name="DarkGray"
    * @generated
    * @ordered
    */
   public static final int DARK_GRAY = 2;

   /**
    * The '<em><b>Blue</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Blue</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #BLUE_LITERAL
    * @model name="Blue"
    * @generated
    * @ordered
    */
   public static final int BLUE = 3;

   /**
    * The '<em><b>Light Gray</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Light Gray</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #LIGHT_GRAY_LITERAL
    * @model name="LightGray"
    * @generated
    * @ordered
    */
   public static final int LIGHT_GRAY = 4;

   /**
    * The '<em><b>Red</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Red</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #RED_LITERAL
    * @model name="Red"
    * @generated
    * @ordered
    */
   public static final int RED = 5;

   /**
    * The '<em><b>Yellow</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Yellow</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #YELLOW_LITERAL
    * @model name="Yellow"
    * @generated
    * @ordered
    */
   public static final int YELLOW = 6;

   /**
    * An array of all the '<em><b>Link Color</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final LinkColor[] VALUES_ARRAY =
      new LinkColor[]
      {
         UNKNOWN_LITERAL,
         BLACK_LITERAL,
         DARK_BLUE_LITERAL,
         DARK_GRAY_LITERAL,
         BLUE_LITERAL,
         LIGHT_GRAY_LITERAL,
         RED_LITERAL,
         YELLOW_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Link Color</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<LinkColor> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Link Color</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public static LinkColor get(String literal)
   {
      if ("ligth gray".equals(literal)) //$NON-NLS-1$
      {
         return LIGHT_GRAY_LITERAL;
      }
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkColor result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Color</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public static LinkColor getByName(String name)
   {
      if ("ligth gray".equals(name)) //$NON-NLS-1$
      {
         return LIGHT_GRAY_LITERAL;
      }
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         LinkColor result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Link Color</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static LinkColor get(int value)
   {
      switch (value)
      {
         case UNKNOWN: return UNKNOWN_LITERAL;
         case BLACK: return BLACK_LITERAL;
         case DARK_BLUE: return DARK_BLUE_LITERAL;
         case DARK_GRAY: return DARK_GRAY_LITERAL;
         case BLUE: return BLUE_LITERAL;
         case LIGHT_GRAY: return LIGHT_GRAY_LITERAL;
         case RED: return RED_LITERAL;
         case YELLOW: return YELLOW_LITERAL;
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
   private LinkColor(int value, String name, String literal)
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
