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
package org.eclipse.stardust.modeling.core.modelserver.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.modelserver.LockFileUtils;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.VcsStatus;
import org.eclipse.swt.widgets.Display;

public class StateCache
{
   private Map<EObject, CollisionInfo> cacheMap = new HashMap<EObject, CollisionInfo>();
   private ModelServer modelServer;
   
   public StateCache(ModelServer server)
   {
      modelServer = server;
   }

   public void remove(EObject eObject)
   {
      cacheMap.remove(eObject);
   }
      
   public CollisionInfo getState(EObject eObject)
   {
      EObject element = CopyPasteUtil.getSameModelElement(eObject, modelServer.getModel(), null); 
      if(element == null)
      {
         return CollisionInfo.REMOVED;
      }
      
      CollisionInfo info = cacheMap.get(element);
      if (info == null)
      {
         info = updateState(element, null);
      }
      return info == null ? CollisionInfo.DEFAULT : info;
   }
   
   public CollisionInfo updateState(EObject element, VcsStatus vcsStatus)
   {
      IFile lockFile = LockFileUtils.getLockFile(element);
      CollisionInfo info = LockFileUtils.getInfo(vcsStatus, lockFile);
      setState(element, info);
      return info;
   }


   public synchronized void setState(EObject eObject, CollisionInfo info)
   {
      final EObject element = CopyPasteUtil.getSameModelElement(eObject, modelServer.getModel(), null);       
      
      CollisionInfo oldInfo = cacheMap.put(element, info);
      if (!CompareHelper.areEqual(info, oldInfo))
      {
         Display.getDefault().asyncExec(new Runnable()
         {
            public void run()
            {
               CreationUtils.refreshTreeItemVisuals(element);  
            }
         });
      }
   }

   public boolean isLockedAll()
   {
      for (Entry<EObject, CollisionInfo> entry : cacheMap.entrySet())
      {
         CollisionState state = entry.getValue().getState();
         if (state == CollisionState.LOCKED_BY_OTHER
               || state == CollisionState.REMOVED
               || state == CollisionState.DEFAULT)
         {
            return false;
         }
      }
      return true;
   }   
   
   public List<EObject> getElementsToLock()
   {
      List<EObject> elements = new ArrayList<EObject>();
      for (Entry<EObject, CollisionInfo> entry : cacheMap.entrySet())
      {
         CollisionState state = entry.getValue().getState();
         if (state == CollisionState.DEFAULT || state == CollisionState.LOCKED_BY_OTHER)
         {
            elements.add(entry.getKey());
         }
      }
      return elements;
   }   

   public List<EObject> getLockedElements()
   {
      List<EObject> elements = new ArrayList<EObject>();
      for (Entry<EObject, CollisionInfo> entry : cacheMap.entrySet())
      {
         CollisionState state = entry.getValue().getState();
         if (state == CollisionState.LOCKED_BY_USER)
         {
            elements.add(entry.getKey());
         }
      }
      return elements;
   }      
   
   public List<EObject> getAllLockedElements()
   {
      List<EObject> elements = new ArrayList<EObject>();
      for (Entry<EObject, CollisionInfo> entry : cacheMap.entrySet())
      {
         CollisionState state = entry.getValue().getState();
         if (state == CollisionState.LOCKED_BY_USER || state == CollisionState.LOCKED_BY_OTHER)
         {
            elements.add(entry.getKey());
         }
      }
      return elements;
   }   
}