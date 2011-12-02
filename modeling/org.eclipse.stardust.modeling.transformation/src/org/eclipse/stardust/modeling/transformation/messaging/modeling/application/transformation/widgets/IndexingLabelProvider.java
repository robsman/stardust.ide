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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.util.Map;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageBreakpointManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;


import com.infinity.bpm.messaging.model.mapping.FieldMapping;

/**
 * LabelProvider for the main element table.
 *
 * @author Rainer Pielmann
 * @version $Revision: 33988 $
 */
public class IndexingLabelProvider extends LabelProvider implements
      ITableLabelProvider
{
   private static final Image primitiveImage = MessageTransformationModelingPlugin.getDefault()
         .getImageDescriptor("icons/primitive_data.gif").createImage(); //$NON-NLS-1$
   private static final Image legoImage = MessageTransformationModelingPlugin.getDefault()
         .getImageDescriptor("icons/lego_icon.gif").createImage(); //$NON-NLS-1$
   private static final Image primitiveBpImage = MessageTransformationModelingPlugin.getDefault()
         .getImageDescriptor("icons/primitive_data_bp.png").createImage(); //$NON-NLS-1$
   private static final Image legoBpImage = MessageTransformationModelingPlugin.getDefault()
         .getImageDescriptor("icons/lego_icon_bp.png").createImage(); //$NON-NLS-1$
   private static final Image errorImage = MessageTransformationModelingPlugin.getDefault()
         .getImageDescriptor("icons/error_tsk.gif").createImage(); //$NON-NLS-1$
   private static final Image serializableImage = MessageTransformationModelingPlugin.getDefault()
		 .getImageDescriptor("icons/serializable_data.gif").createImage(); //$NON-NLS-1$
   
   private static final int COLUMN_ID = 0;
   private static final int COLUMN_INDEX = 1;
   private static final int COLUMN_MAPPING = 2;
   private static final int COLUMN_ERROR_IMAGE = 3;
   
   private FontRegistry fontRegistry = new FontRegistry();
   private MessageTransformationController controller;
   private final MessageBreakpointManager breakpointManager;
   private Map<String,String> indexMap;
   private String xPath;

   public IndexingLabelProvider(MessageTransformationController controller, Map<String, String> indexMap, String xPath)
   {
      super();
      this.controller = controller;
      this.breakpointManager = new MessageBreakpointManager();
      this.indexMap = indexMap;
      this.xPath = xPath;
   }

   public String getText(Object element)
   {
      return getColumnText(element, 0);
   }
   
   public Image getColumnImage(Object element, int columnIndex)
   {
      if (element instanceof AccessPointType)
      {
         if (COLUMN_ID == columnIndex)
         {
            AccessPointType messageType = (AccessPointType) element;
            FieldMapping fm = (FieldMapping) controller.getFieldMappings().get(
                  controller.getXPathFor(messageType));            
            boolean breakpointAvailable = null == fm ? false : breakpointManager
                  .isBreakpointAvailable(fm.getFieldPath());
            if (controller.isSerializable(messageType)) {
            	return breakpointAvailable ? legoBpImage : serializableImage;
            }
            if (controller.isRoot(messageType) && controller.isSerializable(messageType))
            {
               return breakpointAvailable ? legoBpImage : serializableImage;
            }
            if ((controller.isRoot(messageType) || controller.isComplexType(messageType)) & !controller.isPrimitive(messageType))
            {
               return breakpointAvailable ? legoBpImage : legoImage;
            }
            else
            {
               return breakpointAvailable ? primitiveBpImage : primitiveImage;
            }
         }
         else if (COLUMN_ERROR_IMAGE == columnIndex)
         {
            AccessPointType messageType = (AccessPointType) element;
            if (!controller.isRoot(messageType) || controller.isPrimitive(messageType))
            {
               FieldMapping fm = (FieldMapping) controller.getFieldMappings().get(
                     controller.getXPathFor(messageType));
               if (fm != null)
               {
                  boolean valid = controller.validateMapping(fm, false);
                  if (!valid) {
                     return errorImage;   
                  }                  
               }
            }
         }
      } 
      return null;
   }


   public String getColumnText(Object element, int columnIndex)
   {
      switch (columnIndex)
      {
         case COLUMN_ID:
            if (element instanceof AccessPointType)
            {
               AccessPointType messageType = (AccessPointType) element;
               String displayString = messageType.getId();
               if (displayString.startsWith("@")){ //$NON-NLS-1$
                  displayString = displayString.replace("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
               }
               return displayString;
            }
         case COLUMN_INDEX:
            AccessPointType messageElement = (AccessPointType) element;
            if (controller.isList(messageElement)) {
            	AccessPointType apt = (AccessPointType)messageElement;
            	String xPath = controller.getXPathFor(apt);
            	String index = indexMap.get(xPath);
            	if (index != null) {
                	return "[" + index + "]";          		 //$NON-NLS-1$ //$NON-NLS-2$
            	}
            	return "[0]"; //$NON-NLS-1$
            }
            return ""; //$NON-NLS-1$
         case COLUMN_MAPPING:
            if (element instanceof AccessPointType)
            {
               AccessPointType messageType = (AccessPointType) element;
               String xPath = controller.getXPathFor(messageType);
               FieldMapping fieldMapping = (FieldMapping) controller.getFieldMappings()
                     .get(xPath);
               if (fieldMapping != null && fieldMapping.getMappingExpression() != null)
               {
                  String text = fieldMapping.getMappingExpression();
                  text = text.replace('\n', ' ');
                  return text;
               }
            }
      }
      return ""; //$NON-NLS-1$
   }

   public Font getFont(Object element, int columnIndex)
   {
      Font font = Display.getCurrent().getSystemFont();
      if (element instanceof AccessPointType)
      {
         AccessPointType messageType = (AccessPointType) element;
         if (xPath.startsWith(controller.getXPathFor(messageType))) {     	 
            font = fontRegistry.getBold(Display.getCurrent().getSystemFont().getFontData()[0].getName());
         }
      }
      return font;
   }

}