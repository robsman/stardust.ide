/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *     
 * @author Barry.Grotjahn
 *******************************************************************************/

package org.eclipse.stardust.model.xpdl.carnot.spi;

import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class StardustExtensionRegistry implements IStardustExtensionRegistry
{
   private static StardustExtensionRegistry instance;
   private IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
   
   public static StardustExtensionRegistry instance()
   {
      if (instance == null)
      {
         instance = new StardustExtensionRegistry();
      }
      return instance;
   }

   public IExtensionPoint getExtensionPoint(String expandedId)
   {
      if(extensionRegistry == null)
      {
         return null;
      }
      
      return extensionRegistry.getExtensionPoint(expandedId);
   }
}