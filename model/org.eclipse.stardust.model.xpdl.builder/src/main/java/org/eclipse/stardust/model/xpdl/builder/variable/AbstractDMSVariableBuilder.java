/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.variable;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceEObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public abstract class AbstractDMSVariableBuilder<B extends AbstractModelElementBuilder<DataType, B>>
      extends AbstractModelElementBuilder<DataType, B>
{
   private String structuredDataId = null;
   private ModelType typeDeclarationModel;

   public AbstractDMSVariableBuilder()
   {
      super(F_CWM.createDataType());

      AttributeUtil.setBooleanAttribute(element, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
   }

   public ModelType getTypeDeclarationModel()
   {
      return typeDeclarationModel;
   }

   public void setTypeDeclarationModel(ModelType typeDeclarationModel)
   {
      this.typeDeclarationModel = typeDeclarationModel;
   }

   @Override
   protected DataType finalizeElement()
   {
      super.finalizeElement();

      if (structuredDataId  != null)
      {
         if (getTypeDeclarationModel() == null || getTypeDeclarationModel().getId().equals(model.getId()))
         {
            AttributeUtil.setAttribute(element, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, structuredDataId); //$NON-NLS-1$
         }
         else
         {
            TypeDeclarationType typeDeclaration = getTypeDeclaration(typeDeclarationModel, structuredDataId);

            String fileConnectionId = WebModelerConnectionManager.createFileConnection(model, typeDeclarationModel);

            String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
            URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

            ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(MergeUtils.createQualifiedUri(uri, typeDeclaration, true), element,
                  typeDeclaration.getId(), typeDeclaration.getName(), typeDeclaration.getDescription(),
                  bundleId, null);


            AttributeUtil.setAttribute(element, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
            AttributeUtil.setBooleanAttribute(element, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
            AttributeUtil.setAttribute(element, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
            ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
            if (typeDeclarationModel != null)
            {
               reference.setLocation(ImportUtils.getPackageRef(descriptor, model, typeDeclarationModel).getId());
            }
            reference.setXref(structuredDataId);
            String uuid = ExtendedAttributeUtil.getAttributeValue(typeDeclaration.getExtendedAttributes(), "carnot:model:uuid");
            if (uuid != null)
            {
               reference.setUuid(uuid);
            }
            element.setExternalReference(reference);
         }
      }

      if (null == element.getType())
      {
         DataTypeType documentMetaType = getMetaType();
         if (null != documentMetaType)
         {
            element.setType(documentMetaType);
         }
      }

      model.getData().add(element);

      return element;
   }

   protected abstract DataTypeType getMetaType();

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Data";
   }

   public void setTypeDeclaration(String structuredDataId)
   {
      this.structuredDataId = structuredDataId;
   }

   /**
    *
    * @param modelId
    * @param appId
    * @return
    */
   private TypeDeclarationType getTypeDeclaration(ModelType model, String declId)
   {
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      if (typeDeclarations != null)
      {
         List<TypeDeclarationType> decls = typeDeclarations.getTypeDeclaration();
         for (TypeDeclarationType d : decls)
         {
            if (d.getId().equals(declId))
            {
               return d;
            }
         }
      }

      return null;
   }
}