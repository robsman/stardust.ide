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
package org.eclipse.stardust.modeling.debug.debugger.types;

import org.eclipse.stardust.modeling.debug.highlighting.IHighlightable;

import ag.carnot.base.CompareHelper;
import ag.carnot.base.Pair;

public class Highlightable extends Pair<String, String> implements IHighlightable
{
   private static final long serialVersionUID = 1L;

   public Highlightable(String processDefinitionId, String processDefinitionChildId)
   {
      super(processDefinitionId, processDefinitionChildId);
   }

   public String getProcessDefinitionId()
   {
      return getFirst();
   }

   public String getProcessDefinitionChildId()
   {
      return getSecond();
   }

   public boolean equals(Object other)
   {
      if (this == other)
      {
         return true;
      }
      if (!(other instanceof IHighlightable))
      {
         return false;
      }
      IHighlightable tmp = (IHighlightable) other;
      return CompareHelper.areEqual(getProcessDefinitionId(), tmp.getProcessDefinitionId()) &&
             CompareHelper.areEqual(getProcessDefinitionChildId(), tmp.getProcessDefinitionChildId());
   }
}
