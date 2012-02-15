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

import java.util.Iterator;

import org.eclipse.stardust.model.diagram.Diagram;
import org.eclipse.stardust.model.diagram.NodeSymbol;
import org.eclipse.stardust.model.diagram.Symbol;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;


public class DiagramAdapter extends AbstractModelElementAdapter implements Diagram
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof DiagramType) ? new DiagramAdapter(
               (DiagramType) adaptee) : null;
      }
   };

   protected final DiagramType dDelegate;

   public DiagramAdapter(DiagramType target)
   {
      super(target);

      this.dDelegate = target;
   }

   public Iterator getAllSymbols()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean contains(NodeSymbol symbol)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean existConnectionBetween(NodeSymbol symbol1, NodeSymbol symbol2)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean existConnectionBetween(Symbol symbol1, Symbol symbol2, Class connectionType, boolean uniDirectional)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public Symbol findSymbolForUserObject(Class userObjectType, int userObjectOID)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Symbol findSymbolForUserObject(Object searchedObject)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Iterator getAllConnections()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Iterator getAllConnections(Class type)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Iterator getAllNodes(Class type)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Iterator getAllNodeSymbols()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Iterator getExistingConnectionsBetween(Symbol symbol1, Symbol symbol2, Class connectionType, boolean uniDirectional)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getName()
   {
      return dDelegate.getName();
   }

}
