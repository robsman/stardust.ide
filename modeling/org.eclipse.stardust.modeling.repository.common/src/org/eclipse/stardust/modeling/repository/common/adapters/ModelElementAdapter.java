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
package org.eclipse.stardust.modeling.repository.common.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;


public class ModelElementAdapter implements IAdapterFactory
{
   // Superclass is not parameterized
   @SuppressWarnings("unchecked")
   public Object getAdapter(Object adaptableObject, Class adapterType)
   {
      TreeEditPart part = (TreeEditPart) adaptableObject;
      EObject model = (EObject) part.getModel();
      if (adapterType == EObject.class)
      {
         if (model instanceof IObjectReference)
         {
            EObject eObject = ((IObjectReference) model).getEObject();
            if (eObject != null)
            {
               return eObject;
            }
         }
         return model;
      }
      if (model instanceof IObjectDescriptor
            && adapterType == IObjectDescriptor.class)
      {
         return model;
      }
      if (model instanceof ModelElementDescriptor
            && (adapterType == IModelElement.class || adapterType == IIdentifiableModelElement.class))
      {
         return ((ModelElementDescriptor) model).getIdentifiable();
      }
      return null;
   }

   // Superclass is not parameterized
   @SuppressWarnings("unchecked")
   public Class[] getAdapterList()
   {
      return new Class[] {EObject.class, IObjectDescriptor.class,
            IModelElement.class, IIdentifiableModelElement.class};
   }
}
