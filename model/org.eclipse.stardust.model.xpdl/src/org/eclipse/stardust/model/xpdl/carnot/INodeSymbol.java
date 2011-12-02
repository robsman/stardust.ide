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


import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>INode Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getYPos <em>YPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getShape <em>Shape</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getInLinks <em>In Links</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getOutLinks <em>Out Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='nodeSymbol_._type' kind='empty'"
 * @generated
 */
public interface INodeSymbol extends IGraphicalObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>XPos</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The x position in the diagram.
    * <!-- end-model-doc -->
    * @return the value of the '<em>XPos</em>' attribute.
    * @see #isSetXPos()
    * @see #unsetXPos()
    * @see #setXPos(long)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_XPos()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
    *        extendedMetaData="kind='attribute' name='x'"
    * @generated
    */
   long getXPos();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getXPos <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>XPos</em>' attribute.
    * @see #isSetXPos()
    * @see #unsetXPos()
    * @see #getXPos()
    * @generated
    */
   void setXPos(long value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getXPos <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetXPos()
    * @see #getXPos()
    * @see #setXPos(long)
    * @generated
    */
   void unsetXPos();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getXPos <em>XPos</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>XPos</em>' attribute is set.
    * @see #unsetXPos()
    * @see #getXPos()
    * @see #setXPos(long)
    * @generated
    */
   boolean isSetXPos();

   /**
    * Returns the value of the '<em><b>YPos</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The y position in the diagram.
    * <!-- end-model-doc -->
    * @return the value of the '<em>YPos</em>' attribute.
    * @see #isSetYPos()
    * @see #unsetYPos()
    * @see #setYPos(long)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_YPos()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
    *        extendedMetaData="kind='attribute' name='y'"
    * @generated
    */
   long getYPos();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getYPos <em>YPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>YPos</em>' attribute.
    * @see #isSetYPos()
    * @see #unsetYPos()
    * @see #getYPos()
    * @generated
    */
   void setYPos(long value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getYPos <em>YPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetYPos()
    * @see #getYPos()
    * @see #setYPos(long)
    * @generated
    */
   void unsetYPos();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getYPos <em>YPos</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>YPos</em>' attribute is set.
    * @see #unsetYPos()
    * @see #getYPos()
    * @see #setYPos(long)
    * @generated
    */
   boolean isSetYPos();

   /**
    * Returns the value of the '<em><b>Width</b></em>' attribute.
    * The default value is <code>"-1"</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The width of the node.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Width</em>' attribute.
    * @see #isSetWidth()
    * @see #unsetWidth()
    * @see #setWidth(int)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_Width()
    * @model default="-1" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
    *        extendedMetaData="kind='attribute' name='width'"
    * @generated
    */
   int getWidth();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getWidth <em>Width</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Width</em>' attribute.
    * @see #isSetWidth()
    * @see #unsetWidth()
    * @see #getWidth()
    * @generated
    */
   void setWidth(int value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getWidth <em>Width</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetWidth()
    * @see #getWidth()
    * @see #setWidth(int)
    * @generated
    */
   void unsetWidth();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getWidth <em>Width</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Width</em>' attribute is set.
    * @see #unsetWidth()
    * @see #getWidth()
    * @see #setWidth(int)
    * @generated
    */
   boolean isSetWidth();

   /**
    * Returns the value of the '<em><b>Height</b></em>' attribute.
    * The default value is <code>"-1"</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The height of the node.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Height</em>' attribute.
    * @see #isSetHeight()
    * @see #unsetHeight()
    * @see #setHeight(int)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_Height()
    * @model default="-1" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
    *        extendedMetaData="kind='attribute' name='height'"
    * @generated
    */
   int getHeight();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getHeight <em>Height</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Height</em>' attribute.
    * @see #isSetHeight()
    * @see #unsetHeight()
    * @see #getHeight()
    * @generated
    */
   void setHeight(int value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getHeight <em>Height</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetHeight()
    * @see #getHeight()
    * @see #setHeight(int)
    * @generated
    */
   void unsetHeight();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getHeight <em>Height</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Height</em>' attribute is set.
    * @see #unsetHeight()
    * @see #getHeight()
    * @see #setHeight(int)
    * @generated
    */
   boolean isSetHeight();

   /**
    * Returns the value of the '<em><b>Shape</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * A hint on the shape to be used, if applicable.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Shape</em>' attribute.
    * @see #setShape(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_Shape()
    * @model extendedMetaData="kind='attribute' name='shape'"
    * @generated
    */
   String getShape();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol#getShape <em>Shape</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Shape</em>' attribute.
    * @see #getShape()
    * @generated
    */
   void setShape(String value);

   /**
    * Returns the value of the '<em><b>In Links</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>In Links</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>In Links</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_InLinks()
    * @see org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType#getTargetSymbol
    * @model opposite="targetSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<GenericLinkConnectionType> getInLinks();

   /**
    * Returns the value of the '<em><b>Out Links</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Out Links</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Out Links</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getINodeSymbol_OutLinks()
    * @see org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType#getSourceSymbol
    * @model opposite="sourceSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<GenericLinkConnectionType> getOutLinks();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" dataType="org.eclipse.stardust.model.xpdl.carnot.FeatureList" required="true" many="false"
    * @generated
    */
   List getInConnectionFeatures();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" dataType="org.eclipse.stardust.model.xpdl.carnot.FeatureList" required="true" many="false"
    * @generated
    */
   List getOutConnectionFeatures();

} // INodeSymbol
