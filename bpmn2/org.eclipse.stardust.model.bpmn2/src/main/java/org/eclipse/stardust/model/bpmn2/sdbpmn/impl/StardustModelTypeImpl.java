/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.impl;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stardust Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getCarnotVersion <em>Carnot Version</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getModelOID <em>Model OID</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getOid <em>Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.impl.StardustModelTypeImpl#getVendor <em>Vendor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StardustModelTypeImpl extends MinimalEObjectImpl.Container implements StardustModelType {
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
	protected StardustModelTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SdbpmnPackage.Literals.STARDUST_MODEL_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__AUTHOR, oldAuthor, author));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__CARNOT_VERSION, oldCarnotVersion, carnotVersion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__CREATED, oldCreated, created));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__MODEL_OID, oldModelOID, modelOID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__OID, oldOid, oid, !oldOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, SdbpmnPackage.STARDUST_MODEL_TYPE__OID, oldOid, OID_EDEFAULT, oldOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SdbpmnPackage.STARDUST_MODEL_TYPE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SdbpmnPackage.STARDUST_MODEL_TYPE__AUTHOR:
				return getAuthor();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CARNOT_VERSION:
				return getCarnotVersion();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CREATED:
				return getCreated();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__MODEL_OID:
				return getModelOID();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__OID:
				return getOid();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__VENDOR:
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
			case SdbpmnPackage.STARDUST_MODEL_TYPE__AUTHOR:
				setAuthor((String)newValue);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CARNOT_VERSION:
				setCarnotVersion((String)newValue);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CREATED:
				setCreated((XMLGregorianCalendar)newValue);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__MODEL_OID:
				setModelOID((BigInteger)newValue);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__OID:
				setOid((Long)newValue);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__VENDOR:
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
			case SdbpmnPackage.STARDUST_MODEL_TYPE__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CARNOT_VERSION:
				setCarnotVersion(CARNOT_VERSION_EDEFAULT);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__MODEL_OID:
				setModelOID(MODEL_OID_EDEFAULT);
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__OID:
				unsetOid();
				return;
			case SdbpmnPackage.STARDUST_MODEL_TYPE__VENDOR:
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
			case SdbpmnPackage.STARDUST_MODEL_TYPE__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CARNOT_VERSION:
				return CARNOT_VERSION_EDEFAULT == null ? carnotVersion != null : !CARNOT_VERSION_EDEFAULT.equals(carnotVersion);
			case SdbpmnPackage.STARDUST_MODEL_TYPE__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case SdbpmnPackage.STARDUST_MODEL_TYPE__MODEL_OID:
				return MODEL_OID_EDEFAULT == null ? modelOID != null : !MODEL_OID_EDEFAULT.equals(modelOID);
			case SdbpmnPackage.STARDUST_MODEL_TYPE__OID:
				return isSetOid();
			case SdbpmnPackage.STARDUST_MODEL_TYPE__VENDOR:
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
		result.append(" (author: ");
		result.append(author);
		result.append(", carnotVersion: ");
		result.append(carnotVersion);
		result.append(", created: ");
		result.append(created);
		result.append(", modelOID: ");
		result.append(modelOID);
		result.append(", oid: ");
		if (oidESet) result.append(oid); else result.append("<unset>");
		result.append(", vendor: ");
		result.append(vendor);
		result.append(')');
		return result.toString();
	}

} //StardustModelTypeImpl
