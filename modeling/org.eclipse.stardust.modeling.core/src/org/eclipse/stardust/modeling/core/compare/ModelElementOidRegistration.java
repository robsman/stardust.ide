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

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class ModelElementOidRegistration
{
   public static void replaceDuplicateOids(ModelType newModel, ModelType targetModel)
   {
      HashSet oids = new HashSet();
      long nextOid = Long.MIN_VALUE;
      
      // collect existing oids
      for (Iterator i = targetModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();

         if (child instanceof IModelElement)
         {
            long oid = ((IModelElement) child).getElementOid();
            nextOid = Math.max(nextOid, oid);
            oids.add(new Long(oid));
         }
      }
      
      // iterates through new oids to find out max
      for (Iterator i = newModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();

         if (child instanceof IModelElement)
         {
            long oid = ((IModelElement) child).getElementOid();
            nextOid = Math.max(nextOid, oid);
         }
      }

      // determine conflicting oids
      for (Iterator i = newModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();

         if (child instanceof IModelElement)
         {
            IModelElement me = (IModelElement) child;
            if (!me.isSetElementOid())
            {
               me.setElementOid(ModelUtils.getElementOid(me, newModel));
            }
            IModelElement other = (IModelElement) CopyUtil.getElementInOtherModel(
                  child, targetModel); 
            if (other == null)
            {
               if (oids.contains(new Long(me.getElementOid())))
               {
                  long newElementOid = ++nextOid;
                  me.setElementOid(newElementOid);
               }
            }
            else
            {
               me.setElementOid(other.getElementOid());
            }
         }
      }
   }
}
