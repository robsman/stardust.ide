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

import java.util.Set;

import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public abstract class AbstractModelAdapter
      extends AbstractIdentifiableModelElementAdapter implements IModel
{
   protected final ModelType mDelegate;

   public AbstractModelAdapter(ModelType delegate)
   {
      super(delegate);

      this.mDelegate = delegate;
   }

   public Set getElementOIDs()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
