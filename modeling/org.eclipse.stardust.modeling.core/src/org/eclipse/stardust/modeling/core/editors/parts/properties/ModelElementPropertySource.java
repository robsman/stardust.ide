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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.*;
import org.eclipse.stardust.modeling.core.ui.BooleanPropertyDescriptor;
import org.eclipse.stardust.modeling.core.ui.EEnumPropertyDescriptor;
import org.eclipse.stardust.modeling.core.ui.StringListPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class ModelElementPropertySource implements IPropertySource
{
   private final EObject object;

   public ModelElementPropertySource(EObject obj)
   {
      this.object = obj;
   }

   public Object getEditableValue()
   {
      return object;
   }

   public IPropertyDescriptor[] getPropertyDescriptors()
   {
      EClass cls = object.eClass();
      List descriptors = new ArrayList();

      Iterator it = cls.getEAllAttributes().iterator();
      while (it.hasNext())
      {
         EAttribute attr = (EAttribute) it.next();

         EDataType type = attr.getEAttributeType();
         if (attr.isID())
         {
            // shouldn't be editable
            descriptors.add(new PropertyDescriptor(Integer.toString(attr.getFeatureID()),
                  attr.getName()));
         }
         else if (type.getInstanceClass() == String.class)
         {
            if (attr.isMany())
            {
               descriptors.add(new StringListPropertyDescriptor(
                     Integer.toString(attr.getFeatureID()), attr.getName()));
            }
            else
            {
               descriptors.add(new TextPropertyDescriptor(
                     Integer.toString(attr.getFeatureID()), attr.getName()));
            }
         }
         else if (type.getInstanceClass() == boolean.class)
         {
            descriptors.add(new BooleanPropertyDescriptor(
                  Integer.toString(attr.getFeatureID()), attr.getName()));
         }
         else if (attr.isChangeable() && (type instanceof EEnum))
         {
            descriptors.add(new EEnumPropertyDescriptor(
                  Integer.toString(attr.getFeatureID()), attr.getName(), (EEnum) type));
         }
      }

      for (Iterator i = cls.getEAllReferences().iterator(); i.hasNext();)
      {
         /*
          * EReference reference = (EReference) i.next();
          * if ((object instanceof IndependentSubProcess) && reference.isChangeable() &&
          * reference.getEType().equals(bpmnPackage.getDiagram())) { IndependentSubProcess
          * independentSubProcess = (IndependentSubProcess) object; descriptors.add(new
          * DiagramRefPropertyDescriptor( Integer.toString(reference.getFeatureID()),
          * reference.getName(), independentSubProcess.getContainingProcess()
          * .getContainingPool() .getContainingDiagram() .getContainingRepository())); }
          * else if ((object instanceof IndependentSubProcess) && reference.isChangeable() &&
          * reference.getEType().equals(bpmnPackage.getProcess())) { IndependentSubProcess
          * independentSubProcess = (IndependentSubProcess) object; if (null !=
          * independentSubProcess.getDiagramRef()) { descriptors.add(new
          * ProcessRefPropertyDescriptor( Integer.toString(reference.getFeatureID()),
          * reference.getName(), independentSubProcess.getDiagramRef())); } }
          */
      }

      return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[] {});
   }

   public Object getPropertyValue(Object id)
   {
      EStructuralFeature feature = object.eClass().getEStructuralFeature(
            Integer.parseInt((String) id));

      Object result = object.eGet(feature);
      return result != null ? result : ""; //$NON-NLS-1$
   }

   public boolean isPropertySet(Object id)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public void resetPropertyValue(Object id)
   {
      // TODO Auto-generated method stub
   }

   public void setPropertyValue(Object id, Object value)
   {
      EStructuralFeature feature = object.eClass().getEStructuralFeature(
            Integer.parseInt((String) id));
      object.eSet(feature, value);
   }
}
