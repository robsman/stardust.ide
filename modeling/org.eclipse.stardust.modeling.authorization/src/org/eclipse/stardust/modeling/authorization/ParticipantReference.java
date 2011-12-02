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
package org.eclipse.stardust.modeling.authorization;

import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;

public class ParticipantReference
{
   private Permission permission;
   private IModelParticipant participant;

   public ParticipantReference(Permission permission, IModelParticipant participant)
   {
      this.permission = permission;
      this.participant = participant;
   }

   public Permission getPermission()
   {
      return permission;
   }

   public IModelParticipant getParticipant()
   {
      return participant;
   }

   public void setParticipant(IModelParticipant participant)
   {
      this.participant = participant;
   }

   public int hashCode()
   {
      return participant.hashCode();
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null || getClass() != obj.getClass())
      {
         return false;
      }
      return participant == ((ParticipantReference) obj).participant;
   }
}
