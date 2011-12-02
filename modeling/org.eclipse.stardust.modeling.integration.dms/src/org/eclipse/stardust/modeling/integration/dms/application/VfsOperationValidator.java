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
package org.eclipse.stardust.modeling.integration.dms.application;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.integration.dms.DMS_Messages;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;

import com.infinity.bpm.rt.integration.data.dms.DmsConstants;
import com.infinity.bpm.rt.integration.data.dms.VfsOperationAccessPointProvider;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

public class VfsOperationValidator implements IModelElementValidator
{

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();      
      
      ApplicationType application = ((ApplicationType) element);
      boolean folder = AttributeUtil.getBooleanValue((IExtensibleElement) element, DmsConstants.PRP_RUNTIME_DEFINED_TARGET_FOLDER);
      String operation = AttributeUtil.getAttributeValue((IExtensibleElement) element, DmsConstants.PRP_OPERATION_NAME);
      
      if(folder && operation != null && operation.equals("addDocument")) //$NON-NLS-1$
      {
         List<ActivityType> executedActivities = application.getExecutedActivities();
         for(ActivityType activity : executedActivities)
         {
            boolean valid = false;
            List<DataMappingType> dataMappings = activity.getDataMapping();
            for(DataMappingType dataMapping : dataMappings)
            {
               if(dataMapping.getDirection().equals(DirectionType.IN_LITERAL))
               {
                  DataType data = dataMapping.getData();
                  if(data != null && data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER))
                  {                  
                     String applicationAccessPoint = dataMapping.getApplicationAccessPoint();
                     if(!StringUtils.isEmpty(applicationAccessPoint))
                     {
                        if(applicationAccessPoint.equals(VfsOperationAccessPointProvider.AP_ID_TARGET_FOLDER))
                        {
                           valid = true;
                           break;                        
                        }                     
                     }
                  }
               }               
            }
            
            if(!valid)
            {
               result.add(Issue.error(activity,
                     DMS_Messages.DMSApplicationValidator_NoAccessPoint,
                     ValidationService.PKG_CWM.getIIdentifiableElement_Id()));               
            }            
         }         
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}