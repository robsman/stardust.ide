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
package org.eclipse.stardust.modeling.templates.defaulttemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatesType;
import org.eclipse.stardust.modeling.templates.emf.template.util.TemplateManager;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;


public class TemplateFactory implements ITemplateFactory
{
   private static final String XPDL_PARAMETER = "xpdl"; //$NON-NLS-1$
   
   private static final ClasspathUriConverter CLASSPATH_URI_CONVERTER = new ClasspathUriConverter();
   
   private TemplateLibraryType templateLibrary;

   protected URIConverter getURIConverter() {
      
      return CLASSPATH_URI_CONVERTER;
   }
   
   public void initialize(Map parameters)
   {
      final boolean loadingFromClasspath;
      String location = (String) parameters.get(XPDL_PARAMETER);
      URI uri = URI.createURI(location);
      if (uri.scheme() == null)
      {
         loadingFromClasspath = true;
         if(location.startsWith("/")) //$NON-NLS-1$
         {
            location = location.substring(1);
         }
         uri = URI.createURI(ClasspathUriConverter.CLASSPATH_SCHEME + ":/" + location); //$NON-NLS-1$
      }
      else
      {
         loadingFromClasspath = false;
      }
      TemplateManager manager = new TemplateManager()
      {
         protected ResourceSet getResourceSet()
         {
            ResourceSet resourceSet = super.getResourceSet();
            if (loadingFromClasspath)
            {
               resourceSet.setURIConverter(getURIConverter());
            }
            return resourceSet;
         }
      };
      try
      {
         manager.load(uri);
         templateLibrary = manager.getTemplate();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public String getId()
   {
      if (templateLibrary != null)
      {
         return templateLibrary.getId();
      }
      return null;
   }

   public String getName()
   {
      if (templateLibrary != null)
      {
         return templateLibrary.getName();
      }
      return null;
   }

   public String getDescription()
   {
      if (templateLibrary != null)
      {
         DocumentationType documentation = templateLibrary.getDocumentation();
         if (documentation != null)
         {
            return documentation.getAsText();
         }
      }
      return null;
   }

   public ITemplate[] getTemplates()
   {
      if (templateLibrary != null)
      {
         TemplatesType templates = templateLibrary.getTemplates();
         if (templates != null)
         {
            List templateList = templates.getTemplate();
            ITemplate[] result = new ITemplate[templateList.size()];
            for (int i = 0; i < result.length; i++)
            {
               result[i] = new TemplateAdapter((TemplateType) templateList.get(i), this);
            }
            return result;
         }
      }
      return new ITemplate[0];
   }
   
   public static void main(String[] args)
   {
      TemplateFactory factory = new TemplateFactory();
      Map parameters = new HashMap();
      parameters.put(XPDL_PARAMETER, "/com/infinity/bpm/modeling/templates/poc/resources/template.cwmt"); //$NON-NLS-1$
      factory.initialize(parameters);
      System.out.println(factory.getId());
      System.out.println(factory.getName());
      System.out.println(factory.getDescription());
      ITemplate[] templates = factory.getTemplates();
      for (int i = 0; i < templates.length; i++)
      {
         System.out.println("-----------"); //$NON-NLS-1$
         System.out.println(templates[i].getId());
         System.out.println(templates[i].getName());
         System.out.println(templates[i].getDescription());
      }
   }

   public ITemplateFactory[] getChildFactories()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ITemplateFactory getParentFactory()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
