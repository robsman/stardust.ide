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
package org.eclipse.stardust.model.xpdl.xpdl2.impl;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.spi.IResourceResolver;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Reference Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl#getXref <em>Xref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalReferenceTypeImpl extends EObjectImpl implements ExternalReferenceType {
    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
     * @generated NOT
     */
    private XSDSchema schema = null;

    /**
    * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getLocation()
    * @generated
    * @ordered
    */
    protected static final String LOCATION_EDEFAULT = null;

    /**
    * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getLocation()
    * @generated
    * @ordered
    */
    protected String location = LOCATION_EDEFAULT;

    /**
    * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getNamespace()
    * @generated
    * @ordered
    */
    protected static final String NAMESPACE_EDEFAULT = null;

    /**
    * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getNamespace()
    * @generated
    * @ordered
    */
    protected String namespace = NAMESPACE_EDEFAULT;

    /**
    * The default value of the '{@link #getXref() <em>Xref</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getXref()
    * @generated
    * @ordered
    */
    protected static final String XREF_EDEFAULT = null;

    /**
    * The cached value of the '{@link #getXref() <em>Xref</em>}' attribute.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @see #getXref()
    * @generated
    * @ordered
    */
    protected String xref = XREF_EDEFAULT;

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    protected ExternalReferenceTypeImpl() {
      super();
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   protected EClass eStaticClass() {
      return XpdlPackage.Literals.EXTERNAL_REFERENCE_TYPE;
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public String getLocation() {
      return location;
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public void setLocation(String newLocation) {
      String oldLocation = location;
      location = newLocation;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_REFERENCE_TYPE__LOCATION, oldLocation, location));
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public String getNamespace() {
      return namespace;
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public void setNamespace(String newNamespace) {
      String oldNamespace = namespace;
      namespace = newNamespace;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_REFERENCE_TYPE__NAMESPACE, oldNamespace, namespace));
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public String getXref() {
      return xref;
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    public void setXref(String newXref) {
      String oldXref = xref;
      xref = newXref;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_REFERENCE_TYPE__XREF, oldXref, xref));
   }

    /**
     * Simple caching mechanism to speed up the external schema retrieval
     * and to prohibit the existence of multiple instances of the same schema
     * @generated NOT
     */
    public XSDSchema getSchema() {
       if (location != null)
       {
          if (location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
          {
             return getInternalSchema();
          }
          else
          {
             return getReferencedSchema();
          }
       }
       return null;
    }

    /**
     * fixed NPE
     * @generated NOT
     */
    private XSDSchema getInternalSchema()
    {
       String typeId = location.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
       if (typeId.length() > 0)
       {
          TypeDeclarationsType declarations = (TypeDeclarationsType) eContainer().eContainer();
          TypeDeclarationType internalType = declarations.getTypeDeclaration(typeId);
          if (internalType != null)
          {
              XpdlTypeType type = internalType.getDataType();
              if (type instanceof SchemaTypeType)
              {
                 return ((SchemaTypeType) type).getSchema();
              }
          }
       }
       return null;
    }

    private String getWorkspaceRelativePath()
    {
       TypeDeclarationType declaration = (TypeDeclarationType) eContainer();
       return ExtendedAttributeUtil.getAttributeValue(declaration,
             StructuredDataConstants.RESOURCE_MAPPING_ELIPSE_WORKSPACE_FILE);
    }

    private XSDSchema loadSchema(String schemaLocation, String namespaceURI)
    {
       if (StringUtils.isNotEmpty(schemaLocation))
       {
          try
          {
             return schema = TypeDeclarationUtils.loadAndCacheSchema('{' + namespaceURI + '}' + location, schemaLocation, namespaceURI);
          }
          catch (Exception ex)
          {}
       }
       return null;
    }

    /**
     * TODO: describe
     * We must synchronize that method entirely to ensure cache consistency.
     * That has as side effect that the gui may wait for the validation thread
     * to load the schema if the validation kicked in first.
     * @generated NOT
     */
    private synchronized XSDSchema getReferencedSchema()
    {
       if (schema == null)
       {
          String namespaceURI = QNameUtil.parseNamespaceURI(xref);
          //try load xsd from eclipse workspace
          String workspacePath = getWorkspaceRelativePath();
          schema = loadSchema(workspacePath, namespaceURI);

          //try getting from alternate url attribute - for legacy reason
          if(schema == null)
          {
             String alternateUrl = getAlternateURL();
             schema = loadSchema(alternateUrl, namespaceURI);
          }

          //fall back to default value
          if(schema == null)
          {
             schema = loadSchema(location, namespaceURI);
          }
       }

       return schema;
    }

    /**
     * TODO: describe
     * @generated NOT
     */
    private String getAlternateURL()
    {
       TypeDeclarationType declaration = (TypeDeclarationType) eContainer();
       String url = ExtendedAttributeUtil.getAttributeValue(declaration,
             StructuredDataConstants.RESOURCE_MAPPING_LOCAL_FILE);
       if (url == null)
       {
          url = location;
       }
       if (!url.toLowerCase().startsWith("http://")) //$NON-NLS-1$
       {
          List<IResourceResolver> resourceResolvers = ModelUtils.getResourceResolvers();
          for (IResourceResolver resolver : resourceResolvers)
          {
             String localUri = resolver.resolveToLocalUri(url, declaration);
             if (!isEmpty(localUri))
             {
                return localUri;
             }
          }
       }
       return url;
    }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID)
      {
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__LOCATION:
            return getLocation();
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__NAMESPACE:
            return getNamespace();
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__XREF:
            return getXref();
      }
      return super.eGet(featureID, resolve, coreType);
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID)
      {
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__LOCATION:
            setLocation((String)newValue);
            return;
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__NAMESPACE:
            setNamespace((String)newValue);
            return;
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__XREF:
            setXref((String)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   public void eUnset(int featureID) {
      switch (featureID)
      {
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__LOCATION:
            setLocation(LOCATION_EDEFAULT);
            return;
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__NAMESPACE:
            setNamespace(NAMESPACE_EDEFAULT);
            return;
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__XREF:
            setXref(XREF_EDEFAULT);
            return;
      }
      super.eUnset(featureID);
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   public boolean eIsSet(int featureID) {
      switch (featureID)
      {
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__LOCATION:
            return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__NAMESPACE:
            return NAMESPACE_EDEFAULT == null ? namespace != null : !NAMESPACE_EDEFAULT.equals(namespace);
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE__XREF:
            return XREF_EDEFAULT == null ? xref != null : !XREF_EDEFAULT.equals(xref);
      }
      return super.eIsSet(featureID);
   }

    /**
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    * @generated
    */
    @Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (location: "); //$NON-NLS-1$
      result.append(location);
      result.append(", namespace: "); //$NON-NLS-1$
      result.append(namespace);
      result.append(", xref: "); //$NON-NLS-1$
      result.append(xref);
      result.append(')');
      return result.toString();
   }

} //ExternalReferenceTypeImpl