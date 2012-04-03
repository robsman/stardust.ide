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
package org.eclipse.stardust.modeling.core.properties;

import java.awt.List;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class ReferencedModelSorter extends ViewerSorter
{
   private boolean grouped = false;
   private ModelType model;

   public boolean isGrouped()
   {
      return grouped;
   }

   public void setGrouped(boolean grouped)
   {
      this.grouped = grouped;
   }

   public int compare(Viewer viewer, Object e1, Object e2)
   {
      if (!isGrouped())
      {
         return super.compare(viewer, e1, e2);
      }
      ModelType model1 = ModelUtils.findContainingModel((EObject) e1);
      ModelType model2 = ModelUtils.findContainingModel((EObject) e2);
      if (model1 == null && model2 == null)
      {
         if (e1 instanceof AccessPointType && e2 instanceof AccessPointType)
         {
            AccessPointType ap1 = (AccessPointType) e1;
            AccessPointType ap2 = (AccessPointType) e2;
            model1 = parseReferencedModel(ap1);
            model2 = parseReferencedModel(ap2);
         }
      }
      if (model1 != model2)
      {
         if (model1 == model)
         {
            return -1;
         }
         if (model2 == model)
         {
            return 1;
         }
      }
      return super.compare(viewer, e1, e2);
   }

   public void setModel(ModelType model)
   {
      this.model = model;
   }
   
   private ModelType parseReferencedModel(AccessPointType apt)
   {
      String path = AttributeUtil.getAttribute(apt, "carnot:engine:dataType").getValue(); //$NON-NLS-1$      
      if (path.startsWith("typeDeclaration:")) //$NON-NLS-1$ 
      {
         int idx1 = path.indexOf("{");
         int idx2 = path.indexOf("}");
         String modelID = path.substring(idx1 + 1, idx2);
         java.util.List<String> refModelsURI = ModelUtils
               .getURIsForExternalPackages(model);
         for (Iterator<String> i = refModelsURI.iterator(); i.hasNext();)
         {
            ModelType refModel = ModelUtils.getReferencedModelByURI(model, i.next());
            if (refModel.getId().equalsIgnoreCase(modelID))
            {
               return refModel;
            }
         }
      }
      return model;
   }
}
