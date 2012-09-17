/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
    String eNS_URI = "http://www.eclipse.org/stardust/bpmn20/sdbpmn";

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
     * The feature id for the '<em><b>Stardust Activity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_ACTIVITY = 3;

    /**
     * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_ATTRIBUTES = 4;

    /**
     * The feature id for the '<em><b>Stardust Common</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_COMMON = 5;

    /**
     * The feature id for the '<em><b>Stardust Message Start Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT = 6;

    /**
     * The feature id for the '<em><b>Stardust Model</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_MODEL = 7;

    /**
     * The feature id for the '<em><b>Stardust Seqence Flow</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW = 8;

    /**
     * The feature id for the '<em><b>Stardust Service Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_SERVICE_TASK = 9;

    /**
     * The feature id for the '<em><b>Stardust Start Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_START_EVENT = 10;

    /**
     * The feature id for the '<em><b>Stardust Subprocess</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_SUBPROCESS = 11;

    /**
     * The feature id for the '<em><b>Stardust Timer Start Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT = 12;

    /**
     * The feature id for the '<em><b>Stardust User Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STARDUST_USER_TASK = 13;

    /**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__AUTHOR = 14;

    /**
     * The feature id for the '<em><b>Carnot Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__CARNOT_VERSION = 15;

    /**
     * The feature id for the '<em><b>Created</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__CREATED = 16;

    /**
     * The feature id for the '<em><b>Model OID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__MODEL_OID = 17;

    /**
     * The feature id for the '<em><b>Oid</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__OID = 18;

    /**
     * The feature id for the '<em><b>Vendor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__VENDOR = 19;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 20;

    /**
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl <em>Stardust Attributes Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustAttributesTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustAttributesType()
     * @generated
     */
    int STARDUST_ATTRIBUTES_TYPE = 1;

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
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl <em>Stardust Message Start Event Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustMessageStartEventTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustMessageStartEventType()
     * @generated
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE = 2;

    /**
     * The feature id for the '<em><b>Stardust Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE__STARDUST_ATTRIBUTES = 0;

    /**
     * The feature id for the '<em><b>Access Point</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT = 1;

    /**
     * The feature id for the '<em><b>Parameter Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING = 2;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE__TYPE = 3;

    /**
     * The number of structural features of the '<em>Stardust Message Start Event Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_MESSAGE_START_EVENT_TYPE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl <em>Stardust Model Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustModelType()
     * @generated
     */
    int STARDUST_MODEL_TYPE = 3;

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
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl <em>Stardust Seqence Flow Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustSeqenceFlowTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustSeqenceFlowType()
     * @generated
     */
    int STARDUST_SEQENCE_FLOW_TYPE = 4;

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
    int TSTARDUST_COMMON = 11;

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
    int TSTARDUST_ACTIVITY = 10;

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
     * The feature id for the '<em><b>Data Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TSTARDUST_ACTIVITY__DATA_MAPPING = TSTARDUST_COMMON_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION = TSTARDUST_COMMON_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>TStardust Activity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TSTARDUST_ACTIVITY_FEATURE_COUNT = TSTARDUST_COMMON_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl <em>Stardust Service Task Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustServiceTaskTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustServiceTaskType()
     * @generated
     */
    int STARDUST_SERVICE_TASK_TYPE = 5;

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
     * The feature id for the '<em><b>Data Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_SERVICE_TASK_TYPE__DATA_MAPPING = TSTARDUST_ACTIVITY__DATA_MAPPING;

    /**
     * The feature id for the '<em><b>Hibernate On Creation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_SERVICE_TASK_TYPE__HIBERNATE_ON_CREATION = TSTARDUST_ACTIVITY__HIBERNATE_ON_CREATION;

    /**
     * The feature id for the '<em><b>Application</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_SERVICE_TASK_TYPE__APPLICATION = TSTARDUST_ACTIVITY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Stardust Service Task Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_SERVICE_TASK_TYPE_FEATURE_COUNT = TSTARDUST_ACTIVITY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl <em>Stardust Start Event Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustStartEventTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustStartEventType()
     * @generated
     */
    int STARDUST_START_EVENT_TYPE = 6;

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
    int STARDUST_SUBPROCESS_TYPE = 7;

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
     * The feature id for the '<em><b>Data Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_SUBPROCESS_TYPE__DATA_MAPPING = TSTARDUST_ACTIVITY__DATA_MAPPING;

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
    int STARDUST_TIMER_START_EVENT_TYPE = 8;

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
     * The meta object id for the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl <em>Stardust User Task Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustUserTaskTypeImpl
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.impl.SdbpmnPackageImpl#getStardustUserTaskType()
     * @generated
     */
    int STARDUST_USER_TASK_TYPE = 9;

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
     * The feature id for the '<em><b>Data Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_USER_TASK_TYPE__DATA_MAPPING = TSTARDUST_ACTIVITY__DATA_MAPPING;

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
     * The number of structural features of the '<em>Stardust User Task Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STARDUST_USER_TASK_TYPE_FEATURE_COUNT = TSTARDUST_ACTIVITY_FEATURE_COUNT + 1;


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
     * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getAccessPoint <em>Access Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Access Point</em>'.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getAccessPoint()
     * @see #getStardustMessageStartEventType()
     * @generated
     */
    EReference getStardustMessageStartEventType_AccessPoint();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getParameterMapping <em>Parameter Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameter Mapping</em>'.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getParameterMapping()
     * @see #getStardustMessageStartEventType()
     * @generated
     */
    EReference getStardustMessageStartEventType_ParameterMapping();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustMessageStartEventType#getType()
     * @see #getStardustMessageStartEventType()
     * @generated
     */
    EAttribute getStardustMessageStartEventType_Type();

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
     * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType#getApplication <em>Application</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Application</em>'.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType#getApplication()
     * @see #getStardustServiceTaskType()
     * @generated
     */
    EAttribute getStardustServiceTaskType_Application();

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
     * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#getDataMapping <em>Data Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data Mapping</em>'.
     * @see org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#getDataMapping()
     * @see #getTStardustActivity()
     * @generated
     */
    EReference getTStardustActivity_DataMapping();

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
         * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__VENDOR = eINSTANCE.getDocumentRoot_Vendor();

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
         * The meta object literal for the '<em><b>Access Point</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STARDUST_MESSAGE_START_EVENT_TYPE__ACCESS_POINT = eINSTANCE.getStardustMessageStartEventType_AccessPoint();

        /**
         * The meta object literal for the '<em><b>Parameter Mapping</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference STARDUST_MESSAGE_START_EVENT_TYPE__PARAMETER_MAPPING = eINSTANCE.getStardustMessageStartEventType_ParameterMapping();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STARDUST_MESSAGE_START_EVENT_TYPE__TYPE = eINSTANCE.getStardustMessageStartEventType_Type();

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
         * The meta object literal for the '<em><b>Application</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STARDUST_SERVICE_TASK_TYPE__APPLICATION = eINSTANCE.getStardustServiceTaskType_Application();

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
         * The meta object literal for the '<em><b>Data Mapping</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TSTARDUST_ACTIVITY__DATA_MAPPING = eINSTANCE.getTStardustActivity_DataMapping();

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
