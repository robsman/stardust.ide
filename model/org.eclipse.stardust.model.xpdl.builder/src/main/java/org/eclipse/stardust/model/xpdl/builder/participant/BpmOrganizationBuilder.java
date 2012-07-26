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
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;


public class BpmOrganizationBuilder extends AbstractModelElementBuilder<OrganizationType, BpmOrganizationBuilder>
{
   public BpmOrganizationBuilder(ModelType model)
   {
      super(F_CWM.createOrganizationType());
      
      forModel(model);
   }

   @Override
   protected OrganizationType finalizeElement()
   {
      super.finalizeElement();
      
      model.getOrganization().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Organization";
   }

   public static BpmOrganizationBuilder newOrganization(ModelType model)
   {
      return new BpmOrganizationBuilder(model);
   }

   public BpmOrganizationBuilder asSubOrganizationOf(String organizationId)
   {
      // TODO

      return this;
   }

}
