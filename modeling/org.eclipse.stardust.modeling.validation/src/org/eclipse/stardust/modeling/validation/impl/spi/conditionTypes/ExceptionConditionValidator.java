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
package org.eclipse.stardust.modeling.validation.impl.spi.conditionTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;


public class ExceptionConditionValidator implements IModelElementValidator
{

   private static final String EXCEPTION_CLASS_ATT = CarnotConstants.ENGINE_SCOPE
         + "exceptionName"; //$NON-NLS-1$

   private static final String[] messages = {
         Validation_Messages.MSG_IsNotException, Validation_Messages.MSG_MissingException};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      try
      {
         String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               EXCEPTION_CLASS_ATT);
         if (className != null)
         {
            if (!TypeFinder.isAssignable(JavaDataTypeUtils
                  .getTypeFromCurrentProject(java.lang.Exception.class.getName()),
                  JavaDataTypeUtils.getTypeFromCurrentProject(className)))
            {
               result.add(Issue.warning(element, MessageFormat.format(messages[0],
                     new String[] {className})));
            }
         }
         else
         {
            result.add(Issue.warning(element, MessageFormat.format(messages[1],
                  new String[] {((IIdentifiableModelElement) element).getName()})));
         }

      }
      catch (Exception ex)
      {
         result.add(Issue.warning(element, ex.getMessage()));
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}