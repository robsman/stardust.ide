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

import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.CollectionUtils;

public class DifferencesContentProvider implements ITreeContentProvider {
    
   private Viewer viewer;
   private Object input;
   
   protected boolean allFilter = true;

   protected boolean additionFilter = false;

   protected boolean deletionFilter = false;

   protected boolean changeFilter = false;
   
   public void setAllFilter()
   {
      allFilter = true;
      additionFilter = false;
      deletionFilter = false;
      changeFilter = false;
   }

   public void setAdditionFilter()
   {
      allFilter = false;
      additionFilter = true;
      deletionFilter = false;
      changeFilter = false;
   }

   public void setDeletionFilter()
   {
      allFilter = false;
      additionFilter = false;
      deletionFilter = true;
      changeFilter = false;
   }

   public void setChangeFilter()
   {
      allFilter = false;
      additionFilter = false;
      deletionFilter = false;
      changeFilter = true;
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
       this.viewer = viewer;
       input = newInput;
    }

    public boolean isDeleted(Object element) {
        return false;
    }
        
    public void dispose() {
        inputChanged(viewer, input, null);
    }
        
    public Object getParent(Object element) {
        if (element instanceof IDiffElement) 
            return ((IDiffElement)element).getParent();
        return null;
    }
    
    public final boolean hasChildren(Object element) {
        if (element instanceof IDiffContainer) 
            return ((IDiffContainer)element).hasChildren();
        return false;
    }
    
    public final Object[] getChildren(Object element)
    {
       // we don't need to filter in this case
       if (allFilter)
       {
          if (element instanceof IDiffContainer)
             return ((IDiffContainer)element).getChildren();
          return new Object[0];
       }
       
      if (element instanceof IDiffContainer)
      {
         int kind =
            additionFilter ? Differencer.DELETION :
            deletionFilter ? Differencer.ADDITION :
            Differencer.CHANGE;

          List<IDiffElement> filteredChildren = CollectionUtils.newList();
          // all children, may contain also children
          IDiffElement[] children = ((IDiffContainer) element).getChildren();
          
          for (int i = 0; i < children.length; i++)
          {
             // to check
             if (matchesFilters(children[i], kind))
             {
                filteredChildren.add(children[i]);
             }
          }   
          return filteredChildren.toArray();
       }
       return new Object[0];
    }

   private boolean matchesFilters(IDiffElement diff, int kind)
   {
      if (diff instanceof IDiffContainer && ((IDiffContainer) diff).hasChildren())
      {
         Object[] children = ((IDiffContainer) diff).getChildren();
         for (int i = 0; i < children.length; i++)
         {
            if (matchesFilters((IDiffElement) children[i], kind))
            {
               return true;
            }
         }
         return false;
      }
      else
      {
         return diff.getKind() == kind;
      }
   }

    public Object[] getElements(Object element) {
       return getChildren(element);
    }               
}