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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelElementsTableContentProvider
      extends ModelElementNotificationAdapter
      implements IStructuredContentProvider
{
   private TableViewer viewer;
   private String[] labelProperties;
   private ModelElementNotificationAdapter attributeNotifier;

   public ModelElementsTableContentProvider(EStructuralFeature elementListFeature,
         int[] elementFeatureIds, String[] labelProperties)
   {
      super(elementListFeature, elementFeatureIds, true);
      this.labelProperties = labelProperties;
   }

   public ModelElementsTableContentProvider(EStructuralFeature elementListFeature,
         int[] elementFeatureIds, String[] labelProperties, IFilter filter)
   {
      super(elementListFeature, elementFeatureIds, filter, true);
      this.labelProperties = labelProperties;
   }

   public Object[] getElements(Object inputElement)
   {
      EObject owner = (EObject) inputElement;
      return getChildren(owner).toArray();
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      dispose();
      init((EObject) newInput);
      this.viewer = (TableViewer) viewer;
      registerAttributeNotifier(newInput);
   }

   public void dispose()
   {
      if (attributeNotifier != null)
      {
         attributeNotifier.dispose();
      }
      super.dispose();
   }

   public void elementChanged(EObject element)
   {
      viewer.update(element, labelProperties);
   }

   public void elementAdded(EObject element)
   {
      if (attributeNotifier != null)
      {
         attributeNotifier.init(element);
      }
      viewer.refresh();
   }

   public void elementMoved(EObject element)
   {
      viewer.refresh();
   }

   public void elementRemoved(EObject element)
   {
      if (attributeNotifier != null)
      {
         attributeNotifier.dispose(element);
      }
      viewer.refresh();
   }

   public void setLabelChangingAttributes(final String[] attributes)
   {
      if (attributeNotifier != null)
      {
         attributeNotifier.dispose();
      }
      attributeNotifier = new ModelElementNotificationAdapter(
            CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute(),
            new int[] {CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE},
            new IFilter()
            {
               public boolean select(Object toTest)
               {
                  if (toTest instanceof AttributeType)
                  {
                     AttributeType attribute = (AttributeType) toTest;
                     for (int i = 0; i < attributes.length; i++)
                     {
                        if (attributes[i].equals(attribute.getName()))
                        {
                           return true;
                        }
                     }
                  }
                  return false;
               }
            }, true)
      {
         public void elementRemoved(EObject element)
         {
            ModelElementsTableContentProvider.this.elementChanged(element.eContainer());
         }
      
         public void elementMoved(EObject element)
         {
         }
      
         public void elementChanged(EObject element)
         {
            ModelElementsTableContentProvider.this.elementChanged(element.eContainer());
         }
      
         public void elementAdded(EObject element)
         {
            ModelElementsTableContentProvider.this.elementChanged(element.eContainer());
         }
      };
      registerAttributeNotifier();
   }

   private void registerAttributeNotifier()
   {
      for (Iterator i = targets(); i.hasNext();)
      {
         registerAttributeNotifier(i.next());
      }
   }

   private void registerAttributeNotifier(Object target)
   {
      if (attributeNotifier != null && target != null)
      {
         List children = getChildren((EObject) target);
         for (int i = 0; i < children.size(); i++)
         {
            attributeNotifier.init((EObject) children.get(i));
         }
      }
   }
}
