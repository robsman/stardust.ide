/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ITypedElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Link Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getSourceSymbol <em>Source Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.GenericLinkConnectionTypeImpl#getTargetSymbol <em>Target Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenericLinkConnectionTypeImpl extends MinimalEObjectImpl.Container implements GenericLinkConnectionType {
	/**
	 * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected static final long ELEMENT_OID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementOid()
	 * @generated
	 * @ordered
	 */
	protected long elementOid = ELEMENT_OID_EDEFAULT;

	/**
	 * This is true if the Element Oid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elementOidESet;

	/**
	 * The default value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorderColor()
	 * @generated
	 * @ordered
	 */
	protected static final String BORDER_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorderColor()
	 * @generated
	 * @ordered
	 */
	protected String borderColor = BORDER_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected static final String FILL_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected String fillColor = FILL_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected String style = STYLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferingToConnections() <em>Refering To Connections</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferingToConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<RefersToConnectionType> referingToConnections;

	/**
	 * The cached value of the '{@link #getReferingFromConnections() <em>Refering From Connections</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferingFromConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<RefersToConnectionType> referingFromConnections;

	/**
	 * The default value of the '{@link #getSourceAnchor() <em>Source Anchor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceAnchor()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_ANCHOR_EDEFAULT = "center";

	/**
	 * The cached value of the '{@link #getSourceAnchor() <em>Source Anchor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceAnchor()
	 * @generated
	 * @ordered
	 */
	protected String sourceAnchor = SOURCE_ANCHOR_EDEFAULT;

	/**
	 * This is true if the Source Anchor attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sourceAnchorESet;

	/**
	 * The default value of the '{@link #getTargetAnchor() <em>Target Anchor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetAnchor()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_ANCHOR_EDEFAULT = "center";

	/**
	 * The cached value of the '{@link #getTargetAnchor() <em>Target Anchor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetAnchor()
	 * @generated
	 * @ordered
	 */
	protected String targetAnchor = TARGET_ANCHOR_EDEFAULT;

	/**
	 * This is true if the Target Anchor attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean targetAnchorESet;

	/**
	 * The default value of the '{@link #getRouting() <em>Routing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouting()
	 * @generated
	 * @ordered
	 */
	protected static final RoutingType ROUTING_EDEFAULT = RoutingType.DEFAULT;

	/**
	 * The cached value of the '{@link #getRouting() <em>Routing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouting()
	 * @generated
	 * @ordered
	 */
	protected RoutingType routing = ROUTING_EDEFAULT;

	/**
	 * This is true if the Routing attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean routingESet;

	/**
	 * The cached value of the '{@link #getCoordinates() <em>Coordinates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoordinates()
	 * @generated
	 * @ordered
	 */
	protected EList<Coordinates> coordinates;

	/**
	 * The cached value of the '{@link #getLinkType() <em>Link Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkType()
	 * @generated
	 * @ordered
	 */
	protected LinkTypeType linkType;

	/**
	 * The cached value of the '{@link #getSourceSymbol() <em>Source Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceSymbol()
	 * @generated
	 * @ordered
	 */
	protected INodeSymbol sourceSymbol;

	/**
	 * This is true if the Source Symbol reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sourceSymbolESet;

	/**
	 * The cached value of the '{@link #getTargetSymbol() <em>Target Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetSymbol()
	 * @generated
	 * @ordered
	 */
	protected INodeSymbol targetSymbol;

	/**
	 * This is true if the Target Symbol reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean targetSymbolESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericLinkConnectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getGenericLinkConnectionType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getElementOid() {
		return elementOid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementOid(long newElementOid) {
		long oldElementOid = elementOid;
		elementOid = newElementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetElementOid() {
		long oldElementOid = elementOid;
		boolean oldElementOidESet = elementOidESet;
		elementOid = ELEMENT_OID_EDEFAULT;
		elementOidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetElementOid() {
		return elementOidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBorderColor(String newBorderColor) {
		String oldBorderColor = borderColor;
		borderColor = newBorderColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFillColor() {
		return fillColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillColor(String newFillColor) {
		String oldFillColor = fillColor;
		fillColor = newFillColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR, oldFillColor, fillColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(String newStyle) {
		String oldStyle = style;
		style = newStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingToConnections() {
		if (referingToConnections == null) {
			referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__TO);
		}
		return referingToConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingFromConnections() {
		if (referingFromConnections == null) {
			referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__FROM);
		}
		return referingFromConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceAnchor() {
		return sourceAnchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceAnchor(String newSourceAnchor) {
		String oldSourceAnchor = sourceAnchor;
		sourceAnchor = newSourceAnchor;
		boolean oldSourceAnchorESet = sourceAnchorESet;
		sourceAnchorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, sourceAnchor, !oldSourceAnchorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSourceAnchor() {
		String oldSourceAnchor = sourceAnchor;
		boolean oldSourceAnchorESet = sourceAnchorESet;
		sourceAnchor = SOURCE_ANCHOR_EDEFAULT;
		sourceAnchorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, SOURCE_ANCHOR_EDEFAULT, oldSourceAnchorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSourceAnchor() {
		return sourceAnchorESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetAnchor() {
		return targetAnchor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetAnchor(String newTargetAnchor) {
		String oldTargetAnchor = targetAnchor;
		targetAnchor = newTargetAnchor;
		boolean oldTargetAnchorESet = targetAnchorESet;
		targetAnchorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, targetAnchor, !oldTargetAnchorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTargetAnchor() {
		String oldTargetAnchor = targetAnchor;
		boolean oldTargetAnchorESet = targetAnchorESet;
		targetAnchor = TARGET_ANCHOR_EDEFAULT;
		targetAnchorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, TARGET_ANCHOR_EDEFAULT, oldTargetAnchorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTargetAnchor() {
		return targetAnchorESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutingType getRouting() {
		return routing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRouting(RoutingType newRouting) {
		RoutingType oldRouting = routing;
		routing = newRouting == null ? ROUTING_EDEFAULT : newRouting;
		boolean oldRoutingESet = routingESet;
		routingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING, oldRouting, routing, !oldRoutingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRouting() {
		RoutingType oldRouting = routing;
		boolean oldRoutingESet = routingESet;
		routing = ROUTING_EDEFAULT;
		routingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING, oldRouting, ROUTING_EDEFAULT, oldRoutingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRouting() {
		return routingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Coordinates> getCoordinates() {
		if (coordinates == null) {
			coordinates = new EObjectContainmentEList<Coordinates>(Coordinates.class, this, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES);
		}
		return coordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkTypeType getLinkType() {
		return linkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkType(LinkTypeType newLinkType, NotificationChain msgs) {
		LinkTypeType oldLinkType = linkType;
		linkType = newLinkType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE, oldLinkType, newLinkType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkType(LinkTypeType newLinkType) {
		if (newLinkType != linkType) {
			NotificationChain msgs = null;
			if (linkType != null)
				msgs = ((InternalEObject)linkType).eInverseRemove(this, CarnotPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
			if (newLinkType != null)
				msgs = ((InternalEObject)newLinkType).eInverseAdd(this, CarnotPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
			msgs = basicSetLinkType(newLinkType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE, newLinkType, newLinkType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INodeSymbol getSourceSymbol() {
		return sourceSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceSymbol(INodeSymbol newSourceSymbol, NotificationChain msgs) {
		INodeSymbol oldSourceSymbol = sourceSymbol;
		sourceSymbol = newSourceSymbol;
		boolean oldSourceSymbolESet = sourceSymbolESet;
		sourceSymbolESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, oldSourceSymbol, newSourceSymbol, !oldSourceSymbolESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceSymbol(INodeSymbol newSourceSymbol) {
		if (newSourceSymbol != sourceSymbol) {
			NotificationChain msgs = null;
			if (sourceSymbol != null)
				msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
			if (newSourceSymbol != null)
				msgs = ((InternalEObject)newSourceSymbol).eInverseAdd(this, CarnotPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
			msgs = basicSetSourceSymbol(newSourceSymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldSourceSymbolESet = sourceSymbolESet;
			sourceSymbolESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, newSourceSymbol, newSourceSymbol, !oldSourceSymbolESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetSourceSymbol(NotificationChain msgs) {
		INodeSymbol oldSourceSymbol = sourceSymbol;
		sourceSymbol = null;
		boolean oldSourceSymbolESet = sourceSymbolESet;
		sourceSymbolESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, oldSourceSymbol, null, oldSourceSymbolESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSourceSymbol() {
		if (sourceSymbol != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
			msgs = basicUnsetSourceSymbol(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldSourceSymbolESet = sourceSymbolESet;
			sourceSymbolESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, null, null, oldSourceSymbolESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSourceSymbol() {
		return sourceSymbolESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INodeSymbol getTargetSymbol() {
		return targetSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetSymbol(INodeSymbol newTargetSymbol, NotificationChain msgs) {
		INodeSymbol oldTargetSymbol = targetSymbol;
		targetSymbol = newTargetSymbol;
		boolean oldTargetSymbolESet = targetSymbolESet;
		targetSymbolESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, oldTargetSymbol, newTargetSymbol, !oldTargetSymbolESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetSymbol(INodeSymbol newTargetSymbol) {
		if (newTargetSymbol != targetSymbol) {
			NotificationChain msgs = null;
			if (targetSymbol != null)
				msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
			if (newTargetSymbol != null)
				msgs = ((InternalEObject)newTargetSymbol).eInverseAdd(this, CarnotPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
			msgs = basicSetTargetSymbol(newTargetSymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTargetSymbolESet = targetSymbolESet;
			targetSymbolESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, newTargetSymbol, newTargetSymbol, !oldTargetSymbolESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetTargetSymbol(NotificationChain msgs) {
		INodeSymbol oldTargetSymbol = targetSymbol;
		targetSymbol = null;
		boolean oldTargetSymbolESet = targetSymbolESet;
		targetSymbolESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, oldTargetSymbol, null, oldTargetSymbolESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTargetSymbol() {
		if (targetSymbol != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
			msgs = basicUnsetTargetSymbol(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTargetSymbolESet = targetSymbolESet;
			targetSymbolESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, null, null, oldTargetSymbolESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTargetSymbol() {
		return targetSymbolESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMetaType getMetaType() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INodeSymbol getSourceNode() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceNode(INodeSymbol nodeSymbol) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INodeSymbol getTargetNode() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetNode(INodeSymbol nodeSymbol) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				if (linkType != null)
					msgs = ((InternalEObject)linkType).eInverseRemove(this, CarnotPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
				return basicSetLinkType((LinkTypeType)otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				if (sourceSymbol != null)
					msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
				return basicSetSourceSymbol((INodeSymbol)otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				if (targetSymbol != null)
					msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
				return basicSetTargetSymbol((INodeSymbol)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
				return ((InternalEList<?>)getCoordinates()).basicRemove(otherEnd, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				return basicSetLinkType(null, msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				return basicUnsetSourceSymbol(msgs);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				return basicUnsetTargetSymbol(msgs);
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
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
				return getBorderColor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
				return getFillColor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
				return getStyle();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return getReferingToConnections();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return getReferingFromConnections();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
				return getSourceAnchor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
				return getTargetAnchor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
				return getRouting();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
				return getCoordinates();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				return getLinkType();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				return getSourceSymbol();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				return getTargetSymbol();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor((String)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
				setSourceAnchor((String)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
				setTargetAnchor((String)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
				setRouting((RoutingType)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				getCoordinates().addAll((Collection<? extends Coordinates>)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				setLinkType((LinkTypeType)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				setSourceSymbol((INodeSymbol)newValue);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				setTargetSymbol((INodeSymbol)newValue);
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
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor(BORDER_COLOR_EDEFAULT);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
				unsetSourceAnchor();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
				unsetTargetAnchor();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
				unsetRouting();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				setLinkType((LinkTypeType)null);
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				unsetSourceSymbol();
				return;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				unsetTargetSymbol();
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
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
				return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return referingToConnections != null && !referingToConnections.isEmpty();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return referingFromConnections != null && !referingFromConnections.isEmpty();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
				return isSetSourceAnchor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
				return isSetTargetAnchor();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
				return isSetRouting();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
				return coordinates != null && !coordinates.isEmpty();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
				return linkType != null;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
				return isSetSourceSymbol();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
				return isSetTargetSymbol();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == ITypedElement.class) {
			switch (baseOperationID) {
				case CarnotPackage.ITYPED_ELEMENT___GET_META_TYPE: return CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___GET_META_TYPE;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___GET_META_TYPE:
				return getMetaType();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___GET_SOURCE_NODE:
				return getSourceNode();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL:
				setSourceNode((INodeSymbol)arguments.get(0));
				return null;
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___GET_TARGET_NODE:
				return getTargetNode();
			case CarnotPackage.GENERIC_LINK_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL:
				setTargetNode((INodeSymbol)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (elementOid: ");
		if (elementOidESet) result.append(elementOid); else result.append("<unset>");
		result.append(", borderColor: ");
		result.append(borderColor);
		result.append(", fillColor: ");
		result.append(fillColor);
		result.append(", style: ");
		result.append(style);
		result.append(", sourceAnchor: ");
		if (sourceAnchorESet) result.append(sourceAnchor); else result.append("<unset>");
		result.append(", targetAnchor: ");
		if (targetAnchorESet) result.append(targetAnchor); else result.append("<unset>");
		result.append(", routing: ");
		if (routingESet) result.append(routing); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //GenericLinkConnectionTypeImpl
