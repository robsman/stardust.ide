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

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.*;

public class QualityAssuranceCodesTreeContentProvider extends ModelElementNotificationAdapter
      implements ITreeContentProvider
{
   
   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.CODE__CODE,
      CarnotWorkflowModelPackage.CODE__NAME,
      CarnotWorkflowModelPackage.CODE__VALUE
   };
   
   @Override
   protected List getChildren(EObject parent)
   {
      return ((QualityControlType) parent).getCode();
   }

   private TreeViewer viewer;
 
   public QualityAssuranceCodesTreeContentProvider()
   {
      super(CarnotWorkflowModelPackage.eINSTANCE.getModelType_QualityControl(),
            elementFeatureIds, new IFilter()
      {
         public boolean select(Object object)
         {
            Code code = (Code) object;
            return code == null;
         }
      }, true);
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

   public void notifyChanged(Notification msg)
   {      
/*      
      if (msg.getNotifier() == getTarget())
      {
         viewer.refresh();
      }
      else
      {
         super.notifyChanged(msg);
      }
*/
      if(viewer != null)
      {
         viewer.refresh();
         
      }      
   }

   public Object[] getChildren(Object parentElement)
   {
      return new Object[0];
   }

   public Object getParent(Object element)
   {
      return null; 
   }

   public boolean hasChildren(Object element)
   {
      return false;
   }

   public Object[] getElements(Object inputElement)
   {      
      List<Code> codes = ((QualityControlType) inputElement).getCode();
      return codes.toArray();
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      dispose();
      init((EObject) newInput);
      this.viewer = (TreeViewer) viewer;
   }
}