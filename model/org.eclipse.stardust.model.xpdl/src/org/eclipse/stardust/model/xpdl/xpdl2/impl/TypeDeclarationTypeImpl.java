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


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationTypeImpl extends EObjectImpl implements TypeDeclarationType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

	/**
    * The cached value of the '{@link #getExtendedAttributes() <em>Extended Attributes</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getExtendedAttributes()
    * @generated
    * @ordered
    */
	protected ExtendedAttributesType extendedAttributes;

   /**
    * The cached value of the '{@link #getBasicType() <em>Basic Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getBasicType()
    * @generated
    * @ordered
    */
	protected BasicTypeType basicType;

   /**
    * The cached value of the '{@link #getDeclaredType() <em>Declared Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDeclaredType()
    * @generated
    * @ordered
    */
	protected DeclaredTypeType declaredType;

   /**
    * The cached value of the '{@link #getSchemaType() <em>Schema Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getSchemaType()
    * @generated
    * @ordered
    */
	protected SchemaTypeType schemaType;

   /**
    * The cached value of the '{@link #getExternalReference() <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getExternalReference()
    * @generated
    * @ordered
    */
	protected ExternalReferenceType externalReference;

   /**
    * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDescription()
    * @generated
    * @ordered
    */
	protected static final String DESCRIPTION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDescription()
    * @generated
    * @ordered
    */
	protected String description = DESCRIPTION_EDEFAULT;

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
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TypeDeclarationTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return XpdlPackage.Literals.TYPE_DECLARATION_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public BasicTypeType getBasicType() {
      return basicType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetBasicType(BasicTypeType newBasicType, NotificationChain msgs) {
      BasicTypeType oldBasicType = basicType;
      basicType = newBasicType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE, oldBasicType, newBasicType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setBasicType(BasicTypeType newBasicType) {
      if (newBasicType != basicType)
      {
         NotificationChain msgs = null;
         if (basicType != null)
            msgs = ((InternalEObject)basicType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE, null, msgs);
         if (newBasicType != null)
            msgs = ((InternalEObject)newBasicType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE, null, msgs);
         msgs = basicSetBasicType(newBasicType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE, newBasicType, newBasicType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DeclaredTypeType getDeclaredType() {
      return declaredType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetDeclaredType(DeclaredTypeType newDeclaredType, NotificationChain msgs) {
      DeclaredTypeType oldDeclaredType = declaredType;
      declaredType = newDeclaredType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE, oldDeclaredType, newDeclaredType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setDeclaredType(DeclaredTypeType newDeclaredType) {
      if (newDeclaredType != declaredType)
      {
         NotificationChain msgs = null;
         if (declaredType != null)
            msgs = ((InternalEObject)declaredType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE, null, msgs);
         if (newDeclaredType != null)
            msgs = ((InternalEObject)newDeclaredType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE, null, msgs);
         msgs = basicSetDeclaredType(newDeclaredType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE, newDeclaredType, newDeclaredType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public SchemaTypeType getSchemaType() {
      return schemaType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetSchemaType(SchemaTypeType newSchemaType, NotificationChain msgs) {
      SchemaTypeType oldSchemaType = schemaType;
      schemaType = newSchemaType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE, oldSchemaType, newSchemaType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setSchemaType(SchemaTypeType newSchemaType) {
      if (newSchemaType != schemaType)
      {
         NotificationChain msgs = null;
         if (schemaType != null)
            msgs = ((InternalEObject)schemaType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE, null, msgs);
         if (newSchemaType != null)
            msgs = ((InternalEObject)newSchemaType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE, null, msgs);
         msgs = basicSetSchemaType(newSchemaType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE, newSchemaType, newSchemaType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExternalReferenceType getExternalReference() {
      return externalReference;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetExternalReference(ExternalReferenceType newExternalReference, NotificationChain msgs) {
      ExternalReferenceType oldExternalReference = externalReference;
      externalReference = newExternalReference;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE, oldExternalReference, newExternalReference);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setExternalReference(ExternalReferenceType newExternalReference) {
      if (newExternalReference != externalReference)
      {
         NotificationChain msgs = null;
         if (externalReference != null)
            msgs = ((InternalEObject)externalReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE, null, msgs);
         if (newExternalReference != null)
            msgs = ((InternalEObject)newExternalReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE, null, msgs);
         msgs = basicSetExternalReference(newExternalReference, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE, newExternalReference, newExternalReference));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getDescription() {
      return description;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setDescription(String newDescription) {
      String oldDescription = description;
      description = newDescription;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__DESCRIPTION, oldDescription, description));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExtendedAttributesType getExtendedAttributes() {
      return extendedAttributes;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetExtendedAttributes(ExtendedAttributesType newExtendedAttributes, NotificationChain msgs) {
      ExtendedAttributesType oldExtendedAttributes = extendedAttributes;
      extendedAttributes = newExtendedAttributes;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setExtendedAttributes(ExtendedAttributesType newExtendedAttributes) {
      if (newExtendedAttributes != extendedAttributes)
      {
         NotificationChain msgs = null;
         if (extendedAttributes != null)
            msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
         if (newExtendedAttributes != null)
            msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
         msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
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
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__ID, oldId, id));
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
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.TYPE_DECLARATION_TYPE__NAME, oldName, name));
   }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public XpdlTypeType getDataType() {
	   if (basicType != null) {
	      return basicType;
	   }
	   if (declaredType != null) {
	      return declaredType;
	   }
	   if (externalReference != null) {
	      return externalReference;
	   }
	   return schemaType;
	}

	/**
	 * Retrieves the XSDSchema object associated with this type.
	 * Delegates the schema retrieval to the concrete type if the
	 * type is either SchemaType or ExternalReference, otherwise
	 * returns null.
	 *   
	 * @generated NOT
	 */
	public XSDSchema getSchema() {
       XpdlTypeType type = getDataType();
       if (type instanceof SchemaTypeType)
       {
          return ((SchemaTypeType) type).getSchema();
       }
       if (type instanceof ExternalReferenceType)
       {
          return ((ExternalReferenceType) type).getSchema();
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
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES:
            return basicSetExtendedAttributes(null, msgs);
         case XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE:
            return basicSetBasicType(null, msgs);
         case XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE:
            return basicSetDeclaredType(null, msgs);
         case XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE:
            return basicSetSchemaType(null, msgs);
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE:
            return basicSetExternalReference(null, msgs);
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
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES:
            return getExtendedAttributes();
         case XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE:
            return getBasicType();
         case XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE:
            return getDeclaredType();
         case XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE:
            return getSchemaType();
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE:
            return getExternalReference();
         case XpdlPackage.TYPE_DECLARATION_TYPE__DESCRIPTION:
            return getDescription();
         case XpdlPackage.TYPE_DECLARATION_TYPE__ID:
            return getId();
         case XpdlPackage.TYPE_DECLARATION_TYPE__NAME:
            return getName();
      }
      return super.eGet(featureID, resolve, coreType);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID)
      {
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES:
            setExtendedAttributes((ExtendedAttributesType)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE:
            setBasicType((BasicTypeType)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE:
            setDeclaredType((DeclaredTypeType)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE:
            setSchemaType((SchemaTypeType)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DESCRIPTION:
            setDescription((String)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__ID:
            setId((String)newValue);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__NAME:
            setName((String)newValue);
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
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES:
            setExtendedAttributes((ExtendedAttributesType)null);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE:
            setBasicType((BasicTypeType)null);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE:
            setDeclaredType((DeclaredTypeType)null);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE:
            setSchemaType((SchemaTypeType)null);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)null);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DESCRIPTION:
            setDescription(DESCRIPTION_EDEFAULT);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__ID:
            setId(ID_EDEFAULT);
            return;
         case XpdlPackage.TYPE_DECLARATION_TYPE__NAME:
            setName(NAME_EDEFAULT);
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
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES:
            return extendedAttributes != null;
         case XpdlPackage.TYPE_DECLARATION_TYPE__BASIC_TYPE:
            return basicType != null;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DECLARED_TYPE:
            return declaredType != null;
         case XpdlPackage.TYPE_DECLARATION_TYPE__SCHEMA_TYPE:
            return schemaType != null;
         case XpdlPackage.TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE:
            return externalReference != null;
         case XpdlPackage.TYPE_DECLARATION_TYPE__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
         case XpdlPackage.TYPE_DECLARATION_TYPE__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case XpdlPackage.TYPE_DECLARATION_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      }
      return super.eIsSet(featureID);
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
      result.append(" (description: ");
      result.append(description);
      result.append(", id: ");
      result.append(id);
      result.append(", name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //TypeDeclarationTypeImpl