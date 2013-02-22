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

import org.eclipse.stardust.engine.extensions.transformation.model.MappingModelUtil;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.MappingFactory;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.TransformationProperty;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class MessageTransformationApplicationBuilder extends AbstractModelElementBuilder<ApplicationType, MessageTransformationApplicationBuilder>
{

   public MessageTransformationApplicationBuilder(ModelType model)
   {
      super(F_CWM.createApplicationType());

      forModel(model);

      ApplicationTypeType applicationMetaType = XpdlModelUtils.findIdentifiableElement(
            model.getApplicationType(), ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID);
      if (null == applicationMetaType)
      {

         applicationMetaType = AbstractElementBuilder.F_CWM.createApplicationTypeType();
         applicationMetaType.setId(ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID);
         applicationMetaType.setName("Message Transformation Bean");
         applicationMetaType.setIsPredefined(true);
         AttributeUtil.setAttribute(applicationMetaType, "carnot:engine:applicationInstance", "org.eclipse.stardust.engine.extensions.transformation.runtime.transformation.MessageTransformationApplicationInstance");
         AttributeUtil.setAttribute(applicationMetaType, "carnot:engine:validator", "org.eclipse.stardust.engine.extensions.transformation.runtime.transformation.MessageProcessingValidator");

         model.getApplicationType().add(applicationMetaType);
      }
      element.setType(applicationMetaType);
      TransformationProperty property = MappingFactory.eINSTANCE.createTransformationProperty();
      String xmlString = MappingModelUtil.transformEcore2XML(property);
      AttributeUtil.setAttribute(element, "messageTransformation:TransformationProperty", xmlString);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Application";
   }

   public static MessageTransformationApplicationBuilder newMessageTransformationApplication(ModelType model)
   {
      return new MessageTransformationApplicationBuilder(model);
   }

   @Override
   protected ApplicationType finalizeElement()
   {
      super.finalizeElement();
      model.getApplication().add(element);
      return element;
   }

}
