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
package org.eclipse.stardust.modeling.core.editors.ui.validation;

import java.util.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IModelAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.editors.IEObjectValidationStatus;
import org.eclipse.stardust.modeling.core.editors.IValidationEventListener;
import org.eclipse.stardust.modeling.core.editors.IValidationStatus;
import org.eclipse.stardust.modeling.core.editors.ui.IPageValidationEventListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;


public class PageValidationManager implements IValidationEventListener
{
   private final IPreferencePage page;

   private final ListenerList pageStatusListeners = new ListenerList();

   private static final IBindingMediator NOOP_MEDIATOR = new NoopBindingMediator();

   private final Map/* <EObject, Map<EStructuralFeature, List<IWidgetAdapter>>> */widgetBindings = new HashMap();

   private final Map/* <EObject, Set<EStructuralFeature>> */infos = new HashMap();

   private final Map/* <EObject, Set<EStructuralFeature>> */warnings = new HashMap();

   private final Map/* <EObject, Set<EStructuralFeature>> */errors = new HashMap();

   public PageValidationManager(IPreferencePage page)
   {
      this.page = page;
   }

   public void dispose()
   {
      while ( !widgetBindings.isEmpty())
      {
         unbind(widgetBindings.keySet().iterator().next());
      }
   }

   public void bind(EObject model, Object feature, Label label)
   {
      bind(model, feature, new SwtValidationLabelAdapter(label));
   }

   public void bind(EObject model, Object feature, LabelWithStatus label)
   {
      bind(model, feature, new SwtValidationLabelAdapter(label));
   }

   public void bind(EObject model, Object feature,
         IWidgetAdapter widgetAdapter)
   {
      Map modelBindings = (Map) widgetBindings.get(model);
      if (null == modelBindings)
      {
         modelBindings = new HashMap();
         widgetBindings.put(model, modelBindings);
      }
      // TODO check for uniqueness

      List eFtrBindings = (List) modelBindings.get(feature);
      if (null == eFtrBindings)
      {
         eFtrBindings = new ArrayList();
         modelBindings.put(feature, eFtrBindings);
      }
      eFtrBindings.add(widgetAdapter);

      widgetAdapter.bind(NOOP_MEDIATOR);
   }

   public void unbind(EObject model, Object feature,
         Widget widget)
   {
      Map modelBindings = (Map) widgetBindings.get(model);
      if (null != modelBindings)
      {
         List eFtrBindings = (List) modelBindings.get(feature);
         if (null != eFtrBindings)
         {
            for (int i = 0; i < eFtrBindings.size(); i++)
            {
               IWidgetAdapter ftrBinding = (IWidgetAdapter) eFtrBindings.get(i);
               if (ftrBinding instanceof SwtWidgetAdapter
                  && ((SwtWidgetAdapter) ftrBinding).getWidget().equals(widget))
               {
                  eFtrBindings.remove(ftrBinding);
                  ftrBinding.unbind();
                  break;
               }
            }
         }
      }
   }

   public void unbind(Object model)
   {
      Map modelBindings = (Map) widgetBindings.remove(model);
      if (null != modelBindings)
      {
         for (Iterator i = modelBindings.values().iterator(); i.hasNext();)
         {
            List eFtrBindings = (List) i.next();
            for (Iterator j = eFtrBindings.iterator(); j.hasNext();)
            {
               ((IWidgetAdapter) j.next()).unbind();
            }
         }
         modelBindings.clear();
      }
   }

   public IQuickValidationStatus getPageStatus()
   {
      return new PageValidationStatus();
   }

   public void addPageValidationEventListener(IPageValidationEventListener listener)
   {
      pageStatusListeners.add(listener);
   }

   public void removePageValidationEventListener(IPageValidationEventListener listener)
   {
      pageStatusListeners.remove(listener);
   }

   public void onIssuesUpdated(EObject element, IValidationStatus validationStatus)
   {
      Map modelBindings = (Map) widgetBindings.get(element);
      if (null != modelBindings)
      {
         for (Iterator i = modelBindings.entrySet().iterator(); i.hasNext();)
         {
            Map.Entry entry = (Map.Entry) i.next();
            // TODO filter by feature
            Object feature = entry.getKey();
            List eFtrBindings = (List) entry.getValue();

            IValidationStatus bindingStatus = validationStatus;
            if ((bindingStatus instanceof IEObjectValidationStatus) && (null != feature))
            {
               bindingStatus = ((IEObjectValidationStatus) bindingStatus).getFeatureStatus(feature);
            }

            for (Iterator j = eFtrBindings.iterator(); j.hasNext();)
            {
               ((IWidgetAdapter) j.next()).updateVisuals(bindingStatus);
            }

            // update page validation status
            updateValidationStatus(infos, element, feature, bindingStatus.hasInfos());
            updateValidationStatus(warnings, element, feature,
                  bindingStatus.hasWarnings());
            updateValidationStatus(errors, element, feature, bindingStatus.hasErrors());
         }
      }

      firePageStatusUpdated();
   }

   protected void firePageStatusUpdated()
   {
      IQuickValidationStatus status = getPageStatus();

      Object[] listeners = pageStatusListeners.getListeners();
      for (int i = 0; i < listeners.length; i++ )
      {
         ((IPageValidationEventListener) listeners[i]).pageStatusUpdated(page, status);
      }
   }

   private static final void updateValidationStatus(Map issueRegistry, Object model,
         Object eFtr, boolean hasIssues)
   {
      Set modelIssues = (Set) issueRegistry.get(model);
      if (hasIssues)
      {
         if (null == modelIssues)
         {
            modelIssues = new HashSet();
            issueRegistry.put(model, modelIssues);
         }
         modelIssues.add(eFtr);
      }
      else
      {
         if (null != modelIssues)
         {
            modelIssues.remove(eFtr);
            if (modelIssues.isEmpty())
            {
               issueRegistry.remove(model);
            }
         }
      }
   }

   final class PageValidationStatus implements IQuickValidationStatus
   {
      public boolean hasIssues()
      {
         return hasErrors() || hasWarnings() || hasInfos();
      }

      public boolean hasInfos()
      {
         return !infos.isEmpty();
      }

      public boolean hasWarnings()
      {
         return !warnings.isEmpty();
      }

      public boolean hasErrors()
      {
         return !errors.isEmpty();
      }
   }

   private static final class NoopBindingMediator implements IBindingMediator
   {
      public void updateModel(IWidgetAdapter binding, Object value)
      {
      }

      public void updateWidget(IModelAdapter binding, Object value)
      {
      }
   }
}
