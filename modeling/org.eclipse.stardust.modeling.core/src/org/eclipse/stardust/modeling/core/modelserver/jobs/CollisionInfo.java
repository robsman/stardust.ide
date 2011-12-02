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

public class CollisionInfo
{
   public static final CollisionInfo DEFAULT = new CollisionInfo(CollisionState.DEFAULT, null);
   public static final CollisionInfo ADDED = new CollisionInfo(CollisionState.ADDED, null);
   public static final CollisionInfo REMOVED = new CollisionInfo(CollisionState.REMOVED, null);
   public static final CollisionInfo LOCKED_BY_OTHER = new CollisionInfo(CollisionState.LOCKED_BY_OTHER, null);
   public static final CollisionInfo LOCKED_BY_USER = new CollisionInfo(CollisionState.LOCKED_BY_USER, null);
   
   private CollisionState state;
   private String owner;
   
   private CollisionInfo(CollisionState state, String owner)
   {
      this.state = state;
      this.owner = owner;
   }

   public CollisionState getState()
   {
      return state;
   }

   public String getOwner()
   {
      return owner;
   }
   
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((owner == null) ? 0 : owner.hashCode());
      result = prime * result + ((state == null) ? 0 : state.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      CollisionInfo other = (CollisionInfo) obj;
      if (owner == null)
      {
         if (other.owner != null)
         {
            return false;
         }
      }
      else if (!owner.equals(other.owner))
      {
         return false;
      }
      if (state == null)
      {
         if (other.state != null)
         {
            return false;
         }
      }
      else if (!state.equals(other.state))
      {
         return false;
      }
      return true;
   }

   public static CollisionInfo create(CollisionState state, String owner)
   {
      assert state != null;
      if (owner == null)
      {
         switch (state)
         {
         case DEFAULT: return DEFAULT;
         case ADDED: return ADDED;
         case REMOVED: return REMOVED;
         case LOCKED_BY_OTHER: return LOCKED_BY_OTHER;
         case LOCKED_BY_USER: return LOCKED_BY_USER;
         }
      }
      return new CollisionInfo(state, owner);
   }
}
