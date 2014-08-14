/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType;

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
 * An implementation of the model object '<em><b>Team Lead Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getTeamSymbol <em>Team Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TeamLeadConnectionTypeImpl#getTeamLeadSymbol <em>Team Lead Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TeamLeadConnectionTypeImpl extends MinimalEObjectImpl.Container implements TeamLeadConnectionType {
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
	 * The cached value of the '{@link #getTeamSymbol() <em>Team Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeamSymbol()
	 * @generated
	 * @ordered
	 */
	protected OrganizationSymbolType teamSymbol;

	/**
	 * The cached value of the '{@link #getTeamLeadSymbol() <em>Team Lead Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeamLeadSymbol()
	 * @generated
	 * @ordered
	 */
	protected RoleSymbolType teamLeadSymbol;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TeamLeadConnectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getTeamLeadConnectionType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR, oldFillColor, fillColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingToConnections() {
		if (referingToConnections == null) {
			referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
			referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__FROM);
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, sourceAnchor, !oldSourceAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, SOURCE_ANCHOR_EDEFAULT, oldSourceAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, targetAnchor, !oldTargetAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, TARGET_ANCHOR_EDEFAULT, oldTargetAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING, oldRouting, routing, !oldRoutingESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING, oldRouting, ROUTING_EDEFAULT, oldRoutingESet));
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
			coordinates = new EObjectContainmentEList<Coordinates>(Coordinates.class, this, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES);
		}
		return coordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationSymbolType getTeamSymbol() {
		return teamSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTeamSymbol(OrganizationSymbolType newTeamSymbol, NotificationChain msgs) {
		OrganizationSymbolType oldTeamSymbol = teamSymbol;
		teamSymbol = newTeamSymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL, oldTeamSymbol, newTeamSymbol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTeamSymbol(OrganizationSymbolType newTeamSymbol) {
		if (newTeamSymbol != teamSymbol) {
			NotificationChain msgs = null;
			if (teamSymbol != null)
				msgs = ((InternalEObject)teamSymbol).eInverseRemove(this, CarnotPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD, OrganizationSymbolType.class, msgs);
			if (newTeamSymbol != null)
				msgs = ((InternalEObject)newTeamSymbol).eInverseAdd(this, CarnotPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD, OrganizationSymbolType.class, msgs);
			msgs = basicSetTeamSymbol(newTeamSymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL, newTeamSymbol, newTeamSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleSymbolType getTeamLeadSymbol() {
		return teamLeadSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTeamLeadSymbol(RoleSymbolType newTeamLeadSymbol, NotificationChain msgs) {
		RoleSymbolType oldTeamLeadSymbol = teamLeadSymbol;
		teamLeadSymbol = newTeamLeadSymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL, oldTeamLeadSymbol, newTeamLeadSymbol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTeamLeadSymbol(RoleSymbolType newTeamLeadSymbol) {
		if (newTeamLeadSymbol != teamLeadSymbol) {
			NotificationChain msgs = null;
			if (teamLeadSymbol != null)
				msgs = ((InternalEObject)teamLeadSymbol).eInverseRemove(this, CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS, RoleSymbolType.class, msgs);
			if (newTeamLeadSymbol != null)
				msgs = ((InternalEObject)newTeamLeadSymbol).eInverseAdd(this, CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS, RoleSymbolType.class, msgs);
			msgs = basicSetTeamLeadSymbol(newTeamLeadSymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL, newTeamLeadSymbol, newTeamLeadSymbol));
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				if (teamSymbol != null)
					msgs = ((InternalEObject)teamSymbol).eInverseRemove(this, CarnotPackage.ORGANIZATION_SYMBOL_TYPE__TEAM_LEAD, OrganizationSymbolType.class, msgs);
				return basicSetTeamSymbol((OrganizationSymbolType)otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				if (teamLeadSymbol != null)
					msgs = ((InternalEObject)teamLeadSymbol).eInverseRemove(this, CarnotPackage.ROLE_SYMBOL_TYPE__TEAMS, RoleSymbolType.class, msgs);
				return basicSetTeamLeadSymbol((RoleSymbolType)otherEnd, msgs);
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES:
				return ((InternalEList<?>)getCoordinates()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				return basicSetTeamSymbol(null, msgs);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				return basicSetTeamLeadSymbol(null, msgs);
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR:
				return getBorderColor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR:
				return getFillColor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__STYLE:
				return getStyle();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return getReferingToConnections();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return getReferingFromConnections();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR:
				return getSourceAnchor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR:
				return getTargetAnchor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING:
				return getRouting();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES:
				return getCoordinates();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				return getTeamSymbol();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				return getTeamLeadSymbol();
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor((String)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR:
				setSourceAnchor((String)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR:
				setTargetAnchor((String)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING:
				setRouting((RoutingType)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				getCoordinates().addAll((Collection<? extends Coordinates>)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				setTeamSymbol((OrganizationSymbolType)newValue);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				setTeamLeadSymbol((RoleSymbolType)newValue);
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor(BORDER_COLOR_EDEFAULT);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR:
				unsetSourceAnchor();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR:
				unsetTargetAnchor();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING:
				unsetRouting();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				setTeamSymbol((OrganizationSymbolType)null);
				return;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				setTeamLeadSymbol((RoleSymbolType)null);
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
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__BORDER_COLOR:
				return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return referingToConnections != null && !referingToConnections.isEmpty();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return referingFromConnections != null && !referingFromConnections.isEmpty();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__SOURCE_ANCHOR:
				return isSetSourceAnchor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TARGET_ANCHOR:
				return isSetTargetAnchor();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__ROUTING:
				return isSetRouting();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__COORDINATES:
				return coordinates != null && !coordinates.isEmpty();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_SYMBOL:
				return teamSymbol != null;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE__TEAM_LEAD_SYMBOL:
				return teamLeadSymbol != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE___GET_SOURCE_NODE:
				return getSourceNode();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL:
				setSourceNode((INodeSymbol)arguments.get(0));
				return null;
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE___GET_TARGET_NODE:
				return getTargetNode();
			case CarnotPackage.TEAM_LEAD_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL:
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

} //TeamLeadConnectionTypeImpl
