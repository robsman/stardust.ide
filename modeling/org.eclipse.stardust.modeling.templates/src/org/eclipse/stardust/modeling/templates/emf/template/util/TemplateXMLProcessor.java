/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.templates.emf.template.util;


import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplateXMLProcessor extends XMLProcessor {

	/**
    * Public constructor to instantiate the helper.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateXMLProcessor() {
      super((EPackage.Registry.INSTANCE));
      TemplatePackage.eINSTANCE.eClass();
   }
	
	/**
    * Register for "*" and "xml" file extensions the TemplateResourceFactoryImpl factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected Map<String, Resource.Factory> getRegistrations() {
      if (registrations == null)
      {
         super.getRegistrations();
         registrations.put(XML_EXTENSION, new TemplateResourceFactoryImpl());
         registrations.put(STAR_EXTENSION, new TemplateResourceFactoryImpl());
      }
      return registrations;
   }

} //TemplateXMLProcessor
