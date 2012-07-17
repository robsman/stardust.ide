/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TStardust Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl#getEventHandler <em>Event Handler</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl#getDataMapping <em>Data Mapping</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl#isHibernateOnCreation <em>Hibernate On Creation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TStardustActivityImpl extends TStardustCommonImpl implements TStardustActivity {
	/**
	 * The cached value of the '{@link #getEventHandler() <em>Event Handler</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventHandler()
	 * @generated
	 * @ordered
	 */
	protected EList<EventHandlerType> eventHandler;

	/**
	 * The cached value of the '{@link #getDataMapping() <em>Data Mapping</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataMapping()
	 * @generated
	 * @ordered
	 */
	protected EList<DataMappingType> dataMapping;

	/**
	 * The default value of the '{@link #isHibernateOnCreation() <em>Hibernate On Creation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHibernateOnCreation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIBERNATE_ON_CREATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHibernateOnCreation() <em>Hibernate On Creation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHibernateOnCreation()
	 * @generated
	 * @ordered
	 */
	protected boolean hibernateOnCreation = HIBERNATE_ON_CREATION_EDEFAULT;

	/**
	 * This is true if the Hibernate On Creation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hibernateOnCreationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TStardustActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.TSTARDUST_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventHandlerType> getEventHandler() {
		if (eventHandler == null) {
			eventHandler = new EObjectContainmentEList<EventHandlerType>(EventHandlerType.class, this, SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER);
		}
		return eventHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataMappingType> getDataMapping() {
		if (dataMapping == null) {
			dataMapping = new EObjectContainmentEList<DataMappingType>(DataMappingType.class, this, SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING);
		}
		return dataMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHibernateOnCreation() {
		return hibernateOnCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHibernateOnCreation(boolean newHibernateOnCreation) {
		boolean oldHibernateOnCreation = hibernateOnCreation;
		hibernateOnCreation = newHibernateOnCreation;
		boolean oldHibernateOnCreationESet = hibernateOnCreationESet;
		hibernateOnCreationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION, oldHibernateOnCreation, hibernateOnCreation, !oldHibernateOnCreationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHibernateOnCreation() {
		boolean oldHibernateOnCreation = hibernateOnCreation;
		boolean oldHibernateOnCreationESet = hibernateOnCreationESet;
		hibernateOnCreation = HIBERNATE_ON_CREATION_EDEFAULT;
		hibernateOnCreationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION, oldHibernateOnCreation, HIBERNATE_ON_CREATION_EDEFAULT, oldHibernateOnCreationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHibernateOnCreation() {
		return hibernateOnCreationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER:
				return ((InternalEList<?>)getEventHandler()).basicRemove(otherEnd, msgs);
			case SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING:
				return ((InternalEList<?>)getDataMapping()).basicRemove(otherEnd, msgs);
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
			case SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER:
				return getEventHandler();
			case SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING:
				return getDataMapping();
			case SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION:
				return isHibernateOnCreation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER:
				getEventHandler().clear();
				getEventHandler().addAll((Collection<? extends EventHandlerType>)newValue);
				return;
			case SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING:
				getDataMapping().clear();
				getDataMapping().addAll((Collection<? extends DataMappingType>)newValue);
				return;
			case SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION:
				setHibernateOnCreation((Boolean)newValue);
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
			case SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER:
				getEventHandler().clear();
				return;
			case SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING:
				getDataMapping().clear();
				return;
			case SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION:
				unsetHibernateOnCreation();
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
			case SdbpmnPackage.TSTARDUST_ACTIVITY__EVENT_HANDLER:
				return eventHandler != null && !eventHandler.isEmpty();
			case SdbpmnPackage.TSTARDUST_ACTIVITY__DATA_MAPPING:
				return dataMapping != null && !dataMapping.isEmpty();
			case SdbpmnPackage.TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION:
				return isSetHibernateOnCreation();
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
		result.append(" (hibernateOnCreation: ");
		if (hibernateOnCreationESet) result.append(hibernateOnCreation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TStardustActivityImpl
