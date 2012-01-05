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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.validation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.Constants;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

public class MessageSerializationApplicationValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List issues = new ArrayList();
      
      String messageFormat = AttributeUtil.getAttributeValue((IExtensibleElement) element, Constants.MESSAGE_FORMAT);
      if(StringUtils.isEmpty(messageFormat))
      {
         issues.add(new Issue(Issue.ERROR, element,                
               MessageFormat.format("Message Format not set for ''{0}''.",  //$NON-NLS-1$
                     new String[] {((ApplicationType) element).getId()}),
                     Constants.MESSAGE_FORMAT));                  
      }
      String declaredTypeId = getDeclaredTypeId((ApplicationType) element);
      if(StringUtils.isEmpty(declaredTypeId))
      {
         issues.add(new Issue(Issue.ERROR, element,                
               MessageFormat.format("No TypeDeclaration set for ''{0}''.",  //$NON-NLS-1$
                     new String[] {((ApplicationType) element).getId()}),
                     StructuredDataConstants.TYPE_DECLARATION_ATT));                  
      }
      else
      {
         ModelType model = ModelUtils.findContainingModel(element);
         TypeDeclarationType type = model.getTypeDeclarations().getTypeDeclaration(declaredTypeId);
         if(type == null)
         {
            issues.add(new Issue(Issue.ERROR, element,                
                  MessageFormat.format("Invalid TypeDeclaration: ''{0}''.",  //$NON-NLS-1$
                        new String[] {declaredTypeId}),
                        StructuredDataConstants.TYPE_DECLARATION_ATT));                              
         }
      }
      return (Issue[]) issues.toArray(new Issue[issues.size()]);
   }
   
   private String getDeclaredTypeId(ApplicationType application)
   {
      DataTypeType structuredDataType = ModelUtils.getDataType(application, StructuredDataConstants.STRUCTURED_DATA);
      for(int i = 0; i < application.getAccessPoint().size(); i++ )
      {
         AccessPointType accessPoint = (AccessPointType) application.getAccessPoint().get(i);
         if(accessPoint.getType().equals(structuredDataType))
         {
            return AttributeUtil.getAttributeValue(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
         }
      }
      return null;
   }   
}