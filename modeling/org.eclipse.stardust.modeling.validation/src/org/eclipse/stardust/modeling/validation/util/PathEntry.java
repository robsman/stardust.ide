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
package org.eclipse.stardust.modeling.validation.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.BridgeObject;


public class PathEntry
{
   public static final int ALL = -1;

   private ITypedElement element;
   private DirectionType direction;

   /* cached values */
   private String id;
   private List<PathEntry> children;
   private Boolean indexed;
   private long index = ALL;
   private String separator;
   private String indexEnd;
   private String indexStart;
   private IAccessPathEditor editor;
   private String simpleTypeName;
   private Boolean bidirectional;

   public PathEntry(ITypedElement element, DirectionType direction)
   {
      this.element = element;
      this.direction = direction;
   }

   public boolean isIn()
   {
      return AccessPointUtil.isIn(direction);
   }

   public String getIcon()
   {
      IMetaType type = element.getMetaType();
      if (type == null)
      {
         return null;
      }
      if (getChildren().size() == 0)
      {
         return "{org.eclipse.stardust.modeling.data.structured}icons/primitive_data.gif"; //$NON-NLS-1$
      }
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      return registry.getTypeIcon(type.getExtensionPointId(), type.getId());
   }

   public boolean matchesDirection()
   {
      if (element instanceof DataType)
      {
         return true;
      }
      if (element instanceof AccessPointType)
      {
         AccessPointType ap = (AccessPointType) element;
         DirectionType apdir = ap.getDirection();
         return AccessPointUtil.isIn(direction) && AccessPointUtil.isIn(apdir)
            || AccessPointUtil.isOut(direction) && AccessPointUtil.isOut(apdir);
      }
      return false;
   }

   public String getLabel()
   {
      String label = null;
      if (element instanceof IIdentifiableElement)
      {
         IIdentifiableElement identifiable = (IIdentifiableElement) element;
         label = identifiable.getName();
         if (label == null)
         {
            label = identifiable.getId();
         }
      }
      return label;
   }

   public String getTypeName()
   {
      return getTypeName(isSingle() ? 0 : 1);
   }
   
   public String getTypeName(int dimension)
   {
      String type = getSimpleTypeName();
      for (int i = 0; i < dimension; i++)
      {
         type += getIndexStart() + getIndexEnd();
      }
      return type;
   }

   public String getSimpleTypeName()
   {
      if (simpleTypeName == null)
      {
         BridgeObject bo = BridgeObject.getBridge(element, null, direction);
         simpleTypeName = bo.toString();
         if (simpleTypeName == null)
         {
            simpleTypeName = ""; //$NON-NLS-1$
         }
      }
      return simpleTypeName;
   }

   public String getIndexEnd()
   {
      if (indexEnd == null)
      {
         if (element instanceof IExtensibleElement)
         {
            indexEnd = AttributeUtil.getAttributeValue((IExtensibleElement) element,
                  "carnot:engine:path:indexEnd"); //$NON-NLS-1$
         }
         if (indexEnd == null)
         {
            indexEnd = "]"; //$NON-NLS-1$
         }
      }
      return indexEnd;
   }

   public String getIndexStart()
   {
      if (indexStart == null)
      {
         if (element instanceof IExtensibleElement)
         {
            indexStart = AttributeUtil.getAttributeValue((IExtensibleElement) element,
                  "carnot:engine:path:indexStart"); //$NON-NLS-1$
         }
         if (indexStart == null)
         {
            indexStart = "["; //$NON-NLS-1$
         }
      }
      return indexStart;
   }

   public void setIndex(long index)
   {
      if (this.index != index)
      {
         // reset children
         children = null;
      }
      this.index = index;
   }

   public long getIndex()
   {
      return index;
   }

   public boolean isSingle()
   {
      return !isIndexed() || index != ALL;
   }

