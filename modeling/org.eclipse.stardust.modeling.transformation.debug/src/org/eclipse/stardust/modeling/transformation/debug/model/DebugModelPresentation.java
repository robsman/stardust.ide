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
package org.eclipse.stardust.modeling.transformation.debug.model;

import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.breakpoints.MessageTransformationMappingBreakpoint;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;


public class DebugModelPresentation implements IDebugModelPresentation
{

   public void computeDetail(IValue value, IValueDetailListener listener)
   {
      // TODO Auto-generated method stub

   }

   public Image getImage(Object element)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getText(Object element)
   {
      if (element instanceof MessageTransformationMappingBreakpoint)
      {
         MessageTransformationMappingBreakpoint breakpoint = (MessageTransformationMappingBreakpoint) element;
         return "FieldPath: " + breakpoint.getFieldPath(); //$NON-NLS-1$
      }
      
      return null;
   }

   public void setAttribute(String attribute, Object value)
   {
      // TODO Auto-generated method stub

   }

   public void addListener(ILabelProviderListener listener)
   {
      // TODO Auto-generated method stub

   }

   public void dispose()
   {
      // TODO Auto-generated method stub

   }

   public boolean isLabelProperty(Object element, String property)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {
      // TODO Auto-generated method stub

   }

   public String getEditorId(IEditorInput input, Object element)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IEditorInput getEditorInput(Object element)
   {
      // TODO Auto-generated method stub
      return null;
   }

}
