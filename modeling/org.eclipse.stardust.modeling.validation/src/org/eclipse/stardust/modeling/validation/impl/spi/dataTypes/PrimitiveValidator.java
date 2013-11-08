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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.validation.*;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
 
public class PrimitiveValidator implements IModelElementValidator, IBridgeObjectProvider, AccessPathEvaluationContext.Aware
{
   private AccessPathEvaluationContext context;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      String type = AttributeUtil.getAttributeValue((IExtensibleElement) element, CarnotConstants.TYPE_ATT);
      if (StringUtils.isEmpty(type))
      {
         return new Issue[] {Issue.warning(element,
               Validation_Messages.MSG_PRIMITIVE_UnspecifiedType)};
      }
      else
      {
         if (type.equals(Type.Enumeration.toString()))
         {
            EObject ref = AttributeUtil.getIdentifiable((IExtensibleElement) element, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if (!(ref instanceof TypeDeclarationType))
            {
               return new Issue[] {Issue.error(element, 
                     Validation_Messages.MSG_NoTypeDeclarationDefined, 
                     StructuredDataConstants.TYPE_DECLARATION_ATT)};
            }
            else
            {
               String defaultValue = AttributeUtil.getAttributeValue((IExtensibleElement) element, CarnotConstants.DEFAULT_VALUE_ATT);            
               if (StringUtils.isEmpty(defaultValue))
               {
                  return new Issue[] {Issue.error(element, 
                        Validation_Messages.MSG_NoEnumerationDefaultValue, 
                        CarnotConstants.DEFAULT_VALUE_ATT)};
               }
               else
               {
                  XSDSimpleTypeDefinition def = TypeDeclarationUtils.getSimpleType((TypeDeclarationType) ref);
                  if (def != null)
                  {
                     XSDEnumerationFacet effectiveFacet = def.getEffectiveEnumerationFacet();
                     for (Object value : effectiveFacet.getValue())
                     {
                        if (defaultValue.equals(value))
                        {
                           return Issue.ISSUE_ARRAY;
                        }
                     }                     
                  }
                  return new Issue[] {Issue.error(element, MessageFormat.format(
                        Validation_Messages.MSG_InvalidEnumerationDefaultValue, new Object[] {defaultValue}),
                        CarnotConstants.DEFAULT_VALUE_ATT)};
               }
            }
         }
         return Issue.ISSUE_ARRAY;
      }
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