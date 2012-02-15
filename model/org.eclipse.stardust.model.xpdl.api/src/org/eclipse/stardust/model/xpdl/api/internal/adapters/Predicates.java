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

import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;

public interface Predicates
{
   final Predicate IN_DATA_PATH = new Predicate()
   {
      public boolean accept(Object arg)
      {
         return (arg instanceof DataPathType)
               && AccessPointUtil.isIn(((DataPathType) arg).getDirection());
      }
   };

   final Predicate OUT_DATA_PATH = new Predicate()
   {
      public boolean accept(Object arg)
      {
         return (arg instanceof DataPathType)
               && AccessPointUtil.isOut(((DataPathType) arg).getDirection());
      }
   };

   final Predicate IS_DESCRIPTOR = new Predicate()
   {
      public boolean accept(Object arg)
      {
         return (arg instanceof DataPathType) && ((DataPathType) arg).isDescriptor();
      }
   };
}
