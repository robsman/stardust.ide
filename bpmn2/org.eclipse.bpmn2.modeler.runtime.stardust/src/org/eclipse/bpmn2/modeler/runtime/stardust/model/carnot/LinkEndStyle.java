/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Link End Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkEndStyle()
 * @model extendedMetaData="name='linkEndStyle_._type'"
 * @generated
 */
public enum LinkEndStyle implements Enumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(-1, "Unknown", "Unknown"),

	/**
	 * The '<em><b>No Arrow</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_ARROW_VALUE
	 * @generated
	 * @ordered
	 */
	NO_ARROW(0, "NoArrow", "NoArrow"),

	/**
	 * The '<em><b>Open Triangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPEN_TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	OPEN_TRIANGLE(1, "OpenTriangle", "OpenTriangle"),

	/**
	 * The '<em><b>Empty Triangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMPTY_TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	EMPTY_TRIANGLE(2, "EmptyTriangle", "EmptyTriangle"),

	/**
	 * The '<em><b>Filled Triangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILLED_TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	FILLED_TRIANGLE(3, "FilledTriangle", "FilledTriangle"),

	/**
	 * The '<em><b>Empty Rhombus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMPTY_RHOMBUS_VALUE
	 * @generated
	 * @ordered
	 */
	EMPTY_RHOMBUS(4, "EmptyRhombus", "EmptyRhombus"),

	/**
	 * The '<em><b>Filled Rhombus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILLED_RHOMBUS_VALUE
	 * @generated
	 * @ordered
	 */
	FILLED_RHOMBUS(5, "FilledRhombus", "FilledRhombus");

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = -1;

	/**
	 * The '<em><b>No Arrow</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No Arrow</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_ARROW
	 * @model name="NoArrow"
	 * @generated
	 * @ordered
	 */
	public static final int NO_ARROW_VALUE = 0;

	/**
	 * The '<em><b>Open Triangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Open Triangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPEN_TRIANGLE
	 * @model name="OpenTriangle"
	 * @generated
	 * @ordered
	 */
	public static final int OPEN_TRIANGLE_VALUE = 1;

	/**
	 * The '<em><b>Empty Triangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Empty Triangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EMPTY_TRIANGLE
	 * @model name="EmptyTriangle"
	 * @generated
	 * @ordered
	 */
	public static final int EMPTY_TRIANGLE_VALUE = 2;

	/**
	 * The '<em><b>Filled Triangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Filled Triangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILLED_TRIANGLE
	 * @model name="FilledTriangle"
	 * @generated
	 * @ordered
	 */
	public static final int FILLED_TRIANGLE_VALUE = 3;

	/**
	 * The '<em><b>Empty Rhombus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Empty Rhombus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EMPTY_RHOMBUS
	 * @model name="EmptyRhombus"
	 * @generated
	 * @ordered
	 */
	public static final int EMPTY_RHOMBUS_VALUE = 4;

	/**
	 * The '<em><b>Filled Rhombus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Filled Rhombus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILLED_RHOMBUS
	 * @model name="FilledRhombus"
	 * @generated
	 * @ordered
	 */
	public static final int FILLED_RHOMBUS_VALUE = 5;

	/**
	 * An array of all the '<em><b>Link End Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LinkEndStyle[] VALUES_ARRAY =
		new LinkEndStyle[] {
			UNKNOWN,
			NO_ARROW,
			OPEN_TRIANGLE,
			EMPTY_TRIANGLE,
			FILLED_TRIANGLE,
			EMPTY_RHOMBUS,
			FILLED_RHOMBUS,
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
	public static LinkEndStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkEndStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
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
	public static LinkEndStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkEndStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
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
	public static LinkEndStyle get(int value) {
		switch (value) {
			case UNKNOWN_VALUE: return UNKNOWN;
			case NO_ARROW_VALUE: return NO_ARROW;
			case OPEN_TRIANGLE_VALUE: return OPEN_TRIANGLE;
			case EMPTY_TRIANGLE_VALUE: return EMPTY_TRIANGLE;
			case FILLED_TRIANGLE_VALUE: return FILLED_TRIANGLE;
			case EMPTY_RHOMBUS_VALUE: return EMPTY_RHOMBUS;
			case FILLED_RHOMBUS_VALUE: return FILLED_RHOMBUS;
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
	private LinkEndStyle(int value, String name, String literal) {
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
	
} //LinkEndStyle
