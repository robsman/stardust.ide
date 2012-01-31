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
package org.eclipse.stardust.modeling.data.structured.annotations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;


public class AnnotationLabelProvider extends LabelProvider
   implements ITableLabelProvider, IColorProvider
{
   public Image getColumnImage(Object element, int columnIndex)
   {
      if (columnIndex == 0 && element instanceof IAnnotation)
      {
         String location = "{org.eclipse.xsd.edit}icons/full/obj16/XSDAnnotation.gif"; //$NON-NLS-1$
         if (element instanceof AttributeAnnotation)
         {
            location = "{org.eclipse.xsd.edit}icons/full/obj16/XSDAttributeDeclaration.gif"; //$NON-NLS-1$
         }
         else if (element instanceof ElementAnnotation)
         {
            location = "{org.eclipse.xsd.edit}icons/full/obj16/XSDElementDeclaration.gif"; //$NON-NLS-1$
         }
         if (element instanceof ConfigurationItem && !((ConfigurationItem) element).getValidationStatus().isOK())
         {
            IStatus status = ((ConfigurationItem) element).getValidationStatus();
            return DiagramPlugin.getImage(location, status.getSeverity() == IStatus.ERROR
                  ? IImageManager.ICON_STYLE_ERRORS : IImageManager.ICON_STYLE_WARNINGS);
         }
         else
         {
            return DiagramPlugin.getImage(location);
         }
      }
      return null;
   }

   public String getColumnText(Object element, int columnIndex)
   {
      Object result = "?"; //$NON-NLS-1$
      if (element instanceof IAnnotation)
      {
         IAnnotation annotation = (IAnnotation) element;
         switch (columnIndex)
         {
         case 0:
            result = annotation.getName();
            break;
         case 1:
            result = DefaultAnnotationModifier.getAnnotationValue(annotation);
            break;
         case 2:
         case 3:
            result = null;
            break;
         }
      }
      return result == null ? "" : result.toString(); //$NON-NLS-1$
   }

   public Color getBackground(Object element)
   {
      return null;
   }

   public Color getForeground(Object element)
   {
      if (element instanceof IAnnotation)
      {
         IAnnotation annotation = (IAnnotation) element;
         if (!DefaultAnnotationModifier.annotationExists(annotation))
         {
            return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
         }
      }
      else
      {
         return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
      }
      return null;
   }
}