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
package org.eclipse.stardust.model.xpdl.xpdl2.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declarations Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl#getTypeDeclaration <em>Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationsTypeImpl extends EObjectImpl implements TypeDeclarationsType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * The cached value of the '{@link #getTypeDeclaration() <em>Type Declaration</em>}' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getTypeDeclaration()
    * @generated
    * @ordered
    */
	protected EList<TypeDeclarationType> typeDeclaration;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TypeDeclarationsTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return XpdlPackage.Literals.TYPE_DECLARATIONS_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EList<TypeDeclarationType> getTypeDeclaration() {
      if (typeDeclaration == null)
      {
         typeDeclaration = new EObjectContainmentEList<TypeDeclarationType>(TypeDeclarationType.class, this, XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION);
      }
      return typeDeclaration;
   }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeDeclarationType getTypeDeclaration(String typeId) {
	   if (typeId != null)
	   {
	      EList<TypeDeclarationType> declarations = getTypeDeclaration();
	      for (TypeDeclarationType type : declarations)
	      {
	         if (typeId.equals(type.getId())) 
	         {
	            return type;
	         }
	      }
	   }
	   return null;
	}

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
            return ((InternalEList<?>)getTypeDeclaration()).basicRemove(otherEnd, msgs);
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
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
            return getTypeDeclaration();
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
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
            getTypeDeclaration().clear();
            getTypeDeclaration().addAll((Collection<? extends TypeDeclarationType>)newValue);
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
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
            getTypeDeclaration().clear();
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
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION:
            return typeDeclaration != null && !typeDeclaration.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //TypeDeclarationsTypeImpl