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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import org.eclipse.stardust.engine.core.model.utils.Identifiable;
import org.eclipse.stardust.engine.core.model.utils.Nameable;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;


public class AbstractIdentifiableElementAdapter extends AbstractAttributeHolder
      implements Identifiable, Nameable
{
   protected final IIdentifiableElement ieDelegate;

   public AbstractIdentifiableElementAdapter(IIdentifiableElement delegate)
   {
      super((delegate instanceof IExtensibleElement)
            ? (IExtensibleElement) delegate
            : null);

      this.ieDelegate = delegate;
   }

   public String getId()
   {
      return (null != ieDelegate) ? ieDelegate.getId() : null;
   }

   public String getName()
   {
      return (null != ieDelegate) ? ieDelegate.getName() : null;
   }
}
