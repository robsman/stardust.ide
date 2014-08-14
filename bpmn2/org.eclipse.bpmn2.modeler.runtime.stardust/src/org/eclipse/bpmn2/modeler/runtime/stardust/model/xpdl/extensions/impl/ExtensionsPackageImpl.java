/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.SdbpmnPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtendedAnnotationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.XpdlPackageImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xsd.XSDPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionsPackageImpl extends EPackageImpl implements ExtensionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAnnotationTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExtensionsPackageImpl() {
		super(eNS_URI, ExtensionsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExtensionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExtensionsPackage init() {
		if (isInited) return (ExtensionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI);

		// Obtain or create and register package
		ExtensionsPackageImpl theExtensionsPackage = (ExtensionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExtensionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExtensionsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XSDPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		SdbpmnPackageImpl theSdbpmnPackage = (SdbpmnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI) instanceof SdbpmnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI) : SdbpmnPackage.eINSTANCE);
		CarnotPackageImpl theCarnotPackage = (CarnotPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CarnotPackage.eNS_URI) instanceof CarnotPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CarnotPackage.eNS_URI) : CarnotPackage.eINSTANCE);
		org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.ExtensionsPackageImpl theExtensionsPackage_1 = (org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.ExtensionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage.eNS_URI) instanceof org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.ExtensionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage.eNS_URI) : org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage.eINSTANCE);
		XpdlPackageImpl theXpdlPackage = (XpdlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) instanceof XpdlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) : XpdlPackage.eINSTANCE);

		// Load packages
		theSdbpmnPackage.loadPackage();
		theCarnotPackage.loadPackage();

		// Create package meta-data objects
		theExtensionsPackage.createPackageContents();
		theXpdlPackage.createPackageContents();

		// Initialize created meta-data
		theExtensionsPackage.initializePackageContents();
		theXpdlPackage.initializePackageContents();

		// Fix loaded packages
		theSdbpmnPackage.fixPackageContents();
		theCarnotPackage.fixPackageContents();
		theExtensionsPackage_1.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theExtensionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExtensionsPackage.eNS_URI, theExtensionsPackage);
		return theExtensionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAnnotationType() {
		return extendedAnnotationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionsFactory getExtensionsFactory() {
		return (ExtensionsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		extendedAnnotationTypeEClass = createEClass(EXTENDED_ANNOTATION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XSDPackage theXSDPackage = (XSDPackage)EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		extendedAnnotationTypeEClass.getESuperTypes().add(theXSDPackage.getXSDAnnotation());

		// Initialize classes, features, and operations; add parameters
		initEClass(extendedAnnotationTypeEClass, ExtendedAnnotationType.class, "ExtendedAnnotationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (extendedAnnotationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExternalAnnotation_._type",
			 "kind", "empty"
		   });
	}

} //ExtensionsPackageImpl
