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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataFilterExtension;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataLoader;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentAccessPathEvaluator;
import org.eclipse.stardust.engine.extensions.dms.data.VfsDocumentValidator;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BpmDocumentVariableBuilder
      extends AbstractModelElementBuilder<DataType, BpmDocumentVariableBuilder>
{
   private String structuredDataFullId = null;

   public BpmDocumentVariableBuilder()
   {
      super(F_CWM.createDataType());
      
      AttributeUtil.setBooleanAttribute(element, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$      
      AttributeUtil.setAttribute(element, PredefinedConstants.CLASS_NAME_ATT, "org.eclipse.stardust.engine.api.runtime.Document"); //$NON-NLS-1$      
   }

   @Override
   protected DataType finalizeElement()
   {
      super.finalizeElement();
      // TODO set type specific default value?

      if(structuredDataFullId  != null)
      {
         AttributeUtil.setAttribute(element, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, structuredDataFullId); //$NON-NLS-1$      
      }      

      if ((null == element.getType()))
      {
         DataTypeType documentMetaType = XpdlModelUtils.findIdentifiableElement(
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

            long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
            documentMetaType.setElementOid(++maxElementOid);
            
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

   public void setTypeDeclaration(String structuredDataFullId)
   {
      this.structuredDataFullId = structuredDataFullId;           
   }
}