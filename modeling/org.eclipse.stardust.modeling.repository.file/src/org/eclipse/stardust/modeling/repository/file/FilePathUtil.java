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
package org.eclipse.stardust.modeling.repository.file;

import org.eclipse.emf.common.util.URI;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class FilePathUtil
{
   public static String convertPath(ModelType model, String filePath)
   {
      java.net.URI uri = java.net.URI.create(filePath);
      if("project".equals(uri.getScheme()) //$NON-NLS-1$
            || "platform".equals(uri.getScheme())) //$NON-NLS-1$
      {
         return filePath;
      }      

      URI modelURI = model.eResource().getURI();         
      
      int segmentCount = modelURI.segmentCount();
      String uriString = "project:"; //$NON-NLS-1$
      for(int i = 2; i < segmentCount - 1; i++)
      {
         uriString += "/" + modelURI.segment(i); //$NON-NLS-1$
      }
      uriString += "/" + filePath; //$NON-NLS-1$
      
      return uriString;
   }
   
   public static String isSameFolder(ModelType model, java.net.URI projectRelative)
   {
      URI modelURI = model.eResource().getURI();               
      
      int segmentCount = modelURI.segmentCount();
      String modelUriString = ""; //$NON-NLS-1$
      for(int i = 2; i < segmentCount; i++)
      {
         modelUriString += "/" + modelURI.segment(i); //$NON-NLS-1$
      }
      URI modelProjectURI = URI.createURI(modelUriString);
      URI fileProjectURI = URI.createURI(projectRelative.toString());
      
      if(modelProjectURI.segmentCount() == fileProjectURI.segmentCount())
      {
         for(int i = 0; i < modelProjectURI.segmentCount() - 1; i++)
         {
            if(!(modelProjectURI.segment(i).equals(fileProjectURI.segment(i))))
            {
               return null;
            }            
         }  
         return fileProjectURI.segment(fileProjectURI.segmentCount() - 1);
      }
      
      return null;
   }
}