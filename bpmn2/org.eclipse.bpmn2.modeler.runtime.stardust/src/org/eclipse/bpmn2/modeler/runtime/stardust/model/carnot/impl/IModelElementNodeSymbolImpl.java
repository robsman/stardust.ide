/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IIdentifiableModelElement;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelElementNodeSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IModel Element Node Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.IModelElementNodeSymbolImpl#getOutLinks <em>Out Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IModelElementNodeSymbolImpl extends MinimalEObjectImpl.Container implements IModelElementNodeSymbol {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IModelElementNodeSymbolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getIModelElementNodeSymbol();
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR, oldBorderColor, borderColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR, oldFillColor, fillColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getReferingToConnections() {
		if (referingToConnections == null) {
			referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
			referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS, CarnotPackage.REFERS_TO_CONNECTION_TYPE__FROM);
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS, oldXPos, xPos, !oldXPosESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS, oldYPos, yPos, !oldYPosESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH, oldWidth, width, !oldWidthESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT, oldHeight, height, !oldHeightESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__SHAPE, oldShape, shape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericLinkConnectionType> getInLinks() {
		if (inLinks == null) {
			inLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL);
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
			outLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS, CarnotPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL);
		}
		return outLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IIdentifiableModelElement getModelElement() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelElement(IIdentifiableModelElement element) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInLinks()).basicAdd(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutLinks()).basicAdd(otherEnd, msgs);
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				return ((InternalEList<?>)getInLinks()).basicRemove(otherEnd, msgs);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				return ((InternalEList<?>)getOutLinks()).basicRemove(otherEnd, msgs);
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID:
				return getElementOid();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR:
				return getBorderColor();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR:
				return getFillColor();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__STYLE:
				return getStyle();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				return getReferingToConnections();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				return getReferingFromConnections();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS:
				return getXPos();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS:
				return getYPos();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH:
				return getWidth();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT:
				return getHeight();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__SHAPE:
				return getShape();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				return getInLinks();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				return getOutLinks();
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID:
				setElementOid((Long)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR:
				setBorderColor((String)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__STYLE:
				setStyle((String)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS:
				setXPos((Long)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS:
				setYPos((Long)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH:
				setWidth((Integer)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__SHAPE:
				setShape((String)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				getInLinks().clear();
				getInLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				getOutLinks().clear();
				getOutLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID:
				unsetElementOid();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR:
				setBorderColor(BORDER_COLOR_EDEFAULT);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				getReferingToConnections().clear();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				getReferingFromConnections().clear();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS:
				unsetXPos();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS:
				unsetYPos();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH:
				unsetWidth();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT:
				unsetHeight();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__SHAPE:
				setShape(SHAPE_EDEFAULT);
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				getInLinks().clear();
				return;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				getOutLinks().clear();
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__ELEMENT_OID:
				return isSetElementOid();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__BORDER_COLOR:
				return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_TO_CONNECTIONS:
				return referingToConnections != null && !referingToConnections.isEmpty();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__REFERING_FROM_CONNECTIONS:
				return referingFromConnections != null && !referingFromConnections.isEmpty();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__XPOS:
				return isSetXPos();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__YPOS:
				return isSetYPos();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__WIDTH:
				return isSetWidth();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__HEIGHT:
				return isSetHeight();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__SHAPE:
				return SHAPE_EDEFAULT == null ? shape != null : !SHAPE_EDEFAULT.equals(shape);
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__IN_LINKS:
				return inLinks != null && !inLinks.isEmpty();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL__OUT_LINKS:
				return outLinks != null && !outLinks.isEmpty();
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
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL___GET_MODEL_ELEMENT:
				return getModelElement();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL___SET_MODEL_ELEMENT__IIDENTIFIABLEMODELELEMENT:
				setModelElement((IIdentifiableModelElement)arguments.get(0));
				return null;
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL___GET_IN_CONNECTION_FEATURES:
				return getInConnectionFeatures();
			case CarnotPackage.IMODEL_ELEMENT_NODE_SYMBOL___GET_OUT_CONNECTION_FEATURES:
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
		result.append(')');
		return result.toString();
	}

} //IModelElementNodeSymbolImpl
