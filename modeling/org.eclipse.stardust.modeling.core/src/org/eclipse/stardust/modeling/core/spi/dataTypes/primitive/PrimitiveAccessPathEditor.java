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
package org.eclipse.stardust.modeling.core.spi.dataTypes.primitive;

import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.spi.dataTypes.serializable.SerializableAccessPathEditor;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PrimitiveAccessPathEditor extends SerializableAccessPathEditor
{
   protected String getClassName(IExtensibleElement data)
   {
      String className = null;
      String dataType = AttributeUtil.getAttributeValue((IExtensibleElement) data, CarnotConstants.TYPE_ATT);
      if(dataType.equals(Type.Enumeration.toString()))
      {
         TypeDeclarationType typeDeclaration = null;                                    
         String typeDeclarationId = AttributeUtil.getAttributeValue((IExtensibleElement) data, StructuredDataConstants.TYPE_DECLARATION_ATT);
         if(!StringUtils.isEmpty(typeDeclarationId))
         {
            ModelType model = ModelUtils.findContainingModel(data);
            if (model != null)
            {
               TypeDeclarationsType declarations = model.getTypeDeclarations();
               if (declarations != null)
               {
                  typeDeclaration = declarations.getTypeDeclaration(typeDeclarationId);
               }
            }
         }
         if(typeDeclaration != null)
         {
            XSDNamedComponent component = TypeDeclarationUtils.getSimpleType(typeDeclaration);
            if (component instanceof XSDSimpleTypeDefinition)
            {
               className = ExtendedAttributeUtil.getAttributeValue(typeDeclaration, CarnotConstants.CLASS_NAME_ATT);            
               if(!StringUtils.isEmpty(className))
               {
                  return className;
               }               
            }  
         }            
      }
      else
      {
         className = AttributeUtil.getAttributeValue(
               data, CarnotConstants.TYPE_ATT);         
      }
      if(StringUtils.isEmpty(className))
      {
         return null;
      }
      
      Class type = Reflect.getClassFromAbbreviatedName(className);
      return (null != type) ? type.getName() : null;
   }
}