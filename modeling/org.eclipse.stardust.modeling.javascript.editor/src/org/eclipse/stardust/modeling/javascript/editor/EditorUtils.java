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
package org.eclipse.stardust.modeling.javascript.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;


public class EditorUtils
{
   public static IFile createFileStructure(IProject project, ModelType model, String jsFileName)
   {
      String id = "." + model.getId(); //$NON-NLS-1$
      File tempFile;
      IFile tempFileResource = null;
      try
      {
         IFolder kwFolder = project.getFolder(id);
         if (!kwFolder.exists())
         {
            kwFolder.create(true, true, null);
         }
         // ?
         String filePath = kwFolder.getLocation().toString() + "/" + jsFileName; //$NON-NLS-1$
         tempFileResource = null;
         tempFile = new File(filePath);
         if (!tempFile.exists())
         {
            tempFile.createNewFile();
         }

         tempFileResource = kwFolder.getFile(jsFileName);
         if (!tempFileResource.exists())
         {
            tempFileResource.create(new FileInputStream(tempFile), true, null);
         }
      }
      catch (Exception e)
      {
         //e.printStackTrace();         
      }
      return tempFileResource;
   }

   public static void addJSSupport(IProject project, ModelType model) throws CoreException
   {
      //deleteFileStructure(project, model);
      if (null != project  && !project.hasNature("org.eclipse.wst.jsdt.core.jsNature")) //$NON-NLS-1$
      {
         IProjectDescription description = project.getDescription();
         String[] natures = description.getNatureIds();
         String[] newNatures = new String[natures.length + 1];
         System.arraycopy(natures, 0, newNatures, 0, natures.length);
         newNatures[newNatures.length - 1] = "org.eclipse.wst.jsdt.core.jsNature"; //$NON-NLS-1$
         description.setNatureIds(newNatures);
         project.setDescription(description, null);      
      }
      Bundle bundle = Platform.getBundle("org.eclipse.stardust.modeling.javascript.editor"); //$NON-NLS-1$
      URL url = (URL) BundleUtility.find(bundle, "lib/.jsdtscope"); //$NON-NLS-1$
      IFile systemJSResource = project.getFile(".jsdtscope"); //$NON-NLS-1$
      if (!systemJSResource.exists())
      {
         try
         {
            systemJSResource.create(url.openStream(), true, null);
         }
         catch (FileNotFoundException e1)
         {
         }
         catch (CoreException e1)
         {
         }
         catch (IOException e)
         {
         }
      }
   }

   public static void deleteFileStructure(IProject project, ModelType model)
   {
      String id = "." + model.getId(); //$NON-NLS-1$
      IFolder modelFolder = project.getFolder(id);
      if (modelFolder.exists())
      {
         try
         {
            modelFolder.delete(true, false, null);
         }
         catch (Exception e)
         {
         }
      }      
   }
}