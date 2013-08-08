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

import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.ejb.Handle;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;

public class EntityBean20Validator implements IModelElementValidator, IBridgeObjectProvider
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      TypeFinder typeFinder = new TypeFinder(element);
      IType type = null;

      boolean isLocal = AttributeUtil.getBooleanValue((IExtensibleElement) element,
         PredefinedConstants.IS_LOCAL_ATT);
      String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
         PredefinedConstants.REMOTE_INTERFACE_ATT);
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoRemoteIF,
            PredefinedConstants.REMOTE_INTERFACE_ATT));
      }
      else
      {
         type = typeFinder.findExactType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.REMOTE_INTERFACE_ATT));
         }
         else
         {
            if (!isLocal)
            {
               String[] interfaces = {className, javax.ejb.EJBObject.class.getName(),
                                  javax.ejb.EJBLocalObject.class.getName()};
               if (!typeFinder.implementsInterface(type, interfaces[1]) &&
                   !typeFinder.implementsInterface(type, interfaces[2]))
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_EntityBean_InvalidEjbTypeSignature,
                        interfaces), PredefinedConstants.REMOTE_INTERFACE_ATT));
               }
            }
         }
      }

      className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.HOME_INTERFACE_ATT);
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoHomeIF,
            PredefinedConstants.HOME_INTERFACE_ATT));
      }
      else
      {
         type = typeFinder.findExactType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.HOME_INTERFACE_ATT));
         }
         else
         {
            if (!isLocal)
            {
               String[] interfaces = {className, javax.ejb.EJBHome.class.getName(),
                                  javax.ejb.EJBLocalHome.class.getName()};
               if (!typeFinder.implementsInterface(type, interfaces[1]) &&
                   !typeFinder.implementsInterface(type, interfaces[2]))
               {
                  result.add(Issue.warning(element, MessageFormat.format(
                        Validation_Messages.MSG_EntityBean_InvalidEjbTypeSignature,
                        interfaces), PredefinedConstants.HOME_INTERFACE_ATT));
               }
            }
         }
      }

      className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.PRIMARY_KEY_ATT);
      if (StringUtils.isEmpty(className))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoPrimaryKey,
            PredefinedConstants.PRIMARY_KEY_ATT));
      }
      else
      {
         type = typeFinder.findExactType(className);
         if (null == type)
         {
            result.add(Issue.error(element, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.PRIMARY_KEY_ATT));
         }
      }

      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.JNDI_PATH_ATT)))
      {
         result.add(Issue.warning(element, Validation_Messages.MSG_NoJNDI,
            CarnotConstants.JNDI_PATH_ATT));
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction) throws ValidationException
   {
      BridgeObject javaBridge = JavaDataTypeUtils.getBridgeObject(accessPoint, accessPath, direction);
      return new EntityBeanBridgeObject(javaBridge.getEndClass(), javaBridge
            .getDirection());
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
