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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IFlow Object Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getOutLinks <em>Out Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getInTransitions <em>In Transitions</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.IFlowObjectSymbolImpl#getOutTransitions <em>Out Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IFlowObjectSymbolImpl extends EObjectImpl implements IFlowObjectSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * The cached value of the '{@link #getInTransitions() <em>In Transitions</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInTransitions()
    * @generated
    * @ordered
    */
   protected EList<TransitionConnectionType> inTransitions;

   /**
    * The cached value of the '{@link #getOutTransitions() <em>Out Transitions</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutTransitions()
    * @generated
    * @ordered
    */
   protected EList<TransitionConnectionType> outTransitions;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected IFlowObjectSymbolImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.IFLOW_OBJECT_SYMBOL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public long getElementOid()
   {
      return elementOid;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setElementOid(long newElementOid)
   {
      long oldElementOid = elementOid;
      elementOid = newElementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOidESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetElementOid()
   {
      long oldElementOid = elementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOid = ELEMENT_OID_EDEFAULT;
      elementOidESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetElementOid()
   {
      return elementOidESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getBorderColor()
   {
      return borderColor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setBorderColor(String newBorderColor)
   {
      String oldBorderColor = borderColor;
      borderColor = newBorderColor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__BORDER_COLOR, oldBorderColor, borderColor));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getFillColor()
   {
      return fillColor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFillColor(String newFillColor)
   {
      String oldFillColor = fillColor;
      fillColor = newFillColor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__FILL_COLOR, oldFillColor, fillColor));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getStyle()
   {
      return style;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStyle(String newStyle)
   {
      String oldStyle = style;
      style = newStyle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__STYLE, oldStyle, style));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public long getXPos()
   {
      return xPos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setXPos(long newXPos)
   {
      long oldXPos = xPos;
      xPos = newXPos;
      boolean oldXPosESet = xPosESet;
      xPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS, oldXPos, xPos, !oldXPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetXPos()
   {
      long oldXPos = xPos;
      boolean oldXPosESet = xPosESet;
      xPos = XPOS_EDEFAULT;
      xPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetXPos()
   {
      return xPosESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public long getYPos()
   {
      return yPos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setYPos(long newYPos)
   {
      long oldYPos = yPos;
      yPos = newYPos;
      boolean oldYPosESet = yPosESet;
      yPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS, oldYPos, yPos, !oldYPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetYPos()
   {
      long oldYPos = yPos;
      boolean oldYPosESet = yPosESet;
      yPos = YPOS_EDEFAULT;
      yPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetYPos()
   {
      return yPosESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getWidth()
   {
      return width;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setWidth(int newWidth)
   {
      int oldWidth = width;
      width = newWidth;
      boolean oldWidthESet = widthESet;
      widthESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH, oldWidth, width, !oldWidthESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetWidth()
   {
      int oldWidth = width;
      boolean oldWidthESet = widthESet;
      width = WIDTH_EDEFAULT;
      widthESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetWidth()
   {
      return widthESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getHeight()
   {
      return height;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setHeight(int newHeight)
   {
      int oldHeight = height;
      height = newHeight;
      boolean oldHeightESet = heightESet;
      heightESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT, oldHeight, height, !oldHeightESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetHeight()
   {
      int oldHeight = height;
      boolean oldHeightESet = heightESet;
      height = HEIGHT_EDEFAULT;
      heightESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetHeight()
   {
      return heightESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getShape()
   {
      return shape;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setShape(String newShape)
   {
      String oldShape = shape;
      shape = newShape;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__SHAPE, oldShape, shape));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<RefersToConnectionType> getReferingToConnections()
   {
      if (referingToConnections == null)
      {
         referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__TO);
      }
      return referingToConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<RefersToConnectionType> getReferingFromConnections()
   {
      if (referingFromConnections == null)
      {
         referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__FROM);
      }
      return referingFromConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getInLinks()
   {
      if (inLinks == null)
      {
         inLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL);
      }
      return inLinks;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getOutLinks()
   {
      if (outLinks == null)
      {
         outLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL);
      }
      return outLinks;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionConnectionType> getInTransitions()
   {
      if (inTransitions == null)
      {
         inTransitions = new EObjectWithInverseEList<TransitionConnectionType>(TransitionConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS, CarnotWorkflowModelPackage.TRANSITION_CONNECTION_TYPE__TARGET_ACTIVITY_SYMBOL);
      }
      return inTransitions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionConnectionType> getOutTransitions()
   {
      if (outTransitions == null)
      {
         outTransitions = new EObjectWithInverseEList<TransitionConnectionType>(TransitionConnectionType.class, this, CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS, CarnotWorkflowModelPackage.TRANSITION_CONNECTION_TYPE__SOURCE_ACTIVITY_SYMBOL);
      }
      return outTransitions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public List getInConnectionFeatures()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public List getOutConnectionFeatures()
   {
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
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInLinks()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutLinks()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInTransitions()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutTransitions()).basicAdd(otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            return ((InternalEList<?>)getInLinks()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            return ((InternalEList<?>)getOutLinks()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            return ((InternalEList<?>)getInTransitions()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            return ((InternalEList<?>)getOutTransitions()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__BORDER_COLOR:
            return getBorderColor();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__FILL_COLOR:
            return getFillColor();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__STYLE:
            return getStyle();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            return getReferingToConnections();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            return getReferingFromConnections();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS:
            return getXPos();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS:
            return getYPos();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH:
            return getWidth();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT:
            return getHeight();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__SHAPE:
            return getShape();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            return getInLinks();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            return getOutLinks();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            return getInTransitions();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            return getOutTransitions();
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
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__BORDER_COLOR:
            setBorderColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__FILL_COLOR:
            setFillColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__STYLE:
            setStyle((String)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS:
            setXPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS:
            setYPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH:
            setWidth((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT:
            setHeight((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__SHAPE:
            setShape((String)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            getInLinks().clear();
            getInLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            getOutLinks().clear();
            getOutLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            getInTransitions().clear();
            getInTransitions().addAll((Collection<? extends TransitionConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            getOutTransitions().clear();
            getOutTransitions().addAll((Collection<? extends TransitionConnectionType>)newValue);
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
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__BORDER_COLOR:
            setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS:
            unsetXPos();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS:
            unsetYPos();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH:
            unsetWidth();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT:
            unsetHeight();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__SHAPE:
            setShape(SHAPE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            getInLinks().clear();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            getOutLinks().clear();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            getInTransitions().clear();
            return;
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            getOutTransitions().clear();
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
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__STYLE:
            return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_TO_CONNECTIONS:
            return referingToConnections != null && !referingToConnections.isEmpty();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__REFERING_FROM_CONNECTIONS:
            return referingFromConnections != null && !referingFromConnections.isEmpty();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__XPOS:
            return isSetXPos();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__YPOS:
            return isSetYPos();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__WIDTH:
            return isSetWidth();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__HEIGHT:
            return isSetHeight();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__SHAPE:
            return SHAPE_EDEFAULT == null ? shape != null : !SHAPE_EDEFAULT.equals(shape);
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_LINKS:
            return inLinks != null && !inLinks.isEmpty();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_LINKS:
            return outLinks != null && !outLinks.isEmpty();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__IN_TRANSITIONS:
            return inTransitions != null && !inTransitions.isEmpty();
         case CarnotWorkflowModelPackage.IFLOW_OBJECT_SYMBOL__OUT_TRANSITIONS:
            return outTransitions != null && !outTransitions.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (elementOid: "); //$NON-NLS-1$
      if (elementOidESet) result.append(elementOid); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", borderColor: "); //$NON-NLS-1$
      result.append(borderColor);
      result.append(", fillColor: "); //$NON-NLS-1$
      result.append(fillColor);
      result.append(", style: "); //$NON-NLS-1$
      result.append(style);
      result.append(", xPos: "); //$NON-NLS-1$
      if (xPosESet) result.append(xPos); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", yPos: "); //$NON-NLS-1$
      if (yPosESet) result.append(yPos); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", width: "); //$NON-NLS-1$
      if (widthESet) result.append(width); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", height: "); //$NON-NLS-1$
      if (heightESet) result.append(height); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", shape: "); //$NON-NLS-1$
      result.append(shape);
      result.append(')');
      return result.toString();
   }

} //IFlowObjectSymbolImpl
