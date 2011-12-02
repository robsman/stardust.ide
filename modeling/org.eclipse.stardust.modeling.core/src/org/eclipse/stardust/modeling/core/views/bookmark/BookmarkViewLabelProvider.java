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
package org.eclipse.stardust.modeling.core.views.bookmark;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.swt.graphics.Image;


public class BookmarkViewLabelProvider implements ILabelProvider
{
   public Image getImage(Object element)
   {
      EObject modelElement = null;
      if (element instanceof ViewType)
      {
         modelElement = (EObject) element;
      }
      else if (element instanceof ViewableType)
      {
         modelElement = ((ViewableType) element).getViewable();
      }
      else if (element instanceof IIdentifiableElement)
      {
         modelElement = (EObject) element;
      }
      String iconPath = IconFactory.getDefault().getIconFor(modelElement);
      if (iconPath != null)
      {
         return DiagramPlugin.getImage(iconPath);
      }
      return null;
   }

   public String getText(Object element)
   {
      if (element instanceof IIdentifiableElement)
      {
         return ((IIdentifiableElement) element).getName();
      }
      else if (element instanceof ViewType)
      {
         return ((ViewType) element).getName();
      }
      else if (element instanceof ViewableType)
      {
         return ((IIdentifiableElement) ((ViewableType) element).getViewable()).getName();
      }
      return ""; //$NON-NLS-1$
   }

   public void addListener(ILabelProviderListener listener)
   {}

   public void dispose()
   {}

   public boolean isLabelProperty(Object element, String property)
   {
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {}

}
