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
package org.eclipse.stardust.modeling.validation.impl.spi.dataTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class SerializableValidator
      implements IModelElementValidator, IBridgeObjectProvider
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      TypeFinder typeFinder = new TypeFinder(element);

      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.CLASS_NAME_ATT);
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoClass,
            PredefinedConstants.CLASS_NAME_ATT));
      }
      else
      {
         VariableContext context = VariableContextHelper.getInstance().getContext(
               (ModelType) element.eContainer());
         if(context != null)
         {
            className = context.replaceAllVariablesByDefaultValue(className);            
         }
         
         TypeInfo type = typeFinder.findType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.CLASS_NAME_ATT));
         }
         else
         {
            if(type.implementsInterface("java.io.Serializable") || type.isInterface()) //$NON-NLS-1$
            {
               boolean autoInstantiate = AttributeUtil.getBooleanValue((IExtensibleElement) element,
                     PredefinedConstants.AUTO_INSTANTIATE_ATT);
               if (autoInstantiate)
               {
                  boolean hasDefaultConstructor = false;
                  try
                  {
                     List<MethodInfo> constructors = type.getConstructors();
                     for (int i = 0; i < constructors.size(); i++)
                     {
                        MethodInfo ctor = constructors.get(i);
                        if (ctor.getParameterCount() == 0)
                        {
                           hasDefaultConstructor = true;
                           break;
                        }
                     }
                  }
                  catch (JavaModelException e)
                  {
                     //
                  }
                  if (!hasDefaultConstructor)
                  {
                     result.add(Issue.error(element, MessageFormat.format(
                           Validation_Messages.SerializableValidator_NoDefaultConstructorMessage, new Object[] {className}),
                           PredefinedConstants.AUTO_INSTANTIATE_ATT));
                  }
               }
            }
            else
            {
               result.add(Issue.error(element, MessageFormat.format(
                     Validation_Messages.MSG_ClassNotSerilizable, new Object[] {className}),
                     PredefinedConstants.CLASS_NAME_ATT));               
            }
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction) throws ValidationException
   {
      return JavaDataTypeUtils.getBridgeObject(accessPoint, accessPath, direction);
   }
}