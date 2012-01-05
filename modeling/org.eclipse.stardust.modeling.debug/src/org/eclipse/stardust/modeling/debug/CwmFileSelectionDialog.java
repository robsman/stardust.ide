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
package org.eclipse.stardust.modeling.debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;

public class CwmFileSelectionDialog extends ResourceListSelectionDialog
{
   public CwmFileSelectionDialog(Shell parentShell, IResource[] resources)
   {
      super(parentShell, resources);
   }

   public CwmFileSelectionDialog(Shell parentShell, IContainer container, int typeMask)
   {
      super(parentShell, container, typeMask);
   }

   protected boolean select(IResource resource)
   {
      if (resource instanceof IFile)
      {
         IFile file = (IFile) resource;
         String ext = file.getFileExtension(); 
         return "CWM".equalsIgnoreCase(ext) || "XPDL".equalsIgnoreCase(ext); //$NON-NLS-1$ //$NON-NLS-2$
      }
      else
      {
         return super.select(resource);
      }
   }

   protected Control createDialogArea(Composite parent)
   {
      Control control = super.createDialogArea(parent);

      // TODO: Find a better way than reflection
      Text patternText = (Text) Reflect.getFieldValue(this, "pattern"); //$NON-NLS-1$
      if (null != patternText)
      {
         Method setTextMethod = Reflect.getSetterMethod(Text.class, "setText", //$NON-NLS-1$
               String.class);
         try
         {
            setTextMethod.invoke(patternText, new String[] {"*"}); //$NON-NLS-1$
         }
         catch (IllegalArgumentException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         catch (IllegalAccessException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         catch (InvocationTargetException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return control;
   }
}
