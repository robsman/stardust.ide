/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SdbpmnXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SdbpmnXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		SdbpmnPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the SdbpmnResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new SdbpmnResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new SdbpmnResourceFactoryImpl());
		}
		return registrations;
	}

} //SdbpmnXMLProcessor
