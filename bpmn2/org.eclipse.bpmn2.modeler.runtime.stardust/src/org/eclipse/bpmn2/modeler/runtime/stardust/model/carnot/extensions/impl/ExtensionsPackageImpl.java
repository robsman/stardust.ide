/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.SdbpmnPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.XpdlPackageImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
	private EClass formalParameterMappingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formalParameterMappingsTypeEClass = null;

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
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#eNS_URI
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
		XpdlPackageImpl theXpdlPackage = (XpdlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) instanceof XpdlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) : XpdlPackage.eINSTANCE);
		org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl theExtensionsPackage_1 = (org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsPackage.eNS_URI) instanceof org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.impl.ExtensionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsPackage.eNS_URI) : org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.extensions.ExtensionsPackage.eINSTANCE);

		// Load packages
		theSdbpmnPackage.loadPackage();
		theCarnotPackage.loadPackage();

		// Create package meta-data objects
		theXpdlPackage.createPackageContents();
		theExtensionsPackage_1.createPackageContents();

		// Initialize created meta-data
		theXpdlPackage.initializePackageContents();
		theExtensionsPackage_1.initializePackageContents();

		// Fix loaded packages
		theExtensionsPackage.fixPackageContents();
		theSdbpmnPackage.fixPackageContents();
		theCarnotPackage.fixPackageContents();

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
	public EClass getFormalParameterMappingType() {
		if (formalParameterMappingTypeEClass == null) {
			formalParameterMappingTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI).getEClassifiers().get(0);
		}
		return formalParameterMappingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormalParameterMappingType_Data() {
        return (EReference)getFormalParameterMappingType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormalParameterMappingType_Parameter() {
        return (EReference)getFormalParameterMappingType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormalParameterMappingsType() {
		if (formalParameterMappingsTypeEClass == null) {
			formalParameterMappingsTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI).getEClassifiers().get(1);
		}
		return formalParameterMappingsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormalParameterMappingsType_Mapping() {
        return (EReference)getFormalParameterMappingsType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFormalParameterMappingsType__GetMappedData__FormalParameterType() {
        return getFormalParameterMappingsType().getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFormalParameterMappingsType__SetMappedData__FormalParameterType_DataType() {
        return getFormalParameterMappingsType().getEOperations().get(1);
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
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //ExtensionsPackageImpl
