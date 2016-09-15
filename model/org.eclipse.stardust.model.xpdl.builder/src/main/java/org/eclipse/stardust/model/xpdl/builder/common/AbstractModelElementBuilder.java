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

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import java.util.UUID;

import org.eclipse.emf.common.util.EList;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.IdRefHandler;
import org.eclipse.stardust.model.xpdl.builder.utils.ElementBuilderUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.NameIdUtilsExtension;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IdRefOwner;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public abstract class AbstractModelElementBuilder<T extends IIdentifiableElement & IModelElement, B extends AbstractModelElementBuilder<T, B>>
      extends AbstractIdentifiableElementBuilder<T, B>
{
   protected ModelType model;
   private String generatedID = null;

   public String getGeneratedID()
   {
      return generatedID;
   }

   public AbstractModelElementBuilder(T element)
   {
      super(element);
   }

   @Override
   public T build()
   {
      T element = super.build();

      generateId();

      // attaching element to model
      EList<? super T> elementContainer = getElementContainer();
      if ((null != elementContainer) && !elementContainer.contains(element))
      {
         elementContainer.add(element);
      }


      if ((element instanceof DataType) || (element instanceof ApplicationType)
            || (element instanceof ActivityType)
            || (element instanceof IModelParticipant)
            || (element instanceof ProcessDefinitionType))
      {
         AttributeUtil.setAttribute((IIdentifiableModelElement) element, PredefinedConstants.MODEL_ELEMENT_UUID, UUID
               .randomUUID().toString());
      }
      if (element instanceof IdRefOwner || element instanceof DataType)
      {
         IdRefHandler.adapt((IIdentifiableModelElement) element);
      }

      return element;
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if (null == model)
      {
         throw new NullPointerException("Model must be set.");
      }

      return element;
   }

   public B inModel(ModelType model)
   {
      setModel(model);

      return self();
   }

   public B forModel(ModelType model)
   {
      return inModel(model);
   }

   public ModelType model()
   {
      return model;
   }

   public B withDescription(String description)
   {
      if ( !isEmpty(description))
      {
         DescriptionType descriptor = F_CWM.createDescriptionType();
         ModelUtils.setCDataString(descriptor.getMixed(), description, true);

         if (element instanceof IIdentifiableModelElement)
         {
            ((IIdentifiableModelElement) element).setDescription(descriptor);
         }
         else
         {
            throw new IllegalArgumentException("Unsupported proeprty: description");
         }
      }

      return self();
   }

   protected void setModel(ModelType model)
   {
      if (null == this.model)
      {
         if (null != model)
         {
            this.model = model;
         }
      }
      else
      {
         if (this.model != model)
         {
            throw new IllegalArgumentException("Model must only be set once.");
         }
      }
   }

   protected String deriveDefaultElementId()
   {
      if (null != getElementContainer())
      {
         return ElementBuilderUtils.deriveDefaultId(element, getElementContainer(),
               getDefaultElementIdPrefix());
      }
      else
      {
         return null;
      }
   }

   protected EList<? super T> getElementContainer()
   {
      return null;
   }

   protected abstract String getDefaultElementIdPrefix();

   protected void generateId()
   {
      if(generatedID == null)
      {
         generatedID = NameIdUtilsExtension.createIdFromName(getElementContainer(), element);
         if(!StringUtils.isEmpty(element.getId()))
         {
            generatedID = element.getId();
         }
         else if(!StringUtils.isEmpty(generatedID) && StringUtils.isEmpty(element.getId()))
         {
            element.setId(generatedID);
         }
      }
   }
}