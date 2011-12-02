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
package org.eclipse.stardust.modeling.integration.ejb30.entity;

import java.util.ArrayList;
import java.util.List;
import java.text.MessageFormat;

import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.ejb.Handle;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.*;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;

import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

public class EntityBean30Validator implements IModelElementValidator, IBridgeObjectProvider
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      TypeFinder typeFinder = new TypeFinder(element);
      IType type = null;

      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
         PredefinedConstants.CLASS_NAME_ATT);
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoClass,
            PredefinedConstants.CLASS_NAME_ATT));
      }
      else
      {
         type = typeFinder.findExactType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.CLASS_NAME_ATT));
         }
      }

      // only space characters is not valid
      String jndiPath = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.JNDI_PATH_ATT);
      if (jndiPath == null || StringUtils.isEmpty(jndiPath.trim()))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoJNDI,
            CarnotConstants.JNDI_PATH_ATT));
      }
      
      // TODO !

      return result.toArray(Issue.ISSUE_ARRAY);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction) throws ValidationException
   {
      // TODO: misuse of the JavaDataTypeUtils.getBridgeObject
      // code should be moved right here.
      BridgeObject javaBridge = JavaDataTypeUtils.getBridgeObject(accessPoint, accessPath, direction);
      return new EntityBeanBridgeObject(javaBridge.getEndClass(), direction);
   }

   private class EntityBeanBridgeObject extends BridgeObject
   {
      public EntityBeanBridgeObject(IType type, DirectionType direction)
      {
         super(type, direction);
      }

      public boolean acceptAssignmentFrom(BridgeObject rhs)
      {
         // direction must be in or inout or null
         if (getDirection() == DirectionType.OUT_LITERAL)
         {
            return false;
         }
         // rhs direction must be out, inout or null
         if (rhs.getDirection() == DirectionType.IN_LITERAL)
         {
            return false;
         }
         return TypeFinder.isAssignable(getEndClass(), rhs.getEndClass())
               || TypeFinder.isAssignable(rhs.getEndClass(), JavaDataTypeUtils
                     .getTypeFromCurrentProject(EJBObject.class.getName()))
               || TypeFinder.isAssignable(rhs.getEndClass(), JavaDataTypeUtils
                     .getTypeFromCurrentProject(EJBLocalObject.class.getName()))
               || TypeFinder.isAssignable(rhs.getEndClass(), JavaDataTypeUtils
                     .getTypeFromCurrentProject(Handle.class.getName()));
      }
   }
}
