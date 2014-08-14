/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Coordinates</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CoordinatesImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CoordinatesImpl#getYPos <em>YPos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CoordinatesImpl extends MinimalEObjectImpl.Container implements Coordinates {
	/**
	 * The default value of the '{@link #getXPos() <em>XPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPos()
	 * @generated
	 * @ordered
	 */
	protected static final double XPOS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXPos() <em>XPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPos()
	 * @generated
	 * @ordered
	 */
	protected double xPos = XPOS_EDEFAULT;

	/**
	 * This is true if the XPos attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xPosESet;

	/**
	 * The default value of the '{@link #getYPos() <em>YPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPos()
	 * @generated
	 * @ordered
	 */
	protected static final double YPOS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYPos() <em>YPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPos()
	 * @generated
	 * @ordered
	 */
	protected double yPos = YPOS_EDEFAULT;

	/**
	 * This is true if the YPos attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean yPosESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinatesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getCoordinates();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXPos() {
		return xPos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXPos(double newXPos) {
		double oldXPos = xPos;
		xPos = newXPos;
		boolean oldXPosESet = xPosESet;
		xPosESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.COORDINATES__XPOS, oldXPos, xPos, !oldXPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXPos() {
		double oldXPos = xPos;
		boolean oldXPosESet = xPosESet;
		xPos = XPOS_EDEFAULT;
		xPosESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.COORDINATES__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXPos() {
		return xPosESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYPos() {
		return yPos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYPos(double newYPos) {
		double oldYPos = yPos;
		yPos = newYPos;
		boolean oldYPosESet = yPosESet;
		yPosESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.COORDINATES__YPOS, oldYPos, yPos, !oldYPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYPos() {
		double oldYPos = yPos;
		boolean oldYPosESet = yPosESet;
		yPos = YPOS_EDEFAULT;
		yPosESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.COORDINATES__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYPos() {
		return yPosESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CarnotPackage.COORDINATES__XPOS:
				return getXPos();
			case CarnotPackage.COORDINATES__YPOS:
				return getYPos();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CarnotPackage.COORDINATES__XPOS:
				setXPos((Double)newValue);
				return;
			case CarnotPackage.COORDINATES__YPOS:
				setYPos((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CarnotPackage.COORDINATES__XPOS:
				unsetXPos();
				return;
			case CarnotPackage.COORDINATES__YPOS:
				unsetYPos();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CarnotPackage.COORDINATES__XPOS:
				return isSetXPos();
			case CarnotPackage.COORDINATES__YPOS:
				return isSetYPos();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (xPos: ");
		if (xPosESet) result.append(xPos); else result.append("<unset>");
		result.append(", yPos: ");
		if (yPosESet) result.append(yPos); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CoordinatesImpl
