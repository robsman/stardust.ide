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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.graphics.Image;


public class AttributeComparator implements IStructureComparator, ITypedElement
{
   EObject model;
   String attributeName;
   private IStructureComparator parent;
   private IStructureComparator[] children = new IStructureComparator[0];
   private String key;

   public AttributeComparator(EObject model, String attributeName, IStructureComparator parent)
   {
      this.model = model;
      this.attributeName = attributeName;
      this.parent = parent;
      this.key = computeUniqueIdentifier();
   }

   private String computeUniqueIdentifier()
   {
      return '[' + attributeName + ']';
   }

   public Object[] getChildren()
   {
      return children;
   }

   public IStructureComparator getParent()
   {
      return parent;
   }

   public Image getImage()
   {
      return DiagramPlugin.getImage("/icons/full/obj16/attribute_obj.gif"); //$NON-NLS-1$
   }

   public String getName()
   {
      Object value = model.eGet(model.eClass().getEStructuralFeature(attributeName));
      return attributeName + "=" + //$NON-NLS-1$
         (value instanceof EObject ? StructureComparator.getName((EObject)value) : String.valueOf(value));
   }

   public String getType()
   {
      return model.eClass().getName();
   }

   public String toString()
   {
      return getName();
   }

   public int hashCode()
   {
      return key.hashCode();
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      return obj instanceof AttributeComparator
         && key.equals(((AttributeComparator)obj).key);
   }
}