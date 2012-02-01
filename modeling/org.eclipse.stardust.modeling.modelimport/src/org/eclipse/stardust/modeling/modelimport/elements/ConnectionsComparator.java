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
package org.eclipse.stardust.modeling.modelimport.elements;

import java.util.Iterator;
import java.util.List;

import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.swt.graphics.Image;


public class ConnectionsComparator extends StructureComparator
{
   private ConnectionManager manager;

   public ConnectionsComparator(ConnectionManager manager, IStructureComparator parent)
   {
      super(null, parent);
      this.manager = manager;
   }

   @Override
   public String getName()
   {
      return Diagram_Messages.CONNECTION_LABEL;
   }

   @Override
   public Image getImage()
   {
      String icon = ObjectRepositoryActivator.getIcon();
      return icon == null ? null : DiagramPlugin.getImage(icon);
   }

   @Override
   public int getCategory()
   {
      return 1000;
   }

   String computeUniqueIdentifier()
   {
      return "ConnectionManager"; //$NON-NLS-1$
   }

   @Override
   void addAttributes(List<IStructureComparator> all)
   {
      // no attributes
   }

   @Override
   void addReferences(List<IStructureComparator> all)
   {
      // no references
   }

   @Override
   void addContents(List<IStructureComparator> all)
   {
      Iterator<Connection> connections = manager.getConnections();
      while (connections.hasNext())
      {
         all.add(new StructureComparator(connections.next(), this));
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   <T extends EObject> List<T> getList(EObject model, EStructuralFeature feature)
   {
      return (List<T>) manager.getAllConnections();
   }

   @Override
   EObject clone(EObject model)
   {
      EObject clone = super.clone(model);
      manager.setConnectionManager((Connection) clone);
      return clone;
   }
}