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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.NameInfo;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;


/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceFactoryImpl
 * @generated
 */
public class CarnotWorkflowModelResourceImpl extends XMLResourceImpl
{
   private static final String XMLNS_PREFIX = ExtendedMetaData.XMLNS_PREFIX;
   private static final String XMLNS_PREFIX_WITH_COLON = XMLNS_PREFIX + ':';
   
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Creates an instance of the resource.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param uri the URI of the new resource.
    * @generated
    */
   public CarnotWorkflowModelResourceImpl(URI uri)
   {
      super(uri);
   }

   protected XMLHelper createXMLHelper()
   {
      return new XMLHelperImpl(this)
      {
         public String getURI(String prefix)
         {
            String uri = super.getURI(prefix);
            if (uri == null && prefix != null && prefix.length() == 0)
            {
               return CarnotWorkflowModelPackage.eNS_URI;
            }
            if (XpdlPackage.eNS_URI1.equals(uri) || XpdlPackage.eNS_URI2.equals(uri))
            {
               return XpdlPackage.eNS_URI;
            }
            return uri;
         }

         public String getID(EObject obj)
         {
            // don't save element OIDs per EMF or we get duplicate oid attributes
            return (obj instanceof IModelElement) ? null : super.getID(obj);
         }

         @Override
         public String getHREF(EObject obj)
         {
            // encode href to a format that can be transformed to xpdl
            ModelType model = ModelUtils.findContainingModel(obj);
            if (model != null && model.getExternalPackages() != null)
            {
               String id = ((EObjectImpl) obj).eProxyURI().toString();
               for (ExternalPackage pkg : model.getExternalPackages().getExternalPackage())
               {
                  String pkgConnectionUri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
                  if (id.startsWith(pkgConnectionUri))
                  {
                     String path = id.substring(pkgConnectionUri.length());
                     int ix = path.indexOf('/');
                     if (ix > 0)
                     {
                        return path.substring(0, ix) + ':' +  new QName(pkg.getId(), path.substring(ix + 1));
                     }
                  }
               }
            }
            
            return super.getHREF(obj);
         }

         public void populateNameInfo(NameInfo nameInfo, EStructuralFeature feature)
         {
            super.populateNameInfo(nameInfo, feature);
            if (nameInfo.getNamespaceURI() == null)
            {
               String qualifiedName = nameInfo.getQualifiedName();
               if (qualifiedName.equals(XMLNS_PREFIX)
                     || qualifiedName.startsWith(XMLNS_PREFIX_WITH_COLON))
               {
                  nameInfo.setNamespaceURI(ExtendedMetaData.XMLNS_URI);
               }
            }
         }
      };
   }
   
   public OutputStream getNewOutputStream() throws IOException
   {
      return getURIConverter().createOutputStream(getURI());
   }

   protected XMLLoad createXMLLoad()
   {
      return new CwmXmlLoad(createXMLHelper());
   }

   protected XMLSave createXMLSave()
   {
      return new CwmXmlSave(createXMLHelper());
   }

} //CarnotWorkflowModelResourceImpl
