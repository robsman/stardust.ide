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
 * A representation of the literals of the enumeration '<em><b>Sub Process Mode Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getSubProcessModeType()
 * @model extendedMetaData="name='subProcessMode_._type'"
 * @generated
 */
public enum SubProcessModeType implements Enumerator
{
   /**
    * The '<em><b>Sync shared</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SYNC_SHARED
    * @generated
    * @ordered
    */
   SYNC_SHARED_LITERAL(0, "sync_shared", "Synchronous / Shared Data"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Sync separate</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SYNC_SEPARATE
    * @generated
    * @ordered
    */
   SYNC_SEPARATE_LITERAL(1, "sync_separate", "Synchronous / Separate Data"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Async separate</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ASYNC_SEPARATE
    * @generated
    * @ordered
    */
   ASYNC_SEPARATE_LITERAL(2, "async_separate", "Asynchronous / Separate Data"); //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The '<em><b>Sync shared</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Sync shared</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SYNC_SHARED_LITERAL
    * @model name="sync_shared" literal="Synchronous / Shared Data"
    * @generated
    * @ordered
    */
   public static final int SYNC_SHARED = 0;

   /**
    * The '<em><b>Sync separate</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Sync separate</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SYNC_SEPARATE_LITERAL
    * @model name="sync_separate" literal="Synchronous / Separate Data"
    * @generated
    * @ordered
    */
   public static final int SYNC_SEPARATE = 1;

   /**
    * The '<em><b>Async separate</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Async separate</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ASYNC_SEPARATE_LITERAL
    * @model name="async_separate" literal="Asynchronous / Separate Data"
    * @generated
    * @ordered
    */
   public static final int ASYNC_SEPARATE = 2;

   /**
    * An array of all the '<em><b>Sub Process Mode Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final SubProcessModeType[] VALUES_ARRAY =
      new SubProcessModeType[]
      {
         SYNC_SHARED_LITERAL,
         SYNC_SEPARATE_LITERAL,
         ASYNC_SEPARATE_LITERAL,
      };

   /**
    * A public read-only list of all the '<em><b>Sub Process Mode Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<SubProcessModeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Sub Process Mode Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public static SubProcessModeType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         SubProcessModeType result = VALUES_ARRAY[i];
         if (result.name().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Sub Process Mode Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static SubProcessModeType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         SubProcessModeType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Sub Process Mode Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static SubProcessModeType get(int value)
   {
      switch (value)
      {
         case SYNC_SHARED: return SYNC_SHARED_LITERAL;
         case SYNC_SEPARATE: return SYNC_SEPARATE_LITERAL;
         case ASYNC_SEPARATE: return ASYNC_SEPARATE_LITERAL;
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
   private SubProcessModeType(int value, String name, String literal)
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
