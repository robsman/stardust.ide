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
package org.eclipse.stardust.modeling.data.structured.validation;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.util.PathEntry;

// TODO: arrays (collections) are not properly handled!
public class StructBridgeObject extends BridgeObject
{
//   private PathEntry entry;
   protected String actualTypeName;
   private String label;
   protected boolean isList;

   public StructBridgeObject(IType accessPointType, DirectionType direction, PathEntry entry)
   {
      super(accessPointType, direction);
//      this.entry = entry;
      String[] labels = computeActualTypeName(entry);
      label = labels[0];
      actualTypeName = labels[1];
      isList = isList(entry);
   }

   public boolean acceptAssignmentFrom(BridgeObject rhs)
   {
      if (rhs instanceof StructBridgeObject)
      {
         StructBridgeObject other = (StructBridgeObject) rhs;
         if (actualTypeName != null && other.actualTypeName != null)
         {
            return actualTypeName.equals(other.actualTypeName) || isList;
         }
      }
      return isList || super.acceptAssignmentFrom(rhs);
   }

   public static String[] computeActualTypeName(PathEntry entry)
   {
      String[] result = new String[2];
      ITypedElement element = entry.getElement();
      if (element instanceof StructAccessPointType)
      {
         StructAccessPointType struct = (StructAccessPointType) element;
         TypedXPath xpath = struct.getXPath();
         if(xpath == null)
         {
            result[0] = ""; //$NON-NLS-1$
            result[1] = ""; //$NON-NLS-1$
            return result;
         }
         result[0] = xpath.getXsdElementName();
         result[1] = QNameUtil.toString(xpath.getXsdElementNs(), xpath.getXsdElementName()) ;
      }
      else if (element instanceof DataType || element instanceof AccessPointType)
      {
         TypeDeclarationType declaration = findTypeDeclaration(element);
         if (declaration != null)
         {
            XpdlTypeType dataType = declaration.getDataType();

            if (dataType instanceof ExternalReferenceType)
            {
               ExternalReferenceType ref = (ExternalReferenceType) dataType;
               result[0] = QNameUtil.parseLocalName(ref.getXref());
               result[1] = ref.getXref();
            }
            else if (dataType instanceof SchemaTypeType)
            {
               IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(declaration);
               result[0] = xPathMap.getRootXPath().getXsdElementName();
               result[1] = QNameUtil.toString(xPathMap.getRootXPath().getXsdElementNs(), xPathMap.getRootXPath().getXsdElementName()) ;
            }
         }
      }

      return result;
   }

   public static boolean isList(PathEntry entry)
   {
      ITypedElement element = entry.getElement();
      if (element instanceof StructAccessPointType)
      {
         StructAccessPointType struct = (StructAccessPointType) element;
         TypedXPath xpath = struct.getXPath();
         return xpath.isList();
      }
      else if (element instanceof DataType || element instanceof AccessPointType)
      {
         TypeDeclarationType declaration = findTypeDeclaration(element);
         if (declaration != null)
         {
            try
            {
               IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(declaration);
               return xPathMap.getRootXPath().isList();
            }
            catch (Exception ex)
            {
               // (fh) we do nothing, since we may have at some points incomplete defined models
            }
         }
      }

      return false;
   }

   protected static TypeDeclarationType findTypeDeclaration(ITypedElement element)
   {
      TypeDeclarationType declaration = null;
      if (element instanceof DataType)
      {
         ExternalReferenceType ref = ((DataType) element).getExternalReference();
         if (ref != null)
         {
            ModelType model = ModelUtils.findContainingModel((DataType) element);
            ExternalPackages packages = model.getExternalPackages();
            ExternalPackage pkg = packages == null ? null : packages.getExternalPackage(ref.getLocation());
            IConnectionManager manager = model.getConnectionManager();
            ModelType externalModel = manager == null ? null : manager.find(pkg);
            if (externalModel != null)
            {
               TypeDeclarationsType declarations = externalModel.getTypeDeclarations();
               if (declarations != null)
               {
                  declaration = declarations.getTypeDeclaration(ref.getXref());
               }
            }
         }
      }

      if (declaration == null)
      {
         declaration = (TypeDeclarationType) AttributeUtil.getIdentifiable(
            (IExtensibleElement) element, StructuredDataConstants.TYPE_DECLARATION_ATT);
      }

      if (declaration == null)
      {
         String typeDeclarationId = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, StructuredDataConstants.TYPE_DECLARATION_ATT);
         ModelType model = ModelUtils.findContainingModel(element);
         if (model != null)
         {
            TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
            if (typeDeclarations != null)
            {
               declaration = typeDeclarations.getTypeDeclaration(typeDeclarationId);
            }
         }
      }
      return declaration;
   }

   public String toString()
   {
      if (label != null)
      {
         return label;
      }
      return super.toString();
   }
}