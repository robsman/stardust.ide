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
package org.eclipse.stardust.model.xpdl.carnot.merge;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.team.core.RepositoryProvider;


public class ShareUtils
{
   public static boolean isModelShared(ModelType model)
   {
      IProject project = getProject(model.eResource());
      if (project == null || !RepositoryProvider.isShared(project))
      {
         return false;
      }      
      
      IFolder lockFolder = getLockFolder(model);
      if (lockFolder == null || !lockFolder.exists())
      {
         return false;
      }      
      return true;
   }   

   public static IProject getProject(Resource eResource)
   {
      if (eResource != null)
      {
         URI eUri = eResource.getURI();
         IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
               eUri.segment(1));
         if (resource instanceof IProject)
         {
            return (IProject)resource;
         }
         else if (resource != null)
         {
            return resource.getProject();
         }
      }
      return null;
   }
   
   public static IFolder getLockFolder(ModelType model)
   {
      if (model == null)
      {
         return null;
      }
      String uuid = UUIDUtils.getUUID(model);
      if (uuid == null)
      {
         return null;
      }
      IProject project = getProject(model.eResource());
      if (project == null)
      {
         return null;
      }
      return project.getFolder(".MODEL__" + uuid); //$NON-NLS-1$;
   }
   
   public static boolean isLockableElement(Object object)
   {
      if (object instanceof DataType)
      {
         return !((DataType) object).isPredefined();
      }         
      return object instanceof ModelType
            || object instanceof TypeDeclarationType
            || object instanceof LinkTypeType
            || object instanceof EObject 
                  && ((EObject) object).eContainer() instanceof ModelType 
                  && !(object instanceof TypeDeclarationsType)
                  && !(object instanceof ExternalPackages)
                  && !(object instanceof IMetaType);
   }
}