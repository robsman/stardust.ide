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
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Action Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getActionClass <em>Action Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isActivityAction <em>Activity Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isProcessAction <em>Process Action</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getSupportedConditionTypes <em>Supported Condition Types</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getUnsupportedContexts <em>Unsupported Contexts</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getActionInstances <em>Action Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType()
 * @model extendedMetaData="name='eventActionType_._type' kind='elementOnly'"
 * @generated
 */
public interface EventActionTypeType extends IMetaType{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Action Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Action Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Action Class</em>' attribute.
    * @see #setActionClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_ActionClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='actionClass'"
    * @generated
    */
   String getActionClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getActionClass <em>Action Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Action Class</em>' attribute.
    * @see #getActionClass()
    * @generated
    */
   void setActionClass(String value);

   /**
    * Returns the value of the '<em><b>Activity Action</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether actions of this event action type are
    *                   available for workflow activities.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Activity Action</em>' attribute.
    * @see #isSetActivityAction()
    * @see #unsetActivityAction()
    * @see #setActivityAction(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_ActivityAction()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='activityAction'"
    * @generated
    */
   boolean isActivityAction();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isActivityAction <em>Activity Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity Action</em>' attribute.
    * @see #isSetActivityAction()
    * @see #unsetActivityAction()
    * @see #isActivityAction()
    * @generated
    */
   void setActivityAction(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isActivityAction <em>Activity Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetActivityAction()
    * @see #isActivityAction()
    * @see #setActivityAction(boolean)
    * @generated
    */
   void unsetActivityAction();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isActivityAction <em>Activity Action</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Activity Action</em>' attribute is set.
    * @see #unsetActivityAction()
    * @see #isActivityAction()
    * @see #setActivityAction(boolean)
    * @generated
    */
   boolean isSetActivityAction();

   /**
    * Returns the value of the '<em><b>Panel Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Panel Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Panel Class</em>' attribute.
    * @see #setPanelClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_PanelClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='panelClass'"
    * @generated
    */
   String getPanelClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getPanelClass <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Panel Class</em>' attribute.
    * @see #getPanelClass()
    * @generated
    */
   void setPanelClass(String value);

   /**
    * Returns the value of the '<em><b>Process Action</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether actions of this event action type are
    *                   available for workflow processes.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Process Action</em>' attribute.
    * @see #isSetProcessAction()
    * @see #unsetProcessAction()
    * @see #setProcessAction(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_ProcessAction()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='processAction'"
    * @generated
    */
   boolean isProcessAction();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isProcessAction <em>Process Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Process Action</em>' attribute.
    * @see #isSetProcessAction()
    * @see #unsetProcessAction()
    * @see #isProcessAction()
    * @generated
    */
   void setProcessAction(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isProcessAction <em>Process Action</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetProcessAction()
    * @see #isProcessAction()
    * @see #setProcessAction(boolean)
    * @generated
    */
   void unsetProcessAction();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#isProcessAction <em>Process Action</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Process Action</em>' attribute is set.
    * @see #unsetProcessAction()
    * @see #isProcessAction()
    * @see #setProcessAction(boolean)
    * @generated
    */
   boolean isSetProcessAction();

   /**
    * Returns the value of the '<em><b>Supported Condition Types</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A comma separated list of model id's of supported event condition types.
    *                   Valid values are taken from previously defined eventConditionType
    *                   elements.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Supported Condition Types</em>' attribute.
    * @see #setSupportedConditionTypes(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_SupportedConditionTypes()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='supportedConditionTypes'"
    * @generated
    */
   String getSupportedConditionTypes();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getSupportedConditionTypes <em>Supported Condition Types</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Supported Condition Types</em>' attribute.
    * @see #getSupportedConditionTypes()
    * @generated
    */
   void setSupportedConditionTypes(String value);

   /**
    * Returns the value of the '<em><b>Unsupported Contexts</b></em>' attribute.
    * <!-- begin-user-doc -->

    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A comma separated list of unsupported event contexts (actions). Valid
    *                   values are "bind", "unbind" and "event".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Unsupported Contexts</em>' attribute.
    * @see #setUnsupportedContexts(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_UnsupportedContexts()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='unsupportedContexts'"
    * @generated
    */
   String getUnsupportedContexts();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getUnsupportedContexts <em>Unsupported Contexts</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Unsupported Contexts</em>' attribute.
    * @see #getUnsupportedContexts()
    * @generated
    */
   void setUnsupportedContexts(String value);

   /**
    * Returns the value of the '<em><b>Action Instances</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Action Instances</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Action Instances</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventActionTypeType_ActionInstances()
    * @see org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction#getType
    * @model opposite="type" transient="true"
    * @generated
    */
   EList<AbstractEventAction> getActionInstances();

} // EventActionTypeType
