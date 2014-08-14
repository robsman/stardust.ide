/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.xsd.XSDPackage;

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
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsFactory
 * @model kind="package"
 * @generated
 */
public interface ExtensionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "extensions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ext";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtensionsPackage eINSTANCE = org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtendedAnnotationTypeImpl <em>Extended Annotation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtendedAnnotationTypeImpl
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl#getExtendedAnnotationType()
	 * @generated
	 */
	int EXTENDED_ANNOTATION_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__ELEMENT = XSDPackage.XSD_ANNOTATION__ELEMENT;

	/**
	 * The feature id for the '<em><b>Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__CONTAINER = XSDPackage.XSD_ANNOTATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Root Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__ROOT_CONTAINER = XSDPackage.XSD_ANNOTATION__ROOT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__SCHEMA = XSDPackage.XSD_ANNOTATION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Diagnostics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__DIAGNOSTICS = XSDPackage.XSD_ANNOTATION__DIAGNOSTICS;

	/**
	 * The feature id for the '<em><b>Application Information</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__APPLICATION_INFORMATION = XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION;

	/**
	 * The feature id for the '<em><b>User Information</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__USER_INFORMATION = XSDPackage.XSD_ANNOTATION__USER_INFORMATION;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE__ATTRIBUTES = XSDPackage.XSD_ANNOTATION__ATTRIBUTES;

	/**
	 * The number of structural features of the '<em>Extended Annotation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ANNOTATION_TYPE_FEATURE_COUNT = XSDPackage.XSD_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Extended Annotation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * @ordered
	 * FIXME: Why does this keep getting generated? It references an undefined value in XSDPackage,
	 * and this thing is not even used anywhere. Here's the original generated value:
	 * 	int EXTENDED_ANNOTATION_TYPE_OPERATION_COUNT = XSDPackage.XSD_ANNOTATION_OPERATION_COUNT + 0;
	 */
	int EXTENDED_ANNOTATION_TYPE_OPERATION_COUNT = 0;

	/**
	 * The number of operations of the '<em>Extended Annotation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
//	int EXTENDED_ANNOTATION_TYPE_OPERATION_COUNT = XSDPackage.XSD_ANNOTATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtendedAnnotationType <em>Extended Annotation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Annotation Type</em>'.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtendedAnnotationType
	 * @generated
	 */
	EClass getExtendedAnnotationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExtensionsFactory getExtensionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtendedAnnotationTypeImpl <em>Extended Annotation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtendedAnnotationTypeImpl
		 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl#getExtendedAnnotationType()
		 * @generated
		 */
		EClass EXTENDED_ANNOTATION_TYPE = eINSTANCE.getExtendedAnnotationType();

	}

} //ExtensionsPackage
