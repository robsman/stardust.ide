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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;

public class DataFilter extends ViewerFilter
{
   private ViewerFilter categoryFilter;

   private ModelType referencedModel;

   private Object filterType;

   public ViewerFilter getCategoryFilter()
   {
      return categoryFilter;
   }

   public void setCategoryFilter(ViewerFilter categoryFilter)
   {
      this.categoryFilter = categoryFilter;
   }

   public ModelType getReferencedModel()
   {
      return referencedModel;
   }

   public void setReferencedModel(ModelType referencedModel)
   {
      this.referencedModel = referencedModel;
   }

   public Object getFilterType()
   {
      return filterType;
   }

   public void setFilterType(Object filterType)
   {
      this.filterType = filterType;
   }

   public boolean select(Viewer viewer, Object parentElement, Object element)
   {
      if (categoryFilter == null || filterType == null)
      {
         return false;
      }
      DataType dataType = (DataType) element;
      String typeId = dataType.getType().getId();

      if ((PredefinedConstants.PRIMITIVE_DATA.equals(typeId) || PredefinedConstants.STRUCTURED_DATA
            .equals(typeId))
            && !categoryFilter.toString().startsWith("Document")) //$NON-NLS-1$
      {
         String typeName = AttributeUtil.getAttributeValue(dataType.getAttribute(),
               PredefinedConstants.TYPE_ATT);
         if (typeId.equals(PredefinedConstants.STRUCTURED_DATA))
         {
            ModelType model = ModelUtils.findContainingModel(dataType);
            if (referencedModel != null && !model.equals(referencedModel))
            {
               AttributeType attribute = AttributeUtil.getAttribute((IExtensibleElement) element, "carnot:connection:uri"); //$NON-NLS-1$
               if (dataType.getExternalReference() != null)
               {
                  if (this.referencedModel.getId().equals(
                        dataType.getExternalReference().getLocation()))
                  {
                     return true;
                  }
               }
               else if(attribute != null)
               {
                  String uri = attribute.getValue();
                  URI aRealUri = URI.createURI(uri);
                  String typeName_ = aRealUri.lastSegment();
                  Connection connection = (Connection) model.getConnectionManager()
                        .findConnection(uri);
                  if (connection.getAttribute("importByReference") != null //$NON-NLS-1$
                        && !"false".equals(connection.getAttribute("importByReference"))) //$NON-NLS-1$ //$NON-NLS-2$
                  {

                     EObject o = model.getConnectionManager().find(
                           aRealUri.scheme().toString() + "://" + aRealUri.authority() + "/"); //$NON-NLS-1$ //$NON-NLS-2$
                     ModelType refModel = (ModelType) Reflect.getFieldValue(o, "eObject"); //$NON-NLS-1$
                     if (this.referencedModel.getId().equals(refModel.getId()))
                     {
                        return true;
                     }
                  }
               }
               else
               {
                  return false;
               }
            }
         }

         if (typeName == null)
         {
            typeName = AttributeUtil.getAttributeValue(dataType.getAttribute(),
                  "carnot:engine:dataType"); //$NON-NLS-1$
         }

         // extra check for enumeration here
         if (filterType != null
               && filterType instanceof Type
               && ((Type) filterType).equals(Type.Enumeration))
         {
            if (PredefinedConstants.PRIMITIVE_DATA.equals(typeId))
            {
               if (filterType != null && filterType instanceof Type)
               {
                  return ((Type) filterType).getId().equalsIgnoreCase(typeName);
               }
            }
            else if (PredefinedConstants.STRUCTURED_DATA.equals(typeId))
            {
               TypeDeclarationType decl = TypeDeclarationUtils.findTypeDeclaration(dataType);
               if(decl != null)
               {
                  return TypeDeclarationUtils.isEnumeration(decl, false);
               }
            }
         }
         if (filterType != null && filterType instanceof Type)
         {
            return ((Type) filterType).getId().equalsIgnoreCase(typeName);
         }
         if (filterType != null && filterType instanceof TypeDeclarationType)
         {
            if(PredefinedConstants.STRUCTURED_DATA.equals(typeId))
            {
               TypeDeclarationType decl = TypeDeclarationUtils.findTypeDeclaration(dataType);
               if(decl != null)
               {
                  return !TypeDeclarationUtils.isEnumeration(decl, false);
               }
            }
         }
      }
      if (typeId.equals("dmsDocument") && categoryFilter.toString().equals("Document")) //$NON-NLS-1$ //$NON-NLS-2$
      {
         String typeName = AttributeUtil.getAttributeValue(dataType.getAttribute(),
               "carnot:engine:dms:resourceMetadataSchema"); //$NON-NLS-1$
         if (filterType != null && filterType instanceof TypeDeclarationType)
         {
            String filterTypeID = ((TypeDeclarationType) filterType).getId();
            ModelType model = ModelUtils.findContainingModel(dataType);
            if (!filterTypeID.equalsIgnoreCase(StructuredTypeUtils
                  .getResourceTypeDeclaration().getId())
                  && !model.equals(referencedModel))
            {
               if (AttributeUtil.getAttributeValue(dataType,
                     IConnectionManager.URI_ATTRIBUTE_NAME) == null)
               {
                  return false;
               }
               if (dataType.getExternalReference() == null)
               {
                  return false;
               }
               if (!dataType.getExternalReference().getLocation().equals(
                     referencedModel.getId()))
               {
                  return false;
               }
            }
            if (typeName == null && filterTypeID.equals("ResourceProperty")) //$NON-NLS-1$
            {
               return true;
            }
            if (typeName != null && filterTypeID.equals(typeName))
            {
               return true;
            }
         }
         return false;
      }
      if (typeId.equals("dmsDocumentList") //$NON-NLS-1$
            && categoryFilter.toString().equals("Document List")) //$NON-NLS-1$
      {
         String typeName = AttributeUtil.getAttributeValue(dataType.getAttribute(),
               "carnot:engine:dms:resourceMetadataSchema"); //$NON-NLS-1$
         if (filterType != null && filterType instanceof TypeDeclarationType)
         {
            String filterTypeID = ((TypeDeclarationType) filterType).getId();
            ModelType model = ModelUtils.findContainingModel(dataType);
            if (!filterTypeID.equalsIgnoreCase(StructuredTypeUtils
                  .getResourceTypeDeclaration().getId())
                  && !model.equals(referencedModel))
            {
               if (AttributeUtil.getAttributeValue(dataType,
                     IConnectionManager.URI_ATTRIBUTE_NAME) == null)
               {
                  return false;
               }
               if (dataType.getExternalReference() == null)
               {
                  return false;
               }
               if (!dataType.getExternalReference().getLocation().equals(
                     referencedModel.getId()))
               {
                  return false;
               }
            }
            if (typeName == null && filterTypeID.equals("ResourceProperty")) //$NON-NLS-1$
            {
               return true;
            }
            if (typeName != null && filterTypeID.equals(typeName))
            {
               return true;
            }
         }
         return false;
      }

      return false;
   }
}