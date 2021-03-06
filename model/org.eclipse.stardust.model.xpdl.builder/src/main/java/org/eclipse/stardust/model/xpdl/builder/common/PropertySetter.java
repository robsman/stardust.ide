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
package org.eclipse.stardust.model.xpdl.builder.common;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;



public abstract class PropertySetter
{
   public static PropertySetter directValue(EStructuralFeature property, Object value)
   {
      return new DirectValueSetter(property, value);
   }

   public static ModelParticipantSetter participantById(EStructuralFeature property, String participantId)
   {
      return new ModelParticipantSetter(property, participantId);
   }

   public static IdentifiedElementSetter elementById(EStructuralFeature property, EReference elementDomain, String elementId)
   {
      return new IdentifiedElementSetter(property, elementDomain, elementId);
   }

   private final EStructuralFeature property;

   protected abstract Object resolveValue(AbstractElementBuilder<?, ?> builder);

   public PropertySetter(EStructuralFeature property)
   {
      this.property = property;
   }

   public <T extends EObject, B extends AbstractElementBuilder<T, B>> void apply(B builder)
   {
      Object value = resolveValue(builder);

      builder.element.eSet(property, value);
   }

   public static class DirectValueSetter extends PropertySetter
   {
      private final Object value;

      public DirectValueSetter(EStructuralFeature feature, Object value)
      {
         super(feature);

         this.value = value;
      }

      @Override
      protected Object resolveValue(AbstractElementBuilder<?, ?> builder)
      {
         return value;
      }
   }

   public static class ModelParticipantSetter extends PropertySetter
   {
      private final String participantId;

      public ModelParticipantSetter(EStructuralFeature feature, String participantId)
      {
         super(feature);

         this.participantId = participantId;
      }

      @Override
      protected IModelParticipant resolveValue(AbstractElementBuilder<?, ?> builder)
      {
         ModelType model = null;
         if (builder instanceof AbstractModelElementBuilder<?, ?>)
         {
            model = ((AbstractModelElementBuilder<? , ? >) builder).model();
         }

         if (null == model)
         {
            model = ModelUtils.findContainingModel(builder.element);
         }


         IModelParticipant participant = ModelUtils.findElementById(model.getRole(),
               participantId);
         if (null == participant)
         {
            participant = ModelUtils.findElementById(model.getOrganization(), participantId);
         }
         if (null == participant)
         {
            participant = ModelUtils.findElementById(model.getConditionalPerformer(), participantId);
         }

         return participant;
      }
   }

   public static class IdentifiedElementSetter extends PropertySetter
   {
      protected final EReference elementDomain;
      protected final String elementId;

      public IdentifiedElementSetter(EStructuralFeature property, EReference elementDomain, String elementId)
      {
         super(property);

         this.elementDomain = elementDomain;
         this.elementId = elementId;
      }

      @Override
      protected Object resolveValue(AbstractElementBuilder<?, ?> builder)
      {
         EObject value = null;

         if (AbstractIdentifiableElementBuilder.PKG_CWM.getModelType() == elementDomain.getEContainingClass())
         {
            ModelType model = null;
            if (builder instanceof AbstractModelElementBuilder<?, ?>)
            {
               model = ((AbstractModelElementBuilder<? , ? >) builder).model();
            }

            if (null == model)
            {
               model = ModelUtils.findContainingModel(builder.element);
            }

            value = ModelUtils.findElementById(model, elementDomain, elementId);
         }
         else if (AbstractIdentifiableElementBuilder.PKG_CWM.getProcessDefinitionType() == elementDomain.getEContainingClass())
         {
            ProcessDefinitionType process = null;
            if (builder instanceof AbstractProcessElementBuilder<?, ?>)
            {
               process = ((AbstractProcessElementBuilder<? , ? >) builder).process();
            }

            if (null == process)
            {
               process = ModelUtils.findContainingProcess(builder.element);
            }

            value = ModelUtils.findElementById(process, elementDomain, elementId);
         }

         return value;
      }
   }
}
