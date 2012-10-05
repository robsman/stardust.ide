/**
 * ****************************************************************************
 *  Copyright (c) 2012 ITpearls AG and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     ITpearls - initial API and implementation and/or initial documentation
 * *****************************************************************************
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Seqence Flow Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal <em>Fork On Traversal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustSeqenceFlowType()
 * @model extendedMetaData="name='StardustSeqenceFlow_._type' kind='empty'"
 * @generated
 */
public interface StardustSeqenceFlowType extends EObject {
	/**
	 * Returns the value of the '<em><b>Fork On Traversal</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fork On Traversal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fork On Traversal</em>' attribute.
	 * @see #isSetForkOnTraversal()
	 * @see #unsetForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustSeqenceFlowType_ForkOnTraversal()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='forkOnTraversal'"
	 * @generated
	 */
	boolean isForkOnTraversal();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fork On Traversal</em>' attribute.
	 * @see #isSetForkOnTraversal()
	 * @see #unsetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @generated
	 */
	void setForkOnTraversal(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @generated
	 */
	void unsetForkOnTraversal();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustSeqenceFlowType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fork On Traversal</em>' attribute is set.
	 * @see #unsetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @generated
	 */
	boolean isSetForkOnTraversal();

} // StardustSeqenceFlowType
