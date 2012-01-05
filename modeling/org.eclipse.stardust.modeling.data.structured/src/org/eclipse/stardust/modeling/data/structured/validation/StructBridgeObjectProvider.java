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

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.spi.StructDataTransformerKey;
import org.eclipse.stardust.engine.core.struct.spi.StructuredDataTransformation;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.Path;
import org.eclipse.stardust.modeling.validation.util.PathEntry;

import ag.carnot.workflow.model.PredefinedConstants;

// TODO: implement
public class StructBridgeObjectProvider implements IBridgeObjectProvider
{
   private static Map<String, String> mapping = new HashMap<String, String>();
   
   private StructuredDataTransformation xomDomTrans;
   private ITypedElement element; 
   
   static
   {
      // <TODO> (fh)
      // must be synchronized with the full range of xsd types
      // </TODO>
      mapping.put("string", String.class.getName()); //$NON-NLS-1$
      mapping.put("boolean", Boolean.class.getName()); //$NON-NLS-1$
      mapping.put("int", Integer.class.getName()); //$NON-NLS-1$
      mapping.put("long", Long.class.getName()); //$NON-NLS-1$
      mapping.put("short", Short.class.getName()); //$NON-NLS-1$
      mapping.put("byte", Byte.class.getName()); //$NON-NLS-1$
      mapping.put("double", Double.class.getName()); //$NON-NLS-1$
      mapping.put("float", Float.class.getName()); //$NON-NLS-1$
      mapping.put("decimal", BigDecimal.class.getName()); //$NON-NLS-1$
      mapping.put("dateTime", Date.class.getName()); //$NON-NLS-1$
      mapping.put("date", Date.class.getName()); //$NON-NLS-1$
      mapping.put("time", Date.class.getName()); //$NON-NLS-1$
      mapping.put("duration", Long.class.getName()); //$NON-NLS-1$
   }
   
   public BridgeObject getBridgeObject(ITypedElement ap, String accessPath,
         DirectionType direction) throws ValidationException
   {	  
	   PathEntry entry = new PathEntry(ap, direction);
		if (accessPath != null) {
			xomDomTrans = StructuredDataTransformation.valueOf(accessPath);
			element = ap;
			if (ap instanceof AccessPointType) {
				if (ap.getMetaType().getId().startsWith("struct")) { //$NON-NLS-1$
					entry = this.getPathByAccessPointType(ap, accessPath,
							direction);
				}
			}
		}
	  String javaType = null;
	  if (accessPath != null)
	  {
	     Path path = new Path(entry);	     	     
         if (accessPath.matches(StructuredTypeUtils.TRANSFORMATION_PATTERN.pattern())) {
            String xomDomPath = accessPath;
            String copy = accessPath;
            copy = copy.substring(4);
            copy = copy.substring(0, copy.length() - 1);
            path.setMethod(copy);
            if (!StructuredTypeUtils.isValidDomAccessPath((DataType)element, accessPath)) {               
               throw new ValidationException(Structured_Messages.EXC_XOM_DOM_TRANSFORMATION_IS_NOT_VALID_FOR_THIS_DATAPATH, xomDomPath); 
            }
         }  
         try {
            path.setMethod(accessPath);            
         } catch (ValidationException ve) {
            if (accessPath != null && accessPath.matches(StructuredTypeUtils.TRANSFORMATION_PATTERN.pattern())) {
               // igonore this excpetion in that case
            } else {
               throw ve;
            }
         }
	     entry = path.getSelection();
      }
      if (entry.isSingle())
      {
         if (PredefinedConstants.STRUCTURED_DATA.equals(entry.getElement().getMetaType().getId()))
         {
            if (isDOM(xomDomTrans))
            {
               javaType = org.w3c.dom.Element.class.getName();
            }
            else
            {
               if (isEnumeration(entry))
               {
                  // currently enumerations can only be of type string
                  javaType = String.class.getName();
               }
               else
               {
                  javaType = Map.class.getName();
               }
            }
         }
         else
         {
            javaType = entry.getSimpleTypeName();
         }
      }
      else // entries with cardinality > 1 are always lists
      {
         javaType = List.class.getName();
      }
      IType accessPointType = JavaDataTypeUtils.getTypeFromCurrentProject(javaType);
      return new StructBridgeObject(accessPointType, direction, entry);
   }

   private boolean isEnumeration (PathEntry entry)
   {
      ITypedElement data = entry.getElement();
      if (data instanceof DataType || data instanceof AccessPointType)
      {
         TypeDeclarationType type = (TypeDeclarationType) AttributeUtil.getIdentifiable(
               (IExtensibleElement) data, StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (type != null)
         {
            if (TypeDeclarationUtils.getType(type) == TypeDeclarationUtils.SIMPLE_TYPE)
            {
               return true;
            }            
         }
      }      
      return false;
   }
   
   private boolean isDOM(StructuredDataTransformation trans) {
		return trans != null
				&& trans.getType().toString().equalsIgnoreCase(
						StructDataTransformerKey.DOM);
	}

	private PathEntry getPathByAccessPointType(ITypedElement ap,
			String accessPath, DirectionType direction) {
		ModelType modelType = ModelUtils.findContainingModel(ap);
		AccessPointType apt = (AccessPointType) ap;
		String type = AttributeUtil.getAttributeValue(((AccessPointType) apt)
				.getAttribute(), "carnot:engine:dataType"); //$NON-NLS-1$
		TypeDeclarationType decl = modelType.getTypeDeclarations()
				.getTypeDeclaration(type);
		IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(decl);
		ap = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);
		((AccessPointType) ap).setType((DataTypeType) apt.getMetaType());
		IIdentifiableElement d = (IIdentifiableElement) apt;
		((AccessPointType) ap).setId(d.getId());
		((AccessPointType) ap).setName(d.getName());
		PathEntry root = new PathEntry(ap, direction);
		return root;
	}
}