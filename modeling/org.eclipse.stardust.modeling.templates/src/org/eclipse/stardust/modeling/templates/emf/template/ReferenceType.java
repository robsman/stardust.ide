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
package org.eclipse.stardust.modeling.templates.emf.template;



import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationType <em>Application Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationContextType <em>Application Context Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventActionType <em>Event Action Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventConditionType <em>Event Condition Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTriggerType <em>Trigger Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getConditionalPerformer <em>Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTypeDeclaration <em>Type Declaration</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType()
 * @model extendedMetaData="name='reference' kind='elementOnly'"
 * @generated
 */
public interface ReferenceType extends EObject {
	/**
    * Returns the value of the '<em><b>Application Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Application Type</em>' reference.
    * @see #setApplicationType(ApplicationTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_ApplicationType()
    * @model extendedMetaData="kind='attribute' name='applicationType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	ApplicationTypeType getApplicationType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationType <em>Application Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application Type</em>' reference.
    * @see #getApplicationType()
    * @generated
    */
	void setApplicationType(ApplicationTypeType value);

	/**
    * Returns the value of the '<em><b>Application Context Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Context Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Application Context Type</em>' reference.
    * @see #setApplicationContextType(ApplicationContextTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_ApplicationContextType()
    * @model extendedMetaData="kind='attribute' name='applicationContextType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	ApplicationContextTypeType getApplicationContextType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationContextType <em>Application Context Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application Context Type</em>' reference.
    * @see #getApplicationContextType()
    * @generated
    */
	void setApplicationContextType(ApplicationContextTypeType value);

	/**
    * Returns the value of the '<em><b>Data Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Data Type</em>' reference.
    * @see #setDataType(DataTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_DataType()
    * @model extendedMetaData="kind='attribute' name='dataType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	DataTypeType getDataType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getDataType <em>Data Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data Type</em>' reference.
    * @see #getDataType()
    * @generated
    */
	void setDataType(DataTypeType value);

	/**
    * Returns the value of the '<em><b>Event Action Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Action Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Event Action Type</em>' reference.
    * @see #setEventActionType(EventActionTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_EventActionType()
    * @model extendedMetaData="kind='attribute' name='eventActionType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	EventActionTypeType getEventActionType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventActionType <em>Event Action Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Event Action Type</em>' reference.
    * @see #getEventActionType()
    * @generated
    */
	void setEventActionType(EventActionTypeType value);

	/**
    * Returns the value of the '<em><b>Event Condition Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Condition Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Event Condition Type</em>' reference.
    * @see #setEventConditionType(EventConditionTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_EventConditionType()
    * @model extendedMetaData="kind='attribute' name='eventConditionType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	EventConditionTypeType getEventConditionType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventConditionType <em>Event Condition Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Event Condition Type</em>' reference.
    * @see #getEventConditionType()
    * @generated
    */
	void setEventConditionType(EventConditionTypeType value);

	/**
    * Returns the value of the '<em><b>Trigger Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Trigger Type</em>' reference.
    * @see #setTriggerType(TriggerTypeType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_TriggerType()
    * @model extendedMetaData="kind='attribute' name='triggerType'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	TriggerTypeType getTriggerType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTriggerType <em>Trigger Type</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Trigger Type</em>' reference.
    * @see #getTriggerType()
    * @generated
    */
	void setTriggerType(TriggerTypeType value);

	/**
    * Returns the value of the '<em><b>Application</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Application</em>' reference.
    * @see #setApplication(ApplicationType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_Application()
    * @model extendedMetaData="kind='attribute' name='application'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	ApplicationType getApplication();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplication <em>Application</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application</em>' reference.
    * @see #getApplication()
    * @generated
    */
	void setApplication(ApplicationType value);

	/**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_Data()
    * @model resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	DataType getData();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getData <em>Data</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data</em>' reference.
    * @see #getData()
    * @generated
    */
	void setData(DataType value);

	/**
    * Returns the value of the '<em><b>Conditional Performer</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conditional Performer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Conditional Performer</em>' reference.
    * @see #setConditionalPerformer(ConditionalPerformerType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_ConditionalPerformer()
    * @model extendedMetaData="kind='attribute' name='conditionalPerformer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	ConditionalPerformerType getConditionalPerformer();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getConditionalPerformer <em>Conditional Performer</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Conditional Performer</em>' reference.
    * @see #getConditionalPerformer()
    * @generated
    */
	void setConditionalPerformer(ConditionalPerformerType value);

	/**
    * Returns the value of the '<em><b>Organization</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organization</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Organization</em>' reference.
    * @see #setOrganization(OrganizationType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_Organization()
    * @model extendedMetaData="kind='attribute' name='organization'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	OrganizationType getOrganization();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getOrganization <em>Organization</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Organization</em>' reference.
    * @see #getOrganization()
    * @generated
    */
	void setOrganization(OrganizationType value);

	/**
    * Returns the value of the '<em><b>Role</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Role</em>' reference.
    * @see #setRole(RoleType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_Role()
    * @model extendedMetaData="kind='attribute' name='role'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	RoleType getRole();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getRole <em>Role</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Role</em>' reference.
    * @see #getRole()
    * @generated
    */
	void setRole(RoleType value);

	/**
    * Returns the value of the '<em><b>Process Definition</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Process Definition</em>' reference.
    * @see #setProcessDefinition(ProcessDefinitionType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_ProcessDefinition()
    * @model extendedMetaData="kind='attribute' name='process'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	ProcessDefinitionType getProcessDefinition();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getProcessDefinition <em>Process Definition</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Process Definition</em>' reference.
    * @see #getProcessDefinition()
    * @generated
    */
	void setProcessDefinition(ProcessDefinitionType value);

	/**
    * Returns the value of the '<em><b>Type Declaration</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Type Declaration</em>' reference.
    * @see #setTypeDeclaration(TypeDeclarationType)
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_TypeDeclaration()
    * @model extendedMetaData="kind='attribute' name='typeDeclaration'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
	TypeDeclarationType getTypeDeclaration();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTypeDeclaration <em>Type Declaration</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type Declaration</em>' reference.
    * @see #getTypeDeclaration()
    * @generated
    */
	void setTypeDeclaration(TypeDeclarationType value);

	/**
    * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Parameters</em>' containment reference list.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#getReferenceType_Parameters()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='parameter' namespace='##targetNamespace'"
    * @generated
    */
	EList<ParameterType> getParameters();

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
	EObject getReference();

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model
    * @generated
    */
	void setReference(EObject reference);

} // ReferenceType
