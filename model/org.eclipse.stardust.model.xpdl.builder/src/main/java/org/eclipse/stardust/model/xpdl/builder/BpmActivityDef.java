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
package org.eclipse.stardust.model.xpdl.builder;

import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.builder.activity.AbstractActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractActivityElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.datamapping.BpmInDataMappingBuilder;
import org.eclipse.stardust.model.xpdl.builder.datamapping.BpmOutDataMappingBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;


public abstract class BpmActivityDef
{
   private List<AbstractActivityElementBuilder<?, ?>> builders = CollectionUtils.newArrayList();

   public void build(AbstractActivityBuilder<?> activityBuilder, ActivityType activity)
   {
      // finalize builders
      for (AbstractActivityElementBuilder<?, ?> builder : builders)
      {
         builder.forModel(activityBuilder.model())
               .forProcess(activityBuilder.process())
               .forActivity(activity)
               .build();
      }
   }

   protected BpmInDataMappingBuilder inMapping()
   {
      return wrap(BpmModelBuilder.newInDataMapping());
   }

   protected BpmOutDataMappingBuilder outMapping()
   {
      return wrap(BpmModelBuilder.newOutDataMapping());
   }

   private <T extends IModelElement & IIdentifiableElement, B extends AbstractActivityElementBuilder<T, B>> B wrap(
         B builder)
   {
      builders.add(builder);

      return builder;
   }
}
