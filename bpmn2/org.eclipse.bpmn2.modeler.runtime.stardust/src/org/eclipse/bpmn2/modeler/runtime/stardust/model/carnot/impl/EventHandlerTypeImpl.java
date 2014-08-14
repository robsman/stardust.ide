/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.BindActionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.UnbindActionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Handler Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getAccessPoint <em>Access Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getBindAction <em>Bind Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getEventAction <em>Event Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getUnbindAction <em>Unbind Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#isAutoBind <em>Auto Bind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#isConsumeOnMatch <em>Consume On Match</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#isLogHandler <em>Log Handler</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl#isUnbindOnMatch <em>Unbind On Match</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventHandlerTypeImpl extends MinimalEObjectImpl.Container implements EventHandlerType {
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
	 * The cached value of the '{@link #getAccessPoint() <em>Access Point</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPoint()
	 * @generated
	 * @ordered
	 */
	protected EList<AccessPointType> accessPoint;

	/**
	 * The cached value of the '{@link #getBindAction() <em>Bind Action</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindAction()
	 * @generated
	 * @ordered
	 */
	protected EList<BindActionType> bindAction;

	/**
	 * The cached value of the '{@link #getEventAction() <em>Event Action</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventAction()
	 * @generated
	 * @ordered
	 */
	protected EList<EventActionType> eventAction;

	/**
	 * The cached value of the '{@link #getUnbindAction() <em>Unbind Action</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnbindAction()
	 * @generated
	 * @ordered
	 */
	protected EList<UnbindActionType> unbindAction;

	/**
	 * The default value of the '{@link #isAutoBind() <em>Auto Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoBind()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_BIND_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoBind() <em>Auto Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoBind()
	 * @generated
	 * @ordered
	 */
	protected boolean autoBind = AUTO_BIND_EDEFAULT;

	/**
	 * This is true if the Auto Bind attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean autoBindESet;

	/**
	 * The default value of the '{@link #isConsumeOnMatch() <em>Consume On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConsumeOnMatch()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSUME_ON_MATCH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConsumeOnMatch() <em>Consume On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConsumeOnMatch()
	 * @generated
	 * @ordered
	 */
	protected boolean consumeOnMatch = CONSUME_ON_MATCH_EDEFAULT;

	/**
	 * This is true if the Consume On Match attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean consumeOnMatchESet;

	/**
	 * The default value of the '{@link #isLogHandler() <em>Log Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLogHandler()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOG_HANDLER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLogHandler() <em>Log Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLogHandler()
	 * @generated
	 * @ordered
	 */
	protected boolean logHandler = LOG_HANDLER_EDEFAULT;

	/**
	 * This is true if the Log Handler attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean logHandlerESet;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EventConditionTypeType type;

	/**
	 * The default value of the '{@link #isUnbindOnMatch() <em>Unbind On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnbindOnMatch()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNBIND_ON_MATCH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnbindOnMatch() <em>Unbind On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnbindOnMatch()
	 * @generated
	 * @ordered
	 */
	protected boolean unbindOnMatch = UNBIND_ON_MATCH_EDEFAULT;

	/**
	 * This is true if the Unbind On Match attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean unbindOnMatchESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventHandlerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getEventHandlerType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__ID, oldId, id, !oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__NAME, oldName, name, !oldNameESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AccessPointType> getAccessPoint() {
		if (accessPoint == null) {
			accessPoint = new EObjectContainmentEList<AccessPointType>(AccessPointType.class, this, CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT);
		}
		return accessPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BindActionType> getBindAction() {
		if (bindAction == null) {
			bindAction = new EObjectContainmentEList<BindActionType>(BindActionType.class, this, CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION);
		}
		return bindAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventActionType> getEventAction() {
		if (eventAction == null) {
			eventAction = new EObjectContainmentEList<EventActionType>(EventActionType.class, this, CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION);
		}
		return eventAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UnbindActionType> getUnbindAction() {
		if (unbindAction == null) {
			unbindAction = new EObjectContainmentEList<UnbindActionType>(UnbindActionType.class, this, CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION);
		}
		return unbindAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoBind() {
		return autoBind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoBind(boolean newAutoBind) {
		boolean oldAutoBind = autoBind;
		autoBind = newAutoBind;
		boolean oldAutoBindESet = autoBindESet;
		autoBindESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND, oldAutoBind, autoBind, !oldAutoBindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAutoBind() {
		boolean oldAutoBind = autoBind;
		boolean oldAutoBindESet = autoBindESet;
		autoBind = AUTO_BIND_EDEFAULT;
		autoBindESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND, oldAutoBind, AUTO_BIND_EDEFAULT, oldAutoBindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAutoBind() {
		return autoBindESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConsumeOnMatch() {
		return consumeOnMatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConsumeOnMatch(boolean newConsumeOnMatch) {
		boolean oldConsumeOnMatch = consumeOnMatch;
		consumeOnMatch = newConsumeOnMatch;
		boolean oldConsumeOnMatchESet = consumeOnMatchESet;
		consumeOnMatchESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH, oldConsumeOnMatch, consumeOnMatch, !oldConsumeOnMatchESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetConsumeOnMatch() {
		boolean oldConsumeOnMatch = consumeOnMatch;
		boolean oldConsumeOnMatchESet = consumeOnMatchESet;
		consumeOnMatch = CONSUME_ON_MATCH_EDEFAULT;
		consumeOnMatchESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH, oldConsumeOnMatch, CONSUME_ON_MATCH_EDEFAULT, oldConsumeOnMatchESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetConsumeOnMatch() {
		return consumeOnMatchESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLogHandler() {
		return logHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogHandler(boolean newLogHandler) {
		boolean oldLogHandler = logHandler;
		logHandler = newLogHandler;
		boolean oldLogHandlerESet = logHandlerESet;
		logHandlerESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER, oldLogHandler, logHandler, !oldLogHandlerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetLogHandler() {
		boolean oldLogHandler = logHandler;
		boolean oldLogHandlerESet = logHandlerESet;
		logHandler = LOG_HANDLER_EDEFAULT;
		logHandlerESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER, oldLogHandler, LOG_HANDLER_EDEFAULT, oldLogHandlerESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLogHandler() {
		return logHandlerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventConditionTypeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(EventConditionTypeType newType, NotificationChain msgs) {
		EventConditionTypeType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EventConditionTypeType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, CarnotPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CarnotPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnbindOnMatch() {
		return unbindOnMatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnbindOnMatch(boolean newUnbindOnMatch) {
		boolean oldUnbindOnMatch = unbindOnMatch;
		unbindOnMatch = newUnbindOnMatch;
		boolean oldUnbindOnMatchESet = unbindOnMatchESet;
		unbindOnMatchESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH, oldUnbindOnMatch, unbindOnMatch, !oldUnbindOnMatchESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnbindOnMatch() {
		boolean oldUnbindOnMatch = unbindOnMatch;
		boolean oldUnbindOnMatchESet = unbindOnMatchESet;
		unbindOnMatch = UNBIND_ON_MATCH_EDEFAULT;
		unbindOnMatchESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH, oldUnbindOnMatch, UNBIND_ON_MATCH_EDEFAULT, oldUnbindOnMatchESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnbindOnMatch() {
		return unbindOnMatchESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMetaType getMetaType() {
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CarnotPackage.EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS, EventConditionTypeType.class, msgs);
				return basicSetType((EventConditionTypeType)otherEnd, msgs);
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
			case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
				return ((InternalEList<?>)getAccessPoint()).basicRemove(otherEnd, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
				return ((InternalEList<?>)getBindAction()).basicRemove(otherEnd, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
				return ((InternalEList<?>)getEventAction()).basicRemove(otherEnd, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
				return ((InternalEList<?>)getUnbindAction()).basicRemove(otherEnd, msgs);
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				return basicSetType(null, msgs);
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
			case CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.EVENT_HANDLER_TYPE__ID:
				return getId();
			case CarnotPackage.EVENT_HANDLER_TYPE__NAME:
				return getName();
			case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
				return getAccessPoint();
			case CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
				return getBindAction();
			case CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
				return getEventAction();
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
				return getUnbindAction();
			case CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
				return isAutoBind();
			case CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
				return isConsumeOnMatch();
			case CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
				return isLogHandler();
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				return getType();
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
				return isUnbindOnMatch();
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
			case CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
				getAccessPoint().clear();
				getAccessPoint().addAll((Collection<? extends AccessPointType>)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
				getBindAction().clear();
				getBindAction().addAll((Collection<? extends BindActionType>)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
				getEventAction().clear();
				getEventAction().addAll((Collection<? extends EventActionType>)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
				getUnbindAction().clear();
				getUnbindAction().addAll((Collection<? extends UnbindActionType>)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
				setAutoBind((Boolean)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
				setConsumeOnMatch((Boolean)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
				setLogHandler((Boolean)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				setType((EventConditionTypeType)newValue);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
				setUnbindOnMatch((Boolean)newValue);
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
			case CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
				getAccessPoint().clear();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
				getBindAction().clear();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
				getEventAction().clear();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
				getUnbindAction().clear();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
				unsetAutoBind();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
				unsetConsumeOnMatch();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
				unsetLogHandler();
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				setType((EventConditionTypeType)null);
				return;
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
				unsetUnbindOnMatch();
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
			case CarnotPackage.EVENT_HANDLER_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.EVENT_HANDLER_TYPE__ID:
				return isSetId();
			case CarnotPackage.EVENT_HANDLER_TYPE__NAME:
				return isSetName();
			case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.EVENT_HANDLER_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT:
				return accessPoint != null && !accessPoint.isEmpty();
			case CarnotPackage.EVENT_HANDLER_TYPE__BIND_ACTION:
				return bindAction != null && !bindAction.isEmpty();
			case CarnotPackage.EVENT_HANDLER_TYPE__EVENT_ACTION:
				return eventAction != null && !eventAction.isEmpty();
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ACTION:
				return unbindAction != null && !unbindAction.isEmpty();
			case CarnotPackage.EVENT_HANDLER_TYPE__AUTO_BIND:
				return isSetAutoBind();
			case CarnotPackage.EVENT_HANDLER_TYPE__CONSUME_ON_MATCH:
				return isSetConsumeOnMatch();
			case CarnotPackage.EVENT_HANDLER_TYPE__LOG_HANDLER:
				return isSetLogHandler();
			case CarnotPackage.EVENT_HANDLER_TYPE__TYPE:
				return type != null;
			case CarnotPackage.EVENT_HANDLER_TYPE__UNBIND_ON_MATCH:
				return isSetUnbindOnMatch();
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
				case CarnotPackage.EVENT_HANDLER_TYPE__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.EVENT_HANDLER_TYPE__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT: return CarnotPackage.IACCESS_POINT_OWNER__ACCESS_POINT;
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
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.EVENT_HANDLER_TYPE__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.EVENT_HANDLER_TYPE__NAME;
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.EVENT_HANDLER_TYPE__ATTRIBUTE;
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IACCESS_POINT_OWNER__ACCESS_POINT: return CarnotPackage.EVENT_HANDLER_TYPE__ACCESS_POINT;
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
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == IIdentifiableElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IExtensibleElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == ITypedElement.class) {
			switch (baseOperationID) {
				case CarnotPackage.ITYPED_ELEMENT___GET_META_TYPE: return CarnotPackage.EVENT_HANDLER_TYPE___GET_META_TYPE;
				default: return -1;
			}
		}
		if (baseClass == IAccessPointOwner.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.EVENT_HANDLER_TYPE___GET_META_TYPE:
				return getMetaType();
			case CarnotPackage.EVENT_HANDLER_TYPE___GET_SYMBOLS:
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
		result.append(", autoBind: ");
		if (autoBindESet) result.append(autoBind); else result.append("<unset>");
		result.append(", consumeOnMatch: ");
		if (consumeOnMatchESet) result.append(consumeOnMatch); else result.append("<unset>");
		result.append(", logHandler: ");
		if (logHandlerESet) result.append(logHandler); else result.append("<unset>");
		result.append(", unbindOnMatch: ");
		if (unbindOnMatchESet) result.append(unbindOnMatch); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //EventHandlerTypeImpl