   public boolean isIndexed()
   {
      if (indexed == null)
      {
         if (element instanceof IExtensibleElement)
         {
            boolean isIndexed = AttributeUtil.getBooleanValue((IExtensibleElement) element,
                  "carnot:engine:data:indexed"); //$NON-NLS-1$
            indexed = isIndexed ? Boolean.TRUE : Boolean.FALSE;
         }
      }
      return indexed.booleanValue();
   }

   public String getSeparator()
   {
      if (separator == null)
      {
         if (element instanceof IExtensibleElement)
         {
            separator = AttributeUtil.getAttributeValue((IExtensibleElement) element,
                  "carnot:engine:path:separator"); //$NON-NLS-1$
         }
         if (separator == null)
         {
            separator = "."; //$NON-NLS-1$
         }
      }
      return separator;
   }

   public String getId()
   {
      if (id == null)
      {
         if (element instanceof IIdentifiableElement)
         {
            IIdentifiableElement identifiable = (IIdentifiableElement) element;
            id = identifiable.getId();
         }
         if (id == null)
         {
            id = ""; //$NON-NLS-1$
         }
      }
      return id;
   }
   
   public String getFullId()
   {
      StringBuffer sb = new StringBuffer();
      sb.append(getId());
      if (isIndexed() && isSingle())
      {
         sb.append(getIndexStart());
         sb.append(getIndex());
         sb.append(getIndexEnd());
      }
      return sb.toString();
   }
   
   public String toString()
   {
      return getFullId();
   }
   
   public List<PathEntry> getChildren()
   {
      if (children == null)
      {
         children = new ArrayList<PathEntry>();
         if (isSingle() || AccessPointUtil.isOut(direction))
         {
            IAccessPathEditor editor = getAccessPathEditor();
            if (editor != null)
            {
               List points = editor.getAccessPoints(null, (IExtensibleElement) element, direction);
               for (int i = 0; i < points.size(); i++)
               {
                  children.add(new PathEntry((ITypedElement) points.get(i), direction));
               }
               if (DirectionType.IN_LITERAL == direction && includeOuts())
               {
                  points = editor.getAccessPoints(null,
                        (IExtensibleElement) element, DirectionType.OUT_LITERAL);
                  for (int i = 0; i < points.size(); i++)
                  {
                     IExtensibleElement element = (IExtensibleElement) points.get(i);
                     if (AttributeUtil.getBooleanValue(element, CarnotConstants.BROWSABLE_ATT))
                     {
                        children.add(new PathEntry((ITypedElement) element, direction));
                     }
                  }
               }
            }
         }
      }
      return children;
   }

   private boolean includeOuts()
   {
      if (isBidirectional())
      {
         return false;
      }
      if (element instanceof DataType)
      {
         return true;
      }
      if (element instanceof AccessPointType)
      {
         AccessPointType ap = (AccessPointType) element;
         return AccessPointUtil.isIn(direction)
               && (AccessPointUtil.isOut(ap.getDirection())
               || AttributeUtil.getBooleanValue(ap, CarnotConstants.BROWSABLE_ATT));
      }
      return false;
   }

   public boolean isBidirectional()
   {
      if (bidirectional == null)
      {
         if (element instanceof IExtensibleElement)
         {
            boolean isBidirectional = AttributeUtil.getBooleanValue((IExtensibleElement) element,
                  "carnot:engine:data:bidirectional"); //$NON-NLS-1$
            bidirectional = isBidirectional ? Boolean.TRUE : Boolean.FALSE;
         }
      }
      return bidirectional.booleanValue();
   }

   public IAccessPathEditor getAccessPathEditor()
   {
      if (editor == null)
      {
         IMetaType metaType = element.getMetaType();
         if (metaType instanceof DataTypeType)
         {
            DataTypeType dataType = (DataTypeType) metaType;
            editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
         }
      }
      return editor;
   }

   public ITypedElement getElement()
   {
      return element;
   }
}