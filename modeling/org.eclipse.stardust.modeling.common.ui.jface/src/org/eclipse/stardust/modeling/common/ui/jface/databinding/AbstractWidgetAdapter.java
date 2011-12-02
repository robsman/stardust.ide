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

/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class AbstractWidgetAdapter implements IWidgetAdapter
{
   private IBindingMediator mediator;

   public void bind(IBindingMediator manager)
   {
      if (null != this.mediator)
      {
         // TODO unbind previous binding?
      }

      this.mediator = manager;
   }

   public void unbind()
   {
      this.mediator = null;
   }

   public void updateVisuals(Object value)
   {
   }

   public void updateModel(Object value)
   {
      if (null != mediator)
      {
         mediator.updateModel(this, value);
      }
   }
   
   protected IBindingMediator getMediator()
   {
      return mediator;
   }
}
