/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getDataMapping <em>Data Mapping</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation <em>Hibernate On Creation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess <em>Implementation Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin <em>Join</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType <em>Loop Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getQualityControlPerformer <em>Quality Control Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit <em>Split</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode <em>Sub Process Mode</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getActivitySymbols <em>Activity Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getStartingEventSymbols <em>Starting Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getInTransitions <em>In Transitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getOutTransitions <em>Out Transitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getExternalRef <em>External Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getValidQualityCodes <em>Valid Quality Codes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType()
 * @model extendedMetaData="name='activity_._type' kind='elementOnly'"
 * @generated
 */
public interface ActivityType extends IIdentifiableModelElement, IEventHandlerOwner {
	/**
	 * Returns the value of the '<em><b>Data Mapping</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of data mappings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Mapping</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_DataMapping()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataMapping' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataMappingType> getDataMapping();

	/**
	 * Returns the value of the '<em><b>Allows Abort By Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Determines whether the activity is allowed to be aborted.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allows Abort By Performer</em>' attribute.
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_AllowsAbortByPerformer()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='allowsAbortByPerformer'"
	 * @generated
	 */
	boolean isAllowsAbortByPerformer();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allows Abort By Performer</em>' attribute.
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @generated
	 */
	void setAllowsAbortByPerformer(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @generated
	 */
	void unsetAllowsAbortByPerformer();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Allows Abort By Performer</em>' attribute is set.
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @generated
	 */
	boolean isSetAllowsAbortByPerformer();

	/**
	 * Returns the value of the '<em><b>Application</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getExecutedActivities <em>Executed Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the application to execute when the attribute
	 *                   "implementation" is set to "Application".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application</em>' reference.
	 * @see #setApplication(ApplicationType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_Application()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getExecutedActivities
	 * @model opposite="executedActivities" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='application'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	ApplicationType getApplication();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getApplication <em>Application</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' reference.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(ApplicationType value);

	/**
	 * Returns the value of the '<em><b>Hibernate On Creation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Determines whether an activity instance is hibernated immediately after
	 *                   beeing created.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hibernate On Creation</em>' attribute.
	 * @see #isSetHibernateOnCreation()
	 * @see #unsetHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_HibernateOnCreation()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='hibernateOnCreation'"
	 * @generated
	 */
	boolean isHibernateOnCreation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHibernateOnCreation()
	 * @see #isHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @generated
	 */
	void unsetHibernateOnCreation();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#isHibernateOnCreation <em>Hibernate On Creation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Hibernate On Creation</em>' attribute is set.
	 * @see #unsetHibernateOnCreation()
	 * @see #isHibernateOnCreation()
	 * @see #setHibernateOnCreation(boolean)
	 * @generated
	 */
	boolean isSetHibernateOnCreation();

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Description of how the activity is implemented. Valid values are
	 *  "Application", "Subprocess", "Route" or
	 *  "Manual".
	 *  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @see #setImplementation(ActivityImplementationType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_Implementation()
	 * @model unique="false"
	 *        extendedMetaData="kind='attribute' name='implementation'"
	 * @generated
	 */
	ActivityImplementationType getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityImplementationType
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(ActivityImplementationType value);

	/**
	 * Returns the value of the '<em><b>Implementation Process</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExecutingActivities <em>Executing Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the subprocess implementing the activity when the
	 *                   attribute "implementation" is set to "Subprocess".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implementation Process</em>' reference.
	 * @see #setImplementationProcess(ProcessDefinitionType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_ImplementationProcess()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExecutingActivities
	 * @model opposite="executingActivities" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='implementationProcess'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	ProcessDefinitionType getImplementationProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess <em>Implementation Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Process</em>' reference.
	 * @see #getImplementationProcess()
	 * @generated
	 */
	void setImplementationProcess(ProcessDefinitionType value);

	/**
	 * Returns the value of the '<em><b>Join</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The type of activity join. Valid values are: "None",
	 *                   "XOR" or "AND".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Join</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see #isSetJoin()
	 * @see #unsetJoin()
	 * @see #setJoin(JoinSplitType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_Join()
	 * @model default="None" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='join'"
	 * @generated
	 */
	JoinSplitType getJoin();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin <em>Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see #isSetJoin()
	 * @see #unsetJoin()
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinSplitType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin <em>Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetJoin()
	 * @see #getJoin()
	 * @see #setJoin(JoinSplitType)
	 * @generated
	 */
	void unsetJoin();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getJoin <em>Join</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Join</em>' attribute is set.
	 * @see #unsetJoin()
	 * @see #getJoin()
	 * @see #setJoin(JoinSplitType)
	 * @generated
	 */
	boolean isSetJoin();

	/**
	 * Returns the value of the '<em><b>Loop Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Logical condition for the loop when attribute loopType is
	 *                   "WHILE" or "REPEAT".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Loop Condition</em>' attribute.
	 * @see #setLoopCondition(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_LoopCondition()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='loopCondition'"
	 * @generated
	 */
	String getLoopCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopCondition <em>Loop Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Condition</em>' attribute.
	 * @see #getLoopCondition()
	 * @generated
	 */
	void setLoopCondition(String value);

	/**
	 * Returns the value of the '<em><b>Loop Type</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Description of whether the activity shall be executed in a loop. Valid
	 *                   values are "NO LOOP", "WHILE" or "REPEAT".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Loop Type</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @see #isSetLoopType()
	 * @see #unsetLoopType()
	 * @see #setLoopType(LoopType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_LoopType()
	 * @model default="None" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='loopType'"
	 * @generated
	 */
	LoopType getLoopType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType <em>Loop Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Type</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LoopType
	 * @see #isSetLoopType()
	 * @see #unsetLoopType()
	 * @see #getLoopType()
	 * @generated
	 */
	void setLoopType(LoopType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType <em>Loop Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLoopType()
	 * @see #getLoopType()
	 * @see #setLoopType(LoopType)
	 * @generated
	 */
	void unsetLoopType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getLoopType <em>Loop Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Loop Type</em>' attribute is set.
	 * @see #unsetLoopType()
	 * @see #getLoopType()
	 * @see #setLoopType(LoopType)
	 * @generated
	 */
	boolean isSetLoopType();

	/**
	 * Returns the value of the '<em><b>Performer</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedActivities <em>Performed Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of a participant (role, organization or conditional
	 *                   performer) who is assigned to the activity.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Performer</em>' reference.
	 * @see #setPerformer(IModelParticipant)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_Performer()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedActivities
	 * @model opposite="performedActivities" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='performer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	IModelParticipant getPerformer();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer <em>Performer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performer</em>' reference.
	 * @see #getPerformer()
	 * @generated
	 */
	void setPerformer(IModelParticipant value);

	/**
	 * Returns the value of the '<em><b>Quality Control Performer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of a participant (role, organization or conditional
	 *                   performer) who is assigned to the activity.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Quality Control Performer</em>' reference.
	 * @see #setQualityControlPerformer(IModelParticipant)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_QualityControlPerformer()
	 * @model resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='qualityControlPerformer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	IModelParticipant getQualityControlPerformer();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getQualityControlPerformer <em>Quality Control Performer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Control Performer</em>' reference.
	 * @see #getQualityControlPerformer()
	 * @generated
	 */
	void setQualityControlPerformer(IModelParticipant value);

	/**
	 * Returns the value of the '<em><b>Split</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The type of activity split. Valid values are: "None",
	 *                   "XOR" or "AND".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Split</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see #isSetSplit()
	 * @see #unsetSplit()
	 * @see #setSplit(JoinSplitType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_Split()
	 * @model default="None" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='split'"
	 * @generated
	 */
	JoinSplitType getSplit();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit <em>Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.JoinSplitType
	 * @see #isSetSplit()
	 * @see #unsetSplit()
	 * @see #getSplit()
	 * @generated
	 */
	void setSplit(JoinSplitType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit <em>Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSplit()
	 * @see #getSplit()
	 * @see #setSplit(JoinSplitType)
	 * @generated
	 */
	void unsetSplit();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSplit <em>Split</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Split</em>' attribute is set.
	 * @see #unsetSplit()
	 * @see #getSplit()
	 * @see #setSplit(JoinSplitType)
	 * @generated
	 */
	boolean isSetSplit();

	/**
	 * Returns the value of the '<em><b>Sub Process Mode</b></em>' attribute.
	 * The default value is <code>"Synchronous / Shared Data"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The execution mode of the subprocess. "sync_shared" executes
	 *                   the process synchronously (the default mode).
	 *                   "asynch_separate" just triggers a fully independent instance
	 *                   of the implementing process. This attribute is valid only when the
	 *                   attribute "implementation" is set to "Subprocess".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub Process Mode</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @see #isSetSubProcessMode()
	 * @see #unsetSubProcessMode()
	 * @see #setSubProcessMode(SubProcessModeType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_SubProcessMode()
	 * @model default="Synchronous / Shared Data" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='subProcessMode'"
	 * @generated
	 */
	SubProcessModeType getSubProcessMode();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode <em>Sub Process Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Process Mode</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessModeType
	 * @see #isSetSubProcessMode()
	 * @see #unsetSubProcessMode()
	 * @see #getSubProcessMode()
	 * @generated
	 */
	void setSubProcessMode(SubProcessModeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode <em>Sub Process Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSubProcessMode()
	 * @see #getSubProcessMode()
	 * @see #setSubProcessMode(SubProcessModeType)
	 * @generated
	 */
	void unsetSubProcessMode();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getSubProcessMode <em>Sub Process Mode</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sub Process Mode</em>' attribute is set.
	 * @see #unsetSubProcessMode()
	 * @see #getSubProcessMode()
	 * @see #setSubProcessMode(SubProcessModeType)
	 * @generated
	 */
	boolean isSetSubProcessMode();

	/**
	 * Returns the value of the '<em><b>Activity Symbols</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Symbols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Symbols</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_ActivitySymbols()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getActivity
	 * @model opposite="activity" transient="true"
	 * @generated
	 */
	EList<ActivitySymbolType> getActivitySymbols();

	/**
	 * Returns the value of the '<em><b>Starting Event Symbols</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting Event Symbols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting Event Symbols</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_StartingEventSymbols()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity
	 * @model opposite="startActivity" transient="true"
	 * @generated
	 */
	EList<StartEventSymbol> getStartingEventSymbols();

	/**
	 * Returns the value of the '<em><b>In Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Transitions</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_InTransitions()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo
	 * @model opposite="to" transient="true"
	 * @generated
	 */
	EList<TransitionType> getInTransitions();

	/**
	 * Returns the value of the '<em><b>Out Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Transitions</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_OutTransitions()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom
	 * @model opposite="from" transient="true"
	 * @generated
	 */
	EList<TransitionType> getOutTransitions();

	/**
	 * Returns the value of the '<em><b>External Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Ref</em>' containment reference.
	 * @see #setExternalRef(IdRef)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_ExternalRef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='externalReference' namespace='##targetNamespace'"
	 * @generated
	 */
	IdRef getExternalRef();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getExternalRef <em>External Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Ref</em>' containment reference.
	 * @see #getExternalRef()
	 * @generated
	 */
	void setExternalRef(IdRef value);

	/**
	 * Returns the value of the '<em><b>Valid Quality Codes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.Code}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Determines whether an activity instance is hibernated immediately after
	 *                   beeing created.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Valid Quality Codes</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getActivityType_ValidQualityCodes()
	 * @model extendedMetaData="kind='element' name='validQualityCodes' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Code> getValidQualityCodes();

} // ActivityType
