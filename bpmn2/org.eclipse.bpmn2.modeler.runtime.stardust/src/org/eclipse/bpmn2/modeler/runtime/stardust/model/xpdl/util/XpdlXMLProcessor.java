/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.util;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XpdlXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XpdlXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		XpdlPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the XpdlResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new XpdlResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new XpdlResourceFactoryImpl());
		}
		return registrations;
	}

} //XpdlXMLProcessor
