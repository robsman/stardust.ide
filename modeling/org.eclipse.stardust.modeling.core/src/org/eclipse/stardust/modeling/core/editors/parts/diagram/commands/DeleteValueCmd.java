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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class DeleteValueCmd extends Command
{
   private Object object;
   private EStructuralFeature feature;
   private EObject parent;

   public DeleteValueCmd(EObject parent, EStructuralFeature feature)
   {
      this(parent, parent.eGet(feature), feature);
   }

   public DeleteValueCmd(EObject parent, Object object, EStructuralFeature feature)
   {
      this.parent = parent;
      this.object = object;
      this.feature = feature;
   }

   public DeleteValueCmd(EObject parent, boolean value, EStructuralFeature feature)
   {
      this.parent = parent;
      this.object = value ? Boolean.TRUE : Boolean.FALSE;
      this.feature = feature;
   }

   public ModelType getModel()
   {
      if(parent == null)
      {
         return null;
      }
      return ModelUtils.findContainingModel(parent);
   }   
   
   public void execute()
   {
      redo();
   }

   public void redo()
   {
      if (parent != null)
      {         
         Object ref = parent.eGet(feature);
         if (ref instanceof List)
         {
            ((List) ref).remove(object);
         }
         else
         {
            parent.eUnset(feature);
         }
      }
   }

   public void undo()
   {
      try
      {
         if (parent != null)
         {                        
            Object ref = parent.eGet(feature);
            if (ref instanceof List)
            {
               ((List) ref).add(object);
            }
            else
            {
               parent.eSet(feature, object);
            }
         }
      }
      catch (ArrayStoreException ex)
      {
         ex.printStackTrace();
      }
   }
}