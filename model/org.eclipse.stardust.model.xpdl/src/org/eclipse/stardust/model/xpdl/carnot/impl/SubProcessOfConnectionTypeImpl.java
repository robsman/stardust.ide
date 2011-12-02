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
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sub Process Of Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getReferingFromConnections <em>Refering From Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getProcessSymbol <em>Process Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.SubProcessOfConnectionTypeImpl#getSubprocessSymbol <em>Subprocess Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubProcessOfConnectionTypeImpl extends EObjectImpl implements SubProcessOfConnectionType
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
    * The cached value of the '{@link #getProcessSymbol() <em>Process Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProcessSymbol()
    * @generated
    * @ordered
    */
   protected ProcessSymbolType processSymbol;

   /**
    * The cached value of the '{@link #getSubprocessSymbol() <em>Subprocess Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSubprocessSymbol()
    * @generated
    * @ordered
    */
   protected ProcessSymbolType subprocessSymbol;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected SubProcessOfConnectionTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.SUB_PROCESS_OF_CONNECTION_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR, oldBorderColor, borderColor));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR, oldFillColor, fillColor));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING, oldRouting, routing, !oldRoutingESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING, oldRouting, ROUTING_EDEFAULT, oldRoutingESet));
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
         coordinates = new EObjectContainmentEList<Coordinates>(Coordinates.class, this, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES);
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__STYLE, oldStyle, style));
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
         referingToConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__TO);
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
         referingFromConnections = new EObjectWithInverseEList<RefersToConnectionType>(RefersToConnectionType.class, this, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS, CarnotWorkflowModelPackage.REFERS_TO_CONNECTION_TYPE__FROM);
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, sourceAnchor, !oldSourceAnchorESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR, oldSourceAnchor, SOURCE_ANCHOR_EDEFAULT, oldSourceAnchorESet));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, targetAnchor, !oldTargetAnchorESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR, oldTargetAnchor, TARGET_ANCHOR_EDEFAULT, oldTargetAnchorESet));
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
   public ProcessSymbolType getProcessSymbol()
   {
      return processSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetProcessSymbol(ProcessSymbolType newProcessSymbol, NotificationChain msgs)
   {
      ProcessSymbolType oldProcessSymbol = processSymbol;
      processSymbol = newProcessSymbol;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL, oldProcessSymbol, newProcessSymbol);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setProcessSymbol(ProcessSymbolType newProcessSymbol)
   {
      if (newProcessSymbol != processSymbol)
      {
         NotificationChain msgs = null;
         if (processSymbol != null)
            msgs = ((InternalEObject)processSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES, ProcessSymbolType.class, msgs);
         if (newProcessSymbol != null)
            msgs = ((InternalEObject)newProcessSymbol).eInverseAdd(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES, ProcessSymbolType.class, msgs);
         msgs = basicSetProcessSymbol(newProcessSymbol, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL, newProcessSymbol, newProcessSymbol));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ProcessSymbolType getSubprocessSymbol()
   {
      return subprocessSymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSubprocessSymbol(ProcessSymbolType newSubprocessSymbol, NotificationChain msgs)
   {
      ProcessSymbolType oldSubprocessSymbol = subprocessSymbol;
      subprocessSymbol = newSubprocessSymbol;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL, oldSubprocessSymbol, newSubprocessSymbol);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSubprocessSymbol(ProcessSymbolType newSubprocessSymbol)
   {
      if (newSubprocessSymbol != subprocessSymbol)
      {
         NotificationChain msgs = null;
         if (subprocessSymbol != null)
            msgs = ((InternalEObject)subprocessSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES, ProcessSymbolType.class, msgs);
         if (newSubprocessSymbol != null)
            msgs = ((InternalEObject)newSubprocessSymbol).eInverseAdd(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES, ProcessSymbolType.class, msgs);
         msgs = basicSetSubprocessSymbol(newSubprocessSymbol, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL, newSubprocessSymbol, newSubprocessSymbol));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public INodeSymbol getSourceNode()
   {
      return getSubprocessSymbol();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public void setSourceNode(INodeSymbol nodeSymbol)
   {
      setSubprocessSymbol((ProcessSymbolType) nodeSymbol);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public INodeSymbol getTargetNode()
   {
      return getProcessSymbol();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public void setTargetNode(INodeSymbol nodeSymbol)
   {
      setProcessSymbol((ProcessSymbolType) nodeSymbol);
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingToConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferingFromConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            if (processSymbol != null)
               msgs = ((InternalEObject)processSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES, ProcessSymbolType.class, msgs);
            return basicSetProcessSymbol((ProcessSymbolType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            if (subprocessSymbol != null)
               msgs = ((InternalEObject)subprocessSymbol).eInverseRemove(this, CarnotWorkflowModelPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES, ProcessSymbolType.class, msgs);
            return basicSetSubprocessSymbol((ProcessSymbolType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return ((InternalEList<?>)getReferingToConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return ((InternalEList<?>)getReferingFromConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES:
            return ((InternalEList<?>)getCoordinates()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            return basicSetProcessSymbol(null, msgs);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            return basicSetSubprocessSymbol(null, msgs);
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR:
            return getBorderColor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR:
            return getFillColor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__STYLE:
            return getStyle();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return getReferingToConnections();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return getReferingFromConnections();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR:
            return getSourceAnchor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR:
            return getTargetAnchor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING:
            return getRouting();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES:
            return getCoordinates();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            return getProcessSymbol();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            return getSubprocessSymbol();
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR:
            setBorderColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR:
            setFillColor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__STYLE:
            setStyle((String)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            getReferingToConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            getReferingFromConnections().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR:
            setSourceAnchor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR:
            setTargetAnchor((String)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING:
            setRouting((RoutingType)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES:
            getCoordinates().clear();
            getCoordinates().addAll((Collection<? extends Coordinates>)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            setProcessSymbol((ProcessSymbolType)newValue);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            setSubprocessSymbol((ProcessSymbolType)newValue);
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR:
            setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            getReferingToConnections().clear();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            getReferingFromConnections().clear();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR:
            unsetSourceAnchor();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR:
            unsetTargetAnchor();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING:
            unsetRouting();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES:
            getCoordinates().clear();
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            setProcessSymbol((ProcessSymbolType)null);
            return;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            setSubprocessSymbol((ProcessSymbolType)null);
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
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BORDER_COLOR_EDEFAULT.equals(borderColor);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__STYLE:
            return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_TO_CONNECTIONS:
            return referingToConnections != null && !referingToConnections.isEmpty();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__REFERING_FROM_CONNECTIONS:
            return referingFromConnections != null && !referingFromConnections.isEmpty();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SOURCE_ANCHOR:
            return isSetSourceAnchor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__TARGET_ANCHOR:
            return isSetTargetAnchor();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__ROUTING:
            return isSetRouting();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__COORDINATES:
            return coordinates != null && !coordinates.isEmpty();
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL:
            return processSymbol != null;
         case CarnotWorkflowModelPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL:
            return subprocessSymbol != null;
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

} //SubProcessOfConnectionTypeImpl
