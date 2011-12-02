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
package org.eclipse.stardust.modeling.core.utils;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.*;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledWidget;
import org.eclipse.stardust.modeling.core.editors.ui.validation.PageValidationManager;
import org.eclipse.swt.widgets.Button;


public class WidgetBindingManager
{
   private final BindingManager mBndMgr;
   private final PageValidationManager vBndMgr;

   public WidgetBindingManager(IPreferencePage page)
   {
      this.mBndMgr = new BindingManager();
      this.vBndMgr = new PageValidationManager(page);
   }

   public BindingManager getModelBindingManager()
   {
      return mBndMgr;
   }

   public PageValidationManager getValidationBindingManager()
   {
      return vBndMgr;
   }

   public void dispose()
   {
      vBndMgr.dispose();
      mBndMgr.dispose();
   }

   public void unbindModel(EObject model)
   {
      vBndMgr.unbind(model);
      mBndMgr.unbind(model);
   }

   public void bind(LabeledWidget widget, IWidgetAdapter widgetAdapter,
         IExtensibleElement model, String attribute)
   {
/* TODO      
      if (widget.getWidget() != widgetAdapter.getWidget())
      {
         // error
      }
*/      
      mBndMgr.bind(createModelAdapter(model, attribute, false), widgetAdapter);
      vBndMgr.bind(model, attribute, widget.getLabel());
   }

   public void bind(LabeledWidget widget, IWidgetAdapter widgetAdapter,
         IExtensibleElement model, String attribute,
         ExtensibleElementValueAdapter modelValueAdapter)
   {
/* TODO      
      if (widget.getWidget() != widgetAdapter.getWidget())
      {
         // error
      }
*/      
      mBndMgr.bind(createModelAdapter(model, attribute, modelValueAdapter), widgetAdapter);
      vBndMgr.bind(model, attribute, widget.getLabel());
   }

   public void bind(LabeledViewer text, EObject model, EStructuralFeature feature)
   {
      mBndMgr.bind(model, feature, text.getViewer());
      vBndMgr.bind(model, feature, text.getLabel());
   }

   public void bind(LabeledViewer text, EObject model, EStructuralFeature feature,
         EFeatureAdapter featureAdapter)
   {
      mBndMgr.bind(BindingManager.createModelAdapter(model, feature, featureAdapter),
         BindingManager.createWidgetAdapter(text.getViewer()));
      vBndMgr.bind(model, feature, text.getLabel());
   }

   public void bind(LabeledText text, EObject model, EStructuralFeature feature)
   {
      mBndMgr.bind(model, feature, text.getText());
      vBndMgr.bind(model, feature, text.getLabel());
   }

   public void bind(LabeledText text, EObject model, EStructuralFeature feature,
         EFeatureAdapter featureAdapter)
   {
      mBndMgr.bind(BindingManager.createModelAdapter(model, feature, featureAdapter),
            new SwtTextAdapter(text.getText()));
      vBndMgr.bind(model, feature, text.getLabel());
   }

   public void bind(LabeledText text, EObjectAdapter aModel)
   {
      mBndMgr.bind(aModel, new SwtTextAdapter(text.getText()));
      vBndMgr.bind(aModel.getEModel(), aModel.getFeature(), text.getLabel());
   }

   public void bind(LabeledText text, IExtensibleElement model, String attribute)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, false),
         BindingManager.createWidgetAdapter(text.getText()));
      vBndMgr.bind(model, attribute, text.getLabel());
   }

   public void bind(LabeledCombo combo, IExtensibleElement model, String attribute)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, false),
         BindingManager.createWidgetAdapter(combo.getCombo()));
      vBndMgr.bind(model, attribute, combo.getLabel());
   }

   public void bind(LabeledViewer text, IExtensibleElement model, String attribute,
                    EObject scope, EStructuralFeature feature)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, scope, feature),
         BindingManager.createWidgetAdapter(text.getViewer()));
      vBndMgr.bind(model, attribute, text.getLabel());
   }

   public void bind(LabeledViewer text, IExtensibleElement model, String attribute,
                    List scope)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, scope),
         BindingManager.createWidgetAdapter(text.getViewer()));
      vBndMgr.bind(model, attribute, text.getLabel());
   }

   public void bind(LabeledViewer text, IExtensibleElement model, String attribute,
         ExtensibleElementValueAdapter modelValueAdapter)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, modelValueAdapter),
      BindingManager.createWidgetAdapter(text.getViewer()));
      vBndMgr.bind(model, attribute, text.getLabel());
   }

   public void unbind(LabeledText text, IExtensibleElement model, Object feature)
   {
      mBndMgr.unbind(model, text.getText());
      vBndMgr.unbind(model, feature, text.getLabel());
   }

   public void unbind(LabeledViewer text, IExtensibleElement model, Object feature)
   {
      mBndMgr.unbind(model, text.getViewer().getControl());
      vBndMgr.unbind(model, feature, text.getLabel());
   }

   public void bind(Button localBinding, IExtensibleElement model, String attribute)
   {
      mBndMgr.bind(createModelAdapter(model, attribute, true),
         BindingManager.createWidgetAdapter(localBinding));
   }

   public static IModelAdapter createModelAdapter(IExtensibleElement ext, String att,
                                                  boolean isBoolean)
   {
      return new ExtensibleElementAdapter(ext, att, isBoolean);
   }

   public static IModelAdapter createModelAdapter(IExtensibleElement ext, String att,
         EObject scope, EStructuralFeature feature)
   {
      return new ExtensibleElementAdapter(ext, att, scope, feature);
   }

   public static IModelAdapter createModelAdapter(IExtensibleElement ext, String att,
         List scope)
   {
      return new ExtensibleElementAdapter(ext, att, scope);
   }

   public static IModelAdapter createModelAdapter(IExtensibleElement ext, String att,
         ExtensibleElementValueAdapter modelValueAdapter)
   {
      return new ExtensibleElementAdapter(ext, att, false, modelValueAdapter);
   }

   public void unbind(IModelElement model)
   {
      mBndMgr.unbind(model);
      vBndMgr.unbind(model);
   }
}
