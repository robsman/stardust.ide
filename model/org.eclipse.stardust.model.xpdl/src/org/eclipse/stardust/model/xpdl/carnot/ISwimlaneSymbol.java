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
 * A representation of the model object '<em><b>ISwimlane Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getChildLanes <em>Child Lanes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getISwimlaneSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='compartmentSymbol_._type' kind='empty'"
 * @generated
 */
public interface ISwimlaneSymbol extends INodeSymbol, IIdentifiableElement
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Orientation</b></em>' attribute.
    * The default value is <code>"Vertical"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.OrientationType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The orientation of the compartment.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Orientation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrientationType
    * @see #isSetOrientation()
    * @see #unsetOrientation()
    * @see #setOrientation(OrientationType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getISwimlaneSymbol_Orientation()
    * @model default="Vertical" unique="false" unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='orientation'"
    * @generated
    */
   OrientationType getOrientation();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Orientation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrientationType
    * @see #isSetOrientation()
    * @see #unsetOrientation()
    * @see #getOrientation()
    * @generated
    */
   void setOrientation(OrientationType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetOrientation()
    * @see #getOrientation()
    * @see #setOrientation(OrientationType)
    * @generated
    */
   void unsetOrientation();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Orientation</em>' attribute is set.
    * @see #unsetOrientation()
    * @see #getOrientation()
    * @see #setOrientation(OrientationType)
    * @generated
    */
   boolean isSetOrientation();

   /**
    * Returns the value of the '<em><b>Collapsed</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The collapsed state of the compartment.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Collapsed</em>' attribute.
    * @see #setCollapsed(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getISwimlaneSymbol_Collapsed()
    * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='collapsed'"
    * @generated
    */
   boolean isCollapsed();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#isCollapsed <em>Collapsed</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Collapsed</em>' attribute.
    * @see #isCollapsed()
    * @generated
    */
   void setCollapsed(boolean value);

   /**
    * Returns the value of the '<em><b>Participant</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getPerformedSwimlanes <em>Performed Swimlanes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * A reference to the associated participant, if existent.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant</em>' reference.
    * @see #setParticipant(IModelParticipant)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getISwimlaneSymbol_Participant()
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getPerformedSwimlanes
    * @model opposite="performedSwimlanes" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='participant'"
    * @generated
    */
   IModelParticipant getParticipant();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant</em>' reference.
    * @see #getParticipant()
    * @generated
    */
   void setParticipant(IModelParticipant value);

   /**
    * Returns the value of the '<em><b>Child Lanes</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentLane <em>Parent Lane</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Child Lanes</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Child Lanes</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getISwimlaneSymbol_ChildLanes()
    * @see org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentLane
    * @model opposite="parentLane" resolveProxies="false" transient="true"
    * @generated
    */
   EList<LaneSymbol> getChildLanes();

} // ISwimlaneSymbol
