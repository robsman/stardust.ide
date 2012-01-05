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
package org.eclipse.stardust.modeling.common.ui.jface.databinding;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class BindingManager
{
   private final Mediator mediator = new Mediator();

   private final Map<Object, Map<IModelAdapter, IWidgetAdapter>> bindings = new IdentityHashMap<Object, Map<IModelAdapter, IWidgetAdapter>>();

   private final Map<IWidgetAdapter, IModelAdapter> widget2ModelIndex = new IdentityHashMap<IWidgetAdapter, IModelAdapter>();

   public void bind(EObject model, EStructuralFeature feature, Label label)
   {
      bind(model, feature, createWidgetAdapter(label));
   }

   public void bind(EObject model, EStructuralFeature feature, Text text)
   {
      bind(model, feature, createWidgetAdapter(text));
   }

   public void bind(EObject model, EStructuralFeature feature,
         EFeatureAdapter ftrAdapter, Text text)
   {
      bind(model, feature, ftrAdapter, createWidgetAdapter(text));
   }

   public void bind(EObject model, EStructuralFeature feature, Button button)
   {
      bind(model, feature, createWidgetAdapter(button));
   }

   public void bind(EObject model, EStructuralFeature feature, Combo combo)
   {
      bind(model, feature, createWidgetAdapter(combo));
   }

   public void bind(EObject model, EStructuralFeature feature, List list)
   {
      bind(model, feature, createWidgetAdapter(list));
   }

   public void bind(EObject model, EStructuralFeature feature, StructuredViewer viewer)
   {
      bind(model, feature, createWidgetAdapter(viewer));
   }

   public void bind(EObject model, IWidgetAdapter widgetAdapter)
   {
      bind(model, null, widgetAdapter);
   }
   
   public void bind(EObject model, EStructuralFeature eFtr, IWidgetAdapter widgetAdapter)
   {
      bind(createModelAdapter(model, eFtr), widgetAdapter);
   }
   
   public void bind(EObject model, EStructuralFeature eFtr,
                    EFeatureAdapter ftrAdapter, StructuredViewer viewer)
   {
      bind(createModelAdapter(model, eFtr, ftrAdapter), createWidgetAdapter(viewer));
   }

   public void bind(EObject model, EStructuralFeature eFtr, EFeatureAdapter ftrAdapter,
         IWidgetAdapter widgetAdapter)
   {
      bind(createModelAdapter(model, eFtr, ftrAdapter), widgetAdapter);
   }
   
   public void bind(IModelAdapter modelAdapter, IWidgetAdapter widgetAdapter)
   {
      Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.get(modelAdapter.getModel());
      if (null == modelBindings)
      {
         modelBindings = new HashMap<IModelAdapter, IWidgetAdapter>();
         bindings.put(modelAdapter.getModel(), modelBindings);
      }
      // TODO check for uniqueness

      modelBindings.put(modelAdapter, widgetAdapter);
      widget2ModelIndex.put(widgetAdapter, modelAdapter);

      modelAdapter.bind(mediator);
      widgetAdapter.bind(mediator);
      
      widgetAdapter.updateVisuals(modelAdapter.getValue());
   }

   public void unbind(EObject model, Widget target)
   {
      Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.get(model);
      if (null != modelBindings)
      {
         for (IWidgetAdapter widgetAdapter : widget2ModelIndex.keySet())
         {
            if (widgetAdapter instanceof SwtWidgetAdapter
               && ((SwtWidgetAdapter) widgetAdapter).getWidget().equals(target))
            {
               IModelAdapter modelAdapter = widget2ModelIndex.get(widgetAdapter);
               modelBindings.remove(modelAdapter);
               widget2ModelIndex.remove(widgetAdapter);
               modelAdapter.unbind();
               widgetAdapter.unbind();
               break;
            }
         }
      }
   }

   public void unbind(EObject model, IWidgetAdapter target)
   {
      Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.get(model);
      if (null != modelBindings)
      {
         for (IWidgetAdapter widgetAdapter : widget2ModelIndex.keySet())
         {
            if (widgetAdapter.equals(target))
            {
               IModelAdapter modelAdapter = widget2ModelIndex.get(widgetAdapter);
               modelBindings.remove(modelAdapter);
               widget2ModelIndex.remove(widgetAdapter);
               modelAdapter.unbind();
               widgetAdapter.unbind();
               break;
            }
         }
      }
   }

   public void unbind(Object model)
   {
      Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.remove(model);
      if (null != modelBindings)
      {
         for (Map.Entry<IModelAdapter, IWidgetAdapter> binding : modelBindings.entrySet())
         {
            binding.getKey().unbind();
            binding.getValue().unbind();

            widget2ModelIndex.remove(binding.getValue());
         }
         modelBindings.clear();
      }
   }

   public static IModelAdapter createModelAdapter(EObject eObj, EStructuralFeature eFtr)
   {
      return new EObjectAdapter(eObj, eFtr);
   }

   public static IModelAdapter createModelAdapter(EObject eObj, EStructuralFeature eFtr,
         EFeatureAdapter valueAdapter)
   {
      return new EObjectAdapter(eObj, eFtr, valueAdapter);
   }

   public static IWidgetAdapter createWidgetAdapter(Label label)
   {
      return new SwtLabelAdapter(label);
   }

   public static IWidgetAdapter createWidgetAdapter(Text text)
   {
      return new SwtTextAdapter(text);
   }

   public static IWidgetAdapter createWidgetAdapter(Button button)
   {
      return new SwtButtonAdapter(button);
   }

   public static IWidgetAdapter createWidgetAdapter(Combo combo)
   {
      return new SwtComboAdapter(combo);
   }

   public static IWidgetAdapter createWidgetAdapter(List list)
   {
      return new SwtListAdapter(list);
   }

   public static IWidgetAdapter createWidgetAdapter(StructuredViewer viewer)
   {
      return new StructuredViewerAdapter(viewer);
   }

   public void dispose()
   {
      while ( !bindings.isEmpty())
      {
         unbind(bindings.keySet().iterator().next());
      }
   }

   public void updateWidgets(Object model)
   {
      Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.get(model);
      if (null != modelBindings)
      {
         for (Map.Entry<IModelAdapter, IWidgetAdapter> binding : modelBindings.entrySet())
         {
            binding.getValue().updateVisuals(binding.getKey().getValue());
         }
      }
      else
      {
         // TODO
      }
   }

   private class Mediator implements IBindingMediator
   {
      public void updateWidget(IModelAdapter modelAdapter, Object value)
      {
         Map<IModelAdapter, IWidgetAdapter> modelBindings = bindings.get(modelAdapter.getModel());
         if (null != modelBindings)
         {
            IWidgetAdapter widgetAdapter = modelBindings.get(modelAdapter);
            if (null != widgetAdapter)
            {
               widgetAdapter.updateVisuals(value);
            }
            else
            {
               // TODO
            }
         }
         else
         {
            // TODO
         }
      }

      public void updateModel(IWidgetAdapter binding, Object value)
      {
         IModelAdapter modelAdapter = widget2ModelIndex.get(binding);
         if (null != modelAdapter)
         {
            modelAdapter.updateModel(value);
         }
         else
         {
            // TODO
         }
      }
   }
}