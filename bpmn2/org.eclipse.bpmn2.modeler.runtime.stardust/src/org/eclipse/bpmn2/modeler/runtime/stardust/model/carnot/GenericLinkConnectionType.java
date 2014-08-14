/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Link Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGenericLinkConnectionType()
 * @model extendedMetaData="name='genericLinkConnection_._type' kind='empty'"
 * @generated
 */
public interface GenericLinkConnectionType extends IConnectionSymbol, ITypedElement {
	/**
	 * Returns the value of the '<em><b>Link Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLinkInstances <em>Link Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the corresponding link type.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link Type</em>' reference.
	 * @see #setLinkType(LinkTypeType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGenericLinkConnectionType_LinkType()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLinkInstances
	 * @model opposite="linkInstances" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='linkType'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	LinkTypeType getLinkType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType <em>Link Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Type</em>' reference.
	 * @see #getLinkType()
	 * @generated
	 */
	void setLinkType(LinkTypeType value);

	/**
	 * Returns the value of the '<em><b>Source Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutLinks <em>Out Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the first linked element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Symbol</em>' reference.
	 * @see #isSetSourceSymbol()
	 * @see #unsetSourceSymbol()
	 * @see #setSourceSymbol(INodeSymbol)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGenericLinkConnectionType_SourceSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getOutLinks
	 * @model opposite="outLinks" resolveProxies="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='sourceSymbol'"
	 * @generated
	 */
	INodeSymbol getSourceSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Symbol</em>' reference.
	 * @see #isSetSourceSymbol()
	 * @see #unsetSourceSymbol()
	 * @see #getSourceSymbol()
	 * @generated
	 */
	void setSourceSymbol(INodeSymbol value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSourceSymbol()
	 * @see #getSourceSymbol()
	 * @see #setSourceSymbol(INodeSymbol)
	 * @generated
	 */
	void unsetSourceSymbol();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getSourceSymbol <em>Source Symbol</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Source Symbol</em>' reference is set.
	 * @see #unsetSourceSymbol()
	 * @see #getSourceSymbol()
	 * @see #setSourceSymbol(INodeSymbol)
	 * @generated
	 */
	boolean isSetSourceSymbol();

	/**
	 * Returns the value of the '<em><b>Target Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInLinks <em>In Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the second linked element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Symbol</em>' reference.
	 * @see #isSetTargetSymbol()
	 * @see #unsetTargetSymbol()
	 * @see #setTargetSymbol(INodeSymbol)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGenericLinkConnectionType_TargetSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.INodeSymbol#getInLinks
	 * @model opposite="inLinks" resolveProxies="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='targetSymbol'"
	 * @generated
	 */
	INodeSymbol getTargetSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Symbol</em>' reference.
	 * @see #isSetTargetSymbol()
	 * @see #unsetTargetSymbol()
	 * @see #getTargetSymbol()
	 * @generated
	 */
	void setTargetSymbol(INodeSymbol value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTargetSymbol()
	 * @see #getTargetSymbol()
	 * @see #setTargetSymbol(INodeSymbol)
	 * @generated
	 */
	void unsetTargetSymbol();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getTargetSymbol <em>Target Symbol</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Target Symbol</em>' reference is set.
	 * @see #unsetTargetSymbol()
	 * @see #getTargetSymbol()
	 * @see #setTargetSymbol(INodeSymbol)
	 * @generated
	 */
	boolean isSetTargetSymbol();

} // GenericLinkConnectionType
