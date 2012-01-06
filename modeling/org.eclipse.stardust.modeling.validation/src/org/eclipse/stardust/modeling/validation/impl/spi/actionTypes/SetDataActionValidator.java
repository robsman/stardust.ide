/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.validation.impl.spi.actionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class SetDataActionValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String dataId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.SET_DATA_ACTION_DATA_ID_ATT);
      DataType data = null;
      if (StringUtils.isEmpty(dataId))
      {
         result.add(Issue.warning(element,
            Validation_Messages.MSG_NoDataSpecified,
            PredefinedConstants.SET_DATA_ACTION_DATA_ID_ATT));
      }
      else
      {
         data = ModelUtils.findData(element, dataId);
         if (null == data)
         {
            result.add(Issue.warning(element,
               Validation_Messages.MSG_InvalidDataSpecified,
               PredefinedConstants.SET_DATA_ACTION_DATA_ID_ATT));
         }
      }

      String srcApId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT);
      ITypedElement srcAp = null;
      if (StringUtils.isEmpty(srcApId))
      {
         result.add(Issue.warning(element,
            Validation_Messages.MSG_NoAccessPointSpecified,
            PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT));
      }
      else
      {
         // TODO resolve src access point
         EventHandlerType eventHandler = ModelUtils.findContainingEventHandlerType(element);
         if (null != eventHandler)
         {
            Map dataTypes = SpiExtensionRegistry.instance().getExtensions(
                  CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID);
            IConfigurationElement config = (IConfigurationElement) dataTypes.get(eventHandler.getMetaType()
                  .getId());
            if (config != null)
            {
               try
               {
                  IAccessPointProvider apProvider = (IAccessPointProvider) config.createExecutableExtension("accessPointProvider"); //$NON-NLS-1$
                  srcAp = (ITypedElement) ModelUtils.findIdentifiableElement(
                        apProvider.createIntrinsicAccessPoint(element), srcApId);
               }
               catch (CoreException e)
               {
               }
            }
         }
         if (null == srcAp)
         {
            result.add(Issue.warning(element,
               Validation_Messages.SetDataActionValidator_MSG_InvalidParam,
               PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT));
         }
      }

      if ((null != data) && (null != srcAp))
      {
         String dataPath = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               PredefinedConstants.SET_DATA_ACTION_DATA_PATH_ATT);
         String srcApPath = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_PATH_ATT);

         try
         {
            BridgeObject.checkMapping(data, dataPath, srcAp, srcApPath);
         }
         catch (ValidationException e)
         {
            result.add(Issue.warning(element, e.getMessage(),
                  StringUtils.isEmpty(srcApPath)
                        ? PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT
                        : PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_PATH_ATT));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}
