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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.engine.core.model.utils.Connection;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;


public class AbstractConnectionAdapter extends AbstractModelElementAdapter
      implements Connection
{
   public AbstractConnectionAdapter(EObject target)
   {
      // TODO
      super(target);
   }

   public ModelElement getFirst()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElement getSecond()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
