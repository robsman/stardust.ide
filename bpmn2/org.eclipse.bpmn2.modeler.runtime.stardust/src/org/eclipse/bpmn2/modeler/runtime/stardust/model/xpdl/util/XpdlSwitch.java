/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.util;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage
 * @generated
 */
public class XpdlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static XpdlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XpdlSwitch() {
		if (modelPackage == null) {
			modelPackage = XpdlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case XpdlPackage.BASIC_TYPE_TYPE: {
				BasicTypeType basicTypeType = (BasicTypeType)theEObject;
				T result = caseBasicTypeType(basicTypeType);
				if (result == null) result = caseXpdlTypeType(basicTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.DATA_TYPE_TYPE: {
				DataTypeType dataTypeType = (DataTypeType)theEObject;
				T result = caseDataTypeType(dataTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.DECLARED_TYPE_TYPE: {
				DeclaredTypeType declaredTypeType = (DeclaredTypeType)theEObject;
				T result = caseDeclaredTypeType(declaredTypeType);
				if (result == null) result = caseXpdlTypeType(declaredTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTENDED_ATTRIBUTES_TYPE: {
				ExtendedAttributesType extendedAttributesType = (ExtendedAttributesType)theEObject;
				T result = caseExtendedAttributesType(extendedAttributesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE: {
				ExtendedAttributeType extendedAttributeType = (ExtendedAttributeType)theEObject;
				T result = caseExtendedAttributeType(extendedAttributeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTENSIBLE: {
				Extensible extensible = (Extensible)theEObject;
				T result = caseExtensible(extensible);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTERNAL_PACKAGES: {
				ExternalPackages externalPackages = (ExternalPackages)theEObject;
				T result = caseExternalPackages(externalPackages);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTERNAL_PACKAGE: {
				ExternalPackage externalPackage = (ExternalPackage)theEObject;
				T result = caseExternalPackage(externalPackage);
				if (result == null) result = caseExtensible(externalPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.EXTERNAL_REFERENCE_TYPE: {
				ExternalReferenceType externalReferenceType = (ExternalReferenceType)theEObject;
				T result = caseExternalReferenceType(externalReferenceType);
				if (result == null) result = caseXpdlTypeType(externalReferenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.FORMAL_PARAMETERS_TYPE: {
				FormalParametersType formalParametersType = (FormalParametersType)theEObject;
				T result = caseFormalParametersType(formalParametersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.FORMAL_PARAMETER_TYPE: {
				FormalParameterType formalParameterType = (FormalParameterType)theEObject;
				T result = caseFormalParameterType(formalParameterType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.SCHEMA_TYPE_TYPE: {
				SchemaTypeType schemaTypeType = (SchemaTypeType)theEObject;
				T result = caseSchemaTypeType(schemaTypeType);
				if (result == null) result = caseXpdlTypeType(schemaTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.SCRIPT_TYPE: {
				ScriptType scriptType = (ScriptType)theEObject;
				T result = caseScriptType(scriptType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.TYPE_DECLARATIONS_TYPE: {
				TypeDeclarationsType typeDeclarationsType = (TypeDeclarationsType)theEObject;
				T result = caseTypeDeclarationsType(typeDeclarationsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.TYPE_DECLARATION_TYPE: {
				TypeDeclarationType typeDeclarationType = (TypeDeclarationType)theEObject;
				T result = caseTypeDeclarationType(typeDeclarationType);
				if (result == null) result = caseExtensible(typeDeclarationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XpdlPackage.XPDL_TYPE_TYPE: {
				XpdlTypeType xpdlTypeType = (XpdlTypeType)theEObject;
				T result = caseXpdlTypeType(xpdlTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBasicTypeType(BasicTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTypeType(DataTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Declared Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Declared Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeclaredTypeType(DeclaredTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Attributes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Attributes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendedAttributesType(ExtendedAttributesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Attribute Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Attribute Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendedAttributeType(ExtendedAttributeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensible(Extensible object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Packages</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Packages</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPackages(ExternalPackages object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPackage(ExternalPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Reference Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Reference Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalReferenceType(ExternalReferenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Parameters Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Parameters Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalParametersType(FormalParametersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Parameter Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Parameter Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalParameterType(FormalParameterType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schema Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schema Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSchemaTypeType(SchemaTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptType(ScriptType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Declarations Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Declarations Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDeclarationsType(TypeDeclarationsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Declaration Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Declaration Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDeclarationType(TypeDeclarationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXpdlTypeType(XpdlTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //XpdlSwitch
