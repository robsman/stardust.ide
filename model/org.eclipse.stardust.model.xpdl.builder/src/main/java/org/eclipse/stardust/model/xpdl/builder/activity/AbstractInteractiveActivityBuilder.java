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
package org.eclipse.stardust.model.xpdl.builder.activity;

import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.directValue;
import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.participantById;

import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;

public abstract class AbstractInteractiveActivityBuilder<B extends AbstractInteractiveActivityBuilder<B>>
      extends AbstractActivityBuilder<B>
{
   public B havingDefaultPerformer(String participantId)
   {
      setters.add(participantById(PKG_CWM.getActivityType_Performer(), participantId));

      return self();
   }

   public B havingDefaultPerformer(IModelParticipant participant)
   {
      setters.add(directValue(PKG_CWM.getActivityType_Performer(), participant));

      return self();
   }

}
