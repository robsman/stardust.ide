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
package org.eclipse.stardust.modeling.core.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;


public class ResultTreeContentProvider implements ITreeContentProvider
{

   private Set allElements;

   public Object[] getChildren(Object parentElement)
   {
      List children = new ArrayList();
      if (parentElement instanceof IIdentifiableElement)
      {
         for (Iterator iter = ((IIdentifiableElement) parentElement).eContents()
               .iterator(); iter.hasNext();)
         {
            EObject element = (EObject) iter.next();
            if (allElements.contains(element))
            {
               children.add(element);
            }
         }
      }
      else if (parentElement instanceof DiagramType)
      {
         PoolSymbol defaultPool = DiagramUtil.getDefaultPool((DiagramType) parentElement);
         ISymbolContainer symbolContainer = defaultPool != null
               ? defaultPool
               : (ISymbolContainer) parentElement;
         for (Iterator iter = symbolContainer.getNodes().valueListIterator(); iter
               .hasNext();)
         {
            Object element = iter.next();
            if (allElements.contains(element))
            {
               children.add(element);
            }
         }
      }
      return children.toArray();
   }

   public Object getParent(Object element)
   {
      Object parent = null;
      if (element instanceof IIdentifiableModelElement)
      {
         if (((IIdentifiableModelElement) element).eContainer() != null)
         {
            parent = ((IIdentifiableModelElement) element).eContainer();
         }
      }
      return parent;
   }

   public boolean hasChildren(Object element)
   {
      if (element instanceof IIdentifiableModelElement)
      {
         for (Iterator iter = ((IIdentifiableModelElement) element).eContents()
               .iterator(); iter.hasNext();)
         {
            EObject eObject = (EObject) iter.next();
            if (allElements.contains(eObject))
            {
               return true;
            }
         }
      }
      else if (element instanceof DiagramType)
      {
         PoolSymbol defaultPool = DiagramUtil.getDefaultPool((DiagramType) element);
         ISymbolContainer symbolContainer = defaultPool != null
               ? defaultPool
               : (ISymbolContainer) element;
         for (Iterator iter = symbolContainer.getNodes().valueListIterator(); iter
               .hasNext();)
         {
            Object obj = iter.next();
            if (allElements.contains(obj))
            {
               return true;
            }
         }
      }
      return false;
   }

   public Object[] getElements(Object inputElement)
   {
      allElements = new HashSet();
      Set parents = new HashSet();
      Set elements = (Set) inputElement;
      for (Iterator iter = elements.iterator(); iter.hasNext();)
      {
         getParent((EObject) iter.next(), parents);
      }
      return parents.toArray();
   }

   private void getParent(EObject obj, Set parents)
   {
      if (obj != null)
      {
         if (!(obj.eContainer() instanceof ModelType))
         {
            getParent(obj.eContainer(), parents);
            allElements.add(obj);
         }
         else
         {
            parents.add(obj);
         }
      }
   }

   public void dispose()
   {}

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {}

}
