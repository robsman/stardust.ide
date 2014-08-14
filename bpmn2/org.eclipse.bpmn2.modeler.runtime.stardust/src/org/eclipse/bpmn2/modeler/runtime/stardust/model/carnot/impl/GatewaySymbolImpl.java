/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gateway Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GatewaySymbolImpl#getFlowKind <em>Flow Kind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GatewaySymbolImpl#getActivitySymbol <em>Activity Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GatewaySymbolImpl extends IFlowObjectSymbolImpl implements GatewaySymbol {
	/**
	 * The default value of the '{@link #getFlowKind() <em>Flow Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowKind()
	 * @generated
	 * @ordered
	 */
	protected static final FlowControlType FLOW_KIND_EDEFAULT = FlowControlType.NONE;

	/**
	 * The cached value of the '{@link #getFlowKind() <em>Flow Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowKind()
	 * @generated
	 * @ordered
	 */
	protected FlowControlType flowKind = FLOW_KIND_EDEFAULT;

	/**
	 * This is true if the Flow Kind attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean flowKindESet;

	/**
	 * The cached value of the '{@link #getActivitySymbol() <em>Activity Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivitySymbol()
	 * @generated
	 * @ordered
	 */
	protected ActivitySymbolType activitySymbol;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GatewaySymbolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getGatewaySymbol();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowControlType getFlowKind() {
		return flowKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlowKind(FlowControlType newFlowKind) {
		FlowControlType oldFlowKind = flowKind;
		flowKind = newFlowKind == null ? FLOW_KIND_EDEFAULT : newFlowKind;
		boolean oldFlowKindESet = flowKindESet;
		flowKindESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND, oldFlowKind, flowKind, !oldFlowKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFlowKind() {
		FlowControlType oldFlowKind = flowKind;
		boolean oldFlowKindESet = flowKindESet;
		flowKind = FLOW_KIND_EDEFAULT;
		flowKindESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND, oldFlowKind, FLOW_KIND_EDEFAULT, oldFlowKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFlowKind() {
		return flowKindESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySymbolType getActivitySymbol() {
		return activitySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivitySymbol(ActivitySymbolType newActivitySymbol, NotificationChain msgs) {
		ActivitySymbolType oldActivitySymbol = activitySymbol;
		activitySymbol = newActivitySymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL, oldActivitySymbol, newActivitySymbol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivitySymbol(ActivitySymbolType newActivitySymbol) {
		if (newActivitySymbol != activitySymbol) {
			NotificationChain msgs = null;
			if (activitySymbol != null)
				msgs = ((InternalEObject)activitySymbol).eInverseRemove(this, CarnotPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
			if (newActivitySymbol != null)
				msgs = ((InternalEObject)newActivitySymbol).eInverseAdd(this, CarnotPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
			msgs = basicSetActivitySymbol(newActivitySymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL, newActivitySymbol, newActivitySymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				if (activitySymbol != null)
					msgs = ((InternalEObject)activitySymbol).eInverseRemove(this, CarnotPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
				return basicSetActivitySymbol((ActivitySymbolType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				return basicSetActivitySymbol(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND:
				return getFlowKind();
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				return getActivitySymbol();
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
			case CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND:
				setFlowKind((FlowControlType)newValue);
				return;
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				setActivitySymbol((ActivitySymbolType)newValue);
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
			case CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND:
				unsetFlowKind();
				return;
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				setActivitySymbol((ActivitySymbolType)null);
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
			case CarnotPackage.GATEWAY_SYMBOL__FLOW_KIND:
				return isSetFlowKind();
			case CarnotPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
				return activitySymbol != null;
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
		result.append(" (flowKind: ");
		if (flowKindESet) result.append(flowKind); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //GatewaySymbolImpl
