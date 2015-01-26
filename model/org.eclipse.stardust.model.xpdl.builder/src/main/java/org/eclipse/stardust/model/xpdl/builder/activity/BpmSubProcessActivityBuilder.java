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
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
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
      ModelType processModel = getSubProcessModel();

      if (model.equals(processModel))
      {
         IdRefHandler.cleanup(element);
         element.setImplementationProcess(process);
      }
      else
      {
         ExternalReferenceUtils.createExternalReferenceToProcess((ActivityType)element, process, model, processModel);
      }

      return this;
   }


   public BpmSubProcessActivityBuilder usingMode(SubProcessModeType subProcessMode)
   {
      element.setSubProcessMode(subProcessMode);

      return this;
   }

}
