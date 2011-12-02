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

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.jface.viewers.ViewerSorter;

public class ModelElementsSorter extends ViewerSorter
{
   public int category(Object element)
   {
      if (element instanceof DiffNode)
      {
         Object left = ((DiffNode) element).getLeft();
         element = left == null ? ((DiffNode) element).getRight() : left;
      }
      return element instanceof StructureComparator ? ((StructureComparator) element).getCategory() : 0;
   }
}