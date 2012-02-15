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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;

public class AdapterRegistry
{
   private final Map/* <IAdapterFactory, WeakHashMap> */registry = new HashMap();

   public Object getAdapter(Object adaptee, IAdapterFactory adapterFactory)
   {
      WeakHashMap adapters = (WeakHashMap) registry.get(adapterFactory);
      if (null == adapters)
      {
         adapters = new WeakHashMap();
         registry.put(adapterFactory, adapters);
      }
      
      //System.out.println("getAdapter with <"+adaptee+"> <"+adapters+">");
      if (adaptee == null) {
          return null;
      }

      Object result = adapters.get(adaptee);
      if (null == result)
      {
         result = adapterFactory.createAdapter(adaptee);
         if (null != result)
         {
            adapters.put(adaptee, result);
         }
      }

      return result;
   }

   public Object getAdapter(EObject scope, EStructuralFeature feature, String id,
         IAdapterFactory adapterFactory)
   {
      return getAdapter(scope, feature, id, null, adapterFactory);
   }

   public Object getAdapter(EObject scope, EStructuralFeature feature, String id,
         Predicate predicate, IAdapterFactory adapterFactory)
   {
      Object result = null;
      if (null != scope)
      {
         if (null != scope)
         {
            Object rawDomain = scope.eGet(feature);
            if (rawDomain instanceof List)
            {
               List domain = (List) rawDomain;
               result = getAdapter(domain, id, predicate, adapterFactory);
            }
         }
      }
      return result;
   }

   public Object getAdapter(List domain, String id, IAdapterFactory adapterFactory)
   {
      return getAdapter(domain, id, null, adapterFactory);
   }
   
   public Object getAdapter(List domain, String id, Predicate predicate,
         IAdapterFactory adapterFactory)
   {
      Object result = null;
      
      for (int i = 0; i < domain.size(); ++i)
      {
         Object candidate = domain.get(i);
         if (candidate instanceof IIdentifiableElement
               && ((null == predicate) || predicate.accept(candidate))
               && CompareHelper.areEqual(((IIdentifiableElement) candidate).getId(), id))
         {
            result = getAdapter(candidate, adapterFactory);
            break;
         }
      }
      
      return result;
   }

   public List getAdapters(EObject scope, EStructuralFeature feature,
         IAdapterFactory adapterFactory)
   {
      //System.out.println("getAdapters entered with <"+scope+"> <"+feature+"> <"+adapterFactory+">");
      return getAdapters(scope, feature, null, adapterFactory);
   }

   public List getAdapters(EObject scope, EStructuralFeature feature,
         Predicate predicate, IAdapterFactory adapterFactory)
   {
      List result = null;
      if (null != scope)
      {
         Object adaptees = scope.eGet(feature);
         if (adaptees instanceof List)
         {
            result = getAdapters((List) adaptees, predicate, adapterFactory);
         }
      }
      return (null != result) ? result : Collections.EMPTY_LIST;
   }

   public List getAdapters(List adaptees, IAdapterFactory adapterFactory)
   {
      return getAdapters(adaptees, null, adapterFactory);
   }

   public List getAdapters(List adaptees, Predicate predicate,
         IAdapterFactory adapterFactory)
   {
      List result = null;
      for (int i = 0; i < adaptees.size(); i++ )
      {
         Object adaptee = adaptees.get(i);
         if ((null == predicate) || predicate.accept(adaptee))
         {
             //System.out.println("getAdapters iteration with <"+predicate+"> <"+adaptee+"> <"+adapterFactory+">");
            Object adapter = getAdapter(adaptee, adapterFactory);
            if (null != adapter)
            {
               if (null == result)
               {
                  result = new ArrayList(adaptees.size());
               }
               result.add(adapter);
            }
         }
      }
      return (null != result) ? result : Collections.EMPTY_LIST;
   }
}
