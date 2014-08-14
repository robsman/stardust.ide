/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Performer Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerSymbolTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerSymbolTypeImpl#getTriggeredEvents <em>Triggered Events</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerSymbolTypeImpl#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalPerformerSymbolTypeImpl extends IModelElementNodeSymbolImpl implements ConditionalPerformerSymbolType {
	/**
	 * The cached value of the '{@link #getPerformedActivities() <em>Performed Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<PerformsConnectionType> performedActivities;

	/**
	 * The cached value of the '{@link #getTriggeredEvents() <em>Triggered Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggeredEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<TriggersConnectionType> triggeredEvents;

	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected ConditionalPerformerType participant;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalPerformerSymbolTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getConditionalPerformerSymbolType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PerformsConnectionType> getPerformedActivities() {
		if (performedActivities == null) {
			performedActivities = new EObjectWithInverseEList<PerformsConnectionType>(PerformsConnectionType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES, CarnotPackage.PERFORMS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
		}
		return performedActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TriggersConnectionType> getTriggeredEvents() {
		if (triggeredEvents == null) {
			triggeredEvents = new EObjectWithInverseEList<TriggersConnectionType>(TriggersConnectionType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS, CarnotPackage.TRIGGERS_CONNECTION_TYPE__PARTICIPANT_SYMBOL);
		}
		return triggeredEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalPerformerType getParticipant() {
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipant(ConditionalPerformerType newParticipant, NotificationChain msgs) {
		ConditionalPerformerType oldParticipant = participant;
		participant = newParticipant;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT, oldParticipant, newParticipant);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipant(ConditionalPerformerType newParticipant) {
		if (newParticipant != participant) {
			NotificationChain msgs = null;
			if (participant != null)
				msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS, ConditionalPerformerType.class, msgs);
			if (newParticipant != null)
				msgs = ((InternalEObject)newParticipant).eInverseAdd(this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS, ConditionalPerformerType.class, msgs);
			msgs = basicSetParticipant(newParticipant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT, newParticipant, newParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggeredEvents()).basicAdd(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				if (participant != null)
					msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS, ConditionalPerformerType.class, msgs);
				return basicSetParticipant((ConditionalPerformerType)otherEnd, msgs);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return ((InternalEList<?>)getTriggeredEvents()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				return basicSetParticipant(null, msgs);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return getPerformedActivities();
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return getTriggeredEvents();
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				return getParticipant();
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
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				getPerformedActivities().addAll((Collection<? extends PerformsConnectionType>)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				getTriggeredEvents().clear();
				getTriggeredEvents().addAll((Collection<? extends TriggersConnectionType>)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				setParticipant((ConditionalPerformerType)newValue);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				getTriggeredEvents().clear();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				setParticipant((ConditionalPerformerType)null);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES:
				return performedActivities != null && !performedActivities.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS:
				return triggeredEvents != null && !triggeredEvents.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT:
				return participant != null;
		}
		return super.eIsSet(featureID);
	}

} //ConditionalPerformerSymbolTypeImpl
