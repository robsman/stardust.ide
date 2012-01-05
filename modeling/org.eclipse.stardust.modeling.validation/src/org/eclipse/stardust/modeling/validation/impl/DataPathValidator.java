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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.engine.core.struct.XPathAnnotations;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.ValidatorRegistry;

import ag.carnot.bpm.integration.dms.DmsConstants;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.runtime.beans.BigData;

public class DataPathValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      DataPathType dataPath = (DataPathType) element;

      if (!StringUtils.isValidIdentifier(dataPath.getId()))
      {
         result.add(Issue.error(dataPath,

         Validation_Messages.ERR_DATAPATH_NotValidIdentifier,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }      
      if (findDuplicateId(dataPath))
      {
         result.add(Issue.error(dataPath, Validation_Messages.ERR_DATAPATH_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }
      DataType data = dataPath.getData();
      if (DmsConstants.PATH_ID_ATTACHMENTS.equals(dataPath.getId()))
      {
    	 DataPathType other = findOtherDataPath(dataPath);
    	 if (other == null || data == null || data != other.getData()
    			 || !org.eclipse.stardust.engine.extensions.dms.data.DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST.equals(data.getType().getId())
    			 || !StringUtils.isEmpty(dataPath.getDataPath())
    			 || !StringUtils.isEmpty(other.getDataPath()))
    	 {
				result.add(Issue.warning(dataPath, MessageFormat.format(
						Validation_Messages.WR_IS_A_RESERVED_IDENTIFIER,
						new Object[] { DmsConstants.PATH_ID_ATTACHMENTS }),
						ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
    	 }
      }

      if (StringUtils.isEmpty(dataPath.getName()))
      {
         result.add(Issue.error(dataPath, Validation_Messages.ERR_DATAPATH_NoNameSpecified,
               ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
      }

      if (data == null)
      {
         result.add(Issue.error(dataPath, Validation_Messages.ERR_DATAPATH_NoDataSpecified,
               ValidationService.PKG_CWM.getDataPathType_Data()));
      }
      else
      {
         if (!StringUtils.isEmpty(dataPath.getDataPath()))
         {
            IBridgeObjectProvider dataBridgeProvider = ValidatorRegistry.getBridgeObjectProvider(data);
            if (null != dataBridgeProvider)
            {
               try
               {
                  dataBridgeProvider.getBridgeObject(data,
                        dataPath.getDataPath(),
                        DirectionType.OUT_LITERAL.equals(dataPath.getDirection())
                              ? DirectionType.IN_LITERAL
                              : DirectionType.OUT_LITERAL);
               }
               catch (Exception e)
               {
                  result.add(Issue.warning(dataPath,
                        Validation_Messages.MSG_DATAMAPPING_NoValidDataPath,
                        ValidationService.PKG_CWM.getDataPathType_DataPath()));
               }
            }
         }

         if (dataPath.isKey())
         {
            if (dataPath.isDescriptor())
            {
               String dataTypeId = data.getType().getId();
               // all primitive types can be key descriptors. 
               if (!PredefinedConstants.PRIMITIVE_DATA.equals(dataTypeId))
               {
                  String accessPath = dataPath.getDataPath();
                  if (!PredefinedConstants.STRUCTURED_DATA.equals(dataTypeId))
                  {
                     result.add(Issue.error(dataPath,
                           "Key descriptors must be either primitive or structured types.",
                           ValidationService.PKG_CWM.getDataPathType_Key()));
                  }
                  else if (accessPath == null || accessPath.length() == 0)
                  {
                     result.add(Issue.error(dataPath,
                           "Structured key descriptors must have primitive type.",
                           ValidationService.PKG_CWM.getDataPathType_Key()));
                  }
                  else try
                  {
                     IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(data);
                     if (StructuredDataXPathUtils.returnSinglePrimitiveType(accessPath, xPathMap) == BigData.NULL)
                     {
                        result.add(Issue.error(dataPath,
                              "Structured key descriptors must have primitive type.",
                              ValidationService.PKG_CWM.getDataPathType_Key()));
                     }
                     else
                     {
                        XPathAnnotations xPathAnnotations = StructuredDataXPathUtils.getXPathAnnotations(accessPath, xPathMap);
                        if (!xPathAnnotations.isIndexed() || !xPathAnnotations.isPersistent())
                        {
                           result.add(Issue.error(dataPath,
                                 "Structured key descriptors must be indexed and persistent.",
                                 ValidationService.PKG_CWM.getDataPathType_Key()));
                        }
                     }
                  }
                  catch (Exception ex)
                  {
                     result.add(Issue.error(dataPath,
                           "No schema found for structured key descriptor.",
                           ValidationService.PKG_CWM.getDataPathType_Key()));
                  }
               }
            }
            else
            {
               result.add(Issue.warning(dataPath,
                     "DataPath marked as key descriptor but it's not a descriptor.",
                     ValidationService.PKG_CWM.getDataPathType_Key()));
            }
         }
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private DataPathType findOtherDataPath(DataPathType dataPath)
   {
      List<DataPathType> allPaths = ModelUtils.findContainingProcess(dataPath).getDataPath();
      for (DataPathType otherDataPath : allPaths)
      {
    	 if (otherDataPath == dataPath)
    	 {
    		continue;
    	 }
         if (CompareHelper.areEqual(otherDataPath.getId(), dataPath.getId())
            && CompareHelper.areEqual(otherDataPath.getDirection(), oposite(dataPath.getDirection())))
         {
            return otherDataPath;
         }
      }
	  return null;
   }

   private Object oposite(DirectionType direction)
   {
	  if (direction == null)
	  {
		 return null;
	  }
	  switch (direction)
	  {
	  case IN_LITERAL : return DirectionType.OUT_LITERAL;
	  case OUT_LITERAL : return DirectionType.IN_LITERAL;
	  }
	  return direction;
   }

   private boolean findDuplicateId(DataPathType dataPath)
   {
      List<DataPathType> allPaths = ModelUtils.findContainingProcess(dataPath).getDataPath();
      for (DataPathType otherDataPath : allPaths)
      {
    	 if (otherDataPath == dataPath)
    	 {
    		continue;
    	 }
         if (CompareHelper.areEqual(otherDataPath.getId(), dataPath.getId())
            && CompareHelper.areEqual(otherDataPath.getDirection(), dataPath.getDirection()))
         {
            return true;
         }
      }
      return false;
   }
}