/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sub Process Mode Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getSubProcessModeType()
 * @model extendedMetaData="name='subProcessMode_._type'"
 * @generated
 */
public enum SubProcessModeType implements Enumerator {
	/**
	 * The '<em><b>Sync shared</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYNC_SHARED_VALUE
	 * @generated
	 * @ordered
	 */
	SYNC_SHARED(0, "sync_shared", "Synchronous / Shared Data"),

	/**
	 * The '<em><b>Sync separate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYNC_SEPARATE_VALUE
	 * @generated
	 * @ordered
	 */
	SYNC_SEPARATE(1, "sync_separate", "Synchronous / Separate Data"),

	/**
	 * The '<em><b>Async separate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASYNC_SEPARATE_VALUE
	 * @generated
	 * @ordered
	 */
	ASYNC_SEPARATE(2, "async_separate", "Asynchronous / Separate Data");

	/**
	 * The '<em><b>Sync shared</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Sync shared</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYNC_SHARED
	 * @model name="sync_shared" literal="Synchronous / Shared Data"
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SHARED_VALUE = 0;

	/**
	 * The '<em><b>Sync separate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Sync separate</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYNC_SEPARATE
	 * @model name="sync_separate" literal="Synchronous / Separate Data"
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SEPARATE_VALUE = 1;

	/**
	 * The '<em><b>Async separate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Async separate</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASYNC_SEPARATE
	 * @model name="async_separate" literal="Asynchronous / Separate Data"
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SEPARATE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Sub Process Mode Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SubProcessModeType[] VALUES_ARRAY =
		new SubProcessModeType[] {
			SYNC_SHARED,
			SYNC_SEPARATE,
			ASYNC_SEPARATE,
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
	 * @generated
	 */
	public static SubProcessModeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SubProcessModeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
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
	public static SubProcessModeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SubProcessModeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
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
	public static SubProcessModeType get(int value) {
		switch (value) {
			case SYNC_SHARED_VALUE: return SYNC_SHARED;
			case SYNC_SEPARATE_VALUE: return SYNC_SEPARATE;
			case ASYNC_SEPARATE_VALUE: return ASYNC_SEPARATE;
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
	private SubProcessModeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //SubProcessModeType
