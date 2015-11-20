/*******************************************************************************
* Copyright (c) 2015 SunGard CSA LLC and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Barry.Grotjahn (SunGard CSA LLC) - initial API and implementation and/or initial documentation
*******************************************************************************/

package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractModelAdapter;

public class DataMappingModelAdapter extends AbstractModelAdapter
{
   private final Object model;
   private final Object value;
   private final DataMappingType element;

   public DataMappingModelAdapter(Object model, Object value, DataMappingType element)
   {
      this.model = model;
      this.value = value;
      this.element = element;      
   }

   public Object getModel()
   {
      return model;
   }

   public Object getValue()
   {
      return value;
   }

   public void updateModel(Object type)
   {
      try
      {
         if(!(type instanceof Type))
         {
            String safeValue = "(" + this.value.toString() + ")" + " " + type.toString();
            element.setDataPath(safeValue);            
         }
      }
      catch (Exception e)
      {
      }
   }
}