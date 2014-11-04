/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory
 * @model kind="package"
 * @generated
 */
public interface SdbpmnPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sdbpmn";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/stardust/model/bpmn2/sdbpmn";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sdbpmn";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SdbpmnPackage eINSTANCE = org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.DocumentRootImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Stardust Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_ATTRIBUTES = 5;

	/**
	 * The feature id for the '<em><b>Stardust Common</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_COMMON = 6;

	/**
	 * The feature id for the '<em><b>Stardust Data Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_DATA_OBJECT = 7;

	/**
	 * The feature id for the '<em><b>Stardust Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_DATA_STORE = 8;

	/**
	 * The feature id for the '<em><b>Stardust Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_INTERFACE = 9;

	/**
	 * The feature id for the '<em><b>Stardust Message Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT = 10;

	/**
	 * The feature id for the '<em><b>Stardust Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_MODEL = 11;

	/**
	 * The feature id for the '<em><b>Stardust Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_RESOURCE = 12;

	/**
	 * The feature id for the '<em><b>Stardust Seqence Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW = 13;

	/**
	 * The feature id for the '<em><b>Stardust Service Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_SERVICE_TASK = 14;

	/**
	 * The feature id for the '<em><b>Stardust Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_START_EVENT = 15;

	/**
	 * The feature id for the '<em><b>Stardust Subprocess</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_SUBPROCESS = 16;

	/**
	 * The feature id for the '<em><b>Stardust Timer Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT = 17;

	/**
	 * The feature id for the '<em><b>Stardust User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_USER_TASK = 18;

	/**
	 * The feature id for the '<em><b>Application Access Point Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF = 19;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__AUTHOR = 20;

	/**
	 * The feature id for the '<em><b>Carnot Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CARNOT_VERSION = 21;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CREATED = 22;

	/**
	 * The feature id for the '<em><b>Interactive Application Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF = 23;

	/**
	 * The feature id for the '<em><b>Model OID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODEL_OID = 24;

	/**
	 * The feature id for the '<em><b>Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OID = 25;

	/**
	 * The feature id for the '<em><b>Parameter Mapping Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARAMETER_MAPPING_OID = 26;

	/**
	 * The feature id for the '<em><b>Stardust Property Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STARDUST_PROPERTY_ID = 27;

	/**
	 * The feature id for the '<em><b>Synthetic Item Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYNTHETIC_ITEM_DEFINITION = 28;

	/**
	 * The feature id for the '<em><b>Synthetic Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYNTHETIC_PROPERTY = 29;

	/**
	 * The feature id for the '<em><b>Trigger Access Point Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF = 30;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VENDOR = 31;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAccessPointTypeImpl <em>Stardust Access Point Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAccessPointTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustAccessPointType()
	 * @generated
	 */
	int STARDUST_ACCESS_POINT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__ELEMENT_OID = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__ID = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__NAME = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__ATTRIBUTE = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__DESCRIPTION = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__DIRECTION = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__DIRECTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__TYPE = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Type Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE__TYPE_REF = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stardust Access Point Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ACCESS_POINT_TYPE_FEATURE_COUNT = CarnotWorkflowModelPackage.ACCESS_POINT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustApplicationTypeImpl <em>Stardust Application Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustApplicationTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustApplicationType()
	 * @generated
	 */
	int STARDUST_APPLICATION_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__ELEMENT_OID = CarnotWorkflowModelPackage.APPLICATION_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__ID = CarnotWorkflowModelPackage.APPLICATION_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__NAME = CarnotWorkflowModelPackage.APPLICATION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__ATTRIBUTE = CarnotWorkflowModelPackage.APPLICATION_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__DESCRIPTION = CarnotWorkflowModelPackage.APPLICATION_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__ACCESS_POINT = CarnotWorkflowModelPackage.APPLICATION_TYPE__ACCESS_POINT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__CONTEXT = CarnotWorkflowModelPackage.APPLICATION_TYPE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Interactive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__INTERACTIVE = CarnotWorkflowModelPackage.APPLICATION_TYPE__INTERACTIVE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__TYPE = CarnotWorkflowModelPackage.APPLICATION_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Executed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__EXECUTED_ACTIVITIES = CarnotWorkflowModelPackage.APPLICATION_TYPE__EXECUTED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Application Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__APPLICATION_SYMBOLS = CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Access Point1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__ACCESS_POINT1 = CarnotWorkflowModelPackage.APPLICATION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE__CONTEXT1 = CarnotWorkflowModelPackage.APPLICATION_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stardust Application Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_APPLICATION_TYPE_FEATURE_COUNT = CarnotWorkflowModelPackage.APPLICATION_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl <em>Stardust Attributes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustAttributesType()
	 * @generated
	 */
	int STARDUST_ATTRIBUTES_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Attribute Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Stardust Attributes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_ATTRIBUTES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustContextTypeImpl <em>Stardust Context Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustContextTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustContextType()
	 * @generated
	 */
	int STARDUST_CONTEXT_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__ELEMENT_OID = CarnotWorkflowModelPackage.CONTEXT_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__ATTRIBUTE = CarnotWorkflowModelPackage.CONTEXT_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__ACCESS_POINT = CarnotWorkflowModelPackage.CONTEXT_TYPE__ACCESS_POINT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__DESCRIPTION = CarnotWorkflowModelPackage.CONTEXT_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__TYPE = CarnotWorkflowModelPackage.CONTEXT_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Access Point1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__ACCESS_POINT1 = CarnotWorkflowModelPackage.CONTEXT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE__TYPE_REF = CarnotWorkflowModelPackage.CONTEXT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stardust Context Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_CONTEXT_TYPE_FEATURE_COUNT = CarnotWorkflowModelPackage.CONTEXT_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl <em>Stardust Data Object Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustDataObjectType()
	 * @generated
	 */
	int STARDUST_DATA_OBJECT_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_OBJECT_TYPE__PREDEFINED = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_OBJECT_TYPE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Stardust Data Object Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_OBJECT_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataStoreTypeImpl <em>Stardust Data Store Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataStoreTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustDataStoreType()
	 * @generated
	 */
	int STARDUST_DATA_STORE_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_STORE_TYPE__STARDUST_ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_STORE_TYPE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Stardust Data Store Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_DATA_STORE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl <em>Stardust Interface Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustInterfaceType()
	 * @generated
	 */
	int STARDUST_INTERFACE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Stardust Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION = 0;

	/**
	 * The feature id for the '<em><b>Stardust Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER = 1;

	/**
	 * The feature id for the '<em><b>Application Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_INTERFACE_TYPE__APPLICATION_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_INTERFACE_TYPE__ID = 3;

	/**
	 * The number of structural features of the '<em>Stardust Interface Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_INTERFACE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl <em>Stardust Message Start Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustMessageStartEventType()
	 * @generated
	 */
	int STARDUST_MESSAGE_START_EVENT_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES = 0;

	/**
	 * The number of structural features of the '<em>Stardust Message Start Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MESSAGE_START_EVENT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl <em>Stardust Model Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustModelType()
	 * @generated
	 */
	int STARDUST_MODEL_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__AUTHOR = 0;

	/**
	 * The feature id for the '<em><b>Carnot Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__CARNOT_VERSION = 1;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__CREATED = 2;

	/**
	 * The feature id for the '<em><b>Model OID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__MODEL_OID = 3;

	/**
	 * The feature id for the '<em><b>Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__OID = 4;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE__VENDOR = 5;

	/**
	 * The number of structural features of the '<em>Stardust Model Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_MODEL_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl <em>Stardust Resource Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustResourceType()
	 * @generated
	 */
	int STARDUST_RESOURCE_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Stardust Conditional Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER = 0;

	/**
	 * The feature id for the '<em><b>Stardust Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_RESOURCE_TYPE__STARDUST_ROLE = 1;

	/**
	 * The feature id for the '<em><b>Stardust Organization</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION = 2;

	/**
	 * The feature id for the '<em><b>Data Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_RESOURCE_TYPE__DATA_ID = 3;

	/**
	 * The number of structural features of the '<em>Stardust Resource Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_RESOURCE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl <em>Stardust Seqence Flow Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustSeqenceFlowType()
	 * @generated
	 */
	int STARDUST_SEQENCE_FLOW_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Fork On Traversal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL = 0;

	/**
	 * The number of structural features of the '<em>Stardust Seqence Flow Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SEQENCE_FLOW_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustCommonImpl <em>TStardust Common</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustCommonImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getTStardustCommon()
	 * @generated
	 */
	int TSTARDUST_COMMON = 19;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_COMMON__ELEMENT_OID = 0;

	/**
	 * The number of structural features of the '<em>TStardust Common</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_COMMON_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl <em>TStardust Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getTStardustActivity()
	 * @generated
	 */
	int TSTARDUST_ACTIVITY = 18;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_ACTIVITY__ELEMENT_OID = TSTARDUST_COMMON__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_ACTIVITY__EVENT_HANDLER = TSTARDUST_COMMON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION = TSTARDUST_COMMON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TStardust Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTARDUST_ACTIVITY_FEATURE_COUNT = TSTARDUST_COMMON_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl <em>Stardust Service Task Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustServiceTaskType()
	 * @generated
	 */
	int STARDUST_SERVICE_TASK_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SERVICE_TASK_TYPE__ELEMENT_OID = TSTARDUST_ACTIVITY__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SERVICE_TASK_TYPE__EVENT_HANDLER = TSTARDUST_ACTIVITY__EVENT_HANDLER;

	/**
	 * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SERVICE_TASK_TYPE__HIBERNATE_ON_CREATION = TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION;

	/**
	 * The number of structural features of the '<em>Stardust Service Task Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SERVICE_TASK_TYPE_FEATURE_COUNT = TSTARDUST_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl <em>Stardust Start Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustStartEventType()
	 * @generated
	 */
	int STARDUST_START_EVENT_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES = 0;

	/**
	 * The number of structural features of the '<em>Stardust Start Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_START_EVENT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSubprocessTypeImpl <em>Stardust Subprocess Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSubprocessTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustSubprocessType()
	 * @generated
	 */
	int STARDUST_SUBPROCESS_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SUBPROCESS_TYPE__ELEMENT_OID = TSTARDUST_ACTIVITY__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SUBPROCESS_TYPE__EVENT_HANDLER = TSTARDUST_ACTIVITY__EVENT_HANDLER;

	/**
	 * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SUBPROCESS_TYPE__HIBERNATE_ON_CREATION = TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION;

	/**
	 * The feature id for the '<em><b>Implementation Process</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS = TSTARDUST_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stardust Subprocess Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_SUBPROCESS_TYPE_FEATURE_COUNT = TSTARDUST_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTimerStartEventTypeImpl <em>Stardust Timer Start Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTimerStartEventTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustTimerStartEventType()
	 * @generated
	 */
	int STARDUST_TIMER_START_EVENT_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TIMER_START_EVENT_TYPE__STARDUST_ATTRIBUTES = 0;

	/**
	 * The number of structural features of the '<em>Stardust Timer Start Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TIMER_START_EVENT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl <em>Stardust Trigger Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustTriggerType()
	 * @generated
	 */
	int STARDUST_TRIGGER_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__ELEMENT_OID = CarnotWorkflowModelPackage.TRIGGER_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__ID = CarnotWorkflowModelPackage.TRIGGER_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__NAME = CarnotWorkflowModelPackage.TRIGGER_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__ATTRIBUTE = CarnotWorkflowModelPackage.TRIGGER_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__DESCRIPTION = CarnotWorkflowModelPackage.TRIGGER_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__ACCESS_POINT = CarnotWorkflowModelPackage.TRIGGER_TYPE__ACCESS_POINT;

	/**
	 * The feature id for the '<em><b>Parameter Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__PARAMETER_MAPPING = CarnotWorkflowModelPackage.TRIGGER_TYPE__PARAMETER_MAPPING;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__TYPE = CarnotWorkflowModelPackage.TRIGGER_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Starting Event Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__STARTING_EVENT_SYMBOLS = CarnotWorkflowModelPackage.TRIGGER_TYPE__STARTING_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Access Point1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__ACCESS_POINT1 = CarnotWorkflowModelPackage.TRIGGER_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE__CONTEXT = CarnotWorkflowModelPackage.TRIGGER_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stardust Trigger Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_TRIGGER_TYPE_FEATURE_COUNT = CarnotWorkflowModelPackage.TRIGGER_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl <em>Stardust User Task Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustUserTaskType()
	 * @generated
	 */
	int STARDUST_USER_TASK_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE__ELEMENT_OID = TSTARDUST_ACTIVITY__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE__EVENT_HANDLER = TSTARDUST_ACTIVITY__EVENT_HANDLER;

	/**
	 * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE__HIBERNATE_ON_CREATION = TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION;

	/**
	 * The feature id for the '<em><b>Allows Abort By Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER = TSTARDUST_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interactive Application Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF = TSTARDUST_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stardust User Task Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARDUST_USER_TASK_TYPE_FEATURE_COUNT = TSTARDUST_ACTIVITY_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getDataType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustActivity <em>Stardust Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Activity</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustAttributes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustAttributes();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustCommon <em>Stardust Common</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Common</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustCommon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustCommon();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataObject <em>Stardust Data Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Data Object</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataObject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustDataObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataStore <em>Stardust Data Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Data Store</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataStore()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustDataStore();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustInterface <em>Stardust Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Interface</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustInterface()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustInterface();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustMessageStartEvent <em>Stardust Message Start Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Message Start Event</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustMessageStartEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustMessageStartEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustModel <em>Stardust Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Model</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustModel()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustResource <em>Stardust Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Resource</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustResource()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustResource();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSeqenceFlow <em>Stardust Seqence Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Seqence Flow</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSeqenceFlow()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustSeqenceFlow();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustServiceTask <em>Stardust Service Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Service Task</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustServiceTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustServiceTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustStartEvent <em>Stardust Start Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Start Event</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustStartEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustStartEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSubprocess <em>Stardust Subprocess</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Subprocess</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSubprocess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustSubprocess();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustTimerStartEvent <em>Stardust Timer Start Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Timer Start Event</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustTimerStartEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustTimerStartEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustUserTask <em>Stardust User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust User Task</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustUserTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StardustUserTask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getApplicationAccessPointRef <em>Application Access Point Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Access Point Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getApplicationAccessPointRef()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ApplicationAccessPointRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getAuthor()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Author();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCarnotVersion <em>Carnot Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Carnot Version</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCarnotVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CarnotVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCreated()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Created();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getInteractiveApplicationRef <em>Interactive Application Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interactive Application Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getInteractiveApplicationRef()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_InteractiveApplicationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelOID <em>Model OID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model OID</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelOID()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ModelOID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid <em>Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Oid</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Oid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getParameterMappingOid <em>Parameter Mapping Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Mapping Oid</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getParameterMappingOid()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ParameterMappingOid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustPropertyId <em>Stardust Property Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stardust Property Id</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustPropertyId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_StardustPropertyId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition <em>Synthetic Item Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synthetic Item Definition</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_SyntheticItemDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty <em>Synthetic Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synthetic Property</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_SyntheticProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getTriggerAccessPointRef <em>Trigger Access Point Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger Access Point Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getTriggerAccessPointRef()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_TriggerAccessPointRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getVendor()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Vendor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType <em>Stardust Access Point Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Access Point Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType
	 * @generated
	 */
	EClass getStardustAccessPointType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType#getTypeRef <em>Type Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType#getTypeRef()
	 * @see #getStardustAccessPointType()
	 * @generated
	 */
	EAttribute getStardustAccessPointType_TypeRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType <em>Stardust Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Application Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType
	 * @generated
	 */
	EClass getStardustApplicationType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getAccessPoint1 <em>Access Point1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Access Point1</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getAccessPoint1()
	 * @see #getStardustApplicationType()
	 * @generated
	 */
	EReference getStardustApplicationType_AccessPoint1();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getContext1 <em>Context1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context1</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getContext1()
	 * @see #getStardustApplicationType()
	 * @generated
	 */
	EReference getStardustApplicationType_Context1();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType <em>Stardust Attributes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Attributes Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType
	 * @generated
	 */
	EClass getStardustAttributesType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType#getAttributeType <em>Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType#getAttributeType()
	 * @see #getStardustAttributesType()
	 * @generated
	 */
	EReference getStardustAttributesType_AttributeType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType <em>Stardust Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Context Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType
	 * @generated
	 */
	EClass getStardustContextType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getAccessPoint1 <em>Access Point1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Access Point1</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getAccessPoint1()
	 * @see #getStardustContextType()
	 * @generated
	 */
	EReference getStardustContextType_AccessPoint1();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getTypeRef <em>Type Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getTypeRef()
	 * @see #getStardustContextType()
	 * @generated
	 */
	EAttribute getStardustContextType_TypeRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType <em>Stardust Data Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Data Object Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType
	 * @generated
	 */
	EClass getStardustDataObjectType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getStardustAttributes()
	 * @see #getStardustDataObjectType()
	 * @generated
	 */
	EReference getStardustDataObjectType_StardustAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined <em>Predefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predefined</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined()
	 * @see #getStardustDataObjectType()
	 * @generated
	 */
	EAttribute getStardustDataObjectType_Predefined();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getType()
	 * @see #getStardustDataObjectType()
	 * @generated
	 */
	EAttribute getStardustDataObjectType_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType <em>Stardust Data Store Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Data Store Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType
	 * @generated
	 */
	EClass getStardustDataStoreType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType#getStardustAttributes()
	 * @see #getStardustDataStoreType()
	 * @generated
	 */
	EReference getStardustDataStoreType_StardustAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType#getType()
	 * @see #getStardustDataStoreType()
	 * @generated
	 */
	EAttribute getStardustDataStoreType_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType <em>Stardust Interface Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Interface Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType
	 * @generated
	 */
	EClass getStardustInterfaceType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustApplication <em>Stardust Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Application</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustApplication()
	 * @see #getStardustInterfaceType()
	 * @generated
	 */
	EReference getStardustInterfaceType_StardustApplication();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustTrigger <em>Stardust Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Trigger</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getStardustTrigger()
	 * @see #getStardustInterfaceType()
	 * @generated
	 */
	EReference getStardustInterfaceType_StardustTrigger();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getApplicationType()
	 * @see #getStardustInterfaceType()
	 * @generated
	 */
	EAttribute getStardustInterfaceType_ApplicationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType#getId()
	 * @see #getStardustInterfaceType()
	 * @generated
	 */
	EAttribute getStardustInterfaceType_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType <em>Stardust Message Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Message Start Event Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType
	 * @generated
	 */
	EClass getStardustMessageStartEventType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getStardustAttributes()
	 * @see #getStardustMessageStartEventType()
	 * @generated
	 */
	EReference getStardustMessageStartEventType_StardustAttributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType <em>Stardust Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Model Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType
	 * @generated
	 */
	EClass getStardustModelType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getAuthor()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_Author();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getCarnotVersion <em>Carnot Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Carnot Version</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getCarnotVersion()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_CarnotVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getCreated()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_Created();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getModelOID <em>Model OID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model OID</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getModelOID()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_ModelOID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getOid <em>Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Oid</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getOid()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_Oid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType#getVendor()
	 * @see #getStardustModelType()
	 * @generated
	 */
	EAttribute getStardustModelType_Vendor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType <em>Stardust Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Resource Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType
	 * @generated
	 */
	EClass getStardustResourceType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustConditionalPerformer <em>Stardust Conditional Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Conditional Performer</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustConditionalPerformer()
	 * @see #getStardustResourceType()
	 * @generated
	 */
	EReference getStardustResourceType_StardustConditionalPerformer();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustRole <em>Stardust Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Role</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustRole()
	 * @see #getStardustResourceType()
	 * @generated
	 */
	EReference getStardustResourceType_StardustRole();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustOrganization <em>Stardust Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Organization</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getStardustOrganization()
	 * @see #getStardustResourceType()
	 * @generated
	 */
	EReference getStardustResourceType_StardustOrganization();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getDataId <em>Data Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Id</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType#getDataId()
	 * @see #getStardustResourceType()
	 * @generated
	 */
	EAttribute getStardustResourceType_DataId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType <em>Stardust Seqence Flow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Seqence Flow Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType
	 * @generated
	 */
	EClass getStardustSeqenceFlowType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal <em>Fork On Traversal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fork On Traversal</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal()
	 * @see #getStardustSeqenceFlowType()
	 * @generated
	 */
	EAttribute getStardustSeqenceFlowType_ForkOnTraversal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType <em>Stardust Service Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Service Task Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType
	 * @generated
	 */
	EClass getStardustServiceTaskType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType <em>Stardust Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Start Event Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType
	 * @generated
	 */
	EClass getStardustStartEventType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType#getStardustAttributes()
	 * @see #getStardustStartEventType()
	 * @generated
	 */
	EReference getStardustStartEventType_StardustAttributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType <em>Stardust Subprocess Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Subprocess Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType
	 * @generated
	 */
	EClass getStardustSubprocessType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType#getImplementationProcess <em>Implementation Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Process</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType#getImplementationProcess()
	 * @see #getStardustSubprocessType()
	 * @generated
	 */
	EAttribute getStardustSubprocessType_ImplementationProcess();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType <em>Stardust Timer Start Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Timer Start Event Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType
	 * @generated
	 */
	EClass getStardustTimerStartEventType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType#getStardustAttributes <em>Stardust Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stardust Attributes</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType#getStardustAttributes()
	 * @see #getStardustTimerStartEventType()
	 * @generated
	 */
	EReference getStardustTimerStartEventType_StardustAttributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType <em>Stardust Trigger Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust Trigger Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType
	 * @generated
	 */
	EClass getStardustTriggerType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType#getAccessPoint1 <em>Access Point1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Access Point1</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType#getAccessPoint1()
	 * @see #getStardustTriggerType()
	 * @generated
	 */
	EReference getStardustTriggerType_AccessPoint1();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType#getContext()
	 * @see #getStardustTriggerType()
	 * @generated
	 */
	EReference getStardustTriggerType_Context();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType <em>Stardust User Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stardust User Task Type</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType
	 * @generated
	 */
	EClass getStardustUserTaskType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allows Abort By Performer</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer()
	 * @see #getStardustUserTaskType()
	 * @generated
	 */
	EAttribute getStardustUserTaskType_AllowsAbortByPerformer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#getInteractiveApplicationRef <em>Interactive Application Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interactive Application Ref</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#getInteractiveApplicationRef()
	 * @see #getStardustUserTaskType()
	 * @generated
	 */
	EAttribute getStardustUserTaskType_InteractiveApplicationRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity <em>TStardust Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TStardust Activity</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity
	 * @generated
	 */
	EClass getTStardustActivity();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#getEventHandler <em>Event Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Handler</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#getEventHandler()
	 * @see #getTStardustActivity()
	 * @generated
	 */
	EReference getTStardustActivity_EventHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation <em>Hibernate On Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hibernate On Creation</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation()
	 * @see #getTStardustActivity()
	 * @generated
	 */
	EAttribute getTStardustActivity_HibernateOnCreation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon <em>TStardust Common</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TStardust Common</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon
	 * @generated
	 */
	EClass getTStardustCommon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon#getElementOid <em>Element Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Oid</em>'.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon#getElementOid()
	 * @see #getTStardustCommon()
	 * @generated
	 */
	EAttribute getTStardustCommon_ElementOid();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SdbpmnFactory getSdbpmnFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.DocumentRootImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_TYPE = eINSTANCE.getDocumentRoot_DataType();

		/**
		 * The meta object literal for the '<em><b>Stardust Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_ACTIVITY = eINSTANCE.getDocumentRoot_StardustActivity();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_ATTRIBUTES = eINSTANCE.getDocumentRoot_StardustAttributes();

		/**
		 * The meta object literal for the '<em><b>Stardust Common</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_COMMON = eINSTANCE.getDocumentRoot_StardustCommon();

		/**
		 * The meta object literal for the '<em><b>Stardust Data Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_DATA_OBJECT = eINSTANCE.getDocumentRoot_StardustDataObject();

		/**
		 * The meta object literal for the '<em><b>Stardust Data Store</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_DATA_STORE = eINSTANCE.getDocumentRoot_StardustDataStore();

		/**
		 * The meta object literal for the '<em><b>Stardust Interface</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_INTERFACE = eINSTANCE.getDocumentRoot_StardustInterface();

		/**
		 * The meta object literal for the '<em><b>Stardust Message Start Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT = eINSTANCE.getDocumentRoot_StardustMessageStartEvent();

		/**
		 * The meta object literal for the '<em><b>Stardust Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_MODEL = eINSTANCE.getDocumentRoot_StardustModel();

		/**
		 * The meta object literal for the '<em><b>Stardust Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_RESOURCE = eINSTANCE.getDocumentRoot_StardustResource();

		/**
		 * The meta object literal for the '<em><b>Stardust Seqence Flow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW = eINSTANCE.getDocumentRoot_StardustSeqenceFlow();

		/**
		 * The meta object literal for the '<em><b>Stardust Service Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_SERVICE_TASK = eINSTANCE.getDocumentRoot_StardustServiceTask();

		/**
		 * The meta object literal for the '<em><b>Stardust Start Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_START_EVENT = eINSTANCE.getDocumentRoot_StardustStartEvent();

		/**
		 * The meta object literal for the '<em><b>Stardust Subprocess</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_SUBPROCESS = eINSTANCE.getDocumentRoot_StardustSubprocess();

		/**
		 * The meta object literal for the '<em><b>Stardust Timer Start Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT = eINSTANCE.getDocumentRoot_StardustTimerStartEvent();

		/**
		 * The meta object literal for the '<em><b>Stardust User Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STARDUST_USER_TASK = eINSTANCE.getDocumentRoot_StardustUserTask();

		/**
		 * The meta object literal for the '<em><b>Application Access Point Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF = eINSTANCE.getDocumentRoot_ApplicationAccessPointRef();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__AUTHOR = eINSTANCE.getDocumentRoot_Author();

		/**
		 * The meta object literal for the '<em><b>Carnot Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CARNOT_VERSION = eINSTANCE.getDocumentRoot_CarnotVersion();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CREATED = eINSTANCE.getDocumentRoot_Created();

		/**
		 * The meta object literal for the '<em><b>Interactive Application Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF = eINSTANCE.getDocumentRoot_InteractiveApplicationRef();

		/**
		 * The meta object literal for the '<em><b>Model OID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MODEL_OID = eINSTANCE.getDocumentRoot_ModelOID();

		/**
		 * The meta object literal for the '<em><b>Oid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__OID = eINSTANCE.getDocumentRoot_Oid();

		/**
		 * The meta object literal for the '<em><b>Parameter Mapping Oid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__PARAMETER_MAPPING_OID = eINSTANCE.getDocumentRoot_ParameterMappingOid();

		/**
		 * The meta object literal for the '<em><b>Stardust Property Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__STARDUST_PROPERTY_ID = eINSTANCE.getDocumentRoot_StardustPropertyId();

		/**
		 * The meta object literal for the '<em><b>Synthetic Item Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SYNTHETIC_ITEM_DEFINITION = eINSTANCE.getDocumentRoot_SyntheticItemDefinition();

		/**
		 * The meta object literal for the '<em><b>Synthetic Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SYNTHETIC_PROPERTY = eINSTANCE.getDocumentRoot_SyntheticProperty();

		/**
		 * The meta object literal for the '<em><b>Trigger Access Point Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF = eINSTANCE.getDocumentRoot_TriggerAccessPointRef();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VENDOR = eINSTANCE.getDocumentRoot_Vendor();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAccessPointTypeImpl <em>Stardust Access Point Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAccessPointTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustAccessPointType()
		 * @generated
		 */
		EClass STARDUST_ACCESS_POINT_TYPE = eINSTANCE.getStardustAccessPointType();

		/**
		 * The meta object literal for the '<em><b>Type Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_ACCESS_POINT_TYPE__TYPE_REF = eINSTANCE.getStardustAccessPointType_TypeRef();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustApplicationTypeImpl <em>Stardust Application Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustApplicationTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustApplicationType()
		 * @generated
		 */
		EClass STARDUST_APPLICATION_TYPE = eINSTANCE.getStardustApplicationType();

		/**
		 * The meta object literal for the '<em><b>Access Point1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_APPLICATION_TYPE__ACCESS_POINT1 = eINSTANCE.getStardustApplicationType_AccessPoint1();

		/**
		 * The meta object literal for the '<em><b>Context1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_APPLICATION_TYPE__CONTEXT1 = eINSTANCE.getStardustApplicationType_Context1();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl <em>Stardust Attributes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustAttributesType()
		 * @generated
		 */
		EClass STARDUST_ATTRIBUTES_TYPE = eINSTANCE.getStardustAttributesType();

		/**
		 * The meta object literal for the '<em><b>Attribute Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE = eINSTANCE.getStardustAttributesType_AttributeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustContextTypeImpl <em>Stardust Context Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustContextTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustContextType()
		 * @generated
		 */
		EClass STARDUST_CONTEXT_TYPE = eINSTANCE.getStardustContextType();

		/**
		 * The meta object literal for the '<em><b>Access Point1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_CONTEXT_TYPE__ACCESS_POINT1 = eINSTANCE.getStardustContextType_AccessPoint1();

		/**
		 * The meta object literal for the '<em><b>Type Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_CONTEXT_TYPE__TYPE_REF = eINSTANCE.getStardustContextType_TypeRef();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl <em>Stardust Data Object Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataObjectTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustDataObjectType()
		 * @generated
		 */
		EClass STARDUST_DATA_OBJECT_TYPE = eINSTANCE.getStardustDataObjectType();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_DATA_OBJECT_TYPE__STARDUST_ATTRIBUTES = eINSTANCE.getStardustDataObjectType_StardustAttributes();

		/**
		 * The meta object literal for the '<em><b>Predefined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_DATA_OBJECT_TYPE__PREDEFINED = eINSTANCE.getStardustDataObjectType_Predefined();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_DATA_OBJECT_TYPE__TYPE = eINSTANCE.getStardustDataObjectType_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataStoreTypeImpl <em>Stardust Data Store Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustDataStoreTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustDataStoreType()
		 * @generated
		 */
		EClass STARDUST_DATA_STORE_TYPE = eINSTANCE.getStardustDataStoreType();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_DATA_STORE_TYPE__STARDUST_ATTRIBUTES = eINSTANCE.getStardustDataStoreType_StardustAttributes();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_DATA_STORE_TYPE__TYPE = eINSTANCE.getStardustDataStoreType_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl <em>Stardust Interface Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustInterfaceTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustInterfaceType()
		 * @generated
		 */
		EClass STARDUST_INTERFACE_TYPE = eINSTANCE.getStardustInterfaceType();

		/**
		 * The meta object literal for the '<em><b>Stardust Application</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION = eINSTANCE.getStardustInterfaceType_StardustApplication();

		/**
		 * The meta object literal for the '<em><b>Stardust Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_INTERFACE_TYPE__STARDUST_TRIGGER = eINSTANCE.getStardustInterfaceType_StardustTrigger();

		/**
		 * The meta object literal for the '<em><b>Application Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_INTERFACE_TYPE__APPLICATION_TYPE = eINSTANCE.getStardustInterfaceType_ApplicationType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_INTERFACE_TYPE__ID = eINSTANCE.getStardustInterfaceType_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl <em>Stardust Message Start Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustMessageStartEventType()
		 * @generated
		 */
		EClass STARDUST_MESSAGE_START_EVENT_TYPE = eINSTANCE.getStardustMessageStartEventType();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES = eINSTANCE.getStardustMessageStartEventType_StardustAttributes();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl <em>Stardust Model Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustModelType()
		 * @generated
		 */
		EClass STARDUST_MODEL_TYPE = eINSTANCE.getStardustModelType();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__AUTHOR = eINSTANCE.getStardustModelType_Author();

		/**
		 * The meta object literal for the '<em><b>Carnot Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__CARNOT_VERSION = eINSTANCE.getStardustModelType_CarnotVersion();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__CREATED = eINSTANCE.getStardustModelType_Created();

		/**
		 * The meta object literal for the '<em><b>Model OID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__MODEL_OID = eINSTANCE.getStardustModelType_ModelOID();

		/**
		 * The meta object literal for the '<em><b>Oid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__OID = eINSTANCE.getStardustModelType_Oid();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_MODEL_TYPE__VENDOR = eINSTANCE.getStardustModelType_Vendor();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl <em>Stardust Resource Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustResourceTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustResourceType()
		 * @generated
		 */
		EClass STARDUST_RESOURCE_TYPE = eINSTANCE.getStardustResourceType();

		/**
		 * The meta object literal for the '<em><b>Stardust Conditional Performer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER = eINSTANCE.getStardustResourceType_StardustConditionalPerformer();

		/**
		 * The meta object literal for the '<em><b>Stardust Role</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_RESOURCE_TYPE__STARDUST_ROLE = eINSTANCE.getStardustResourceType_StardustRole();

		/**
		 * The meta object literal for the '<em><b>Stardust Organization</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION = eINSTANCE.getStardustResourceType_StardustOrganization();

		/**
		 * The meta object literal for the '<em><b>Data Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_RESOURCE_TYPE__DATA_ID = eINSTANCE.getStardustResourceType_DataId();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl <em>Stardust Seqence Flow Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustSeqenceFlowType()
		 * @generated
		 */
		EClass STARDUST_SEQENCE_FLOW_TYPE = eINSTANCE.getStardustSeqenceFlowType();

		/**
		 * The meta object literal for the '<em><b>Fork On Traversal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL = eINSTANCE.getStardustSeqenceFlowType_ForkOnTraversal();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl <em>Stardust Service Task Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustServiceTaskType()
		 * @generated
		 */
		EClass STARDUST_SERVICE_TASK_TYPE = eINSTANCE.getStardustServiceTaskType();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl <em>Stardust Start Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustStartEventType()
		 * @generated
		 */
		EClass STARDUST_START_EVENT_TYPE = eINSTANCE.getStardustStartEventType();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES = eINSTANCE.getStardustStartEventType_StardustAttributes();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSubprocessTypeImpl <em>Stardust Subprocess Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSubprocessTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustSubprocessType()
		 * @generated
		 */
		EClass STARDUST_SUBPROCESS_TYPE = eINSTANCE.getStardustSubprocessType();

		/**
		 * The meta object literal for the '<em><b>Implementation Process</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS = eINSTANCE.getStardustSubprocessType_ImplementationProcess();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTimerStartEventTypeImpl <em>Stardust Timer Start Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTimerStartEventTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustTimerStartEventType()
		 * @generated
		 */
		EClass STARDUST_TIMER_START_EVENT_TYPE = eINSTANCE.getStardustTimerStartEventType();

		/**
		 * The meta object literal for the '<em><b>Stardust Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_TIMER_START_EVENT_TYPE__STARDUST_ATTRIBUTES = eINSTANCE.getStardustTimerStartEventType_StardustAttributes();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl <em>Stardust Trigger Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustTriggerTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustTriggerType()
		 * @generated
		 */
		EClass STARDUST_TRIGGER_TYPE = eINSTANCE.getStardustTriggerType();

		/**
		 * The meta object literal for the '<em><b>Access Point1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_TRIGGER_TYPE__ACCESS_POINT1 = eINSTANCE.getStardustTriggerType_AccessPoint1();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARDUST_TRIGGER_TYPE__CONTEXT = eINSTANCE.getStardustTriggerType_Context();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl <em>Stardust User Task Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustUserTaskType()
		 * @generated
		 */
		EClass STARDUST_USER_TASK_TYPE = eINSTANCE.getStardustUserTaskType();

		/**
		 * The meta object literal for the '<em><b>Allows Abort By Performer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER = eINSTANCE.getStardustUserTaskType_AllowsAbortByPerformer();

		/**
		 * The meta object literal for the '<em><b>Interactive Application Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF = eINSTANCE.getStardustUserTaskType_InteractiveApplicationRef();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl <em>TStardust Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustActivityImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getTStardustActivity()
		 * @generated
		 */
		EClass TSTARDUST_ACTIVITY = eINSTANCE.getTStardustActivity();

		/**
		 * The meta object literal for the '<em><b>Event Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSTARDUST_ACTIVITY__EVENT_HANDLER = eINSTANCE.getTStardustActivity_EventHandler();

		/**
		 * The meta object literal for the '<em><b>Hibernate On Creation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION = eINSTANCE.getTStardustActivity_HibernateOnCreation();

		/**
		 * The meta object literal for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustCommonImpl <em>TStardust Common</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.TStardustCommonImpl
		 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getTStardustCommon()
		 * @generated
		 */
		EClass TSTARDUST_COMMON = eINSTANCE.getTStardustCommon();

		/**
		 * The meta object literal for the '<em><b>Element Oid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTARDUST_COMMON__ELEMENT_OID = eINSTANCE.getTStardustCommon_ElementOid();

	}

} //SdbpmnPackage
