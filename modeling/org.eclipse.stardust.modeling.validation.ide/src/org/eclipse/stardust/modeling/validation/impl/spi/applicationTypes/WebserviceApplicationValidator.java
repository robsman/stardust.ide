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

import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.WorkspaceValidationUtils;
import org.eclipse.stardust.modeling.validation.util.ProjectClassLoader;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;

public class WebserviceApplicationValidator implements IModelElementValidator
{
   private static final String[] messages = {
         Validation_Messages.MSG_NoTypeMappingDefined,
         Validation_Messages.MSG_XMLTypeHasInvalidMapping,
         Validation_Messages.MSG_TemplateIsInvalid,
         Validation_Messages.MSG_WSDL_URLIsInvalid,
         Validation_Messages.MSG_PropertyNotSet};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();

      result.addAll(checkProperty(element, PredefinedConstants.WS_WSDL_URL_ATT));
      result.addAll(checkProperty(element, PredefinedConstants.WS_SERVICE_NAME_ATT));
      result.addAll(checkProperty(element, PredefinedConstants.WS_PORT_NAME_ATT));
      result.addAll(checkProperty(element, PredefinedConstants.WS_OPERATION_NAME_ATT));

      for (AttributeType attribute : ((IExtensibleElement) element).getAttribute())
      {
         String key = attribute.getName();

         if (key.startsWith(PredefinedConstants.WS_MAPPING_ATTR_PREFIX))
         {
            result.addAll(checkTypeMapping(element, attribute));
         }
         else if (key.startsWith(PredefinedConstants.WS_TEMPLATE_ATTR_PREFIX))
         {
            result.addAll(checkXmlTemplate(element, attribute));
         }
         else if (key.equals(PredefinedConstants.WS_WSDL_URL_ATT))
         {
            result.addAll(checkWsdlUrl(element, attribute));
         }
      }

      return result.toArray(Issue.ISSUE_ARRAY);
   }

   private List<Issue> checkTypeMapping(IModelElement element, AttributeType attribute)
   {
      List<Issue> result = CollectionUtils.newList();
      String key = attribute.getName();
      String xmlType = key.substring(PredefinedConstants.WS_MAPPING_ATTR_PREFIX.length());
      String clazz = attribute.getValue();

      if (StringUtils.isEmpty(clazz))
      {
         result.add(Issue.warning(element, MessageFormat.format(messages[0], xmlType)));
      }
      else
      {
         TypeFinder finder = new TypeFinder(element);
         if (finder.findType(clazz) == null)
         {
            result.add(Issue.warning(element, MessageFormat.format(messages[1], xmlType, clazz)));
         }
      }
      return result;
   }

   private List<Issue> checkXmlTemplate(IModelElement element, AttributeType attribute)
   {
      List<Issue> result = CollectionUtils.newList();
      String key = attribute.getName();
      String name = key.substring(PredefinedConstants.WS_TEMPLATE_ATTR_PREFIX.length());
      String xml = attribute.getValue();

      if (!StringUtils.isEmpty(xml))
      {
         try
         {
            XmlUtils.parseString(xml);
         }
         catch (Exception ex)
         {
            result.add(Issue.warning(element, MessageFormat.format(messages[2], name)));
         }
      }
      return result;
   }

   private List<Issue> checkWsdlUrl(IModelElement element, AttributeType attribute)
   {
      List<Issue> result = CollectionUtils.newList();
      String uri = attribute.getValue();
      if (uri != null)
      {
         uri = VariableContextHelper.getInstance().getContext(
               (ModelType) element.eContainer()).replaceAllVariablesByDefaultValue(uri);
      }
      if (!StringUtils.isEmpty(uri))
      {
         // need to override context class loader so we can find the resource from the
         // project classpath
         ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
         try
         {
            if (Platform.isRunning())
            {
               IProject project = WorkspaceValidationUtils.getProjectFromEObject(element);
               Thread.currentThread().setContextClassLoader(
                     new ProjectClassLoader(XmlUtils.class.getClassLoader(), project,
                           uri.startsWith("/") //$NON-NLS-1$
                                 ? uri.substring(1)
                                 : uri));
            }
            new URL(XmlUtils.resolveResourceUri(uri));
         }
         catch (Exception ex)
         {
            result.add(Issue.warning(element, MessageFormat.format(messages[3], uri)));
         }
         finally
         {
            // restoring previous context class loader
            Thread.currentThread().setContextClassLoader(cclBackup);
         }
      }
      return result;
   }

   private List<Issue> checkProperty(IModelElement element, String name)
   {
      List<Issue> result = CollectionUtils.newList();
      String property = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            name);
      if (StringUtils.isEmpty(property))
      {
         result.add(Issue.error(element, MessageFormat.format(messages[4], name), name));
      }
      return result;
   }

}
