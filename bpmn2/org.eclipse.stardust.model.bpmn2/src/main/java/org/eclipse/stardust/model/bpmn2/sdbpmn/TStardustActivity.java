/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.common.util.EList;

import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStardust Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#getEventHandler <em>Event Handler</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation <em>Hibernate On Creation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getTStardustActivity()
 * @model extendedMetaData="name='tStardustActivity' kind='elementOnly'"
 * @generated
 */
public interface TStardustActivity extends TStardustCommon {
	/**
	 * Returns the value of the '<em><b>Event Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.EventHandlerType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of event handlers defined for this model element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Handler</em>' containment reference list.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getTStardustActivity_EventHandler()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='eventHandler' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EventHandlerType> getEventHandler();

	/**
	 * Returns the value of the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 							Determines whether an activity instance is hibernated immediately after beeing created.
	 *                			
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hibernate On Creation</em>' attribute.
	 * @see #isSetHibernateOnCreation()
	 * @see #unsetHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getTStardustActivity_HibernateOnCreation()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='hibernateOnCreation'"
	 * @generated
	 */
	boolean isHibernateOnCreation();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hibernate On Creation</em>' attribute.
	 * @see #isSetHibernateOnCreation()
	 * @see #unsetHibernateOnCreation()
	 * @see #isHibernateOnCreation()
	 * @generated
	 */
	void setHibernateOnCreation(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHibernateOnCreation()
	 * @see #isHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @generated
	 */
	void unsetHibernateOnCreation();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustActivity#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Hibernate On Creation</em>' attribute is set.
	 * @see #unsetHibernateOnCreation()
	 * @see #isHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @generated
	 */
	boolean isSetHibernateOnCreation();

} // TStardustActivity
