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
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.Constants;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

public class MessageSerializationApplicationValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> issues = CollectionUtils.newList();
      
      String messageFormat = AttributeUtil.getAttributeValue((IExtensibleElement) element, Constants.MESSAGE_FORMAT);
      if(StringUtils.isEmpty(messageFormat))
      {
         issues.add(new Issue(Issue.ERROR, element,                
               MessageFormat.format("Message Format not set for ''{0}''.",  //$NON-NLS-1$
                     ((ApplicationType) element).getId()),
                     Constants.MESSAGE_FORMAT));                  
      }
      String declaredTypeId = getDeclaredTypeId((ApplicationType) element);
      if (StringUtils.isEmpty(declaredTypeId))
      {
         issues.add(new Issue(Issue.ERROR, element,                
               MessageFormat.format("No TypeDeclaration set for ''{0}''.",  //$NON-NLS-1$
                     ((ApplicationType) element).getId()),
                     StructuredDataConstants.TYPE_DECLARATION_ATT));                  
      }
      else
      {
         ModelType model = ModelUtils.findContainingModel(element);
         int ix = declaredTypeId.indexOf(":{"); //$NON-NLS-1$
         if (ix > 0 && declaredTypeId.substring(0, ix).equals(XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration().getName()))
         {
            ExternalPackages packages = model.getExternalPackages();
            if (packages != null)
            {
               QName qname = QName.valueOf(declaredTypeId.substring(ix + 1));
               ExternalPackage pkg = packages.getExternalPackage(qname.getNamespaceURI());
               if (pkg != null)
               {
                  ModelType otherModel = ModelUtils.getExternalModel(pkg);
                  if (otherModel != null)
                  {
                     model = otherModel;
                     declaredTypeId = qname.getLocalPart();
                  }
               }
            }
         }
         TypeDeclarationType type = model.getTypeDeclarations().getTypeDeclaration(declaredTypeId);
         if(type == null)
         {
            issues.add(new Issue(Issue.ERROR, element,                
                  MessageFormat.format("Invalid TypeDeclaration: ''{0}''.",  //$NON-NLS-1$
                        declaredTypeId),
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