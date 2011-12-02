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
package org.eclipse.stardust.modeling.templates.emf.template.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.RootsType;
import org.eclipse.stardust.modeling.templates.emf.template.StyleType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getRoots <em>Roots</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateTypeImpl extends EObjectImpl implements TemplateType {
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
    * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDocumentation()
    * @generated
    * @ordered
    */
	protected DocumentationType documentation;

	/**
    * The cached value of the '{@link #getRoots() <em>Roots</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getRoots()
    * @generated
    * @ordered
    */
	protected RootsType roots;

	/**
    * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getStyle()
    * @generated
    * @ordered
    */
	protected static final StyleType STYLE_EDEFAULT = StyleType.STANDALONE;

	/**
    * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getStyle()
    * @generated
    * @ordered
    */
	protected StyleType style = STYLE_EDEFAULT;

	/**
    * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getCategory()
    * @generated
    * @ordered
    */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
    * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getCategory()
    * @generated
    * @ordered
    */
	protected String category = CATEGORY_EDEFAULT;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TemplateTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.TEMPLATE_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__ID, oldId, id));
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
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__NAME, oldName, name));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DocumentationType getDocumentation() {
      return documentation;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetDocumentation(DocumentationType newDocumentation, NotificationChain msgs) {
      DocumentationType oldDocumentation = documentation;
      documentation = newDocumentation;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION, oldDocumentation, newDocumentation);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setDocumentation(DocumentationType newDocumentation) {
      if (newDocumentation != documentation)
      {
         NotificationChain msgs = null;
         if (documentation != null)
            msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION, null, msgs);
         if (newDocumentation != null)
            msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION, null, msgs);
         msgs = basicSetDocumentation(newDocumentation, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION, newDocumentation, newDocumentation));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public RootsType getRoots() {
      return roots;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetRoots(RootsType newRoots, NotificationChain msgs) {
      RootsType oldRoots = roots;
      roots = newRoots;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__ROOTS, oldRoots, newRoots);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setRoots(RootsType newRoots) {
      if (newRoots != roots)
      {
         NotificationChain msgs = null;
         if (roots != null)
            msgs = ((InternalEObject)roots).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_TYPE__ROOTS, null, msgs);
         if (newRoots != null)
            msgs = ((InternalEObject)newRoots).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_TYPE__ROOTS, null, msgs);
         msgs = basicSetRoots(newRoots, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__ROOTS, newRoots, newRoots));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public StyleType getStyle() {
      return style;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setStyle(StyleType newStyle) {
      StyleType oldStyle = style;
      style = newStyle == null ? STYLE_EDEFAULT : newStyle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__STYLE, oldStyle, style));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getCategory() {
      return category;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setCategory(String newCategory) {
      String oldCategory = category;
      category = newCategory;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_TYPE__CATEGORY, oldCategory, category));
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
         case TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION:
            return basicSetDocumentation(null, msgs);
         case TemplatePackage.TEMPLATE_TYPE__ROOTS:
            return basicSetRoots(null, msgs);
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
         case TemplatePackage.TEMPLATE_TYPE__ID:
            return getId();
         case TemplatePackage.TEMPLATE_TYPE__NAME:
            return getName();
         case TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION:
            return getDocumentation();
         case TemplatePackage.TEMPLATE_TYPE__ROOTS:
            return getRoots();
         case TemplatePackage.TEMPLATE_TYPE__STYLE:
            return getStyle();
         case TemplatePackage.TEMPLATE_TYPE__CATEGORY:
            return getCategory();
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
         case TemplatePackage.TEMPLATE_TYPE__ID:
            setId((String)newValue);
            return;
         case TemplatePackage.TEMPLATE_TYPE__NAME:
            setName((String)newValue);
            return;
         case TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION:
            setDocumentation((DocumentationType)newValue);
            return;
         case TemplatePackage.TEMPLATE_TYPE__ROOTS:
            setRoots((RootsType)newValue);
            return;
         case TemplatePackage.TEMPLATE_TYPE__STYLE:
            setStyle((StyleType)newValue);
            return;
         case TemplatePackage.TEMPLATE_TYPE__CATEGORY:
            setCategory((String)newValue);
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
         case TemplatePackage.TEMPLATE_TYPE__ID:
            setId(ID_EDEFAULT);
            return;
         case TemplatePackage.TEMPLATE_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION:
            setDocumentation((DocumentationType)null);
            return;
         case TemplatePackage.TEMPLATE_TYPE__ROOTS:
            setRoots((RootsType)null);
            return;
         case TemplatePackage.TEMPLATE_TYPE__STYLE:
            setStyle(STYLE_EDEFAULT);
            return;
         case TemplatePackage.TEMPLATE_TYPE__CATEGORY:
            setCategory(CATEGORY_EDEFAULT);
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
         case TemplatePackage.TEMPLATE_TYPE__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case TemplatePackage.TEMPLATE_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case TemplatePackage.TEMPLATE_TYPE__DOCUMENTATION:
            return documentation != null;
         case TemplatePackage.TEMPLATE_TYPE__ROOTS:
            return roots != null;
         case TemplatePackage.TEMPLATE_TYPE__STYLE:
            return style != STYLE_EDEFAULT;
         case TemplatePackage.TEMPLATE_TYPE__CATEGORY:
            return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
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
      result.append(" (id: ");
      result.append(id);
      result.append(", name: ");
      result.append(name);
      result.append(", style: ");
      result.append(style);
      result.append(", category: ");
      result.append(category);
      result.append(')');
      return result.toString();
   }

} //TemplateTypeImpl
