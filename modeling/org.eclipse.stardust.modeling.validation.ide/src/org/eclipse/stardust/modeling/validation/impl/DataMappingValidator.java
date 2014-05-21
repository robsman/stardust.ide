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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.engine.core.struct.spi.StructDataTransformerKey;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.BridgeObjectProviderRegistry;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class DataMappingValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      DataMappingType dataMapping = (DataMappingType) element;

      if(StringUtils.isEmpty(dataMapping.getId()))
      {
         result.add(Issue.error(dataMapping,
               Validation_Messages.ERR_ELEMENT_EmptyId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }
      if(StringUtils.isEmpty(dataMapping.getName()))
      {
         result.add(Issue.warning(dataMapping,
               Validation_Messages.MSG_ELEMENT_EmptyName,
               ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
      }

      checkActivitySupportsDataMapppings(result, dataMapping);

      checkDuplicateIds(result, dataMapping);

      ActivityType activity = (ActivityType) dataMapping.eContainer();

      checkData(result, dataMapping);

      // checkDirection(result, dataMapping());

      ApplicationContextTypeType ctxType = checkContext(result, dataMapping);

      if (!StringUtils.isEmpty(dataMapping.getApplicationAccessPoint()))
      {
         checkAccessPoint(result, dataMapping, ctxType);
      }
      else
      {
         // no application access point present
         if (ctxType.isHasApplicationPath())
         {
            result.add(Issue.warning(dataMapping,
                  Validation_Messages.MSG_DATAMAPPING_NoApplicationAccessPointSet,
                  ValidationService.PKG_CWM.getDataMappingType_ApplicationAccessPoint()));
         }
         else if (ActivityUtil.isRouteActivity(activity))
         {
            result.add(Issue.warning(dataMapping, DirectionType.OUT_LITERAL
                  .equals(dataMapping.getDirection())
                  ? Validation_Messages.WR_MAPPING_NO_IN_DATA_MAPPING_SPECIFIED
                  : Validation_Messages.WR_MAPPING_INCONSISTENT_DATA_MAPPING_ID, ValidationService.PKG_CWM
                  .getDataMappingType_ApplicationAccessPoint()));
         }
         // check the validity of the data path
         IBridgeObjectProvider dataBridgeProvider = (null != dataMapping.getData())
               ? BridgeObjectProviderRegistry.getBridgeObjectProvider(dataMapping.getData())
               : null;
         if (null != dataBridgeProvider)
         {
            try
            {
               String dataPath = dataMapping.getDataPath();
               if (dataPath != null)
               {
                  dataPath = VariableContextHelper.getInstance().getContext(
                        (ModelType) element.eContainer().eContainer().eContainer())
                        .replaceAllVariablesByDefaultValue(dataPath);
               }
               dataBridgeProvider.getBridgeObject(dataMapping.getData(), dataPath,
                     DirectionType.OUT_LITERAL.equals(dataMapping.getDirection())
                           ? DirectionType.IN_LITERAL
                           : DirectionType.OUT_LITERAL);
               checkValidXPath(dataMapping);
            }
            catch (Exception e)
            {
               if (PredefinedConstants.STRUCTURED_DATA.equals(dataMapping.getData().getMetaType().getId()))
               {
                  checkValidXPath(dataMapping);
               }
               else
               {
                  result.add(Issue.warning(dataMapping,
                        Validation_Messages.MSG_DATAMAPPING_NoValidDataPath,
                        ValidationService.PKG_CWM.getDataMappingType_DataPath()));
               }
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkValidXPath(DataMappingType dataMapping)
   {
      if (PredefinedConstants.STRUCTURED_DATA.equals(dataMapping.getData().getMetaType().getId()))
      {
         String dataPath = dataMapping.getDataPath();
         if (!StringUtils.isEmpty(dataPath))
         {
            // Strip IPP specific transform operations from xpath before validating
            if (dataPath.charAt(dataPath.length() - 1) == ')')
            {
               int ix = dataPath.indexOf('(');
               if (ix > 0)
               {
                  StructDataTransformerKey transformerKey = StructDataTransformerKey.getKey(dataPath.substring(0, ix));
                  if (transformerKey != null)
                  {
                     dataPath = dataPath.substring(ix + 1, dataPath.length() - 1);
                     System.err.println(MessageFormat.format(
                           Validation_Messages.MSG_REMOVED_TRANSFORMATION,
                           transformerKey, dataPath));
                  }
               }
            }

            IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(dataMapping.getData());
            if (dataPath.indexOf("[") > -1) //$NON-NLS-1$
            {
               checkNestedPath(dataPath, xPathMap);
            }
            else
            {
               if (!xPathMap.containsXPath(dataPath))
               {
                  int ix = dataPath.lastIndexOf('/');
                  while (ix >= 0)
                  {
                     dataPath = dataPath.substring(0, ix);
                     if (xPathMap.containsXPath(dataPath) && xPathMap.getXPath(dataPath).hasWildcards())
                     {
                        return;
                     }
                  }
                  throw new ValidationException(
                        Validation_Messages.MSG_DATAMAPPING_NoValidDataPath, dataPath);
               }
            }
         }
      }
   }

   private void checkNestedPath(String dataPath, IXPathMap xPathMap)
   {
      String[] pathSegs = dataPath.split("/"); //$NON-NLS-1$
      String path = ""; //$NON-NLS-1$
      for (int i = 0; i < pathSegs.length; i++)
      {
         path = path + pathSegs[i];
         path = path.replaceAll("\\[[^\\]]*\\]|\\..*/", ""); //$NON-NLS-1$ //$NON-NLS-2$
         if (!xPathMap.containsXPath(path))
         {
            throw new ValidationException(
                  Validation_Messages.MSG_DATAMAPPING_NoValidDataPath, dataPath);
         }
         else
         {
            if (pathSegs[i].indexOf("[") > -1) //$NON-NLS-1$
            {
               TypedXPath xPath = xPathMap.getXPath(path);
               if (!xPath.isList())
               {
                  throw new ValidationException(
                        Validation_Messages.MSG_DATAMAPPING_NoValidDataPath, dataPath);
               }
            }
            if (i < pathSegs.length)
            {
               path = path + "/"; //$NON-NLS-1$
            }
         }
      }
   }

   private void checkAccessPoint(List<Issue> result, DataMappingType dataMapping,
         ApplicationContextTypeType ctxType)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      if (ctxType != null
            && CarnotConstants.DEFAULT_CONTEXT_ID.equalsIgnoreCase(ctxType.getId())
            && ActivityUtil.isRouteActivity(activity))
      {
         if (DirectionType.OUT_LITERAL.equals(dataMapping.getDirection()))
         {
            checkCorrespondingInDataMapping(result, dataMapping);
         }
         else if (!CompareHelper.areEqual(dataMapping.getId(), dataMapping
               .getApplicationAccessPoint()))
         {
            result.add(Issue.warning(dataMapping, Validation_Messages.WR_MAPPING_INCONSISTENT_DATA_MAPPING_ID,
                  ValidationService.PKG_CWM.getDataMappingType_ApplicationAccessPoint()));
         }
         try
         {
            checkValidXPath(dataMapping);
         }
         catch (Exception e)
         {
            result.add(Issue.warning(dataMapping,
                  Validation_Messages.MSG_DATAMAPPING_NoValidDataPath,
                  ValidationService.PKG_CWM.getDataMappingType_DataPath()));
         }
      }
      else if (ctxType != null && dataMapping.getData() != null)
      {
         AccessPointType point = getActivityAccessPoint(dataMapping);
         if (point == null)
         {
            result.add(Issue.warning(dataMapping, MessageFormat.format(
                  Validation_Messages.MSG_DATAMAPPING_ApplicationAccessPointWarning,
                  new Object[] {dataMapping.getId()}), ValidationService.PKG_CWM
                  .getDataMappingType_ApplicationAccessPoint()));
         }
         else
         {
            try
            {
               if (AccessPointUtil.isIn(dataMapping.getDirection()))
               {
                  BridgeObject.checkMapping(point, dataMapping.getApplicationPath(),
                        dataMapping.getData(), dataMapping.getDataPath(), activity, dataMapping);
               }
               else
               {
                  BridgeObject.checkMapping(dataMapping.getData(), dataMapping
                        .getDataPath(), point, dataMapping.getApplicationPath(), activity, dataMapping);
               }
               try
               {
                  checkValidXPath(dataMapping);
               }
               catch (Exception e)
               {
                  result.add(Issue.warning(dataMapping,
                        Validation_Messages.MSG_DATAMAPPING_NoValidDataPath,
                        ValidationService.PKG_CWM.getDataMappingType_DataPath()));
               }
            }
            catch (ValidationException e)
            {
               result.add(Issue.warning(dataMapping, e.getMessage(), getFeature(
                     dataMapping, e.getSource())));
            }
         }
      }
   }

   private void checkCorrespondingInDataMapping(List<Issue> result, DataMappingType dataMapping)
   {
      DataMappingType inMapping = findCorrespondingInDataMapping(dataMapping);
      if (inMapping == null)
      {
         result.add(Issue.warning(dataMapping, MessageFormat.format(
               Validation_Messages.WR_MAPPING_NO_IN_DATA_MAPPING_WITH_ID_STELLE_NULL_FOUND, new Object[] {dataMapping
                     .getApplicationAccessPoint()}), ValidationService.PKG_CWM
               .getDataMappingType_ApplicationAccessPoint()));
      }
      else if (dataMapping.getData() != null && inMapping.getData() != null)
      {
         try
         {
            ActivityType activity = (ActivityType) dataMapping.eContainer();
            BridgeObject.checkMapping(dataMapping.getData(), dataMapping.getDataPath(),
                  inMapping.getData(), inMapping.getDataPath(), activity);
            checkValidXPath(dataMapping);
         }
         catch (ValidationException e)
         {
            if (PredefinedConstants.STRUCTURED_DATA.equals(dataMapping.getData().getMetaType().getId()))
            {
               checkValidXPath(dataMapping);
            }
            else
            {
               result.add(Issue.warning(dataMapping, e.getMessage(),
                  ValidationService.PKG_CWM.getDataMappingType_DataPath()));
            }
         }
      }
   }

   private DataMappingType findCorrespondingInDataMapping(DataMappingType dataMapping)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      List mappings = activity.getDataMapping();
      for (Iterator iter = mappings.iterator(); iter.hasNext();)
      {
         DataMappingType inMapping = (DataMappingType) iter.next();
         if (DirectionType.IN_LITERAL.equals(inMapping.getDirection())
               && dataMapping.getContext().equalsIgnoreCase(inMapping.getContext())
               && dataMapping.getApplicationAccessPoint().equalsIgnoreCase(
                     inMapping.getApplicationAccessPoint()))
         {
            return inMapping;
         }
      }
      return null;
   }

   private ApplicationContextTypeType checkContext(List<Issue> result,
         DataMappingType dataMapping)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      ApplicationContextTypeType ctxType = null;
      if (StringUtils.isEmpty(dataMapping.getContext()))
      {
         result.add(Issue.warning(dataMapping,
               Validation_Messages.MSG_DATAMAPPING_NoContextSet,
               ValidationService.PKG_CWM.getDataMappingType_Context()));
      }
      else
      {
         ctxType = (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(
               ActivityUtil.getContextTypes(activity, dataMapping.getDirection()),
               dataMapping.getContext());
         if (null == ctxType)
         {
            // every data mapping must have a valid context
            result.add(Issue.warning(dataMapping, MessageFormat.format(
                  Validation_Messages.MSG_DATAMAPPING_ContextInvalid,
                  new Object[] {dataMapping.getContext()}), ValidationService.PKG_CWM
                  .getDataMappingType_Context()));
         }
      }
      return ctxType;
   }

   private void checkData(List<Issue> result, DataMappingType dataMapping)
   {
      if (null == dataMapping.getData())
      {
         // every data mapping must have a data
         result.add(Issue.error(dataMapping,
               Validation_Messages.MSG_DATAMAPPING_NoDataSet, ValidationService.PKG_CWM
                     .getDataMappingType_Data()));
      }
   }

   private void checkDuplicateIds(List<Issue> result, DataMappingType dataMapping)
   {
      if (findDuplicateId(dataMapping))
      {
         // each data mapping id must be unique
         result.add(Issue.warning(dataMapping, MessageFormat.format(
               Validation_Messages.MSG_DATAMAPPING_NoUniqueId, new Object[] {dataMapping
                     .getDirection().getName()}), ValidationService.PKG_CWM
               .getIIdentifiableElement_Id()));
      }
   }

   private void checkActivitySupportsDataMapppings(List<Issue> result,
         DataMappingType dataMapping)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      if (ActivityImplementationType.SUBPROCESS_LITERAL.equals(activity.getImplementation()))
      {
         if (activity.getSubProcessMode() == SubProcessModeType.ASYNC_SEPARATE_LITERAL
               && DirectionType.IN_LITERAL != dataMapping.getDirection())
         {
            result.add(Issue.warning(dataMapping,
                  Validation_Messages.MSG_DATAMAPPING_NotSupportedByActivity,
                  ValidationService.PKG_CWM.getActivityType_DataMapping()));
         }
      }
   }

   private EStructuralFeature getFeature(DataMappingType dataMapping, Object source)
   {
      if (source != null)
      {
         if (source.equals(dataMapping.getData()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_Data();
         }
         if (source.equals(dataMapping.getDataPath()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_DataPath();
         }
         if (source instanceof AccessPointType
               && ((AccessPointType) source).getId().equals(
                     dataMapping.getApplicationAccessPoint()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_ApplicationAccessPoint();
         }
         if (source.equals(dataMapping.getApplicationPath()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_ApplicationPath();
         }
      }
      return null;
   }

   private AccessPointType getActivityAccessPoint(DataMappingType dataMapping)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      Collection accessPoints = ActivityUtil.getAccessPoints(activity,
            DirectionType.IN_LITERAL.equals(dataMapping.getDirection()), dataMapping
                  .getContext());
      for (Iterator iter = accessPoints.iterator(); iter.hasNext();)
      {
         AccessPointType ap = (AccessPointType) iter.next();
         if (ap.getId().equals(dataMapping.getApplicationAccessPoint()))
         {
            return ap;
         }
      }
      return null;
   }

   private boolean findDuplicateId(DataMappingType dataMapping)
   {
      ActivityType activity = (ActivityType) dataMapping.eContainer();
      for (Iterator iter = activity.getDataMapping().iterator(); iter.hasNext();)
      {
         DataMappingType otherDataMapping = (DataMappingType) iter.next();
         if (CompareHelper.areEqual(dataMapping.getId(), otherDataMapping.getId())
               && CompareHelper.areEqual(dataMapping.getContext(), otherDataMapping
                     .getContext()) && !dataMapping.equals(otherDataMapping)
               && dataMapping.getDirection().equals(otherDataMapping.getDirection()))
         {
            if ((dataMapping.getContext() != null)
                  && (otherDataMapping.getContext() != null))
            {

               return true;

            }
         }
      }
      return false;
   }
}