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
package org.eclipse.stardust.modeling.core.modelserver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;


public class LockFileUtils
{   
   public static String readLockFile(IFile lockFile)
   {
      String data = null;
      try
      {
         if (!lockFile.isSynchronized(IResource.DEPTH_ZERO))
         {
            lockFile.refreshLocal(IResource.DEPTH_ZERO, null);
         }
         BufferedReader reader = new BufferedReader(new InputStreamReader(
               lockFile.getContents(), "ISO-8859-1")); //$NON-NLS-1$;
         data = reader.readLine();
         reader.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return data;
   }   
      
   public static void setLockFile(IFile lockFile, String data)
   {
      try
      {
         lockFile.setContents(new ByteArrayInputStream(data.getBytes("ISO-8859-1")), true, false, null); //$NON-NLS-1$;
      }
      catch (Exception e)
      {
      }
   }  
   
   // hasLockFile should check if we have a lockFile in SVN
   public static boolean hasLockFile(EObject eObject, ModelType model)
   {
      IFolder lockFolder = getLockFolder(eObject, model);
      IFile lockFile = getFile(lockFolder, eObject);
      return lockFile != null && lockFile.exists();      
   }   
   
   public static IFile getLockFile(EObject eObject)
   {
      IFolder lockFolder = getLockFolder(eObject, ModelUtils.findContainingModel(eObject));
      return getFile(lockFolder, eObject);
   }
   
   static String getFileName(EObject element, String uuid)
   {
      return element.eClass().getName() + '_' + uuid + ".lck"; //$NON-NLS-1$;
   }
   
   public static IFile getFile(IFolder lockFolder, EObject element)
   {
      if (lockFolder == null)
      {
         return null;
      }
      String uuid = UUIDUtils.getUUID(element);
      if (uuid == null)
      {
         return null;
      }
      
      String fileName = getFileName(element, uuid);
      return lockFolder.getFile(fileName);
   }

   public static IFolder getLockFolder(EObject element, ModelType model)
   {
      IFolder lockFolder = ShareUtils.getLockFolder(model);
      if(lockFolder == null)
      {
         return null;
      }
      return getLockFolder(element.eClass(), lockFolder);
   }

   static IFolder getLockFolder(EClass eClass, IFolder lockFolder)
   {
      return lockFolder.getFolder(eClass.getName());
   }     
   
   public static CollisionInfo getInfo(VcsStatus vcsStatus, IFile lockFile)
   {
      if (lockFile != null && !lockFile.isSynchronized(IResource.DEPTH_ZERO))
      {
         try
         {
            lockFile.refreshLocal(IResource.DEPTH_ZERO, null);
         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      CollisionInfo info = CollisionInfo.DEFAULT;
      if (lockFile == null)
      {
         // there is no lock file:
         // element is locally added
         info = CollisionInfo.ADDED;
      }
      else if (!lockFile.exists())
      {
         // there should be a lock file, but it does not exist:
         // element was remotely removed
         info = CollisionInfo.REMOVED;
      }
      else
      {
         // we do have a lock file, possible content:
         // - "0" - default (unlocked) element
         // - any other numeric value is deprecated !!!
         // - non number value in the format <username><filename> - locked element
         // - missing content is equivalent with "0"
         String content = LockFileUtils.readLockFile(lockFile);
         if (content != null)
         {
            // decode content
            int status = 0;
            try
            {
               status = Integer.parseInt(content);
               if (status != 0)
               {
                  // ignore for the moment
//                  System.out.println("Invalid content '" + content + "' in lock file: " + lockFile);
               }
            }
            catch (NumberFormatException e)
            {
               if (content.equals(ModelServerUtils.getUser(lockFile)))
               {
                  info = CollisionInfo.LOCKED_BY_USER;
               }
               else
               {
                  info = CollisionInfo.LOCKED_BY_OTHER;
                  if (vcsStatus != null)
                  {
                     String lockOwner = vcsStatus.getLockOwner();
                     info = CollisionInfo.create(CollisionState.LOCKED_BY_OTHER, lockOwner == null
                           ? vcsStatus.getLastCommitAuthor() : lockOwner);
                  }
               }
            }
         }
      }
      return info;
   }   
}