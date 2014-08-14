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
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType;
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
 * An implementation of the model object '<em><b>Organization Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getParticipantAssociations <em>Participant Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getOrganizationSymbols <em>Organization Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl#getTeamLead <em>Team Lead</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrganizationTypeImpl extends MinimalEObjectImpl.Container implements OrganizationType {
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
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected EList<ParticipantType> participant;

	/**
	 * The cached value of the '{@link #getOrganizationSymbols() <em>Organization Symbols</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganizationSymbols()
	 * @generated
	 * @ordered
	 */
	protected EList<OrganizationSymbolType> organizationSymbols;

	/**
	 * The cached value of the '{@link #getTeamLead() <em>Team Lead</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeamLead()
	 * @generated
	 * @ordered
	 */
	protected RoleType teamLead;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrganizationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getOrganizationType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__ID, oldId, id, !oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ORGANIZATION_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__NAME, oldName, name, !oldNameESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.ORGANIZATION_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityType> getPerformedActivities() {
		if (performedActivities == null) {
			performedActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES, CarnotPackage.ACTIVITY_TYPE__PERFORMER);
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
			performedSwimlanes = new EObjectWithInverseResolvingEList<ISwimlaneSymbol>(ISwimlaneSymbol.class, this, CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES, CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT);
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
			participantAssociations = new EObjectWithInverseResolvingEList<ParticipantType>(ParticipantType.class, this, CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS, CarnotPackage.PARTICIPANT_TYPE__PARTICIPANT);
		}
		return participantAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParticipantType> getParticipant() {
		if (participant == null) {
			participant = new EObjectContainmentEList<ParticipantType>(ParticipantType.class, this, CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT);
		}
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OrganizationSymbolType> getOrganizationSymbols() {
		if (organizationSymbols == null) {
			organizationSymbols = new EObjectWithInverseResolvingEList<OrganizationSymbolType>(OrganizationSymbolType.class, this, CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS, CarnotPackage.ORGANIZATION_SYMBOL_TYPE__ORGANIZATION);
		}
		return organizationSymbols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType getTeamLead() {
		return teamLead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTeamLead(RoleType newTeamLead, NotificationChain msgs) {
		RoleType oldTeamLead = teamLead;
		teamLead = newTeamLead;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD, oldTeamLead, newTeamLead);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTeamLead(RoleType newTeamLead) {
		if (newTeamLead != teamLead) {
			NotificationChain msgs = null;
			if (teamLead != null)
				msgs = ((InternalEObject)teamLead).eInverseRemove(this, CarnotPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
			if (newTeamLead != null)
				msgs = ((InternalEObject)newTeamLead).eInverseAdd(this, CarnotPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
			msgs = basicSetTeamLead(newTeamLead, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD, newTeamLead, newTeamLead));
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
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedSwimlanes()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipantAssociations()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOrganizationSymbols()).basicAdd(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				if (teamLead != null)
					msgs = ((InternalEObject)teamLead).eInverseRemove(this, CarnotPackage.ROLE_TYPE__TEAMS, RoleType.class, msgs);
				return basicSetTeamLead((RoleType)otherEnd, msgs);
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
			case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<?>)getPerformedSwimlanes()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<?>)getParticipantAssociations()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT:
				return ((InternalEList<?>)getParticipant()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				return ((InternalEList<?>)getOrganizationSymbols()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				return basicSetTeamLead(null, msgs);
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
			case CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.ORGANIZATION_TYPE__ID:
				return getId();
			case CarnotPackage.ORGANIZATION_TYPE__NAME:
				return getName();
			case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				return getPerformedActivities();
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				return getPerformedSwimlanes();
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
				return getParticipantAssociations();
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT:
				return getParticipant();
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				return getOrganizationSymbols();
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				return getTeamLead();
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
			case CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				getPerformedActivities().addAll((Collection<? extends ActivityType>)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				getPerformedSwimlanes().addAll((Collection<? extends ISwimlaneSymbol>)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT:
				getParticipant().clear();
				getParticipant().addAll((Collection<? extends ParticipantType>)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				getOrganizationSymbols().clear();
				getOrganizationSymbols().addAll((Collection<? extends OrganizationSymbolType>)newValue);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				setTeamLead((RoleType)newValue);
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
			case CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT:
				getParticipant().clear();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				getOrganizationSymbols().clear();
				return;
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				setTeamLead((RoleType)null);
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
			case CarnotPackage.ORGANIZATION_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.ORGANIZATION_TYPE__ID:
				return isSetId();
			case CarnotPackage.ORGANIZATION_TYPE__NAME:
				return isSetName();
			case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_ACTIVITIES:
				return performedActivities != null && !performedActivities.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__PERFORMED_SWIMLANES:
				return performedSwimlanes != null && !performedSwimlanes.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS:
				return participantAssociations != null && !participantAssociations.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__PARTICIPANT:
				return participant != null && !participant.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS:
				return organizationSymbols != null && !organizationSymbols.isEmpty();
			case CarnotPackage.ORGANIZATION_TYPE__TEAM_LEAD:
				return teamLead != null;
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
				case CarnotPackage.ORGANIZATION_TYPE__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.ORGANIZATION_TYPE__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.ORGANIZATION_TYPE__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.ORGANIZATION_TYPE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.ORGANIZATION_TYPE__ATTRIBUTE;
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
			case CarnotPackage.ORGANIZATION_TYPE___GET_SYMBOLS:
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
		result.append(')');
		return result.toString();
	}

} //OrganizationTypeImpl
