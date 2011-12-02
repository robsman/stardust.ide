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

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class ExternalPackagesValidator implements IModelValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   public Issue[] validate(ModelType model) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      IConnectionManager connectionManager = model.getConnectionManager();
      if (model.getExternalPackages() != null)
      {
         for (Iterator<ExternalPackage> i = model.getExternalPackages()
               .getExternalPackage().iterator(); i.hasNext();)
         {
            ExternalPackage externalPackage = i.next();
            String uri = ExtendedAttributeUtil.getAttributeValue(externalPackage
                  .getExtendedAttributes(), IConnectionManager.URI_ATTRIBUTE_NAME);
            IConnection connection = connectionManager.findConnection(uri);
            if (connection == null)
            {
               result.add(Issue.error(externalPackage, MessageFormat.format(
                     Validation_Messages.MODEL_Connection_NoConnection,
                     new Object[] {externalPackage.getName()}),
                     IConnectionManager.URI_ATTRIBUTE_NAME));
            }
            else
            {
               ModelType referencedModel = null;
               referencedModel = connectionManager.find(externalPackage);
               if (referencedModel == null)
               {
                  result.add(Issue.error(externalPackage, MessageFormat.format(
                        Validation_Messages.MODEL_ReferencedModel_NoModel,
                        new Object[] {externalPackage.getHref()}), XpdlPackage.eINSTANCE
                        .getExternalPackage_Href()));
               }
               else
               {
                  if (!referencedModel.getId().equals(externalPackage.getHref()))
                  {
                     result.add(Issue.warning(externalPackage, MessageFormat.format(
                           Validation_Messages.MODEL_ExternalPackage_IDConflict,
                           new Object[] {externalPackage.getHref()}),
                           XpdlPackage.eINSTANCE.getExternalPackage_Href()));
                  }
               }
            }
         }
      }
      return (Issue[]) result.toArray(ISSUE_ARRAY);
   }
}