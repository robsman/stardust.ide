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

import java.util.Iterator;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
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

public class PrimitiveAccessPointBuilder
      extends AbstractModelElementBuilder<AccessPointType, PrimitiveAccessPointBuilder>
{

   private IAccessPointOwner owner;

   public PrimitiveAccessPointBuilder()
   {
      super(F_CWM.createAccessPointType());
   }

   public PrimitiveAccessPointBuilder(IAccessPointOwner anOwner)
   {
      super(F_CWM.createAccessPointType());
      forModel(ModelUtils.findContainingModel(anOwner));
      this.owner = anOwner;
      long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
      element.setElementOid(++maxElementOid);
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

   private void createMessageTransformationApplicationAccessPoint(
         ApplicationType applicationType)
   {
      applicationType.getAccessPoint().add(element);
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
      ContextType contextType = getApplicationContext(applicationType,
            ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      contextType.getAccessPoint().add(element);
      AttributeUtil.setAttribute(element, "RootElement", element.getId());
      TransformationProperty property = MappingFactory.eINSTANCE
            .createTransformationProperty();
      String xmlString = MappingModelUtil.transformEcore2XML(property);
      AttributeUtil.setAttribute(contextType,
            "messageTransformation:TransformationProperty", xmlString);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "PrimitiveAccessPoint";
   }

   public static PrimitiveAccessPointBuilder newAccessPoint(IAccessPointOwner anOwner)
   {
      return new PrimitiveAccessPointBuilder(anOwner);
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
         createDefaultAccessPoint(owner);
      }
      return element;
   }

   private void createDefaultAccessPoint(IAccessPointOwner owner2)
   {
      owner.getAccessPoint().add(element);
   }

   public PrimitiveAccessPointBuilder withDirection(String direction)
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

   public PrimitiveAccessPointBuilder withType(String primitiveTypeID)
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", primitiveTypeID);
      DataTypeType dataTypeType = new ModelBuilderFacade().findDataType(model,
            PredefinedConstants.PRIMITIVE_DATA);
      element.setType(dataTypeType);
      return self();
   }

   private ContextType getApplicationContext(ApplicationType application,
         String contextTypeKey)
   {
      for (Iterator<ContextType> i = application.getContext().iterator(); i.hasNext();)
      {
         ContextType contextType = i.next();
         if (contextType.getType().getId().equals(contextTypeKey))
         {
            return contextType;
         }
      }
      return null;
   }

}
