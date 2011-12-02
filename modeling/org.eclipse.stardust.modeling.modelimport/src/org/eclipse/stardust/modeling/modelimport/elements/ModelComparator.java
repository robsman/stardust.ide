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

import java.util.List;

import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;


public class ModelComparator extends StructureComparator
{
   private ConnectionsComparator connections;
   
   public ModelComparator(ModelType model, ConnectionManager manager, IStructureComparator parent)
   {
      super(model, parent);
      connections = new ConnectionsComparator(manager, this);
   }

   String computeUniqueIdentifier()
   {
      return getType();
   }

   @Override
   void addContents(List<IStructureComparator> all)
   {
      super.addContents(all);
      all.add(connections);
   }

   void addContentElement(List<IStructureComparator> all, EObject eObject)
   {
      if (eObject instanceof AttributeType)
      {
         if (((AttributeType) eObject).getName().startsWith(IConnectionManager.CONNECTION_SCOPE))
         {
            return;
         }
      }
      super.addContentElement(all, eObject);
   }
}