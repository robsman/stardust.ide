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




import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.templates.emf.template.ParameterType;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getApplicationType <em>Application Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getApplicationContextType <em>Application Context Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getEventActionType <em>Event Action Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getEventConditionType <em>Event Condition Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getTriggerType <em>Trigger Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getConditionalPerformer <em>Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getTypeDeclaration <em>Type Declaration</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceTypeImpl extends EObjectImpl implements ReferenceType {
	/**
    * The cached value of the '{@link #getApplicationType() <em>Application Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getApplicationType()
    * @generated
    * @ordered
    */
	protected ApplicationTypeType applicationType;

	/**
    * The cached value of the '{@link #getApplicationContextType() <em>Application Context Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getApplicationContextType()
    * @generated
    * @ordered
    */
	protected ApplicationContextTypeType applicationContextType;

	/**
    * The cached value of the '{@link #getDataType() <em>Data Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getDataType()
    * @generated
    * @ordered
    */
	protected DataTypeType dataType;

	/**
    * The cached value of the '{@link #getEventActionType() <em>Event Action Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getEventActionType()
    * @generated
    * @ordered
    */
	protected EventActionTypeType eventActionType;

	/**
    * The cached value of the '{@link #getEventConditionType() <em>Event Condition Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getEventConditionType()
    * @generated
    * @ordered
    */
	protected EventConditionTypeType eventConditionType;

	/**
    * The cached value of the '{@link #getTriggerType() <em>Trigger Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getTriggerType()
    * @generated
    * @ordered
    */
	protected TriggerTypeType triggerType;

	/**
    * The cached value of the '{@link #getApplication() <em>Application</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getApplication()
    * @generated
    * @ordered
    */
	protected ApplicationType application;

	/**
    * The cached value of the '{@link #getData() <em>Data</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getData()
    * @generated
    * @ordered
    */
	protected DataType data;

	/**
    * The cached value of the '{@link #getConditionalPerformer() <em>Conditional Performer</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getConditionalPerformer()
    * @generated
    * @ordered
    */
	protected ConditionalPerformerType conditionalPerformer;

	/**
    * The cached value of the '{@link #getOrganization() <em>Organization</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getOrganization()
    * @generated
    * @ordered
    */
	protected OrganizationType organization;

	/**
    * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getRole()
    * @generated
    * @ordered
    */
	protected RoleType role;

	/**
    * The cached value of the '{@link #getProcessDefinition() <em>Process Definition</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getProcessDefinition()
    * @generated
    * @ordered
    */
	protected ProcessDefinitionType processDefinition;

	/**
    * The cached value of the '{@link #getTypeDeclaration() <em>Type Declaration</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getTypeDeclaration()
    * @generated
    * @ordered
    */
	protected TypeDeclarationType typeDeclaration;

	/**
    * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getParameters()
    * @generated
    * @ordered
    */
	protected EList<ParameterType> parameters;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected ReferenceTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.REFERENCE_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationTypeType getApplicationType() {
      if (applicationType != null && applicationType.eIsProxy())
      {
         InternalEObject oldApplicationType = (InternalEObject)applicationType;
         applicationType = (ApplicationTypeType)eResolveProxy(oldApplicationType);
         if (applicationType != oldApplicationType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE, oldApplicationType, applicationType));
         }
      }
      return applicationType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationTypeType basicGetApplicationType() {
      return applicationType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setApplicationType(ApplicationTypeType newApplicationType) {
      ApplicationTypeType oldApplicationType = applicationType;
      applicationType = newApplicationType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE, oldApplicationType, applicationType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationContextTypeType getApplicationContextType() {
      if (applicationContextType != null && applicationContextType.eIsProxy())
      {
         InternalEObject oldApplicationContextType = (InternalEObject)applicationContextType;
         applicationContextType = (ApplicationContextTypeType)eResolveProxy(oldApplicationContextType);
         if (applicationContextType != oldApplicationContextType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE, oldApplicationContextType, applicationContextType));
         }
      }
      return applicationContextType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationContextTypeType basicGetApplicationContextType() {
      return applicationContextType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setApplicationContextType(ApplicationContextTypeType newApplicationContextType) {
      ApplicationContextTypeType oldApplicationContextType = applicationContextType;
      applicationContextType = newApplicationContextType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE, oldApplicationContextType, applicationContextType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DataTypeType getDataType() {
      if (dataType != null && dataType.eIsProxy())
      {
         InternalEObject oldDataType = (InternalEObject)dataType;
         dataType = (DataTypeType)eResolveProxy(oldDataType);
         if (dataType != oldDataType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__DATA_TYPE, oldDataType, dataType));
         }
      }
      return dataType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DataTypeType basicGetDataType() {
      return dataType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setDataType(DataTypeType newDataType) {
      DataTypeType oldDataType = dataType;
      dataType = newDataType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__DATA_TYPE, oldDataType, dataType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EventActionTypeType getEventActionType() {
      if (eventActionType != null && eventActionType.eIsProxy())
      {
         InternalEObject oldEventActionType = (InternalEObject)eventActionType;
         eventActionType = (EventActionTypeType)eResolveProxy(oldEventActionType);
         if (eventActionType != oldEventActionType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE, oldEventActionType, eventActionType));
         }
      }
      return eventActionType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EventActionTypeType basicGetEventActionType() {
      return eventActionType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setEventActionType(EventActionTypeType newEventActionType) {
      EventActionTypeType oldEventActionType = eventActionType;
      eventActionType = newEventActionType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE, oldEventActionType, eventActionType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EventConditionTypeType getEventConditionType() {
      if (eventConditionType != null && eventConditionType.eIsProxy())
      {
         InternalEObject oldEventConditionType = (InternalEObject)eventConditionType;
         eventConditionType = (EventConditionTypeType)eResolveProxy(oldEventConditionType);
         if (eventConditionType != oldEventConditionType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE, oldEventConditionType, eventConditionType));
         }
      }
      return eventConditionType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EventConditionTypeType basicGetEventConditionType() {
      return eventConditionType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setEventConditionType(EventConditionTypeType newEventConditionType) {
      EventConditionTypeType oldEventConditionType = eventConditionType;
      eventConditionType = newEventConditionType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE, oldEventConditionType, eventConditionType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TriggerTypeType getTriggerType() {
      if (triggerType != null && triggerType.eIsProxy())
      {
         InternalEObject oldTriggerType = (InternalEObject)triggerType;
         triggerType = (TriggerTypeType)eResolveProxy(oldTriggerType);
         if (triggerType != oldTriggerType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE, oldTriggerType, triggerType));
         }
      }
      return triggerType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TriggerTypeType basicGetTriggerType() {
      return triggerType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setTriggerType(TriggerTypeType newTriggerType) {
      TriggerTypeType oldTriggerType = triggerType;
      triggerType = newTriggerType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE, oldTriggerType, triggerType));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationType getApplication() {
      if (application != null && application.eIsProxy())
      {
         InternalEObject oldApplication = (InternalEObject)application;
         application = (ApplicationType)eResolveProxy(oldApplication);
         if (application != oldApplication)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__APPLICATION, oldApplication, application));
         }
      }
      return application;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ApplicationType basicGetApplication() {
      return application;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setApplication(ApplicationType newApplication) {
      ApplicationType oldApplication = application;
      application = newApplication;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__APPLICATION, oldApplication, application));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DataType getData() {
      return data;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setData(DataType newData) {
      DataType oldData = data;
      data = newData;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__DATA, oldData, data));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ConditionalPerformerType getConditionalPerformer() {
      if (conditionalPerformer != null && conditionalPerformer.eIsProxy())
      {
         InternalEObject oldConditionalPerformer = (InternalEObject)conditionalPerformer;
         conditionalPerformer = (ConditionalPerformerType)eResolveProxy(oldConditionalPerformer);
         if (conditionalPerformer != oldConditionalPerformer)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER, oldConditionalPerformer, conditionalPerformer));
         }
      }
      return conditionalPerformer;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ConditionalPerformerType basicGetConditionalPerformer() {
      return conditionalPerformer;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setConditionalPerformer(ConditionalPerformerType newConditionalPerformer) {
      ConditionalPerformerType oldConditionalPerformer = conditionalPerformer;
      conditionalPerformer = newConditionalPerformer;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER, oldConditionalPerformer, conditionalPerformer));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public OrganizationType getOrganization() {
      if (organization != null && organization.eIsProxy())
      {
         InternalEObject oldOrganization = (InternalEObject)organization;
         organization = (OrganizationType)eResolveProxy(oldOrganization);
         if (organization != oldOrganization)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__ORGANIZATION, oldOrganization, organization));
         }
      }
      return organization;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public OrganizationType basicGetOrganization() {
      return organization;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setOrganization(OrganizationType newOrganization) {
      OrganizationType oldOrganization = organization;
      organization = newOrganization;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__ORGANIZATION, oldOrganization, organization));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public RoleType getRole() {
      if (role != null && role.eIsProxy())
      {
         InternalEObject oldRole = (InternalEObject)role;
         role = (RoleType)eResolveProxy(oldRole);
         if (role != oldRole)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__ROLE, oldRole, role));
         }
      }
      return role;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public RoleType basicGetRole() {
      return role;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setRole(RoleType newRole) {
      RoleType oldRole = role;
      role = newRole;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__ROLE, oldRole, role));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ProcessDefinitionType getProcessDefinition() {
      if (processDefinition != null && processDefinition.eIsProxy())
      {
         InternalEObject oldProcessDefinition = (InternalEObject)processDefinition;
         processDefinition = (ProcessDefinitionType)eResolveProxy(oldProcessDefinition);
         if (processDefinition != oldProcessDefinition)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION, oldProcessDefinition, processDefinition));
         }
      }
      return processDefinition;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ProcessDefinitionType basicGetProcessDefinition() {
      return processDefinition;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setProcessDefinition(ProcessDefinitionType newProcessDefinition) {
      ProcessDefinitionType oldProcessDefinition = processDefinition;
      processDefinition = newProcessDefinition;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION, oldProcessDefinition, processDefinition));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TypeDeclarationType getTypeDeclaration() {
      if (typeDeclaration != null && typeDeclaration.eIsProxy())
      {
         InternalEObject oldTypeDeclaration = (InternalEObject)typeDeclaration;
         typeDeclaration = (TypeDeclarationType)eResolveProxy(oldTypeDeclaration);
         if (typeDeclaration != oldTypeDeclaration)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION, oldTypeDeclaration, typeDeclaration));
         }
      }
      return typeDeclaration;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TypeDeclarationType basicGetTypeDeclaration() {
      return typeDeclaration;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setTypeDeclaration(TypeDeclarationType newTypeDeclaration) {
      TypeDeclarationType oldTypeDeclaration = typeDeclaration;
      typeDeclaration = newTypeDeclaration;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION, oldTypeDeclaration, typeDeclaration));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EList<ParameterType> getParameters() {
      if (parameters == null)
      {
         parameters = new EObjectContainmentEList<ParameterType>(ParameterType.class, this, TemplatePackage.REFERENCE_TYPE__PARAMETERS);
      }
      return parameters;
   }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EObject getReference() {
	    if (applicationType != null)
	    {
	       return applicationType;
	    }
        if (applicationContextType != null)
        {
           return applicationContextType;
        }
        if (dataType != null)
        {
           return dataType;
        }
        if (eventActionType != null)
        {
           return eventActionType;
        }
        if (eventConditionType != null)
        {
           return eventConditionType;
        }
        if (triggerType != null)
        {
           return triggerType;
        }
        if (application != null)
        {
           return application;
        }
        if (data != null)
        {
           return data;
        }
        if (conditionalPerformer != null)
        {
           return conditionalPerformer;
        }
        if (organization != null)
        {
           return organization;
        }
        if (role != null)
        {
           return role;
        }
        if (processDefinition != null)
        {
           return processDefinition;
        }
        if (typeDeclaration != null)
        {
           return typeDeclaration;
        }
        return null;
	}

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setReference(EObject reference) {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
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
         case TemplatePackage.REFERENCE_TYPE__PARAMETERS:
            return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE:
            if (resolve) return getApplicationType();
            return basicGetApplicationType();
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE:
            if (resolve) return getApplicationContextType();
            return basicGetApplicationContextType();
         case TemplatePackage.REFERENCE_TYPE__DATA_TYPE:
            if (resolve) return getDataType();
            return basicGetDataType();
         case TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE:
            if (resolve) return getEventActionType();
            return basicGetEventActionType();
         case TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE:
            if (resolve) return getEventConditionType();
            return basicGetEventConditionType();
         case TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE:
            if (resolve) return getTriggerType();
            return basicGetTriggerType();
         case TemplatePackage.REFERENCE_TYPE__APPLICATION:
            if (resolve) return getApplication();
            return basicGetApplication();
         case TemplatePackage.REFERENCE_TYPE__DATA:
            return getData();
         case TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER:
            if (resolve) return getConditionalPerformer();
            return basicGetConditionalPerformer();
         case TemplatePackage.REFERENCE_TYPE__ORGANIZATION:
            if (resolve) return getOrganization();
            return basicGetOrganization();
         case TemplatePackage.REFERENCE_TYPE__ROLE:
            if (resolve) return getRole();
            return basicGetRole();
         case TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION:
            if (resolve) return getProcessDefinition();
            return basicGetProcessDefinition();
         case TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION:
            if (resolve) return getTypeDeclaration();
            return basicGetTypeDeclaration();
         case TemplatePackage.REFERENCE_TYPE__PARAMETERS:
            return getParameters();
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
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE:
            setApplicationType((ApplicationTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE:
            setApplicationContextType((ApplicationContextTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__DATA_TYPE:
            setDataType((DataTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE:
            setEventActionType((EventActionTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE:
            setEventConditionType((EventConditionTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE:
            setTriggerType((TriggerTypeType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION:
            setApplication((ApplicationType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__DATA:
            setData((DataType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER:
            setConditionalPerformer((ConditionalPerformerType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__ORGANIZATION:
            setOrganization((OrganizationType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__ROLE:
            setRole((RoleType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION:
            setProcessDefinition((ProcessDefinitionType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION:
            setTypeDeclaration((TypeDeclarationType)newValue);
            return;
         case TemplatePackage.REFERENCE_TYPE__PARAMETERS:
            getParameters().clear();
            getParameters().addAll((Collection<? extends ParameterType>)newValue);
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
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE:
            setApplicationType((ApplicationTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE:
            setApplicationContextType((ApplicationContextTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__DATA_TYPE:
            setDataType((DataTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE:
            setEventActionType((EventActionTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE:
            setEventConditionType((EventConditionTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE:
            setTriggerType((TriggerTypeType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION:
            setApplication((ApplicationType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__DATA:
            setData((DataType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER:
            setConditionalPerformer((ConditionalPerformerType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__ORGANIZATION:
            setOrganization((OrganizationType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__ROLE:
            setRole((RoleType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION:
            setProcessDefinition((ProcessDefinitionType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION:
            setTypeDeclaration((TypeDeclarationType)null);
            return;
         case TemplatePackage.REFERENCE_TYPE__PARAMETERS:
            getParameters().clear();
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
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_TYPE:
            return applicationType != null;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE:
            return applicationContextType != null;
         case TemplatePackage.REFERENCE_TYPE__DATA_TYPE:
            return dataType != null;
         case TemplatePackage.REFERENCE_TYPE__EVENT_ACTION_TYPE:
            return eventActionType != null;
         case TemplatePackage.REFERENCE_TYPE__EVENT_CONDITION_TYPE:
            return eventConditionType != null;
         case TemplatePackage.REFERENCE_TYPE__TRIGGER_TYPE:
            return triggerType != null;
         case TemplatePackage.REFERENCE_TYPE__APPLICATION:
            return application != null;
         case TemplatePackage.REFERENCE_TYPE__DATA:
            return data != null;
         case TemplatePackage.REFERENCE_TYPE__CONDITIONAL_PERFORMER:
            return conditionalPerformer != null;
         case TemplatePackage.REFERENCE_TYPE__ORGANIZATION:
            return organization != null;
         case TemplatePackage.REFERENCE_TYPE__ROLE:
            return role != null;
         case TemplatePackage.REFERENCE_TYPE__PROCESS_DEFINITION:
            return processDefinition != null;
         case TemplatePackage.REFERENCE_TYPE__TYPE_DECLARATION:
            return typeDeclaration != null;
         case TemplatePackage.REFERENCE_TYPE__PARAMETERS:
            return parameters != null && !parameters.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //ReferenceTypeImpl
