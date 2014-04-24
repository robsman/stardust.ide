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
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
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

public class SessionBean20Validator implements IModelElementValidator
{
   private static final int HOME = 0;

   private static final int REMOTE = 1;

   private final static String[] classAttrNames = {
         PredefinedConstants.HOME_INTERFACE_ATT,
         PredefinedConstants.REMOTE_INTERFACE_ATT, PredefinedConstants.JNDI_PATH_ATT};

   private final static String[] interfaceNames = {"Home", "Remote"}; //$NON-NLS-1$ //$NON-NLS-2$

   private final static String[] methodAttrNames = {
         PredefinedConstants.CREATE_METHOD_NAME_ATT, PredefinedConstants.METHOD_NAME_ATT};

   private final static String[] methodNames = {"Creation", "Completion"}; //$NON-NLS-1$ //$NON-NLS-2$

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();

      if (element instanceof ApplicationType)
      {
         ApplicationType sessionBean = (ApplicationType) element;

         String createdTypeName = checkMethod(sessionBean, HOME, result);
         checkMethod(sessionBean, REMOTE, result);

         // #4153 checking if result of create method is assignment compatible
         String componentTypeName = AttributeUtil.getAttributeValue(sessionBean,
               classAttrNames[REMOTE]);

         if (!StringUtils.isEmpty(createdTypeName)
               && !StringUtils.isEmpty(componentTypeName))
         {
            TypeFinder typeFinder = new TypeFinder(sessionBean);
            TypeInfo createdType = typeFinder.findType(createdTypeName);
            TypeInfo componentType = typeFinder.findType(componentTypeName);

            if (!TypeFinder.isAssignable(componentType.getType(), createdType.getType()))
            {
               result.add(Issue.warning(sessionBean,
                     Validation_Messages.SessionBean_IncompatibleCreatedType,
                     PredefinedConstants.CREATE_METHOD_NAME_ATT));
            }
         }

         checkJndiPath(result, sessionBean);
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkJndiPath(List<Issue> result, IExtensibleElement element)
   {
      if (AttributeUtil.getAttributeValue(element, classAttrNames[2]) == null)
      {
         result.add(Issue.warning((IModelElement) element,
               Validation_Messages.Validation_MSG_JNDIPathNotSpecified,
               classAttrNames[2]));
      }
   }

   private String checkMethod(ApplicationType sessionBean, int type, List<Issue> issues)
   {
      TypeFinder typeFinder = new TypeFinder(sessionBean);
      TypeInfo iType = null;

      boolean isLocal = AttributeUtil.getBooleanValue(sessionBean,
            PredefinedConstants.IS_LOCAL_ATT);

      String className = AttributeUtil.getAttributeValue(sessionBean,
            classAttrNames[type]);

      VariableContext variableContext = VariableContextHelper.getInstance().getContext(sessionBean);
      className = variableContext.replaceAllVariablesByDefaultValue(className);

      String rType = null;

      if (className == null)
      {
         issues.add(Issue.warning(sessionBean,
               MessageFormat.format(Validation_Messages.MSG_InterfaceNotSpecified, interfaceNames[type]),
               classAttrNames[type]));
      }
      else
      {
         iType = typeFinder.findType(className);
      }

      if (null == iType)
      {
         issues.add(Issue.error(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, className),
               classAttrNames[type]));
      }
      else
      {
         if (!isLocal)
         {
            Object[] interfaces = type == REMOTE
                  ? new Object[] {
                        className,
                        javax.ejb.EJBObject.class.getName(),
                        javax.ejb.EJBLocalObject.class.getName()}
                  : new Object[] {
                        className,
                        javax.ejb.EJBHome.class.getName(),
                        javax.ejb.EJBLocalHome.class.getName()};
            if (!iType.implementsInterface((String) interfaces[1])
                  && !iType.implementsInterface((String) interfaces[2]))
            {
               issues.add(Issue.warning(sessionBean,
                     MessageFormat.format(Validation_Messages.MSG_SessionBean_InvalidEjbTypeSignature, interfaces),
                     classAttrNames[type]));
            }
         }
         String method = AttributeUtil.getAttributeValue(sessionBean,
               methodAttrNames[type]);
         if (StringUtils.isEmpty(method))
         {
            issues.add(Issue.warning(sessionBean,
                  MessageFormat.format(Validation_Messages.MSG_MethodNotSpecified, methodNames[type]),
                  methodAttrNames[type]));
         }
         else
         {
            MethodInfo info = typeFinder.getMethod(iType, method);
            if (info == null)
            {
               issues.add(Issue.warning(sessionBean,
                     MessageFormat.format(Validation_Messages.MSG_CantFindMethodInClass,
                           method, iType.getType().getElementName()),
                     methodAttrNames[type]));
            }
            else
            {
               rType = info.getReturnType();
            }
         }
      }

      return rType;
   }
}