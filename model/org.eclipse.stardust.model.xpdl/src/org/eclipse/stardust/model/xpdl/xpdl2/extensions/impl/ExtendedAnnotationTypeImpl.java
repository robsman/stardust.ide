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
package org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDAnnotationImpl;
import org.w3c.dom.Element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Annotation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ExtendedAnnotationTypeImpl extends XSDAnnotationImpl implements ExtendedAnnotationType {
   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
   private XSDSchema hiddenSchema = null;

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   protected ExtendedAnnotationTypeImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return ExtensionPackage.Literals.EXTENDED_ANNOTATION_TYPE;
   }

    /**
     * <!-- begin-user-doc -->
     * Overwritten to allow creation/updating of elements
     * <!-- end-user-doc -->
     * @generated NOT
     */
   public XSDSchema getSchema()
   {
      if (hiddenSchema == null)
      {
         hiddenSchema = XSDFactory.eINSTANCE.createXSDSchema();
         hiddenSchema.getQNamePrefixToNamespaceMap().put(XSDPackage.eNS_PREFIX, XMLResource.XML_SCHEMA_URI);
         hiddenSchema.setSchemaForSchemaQNamePrefix(XSDPackage.eNS_PREFIX);
         hiddenSchema.updateElement();
      }
      return hiddenSchema;
   }

   /**
    * <!-- begin-user-doc -->
    * Overridden to allow creation/updating of elements
    * <!-- end-user-doc -->
    * @generated NOT
    */
   @Override
   public XSDConcreteComponent getContainer()
  {
     return getSchema();
  }

  /**
   * <!-- begin-user-doc -->
   * Overridden to allow cloning
   * <!-- end-user-doc -->
   * @generated NOT
   */
   @Override
   public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
   {
      ExtendedAnnotationType clonedAnnotation = getExtensionFactory().createExtendedAnnotationType();

      if (shareDOM)
      {
         Element element = getElement();
         if (element != null)
         {
            clonedAnnotation.setElement(element);
         }

         clonedAnnotation.getApplicationInformation().addAll(getApplicationInformation());
         clonedAnnotation.getUserInformation().addAll(getUserInformation());
         clonedAnnotation.getAttributes().addAll(getAttributes());
      }

      return clonedAnnotation;
   }

   /**
    * @generated NOT
    */
   private ExtensionFactory getExtensionFactory()
   {
      return getExtensionPackage().getExtensionFactory();
   }

   /**
    * @generated NOT
    */
   private ExtensionPackage getExtensionPackage()
   {
      return (ExtensionPackage) eClass().getEPackage();
   }

} //ExtendedAnnotationTypeImpl