/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.participant;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;


public class BpmRoleBuilder extends AbstractModelElementBuilder<RoleType, BpmRoleBuilder>
{
   public BpmRoleBuilder(ModelType model)
   {
      super(F_CWM.createRoleType());
      
      forModel(model);
   }

   @Override
   protected RoleType finalizeElement()
   {
      model.getRole().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Role";
   }

   public static BpmRoleBuilder newRole(ModelType model)
   {
      return new BpmRoleBuilder(model);
   }

   public BpmRoleBuilder workingForOrganization(String organizationId)
   {
      // TODO

      return this;
   }

   public BpmRoleBuilder asTeamLead()
   {
      // TODO

      return this;
   }

}
