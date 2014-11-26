/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustActivity <em>Stardust Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustCommon <em>Stardust Common</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataObject <em>Stardust Data Object</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataStore <em>Stardust Data Store</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustInterface <em>Stardust Interface</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustMessageStartEvent <em>Stardust Message Start Event</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustModel <em>Stardust Model</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustProcess <em>Stardust Process</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustResource <em>Stardust Resource</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSeqenceFlow <em>Stardust Seqence Flow</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustServiceTask <em>Stardust Service Task</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustStartEvent <em>Stardust Start Event</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSubprocess <em>Stardust Subprocess</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustTimerStartEvent <em>Stardust Timer Start Event</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustUserTask <em>Stardust User Task</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getApplicationAccessPointRef <em>Application Access Point Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCarnotVersion <em>Carnot Version</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCreated <em>Created</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getInteractiveApplicationRef <em>Interactive Application Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelOID <em>Model OID</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isModelReleased <em>Model Released</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid <em>Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getParameterMappingOid <em>Parameter Mapping Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isStardustIgnore <em>Stardust Ignore</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustPropertyId <em>Stardust Property Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition <em>Synthetic Item Definition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty <em>Synthetic Property</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getTriggerAccessPointRef <em>Trigger Access Point Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getVendor <em>Vendor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' containment reference.
	 * @see #setDataType(DataTypeType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_DataType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataType' namespace='##targetNamespace'"
	 * @generated
	 */
	DataTypeType getDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getDataType <em>Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' containment reference.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(DataTypeType value);

	/**
	 * Returns the value of the '<em><b>Stardust Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Activity</em>' containment reference.
	 * @see #setStardustActivity(TStardustActivity)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustActivity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustActivity' namespace='##targetNamespace'"
	 * @generated
	 */
	TStardustActivity getStardustActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustActivity <em>Stardust Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Activity</em>' containment reference.
	 * @see #getStardustActivity()
	 * @generated
	 */
	void setStardustActivity(TStardustActivity value);

	/**
	 * Returns the value of the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #setStardustAttributes(StardustAttributesType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustAttributes()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustAttributesType getStardustAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustAttributes <em>Stardust Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #getStardustAttributes()
	 * @generated
	 */
	void setStardustAttributes(StardustAttributesType value);

	/**
	 * Returns the value of the '<em><b>Stardust Common</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Common</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Common</em>' containment reference.
	 * @see #setStardustCommon(TStardustCommon)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustCommon()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustCommon' namespace='##targetNamespace'"
	 * @generated
	 */
	TStardustCommon getStardustCommon();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustCommon <em>Stardust Common</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Common</em>' containment reference.
	 * @see #getStardustCommon()
	 * @generated
	 */
	void setStardustCommon(TStardustCommon value);

	/**
	 * Returns the value of the '<em><b>Stardust Data Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Data Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Data Object</em>' containment reference.
	 * @see #setStardustDataObject(StardustDataObjectType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustDataObject()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustDataObject' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustDataObjectType getStardustDataObject();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataObject <em>Stardust Data Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Data Object</em>' containment reference.
	 * @see #getStardustDataObject()
	 * @generated
	 */
	void setStardustDataObject(StardustDataObjectType value);

	/**
	 * Returns the value of the '<em><b>Stardust Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Data Store</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Data Store</em>' containment reference.
	 * @see #setStardustDataStore(StardustDataStoreType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustDataStore()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustDataStore' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustDataStoreType getStardustDataStore();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustDataStore <em>Stardust Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Data Store</em>' containment reference.
	 * @see #getStardustDataStore()
	 * @generated
	 */
	void setStardustDataStore(StardustDataStoreType value);

	/**
	 * Returns the value of the '<em><b>Stardust Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Interface</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Interface</em>' containment reference.
	 * @see #setStardustInterface(StardustInterfaceType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustInterface()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustInterface' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustInterfaceType getStardustInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustInterface <em>Stardust Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Interface</em>' containment reference.
	 * @see #getStardustInterface()
	 * @generated
	 */
	void setStardustInterface(StardustInterfaceType value);

	/**
	 * Returns the value of the '<em><b>Stardust Message Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Message Start Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Message Start Event</em>' containment reference.
	 * @see #setStardustMessageStartEvent(StardustMessageStartEventType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustMessageStartEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustMessageStartEvent' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustMessageStartEventType getStardustMessageStartEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustMessageStartEvent <em>Stardust Message Start Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Message Start Event</em>' containment reference.
	 * @see #getStardustMessageStartEvent()
	 * @generated
	 */
	void setStardustMessageStartEvent(StardustMessageStartEventType value);

	/**
	 * Returns the value of the '<em><b>Stardust Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Model</em>' containment reference.
	 * @see #setStardustModel(StardustModelType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustModel()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustModel' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustModelType getStardustModel();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustModel <em>Stardust Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Model</em>' containment reference.
	 * @see #getStardustModel()
	 * @generated
	 */
	void setStardustModel(StardustModelType value);

	/**
	 * Returns the value of the '<em><b>Stardust Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Process</em>' containment reference.
	 * @see #setStardustProcess(StardustProcessType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustProcess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustProcess' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustProcessType getStardustProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustProcess <em>Stardust Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Process</em>' containment reference.
	 * @see #getStardustProcess()
	 * @generated
	 */
	void setStardustProcess(StardustProcessType value);

	/**
	 * Returns the value of the '<em><b>Stardust Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Resource</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Resource</em>' containment reference.
	 * @see #setStardustResource(StardustResourceType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustResource()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustResource' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustResourceType getStardustResource();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustResource <em>Stardust Resource</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Resource</em>' containment reference.
	 * @see #getStardustResource()
	 * @generated
	 */
	void setStardustResource(StardustResourceType value);

	/**
	 * Returns the value of the '<em><b>Stardust Seqence Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Seqence Flow</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Seqence Flow</em>' containment reference.
	 * @see #setStardustSeqenceFlow(StardustSeqenceFlowType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustSeqenceFlow()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustSeqenceFlow' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustSeqenceFlowType getStardustSeqenceFlow();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSeqenceFlow <em>Stardust Seqence Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Seqence Flow</em>' containment reference.
	 * @see #getStardustSeqenceFlow()
	 * @generated
	 */
	void setStardustSeqenceFlow(StardustSeqenceFlowType value);

	/**
	 * Returns the value of the '<em><b>Stardust Service Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Service Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Service Task</em>' containment reference.
	 * @see #setStardustServiceTask(StardustServiceTaskType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustServiceTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustServiceTask' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustServiceTaskType getStardustServiceTask();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustServiceTask <em>Stardust Service Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Service Task</em>' containment reference.
	 * @see #getStardustServiceTask()
	 * @generated
	 */
	void setStardustServiceTask(StardustServiceTaskType value);

	/**
	 * Returns the value of the '<em><b>Stardust Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Start Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Start Event</em>' containment reference.
	 * @see #setStardustStartEvent(StardustStartEventType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustStartEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustStartEvent' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustStartEventType getStardustStartEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustStartEvent <em>Stardust Start Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Start Event</em>' containment reference.
	 * @see #getStardustStartEvent()
	 * @generated
	 */
	void setStardustStartEvent(StardustStartEventType value);

	/**
	 * Returns the value of the '<em><b>Stardust Subprocess</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Subprocess</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Subprocess</em>' containment reference.
	 * @see #setStardustSubprocess(StardustSubprocessType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustSubprocess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustSubprocess' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustSubprocessType getStardustSubprocess();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustSubprocess <em>Stardust Subprocess</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Subprocess</em>' containment reference.
	 * @see #getStardustSubprocess()
	 * @generated
	 */
	void setStardustSubprocess(StardustSubprocessType value);

	/**
	 * Returns the value of the '<em><b>Stardust Timer Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Timer Start Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Timer Start Event</em>' containment reference.
	 * @see #setStardustTimerStartEvent(StardustTimerStartEventType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustTimerStartEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustTimerStartEvent' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustTimerStartEventType getStardustTimerStartEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustTimerStartEvent <em>Stardust Timer Start Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Timer Start Event</em>' containment reference.
	 * @see #getStardustTimerStartEvent()
	 * @generated
	 */
	void setStardustTimerStartEvent(StardustTimerStartEventType value);

	/**
	 * Returns the value of the '<em><b>Stardust User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust User Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust User Task</em>' containment reference.
	 * @see #setStardustUserTask(StardustUserTaskType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustUserTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StardustUserTask' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustUserTaskType getStardustUserTask();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustUserTask <em>Stardust User Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust User Task</em>' containment reference.
	 * @see #getStardustUserTask()
	 * @generated
	 */
	void setStardustUserTask(StardustUserTaskType value);

	/**
	 * Returns the value of the '<em><b>Application Access Point Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * BPMN2.0 Assignment-From/To: reference to a stardust application accesspoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Access Point Ref</em>' attribute.
	 * @see #setApplicationAccessPointRef(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_ApplicationAccessPointRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='applicationAccessPointRef' namespace='##targetNamespace'"
	 * @generated
	 */
	String getApplicationAccessPointRef();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getApplicationAccessPointRef <em>Application Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application Access Point Ref</em>' attribute.
	 * @see #getApplicationAccessPointRef()
	 * @generated
	 */
	void setApplicationAccessPointRef(String value);

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				The name of the user editing this model last.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_Author()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='author' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Carnot Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				The software version used to create and edit this model. Opening this
	 * 				model in another version of the software may require an explicit
	 * 				conversion.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Carnot Version</em>' attribute.
	 * @see #setCarnotVersion(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_CarnotVersion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='carnotVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCarnotVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCarnotVersion <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Carnot Version</em>' attribute.
	 * @see #getCarnotVersion()
	 * @generated
	 */
	void setCarnotVersion(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date this model was created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(XMLGregorianCalendar)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_Created()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='attribute' name='created' namespace='##targetNamespace'"
	 * @generated
	 */
	XMLGregorianCalendar getCreated();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Interactive Application Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Implementation reference for UserTasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interactive Application Ref</em>' attribute.
	 * @see #setInteractiveApplicationRef(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_InteractiveApplicationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='interactiveApplicationRef' namespace='##targetNamespace'"
	 * @generated
	 */
	String getInteractiveApplicationRef();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getInteractiveApplicationRef <em>Interactive Application Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interactive Application Ref</em>' attribute.
	 * @see #getInteractiveApplicationRef()
	 * @generated
	 */
	void setInteractiveApplicationRef(String value);

	/**
	 * Returns the value of the '<em><b>Model OID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				A 32-bit number assigned to the model during deployment to an audit
	 * 				trail. This number may be changed if the model is deployed to different
	 * 				audit trails or even to the same audit trail multiple times.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model OID</em>' attribute.
	 * @see #setModelOID(BigInteger)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_ModelOID()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='attribute' name='modelOID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getModelOID();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelOID <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model OID</em>' attribute.
	 * @see #getModelOID()
	 * @generated
	 */
	void setModelOID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Model Released</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				Whether the Model is released or not (= UNDER_REVISION)
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Released</em>' attribute.
	 * @see #isSetModelReleased()
	 * @see #unsetModelReleased()
	 * @see #setModelReleased(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_ModelReleased()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='modelReleased' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isModelReleased();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isModelReleased <em>Model Released</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Released</em>' attribute.
	 * @see #isSetModelReleased()
	 * @see #unsetModelReleased()
	 * @see #isModelReleased()
	 * @generated
	 */
	void setModelReleased(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isModelReleased <em>Model Released</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetModelReleased()
	 * @see #isModelReleased()
	 * @see #setModelReleased(boolean)
	 * @generated
	 */
	void unsetModelReleased();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isModelReleased <em>Model Released</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Model Released</em>' attribute is set.
	 * @see #unsetModelReleased()
	 * @see #isModelReleased()
	 * @see #setModelReleased(boolean)
	 * @generated
	 */
	boolean isSetModelReleased();

	/**
	 * Returns the value of the '<em><b>Model Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				Version of the Model (content versioning).
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Version</em>' attribute.
	 * @see #setModelVersion(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_ModelVersion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='modelVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	String getModelVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getModelVersion <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Version</em>' attribute.
	 * @see #getModelVersion()
	 * @generated
	 */
	void setModelVersion(String value);

	/**
	 * Returns the value of the '<em><b>Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				A 64-bit number uniquely identifying the model in the model repository.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Oid</em>' attribute.
	 * @see #isSetOid()
	 * @see #unsetOid()
	 * @see #setOid(long)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_Oid()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long"
	 *        extendedMetaData="kind='attribute' name='oid' namespace='##targetNamespace'"
	 * @generated
	 */
	long getOid();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Oid</em>' attribute.
	 * @see #isSetOid()
	 * @see #unsetOid()
	 * @see #getOid()
	 * @generated
	 */
	void setOid(long value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOid()
	 * @see #getOid()
	 * @see #setOid(long)
	 * @generated
	 */
	void unsetOid();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getOid <em>Oid</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Oid</em>' attribute is set.
	 * @see #unsetOid()
	 * @see #getOid()
	 * @see #setOid(long)
	 * @generated
	 */
	boolean isSetOid();

	/**
	 * Returns the value of the '<em><b>Parameter Mapping Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * BPMN2.0 Assignment: stardust mapping oid
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter Mapping Oid</em>' attribute.
	 * @see #setParameterMappingOid(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_ParameterMappingOid()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='parameterMappingOid' namespace='##targetNamespace'"
	 * @generated
	 */
	String getParameterMappingOid();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getParameterMappingOid <em>Parameter Mapping Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Mapping Oid</em>' attribute.
	 * @see #getParameterMappingOid()
	 * @generated
	 */
	void setParameterMappingOid(String value);

	/**
	 * Returns the value of the '<em><b>Stardust Ignore</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that a certain element should not be transformed (i.e. start event).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stardust Ignore</em>' attribute.
	 * @see #isSetStardustIgnore()
	 * @see #unsetStardustIgnore()
	 * @see #setStardustIgnore(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustIgnore()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='stardustIgnore' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isStardustIgnore();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isStardustIgnore <em>Stardust Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Ignore</em>' attribute.
	 * @see #isSetStardustIgnore()
	 * @see #unsetStardustIgnore()
	 * @see #isStardustIgnore()
	 * @generated
	 */
	void setStardustIgnore(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isStardustIgnore <em>Stardust Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStardustIgnore()
	 * @see #isStardustIgnore()
	 * @see #setStardustIgnore(boolean)
	 * @generated
	 */
	void unsetStardustIgnore();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isStardustIgnore <em>Stardust Ignore</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Stardust Ignore</em>' attribute is set.
	 * @see #unsetStardustIgnore()
	 * @see #isStardustIgnore()
	 * @see #setStardustIgnore(boolean)
	 * @generated
	 */
	boolean isSetStardustIgnore();

	/**
	 * Returns the value of the '<em><b>Stardust Property Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Property Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Property Id</em>' attribute.
	 * @see #setStardustPropertyId(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_StardustPropertyId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='stardustPropertyId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getStardustPropertyId();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getStardustPropertyId <em>Stardust Property Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Property Id</em>' attribute.
	 * @see #getStardustPropertyId()
	 * @generated
	 */
	void setStardustPropertyId(String value);

	/**
	 * Returns the value of the '<em><b>Synthetic Item Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the item definition has been generated (i.e. for structural conformance with BPMN). Normally these items are not transformed but used to refer accesspoints.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Synthetic Item Definition</em>' attribute.
	 * @see #isSetSyntheticItemDefinition()
	 * @see #unsetSyntheticItemDefinition()
	 * @see #setSyntheticItemDefinition(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_SyntheticItemDefinition()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='syntheticItemDefinition' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSyntheticItemDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition <em>Synthetic Item Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synthetic Item Definition</em>' attribute.
	 * @see #isSetSyntheticItemDefinition()
	 * @see #unsetSyntheticItemDefinition()
	 * @see #isSyntheticItemDefinition()
	 * @generated
	 */
	void setSyntheticItemDefinition(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition <em>Synthetic Item Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSyntheticItemDefinition()
	 * @see #isSyntheticItemDefinition()
	 * @see #setSyntheticItemDefinition(boolean)
	 * @generated
	 */
	void unsetSyntheticItemDefinition();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticItemDefinition <em>Synthetic Item Definition</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Synthetic Item Definition</em>' attribute is set.
	 * @see #unsetSyntheticItemDefinition()
	 * @see #isSyntheticItemDefinition()
	 * @see #setSyntheticItemDefinition(boolean)
	 * @generated
	 */
	boolean isSetSyntheticItemDefinition();

	/**
	 * Returns the value of the '<em><b>Synthetic Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the property has not been modelled by the user but has been generated for conformance or convenience (e.g. predefined properties already available in the target model). Thus, these are normally not transformed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Synthetic Property</em>' attribute.
	 * @see #isSetSyntheticProperty()
	 * @see #unsetSyntheticProperty()
	 * @see #setSyntheticProperty(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_SyntheticProperty()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='syntheticProperty' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSyntheticProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty <em>Synthetic Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synthetic Property</em>' attribute.
	 * @see #isSetSyntheticProperty()
	 * @see #unsetSyntheticProperty()
	 * @see #isSyntheticProperty()
	 * @generated
	 */
	void setSyntheticProperty(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty <em>Synthetic Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSyntheticProperty()
	 * @see #isSyntheticProperty()
	 * @see #setSyntheticProperty(boolean)
	 * @generated
	 */
	void unsetSyntheticProperty();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#isSyntheticProperty <em>Synthetic Property</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Synthetic Property</em>' attribute is set.
	 * @see #unsetSyntheticProperty()
	 * @see #isSyntheticProperty()
	 * @see #setSyntheticProperty(boolean)
	 * @generated
	 */
	boolean isSetSyntheticProperty();

	/**
	 * Returns the value of the '<em><b>Trigger Access Point Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * BPMN2.0 Assignment-From/To: reference to a stardust trigger accesspoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger Access Point Ref</em>' attribute.
	 * @see #setTriggerAccessPointRef(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_TriggerAccessPointRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='triggerAccessPointRef' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTriggerAccessPointRef();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getTriggerAccessPointRef <em>Trigger Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger Access Point Ref</em>' attribute.
	 * @see #getTriggerAccessPointRef()
	 * @generated
	 */
	void setTriggerAccessPointRef(String value);

	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 				The vendor of the software used to create and edit this model. Usually
	 * 				this will be "carnot".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getDocumentRoot_Vendor()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='vendor' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.DocumentRoot#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

} // DocumentRoot
