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
 * A representation of the literals of the enumeration '<em><b>Feature Style Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getFeatureStyleType()
 * @model extendedMetaData="name='type'"
 * @generated
 */
public enum FeatureStyleType implements Enumerator
{
   /**
    * The '<em><b>Text</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #TEXT_VALUE
    * @generated
    * @ordered
    */
   TEXT(0, "text", "text"), //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Selection</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SELECTION_VALUE
    * @generated
    * @ordered
    */
   SELECTION(1, "selection", "selection"); //$NON-NLS-1$ //$NON-NLS-2$
   /**
    * The '<em><b>Text</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Text</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #TEXT
    * @model name="text"
    * @generated
    * @ordered
    */
   public static final int TEXT_VALUE = 0;

   /**
    * The '<em><b>Selection</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Selection</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SELECTION
    * @model name="selection"
    * @generated
    * @ordered
    */
   public static final int SELECTION_VALUE = 1;

   /**
    * An array of all the '<em><b>Feature Style Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private static final FeatureStyleType[] VALUES_ARRAY =
		new FeatureStyleType[]
      {
         TEXT,
         SELECTION,
      };

   /**
    * A public read-only list of all the '<em><b>Feature Style Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final List<FeatureStyleType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Feature Style Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static FeatureStyleType get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         FeatureStyleType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Feature Style Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static FeatureStyleType getByName(String name) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         FeatureStyleType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Feature Style Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static FeatureStyleType get(int value) {
      switch (value)
      {
         case TEXT_VALUE: return TEXT;
         case SELECTION_VALUE: return SELECTION;
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
	private FeatureStyleType(int value, String name, String literal) {
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
