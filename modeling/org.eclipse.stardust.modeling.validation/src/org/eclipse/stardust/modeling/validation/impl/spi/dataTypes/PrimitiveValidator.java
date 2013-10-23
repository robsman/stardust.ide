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
package org.eclipse.stardust.modeling.validation.impl.spi.dataTypes;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.validation.*;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
 
public class PrimitiveValidator implements IModelElementValidator, IBridgeObjectProvider, AccessPathEvaluationContext.Aware
{
   private static final String MESSAGES = Validation_Messages.MSG_PRIMITIVE_UnspecifiedType;
   
   private AccessPathEvaluationContext context;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();
      String type = AttributeUtil.getAttributeValue((IExtensibleElement) element, CarnotConstants.TYPE_ATT);
      if (StringUtils.isEmpty(type))
      {
         result.add(Issue.warning(element, MESSAGES));
      }
      else
      {
         if(type.equals(Type.Enumeration.toString()))
         {
            TypeDeclarationType typeDeclaration = null;                        
            String typeDeclarationId = AttributeUtil.getAttributeValue((IExtensibleElement) element, StructuredDataConstants.TYPE_DECLARATION_ATT);                        
            if(!StringUtils.isEmpty(typeDeclarationId))
            {
               ModelType model = ModelUtils.findContainingModel(element);
               if (model != null)
               {
                  TypeDeclarationsType declarations = model.getTypeDeclarations();
                  if (declarations != null)
                  {
                     typeDeclaration = declarations.getTypeDeclaration(typeDeclarationId);
                  }
                  if(typeDeclaration == null)
                  {
                     result.add(Issue.error(element, MessageFormat.format(
                           Validation_Messages.MSG_NoTypeDeclarationFound, new Object[] {typeDeclarationId}),
                           StructuredDataConstants.TYPE_DECLARATION_ATT));
                  }     
               }
            }
            else
            {
               result.add(Issue.error(element, 
                     Validation_Messages.MSG_NoTypeDeclarationDefined, 
                     StructuredDataConstants.TYPE_DECLARATION_ATT));
            }

            String defaultValue = AttributeUtil.getAttributeValue((IExtensibleElement) element, CarnotConstants.DEFAULT_VALUE_ATT);            
            if(StringUtils.isEmpty(defaultValue))
            {
               result.add(Issue.error(element, 
                     Validation_Messages.MSG_NoEnumerationDefaultValue, 
                     CarnotConstants.DEFAULT_VALUE_ATT));
            }
            else
            {
               boolean match = false;               
               if(typeDeclaration != null)
               {
                  XSDNamedComponent component = TypeDeclarationUtils.getSimpleType(typeDeclaration);
                  if (component instanceof XSDSimpleTypeDefinition)
                  {
                     XSDEnumerationFacet effectiveFacet = ((XSDSimpleTypeDefinition) component).getEffectiveEnumerationFacet();
                     for(Object object : effectiveFacet.getValue())
                     {
                        if(object instanceof String)
                        {
                           if(defaultValue.equals(object))
                           {
                              match = true;
                              break;
                           }
                        }
                     }                     
                  }
               }
               if(!match)
               {
                  result.add(Issue.error(element, MessageFormat.format(
                        Validation_Messages.MSG_InvalidEnumerationDefaultValue, new Object[] {defaultValue}),
                        CarnotConstants.DEFAULT_VALUE_ATT));
               }               
            }
         }
      }
      
      return result.toArray(Issue.ISSUE_ARRAY);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath, DirectionType direction) throws ValidationException
   {      
      return JavaDataTypeUtils.getBridgeObject(accessPoint, accessPath, direction, context);
   }

   @Override
   public void setContext(AccessPathEvaluationContext context)
   {
      this.context = context;
   }
}