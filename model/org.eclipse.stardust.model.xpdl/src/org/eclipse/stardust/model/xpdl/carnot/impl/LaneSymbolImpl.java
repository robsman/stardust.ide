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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lane Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getOutLinks <em>Out Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getChildLanes <em>Child Lanes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getParentPool <em>Parent Pool</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.LaneSymbolImpl#getParentLane <em>Parent Lane</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LaneSymbolImpl extends ISymbolContainerImpl implements LaneSymbol
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
   protected static final OrientationType ORIENTATION_EDEFAULT = OrientationType.VERTICAL_LITERAL;

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
    * The cached value of the '{@link #getParentLane() <em>Parent Lane</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParentLane()
    * @generated
    * @ordered
    */
   protected ISwimlaneSymbol parentLane;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LaneSymbolImpl()
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
      return CarnotWorkflowModelPackage.Literals.LANE_SYMBOL;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR, oldBorderColor, borderColor));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR, oldFillColor, fillColor));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE, oldStyle, style));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS, oldXPos, xPos, !oldXPosESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS, oldYPos, yPos, !oldYPosESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH, oldWidth, width, !oldWidthESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT, oldHeight, height, !oldHeightESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE, oldShape, shape));
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
         referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
         referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__FROM);
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
         inLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL);
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
         outLinks = new EObjectWithInverseEList<GenericLinkConnectionType>(GenericLinkConnectionType.class, this, CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL);
      }
      return outLinks;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getId()
   {
      return id;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setId(String newId)
   {
      String oldId = id;
      id = newId;
      boolean oldIdESet = idESet;
      idESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__ID, oldId, id, !oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetId()
   {
      String oldId = id;
      boolean oldIdESet = idESet;
      id = ID_EDEFAULT;
      idESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__ID, oldId, ID_EDEFAULT, oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetId()
   {
      return idESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      boolean oldNameESet = nameESet;
      nameESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__NAME, oldName, name, !oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetName()
   {
      String oldName = name;
      boolean oldNameESet = nameESet;
      name = NAME_EDEFAULT;
      nameESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__NAME, oldName, NAME_EDEFAULT, oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetName()
   {
      return nameESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public OrientationType getOrientation()
   {
      return orientation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setOrientation(OrientationType newOrientation)
   {
      OrientationType oldOrientation = orientation;
      orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
      boolean oldOrientationESet = orientationESet;
      orientationESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetOrientation()
   {
      OrientationType oldOrientation = orientation;
      boolean oldOrientationESet = orientationESet;
      orientation = ORIENTATION_EDEFAULT;
      orientationESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetOrientation()
   {
      return orientationESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isCollapsed()
   {
      return collapsed;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setCollapsed(boolean newCollapsed)
   {
      boolean oldCollapsed = collapsed;
      collapsed = newCollapsed;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED, oldCollapsed, collapsed));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public IModelParticipant getParticipant()
   {
      return participant;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetParticipant(IModelParticipant newParticipant, NotificationChain msgs)
   {
      IModelParticipant oldParticipant = participant;
      participant = newParticipant;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT, oldParticipant, newParticipant);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParticipant(IModelParticipant newParticipant)
   {
      if (newParticipant != participant)
      {
         NotificationChain msgs = null;
         if (participant != null)
            msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
         if (newParticipant != null)
            msgs = ((InternalEObject)newParticipant).eInverseAdd(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
         msgs = basicSetParticipant(newParticipant, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT, newParticipant, newParticipant));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PoolSymbol getParentPool()
   {
      if (eContainerFeatureID() != CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL) return null;
      return (PoolSymbol)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetParentPool(PoolSymbol newParentPool, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newParentPool, CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParentPool(PoolSymbol newParentPool)
   {
      if (newParentPool != eInternalContainer() || (eContainerFeatureID() != CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL && newParentPool != null))
      {
         if (EcoreUtil.isAncestor(this, newParentPool))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentPool != null)
            msgs = ((InternalEObject)newParentPool).eInverseAdd(this, CarnotWorkflowModelPackage.POOL_SYMBOL__LANES, PoolSymbol.class, msgs);
         msgs = basicSetParentPool(newParentPool, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL, newParentPool, newParentPool));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ISwimlaneSymbol getParentLane()
   {
      return parentLane;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetParentLane(ISwimlaneSymbol newParentLane, NotificationChain msgs)
   {
      ISwimlaneSymbol oldParentLane = parentLane;
      parentLane = newParentLane;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE, oldParentLane, newParentLane);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParentLane(ISwimlaneSymbol newParentLane)
   {
      if (newParentLane != parentLane)
      {
         NotificationChain msgs = null;
         if (parentLane != null)
            msgs = ((InternalEObject)parentLane).eInverseRemove(this, CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES, ISwimlaneSymbol.class, msgs);
         if (newParentLane != null)
            msgs = ((InternalEObject)newParentLane).eInverseAdd(this, CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES, ISwimlaneSymbol.class, msgs);
         msgs = basicSetParentLane(newParentLane, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE, newParentLane, newParentLane));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<LaneSymbol> getChildLanes()
   {
      if (childLanes == null)
      {
         childLanes = new EObjectWithInverseEList<LaneSymbol>(LaneSymbol.class, this, CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES, CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE);
      }
      return childLanes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EReference[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections()
      });
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInLinks()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutLinks()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            if (participant != null)
               msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PERFORMED_SWIMLANES, IModelParticipant.class, msgs);
            return basicSetParticipant((IModelParticipant)otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildLanes()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParentPool((PoolSymbol)otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            if (parentLane != null)
               msgs = ((InternalEObject)parentLane).eInverseRemove(this, CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES, ISwimlaneSymbol.class, msgs);
            return basicSetParentLane((ISwimlaneSymbol)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            return ((InternalEList<?>)getInLinks()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            return ((InternalEList<?>)getOutLinks()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            return basicSetParticipant(null, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            return ((InternalEList<?>)getChildLanes()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            return basicSetParentPool(null, msgs);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            return basicSetParentLane(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            return eInternalContainer().eInverseRemove(this, CarnotWorkflowModelPackage.POOL_SYMBOL__LANES, PoolSymbol.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR:
            return getBorderColor();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR:
            return getFillColor();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE:
            return getStyle();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            return getReferingToConnections();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            return getReferingFromConnections();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS:
            return getXPos();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS:
            return getYPos();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH:
            return getWidth();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT:
            return getHeight();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE:
            return getShape();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            return getInLinks();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            return getOutLinks();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ID:
            return getId();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__NAME:
            return getName();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION:
            return getOrientation();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED:
            return isCollapsed();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            return getParticipant();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            return getChildLanes();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            return getParentPool();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            return getParentLane();
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR:
            setBorderColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR:
            setFillColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE:
            setStyle((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS:
            setXPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS:
            setYPos((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH:
            setWidth((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT:
            setHeight((Integer)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE:
            setShape((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            getInLinks().clear();
            getInLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            getOutLinks().clear();
            getOutLinks().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION:
            setOrientation((OrientationType)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED:
            setCollapsed((Boolean)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            setParticipant((IModelParticipant)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            getChildLanes().clear();
            getChildLanes().addAll((Collection<? extends LaneSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            setParentPool((PoolSymbol)newValue);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            setParentLane((ISwimlaneSymbol)newValue);
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR:
            setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS:
            unsetXPos();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS:
            unsetYPos();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH:
            unsetWidth();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT:
            unsetHeight();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE:
            setShape(SHAPE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            getInLinks().clear();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            getOutLinks().clear();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION:
            unsetOrientation();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED:
            setCollapsed(COLLAPSED_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            setParticipant((IModelParticipant)null);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            getChildLanes().clear();
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            setParentPool((PoolSymbol)null);
            return;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            setParentLane((ISwimlaneSymbol)null);
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
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE:
            return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS:
            return referingToConnections != null && !referingToConnections.isEmpty();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS:
            return referingFromConnections != null && !referingFromConnections.isEmpty();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS:
            return isSetXPos();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS:
            return isSetYPos();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH:
            return isSetWidth();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT:
            return isSetHeight();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE:
            return SHAPE_EDEFAULT == null ? shape != null : !SHAPE_EDEFAULT.equals(shape);
         case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS:
            return inLinks != null && !inLinks.isEmpty();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS:
            return outLinks != null && !outLinks.isEmpty();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION:
            return isSetOrientation();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED:
            return collapsed != COLLAPSED_EDEFAULT;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT:
            return participant != null;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES:
            return childLanes != null && !childLanes.isEmpty();
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_POOL:
            return getParentPool() != null;
         case CarnotWorkflowModelPackage.LANE_SYMBOL__PARENT_LANE:
            return parentLane != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == IModelElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID: return CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IGraphicalObject.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__BORDER_COLOR;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__FILL_COLOR;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__STYLE;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS: return CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS;
            default: return -1;
         }
      }
      if (baseClass == INodeSymbol.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS: return CarnotWorkflowModelPackage.INODE_SYMBOL__XPOS;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS: return CarnotWorkflowModelPackage.INODE_SYMBOL__YPOS;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH: return CarnotWorkflowModelPackage.INODE_SYMBOL__WIDTH;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT: return CarnotWorkflowModelPackage.INODE_SYMBOL__HEIGHT;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE: return CarnotWorkflowModelPackage.INODE_SYMBOL__SHAPE;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS: return CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS: return CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS;
            default: return -1;
         }
      }
      if (baseClass == IIdentifiableElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LANE_SYMBOL__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
            default: return -1;
         }
      }
      if (baseClass == ISwimlaneSymbol.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION: return CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__ORIENTATION;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED: return CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__COLLAPSED;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT: return CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__PARTICIPANT;
            case CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES: return CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES;
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
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == IModelElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID: return CarnotWorkflowModelPackage.LANE_SYMBOL__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IGraphicalObject.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__BORDER_COLOR: return CarnotWorkflowModelPackage.LANE_SYMBOL__BORDER_COLOR;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__FILL_COLOR: return CarnotWorkflowModelPackage.LANE_SYMBOL__FILL_COLOR;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__STYLE: return CarnotWorkflowModelPackage.LANE_SYMBOL__STYLE;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_TO_CONNECTIONS: return CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_TO_CONNECTIONS;
            case CarnotWorkflowModelPackage.IGRAPHICAL_OBJECT__REFERING_FROM_CONNECTIONS: return CarnotWorkflowModelPackage.LANE_SYMBOL__REFERING_FROM_CONNECTIONS;
            default: return -1;
         }
      }
      if (baseClass == INodeSymbol.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.INODE_SYMBOL__XPOS: return CarnotWorkflowModelPackage.LANE_SYMBOL__XPOS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__YPOS: return CarnotWorkflowModelPackage.LANE_SYMBOL__YPOS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__WIDTH: return CarnotWorkflowModelPackage.LANE_SYMBOL__WIDTH;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__HEIGHT: return CarnotWorkflowModelPackage.LANE_SYMBOL__HEIGHT;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__SHAPE: return CarnotWorkflowModelPackage.LANE_SYMBOL__SHAPE;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS: return CarnotWorkflowModelPackage.LANE_SYMBOL__IN_LINKS;
            case CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS: return CarnotWorkflowModelPackage.LANE_SYMBOL__OUT_LINKS;
            default: return -1;
         }
      }
      if (baseClass == IIdentifiableElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.LANE_SYMBOL__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.LANE_SYMBOL__NAME;
            default: return -1;
         }
      }
      if (baseClass == ISwimlaneSymbol.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__ORIENTATION: return CarnotWorkflowModelPackage.LANE_SYMBOL__ORIENTATION;
            case CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__COLLAPSED: return CarnotWorkflowModelPackage.LANE_SYMBOL__COLLAPSED;
            case CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__PARTICIPANT: return CarnotWorkflowModelPackage.LANE_SYMBOL__PARTICIPANT;
            case CarnotWorkflowModelPackage.ISWIMLANE_SYMBOL__CHILD_LANES: return CarnotWorkflowModelPackage.LANE_SYMBOL__CHILD_LANES;
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
   public String toString()
   {
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
      result.append(')');
      return result.toString();
   }

} //LaneSymbolImpl
