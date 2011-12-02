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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.compare.structuremergeviewer.DiffNode;

public class ImportSelectionRegistration
{
   private static ImportSelectionRegistration _instance;

   private ArrayList importSelectionRegistrationList;

   private ImportSelectionRegistration()
   {
      importSelectionRegistrationList = new ArrayList();
   }

   public static void createInstance()
   {
      _instance = new ImportSelectionRegistration();
   }

   public static void rigisterSelection(List selectionList, boolean leftToRight)
   {
      _instance = new ImportSelectionRegistration();

      for (Iterator _iterator = selectionList.iterator(); _iterator.hasNext();)
      {
         DiffNode node = (DiffNode) _iterator.next();

         ComparableModelElementNode cNode = null;

         if (leftToRight)
         {
            cNode = (ComparableModelElementNode) node.getLeft();
         }
         else
         {
            cNode = (ComparableModelElementNode) node.getRight();
         }

         if (cNode != null)
         {
            if (cNode.getETypedElement() != null)
            {
               _instance.register(cNode.getETypedElement());
            }
            else
            {
               _instance.register(cNode.getEObject());
            }
         }
      }
   }

   public static boolean lookup(Object obj)
   {
      if (_instance == null)
      {
         _instance = new ImportSelectionRegistration();
      }
      return _instance.lookupElement(obj);
   }

   private void register(Object eObj)
   {
      if (!importSelectionRegistrationList.contains(eObj))
      {
         importSelectionRegistrationList.add(eObj);
      }
   }

   private boolean lookupElement(Object obj)
   {
      if (importSelectionRegistrationList.contains(obj))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}
