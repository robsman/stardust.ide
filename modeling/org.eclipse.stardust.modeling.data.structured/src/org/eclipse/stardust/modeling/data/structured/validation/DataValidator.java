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
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

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
      ModelType model = ModelUtils.findContainingModel(data);      
      String typeId = AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT);      
      if (StringUtils.isEmpty(typeId))
      {
         issues.add(new Issue(Issue.ERROR, element, Structured_Messages.DataValidator_NoType,
               StructuredDataConstants.TYPE_DECLARATION_ATT));         
      }
      else
      {
         TypeDeclarationsType declarations = model.getTypeDeclarations();      
         TypeDeclarationType type = declarations.getTypeDeclaration(typeId);
         if (type == null)
         {
            String message = Structured_Messages.DataValidator_InvalidType + typeId;
            // TODO: check other types when implemented
            issues.add(new Issue(Issue.WARNING, element, message,
                  StructuredDataConstants.TYPE_DECLARATION_ATT));
         }         
      }      
      return issues.toArray(new Issue[issues.size()]);
   }
}