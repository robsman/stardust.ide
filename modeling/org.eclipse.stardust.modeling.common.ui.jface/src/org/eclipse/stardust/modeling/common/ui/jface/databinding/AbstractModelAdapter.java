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
package org.eclipse.stardust.modeling.common.ui.jface.databinding;

public abstract class AbstractModelAdapter implements IModelAdapter
{
   private IBindingMediator mediator;

   public IBindingMediator getMediator()
   {
      return mediator;
   }

   public void bind(IBindingMediator manager)
   {
      if (null != this.mediator)
      {
         // TODO force unbind
      }

      this.mediator = manager;
   }

   public void unbind()
   {
      this.mediator = null;
   }
}
