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
package org.eclipse.stardust.modeling.common.ui.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;

import ag.carnot.base.CompareHelper;

public class MetaTypeTester extends PropertyTester
{
   public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
   {
      if ("metaType".equals(property) && receiver instanceof ITypedElement) //$NON-NLS-1$
      {
         IMetaType type = ((ITypedElement) receiver).getMetaType();
         if (type != null)
         {
            return CompareHelper.areEqual(type.getId(), expectedValue);
         }
      }
      return false;
   }
}
