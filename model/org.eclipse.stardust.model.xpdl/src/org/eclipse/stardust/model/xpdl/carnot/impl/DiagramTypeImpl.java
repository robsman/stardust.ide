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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getPoolSymbols <em>Pool Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DiagramTypeImpl#getMode <em>Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramTypeImpl extends ISymbolContainerImpl implements DiagramType
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
    * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getAttribute()
    * @generated
    * @ordered
    */
	protected EList<AttributeType> attribute;

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
    * The cached value of the '{@link #getPoolSymbols() <em>Pool Symbols</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPoolSymbols()
    * @generated
    * @ordered
    */
   protected EList<PoolSymbol> poolSymbols;

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
    * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMode()
    * @generated
    * @ordered
    */
   protected static final DiagramModeType MODE_EDEFAULT = DiagramModeType.MODE_400_LITERAL;

   /**
    * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMode()
    * @generated
    * @ordered
    */
   protected DiagramModeType mode = MODE_EDEFAULT;

   /**
    * This is true if the Mode attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean modeESet;

   /**
    * The cached value of the '{@link #getNodeContainingFeatures()}' operation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodeContainingFeatures()
    * @generated NOT
    * @ordered
    */
   private List nodeContainingFeatures = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DiagramTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.DIAGRAM_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
	public EList<AttributeType> getAttribute() {
      if (attribute == null)
      {
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE);
      }
      return attribute;
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
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PoolSymbol> getPoolSymbols()
   {
      if (poolSymbols == null)
      {
         poolSymbols = new EObjectContainmentWithInverseEList<PoolSymbol>(PoolSymbol.class, this, CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS, CarnotWorkflowModelPackage.POOL_SYMBOL__DIAGRAM);
      }
      return poolSymbols;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
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
   public DiagramModeType getMode()
   {
      return mode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMode(DiagramModeType newMode)
   {
      DiagramModeType oldMode = mode;
      mode = newMode == null ? MODE_EDEFAULT : newMode;
      boolean oldModeESet = modeESet;
      modeESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE, oldMode, mode, !oldModeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetMode()
   {
      DiagramModeType oldMode = mode;
      boolean oldModeESet = modeESet;
      mode = MODE_EDEFAULT;
      modeESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE, oldMode, MODE_EDEFAULT, oldModeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetMode()
   {
      return modeESet;
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPoolSymbols()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            return ((InternalEList<?>)getPoolSymbols()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            return getPoolSymbols();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION:
            return getOrientation();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE:
            return getMode();
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            getPoolSymbols().clear();
            getPoolSymbols().addAll((Collection<? extends PoolSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION:
            setOrientation((OrientationType)newValue);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE:
            setMode((DiagramModeType)newValue);
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            getPoolSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION:
            unsetOrientation();
            return;
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE:
            unsetMode();
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
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__POOL_SYMBOLS:
            return poolSymbols != null && !poolSymbols.isEmpty();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ORIENTATION:
            return isSetOrientation();
         case CarnotWorkflowModelPackage.DIAGRAM_TYPE__MODE:
            return isSetMode();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getNodeContainingFeatures()
   {
      if (null == nodeContainingFeatures)
      {
         // no need to synchronize on init as effectively a constant is initialized
         List features = new ArrayList(super.getNodeContainingFeatures());
         features.add(CarnotWorkflowModelPackage.eINSTANCE.getDiagramType_PoolSymbols());
         this.nodeContainingFeatures = Collections.unmodifiableList(features);
      }
      return nodeContainingFeatures;
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
            case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID: return CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
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
            case CarnotWorkflowModelPackage.IMODEL_ELEMENT__ELEMENT_OID: return CarnotWorkflowModelPackage.DIAGRAM_TYPE__ELEMENT_OID;
            default: return -1;
         }
      }
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.DIAGRAM_TYPE__ATTRIBUTE;
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
      result.append(" (elementOid: "); //$NON-NLS-1$
      if (elementOidESet) result.append(elementOid); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", name: "); //$NON-NLS-1$
      result.append(name);
      result.append(", orientation: "); //$NON-NLS-1$
      if (orientationESet) result.append(orientation); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", mode: "); //$NON-NLS-1$
      if (modeESet) result.append(mode); else result.append("<unset>"); //$NON-NLS-1$
      result.append(')');
      return result.toString();
   }

} //DiagramTypeImpl
