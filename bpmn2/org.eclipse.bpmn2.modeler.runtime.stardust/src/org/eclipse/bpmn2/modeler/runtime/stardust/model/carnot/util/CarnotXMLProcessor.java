/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.util;

import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CarnotXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CarnotXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		CarnotPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the CarnotResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new CarnotResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new CarnotResourceFactoryImpl());
		}
		return registrations;
	}

} //CarnotXMLProcessor
