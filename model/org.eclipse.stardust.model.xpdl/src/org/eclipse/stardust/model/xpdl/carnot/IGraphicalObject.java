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
 * A representation of the model object '<em><b>IGraphical Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getBorderColor <em>Border Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getStyle <em>Style</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingToConnections <em>Refering To Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingFromConnections <em>Refering From Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='graphicalObject_._type' kind='empty'"
 * @generated
 */
public interface IGraphicalObject extends IModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Border Color</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The border color, if applicable.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Border Color</em>' attribute.
    * @see #setBorderColor(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject_BorderColor()
    * @model extendedMetaData="kind='attribute' name='borderColor'"
    * @generated
    */
   String getBorderColor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getBorderColor <em>Border Color</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Border Color</em>' attribute.
    * @see #getBorderColor()
    * @generated
    */
   void setBorderColor(String value);

   /**
    * Returns the value of the '<em><b>Fill Color</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The fill color, if applicable.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Fill Color</em>' attribute.
    * @see #setFillColor(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject_FillColor()
    * @model extendedMetaData="kind='attribute' name='fillColor'"
    * @generated
    */
   String getFillColor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getFillColor <em>Fill Color</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Fill Color</em>' attribute.
    * @see #getFillColor()
    * @generated
    */
   void setFillColor(String value);

   /**
    * Returns the value of the '<em><b>Style</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The drawing style, if applicable.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Style</em>' attribute.
    * @see #setStyle(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject_Style()
    * @model extendedMetaData="kind='attribute' name='style'"
    * @generated
    */
   String getStyle();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getStyle <em>Style</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Style</em>' attribute.
    * @see #getStyle()
    * @generated
    */
   void setStyle(String value);

   /**
    * Returns the value of the '<em><b>Refering To Connections</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo <em>To</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Refering To Connections</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Refering To Connections</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject_ReferingToConnections()
    * @see org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo
    * @model opposite="to" resolveProxies="false" transient="true"
    * @generated
    */
   EList<RefersToConnectionType> getReferingToConnections();

   /**
    * Returns the value of the '<em><b>Refering From Connections</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom <em>From</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Refering From Connections</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Refering From Connections</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIGraphicalObject_ReferingFromConnections()
    * @see org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom
    * @model opposite="from" resolveProxies="false" transient="true"
    * @generated
    */
   EList<RefersToConnectionType> getReferingFromConnections();

} // IGraphicalObject
