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
 * A representation of the literals of the enumeration '<em><b>Activity Implementation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivityImplementationType()
 * @model extendedMetaData="name='activityImplementation_._type'"
 * @generated
 */
public enum ActivityImplementationType implements Enumerator
{
   /**
    * The '<em><b>Route</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ROUTE
    * @generated
    * @ordered
    */
   ROUTE_LITERAL(0, "Route", "Route"),
   /**
    * The '<em><b>Manual</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #MANUAL
    * @generated
    * @ordered
    */
   MANUAL_LITERAL(1, "Manual", "Manual"),
   /**
    * The '<em><b>Application</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #APPLICATION
    * @generated
    * @ordered
    */
   APPLICATION_LITERAL(2, "Application", "Application"),
   /**
    * The '<em><b>Subprocess</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SUBPROCESS
    * @generated
    * @ordered
    */
   SUBPROCESS_LITERAL(3, "Subprocess", "Subprocess");
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The '<em><b>Route</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Route</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ROUTE_LITERAL
    * @model name="Route"
    * @generated
    * @ordered
    */
   public static final int ROUTE = 0;

   /**
    * The '<em><b>Manual</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Manual</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #MANUAL_LITERAL
    * @model name="Manual"
    * @generated
    * @ordered
    */
   public static final int MANUAL = 1;

   /**
    * The '<em><b>Application</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Application</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #APPLICATION_LITERAL
    * @model name="Application"
    * @generated
    * @ordered
    */
   public static final int APPLICATION = 2;

   /**
    * The '<em><b>Subprocess</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Subprocess</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SUBPROCESS_LITERAL
    * @model name="Subprocess"
    * @generated
    * @ordered
    */
   public static final int SUBPROCESS = 3;

   /**
    * An array of all the '<em><b>Activity Implementation Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final ActivityImplementationType[] VALUES_ARRAY =
      new ActivityImplementationType[]
      {
         ROUTE_LITERAL,
         MANUAL_LITERAL,
         APPLICATION_LITERAL,
         SUBPROCESS_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Activity Implementation Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<ActivityImplementationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Activity Implementation Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ActivityImplementationType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ActivityImplementationType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Activity Implementation Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ActivityImplementationType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ActivityImplementationType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Activity Implementation Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ActivityImplementationType get(int value)
   {
      switch (value)
      {
         case ROUTE: return ROUTE_LITERAL;
         case MANUAL: return MANUAL_LITERAL;
         case APPLICATION: return APPLICATION_LITERAL;
         case SUBPROCESS: return SUBPROCESS_LITERAL;
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
   private ActivityImplementationType(int value, String name, String literal)
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
