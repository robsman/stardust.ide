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
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class ExternalWebApplicationApplicationBuilder extends AbstractModelElementBuilder<ApplicationType, ExternalWebApplicationApplicationBuilder>
{

   public ExternalWebApplicationApplicationBuilder(ModelType model)
   {
      super(F_CWM.createApplicationType());

      forModel(model);

      ContextType context = F_CWM.createContextType();
      element.setInteractive(true);
      ApplicationContextTypeType contextTypeType = XpdlModelUtils.findIdentifiableElement(
            model.getApplicationContextType(), ModelerConstants.EXTERNAL_WEB_APP_CONTEXT_TYPE_KEY);
      if (contextTypeType == null) {
         contextTypeType = AbstractElementBuilder.F_CWM.createApplicationContextTypeType();
         contextTypeType.setName("External Web Application");
         contextTypeType.setId("externalWebApp");
         contextTypeType.setIsPredefined(true);
         long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
         contextTypeType.setElementOid(++maxElementOid);
         model.getApplicationContextType().add(contextTypeType);
      }
      context.setType(contextTypeType); // TODO Add this context type to the model
      element.getContext().add(context);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Application";
   }

   public static ExternalWebApplicationApplicationBuilder newExternalWebApplication(ModelType model)
   {
      return new ExternalWebApplicationApplicationBuilder(model);
   }

   @Override
   protected ApplicationType finalizeElement()
   {
      super.finalizeElement();
      model.getApplication().add(element);
      return element;
   }

}
