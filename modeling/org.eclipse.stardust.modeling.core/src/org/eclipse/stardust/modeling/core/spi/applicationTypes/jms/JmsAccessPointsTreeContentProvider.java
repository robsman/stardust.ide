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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.jms;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.engine.extensions.jms.app.JMSDirection;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.properties.ModelElementNotificationAdapter;

public class JmsAccessPointsTreeContentProvider extends ModelElementNotificationAdapter
      implements ITreeContentProvider
{
   private static final String REQUEST = "Request"; //$NON-NLS-1$

   private static final String RESPONSE = "Response"; //$NON-NLS-1$

   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME
   };
   
   private TreeViewer viewer;

   private ModelElementNotificationAdapter attributeNotifier;

   public JmsAccessPointsTreeContentProvider()
   {
      super(CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint(),
            elementFeatureIds, true);
   }

   public void elementAdded(EObject element)
   {
      viewer.refresh();
   }

   public void elementChanged(EObject element)
   {
      viewer.refresh();
   }

   public void elementMoved(EObject element)
   {
      viewer.refresh();
   }

   public void elementRemoved(EObject element)
   {
      viewer.refresh();
   }

   public void init(EObject target)
   {
      super.init(target);
      if (target instanceof ApplicationType)
      {
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
                        return CarnotConstants.TYPE_ATT.equals(attribute.getName());
                     }
                     return false;
                  }
               }, true)
         {
            public void elementRemoved(EObject element)
            {
               viewer.refresh();
            }
         
            public void elementMoved(EObject element)
            {
            }
         
            public void elementChanged(EObject element)
            {
               viewer.refresh();
            }
         
            public void elementAdded(EObject element)
            {
               viewer.refresh();
            }
         };
         attributeNotifier.init(target);
      }
   }

   public void dispose()
   {
      if (attributeNotifier != null)
      {
         attributeNotifier.dispose();
      }
      super.dispose();
   }

   public Object[] getChildren(Object parentElement)
   {
      if (REQUEST.equals(parentElement))
      {
         return AccessPointUtil.getInAccessPonts((IAccessPointOwner) viewer.getInput()).toArray();
      }
      else if (RESPONSE.equals(parentElement))
      {
         return AccessPointUtil.getOutAccessPonts((IAccessPointOwner) viewer.getInput()).toArray();
      }
      return new Object[0];
   }

   public Object getParent(Object element)
   {
      return element instanceof AccessPointType ?
            AccessPointUtil.isDirectionCompatible((AccessPointType) element, true)
               ? REQUEST : RESPONSE: null;
   }

   public boolean hasChildren(Object element)
   {
      return REQUEST.equals(element) || RESPONSE.equals(element);
   }

   public Object[] getElements(Object inputElement)
   {
      if (inputElement instanceof ApplicationType)
      {
         String type = AttributeUtil.getAttributeValue(
               (IExtensibleElement) inputElement, CarnotConstants.TYPE_ATT);
         if (JMSDirection.IN.getId().equals(type))
         {
            return new Object[] {RESPONSE};
         }
         else if (JMSDirection.OUT.getId().equals(type))
         {
            return new Object[] {REQUEST};
         }
         else if (JMSDirection.INOUT.getId().equals(type))
         {
            return new Object[] {REQUEST, RESPONSE};
         }
      }
      return new Object[0];
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      dispose();
      init((IModelElement) newInput);
      this.viewer = (TreeViewer) viewer;
   }
}
