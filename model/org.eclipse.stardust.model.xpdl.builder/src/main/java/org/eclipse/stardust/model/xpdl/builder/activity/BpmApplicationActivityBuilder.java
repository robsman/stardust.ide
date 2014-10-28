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

import java.util.UUID;

import org.eclipse.emf.common.util.URI;

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
      ActivityType activity = element;
      ModelType applicationModel = getApplicationModel();

      if(model.equals(applicationModel))
      {

         element.setApplication(application);
      }
      else
      {
         String fileConnectionId = WebModelerConnectionManager.createFileConnection(model, applicationModel);

         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");

         ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(uri,
               application, bundleId, null, true);

         AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());

         IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
         idRef.setRef(application.getId());
         idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, model, applicationModel));
         activity.setExternalRef(idRef);
         AttributeType uuidAttribute = AttributeUtil.getAttribute((IIdentifiableModelElement) application,  "carnot:model:uuid");
         if (uuidAttribute != null)
         {
            AttributeUtil.setAttribute((IIdentifiableModelElement) element,
                  "carnot:connection:uuid", uuidAttribute.getValue());

         }
      }

      return this;
   }

}
