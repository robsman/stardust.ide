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
package org.eclipse.stardust.modeling.validation.impl.spi.contextTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;

public class JFCValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.CLASS_NAME_ATT);

      if (className == null)
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_JFC_UnspecifiedClass,
               PredefinedConstants.CLASS_NAME_ATT));
      }
      else
      {
         TypeFinder typeFinder = new TypeFinder(element);
         className = VariableContextHelper.getInstance().getContext(
               (ModelType) element.eContainer().eContainer())
               .replaceAllVariablesByDefaultValue(className);
         IType type = typeFinder.findExactType(className);
         if (null == type)
         {            
            result.add(Issue.warning(element,
                  MessageFormat.format(Validation_Messages.MSG_JFC_CouldntFindClass,
                        new String[] {className}), PredefinedConstants.CLASS_NAME_ATT));
         }
         else
         {
            String methodName = AttributeUtil.getAttributeValue(
                  (IExtensibleElement) element, PredefinedConstants.METHOD_NAME_ATT);
            if (methodName == null)
            {
               result.add(Issue.warning(element,
                     Validation_Messages.MSG_JFC_UnspecifiedCompletionMethod,
                     PredefinedConstants.METHOD_NAME_ATT));
            }
            else
            {               
               methodName = VariableContextHelper.getInstance().getContext(
                     (ModelType) element.eContainer().eContainer())
                     .replaceAllVariablesByDefaultValue(methodName);
               MethodInfo info = typeFinder.getMethod(type, methodName);
               if (info == null)
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_JFC_CouldntFindCompletionMethod,
                        new String[] {methodName}), PredefinedConstants.METHOD_NAME_ATT));
               }
               else if (!info.isAccessible())
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_MethodNotVisible, new Object[] {
                              methodName, type.getElementName()}),
                        PredefinedConstants.METHOD_NAME_ATT));
               }
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}