/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotFactory
 * @model kind="package"
 * @generated
 */
public interface CarnotPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "carnot";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.carnot.ag/workflowmodel/3.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "carnot";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CarnotPackage eINSTANCE = org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CoordinatesImpl <em>Coordinates</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CoordinatesImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getCoordinates()
	 * @generated
	 */
	int COORDINATES = 0;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATES__XPOS = 0;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATES__YPOS = 1;

	/**
	 * The number of structural features of the '<em>Coordinates</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Coordinates</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement <em>IIdentifiable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIIdentifiableElement()
	 * @generated
	 */
	int IIDENTIFIABLE_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_ELEMENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_ELEMENT__NAME = 1;

	/**
	 * The number of structural features of the '<em>IIdentifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>IIdentifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IExtensibleElementImpl <em>IExtensible Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IExtensibleElementImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIExtensibleElement()
	 * @generated
	 */
	int IEXTENSIBLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXTENSIBLE_ELEMENT__ATTRIBUTE = 0;

	/**
	 * The number of structural features of the '<em>IExtensible Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXTENSIBLE_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IExtensible Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXTENSIBLE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdentifiableReferenceImpl <em>Identifiable Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdentifiableReferenceImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIdentifiableReference()
	 * @generated
	 */
	int IDENTIFIABLE_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_REFERENCE__ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Identifiable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_REFERENCE__IDENTIFIABLE = 1;

	/**
	 * The number of structural features of the '<em>Identifiable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Identifiable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement <em>IModel Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIModelElement()
	 * @generated
	 */
	int IMODEL_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT__ELEMENT_OID = 0;

	/**
	 * The number of structural features of the '<em>IModel Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IModel Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement <em>IIdentifiable Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIIdentifiableModelElement()
	 * @generated
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT__ID = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT__NAME = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>IIdentifiable Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>IIdentifiable Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner <em>IEvent Handler Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIEventHandlerOwner()
	 * @generated
	 */
	int IEVENT_HANDLER_OWNER = 6;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEVENT_HANDLER_OWNER__EVENT_HANDLER = 0;

	/**
	 * The number of structural features of the '<em>IEvent Handler Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEVENT_HANDLER_OWNER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IEvent Handler Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEVENT_HANDLER_OWNER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner <em>IAccess Point Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIAccessPointOwner()
	 * @generated
	 */
	int IACCESS_POINT_OWNER = 7;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACCESS_POINT_OWNER__ACCESS_POINT = 0;

	/**
	 * The number of structural features of the '<em>IAccess Point Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACCESS_POINT_OWNER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IAccess Point Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACCESS_POINT_OWNER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType <em>IMeta Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIMetaType()
	 * @generated
	 */
	int IMETA_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE__IS_PREDEFINED = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IMeta Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE___GET_EXTENSION_POINT_ID = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE___GET_TYPED_ELEMENTS = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>IMeta Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMETA_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement <em>ITyped Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getITypedElement()
	 * @generated
	 */
	int ITYPED_ELEMENT = 9;

	/**
	 * The number of structural features of the '<em>ITyped Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPED_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPED_ELEMENT___GET_META_TYPE = 0;

	/**
	 * The number of operations of the '<em>ITyped Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPED_ELEMENT_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl <em>ISymbol Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getISymbolContainer()
	 * @generated
	 */
	int ISYMBOL_CONTAINER = 10;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__ATTRIBUTE = IEXTENSIBLE_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__NODES = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__ACTIVITY_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__ANNOTATION_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__APPLICATION_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__DATA_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__END_EVENT_SYMBOLS = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__GATEWAY_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Group Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__GROUP_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__MODELER_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__PROCESS_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Role Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__ROLE_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__START_EVENT_SYMBOLS = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Text Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__TEXT_SYMBOL = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__CONNECTIONS = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__PART_OF_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Performs Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__PERFORMS_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__TRIGGERS_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__REFERS_TO_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Transition Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__TRANSITION_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Works For Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The number of structural features of the '<em>ISymbol Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER_FEATURE_COUNT = IEXTENSIBLE_ELEMENT_FEATURE_COUNT + 29;

	/**
	 * The operation id for the '<em>Get Node Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES = IEXTENSIBLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Connection Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES = IEXTENSIBLE_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>ISymbol Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISYMBOL_CONTAINER_OPERATION_COUNT = IEXTENSIBLE_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject <em>IGraphical Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIGraphicalObject()
	 * @generated
	 */
	int IGRAPHICAL_OBJECT = 11;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__BORDER_COLOR = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__FILL_COLOR = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__STYLE = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>IGraphical Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>IGraphical Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IGRAPHICAL_OBJECT_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol <em>INode Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getINodeSymbol()
	 * @generated
	 */
	int INODE_SYMBOL = 12;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__ELEMENT_OID = IGRAPHICAL_OBJECT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__BORDER_COLOR = IGRAPHICAL_OBJECT__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__FILL_COLOR = IGRAPHICAL_OBJECT__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__STYLE = IGRAPHICAL_OBJECT__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__REFERING_TO_CONNECTIONS = IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__REFERING_FROM_CONNECTIONS = IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__XPOS = IGRAPHICAL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__YPOS = IGRAPHICAL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__WIDTH = IGRAPHICAL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__HEIGHT = IGRAPHICAL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__SHAPE = IGRAPHICAL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__IN_LINKS = IGRAPHICAL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL__OUT_LINKS = IGRAPHICAL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>INode Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL_FEATURE_COUNT = IGRAPHICAL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL___GET_IN_CONNECTION_FEATURES = IGRAPHICAL_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES = IGRAPHICAL_OBJECT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>INode Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INODE_SYMBOL_OPERATION_COUNT = IGRAPHICAL_OBJECT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol <em>ISwimlane Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getISwimlaneSymbol()
	 * @generated
	 */
	int ISWIMLANE_SYMBOL = 13;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__ELEMENT_OID = INODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__BORDER_COLOR = INODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__FILL_COLOR = INODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__STYLE = INODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__REFERING_TO_CONNECTIONS = INODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__REFERING_FROM_CONNECTIONS = INODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__XPOS = INODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__YPOS = INODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__WIDTH = INODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__HEIGHT = INODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__SHAPE = INODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__IN_LINKS = INODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__OUT_LINKS = INODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__ID = INODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__NAME = INODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__ORIENTATION = INODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__COLLAPSED = INODE_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__PARTICIPANT = INODE_SYMBOL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Child Lanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__CHILD_LANES = INODE_SYMBOL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Participant Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL__PARTICIPANT_REFERENCE = INODE_SYMBOL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>ISwimlane Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL_FEATURE_COUNT = INODE_SYMBOL_FEATURE_COUNT + 7;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL___GET_IN_CONNECTION_FEATURES = INODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL___GET_OUT_CONNECTION_FEATURES = INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The number of operations of the '<em>ISwimlane Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISWIMLANE_SYMBOL_OPERATION_COUNT = INODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl <em>IModel Element Node Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIModelElementNodeSymbol()
	 * @generated
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL = 14;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID = INODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR = INODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR = INODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__STYLE = INODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS = INODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS = INODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__XPOS = INODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__YPOS = INODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__WIDTH = INODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT = INODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__SHAPE = INODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS = INODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS = INODE_SYMBOL__OUT_LINKS;

	/**
	 * The number of structural features of the '<em>IModel Element Node Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT = INODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES = INODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES = INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT = INODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = INODE_SYMBOL_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>IModel Element Node Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT = INODE_SYMBOL_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IFlowObjectSymbolImpl <em>IFlow Object Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IFlowObjectSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIFlowObjectSymbol()
	 * @generated
	 */
	int IFLOW_OBJECT_SYMBOL = 15;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__ELEMENT_OID = INODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__BORDER_COLOR = INODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__FILL_COLOR = INODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__STYLE = INODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS = INODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS = INODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__XPOS = INODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__YPOS = INODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__WIDTH = INODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__HEIGHT = INODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__SHAPE = INODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__IN_LINKS = INODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__OUT_LINKS = INODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS = INODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS = INODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IFlow Object Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL_FEATURE_COUNT = INODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL___GET_IN_CONNECTION_FEATURES = INODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL___GET_OUT_CONNECTION_FEATURES = INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The number of operations of the '<em>IFlow Object Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFLOW_OBJECT_SYMBOL_OPERATION_COUNT = INODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol <em>IConnection Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIConnectionSymbol()
	 * @generated
	 */
	int ICONNECTION_SYMBOL = 16;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__ELEMENT_OID = IGRAPHICAL_OBJECT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__BORDER_COLOR = IGRAPHICAL_OBJECT__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__FILL_COLOR = IGRAPHICAL_OBJECT__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__STYLE = IGRAPHICAL_OBJECT__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS = IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS = IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__SOURCE_ANCHOR = IGRAPHICAL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__TARGET_ANCHOR = IGRAPHICAL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__ROUTING = IGRAPHICAL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL__COORDINATES = IGRAPHICAL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>IConnection Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL_FEATURE_COUNT = IGRAPHICAL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL___GET_SOURCE_NODE = IGRAPHICAL_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL = IGRAPHICAL_OBJECT_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL___GET_TARGET_NODE = IGRAPHICAL_OBJECT_OPERATION_COUNT + 2;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL = IGRAPHICAL_OBJECT_OPERATION_COUNT + 3;

	/**
	 * The number of operations of the '<em>IConnection Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTION_SYMBOL_OPERATION_COUNT = IGRAPHICAL_OBJECT_OPERATION_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant <em>IModel Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIModelParticipant()
	 * @generated
	 */
	int IMODEL_PARTICIPANT = 17;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Performed Swimlanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__PERFORMED_SWIMLANES = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Participant Associations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IModel Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>IModel Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol <em>IModel Participant Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIModelParticipantSymbol()
	 * @generated
	 */
	int IMODEL_PARTICIPANT_SYMBOL = 18;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__ELEMENT_OID = IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__BORDER_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__FILL_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__STYLE = IMODEL_ELEMENT_NODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__XPOS = IMODEL_ELEMENT_NODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__YPOS = IMODEL_ELEMENT_NODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__WIDTH = IMODEL_ELEMENT_NODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__HEIGHT = IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__SHAPE = IMODEL_ELEMENT_NODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__IN_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__OUT_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__PERFORMED_ACTIVITIES = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Triggered Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL__TRIGGERED_EVENTS = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IModel Participant Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL___GET_IN_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL___GET_OUT_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL___GET_MODEL_ELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>IModel Participant Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMODEL_PARTICIPANT_SYMBOL_OPERATION_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AbstractEventActionImpl <em>Abstract Event Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AbstractEventActionImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getAbstractEventAction()
	 * @generated
	 */
	int ABSTRACT_EVENT_ACTION = 19;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Event Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Abstract Event Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_ACTION_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AbstractEventSymbolImpl <em>Abstract Event Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AbstractEventSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getAbstractEventSymbol()
	 * @generated
	 */
	int ABSTRACT_EVENT_SYMBOL = 20;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__ELEMENT_OID = IFLOW_OBJECT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__BORDER_COLOR = IFLOW_OBJECT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__FILL_COLOR = IFLOW_OBJECT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__STYLE = IFLOW_OBJECT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__REFERING_TO_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__XPOS = IFLOW_OBJECT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__YPOS = IFLOW_OBJECT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__WIDTH = IFLOW_OBJECT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__HEIGHT = IFLOW_OBJECT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__SHAPE = IFLOW_OBJECT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__IN_LINKS = IFLOW_OBJECT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__OUT_LINKS = IFLOW_OBJECT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__IN_TRANSITIONS = IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__OUT_TRANSITIONS = IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL__LABEL = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL___GET_MODEL_ELEMENT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EVENT_SYMBOL_OPERATION_COUNT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AccessPointTypeImpl <em>Access Point Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AccessPointTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getAccessPointType()
	 * @generated
	 */
	int ACCESS_POINT_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__DIRECTION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Access Point Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Access Point Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCESS_POINT_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ActivitySymbolTypeImpl <em>Activity Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ActivitySymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getActivitySymbolType()
	 * @generated
	 */
	int ACTIVITY_SYMBOL_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__ELEMENT_OID = IFLOW_OBJECT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__BORDER_COLOR = IFLOW_OBJECT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__FILL_COLOR = IFLOW_OBJECT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__STYLE = IFLOW_OBJECT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__XPOS = IFLOW_OBJECT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__YPOS = IFLOW_OBJECT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__WIDTH = IFLOW_OBJECT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__HEIGHT = IFLOW_OBJECT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__SHAPE = IFLOW_OBJECT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__IN_LINKS = IFLOW_OBJECT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__OUT_LINKS = IFLOW_OBJECT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__IN_TRANSITIONS = IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__OUT_TRANSITIONS = IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__ACTIVITY = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Performs Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Executed By Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Gateway Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Activity Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE_FEATURE_COUNT = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE___GET_MODEL_ELEMENT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Activity Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SYMBOL_TYPE_OPERATION_COUNT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ActivityTypeImpl <em>Activity Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ActivityTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getActivityType()
	 * @generated
	 */
	int ACTIVITY_TYPE = 23;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__EVENT_HANDLER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__DATA_MAPPING = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Allows Abort By Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ALLOWS_ABORT_BY_PERFORMER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Application</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__APPLICATION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__HIBERNATE_ON_CREATION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__IMPLEMENTATION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Implementation Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__IMPLEMENTATION_PROCESS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Join</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__JOIN = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Loop Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__LOOP_CONDITION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Loop Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__LOOP_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Performer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__PERFORMER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Quality Control Performer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__QUALITY_CONTROL_PERFORMER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Split</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__SPLIT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Sub Process Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__SUB_PROCESS_MODE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Activity Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ACTIVITY_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Starting Event Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__IN_TRANSITIONS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__OUT_TRANSITIONS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>External Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__EXTERNAL_REF = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Valid Quality Codes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__VALID_QUALITY_CODES = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The number of structural features of the '<em>Activity Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Activity Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AnnotationSymbolTypeImpl <em>Annotation Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AnnotationSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getAnnotationSymbolType()
	 * @generated
	 */
	int ANNOTATION_SYMBOL_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__ELEMENT_OID = INODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__BORDER_COLOR = INODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__FILL_COLOR = INODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__STYLE = INODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = INODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = INODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__XPOS = INODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__YPOS = INODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__WIDTH = INODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__HEIGHT = INODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__SHAPE = INODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__IN_LINKS = INODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__OUT_LINKS = INODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE__TEXT = INODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Annotation Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE_FEATURE_COUNT = INODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = INODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The number of operations of the '<em>Annotation Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_SYMBOL_TYPE_OPERATION_COUNT = INODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl <em>Application Context Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationContextTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getApplicationContextTypeType()
	 * @generated
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Access Point Provider Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Has Application Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__HAS_APPLICATION_PATH = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Has Mapping Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__HAS_MAPPING_ID = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Validator Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__VALIDATOR_CLASS = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE__CONTEXTS = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Application Context Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Application Context Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CONTEXT_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationSymbolTypeImpl <em>Application Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getApplicationSymbolType()
	 * @generated
	 */
	int APPLICATION_SYMBOL_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__ELEMENT_OID = IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__BORDER_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__FILL_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__STYLE = IMODEL_ELEMENT_NODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__XPOS = IMODEL_ELEMENT_NODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__YPOS = IMODEL_ELEMENT_NODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__WIDTH = IMODEL_ELEMENT_NODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__HEIGHT = IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__SHAPE = IMODEL_ELEMENT_NODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__IN_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__OUT_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Executing Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Application</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE__APPLICATION = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Application Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Application Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationTypeImpl <em>Application Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getApplicationType()
	 * @generated
	 */
	int APPLICATION_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ACCESS_POINT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__CONTEXT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Interactive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__INTERACTIVE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Executed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__EXECUTED_ACTIVITIES = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Application Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__APPLICATION_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Application Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Application Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationTypeTypeImpl <em>Application Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ApplicationTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getApplicationTypeType()
	 * @generated
	 */
	int APPLICATION_TYPE_TYPE = 28;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Access Point Provider Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__ACCESS_POINT_PROVIDER_CLASS = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__INSTANCE_CLASS = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__SYNCHRONOUS = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Validator Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__VALIDATOR_CLASS = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE__APPLICATIONS = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Application Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Application Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AttributeTypeImpl <em>Attribute Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.AttributeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getAttributeType()
	 * @generated
	 */
	int ATTRIBUTE_TYPE = 29;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ANY = 2;

	/**
	 * The feature id for the '<em><b>Value Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__VALUE_NODE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__NAME = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__TYPE = 5;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__VALUE = 6;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__REFERENCE = 7;

	/**
	 * The number of structural features of the '<em>Attribute Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE_FEATURE_COUNT = 8;

	/**
	 * The operation id for the '<em>Get Attribute Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE___GET_ATTRIBUTE_VALUE = 0;

	/**
	 * The operation id for the '<em>Set Attribute Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE___SET_ATTRIBUTE_VALUE__STRING_STRING = 1;

	/**
	 * The number of operations of the '<em>Attribute Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.BindActionTypeImpl <em>Bind Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.BindActionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getBindActionType()
	 * @generated
	 */
	int BIND_ACTION_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__ELEMENT_OID = ABSTRACT_EVENT_ACTION__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__ID = ABSTRACT_EVENT_ACTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__NAME = ABSTRACT_EVENT_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__ATTRIBUTE = ABSTRACT_EVENT_ACTION__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__DESCRIPTION = ABSTRACT_EVENT_ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE__TYPE = ABSTRACT_EVENT_ACTION__TYPE;

	/**
	 * The number of structural features of the '<em>Bind Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE_FEATURE_COUNT = ABSTRACT_EVENT_ACTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE___GET_SYMBOLS = ABSTRACT_EVENT_ACTION___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE___GET_META_TYPE = ABSTRACT_EVENT_ACTION___GET_META_TYPE;

	/**
	 * The number of operations of the '<em>Bind Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIND_ACTION_TYPE_OPERATION_COUNT = ABSTRACT_EVENT_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CodeImpl <em>Code</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CodeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getCode()
	 * @generated
	 */
	int CODE = 31;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE__CODE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE__NAME = 2;

	/**
	 * The number of structural features of the '<em>Code</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Code</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerSymbolTypeImpl <em>Conditional Performer Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getConditionalPerformerSymbolType()
	 * @generated
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE = 32;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__BORDER_COLOR = IMODEL_PARTICIPANT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__FILL_COLOR = IMODEL_PARTICIPANT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__STYLE = IMODEL_PARTICIPANT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__XPOS = IMODEL_PARTICIPANT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__YPOS = IMODEL_PARTICIPANT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__WIDTH = IMODEL_PARTICIPANT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__HEIGHT = IMODEL_PARTICIPANT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__SHAPE = IMODEL_PARTICIPANT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__IN_LINKS = IMODEL_PARTICIPANT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__OUT_LINKS = IMODEL_PARTICIPANT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT_SYMBOL__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Triggered Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__TRIGGERED_EVENTS = IMODEL_PARTICIPANT_SYMBOL__TRIGGERED_EVENTS;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE__PARTICIPANT = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conditional Performer Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_PARTICIPANT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_PARTICIPANT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Conditional Performer Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl <em>Conditional Performer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ConditionalPerformerTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getConditionalPerformerType()
	 * @generated
	 */
	int CONDITIONAL_PERFORMER_TYPE = 33;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__ID = IMODEL_PARTICIPANT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__NAME = IMODEL_PARTICIPANT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__ATTRIBUTE = IMODEL_PARTICIPANT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__DESCRIPTION = IMODEL_PARTICIPANT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Performed Swimlanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__PERFORMED_SWIMLANES = IMODEL_PARTICIPANT__PERFORMED_SWIMLANES;

	/**
	 * The feature id for the '<em><b>Participant Associations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__PARTICIPANT_ASSOCIATIONS = IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__DATA = IMODEL_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__DATA_PATH = IMODEL_PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__IS_USER = IMODEL_PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE__CONDITIONAL_PERFORMER_SYMBOLS = IMODEL_PARTICIPANT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Conditional Performer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE___GET_SYMBOLS = IMODEL_PARTICIPANT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Conditional Performer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PERFORMER_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl <em>Context Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ContextTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getContextType()
	 * @generated
	 */
	int CONTEXT_TYPE = 34;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE__ATTRIBUTE = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE__ACCESS_POINT = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE__DESCRIPTION = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE__TYPE = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Context Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE___GET_META_TYPE = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Context Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataMappingConnectionTypeImpl <em>Data Mapping Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataMappingConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataMappingConnectionType()
	 * @generated
	 */
	int DATA_MAPPING_CONNECTION_TYPE = 35;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__ACTIVITY_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE__DATA_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Mapping Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Data Mapping Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataMappingTypeImpl <em>Data Mapping Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataMappingTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataMappingType()
	 * @generated
	 */
	int DATA_MAPPING_TYPE = 36;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__ID = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__NAME = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Application Access Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Application Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__APPLICATION_PATH = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__CONTEXT = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__DATA = IMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__DATA_PATH = IMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE__DIRECTION = IMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Data Mapping Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Data Mapping Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MAPPING_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataPathTypeImpl <em>Data Path Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataPathTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataPathType()
	 * @generated
	 */
	int DATA_PATH_TYPE = 37;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__DATA = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__DATA_PATH = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Descriptor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__DESCRIPTOR = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__KEY = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE__DIRECTION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Data Path Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Data Path Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PATH_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataSymbolTypeImpl <em>Data Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataSymbolType()
	 * @generated
	 */
	int DATA_SYMBOL_TYPE = 38;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__ELEMENT_OID = IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__BORDER_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__FILL_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__STYLE = IMODEL_ELEMENT_NODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__XPOS = IMODEL_ELEMENT_NODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__YPOS = IMODEL_ELEMENT_NODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__WIDTH = IMODEL_ELEMENT_NODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__HEIGHT = IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__SHAPE = IMODEL_ELEMENT_NODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__IN_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__OUT_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__DATA = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE__DATA_MAPPINGS = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Data Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataTypeImpl <em>Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataType()
	 * @generated
	 */
	int DATA_TYPE = 39;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Data Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DATA_MAPPINGS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__PREDEFINED = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DATA_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Conditional Performers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__CONDITIONAL_PERFORMERS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Data Paths</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DATA_PATHS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parameter Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__PARAMETER_MAPPINGS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__EXTERNAL_REFERENCE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataTypeTypeImpl <em>Data Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DataTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDataTypeType()
	 * @generated
	 */
	int DATA_TYPE_TYPE = 40;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Access Path Editor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ACCESS_PATH_EDITOR = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Evaluator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__EVALUATOR = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Instance Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__INSTANCE_CLASS = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__READABLE = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Storage Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__STORAGE_STRATEGY = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Validator Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__VALIDATOR_CLASS = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Value Creator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__VALUE_CREATOR = IMETA_TYPE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__WRITABLE = IMETA_TYPE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__DATA = IMETA_TYPE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Data Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 10;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Data Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DescriptionTypeImpl <em>Description Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DescriptionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDescriptionType()
	 * @generated
	 */
	int DESCRIPTION_TYPE = 41;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE__MIXED = 0;

	/**
	 * The number of structural features of the '<em>Description Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Description Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DiagramTypeImpl <em>Diagram Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DiagramTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDiagramType()
	 * @generated
	 */
	int DIAGRAM_TYPE = 42;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ATTRIBUTE = ISYMBOL_CONTAINER__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__NODES = ISYMBOL_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ACTIVITY_SYMBOL = ISYMBOL_CONTAINER__ACTIVITY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ANNOTATION_SYMBOL = ISYMBOL_CONTAINER__ANNOTATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__APPLICATION_SYMBOL = ISYMBOL_CONTAINER__APPLICATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__CONDITIONAL_PERFORMER_SYMBOL = ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__DATA_SYMBOL = ISYMBOL_CONTAINER__DATA_SYMBOL;

	/**
	 * The feature id for the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__END_EVENT_SYMBOLS = ISYMBOL_CONTAINER__END_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__GATEWAY_SYMBOL = ISYMBOL_CONTAINER__GATEWAY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Group Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__GROUP_SYMBOL = ISYMBOL_CONTAINER__GROUP_SYMBOL;

	/**
	 * The feature id for the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__INTERMEDIATE_EVENT_SYMBOLS = ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__MODELER_SYMBOL = ISYMBOL_CONTAINER__MODELER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ORGANIZATION_SYMBOL = ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__PROCESS_SYMBOL = ISYMBOL_CONTAINER__PROCESS_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__PROCESS_INTERFACE_SYMBOLS = ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Role Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ROLE_SYMBOL = ISYMBOL_CONTAINER__ROLE_SYMBOL;

	/**
	 * The feature id for the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__START_EVENT_SYMBOLS = ISYMBOL_CONTAINER__START_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Text Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__TEXT_SYMBOL = ISYMBOL_CONTAINER__TEXT_SYMBOL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__CONNECTIONS = ISYMBOL_CONTAINER__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__DATA_MAPPING_CONNECTION = ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION;

	/**
	 * The feature id for the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__EXECUTED_BY_CONNECTION = ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION;

	/**
	 * The feature id for the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__GENERIC_LINK_CONNECTION = ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION;

	/**
	 * The feature id for the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__PART_OF_CONNECTION = ISYMBOL_CONTAINER__PART_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Performs Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__PERFORMS_CONNECTION = ISYMBOL_CONTAINER__PERFORMS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__TRIGGERS_CONNECTION = ISYMBOL_CONTAINER__TRIGGERS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__REFERS_TO_CONNECTION = ISYMBOL_CONTAINER__REFERS_TO_CONNECTION;

	/**
	 * The feature id for the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__SUB_PROCESS_OF_CONNECTION = ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Transition Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__TRANSITION_CONNECTION = ISYMBOL_CONTAINER__TRANSITION_CONNECTION;

	/**
	 * The feature id for the '<em><b>Works For Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__WORKS_FOR_CONNECTION = ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION;

	/**
	 * The feature id for the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__TEAM_LEAD_CONNECTION = ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ELEMENT_OID = ISYMBOL_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__NAME = ISYMBOL_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pool Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__POOL_SYMBOLS = ISYMBOL_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__ORIENTATION = ISYMBOL_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE__MODE = ISYMBOL_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Diagram Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE_FEATURE_COUNT = ISYMBOL_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get Node Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE___GET_NODE_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get Connection Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE___GET_CONNECTION_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES;

	/**
	 * The number of operations of the '<em>Diagram Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_TYPE_OPERATION_COUNT = ISYMBOL_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.DocumentRootImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 43;

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
	 * The feature id for the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODEL = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EndEventSymbolImpl <em>End Event Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EndEventSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getEndEventSymbol()
	 * @generated
	 */
	int END_EVENT_SYMBOL = 44;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__ELEMENT_OID = ABSTRACT_EVENT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__BORDER_COLOR = ABSTRACT_EVENT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__FILL_COLOR = ABSTRACT_EVENT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__STYLE = ABSTRACT_EVENT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__REFERING_TO_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__XPOS = ABSTRACT_EVENT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__YPOS = ABSTRACT_EVENT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__WIDTH = ABSTRACT_EVENT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__HEIGHT = ABSTRACT_EVENT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__SHAPE = ABSTRACT_EVENT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__IN_LINKS = ABSTRACT_EVENT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__OUT_LINKS = ABSTRACT_EVENT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__IN_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__OUT_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL__LABEL = ABSTRACT_EVENT_SYMBOL__LABEL;

	/**
	 * The number of structural features of the '<em>End Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL_FEATURE_COUNT = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL___GET_MODEL_ELEMENT = ABSTRACT_EVENT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = ABSTRACT_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>End Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_EVENT_SYMBOL_OPERATION_COUNT = ABSTRACT_EVENT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventActionTypeImpl <em>Event Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventActionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getEventActionType()
	 * @generated
	 */
	int EVENT_ACTION_TYPE = 45;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__ELEMENT_OID = ABSTRACT_EVENT_ACTION__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__ID = ABSTRACT_EVENT_ACTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__NAME = ABSTRACT_EVENT_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__ATTRIBUTE = ABSTRACT_EVENT_ACTION__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__DESCRIPTION = ABSTRACT_EVENT_ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE__TYPE = ABSTRACT_EVENT_ACTION__TYPE;

	/**
	 * The number of structural features of the '<em>Event Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_FEATURE_COUNT = ABSTRACT_EVENT_ACTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE___GET_SYMBOLS = ABSTRACT_EVENT_ACTION___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE___GET_META_TYPE = ABSTRACT_EVENT_ACTION___GET_META_TYPE;

	/**
	 * The number of operations of the '<em>Event Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_OPERATION_COUNT = ABSTRACT_EVENT_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventActionTypeTypeImpl <em>Event Action Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventActionTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getEventActionTypeType()
	 * @generated
	 */
	int EVENT_ACTION_TYPE_TYPE = 46;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Action Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ACTION_CLASS = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ACTIVITY_ACTION = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Process Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__PROCESS_ACTION = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Supported Condition Types</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__SUPPORTED_CONDITION_TYPES = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unsupported Contexts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__UNSUPPORTED_CONTEXTS = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Action Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE__ACTION_INSTANCES = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Event Action Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 7;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Event Action Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_ACTION_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventConditionTypeTypeImpl <em>Event Condition Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventConditionTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getEventConditionTypeType()
	 * @generated
	 */
	int EVENT_CONDITION_TYPE_TYPE = 47;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Activity Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__ACTIVITY_CONDITION = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binder Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__BINDER_CLASS = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__IMPLEMENTATION = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Process Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__PROCESS_CONDITION = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Pull Event Emitter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__PULL_EVENT_EMITTER_CLASS = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__RULE = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE__EVENT_HANDLERS = IMETA_TYPE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Event Condition Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 8;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Event Condition Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl <em>Event Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.EventHandlerTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getEventHandlerType()
	 * @generated
	 */
	int EVENT_HANDLER_TYPE = 48;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__ACCESS_POINT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bind Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__BIND_ACTION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Event Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__EVENT_ACTION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Unbind Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__UNBIND_ACTION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Auto Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__AUTO_BIND = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Consume On Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__CONSUME_ON_MATCH = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Log Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__LOG_HANDLER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Unbind On Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE__UNBIND_ON_MATCH = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Event Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Event Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ExecutedByConnectionTypeImpl <em>Executed By Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ExecutedByConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getExecutedByConnectionType()
	 * @generated
	 */
	int EXECUTED_BY_CONNECTION_TYPE = 49;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__ACTIVITY_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE__APPLICATION_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Executed By Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Executed By Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTED_BY_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdRefImpl <em>Id Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IdRefImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIdRef()
	 * @generated
	 */
	int ID_REF = 50;

	/**
	 * The feature id for the '<em><b>Package Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_REF__PACKAGE_REF = 0;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_REF__REF = 1;

	/**
	 * The number of structural features of the '<em>Id Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_REF_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Id Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_REF_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GatewaySymbolImpl <em>Gateway Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GatewaySymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getGatewaySymbol()
	 * @generated
	 */
	int GATEWAY_SYMBOL = 51;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__ELEMENT_OID = IFLOW_OBJECT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__BORDER_COLOR = IFLOW_OBJECT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__FILL_COLOR = IFLOW_OBJECT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__STYLE = IFLOW_OBJECT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__REFERING_TO_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__REFERING_FROM_CONNECTIONS = IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__XPOS = IFLOW_OBJECT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__YPOS = IFLOW_OBJECT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__WIDTH = IFLOW_OBJECT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__HEIGHT = IFLOW_OBJECT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__SHAPE = IFLOW_OBJECT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__IN_LINKS = IFLOW_OBJECT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__OUT_LINKS = IFLOW_OBJECT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__IN_TRANSITIONS = IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__OUT_TRANSITIONS = IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Flow Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__FLOW_KIND = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL__ACTIVITY_SYMBOL = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gateway Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL_FEATURE_COUNT = IFLOW_OBJECT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL___GET_IN_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL___GET_OUT_CONNECTION_FEATURES = IFLOW_OBJECT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The number of operations of the '<em>Gateway Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATEWAY_SYMBOL_OPERATION_COUNT = IFLOW_OBJECT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl <em>Generic Link Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getGenericLinkConnectionType()
	 * @generated
	 */
	int GENERIC_LINK_CONNECTION_TYPE = 52;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Link Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Generic Link Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE___GET_META_TYPE = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Link Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_LINK_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GroupSymbolTypeImpl <em>Group Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GroupSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getGroupSymbolType()
	 * @generated
	 */
	int GROUP_SYMBOL_TYPE = 53;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ATTRIBUTE = ISYMBOL_CONTAINER__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__NODES = ISYMBOL_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ACTIVITY_SYMBOL = ISYMBOL_CONTAINER__ACTIVITY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ANNOTATION_SYMBOL = ISYMBOL_CONTAINER__ANNOTATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__APPLICATION_SYMBOL = ISYMBOL_CONTAINER__APPLICATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__CONDITIONAL_PERFORMER_SYMBOL = ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__DATA_SYMBOL = ISYMBOL_CONTAINER__DATA_SYMBOL;

	/**
	 * The feature id for the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__END_EVENT_SYMBOLS = ISYMBOL_CONTAINER__END_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__GATEWAY_SYMBOL = ISYMBOL_CONTAINER__GATEWAY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Group Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__GROUP_SYMBOL = ISYMBOL_CONTAINER__GROUP_SYMBOL;

	/**
	 * The feature id for the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__INTERMEDIATE_EVENT_SYMBOLS = ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__MODELER_SYMBOL = ISYMBOL_CONTAINER__MODELER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ORGANIZATION_SYMBOL = ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__PROCESS_SYMBOL = ISYMBOL_CONTAINER__PROCESS_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__PROCESS_INTERFACE_SYMBOLS = ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Role Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ROLE_SYMBOL = ISYMBOL_CONTAINER__ROLE_SYMBOL;

	/**
	 * The feature id for the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__START_EVENT_SYMBOLS = ISYMBOL_CONTAINER__START_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Text Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__TEXT_SYMBOL = ISYMBOL_CONTAINER__TEXT_SYMBOL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__CONNECTIONS = ISYMBOL_CONTAINER__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__DATA_MAPPING_CONNECTION = ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION;

	/**
	 * The feature id for the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__EXECUTED_BY_CONNECTION = ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION;

	/**
	 * The feature id for the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__GENERIC_LINK_CONNECTION = ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION;

	/**
	 * The feature id for the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__PART_OF_CONNECTION = ISYMBOL_CONTAINER__PART_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Performs Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__PERFORMS_CONNECTION = ISYMBOL_CONTAINER__PERFORMS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__TRIGGERS_CONNECTION = ISYMBOL_CONTAINER__TRIGGERS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__REFERS_TO_CONNECTION = ISYMBOL_CONTAINER__REFERS_TO_CONNECTION;

	/**
	 * The feature id for the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__SUB_PROCESS_OF_CONNECTION = ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Transition Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__TRANSITION_CONNECTION = ISYMBOL_CONTAINER__TRANSITION_CONNECTION;

	/**
	 * The feature id for the '<em><b>Works For Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__WORKS_FOR_CONNECTION = ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION;

	/**
	 * The feature id for the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__TEAM_LEAD_CONNECTION = ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__ELEMENT_OID = ISYMBOL_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__BORDER_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__FILL_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__STYLE = ISYMBOL_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__XPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__YPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__WIDTH = ISYMBOL_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__HEIGHT = ISYMBOL_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__SHAPE = ISYMBOL_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__IN_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE__OUT_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Group Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE_FEATURE_COUNT = ISYMBOL_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The operation id for the '<em>Get Node Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE___GET_NODE_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get Connection Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE___GET_CONNECTION_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Group Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_SYMBOL_TYPE_OPERATION_COUNT = ISYMBOL_CONTAINER_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IntermediateEventSymbolImpl <em>Intermediate Event Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IntermediateEventSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getIntermediateEventSymbol()
	 * @generated
	 */
	int INTERMEDIATE_EVENT_SYMBOL = 54;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__ELEMENT_OID = ABSTRACT_EVENT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__BORDER_COLOR = ABSTRACT_EVENT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__FILL_COLOR = ABSTRACT_EVENT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__STYLE = ABSTRACT_EVENT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__REFERING_TO_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__XPOS = ABSTRACT_EVENT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__YPOS = ABSTRACT_EVENT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__WIDTH = ABSTRACT_EVENT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__HEIGHT = ABSTRACT_EVENT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__SHAPE = ABSTRACT_EVENT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__IN_LINKS = ABSTRACT_EVENT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__OUT_LINKS = ABSTRACT_EVENT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__IN_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__OUT_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL__LABEL = ABSTRACT_EVENT_SYMBOL__LABEL;

	/**
	 * The number of structural features of the '<em>Intermediate Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL_FEATURE_COUNT = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL___GET_MODEL_ELEMENT = ABSTRACT_EVENT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = ABSTRACT_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Intermediate Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_EVENT_SYMBOL_OPERATION_COUNT = ABSTRACT_EVENT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.LaneSymbolImpl <em>Lane Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.LaneSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLaneSymbol()
	 * @generated
	 */
	int LANE_SYMBOL = 55;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ATTRIBUTE = ISYMBOL_CONTAINER__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__NODES = ISYMBOL_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ACTIVITY_SYMBOL = ISYMBOL_CONTAINER__ACTIVITY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ANNOTATION_SYMBOL = ISYMBOL_CONTAINER__ANNOTATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__APPLICATION_SYMBOL = ISYMBOL_CONTAINER__APPLICATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__CONDITIONAL_PERFORMER_SYMBOL = ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__DATA_SYMBOL = ISYMBOL_CONTAINER__DATA_SYMBOL;

	/**
	 * The feature id for the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__END_EVENT_SYMBOLS = ISYMBOL_CONTAINER__END_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__GATEWAY_SYMBOL = ISYMBOL_CONTAINER__GATEWAY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Group Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__GROUP_SYMBOL = ISYMBOL_CONTAINER__GROUP_SYMBOL;

	/**
	 * The feature id for the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__INTERMEDIATE_EVENT_SYMBOLS = ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__MODELER_SYMBOL = ISYMBOL_CONTAINER__MODELER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ORGANIZATION_SYMBOL = ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PROCESS_SYMBOL = ISYMBOL_CONTAINER__PROCESS_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PROCESS_INTERFACE_SYMBOLS = ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Role Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ROLE_SYMBOL = ISYMBOL_CONTAINER__ROLE_SYMBOL;

	/**
	 * The feature id for the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__START_EVENT_SYMBOLS = ISYMBOL_CONTAINER__START_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Text Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__TEXT_SYMBOL = ISYMBOL_CONTAINER__TEXT_SYMBOL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__CONNECTIONS = ISYMBOL_CONTAINER__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__DATA_MAPPING_CONNECTION = ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION;

	/**
	 * The feature id for the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__EXECUTED_BY_CONNECTION = ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION;

	/**
	 * The feature id for the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__GENERIC_LINK_CONNECTION = ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION;

	/**
	 * The feature id for the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PART_OF_CONNECTION = ISYMBOL_CONTAINER__PART_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Performs Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PERFORMS_CONNECTION = ISYMBOL_CONTAINER__PERFORMS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__TRIGGERS_CONNECTION = ISYMBOL_CONTAINER__TRIGGERS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__REFERS_TO_CONNECTION = ISYMBOL_CONTAINER__REFERS_TO_CONNECTION;

	/**
	 * The feature id for the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__SUB_PROCESS_OF_CONNECTION = ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Transition Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__TRANSITION_CONNECTION = ISYMBOL_CONTAINER__TRANSITION_CONNECTION;

	/**
	 * The feature id for the '<em><b>Works For Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__WORKS_FOR_CONNECTION = ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION;

	/**
	 * The feature id for the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__TEAM_LEAD_CONNECTION = ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ELEMENT_OID = ISYMBOL_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__BORDER_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__FILL_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__STYLE = ISYMBOL_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__REFERING_TO_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__REFERING_FROM_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__XPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__YPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__WIDTH = ISYMBOL_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__HEIGHT = ISYMBOL_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__SHAPE = ISYMBOL_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__IN_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__OUT_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ID = ISYMBOL_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__NAME = ISYMBOL_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__ORIENTATION = ISYMBOL_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__COLLAPSED = ISYMBOL_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PARTICIPANT = ISYMBOL_CONTAINER_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Child Lanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__CHILD_LANES = ISYMBOL_CONTAINER_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Participant Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PARTICIPANT_REFERENCE = ISYMBOL_CONTAINER_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Parent Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PARENT_POOL = ISYMBOL_CONTAINER_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Parent Lane</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL__PARENT_LANE = ISYMBOL_CONTAINER_FEATURE_COUNT + 21;

	/**
	 * The number of structural features of the '<em>Lane Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL_FEATURE_COUNT = ISYMBOL_CONTAINER_FEATURE_COUNT + 22;

	/**
	 * The operation id for the '<em>Get Node Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL___GET_NODE_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get Connection Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL___GET_CONNECTION_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL___GET_IN_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL___GET_OUT_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Lane Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANE_SYMBOL_OPERATION_COUNT = ISYMBOL_CONTAINER_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.LinkTypeTypeImpl <em>Link Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.LinkTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkTypeType()
	 * @generated
	 */
	int LINK_TYPE_TYPE = 56;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Source Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SOURCE_ROLE = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SOURCE_CLASS = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SOURCE_CARDINALITY = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__TARGET_ROLE = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__TARGET_CLASS = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__TARGET_CARDINALITY = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__LINE_STYLE = IMETA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__LINE_COLOR = IMETA_TYPE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Source Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SOURCE_SYMBOL = IMETA_TYPE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Target Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__TARGET_SYMBOL = IMETA_TYPE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Show Role Names</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SHOW_ROLE_NAMES = IMETA_TYPE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Show Link Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__SHOW_LINK_TYPE_NAME = IMETA_TYPE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Link Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE__LINK_INSTANCES = IMETA_TYPE_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Link Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 13;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Link Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelerSymbolTypeImpl <em>Modeler Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelerSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getModelerSymbolType()
	 * @generated
	 */
	int MODELER_SYMBOL_TYPE = 57;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__ELEMENT_OID = IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__BORDER_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__FILL_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__STYLE = IMODEL_ELEMENT_NODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__XPOS = IMODEL_ELEMENT_NODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__YPOS = IMODEL_ELEMENT_NODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__WIDTH = IMODEL_ELEMENT_NODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__HEIGHT = IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__SHAPE = IMODEL_ELEMENT_NODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__IN_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__OUT_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Modeler</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE__MODELER = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Modeler Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Modeler Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelerTypeImpl <em>Modeler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelerTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getModelerType()
	 * @generated
	 */
	int MODELER_TYPE = 58;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__EMAIL = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__PASSWORD = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Modeler Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE__MODELER_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Modeler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Modeler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELER_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl <em>Model Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ModelTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getModelType()
	 * @generated
	 */
	int MODEL_TYPE = 59;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__ID = IIDENTIFIABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__NAME = IIDENTIFIABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__ATTRIBUTE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__DESCRIPTION = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__DATA_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Application Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__APPLICATION_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Application Context Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__APPLICATION_CONTEXT_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Trigger Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__TRIGGER_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Event Condition Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__EVENT_CONDITION_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Event Action Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__EVENT_ACTION_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__DATA = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__APPLICATION = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Modeler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__MODELER = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Quality Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__QUALITY_CONTROL = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__ROLE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__ORGANIZATION = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Conditional Performer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__CONDITIONAL_PERFORMER = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Process Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__PROCESS_DEFINITION = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>External Packages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__EXTERNAL_PACKAGES = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__SCRIPT = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Type Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__TYPE_DECLARATIONS = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__DIAGRAM = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Link Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__LINK_TYPE = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>View</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__VIEW = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__AUTHOR = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Carnot Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__CARNOT_VERSION = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__CREATED = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Model OID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__MODEL_OID = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__OID = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__VENDOR = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The number of structural features of the '<em>Model Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_FEATURE_COUNT = IIDENTIFIABLE_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The number of operations of the '<em>Model Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_OPERATION_COUNT = IIDENTIFIABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationSymbolTypeImpl <em>Organization Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getOrganizationSymbolType()
	 * @generated
	 */
	int ORGANIZATION_SYMBOL_TYPE = 60;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__BORDER_COLOR = IMODEL_PARTICIPANT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__FILL_COLOR = IMODEL_PARTICIPANT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__STYLE = IMODEL_PARTICIPANT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__XPOS = IMODEL_PARTICIPANT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__YPOS = IMODEL_PARTICIPANT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__WIDTH = IMODEL_PARTICIPANT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__HEIGHT = IMODEL_PARTICIPANT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__SHAPE = IMODEL_PARTICIPANT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__IN_LINKS = IMODEL_PARTICIPANT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__OUT_LINKS = IMODEL_PARTICIPANT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT_SYMBOL__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Triggered Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__TRIGGERED_EVENTS = IMODEL_PARTICIPANT_SYMBOL__TRIGGERED_EVENTS;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__ORGANIZATION = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Super Organizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__SUPER_ORGANIZATIONS = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Organizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__SUB_ORGANIZATIONS = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Member Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__MEMBER_ROLES = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Team Lead</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Organization Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_PARTICIPANT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_PARTICIPANT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Organization Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl <em>Organization Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.OrganizationTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getOrganizationType()
	 * @generated
	 */
	int ORGANIZATION_TYPE = 61;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__ID = IMODEL_PARTICIPANT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__NAME = IMODEL_PARTICIPANT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__ATTRIBUTE = IMODEL_PARTICIPANT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__DESCRIPTION = IMODEL_PARTICIPANT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Performed Swimlanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__PERFORMED_SWIMLANES = IMODEL_PARTICIPANT__PERFORMED_SWIMLANES;

	/**
	 * The feature id for the '<em><b>Participant Associations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__PARTICIPANT_ASSOCIATIONS = IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__PARTICIPANT = IMODEL_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Organization Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__ORGANIZATION_SYMBOLS = IMODEL_PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Team Lead</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE__TEAM_LEAD = IMODEL_PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Organization Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE___GET_SYMBOLS = IMODEL_PARTICIPANT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Organization Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ParameterMappingTypeImpl <em>Parameter Mapping Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ParameterMappingTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getParameterMappingType()
	 * @generated
	 */
	int PARAMETER_MAPPING_TYPE = 62;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE__DATA = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE__DATA_PATH = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE__PARAMETER = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameter Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE__PARAMETER_PATH = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Parameter Mapping Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Parameter Mapping Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_MAPPING_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ParticipantTypeImpl <em>Participant Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ParticipantTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getParticipantType()
	 * @generated
	 */
	int PARTICIPANT_TYPE = 63;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__PARTICIPANT = 0;

	/**
	 * The number of structural features of the '<em>Participant Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Participant Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PartOfConnectionTypeImpl <em>Part Of Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PartOfConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getPartOfConnectionType()
	 * @generated
	 */
	int PART_OF_CONNECTION_TYPE = 64;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__ORGANIZATION_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Suborganization Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE__SUBORGANIZATION_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Part Of Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Part Of Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_OF_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PerformsConnectionTypeImpl <em>Performs Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PerformsConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getPerformsConnectionType()
	 * @generated
	 */
	int PERFORMS_CONNECTION_TYPE = 65;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__ACTIVITY_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Participant Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE__PARTICIPANT_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Performs Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Performs Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMS_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl <em>Pool Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getPoolSymbol()
	 * @generated
	 */
	int POOL_SYMBOL = 66;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ATTRIBUTE = ISYMBOL_CONTAINER__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__NODES = ISYMBOL_CONTAINER__NODES;

	/**
	 * The feature id for the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ACTIVITY_SYMBOL = ISYMBOL_CONTAINER__ACTIVITY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ANNOTATION_SYMBOL = ISYMBOL_CONTAINER__ANNOTATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Application Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__APPLICATION_SYMBOL = ISYMBOL_CONTAINER__APPLICATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__CONDITIONAL_PERFORMER_SYMBOL = ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Data Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__DATA_SYMBOL = ISYMBOL_CONTAINER__DATA_SYMBOL;

	/**
	 * The feature id for the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__END_EVENT_SYMBOLS = ISYMBOL_CONTAINER__END_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__GATEWAY_SYMBOL = ISYMBOL_CONTAINER__GATEWAY_SYMBOL;

	/**
	 * The feature id for the '<em><b>Group Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__GROUP_SYMBOL = ISYMBOL_CONTAINER__GROUP_SYMBOL;

	/**
	 * The feature id for the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__INTERMEDIATE_EVENT_SYMBOLS = ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__MODELER_SYMBOL = ISYMBOL_CONTAINER__MODELER_SYMBOL;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ORGANIZATION_SYMBOL = ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PROCESS_SYMBOL = ISYMBOL_CONTAINER__PROCESS_SYMBOL;

	/**
	 * The feature id for the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PROCESS_INTERFACE_SYMBOLS = ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Role Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ROLE_SYMBOL = ISYMBOL_CONTAINER__ROLE_SYMBOL;

	/**
	 * The feature id for the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__START_EVENT_SYMBOLS = ISYMBOL_CONTAINER__START_EVENT_SYMBOLS;

	/**
	 * The feature id for the '<em><b>Text Symbol</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__TEXT_SYMBOL = ISYMBOL_CONTAINER__TEXT_SYMBOL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__CONNECTIONS = ISYMBOL_CONTAINER__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__DATA_MAPPING_CONNECTION = ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION;

	/**
	 * The feature id for the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__EXECUTED_BY_CONNECTION = ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION;

	/**
	 * The feature id for the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__GENERIC_LINK_CONNECTION = ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION;

	/**
	 * The feature id for the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PART_OF_CONNECTION = ISYMBOL_CONTAINER__PART_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Performs Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PERFORMS_CONNECTION = ISYMBOL_CONTAINER__PERFORMS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__TRIGGERS_CONNECTION = ISYMBOL_CONTAINER__TRIGGERS_CONNECTION;

	/**
	 * The feature id for the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__REFERS_TO_CONNECTION = ISYMBOL_CONTAINER__REFERS_TO_CONNECTION;

	/**
	 * The feature id for the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__SUB_PROCESS_OF_CONNECTION = ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION;

	/**
	 * The feature id for the '<em><b>Transition Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__TRANSITION_CONNECTION = ISYMBOL_CONTAINER__TRANSITION_CONNECTION;

	/**
	 * The feature id for the '<em><b>Works For Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__WORKS_FOR_CONNECTION = ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION;

	/**
	 * The feature id for the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__TEAM_LEAD_CONNECTION = ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ELEMENT_OID = ISYMBOL_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__BORDER_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__FILL_COLOR = ISYMBOL_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__STYLE = ISYMBOL_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__REFERING_TO_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__REFERING_FROM_CONNECTIONS = ISYMBOL_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__XPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__YPOS = ISYMBOL_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__WIDTH = ISYMBOL_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__HEIGHT = ISYMBOL_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__SHAPE = ISYMBOL_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__IN_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__OUT_LINKS = ISYMBOL_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ID = ISYMBOL_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__NAME = ISYMBOL_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__ORIENTATION = ISYMBOL_CONTAINER_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__COLLAPSED = ISYMBOL_CONTAINER_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PARTICIPANT = ISYMBOL_CONTAINER_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Child Lanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__CHILD_LANES = ISYMBOL_CONTAINER_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Participant Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PARTICIPANT_REFERENCE = ISYMBOL_CONTAINER_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__DIAGRAM = ISYMBOL_CONTAINER_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Boundary Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__BOUNDARY_VISIBLE = ISYMBOL_CONTAINER_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__PROCESS = ISYMBOL_CONTAINER_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Lanes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL__LANES = ISYMBOL_CONTAINER_FEATURE_COUNT + 23;

	/**
	 * The number of structural features of the '<em>Pool Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL_FEATURE_COUNT = ISYMBOL_CONTAINER_FEATURE_COUNT + 24;

	/**
	 * The operation id for the '<em>Get Node Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL___GET_NODE_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get Connection Containing Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL___GET_CONNECTION_CONTAINING_FEATURES = ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL___GET_IN_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL___GET_OUT_CONNECTION_FEATURES = ISYMBOL_CONTAINER_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Pool Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_SYMBOL_OPERATION_COUNT = ISYMBOL_CONTAINER_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessDefinitionTypeImpl <em>Process Definition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessDefinitionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getProcessDefinitionType()
	 * @generated
	 */
	int PROCESS_DEFINITION_TYPE = 67;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Event Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__EVENT_HANDLER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__ACTIVITY = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__TRANSITION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__TRIGGER = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DATA_PATH = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DIAGRAM = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Executing Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__EXECUTING_ACTIVITIES = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Process Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Default Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DEFAULT_PRIORITY = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__FORMAL_PARAMETERS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Formal Parameter Mappings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__FORMAL_PARAMETER_MAPPINGS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>External Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__EXTERNAL_REF = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Process Definition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Process Definition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessSymbolTypeImpl <em>Process Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getProcessSymbolType()
	 * @generated
	 */
	int PROCESS_SYMBOL_TYPE = 68;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__ELEMENT_OID = IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__BORDER_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__FILL_COLOR = IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__STYLE = IMODEL_ELEMENT_NODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__XPOS = IMODEL_ELEMENT_NODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__YPOS = IMODEL_ELEMENT_NODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__WIDTH = IMODEL_ELEMENT_NODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__HEIGHT = IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__SHAPE = IMODEL_ELEMENT_NODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__IN_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__OUT_LINKS = IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__PROCESS = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Processes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__SUB_PROCESSES = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent Processes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE__PARENT_PROCESSES = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Process Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Process Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_NODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PublicInterfaceSymbolImpl <em>Public Interface Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PublicInterfaceSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getPublicInterfaceSymbol()
	 * @generated
	 */
	int PUBLIC_INTERFACE_SYMBOL = 69;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__ELEMENT_OID = ABSTRACT_EVENT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__BORDER_COLOR = ABSTRACT_EVENT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__FILL_COLOR = ABSTRACT_EVENT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__STYLE = ABSTRACT_EVENT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__REFERING_TO_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__REFERING_FROM_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__XPOS = ABSTRACT_EVENT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__YPOS = ABSTRACT_EVENT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__WIDTH = ABSTRACT_EVENT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__HEIGHT = ABSTRACT_EVENT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__SHAPE = ABSTRACT_EVENT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__IN_LINKS = ABSTRACT_EVENT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__OUT_LINKS = ABSTRACT_EVENT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__IN_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__OUT_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL__LABEL = ABSTRACT_EVENT_SYMBOL__LABEL;

	/**
	 * The number of structural features of the '<em>Public Interface Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL_FEATURE_COUNT = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL___GET_IN_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL___GET_OUT_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL___GET_MODEL_ELEMENT = ABSTRACT_EVENT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = ABSTRACT_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Public Interface Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLIC_INTERFACE_SYMBOL_OPERATION_COUNT = ABSTRACT_EVENT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.QualityControlTypeImpl <em>Quality Control Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.QualityControlTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getQualityControlType()
	 * @generated
	 */
	int QUALITY_CONTROL_TYPE = 70;

	/**
	 * The feature id for the '<em><b>Code</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_CONTROL_TYPE__CODE = 0;

	/**
	 * The number of structural features of the '<em>Quality Control Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_CONTROL_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Quality Control Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_CONTROL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RefersToConnectionTypeImpl <em>Refers To Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RefersToConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getRefersToConnectionType()
	 * @generated
	 */
	int REFERS_TO_CONNECTION_TYPE = 71;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__FROM = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE__TO = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Refers To Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Refers To Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERS_TO_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl <em>Role Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getRoleSymbolType()
	 * @generated
	 */
	int ROLE_SYMBOL_TYPE = 72;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__BORDER_COLOR = IMODEL_PARTICIPANT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__FILL_COLOR = IMODEL_PARTICIPANT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__STYLE = IMODEL_PARTICIPANT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = IMODEL_PARTICIPANT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__XPOS = IMODEL_PARTICIPANT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__YPOS = IMODEL_PARTICIPANT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__WIDTH = IMODEL_PARTICIPANT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__HEIGHT = IMODEL_PARTICIPANT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__SHAPE = IMODEL_PARTICIPANT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__IN_LINKS = IMODEL_PARTICIPANT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__OUT_LINKS = IMODEL_PARTICIPANT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT_SYMBOL__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Triggered Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__TRIGGERED_EVENTS = IMODEL_PARTICIPANT_SYMBOL__TRIGGERED_EVENTS;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__ROLE = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Organization Memberships</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__ORGANIZATION_MEMBERSHIPS = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Teams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE__TEAMS = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Role Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = IMODEL_PARTICIPANT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE___GET_MODEL_ELEMENT = IMODEL_PARTICIPANT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = IMODEL_PARTICIPANT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Role Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SYMBOL_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl <em>Role Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.RoleTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getRoleType()
	 * @generated
	 */
	int ROLE_TYPE = 73;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ELEMENT_OID = IMODEL_PARTICIPANT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ID = IMODEL_PARTICIPANT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__NAME = IMODEL_PARTICIPANT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ATTRIBUTE = IMODEL_PARTICIPANT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__DESCRIPTION = IMODEL_PARTICIPANT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Performed Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__PERFORMED_ACTIVITIES = IMODEL_PARTICIPANT__PERFORMED_ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Performed Swimlanes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__PERFORMED_SWIMLANES = IMODEL_PARTICIPANT__PERFORMED_SWIMLANES;

	/**
	 * The feature id for the '<em><b>Participant Associations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__PARTICIPANT_ASSOCIATIONS = IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__CARDINALITY = IMODEL_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Teams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__TEAMS = IMODEL_PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Role Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ROLE_SYMBOLS = IMODEL_PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Role Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_FEATURE_COUNT = IMODEL_PARTICIPANT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE___GET_SYMBOLS = IMODEL_PARTICIPANT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Role Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_OPERATION_COUNT = IMODEL_PARTICIPANT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.StartEventSymbolImpl <em>Start Event Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.StartEventSymbolImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getStartEventSymbol()
	 * @generated
	 */
	int START_EVENT_SYMBOL = 74;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__ELEMENT_OID = ABSTRACT_EVENT_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__BORDER_COLOR = ABSTRACT_EVENT_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__FILL_COLOR = ABSTRACT_EVENT_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__STYLE = ABSTRACT_EVENT_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__REFERING_TO_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS = ABSTRACT_EVENT_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__XPOS = ABSTRACT_EVENT_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__YPOS = ABSTRACT_EVENT_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__WIDTH = ABSTRACT_EVENT_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__HEIGHT = ABSTRACT_EVENT_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__SHAPE = ABSTRACT_EVENT_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__IN_LINKS = ABSTRACT_EVENT_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__OUT_LINKS = ABSTRACT_EVENT_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>In Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__IN_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__IN_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Out Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__OUT_TRANSITIONS = ABSTRACT_EVENT_SYMBOL__OUT_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__LABEL = ABSTRACT_EVENT_SYMBOL__LABEL;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__TRIGGER = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Triggers Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Start Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL__START_ACTIVITY = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Start Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL_FEATURE_COUNT = ABSTRACT_EVENT_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES = ABSTRACT_EVENT_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL___GET_MODEL_ELEMENT = ABSTRACT_EVENT_SYMBOL___GET_MODEL_ELEMENT;

	/**
	 * The operation id for the '<em>Set Model Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT = ABSTRACT_EVENT_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT;

	/**
	 * The number of operations of the '<em>Start Event Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_EVENT_SYMBOL_OPERATION_COUNT = ABSTRACT_EVENT_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.SubProcessOfConnectionTypeImpl <em>Sub Process Of Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.SubProcessOfConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getSubProcessOfConnectionType()
	 * @generated
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE = 75;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Process Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subprocess Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sub Process Of Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Sub Process Of Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_OF_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl <em>Team Lead Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTeamLeadConnectionType()
	 * @generated
	 */
	int TEAM_LEAD_CONNECTION_TYPE = 76;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Team Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Team Lead Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Team Lead Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Team Lead Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEAM_LEAD_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TextSymbolTypeImpl <em>Text Symbol Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TextSymbolTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTextSymbolType()
	 * @generated
	 */
	int TEXT_SYMBOL_TYPE = 77;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__ELEMENT_OID = INODE_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__BORDER_COLOR = INODE_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__FILL_COLOR = INODE_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__STYLE = INODE_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__REFERING_TO_CONNECTIONS = INODE_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS = INODE_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>XPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__XPOS = INODE_SYMBOL__XPOS;

	/**
	 * The feature id for the '<em><b>YPos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__YPOS = INODE_SYMBOL__YPOS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__WIDTH = INODE_SYMBOL__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__HEIGHT = INODE_SYMBOL__HEIGHT;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__SHAPE = INODE_SYMBOL__SHAPE;

	/**
	 * The feature id for the '<em><b>In Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__IN_LINKS = INODE_SYMBOL__IN_LINKS;

	/**
	 * The feature id for the '<em><b>Out Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__OUT_LINKS = INODE_SYMBOL__OUT_LINKS;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE__TEXT = INODE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE_FEATURE_COUNT = INODE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get In Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE___GET_IN_CONNECTION_FEATURES = INODE_SYMBOL___GET_IN_CONNECTION_FEATURES;

	/**
	 * The operation id for the '<em>Get Out Connection Features</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE___GET_OUT_CONNECTION_FEATURES = INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES;

	/**
	 * The number of operations of the '<em>Text Symbol Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_SYMBOL_TYPE_OPERATION_COUNT = INODE_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TextTypeImpl <em>Text Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TextTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTextType()
	 * @generated
	 */
	int TEXT_TYPE = 78;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TYPE__MIXED = 0;

	/**
	 * The number of structural features of the '<em>Text Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Text Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl <em>Transition Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTransitionConnectionType()
	 * @generated
	 */
	int TRANSITION_CONNECTION_TYPE = 79;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__POINTS = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Activity Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE__TRANSITION = ICONNECTION_SYMBOL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Transition Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Transition Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionTypeImpl <em>Transition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTransitionType()
	 * @generated
	 */
	int TRANSITION_TYPE = 80;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__EXPRESSION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__CONDITION = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fork On Traversal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__FORK_ON_TRAVERSAL = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__FROM = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__TO = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Transition Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__TRANSITION_CONNECTIONS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Transition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The number of operations of the '<em>Transition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggersConnectionTypeImpl <em>Triggers Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggersConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTriggersConnectionType()
	 * @generated
	 */
	int TRIGGERS_CONNECTION_TYPE = 81;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Start Event Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__START_EVENT_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Participant Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE__PARTICIPANT_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Triggers Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Triggers Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggerTypeImpl <em>Trigger Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggerTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTriggerType()
	 * @generated
	 */
	int TRIGGER_TYPE = 82;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__ELEMENT_OID = IIDENTIFIABLE_MODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__ID = IIDENTIFIABLE_MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__NAME = IIDENTIFIABLE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__ATTRIBUTE = IIDENTIFIABLE_MODEL_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__DESCRIPTION = IIDENTIFIABLE_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__ACCESS_POINT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__PARAMETER_MAPPING = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__TYPE = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Starting Event Symbols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__STARTING_EVENT_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Trigger Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_FEATURE_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE___GET_SYMBOLS = IIDENTIFIABLE_MODEL_ELEMENT___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE___GET_META_TYPE = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Trigger Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_OPERATION_COUNT = IIDENTIFIABLE_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggerTypeTypeImpl <em>Trigger Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TriggerTypeTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getTriggerTypeType()
	 * @generated
	 */
	int TRIGGER_TYPE_TYPE = 83;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__ELEMENT_OID = IMETA_TYPE__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__ID = IMETA_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__NAME = IMETA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__ATTRIBUTE = IMETA_TYPE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__DESCRIPTION = IMETA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__IS_PREDEFINED = IMETA_TYPE__IS_PREDEFINED;

	/**
	 * The feature id for the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__PANEL_CLASS = IMETA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pull Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__PULL_TRIGGER = IMETA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pull Trigger Evaluator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__PULL_TRIGGER_EVALUATOR = IMETA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__RULE = IMETA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE__TRIGGERS = IMETA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Trigger Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE_FEATURE_COUNT = IMETA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE___GET_SYMBOLS = IMETA_TYPE___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Extension Point Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE___GET_EXTENSION_POINT_ID = IMETA_TYPE___GET_EXTENSION_POINT_ID;

	/**
	 * The operation id for the '<em>Get Typed Elements</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE___GET_TYPED_ELEMENTS = IMETA_TYPE___GET_TYPED_ELEMENTS;

	/**
	 * The number of operations of the '<em>Trigger Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_TYPE_OPERATION_COUNT = IMETA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.UnbindActionTypeImpl <em>Unbind Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.UnbindActionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getUnbindActionType()
	 * @generated
	 */
	int UNBIND_ACTION_TYPE = 84;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__ELEMENT_OID = ABSTRACT_EVENT_ACTION__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__ID = ABSTRACT_EVENT_ACTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__NAME = ABSTRACT_EVENT_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__ATTRIBUTE = ABSTRACT_EVENT_ACTION__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__DESCRIPTION = ABSTRACT_EVENT_ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE__TYPE = ABSTRACT_EVENT_ACTION__TYPE;

	/**
	 * The number of structural features of the '<em>Unbind Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE_FEATURE_COUNT = ABSTRACT_EVENT_ACTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Symbols</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE___GET_SYMBOLS = ABSTRACT_EVENT_ACTION___GET_SYMBOLS;

	/**
	 * The operation id for the '<em>Get Meta Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE___GET_META_TYPE = ABSTRACT_EVENT_ACTION___GET_META_TYPE;

	/**
	 * The number of operations of the '<em>Unbind Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBIND_ACTION_TYPE_OPERATION_COUNT = ABSTRACT_EVENT_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ViewableTypeImpl <em>Viewable Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ViewableTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getViewableType()
	 * @generated
	 */
	int VIEWABLE_TYPE = 85;

	/**
	 * The feature id for the '<em><b>Viewable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEWABLE_TYPE__VIEWABLE = 0;

	/**
	 * The number of structural features of the '<em>Viewable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEWABLE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Viewable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEWABLE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ViewTypeImpl <em>View Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ViewTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getViewType()
	 * @generated
	 */
	int VIEW_TYPE = 86;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__ELEMENT_OID = IMODEL_ELEMENT__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__ATTRIBUTE = IMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__DESCRIPTION = IMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>View</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__VIEW = IMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Viewable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__VIEWABLE = IMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE__NAME = IMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>View Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE_FEATURE_COUNT = IMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>View Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TYPE_OPERATION_COUNT = IMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.WorksForConnectionTypeImpl <em>Works For Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.WorksForConnectionTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getWorksForConnectionType()
	 * @generated
	 */
	int WORKS_FOR_CONNECTION_TYPE = 87;

	/**
	 * The feature id for the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__ELEMENT_OID = ICONNECTION_SYMBOL__ELEMENT_OID;

	/**
	 * The feature id for the '<em><b>Border Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__BORDER_COLOR = ICONNECTION_SYMBOL__BORDER_COLOR;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__FILL_COLOR = ICONNECTION_SYMBOL__FILL_COLOR;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__STYLE = ICONNECTION_SYMBOL__STYLE;

	/**
	 * The feature id for the '<em><b>Refering To Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__REFERING_TO_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_TO_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Refering From Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS = ICONNECTION_SYMBOL__REFERING_FROM_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__SOURCE_ANCHOR = ICONNECTION_SYMBOL__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__TARGET_ANCHOR = ICONNECTION_SYMBOL__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__ROUTING = ICONNECTION_SYMBOL__ROUTING;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__COORDINATES = ICONNECTION_SYMBOL__COORDINATES;

	/**
	 * The feature id for the '<em><b>Organization Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__ORGANIZATION_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Participant Symbol</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE__PARTICIPANT_SYMBOL = ICONNECTION_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Works For Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE_FEATURE_COUNT = ICONNECTION_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE___GET_SOURCE_NODE = ICONNECTION_SYMBOL___GET_SOURCE_NODE;

	/**
	 * The operation id for the '<em>Set Source Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_SOURCE_NODE__INODESYMBOL;

	/**
	 * The operation id for the '<em>Get Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE___GET_TARGET_NODE = ICONNECTION_SYMBOL___GET_TARGET_NODE;

	/**
	 * The operation id for the '<em>Set Target Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL = ICONNECTION_SYMBOL___SET_TARGET_NODE__INODESYMBOL;

	/**
	 * The number of operations of the '<em>Works For Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKS_FOR_CONNECTION_TYPE_OPERATION_COUNT = ICONNECTION_SYMBOL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.XmlTextNodeImpl <em>Xml Text Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.XmlTextNodeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getXmlTextNode()
	 * @generated
	 */
	int XML_TEXT_NODE = 88;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_TEXT_NODE__MIXED = 0;

	/**
	 * The number of structural features of the '<em>Xml Text Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_TEXT_NODE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Xml Text Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_TEXT_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType <em>Activity Implementation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getActivityImplementationType()
	 * @generated
	 */
	int ACTIVITY_IMPLEMENTATION_TYPE = 89;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType <em>Direction Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDirectionType()
	 * @generated
	 */
	int DIRECTION_TYPE = 90;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType <em>Flow Control Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getFlowControlType()
	 * @generated
	 */
	int FLOW_CONTROL_TYPE = 91;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType <em>Implementation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getImplementationType()
	 * @generated
	 */
	int IMPLEMENTATION_TYPE = 92;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType <em>Join Split Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getJoinSplitType()
	 * @generated
	 */
	int JOIN_SPLIT_TYPE = 93;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality <em>Link Cardinality</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkCardinality()
	 * @generated
	 */
	int LINK_CARDINALITY = 94;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor <em>Link Color</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkColor()
	 * @generated
	 */
	int LINK_COLOR = 95;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle <em>Link Line Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkLineStyle()
	 * @generated
	 */
	int LINK_LINE_STYLE = 96;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle <em>Link End Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkEndStyle()
	 * @generated
	 */
	int LINK_END_STYLE = 97;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType <em>Loop Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLoopType()
	 * @generated
	 */
	int LOOP_TYPE = 98;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType <em>Orientation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getOrientationType()
	 * @generated
	 */
	int ORIENTATION_TYPE = 99;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType <em>Routing Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getRoutingType()
	 * @generated
	 */
	int ROUTING_TYPE = 100;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType <em>Sub Process Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getSubProcessModeType()
	 * @generated
	 */
	int SUB_PROCESS_MODE_TYPE = 101;

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType <em>Diagram Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDiagramModeType()
	 * @generated
	 */
	int DIAGRAM_MODE_TYPE = 102;

	/**
	 * The meta object id for the '<em>Element Id</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getElementId()
	 * @generated
	 */
	int ELEMENT_ID = 103;

	/**
	 * The meta object id for the '<em>Feature List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getFeatureList()
	 * @generated
	 */
	int FEATURE_LIST = 104;

	/**
	 * The meta object id for the '<em>Activity Implementation Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getActivityImplementationTypeObject()
	 * @generated
	 */
	int ACTIVITY_IMPLEMENTATION_TYPE_OBJECT = 105;

	/**
	 * The meta object id for the '<em>Direction Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDirectionTypeObject()
	 * @generated
	 */
	int DIRECTION_TYPE_OBJECT = 106;

	/**
	 * The meta object id for the '<em>Flow Control Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getFlowControlTypeObject()
	 * @generated
	 */
	int FLOW_CONTROL_TYPE_OBJECT = 107;

	/**
	 * The meta object id for the '<em>Implementation Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getImplementationTypeObject()
	 * @generated
	 */
	int IMPLEMENTATION_TYPE_OBJECT = 108;

	/**
	 * The meta object id for the '<em>Link Cardinality Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkCardinalityObject()
	 * @generated
	 */
	int LINK_CARDINALITY_OBJECT = 109;

	/**
	 * The meta object id for the '<em>Link Color Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkColorObject()
	 * @generated
	 */
	int LINK_COLOR_OBJECT = 110;

	/**
	 * The meta object id for the '<em>Link Line Style Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkLineStyleObject()
	 * @generated
	 */
	int LINK_LINE_STYLE_OBJECT = 111;

	/**
	 * The meta object id for the '<em>Link End Style Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLinkEndStyleObject()
	 * @generated
	 */
	int LINK_END_STYLE_OBJECT = 112;

	/**
	 * The meta object id for the '<em>Join Split Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getJoinSplitTypeObject()
	 * @generated
	 */
	int JOIN_SPLIT_TYPE_OBJECT = 113;

	/**
	 * The meta object id for the '<em>Loop Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getLoopTypeObject()
	 * @generated
	 */
	int LOOP_TYPE_OBJECT = 114;

	/**
	 * The meta object id for the '<em>Orientation Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getOrientationTypeObject()
	 * @generated
	 */
	int ORIENTATION_TYPE_OBJECT = 115;

	/**
	 * The meta object id for the '<em>Routing Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getRoutingTypeObject()
	 * @generated
	 */
	int ROUTING_TYPE_OBJECT = 116;

	/**
	 * The meta object id for the '<em>Sub Process Mode Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getSubProcessModeTypeObject()
	 * @generated
	 */
	int SUB_PROCESS_MODE_TYPE_OBJECT = 117;

	/**
	 * The meta object id for the '<em>Diagram Mode Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl#getDiagramModeTypeObject()
	 * @generated
	 */
	int DIAGRAM_MODE_TYPE_OBJECT = 118;


	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coordinates</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates
	 * @generated
	 */
	EClass getCoordinates();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates#getXPos <em>XPos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPos</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates#getXPos()
	 * @see #getCoordinates()
	 * @generated
	 */
	EAttribute getCoordinates_XPos();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates#getYPos <em>YPos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPos</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates#getYPos()
	 * @see #getCoordinates()
	 * @generated
	 */
	EAttribute getCoordinates_YPos();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement <em>IIdentifiable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IIdentifiable Element</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement
	 * @generated
	 */
	EClass getIIdentifiableElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement#getId()
	 * @see #getIIdentifiableElement()
	 * @generated
	 */
	EAttribute getIIdentifiableElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement#getName()
	 * @see #getIIdentifiableElement()
	 * @generated
	 */
	EAttribute getIIdentifiableElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement <em>IExtensible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExtensible Element</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement
	 * @generated
	 */
	EClass getIExtensibleElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement#getAttribute()
	 * @see #getIExtensibleElement()
	 * @generated
	 */
	EReference getIExtensibleElement_Attribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference <em>Identifiable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable Reference</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference
	 * @generated
	 */
	EClass getIdentifiableReference();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference#getAttribute()
	 * @see #getIdentifiableReference()
	 * @generated
	 */
	EReference getIdentifiableReference_Attribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference#getIdentifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Identifiable</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdentifiableReference#getIdentifiable()
	 * @see #getIdentifiableReference()
	 * @generated
	 */
	EReference getIdentifiableReference_Identifiable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement <em>IModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IModel Element</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement
	 * @generated
	 */
	EClass getIModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement#getElementOid <em>Element Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Oid</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement#getElementOid()
	 * @see #getIModelElement()
	 * @generated
	 */
	EAttribute getIModelElement_ElementOid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement <em>IIdentifiable Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IIdentifiable Model Element</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement
	 * @generated
	 */
	EClass getIIdentifiableModelElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement#getDescription()
	 * @see #getIIdentifiableModelElement()
	 * @generated
	 */
	EReference getIIdentifiableModelElement_Description();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement#getSymbols() <em>Get Symbols</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Symbols</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement#getSymbols()
	 * @generated
	 */
	EOperation getIIdentifiableModelElement__GetSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner <em>IEvent Handler Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IEvent Handler Owner</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner
	 * @generated
	 */
	EClass getIEventHandlerOwner();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner#getEventHandler <em>Event Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Handler</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner#getEventHandler()
	 * @see #getIEventHandlerOwner()
	 * @generated
	 */
	EReference getIEventHandlerOwner_EventHandler();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner <em>IAccess Point Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IAccess Point Owner</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner
	 * @generated
	 */
	EClass getIAccessPointOwner();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner#getAccessPoint <em>Access Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Access Point</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner#getAccessPoint()
	 * @see #getIAccessPointOwner()
	 * @generated
	 */
	EReference getIAccessPointOwner_AccessPoint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType <em>IMeta Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMeta Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType
	 * @generated
	 */
	EClass getIMetaType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Predefined</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined()
	 * @see #getIMetaType()
	 * @generated
	 */
	EAttribute getIMetaType_IsPredefined();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#getExtensionPointId() <em>Get Extension Point Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Extension Point Id</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#getExtensionPointId()
	 * @generated
	 */
	EOperation getIMetaType__GetExtensionPointId();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#getTypedElements() <em>Get Typed Elements</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Typed Elements</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#getTypedElements()
	 * @generated
	 */
	EOperation getIMetaType__GetTypedElements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement <em>ITyped Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ITyped Element</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement
	 * @generated
	 */
	EClass getITypedElement();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement#getMetaType() <em>Get Meta Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Meta Type</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement#getMetaType()
	 * @generated
	 */
	EOperation getITypedElement__GetMetaType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer <em>ISymbol Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISymbol Container</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer
	 * @generated
	 */
	EClass getISymbolContainer();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Nodes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getNodes()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EAttribute getISymbolContainer_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getActivitySymbol <em>Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getActivitySymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ActivitySymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getAnnotationSymbol <em>Annotation Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotation Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getAnnotationSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_AnnotationSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getApplicationSymbol <em>Application Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getApplicationSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ApplicationSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConditionalPerformerSymbol <em>Conditional Performer Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditional Performer Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConditionalPerformerSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ConditionalPerformerSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataSymbol <em>Data Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_DataSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getEndEventSymbols <em>End Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>End Event Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getEndEventSymbols()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_EndEventSymbols();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGatewaySymbol <em>Gateway Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gateway Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGatewaySymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_GatewaySymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGroupSymbol <em>Group Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Group Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGroupSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_GroupSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getIntermediateEventSymbols <em>Intermediate Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Intermediate Event Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getIntermediateEventSymbols()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_IntermediateEventSymbols();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getModelerSymbol <em>Modeler Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modeler Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getModelerSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ModelerSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getOrganizationSymbol <em>Organization Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Organization Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getOrganizationSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_OrganizationSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessSymbol <em>Process Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ProcessSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessInterfaceSymbols <em>Process Interface Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Interface Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessInterfaceSymbols()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ProcessInterfaceSymbols();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRoleSymbol <em>Role Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Role Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRoleSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_RoleSymbol();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getStartEventSymbols <em>Start Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Start Event Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getStartEventSymbols()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_StartEventSymbols();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTextSymbol <em>Text Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Text Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTextSymbol()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_TextSymbol();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConnections()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EAttribute getISymbolContainer_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataMappingConnection <em>Data Mapping Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Mapping Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataMappingConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_DataMappingConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getExecutedByConnection <em>Executed By Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Executed By Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getExecutedByConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_ExecutedByConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGenericLinkConnection <em>Generic Link Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generic Link Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGenericLinkConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_GenericLinkConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPartOfConnection <em>Part Of Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Part Of Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPartOfConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_PartOfConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPerformsConnection <em>Performs Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Performs Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPerformsConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_PerformsConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTriggersConnection <em>Triggers Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTriggersConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_TriggersConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRefersToConnection <em>Refers To Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Refers To Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRefersToConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_RefersToConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getSubProcessOfConnection <em>Sub Process Of Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Process Of Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getSubProcessOfConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_SubProcessOfConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTransitionConnection <em>Transition Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTransitionConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_TransitionConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getWorksForConnection <em>Works For Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Works For Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getWorksForConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_WorksForConnection();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTeamLeadConnection <em>Team Lead Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Team Lead Connection</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTeamLeadConnection()
	 * @see #getISymbolContainer()
	 * @generated
	 */
	EReference getISymbolContainer_TeamLeadConnection();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getNodeContainingFeatures() <em>Get Node Containing Features</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Node Containing Features</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getNodeContainingFeatures()
	 * @generated
	 */
	EOperation getISymbolContainer__GetNodeContainingFeatures();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConnectionContainingFeatures() <em>Get Connection Containing Features</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Connection Containing Features</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConnectionContainingFeatures()
	 * @generated
	 */
	EOperation getISymbolContainer__GetConnectionContainingFeatures();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject <em>IGraphical Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IGraphical Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject
	 * @generated
	 */
	EClass getIGraphicalObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getBorderColor <em>Border Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Border Color</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getBorderColor()
	 * @see #getIGraphicalObject()
	 * @generated
	 */
	EAttribute getIGraphicalObject_BorderColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getFillColor()
	 * @see #getIGraphicalObject()
	 * @generated
	 */
	EAttribute getIGraphicalObject_FillColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getStyle()
	 * @see #getIGraphicalObject()
	 * @generated
	 */
	EAttribute getIGraphicalObject_Style();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingToConnections <em>Refering To Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refering To Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingToConnections()
	 * @see #getIGraphicalObject()
	 * @generated
	 */
	EReference getIGraphicalObject_ReferingToConnections();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingFromConnections <em>Refering From Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refering From Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingFromConnections()
	 * @see #getIGraphicalObject()
	 * @generated
	 */
	EReference getIGraphicalObject_ReferingFromConnections();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol <em>INode Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INode Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol
	 * @generated
	 */
	EClass getINodeSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getXPos <em>XPos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPos</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getXPos()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EAttribute getINodeSymbol_XPos();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getYPos <em>YPos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPos</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getYPos()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EAttribute getINodeSymbol_YPos();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getWidth()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EAttribute getINodeSymbol_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getHeight()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EAttribute getINodeSymbol_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shape</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getShape()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EAttribute getINodeSymbol_Shape();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInLinks <em>In Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Links</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInLinks()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EReference getINodeSymbol_InLinks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutLinks <em>Out Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Links</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutLinks()
	 * @see #getINodeSymbol()
	 * @generated
	 */
	EReference getINodeSymbol_OutLinks();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInConnectionFeatures() <em>Get In Connection Features</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get In Connection Features</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInConnectionFeatures()
	 * @generated
	 */
	EOperation getINodeSymbol__GetInConnectionFeatures();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutConnectionFeatures() <em>Get Out Connection Features</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Out Connection Features</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutConnectionFeatures()
	 * @generated
	 */
	EOperation getINodeSymbol__GetOutConnectionFeatures();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol <em>ISwimlane Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISwimlane Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol
	 * @generated
	 */
	EClass getISwimlaneSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation()
	 * @see #getISwimlaneSymbol()
	 * @generated
	 */
	EAttribute getISwimlaneSymbol_Orientation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#isCollapsed <em>Collapsed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collapsed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#isCollapsed()
	 * @see #getISwimlaneSymbol()
	 * @generated
	 */
	EAttribute getISwimlaneSymbol_Collapsed();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant()
	 * @see #getISwimlaneSymbol()
	 * @generated
	 */
	EReference getISwimlaneSymbol_Participant();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getChildLanes <em>Child Lanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Child Lanes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getChildLanes()
	 * @see #getISwimlaneSymbol()
	 * @generated
	 */
	EReference getISwimlaneSymbol_ChildLanes();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipantReference <em>Participant Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant Reference</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipantReference()
	 * @see #getISwimlaneSymbol()
	 * @generated
	 */
	EReference getISwimlaneSymbol_ParticipantReference();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol <em>IModel Element Node Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IModel Element Node Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol
	 * @generated
	 */
	EClass getIModelElementNodeSymbol();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol#getModelElement() <em>Get Model Element</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Model Element</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol#getModelElement()
	 * @generated
	 */
	EOperation getIModelElementNodeSymbol__GetModelElement();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol#setModelElement(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement) <em>Set Model Element</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Model Element</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol#setModelElement(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement)
	 * @generated
	 */
	EOperation getIModelElementNodeSymbol__SetModelElement__IIdentifiableModelElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol <em>IFlow Object Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFlow Object Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol
	 * @generated
	 */
	EClass getIFlowObjectSymbol();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getInTransitions <em>In Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Transitions</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getInTransitions()
	 * @see #getIFlowObjectSymbol()
	 * @generated
	 */
	EReference getIFlowObjectSymbol_InTransitions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getOutTransitions <em>Out Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Transitions</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getOutTransitions()
	 * @see #getIFlowObjectSymbol()
	 * @generated
	 */
	EReference getIFlowObjectSymbol_OutTransitions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol <em>IConnection Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConnection Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol
	 * @generated
	 */
	EClass getIConnectionSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getSourceAnchor <em>Source Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Anchor</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getSourceAnchor()
	 * @see #getIConnectionSymbol()
	 * @generated
	 */
	EAttribute getIConnectionSymbol_SourceAnchor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getTargetAnchor <em>Target Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Anchor</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getTargetAnchor()
	 * @see #getIConnectionSymbol()
	 * @generated
	 */
	EAttribute getIConnectionSymbol_TargetAnchor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getRouting <em>Routing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Routing</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getRouting()
	 * @see #getIConnectionSymbol()
	 * @generated
	 */
	EAttribute getIConnectionSymbol_Routing();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getCoordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Coordinates</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getCoordinates()
	 * @see #getIConnectionSymbol()
	 * @generated
	 */
	EReference getIConnectionSymbol_Coordinates();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getSourceNode() <em>Get Source Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Source Node</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getSourceNode()
	 * @generated
	 */
	EOperation getIConnectionSymbol__GetSourceNode();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#setSourceNode(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol) <em>Set Source Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Source Node</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#setSourceNode(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol)
	 * @generated
	 */
	EOperation getIConnectionSymbol__SetSourceNode__INodeSymbol();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getTargetNode() <em>Get Target Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target Node</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#getTargetNode()
	 * @generated
	 */
	EOperation getIConnectionSymbol__GetTargetNode();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#setTargetNode(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol) <em>Set Target Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Target Node</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IConnectionSymbol#setTargetNode(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol)
	 * @generated
	 */
	EOperation getIConnectionSymbol__SetTargetNode__INodeSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant <em>IModel Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IModel Participant</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant
	 * @generated
	 */
	EClass getIModelParticipant();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedActivities <em>Performed Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed Activities</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedActivities()
	 * @see #getIModelParticipant()
	 * @generated
	 */
	EReference getIModelParticipant_PerformedActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedSwimlanes <em>Performed Swimlanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed Swimlanes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedSwimlanes()
	 * @see #getIModelParticipant()
	 * @generated
	 */
	EReference getIModelParticipant_PerformedSwimlanes();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getParticipantAssociations <em>Participant Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participant Associations</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getParticipantAssociations()
	 * @see #getIModelParticipant()
	 * @generated
	 */
	EReference getIModelParticipant_ParticipantAssociations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol <em>IModel Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IModel Participant Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol
	 * @generated
	 */
	EClass getIModelParticipantSymbol();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getPerformedActivities <em>Performed Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performed Activities</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getPerformedActivities()
	 * @see #getIModelParticipantSymbol()
	 * @generated
	 */
	EReference getIModelParticipantSymbol_PerformedActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getTriggeredEvents <em>Triggered Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggered Events</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getTriggeredEvents()
	 * @see #getIModelParticipantSymbol()
	 * @generated
	 */
	EReference getIModelParticipantSymbol_TriggeredEvents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventAction <em>Abstract Event Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Event Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventAction
	 * @generated
	 */
	EClass getAbstractEventAction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventAction#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventAction#getType()
	 * @see #getAbstractEventAction()
	 * @generated
	 */
	EReference getAbstractEventAction_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventSymbol <em>Abstract Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Event Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventSymbol
	 * @generated
	 */
	EClass getAbstractEventSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventSymbol#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AbstractEventSymbol#getLabel()
	 * @see #getAbstractEventSymbol()
	 * @generated
	 */
	EAttribute getAbstractEventSymbol_Label();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType <em>Access Point Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Access Point Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType
	 * @generated
	 */
	EClass getAccessPointType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType#getDirection()
	 * @see #getAccessPointType()
	 * @generated
	 */
	EAttribute getAccessPointType_Direction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType#getType()
	 * @see #getAccessPointType()
	 * @generated
	 */
	EReference getAccessPointType_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType <em>Activity Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType
	 * @generated
	 */
	EClass getActivitySymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getActivity()
	 * @see #getActivitySymbolType()
	 * @generated
	 */
	EReference getActivitySymbolType_Activity();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getPerformsConnections <em>Performs Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performs Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getPerformsConnections()
	 * @see #getActivitySymbolType()
	 * @generated
	 */
	EReference getActivitySymbolType_PerformsConnections();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getExecutedByConnections <em>Executed By Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Executed By Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getExecutedByConnections()
	 * @see #getActivitySymbolType()
	 * @generated
	 */
	EReference getActivitySymbolType_ExecutedByConnections();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getDataMappings <em>Data Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Mappings</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getDataMappings()
	 * @see #getActivitySymbolType()
	 * @generated
	 */
	EReference getActivitySymbolType_DataMappings();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getGatewaySymbols <em>Gateway Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gateway Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getGatewaySymbols()
	 * @see #getActivitySymbolType()
	 * @generated
	 */
	EReference getActivitySymbolType_GatewaySymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType <em>Activity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType
	 * @generated
	 */
	EClass getActivityType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getDataMapping <em>Data Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Mapping</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getDataMapping()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_DataMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allows Abort By Performer</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_AllowsAbortByPerformer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Application</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getApplication()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_Application();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation <em>Hibernate On Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hibernate On Creation</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_HibernateOnCreation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementation()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Implementation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess <em>Implementation Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Implementation Process</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_ImplementationProcess();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Join</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Join();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopCondition <em>Loop Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Condition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopCondition()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_LoopCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType <em>Loop Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_LoopType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer <em>Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Performer</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_Performer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getQualityControlPerformer <em>Quality Control Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Quality Control Performer</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getQualityControlPerformer()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_QualityControlPerformer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Split();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode <em>Sub Process Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Process Mode</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_SubProcessMode();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getActivitySymbols <em>Activity Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Activity Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getActivitySymbols()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_ActivitySymbols();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Starting Event Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getStartingEventSymbols()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_StartingEventSymbols();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getInTransitions <em>In Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Transitions</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getInTransitions()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_InTransitions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getOutTransitions <em>Out Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Transitions</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getOutTransitions()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_OutTransitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getExternalRef <em>External Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Ref</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getExternalRef()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_ExternalRef();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getValidQualityCodes <em>Valid Quality Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Valid Quality Codes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getValidQualityCodes()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_ValidQualityCodes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType <em>Annotation Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType
	 * @generated
	 */
	EClass getAnnotationSymbolType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType#getText()
	 * @see #getAnnotationSymbolType()
	 * @generated
	 */
	EReference getAnnotationSymbolType_Text();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType <em>Application Context Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Context Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType
	 * @generated
	 */
	EClass getApplicationContextTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Point Provider Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getAccessPointProviderClass()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EAttribute getApplicationContextTypeType_AccessPointProviderClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath <em>Has Application Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Application Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EAttribute getApplicationContextTypeType_HasApplicationPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId <em>Has Mapping Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Mapping Id</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EAttribute getApplicationContextTypeType_HasMappingId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getPanelClass()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EAttribute getApplicationContextTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validator Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getValidatorClass()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EAttribute getApplicationContextTypeType_ValidatorClass();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getContexts <em>Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contexts</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getContexts()
	 * @see #getApplicationContextTypeType()
	 * @generated
	 */
	EReference getApplicationContextTypeType_Contexts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType <em>Application Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType
	 * @generated
	 */
	EClass getApplicationSymbolType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getExecutingActivities <em>Executing Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Executing Activities</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getExecutingActivities()
	 * @see #getApplicationSymbolType()
	 * @generated
	 */
	EReference getApplicationSymbolType_ExecutingActivities();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Application</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getApplication()
	 * @see #getApplicationSymbolType()
	 * @generated
	 */
	EReference getApplicationSymbolType_Application();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType
	 * @generated
	 */
	EClass getApplicationType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getContext()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_Context();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#isInteractive <em>Interactive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interactive</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#isInteractive()
	 * @see #getApplicationType()
	 * @generated
	 */
	EAttribute getApplicationType_Interactive();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getType()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getExecutedActivities <em>Executed Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Executed Activities</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getExecutedActivities()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ExecutedActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getApplicationSymbols <em>Application Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Application Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getApplicationSymbols()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ApplicationSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType <em>Application Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType
	 * @generated
	 */
	EClass getApplicationTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Point Provider Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getAccessPointProviderClass()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EAttribute getApplicationTypeType_AccessPointProviderClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getInstanceClass <em>Instance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getInstanceClass()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EAttribute getApplicationTypeType_InstanceClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getPanelClass()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EAttribute getApplicationTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#isSynchronous <em>Synchronous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronous</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#isSynchronous()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EAttribute getApplicationTypeType_Synchronous();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validator Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getValidatorClass()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EAttribute getApplicationTypeType_ValidatorClass();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Applications</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType#getApplications()
	 * @see #getApplicationTypeType()
	 * @generated
	 */
	EReference getApplicationTypeType_Applications();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType <em>Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType
	 * @generated
	 */
	EClass getAttributeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getMixed()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getGroup()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getAny()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Any();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getValueNode <em>Value Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Node</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getValueNode()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_ValueNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getName()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getType()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getValue()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getReference()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_Reference();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getAttributeValue() <em>Get Attribute Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Attribute Value</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#getAttributeValue()
	 * @generated
	 */
	EOperation getAttributeType__GetAttributeValue();

	/**
	 * Returns the meta object for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#setAttributeValue(java.lang.String, java.lang.String) <em>Set Attribute Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Attribute Value</em>' operation.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType#setAttributeValue(java.lang.String, java.lang.String)
	 * @generated
	 */
	EOperation getAttributeType__SetAttributeValue__String_String();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.BindActionType <em>Bind Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bind Action Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.BindActionType
	 * @generated
	 */
	EClass getBindActionType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Code</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code
	 * @generated
	 */
	EClass getCode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getCode()
	 * @see #getCode()
	 * @generated
	 */
	EAttribute getCode_Code();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getValue()
	 * @see #getCode()
	 * @generated
	 */
	EAttribute getCode_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code#getName()
	 * @see #getCode()
	 * @generated
	 */
	EAttribute getCode_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType <em>Conditional Performer Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Performer Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType
	 * @generated
	 */
	EClass getConditionalPerformerSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType#getParticipant()
	 * @see #getConditionalPerformerSymbolType()
	 * @generated
	 */
	EReference getConditionalPerformerSymbolType_Participant();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType <em>Conditional Performer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Performer Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType
	 * @generated
	 */
	EClass getConditionalPerformerType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getData()
	 * @see #getConditionalPerformerType()
	 * @generated
	 */
	EReference getConditionalPerformerType_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getDataPath()
	 * @see #getConditionalPerformerType()
	 * @generated
	 */
	EAttribute getConditionalPerformerType_DataPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#isIsUser <em>Is User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is User</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#isIsUser()
	 * @see #getConditionalPerformerType()
	 * @generated
	 */
	EAttribute getConditionalPerformerType_IsUser();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getConditionalPerformerSymbols <em>Conditional Performer Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conditional Performer Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getConditionalPerformerSymbols()
	 * @see #getConditionalPerformerType()
	 * @generated
	 */
	EReference getConditionalPerformerType_ConditionalPerformerSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType <em>Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType
	 * @generated
	 */
	EClass getContextType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getDescription()
	 * @see #getContextType()
	 * @generated
	 */
	EReference getContextType_Description();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getType()
	 * @see #getContextType()
	 * @generated
	 */
	EReference getContextType_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType <em>Data Mapping Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Mapping Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType
	 * @generated
	 */
	EClass getDataMappingConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getActivitySymbol()
	 * @see #getDataMappingConnectionType()
	 * @generated
	 */
	EReference getDataMappingConnectionType_ActivitySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol <em>Data Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol()
	 * @see #getDataMappingConnectionType()
	 * @generated
	 */
	EReference getDataMappingConnectionType_DataSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType <em>Data Mapping Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Mapping Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType
	 * @generated
	 */
	EClass getDataMappingType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getApplicationAccessPoint <em>Application Access Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Access Point</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getApplicationAccessPoint()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EAttribute getDataMappingType_ApplicationAccessPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getApplicationPath <em>Application Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getApplicationPath()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EAttribute getDataMappingType_ApplicationPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getContext()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EAttribute getDataMappingType_Context();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getData()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EReference getDataMappingType_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getDataPath()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EAttribute getDataMappingType_DataPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getDirection()
	 * @see #getDataMappingType()
	 * @generated
	 */
	EAttribute getDataMappingType_Direction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType <em>Data Path Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Path Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType
	 * @generated
	 */
	EClass getDataPathType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getData()
	 * @see #getDataPathType()
	 * @generated
	 */
	EReference getDataPathType_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getDataPath()
	 * @see #getDataPathType()
	 * @generated
	 */
	EAttribute getDataPathType_DataPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#isDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Descriptor</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#isDescriptor()
	 * @see #getDataPathType()
	 * @generated
	 */
	EAttribute getDataPathType_Descriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#isKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#isKey()
	 * @see #getDataPathType()
	 * @generated
	 */
	EAttribute getDataPathType_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getDirection()
	 * @see #getDataPathType()
	 * @generated
	 */
	EAttribute getDataPathType_Direction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType <em>Data Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType
	 * @generated
	 */
	EClass getDataSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData()
	 * @see #getDataSymbolType()
	 * @generated
	 */
	EReference getDataSymbolType_Data();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings <em>Data Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Mappings</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings()
	 * @see #getDataSymbolType()
	 * @generated
	 */
	EReference getDataSymbolType_DataMappings();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType
	 * @generated
	 */
	EClass getDataType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataMappings <em>Data Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Mappings</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataMappings()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_DataMappings();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined <em>Predefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predefined</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_Predefined();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getType()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataSymbols <em>Data Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataSymbols()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_DataSymbols();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getConditionalPerformers <em>Conditional Performers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conditional Performers</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getConditionalPerformers()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_ConditionalPerformers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataPaths <em>Data Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Paths</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataPaths()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_DataPaths();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getParameterMappings <em>Parameter Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter Mappings</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getParameterMappings()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_ParameterMappings();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getExternalReference()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_ExternalReference();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType <em>Data Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType
	 * @generated
	 */
	EClass getDataTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getAccessPathEditor <em>Access Path Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Path Editor</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getAccessPathEditor()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_AccessPathEditor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getEvaluator <em>Evaluator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluator</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getEvaluator()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_Evaluator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getInstanceClass <em>Instance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getInstanceClass()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_InstanceClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getPanelClass()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#isReadable <em>Readable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Readable</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#isReadable()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_Readable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getStorageStrategy <em>Storage Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Storage Strategy</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getStorageStrategy()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_StorageStrategy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validator Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getValidatorClass()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_ValidatorClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getValueCreator <em>Value Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Creator</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getValueCreator()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_ValueCreator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#isWritable <em>Writable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Writable</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#isWritable()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EAttribute getDataTypeType_Writable();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getData()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_Data();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType <em>Description Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType
	 * @generated
	 */
	EClass getDescriptionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DescriptionType#getMixed()
	 * @see #getDescriptionType()
	 * @generated
	 */
	EAttribute getDescriptionType_Mixed();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType <em>Diagram Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType
	 * @generated
	 */
	EClass getDiagramType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getName()
	 * @see #getDiagramType()
	 * @generated
	 */
	EAttribute getDiagramType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getPoolSymbols <em>Pool Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pool Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getPoolSymbols()
	 * @see #getDiagramType()
	 * @generated
	 */
	EReference getDiagramType_PoolSymbols();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getOrientation()
	 * @see #getDiagramType()
	 * @generated
	 */
	EAttribute getDiagramType_Orientation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getMode()
	 * @see #getDiagramType()
	 * @generated
	 */
	EAttribute getDiagramType_Mode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DocumentRoot#getModel()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Model();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EndEventSymbol <em>End Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Event Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EndEventSymbol
	 * @generated
	 */
	EClass getEndEventSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionType <em>Event Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Action Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionType
	 * @generated
	 */
	EClass getEventActionType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType <em>Event Action Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Action Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType
	 * @generated
	 */
	EClass getEventActionTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getActionClass <em>Action Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getActionClass()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_ActionClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#isActivityAction <em>Activity Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#isActivityAction()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_ActivityAction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getPanelClass()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#isProcessAction <em>Process Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#isProcessAction()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_ProcessAction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getSupportedConditionTypes <em>Supported Condition Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supported Condition Types</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getSupportedConditionTypes()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_SupportedConditionTypes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getUnsupportedContexts <em>Unsupported Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unsupported Contexts</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getUnsupportedContexts()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EAttribute getEventActionTypeType_UnsupportedContexts();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getActionInstances <em>Action Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Action Instances</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType#getActionInstances()
	 * @see #getEventActionTypeType()
	 * @generated
	 */
	EReference getEventActionTypeType_ActionInstances();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType <em>Event Condition Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Condition Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType
	 * @generated
	 */
	EClass getEventConditionTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#isActivityCondition <em>Activity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Condition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#isActivityCondition()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_ActivityCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getBinderClass <em>Binder Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binder Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getBinderClass()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_BinderClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getImplementation()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getPanelClass()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#isProcessCondition <em>Process Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Condition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#isProcessCondition()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_ProcessCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getPullEventEmitterClass <em>Pull Event Emitter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pull Event Emitter Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getPullEventEmitterClass()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_PullEventEmitterClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getRule()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EAttribute getEventConditionTypeType_Rule();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getEventHandlers <em>Event Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Event Handlers</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getEventHandlers()
	 * @see #getEventConditionTypeType()
	 * @generated
	 */
	EReference getEventConditionTypeType_EventHandlers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType <em>Event Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Handler Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType
	 * @generated
	 */
	EClass getEventHandlerType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getBindAction <em>Bind Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bind Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getBindAction()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EReference getEventHandlerType_BindAction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getEventAction <em>Event Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getEventAction()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EReference getEventHandlerType_EventAction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getUnbindAction <em>Unbind Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unbind Action</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getUnbindAction()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EReference getEventHandlerType_UnbindAction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind <em>Auto Bind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Bind</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EAttribute getEventHandlerType_AutoBind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch <em>Consume On Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Consume On Match</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EAttribute getEventHandlerType_ConsumeOnMatch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler <em>Log Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log Handler</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EAttribute getEventHandlerType_LogHandler();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getType()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EReference getEventHandlerType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch <em>Unbind On Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unbind On Match</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch()
	 * @see #getEventHandlerType()
	 * @generated
	 */
	EAttribute getEventHandlerType_UnbindOnMatch();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType <em>Executed By Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Executed By Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType
	 * @generated
	 */
	EClass getExecutedByConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getActivitySymbol()
	 * @see #getExecutedByConnectionType()
	 * @generated
	 */
	EReference getExecutedByConnectionType_ActivitySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getApplicationSymbol <em>Application Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Application Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getApplicationSymbol()
	 * @see #getExecutedByConnectionType()
	 * @generated
	 */
	EReference getExecutedByConnectionType_ApplicationSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef <em>Id Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Id Ref</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef
	 * @generated
	 */
	EClass getIdRef();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef#getPackageRef <em>Package Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Package Ref</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef#getPackageRef()
	 * @see #getIdRef()
	 * @generated
	 */
	EReference getIdRef_PackageRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IdRef#getRef()
	 * @see #getIdRef()
	 * @generated
	 */
	EAttribute getIdRef_Ref();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol <em>Gateway Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gateway Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol
	 * @generated
	 */
	EClass getGatewaySymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind <em>Flow Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flow Kind</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind()
	 * @see #getGatewaySymbol()
	 * @generated
	 */
	EAttribute getGatewaySymbol_FlowKind();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getActivitySymbol <em>Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getActivitySymbol()
	 * @see #getGatewaySymbol()
	 * @generated
	 */
	EReference getGatewaySymbol_ActivitySymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType <em>Generic Link Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Link Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType
	 * @generated
	 */
	EClass getGenericLinkConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType()
	 * @see #getGenericLinkConnectionType()
	 * @generated
	 */
	EReference getGenericLinkConnectionType_LinkType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol()
	 * @see #getGenericLinkConnectionType()
	 * @generated
	 */
	EReference getGenericLinkConnectionType_SourceSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol()
	 * @see #getGenericLinkConnectionType()
	 * @generated
	 */
	EReference getGenericLinkConnectionType_TargetSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GroupSymbolType <em>Group Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GroupSymbolType
	 * @generated
	 */
	EClass getGroupSymbolType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IntermediateEventSymbol <em>Intermediate Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intermediate Event Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IntermediateEventSymbol
	 * @generated
	 */
	EClass getIntermediateEventSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol <em>Lane Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lane Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol
	 * @generated
	 */
	EClass getLaneSymbol();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentPool <em>Parent Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Pool</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentPool()
	 * @see #getLaneSymbol()
	 * @generated
	 */
	EReference getLaneSymbol_ParentPool();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentLane <em>Parent Lane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Lane</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentLane()
	 * @see #getLaneSymbol()
	 * @generated
	 */
	EReference getLaneSymbol_ParentLane();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType <em>Link Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType
	 * @generated
	 */
	EClass getLinkTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceRole <em>Source Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Role</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceRole()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_SourceRole();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceClass <em>Source Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceClass()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_SourceClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceCardinality <em>Source Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Cardinality</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceCardinality()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_SourceCardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetRole <em>Target Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Role</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetRole()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_TargetRole();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetClass <em>Target Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetClass()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_TargetClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetCardinality <em>Target Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Cardinality</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetCardinality()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_TargetCardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Style</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineStyle()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_LineStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineColor <em>Line Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Color</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineColor()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_LineColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceSymbol <em>Source Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceSymbol()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_SourceSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetSymbol <em>Target Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetSymbol()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_TargetSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames <em>Show Role Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Role Names</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_ShowRoleNames();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName <em>Show Link Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Link Type Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EAttribute getLinkTypeType_ShowLinkTypeName();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLinkInstances <em>Link Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Link Instances</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLinkInstances()
	 * @see #getLinkTypeType()
	 * @generated
	 */
	EReference getLinkTypeType_LinkInstances();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType <em>Modeler Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modeler Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType
	 * @generated
	 */
	EClass getModelerSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Modeler</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType#getModeler()
	 * @see #getModelerSymbolType()
	 * @generated
	 */
	EReference getModelerSymbolType_Modeler();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType <em>Modeler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modeler Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType
	 * @generated
	 */
	EClass getModelerType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getEmail()
	 * @see #getModelerType()
	 * @generated
	 */
	EAttribute getModelerType_Email();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getPassword()
	 * @see #getModelerType()
	 * @generated
	 */
	EAttribute getModelerType_Password();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getModelerSymbols <em>Modeler Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modeler Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getModelerSymbols()
	 * @see #getModelerType()
	 * @generated
	 */
	EReference getModelerType_ModelerSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType <em>Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType
	 * @generated
	 */
	EClass getModelType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDescription()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDataType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_DataType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ApplicationType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationContextType <em>Application Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application Context Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationContextType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ApplicationContextType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTriggerType <em>Trigger Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trigger Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTriggerType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_TriggerType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventConditionType <em>Event Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Condition Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventConditionType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_EventConditionType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventActionType <em>Event Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Action Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventActionType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_EventActionType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getData()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Data();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplication()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Application();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModeler <em>Modeler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modeler</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModeler()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Modeler();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getQualityControl <em>Quality Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Quality Control</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getQualityControl()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_QualityControl();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Role</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getRole()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Role();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Organization</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOrganization()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Organization();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getConditionalPerformer <em>Conditional Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditional Performer</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getConditionalPerformer()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ConditionalPerformer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getProcessDefinition <em>Process Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Definition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getProcessDefinition()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ProcessDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getExternalPackages <em>External Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Packages</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getExternalPackages()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ExternalPackages();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getScript()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTypeDeclarations <em>Type Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Declarations</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTypeDeclarations()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_TypeDeclarations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagram</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDiagram()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_Diagram();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getLinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Link Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getLinkType()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_LinkType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getView()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_View();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getAuthor()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_Author();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCarnotVersion <em>Carnot Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Carnot Version</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCarnotVersion()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_CarnotVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCreated()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_Created();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID <em>Model OID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model OID</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_ModelOID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid <em>Oid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Oid</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_Oid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getVendor()
	 * @see #getModelType()
	 * @generated
	 */
	EAttribute getModelType_Vendor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType <em>Organization Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organization Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType
	 * @generated
	 */
	EClass getOrganizationSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Organization</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getOrganization()
	 * @see #getOrganizationSymbolType()
	 * @generated
	 */
	EReference getOrganizationSymbolType_Organization();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSuperOrganizations <em>Super Organizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Organizations</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSuperOrganizations()
	 * @see #getOrganizationSymbolType()
	 * @generated
	 */
	EReference getOrganizationSymbolType_SuperOrganizations();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSubOrganizations <em>Sub Organizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Organizations</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSubOrganizations()
	 * @see #getOrganizationSymbolType()
	 * @generated
	 */
	EReference getOrganizationSymbolType_SubOrganizations();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getMemberRoles <em>Member Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Member Roles</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getMemberRoles()
	 * @see #getOrganizationSymbolType()
	 * @generated
	 */
	EReference getOrganizationSymbolType_MemberRoles();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getTeamLead <em>Team Lead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Team Lead</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getTeamLead()
	 * @see #getOrganizationSymbolType()
	 * @generated
	 */
	EReference getOrganizationSymbolType_TeamLead();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType <em>Organization Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organization Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType
	 * @generated
	 */
	EClass getOrganizationType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getParticipant()
	 * @see #getOrganizationType()
	 * @generated
	 */
	EReference getOrganizationType_Participant();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getOrganizationSymbols <em>Organization Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Organization Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getOrganizationSymbols()
	 * @see #getOrganizationType()
	 * @generated
	 */
	EReference getOrganizationType_OrganizationSymbols();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getTeamLead <em>Team Lead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Team Lead</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getTeamLead()
	 * @see #getOrganizationType()
	 * @generated
	 */
	EReference getOrganizationType_TeamLead();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType <em>Parameter Mapping Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Mapping Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType
	 * @generated
	 */
	EClass getParameterMappingType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getData()
	 * @see #getParameterMappingType()
	 * @generated
	 */
	EReference getParameterMappingType_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getDataPath()
	 * @see #getParameterMappingType()
	 * @generated
	 */
	EAttribute getParameterMappingType_DataPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getParameter()
	 * @see #getParameterMappingType()
	 * @generated
	 */
	EAttribute getParameterMappingType_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getParameterPath <em>Parameter Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getParameterPath()
	 * @see #getParameterMappingType()
	 * @generated
	 */
	EAttribute getParameterMappingType_ParameterPath();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType <em>Participant Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType
	 * @generated
	 */
	EClass getParticipantType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant()
	 * @see #getParticipantType()
	 * @generated
	 */
	EReference getParticipantType_Participant();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType <em>Part Of Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Part Of Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType
	 * @generated
	 */
	EClass getPartOfConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Organization Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getOrganizationSymbol()
	 * @see #getPartOfConnectionType()
	 * @generated
	 */
	EReference getPartOfConnectionType_OrganizationSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getSuborganizationSymbol <em>Suborganization Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Suborganization Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getSuborganizationSymbol()
	 * @see #getPartOfConnectionType()
	 * @generated
	 */
	EReference getPartOfConnectionType_SuborganizationSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType <em>Performs Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Performs Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType
	 * @generated
	 */
	EClass getPerformsConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getActivitySymbol()
	 * @see #getPerformsConnectionType()
	 * @generated
	 */
	EReference getPerformsConnectionType_ActivitySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getParticipantSymbol()
	 * @see #getPerformsConnectionType()
	 * @generated
	 */
	EReference getPerformsConnectionType_ParticipantSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol <em>Pool Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pool Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol
	 * @generated
	 */
	EClass getPoolSymbol();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getDiagram()
	 * @see #getPoolSymbol()
	 * @generated
	 */
	EReference getPoolSymbol_Diagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#isBoundaryVisible <em>Boundary Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boundary Visible</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#isBoundaryVisible()
	 * @see #getPoolSymbol()
	 * @generated
	 */
	EAttribute getPoolSymbol_BoundaryVisible();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Process</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getProcess()
	 * @see #getPoolSymbol()
	 * @generated
	 */
	EReference getPoolSymbol_Process();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getLanes <em>Lanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lanes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getLanes()
	 * @see #getPoolSymbol()
	 * @generated
	 */
	EReference getPoolSymbol_Lanes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType <em>Process Definition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Definition Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType
	 * @generated
	 */
	EClass getProcessDefinitionType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getActivity()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Activity();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTransition()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trigger</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTrigger()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Trigger();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Path</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDataPath()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_DataPath();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagram</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDiagram()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Diagram();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExecutingActivities <em>Executing Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Executing Activities</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExecutingActivities()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_ExecutingActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getProcessSymbols <em>Process Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Process Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getProcessSymbols()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_ProcessSymbols();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDefaultPriority <em>Default Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Priority</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDefaultPriority()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EAttribute getProcessDefinitionType_DefaultPriority();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameters</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameters()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_FormalParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameterMappings <em>Formal Parameter Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameter Mappings</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameterMappings()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_FormalParameterMappings();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExternalRef <em>External Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Ref</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExternalRef()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_ExternalRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType <em>Process Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType
	 * @generated
	 */
	EClass getProcessSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Process</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess()
	 * @see #getProcessSymbolType()
	 * @generated
	 */
	EReference getProcessSymbolType_Process();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getSubProcesses <em>Sub Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Processes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getSubProcesses()
	 * @see #getProcessSymbolType()
	 * @generated
	 */
	EReference getProcessSymbolType_SubProcesses();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getParentProcesses <em>Parent Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Processes</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getParentProcesses()
	 * @see #getProcessSymbolType()
	 * @generated
	 */
	EReference getProcessSymbolType_ParentProcesses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PublicInterfaceSymbol <em>Public Interface Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Public Interface Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PublicInterfaceSymbol
	 * @generated
	 */
	EClass getPublicInterfaceSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.QualityControlType <em>Quality Control Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quality Control Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.QualityControlType
	 * @generated
	 */
	EClass getQualityControlType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.QualityControlType#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Code</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.QualityControlType#getCode()
	 * @see #getQualityControlType()
	 * @generated
	 */
	EReference getQualityControlType_Code();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType <em>Refers To Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Refers To Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType
	 * @generated
	 */
	EClass getRefersToConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom()
	 * @see #getRefersToConnectionType()
	 * @generated
	 */
	EReference getRefersToConnectionType_From();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo()
	 * @see #getRefersToConnectionType()
	 * @generated
	 */
	EReference getRefersToConnectionType_To();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType <em>Role Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType
	 * @generated
	 */
	EClass getRoleSymbolType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getRole()
	 * @see #getRoleSymbolType()
	 * @generated
	 */
	EReference getRoleSymbolType_Role();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getOrganizationMemberships <em>Organization Memberships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Organization Memberships</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getOrganizationMemberships()
	 * @see #getRoleSymbolType()
	 * @generated
	 */
	EReference getRoleSymbolType_OrganizationMemberships();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getTeams <em>Teams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Teams</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getTeams()
	 * @see #getRoleSymbolType()
	 * @generated
	 */
	EReference getRoleSymbolType_Teams();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType <em>Role Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType
	 * @generated
	 */
	EClass getRoleType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getCardinality()
	 * @see #getRoleType()
	 * @generated
	 */
	EAttribute getRoleType_Cardinality();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getTeams <em>Teams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Teams</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getTeams()
	 * @see #getRoleType()
	 * @generated
	 */
	EReference getRoleType_Teams();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getRoleSymbols <em>Role Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Role Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getRoleSymbols()
	 * @see #getRoleType()
	 * @generated
	 */
	EReference getRoleType_RoleSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol <em>Start Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Event Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol
	 * @generated
	 */
	EClass getStartEventSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Trigger</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTrigger()
	 * @see #getStartEventSymbol()
	 * @generated
	 */
	EReference getStartEventSymbol_Trigger();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTriggersConnections <em>Triggers Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggers Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTriggersConnections()
	 * @see #getStartEventSymbol()
	 * @generated
	 */
	EReference getStartEventSymbol_TriggersConnections();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Activity</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity()
	 * @see #getStartEventSymbol()
	 * @generated
	 */
	EReference getStartEventSymbol_StartActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType <em>Sub Process Of Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Process Of Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType
	 * @generated
	 */
	EClass getSubProcessOfConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getProcessSymbol <em>Process Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Process Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getProcessSymbol()
	 * @see #getSubProcessOfConnectionType()
	 * @generated
	 */
	EReference getSubProcessOfConnectionType_ProcessSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getSubprocessSymbol <em>Subprocess Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subprocess Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getSubprocessSymbol()
	 * @see #getSubProcessOfConnectionType()
	 * @generated
	 */
	EReference getSubProcessOfConnectionType_SubprocessSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType <em>Team Lead Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Team Lead Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType
	 * @generated
	 */
	EClass getTeamLeadConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Team Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamSymbol()
	 * @see #getTeamLeadConnectionType()
	 * @generated
	 */
	EReference getTeamLeadConnectionType_TeamSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Team Lead Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol()
	 * @see #getTeamLeadConnectionType()
	 * @generated
	 */
	EReference getTeamLeadConnectionType_TeamLeadSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType <em>Text Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Symbol Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType
	 * @generated
	 */
	EClass getTextSymbolType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType#getText()
	 * @see #getTextSymbolType()
	 * @generated
	 */
	EAttribute getTextSymbolType_Text();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextType <em>Text Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextType
	 * @generated
	 */
	EClass getTextType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextType#getMixed()
	 * @see #getTextType()
	 * @generated
	 */
	EAttribute getTextType_Mixed();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType <em>Transition Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType
	 * @generated
	 */
	EClass getTransitionConnectionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Points</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getPoints()
	 * @see #getTransitionConnectionType()
	 * @generated
	 */
	EAttribute getTransitionConnectionType_Points();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getSourceActivitySymbol <em>Source Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getSourceActivitySymbol()
	 * @see #getTransitionConnectionType()
	 * @generated
	 */
	EReference getTransitionConnectionType_SourceActivitySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTargetActivitySymbol <em>Target Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Activity Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTargetActivitySymbol()
	 * @see #getTransitionConnectionType()
	 * @generated
	 */
	EReference getTransitionConnectionType_TargetActivitySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTransition()
	 * @see #getTransitionConnectionType()
	 * @generated
	 */
	EReference getTransitionConnectionType_Transition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType <em>Transition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType
	 * @generated
	 */
	EClass getTransitionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getExpression()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getCondition()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Condition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal <em>Fork On Traversal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fork On Traversal</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_ForkOnTraversal();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_From();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_To();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTransitionConnections <em>Transition Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Transition Connections</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTransitionConnections()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_TransitionConnections();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType <em>Triggers Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Triggers Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType
	 * @generated
	 */
	EClass getTriggersConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Event Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol()
	 * @see #getTriggersConnectionType()
	 * @generated
	 */
	EReference getTriggersConnectionType_StartEventSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol()
	 * @see #getTriggersConnectionType()
	 * @generated
	 */
	EReference getTriggersConnectionType_ParticipantSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType <em>Trigger Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType
	 * @generated
	 */
	EClass getTriggerType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getParameterMapping <em>Parameter Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Mapping</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getParameterMapping()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_ParameterMapping();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getType()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Starting Event Symbols</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getStartingEventSymbols()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_StartingEventSymbols();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType <em>Trigger Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Type Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType
	 * @generated
	 */
	EClass getTriggerTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panel Class</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPanelClass()
	 * @see #getTriggerTypeType()
	 * @generated
	 */
	EAttribute getTriggerTypeType_PanelClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger <em>Pull Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pull Trigger</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger()
	 * @see #getTriggerTypeType()
	 * @generated
	 */
	EAttribute getTriggerTypeType_PullTrigger();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPullTriggerEvaluator <em>Pull Trigger Evaluator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pull Trigger Evaluator</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPullTriggerEvaluator()
	 * @see #getTriggerTypeType()
	 * @generated
	 */
	EAttribute getTriggerTypeType_PullTriggerEvaluator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getRule()
	 * @see #getTriggerTypeType()
	 * @generated
	 */
	EAttribute getTriggerTypeType_Rule();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggers</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getTriggers()
	 * @see #getTriggerTypeType()
	 * @generated
	 */
	EReference getTriggerTypeType_Triggers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.UnbindActionType <em>Unbind Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unbind Action Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.UnbindActionType
	 * @generated
	 */
	EClass getUnbindActionType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType <em>Viewable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Viewable Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType
	 * @generated
	 */
	EClass getViewableType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType#getViewable <em>Viewable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Viewable</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType#getViewable()
	 * @see #getViewableType()
	 * @generated
	 */
	EReference getViewableType_Viewable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType <em>View Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType
	 * @generated
	 */
	EClass getViewType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getDescription()
	 * @see #getViewType()
	 * @generated
	 */
	EReference getViewType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getView()
	 * @see #getViewType()
	 * @generated
	 */
	EReference getViewType_View();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getViewable <em>Viewable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Viewable</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getViewable()
	 * @see #getViewType()
	 * @generated
	 */
	EReference getViewType_Viewable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType#getName()
	 * @see #getViewType()
	 * @generated
	 */
	EAttribute getViewType_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType <em>Works For Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Works For Connection Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType
	 * @generated
	 */
	EClass getWorksForConnectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Organization Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getOrganizationSymbol()
	 * @see #getWorksForConnectionType()
	 * @generated
	 */
	EReference getWorksForConnectionType_OrganizationSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant Symbol</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getParticipantSymbol()
	 * @see #getWorksForConnectionType()
	 * @generated
	 */
	EReference getWorksForConnectionType_ParticipantSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.XmlTextNode <em>Xml Text Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Xml Text Node</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.XmlTextNode
	 * @generated
	 */
	EClass getXmlTextNode();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.XmlTextNode#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.XmlTextNode#getMixed()
	 * @see #getXmlTextNode()
	 * @generated
	 */
	EAttribute getXmlTextNode_Mixed();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType <em>Activity Implementation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Activity Implementation Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @generated
	 */
	EEnum getActivityImplementationType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType <em>Direction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType
	 * @generated
	 */
	EEnum getDirectionType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType <em>Flow Control Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Flow Control Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @generated
	 */
	EEnum getFlowControlType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType <em>Implementation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Implementation Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType
	 * @generated
	 */
	EEnum getImplementationType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType <em>Join Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Join Split Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @generated
	 */
	EEnum getJoinSplitType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality <em>Link Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link Cardinality</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @generated
	 */
	EEnum getLinkCardinality();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor <em>Link Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link Color</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @generated
	 */
	EEnum getLinkColor();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle <em>Link Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link Line Style</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @generated
	 */
	EEnum getLinkLineStyle();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle <em>Link End Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link End Style</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @generated
	 */
	EEnum getLinkEndStyle();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType <em>Loop Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Loop Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @generated
	 */
	EEnum getLoopType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType <em>Orientation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @generated
	 */
	EEnum getOrientationType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType <em>Routing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Routing Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType
	 * @generated
	 */
	EEnum getRoutingType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType <em>Sub Process Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sub Process Mode Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @generated
	 */
	EEnum getSubProcessModeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType <em>Diagram Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Diagram Mode Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType
	 * @generated
	 */
	EEnum getDiagramModeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Element Id</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 * @generated
	 */
	EDataType getElementId();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Feature List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Feature List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getFeatureList();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType <em>Activity Implementation Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Implementation Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType"
	 *        extendedMetaData="name='activityImplementation_._type:Object' baseType='activityImplementation_._type'"
	 * @generated
	 */
	EDataType getActivityImplementationTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType <em>Direction Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Direction Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DirectionType"
	 *        extendedMetaData="name='direction_._type:Object' baseType='direction_._type'"
	 * @generated
	 */
	EDataType getDirectionTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType <em>Flow Control Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Flow Control Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType"
	 *        extendedMetaData="name='flowControl_._type:Object' baseType='flowControl_._type'"
	 * @generated
	 */
	EDataType getFlowControlTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType <em>Implementation Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Implementation Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ImplementationType"
	 *        extendedMetaData="name='implementation_._type:Object' baseType='implementation_._type'"
	 * @generated
	 */
	EDataType getImplementationTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality <em>Link Cardinality Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Link Cardinality Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality"
	 *        extendedMetaData="name='linkCardinality_._type:Object' baseType='linkCardinality_._type'"
	 * @generated
	 */
	EDataType getLinkCardinalityObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor <em>Link Color Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Link Color Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor"
	 *        extendedMetaData="name='linkColor_._type:Object' baseType='linkColor_._type'"
	 * @generated
	 */
	EDataType getLinkColorObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle <em>Link Line Style Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Link Line Style Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle"
	 *        extendedMetaData="name='linkLineStyle_._type:Object' baseType='linkLineStyle_._type'"
	 * @generated
	 */
	EDataType getLinkLineStyleObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle <em>Link End Style Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Link End Style Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle"
	 *        extendedMetaData="name='linkEndStyle_._type:Object' baseType='linkEndStyle_._type'"
	 * @generated
	 */
	EDataType getLinkEndStyleObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType <em>Join Split Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Join Split Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType"
	 *        extendedMetaData="name='joinSplit_._type:Object' baseType='joinSplit_._type'"
	 * @generated
	 */
	EDataType getJoinSplitTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType <em>Loop Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Loop Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType"
	 *        extendedMetaData="name='loop_._type:Object' baseType='loop_._type'"
	 * @generated
	 */
	EDataType getLoopTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType <em>Orientation Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Orientation Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType"
	 *        extendedMetaData="name='orientation_._type:Object' baseType='orientation_._type'"
	 * @generated
	 */
	EDataType getOrientationTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType <em>Routing Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Routing Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType"
	 *        extendedMetaData="name='routing_._type:Object' baseType='routing_._type'"
	 * @generated
	 */
	EDataType getRoutingTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType <em>Sub Process Mode Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sub Process Mode Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType"
	 *        extendedMetaData="name='subProcessMode_._type:Object' baseType='subProcessMode_._type'"
	 * @generated
	 */
	EDataType getSubProcessModeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType <em>Diagram Mode Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Diagram Mode Type Object</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType
	 * @model instanceClass="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramModeType"
	 *        extendedMetaData="name='diagramMode_._type:Object' baseType='diagramMode_._type'"
	 * @generated
	 */
	EDataType getDiagramModeTypeObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CarnotFactory getCarnotFactory();

} //CarnotPackage
