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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class ReferencedModelElementValidator implements IModelElementValidator
{

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      AttributeType attribute = AttributeUtil.getAttribute((IExtensibleElement) element,
            "carnot:connection:uri"); //$NON-NLS-1$
      boolean invalid = false;
      if (attribute != null && !element.eIsProxy()) // TODO: validation that proxy elements are resolved
      {
         String uri = attribute.getValue();
         URI aRealUri = URI.createURI(uri);
         String typeName = aRealUri.lastSegment();
         ModelType modelType = ModelUtils.findContainingModel(element);
         Connection connection = (Connection) modelType.getConnectionManager()
               .findConnection(uri);
         if (connection.getAttribute("importByReference") != null //$NON-NLS-1$
               && !"false".equals(connection.getAttribute("importByReference"))) //$NON-NLS-1$ //$NON-NLS-2$
         {

            EObject o = modelType.getConnectionManager().find(
                  aRealUri.scheme().toString() + "://" + aRealUri.authority() + "/"); //$NON-NLS-1$ //$NON-NLS-2$
            ModelType referencedModel = (ModelType) Reflect.getFieldValue(o, "eObject"); //$NON-NLS-1$

            if (element instanceof DataType)
            {
               TypeDeclarationType type = referencedModel.getTypeDeclarations()
                     .getTypeDeclaration(typeName);
               invalid = (referencedModel.getTypeDeclarations().getTypeDeclaration(
                     typeName) == null);
               if (!invalid)
               {
                  if (Platform.isRunning() && !isPublic(type))
                  {
                     result.add(Issue.error(element, MessageFormat.format(
                           Validation_Messages.MODEL_ReferencedType_NotVisible,
                           new Object[] {typeName, referencedModel.getName()}),
                           StructuredDataConstants.TYPE_DECLARATION_ATT));
                  }
               }
               else
               {
                  result.add(Issue.error(element, MessageFormat.format(
                        Validation_Messages.MODEL_ReferencedType_NotFound, new Object[] {
                              typeName, referencedModel.getName()}),
                        StructuredDataConstants.TYPE_DECLARATION_ATT));
               }
            }

            if (element instanceof ActivityType)
            {
               boolean found = false;
               ApplicationType refApp = null;
               if (aRealUri.segment(0).equalsIgnoreCase("application")) //$NON-NLS-1$
               {
                  for (Iterator<ApplicationType> i = referencedModel.getApplication()
                        .iterator(); i.hasNext();)
                  {
                     ApplicationType referencedApp = i.next();
                     if (referencedApp.getId().equalsIgnoreCase(typeName))
                     {
                        found = true;
                        refApp = referencedApp;
                     }
                  }
                  invalid = !found;
                  if (!invalid)
                  {
                     if (Platform.isRunning() && !isPublic(refApp))
                     {
                        result.add(Issue.error(element, MessageFormat.format(
                              Validation_Messages.MODEL_ReferencedType_NotVisible,
                              new Object[] {typeName, referencedModel.getName()}),
                              ValidationService.PKG_CWM.getActivityType_Application()));
                     }
                  }
                  else
                  {
                     result.add(Issue.error(element, MessageFormat.format(
                           Validation_Messages.MODEL_ReferencedType_NotFound,
                           new Object[] {typeName, referencedModel.getName()}),
                           ValidationService.PKG_CWM.getActivityType_Application()));
                  }
               }
               if (aRealUri.segment(0).equalsIgnoreCase("processDefinition")) //$NON-NLS-1$
               {
                  for (Iterator<ProcessDefinitionType> i = referencedModel
                        .getProcessDefinition().iterator(); i.hasNext();)
                  {
                     ProcessDefinitionType referencedProcess = i.next();
                     if (referencedProcess.getId() != null
                           && referencedProcess.getId().equalsIgnoreCase(typeName))
                     {
                        found = true;
                     }
                  }
                  invalid = !found;
                  if (invalid)
                  {
                     result.add(Issue.error(element, MessageFormat.format(
                           Validation_Messages.MODEL_ReferencedType_NotFound,
                           new Object[] {typeName, referencedModel.getName()}),
                           ValidationService.PKG_CWM.getProcessDefinitionType()));
                  }
               }
            }
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   public boolean isPublic(EObject element)
   {
      String visibility = null;
      if (element instanceof IExtensibleElement)
      {
         AttributeType attributeType = AttributeUtil.getAttribute(
               (IExtensibleElement) element, PredefinedConstants.MODELELEMENT_VISIBILITY);
         if (attributeType != null)
         {
            visibility = attributeType.getValue();
         }
      }
      if (element instanceof Extensible)
      {
         ExtendedAttributeType attributeType = ExtendedAttributeUtil.getAttribute(
               ((Extensible) element).getExtendedAttributes(),
               PredefinedConstants.MODELELEMENT_VISIBILITY);
         if (attributeType != null)
         {
            visibility = attributeType.getValue();
         }
      }
      if (visibility == null)
      {
         Iterator<ConfigurationProvider> configProviders = ServiceLoader.load(
               ConfigurationProvider.class).iterator();
         ConfigurationProvider configProvider = configProviders.hasNext()
               ? configProviders.next()
               : null;
         String visibilityDefault = (null != configProvider) ? configProvider
               .getString("multiPackageModelingVisibility") : null; //$NON-NLS-1$
         if (visibilityDefault == null || visibilityDefault == "" //$NON-NLS-1$
               || visibilityDefault.equalsIgnoreCase("Public")) //$NON-NLS-1$
         {

            return true;
         }
      }
      return visibility.equalsIgnoreCase("public"); //$NON-NLS-1$
   }
}
