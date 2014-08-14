/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement;

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
 * An implementation of the model object '<em><b>Application Context Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#isIsPredefined <em>Is Predefined</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getAccessPointProviderClass <em>Access Point Provider Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#isHasApplicationPath <em>Has Application Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#isHasMappingId <em>Has Mapping Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl#getContexts <em>Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationContextTypeTypeImpl extends MinimalEObjectImpl.Container implements ApplicationContextTypeType {
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
	 * The default value of the '{@link #isIsPredefined() <em>Is Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPredefined()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PREDEFINED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsPredefined() <em>Is Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPredefined()
	 * @generated
	 * @ordered
	 */
	protected boolean isPredefined = IS_PREDEFINED_EDEFAULT;

	/**
	 * This is true if the Is Predefined attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isPredefinedESet;

	/**
	 * The default value of the '{@link #getAccessPointProviderClass() <em>Access Point Provider Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPointProviderClass()
	 * @generated
	 * @ordered
	 */
	protected static final String ACCESS_POINT_PROVIDER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAccessPointProviderClass() <em>Access Point Provider Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPointProviderClass()
	 * @generated
	 * @ordered
	 */
	protected String accessPointProviderClass = ACCESS_POINT_PROVIDER_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasApplicationPath() <em>Has Application Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasApplicationPath()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_APPLICATION_PATH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasApplicationPath() <em>Has Application Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasApplicationPath()
	 * @generated
	 * @ordered
	 */
	protected boolean hasApplicationPath = HAS_APPLICATION_PATH_EDEFAULT;

	/**
	 * This is true if the Has Application Path attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hasApplicationPathESet;

	/**
	 * The default value of the '{@link #isHasMappingId() <em>Has Mapping Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasMappingId()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_MAPPING_ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasMappingId() <em>Has Mapping Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasMappingId()
	 * @generated
	 * @ordered
	 */
	protected boolean hasMappingId = HAS_MAPPING_ID_EDEFAULT;

	/**
	 * This is true if the Has Mapping Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hasMappingIdESet;

	/**
	 * The default value of the '{@link #getPanelClass() <em>Panel Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanelClass()
	 * @generated
	 * @ordered
	 */
	protected static final String PANEL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPanelClass() <em>Panel Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanelClass()
	 * @generated
	 * @ordered
	 */
	protected String panelClass = PANEL_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidatorClass()
	 * @generated
	 * @ordered
	 */
	protected static final String VALIDATOR_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidatorClass()
	 * @generated
	 * @ordered
	 */
	protected String validatorClass = VALIDATOR_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContexts() <em>Contexts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContexts()
	 * @generated
	 * @ordered
	 */
	protected EList<ContextType> contexts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationContextTypeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getApplicationContextTypeType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID, oldId, id, !oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME, oldName, name, !oldNameESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsPredefined() {
		return isPredefined;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPredefined(boolean newIsPredefined) {
		boolean oldIsPredefined = isPredefined;
		isPredefined = newIsPredefined;
		boolean oldIsPredefinedESet = isPredefinedESet;
		isPredefinedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, isPredefined, !oldIsPredefinedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsPredefined() {
		boolean oldIsPredefined = isPredefined;
		boolean oldIsPredefinedESet = isPredefinedESet;
		isPredefined = IS_PREDEFINED_EDEFAULT;
		isPredefinedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED, oldIsPredefined, IS_PREDEFINED_EDEFAULT, oldIsPredefinedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsPredefined() {
		return isPredefinedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAccessPointProviderClass() {
		return accessPointProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessPointProviderClass(String newAccessPointProviderClass) {
		String oldAccessPointProviderClass = accessPointProviderClass;
		accessPointProviderClass = newAccessPointProviderClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS, oldAccessPointProviderClass, accessPointProviderClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasApplicationPath() {
		return hasApplicationPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasApplicationPath(boolean newHasApplicationPath) {
		boolean oldHasApplicationPath = hasApplicationPath;
		hasApplicationPath = newHasApplicationPath;
		boolean oldHasApplicationPathESet = hasApplicationPathESet;
		hasApplicationPathESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH, oldHasApplicationPath, hasApplicationPath, !oldHasApplicationPathESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHasApplicationPath() {
		boolean oldHasApplicationPath = hasApplicationPath;
		boolean oldHasApplicationPathESet = hasApplicationPathESet;
		hasApplicationPath = HAS_APPLICATION_PATH_EDEFAULT;
		hasApplicationPathESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH, oldHasApplicationPath, HAS_APPLICATION_PATH_EDEFAULT, oldHasApplicationPathESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHasApplicationPath() {
		return hasApplicationPathESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasMappingId() {
		return hasMappingId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasMappingId(boolean newHasMappingId) {
		boolean oldHasMappingId = hasMappingId;
		hasMappingId = newHasMappingId;
		boolean oldHasMappingIdESet = hasMappingIdESet;
		hasMappingIdESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID, oldHasMappingId, hasMappingId, !oldHasMappingIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHasMappingId() {
		boolean oldHasMappingId = hasMappingId;
		boolean oldHasMappingIdESet = hasMappingIdESet;
		hasMappingId = HAS_MAPPING_ID_EDEFAULT;
		hasMappingIdESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID, oldHasMappingId, HAS_MAPPING_ID_EDEFAULT, oldHasMappingIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHasMappingId() {
		return hasMappingIdESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPanelClass() {
		return panelClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanelClass(String newPanelClass) {
		String oldPanelClass = panelClass;
		panelClass = newPanelClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS, oldPanelClass, panelClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidatorClass() {
		return validatorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidatorClass(String newValidatorClass) {
		String oldValidatorClass = validatorClass;
		validatorClass = newValidatorClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ContextType> getContexts() {
		if (contexts == null) {
			contexts = new EObjectWithInverseResolvingEList<ContextType>(ContextType.class, this, CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS, CarnotPackage.CONTEXT_TYPE__TYPE);
		}
		return contexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExtensionPointId() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ITypedElement> getTypedElements() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContexts()).basicAdd(otherEnd, msgs);
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				return ((InternalEList<?>)getContexts()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID:
				return getId();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME:
				return getName();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED:
				return isIsPredefined();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
				return getAccessPointProviderClass();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH:
				return isHasApplicationPath();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID:
				return isHasMappingId();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS:
				return getPanelClass();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS:
				return getValidatorClass();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				return getContexts();
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED:
				setIsPredefined((Boolean)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
				setAccessPointProviderClass((String)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH:
				setHasApplicationPath((Boolean)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID:
				setHasMappingId((Boolean)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS:
				setPanelClass((String)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS:
				setValidatorClass((String)newValue);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				getContexts().clear();
				getContexts().addAll((Collection<? extends ContextType>)newValue);
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED:
				unsetIsPredefined();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
				setAccessPointProviderClass(ACCESS_POINT_PROVIDER_CLASS_EDEFAULT);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH:
				unsetHasApplicationPath();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID:
				unsetHasMappingId();
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS:
				setPanelClass(PANEL_CLASS_EDEFAULT);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS:
				setValidatorClass(VALIDATOR_CLASS_EDEFAULT);
				return;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				getContexts().clear();
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID:
				return isSetId();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME:
				return isSetName();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED:
				return isSetIsPredefined();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS:
				return ACCESS_POINT_PROVIDER_CLASS_EDEFAULT == null ? accessPointProviderClass != null : !ACCESS_POINT_PROVIDER_CLASS_EDEFAULT.equals(accessPointProviderClass);
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH:
				return isSetHasApplicationPath();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID:
				return isSetHasMappingId();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS:
				return PANEL_CLASS_EDEFAULT == null ? panelClass != null : !PANEL_CLASS_EDEFAULT.equals(panelClass);
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS:
				return VALIDATOR_CLASS_EDEFAULT == null ? validatorClass != null : !VALIDATOR_CLASS_EDEFAULT.equals(validatorClass);
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS:
				return contexts != null && !contexts.isEmpty();
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
				case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE;
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
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE___GET_EXTENSION_POINT_ID:
				return getExtensionPointId();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE___GET_TYPED_ELEMENTS:
				return getTypedElements();
			case CarnotPackage.APPLICATION_CONTEXT_TYPE_TYPE___GET_SYMBOLS:
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
		result.append(", isPredefined: ");
		if (isPredefinedESet) result.append(isPredefined); else result.append("<unset>");
		result.append(", accessPointProviderClass: ");
		result.append(accessPointProviderClass);
		result.append(", hasApplicationPath: ");
		if (hasApplicationPathESet) result.append(hasApplicationPath); else result.append("<unset>");
		result.append(", hasMappingId: ");
		if (hasMappingIdESet) result.append(hasMappingId); else result.append("<unset>");
		result.append(", panelClass: ");
		result.append(panelClass);
		result.append(", validatorClass: ");
		result.append(validatorClass);
		result.append(')');
		return result.toString();
	}

} //ApplicationContextTypeTypeImpl
