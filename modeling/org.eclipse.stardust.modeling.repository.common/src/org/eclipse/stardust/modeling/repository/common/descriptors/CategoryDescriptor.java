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
package org.eclipse.stardust.modeling.repository.common.descriptors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ui.ImageUtil;
import org.eclipse.swt.graphics.Image;


public class CategoryDescriptor extends EObjectImpl implements IObjectDescriptor
{
   private URI uri;
   private String id;
   private String name;
   private IObjectDescriptor[] children;
   private String iconPath;
   private String iconBundleId;

   public CategoryDescriptor(URI uri, String id, String name, IObjectDescriptor[] children,
         String iconBundleId, String iconPath)
   {
      this.uri = uri;
      this.id = id;
      this.name = name;
      this.children = children;
      this.iconBundleId = iconBundleId;
      this.iconPath = iconPath;
   }

   public IObjectDescriptor[] getChildren()
   {
      return children;
   }

   public String getDescription()
   {
      return null;
   }

   public Image getIcon()
   {
      if (iconBundleId == null || iconPath == null)
      {
         return null;
      }
      IImageManager im = ImageUtil.getImageManager(iconBundleId);
      return im.getPlainIcon(iconPath);
   }

   public String getLabel()
   {
      return name;
   }

   public Object getType()
   {
      return id;
   }

   public URI getURI()
   {
      return uri;
   }

   public boolean hasChildren()
   {
      return children != null && children.length > 0;
   }

   public IObjectDescriptor find(URI uri)
   {
      IObjectDescriptor result = null;
      for (int i = 0; i < children.length && result == null; i++)
      {
         if (uri.equals(children[i].getURI()))
         {
            result = children[i];
         }
         else if (children[i] instanceof CategoryDescriptor)
         {
            result = ((CategoryDescriptor) children[i]).find(uri);
         }
      }
      return result;
   }

   public boolean isLazyLoading()
   {
      return false;
   }
}
