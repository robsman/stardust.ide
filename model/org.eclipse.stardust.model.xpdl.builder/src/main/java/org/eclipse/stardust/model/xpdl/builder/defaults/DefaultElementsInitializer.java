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
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class DefaultElementsInitializer implements ModelInitializer
{

   public void initializeModel(ModelType model)
   {
      ModelBuilderFacade facade = new ModelBuilderFacade();

      DataType data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_ID, "Process OID",
            ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);
      data = facade.createPrimitiveData(model, PredefinedConstants.ROOT_PROCESS_ID,
            "Root Process OID", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);
      data = facade.createPrimitiveData(model, PredefinedConstants.PROCESS_PRIORITY,
            "Process Priority", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);
      data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_LOCALE,
            "Current Locale", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);
      data = facade.createPrimitiveData(model, PredefinedConstants.CURRENT_DATE, "Current Date",
            ModelerConstants.DATE_PRIMITIVE_DATA_TYPE);
      data.setPredefined(true);


      DataTypeType entityBean = (DataTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            PredefinedConstants.ENTITY_BEAN_DATA);

      DataType lastActivityPerformer = facade.createData(model, entityBean,
            PredefinedConstants.LAST_ACTIVITY_PERFORMER, "Last Activity Performer", //$NON-NLS-1$
            "");
      facade.createAttribute(lastActivityPerformer, PredefinedConstants.BROWSABLE_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      facade.createAttribute(lastActivityPerformer,
            PredefinedConstants.HOME_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserHome"); //$NON-NLS-1$
      facade.createAttribute(lastActivityPerformer, PredefinedConstants.IS_LOCAL_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      facade.createAttribute(lastActivityPerformer, PredefinedConstants.JNDI_PATH_ATT,
            null, "org.eclipse.stardust.engine.api.runtime.User"); //$NON-NLS-1$
      facade.createAttribute(lastActivityPerformer, PredefinedConstants.PRIMARY_KEY_ATT,
            null, "org.eclipse.stardust.engine.api.runtime.UserPK"); //$NON-NLS-1$
      facade.createAttribute(lastActivityPerformer,
            PredefinedConstants.REMOTE_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.core.runtime.beans.IUser"); //$NON-NLS-1$

      DataType currentUser = facade.createData(model, entityBean,
            PredefinedConstants.CURRENT_USER, "Current User", "");
      facade.createAttribute(currentUser, PredefinedConstants.BROWSABLE_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      facade.createAttribute(currentUser, PredefinedConstants.HOME_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserHome"); //$NON-NLS-1$
      facade.createAttribute(currentUser, PredefinedConstants.IS_LOCAL_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      facade.createAttribute(currentUser, PredefinedConstants.JNDI_PATH_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.User"); //$NON-NLS-1$
      facade.createAttribute(currentUser, PredefinedConstants.PRIMARY_KEY_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserPK"); //$NON-NLS-1$
      facade.createAttribute(currentUser, PredefinedConstants.REMOTE_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.core.runtime.beans.IUser"); //$NON-NLS-1$

   }
}