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
package org.eclipse.stardust.modeling.validation.impl.spi.applicationTypes;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class PlainJavaValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();
      TypeFinder typeFinder = new TypeFinder(element);
      TypeInfo type = null;

      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.CLASS_NAME_ATT);
      VariableContext variableContext = VariableContextHelper.getInstance().getContext(element);
      className = variableContext.replaceAllVariablesByDefaultValue(className);

      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_JavaClassNotSpecified,
               PredefinedConstants.CLASS_NAME_ATT));
      }
      else
      {
         type = typeFinder.findType(className);
         if (null == type)
         {
            result.add(Issue.error(element,
                  MessageFormat.format(Validation_Messages.MSG_ClassCanNotBeResolved, className),
                  PredefinedConstants.CLASS_NAME_ATT));
         }
      }

      String constructorName = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.CONSTRUCTOR_NAME_ATT);
      constructorName = variableContext.replaceAllVariablesByDefaultValue(constructorName);

      if (StringUtils.isEmpty(constructorName))
      {
         result.add(Issue.warning(element,
               Validation_Messages.MSG_ConstructorNotSpecified,
               PredefinedConstants.CONSTRUCTOR_NAME_ATT));
      }
      else
      {
         if (null != type)
         {
            MethodInfo constructor = typeFinder.getConstructor(type, constructorName);
            if (constructor == null)
            {
               result.add(Issue.warning(element, MessageFormat.format(
                     Validation_Messages.MSG_CouldntFindConstructor,
                           constructorName, type.getType().getElementName()),
                     PredefinedConstants.CONSTRUCTOR_NAME_ATT));
            }
            else if (!constructor.isAccessible())
            {
               result.add(Issue.warning(element, MessageFormat.format(
                     Validation_Messages.MSG_ConstructorNotVisible,
                           constructorName, type.getType().getElementName()),
                     PredefinedConstants.CONSTRUCTOR_NAME_ATT));
            }
         }
      }

      String method = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.METHOD_NAME_ATT);
      method = variableContext.replaceAllVariablesByDefaultValue(method);

      if (StringUtils.isEmpty(method))
      {
         result.add(Issue.warning(element,
               Validation_Messages.MSG_CompletionMethodNotSpecified,
               PredefinedConstants.METHOD_NAME_ATT));
      }
      else if (null != type)
      {
         MethodInfo info = typeFinder.getMethod(type, method);
         if (info == null)
         {
            result.add(Issue.warning(element, MessageFormat.format(
                  Validation_Messages.MSG_CantFindMethodInClass,
                        method, type.getType().getElementName()),
                  PredefinedConstants.METHOD_NAME_ATT));
         }
         else if (!info.isAccessible())
         {
            result.add(Issue.warning(element, MessageFormat.format(
                  Validation_Messages.MSG_MethodNotVisible,
                        method, type.getType().getElementName()),
                  PredefinedConstants.METHOD_NAME_ATT));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}