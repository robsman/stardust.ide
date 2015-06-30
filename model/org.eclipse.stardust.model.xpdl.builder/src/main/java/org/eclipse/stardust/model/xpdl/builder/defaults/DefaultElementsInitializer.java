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
package org.eclipse.stardust.model.xpdl.builder.defaults;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class DefaultElementsInitializer implements ModelInitializer
{
   public void initializeModel(ModelType model)
   {
      DataType data;

      ModelBuilderFacade facade = new ModelBuilderFacade();

      if (!existsData(model, PredefinedConstants.PROCESS_ID))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_ID,
               "Process OID", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.ROOT_PROCESS_ID))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.ROOT_PROCESS_ID,
               "Root Process OID", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.PROCESS_PRIORITY))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_PRIORITY,
               "Process Priority", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.CURRENT_LOCALE))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_LOCALE,
               "Current Locale", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.CURRENT_DATE))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_DATE,
               "Current Date", ModelerConstants.DATE_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.BUSINESS_DATE))
      {
         data = facade.createPrimitiveData(model, PredefinedConstants.BUSINESS_DATE, "Business Date",
               ModelerConstants.TIMESTAMP_PRIMITIVE_DATA_TYPE);
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.CURRENT_MODEL))
      {
         data = facade.createSerializableData(model, PredefinedConstants.CURRENT_MODEL,
               "Current Model", null, "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription");
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.CURRENT_USER))
      {
         data = facade.createEntityData(model, PredefinedConstants.CURRENT_USER,
               "Current User", null, "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription");
         AttributeUtil.setBooleanAttribute(data, PredefinedConstants.IS_LOCAL_ATT, true);
         AttributeUtil.setAttribute(data, PredefinedConstants.HOME_INTERFACE_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserHome");
         AttributeUtil.setAttribute(data, PredefinedConstants.JNDI_PATH_ATT, null, "org.eclipse.stardust.engine.api.runtime.User");
         AttributeUtil.setAttribute(data, PredefinedConstants.PRIMARY_KEY_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserPK");
         AttributeUtil.setAttribute(data, PredefinedConstants.REMOTE_INTERFACE_ATT, null, "org.eclipse.stardust.engine.core.runtime.beans.IUser");
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.LAST_ACTIVITY_PERFORMER))
      {
         data = facade.createEntityData(model, PredefinedConstants.LAST_ACTIVITY_PERFORMER,
               "Last Activity Performer", null, "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription");
         AttributeUtil.setBooleanAttribute(data, PredefinedConstants.IS_LOCAL_ATT, true);
         AttributeUtil.setAttribute(data, PredefinedConstants.HOME_INTERFACE_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserHome");
         AttributeUtil.setAttribute(data, PredefinedConstants.JNDI_PATH_ATT, null, "org.eclipse.stardust.engine.api.runtime.User");
         AttributeUtil.setAttribute(data, PredefinedConstants.PRIMARY_KEY_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserPK");
         AttributeUtil.setAttribute(data, PredefinedConstants.REMOTE_INTERFACE_ATT, null, "org.eclipse.stardust.engine.core.runtime.beans.IUser");
         data.setPredefined(true);
      }

      if (!existsData(model, PredefinedConstants.STARTING_USER))
      {
         data = facade.createEntityData(model, PredefinedConstants.STARTING_USER,
               "Starting User", null, "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription");
         AttributeUtil.setBooleanAttribute(data, PredefinedConstants.IS_LOCAL_ATT, true);
         AttributeUtil.setAttribute(data, PredefinedConstants.HOME_INTERFACE_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserHome");
         AttributeUtil.setAttribute(data, PredefinedConstants.JNDI_PATH_ATT, null, "org.eclipse.stardust.engine.api.runtime.User");
         AttributeUtil.setAttribute(data, PredefinedConstants.PRIMARY_KEY_ATT, null, "org.eclipse.stardust.engine.api.runtime.UserPK");
         AttributeUtil.setAttribute(data, PredefinedConstants.REMOTE_INTERFACE_ATT, null, "org.eclipse.stardust.engine.core.runtime.beans.IUser");
         data.setPredefined(true);
      }
   }

   private boolean existsData(ModelType model, String id)
   {
      return ModelUtils.findIdentifiableElement(model.getData(), id) != null;
   }
}