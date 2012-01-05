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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.stardust.common.FilteringIterator;
import org.eclipse.stardust.common.Predicate;

/**
 * Later this class could be merged into {@link org.eclipse.stardust.common.CollectionUtils}
 * 
 * @author sborn
 * @version $Revision$
 */
public class CollectionUtils
{
   public static void copy(Collection target, Collection source, Predicate predicate)
   {
      copy(target, source.iterator(), predicate);
   }
   
   public static void copy(Collection target, Iterator sourceIterator, Predicate predicate)
   {
      Iterator iterator;
      
      if (null != predicate)
      {
         iterator = new FilteringIterator(sourceIterator, predicate);
      }
      else
      {
         iterator = sourceIterator;
      }
      
      for (; iterator.hasNext();)
      {
         target.add(iterator.next());
      }
   }
   
   private CollectionUtils()
   {
      // utility class
   }
}