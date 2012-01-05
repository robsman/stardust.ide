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
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class DataMappingsTreeContentProvider extends ModelElementNotificationAdapter
      implements ITreeContentProvider
{
   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT,
      CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID,
      CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA,
      CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT
   };
   
   private TreeViewer viewer;

   private DataType data;
   private boolean isIn;

   private ModelElementNotificationAdapter attributeNotifier;

   public DataMappingsTreeContentProvider(final DataType data, final boolean isIn)
   {
      super(CarnotWorkflowModelPackage.eINSTANCE.getActivityType_DataMapping(),
            elementFeatureIds, new IFilter()
      {
         public boolean select(Object object)
         {
            DataMappingType type = (DataMappingType) object;
            return isIn == AccessPointUtil.isIn(type.getDirection())
               && (data == null || data.equals(type.getData()));
         }
      }, true);
      this.data = data;
      this.isIn = isIn;
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
      if (target instanceof ActivityType)
      {
         attributeNotifier = new ModelElementNotificationAdapter(
               CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute(),
               new int[] {CarnotWorkflowModelPackage.ATTRIBUTE_TYPE__VALUE},
               new IFilter()
               {
                  public boolean select(Object toTest)
                  {
                     AttributeType attribute = (AttributeType) toTest;
                     return CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT.equals(
                           attribute.getName());
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

   public void notifyChanged(Notification msg)
   {
      if (msg.getNotifier() == getTarget() &&
            CarnotWorkflowModelPackage.ACTIVITY_TYPE__SUB_PROCESS_MODE == msg.getFeatureID(getTarget().getClass()))
      {
         viewer.refresh();
      }
      else
      {
         super.notifyChanged(msg);
      }
   }

   public Object[] getChildren(Object parentElement)
   {
      if (parentElement instanceof ApplicationContextTypeType)
      {
         ApplicationContextTypeType context = (ApplicationContextTypeType) parentElement;
         List<?> allMappings = getChildren((ActivityType) viewer.getInput());
         for (int i = allMappings.size() - 1; i >= 0; i--)
         {
            DataMappingType dm = (DataMappingType) allMappings.get(i);
            if (!context.getId().equals(dm.getContext()))
            {
               allMappings.remove(i);
            }
         }
         return allMappings.toArray();
      }
      return new Object[0];
   }

   public Object getParent(Object element)
   {
      return element instanceof DataMappingType ?
            getApplicationContext((DataMappingType) element) : null;
   }

   private ApplicationContextTypeType getApplicationContext(DataMappingType dataMapping)
   {
      String context = dataMapping.getContext();
      ModelType model = ModelUtils.findContainingModel(dataMapping);
      ActivityType activity = ModelUtils.findContainingActivity(dataMapping);
      if (ActivityUtil.isInteractive(activity) && ActivityUtil.isApplicationActivity(activity)
            && !ActivityUtil.isImplicitContext(context))
      {
         ApplicationType application = activity.getApplication();
         if (application != null)
         {
            model = ModelUtils.findContainingModel(application);
         }
      }
      return (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(),
            context);
   }

   public boolean hasChildren(Object element)
   {
      return element instanceof ApplicationContextTypeType;
   }

   public Object[] getElements(Object inputElement)
   {
      ActivityType activity = (ActivityType) inputElement;
      List<ApplicationContextTypeType> contexts = ActivityUtil.getContextTypes(activity, isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      List<DataMappingType> dataMappings = activity.getDataMapping();
      for (DataMappingType type : dataMappings)
      {
         ApplicationContextTypeType ctx = getApplicationContext(type);
         if (isIn == AccessPointUtil.isIn(type.getDirection()) &&
               !contexts.contains(ctx) && (data == null || data.equals(type.getData())))
         {
            contexts.add(ctx);
         }
      }
      return contexts.toArray();
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      dispose();
      init((IModelElement) newInput);
      this.viewer = (TreeViewer) viewer;
   }
}
