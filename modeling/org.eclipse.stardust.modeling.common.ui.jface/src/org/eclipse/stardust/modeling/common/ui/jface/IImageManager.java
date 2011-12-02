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
package org.eclipse.stardust.modeling.common.ui.jface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface IImageManager
{
   public static final int ICON_STYLE_PLAIN = 1;
   
   //public static final int ICON_STYLE_INFOS = 2;

   public static final int ICON_STYLE_WARNINGS = 3;

   public static final int ICON_STYLE_ERRORS = 4;
   
   public static final int ICON_STYLE_LINK = 100;

   public static final int ICON_STYLE_REF = 101;

   public abstract ImageDescriptor getImageDescriptor(String path);

   public abstract Image getImage(String path);

   public abstract Image getPlainIcon(String iconLocator);

   public abstract Image getIconWithWarnings(String iconLocator);

   public abstract Image getIconWithErrors(String iconLocator);
   
   public abstract Image getIcon(String iconLocator, int style);

   public abstract ImageDescriptor getIconDescriptor(String iconLocator, int style);

   public abstract void registerImage(String path, Image image);
}