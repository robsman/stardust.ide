/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.QualityControlType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackages;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ScriptType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationsType;

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
 * An implementation of the model object '<em><b>Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getApplicationType <em>Application Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getApplicationContextType <em>Application Context Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getTriggerType <em>Trigger Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getEventConditionType <em>Event Condition Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getEventActionType <em>Event Action Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getModeler <em>Modeler</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getQualityControl <em>Quality Control</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getConditionalPerformer <em>Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getView <em>View</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getCarnotVersion <em>Carnot Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getModelOID <em>Model OID</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getOid <em>Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl#getVendor <em>Vendor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelTypeImpl extends MinimalEObjectImpl.Container implements ModelType {
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
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected EList<DataTypeType> dataType;

	/**
	 * The cached value of the '{@link #getApplicationType() <em>Application Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationType()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationTypeType> applicationType;

	/**
	 * The cached value of the '{@link #getApplicationContextType() <em>Application Context Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationContextType()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationContextTypeType> applicationContextType;

	/**
	 * The cached value of the '{@link #getTriggerType() <em>Trigger Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerType()
	 * @generated
	 * @ordered
	 */
	protected EList<TriggerTypeType> triggerType;

	/**
	 * The cached value of the '{@link #getEventConditionType() <em>Event Condition Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventConditionType()
	 * @generated
	 * @ordered
	 */
	protected EList<EventConditionTypeType> eventConditionType;

	/**
	 * The cached value of the '{@link #getEventActionType() <em>Event Action Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventActionType()
	 * @generated
	 * @ordered
	 */
	protected EList<EventActionTypeType> eventActionType;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EList<DataType> data;

	/**
	 * The cached value of the '{@link #getApplication() <em>Application</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplication()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationType> application;

	/**
	 * The cached value of the '{@link #getModeler() <em>Modeler</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeler()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelerType> modeler;

	/**
	 * The cached value of the '{@link #getQualityControl() <em>Quality Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualityControl()
	 * @generated
	 * @ordered
	 */
	protected QualityControlType qualityControl;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleType> role;

	/**
	 * The cached value of the '{@link #getOrganization() <em>Organization</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected EList<OrganizationType> organization;

	/**
	 * The cached value of the '{@link #getConditionalPerformer() <em>Conditional Performer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionalPerformer()
	 * @generated
	 * @ordered
	 */
	protected EList<ConditionalPerformerType> conditionalPerformer;

	/**
	 * The cached value of the '{@link #getProcessDefinition() <em>Process Definition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessDefinitionType> processDefinition;

	/**
	 * The cached value of the '{@link #getExternalPackages() <em>External Packages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackages()
	 * @generated
	 * @ordered
	 */
	protected ExternalPackages externalPackages;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected ScriptType script;

	/**
	 * The cached value of the '{@link #getTypeDeclarations() <em>Type Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeDeclarations()
	 * @generated
	 * @ordered
	 */
	protected TypeDeclarationsType typeDeclarations;

	/**
	 * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagram()
	 * @generated
	 * @ordered
	 */
	protected EList<DiagramType> diagram;

	/**
	 * The cached value of the '{@link #getLinkType() <em>Link Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkType()
	 * @generated
	 * @ordered
	 */
	protected EList<LinkTypeType> linkType;

	/**
	 * The cached value of the '{@link #getView() <em>View</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
	protected EList<ViewType> view;

	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCarnotVersion() <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCarnotVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String CARNOT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCarnotVersion() <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCarnotVersion()
	 * @generated
	 * @ordered
	 */
	protected String carnotVersion = CARNOT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected String created = CREATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelOID() <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelOID()
	 * @generated
	 * @ordered
	 */
	protected static final int MODEL_OID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getModelOID() <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelOID()
	 * @generated
	 * @ordered
	 */
	protected int modelOID = MODEL_OID_EDEFAULT;

	/**
	 * This is true if the Model OID attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean modelOIDESet;

	/**
	 * The default value of the '{@link #getOid() <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOid()
	 * @generated
	 * @ordered
	 */
	protected static final long OID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getOid() <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOid()
	 * @generated
	 * @ordered
	 */
	protected long oid = OID_EDEFAULT;

	/**
	 * This is true if the Oid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean oidESet;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getModelType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__ID, oldId, id, !oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.MODEL_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__NAME, oldName, name, !oldNameESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.MODEL_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
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
			attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotPackage.MODEL_TYPE__ATTRIBUTE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataTypeType> getDataType() {
		if (dataType == null) {
			dataType = new EObjectContainmentEList<DataTypeType>(DataTypeType.class, this, CarnotPackage.MODEL_TYPE__DATA_TYPE);
		}
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationTypeType> getApplicationType() {
		if (applicationType == null) {
			applicationType = new EObjectContainmentEList<ApplicationTypeType>(ApplicationTypeType.class, this, CarnotPackage.MODEL_TYPE__APPLICATION_TYPE);
		}
		return applicationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationContextTypeType> getApplicationContextType() {
		if (applicationContextType == null) {
			applicationContextType = new EObjectContainmentEList<ApplicationContextTypeType>(ApplicationContextTypeType.class, this, CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE);
		}
		return applicationContextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TriggerTypeType> getTriggerType() {
		if (triggerType == null) {
			triggerType = new EObjectContainmentEList<TriggerTypeType>(TriggerTypeType.class, this, CarnotPackage.MODEL_TYPE__TRIGGER_TYPE);
		}
		return triggerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventConditionTypeType> getEventConditionType() {
		if (eventConditionType == null) {
			eventConditionType = new EObjectContainmentEList<EventConditionTypeType>(EventConditionTypeType.class, this, CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE);
		}
		return eventConditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventActionTypeType> getEventActionType() {
		if (eventActionType == null) {
			eventActionType = new EObjectContainmentEList<EventActionTypeType>(EventActionTypeType.class, this, CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE);
		}
		return eventActionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataType> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<DataType>(DataType.class, this, CarnotPackage.MODEL_TYPE__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationType> getApplication() {
		if (application == null) {
			application = new EObjectContainmentEList<ApplicationType>(ApplicationType.class, this, CarnotPackage.MODEL_TYPE__APPLICATION);
		}
		return application;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelerType> getModeler() {
		if (modeler == null) {
			modeler = new EObjectContainmentEList<ModelerType>(ModelerType.class, this, CarnotPackage.MODEL_TYPE__MODELER);
		}
		return modeler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualityControlType getQualityControl() {
		return qualityControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualityControl(QualityControlType newQualityControl, NotificationChain msgs) {
		QualityControlType oldQualityControl = qualityControl;
		qualityControl = newQualityControl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__QUALITY_CONTROL, oldQualityControl, newQualityControl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityControl(QualityControlType newQualityControl) {
		if (newQualityControl != qualityControl) {
			NotificationChain msgs = null;
			if (qualityControl != null)
				msgs = ((InternalEObject)qualityControl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__QUALITY_CONTROL, null, msgs);
			if (newQualityControl != null)
				msgs = ((InternalEObject)newQualityControl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__QUALITY_CONTROL, null, msgs);
			msgs = basicSetQualityControl(newQualityControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__QUALITY_CONTROL, newQualityControl, newQualityControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleType> getRole() {
		if (role == null) {
			role = new EObjectContainmentEList<RoleType>(RoleType.class, this, CarnotPackage.MODEL_TYPE__ROLE);
		}
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OrganizationType> getOrganization() {
		if (organization == null) {
			organization = new EObjectContainmentEList<OrganizationType>(OrganizationType.class, this, CarnotPackage.MODEL_TYPE__ORGANIZATION);
		}
		return organization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionalPerformerType> getConditionalPerformer() {
		if (conditionalPerformer == null) {
			conditionalPerformer = new EObjectContainmentEList<ConditionalPerformerType>(ConditionalPerformerType.class, this, CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER);
		}
		return conditionalPerformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcessDefinitionType> getProcessDefinition() {
		if (processDefinition == null) {
			processDefinition = new EObjectContainmentEList<ProcessDefinitionType>(ProcessDefinitionType.class, this, CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION);
		}
		return processDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackages getExternalPackages() {
		return externalPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalPackages(ExternalPackages newExternalPackages, NotificationChain msgs) {
		ExternalPackages oldExternalPackages = externalPackages;
		externalPackages = newExternalPackages;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES, oldExternalPackages, newExternalPackages);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalPackages(ExternalPackages newExternalPackages) {
		if (newExternalPackages != externalPackages) {
			NotificationChain msgs = null;
			if (externalPackages != null)
				msgs = ((InternalEObject)externalPackages).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES, null, msgs);
			if (newExternalPackages != null)
				msgs = ((InternalEObject)newExternalPackages).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES, null, msgs);
			msgs = basicSetExternalPackages(newExternalPackages, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES, newExternalPackages, newExternalPackages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		ScriptType oldScript = script;
		script = newScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__SCRIPT, oldScript, newScript);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		if (newScript != script) {
			NotificationChain msgs = null;
			if (script != null)
				msgs = ((InternalEObject)script).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__SCRIPT, null, msgs);
			if (newScript != null)
				msgs = ((InternalEObject)newScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__SCRIPT, null, msgs);
			msgs = basicSetScript(newScript, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__SCRIPT, newScript, newScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationsType getTypeDeclarations() {
		return typeDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeDeclarations(TypeDeclarationsType newTypeDeclarations, NotificationChain msgs) {
		TypeDeclarationsType oldTypeDeclarations = typeDeclarations;
		typeDeclarations = newTypeDeclarations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS, oldTypeDeclarations, newTypeDeclarations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeDeclarations(TypeDeclarationsType newTypeDeclarations) {
		if (newTypeDeclarations != typeDeclarations) {
			NotificationChain msgs = null;
			if (typeDeclarations != null)
				msgs = ((InternalEObject)typeDeclarations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS, null, msgs);
			if (newTypeDeclarations != null)
				msgs = ((InternalEObject)newTypeDeclarations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS, null, msgs);
			msgs = basicSetTypeDeclarations(newTypeDeclarations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS, newTypeDeclarations, newTypeDeclarations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DiagramType> getDiagram() {
		if (diagram == null) {
			diagram = new EObjectContainmentEList<DiagramType>(DiagramType.class, this, CarnotPackage.MODEL_TYPE__DIAGRAM);
		}
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkTypeType> getLinkType() {
		if (linkType == null) {
			linkType = new EObjectContainmentEList<LinkTypeType>(LinkTypeType.class, this, CarnotPackage.MODEL_TYPE__LINK_TYPE);
		}
		return linkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ViewType> getView() {
		if (view == null) {
			view = new EObjectContainmentEList<ViewType>(ViewType.class, this, CarnotPackage.MODEL_TYPE__VIEW);
		}
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__AUTHOR, oldAuthor, author));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCarnotVersion() {
		return carnotVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCarnotVersion(String newCarnotVersion) {
		String oldCarnotVersion = carnotVersion;
		carnotVersion = newCarnotVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__CARNOT_VERSION, oldCarnotVersion, carnotVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(String newCreated) {
		String oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__CREATED, oldCreated, created));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getModelOID() {
		return modelOID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelOID(int newModelOID) {
		int oldModelOID = modelOID;
		modelOID = newModelOID;
		boolean oldModelOIDESet = modelOIDESet;
		modelOIDESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__MODEL_OID, oldModelOID, modelOID, !oldModelOIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetModelOID() {
		int oldModelOID = modelOID;
		boolean oldModelOIDESet = modelOIDESet;
		modelOID = MODEL_OID_EDEFAULT;
		modelOIDESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.MODEL_TYPE__MODEL_OID, oldModelOID, MODEL_OID_EDEFAULT, oldModelOIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetModelOID() {
		return modelOIDESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getOid() {
		return oid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOid(long newOid) {
		long oldOid = oid;
		oid = newOid;
		boolean oldOidESet = oidESet;
		oidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__OID, oldOid, oid, !oldOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOid() {
		long oldOid = oid;
		boolean oldOidESet = oidESet;
		oid = OID_EDEFAULT;
		oidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.MODEL_TYPE__OID, oldOid, OID_EDEFAULT, oldOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOid() {
		return oidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.MODEL_TYPE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.MODEL_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case CarnotPackage.MODEL_TYPE__DATA_TYPE:
				return ((InternalEList<?>)getDataType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__APPLICATION_TYPE:
				return ((InternalEList<?>)getApplicationType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
				return ((InternalEList<?>)getApplicationContextType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__TRIGGER_TYPE:
				return ((InternalEList<?>)getTriggerType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
				return ((InternalEList<?>)getEventConditionType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
				return ((InternalEList<?>)getEventActionType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__APPLICATION:
				return ((InternalEList<?>)getApplication()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__MODELER:
				return ((InternalEList<?>)getModeler()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__QUALITY_CONTROL:
				return basicSetQualityControl(null, msgs);
			case CarnotPackage.MODEL_TYPE__ROLE:
				return ((InternalEList<?>)getRole()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__ORGANIZATION:
				return ((InternalEList<?>)getOrganization()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
				return ((InternalEList<?>)getConditionalPerformer()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION:
				return ((InternalEList<?>)getProcessDefinition()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
				return basicSetExternalPackages(null, msgs);
			case CarnotPackage.MODEL_TYPE__SCRIPT:
				return basicSetScript(null, msgs);
			case CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS:
				return basicSetTypeDeclarations(null, msgs);
			case CarnotPackage.MODEL_TYPE__DIAGRAM:
				return ((InternalEList<?>)getDiagram()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__LINK_TYPE:
				return ((InternalEList<?>)getLinkType()).basicRemove(otherEnd, msgs);
			case CarnotPackage.MODEL_TYPE__VIEW:
				return ((InternalEList<?>)getView()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.MODEL_TYPE__ID:
				return getId();
			case CarnotPackage.MODEL_TYPE__NAME:
				return getName();
			case CarnotPackage.MODEL_TYPE__ATTRIBUTE:
				return getAttribute();
			case CarnotPackage.MODEL_TYPE__DESCRIPTION:
				return getDescription();
			case CarnotPackage.MODEL_TYPE__DATA_TYPE:
				return getDataType();
			case CarnotPackage.MODEL_TYPE__APPLICATION_TYPE:
				return getApplicationType();
			case CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
				return getApplicationContextType();
			case CarnotPackage.MODEL_TYPE__TRIGGER_TYPE:
				return getTriggerType();
			case CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
				return getEventConditionType();
			case CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
				return getEventActionType();
			case CarnotPackage.MODEL_TYPE__DATA:
				return getData();
			case CarnotPackage.MODEL_TYPE__APPLICATION:
				return getApplication();
			case CarnotPackage.MODEL_TYPE__MODELER:
				return getModeler();
			case CarnotPackage.MODEL_TYPE__QUALITY_CONTROL:
				return getQualityControl();
			case CarnotPackage.MODEL_TYPE__ROLE:
				return getRole();
			case CarnotPackage.MODEL_TYPE__ORGANIZATION:
				return getOrganization();
			case CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
				return getConditionalPerformer();
			case CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION:
				return getProcessDefinition();
			case CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
				return getExternalPackages();
			case CarnotPackage.MODEL_TYPE__SCRIPT:
				return getScript();
			case CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS:
				return getTypeDeclarations();
			case CarnotPackage.MODEL_TYPE__DIAGRAM:
				return getDiagram();
			case CarnotPackage.MODEL_TYPE__LINK_TYPE:
				return getLinkType();
			case CarnotPackage.MODEL_TYPE__VIEW:
				return getView();
			case CarnotPackage.MODEL_TYPE__AUTHOR:
				return getAuthor();
			case CarnotPackage.MODEL_TYPE__CARNOT_VERSION:
				return getCarnotVersion();
			case CarnotPackage.MODEL_TYPE__CREATED:
				return getCreated();
			case CarnotPackage.MODEL_TYPE__MODEL_OID:
				return getModelOID();
			case CarnotPackage.MODEL_TYPE__OID:
				return getOid();
			case CarnotPackage.MODEL_TYPE__VENDOR:
				return getVendor();
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
			case CarnotPackage.MODEL_TYPE__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection<? extends AttributeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__DATA_TYPE:
				getDataType().clear();
				getDataType().addAll((Collection<? extends DataTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION_TYPE:
				getApplicationType().clear();
				getApplicationType().addAll((Collection<? extends ApplicationTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
				getApplicationContextType().clear();
				getApplicationContextType().addAll((Collection<? extends ApplicationContextTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__TRIGGER_TYPE:
				getTriggerType().clear();
				getTriggerType().addAll((Collection<? extends TriggerTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
				getEventConditionType().clear();
				getEventConditionType().addAll((Collection<? extends EventConditionTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
				getEventActionType().clear();
				getEventActionType().addAll((Collection<? extends EventActionTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__DATA:
				getData().clear();
				getData().addAll((Collection<? extends DataType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION:
				getApplication().clear();
				getApplication().addAll((Collection<? extends ApplicationType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__MODELER:
				getModeler().clear();
				getModeler().addAll((Collection<? extends ModelerType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__QUALITY_CONTROL:
				setQualityControl((QualityControlType)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__ROLE:
				getRole().clear();
				getRole().addAll((Collection<? extends RoleType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__ORGANIZATION:
				getOrganization().clear();
				getOrganization().addAll((Collection<? extends OrganizationType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
				getConditionalPerformer().clear();
				getConditionalPerformer().addAll((Collection<? extends ConditionalPerformerType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION:
				getProcessDefinition().clear();
				getProcessDefinition().addAll((Collection<? extends ProcessDefinitionType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackages)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__DIAGRAM:
				getDiagram().clear();
				getDiagram().addAll((Collection<? extends DiagramType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__LINK_TYPE:
				getLinkType().clear();
				getLinkType().addAll((Collection<? extends LinkTypeType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__VIEW:
				getView().clear();
				getView().addAll((Collection<? extends ViewType>)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__AUTHOR:
				setAuthor((String)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__CARNOT_VERSION:
				setCarnotVersion((String)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__CREATED:
				setCreated((String)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__MODEL_OID:
				setModelOID((Integer)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__OID:
				setOid((Long)newValue);
				return;
			case CarnotPackage.MODEL_TYPE__VENDOR:
				setVendor((String)newValue);
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
			case CarnotPackage.MODEL_TYPE__ID:
				unsetId();
				return;
			case CarnotPackage.MODEL_TYPE__NAME:
				unsetName();
				return;
			case CarnotPackage.MODEL_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case CarnotPackage.MODEL_TYPE__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case CarnotPackage.MODEL_TYPE__DATA_TYPE:
				getDataType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION_TYPE:
				getApplicationType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
				getApplicationContextType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__TRIGGER_TYPE:
				getTriggerType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
				getEventConditionType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
				getEventActionType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__DATA:
				getData().clear();
				return;
			case CarnotPackage.MODEL_TYPE__APPLICATION:
				getApplication().clear();
				return;
			case CarnotPackage.MODEL_TYPE__MODELER:
				getModeler().clear();
				return;
			case CarnotPackage.MODEL_TYPE__QUALITY_CONTROL:
				setQualityControl((QualityControlType)null);
				return;
			case CarnotPackage.MODEL_TYPE__ROLE:
				getRole().clear();
				return;
			case CarnotPackage.MODEL_TYPE__ORGANIZATION:
				getOrganization().clear();
				return;
			case CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
				getConditionalPerformer().clear();
				return;
			case CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION:
				getProcessDefinition().clear();
				return;
			case CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackages)null);
				return;
			case CarnotPackage.MODEL_TYPE__SCRIPT:
				setScript((ScriptType)null);
				return;
			case CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)null);
				return;
			case CarnotPackage.MODEL_TYPE__DIAGRAM:
				getDiagram().clear();
				return;
			case CarnotPackage.MODEL_TYPE__LINK_TYPE:
				getLinkType().clear();
				return;
			case CarnotPackage.MODEL_TYPE__VIEW:
				getView().clear();
				return;
			case CarnotPackage.MODEL_TYPE__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case CarnotPackage.MODEL_TYPE__CARNOT_VERSION:
				setCarnotVersion(CARNOT_VERSION_EDEFAULT);
				return;
			case CarnotPackage.MODEL_TYPE__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case CarnotPackage.MODEL_TYPE__MODEL_OID:
				unsetModelOID();
				return;
			case CarnotPackage.MODEL_TYPE__OID:
				unsetOid();
				return;
			case CarnotPackage.MODEL_TYPE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
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
			case CarnotPackage.MODEL_TYPE__ID:
				return isSetId();
			case CarnotPackage.MODEL_TYPE__NAME:
				return isSetName();
			case CarnotPackage.MODEL_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case CarnotPackage.MODEL_TYPE__DESCRIPTION:
				return description != null;
			case CarnotPackage.MODEL_TYPE__DATA_TYPE:
				return dataType != null && !dataType.isEmpty();
			case CarnotPackage.MODEL_TYPE__APPLICATION_TYPE:
				return applicationType != null && !applicationType.isEmpty();
			case CarnotPackage.MODEL_TYPE__APPLICATION_CONTEXT_TYPE:
				return applicationContextType != null && !applicationContextType.isEmpty();
			case CarnotPackage.MODEL_TYPE__TRIGGER_TYPE:
				return triggerType != null && !triggerType.isEmpty();
			case CarnotPackage.MODEL_TYPE__EVENT_CONDITION_TYPE:
				return eventConditionType != null && !eventConditionType.isEmpty();
			case CarnotPackage.MODEL_TYPE__EVENT_ACTION_TYPE:
				return eventActionType != null && !eventActionType.isEmpty();
			case CarnotPackage.MODEL_TYPE__DATA:
				return data != null && !data.isEmpty();
			case CarnotPackage.MODEL_TYPE__APPLICATION:
				return application != null && !application.isEmpty();
			case CarnotPackage.MODEL_TYPE__MODELER:
				return modeler != null && !modeler.isEmpty();
			case CarnotPackage.MODEL_TYPE__QUALITY_CONTROL:
				return qualityControl != null;
			case CarnotPackage.MODEL_TYPE__ROLE:
				return role != null && !role.isEmpty();
			case CarnotPackage.MODEL_TYPE__ORGANIZATION:
				return organization != null && !organization.isEmpty();
			case CarnotPackage.MODEL_TYPE__CONDITIONAL_PERFORMER:
				return conditionalPerformer != null && !conditionalPerformer.isEmpty();
			case CarnotPackage.MODEL_TYPE__PROCESS_DEFINITION:
				return processDefinition != null && !processDefinition.isEmpty();
			case CarnotPackage.MODEL_TYPE__EXTERNAL_PACKAGES:
				return externalPackages != null;
			case CarnotPackage.MODEL_TYPE__SCRIPT:
				return script != null;
			case CarnotPackage.MODEL_TYPE__TYPE_DECLARATIONS:
				return typeDeclarations != null;
			case CarnotPackage.MODEL_TYPE__DIAGRAM:
				return diagram != null && !diagram.isEmpty();
			case CarnotPackage.MODEL_TYPE__LINK_TYPE:
				return linkType != null && !linkType.isEmpty();
			case CarnotPackage.MODEL_TYPE__VIEW:
				return view != null && !view.isEmpty();
			case CarnotPackage.MODEL_TYPE__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case CarnotPackage.MODEL_TYPE__CARNOT_VERSION:
				return CARNOT_VERSION_EDEFAULT == null ? carnotVersion != null : !CARNOT_VERSION_EDEFAULT.equals(carnotVersion);
			case CarnotPackage.MODEL_TYPE__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case CarnotPackage.MODEL_TYPE__MODEL_OID:
				return isSetModelOID();
			case CarnotPackage.MODEL_TYPE__OID:
				return isSetOid();
			case CarnotPackage.MODEL_TYPE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
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
		if (baseClass == IExtensibleElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.MODEL_TYPE__ATTRIBUTE: return CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
		if (baseClass == IExtensibleElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotPackage.MODEL_TYPE__ATTRIBUTE;
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		if (idESet) result.append(id); else result.append("<unset>");
		result.append(", name: ");
		if (nameESet) result.append(name); else result.append("<unset>");
		result.append(", author: ");
		result.append(author);
		result.append(", carnotVersion: ");
		result.append(carnotVersion);
		result.append(", created: ");
		result.append(created);
		result.append(", modelOID: ");
		if (modelOIDESet) result.append(modelOID); else result.append("<unset>");
		result.append(", oid: ");
		if (oidESet) result.append(oid); else result.append("<unset>");
		result.append(", vendor: ");
		result.append(vendor);
		result.append(')');
		return result.toString();
	}

} //ModelTypeImpl
