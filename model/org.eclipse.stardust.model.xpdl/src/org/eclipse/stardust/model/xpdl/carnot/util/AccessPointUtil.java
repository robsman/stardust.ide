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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;

/**
 * @author fherinean
 * @version $Revision$
 */
public class AccessPointUtil
{
   public static boolean isDirectionCompatible(AccessPointType ap, boolean isIn)
   {
      DirectionType dir = ap.getDirection();
      return dir == null
            ? false
            : isIn
                  ? isIn(dir)
                  : isOut(dir);
   }

   public static boolean isBrowsable(AccessPointType ap)
   {
      return AttributeUtil.getBooleanValue(ap, CarnotConstants.BROWSABLE_ATT);
   }

   public static AccessPointType createIntrinsicAccessPoint(String id, String name,
         String className, DirectionType direction, boolean browsable,
         String[] characteristics, DataTypeType type)
   {
      AccessPointType result = createAccessPoint(id, name, direction, type);
      if (className != null)
      {
         String typeAtt = CarnotConstants.SERIALIZABLE_DATA_ID.equals(type.getId())
               ? CarnotConstants.CLASS_NAME_ATT
               : CarnotConstants.ENTITY_BEAN_DATA_ID.equals(type.getId())
                     ? CarnotConstants.REMOTE_INTERFACE_ATT
                     : CarnotConstants.TYPE_ATT;

         AttributeUtil.setAttribute(result, typeAtt, className);
      }
      if (characteristics != null && characteristics.length > 0)
      {
         AttributeUtil.setAttribute(result, CarnotConstants.FLAVOR_ATT,
               characteristics[1], characteristics[0]);
      }
      AttributeUtil.setAttribute(result, CarnotConstants.BROWSABLE_ATT, "boolean", //$NON-NLS-1$
            (browsable ? Boolean.TRUE : Boolean.FALSE).toString());
      return result;
   }

   public static AccessPointType createAccessPoint(final FormalParameterType parameter, final DataType data)
   {
      final DirectionType direction;
      switch (parameter.getMode())
      {
      case IN: direction = DirectionType.IN_LITERAL; break;
      case OUT: direction = DirectionType.OUT_LITERAL; break;
      case INOUT: direction = DirectionType.INOUT_LITERAL; break;
      default: direction = null;
      }
      AccessPointType ap = createAccessPoint(parameter.getId(), parameter.getName(), direction, data.getType());
      List<AttributeType> attributes = ap.getAttribute();
      for (AttributeType attribute : data.getAttribute())
      {
         attributes.add((AttributeType) EcoreUtil.copy(attribute));
      }
      return ap;
   }

   public static AccessPointType createAccessPoint(String id, String name,
         DirectionType direction, DataTypeType type)
   {
      AccessPointType result = CarnotWorkflowModelFactory.eINSTANCE
            .createAccessPointType();
      result.setId(id);
      result.setName(name);
      result.setType(type);
      result.setDirection(direction);
      return result;
   }

   public static boolean isIn(DirectionType direction)
   {
      return DirectionType.IN_LITERAL.equals(direction)
            || DirectionType.INOUT_LITERAL.equals(direction) || direction == null;
   }

   public static boolean isOut(DirectionType direction)
   {
      return DirectionType.OUT_LITERAL.equals(direction)
            || DirectionType.INOUT_LITERAL.equals(direction) || direction == null;
   }

   public static List<AccessPointType> getInAccessPonts(IAccessPointOwner scope)
   {
      List<AccessPointType> result = null;
      if (null != scope)
      {
         List<AccessPointType> aps = scope.getAccessPoint();
         for (AccessPointType ap : aps)
         {
            if (isIn(ap.getDirection()))
            {
               if (null == result)
               {
                  result = CollectionUtils.newList(aps.size());
               }
               result.add(ap);
            }
         }
      }
      return null == result ? Collections.<AccessPointType>emptyList() : result;
   }

   public static List<AccessPointType> getOutAccessPonts(IAccessPointOwner scope)
   {
      List<AccessPointType> result = null;
      if (null != scope)
      {
         List<AccessPointType> aps = scope.getAccessPoint();
         for (AccessPointType ap : aps)
         {
            if (isOut(ap.getDirection()))
            {
               if (null == result)
               {
                  result = CollectionUtils.newList(aps.size());
               }
               result.add(ap);
            }
         }
      }
      return null == result ? Collections.<AccessPointType>emptyList() : result;
   }

   public static void removeAccessPoints(List<? extends AccessPointType> list, boolean isIn)
   {
      for (int i = list.size() - 1; i >= 0; i--)
      {
         AccessPointType ap = list.get(i);
         if (isDirectionCompatible(ap, isIn))
         {
            list.remove(i);
         }
      }
   }

   public static IAccessPathEditor getSPIAccessPathEditor(DataTypeType type)
   {
      Map<String, IConfigurationElement> dataTypes = SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);
      IConfigurationElement config = dataTypes.get(type.getId());
      if (config != null)
      {
         try
         {
            return (IAccessPathEditor) config
                  .createExecutableExtension("accessPathEditorClass"); //$NON-NLS-1$
         }
         catch (CoreException e)
         {
         }
      }
      return null;
   }
   
   public static String getTypeAttributeValue(AccessPointType accessPoint)
   {
      String attrValue = AttributeUtil.getAttributeValue(accessPoint, CarnotConstants.CLASS_NAME_ATT);
      if(attrValue == null)
      {
         attrValue = AttributeUtil.getAttributeValue(accessPoint, CarnotConstants.TYPE_ATT);         
      }
      if(attrValue == null)
      {
         attrValue = AttributeUtil.getAttributeValue(accessPoint, CarnotConstants.REMOTE_INTERFACE_ATT);
      }
      return attrValue;
   }
}