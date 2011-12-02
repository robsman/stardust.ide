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
package org.eclipse.stardust.modeling.templates.emf.template.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl;



public class TemplateManager extends ExtendedModelManager
{
   private static final String EXT_CWMT = "cwmt"; //$NON-NLS-1$
   private static final String EXT_XPDLT = "xpdlt"; //$NON-NLS-1$
   
   private TemplateLibraryType template;

   protected ResourceSet getResourceSet()
   {
      // this will cascade the initialization of all dependent packages
      // and is a noop of the package was already initialized
      TemplatePackageImpl.init();
      
      Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
      Map extensionMap = reg.getExtensionToFactoryMap();
      if (!extensionMap.containsKey(EXT_CWMT))
      {
         extensionMap.put(EXT_CWMT, new TemplateResourceFactoryImpl());
      }
      if (!extensionMap.containsKey(EXT_XPDLT))
      {
         extensionMap.put(EXT_XPDLT, new TemplateResourceFactoryImpl());
      }
      // Obtain a new resource set
      return super.getResourceSet();
   }

   /**
    * Gets the top level model.
    */
   public ModelType getModel()
   {
      TemplateLibraryType template = getTemplate();
      return template == null ? null : template.getModel();
   }

   public TemplateLibraryType getTemplate()
   {
      if (template == null)
      {
         List contents = resource.getContents();
         for (int i = 0; i < contents.size(); i++)
         {
            Object rootObject = contents.get(i);
            if (rootObject instanceof TemplateLibraryType)
            {
               template = (TemplateLibraryType) rootObject;
               model = template.getModel();
               if (model != null)
               {
                  // resolve string-id references in attributes
                  ModelUtils.resolve(model, model);
               }
            }
         }
      }
      return template;
   }
}