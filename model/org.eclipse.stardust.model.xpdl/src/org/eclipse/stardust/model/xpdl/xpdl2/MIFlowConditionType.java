/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>MI Flow Condition Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getMIFlowConditionType()
 * @model extendedMetaData="name='MI_FlowCondition_._type'"
 * @generated
 */
public enum MIFlowConditionType implements Enumerator
{
   /**
    * The '<em><b>None</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #NONE_VALUE
    * @generated
    * @ordered
    */
   NONE(0, "None", "None"),

   /**
    * The '<em><b>One</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ONE_VALUE
    * @generated
    * @ordered
    */
   ONE(1, "One", "One"),

   /**
    * The '<em><b>All</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #ALL_VALUE
    * @generated
    * @ordered
    */
   ALL(2, "All", "All"),

   /**
    * The '<em><b>Complex</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #COMPLEX_VALUE
    * @generated
    * @ordered
    */
   COMPLEX(3, "Complex", "Complex");

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The '<em><b>None</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #NONE
    * @model name="None"
    * @generated
    * @ordered
    */
   public static final int NONE_VALUE = 0;

   /**
    * The '<em><b>One</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ONE
    * @model name="One"
    * @generated
    * @ordered
    */
   public static final int ONE_VALUE = 1;

   /**
    * The '<em><b>All</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>All</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #ALL
    * @model name="All"
    * @generated
    * @ordered
    */
   public static final int ALL_VALUE = 2;

   /**
    * The '<em><b>Complex</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Complex</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #COMPLEX
    * @model name="Complex"
    * @generated
    * @ordered
    */
   public static final int COMPLEX_VALUE = 3;

   /**
    * An array of all the '<em><b>MI Flow Condition Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final MIFlowConditionType[] VALUES_ARRAY =
      new MIFlowConditionType[]
      {
         NONE,
         ONE,
         ALL,
         COMPLEX,
      };

   /**
    * A public read-only list of all the '<em><b>MI Flow Condition Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<MIFlowConditionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>MI Flow Condition Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static MIFlowConditionType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         MIFlowConditionType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>MI Flow Condition Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static MIFlowConditionType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         MIFlowConditionType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>MI Flow Condition Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static MIFlowConditionType get(int value)
   {
      switch (value)
      {
         case NONE_VALUE: return NONE;
         case ONE_VALUE: return ONE;
         case ALL_VALUE: return ALL;
         case COMPLEX_VALUE: return COMPLEX;
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
   private MIFlowConditionType(int value, String name, String literal)
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

} //MIFlowConditionType