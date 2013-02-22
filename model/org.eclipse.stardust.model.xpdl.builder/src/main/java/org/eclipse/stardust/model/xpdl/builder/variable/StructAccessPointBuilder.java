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
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.transformation.model.MappingModelUtil;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.MappingFactory;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.TransformationProperty;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class StructAccessPointBuilder
      extends AbstractModelElementBuilder<AccessPointType, StructAccessPointBuilder>
{

   private ModelBuilderFacade facade;

   private IAccessPointOwner owner;

   public StructAccessPointBuilder()
   {
      super(F_CWM.createAccessPointType());
   }

   public StructAccessPointBuilder(IAccessPointOwner anOwner)
   {
      super(F_CWM.createAccessPointType());
      this.owner = anOwner;
      forModel(ModelUtils.findContainingModel(anOwner));
   }

   private void createApplicationAccessPoint(IAccessPointOwner anOwner)
   {
      ApplicationType applicationType = (ApplicationType) anOwner;
      if (applicationType.isInteractive())
      {
         createExternalWebApplicationAccessPoint(applicationType);
      }
      else
      {
         if (applicationType.getType().getId()
               .equals(ModelerConstants.MESSAGE_TRANSFORMATION_APPLICATION_TYPE_ID))
         {
            createMessageTransformationApplicationAccessPoint(applicationType);
         }
         else
         {
            applicationType.getAccessPoint().add(element);
         }
      }
   }

   private void createDefaultAccessPoint(IAccessPointOwner owner2)
   {
      owner.getAccessPoint().add(element);
   }

   private void createMessageTransformationApplicationAccessPoint(
         ApplicationType applicationType)
   {
      applicationType.getAccessPoint().add(element);
      AttributeUtil
            .setAttribute(
                  element,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil
            .setBooleanAttribute(element, "carnot:engine:data:bidirectional", true);
      AttributeUtil.setAttribute(element, "RootElement", element.getId());
      AttributeUtil.setAttribute(element, "FullXPath", element.getId() + "/");
      TransformationProperty property = MappingFactory.eINSTANCE
            .createTransformationProperty();
      String xmlString = MappingModelUtil.transformEcore2XML(property);
      AttributeUtil.setAttribute(element, "messageTransformation:TransformationProperty",
            xmlString);
   }

   private void createExternalWebApplicationAccessPoint(ApplicationType applicationType)
   {
      ContextType contextType = getModelBuilderFacade().getApplicationContext(
            applicationType, ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      contextType.getAccessPoint().add(element);
      AttributeUtil
            .setAttribute(
                  element,
                  "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil
            .setBooleanAttribute(element, "carnot:engine:data:bidirectional", true);
      AttributeUtil.setAttribute(element, "RootElement", element.getId());
      TransformationProperty property = MappingFactory.eINSTANCE
            .createTransformationProperty();
      String xmlString = MappingModelUtil.transformEcore2XML(property);
      AttributeUtil.setAttribute(contextType,
            "messageTransformation:TransformationProperty", xmlString);
   }

   private void createInteractiveAccessPoint(ContextType contextType)
   {
      contextType.getAccessPoint().add(element);
      if (contextType.getType().getId()
            .equals(ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY))
      {
         AttributeUtil.setAttribute(element, "RootElement", element.getId());
         TransformationProperty property = MappingFactory.eINSTANCE
               .createTransformationProperty();
         String xmlString = MappingModelUtil.transformEcore2XML(property);
         AttributeUtil.setAttribute(contextType,
               "messageTransformation:TransformationProperty", xmlString);
      }
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "StructAccessPoint";
   }

   public static StructAccessPointBuilder newAccessPoint(IAccessPointOwner anOwner)
   {
      return new StructAccessPointBuilder(anOwner);
   }

   @Override
   protected AccessPointType finalizeElement()
   {
      super.finalizeElement();
      if (owner instanceof ApplicationType)
      {
         createApplicationAccessPoint(owner);
      }
      else
      {
         if (owner instanceof ContextType)
         {
            createInteractiveAccessPoint((ContextType) owner);
         }
         else
         {
            createDefaultAccessPoint(owner);
         }

      }
      return element;
   }
   public StructAccessPointBuilder withDirection(String direction)
   {
      DirectionType directionType;
      if (direction.equals(DirectionType.IN_LITERAL.getName()))
      {
         directionType = DirectionType.IN_LITERAL;
      }
      else
      {
         directionType = DirectionType.OUT_LITERAL;
      }
      element.setDirection(directionType);
      return self();
   }

   public StructAccessPointBuilder withType(String structTypeFullID)
   {
      /*
       * TypeDeclarationType declaringType = new ModelBuilderFacade()
       * .findTypeDeclaration(structTypeFullID); ModelType typeDeclarationModel =
       * ModelUtils.findContainingModel(declaringType);
       */
      String declaredTypeID = null;
      // if (model.getId().equals(typeDeclarationModel.getId()))
      if (model.getId().equals(getModelBuilderFacade().getModelId(structTypeFullID)))
      {
         declaredTypeID = getModelBuilderFacade().stripFullId(structTypeFullID);
      }
      else
      {
         declaredTypeID = "typeDeclaration:{"
               + getModelBuilderFacade().getModelId(structTypeFullID) + "}"
               + getModelBuilderFacade().stripFullId(structTypeFullID);
      }
      AttributeUtil.setAttribute(element, ModelerConstants.DATA_TYPE, declaredTypeID);
      DataTypeType dataTypeType = new ModelBuilderFacade().findDataType(model,
            PredefinedConstants.STRUCTURED_DATA);
      element.setType(dataTypeType);
      return self();
   }

   private ModelBuilderFacade getModelBuilderFacade()
   {
      if (facade == null)
      {
         facade = new ModelBuilderFacade();
      }
      return facade;
   }

}
