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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.builder.utils.JcrConnectionManager;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.Attribute;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.RepositoryFactory;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;


public class BpmSubProcessActivityBuilder
      extends AbstractActivityBuilder<BpmSubProcessActivityBuilder>
{
   ModelType subProcessModel;
   
   public ModelType getSubProcessModel()
   {
      return subProcessModel;
   }

   public void setSubProcessModel(ModelType subProcessModel)
   {
      this.subProcessModel = subProcessModel;
   }

   public BpmSubProcessActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.SUBPROCESS_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      if (null == element.getSubProcessMode())
      {
         element.setSubProcessMode(SubProcessModeType.SYNC_SHARED_LITERAL);
      }
      
      return super.finalizeElement();
   }

   public BpmSubProcessActivityBuilder invokingProcess(ProcessDefinitionType process)
   {
      ActivityType activity = element;
      ModelType processModel = getSubProcessModel();

      if(model.equals(processModel))
      {
         element.setImplementationProcess(process);
      }
      else
      {
         String fileConnectionId = JcrConnectionManager.createFileConnection(model, processModel);
         
         
         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;         
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");
         
         ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(uri, 
               process, bundleId, null, true);
         
         AttributeUtil.setAttribute(activity, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
         if (processModel != null)
         {
            IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
            idRef.setRef(process.getId());
            idRef.setPackageRef(ImportUtils.getPackageRef(descriptor, model, processModel));
            activity.setExternalRef(idRef);
            activity.setSubProcessMode(SubProcessModeType.SYNC_SEPARATE_LITERAL);
         }   
         
         
      }
      
      return this;
   }

   public BpmSubProcessActivityBuilder usingMode(SubProcessModeType subProcessMode)
   {
      element.setSubProcessMode(subProcessMode);

      return this;
   }

}
