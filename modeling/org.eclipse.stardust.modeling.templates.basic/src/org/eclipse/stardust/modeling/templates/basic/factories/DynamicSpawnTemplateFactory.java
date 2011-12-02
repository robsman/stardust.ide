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
import org.eclipse.stardust.modeling.templates.defaulttemplate.TemplateFactory;


public class DynamicSpawnTemplateFactory extends TemplateFactory
{

   protected URIConverter getURIConverter()
   {     
      return new POCClasspathUriConverter();
   }
   
   public String getId()
   {
      return "Advanced Branching and Synchronization Patterns";
   }



}
