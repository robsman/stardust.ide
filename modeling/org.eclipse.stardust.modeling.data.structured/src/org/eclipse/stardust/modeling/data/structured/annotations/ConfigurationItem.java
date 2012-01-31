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
package org.eclipse.stardust.modeling.data.structured.annotations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;


public class ConfigurationItem
{
   private static final String EDITOR_ATTR = "editor"; //$NON-NLS-1$
   private static final String VALIDATOR_ATTR = "validator"; //$NON-NLS-1$

   private IConfigurationElement config;
   
   private IAnnotationEditor editor;
   private boolean editorLoaded = false;
   
   private IAnnotationValidator validator;
   private boolean validatorLoaded = false;
   private IStatus status;

   public ConfigurationItem(IConfigurationElement config)
   {
      this.config = config;
   }

   public IConfigurationElement getConfiguration()
   {
      return config;
   }

   public IAnnotationEditor getEditor()
   {
      if (!editorLoaded)
      {
         editorLoaded = true;
         if (config.getAttribute(EDITOR_ATTR) != null)
         {
            try
            {
               editor = (IAnnotationEditor) config.createExecutableExtension(EDITOR_ATTR);
            }
            catch (CoreException e)
            {
               DiagramPlugin.log(e.getStatus());
            }
         }
      }
      return editor;
   }

   public IAnnotationValidator getValidator()
   {
      if (!validatorLoaded)
      {
         validatorLoaded = true;
         if (config.getAttribute(VALIDATOR_ATTR) != null)
         {
            try
            {
               validator = (IAnnotationValidator) config.createExecutableExtension(VALIDATOR_ATTR);
            }
            catch (CoreException e)
            {
               DiagramPlugin.log(e.getStatus());
            }
         }
      }
      return validator;
   }

   public String getConfigurationAttribute(String name)
   {
      return config.getAttribute(name);
   }

   public IConfigurationElement[] getConfigurationChildren(String name)
   {
      return config.getChildren(name);
   }
   
   public String getName()
   {
      String name = getConfigurationAttribute("label"); //$NON-NLS-1$
      if (name == null)
      {
         name = getConfigurationAttribute("name"); //$NON-NLS-1$
      }
      return name;
   }

   void validate()
   {
      status = Status.OK_STATUS;
      try
      {
         if (this instanceof IAnnotation)
         {
            IAnnotationValidator validator = getValidator();
            if (validator != null)
            {
               status = validator.validate((IAnnotation) this);
            }
         }
      }
      catch (Exception ex)
      {
         status = new Status(IStatus.WARNING, "org.eclipse.stardust.modeling.data.structured", Structured_Messages.EXC_DURING_VALIDATION, ex); //$NON-NLS-1$
      }
   }
   
   public IStatus getValidationStatus()
   {
      if (status == null)
      {
         validate();
      }
      return status;
   }
   
   public String toString()
   {
      return getName();
   }
}
