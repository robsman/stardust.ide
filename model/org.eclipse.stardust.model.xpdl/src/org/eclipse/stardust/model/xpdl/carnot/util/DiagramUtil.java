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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author fherinean
 * @version $Revision$
 */
public class DiagramUtil
{
   public static <T> List<T> getSymbols(ISymbolContainer container,
                           EStructuralFeature feature, IModelElement element)
   {
      List<T> list = CollectionUtils.newList();
      addSymbols(list, container, feature, element);
      return list;
   }

   private static void addSymbols(List list, ISymbolContainer container,
                           EStructuralFeature feature, IModelElement element)
   {
      List symbols = (List) container.eGet(feature);
      for (int i = 0; i < symbols.size(); i++)
      {
         IGraphicalObject object = (IGraphicalObject) symbols.get(i);
         if (element == null
               || !(object instanceof IModelElementNodeSymbol)
               || element.equals(((IModelElementNodeSymbol) object).getModelElement()))
         {
            list.add(object);
         }
      }
      for (Iterator i = container.getGroupSymbol().iterator(); i.hasNext();)
      {
         addSymbols(list, (GroupSymbolType) i.next(), feature, element);
      }
      if (container instanceof DiagramType)
      {
         for (Iterator i = ((DiagramType) container).getPoolSymbols().iterator(); i.hasNext();)
         {
            addSymbols(list, (ISymbolContainer) i.next(), feature, element);
         }
      }
      else if (container instanceof ISwimlaneSymbol)
      {
         for (Iterator i = ((ISwimlaneSymbol) container).getChildLanes().iterator(); i.hasNext();)
         {
            addSymbols(list, (ISymbolContainer) i.next(), feature, element);
         }
      }
   }

   public static INodeSymbol getClosestSymbol(INodeSymbol reference,
         EStructuralFeature feature, IModelElement element)
   {
      IGraphicalObject symbol = reference;
      while (symbol.eContainer() instanceof ISymbolContainer)
      {
         ISymbolContainer container = (ISymbolContainer) symbol.eContainer();
         List dataSymbols = getSymbols(container, feature, element);
         if (!dataSymbols.isEmpty())
         {
            return findClosestSymbol(reference, dataSymbols);
         }
         if (container instanceof IGraphicalObject)
         {
            symbol = (IGraphicalObject) container;
         }
         else
         {
            break;
         }
      }
      return null;
   }

   private static INodeSymbol findClosestSymbol(INodeSymbol reference, List symbols)
   {
      INodeSymbol dataSymbol = (INodeSymbol) symbols.get(0);
      double distance = getDistance2(reference, dataSymbol);
      for (int i = 1; i < symbols.size(); i++)
      {
         INodeSymbol symbol = (INodeSymbol) symbols.get(i);
         double newDistance = getDistance2(reference, symbol);
         if (newDistance < distance)
         {
            distance = newDistance;
            dataSymbol = symbol;
         }
      }
      return dataSymbol;
   }

   private static double getDistance2(INodeSymbol first, INodeSymbol second)
   {
      double i = first.getXPos() - second.getXPos();
      double j = first.getYPos() - second.getYPos();
      return i * i + j * j;
   }

   public static PoolSymbol getDefaultPool(DiagramType diagram)
   {
      PoolSymbol defaultPool = null;
      if (diagram != null && diagram.eContainer() instanceof ProcessDefinitionType)
      {
         List pools = diagram.getPoolSymbols();
         if (!pools.isEmpty())
         {
            for (Iterator i = pools.iterator(); i.hasNext();)
            {
               PoolSymbol pool = (PoolSymbol) i.next();
               if (isDefaultPool(pool))
               {
                  defaultPool = pool;
                  break;
               }
            }
         }
      }
      return defaultPool;
   }

   public static boolean isDefaultPool(ISwimlaneSymbol pool)
   {
      return pool instanceof PoolSymbol && "_default_pool__1".equals(pool.getId())
         && pool.eContainer().eContainer() instanceof ProcessDefinitionType;
   }

   public static PoolSymbol createDefaultPool(DiagramType diagram)
   {
      PoolSymbol pool = CarnotWorkflowModelFactory.eINSTANCE.createPoolSymbol();
      pool.setId("_default_pool__1");
      pool.setName("Default Pool");
      pool.setXPos(0);
      pool.setYPos(0);
      pool.setWidth(-1);
      pool.setHeight(-1);
      if (diagram != null)
      {
         pool.setElementOid(ModelUtils.getElementOid(pool,
            ModelUtils.findContainingModel(diagram)));
         diagram.getPoolSymbols().add(pool);
      }
      return pool;
   }

   public static boolean isDiagramModelElementProxy(EObject model)
   {
      DiagramType diagram = null;
      if (model instanceof DiagramType)
      {
         diagram = (DiagramType) model;
      }
      if (model instanceof IGraphicalObject)
      {
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) model);
      }
      // can be null (when?)
      if(diagram == null)
      {
         return false;
      }
      EObject root = diagram.eContainer(); // ProcessDefinition      
      return (((EObject) model).eIsProxy() 
         || (diagram instanceof DiagramType && diagram.eIsProxy())
         || (root instanceof ProcessDefinitionType && root.eIsProxy()));
   }
   
   public static DiagramType findDiagramByName(List diagrams, String diagramName)
   {
      if (null == diagrams || diagrams.isEmpty())
      {
         return null;
      }

      if (StringUtils.isEmpty(diagramName))
      {
         return null;
      }

      for (Iterator iterator = diagrams.iterator(); iterator.hasNext();)
      {
         DiagramType diagram = (DiagramType) iterator.next();

         if (diagramName.equals(diagram.getName()))
         {
            return diagram;
         }
      }

      return null;
   }
}
