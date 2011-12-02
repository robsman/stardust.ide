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
package org.eclipse.stardust.modeling.integration.ejb30.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.integration.ejb30.entity.MyASTVisitor;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;


import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

/**
 * 
 * @author herinean
 * @version $Revision$
 */
public class SessionBean30Validator implements IModelElementValidator
{
   private final static String[] methodAttrNames = {
         PredefinedConstants.CREATE_METHOD_NAME_ATT,
         PredefinedConstants.METHOD_NAME_ATT};
   private ModelType model;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      model = (ModelType) element.eContainer();
      List<Issue> result = new ArrayList<Issue>();
      
      if (element instanceof ApplicationType)
      {
         ApplicationType sessionBean = (ApplicationType) element;
         checkInterface(result, sessionBean);
         checkBean(result, sessionBean);
         checkJndiPath(result, sessionBean);
      }
      return result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkInterface(List<Issue> result, ApplicationType sessionBean)
   {
      TypeFinder typeFinder = new TypeFinder(sessionBean);
      
      String className = AttributeUtil.getAttributeValue(sessionBean, PredefinedConstants.REMOTE_INTERFACE_ATT);
      className = VariableContextHelper.getInstance().getContext(model)
            .replaceAllVariablesByDefaultValue(className);
      if (className == null || StringUtils.isEmpty(className.trim()))
      {
         result.add(Issue.warning(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_InterfaceNotSpecified,
               new Object[] {"Business"}), PredefinedConstants.REMOTE_INTERFACE_ATT)); //$NON-NLS-1$
      }
      else
      {
         IType rType = typeFinder.findExactType(className);
         if (null == rType)
         {
            result.add(Issue.error(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.REMOTE_INTERFACE_ATT));
         }
         else
         {
            for (int i = 0; i < methodAttrNames.length; i++)
            {
               checkMethod(result, sessionBean, typeFinder, rType, className,
                     methodAttrNames[i]);
            }
         }
      }
   }

   private void checkBean(List<Issue> result, ApplicationType sessionBean)
   {
      TypeFinder typeFinder = new TypeFinder(sessionBean);
      String className = AttributeUtil.getAttributeValue(sessionBean, PredefinedConstants.CLASS_NAME_ATT);
      className = VariableContextHelper.getInstance().getContext(model)
            .replaceAllVariablesByDefaultValue(className);
      if (!StringUtils.isEmpty(className))
      {
         IType rType = typeFinder.findExactType(className);
         if (null == rType)
         {
            result.add(Issue.error(sessionBean, MessageFormat.format(
               Validation_Messages.MSG_ClassCanNotBeResolved, new Object[] {className}),
               PredefinedConstants.CLASS_NAME_ATT));
         }
         else
         {
            try
            {
               TypeInfo type = new TypeInfo(typeFinder, rType, null);
               TypeDeclaration td = MyASTVisitor.findTypeDeclaration(type);
               Annotation stateless = MyASTVisitor.getAnnotation(type, td, Stateless.class);
               Annotation stateful = MyASTVisitor.getAnnotation(type, td, Stateful.class);
               if (stateless == null && stateful == null)
               {
                  result.add(Issue.warning(sessionBean, MessageFormat.format(
                        Validation_Messages.SessionBean30Validator_MissingAnnotation,
                        new Object[] {type.getFullName()}), PredefinedConstants.CLASS_NAME_ATT));
               }
            }
            catch (Exception e)
            {
               // ignore, it just means we cannot get hold on annotation model
            }
         }
      }
   }

   private void checkMethod(List<Issue> result, ApplicationType sessionBean,
         TypeFinder typeFinder, IType rType, String className, String attributeName)
   {
      String method = AttributeUtil.getAttributeValue(sessionBean, attributeName);
      if (!StringUtils.isEmpty(method))
      {
         MethodInfo info = typeFinder.getMethod(rType, method);
         if (info == null)
         {
            result.add(Issue.warning(sessionBean, MessageFormat.format(
                  Validation_Messages.MSG_CantFindMethodInClass, new Object[] {
                        method, className}), attributeName));
         }
      }
   }

   private void checkJndiPath(List<Issue> result, IExtensibleElement element)
   {
      String jndiPath = AttributeUtil.getAttributeValue(element, PredefinedConstants.JNDI_PATH_ATT); 
      if (jndiPath == null || jndiPath.trim().length() == 0)
      {
         result.add(Issue.warning((IModelElement) element, MessageFormat.format(
               Validation_Messages.Validation_MSG_JNDIPathNotSpecified, new Object[] {}),
               PredefinedConstants.JNDI_PATH_ATT));
      }
   }
}