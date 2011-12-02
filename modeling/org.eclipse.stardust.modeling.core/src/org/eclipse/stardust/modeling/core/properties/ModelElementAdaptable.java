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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.core.runtime.IAdaptable;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelElementAdaptable implements IAdaptable
{
   private Class[] adaptedClass;
   private Object[] adapted;
   private Class[] delegateClass;
   private IAdaptable delegate;

   public ModelElementAdaptable(Class adaptedClass, Object adapted, Class delegateClass, IAdaptable delegate)
   {
      if (adaptedClass != null)
      {
         this.adaptedClass = new Class[] {adaptedClass};
      }
      if (adapted != null)
      {
         this.adapted = new Object[] {adapted};
      }
      if (delegateClass != null)
      {
         this.delegateClass = new Class[] {delegateClass};
      }
      this.delegate = delegate;
   }

   public ModelElementAdaptable(Class[] adaptedClass, Object[] adapted, Class[] delegateClass, IAdaptable delegate)
   {
      this.adaptedClass = adaptedClass;
      this.adapted = adapted;
      this.delegateClass = delegateClass;
      this.delegate = delegate;
   }

   public ModelElementAdaptable(Class[] adaptedClass, Object[] adapted, IAdaptable delegate)
   {
      this.adaptedClass = adaptedClass;
      this.adapted = adapted;
      this.delegate = delegate;
   }

   public Object getAdapter(Class adapter)
   {
      if (adaptedClass != null)
      {
         for (int i = 0; i < adaptedClass.length; i++)
         {
            if (adapter == adaptedClass[i])
            {
               return i < adapted.length ? adapted[i] : null;
            }
         }
      }

      if (delegate != null)
      {
         if (delegateClass == null)
         {
            return delegate.getAdapter(adapter);
         }
         for (int i = 0; i < delegateClass.length; i++)
         {
            if (adapter == delegateClass[i])
            {
               return delegate.getAdapter(adapter);
            }
         }
      }

      return null;
   }
}
