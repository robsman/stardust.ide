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


import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class DiagramCommand extends CreateModelElementCommand
      implements IDiagramCommand
{
   protected Rectangle location;

   public DiagramCommand(int parentLevel, IdFactory id, EClass eClass)
   {
      super(parentLevel, id, eClass);
   }

   public PoolSymbol getPool()
   {
      EObject parent = getParent();
      if (parent instanceof PoolSymbol)
      {
         return (PoolSymbol) parent;
      }
      if (parent instanceof LaneSymbol)
      {
         return ((LaneSymbol) parent).getParentPool();
      }
      if (parent instanceof DiagramType
            && parent.eContainer() instanceof ProcessDefinitionType)
      {
         return DiagramUtil.getDefaultPool((DiagramType) parent);
      }
      return null;
   }

   public DiagramType getDiagram()
   {
      DiagramType diagram = null;
      if (getParent() instanceof ISymbolContainer)
      {
         ISymbolContainer container = (ISymbolContainer) getParent();
         if (container instanceof LaneSymbol)
         {
            container = ((LaneSymbol) container).getParentPool();
         }
         if (container instanceof GroupSymbolType)
         {
            container = ModelUtils.findContainingDiagram(((GroupSymbolType) container));
         }
         diagram = container instanceof PoolSymbol ? ((PoolSymbol) container)
               .getDiagram() : (DiagramType) container;
      }
      return diagram;
   }

   public void setLocation(Rectangle location)
   {
      this.location = location;
   }
   
   public Rectangle getLocation()
   {
      return location;
   }

   public void dispose()
   {
      location = null;
      super.dispose();
   }

   public EObject getContainer()
   {
      EObject container = null;
      switch (getParentLevel())
      {
         case DIAGRAM:
            container = getDiagram();
            break;
         case POOL:
            container = getPool();
            if (container == null)
            {
               container = getDiagram();
            }
            break;
         default:
            container = super.getContainer();
      }
      if (getParentLevel() != DIAGRAM && container instanceof DiagramType)
      {
         PoolSymbol defaultPool = DiagramUtil.getDefaultPool((DiagramType) container);
         if (defaultPool != null)
         {
            container = defaultPool;
         }
      }
      return container;
   }
}
