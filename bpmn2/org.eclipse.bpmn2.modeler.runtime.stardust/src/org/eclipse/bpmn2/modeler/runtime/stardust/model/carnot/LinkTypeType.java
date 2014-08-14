/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceRole <em>Source Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceClass <em>Source Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceCardinality <em>Source Cardinality</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetRole <em>Target Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetClass <em>Target Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetCardinality <em>Target Cardinality</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceSymbol <em>Source Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetSymbol <em>Target Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames <em>Show Role Names</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName <em>Show Link Type Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLinkInstances <em>Link Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType()
 * @model extendedMetaData="name='linkType_._type' kind='empty'"
 * @generated
 */
public interface LinkTypeType extends IMetaType, IExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Source Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the role of the source model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Role</em>' attribute.
	 * @see #setSourceRole(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_SourceRole()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='source_rolename'"
	 * @generated
	 */
	String getSourceRole();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceRole <em>Source Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Role</em>' attribute.
	 * @see #getSourceRole()
	 * @generated
	 */
	void setSourceRole(String value);

	/**
	 * Returns the value of the '<em><b>Source Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The classname of the source model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Class</em>' attribute.
	 * @see #setSourceClass(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_SourceClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='source_classname'"
	 * @generated
	 */
	String getSourceClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceClass <em>Source Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Class</em>' attribute.
	 * @see #getSourceClass()
	 * @generated
	 */
	void setSourceClass(String value);

	/**
	 * Returns the value of the '<em><b>Source Cardinality</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The cardinality to the source model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Cardinality</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see #setSourceCardinality(LinkCardinality)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_SourceCardinality()
	 * @model unique="false" required="true"
	 *        extendedMetaData="kind='attribute' name='source_cardinality'"
	 * @generated
	 */
	LinkCardinality getSourceCardinality();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceCardinality <em>Source Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Cardinality</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see #getSourceCardinality()
	 * @generated
	 */
	void setSourceCardinality(LinkCardinality value);

	/**
	 * Returns the value of the '<em><b>Target Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the role of the target model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Role</em>' attribute.
	 * @see #setTargetRole(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_TargetRole()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='target_rolename'"
	 * @generated
	 */
	String getTargetRole();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetRole <em>Target Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Role</em>' attribute.
	 * @see #getTargetRole()
	 * @generated
	 */
	void setTargetRole(String value);

	/**
	 * Returns the value of the '<em><b>Target Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The classname of the target model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Class</em>' attribute.
	 * @see #setTargetClass(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_TargetClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='target_classname'"
	 * @generated
	 */
	String getTargetClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetClass <em>Target Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Class</em>' attribute.
	 * @see #getTargetClass()
	 * @generated
	 */
	void setTargetClass(String value);

	/**
	 * Returns the value of the '<em><b>Target Cardinality</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The cardinality to the target model element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Cardinality</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see #setTargetCardinality(LinkCardinality)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_TargetCardinality()
	 * @model unique="false" required="true"
	 *        extendedMetaData="kind='attribute' name='target_cardinality'"
	 * @generated
	 */
	LinkCardinality getTargetCardinality();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetCardinality <em>Target Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Cardinality</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkCardinality
	 * @see #getTargetCardinality()
	 * @generated
	 */
	void setTargetCardinality(LinkCardinality value);

	/**
	 * Returns the value of the '<em><b>Line Style</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The type of the line. Valid values are "normal line",
	 *                   "short strokes", "long strokes".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Style</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @see #setLineStyle(LinkLineStyle)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_LineStyle()
	 * @model unique="false"
	 *        extendedMetaData="kind='attribute' name='lineType'"
	 * @generated
	 */
	LinkLineStyle getLineStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineStyle <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Style</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkLineStyle
	 * @see #getLineStyle()
	 * @generated
	 */
	void setLineStyle(LinkLineStyle value);

	/**
	 * Returns the value of the '<em><b>Line Color</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   he color of the line as serialized color.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Color</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @see #setLineColor(LinkColor)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_LineColor()
	 * @model unique="false"
	 *        extendedMetaData="kind='attribute' name='lineColor'"
	 * @generated
	 */
	LinkColor getLineColor();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getLineColor <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Color</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkColor
	 * @see #getLineColor()
	 * @generated
	 */
	void setLineColor(LinkColor value);

	/**
	 * Returns the value of the '<em><b>Source Symbol</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the source model element symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Symbol</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see #setSourceSymbol(LinkEndStyle)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_SourceSymbol()
	 * @model unique="false"
	 *        extendedMetaData="kind='attribute' name='source_symbol'"
	 * @generated
	 */
	LinkEndStyle getSourceSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getSourceSymbol <em>Source Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Symbol</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see #getSourceSymbol()
	 * @generated
	 */
	void setSourceSymbol(LinkEndStyle value);

	/**
	 * Returns the value of the '<em><b>Target Symbol</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the target model element symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Symbol</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see #setTargetSymbol(LinkEndStyle)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_TargetSymbol()
	 * @model unique="false"
	 *        extendedMetaData="kind='attribute' name='target_symbol'"
	 * @generated
	 */
	LinkEndStyle getTargetSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#getTargetSymbol <em>Target Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Symbol</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkEndStyle
	 * @see #getTargetSymbol()
	 * @generated
	 */
	void setTargetSymbol(LinkEndStyle value);

	/**
	 * Returns the value of the '<em><b>Show Role Names</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Determines whether the names of the roles are to be shown.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Show Role Names</em>' attribute.
	 * @see #isSetShowRoleNames()
	 * @see #unsetShowRoleNames()
	 * @see #setShowRoleNames(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_ShowRoleNames()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='show_role_names'"
	 * @generated
	 */
	boolean isShowRoleNames();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames <em>Show Role Names</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Role Names</em>' attribute.
	 * @see #isSetShowRoleNames()
	 * @see #unsetShowRoleNames()
	 * @see #isShowRoleNames()
	 * @generated
	 */
	void setShowRoleNames(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames <em>Show Role Names</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetShowRoleNames()
	 * @see #isShowRoleNames()
	 * @see #setShowRoleNames(boolean)
	 * @generated
	 */
	void unsetShowRoleNames();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowRoleNames <em>Show Role Names</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Show Role Names</em>' attribute is set.
	 * @see #unsetShowRoleNames()
	 * @see #isShowRoleNames()
	 * @see #setShowRoleNames(boolean)
	 * @generated
	 */
	boolean isSetShowRoleNames();

	/**
	 * Returns the value of the '<em><b>Show Link Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Determines whether the name is to be shown.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Show Link Type Name</em>' attribute.
	 * @see #isSetShowLinkTypeName()
	 * @see #unsetShowLinkTypeName()
	 * @see #setShowLinkTypeName(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_ShowLinkTypeName()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='show_linktype_name'"
	 * @generated
	 */
	boolean isShowLinkTypeName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName <em>Show Link Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Link Type Name</em>' attribute.
	 * @see #isSetShowLinkTypeName()
	 * @see #unsetShowLinkTypeName()
	 * @see #isShowLinkTypeName()
	 * @generated
	 */
	void setShowLinkTypeName(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName <em>Show Link Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetShowLinkTypeName()
	 * @see #isShowLinkTypeName()
	 * @see #setShowLinkTypeName(boolean)
	 * @generated
	 */
	void unsetShowLinkTypeName();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType#isShowLinkTypeName <em>Show Link Type Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Show Link Type Name</em>' attribute is set.
	 * @see #unsetShowLinkTypeName()
	 * @see #isShowLinkTypeName()
	 * @see #setShowLinkTypeName(boolean)
	 * @generated
	 */
	boolean isSetShowLinkTypeName();

	/**
	 * Returns the value of the '<em><b>Link Instances</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Instances</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getLinkTypeType_LinkInstances()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType#getLinkType
	 * @model opposite="linkType" transient="true"
	 * @generated
	 */
	EList<GenericLinkConnectionType> getLinkInstances();

} // LinkTypeType
