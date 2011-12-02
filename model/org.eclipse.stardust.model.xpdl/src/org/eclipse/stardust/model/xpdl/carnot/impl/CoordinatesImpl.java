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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Coordinates</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.CoordinatesImpl#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.CoordinatesImpl#getYPos <em>YPos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CoordinatesImpl extends EObjectImpl implements Coordinates
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The default value of the '{@link #getXPos() <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getXPos()
    * @generated
    * @ordered
    */
   protected static final double XPOS_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getXPos() <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getXPos()
    * @generated
    * @ordered
    */
   protected double xPos = XPOS_EDEFAULT;

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
   protected static final double YPOS_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getYPos() <em>YPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getYPos()
    * @generated
    * @ordered
    */
   protected double yPos = YPOS_EDEFAULT;

   /**
    * This is true if the YPos attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean yPosESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected CoordinatesImpl()
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
      return CarnotWorkflowModelPackage.Literals.COORDINATES;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getXPos()
   {
      return xPos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setXPos(double newXPos)
   {
      double oldXPos = xPos;
      xPos = newXPos;
      boolean oldXPosESet = xPosESet;
      xPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.COORDINATES__XPOS, oldXPos, xPos, !oldXPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetXPos()
   {
      double oldXPos = xPos;
      boolean oldXPosESet = xPosESet;
      xPos = XPOS_EDEFAULT;
      xPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.COORDINATES__XPOS, oldXPos, XPOS_EDEFAULT, oldXPosESet));
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
   public double getYPos()
   {
      return yPos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setYPos(double newYPos)
   {
      double oldYPos = yPos;
      yPos = newYPos;
      boolean oldYPosESet = yPosESet;
      yPosESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.COORDINATES__YPOS, oldYPos, yPos, !oldYPosESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetYPos()
   {
      double oldYPos = yPos;
      boolean oldYPosESet = yPosESet;
      yPos = YPOS_EDEFAULT;
      yPosESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.COORDINATES__YPOS, oldYPos, YPOS_EDEFAULT, oldYPosESet));
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
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.COORDINATES__XPOS:
            return getXPos();
         case CarnotWorkflowModelPackage.COORDINATES__YPOS:
            return getYPos();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.COORDINATES__XPOS:
            setXPos((Double)newValue);
            return;
         case CarnotWorkflowModelPackage.COORDINATES__YPOS:
            setYPos((Double)newValue);
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
         case CarnotWorkflowModelPackage.COORDINATES__XPOS:
            unsetXPos();
            return;
         case CarnotWorkflowModelPackage.COORDINATES__YPOS:
            unsetYPos();
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
         case CarnotWorkflowModelPackage.COORDINATES__XPOS:
            return isSetXPos();
         case CarnotWorkflowModelPackage.COORDINATES__YPOS:
            return isSetYPos();
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
      result.append(" (xPos: ");
      if (xPosESet) result.append(xPos); else result.append("<unset>");
      result.append(", yPos: ");
      if (yPosESet) result.append(yPos); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //CoordinatesImpl
