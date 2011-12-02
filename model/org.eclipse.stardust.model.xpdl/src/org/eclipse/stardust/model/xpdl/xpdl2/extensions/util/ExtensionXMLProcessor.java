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
package org.eclipse.stardust.model.xpdl.xpdl2.extensions.util;


import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionXMLProcessor extends XMLProcessor {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";


	/**
    * Public constructor to instantiate the helper.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExtensionXMLProcessor() {
      super((EPackage.Registry.INSTANCE));
      ExtensionPackage.eINSTANCE.eClass();
   }
	
	/**
    * Register for "*" and "xml" file extensions the ExtensionResourceFactoryImpl factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected Map<String, Resource.Factory> getRegistrations() {
      if (registrations == null)
      {
         super.getRegistrations();
         registrations.put(XML_EXTENSION, new ExtensionResourceFactoryImpl());
         registrations.put(STAR_EXTENSION, new ExtensionResourceFactoryImpl());
      }
      return registrations;
   }

} //ExtensionXMLProcessor
