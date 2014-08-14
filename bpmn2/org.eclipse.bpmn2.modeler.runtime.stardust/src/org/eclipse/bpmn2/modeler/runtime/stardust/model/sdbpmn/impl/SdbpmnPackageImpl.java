/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.ExtensionsPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.CarnotPackageImpl;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnFactory;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.impl.XpdlPackageImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.xsd.XSDPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SdbpmnPackageImpl extends EPackageImpl implements SdbpmnPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String packageFilename = "sdbpmn.ecore";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustAccessPointTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustApplicationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustAttributesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustContextTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustInterfaceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustMessageStartEventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustModelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustResourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustSeqenceFlowTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustServiceTaskTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustStartEventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustSubprocessTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustTimerStartEventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustTriggerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stardustUserTaskTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tStardustActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tStardustCommonEClass = null;

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
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SdbpmnPackageImpl() {
		super(eNS_URI, SdbpmnFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SdbpmnPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static SdbpmnPackage init() {
		if (isInited) return (SdbpmnPackage)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI);

		// Obtain or create and register package
		SdbpmnPackageImpl theSdbpmnPackage = (SdbpmnPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SdbpmnPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SdbpmnPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XSDPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CarnotPackageImpl theCarnotPackage = (CarnotPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CarnotPackage.eNS_URI) instanceof CarnotPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CarnotPackage.eNS_URI) : CarnotPackage.eINSTANCE);
		ExtensionsPackageImpl theExtensionsPackage = (ExtensionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) instanceof ExtensionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) : ExtensionsPackage.eINSTANCE);
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
		theSdbpmnPackage.fixPackageContents();
		theCarnotPackage.fixPackageContents();
		theExtensionsPackage.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theSdbpmnPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SdbpmnPackage.eNS_URI, theSdbpmnPackage);
		return theSdbpmnPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		if (documentRootEClass == null) {
			documentRootEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(0);
		}
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataType() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustActivity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustAttributes() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustCommon() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustInterface() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustMessageStartEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustModel() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustResource() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustSeqenceFlow() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustServiceTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustStartEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustSubprocess() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustTimerStartEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustUserTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ApplicationAccessPointRef() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Author() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_CarnotVersion() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Created() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_InteractiveApplicationRef() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ModelOID() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Oid() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ParameterMappingOid() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_TriggerAccessPointRef() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Vendor() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustAccessPointType() {
		if (stardustAccessPointTypeEClass == null) {
			stardustAccessPointTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(1);
		}
		return stardustAccessPointTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustAccessPointType_TypeRef() {
        return (EAttribute)getStardustAccessPointType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustApplicationType() {
		if (stardustApplicationTypeEClass == null) {
			stardustApplicationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(2);
		}
		return stardustApplicationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustApplicationType_AccessPoint1() {
        return (EReference)getStardustApplicationType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustApplicationType_Context1() {
        return (EReference)getStardustApplicationType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustAttributesType() {
		if (stardustAttributesTypeEClass == null) {
			stardustAttributesTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(3);
		}
		return stardustAttributesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustAttributesType_AttributeType() {
        return (EReference)getStardustAttributesType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustContextType() {
		if (stardustContextTypeEClass == null) {
			stardustContextTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(4);
		}
		return stardustContextTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustContextType_TypeRef() {
        return (EAttribute)getStardustContextType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustInterfaceType() {
		if (stardustInterfaceTypeEClass == null) {
			stardustInterfaceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(5);
		}
		return stardustInterfaceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustInterfaceType_StardustApplication() {
        return (EReference)getStardustInterfaceType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustInterfaceType_StardustTrigger() {
        return (EReference)getStardustInterfaceType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustInterfaceType_ApplicationType() {
        return (EAttribute)getStardustInterfaceType().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustMessageStartEventType() {
		if (stardustMessageStartEventTypeEClass == null) {
			stardustMessageStartEventTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(6);
		}
		return stardustMessageStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustMessageStartEventType_StardustAttributes() {
        return (EReference)getStardustMessageStartEventType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustModelType() {
		if (stardustModelTypeEClass == null) {
			stardustModelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(7);
		}
		return stardustModelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Author() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_CarnotVersion() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Created() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_ModelOID() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Oid() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Vendor() {
        return (EAttribute)getStardustModelType().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustResourceType() {
		if (stardustResourceTypeEClass == null) {
			stardustResourceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(8);
		}
		return stardustResourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustConditionalPerformer() {
        return (EReference)getStardustResourceType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustRole() {
        return (EReference)getStardustResourceType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustOrganization() {
        return (EReference)getStardustResourceType().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustResourceType_DataId() {
        return (EAttribute)getStardustResourceType().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustSeqenceFlowType() {
		if (stardustSeqenceFlowTypeEClass == null) {
			stardustSeqenceFlowTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(9);
		}
		return stardustSeqenceFlowTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustSeqenceFlowType_ForkOnTraversal() {
        return (EAttribute)getStardustSeqenceFlowType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustServiceTaskType() {
		if (stardustServiceTaskTypeEClass == null) {
			stardustServiceTaskTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(10);
		}
		return stardustServiceTaskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustStartEventType() {
		if (stardustStartEventTypeEClass == null) {
			stardustStartEventTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(11);
		}
		return stardustStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustStartEventType_StardustAttributes() {
        return (EReference)getStardustStartEventType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustSubprocessType() {
		if (stardustSubprocessTypeEClass == null) {
			stardustSubprocessTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(12);
		}
		return stardustSubprocessTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustSubprocessType_ImplementationProcess() {
        return (EAttribute)getStardustSubprocessType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustTimerStartEventType() {
		if (stardustTimerStartEventTypeEClass == null) {
			stardustTimerStartEventTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(13);
		}
		return stardustTimerStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustTimerStartEventType_StardustAttributes() {
        return (EReference)getStardustTimerStartEventType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustTriggerType() {
		if (stardustTriggerTypeEClass == null) {
			stardustTriggerTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(14);
		}
		return stardustTriggerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustTriggerType_AccessPoint1() {
        return (EReference)getStardustTriggerType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustTriggerType_Context() {
        return (EReference)getStardustTriggerType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustUserTaskType() {
		if (stardustUserTaskTypeEClass == null) {
			stardustUserTaskTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(15);
		}
		return stardustUserTaskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustUserTaskType_AllowsAbortByPerformer() {
        return (EAttribute)getStardustUserTaskType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustUserTaskType_InteractiveApplicationRef() {
        return (EAttribute)getStardustUserTaskType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStardustActivity() {
		if (tStardustActivityEClass == null) {
			tStardustActivityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(16);
		}
		return tStardustActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTStardustActivity_EventHandler() {
        return (EReference)getTStardustActivity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStardustActivity_HibernateOnCreation() {
        return (EAttribute)getTStardustActivity().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStardustCommon() {
		if (tStardustCommonEClass == null) {
			tStardustCommonEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI).getEClassifiers().get(17);
		}
		return tStardustCommonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStardustCommon_ElementOid() {
        return (EAttribute)getTStardustCommon().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnFactory getSdbpmnFactory() {
		return (SdbpmnFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
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
			eClassifier.setInstanceClassName("org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //SdbpmnPackageImpl
