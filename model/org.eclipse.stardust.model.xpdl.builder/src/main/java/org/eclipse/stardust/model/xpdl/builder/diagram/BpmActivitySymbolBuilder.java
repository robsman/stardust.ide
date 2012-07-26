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
package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;

public class BpmActivitySymbolBuilder
      extends AbstractNodeSymbolBuilder<ActivitySymbolType, ISymbolContainer, BpmActivitySymbolBuilder>
{
   private ActivityType modelElement;

   public BpmActivitySymbolBuilder()
   {
      super(F_CWM.createActivitySymbolType(),
            PKG_CWM.getISymbolContainer_ActivitySymbol());
   }

   public BpmActivitySymbolBuilder forActivity(ActivityType activity)
   {
      this.modelElement = activity;

      return forElement(activity);
   }

   @Override
   protected ActivitySymbolType finalizeElement()
   {
      ActivitySymbolType element = super.finalizeElement();

      if (null == modelElement)
      {
         throw new NullPointerException("Model element must be set.");
      }

      element.setActivity(modelElement);

      return element;
   }

}
