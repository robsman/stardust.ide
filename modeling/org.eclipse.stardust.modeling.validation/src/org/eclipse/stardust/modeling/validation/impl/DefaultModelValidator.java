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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.base.DateUtils;
import ag.carnot.base.StringUtils;
import ag.carnot.config.CurrentVersion;
import ag.carnot.workflow.model.PredefinedConstants;

public class DefaultModelValidator implements IModelValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   public Issue[] validate(ModelType model) throws ValidationException
   {
      IModelElement element = model instanceof IModelElement
         ? (IModelElement) model : ModelUtils.getIdentifiableModelProxy(model, ModelType.class);
      
      ValidationService vs = ValidationPlugin.getDefault().getValidationService();

      List<Issue> result = new ArrayList<Issue>();

      // validate model properties

      if (StringUtils.isEmpty(model.getId()))
      {
         result.add(Issue.error(
               element, //
               Validation_Messages.MODEL_EmptyId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }
      else if (model.getId().length() > IdentifiableModelElementValidator.MAX_ID_LENGTH)
      {
         result.add(Issue.error(
               element, //
               MessageFormat.format(
                     Validation_Messages.MODEL_TooLongId,
                     new Object[] {String.valueOf(IdentifiableModelElementValidator.MAX_ID_LENGTH)}),
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (StringUtils.isEmpty(model.getName()))
      {
         result.add(Issue.warning(
               element, //
               Validation_Messages.MODEL_EmptyModelName,
               ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
      }

      AttributeType attrValidFrom = AttributeUtil.getAttribute(model,
            PredefinedConstants.VALID_FROM_ATT);
      Date validFrom = null;
      if (null != attrValidFrom)
      {
         try
         {
            validFrom = DateUtils.getNoninteractiveDateFormat().parse(
                  attrValidFrom.getAttributeValue());
         }
         catch (ParseException e)
         {
            result.add(Issue.error(element, Validation_Messages.MODEL_InvalidValidFrom,
                  PredefinedConstants.VALID_FROM_ATT));
         }
      }

      AttributeType attrValidTo = AttributeUtil.getAttribute(model,
            PredefinedConstants.VALID_TO_ATT);
      Date validTo = null;
      if (null != attrValidTo)
      {
         try
         {
            validTo = DateUtils.getNoninteractiveDateFormat().parse(
                  attrValidTo.getAttributeValue());
         }
         catch (ParseException e)
         {
            result.add(Issue.error(element, Validation_Messages.MODEL_InvalidValidTo,
                  PredefinedConstants.VALID_TO_ATT));
         }
      }

      if ((null != validFrom) && (null != validTo))
      {

         if (validTo.before(validFrom))
         {
            result.add(Issue.error(element, Validation_Messages.MODEL_ValidFromAfterValidTo,
                  PredefinedConstants.VALID_TO_ATT));
         }
      }
      
      if (!CurrentVersion.getVersionName().equals(model.getCarnotVersion()))
      {
         DataTypeType dataType = model.getDataType().size() > 0 ? (DataTypeType) model
               .getDataType().get(0) : null;
         result.add(Issue.warning(dataType, Validation_Messages.WR_MD_HAS_AN_OLDER_VERSION,
               ValidationService.PKG_CWM.getModelType_CarnotVersion()));
      }

      // validate child elements
      result.addAll(Arrays.asList(vs.validateModelElements(model.getData())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getApplication())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getProcessDefinition())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getOrganization())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getRole())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getConditionalPerformer())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getModeler())));
      result.addAll(Arrays.asList(vs.validateModelElements(model.getDiagram())));
      
      // check for administrator role
      if (ModelUtils.findIdentifiableElement(model.getRole(), PredefinedConstants.ADMINISTRATOR_ROLE) == null)
      {
         result.add(Issue.error(element, Validation_Messages.DefaultModelValidator_MissingAdministrator,
               PredefinedConstants.ADMINISTRATOR_ROLE));
      }

      return (Issue[]) result.toArray(ISSUE_ARRAY);
   }
}