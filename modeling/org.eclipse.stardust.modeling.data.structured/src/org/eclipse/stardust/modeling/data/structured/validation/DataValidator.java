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
package org.eclipse.stardust.modeling.data.structured.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

public class DataValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> issues = new ArrayList<Issue>();
      DataType data = (DataType) element;
      if (data.getExternalReference() != null)
      {
         // Validation for external references takes place in the ReferencedModelElementValidator
         return null;
      }

      String typeId = AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT);
      if (StringUtils.isEmpty(typeId))
      {
         issues.add(new Issue(Issue.ERROR, element, Structured_Messages.DataValidator_NoType,
               StructuredDataConstants.TYPE_DECLARATION_ATT));
      }
      else
      {
         TypeDeclarationType type = StructuredTypeUtils.getTypeDeclaration(data);

         if (type == null)
         {
            String message = Structured_Messages.DataValidator_InvalidType + typeId;
            // TODO: check other types when implemented
            issues.add(new Issue(Issue.WARNING, element, message,
                  StructuredDataConstants.TYPE_DECLARATION_ATT));
         }
         else
         {
            if (TypeDeclarationUtils.isEnumeration(type, true))
            {
               issues.add(new Issue(Issue.ERROR, element, Structured_Messages.MSG_TypeDeclarationJavaBoundEnum,
                     StructuredDataConstants.TYPE_DECLARATION_ATT));
            }
         }
      }
      return issues.toArray(new Issue[issues.size()]);
   }
}