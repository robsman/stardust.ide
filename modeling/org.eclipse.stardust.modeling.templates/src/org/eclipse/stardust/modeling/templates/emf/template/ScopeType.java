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
package org.eclipse.stardust.modeling.templates.emf.template;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Scope Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getScopeType()
 * @model extendedMetaData="name='type'"
 * @generated
 */
public enum ScopeType implements Enumerator
{
   /**
    * The '<em><b>Model</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #MODEL_VALUE
    * @generated
    * @ordered
    */
   MODEL(0, "model", "model"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Process</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #PROCESS_VALUE
    * @generated
    * @ordered
    */
   PROCESS(1, "process", "process"); //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Model</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Model</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #MODEL
    * @model name="model"
    * @generated
    * @ordered
    */
   public static final int MODEL_VALUE = 0;

   /**
    * The '<em><b>Process</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Process</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #PROCESS
    * @model name="process"
    * @generated
    * @ordered
    */
   public static final int PROCESS_VALUE = 1;

   /**
    * An array of all the '<em><b>Scope Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private static final ScopeType[] VALUES_ARRAY =
		new ScopeType[]
      {
         MODEL,
         PROCESS,
      };

   /**
    * A public read-only list of all the '<em><b>Scope Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final List<ScopeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Scope Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static ScopeType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ScopeType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Scope Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static ScopeType getByName(String name) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         ScopeType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Scope Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static ScopeType get(int value) {
      switch (value)
      {
         case MODEL_VALUE: return MODEL;
         case PROCESS_VALUE: return PROCESS;
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
	private ScopeType(int value, String name, String literal) {
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
