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

import java.awt.List;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataFilterExtension;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataLoader;
import org.eclipse.stardust.engine.extensions.dms.data.*;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class BpmDocumentListVariableBuilder
      extends AbstractDMSVariableBuilder<BpmDocumentListVariableBuilder>
{
   public BpmDocumentListVariableBuilder()
   {
      super();

      AttributeUtil.setAttribute(element, PredefinedConstants.CLASS_NAME_ATT, List.class.getName());
   }

   public static BpmDocumentListVariableBuilder newDocumentListVariable()
   {
      return new BpmDocumentListVariableBuilder();
   }

   public static BpmDocumentListVariableBuilder newDocumentListVariable(ModelType model)
   {
      return newDocumentListVariable().inModel(model);
   }

   @Override
   protected DataTypeType getMetaType()
   {
      DataTypeType documentMetaType = ModelUtils.findIdentifiableElement(
            this.model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);

      if (null == documentMetaType)
      {
         documentMetaType = F_CWM.createDataTypeType();
         documentMetaType.setId(DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);
         documentMetaType.setName("Document List");
         documentMetaType.setIsPredefined(true);

         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.EVALUATOR_CLASS_ATT,
               VfsDocumentListAccessPathEvaluator.class.getName());
         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.VALIDATOR_CLASS_ATT,
               VfsDocumentListValidator.class.getName());

         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.DATA_FILTER_EXTENSION_ATT,
               StructuredDataFilterExtension.class.getName());
         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.DATA_LOADER_ATT,
               StructuredDataLoader.class.getName());

         model.getDataType().add(documentMetaType);
      }

      return documentMetaType;
   }
}