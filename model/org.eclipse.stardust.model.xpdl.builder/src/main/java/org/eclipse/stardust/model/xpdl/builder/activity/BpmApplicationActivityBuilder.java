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
package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.IdRefHandler;
import org.eclipse.stardust.model.xpdl.builder.utils.ExternalReferenceUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class BpmApplicationActivityBuilder
      extends AbstractActivityBuilder<BpmApplicationActivityBuilder>
{
   ModelType applicationModel;

   public ModelType getApplicationModel()
   {
      return applicationModel;
   }

   public void setApplicationModel(ModelType applicationModel)
   {
      this.applicationModel = applicationModel;
   }

   public BpmApplicationActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.APPLICATION_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      // TODO more specific handling?

      return super.finalizeElement();
   }

   public BpmApplicationActivityBuilder invokingApplication(ApplicationType application)
   {
      ModelType applicationModel = getApplicationModel();

      if (model.equals(applicationModel))
      {
         element.setApplication(application);
      }
      else
      {
         ExternalReferenceUtils.createExternalReferenceToApplication((ActivityType)element, application, model, applicationModel);
      }

      return this;
   }



}
