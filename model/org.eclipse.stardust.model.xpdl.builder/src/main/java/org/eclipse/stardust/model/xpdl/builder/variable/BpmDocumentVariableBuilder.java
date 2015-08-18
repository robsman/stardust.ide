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
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class BpmDocumentVariableBuilder
      extends AbstractDMSVariableBuilder<BpmDocumentVariableBuilder>
{
   public BpmDocumentVariableBuilder()
   {
      super();

      AttributeUtil.setAttribute(element, PredefinedConstants.CLASS_NAME_ATT, "org.eclipse.stardust.engine.api.runtime.Document"); //$NON-NLS-1$
   }

   public static BpmDocumentVariableBuilder newDocumentVariable()
   {
      return new BpmDocumentVariableBuilder();
   }

   public static BpmDocumentVariableBuilder newDocumentVariable(ModelType model)
   {
      return newDocumentVariable().inModel(model);
   }

   @Override
   protected DataTypeType getMetaType()
   {
      DataTypeType documentMetaType = ModelUtils.findIdentifiableElement(
            this.model.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT);

      if (null == documentMetaType)
      {
         documentMetaType = F_CWM.createDataTypeType();
         documentMetaType.setId(DmsConstants.DATA_TYPE_DMS_DOCUMENT);
         documentMetaType.setName("Document");
         documentMetaType.setIsPredefined(true);

         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.EVALUATOR_CLASS_ATT,
               VfsDocumentAccessPathEvaluator.class.getName());
         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.VALIDATOR_CLASS_ATT,
               VfsDocumentValidator.class.getName());

         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.DATA_FILTER_EXTENSION_ATT,
               StructuredDataFilterExtension.class.getName());
         AttributeUtil.setAttribute(documentMetaType, PredefinedConstants.DATA_LOADER_ATT,
               StructuredDataLoader.class.getName());

         model.getDataType().add(documentMetaType);
      }

      return documentMetaType;
   }
}