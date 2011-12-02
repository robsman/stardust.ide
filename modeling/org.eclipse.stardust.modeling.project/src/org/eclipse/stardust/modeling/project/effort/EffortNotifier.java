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
package org.eclipse.stardust.modeling.project.effort;

import java.util.List;

import ag.carnot.base.CollectionUtils;

public abstract class EffortNotifier
{
   private List<EffortListener> listeners = CollectionUtils.newList();
   
   public void addListener(EffortListener listener)
   {
      if (!listeners.contains(listener))
      {
         listeners.add(listener);
      }
   }
   
   public void removeListener(EffortListener listener)
   {
      listeners.remove(listener);
   }
   
   public void notifyListeners(EffortEvent event)
   {
      for (int i = 0; i < listeners.size(); i++)
      {
         listeners.get(i).handleEvent(event);
      }
   }
}
