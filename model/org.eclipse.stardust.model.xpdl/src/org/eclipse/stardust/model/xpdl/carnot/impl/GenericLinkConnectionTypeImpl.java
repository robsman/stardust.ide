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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Link Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getSourceSymbol <em>Source Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GenericLinkConnectionTypeImpl#getTargetSymbol <em>Target Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenericLinkConnectionTypeImpl extends EObjectImpl implements GenericLinkConnectionType
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
    * The default value of the '{@link #getSourceAnchor() <em>Source Anchor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSourceAnchor()
    * @generated
    * @ordered
    */
   protected static final String SOURCE_ANCHOR_EDEFAULT = "center"; //$NON-NLS-1$

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
   protected static final String TARGET_ANCHOR_EDEFAULT = "center"; //$NON-NLS-1$

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
   protected static final RoutingType ROUTING_EDEFAULT = RoutingType.DEFAULT_LITERAL;

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
   protected GenericLinkConnectionTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.GENERIC_LINK_CONNECTION_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR, oldFillColor, fillColor));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RoutingType getRouting()
   {
      return routing;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setRouting(RoutingType newRouting)
   {
      RoutingType oldRouting = routing;
      routing = newRouting == null ? ROUTING_EDEFAULT : newRouting;
      boolean oldRoutingESet = routingESet;
      routingESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING, oldRouting, routing, !oldRoutingESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetRouting()
   {
      RoutingType oldRouting = routing;
      boolean oldRoutingESet = routingESet;
      routing = ROUTING_EDEFAULT;
      routingESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING, oldRouting, ROUTING_EDEFAULT, oldRoutingESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetRouting()
   {
      return routingESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<Coordinates> getCoordinates()
   {
      if (coordinates == null)
      {
         coordinates = new EObjectContainmentEList<Coordinates>(Coordinates.class, this, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES);
      }
      return coordinates;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE, oldStyle, style));
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
         referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
         referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__FROM);
      }
      return referingFromConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getSourceAnchor()
   {
      return sourceAnchor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceAnchor(String newSourceAnchor)
   {
      String oldSourceAnchor = sourceAnchor;
      sourceAnchor = newSourceAnchor;
      boolean oldSourceAnchorESet = sourceAnchorESet;
      sourceAnchorESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, sourceAnchor, !oldSourceAnchorESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetSourceAnchor()
   {
      String oldSourceAnchor = sourceAnchor;
      boolean oldSourceAnchorESet = sourceAnchorESet;
      sourceAnchor = SOURCE_ANCHOR_EDEFAULT;
      sourceAnchorESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, SOURCE_ANCHOR_EDEFAULT, oldSourceAnchorESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetSourceAnchor()
   {
      return sourceAnchorESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getTargetAnchor()
   {
      return targetAnchor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetAnchor(String newTargetAnchor)
   {
      String oldTargetAnchor = targetAnchor;
      targetAnchor = newTargetAnchor;
      boolean oldTargetAnchorESet = targetAnchorESet;
      targetAnchorESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, targetAnchor, !oldTargetAnchorESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetTargetAnchor()
   {
      String oldTargetAnchor = targetAnchor;
      boolean oldTargetAnchorESet = targetAnchorESet;
      targetAnchor = TARGET_ANCHOR_EDEFAULT;
      targetAnchorESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, TARGET_ANCHOR_EDEFAULT, oldTargetAnchorESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetTargetAnchor()
   {
      return targetAnchorESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LinkTypeType getLinkType()
   {
      return linkType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLinkType(LinkTypeType newLinkType, NotificationChain msgs)
   {
      LinkTypeType oldLinkType = linkType;
      linkType = newLinkType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE, oldLinkType, newLinkType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLinkType(LinkTypeType newLinkType)
   {
      if (newLinkType != linkType)
      {
         NotificationChain msgs = null;
         if (linkType != null)
            msgs = ((InternalEObject)linkType).eInverseRemove(this, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
         if (newLinkType != null)
            msgs = ((InternalEObject)newLinkType).eInverseAdd(this, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
         msgs = basicSetLinkType(newLinkType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE, newLinkType, newLinkType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public INodeSymbol getSourceSymbol()
   {
      return sourceSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSourceSymbol(INodeSymbol newSourceSymbol, NotificationChain msgs)
   {
      INodeSymbol oldSourceSymbol = sourceSymbol;
      sourceSymbol = newSourceSymbol;
      boolean oldSourceSymbolESet = sourceSymbolESet;
      sourceSymbolESet = true;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, oldSourceSymbol, newSourceSymbol, !oldSourceSymbolESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSourceSymbol(INodeSymbol newSourceSymbol)
   {
      if (newSourceSymbol != sourceSymbol)
      {
         NotificationChain msgs = null;
         if (sourceSymbol != null)
            msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
         if (newSourceSymbol != null)
            msgs = ((InternalEObject)newSourceSymbol).eInverseAdd(this, CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
         msgs = basicSetSourceSymbol(newSourceSymbol, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldSourceSymbolESet = sourceSymbolESet;
         sourceSymbolESet = true;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, newSourceSymbol, newSourceSymbol, !oldSourceSymbolESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicUnsetSourceSymbol(NotificationChain msgs)
   {
      INodeSymbol oldSourceSymbol = sourceSymbol;
      sourceSymbol = null;
      boolean oldSourceSymbolESet = sourceSymbolESet;
      sourceSymbolESet = false;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, oldSourceSymbol, null, oldSourceSymbolESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetSourceSymbol()
   {
      if (sourceSymbol != null)
      {
         NotificationChain msgs = null;
         msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
         msgs = basicUnsetSourceSymbol(msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldSourceSymbolESet = sourceSymbolESet;
         sourceSymbolESet = false;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL, null, null, oldSourceSymbolESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetSourceSymbol()
   {
      return sourceSymbolESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public INodeSymbol getTargetSymbol()
   {
      return targetSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTargetSymbol(INodeSymbol newTargetSymbol, NotificationChain msgs)
   {
      INodeSymbol oldTargetSymbol = targetSymbol;
      targetSymbol = newTargetSymbol;
      boolean oldTargetSymbolESet = targetSymbolESet;
      targetSymbolESet = true;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, oldTargetSymbol, newTargetSymbol, !oldTargetSymbolESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTargetSymbol(INodeSymbol newTargetSymbol)
   {
      if (newTargetSymbol != targetSymbol)
      {
         NotificationChain msgs = null;
         if (targetSymbol != null)
            msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
         if (newTargetSymbol != null)
            msgs = ((InternalEObject)newTargetSymbol).eInverseAdd(this, CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
         msgs = basicSetTargetSymbol(newTargetSymbol, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldTargetSymbolESet = targetSymbolESet;
         targetSymbolESet = true;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, newTargetSymbol, newTargetSymbol, !oldTargetSymbolESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicUnsetTargetSymbol(NotificationChain msgs)
   {
      INodeSymbol oldTargetSymbol = targetSymbol;
      targetSymbol = null;
      boolean oldTargetSymbolESet = targetSymbolESet;
      targetSymbolESet = false;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, oldTargetSymbol, null, oldTargetSymbolESet);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetTargetSymbol()
   {
      if (targetSymbol != null)
      {
         NotificationChain msgs = null;
         msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
         msgs = basicUnsetTargetSymbol(msgs);
         if (msgs != null) msgs.dispatch();
      }
      else
      {
         boolean oldTargetSymbolESet = targetSymbolESet;
         targetSymbolESet = false;
         if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL, null, null, oldTargetSymbolESet));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetTargetSymbol()
   {
      return targetSymbolESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public INodeSymbol getSourceNode()
   {
      return getSourceSymbol();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public void setSourceNode(INodeSymbol nodeSymbol)
   {
      setSourceSymbol(nodeSymbol);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public INodeSymbol getTargetNode()
   {
      return getTargetSymbol();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public void setTargetNode(INodeSymbol nodeSymbol)
   {
      setTargetSymbol(nodeSymbol);
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
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            if (linkType != null)
               msgs = ((InternalEObject)linkType).eInverseRemove(this, CarnotWorkflowModelPackage.LINK_TYPE_TYPE__LINK_INSTANCES, LinkTypeType.class, msgs);
            return basicSetLinkType((LinkTypeType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            if (sourceSymbol != null)
               msgs = ((InternalEObject)sourceSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__OUT_LINKS, INodeSymbol.class, msgs);
            return basicSetSourceSymbol((INodeSymbol)otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
            if (targetSymbol != null)
               msgs = ((InternalEObject)targetSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.INODE_SYMBOL__IN_LINKS, INodeSymbol.class, msgs);
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
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
            return ((InternalEList<?>)getCoordinates()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            return basicSetLinkType(null, msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            return basicUnsetSourceSymbol(msgs);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
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
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
            return getBorderColor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
            return getFillColor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
            return getStyle();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return getReferingToConnections();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return getReferingFromConnections();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
            return getSourceAnchor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
            return getTargetAnchor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
            return getRouting();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
            return getCoordinates();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            return getLinkType();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            return getSourceSymbol();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
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
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
            setBorderColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
            setFillColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
            setStyle((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
            setSourceAnchor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
            setTargetAnchor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
            setRouting((RoutingType)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
            getCoordinates().clear();
            getCoordinates().addAll((Collection<? extends Coordinates>)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            setLinkType((LinkTypeType)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            setSourceSymbol((INodeSymbol)newValue);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
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
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
            setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
            unsetSourceAnchor();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
            unsetTargetAnchor();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
            unsetRouting();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
            getCoordinates().clear();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            setLinkType((LinkTypeType)null);
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            unsetSourceSymbol();
            return;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
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
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__STYLE:
            return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return referingToConnections != null && !referingToConnections.isEmpty();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return referingFromConnections != null && !referingFromConnections.isEmpty();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_ANCHOR:
            return isSetSourceAnchor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_ANCHOR:
            return isSetTargetAnchor();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__ROUTING:
            return isSetRouting();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__COORDINATES:
            return coordinates != null && !coordinates.isEmpty();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__LINK_TYPE:
            return linkType != null;
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__SOURCE_SYMBOL:
            return isSetSourceSymbol();
         case CarnotWorkflowModelPackage.GENERIC_LINK_CONNECTION_TYPE__TARGET_SYMBOL:
            return isSetTargetSymbol();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public IMetaType getMetaType()
   {
      return getLinkType();
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
      result.append(", sourceAnchor: "); //$NON-NLS-1$
      if (sourceAnchorESet) result.append(sourceAnchor); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", targetAnchor: "); //$NON-NLS-1$
      if (targetAnchorESet) result.append(targetAnchor); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", routing: "); //$NON-NLS-1$
      if (routingESet) result.append(routing); else result.append("<unset>"); //$NON-NLS-1$
      result.append(')');
      return result.toString();
   }

} //GenericLinkConnectionTypeImpl
