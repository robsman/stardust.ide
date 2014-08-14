/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Coordinates;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoutingType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType;

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
 * An implementation of the model object '<em><b>Transition Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getSourceActivitySymbol <em>Source Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getTargetActivitySymbol <em>Target Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.TransitionConnectionTypeImpl#getTransition <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionConnectionTypeImpl extends MinimalEObjectImpl.Container implements TransitionConnectionType {
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
	 * The default value of the '{@link #getPoints() <em>Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoints()
	 * @generated
	 * @ordered
	 */
	protected static final String POINTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPoints() <em>Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoints()
	 * @generated
	 * @ordered
	 */
	protected String points = POINTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceActivitySymbol() <em>Source Activity Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceActivitySymbol()
	 * @generated
	 * @ordered
	 */
	protected IFlowObjectSymbol sourceActivitySymbol;

	/**
	 * The cached value of the '{@link #getTargetActivitySymbol() <em>Target Activity Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetActivitySymbol()
	 * @generated
	 * @ordered
	 */
	protected IFlowObjectSymbol targetActivitySymbol;

	/**
	 * The cached value of the '{@link #getTransition() <em>Transition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransition()
	 * @generated
	 * @ordered
	 */
	protected TransitionType transition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionConnectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getTransitionConnectionType();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__FILL_COLOR, oldFillColor, fillColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingToConnections() {
		if (referingToConnections == null) {
			referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
			referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__FROM);
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, sourceAnchor, !oldSourceAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, SOURCE_ANCHOR_EDEFAULT, oldSourceAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, targetAnchor, !oldTargetAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, TARGET_ANCHOR_EDEFAULT, oldTargetAnchorESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING, oldRouting, routing, !oldRoutingESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING, oldRouting, ROUTING_EDEFAULT, oldRoutingESet));
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
			coordinates = new EObjectContainmentEList<Coordinates>(Coordinates.class, this, CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES);
		}
		return coordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPoints() {
		return points;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPoints(String newPoints) {
		String oldPoints = points;
		points = newPoints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__POINTS, oldPoints, points));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IFlowObjectSymbol getSourceActivitySymbol() {
		return sourceActivitySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceActivitySymbol(IFlowObjectSymbol newSourceActivitySymbol, NotificationChain msgs) {
		IFlowObjectSymbol oldSourceActivitySymbol = sourceActivitySymbol;
		sourceActivitySymbol = newSourceActivitySymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL, oldSourceActivitySymbol, newSourceActivitySymbol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceActivitySymbol(IFlowObjectSymbol newSourceActivitySymbol) {
		if (newSourceActivitySymbol != sourceActivitySymbol) {
			NotificationChain msgs = null;
			if (sourceActivitySymbol != null)
				msgs = ((InternalEObject)sourceActivitySymbol).eInverseRemove(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS, IFlowObjectSymbol.class, msgs);
			if (newSourceActivitySymbol != null)
				msgs = ((InternalEObject)newSourceActivitySymbol).eInverseAdd(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS, IFlowObjectSymbol.class, msgs);
			msgs = basicSetSourceActivitySymbol(newSourceActivitySymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL, newSourceActivitySymbol, newSourceActivitySymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IFlowObjectSymbol getTargetActivitySymbol() {
		return targetActivitySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetActivitySymbol(IFlowObjectSymbol newTargetActivitySymbol, NotificationChain msgs) {
		IFlowObjectSymbol oldTargetActivitySymbol = targetActivitySymbol;
		targetActivitySymbol = newTargetActivitySymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL, oldTargetActivitySymbol, newTargetActivitySymbol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetActivitySymbol(IFlowObjectSymbol newTargetActivitySymbol) {
		if (newTargetActivitySymbol != targetActivitySymbol) {
			NotificationChain msgs = null;
			if (targetActivitySymbol != null)
				msgs = ((InternalEObject)targetActivitySymbol).eInverseRemove(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS, IFlowObjectSymbol.class, msgs);
			if (newTargetActivitySymbol != null)
				msgs = ((InternalEObject)newTargetActivitySymbol).eInverseAdd(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS, IFlowObjectSymbol.class, msgs);
			msgs = basicSetTargetActivitySymbol(newTargetActivitySymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL, newTargetActivitySymbol, newTargetActivitySymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType getTransition() {
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransition(TransitionType newTransition, NotificationChain msgs) {
		TransitionType oldTransition = transition;
		transition = newTransition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION, oldTransition, newTransition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransition(TransitionType newTransition) {
		if (newTransition != transition) {
			NotificationChain msgs = null;
			if (transition != null)
				msgs = ((InternalEObject)transition).eInverseRemove(this, CarnotPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS, TransitionType.class, msgs);
			if (newTransition != null)
				msgs = ((InternalEObject)newTransition).eInverseAdd(this, CarnotPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS, TransitionType.class, msgs);
			msgs = basicSetTransition(newTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION, newTransition, newTransition));
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				if (sourceActivitySymbol != null)
					msgs = ((InternalEObject)sourceActivitySymbol).eInverseRemove(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS, IFlowObjectSymbol.class, msgs);
				return basicSetSourceActivitySymbol((IFlowObjectSymbol)otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				if (targetActivitySymbol != null)
					msgs = ((InternalEObject)targetActivitySymbol).eInverseRemove(this, CarnotPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS, IFlowObjectSymbol.class, msgs);
				return basicSetTargetActivitySymbol((IFlowObjectSymbol)otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				if (transition != null)
					msgs = ((InternalEObject)transition).eInverseRemove(this, CarnotPackage.TRANSITION_TYPE__TRANSITION_CONNECTIONS, TransitionType.class, msgs);
				return basicSetTransition((TransitionType)otherEnd, msgs);
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES:
				return ((InternalEList<?>)getCoordinates()).basicRemove(otherEnd, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				return basicSetSourceActivitySymbol(null, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				return basicSetTargetActivitySymbol(null, msgs);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				return basicSetTransition(null, msgs);
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__BORDER_COLOR:
				return getBorderColor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__FILL_COLOR:
				return getFillColor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__STYLE:
				return getStyle();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return getReferingToConnections();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return getReferingFromConnections();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR:
				return getSourceAnchor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR:
				return getTargetAnchor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING:
				return getRouting();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES:
				return getCoordinates();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__POINTS:
				return getPoints();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				return getSourceActivitySymbol();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				return getTargetActivitySymbol();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				return getTransition();
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR:
				setSourceAnchor((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR:
				setTargetAnchor((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING:
				setRouting((RoutingType)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				getCoordinates().addAll((Collection<? extends Coordinates>)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__POINTS:
				setPoints((String)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				setSourceActivitySymbol((IFlowObjectSymbol)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				setTargetActivitySymbol((IFlowObjectSymbol)newValue);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				setTransition((TransitionType)newValue);
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__BORDER_COLOR:
				setBorderColor(BORDER_COLOR_EDEFAULT);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR:
				unsetSourceAnchor();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR:
				unsetTargetAnchor();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING:
				unsetRouting();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES:
				getCoordinates().clear();
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__POINTS:
				setPoints(POINTS_EDEFAULT);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				setSourceActivitySymbol((IFlowObjectSymbol)null);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				setTargetActivitySymbol((IFlowObjectSymbol)null);
				return;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				setTransition((TransitionType)null);
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__BORDER_COLOR:
				return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
				return referingToConnections != null && !referingToConnections.isEmpty();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
				return referingFromConnections != null && !referingFromConnections.isEmpty();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ANCHOR:
				return isSetSourceAnchor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ANCHOR:
				return isSetTargetAnchor();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__ROUTING:
				return isSetRouting();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__COORDINATES:
				return coordinates != null && !coordinates.isEmpty();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__POINTS:
				return POINTS_EDEFAULT == null ? points != null : !POINTS_EDEFAULT.equals(points);
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL:
				return sourceActivitySymbol != null;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL:
				return targetActivitySymbol != null;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE__TRANSITION:
				return transition != null;
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
			case CarnotPackage.TRANSITION_CONNECTION_TYPE___GET_SOURCE_NODE:
				return getSourceNode();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE___SET_SOURCE_NODE__INODESYMBOL:
				setSourceNode((INodeSymbol)arguments.get(0));
				return null;
			case CarnotPackage.TRANSITION_CONNECTION_TYPE___GET_TARGET_NODE:
				return getTargetNode();
			case CarnotPackage.TRANSITION_CONNECTION_TYPE___SET_TARGET_NODE__INODESYMBOL:
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
		result.append(", points: ");
		result.append(points);
		result.append(')');
		return result.toString();
	}

} //TransitionConnectionTypeImpl
