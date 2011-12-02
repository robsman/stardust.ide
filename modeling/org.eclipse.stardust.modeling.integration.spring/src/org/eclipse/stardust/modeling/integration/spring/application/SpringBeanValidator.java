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
package org.eclipse.stardust.modeling.integration.spring.application;

import java.util.List;
import java.util.ArrayList;
import java.text.MessageFormat;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.integration.spring.SpringConstants;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;

import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.base.StringUtils;

public class SpringBeanValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String beanId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            SpringConstants.ATTR_BEAN_ID);

      if (StringUtils.isEmpty(beanId))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoBeanId,
               SpringConstants.ATTR_BEAN_ID));
      }

      TypeFinder typeFinder = new TypeFinder(element);
      IType type = null;

      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.CLASS_NAME_ATT);      
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_JavaClassNotSpecified,
               PredefinedConstants.CLASS_NAME_ATT));
      }
      else
      {
         className = VariableContextHelper.getInstance().getContext(
               (ModelType) element.eContainer().eContainer())
               .replaceAllVariablesByDefaultValue(className);
         type = typeFinder.findExactType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat
                  .format(Validation_Messages.MSG_ClassCanNotBeResolved,
                        new Object[] {className}), PredefinedConstants.CLASS_NAME_ATT));
         }
         else
         {
            String method = AttributeUtil.getAttributeValue((IExtensibleElement) element,
                  PredefinedConstants.METHOD_NAME_ATT);
            if (StringUtils.isEmpty(method))
            {
               result.add(Issue.warning(element,
                     Validation_Messages.MSG_CompletionMethodNotSpecified,
                     PredefinedConstants.METHOD_NAME_ATT));
            }
            else
            {
               MethodInfo info = typeFinder.getMethod(type, method);
               if (info == null)
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_CantFindMethodInClass, new Object[] {
                              method, type.getElementName()}),
                        PredefinedConstants.METHOD_NAME_ATT));
               }
               else if (!info.isAccessible())
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_MethodNotVisible, new Object[] {
                              method, type.getElementName()}),
                        PredefinedConstants.METHOD_NAME_ATT));
               }
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}
