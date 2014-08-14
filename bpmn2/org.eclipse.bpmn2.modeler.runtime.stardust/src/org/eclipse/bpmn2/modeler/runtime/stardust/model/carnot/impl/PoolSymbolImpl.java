/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pool Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getOutLinks <em>Out Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getChildLanes <em>Child Lanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getParticipantReference <em>Participant Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#isBoundaryVisible <em>Boundary Visible</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.PoolSymbolImpl#getLanes <em>Lanes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PoolSymbolImpl extends ISymbolContainerImpl implements PoolSymbol {
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
	 * The default value of the '{@link #getXPos() <em>XPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPos()
	 * @generated
	 * @ordered
	 */
	protected static final long XPOS_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getXPos() <em>XPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPos()
	 * @generated
	 * @ordered
	 */
	protected long xPos = XPOS_EDEFAULT;

	/**
	 * This is true if the XPos attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xPosESet;

	/**
	 * The default value of the '{@link #getYPos() <em>YPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPos()
	 * @generated
	 * @ordered
	 */
	protected static final long YPOS_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getYPos() <em>YPos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPos()
	 * @generated
	 * @ordered
	 */
	protected long yPos = YPOS_EDEFAULT;

	/**
	 * This is true if the YPos attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean yPosESet;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * This is true if the Width attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean widthESet;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * This is true if the Height attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean heightESet;

	/**
	 * The default value of the '{@link #getShape() <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected static final String SHAPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected String shape = SHAPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInLinks() <em>In Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericLinkConnectionType> inLinks;

	/**
	 * The cached value of the '{@link #getOutLinks() <em>Out Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericLinkConnectionType> outLinks;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * This is true if the Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean idESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * This is true if the Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean nameESet;

	/**
	 * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final OrientationType ORIENTATION_EDEFAULT = OrientationType.VERTICAL;

	/**
	 * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected OrientationType orientation = ORIENTATION_EDEFAULT;

	/**
	 * This is true if the Orientation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean orientationESet;

	/**
	 * The default value of the '{@link #isCollapsed() <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLAPSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCollapsed() <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsed()
	 * @generated
	 * @ordered
	 */
	protected boolean collapsed = COLLAPSED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected IModelParticipant participant;

	/**
	 * The cached value of the '{@link #getChildLanes() <em>Child Lanes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildLanes()
	 * @generated
	 * @ordered
	 */
	protected EList<LaneSymbol> childLanes;

	/**
	 * The cached value of the '{@link #getParticipantReference() <em>Participant Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantReference()
	 * @generated
	 * @ordered
	 */
	protected IModelParticipant participantReference;

	/**
	 * The default value of the '{@link #isBoundaryVisible() <em>Boundary Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBoundaryVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOUNDARY_VISIBLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isBoundaryVisible() <em>Boundary Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBoundaryVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean boundaryVisible = BOUNDARY_VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProcess() <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcess()
	 * @generated
	 * @ordered
	 */
	protected ProcessDefinitionType process;

	/**
	 * The cached value of the '{@link #getLanes() <em>Lanes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanes()
	 * @generated
	 * @ordered
	 */
	protected EList<LaneSymbol> lanes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PoolSymbolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getPoolSymbol();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__BORDER_COLOR, oldBorderColor, borderColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__FILL_COLOR, oldFillColor, fillColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingToConnections() {
		if (referingToConnections == null) {
			referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
			referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__FROM);
		}
		return referingFromConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getXPos() {
		return xPos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXPos(long newXPos) {
		long oldXPos = xPos;
		xPos = newXPos;
		boolean oldXPosESet = xPosESet;
		xPosESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__XPOS, oldXPos, xPos, !oldXPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXPos() {
		long oldXPos = xPos;
		boolean oldXPosESet = xPosESet;
		xPos = XPOS_EDEFAULT;
		xPosESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXPos() {
		return xPosESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getYPos() {
		return yPos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYPos(long newYPos) {
		long oldYPos = yPos;
		yPos = newYPos;
		boolean oldYPosESet = yPosESet;
		yPosESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__YPOS, oldYPos, yPos, !oldYPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYPos() {
		long oldYPos = yPos;
		boolean oldYPosESet = yPosESet;
		yPos = YPOS_EDEFAULT;
		yPosESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYPos() {
		return yPosESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		boolean oldWidthESet = widthESet;
		widthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__WIDTH, oldWidth, width, !oldWidthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWidth() {
		int oldWidth = width;
		boolean oldWidthESet = widthESet;
		width = WIDTH_EDEFAULT;
		widthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWidth() {
		return widthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		boolean oldHeightESet = heightESet;
		heightESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__HEIGHT, oldHeight, height, !oldHeightESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHeight() {
		int oldHeight = height;
		boolean oldHeightESet = heightESet;
		height = HEIGHT_EDEFAULT;
		heightESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHeight() {
		return heightESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShape(String newShape) {
		String oldShape = shape;
		shape = newShape;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__SHAPE, oldShape, shape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericLinkConnectionType> getInLinks() {
		if (inLinks == null) {
			inLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotPackage.POOL_SYMBOL__IN_LINKS, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL);
		}
		return inLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericLinkConnectionType> getOutLinks() {
		if (outLinks == null) {
			outLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotPackage.POOL_SYMBOL__OUT_LINKS, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL);
		}
		return outLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		boolean oldIdESet = idESet;
		idESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__ID, oldId, id, !oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetId() {
		String oldId = id;
		boolean oldIdESet = idESet;
		id = ID_EDEFAULT;
		idESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__ID, oldId, ID_EDEFAULT, oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetId() {
		return idESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		boolean oldNameESet = nameESet;
		nameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__NAME, oldName, name, !oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetName() {
		String oldName = name;
		boolean oldNameESet = nameESet;
		name = NAME_EDEFAULT;
		nameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__NAME, oldName, NAME_EDEFAULT, oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetName() {
		return nameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationType getOrientation() {
		return orientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientation(OrientationType newOrientation) {
		OrientationType oldOrientation = orientation;
		orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
		boolean oldOrientationESet = orientationESet;
		orientationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOrientation() {
		OrientationType oldOrientation = orientation;
		boolean oldOrientationESet = orientationESet;
		orientation = ORIENTATION_EDEFAULT;
		orientationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.POOL_SYMBOL__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOrientation() {
		return orientationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCollapsed() {
		return collapsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollapsed(boolean newCollapsed) {
		boolean oldCollapsed = collapsed;
		collapsed = newCollapsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__COLLAPSED, oldCollapsed, collapsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IModelParticipant getParticipant() {
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipant(IModelParticipant newParticipant, NotificationChain msgs) {
		IModelParticipant oldParticipant = participant;
		participant = newParticipant;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__PARTICIPANT, oldParticipant, newParticipant);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipant(IModelParticipant newParticipant) {
		if (newParticipant != participant) {
			NotificationChain msgs = null;
			if (participant != null)
				msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
			if (newParticipant != null)
				msgs = ((InternalEObject)newParticipant).eInverseAdd(this, CarnotPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
			msgs = basicSetParticipant(newParticipant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__PARTICIPANT, newParticipant, newParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LaneSymbol> getChildLanes() {
		if (childLanes == null) {
			childLanes = new EObjectWithInverseEList<LaneSymbol>(LaneSymbol.class, this, CarnotPackage.POOL_SYMBOL__CHILD_LANES, CarnotPackage.LANE_SYMBOL__PARENT_LANE);
		}
		return childLanes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IModelParticipant getParticipantReference() {
		return participantReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantReference(IModelParticipant newParticipantReference) {
		IModelParticipant oldParticipantReference = participantReference;
		participantReference = newParticipantReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE, oldParticipantReference, participantReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramType getDiagram() {
		if (eContainerFeatureID() != CarnotPackage.POOL_SYMBOL__DIAGRAM) return null;
		return (DiagramType)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBoundaryVisible() {
		return boundaryVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoundaryVisible(boolean newBoundaryVisible) {
		boolean oldBoundaryVisible = boundaryVisible;
		boundaryVisible = newBoundaryVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__BOUNDARY_VISIBLE, oldBoundaryVisible, boundaryVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDefinitionType getProcess() {
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(ProcessDefinitionType newProcess) {
		ProcessDefinitionType oldProcess = process;
		process = newProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.POOL_SYMBOL__PROCESS, oldProcess, process));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LaneSymbol> getLanes() {
		if (lanes == null) {
			lanes = new EObjectContainmentWithInverseEList<LaneSymbol>(LaneSymbol.class, this, CarnotPackage.POOL_SYMBOL__LANES, CarnotPackage.LANE_SYMBOL__PARENT_POOL);
		}
		return lanes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInConnectionFeatures() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getOutConnectionFeatures() {
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
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInLinks()).basicAdd(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutLinks()).basicAdd(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				if (participant != null)
					msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
				return basicSetParticipant((IModelParticipant)otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildLanes()).basicAdd(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__DIAGRAM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, CarnotPackage.POOL_SYMBOL__DIAGRAM, msgs);
			case CarnotPackage.POOL_SYMBOL__LANES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLanes()).basicAdd(otherEnd, msgs);
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
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				return ((InternalEList<?>)getInLinks()).basicRemove(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				return ((InternalEList<?>)getOutLinks()).basicRemove(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				return basicSetParticipant(null, msgs);
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				return ((InternalEList<?>)getChildLanes()).basicRemove(otherEnd, msgs);
			case CarnotPackage.POOL_SYMBOL__DIAGRAM:
				return eBasicSetContainer(null, CarnotPackage.POOL_SYMBOL__DIAGRAM, msgs);
			case CarnotPackage.POOL_SYMBOL__LANES:
				return ((InternalEList<?>)getLanes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CarnotPackage.POOL_SYMBOL__DIAGRAM:
				return eInternalContainer().eInverseRemove(this, CarnotPackage.DIAGRAM_TYPE__POOL_SYMBOLS, DiagramType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CarnotPackage.POOL_SYMBOL__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.POOL_SYMBOL__BORDER_COLOR:
				return getBorderColor();
			case CarnotPackage.POOL_SYMBOL__FILL_COLOR:
				return getFillColor();
			case CarnotPackage.POOL_SYMBOL__STYLE:
				return getStyle();
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				return getReferingToConnections();
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				return getReferingFromConnections();
			case CarnotPackage.POOL_SYMBOL__XPOS:
				return getXPos();
			case CarnotPackage.POOL_SYMBOL__YPOS:
				return getYPos();
			case CarnotPackage.POOL_SYMBOL__WIDTH:
				return getWidth();
			case CarnotPackage.POOL_SYMBOL__HEIGHT:
				return getHeight();
			case CarnotPackage.POOL_SYMBOL__SHAPE:
				return getShape();
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				return getInLinks();
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				return getOutLinks();
			case CarnotPackage.POOL_SYMBOL__ID:
				return getId();
			case CarnotPackage.POOL_SYMBOL__NAME:
				return getName();
			case CarnotPackage.POOL_SYMBOL__ORIENTATION:
				return getOrientation();
			case CarnotPackage.POOL_SYMBOL__COLLAPSED:
				return isCollapsed();
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				return getParticipant();
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				return getChildLanes();
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE:
				return getParticipantReference();
			case CarnotPackage.POOL_SYMBOL__DIAGRAM:
				return getDiagram();
			case CarnotPackage.POOL_SYMBOL__BOUNDARY_VISIBLE:
				return isBoundaryVisible();
			case CarnotPackage.POOL_SYMBOL__PROCESS:
				return getProcess();
			case CarnotPackage.POOL_SYMBOL__LANES:
				return getLanes();
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
			case CarnotPackage.POOL_SYMBOL__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__BORDER_COLOR:
				setBorderColor((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__STYLE:
				setStyle((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__XPOS:
				setXPos((Long)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__YPOS:
				setYPos((Long)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__WIDTH:
				setWidth((Integer)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__SHAPE:
				setShape((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				getInLinks().clear();
				getInLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				getOutLinks().clear();
				getOutLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__ID:
				setId((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__NAME:
				setName((String)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__ORIENTATION:
				setOrientation((OrientationType)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__COLLAPSED:
				setCollapsed((Boolean)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				setParticipant((IModelParticipant)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				getChildLanes().clear();
				getChildLanes().addAll((Collection<? extends LaneSymbol>)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE:
				setParticipantReference((IModelParticipant)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__BOUNDARY_VISIBLE:
				setBoundaryVisible((Boolean)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__PROCESS:
				setProcess((ProcessDefinitionType)newValue);
				return;
			case CarnotPackage.POOL_SYMBOL__LANES:
				getLanes().clear();
				getLanes().addAll((Collection<? extends LaneSymbol>)newValue);
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
			case CarnotPackage.POOL_SYMBOL__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.POOL_SYMBOL__BORDER_COLOR:
				setBorderColor(BORDER_COLOR_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				return;
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				return;
			case CarnotPackage.POOL_SYMBOL__XPOS:
				unsetXPos();
				return;
			case CarnotPackage.POOL_SYMBOL__YPOS:
				unsetYPos();
				return;
			case CarnotPackage.POOL_SYMBOL__WIDTH:
				unsetWidth();
				return;
			case CarnotPackage.POOL_SYMBOL__HEIGHT:
				unsetHeight();
				return;
			case CarnotPackage.POOL_SYMBOL__SHAPE:
				setShape(SHAPE_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				getInLinks().clear();
				return;
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				getOutLinks().clear();
				return;
			case CarnotPackage.POOL_SYMBOL__ID:
				unsetId();
				return;
			case CarnotPackage.POOL_SYMBOL__NAME:
				unsetName();
				return;
			case CarnotPackage.POOL_SYMBOL__ORIENTATION:
				unsetOrientation();
				return;
			case CarnotPackage.POOL_SYMBOL__COLLAPSED:
				setCollapsed(COLLAPSED_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				setParticipant((IModelParticipant)null);
				return;
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				getChildLanes().clear();
				return;
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE:
				setParticipantReference((IModelParticipant)null);
				return;
			case CarnotPackage.POOL_SYMBOL__BOUNDARY_VISIBLE:
				setBoundaryVisible(BOUNDARY_VISIBLE_EDEFAULT);
				return;
			case CarnotPackage.POOL_SYMBOL__PROCESS:
				setProcess((ProcessDefinitionType)null);
				return;
			case CarnotPackage.POOL_SYMBOL__LANES:
				getLanes().clear();
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
			case CarnotPackage.POOL_SYMBOL__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.POOL_SYMBOL__BORDER_COLOR:
				return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
			case CarnotPackage.POOL_SYMBOL__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case CarnotPackage.POOL_SYMBOL__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS:
				return referingToConnections != null && !referingToConnections.isEmpty();
			case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS:
				return referingFromConnections != null && !referingFromConnections.isEmpty();
			case CarnotPackage.POOL_SYMBOL__XPOS:
				return isSetXPos();
			case CarnotPackage.POOL_SYMBOL__YPOS:
				return isSetYPos();
			case CarnotPackage.POOL_SYMBOL__WIDTH:
				return isSetWidth();
			case CarnotPackage.POOL_SYMBOL__HEIGHT:
				return isSetHeight();
			case CarnotPackage.POOL_SYMBOL__SHAPE:
				return SHAPE_EDEFAULT == null ? shape != null : !SHAPE_EDEFAULT.equals(shape);
			case CarnotPackage.POOL_SYMBOL__IN_LINKS:
				return inLinks != null && !inLinks.isEmpty();
			case CarnotPackage.POOL_SYMBOL__OUT_LINKS:
				return outLinks != null && !outLinks.isEmpty();
			case CarnotPackage.POOL_SYMBOL__ID:
				return isSetId();
			case CarnotPackage.POOL_SYMBOL__NAME:
				return isSetName();
			case CarnotPackage.POOL_SYMBOL__ORIENTATION:
				return isSetOrientation();
			case CarnotPackage.POOL_SYMBOL__COLLAPSED:
				return collapsed != COLLAPSED_EDEFAULT;
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT:
				return participant != null;
			case CarnotPackage.POOL_SYMBOL__CHILD_LANES:
				return childLanes != null && !childLanes.isEmpty();
			case CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE:
				return participantReference != null;
			case CarnotPackage.POOL_SYMBOL__DIAGRAM:
				return getDiagram() != null;
			case CarnotPackage.POOL_SYMBOL__BOUNDARY_VISIBLE:
				return boundaryVisible != BOUNDARY_VISIBLE_EDEFAULT;
			case CarnotPackage.POOL_SYMBOL__PROCESS:
				return process != null;
			case CarnotPackage.POOL_SYMBOL__LANES:
				return lanes != null && !lanes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IModelElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.POOL_SYMBOL__ELEMENT_OID: return CarnotPackage.IMODEL_ELEMENT__ELEMENT_OID;
				default: return -1;
			}
		}
		if (baseClass == IGraphicalObject.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.POOL_SYMBOL__BORDER_COLOR: return CarnotPackage.IGRAPHICAL_OBJECT__BORDER_COLOR;
				case CarnotPackage.POOL_SYMBOL__FILL_COLOR: return CarnotPackage.IGRAPHICAL_OBJECT__FILL_COLOR;
				case CarnotPackage.POOL_SYMBOL__STYLE: return CarnotPackage.IGRAPHICAL_OBJECT__STYLE;
				case CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS: return CarnotPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS;
				case CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS: return CarnotPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS;
				default: return -1;
			}
		}
		if (baseClass == INodeSymbol.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.POOL_SYMBOL__XPOS: return CarnotPackage.INODE_SYMBOL__XPOS;
				case CarnotPackage.POOL_SYMBOL__YPOS: return CarnotPackage.INODE_SYMBOL__YPOS;
				case CarnotPackage.POOL_SYMBOL__WIDTH: return CarnotPackage.INODE_SYMBOL__WIDTH;
				case CarnotPackage.POOL_SYMBOL__HEIGHT: return CarnotPackage.INODE_SYMBOL__HEIGHT;
				case CarnotPackage.POOL_SYMBOL__SHAPE: return CarnotPackage.INODE_SYMBOL__SHAPE;
				case CarnotPackage.POOL_SYMBOL__IN_LINKS: return CarnotPackage.INODE_SYMBOL__IN_LINKS;
				case CarnotPackage.POOL_SYMBOL__OUT_LINKS: return CarnotPackage.INODE_SYMBOL__OUT_LINKS;
				default: return -1;
			}
		}
		if (baseClass == IIdentifiableElement.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.POOL_SYMBOL__ID: return CarnotPackage.IIDENTIFIABLE_ELEMENT__ID;
				case CarnotPackage.POOL_SYMBOL__NAME: return CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ISwimlaneSymbol.class) {
			switch (derivedFeatureID) {
				case CarnotPackage.POOL_SYMBOL__ORIENTATION: return CarnotPackage.ISWIMLANE_SYMBOL__ORIENTATION;
				case CarnotPackage.POOL_SYMBOL__COLLAPSED: return CarnotPackage.ISWIMLANE_SYMBOL__COLLAPSED;
				case CarnotPackage.POOL_SYMBOL__PARTICIPANT: return CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT;
				case CarnotPackage.POOL_SYMBOL__CHILD_LANES: return CarnotPackage.ISWIMLANE_SYMBOL__CHILD_LANES;
				case CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE: return CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT_REFERENCE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IModelElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IMODEL_ELEMENT__ELEMENT_OID: return CarnotPackage.POOL_SYMBOL__ELEMENT_OID;
				default: return -1;
			}
		}
		if (baseClass == IGraphicalObject.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IGRAPHICAL_OBJECT__BORDER_COLOR: return CarnotPackage.POOL_SYMBOL__BORDER_COLOR;
				case CarnotPackage.IGRAPHICAL_OBJECT__FILL_COLOR: return CarnotPackage.POOL_SYMBOL__FILL_COLOR;
				case CarnotPackage.IGRAPHICAL_OBJECT__STYLE: return CarnotPackage.POOL_SYMBOL__STYLE;
				case CarnotPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS: return CarnotPackage.POOL_SYMBOL__REFERING_TO_CONNECTIONS;
				case CarnotPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS: return CarnotPackage.POOL_SYMBOL__REFERING_FROM_CONNECTIONS;
				default: return -1;
			}
		}
		if (baseClass == INodeSymbol.class) {
			switch (baseFeatureID) {
				case CarnotPackage.INODE_SYMBOL__XPOS: return CarnotPackage.POOL_SYMBOL__XPOS;
				case CarnotPackage.INODE_SYMBOL__YPOS: return CarnotPackage.POOL_SYMBOL__YPOS;
				case CarnotPackage.INODE_SYMBOL__WIDTH: return CarnotPackage.POOL_SYMBOL__WIDTH;
				case CarnotPackage.INODE_SYMBOL__HEIGHT: return CarnotPackage.POOL_SYMBOL__HEIGHT;
				case CarnotPackage.INODE_SYMBOL__SHAPE: return CarnotPackage.POOL_SYMBOL__SHAPE;
				case CarnotPackage.INODE_SYMBOL__IN_LINKS: return CarnotPackage.POOL_SYMBOL__IN_LINKS;
				case CarnotPackage.INODE_SYMBOL__OUT_LINKS: return CarnotPackage.POOL_SYMBOL__OUT_LINKS;
				default: return -1;
			}
		}
		if (baseClass == IIdentifiableElement.class) {
			switch (baseFeatureID) {
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotPackage.POOL_SYMBOL__ID;
				case CarnotPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotPackage.POOL_SYMBOL__NAME;
				default: return -1;
			}
		}
		if (baseClass == ISwimlaneSymbol.class) {
			switch (baseFeatureID) {
				case CarnotPackage.ISWIMLANE_SYMBOL__ORIENTATION: return CarnotPackage.POOL_SYMBOL__ORIENTATION;
				case CarnotPackage.ISWIMLANE_SYMBOL__COLLAPSED: return CarnotPackage.POOL_SYMBOL__COLLAPSED;
				case CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT: return CarnotPackage.POOL_SYMBOL__PARTICIPANT;
				case CarnotPackage.ISWIMLANE_SYMBOL__CHILD_LANES: return CarnotPackage.POOL_SYMBOL__CHILD_LANES;
				case CarnotPackage.ISWIMLANE_SYMBOL__PARTICIPANT_REFERENCE: return CarnotPackage.POOL_SYMBOL__PARTICIPANT_REFERENCE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == IModelElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == IGraphicalObject.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == INodeSymbol.class) {
			switch (baseOperationID) {
				case CarnotPackage.INODE_SYMBOL___GET_IN_CONNECTION_FEATURES: return CarnotPackage.POOL_SYMBOL___GET_IN_CONNECTION_FEATURES;
				case CarnotPackage.INODE_SYMBOL___GET_OUT_CONNECTION_FEATURES: return CarnotPackage.POOL_SYMBOL___GET_OUT_CONNECTION_FEATURES;
				default: return -1;
			}
		}
		if (baseClass == IIdentifiableElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == ISwimlaneSymbol.class) {
			switch (baseOperationID) {
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
			case CarnotPackage.POOL_SYMBOL___GET_IN_CONNECTION_FEATURES:
				return getInConnectionFeatures();
			case CarnotPackage.POOL_SYMBOL___GET_OUT_CONNECTION_FEATURES:
				return getOutConnectionFeatures();
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
		result.append(", xPos: ");
		if (xPosESet) result.append(xPos); else result.append("<unset>");
		result.append(", yPos: ");
		if (yPosESet) result.append(yPos); else result.append("<unset>");
		result.append(", width: ");
		if (widthESet) result.append(width); else result.append("<unset>");
		result.append(", height: ");
		if (heightESet) result.append(height); else result.append("<unset>");
		result.append(", shape: ");
		result.append(shape);
		result.append(", id: ");
		if (idESet) result.append(id); else result.append("<unset>");
		result.append(", name: ");
		if (nameESet) result.append(name); else result.append("<unset>");
		result.append(", orientation: ");
		if (orientationESet) result.append(orientation); else result.append("<unset>");
		result.append(", collapsed: ");
		result.append(collapsed);
		result.append(", boundaryVisible: ");
		result.append(boundaryVisible);
		result.append(')');
		return result.toString();
	}

} //PoolSymbolImpl
