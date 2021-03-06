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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;

/**
 * @author fherinean
 * @version $Revision$
 */
public class DelegatingActionRegistry extends ActionRegistry
{
   private ActionRegistry delegate;

   public DelegatingActionRegistry(ActionRegistry delegate)
   {
      this.delegate = delegate;
   }

   public IAction getAction(Object key)
   {
      IAction action = super.getAction(key);
      if (action == null)
      {
         action = delegate.getAction(key);
      }
      return action;
   }
}
