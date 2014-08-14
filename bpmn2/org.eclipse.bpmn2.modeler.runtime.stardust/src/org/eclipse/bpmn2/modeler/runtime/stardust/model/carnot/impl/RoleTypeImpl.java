/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getParticipantAssociations <em>Participant Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getTeams <em>Teams</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl#getRoleSymbols <em>Role Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTypeImpl extends MinimalEObjectImpl.Container implements RoleType {
	/**
	 * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected static final long ELEMENT_OID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected long elementOid = ELEMENT_OID_EDEFAULT;

	/**
	 * This is true if the Element Oid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elementOidESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * This is true if the Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean idESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * This is true if the Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean nameESet;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeType> attribute;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected DescriptionType description;

	/**
	 * The cached value of the '{@link #getPerformedActivities() <em>Performed Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityType> performedActivities;

	/**
	 * The cached value of the '{@link #getPerformedSwimlanes() <em>Performed Swimlanes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedSwimlanes()
	 * @generated
	 * @ordered
	 */
	protected EList<ISwimlaneSymbol> performedSwimlanes;

	/**
	 * The cached value of the '{@link #getParticipantAssociations() <em>Participant Associations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<ParticipantType> participantAssociations;

	/**
	 * The default value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final int CARDINALITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected int cardinality = CARDINALITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTeams() <em>Teams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeams()
	 * @generated
	 * @ordered
	 */
	protected EList<OrganizationType> teams;

	/**
	 * The cached value of the '{@link #getRoleSymbols() <em>Role Symbols</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleSymbols()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleSymbolType> roleSymbols;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getRoleType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getElementOid() {
		return elementOid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementOid(long newElementOid) {
		long oldElementOid = elementOid;
		elementOid = newElementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetElementOid() {
		long oldElementOid = elementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOid = ELEMENT_OID_EDEFAULT;
		elementOidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ROLE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetElementOid() {
		return elementOidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		boolean oldIdESet = idESet;
		idESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__ID, oldId, id, !oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetId() {
		String oldId = id;
		boolean oldIdESet = idESet;
		id = ID_EDEFAULT;
		idESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ROLE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetId() {
		return idESet;
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		boolean oldNameESet = nameESet;
		nameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__NAME, oldName, name, !oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetName() {
		String oldName = name;
		boolean oldNameESet = nameESet;
		name = NAME_EDEFAULT;
		nameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ROLE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetName() {
		return nameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeType> getAttribute() {
		if (attribute == null) {
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.ROLE_TYPE__ATTRIBUTE);
		}
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptionType getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(DescriptionType newDescription, NotificationChain msgs) {
		DescriptionType oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(DescriptionType newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.ROLE_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.ROLE_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityType> getPerformedActivities() {
		if (performedActivities == null) {
			performedActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES, CarnotPackage.ACTIVITY_TYPE__PERFORMER);
		}
		return performedActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ISwimlaneSymbol> getPerformedSwimlanes() {
		if (performedSwimlanes == null) {
			performedSwimlanes = new EObjectWithInverseResolvingEList<ISwimlaneSymbol>(ISwimlaneSymbol.class, this, CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES, CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT);
		}
		return performedSwimlanes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParticipantType> getParticipantAssociations() {
		if (participantAssociations == null) {
			participantAssociations = new EObjectWithInverseResolvingEList<ParticipantType>(ParticipantType.class, this, CarnotPackage.ROLE_TYPE__PARTICIPANT_ASSOCIATIONS, CarnotPackage.PARTICIPANT_TYPE__PARTICIPANT);
		}
		return participantAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardinality(int newCardinality) {
		int oldCardinality = cardinality;
		cardinality = newCardinality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ROLE_TYPE__CARDINALITY, oldCardinality, cardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OrganizationType> getTeams() {
		if (teams == null) {
			teams = new EObjectWithInverseResolvingEList<OrganizationType>(OrganizationType.class, this, CarnotPackage.ROLE_TYPE__TEAMS, CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD);
		}
		return teams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleSymbolType> getRoleSymbols() {
		if (roleSymbols == null) {
			roleSymbols = new EObjectWithInverseResolvingEList<RoleSymbolType>(RoleSymbolType.class, this, CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS, CarnotPackage.ROLE_SYMBOL_TYPE__ROLE);
		}
		return roleSymbols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<INodeSymbol> getSymbols() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedSwimlanes()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipantAssociations()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__TEAMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTeams()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRoleSymbols()).basicAdd(otherEnd, msgs);
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
			case CarnotPackage.ROLE_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<?>)getPerformedSwimlanes()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<?>)getParticipantAssociations()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__TEAMS:
				return ((InternalEList<?>)getTeams()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				return ((InternalEList<?>)getRoleSymbols()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.ROLE_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.ROLE_TYPE__ID:
				return getId();
			case CarnotPackage.ROLE_TYPE__NAME:
				return getName();
			case CarnotPackage.ROLE_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.ROLE_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				return getPerformedActivities();
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				return getPerformedSwimlanes();
			case CarnotPackage.ROLE_TYPE__PARTICIPANT_ASSOCIATIONS:
				return getParticipantAssociations();
			case CarnotPackage.ROLE_TYPE__CARDINALITY:
				return getCardinality();
			case CarnotPackage.ROLE_TYPE__TEAMS:
				return getTeams();
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				return getRoleSymbols();
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
			case CarnotPackage.ROLE_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				getPerformedActivities().addAll((Collection<? extends ActivityType>)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				getPerformedSwimlanes().addAll((Collection<? extends ISwimlaneSymbol>)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__CARDINALITY:
				setCardinality((Integer)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__TEAMS:
				getTeams().clear();
				getTeams().addAll((Collection<? extends OrganizationType>)newValue);
				return;
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				getRoleSymbols().clear();
				getRoleSymbols().addAll((Collection<? extends RoleSymbolType>)newValue);
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
			case CarnotPackage.ROLE_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.ROLE_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.ROLE_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.ROLE_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.ROLE_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				return;
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				return;
			case CarnotPackage.ROLE_TYPE__CARDINALITY:
				setCardinality(CARDINALITY_EDEFAULT);
				return;
			case CarnotPackage.ROLE_TYPE__TEAMS:
				getTeams().clear();
				return;
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				getRoleSymbols().clear();
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
			case CarnotPackage.ROLE_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.ROLE_TYPE__ID:
				return isSetId();
			case CarnotPackage.ROLE_TYPE__NAME:
				return isSetName();
			case CarnotPackage.ROLE_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.ROLE_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.ROLE_TYPE__PERFORMED_ACTIVITIES:
				return performedActivities != null && !performedActivities.isEmpty();
			case CarnotPackage.ROLE_TYPE__PERFORMED_SWIMLANES:
				return performedSwimlanes != null && !performedSwimlanes.isEmpty();
			case CarnotPackage.ROLE_TYPE__PARTICIPANT_ASSOCIATIONS:
				return participantAssociations != null && !participantAssociations.isEmpty();
			case CarnotPackage.ROLE_TYPE__CARDINALITY:
				return cardinality != CARDINALITY_EDEFAULT;
			case CarnotPackage.ROLE_TYPE__TEAMS:
				return teams != null && !teams.isEmpty();
			case CarnotPackage.ROLE_TYPE__ROLE_SYMBOLS:
				return roleSymbols != null && !roleSymbols.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IIdentifiableElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.ROLE_TYPE__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.ROLE_TYPE__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.ROLE_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IIdentifiableElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.ROLE_TYPE__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.ROLE_TYPE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.ROLE_TYPE__ATTRIBUTE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.ROLE_TYPE___GET_SYMBOLS:
				return getSymbols();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (elementOid: ");
		if (elementOidESet) result.append(elementOid); else result.append("<unset>");
		result.append(", id: ");
		if (idESet) result.append(id); else result.append("<unset>");
		result.append(", name: ");
		if (nameESet) result.append(name); else result.append("<unset>");
		result.append(", cardinality: ");
		result.append(cardinality);
		result.append(')');
		return result.toString();
	}

} //RoleTypeImpl
