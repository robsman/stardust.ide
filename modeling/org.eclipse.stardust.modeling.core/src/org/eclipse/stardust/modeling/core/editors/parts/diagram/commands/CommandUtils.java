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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class CommandUtils
{
   public static ContainmentState backupContainment(Object item, List container)
   {
      ContainmentState descriptor = new ContainmentState(item, container, container.indexOf(item));
      
      return descriptor;
   }
   
   public static ContainmentState backupContainment(EObject item)
   {
      ContainmentState descriptor = null;
      
      if ((null != item) && (null != item.eContainer()))
      {
         EReference eFtrContainment = item.eContainmentFeature();
         Object containment = item.eContainer().eGet(eFtrContainment);
         if (containment instanceof List)
         {
            descriptor = new ContainmentState(item, (List) containment,
                  ((List) containment).indexOf(item));
         }
         else if (containment instanceof EObject)
         {
            // TODO
            descriptor = null;
         }
      }
      
      return descriptor;
   }
   
   public static void undoContainment(ContainmentState state)
   {
      if (null != state)
      {
         // TODO assert
         state.container.remove(state.item);
      }
   }
   
   public static void redoContainment(ContainmentState state)
   {
      if (null != state)
      {
         if (state.position != -1)
         {
            if (state.position < state.container.size())
            {
               state.container.add(state.position, state.item);
            }
            else
            {
               state.container.add(state.item);
            }
         }
      }
   }

   public static EStructuralFeature findContainmentFeature(List containingFeatures,
         IGraphicalObject symbol)
   {
      EStructuralFeature result = null;

      for (Iterator i = containingFeatures.iterator(); i.hasNext();)
      {
         EStructuralFeature feature = (EStructuralFeature) i.next();
         if (feature.getEType().isInstance(symbol))
         {
            if (null != result)
            {
               // TODO
               throw new RuntimeException(
                     Diagram_Messages.EX_RUNTIME_FoundMultipleFeatures + symbol
                           + ": " + result + " vs. " + feature + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            result = feature;
         }
      }

      return result;
   }

   // TODO pass the containment feature explicitly
   public static EStructuralFeature findContainmentFeature(List containingFeatures,
         EClass eClass)
   {
      EStructuralFeature result = null;

      for (Iterator i = containingFeatures.iterator(); i.hasNext();)
      {
         EStructuralFeature feature = (EStructuralFeature) i.next();
         if ((!feature.isTransient() 
            || feature.getEContainingClass().equals(CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer())
            || feature.getEContainingClass().equals(XpdlPackage.eINSTANCE.getTypeDeclarationsType()))
            && feature.getEType().equals(eClass))
         {
            if (null != result)
            {
               return null;
            }
            result = feature;
         }
      }

      return result;
   }

   public static DiagramType getDiagram(ISymbolContainer symbolContainer)
   {
      DiagramType diagram = null;
      if (symbolContainer instanceof DiagramType)
      {
         diagram = (DiagramType) symbolContainer;
      }
      else
      {
         PoolSymbol pool = null;
         if (symbolContainer instanceof PoolSymbol)
         {
            pool = (PoolSymbol) symbolContainer;
         }
         else if (symbolContainer instanceof LaneSymbol)
         {
            pool = ((LaneSymbol) symbolContainer).getParentPool();
         }
         if (pool != null)
         {
            diagram = pool.getDiagram();
         }
      }
      return diagram;
   }

   public static List getSymbols(ISymbolContainer symbolContainer, EClass eClass)
   {
      ArrayList list = new ArrayList();
      ISymbolContainer top = symbolContainer instanceof LaneSymbol
            ? ((LaneSymbol) symbolContainer).getParentPool() : symbolContainer;
      addElements(list, top, eClass);
      return list;
   }

   private static void addElements(ArrayList list, ISymbolContainer symbolContainer, EClass eClass)
   {
      EStructuralFeature feature = findContainmentFeature(
         symbolContainer.getNodeContainingFeatures(), eClass);
      if (feature != null)
      {
         List elements = (List) symbolContainer.eGet(feature);
         list.addAll(elements);
         List children = Collections.EMPTY_LIST;
         if (symbolContainer instanceof DiagramType)
         {
            children = ((DiagramType) symbolContainer).getPoolSymbols();
         }
         else if (symbolContainer instanceof PoolSymbol)
         {
            children = ((PoolSymbol) symbolContainer).getLanes();
         }
         for (Iterator i = children.iterator(); i.hasNext();)
         {
            addElements(list, (ISymbolContainer) i.next(), eClass);
         }
      }
   }

   public static class ContainmentState
   {
      private Object item;
      private List container;
      private int position;
      
      
      public ContainmentState(Object item, List container, int position)
      {
         this.item = item;
         this.container = container;
         this.position = position;
      }
   }
}
