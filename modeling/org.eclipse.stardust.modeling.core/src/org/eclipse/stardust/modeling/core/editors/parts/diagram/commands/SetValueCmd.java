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
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;

public class SetValueCmd extends ContainedElementCommand
{
   private Object value;
   private Object undoValue;
   private EStructuralFeature feature;
   private boolean wasSet;
   private int position = -1;

   public SetValueCmd(int parentLevel, EStructuralFeature feature, Object object)
   {
   this(parentLevel, null, feature, -1, object);
   }

   public SetValueCmd(EObject parent, EStructuralFeature feature, Object object)
   {
      this(IContainedElementCommand.PARENT, parent, feature, -1, object);
   }

   public SetValueCmd(EObject parent, EStructuralFeature feature, int position, Object object)
   {
   this(IContainedElementCommand.PARENT, parent, feature, position, object);
   }

   public SetValueCmd(int parentLevel, EObject parent, EStructuralFeature feature, int position, Object object)
   {
   super(parentLevel);
      setParent(parent);
      this.value = object;
      this.feature = feature;
      this.position = position;
   }

   public SetValueCmd(EObject parent, EStructuralFeature feature, boolean value)
   {
      this(parent, feature, -1, value ? Boolean.TRUE : Boolean.FALSE);
   }

   public void execute()
   {
      redo();
   }

   public void redo()
   {
      EObject parent = getContainer();
      if (parent != null)
      {
         Object ref = parent.eGet(feature);
         if (ref instanceof List)
         {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) ref;
            if (position < 0)
            {
               list.add(getValue());
            }
            else
            {
               list.add(position, getValue());
            }
         }
         else
         {
            wasSet = parent.eIsSet(feature);
            undoValue = parent.eGet(feature);
            if (parent instanceof ModelType)
            {
               ModelType modelType = (ModelType) parent;
               VariableContextHelper.getInstance().updateContextID(modelType,
                     getValue().toString());
            }
            parent.eSet(feature, getValue());
         }
      }
   }

   public void undo()
   {
      EObject parent = getContainer();
      if (parent != null)
      {
         Object ref = parent.eGet(feature);
         if (ref instanceof List)
         {
            ((List<?>) ref).remove(getValue());
         }
         else
         {
            if (wasSet)
            {
               if (parent instanceof ModelType)
               {
                  ModelType modelType = (ModelType) parent;
                  VariableContextHelper.getInstance().updateContextID(modelType,
                        undoValue.toString());
               }
               parent.eSet(feature, undoValue);
            }
            else
            {
               parent.eUnset(feature);
            }
         }
      }
   }

   public Object getValue()
   {
      return value;
   }

   public Object getUndoValue()
   {
      return undoValue;
   }
}