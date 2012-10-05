/**
 * ****************************************************************************
 *  Copyright (c) 2012 ITpearls AG and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     ITpearls - initial API and implementation and/or initial documentation
 * *****************************************************************************
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSubprocessType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity;
import org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon;

import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;

import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;

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
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#eNS_URI
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
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SdbpmnPackage init() {
		if (isInited) return (SdbpmnPackage)EPackage.Registry.INSTANCE.getEPackage(SdbpmnPackage.eNS_URI);

		// Obtain or create and register package
		SdbpmnPackageImpl theSdbpmnPackage = (SdbpmnPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SdbpmnPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SdbpmnPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CarnotWorkflowModelPackage.eINSTANCE.eClass();
		ExtensionPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSdbpmnPackage.createPackageContents();

		// Initialize created meta-data
		theSdbpmnPackage.initializePackageContents();

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
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustActivity() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustAttributes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustCommon() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustInterface() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustMessageStartEvent() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustModel() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustResource() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustSeqenceFlow() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustServiceTask() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustStartEvent() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustSubprocess() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustTimerStartEvent() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StardustUserTask() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ApplicationAccessPointRef() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Author() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_CarnotVersion() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Created() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_InteractiveApplicationRef() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ModelOID() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Oid() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Vendor() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustAccessPointType() {
		return stardustAccessPointTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustAccessPointType_TypeRef() {
		return (EAttribute)stardustAccessPointTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustApplicationType() {
		return stardustApplicationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustApplicationType_AccessPoint1() {
		return (EReference)stardustApplicationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustApplicationType_Context1() {
		return (EReference)stardustApplicationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustAttributesType() {
		return stardustAttributesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustAttributesType_AttributeType() {
		return (EReference)stardustAttributesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustContextType() {
		return stardustContextTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustContextType_TypeRef() {
		return (EAttribute)stardustContextTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustInterfaceType() {
		return stardustInterfaceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustInterfaceType_StardustApplication() {
		return (EReference)stardustInterfaceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustInterfaceType_ApplicationType() {
		return (EAttribute)stardustInterfaceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustMessageStartEventType() {
		return stardustMessageStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustMessageStartEventType_StardustAttributes() {
		return (EReference)stardustMessageStartEventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustMessageStartEventType_AccessPoint() {
		return (EReference)stardustMessageStartEventTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustMessageStartEventType_ParameterMapping() {
		return (EReference)stardustMessageStartEventTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustMessageStartEventType_Type() {
		return (EAttribute)stardustMessageStartEventTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustModelType() {
		return stardustModelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Author() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_CarnotVersion() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Created() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_ModelOID() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Oid() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustModelType_Vendor() {
		return (EAttribute)stardustModelTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustResourceType() {
		return stardustResourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustConditionalPerformer() {
		return (EReference)stardustResourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustRole() {
		return (EReference)stardustResourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustResourceType_StardustOrganization() {
		return (EReference)stardustResourceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustResourceType_DataId() {
		return (EAttribute)stardustResourceTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustSeqenceFlowType() {
		return stardustSeqenceFlowTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustSeqenceFlowType_ForkOnTraversal() {
		return (EAttribute)stardustSeqenceFlowTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustServiceTaskType() {
		return stardustServiceTaskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustStartEventType() {
		return stardustStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustStartEventType_StardustAttributes() {
		return (EReference)stardustStartEventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustSubprocessType() {
		return stardustSubprocessTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustSubprocessType_ImplementationProcess() {
		return (EAttribute)stardustSubprocessTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustTimerStartEventType() {
		return stardustTimerStartEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStardustTimerStartEventType_StardustAttributes() {
		return (EReference)stardustTimerStartEventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStardustUserTaskType() {
		return stardustUserTaskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustUserTaskType_AllowsAbortByPerformer() {
		return (EAttribute)stardustUserTaskTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStardustUserTaskType_InteractiveApplicationRef() {
		return (EAttribute)stardustUserTaskTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStardustActivity() {
		return tStardustActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTStardustActivity_EventHandler() {
		return (EReference)tStardustActivityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStardustActivity_HibernateOnCreation() {
		return (EAttribute)tStardustActivityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStardustCommon() {
		return tStardustCommonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStardustCommon_ElementOid() {
		return (EAttribute)tStardustCommonEClass.getEStructuralFeatures().get(0);
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
		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_ACTIVITY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_ATTRIBUTES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_COMMON);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_INTERFACE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_MODEL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_RESOURCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_SERVICE_TASK);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_START_EVENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_SUBPROCESS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STARDUST_USER_TASK);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__AUTHOR);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__CARNOT_VERSION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__CREATED);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MODEL_OID);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__OID);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VENDOR);

		stardustAccessPointTypeEClass = createEClass(STARDUST_ACCESS_POINT_TYPE);
		createEAttribute(stardustAccessPointTypeEClass, STARDUST_ACCESS_POINT_TYPE__TYPE_REF);

		stardustApplicationTypeEClass = createEClass(STARDUST_APPLICATION_TYPE);
		createEReference(stardustApplicationTypeEClass, STARDUST_APPLICATION_TYPE__ACCESS_POINT1);
		createEReference(stardustApplicationTypeEClass, STARDUST_APPLICATION_TYPE__CONTEXT1);

		stardustAttributesTypeEClass = createEClass(STARDUST_ATTRIBUTES_TYPE);
		createEReference(stardustAttributesTypeEClass, STARDUST_ATTRIBUTES_TYPE__ATTRIBUTE_TYPE);

		stardustContextTypeEClass = createEClass(STARDUST_CONTEXT_TYPE);
		createEAttribute(stardustContextTypeEClass, STARDUST_CONTEXT_TYPE__TYPE_REF);

		stardustInterfaceTypeEClass = createEClass(STARDUST_INTERFACE_TYPE);
		createEReference(stardustInterfaceTypeEClass, STARDUST_INTERFACE_TYPE__STARDUST_APPLICATION);
		createEAttribute(stardustInterfaceTypeEClass, STARDUST_INTERFACE_TYPE__APPLICATION_TYPE);

		stardustMessageStartEventTypeEClass = createEClass(STARDUST_MESSAGE_START_EVENT_TYPE);
		createEReference(stardustMessageStartEventTypeEClass, STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES);
		createEReference(stardustMessageStartEventTypeEClass, STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT);
		createEReference(stardustMessageStartEventTypeEClass, STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING);
		createEAttribute(stardustMessageStartEventTypeEClass, STARDUST_MESSAGE_START_EVENT_TYPE__TYPE);

		stardustModelTypeEClass = createEClass(STARDUST_MODEL_TYPE);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__AUTHOR);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__CARNOT_VERSION);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__CREATED);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__MODEL_OID);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__OID);
		createEAttribute(stardustModelTypeEClass, STARDUST_MODEL_TYPE__VENDOR);

		stardustResourceTypeEClass = createEClass(STARDUST_RESOURCE_TYPE);
		createEReference(stardustResourceTypeEClass, STARDUST_RESOURCE_TYPE__STARDUST_CONDITIONAL_PERFORMER);
		createEReference(stardustResourceTypeEClass, STARDUST_RESOURCE_TYPE__STARDUST_ROLE);
		createEReference(stardustResourceTypeEClass, STARDUST_RESOURCE_TYPE__STARDUST_ORGANIZATION);
		createEAttribute(stardustResourceTypeEClass, STARDUST_RESOURCE_TYPE__DATA_ID);

		stardustSeqenceFlowTypeEClass = createEClass(STARDUST_SEQENCE_FLOW_TYPE);
		createEAttribute(stardustSeqenceFlowTypeEClass, STARDUST_SEQENCE_FLOW_TYPE__FORK_ON_TRAVERSAL);

		stardustServiceTaskTypeEClass = createEClass(STARDUST_SERVICE_TASK_TYPE);

		stardustStartEventTypeEClass = createEClass(STARDUST_START_EVENT_TYPE);
		createEReference(stardustStartEventTypeEClass, STARDUST_START_EVENT_TYPE__STARDUST_ATTRIBUTES);

		stardustSubprocessTypeEClass = createEClass(STARDUST_SUBPROCESS_TYPE);
		createEAttribute(stardustSubprocessTypeEClass, STARDUST_SUBPROCESS_TYPE__IMPLEMENTATION_PROCESS);

		stardustTimerStartEventTypeEClass = createEClass(STARDUST_TIMER_START_EVENT_TYPE);
		createEReference(stardustTimerStartEventTypeEClass, STARDUST_TIMER_START_EVENT_TYPE__STARDUST_ATTRIBUTES);

		stardustUserTaskTypeEClass = createEClass(STARDUST_USER_TASK_TYPE);
		createEAttribute(stardustUserTaskTypeEClass, STARDUST_USER_TASK_TYPE__ALLOWS_ABORT_BY_PERFORMER);
		createEAttribute(stardustUserTaskTypeEClass, STARDUST_USER_TASK_TYPE__INTERACTIVE_APPLICATION_REF);

		tStardustActivityEClass = createEClass(TSTARDUST_ACTIVITY);
		createEReference(tStardustActivityEClass, TSTARDUST_ACTIVITY__EVENT_HANDLER);
		createEAttribute(tStardustActivityEClass, TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION);

		tStardustCommonEClass = createEClass(TSTARDUST_COMMON);
		createEAttribute(tStardustCommonEClass, TSTARDUST_COMMON__ELEMENT_OID);
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
		CarnotWorkflowModelPackage theCarnotWorkflowModelPackage = (CarnotWorkflowModelPackage)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stardustAccessPointTypeEClass.getESuperTypes().add(theCarnotWorkflowModelPackage.getAccessPointType());
		stardustApplicationTypeEClass.getESuperTypes().add(theCarnotWorkflowModelPackage.getApplicationType());
		stardustContextTypeEClass.getESuperTypes().add(theCarnotWorkflowModelPackage.getContextType());
		stardustServiceTaskTypeEClass.getESuperTypes().add(this.getTStardustActivity());
		stardustSubprocessTypeEClass.getESuperTypes().add(this.getTStardustActivity());
		stardustUserTaskTypeEClass.getESuperTypes().add(this.getTStardustActivity());
		tStardustActivityEClass.getESuperTypes().add(this.getTStardustCommon());

		// Initialize classes and features; add operations and parameters
		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DataType(), theCarnotWorkflowModelPackage.getDataTypeType(), null, "dataType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustActivity(), this.getTStardustActivity(), null, "stardustActivity", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustAttributes(), this.getStardustAttributesType(), null, "stardustAttributes", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustCommon(), this.getTStardustCommon(), null, "stardustCommon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustInterface(), this.getStardustInterfaceType(), null, "stardustInterface", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustMessageStartEvent(), this.getStardustMessageStartEventType(), null, "stardustMessageStartEvent", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustModel(), this.getStardustModelType(), null, "stardustModel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustResource(), this.getStardustResourceType(), null, "stardustResource", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustSeqenceFlow(), this.getStardustSeqenceFlowType(), null, "stardustSeqenceFlow", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustServiceTask(), this.getStardustServiceTaskType(), null, "stardustServiceTask", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustStartEvent(), this.getStardustStartEventType(), null, "stardustStartEvent", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustSubprocess(), this.getStardustSubprocessType(), null, "stardustSubprocess", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustTimerStartEvent(), this.getStardustTimerStartEventType(), null, "stardustTimerStartEvent", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StardustUserTask(), this.getStardustUserTaskType(), null, "stardustUserTask", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ApplicationAccessPointRef(), theXMLTypePackage.getString(), "applicationAccessPointRef", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Author(), theXMLTypePackage.getString(), "author", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_CarnotVersion(), theXMLTypePackage.getString(), "carnotVersion", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Created(), theXMLTypePackage.getDateTime(), "created", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_InteractiveApplicationRef(), theXMLTypePackage.getString(), "interactiveApplicationRef", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ModelOID(), theXMLTypePackage.getInteger(), "modelOID", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Oid(), theXMLTypePackage.getLong(), "oid", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Vendor(), theXMLTypePackage.getString(), "vendor", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustAccessPointTypeEClass, StardustAccessPointType.class, "StardustAccessPointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustAccessPointType_TypeRef(), theXMLTypePackage.getString(), "typeRef", null, 0, 1, StardustAccessPointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustApplicationTypeEClass, StardustApplicationType.class, "StardustApplicationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustApplicationType_AccessPoint1(), this.getStardustAccessPointType(), null, "accessPoint1", null, 0, -1, StardustApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStardustApplicationType_Context1(), this.getStardustContextType(), null, "context1", null, 0, -1, StardustApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustAttributesTypeEClass, StardustAttributesType.class, "StardustAttributesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustAttributesType_AttributeType(), theCarnotWorkflowModelPackage.getAttributeType(), null, "attributeType", null, 0, -1, StardustAttributesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustContextTypeEClass, StardustContextType.class, "StardustContextType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustContextType_TypeRef(), theXMLTypePackage.getString(), "typeRef", null, 0, 1, StardustContextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustInterfaceTypeEClass, StardustInterfaceType.class, "StardustInterfaceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustInterfaceType_StardustApplication(), this.getStardustApplicationType(), null, "stardustApplication", null, 0, 1, StardustInterfaceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustInterfaceType_ApplicationType(), theXMLTypePackage.getString(), "applicationType", null, 0, 1, StardustInterfaceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustMessageStartEventTypeEClass, StardustMessageStartEventType.class, "StardustMessageStartEventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustMessageStartEventType_StardustAttributes(), this.getStardustAttributesType(), null, "stardustAttributes", null, 1, 1, StardustMessageStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStardustMessageStartEventType_AccessPoint(), theCarnotWorkflowModelPackage.getAccessPointType(), null, "accessPoint", null, 0, -1, StardustMessageStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStardustMessageStartEventType_ParameterMapping(), theCarnotWorkflowModelPackage.getParameterMappingType(), null, "parameterMapping", null, 0, -1, StardustMessageStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustMessageStartEventType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, StardustMessageStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustModelTypeEClass, StardustModelType.class, "StardustModelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustModelType_Author(), theXMLTypePackage.getString(), "author", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustModelType_CarnotVersion(), theXMLTypePackage.getString(), "carnotVersion", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustModelType_Created(), theXMLTypePackage.getDateTime(), "created", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustModelType_ModelOID(), theXMLTypePackage.getInteger(), "modelOID", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustModelType_Oid(), theXMLTypePackage.getLong(), "oid", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustModelType_Vendor(), theXMLTypePackage.getString(), "vendor", null, 0, 1, StardustModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustResourceTypeEClass, StardustResourceType.class, "StardustResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustResourceType_StardustConditionalPerformer(), theCarnotWorkflowModelPackage.getConditionalPerformerType(), null, "stardustConditionalPerformer", null, 0, 1, StardustResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStardustResourceType_StardustRole(), theCarnotWorkflowModelPackage.getRoleType(), null, "stardustRole", null, 0, 1, StardustResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStardustResourceType_StardustOrganization(), theCarnotWorkflowModelPackage.getOrganizationType(), null, "stardustOrganization", null, 0, 1, StardustResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustResourceType_DataId(), theXMLTypePackage.getString(), "dataId", null, 0, 1, StardustResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustSeqenceFlowTypeEClass, StardustSeqenceFlowType.class, "StardustSeqenceFlowType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustSeqenceFlowType_ForkOnTraversal(), theXMLTypePackage.getBoolean(), "forkOnTraversal", "false", 0, 1, StardustSeqenceFlowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustServiceTaskTypeEClass, StardustServiceTaskType.class, "StardustServiceTaskType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stardustStartEventTypeEClass, StardustStartEventType.class, "StardustStartEventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustStartEventType_StardustAttributes(), this.getStardustAttributesType(), null, "stardustAttributes", null, 1, 1, StardustStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustSubprocessTypeEClass, StardustSubprocessType.class, "StardustSubprocessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustSubprocessType_ImplementationProcess(), theXMLTypePackage.getString(), "implementationProcess", null, 0, 1, StardustSubprocessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustTimerStartEventTypeEClass, StardustTimerStartEventType.class, "StardustTimerStartEventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStardustTimerStartEventType_StardustAttributes(), this.getStardustAttributesType(), null, "stardustAttributes", null, 1, 1, StardustTimerStartEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stardustUserTaskTypeEClass, StardustUserTaskType.class, "StardustUserTaskType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStardustUserTaskType_AllowsAbortByPerformer(), theXMLTypePackage.getBoolean(), "allowsAbortByPerformer", null, 0, 1, StardustUserTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStardustUserTaskType_InteractiveApplicationRef(), theXMLTypePackage.getString(), "interactiveApplicationRef", null, 0, 1, StardustUserTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tStardustActivityEClass, TStardustActivity.class, "TStardustActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTStardustActivity_EventHandler(), theCarnotWorkflowModelPackage.getEventHandlerType(), null, "eventHandler", null, 0, -1, TStardustActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTStardustActivity_HibernateOnCreation(), theXMLTypePackage.getBoolean(), "hibernateOnCreation", null, 0, 1, TStardustActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tStardustCommonEClass, TStardustCommon.class, "TStardustCommon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTStardustCommon_ElementOid(), theXMLTypePackage.getString(), "elementOid", null, 1, 1, TStardustCommon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

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
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_DataType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "dataType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustActivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustActivity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustCommon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustCommon",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustInterface(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustInterface",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustMessageStartEvent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustMessageStartEvent",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustModel(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustModel",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustResource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustResource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustSeqenceFlow(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustSeqenceFlow",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustServiceTask(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustServiceTask",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustStartEvent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustStartEvent",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustSubprocess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustSubprocess",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustTimerStartEvent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustTimerStartEvent",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StardustUserTask(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustUserTask",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ApplicationAccessPointRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "applicationAccessPointRef",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Author(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_CarnotVersion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "carnotVersion",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Created(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "created",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_InteractiveApplicationRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "interactiveApplicationRef",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_ModelOID(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "modelOID",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Oid(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "oid",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Vendor(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "vendor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (stardustAccessPointTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustAccessPointType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustAccessPointType_TypeRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "typeRef"
		   });		
		addAnnotation
		  (stardustApplicationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustApplicationType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustApplicationType_AccessPoint1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "accessPoint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStardustApplicationType_Context1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "context",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (stardustAttributesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustAttributes_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustAttributesType_AttributeType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AttributeType",
			 "namespace", "http://www.carnot.ag/workflowmodel/3.1"
		   });		
		addAnnotation
		  (stardustContextTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustContextType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustContextType_TypeRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "typeRef"
		   });		
		addAnnotation
		  (stardustInterfaceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustInterface_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustInterfaceType_StardustApplication(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustApplication",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStardustInterfaceType_ApplicationType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "applicationType"
		   });		
		addAnnotation
		  (stardustMessageStartEventTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustMessageStartEvent_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustMessageStartEventType_StardustAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustAttributes",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustMessageStartEventType_AccessPoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "accessPoint",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustMessageStartEventType_ParameterMapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "parameterMapping",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustMessageStartEventType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (stardustModelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustModel_._type",
			 "kind", "empty"
		   });			
		addAnnotation
		  (getStardustModelType_Author(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustModelType_CarnotVersion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "carnotVersion",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustModelType_Created(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "created",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustModelType_ModelOID(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "modelOID",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustModelType_Oid(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "oid",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getStardustModelType_Vendor(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "vendor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (stardustResourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustResource_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustResourceType_StardustConditionalPerformer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustConditionalPerformer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStardustResourceType_StardustRole(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustRole",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStardustResourceType_StardustOrganization(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustOrganization",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStardustResourceType_DataId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "dataId"
		   });		
		addAnnotation
		  (stardustSeqenceFlowTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustSeqenceFlow_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getStardustSeqenceFlowType_ForkOnTraversal(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "forkOnTraversal"
		   });		
		addAnnotation
		  (stardustServiceTaskTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustServiceTask_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (stardustStartEventTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustStartEvent_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustStartEventType_StardustAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (stardustSubprocessTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustSubprocess_._type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getStardustSubprocessType_ImplementationProcess(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "implementationProcess"
		   });		
		addAnnotation
		  (stardustTimerStartEventTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustTimerStartEvent_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStardustTimerStartEventType_StardustAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StardustAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (stardustUserTaskTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StardustUserTask_._type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getStardustUserTaskType_AllowsAbortByPerformer(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "allowsAbortByPerformer"
		   });		
		addAnnotation
		  (getStardustUserTaskType_InteractiveApplicationRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "interactiveApplicationRef"
		   });		
		addAnnotation
		  (tStardustActivityEClass, 
		   source, 
		   new String[] {
			 "name", "tStardustActivity",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getTStardustActivity_EventHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventHandler",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getTStardustActivity_HibernateOnCreation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "hibernateOnCreation"
		   });		
		addAnnotation
		  (tStardustCommonEClass, 
		   source, 
		   new String[] {
			 "name", "tStardustCommon",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTStardustCommon_ElementOid(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "elementOid"
		   });
	}

} //SdbpmnPackageImpl
