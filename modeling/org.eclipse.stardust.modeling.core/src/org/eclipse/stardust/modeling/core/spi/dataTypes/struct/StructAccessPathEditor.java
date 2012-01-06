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
package org.eclipse.stardust.modeling.core.spi.dataTypes.struct;

import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.runtime.beans.BigData;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;

public class StructAccessPathEditor implements IAccessPathEditor
{
   public List<AccessPointType> getAccessPoints(String hint, IExtensibleElement dataObject, DirectionType direction)
   {
      final List<AccessPointType> accessPoints = CollectionUtils.newList();
      
      if (dataObject instanceof DataType)
      {
         TypeDeclarationType declaration = null;
         if (GenericUtils.isStructuredDataType((DataType)dataObject))
         {
            declaration = StructuredTypeUtils.getTypeDeclaration((DataType)dataObject);
         }
         if (declaration == null)
         {
            return accessPoints;                  
         }
         
         IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(declaration);
         addAccessPoints(accessPoints, (IModelElement)dataObject, xPathMap.getRootXPath(), xPathMap, direction);
      }
      else if (dataObject instanceof StructAccessPointType)
      {
         StructAccessPointType ap = (StructAccessPointType) dataObject;
         addAccessPoints(accessPoints, ap.getType(), ap.getXPath(), ap.getXPathMap(), direction);
      }
      
      return accessPoints;
   }
   
   private void addAccessPoints(List<AccessPointType> accessPoints, IModelElement modelElement,
         TypedXPath xPath, IXPathMap xPathMap, DirectionType direction)
   {
      List<TypedXPath> childXPaths = xPath.getChildXPaths();
      for (int i = 0; i < childXPaths.size(); i++ )
      {
         TypedXPath childXPath = childXPaths.get(i);
         accessPoints.add(createElementAccessPoint(childXPath, xPathMap, modelElement, direction));
      }
   }

   private AccessPointType createElementAccessPoint(TypedXPath xPath, IXPathMap xPathMap,
         IModelElement modelElement, DirectionType direction)
   {
      DataTypeType dataType = null;
      String type = null;
      if (xPath.getType() == BigData.NULL)
      {
         // complex
         dataType = ModelUtils.getDataType(modelElement, StructuredDataConstants.STRUCTURED_DATA);
      }
      else
      {
         // simple
         dataType = ModelUtils.getDataType(modelElement, PredefinedConstants.PRIMITIVE_DATA);
         switch (xPath.getType())
         {
         case BigData.BOOLEAN: type = "boolean"; break; //$NON-NLS-1$
         case BigData.CHAR: type = "char"; break; //$NON-NLS-1$
         case BigData.BYTE: type = "byte"; break; //$NON-NLS-1$
         case BigData.SHORT: type = "short"; break; //$NON-NLS-1$
         case BigData.INTEGER: type = "int"; break; //$NON-NLS-1$
         case BigData.LONG: type = "long"; break; //$NON-NLS-1$
         case BigData.FLOAT: type = "float"; break; //$NON-NLS-1$
         case BigData.DOUBLE: type = "double"; break; //$NON-NLS-1$
         case BigData.STRING: type = "String"; break; //$NON-NLS-1$
         case BigData.DATE: type = "Timestamp"; break; //$NON-NLS-1$
         // (fh) other cases may not appear in an xsd document
         }
      }
      
      StructAccessPointType accessPoint = new StructAccessPointType(xPath, xPathMap);
      String name = StructuredDataXPathUtils.getLastXPathPart(xPath.getXPath());
      accessPoint.setId(name);
      accessPoint.setName(name);
      accessPoint.setType(dataType);
      accessPoint.setDirection(direction);
      AttributeUtil.setAttribute(accessPoint, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(accessPoint, PredefinedConstants.BROWSABLE_ATT, true);
      if (xPath.isList())
      {
         AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:indexed", true); //$NON-NLS-1$
      }
      AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
      if (type != null)
      {
         AttributeUtil.setAttribute(accessPoint, CarnotConstants.TYPE_ATT, type);
      }
      
      return accessPoint;
   }

   public String[] splitAccessPath(String accessPath)
   {
      int ix = accessPath.indexOf(StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR);
      return ix < 0 ? new String[] {accessPath, null}
         : new String[] {accessPath.substring(0, ix),
            accessPath.substring(ix + StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR.length())};
   }

   public boolean supportsBrowsing()
   {
      return true;
   }
}
