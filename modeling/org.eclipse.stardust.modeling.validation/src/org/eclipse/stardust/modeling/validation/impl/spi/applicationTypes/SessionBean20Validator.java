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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
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

import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

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

   private ModelType model;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      if (element instanceof ApplicationType)
      {
         ApplicationType sessionBean = (ApplicationType) element;

         ModelType model = (ModelType) element.eContainer();
         String createdTypeName = checkMethod(sessionBean, HOME, result);
         checkMethod(sessionBean, REMOTE, result);

         // #4153 checking if result of create method is assignment compatible
         String componentTypeName = AttributeUtil.getAttributeValue(sessionBean,
               classAttrNames[REMOTE]);

         if (!StringUtils.isEmpty(createdTypeName)
               && !StringUtils.isEmpty(componentTypeName))
         {
            TypeFinder typeFinder = new TypeFinder(sessionBean);
            IType createdType = typeFinder.findExactType(createdTypeName);
            IType componentType = typeFinder.findExactType(componentTypeName);

            if (!TypeFinder.isAssignable(componentType, createdType))
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

   private void checkJndiPath(List result, IExtensibleElement element)
   {
      if (AttributeUtil.getAttributeValue(element, classAttrNames[2]) == null)
      {
         result.add(Issue.warning((IModelElement) element, MessageFormat.format(
               Validation_Messages.Validation_MSG_JNDIPathNotSpecified, new String[] {}),
               classAttrNames[2]));
      }
   }

   private String checkMethod(ApplicationType sessionBean, int type, List issues)
   {
      TypeFinder typeFinder = new TypeFinder(sessionBean);
      IType iType = null;

      boolean isLocal = AttributeUtil.getBooleanValue(sessionBean,
            PredefinedConstants.IS_LOCAL_ATT);

      String className = AttributeUtil.getAttributeValue(sessionBean,
            classAttrNames[type]);
      
      className = VariableContextHelper.getInstance().getContext(model)
            .replaceAllVariablesByDefaultValue(className);

      String rType = null;

      if (className == null)
      {
         issues.add(Issue.warning(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_InterfaceNotSpecified,
               new String[] {interfaceNames[type]}), classAttrNames[type]));
      }
      else
      {
         iType = typeFinder.findExactType(className);
      }

      if (null == iType)
      {
         issues.add(Issue.error(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               classAttrNames[type]));
      }
      else
      {
         if (!isLocal)
         {
            String[] interfaces = type == REMOTE ? new String[] {
                  className, javax.ejb.EJBObject.class.getName(),
                  javax.ejb.EJBLocalObject.class.getName()} : new String[] {
                  className, javax.ejb.EJBHome.class.getName(),
                  javax.ejb.EJBLocalHome.class.getName()};
            if (!typeFinder.implementsInterface(iType, interfaces[1])
                  && !typeFinder.implementsInterface(iType, interfaces[2]))
            {
               issues.add(Issue.warning(sessionBean, MessageFormat.format(
                     Validation_Messages.MSG_SessionBean_InvalidEjbTypeSignature,
                     interfaces), classAttrNames[type]));
            }
         }
         String method = AttributeUtil.getAttributeValue(sessionBean,
               methodAttrNames[type]);
         if (StringUtils.isEmpty(method))
         {
            issues.add(Issue.warning(sessionBean, MessageFormat.format(
                  Validation_Messages.MSG_MethodNotSpecified,
                  new String[] {methodNames[type]}), methodAttrNames[type]));
         }
         else
         {
            MethodInfo info = typeFinder.getMethod(iType, method);
            if (info == null)
            {
               issues.add(Issue.warning(sessionBean, MessageFormat.format(
                     Validation_Messages.MSG_CantFindMethodInClass, new Object[] {
                           method, iType.getElementName()}), methodAttrNames[type]));
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