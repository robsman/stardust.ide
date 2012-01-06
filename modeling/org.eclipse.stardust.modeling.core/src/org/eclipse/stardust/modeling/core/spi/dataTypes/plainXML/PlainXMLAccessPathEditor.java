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
package org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML;

import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
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
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

import ag.carnot.workflow.runtime.beans.BigData;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PlainXMLAccessPathEditor implements IAccessPathEditor
{
   public static final String SEPARATOR = "/"; //$NON-NLS-1$
   
   public List getAccessPoints(String hint, IExtensibleElement dataObject, DirectionType direction)
   {
      final List accessPoints = CollectionUtils.newList();
      
      // null, data, direction
      if (dataObject instanceof DataType)
      {
         IXPathMap xPathMap = PlainXMLUtils.getXPathMap((DataType)dataObject);
         if(xPathMap != null)
         {
            addAccessPoints(accessPoints, (IModelElement)dataObject, xPathMap.getRootXPath(), xPathMap, direction);
         }
      }
      // null, PlainXMLAccessPointType, direction
      else if (dataObject instanceof PlainXMLAccessPointType)
      {
         PlainXMLAccessPointType ap = (PlainXMLAccessPointType) dataObject;
         addAccessPoints(accessPoints, ap.getType(), ap.getXPath(), ap.getXPathMap(), direction);
      }
      
      return accessPoints;
   }
   
   private void addAccessPoints(List accessPoints, IModelElement modelElement,
         TypedXPath xPath, IXPathMap xPathMap, DirectionType direction)
   {
      List /* <TypedXPath> */childXPaths = xPath.getChildXPaths();
      for (int i = 0; i < childXPaths.size(); i++ )
      {
         TypedXPath childXPath = (TypedXPath) childXPaths.get(i);
         accessPoints.add(createElementAccessPoint(childXPath, xPathMap, modelElement, direction));
      }
   }

   private AccessPointType createElementAccessPoint(TypedXPath xPath, IXPathMap xPathMap,
         IModelElement modelElement, DirectionType direction)
   {
      DataTypeType dataType = null;
      if (xPath.getType() == BigData.NULL)
      {
         // complex
         dataType = ModelUtils.getDataType(modelElement, StructuredDataConstants.STRUCTURED_DATA);
      }
      else
      {
         // simple
         dataType = ModelUtils.getDataType(modelElement, PredefinedConstants.PRIMITIVE_DATA);
      }      
      
      PlainXMLAccessPointType accessPoint = new PlainXMLAccessPointType(xPath, xPathMap);
      String name = StructuredDataXPathUtils.getLastXPathPart(xPath.getXPath());
      accessPoint.setId(name);
      accessPoint.setName(name);
      accessPoint.setType(dataType);
      accessPoint.setDirection(direction);
      AttributeUtil.setAttribute(accessPoint, "carnot:engine:path:separator", SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(accessPoint, PredefinedConstants.BROWSABLE_ATT, true);
      if (xPath.isList())
      {
         AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:indexed", true); //$NON-NLS-1$
      }
      AttributeUtil.setBooleanAttribute(accessPoint, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$
      return accessPoint;
   }

   public String[] splitAccessPath(String accessPath)
   {
      int ix = accessPath.indexOf(SEPARATOR);
      return ix < 0 ? new String[] {accessPath, null}
         : new String[] {accessPath.substring(0, ix),
            accessPath.substring(ix + SEPARATOR.length())};
   }

   public boolean supportsBrowsing()
   {
      return false;
   }   
}