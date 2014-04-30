/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.io.IOException;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage;
import org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl;
import org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl;
import org.eclipse.xsd.XSDPackage;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CarnotWorkflowModelPackageImpl extends EPackageImpl
      implements CarnotWorkflowModelPackage
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected String packageFilename = "carnot.ecore"; //$NON-NLS-1$

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass coordinatesEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass accessPointTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass activitySymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass activityTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass annotationSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass applicationContextTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass applicationSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass applicationTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass applicationTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass attributeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass bindActionTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass codeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass conditionalPerformerSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass conditionalPerformerTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass contextTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataMappingConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataMappingTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataPathTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass dataTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass descriptionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass diagramTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass documentRootEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass endEventSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass eventActionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass eventActionTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass eventConditionTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass eventHandlerTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass executedByConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass idRefEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass gatewaySymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass genericLinkConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass groupSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass intermediateEventSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass laneSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iIdentifiableElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iExtensibleElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass identifiableReferenceEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iIdentifiableModelElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iEventHandlerOwnerEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iAccessPointOwnerEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iMetaTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iTypedElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iSymbolContainerEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iGraphicalObjectEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iNodeSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iSwimlaneSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iModelElementNodeSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iFlowObjectSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iConnectionSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iModelElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass linkTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass modelerSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass modelerTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass modelTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass organizationSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass organizationTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass parameterMappingTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass participantTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass partOfConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass performsConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass poolSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass processDefinitionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass processSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass publicInterfaceSymbolEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass qualityControlTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass refersToConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass roleSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass roleTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass startEventSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass subProcessOfConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass textSymbolTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass textTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass transitionConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass transitionTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass triggersConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass triggerTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass triggerTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass unbindActionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass viewableTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass viewTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass worksForConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass xmlTextNodeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass teamLeadConnectionTypeEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass abstractEventActionEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum activityImplementationTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum directionTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum flowControlTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iModelParticipantEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass iModelParticipantSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass abstractEventSymbolEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum implementationTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum joinSplitTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum linkCardinalityEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum linkColorEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum linkLineStyleEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum linkEndStyleEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum loopTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum orientationTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum routingTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum subProcessModeTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum diagramModeTypeEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType elementIdEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType featureListEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType activityImplementationTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType directionTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType flowControlTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType implementationTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType joinSplitTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType linkCardinalityObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType linkColorObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType linkLineStyleObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType linkEndStyleObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType loopTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType orientationTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType routingTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EDataType subProcessModeTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EDataType diagramModeTypeObjectEDataType = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private CarnotWorkflowModelPackageImpl() {
      super(eNS_URI, CarnotWorkflowModelFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    *
    * <p>This method is used to initialize {@link CarnotWorkflowModelPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #eNS_URI
    * @generated
    */
   public static CarnotWorkflowModelPackage init() {
      if (isInited) return (CarnotWorkflowModelPackage)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI);

      // Obtain or create and register package
      CarnotWorkflowModelPackageImpl theCarnotWorkflowModelPackage = (CarnotWorkflowModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CarnotWorkflowModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CarnotWorkflowModelPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      XpdlPackage.eINSTANCE.eClass();
      XMLTypePackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      ExtensionsPackageImpl theExtensionsPackage = (ExtensionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) instanceof ExtensionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) : ExtensionsPackage.eINSTANCE);

      // Load packages
      theCarnotWorkflowModelPackage.loadPackage();

      // Fix loaded packages
      theCarnotWorkflowModelPackage.fixPackageContents();
      theExtensionsPackage.fixPackageContents();

      // Mark meta-data to indicate it can't be changed
      theCarnotWorkflowModelPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(CarnotWorkflowModelPackage.eNS_URI, theCarnotWorkflowModelPackage);
      return theCarnotWorkflowModelPackage;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getCoordinates() {
      if (coordinatesEClass == null)
      {
         coordinatesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(0);
      }
      return coordinatesEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getCoordinates_XPos() {
        return (EAttribute)getCoordinates().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getCoordinates_YPos() {
        return (EAttribute)getCoordinates().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getAccessPointType() {
      if (accessPointTypeEClass == null)
      {
         accessPointTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(21);
      }
      return accessPointTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAccessPointType_Direction() {
        return (EAttribute)getAccessPointType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getAccessPointType_Type() {
        return (EReference)getAccessPointType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getActivitySymbolType() {
      if (activitySymbolTypeEClass == null)
      {
         activitySymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(22);
      }
      return activitySymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivitySymbolType_Activity() {
        return (EReference)getActivitySymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivitySymbolType_PerformsConnections() {
        return (EReference)getActivitySymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivitySymbolType_ExecutedByConnections() {
        return (EReference)getActivitySymbolType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivitySymbolType_DataMappings() {
        return (EReference)getActivitySymbolType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivitySymbolType_GatewaySymbols() {
        return (EReference)getActivitySymbolType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getActivityType() {
      if (activityTypeEClass == null)
      {
         activityTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(23);
      }
      return activityTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_DataMapping() {
        return (EReference)getActivityType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_AllowsAbortByPerformer() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_Application() {
        return (EReference)getActivityType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_HibernateOnCreation() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_Implementation() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_ImplementationProcess() {
        return (EReference)getActivityType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_Join() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_LoopCondition() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_LoopType() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_Performer() {
        return (EReference)getActivityType().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_QualityControlPerformer()
   {
        return (EReference)getActivityType().getEStructuralFeatures().get(10);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_Split() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(11);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getActivityType_SubProcessMode() {
        return (EAttribute)getActivityType().getEStructuralFeatures().get(12);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_ActivitySymbols() {
        return (EReference)getActivityType().getEStructuralFeatures().get(13);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_StartingEventSymbols() {
        return (EReference)getActivityType().getEStructuralFeatures().get(14);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_InTransitions() {
        return (EReference)getActivityType().getEStructuralFeatures().get(15);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_OutTransitions() {
        return (EReference)getActivityType().getEStructuralFeatures().get(16);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_ExternalRef()
   {
        return (EReference)getActivityType().getEStructuralFeatures().get(17);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_ValidQualityCodes()
   {
        return (EReference)getActivityType().getEStructuralFeatures().get(18);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getActivityType_Loop()
   {
        return (EReference)getActivityType().getEStructuralFeatures().get(19);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getAnnotationSymbolType() {
      if (annotationSymbolTypeEClass == null)
      {
         annotationSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(24);
      }
      return annotationSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getAnnotationSymbolType_Text() {
        return (EReference)getAnnotationSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getApplicationContextTypeType() {
      if (applicationContextTypeTypeEClass == null)
      {
         applicationContextTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(25);
      }
      return applicationContextTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationContextTypeType_AccessPointProviderClass() {
        return (EAttribute)getApplicationContextTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationContextTypeType_HasApplicationPath() {
        return (EAttribute)getApplicationContextTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationContextTypeType_HasMappingId() {
        return (EAttribute)getApplicationContextTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationContextTypeType_PanelClass() {
        return (EAttribute)getApplicationContextTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationContextTypeType_ValidatorClass() {
        return (EAttribute)getApplicationContextTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationContextTypeType_Contexts() {
        return (EReference)getApplicationContextTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getApplicationSymbolType() {
      if (applicationSymbolTypeEClass == null)
      {
         applicationSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(26);
      }
      return applicationSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationSymbolType_ExecutingActivities() {
        return (EReference)getApplicationSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationSymbolType_Application() {
        return (EReference)getApplicationSymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getApplicationType() {
      if (applicationTypeEClass == null)
      {
         applicationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(27);
      }
      return applicationTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationType_Context() {
        return (EReference)getApplicationType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationType_Interactive() {
        return (EAttribute)getApplicationType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationType_Type() {
        return (EReference)getApplicationType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationType_ExecutedActivities() {
        return (EReference)getApplicationType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationType_ApplicationSymbols() {
        return (EReference)getApplicationType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getApplicationTypeType() {
      if (applicationTypeTypeEClass == null)
      {
         applicationTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(28);
      }
      return applicationTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationTypeType_AccessPointProviderClass() {
        return (EAttribute)getApplicationTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationTypeType_InstanceClass() {
        return (EAttribute)getApplicationTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationTypeType_PanelClass() {
        return (EAttribute)getApplicationTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationTypeType_Synchronous() {
        return (EAttribute)getApplicationTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getApplicationTypeType_ValidatorClass() {
        return (EAttribute)getApplicationTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getApplicationTypeType_Applications() {
        return (EReference)getApplicationTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getAttributeType() {
      if (attributeTypeEClass == null)
      {
         attributeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(29);
      }
      return attributeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Mixed() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Group() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Any() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getAttributeType_ValueNode() {
        return (EReference)getAttributeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Name() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Type() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttributeType_Value() {
        return (EAttribute)getAttributeType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getAttributeType_Reference() {
        return (EReference)getAttributeType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getBindActionType() {
      if (bindActionTypeEClass == null)
      {
         bindActionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(30);
      }
      return bindActionTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getCode()
   {
      if (codeEClass == null)
      {
         codeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(31);
      }
      return codeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getCode_Code()
   {
        return (EAttribute)getCode().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getCode_Value()
   {
        return (EAttribute)getCode().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getCode_Name()
   {
        return (EAttribute)getCode().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getConditionalPerformerSymbolType() {
      if (conditionalPerformerSymbolTypeEClass == null)
      {
         conditionalPerformerSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(32);
      }
      return conditionalPerformerSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getConditionalPerformerSymbolType_Participant() {
        return (EReference)getConditionalPerformerSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getConditionalPerformerType() {
      if (conditionalPerformerTypeEClass == null)
      {
         conditionalPerformerTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(33);
      }
      return conditionalPerformerTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getConditionalPerformerType_Data() {
        return (EReference)getConditionalPerformerType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getConditionalPerformerType_DataPath() {
        return (EAttribute)getConditionalPerformerType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getConditionalPerformerType_IsUser() {
        return (EAttribute)getConditionalPerformerType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getConditionalPerformerType_ConditionalPerformerSymbols() {
        return (EReference)getConditionalPerformerType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getContextType() {
      if (contextTypeEClass == null)
      {
         contextTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(34);
      }
      return contextTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getContextType_Description() {
        return (EReference)getContextType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getContextType_Type() {
        return (EReference)getContextType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataMappingConnectionType() {
      if (dataMappingConnectionTypeEClass == null)
      {
         dataMappingConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(35);
      }
      return dataMappingConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataMappingConnectionType_ActivitySymbol() {
        return (EReference)getDataMappingConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataMappingConnectionType_DataSymbol() {
        return (EReference)getDataMappingConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataMappingType() {
      if (dataMappingTypeEClass == null)
      {
         dataMappingTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(36);
      }
      return dataMappingTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataMappingType_ApplicationAccessPoint() {
        return (EAttribute)getDataMappingType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataMappingType_ApplicationPath() {
        return (EAttribute)getDataMappingType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataMappingType_Context() {
        return (EAttribute)getDataMappingType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataMappingType_Data() {
        return (EReference)getDataMappingType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataMappingType_DataPath() {
        return (EAttribute)getDataMappingType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataMappingType_Direction() {
        return (EAttribute)getDataMappingType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataPathType() {
      if (dataPathTypeEClass == null)
      {
         dataPathTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(37);
      }
      return dataPathTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataPathType_Data() {
        return (EReference)getDataPathType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataPathType_DataPath() {
        return (EAttribute)getDataPathType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataPathType_Descriptor() {
        return (EAttribute)getDataPathType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataPathType_Key()
   {
        return (EAttribute)getDataPathType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataPathType_Direction() {
        return (EAttribute)getDataPathType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataSymbolType() {
      if (dataSymbolTypeEClass == null)
      {
         dataSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(38);
      }
      return dataSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataSymbolType_Data() {
        return (EReference)getDataSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataSymbolType_DataMappings() {
        return (EReference)getDataSymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataType() {
      if (dataTypeEClass == null)
      {
         dataTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(39);
      }
      return dataTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_DataMappings() {
        return (EReference)getDataType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataType_Predefined() {
        return (EAttribute)getDataType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_Type() {
        return (EReference)getDataType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_DataSymbols() {
        return (EReference)getDataType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_ConditionalPerformers() {
        return (EReference)getDataType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_DataPaths() {
        return (EReference)getDataType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_ParameterMappings() {
        return (EReference)getDataType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataType_ExternalReference()
   {
        return (EReference)getDataType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataTypeType() {
      if (dataTypeTypeEClass == null)
      {
         dataTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(40);
      }
      return dataTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_AccessPathEditor() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_Evaluator() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_InstanceClass() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_PanelClass() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_Readable() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_StorageStrategy() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_ValidatorClass() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_ValueCreator() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_Writable() {
        return (EAttribute)getDataTypeType().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataTypeType_Data() {
        return (EReference)getDataTypeType().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDescriptionType() {
      if (descriptionTypeEClass == null)
      {
         descriptionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(41);
      }
      return descriptionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDescriptionType_Mixed() {
        return (EAttribute)getDescriptionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDiagramType() {
      if (diagramTypeEClass == null)
      {
         diagramTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(42);
      }
      return diagramTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDiagramType_Name() {
        return (EAttribute)getDiagramType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDiagramType_PoolSymbols() {
        return (EReference)getDiagramType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDiagramType_Orientation() {
        return (EAttribute)getDiagramType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDiagramType_Mode() {
        return (EAttribute)getDiagramType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDocumentRoot() {
      if (documentRootEClass == null)
      {
         documentRootEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(43);
      }
      return documentRootEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getDocumentRoot_Model() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getEndEventSymbol() {
      if (endEventSymbolEClass == null)
      {
         endEventSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(44);
      }
      return endEventSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getEventActionType() {
      if (eventActionTypeEClass == null)
      {
         eventActionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(45);
      }
      return eventActionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getEventActionTypeType() {
      if (eventActionTypeTypeEClass == null)
      {
         eventActionTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(46);
      }
      return eventActionTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_ActionClass() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_ActivityAction() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_PanelClass() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_ProcessAction() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_SupportedConditionTypes() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventActionTypeType_UnsupportedContexts() {
        return (EAttribute)getEventActionTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventActionTypeType_ActionInstances() {
        return (EReference)getEventActionTypeType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getEventConditionTypeType() {
      if (eventConditionTypeTypeEClass == null)
      {
         eventConditionTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(47);
      }
      return eventConditionTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_ActivityCondition() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_BinderClass() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_Implementation() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_PanelClass() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_ProcessCondition() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_PullEventEmitterClass() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventConditionTypeType_Rule() {
        return (EAttribute)getEventConditionTypeType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventConditionTypeType_EventHandlers() {
        return (EReference)getEventConditionTypeType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getEventHandlerType() {
      if (eventHandlerTypeEClass == null)
      {
         eventHandlerTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(48);
      }
      return eventHandlerTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventHandlerType_BindAction() {
        return (EReference)getEventHandlerType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventHandlerType_EventAction() {
        return (EReference)getEventHandlerType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventHandlerType_UnbindAction() {
        return (EReference)getEventHandlerType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventHandlerType_AutoBind() {
        return (EAttribute)getEventHandlerType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventHandlerType_ConsumeOnMatch() {
        return (EAttribute)getEventHandlerType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventHandlerType_LogHandler() {
        return (EAttribute)getEventHandlerType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getEventHandlerType_Type() {
        return (EReference)getEventHandlerType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getEventHandlerType_UnbindOnMatch() {
        return (EAttribute)getEventHandlerType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getExecutedByConnectionType() {
      if (executedByConnectionTypeEClass == null)
      {
         executedByConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(49);
      }
      return executedByConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getExecutedByConnectionType_ActivitySymbol() {
        return (EReference)getExecutedByConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getExecutedByConnectionType_ApplicationSymbol() {
        return (EReference)getExecutedByConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getIdRef()
   {
      if (idRefEClass == null)
      {
         idRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(50);
      }
      return idRefEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getIdRef_PackageRef()
   {
        return (EReference)getIdRef().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIdRef_Ref()
   {
        return (EAttribute)getIdRef().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getGatewaySymbol() {
      if (gatewaySymbolEClass == null)
      {
         gatewaySymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(51);
      }
      return gatewaySymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getGatewaySymbol_FlowKind() {
        return (EAttribute)getGatewaySymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getGatewaySymbol_ActivitySymbol() {
        return (EReference)getGatewaySymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getGenericLinkConnectionType() {
      if (genericLinkConnectionTypeEClass == null)
      {
         genericLinkConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(52);
      }
      return genericLinkConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getGenericLinkConnectionType_LinkType() {
        return (EReference)getGenericLinkConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getGenericLinkConnectionType_SourceSymbol() {
        return (EReference)getGenericLinkConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getGenericLinkConnectionType_TargetSymbol() {
        return (EReference)getGenericLinkConnectionType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getGroupSymbolType() {
      if (groupSymbolTypeEClass == null)
      {
         groupSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(53);
      }
      return groupSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIntermediateEventSymbol() {
      if (intermediateEventSymbolEClass == null)
      {
         intermediateEventSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(54);
      }
      return intermediateEventSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getLaneSymbol() {
      if (laneSymbolEClass == null)
      {
         laneSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(55);
      }
      return laneSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getLaneSymbol_ParentPool() {
        return (EReference)getLaneSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getLaneSymbol_ParentLane() {
        return (EReference)getLaneSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIIdentifiableElement() {
      if (iIdentifiableElementEClass == null)
      {
         iIdentifiableElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(1);
      }
      return iIdentifiableElementEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIIdentifiableElement_Id() {
        return (EAttribute)getIIdentifiableElement().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIIdentifiableElement_Name() {
        return (EAttribute)getIIdentifiableElement().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIExtensibleElement() {
      if (iExtensibleElementEClass == null)
      {
         iExtensibleElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(2);
      }
      return iExtensibleElementEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIExtensibleElement_Attribute() {
        return (EReference)getIExtensibleElement().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIdentifiableReference() {
      if (identifiableReferenceEClass == null)
      {
         identifiableReferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(3);
      }
      return identifiableReferenceEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIdentifiableReference_Attribute() {
        return (EReference)getIdentifiableReference().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIdentifiableReference_Identifiable() {
        return (EReference)getIdentifiableReference().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIIdentifiableModelElement() {
      if (iIdentifiableModelElementEClass == null)
      {
         iIdentifiableModelElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(5);
      }
      return iIdentifiableModelElementEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIIdentifiableModelElement_Description() {
        return (EReference)getIIdentifiableModelElement().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIEventHandlerOwner() {
      if (iEventHandlerOwnerEClass == null)
      {
         iEventHandlerOwnerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(6);
      }
      return iEventHandlerOwnerEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIEventHandlerOwner_EventHandler() {
        return (EReference)getIEventHandlerOwner().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIAccessPointOwner() {
      if (iAccessPointOwnerEClass == null)
      {
         iAccessPointOwnerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(7);
      }
      return iAccessPointOwnerEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIAccessPointOwner_AccessPoint() {
        return (EReference)getIAccessPointOwner().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIMetaType() {
      if (iMetaTypeEClass == null)
      {
         iMetaTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(8);
      }
      return iMetaTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIMetaType_IsPredefined() {
        return (EAttribute)getIMetaType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getITypedElement() {
      if (iTypedElementEClass == null)
      {
         iTypedElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(9);
      }
      return iTypedElementEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getISymbolContainer() {
      if (iSymbolContainerEClass == null)
      {
         iSymbolContainerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(10);
      }
      return iSymbolContainerEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getISymbolContainer_Nodes() {
        return (EAttribute)getISymbolContainer().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ActivitySymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_AnnotationSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ApplicationSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ConditionalPerformerSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_DataSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_EndEventSymbols() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_GatewaySymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_GroupSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_IntermediateEventSymbols() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ModelerSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(10);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_OrganizationSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(11);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ProcessSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(12);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ProcessInterfaceSymbols()
   {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(13);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_RoleSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(14);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_StartEventSymbols() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(15);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_TextSymbol() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(16);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getISymbolContainer_Connections() {
        return (EAttribute)getISymbolContainer().getEStructuralFeatures().get(17);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_DataMappingConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(18);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_ExecutedByConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(19);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_GenericLinkConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(20);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_PartOfConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(21);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_PerformsConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(22);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_TriggersConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(23);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_RefersToConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(24);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_SubProcessOfConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(25);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_TransitionConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(26);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_WorksForConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(27);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getISymbolContainer_TeamLeadConnection() {
        return (EReference)getISymbolContainer().getEStructuralFeatures().get(28);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIGraphicalObject() {
      if (iGraphicalObjectEClass == null)
      {
         iGraphicalObjectEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(11);
      }
      return iGraphicalObjectEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIGraphicalObject_BorderColor() {
        return (EAttribute)getIGraphicalObject().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIGraphicalObject_FillColor() {
        return (EAttribute)getIGraphicalObject().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIGraphicalObject_Style() {
        return (EAttribute)getIGraphicalObject().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIGraphicalObject_ReferingToConnections() {
        return (EReference)getIGraphicalObject().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIGraphicalObject_ReferingFromConnections() {
        return (EReference)getIGraphicalObject().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getINodeSymbol() {
      if (iNodeSymbolEClass == null)
      {
         iNodeSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(12);
      }
      return iNodeSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getINodeSymbol_XPos() {
        return (EAttribute)getINodeSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getINodeSymbol_YPos() {
        return (EAttribute)getINodeSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getINodeSymbol_Width() {
        return (EAttribute)getINodeSymbol().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getINodeSymbol_Height() {
        return (EAttribute)getINodeSymbol().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getINodeSymbol_Shape() {
        return (EAttribute)getINodeSymbol().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getINodeSymbol_InLinks() {
        return (EReference)getINodeSymbol().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getINodeSymbol_OutLinks() {
        return (EReference)getINodeSymbol().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getISwimlaneSymbol() {
      if (iSwimlaneSymbolEClass == null)
      {
         iSwimlaneSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(13);
      }
      return iSwimlaneSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getISwimlaneSymbol_Orientation() {
        return (EAttribute)getISwimlaneSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getISwimlaneSymbol_Collapsed() {
        return (EAttribute)getISwimlaneSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISwimlaneSymbol_Participant() {
        return (EReference)getISwimlaneSymbol().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getISwimlaneSymbol_ChildLanes() {
        return (EReference)getISwimlaneSymbol().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getISwimlaneSymbol_ParticipantReference()
   {
        return (EReference)getISwimlaneSymbol().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIModelElementNodeSymbol() {
      if (iModelElementNodeSymbolEClass == null)
      {
         iModelElementNodeSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(14);
      }
      return iModelElementNodeSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIFlowObjectSymbol() {
      if (iFlowObjectSymbolEClass == null)
      {
         iFlowObjectSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(15);
      }
      return iFlowObjectSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIFlowObjectSymbol_InTransitions() {
        return (EReference)getIFlowObjectSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIFlowObjectSymbol_OutTransitions() {
        return (EReference)getIFlowObjectSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIConnectionSymbol() {
      if (iConnectionSymbolEClass == null)
      {
         iConnectionSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(16);
      }
      return iConnectionSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIConnectionSymbol_SourceAnchor() {
        return (EAttribute)getIConnectionSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIConnectionSymbol_TargetAnchor() {
        return (EAttribute)getIConnectionSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIConnectionSymbol_Routing() {
        return (EAttribute)getIConnectionSymbol().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIConnectionSymbol_Coordinates() {
        return (EReference)getIConnectionSymbol().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIModelElement() {
      if (iModelElementEClass == null)
      {
         iModelElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(4);
      }
      return iModelElementEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIModelElement_ElementOid() {
        return (EAttribute)getIModelElement().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getLinkTypeType() {
      if (linkTypeTypeEClass == null)
      {
         linkTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(56);
      }
      return linkTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_SourceRole() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_SourceClass() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_LineColor() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getLinkTypeType_LinkInstances() {
        return (EReference)getLinkTypeType().getEStructuralFeatures().get(12);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_ShowRoleNames() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(10);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_ShowLinkTypeName() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(11);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_SourceCardinality() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_TargetRole() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_TargetClass() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_SourceSymbol() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_TargetCardinality() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_LineStyle() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLinkTypeType_TargetSymbol() {
        return (EAttribute)getLinkTypeType().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getModelerSymbolType() {
      if (modelerSymbolTypeEClass == null)
      {
         modelerSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(57);
      }
      return modelerSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelerSymbolType_Modeler() {
        return (EReference)getModelerSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getModelerType() {
      if (modelerTypeEClass == null)
      {
         modelerTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(58);
      }
      return modelerTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelerType_Email() {
        return (EAttribute)getModelerType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelerType_Password() {
        return (EAttribute)getModelerType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelerType_ModelerSymbols() {
        return (EReference)getModelerType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getModelType() {
      if (modelTypeEClass == null)
      {
         modelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(59);
      }
      return modelTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Description() {
        return (EReference)getModelType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_DataType() {
        return (EReference)getModelType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_ApplicationType() {
        return (EReference)getModelType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_ApplicationContextType() {
        return (EReference)getModelType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_TriggerType() {
        return (EReference)getModelType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_EventConditionType() {
        return (EReference)getModelType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_EventActionType() {
        return (EReference)getModelType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Data() {
        return (EReference)getModelType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Application() {
        return (EReference)getModelType().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Modeler() {
        return (EReference)getModelType().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_QualityControl()
   {
        return (EReference)getModelType().getEStructuralFeatures().get(10);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Role() {
        return (EReference)getModelType().getEStructuralFeatures().get(11);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Organization() {
        return (EReference)getModelType().getEStructuralFeatures().get(12);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_ConditionalPerformer() {
        return (EReference)getModelType().getEStructuralFeatures().get(13);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_ProcessDefinition() {
        return (EReference)getModelType().getEStructuralFeatures().get(14);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_ExternalPackages()
   {
        return (EReference)getModelType().getEStructuralFeatures().get(15);
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Script() {
        return (EReference)getModelType().getEStructuralFeatures().get(16);
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_TypeDeclarations() {
        return (EReference)getModelType().getEStructuralFeatures().get(17);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_Diagram() {
        return (EReference)getModelType().getEStructuralFeatures().get(18);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_LinkType() {
        return (EReference)getModelType().getEStructuralFeatures().get(19);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getModelType_View() {
        return (EReference)getModelType().getEStructuralFeatures().get(20);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_Author() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(21);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_CarnotVersion() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(22);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_Created() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(23);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_ModelOID() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(24);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_Oid() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(25);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getModelType_Vendor() {
        return (EAttribute)getModelType().getEStructuralFeatures().get(26);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getOrganizationSymbolType() {
      if (organizationSymbolTypeEClass == null)
      {
         organizationSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(60);
      }
      return organizationSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationSymbolType_Organization() {
        return (EReference)getOrganizationSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationSymbolType_SuperOrganizations() {
        return (EReference)getOrganizationSymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationSymbolType_SubOrganizations() {
        return (EReference)getOrganizationSymbolType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationSymbolType_MemberRoles() {
        return (EReference)getOrganizationSymbolType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationSymbolType_TeamLead() {
        return (EReference)getOrganizationSymbolType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getOrganizationType() {
      if (organizationTypeEClass == null)
      {
         organizationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(61);
      }
      return organizationTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationType_Participant() {
        return (EReference)getOrganizationType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationType_OrganizationSymbols() {
        return (EReference)getOrganizationType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getOrganizationType_TeamLead() {
        return (EReference)getOrganizationType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getParameterMappingType() {
      if (parameterMappingTypeEClass == null)
      {
         parameterMappingTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(62);
      }
      return parameterMappingTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getParameterMappingType_Data() {
        return (EReference)getParameterMappingType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getParameterMappingType_DataPath()
   {
        return (EAttribute)getParameterMappingType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getParameterMappingType_Parameter() {
        return (EAttribute)getParameterMappingType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getParameterMappingType_ParameterPath() {
        return (EAttribute)getParameterMappingType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getParticipantType() {
      if (participantTypeEClass == null)
      {
         participantTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(63);
      }
      return participantTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getParticipantType_Participant() {
        return (EReference)getParticipantType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getPartOfConnectionType() {
      if (partOfConnectionTypeEClass == null)
      {
         partOfConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(64);
      }
      return partOfConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPartOfConnectionType_OrganizationSymbol() {
        return (EReference)getPartOfConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPartOfConnectionType_SuborganizationSymbol() {
        return (EReference)getPartOfConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getPerformsConnectionType() {
      if (performsConnectionTypeEClass == null)
      {
         performsConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(65);
      }
      return performsConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPerformsConnectionType_ActivitySymbol() {
        return (EReference)getPerformsConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPerformsConnectionType_ParticipantSymbol() {
        return (EReference)getPerformsConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getPoolSymbol() {
      if (poolSymbolEClass == null)
      {
         poolSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(66);
      }
      return poolSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPoolSymbol_Diagram() {
        return (EReference)getPoolSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPoolSymbol_BoundaryVisible() {
        return (EAttribute)getPoolSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPoolSymbol_Process() {
        return (EReference)getPoolSymbol().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPoolSymbol_Lanes() {
        return (EReference)getPoolSymbol().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getProcessDefinitionType() {
      if (processDefinitionTypeEClass == null)
      {
         processDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(67);
      }
      return processDefinitionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_Activity() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_Transition() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_Trigger() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_DataPath() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_Diagram() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_ExecutingActivities() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_ProcessSymbols() {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getProcessDefinitionType_DefaultPriority() {
        return (EAttribute)getProcessDefinitionType().getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_FormalParameters()
   {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_FormalParameterMappings()
   {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessDefinitionType_ExternalRef()
   {
        return (EReference)getProcessDefinitionType().getEStructuralFeatures().get(10);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getProcessSymbolType() {
      if (processSymbolTypeEClass == null)
      {
         processSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(68);
      }
      return processSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessSymbolType_Process() {
        return (EReference)getProcessSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessSymbolType_SubProcesses() {
        return (EReference)getProcessSymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getProcessSymbolType_ParentProcesses() {
        return (EReference)getProcessSymbolType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPublicInterfaceSymbol()
   {
      if (publicInterfaceSymbolEClass == null)
      {
         publicInterfaceSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(69);
      }
      return publicInterfaceSymbolEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getQualityControlType()
   {
      if (qualityControlTypeEClass == null)
      {
         qualityControlTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(70);
      }
      return qualityControlTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getQualityControlType_Code()
   {
        return (EReference)getQualityControlType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getRefersToConnectionType() {
      if (refersToConnectionTypeEClass == null)
      {
         refersToConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(71);
      }
      return refersToConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getRefersToConnectionType_From() {
        return (EReference)getRefersToConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getRefersToConnectionType_To() {
        return (EReference)getRefersToConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getRoleSymbolType() {
      if (roleSymbolTypeEClass == null)
      {
         roleSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(72);
      }
      return roleSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getRoleSymbolType_Role() {
        return (EReference)getRoleSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getRoleSymbolType_OrganizationMemberships() {
        return (EReference)getRoleSymbolType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getRoleSymbolType_Teams() {
        return (EReference)getRoleSymbolType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getRoleType() {
      if (roleTypeEClass == null)
      {
         roleTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(73);
      }
      return roleTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getRoleType_Cardinality() {
        return (EAttribute)getRoleType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getRoleType_Teams() {
        return (EReference)getRoleType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getRoleType_RoleSymbols() {
        return (EReference)getRoleType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getStartEventSymbol() {
      if (startEventSymbolEClass == null)
      {
         startEventSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(74);
      }
      return startEventSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getStartEventSymbol_Trigger() {
        return (EReference)getStartEventSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getStartEventSymbol_TriggersConnections() {
        return (EReference)getStartEventSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getStartEventSymbol_StartActivity() {
        return (EReference)getStartEventSymbol().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getSubProcessOfConnectionType() {
      if (subProcessOfConnectionTypeEClass == null)
      {
         subProcessOfConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(75);
      }
      return subProcessOfConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getSubProcessOfConnectionType_ProcessSymbol() {
        return (EReference)getSubProcessOfConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getSubProcessOfConnectionType_SubprocessSymbol() {
        return (EReference)getSubProcessOfConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTextSymbolType() {
      if (textSymbolTypeEClass == null)
      {
         textSymbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(77);
      }
      return textSymbolTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTextSymbolType_Text() {
        return (EAttribute)getTextSymbolType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTextType() {
      if (textTypeEClass == null)
      {
         textTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(78);
      }
      return textTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTextType_Mixed() {
        return (EAttribute)getTextType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTransitionConnectionType() {
      if (transitionConnectionTypeEClass == null)
      {
         transitionConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(79);
      }
      return transitionConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTransitionConnectionType_Points() {
        return (EAttribute)getTransitionConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionConnectionType_SourceActivitySymbol() {
        return (EReference)getTransitionConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionConnectionType_TargetActivitySymbol() {
        return (EReference)getTransitionConnectionType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionConnectionType_Transition() {
        return (EReference)getTransitionConnectionType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTransitionType() {
      if (transitionTypeEClass == null)
      {
         transitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(80);
      }
      return transitionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionType_Expression() {
        return (EReference)getTransitionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTransitionType_Condition() {
        return (EAttribute)getTransitionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTransitionType_ForkOnTraversal() {
        return (EAttribute)getTransitionType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionType_From() {
        return (EReference)getTransitionType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionType_To() {
        return (EReference)getTransitionType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTransitionType_TransitionConnections() {
        return (EReference)getTransitionType().getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getTriggersConnectionType() {
      if (triggersConnectionTypeEClass == null)
      {
         triggersConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(81);
      }
      return triggersConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggersConnectionType_StartEventSymbol() {
        return (EReference)getTriggersConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggersConnectionType_ParticipantSymbol() {
        return (EReference)getTriggersConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTriggerType() {
      if (triggerTypeEClass == null)
      {
         triggerTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(82);
      }
      return triggerTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggerType_ParameterMapping() {
        return (EReference)getTriggerType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggerType_Type() {
        return (EReference)getTriggerType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggerType_StartingEventSymbols() {
        return (EReference)getTriggerType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTriggerTypeType() {
      if (triggerTypeTypeEClass == null)
      {
         triggerTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(83);
      }
      return triggerTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTriggerTypeType_PanelClass() {
        return (EAttribute)getTriggerTypeType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTriggerTypeType_PullTrigger() {
        return (EAttribute)getTriggerTypeType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTriggerTypeType_PullTriggerEvaluator() {
        return (EAttribute)getTriggerTypeType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTriggerTypeType_Rule() {
        return (EAttribute)getTriggerTypeType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getTriggerTypeType_Triggers() {
        return (EReference)getTriggerTypeType().getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getUnbindActionType() {
      if (unbindActionTypeEClass == null)
      {
         unbindActionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(84);
      }
      return unbindActionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getViewableType() {
      if (viewableTypeEClass == null)
      {
         viewableTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(85);
      }
      return viewableTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getViewableType_Viewable() {
        return (EReference)getViewableType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getViewType() {
      if (viewTypeEClass == null)
      {
         viewTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(86);
      }
      return viewTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getViewType_Description() {
        return (EReference)getViewType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getViewType_View() {
        return (EReference)getViewType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getViewType_Viewable() {
        return (EReference)getViewType().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getViewType_Name() {
        return (EAttribute)getViewType().getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getWorksForConnectionType() {
      if (worksForConnectionTypeEClass == null)
      {
         worksForConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(87);
      }
      return worksForConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getWorksForConnectionType_OrganizationSymbol() {
        return (EReference)getWorksForConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getWorksForConnectionType_ParticipantSymbol() {
        return (EReference)getWorksForConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getXmlTextNode() {
      if (xmlTextNodeEClass == null)
      {
         xmlTextNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(88);
      }
      return xmlTextNodeEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getXmlTextNode_Mixed() {
        return (EAttribute)getXmlTextNode().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getTeamLeadConnectionType() {
      if (teamLeadConnectionTypeEClass == null)
      {
         teamLeadConnectionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(76);
      }
      return teamLeadConnectionTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getTeamLeadConnectionType_TeamSymbol() {
        return (EReference)getTeamLeadConnectionType().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getTeamLeadConnectionType_TeamLeadSymbol() {
        return (EReference)getTeamLeadConnectionType().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getAbstractEventAction() {
      if (abstractEventActionEClass == null)
      {
         abstractEventActionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(19);
      }
      return abstractEventActionEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getAbstractEventAction_Type() {
        return (EReference)getAbstractEventAction().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getActivityImplementationType() {
      if (activityImplementationTypeEEnum == null)
      {
         activityImplementationTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(89);
      }
      return activityImplementationTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getDirectionType() {
      if (directionTypeEEnum == null)
      {
         directionTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(90);
      }
      return directionTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getFlowControlType() {
      if (flowControlTypeEEnum == null)
      {
         flowControlTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(91);
      }
      return flowControlTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIModelParticipant() {
      if (iModelParticipantEClass == null)
      {
         iModelParticipantEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(17);
      }
      return iModelParticipantEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIModelParticipant_PerformedActivities() {
        return (EReference)getIModelParticipant().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIModelParticipant_PerformedSwimlanes() {
        return (EReference)getIModelParticipant().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIModelParticipant_ParticipantAssociations() {
        return (EReference)getIModelParticipant().getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIModelParticipantSymbol() {
      if (iModelParticipantSymbolEClass == null)
      {
         iModelParticipantSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(18);
      }
      return iModelParticipantSymbolEClass;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getIModelParticipantSymbol_PerformedActivities() {
        return (EReference)getIModelParticipantSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getIModelParticipantSymbol_TriggeredEvents() {
        return (EReference)getIModelParticipantSymbol().getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getAbstractEventSymbol() {
      if (abstractEventSymbolEClass == null)
      {
         abstractEventSymbolEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(20);
      }
      return abstractEventSymbolEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAbstractEventSymbol_Label()
   {
        return (EAttribute)getAbstractEventSymbol().getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getImplementationType() {
      if (implementationTypeEEnum == null)
      {
         implementationTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(92);
      }
      return implementationTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getJoinSplitType() {
      if (joinSplitTypeEEnum == null)
      {
         joinSplitTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(93);
      }
      return joinSplitTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getLinkCardinality() {
      if (linkCardinalityEEnum == null)
      {
         linkCardinalityEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(94);
      }
      return linkCardinalityEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getLinkColor() {
      if (linkColorEEnum == null)
      {
         linkColorEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(95);
      }
      return linkColorEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getLinkLineStyle() {
      if (linkLineStyleEEnum == null)
      {
         linkLineStyleEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(96);
      }
      return linkLineStyleEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getLinkEndStyle() {
      if (linkEndStyleEEnum == null)
      {
         linkEndStyleEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(97);
      }
      return linkEndStyleEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getLoopType() {
      if (loopTypeEEnum == null)
      {
         loopTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(98);
      }
      return loopTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getOrientationType() {
      if (orientationTypeEEnum == null)
      {
         orientationTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(99);
      }
      return orientationTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getRoutingType() {
      if (routingTypeEEnum == null)
      {
         routingTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(100);
      }
      return routingTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getSubProcessModeType() {
      if (subProcessModeTypeEEnum == null)
      {
         subProcessModeTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(101);
      }
      return subProcessModeTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getDiagramModeType() {
      if (diagramModeTypeEEnum == null)
      {
         diagramModeTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(102);
      }
      return diagramModeTypeEEnum;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getElementId() {
      if (elementIdEDataType == null)
      {
         elementIdEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(103);
      }
      return elementIdEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getFeatureList() {
      if (featureListEDataType == null)
      {
         featureListEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(104);
      }
      return featureListEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getActivityImplementationTypeObject() {
      if (activityImplementationTypeObjectEDataType == null)
      {
         activityImplementationTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(105);
      }
      return activityImplementationTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getDirectionTypeObject() {
      if (directionTypeObjectEDataType == null)
      {
         directionTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(106);
      }
      return directionTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getFlowControlTypeObject() {
      if (flowControlTypeObjectEDataType == null)
      {
         flowControlTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(107);
      }
      return flowControlTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getImplementationTypeObject() {
      if (implementationTypeObjectEDataType == null)
      {
         implementationTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(108);
      }
      return implementationTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getJoinSplitTypeObject() {
      if (joinSplitTypeObjectEDataType == null)
      {
         joinSplitTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(113);
      }
      return joinSplitTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EDataType getLinkCardinalityObject() {
      if (linkCardinalityObjectEDataType == null)
      {
         linkCardinalityObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(109);
      }
      return linkCardinalityObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getLinkColorObject() {
      if (linkColorObjectEDataType == null)
      {
         linkColorObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(110);
      }
      return linkColorObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getLinkLineStyleObject() {
      if (linkLineStyleObjectEDataType == null)
      {
         linkLineStyleObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(111);
      }
      return linkLineStyleObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getLinkEndStyleObject() {
      if (linkEndStyleObjectEDataType == null)
      {
         linkEndStyleObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(112);
      }
      return linkEndStyleObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getLoopTypeObject() {
      if (loopTypeObjectEDataType == null)
      {
         loopTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(114);
      }
      return loopTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getOrientationTypeObject() {
      if (orientationTypeObjectEDataType == null)
      {
         orientationTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(115);
      }
      return orientationTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getRoutingTypeObject() {
      if (routingTypeObjectEDataType == null)
      {
         routingTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(116);
      }
      return routingTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getSubProcessModeTypeObject() {
      if (subProcessModeTypeObjectEDataType == null)
      {
         subProcessModeTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(117);
      }
      return subProcessModeTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getDiagramModeTypeObject() {
      if (diagramModeTypeObjectEDataType == null)
      {
         diagramModeTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI).getEClassifiers().get(118);
      }
      return diagramModeTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public CarnotWorkflowModelFactory getCarnotWorkflowModelFactory() {
      return (CarnotWorkflowModelFactory)getEFactoryInstance();
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
      if (url == null)
      {
         throw new RuntimeException("Missing serialized package: " + packageFilename); //$NON-NLS-1$
      }
      URI uri = URI.createURI(url.toString());
      Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
      try
      {
         resource.load(null);
      }
      catch (IOException exception)
      {
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
      if (eClassifier.getInstanceClassName() == null)
      {
         eClassifier.setInstanceClassName("org.eclipse.stardust.model.xpdl.carnot." + eClassifier.getName());
         setGeneratedClassName(eClassifier);
      }
   }

} //CarnotWorkflowModelPackageImpl
