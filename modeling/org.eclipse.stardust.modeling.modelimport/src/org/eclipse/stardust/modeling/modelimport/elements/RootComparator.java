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

import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.swt.graphics.Image;

public class RootComparator implements IStructureComparator, ITypedElement
{
   private ModelComparator modelComparator;
   private ModelComparator[] children;

   public RootComparator(Root root)
   {
      modelComparator = new ModelComparator(root.getModel(), root.getConnectionManager(), null);
      children = new ModelComparator[] {modelComparator};
   }

   public Object[] getChildren()
   {
      return children;
   }

   public Image getImage()
   {
      return modelComparator.getImage();
   }

   public String getName()
   {
      return modelComparator.getName();
   }

   public String getType()
   {
      return modelComparator.getType();
   }

   public String toString()
   {
      return getName();
   }
}