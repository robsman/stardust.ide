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
package org.eclipse.stardust.modeling.templates.basic.factories;

import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.stardust.modeling.templates.basic.Templates_Basic_Messages;
import org.eclipse.stardust.modeling.templates.defaulttemplate.TemplateFactory;
import org.eclipse.stardust.modeling.templates.defaulttemplate.TemplateHelper;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;


public class DynamicSpawnTemplateFactory extends TemplateFactory
{

   protected URIConverter getURIConverter()
   {     
      return new POCClasspathUriConverter();
   }
   
   public ITemplate[] getTemplates()
   {
      ITemplate[] templates = super.getTemplates();
      for (int i = 0; i < templates.length; i++)
      {
         ITemplate template = templates[i];
         if (template.getId().equals("dynamicSpawn")) //$NON-NLS-1$
         {
            template
                  .setName(Templates_Basic_Messages.TXT_DYNAMIC_SPAWN);
         }
      }
      return templates;
   }

   public String getId()
   {
      return "Advanced Branching and Synchronization Patterns"; //$NON-NLS-1$
   }
   
   public String getName() 
   {
      return Templates_Basic_Messages.TXT_ADVANCED_BRANCHING_AND_SYNCHRONIZATION_PATTERNS;
   }
   
   public String getDescription()
   {
      return TemplateHelper.readDescriptionFromBundle(
            "org.eclipse.stardust.modeling.templates.basic.nl", "dynamicspawn.html", this); //$NON-NLS-1$ //$NON-NLS-2$
   }




}
