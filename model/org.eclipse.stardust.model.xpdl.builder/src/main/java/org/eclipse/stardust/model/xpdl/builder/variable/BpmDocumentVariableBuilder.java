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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataFilterExtension;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataLoader;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentAccessPathEvaluator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentValidator;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceEObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class BpmDocumentVariableBuilder
      extends AbstractModelElementBuilder<DataType, BpmDocumentVariableBuilder>
{
   private String structuredDataId = null;
   private ModelType typeDeclarationModel;

   public BpmDocumentVariableBuilder()
   {
      super(F_CWM.createDataType());

      AttributeUtil.setBooleanAttribute(element, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
      AttributeUtil.setAttribute(element, PredefinedConstants.CLASS_NAME_ATT, "org.eclipse.stardust.engine.api.runtime.Document"); //$NON-NLS-1$
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
      // TODO set type specific default value?

      if(structuredDataId  != null)
      {
         if(getTypeDeclarationModel() == null || getTypeDeclarationModel().getId().equals(model.getId()))
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

      if ((null == element.getType()))
      {
         DataTypeType documentMetaType = ModelUtils.findIdentifiableElement(
               this.model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT);

         if (null == documentMetaType)
         {
            documentMetaType = F_CWM.createDataTypeType();
            documentMetaType.setId(DmsConstants.DATA_TYPE_DMS_DOCUMENT);
            documentMetaType.setName("Document");
            documentMetaType.setIsPredefined(true);

            Class<VfsDocumentAccessPathEvaluator> clsEvaluator = VfsDocumentAccessPathEvaluator.class;
            Class<VfsDocumentValidator> clsValidator = VfsDocumentValidator.class;
            Class<StructuredDataFilterExtension> clsExtension = StructuredDataFilterExtension.class;
            Class<StructuredDataLoader> dataLoader = StructuredDataLoader.class;

            AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.EVALUATOR_CLASS_ATT,
                  clsEvaluator.getName());
            AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.VALIDATOR_CLASS_ATT,
                  clsValidator.getName());

            AttributeUtil.setAttribute(documentMetaType,
                  PredefinedConstants.DATA_FILTER_EXTENSION_ATT,
                  clsExtension.getName());
            AttributeUtil.setAttribute(documentMetaType,
                  PredefinedConstants.DATA_LOADER_ATT, dataLoader.getName());

            model.getDataType().add(documentMetaType);
         }

         if (null != documentMetaType)
         {
            element.setType(documentMetaType);
         }
      }

      model.getData().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Data";
   }

   public static BpmDocumentVariableBuilder newDocumentVariable()
   {
      return new BpmDocumentVariableBuilder();
   }

   public static BpmDocumentVariableBuilder newDocumentVariable(ModelType model)
   {
      return newDocumentVariable().inModel(model);
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
  private TypeDeclarationType getTypeDeclaration(ModelType model, String declId) {
     TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
     if(typeDeclarations != null)
     {
        List<TypeDeclarationType> decls = typeDeclarations.getTypeDeclaration();
        for (TypeDeclarationType d : decls) {
           if (d.getId().equals(declId)) {
              return d;
           }
        }
     }

     return null;
  }
}