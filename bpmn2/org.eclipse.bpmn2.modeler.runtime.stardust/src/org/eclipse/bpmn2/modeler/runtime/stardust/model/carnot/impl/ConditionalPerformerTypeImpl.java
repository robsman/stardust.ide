/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType;

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
 * An implementation of the model object '<em><b>Conditional Performer Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getParticipantAssociations <em>Participant Associations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#isIsUser <em>Is User</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl#getConditionalPerformerSymbols <em>Conditional Performer Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalPerformerTypeImpl extends MinimalEObjectImpl.Container implements ConditionalPerformerType {
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
	 * The cached value of the '{@link #getData() <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected DataType data;

	/**
	 * The default value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPath()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPath()
	 * @generated
	 * @ordered
	 */
	protected String dataPath = DATA_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsUser() <em>Is User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUser()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_USER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsUser() <em>Is User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUser()
	 * @generated
	 * @ordered
	 */
	protected boolean isUser = IS_USER_EDEFAULT;

	/**
	 * This is true if the Is User attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isUserESet;

	/**
	 * The cached value of the '{@link #getConditionalPerformerSymbols() <em>Conditional Performer Symbols</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionalPerformerSymbols()
	 * @generated
	 * @ordered
	 */
	protected EList<ConditionalPerformerSymbolType> conditionalPerformerSymbols;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalPerformerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getConditionalPerformerType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID, oldId, id, !oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME, oldName, name, !oldNameESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityType> getPerformedActivities() {
		if (performedActivities == null) {
			performedActivities = new EObjectWithInverseResolvingEList<ActivityType>(ActivityType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES, CarnotPackage.ACTIVITY_TYPE__PERFORMER);
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
			performedSwimlanes = new EObjectWithInverseResolvingEList<ISwimlaneSymbol>(ISwimlaneSymbol.class, this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES, CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT);
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
			participantAssociations = new EObjectWithInverseResolvingEList<ParticipantType>(ParticipantType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS, CarnotPackage.PARTICIPANT_TYPE__PARTICIPANT);
		}
		return participantAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetData(DataType newData, NotificationChain msgs) {
		DataType oldData = data;
		data = newData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA, oldData, newData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(DataType newData) {
		if (newData != data) {
			NotificationChain msgs = null;
			if (data != null)
				msgs = ((InternalEObject)data).eInverseRemove(this, CarnotPackage.DATA_TYPE__CONDITIONAL_PERFORMERS, DataType.class, msgs);
			if (newData != null)
				msgs = ((InternalEObject)newData).eInverseAdd(this, CarnotPackage.DATA_TYPE__CONDITIONAL_PERFORMERS, DataType.class, msgs);
			msgs = basicSetData(newData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA, newData, newData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataPath() {
		return dataPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataPath(String newDataPath) {
		String oldDataPath = dataPath;
		dataPath = newDataPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA_PATH, oldDataPath, dataPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsUser() {
		return isUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUser(boolean newIsUser) {
		boolean oldIsUser = isUser;
		isUser = newIsUser;
		boolean oldIsUserESet = isUserESet;
		isUserESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER, oldIsUser, isUser, !oldIsUserESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsUser() {
		boolean oldIsUser = isUser;
		boolean oldIsUserESet = isUserESet;
		isUser = IS_USER_EDEFAULT;
		isUserESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER, oldIsUser, IS_USER_EDEFAULT, oldIsUserESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsUser() {
		return isUserESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionalPerformerSymbolType> getConditionalPerformerSymbols() {
		if (conditionalPerformerSymbols == null) {
			conditionalPerformerSymbols = new EObjectWithInverseResolvingEList<ConditionalPerformerSymbolType>(ConditionalPerformerSymbolType.class, this, CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS, CarnotPackage.CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT);
		}
		return conditionalPerformerSymbols;
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedActivities()).basicAdd(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformedSwimlanes()).basicAdd(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipantAssociations()).basicAdd(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				if (data != null)
					msgs = ((InternalEObject)data).eInverseRemove(this, CarnotPackage.DATA_TYPE__CONDITIONAL_PERFORMERS, DataType.class, msgs);
				return basicSetData((DataType)otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConditionalPerformerSymbols()).basicAdd(otherEnd, msgs);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				return ((InternalEList<?>)getPerformedActivities()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				return ((InternalEList<?>)getPerformedSwimlanes()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS:
				return ((InternalEList<?>)getParticipantAssociations()).basicRemove(otherEnd, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				return basicSetData(null, msgs);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				return ((InternalEList<?>)getConditionalPerformerSymbols()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID:
				return getId();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME:
				return getName();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				return getPerformedActivities();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				return getPerformedSwimlanes();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS:
				return getParticipantAssociations();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				return getData();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA_PATH:
				return getDataPath();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER:
				return isIsUser();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				return getConditionalPerformerSymbols();
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				getPerformedActivities().addAll((Collection<? extends ActivityType>)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				getPerformedSwimlanes().addAll((Collection<? extends ISwimlaneSymbol>)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				setData((DataType)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA_PATH:
				setDataPath((String)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER:
				setIsUser((Boolean)newValue);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				getConditionalPerformerSymbols().clear();
				getConditionalPerformerSymbols().addAll((Collection<? extends ConditionalPerformerSymbolType>)newValue);
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				getPerformedActivities().clear();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				getPerformedSwimlanes().clear();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				setData((DataType)null);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA_PATH:
				setDataPath(DATA_PATH_EDEFAULT);
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER:
				unsetIsUser();
				return;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				getConditionalPerformerSymbols().clear();
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID:
				return isSetId();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME:
				return isSetName();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES:
				return performedActivities != null && !performedActivities.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES:
				return performedSwimlanes != null && !performedSwimlanes.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS:
				return participantAssociations != null && !participantAssociations.isEmpty();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA:
				return data != null;
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__DATA_PATH:
				return DATA_PATH_EDEFAULT == null ? dataPath != null : !DATA_PATH_EDEFAULT.equals(dataPath);
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__IS_USER:
				return isSetIsUser();
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS:
				return conditionalPerformerSymbols != null && !conditionalPerformerSymbols.isEmpty();
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
				case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.CONDITIONAL_PERFORMER_TYPE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE;
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
			case CarnotPackage.CONDITIONAL_PERFORMER_TYPE___GET_SYMBOLS:
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
		result.append(", dataPath: ");
		result.append(dataPath);
		result.append(", isUser: ");
		if (isUserESet) result.append(isUser); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ConditionalPerformerTypeImpl
