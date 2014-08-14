/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.DocumentRoot;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustAttributesType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustInterfaceType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustMessageStartEventType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustModelType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustSeqenceFlowType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustServiceTaskType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustStartEventType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustSubprocessType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustTimerStartEventType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustUserTaskType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.TStardustActivity;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.TStardustCommon;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustActivity <em>Stardust Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustCommon <em>Stardust Common</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustInterface <em>Stardust Interface</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustMessageStartEvent <em>Stardust Message Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustModel <em>Stardust Model</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustResource <em>Stardust Resource</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustSeqenceFlow <em>Stardust Seqence Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustServiceTask <em>Stardust Service Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustStartEvent <em>Stardust Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustSubprocess <em>Stardust Subprocess</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustTimerStartEvent <em>Stardust Timer Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getStardustUserTask <em>Stardust User Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getApplicationAccessPointRef <em>Application Access Point Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getCarnotVersion <em>Carnot Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getInteractiveApplicationRef <em>Interactive Application Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getModelOID <em>Model OID</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getOid <em>Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getParameterMappingOid <em>Parameter Mapping Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getTriggerAccessPointRef <em>Trigger Access Point Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.impl.DocumentRootImpl#getVendor <em>Vendor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getApplicationAccessPointRef() <em>Application Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationAccessPointRef()
	 * @generated
	 * @ordered
	 */
	protected static final String APPLICATION_ACCESS_POINT_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getApplicationAccessPointRef() <em>Application Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationAccessPointRef()
	 * @generated
	 * @ordered
	 */
	protected String applicationAccessPointRef = APPLICATION_ACCESS_POINT_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCarnotVersion() <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCarnotVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String CARNOT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCarnotVersion() <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCarnotVersion()
	 * @generated
	 * @ordered
	 */
	protected String carnotVersion = CARNOT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final XMLGregorianCalendar CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected XMLGregorianCalendar created = CREATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getInteractiveApplicationRef() <em>Interactive Application Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractiveApplicationRef()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERACTIVE_APPLICATION_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInteractiveApplicationRef() <em>Interactive Application Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractiveApplicationRef()
	 * @generated
	 * @ordered
	 */
	protected String interactiveApplicationRef = INTERACTIVE_APPLICATION_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelOID() <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelOID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MODEL_OID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelOID() <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelOID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger modelOID = MODEL_OID_EDEFAULT;

	/**
	 * The default value of the '{@link #getOid() <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOid()
	 * @generated
	 * @ordered
	 */
	protected static final long OID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getOid() <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOid()
	 * @generated
	 * @ordered
	 */
	protected long oid = OID_EDEFAULT;

	/**
	 * This is true if the Oid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean oidESet;

	/**
	 * The default value of the '{@link #getParameterMappingOid() <em>Parameter Mapping Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterMappingOid()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_MAPPING_OID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterMappingOid() <em>Parameter Mapping Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterMappingOid()
	 * @generated
	 * @ordered
	 */
	protected String parameterMappingOid = PARAMETER_MAPPING_OID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTriggerAccessPointRef() <em>Trigger Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerAccessPointRef()
	 * @generated
	 * @ordered
	 */
	protected static final String TRIGGER_ACCESS_POINT_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTriggerAccessPointRef() <em>Trigger Access Point Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerAccessPointRef()
	 * @generated
	 * @ordered
	 */
	protected String triggerAccessPointRef = TRIGGER_ACCESS_POINT_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.eINSTANCE.getDocumentRoot();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, SdbpmnPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeType getDataType() {
		return (DataTypeType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_DataType(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataType(DataTypeType newDataType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_DataType(), newDataType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(DataTypeType newDataType) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_DataType(), newDataType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStardustActivity getStardustActivity() {
		return (TStardustActivity)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustActivity(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustActivity(TStardustActivity newStardustActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustActivity(), newStardustActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustActivity(TStardustActivity newStardustActivity) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustActivity(), newStardustActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustAttributesType getStardustAttributes() {
		return (StardustAttributesType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustAttributes(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustAttributes(StardustAttributesType newStardustAttributes, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustAttributes(), newStardustAttributes, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustAttributes(StardustAttributesType newStardustAttributes) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustAttributes(), newStardustAttributes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStardustCommon getStardustCommon() {
		return (TStardustCommon)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustCommon(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustCommon(TStardustCommon newStardustCommon, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustCommon(), newStardustCommon, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustCommon(TStardustCommon newStardustCommon) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustCommon(), newStardustCommon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustInterfaceType getStardustInterface() {
		return (StardustInterfaceType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustInterface(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustInterface(StardustInterfaceType newStardustInterface, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustInterface(), newStardustInterface, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustInterface(StardustInterfaceType newStardustInterface) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustInterface(), newStardustInterface);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustMessageStartEventType getStardustMessageStartEvent() {
		return (StardustMessageStartEventType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustMessageStartEvent(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustMessageStartEvent(StardustMessageStartEventType newStardustMessageStartEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustMessageStartEvent(), newStardustMessageStartEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustMessageStartEvent(StardustMessageStartEventType newStardustMessageStartEvent) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustMessageStartEvent(), newStardustMessageStartEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustModelType getStardustModel() {
		return (StardustModelType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustModel(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustModel(StardustModelType newStardustModel, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustModel(), newStardustModel, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustModel(StardustModelType newStardustModel) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustModel(), newStardustModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustResourceType getStardustResource() {
		return (StardustResourceType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustResource(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustResource(StardustResourceType newStardustResource, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustResource(), newStardustResource, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustResource(StardustResourceType newStardustResource) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustResource(), newStardustResource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustSeqenceFlowType getStardustSeqenceFlow() {
		return (StardustSeqenceFlowType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSeqenceFlow(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustSeqenceFlow(StardustSeqenceFlowType newStardustSeqenceFlow, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSeqenceFlow(), newStardustSeqenceFlow, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustSeqenceFlow(StardustSeqenceFlowType newStardustSeqenceFlow) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSeqenceFlow(), newStardustSeqenceFlow);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustServiceTaskType getStardustServiceTask() {
		return (StardustServiceTaskType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustServiceTask(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustServiceTask(StardustServiceTaskType newStardustServiceTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustServiceTask(), newStardustServiceTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustServiceTask(StardustServiceTaskType newStardustServiceTask) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustServiceTask(), newStardustServiceTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustStartEventType getStardustStartEvent() {
		return (StardustStartEventType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustStartEvent(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustStartEvent(StardustStartEventType newStardustStartEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustStartEvent(), newStardustStartEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustStartEvent(StardustStartEventType newStardustStartEvent) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustStartEvent(), newStardustStartEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustSubprocessType getStardustSubprocess() {
		return (StardustSubprocessType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSubprocess(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustSubprocess(StardustSubprocessType newStardustSubprocess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSubprocess(), newStardustSubprocess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustSubprocess(StardustSubprocessType newStardustSubprocess) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustSubprocess(), newStardustSubprocess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustTimerStartEventType getStardustTimerStartEvent() {
		return (StardustTimerStartEventType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustTimerStartEvent(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustTimerStartEvent(StardustTimerStartEventType newStardustTimerStartEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustTimerStartEvent(), newStardustTimerStartEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustTimerStartEvent(StardustTimerStartEventType newStardustTimerStartEvent) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustTimerStartEvent(), newStardustTimerStartEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StardustUserTaskType getStardustUserTask() {
		return (StardustUserTaskType)getMixed().get(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustUserTask(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStardustUserTask(StardustUserTaskType newStardustUserTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustUserTask(), newStardustUserTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStardustUserTask(StardustUserTaskType newStardustUserTask) {
		((FeatureMap.Internal)getMixed()).set(SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustUserTask(), newStardustUserTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getApplicationAccessPointRef() {
		return applicationAccessPointRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplicationAccessPointRef(String newApplicationAccessPointRef) {
		String oldApplicationAccessPointRef = applicationAccessPointRef;
		applicationAccessPointRef = newApplicationAccessPointRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF, oldApplicationAccessPointRef, applicationAccessPointRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__AUTHOR, oldAuthor, author));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCarnotVersion() {
		return carnotVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCarnotVersion(String newCarnotVersion) {
		String oldCarnotVersion = carnotVersion;
		carnotVersion = newCarnotVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__CARNOT_VERSION, oldCarnotVersion, carnotVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(XMLGregorianCalendar newCreated) {
		XMLGregorianCalendar oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__CREATED, oldCreated, created));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInteractiveApplicationRef() {
		return interactiveApplicationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInteractiveApplicationRef(String newInteractiveApplicationRef) {
		String oldInteractiveApplicationRef = interactiveApplicationRef;
		interactiveApplicationRef = newInteractiveApplicationRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF, oldInteractiveApplicationRef, interactiveApplicationRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getModelOID() {
		return modelOID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelOID(BigInteger newModelOID) {
		BigInteger oldModelOID = modelOID;
		modelOID = newModelOID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__MODEL_OID, oldModelOID, modelOID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getOid() {
		return oid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOid(long newOid) {
		long oldOid = oid;
		oid = newOid;
		boolean oldOidESet = oidESet;
		oidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__OID, oldOid, oid, !oldOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOid() {
		long oldOid = oid;
		boolean oldOidESet = oidESet;
		oid = OID_EDEFAULT;
		oidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.DOCUMENT_ROOT__OID, oldOid, OID_EDEFAULT, oldOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOid() {
		return oidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParameterMappingOid() {
		return parameterMappingOid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterMappingOid(String newParameterMappingOid) {
		String oldParameterMappingOid = parameterMappingOid;
		parameterMappingOid = newParameterMappingOid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__PARAMETER_MAPPING_OID, oldParameterMappingOid, parameterMappingOid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTriggerAccessPointRef() {
		return triggerAccessPointRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTriggerAccessPointRef(String newTriggerAccessPointRef) {
		String oldTriggerAccessPointRef = triggerAccessPointRef;
		triggerAccessPointRef = newTriggerAccessPointRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF, oldTriggerAccessPointRef, triggerAccessPointRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.DOCUMENT_ROOT__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SdbpmnPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__DATA_TYPE:
				return basicSetDataType(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ACTIVITY:
				return basicSetStardustActivity(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ATTRIBUTES:
				return basicSetStardustAttributes(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_COMMON:
				return basicSetStardustCommon(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_INTERFACE:
				return basicSetStardustInterface(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT:
				return basicSetStardustMessageStartEvent(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MODEL:
				return basicSetStardustModel(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_RESOURCE:
				return basicSetStardustResource(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW:
				return basicSetStardustSeqenceFlow(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SERVICE_TASK:
				return basicSetStardustServiceTask(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_START_EVENT:
				return basicSetStardustStartEvent(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SUBPROCESS:
				return basicSetStardustSubprocess(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT:
				return basicSetStardustTimerStartEvent(null, msgs);
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_USER_TASK:
				return basicSetStardustUserTask(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case SdbpmnPackage.DOCUMENT_ROOT__DATA_TYPE:
				return getDataType();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ACTIVITY:
				return getStardustActivity();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ATTRIBUTES:
				return getStardustAttributes();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_COMMON:
				return getStardustCommon();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_INTERFACE:
				return getStardustInterface();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT:
				return getStardustMessageStartEvent();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MODEL:
				return getStardustModel();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_RESOURCE:
				return getStardustResource();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW:
				return getStardustSeqenceFlow();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SERVICE_TASK:
				return getStardustServiceTask();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_START_EVENT:
				return getStardustStartEvent();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SUBPROCESS:
				return getStardustSubprocess();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT:
				return getStardustTimerStartEvent();
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_USER_TASK:
				return getStardustUserTask();
			case SdbpmnPackage.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF:
				return getApplicationAccessPointRef();
			case SdbpmnPackage.DOCUMENT_ROOT__AUTHOR:
				return getAuthor();
			case SdbpmnPackage.DOCUMENT_ROOT__CARNOT_VERSION:
				return getCarnotVersion();
			case SdbpmnPackage.DOCUMENT_ROOT__CREATED:
				return getCreated();
			case SdbpmnPackage.DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF:
				return getInteractiveApplicationRef();
			case SdbpmnPackage.DOCUMENT_ROOT__MODEL_OID:
				return getModelOID();
			case SdbpmnPackage.DOCUMENT_ROOT__OID:
				return getOid();
			case SdbpmnPackage.DOCUMENT_ROOT__PARAMETER_MAPPING_OID:
				return getParameterMappingOid();
			case SdbpmnPackage.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF:
				return getTriggerAccessPointRef();
			case SdbpmnPackage.DOCUMENT_ROOT__VENDOR:
				return getVendor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SdbpmnPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__DATA_TYPE:
				setDataType((DataTypeType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ACTIVITY:
				setStardustActivity((TStardustActivity)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_COMMON:
				setStardustCommon((TStardustCommon)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_INTERFACE:
				setStardustInterface((StardustInterfaceType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT:
				setStardustMessageStartEvent((StardustMessageStartEventType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MODEL:
				setStardustModel((StardustModelType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_RESOURCE:
				setStardustResource((StardustResourceType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW:
				setStardustSeqenceFlow((StardustSeqenceFlowType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SERVICE_TASK:
				setStardustServiceTask((StardustServiceTaskType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_START_EVENT:
				setStardustStartEvent((StardustStartEventType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SUBPROCESS:
				setStardustSubprocess((StardustSubprocessType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT:
				setStardustTimerStartEvent((StardustTimerStartEventType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_USER_TASK:
				setStardustUserTask((StardustUserTaskType)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF:
				setApplicationAccessPointRef((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__CARNOT_VERSION:
				setCarnotVersion((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__CREATED:
				setCreated((XMLGregorianCalendar)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF:
				setInteractiveApplicationRef((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__MODEL_OID:
				setModelOID((BigInteger)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__OID:
				setOid((Long)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__PARAMETER_MAPPING_OID:
				setParameterMappingOid((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF:
				setTriggerAccessPointRef((String)newValue);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__VENDOR:
				setVendor((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SdbpmnPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__DATA_TYPE:
				setDataType((DataTypeType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ACTIVITY:
				setStardustActivity((TStardustActivity)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ATTRIBUTES:
				setStardustAttributes((StardustAttributesType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_COMMON:
				setStardustCommon((TStardustCommon)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_INTERFACE:
				setStardustInterface((StardustInterfaceType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT:
				setStardustMessageStartEvent((StardustMessageStartEventType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MODEL:
				setStardustModel((StardustModelType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_RESOURCE:
				setStardustResource((StardustResourceType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW:
				setStardustSeqenceFlow((StardustSeqenceFlowType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SERVICE_TASK:
				setStardustServiceTask((StardustServiceTaskType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_START_EVENT:
				setStardustStartEvent((StardustStartEventType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SUBPROCESS:
				setStardustSubprocess((StardustSubprocessType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT:
				setStardustTimerStartEvent((StardustTimerStartEventType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_USER_TASK:
				setStardustUserTask((StardustUserTaskType)null);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF:
				setApplicationAccessPointRef(APPLICATION_ACCESS_POINT_REF_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__CARNOT_VERSION:
				setCarnotVersion(CARNOT_VERSION_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF:
				setInteractiveApplicationRef(INTERACTIVE_APPLICATION_REF_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__MODEL_OID:
				setModelOID(MODEL_OID_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__OID:
				unsetOid();
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__PARAMETER_MAPPING_OID:
				setParameterMappingOid(PARAMETER_MAPPING_OID_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF:
				setTriggerAccessPointRef(TRIGGER_ACCESS_POINT_REF_EDEFAULT);
				return;
			case SdbpmnPackage.DOCUMENT_ROOT__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SdbpmnPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case SdbpmnPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case SdbpmnPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case SdbpmnPackage.DOCUMENT_ROOT__DATA_TYPE:
				return getDataType() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ACTIVITY:
				return getStardustActivity() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_ATTRIBUTES:
				return getStardustAttributes() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_COMMON:
				return getStardustCommon() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_INTERFACE:
				return getStardustInterface() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MESSAGE_START_EVENT:
				return getStardustMessageStartEvent() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_MODEL:
				return getStardustModel() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_RESOURCE:
				return getStardustResource() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SEQENCE_FLOW:
				return getStardustSeqenceFlow() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SERVICE_TASK:
				return getStardustServiceTask() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_START_EVENT:
				return getStardustStartEvent() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_SUBPROCESS:
				return getStardustSubprocess() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_TIMER_START_EVENT:
				return getStardustTimerStartEvent() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__STARDUST_USER_TASK:
				return getStardustUserTask() != null;
			case SdbpmnPackage.DOCUMENT_ROOT__APPLICATION_ACCESS_POINT_REF:
				return APPLICATION_ACCESS_POINT_REF_EDEFAULT == null ? applicationAccessPointRef != null : !APPLICATION_ACCESS_POINT_REF_EDEFAULT.equals(applicationAccessPointRef);
			case SdbpmnPackage.DOCUMENT_ROOT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case SdbpmnPackage.DOCUMENT_ROOT__CARNOT_VERSION:
				return CARNOT_VERSION_EDEFAULT == null ? carnotVersion != null : !CARNOT_VERSION_EDEFAULT.equals(carnotVersion);
			case SdbpmnPackage.DOCUMENT_ROOT__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case SdbpmnPackage.DOCUMENT_ROOT__INTERACTIVE_APPLICATION_REF:
				return INTERACTIVE_APPLICATION_REF_EDEFAULT == null ? interactiveApplicationRef != null : !INTERACTIVE_APPLICATION_REF_EDEFAULT.equals(interactiveApplicationRef);
			case SdbpmnPackage.DOCUMENT_ROOT__MODEL_OID:
				return MODEL_OID_EDEFAULT == null ? modelOID != null : !MODEL_OID_EDEFAULT.equals(modelOID);
			case SdbpmnPackage.DOCUMENT_ROOT__OID:
				return isSetOid();
			case SdbpmnPackage.DOCUMENT_ROOT__PARAMETER_MAPPING_OID:
				return PARAMETER_MAPPING_OID_EDEFAULT == null ? parameterMappingOid != null : !PARAMETER_MAPPING_OID_EDEFAULT.equals(parameterMappingOid);
			case SdbpmnPackage.DOCUMENT_ROOT__TRIGGER_ACCESS_POINT_REF:
				return TRIGGER_ACCESS_POINT_REF_EDEFAULT == null ? triggerAccessPointRef != null : !TRIGGER_ACCESS_POINT_REF_EDEFAULT.equals(triggerAccessPointRef);
			case SdbpmnPackage.DOCUMENT_ROOT__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", applicationAccessPointRef: ");
		result.append(applicationAccessPointRef);
		result.append(", author: ");
		result.append(author);
		result.append(", carnotVersion: ");
		result.append(carnotVersion);
		result.append(", created: ");
		result.append(created);
		result.append(", interactiveApplicationRef: ");
		result.append(interactiveApplicationRef);
		result.append(", modelOID: ");
		result.append(modelOID);
		result.append(", oid: ");
		if (oidESet) result.append(oid); else result.append("<unset>");
		result.append(", parameterMappingOid: ");
		result.append(parameterMappingOid);
		result.append(", triggerAccessPointRef: ");
		result.append(triggerAccessPointRef);
		result.append(", vendor: ");
		result.append(vendor);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
