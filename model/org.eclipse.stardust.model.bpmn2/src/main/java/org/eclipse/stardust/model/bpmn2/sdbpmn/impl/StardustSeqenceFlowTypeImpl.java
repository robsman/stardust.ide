/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Seqence Flow Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl#isForkOnTraversal <em>Fork On Traversal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustSeqenceFlowTypeImpl extends EObjectImpl implements StardustSeqenceFlowType {
	/**
	 * The default value of the '{@link #isForkOnTraversal() <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForkOnTraversal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FORK_ON_TRAVERSAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForkOnTraversal() <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForkOnTraversal()
	 * @generated
	 * @ordered
	 */
	protected boolean forkOnTraversal = FORK_ON_TRAVERSAL_EDEFAULT;

	/**
	 * This is true if the Fork On Traversal attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean forkOnTraversalESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StardustSeqenceFlowTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_SEQENCE_FLOW_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForkOnTraversal() {
		return forkOnTraversal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForkOnTraversal(boolean newForkOnTraversal) {
		boolean oldForkOnTraversal = forkOnTraversal;
		forkOnTraversal = newForkOnTraversal;
		boolean oldForkOnTraversalESet = forkOnTraversalESet;
		forkOnTraversalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL, oldForkOnTraversal, forkOnTraversal, !oldForkOnTraversalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetForkOnTraversal() {
		boolean oldForkOnTraversal = forkOnTraversal;
		boolean oldForkOnTraversalESet = forkOnTraversalESet;
		forkOnTraversal = FORK_ON_TRAVERSAL_EDEFAULT;
		forkOnTraversalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL, oldForkOnTraversal, FORK_ON_TRAVERSAL_EDEFAULT, oldForkOnTraversalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetForkOnTraversal() {
		return forkOnTraversalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL:
				return isForkOnTraversal();
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
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL:
				setForkOnTraversal((Boolean)newValue);
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
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL:
				unsetForkOnTraversal();
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
			case SdbpmnPackage.STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL:
				return isSetForkOnTraversal();
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
		result.append(" (forkOnTraversal: ");
		if (forkOnTraversalESet) result.append(forkOnTraversal); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //StardustSeqenceFlowTypeImpl
