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
package org.eclipse.stardust.modeling.core.compare;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.*;


public class ModelViewerSorter extends ViewerSorter
{

   public int category(Object element)
   {
      ComparableModelElementNode cNode = null;
      
      if (element instanceof DiffNode)
      {  
         cNode = (ComparableModelElementNode) (((DiffNode) element)
               .getLeft() != null ? ((DiffNode) element).getLeft() : ((DiffNode) element)
               .getRight());
      }
      else if (element instanceof ComparableModelElementNode)
      {
         cNode = (ComparableModelElementNode) element;
      }

      if (cNode != null)
      {
         
         int cat = 100;

         
         
         if (cNode == null)
         {
            return cat;
         }
         
         EObject node = cNode.getEObject(); 

         if (node instanceof ModelType)
         {
            cat = 1;
         }
         else if (node instanceof AttributeType)
         {
            cat = 2;
         }
         else if (node instanceof DataTypeType)
         {
            cat = 3;
         }
         else if (node instanceof ApplicationTypeType)
         {
            cat = 4;
         }
         else if (node instanceof ApplicationContextTypeType)
         {
            cat = 5;
         }
         else if (node instanceof TriggerTypeType)
         {
            cat = 6;
         }
         else if (node instanceof EventConditionTypeType)
         {
            cat = 7;
         }
         else if (node instanceof EventActionTypeType)
         {
            cat = 8;
         }
         else if (node instanceof DataType)
         {
            cat = 9;
         }
         else if (node instanceof ApplicationType)
         {
            cat = 10;
         }
         else if (node instanceof ModelerType)
         {
            cat = 11;
         }
         else if (node instanceof RoleType)
         {
            cat = 12;
         }
         else if (node instanceof OrganizationType)
         {
            cat = 13;
         }
         else if (node instanceof ConditionalPerformerType)
         {
            cat = 14;
         }
         else if (node instanceof ProcessDefinitionType)
         {
            cat = 15;
         }
         else if (node instanceof DiagramType)
         {
            cat = 16;
         }
         else if (node instanceof ActivityType)
         {
            cat = 17;
         }
         else if (node instanceof TransitionType)
         {
            cat = 18;
         }
         else if (node instanceof ActivitySymbolType)
         {
            cat = 19;
         }
         else if (node instanceof DataSymbolType)
         {
            cat = 20;
         }
         else if (node instanceof ApplicationSymbolType)
         {
            cat = 21;
         }
         else if (node instanceof TransitionConnectionType)
         {
            cat = 22;
         }
         else if (node instanceof ExecutedByConnectionType)
         {
            cat = 23;
         }
         else if (node instanceof DescriptionType)
         {
            cat = 24;
         }
         else if (node instanceof AttributeType)
         {
            cat = 25;
         }
         else if (node instanceof ViewType)
         {
            cat = 26;
         }


         return cat;
      }
      else
      {
         return super.category(element);
      }
   }

   public boolean isSorterProperty(Object element, String property)
   {
      return super.isSorterProperty(element, property);
   }

}
