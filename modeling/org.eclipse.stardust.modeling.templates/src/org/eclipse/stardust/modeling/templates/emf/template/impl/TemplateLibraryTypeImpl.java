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
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatesType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl#getTemplates <em>Templates</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateLibraryTypeImpl extends EObjectImpl implements TemplateLibraryType {
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
    * The cached value of the '{@link #getTemplates() <em>Templates</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getTemplates()
    * @generated
    * @ordered
    */
	protected TemplatesType templates;

	/**
    * The cached value of the '{@link #getModel() <em>Model</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getModel()
    * @generated
    * @ordered
    */
	protected ModelType model;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TemplateLibraryTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.TEMPLATE_LIBRARY_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__ID, oldId, id));
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
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__NAME, oldName, name));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION, oldDocumentation, newDocumentation);
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
            msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION, null, msgs);
         if (newDocumentation != null)
            msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION, null, msgs);
         msgs = basicSetDocumentation(newDocumentation, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION, newDocumentation, newDocumentation));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplatesType getTemplates() {
      return templates;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetTemplates(TemplatesType newTemplates, NotificationChain msgs) {
      TemplatesType oldTemplates = templates;
      templates = newTemplates;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES, oldTemplates, newTemplates);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setTemplates(TemplatesType newTemplates) {
      if (newTemplates != templates)
      {
         NotificationChain msgs = null;
         if (templates != null)
            msgs = ((InternalEObject)templates).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES, null, msgs);
         if (newTemplates != null)
            msgs = ((InternalEObject)newTemplates).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES, null, msgs);
         msgs = basicSetTemplates(newTemplates, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES, newTemplates, newTemplates));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ModelType getModel() {
      return model;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetModel(ModelType newModel, NotificationChain msgs) {
      ModelType oldModel = model;
      model = newModel;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL, oldModel, newModel);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setModel(ModelType newModel) {
      if (newModel != model)
      {
         NotificationChain msgs = null;
         if (model != null)
            msgs = ((InternalEObject)model).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL, null, msgs);
         if (newModel != null)
            msgs = ((InternalEObject)newModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL, null, msgs);
         msgs = basicSetModel(newModel, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL, newModel, newModel));
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
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION:
            return basicSetDocumentation(null, msgs);
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES:
            return basicSetTemplates(null, msgs);
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL:
            return basicSetModel(null, msgs);
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
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__ID:
            return getId();
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__NAME:
            return getName();
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION:
            return getDocumentation();
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES:
            return getTemplates();
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL:
            return getModel();
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
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__ID:
            setId((String)newValue);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__NAME:
            setName((String)newValue);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION:
            setDocumentation((DocumentationType)newValue);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES:
            setTemplates((TemplatesType)newValue);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL:
            setModel((ModelType)newValue);
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
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__ID:
            setId(ID_EDEFAULT);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION:
            setDocumentation((DocumentationType)null);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES:
            setTemplates((TemplatesType)null);
            return;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL:
            setModel((ModelType)null);
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
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__DOCUMENTATION:
            return documentation != null;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__TEMPLATES:
            return templates != null;
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE__MODEL:
            return model != null;
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
      result.append(" (id: "); //$NON-NLS-1$
      result.append(id);
      result.append(", name: "); //$NON-NLS-1$
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //TemplateLibraryTypeImpl
