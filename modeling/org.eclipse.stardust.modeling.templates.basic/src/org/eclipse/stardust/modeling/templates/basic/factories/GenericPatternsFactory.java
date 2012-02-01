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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.stardust.modeling.templates.basic.Templates_Basic_Messages;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;


public class GenericPatternsFactory implements ITemplateFactory
{
   ITemplateFactory[] childFactories;

   public ITemplateFactory[] getChildFactories()
   {                 
      return childFactories;
   }

   public String getDescription()
   {
      return Templates_Basic_Messages.TXT_GENERIC_PATTERNS;
   }

   public String getId()
   {
      return "Generic Patterns"; //$NON-NLS-1$
   }

   public String getName() 
   {
      return Templates_Basic_Messages.TXT_GENERIC_PATTERNS;
   }

   public ITemplateFactory getParentFactory()
   {
      return null;
   }

   public ITemplate[] getTemplates()
   {
      return null;
   }

   public void initialize(Map parameters)
   {
      childFactories = new ITemplateFactory[2];
      childFactories[0] = new BasicControlFlowPatterns(); 
      parameters = new HashMap();
      parameters.put("xpdl", "/org/eclipse/stardust/modeling/templates/basic/resources/dynamicspawn.cwmt");      
      ITemplateFactory factory = new DynamicSpawnTemplateFactory();
      factory.initialize(parameters);
      childFactories[1] = factory;
   }

}
