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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GroupSymbolTypeImpl#getOutLinks <em>Out Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupSymbolTypeImpl extends ISymbolContainerImpl implements GroupSymbolType
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected static final long ELEMENT_OID_EDEFAULT = 0L;

   /**
    * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected long elementOid = ELEMENT_OID_EDEFAULT;

   /**
    * This is true if the Element Oid attribute has been set.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean elementOidESet;

   /**
    * The default value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getBorderColor()
    * @generated
    * @ordered
    */
   protected static final String BORDER_COLOR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getBorderColor()
    * @generated
    * @ordered
    */
   protected String borderColor = BORDER_COLOR_EDEFAULT;

   /**
    * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getFillColor()
    * @generated
    * @ordered
    */
   protected static final String FILL_COLOR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getFillColor()
    * @generated
    * @ordered
    */
   protected String fillColor = FILL_COLOR_EDEFAULT;

   /**
    * The default value of the '{@link #getStyle() <em>Style</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getStyle()
    * @generated
    * @ordered
    */
   protected static final String STYLE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
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
    * The default value of the '{@link #getXPos() <em>XPos</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getXPos()
    * @generated
    * @ordered
    */
   protected static final long XPOS_EDEFAULT = 0L;

   /**
    * The cached value of the '{@link #getXPos() <em>XPos</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getXPos()
    * @generated
    * @ordered
    */
   protected long xPos = XPOS_EDEFAULT;

   /**
    * This is true if the XPos attribute has been set.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean xPosESet;

   /**
    * The default value of the '{@link #getYPos() <em>YPos</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getYPos()
    * @generated
    * @ordered
    */
   protected static final long YPOS_EDEFAULT = 0L;

   /**
    * The cached value of the '{@link #getYPos() <em>YPos</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getYPos()
    * @generated
    * @ordered
    */
   protected long yPos = YPOS_EDEFAULT;

   /**
    * This is true if the YPos attribute has been set.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean yPosESet;

   /**
    * The default value of the '{@link #getWidth() <em>Width</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getWidth()
    * @generated
    * @ordered
    */
   protected static final int WIDTH_EDEFAULT = -1;

   /**
    * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getWidth()
    * @generated
    * @ordered
    */
   protected int width = WIDTH_EDEFAULT;

   /**
    * This is true if the Width attribute has been set.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean widthESet;

   /**
    * The default value of the '{@link #getHeight() <em>Height</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getHeight()
    * @generated
    * @ordered
    */
   protected static final int HEIGHT_EDEFAULT = -1;

   /**
    * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getHeight()
    * @generated
    * @ordered
    */
   protected int height = HEIGHT_EDEFAULT;

   /**
    * This is true if the Height attribute has been set.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean heightESet;

   /**
    * The default value of the '{@link #getShape() <em>Shape</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getShape()
    * @generated
    * @ordered
    */
   protected static final String SHAPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getShape()
    * @generated
    * @ordered
    */
   protected String shape = SHAPE_EDEFAULT;

   /**
    * The cached value of the '{@link #getInLinks() <em>In Links</em>}' reference list.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getInLinks()
    * @generated
    * @ordered
    */
   protected EList<GenericLinkConnectionType> inLinks;

   /**
    * The cached value of the '{@link #getOutLinks() <em>Out Links</em>}' reference list.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getOutLinks()
    * @generated
    * @ordered
    */
   protected EList<GenericLinkConnectionType> outLinks;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected GroupSymbolTypeImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.GROUP_SYMBOL_TYPE;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public long getElementOid()
   {
      return elementOid;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setElementOid(long newElementOid)
   {
      long oldElementOid = elementOid;
      elementOid = newElementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOidESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void unsetElementOid()
   {
      long oldElementOid = elementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOid = ELEMENT_OID_EDEFAULT;
      elementOidESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetElementOid()
   {
      return elementOidESet;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getBorderColor()
   {
      return borderColor;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setBorderColor(String newBorderColor)
   {
      String oldBorderColor = borderColor;
      borderColor = newBorderColor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getFillColor()
   {
      return fillColor;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setFillColor(String newFillColor)
   {
      String oldFillColor = fillColor;
      fillColor = newFillColor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR, oldFillColor, fillColor));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getStyle()
   {
      return style;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setStyle(String newStyle)
   {
      String oldStyle = style;
      style = newStyle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE, oldStyle, style));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public long getXPos()
   {
      return xPos;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setXPos(long newXPos)
   {
      long oldXPos = xPos;
      xPos = newXPos;
      boolean oldXPosESet = xPosESet;
      xPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS, oldXPos, xPos, !oldXPosESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void unsetXPos()
   {
      long oldXPos = xPos;
      boolean oldXPosESet = xPosESet;
      xPos = XPOS_EDEFAULT;
      xPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetXPos()
   {
      return xPosESet;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public long getYPos()
   {
      return yPos;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setYPos(long newYPos)
   {
      long oldYPos = yPos;
      yPos = newYPos;
      boolean oldYPosESet = yPosESet;
      yPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS, oldYPos, yPos, !oldYPosESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void unsetYPos()
   {
      long oldYPos = yPos;
      boolean oldYPosESet = yPosESet;
      yPos = YPOS_EDEFAULT;
      yPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetYPos()
   {
      return yPosESet;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public int getWidth()
   {
      return width;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setWidth(int newWidth)
   {
      int oldWidth = width;
      width = newWidth;
      boolean oldWidthESet = widthESet;
      widthESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH, oldWidth, width, !oldWidthESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void unsetWidth()
   {
      int oldWidth = width;
      boolean oldWidthESet = widthESet;
      width = WIDTH_EDEFAULT;
      widthESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetWidth()
   {
      return widthESet;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public int getHeight()
   {
      return height;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setHeight(int newHeight)
   {
      int oldHeight = height;
      height = newHeight;
      boolean oldHeightESet = heightESet;
      heightESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT, oldHeight, height, !oldHeightESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void unsetHeight()
   {
      int oldHeight = height;
      boolean oldHeightESet = heightESet;
      height = HEIGHT_EDEFAULT;
      heightESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetHeight()
   {
      return heightESet;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public String getShape()
   {
      return shape;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setShape(String newShape)
   {
      String oldShape = shape;
      shape = newShape;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE, oldShape, shape));
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
         referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
         referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__FROM);
      }
      return referingFromConnections;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getInLinks()
   {
      if (inLinks == null)
      {
         inLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL);
      }
      return inLinks;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getOutLinks()
   {
      if (outLinks == null)
      {
         outLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL);
      }
      return outLinks;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections()
      });
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Collections.EMPTY_LIST;
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
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInLinks()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
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
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            return ((InternalEList<?>)getInLinks()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
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
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR:
            return getBorderColor();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR:
            return getFillColor();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE:
            return getStyle();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            return getReferingToConnections();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            return getReferingFromConnections();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS:
            return getXPos();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS:
            return getYPos();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH:
            return getWidth();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT:
            return getHeight();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE:
            return getShape();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            return getInLinks();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
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
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR:
            setBorderColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR:
            setFillColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE:
            setStyle((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS:
            setXPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS:
            setYPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH:
            setWidth((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT:
            setHeight((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE:
            setShape((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            getInLinks().clear();
            getInLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
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
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR:
            setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS:
            unsetXPos();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS:
            unsetYPos();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH:
            unsetWidth();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT:
            unsetHeight();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE:
            setShape(SHAPE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            getInLinks().clear();
            return;
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
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
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE:
            return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS:
            return referingToConnections != null && !referingToConnections.isEmpty();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS:
            return referingFromConnections != null && !referingFromConnections.isEmpty();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS:
            return isSetXPos();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS:
            return isSetYPos();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH:
            return isSetWidth();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT:
            return isSetHeight();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE:
            return SHAPE_EDEFAULT == null ? shape != null : !SHAPE_EDEFAULT.equals(shape);
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS:
            return inLinks != null && !inLinks.isEmpty();
         case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS:
            return outLinks != null && !outLinks.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == IModelElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID: return CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IGraphicalObject.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__BORDER_COLOR;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__FILL_COLOR;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__STYLE;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS;
            default: return -1;
         }
      }
      if (baseClass == INodeSymbol.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS: return CarnotWorkflowModelPackage.INODE_SYMBOL__XPOS;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS: return CarnotWorkflowModelPackage.INODE_SYMBOL__YPOS;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH: return CarnotWorkflowModelPackage.INODE_SYMBOL__WIDTH;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT: return CarnotWorkflowModelPackage.INODE_SYMBOL__HEIGHT;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE: return CarnotWorkflowModelPackage.INODE_SYMBOL__SHAPE;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS: return CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS;
            case CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS: return CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS;
            default: return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == IModelElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IGraphicalObject.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__BORDER_COLOR: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__BORDER_COLOR;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__FILL_COLOR: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__FILL_COLOR;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__STYLE: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__STYLE;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_TO_CONNECTIONS;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__REFERING_FROM_CONNECTIONS;
            default: return -1;
         }
      }
      if (baseClass == INodeSymbol.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.INODE_SYMBOL__XPOS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__XPOS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__YPOS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__YPOS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__WIDTH: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__WIDTH;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__HEIGHT: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__HEIGHT;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__SHAPE: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__SHAPE;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__IN_LINKS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS: return CarnotWorkflowModelPackage.GROUP_SYMBOL_TYPE__OUT_LINKS;
            default: return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
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

} // GroupSymbolTypeImpl
