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
 * A representation of the model object '<em><b>Event Condition Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isActivityCondition <em>Activity Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getBinderClass <em>Binder Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isProcessCondition <em>Process Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getPullEventEmitterClass <em>Pull Event Emitter Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getRule <em>Rule</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getEventHandlers <em>Event Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType()
 * @model extendedMetaData="name='eventConditionType_._type' kind='elementOnly'"
 * @generated
 */
public interface EventConditionTypeType extends IMetaType{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Activity Condition</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether event handlers of this event condition
    *                   type are available for workflow activities.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Activity Condition</em>' attribute.
    * @see #isSetActivityCondition()
    * @see #unsetActivityCondition()
    * @see #setActivityCondition(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_ActivityCondition()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='activityCondition'"
    * @generated
    */
   boolean isActivityCondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isActivityCondition <em>Activity Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity Condition</em>' attribute.
    * @see #isSetActivityCondition()
    * @see #unsetActivityCondition()
    * @see #isActivityCondition()
    * @generated
    */
   void setActivityCondition(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isActivityCondition <em>Activity Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetActivityCondition()
    * @see #isActivityCondition()
    * @see #setActivityCondition(boolean)
    * @generated
    */
   void unsetActivityCondition();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isActivityCondition <em>Activity Condition</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Activity Condition</em>' attribute is set.
    * @see #unsetActivityCondition()
    * @see #isActivityCondition()
    * @see #setActivityCondition(boolean)
    * @generated
    */
   boolean isSetActivityCondition();

   /**
    * Returns the value of the '<em><b>Binder Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Binder Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Binder Class</em>' attribute.
    * @see #setBinderClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_BinderClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='binderClass'"
    * @generated
    */
   String getBinderClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getBinderClass <em>Binder Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Binder Class</em>' attribute.
    * @see #getBinderClass()
    * @generated
    */
   void setBinderClass(String value);

   /**
    * Returns the value of the '<em><b>Implementation</b></em>' attribute.
    * The default value is <code>"engine"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.ImplementationType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Implementation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.ImplementationType
    * @see #isSetImplementation()
    * @see #unsetImplementation()
    * @see #setImplementation(ImplementationType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_Implementation()
    * @model default="engine" unique="false" unsettable="true"
    *        extendedMetaData="kind='attribute' name='implementation'"
    * @generated
    */
   ImplementationType getImplementation();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getImplementation <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Implementation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.ImplementationType
    * @see #isSetImplementation()
    * @see #unsetImplementation()
    * @see #getImplementation()
    * @generated
    */
   void setImplementation(ImplementationType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getImplementation <em>Implementation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetImplementation()
    * @see #getImplementation()
    * @see #setImplementation(ImplementationType)
    * @generated
    */
   void unsetImplementation();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getImplementation <em>Implementation</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Implementation</em>' attribute is set.
    * @see #unsetImplementation()
    * @see #getImplementation()
    * @see #setImplementation(ImplementationType)
    * @generated
    */
   boolean isSetImplementation();

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
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_PanelClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='panelClass'"
    * @generated
    */
   String getPanelClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getPanelClass <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Panel Class</em>' attribute.
    * @see #getPanelClass()
    * @generated
    */
   void setPanelClass(String value);

   /**
    * Returns the value of the '<em><b>Process Condition</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether event handlers of this event condition
    *                   type are available for workflow processes.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Process Condition</em>' attribute.
    * @see #isSetProcessCondition()
    * @see #unsetProcessCondition()
    * @see #setProcessCondition(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_ProcessCondition()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='processCondition'"
    * @generated
    */
   boolean isProcessCondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isProcessCondition <em>Process Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Process Condition</em>' attribute.
    * @see #isSetProcessCondition()
    * @see #unsetProcessCondition()
    * @see #isProcessCondition()
    * @generated
    */
   void setProcessCondition(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isProcessCondition <em>Process Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetProcessCondition()
    * @see #isProcessCondition()
    * @see #setProcessCondition(boolean)
    * @generated
    */
   void unsetProcessCondition();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#isProcessCondition <em>Process Condition</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Process Condition</em>' attribute is set.
    * @see #unsetProcessCondition()
    * @see #isProcessCondition()
    * @see #setProcessCondition(boolean)
    * @generated
    */
   boolean isSetProcessCondition();

   /**
    * Returns the value of the '<em><b>Pull Event Emitter Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pull Event Emitter Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pull Event Emitter Class</em>' attribute.
    * @see #setPullEventEmitterClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_PullEventEmitterClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='pullEventEmitterClass'"
    * @generated
    */
   String getPullEventEmitterClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getPullEventEmitterClass <em>Pull Event Emitter Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pull Event Emitter Class</em>' attribute.
    * @see #getPullEventEmitterClass()
    * @generated
    */
   void setPullEventEmitterClass(String value);

   /**
    * Returns the value of the '<em><b>Rule</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Rule</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Rule</em>' attribute.
    * @see #setRule(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_Rule()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='rule'"
    * @generated
    */
   String getRule();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType#getRule <em>Rule</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Rule</em>' attribute.
    * @see #getRule()
    * @generated
    */
   void setRule(String value);

   /**
    * Returns the value of the '<em><b>Event Handlers</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.EventHandlerType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.EventHandlerType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Event Handlers</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Event Handlers</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getEventConditionTypeType_EventHandlers()
    * @see org.eclipse.stardust.model.xpdl.carnot.EventHandlerType#getType
    * @model opposite="type" transient="true"
    * @generated
    */
   EList<EventHandlerType> getEventHandlers();

} // EventConditionTypeType
