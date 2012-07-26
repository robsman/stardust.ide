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
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.process.BpmProcessDefinitionBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmPrimitiveVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public abstract class BpmModelDef
{
   private List<AbstractModelElementBuilder<?, ?>> builders = CollectionUtils.newArrayList();

   public void build(ModelType model)
   {
      // finalize builders
      for (AbstractModelElementBuilder<?, ?> builder : builders)
      {
         builder.inModel(model).build();
      }
   }

   protected BpmProcessDefinitionBuilder processDefinition()
   {
      return wrap(BpmModelBuilder.newProcessDefinition());
   }

   protected BpmPrimitiveVariableBuilder<Object> primitiveVariable()
   {
      return wrap(BpmModelBuilder.newPrimitiveVariable());
   }

   private <T extends IIdentifiableElement & IModelElement, B extends AbstractModelElementBuilder<T, B>> B wrap(
         B builder)
   {
      builders.add(builder);

      return builder;
   }
}
