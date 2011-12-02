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

public class VcsStatus
{
   public static final int STATUS_ADDED = 0;
   public static final int STATUS_SHARED = 1;
   public static final int STATUS_NOT_SHARED = 2;
   
   private String lockOwner;
   private String lastCommitAuthor;

   private boolean isLockedByCurrentUser;
   private int status;
   private Long headRevision;
   private Long baseRevision;
   
   public VcsStatus(int status, boolean isLockedByCurrentUser, String lockOwner, String lastCommitAuthor, Long headRevision, Long baseRevision)
   {
      this.status = status;
      this.lockOwner = lockOwner;
      this.lastCommitAuthor = lastCommitAuthor;
      this.isLockedByCurrentUser = isLockedByCurrentUser;
      this.headRevision = headRevision;
      this.baseRevision = baseRevision;
   }

   public String getLastCommitAuthor()
   {
      return lastCommitAuthor;
   }   
   
   public Long getHeadRevision()
   {
      return headRevision;
   }

   public Long getBaseRevision()
   {
      return baseRevision;
   }

   public int getStatus()
   {
      return status;
   }

   public String getLockOwner()
   {
      return lockOwner;
   }
   
   public boolean isLocked()
   {
      if (this.lockOwner == null)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   public boolean isLockedByCurrentUser()
   {
      return isLockedByCurrentUser;
   }
}