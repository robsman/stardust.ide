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

import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class DecoratorApplicationBuilder extends AbstractModelElementBuilder<ApplicationType, DecoratorApplicationBuilder>
{

   public DecoratorApplicationBuilder(ModelType model)
   {
      super(F_CWM.createApplicationType());

      forModel(model);

      ApplicationTypeType applicationMetaType = ModelUtils.findIdentifiableElement(
            model.getApplicationType(), ModelerConstants.DECORATOR_APP_CONTEXT_TYPE_KEY);
      if (null == applicationMetaType)
      {
         applicationMetaType = AbstractElementBuilder.F_CWM.createApplicationTypeType();
         applicationMetaType.setId(ModelerConstants.DECORATOR_APP_CONTEXT_TYPE_KEY);
         applicationMetaType.setName("Decorator Application");
         applicationMetaType.setIsPredefined(true);
         applicationMetaType.setSynchronous(true);
         AttributeUtil.setAttribute(applicationMetaType, "carnot:engine:validator", "org.eclipse.stardust.engine.extensions.decorator.DecoratorApplicationValidator");
         AttributeUtil.setAttribute(applicationMetaType, "carnot:engine:applicationInstance", "org.eclipse.stardust.engine.extensions.decorator.DecoratorAppApplicationInstance");
         model.getApplicationType().add(applicationMetaType);
      }
      element.setType(applicationMetaType);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Application";
   }

   public static DecoratorApplicationBuilder newDecoratorApplication(ModelType model)
   {
      return new DecoratorApplicationBuilder(model);
   }

   @Override
   protected ApplicationType finalizeElement()
   {
      super.finalizeElement();
      model.getApplication().add(element);
      return element;
   }

}
