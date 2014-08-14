/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getAnnotationSymbolType()
 * @model extendedMetaData="name='annotationSymbol_._type' kind='elementOnly'"
 * @generated
 */
public interface AnnotationSymbolType extends INodeSymbol {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' containment reference.
	 * @see #setText(TextType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getAnnotationSymbolType_Text()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
	 * @generated
	 */
	TextType getText();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType#getText <em>Text</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' containment reference.
	 * @see #getText()
	 * @generated
	 */
	void setText(TextType value);

} // AnnotationSymbolType
