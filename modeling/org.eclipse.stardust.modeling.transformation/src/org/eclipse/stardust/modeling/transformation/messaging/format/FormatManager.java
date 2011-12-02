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
package org.eclipse.stardust.modeling.transformation.messaging.format;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.StandardClasspathProvider;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;

import ag.carnot.base.CollectionUtils;

import com.infinity.bpm.messaging.format.IMessageFormat;

/**
 * Encapsulates all extension point handling.
 * 
 * @author gille
 *
 */
public class FormatManager
{
	private static final String EXTENSION_POINT_ID = "org.eclipse.stardust.modeling.transformation.messageFormat"; //$NON-NLS-1$
	private static final String CONFIGURATION_ID = "messageFormat"; //$NON-NLS-1$
	private static final String ID_FIELD_ID = "id"; //$NON-NLS-1$
	private static final String CLASS_FIELD_ID = "class"; //$NON-NLS-1$
	private static final String DEPENDENCY_PROVIDER_FIELD_ID = "dependencyProvider"; //$NON-NLS-1$
	
	private static IExtensionPoint getMessageFormatExtensionPoint()
	{
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry
				.getExtensionPoint(EXTENSION_POINT_ID);

		if (point == null)
		{
			throw new RuntimeException(Modeling_Messages.EXC_EXTENSION_POINT_NOT_FOUND);
		}

		return point;
	}
	
	public static List<String> getMessageFormats()
	{		
		IExtension[] extensions = getMessageFormatExtensionPoint().getExtensions();
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < extensions.length; i++)
		{
			IExtension extension = extensions[i];
			
			for (int j = 0; j < extension.getConfigurationElements().length; j++)
			{
				IConfigurationElement configurationElement = extension.getConfigurationElements()[j];
				
				if (configurationElement.getName().equals(CONFIGURATION_ID))
				{
					list.add(configurationElement.getAttribute(ID_FIELD_ID));
				}
			}
		}
		
		return list;
	}
	
	public static IMessageFormat getMessageFormat(String id, ILaunchConfiguration launchConfiguration)
	{
		IExtension[] extensions = getMessageFormatExtensionPoint().getExtensions();
		
		for (int i = 0; i < extensions.length; i++)
		{
			IExtension extension = extensions[i];
			
			for (int j = 0; j < extension.getConfigurationElements().length; j++)
			{
				IConfigurationElement configurationElement = extension.getConfigurationElements()[j];
				
				if (configurationElement.getName().equals(CONFIGURATION_ID) && configurationElement.getAttribute(ID_FIELD_ID).equals(id))
				{
					try
					{
					   return (IMessageFormat) configurationElement.createExecutableExtension(CLASS_FIELD_ID); 
					}
					catch (Exception e)
					{
						throw new RuntimeException(
								MessageFormat.format(
										Modeling_Messages.EXC_CANNOT_LOAD_MSG_FORMAT_CL,
										new Object[] {
												configurationElement
														.getAttribute(CLASS_FIELD_ID),
												id }), e);						
					}
				}
			}
		}
		
		return null;
	}
	
   public static URL[] parseClasspath(ILaunchConfiguration configuration) throws CoreException {
      StandardClasspathProvider standardClasspathProvider = new StandardClasspathProvider();
      IRuntimeClasspathEntry [] runtimeClasspathEntries = standardClasspathProvider.resolveClasspath(standardClasspathProvider.computeUnresolvedClasspath(configuration), configuration);
      
      List<URL> urls = CollectionUtils.newList();
      
      for (int i=0; i<runtimeClasspathEntries.length; i++) {
          try {
             IResource resource = runtimeClasspathEntries[i].getResource();
             if (resource != null)
             {
                urls.add(runtimeClasspathEntries[i].getResource().getLocationURI().toURL());
             }
          } catch (MalformedURLException e) {
              throw new RuntimeException(e);
          }
      }
      
      return urls.toArray(new URL[urls.size()]);
  }

   public static IMessageFormatDependencyProvider getMessageFormatDependencyProvider (String messageFormatId)
   {
      IExtension[] extensions = getMessageFormatExtensionPoint().getExtensions();
      
      for (int i = 0; i < extensions.length; i++)
      {
         IExtension extension = extensions[i];
         
         for (int j = 0; j < extension.getConfigurationElements().length; j++)
         {
            IConfigurationElement configurationElement = extension.getConfigurationElements()[j];
            
            if (configurationElement.getName().equals(CONFIGURATION_ID) && configurationElement.getAttribute(ID_FIELD_ID).equals(messageFormatId))
            {
               String className = configurationElement.getAttribute(DEPENDENCY_PROVIDER_FIELD_ID);
               // instantiate only if configured
               if (className != null)
               {
                  try
                  {
                     return (IMessageFormatDependencyProvider)configurationElement.createExecutableExtension(DEPENDENCY_PROVIDER_FIELD_ID); 
                  }
                  catch (Exception e)
                  {
							throw new RuntimeException(
									MessageFormat.format(
											Modeling_Messages.EXC_CANNOT_LOAD_MSG_FORMAT_DEPENDENCY_PD_CL,
											new Object[] { className,
													messageFormatId }), e);
                  }
               }
            }
         }
      }
      
      return null;
   }
   
}
