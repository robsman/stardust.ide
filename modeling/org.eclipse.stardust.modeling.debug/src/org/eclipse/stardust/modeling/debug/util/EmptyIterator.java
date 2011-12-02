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
package org.eclipse.stardust.modeling.debug.util;

import java.util.Iterator;

/**
 * This iterator does complete an iteration immediately
 * 
 * @author sborn
 * @version $Revision$
 */
public class EmptyIterator implements Iterator
{
   public void remove()
   {
   }

   public boolean hasNext()
   {
      return false;
   }

   public Object next()
   {
      return null;
   }
}
