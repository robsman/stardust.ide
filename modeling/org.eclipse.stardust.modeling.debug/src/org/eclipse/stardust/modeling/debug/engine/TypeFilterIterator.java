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
package org.eclipse.stardust.modeling.debug.engine;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.core.persistence.ResultIterator;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.TypeFilterIterator}. 
 * 
 * @author sborn
 * @version $Revision$
 */
public class TypeFilterIterator implements ResultIterator
{
   private static final Logger trace = LogManager.getLogger(TypeFilterIterator.class);

   private final Iterator iterator;
   private final Class type;
   private final Predicate predicate;

   private Object nextObject;

   /**
    *
    */
   TypeFilterIterator(Iterator iterator, Class type)
   {
      this.iterator = iterator;
      this.type = type;
      this.predicate = null;

      fetch();
   }

   /**
    *
    */
   TypeFilterIterator(Iterator iterator, Class type, Predicate predicate)
   {
      this.iterator = iterator;
      this.type = type;
      this.predicate = predicate;

      trace.debug(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_TypeFilterOnTypeWithPredicate"), //$NON-NLS-1$
            new Object[] { type.getName(), predicate }));

      fetch();
   }

   /**
    * Iterates the passed iterator and looks for object that macthes the passed
    * type.
    */
   private void fetch()
   {
      nextObject = null;

      while (iterator.hasNext() && (nextObject == null))
      {
         nextObject = iterator.next();

         if (!type.isAssignableFrom(nextObject.getClass()))
         {
            nextObject = null;
         }
         else if ((null != predicate) && !predicate.accept(nextObject))
         {
            nextObject = null;
         }
      }

      trace.debug(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_RetrievedObjectIs"), //$NON-NLS-1$
            new Object[] { nextObject != null ? nextObject.getClass().getName()
                        : "null" }));  //$NON-NLS-1$
   }

   public boolean hasNext()
   {
      return nextObject != null;
   }

   public Object next()
   {
      Assert.isNotNull(nextObject);

      Object returnObject = nextObject;

      fetch();

      return returnObject;
   }

   public void remove()
   {
   }

   public void close()
   {
      // Intentionally left empty
   }

   public int getStartIndex()
   {
      return 0;
   }

   public int getMaxSize()
   {
      return Integer.MAX_VALUE;
   }

   public boolean hasMore()
   {
      return false;
   }

   public boolean hasTotalCount()
   {
      return false;
   }

   public long getTotalCount() throws UnsupportedOperationException
   {
      // TODO implement
      throw new UnsupportedOperationException(Internal_Debugger_Messages.getString("EXP_DebugModeLimitation")); //$NON-NLS-1$
   }
   
   @Override
   public long getTotalCountThreshold()
   {
      return Long.MAX_VALUE;
   }
}
