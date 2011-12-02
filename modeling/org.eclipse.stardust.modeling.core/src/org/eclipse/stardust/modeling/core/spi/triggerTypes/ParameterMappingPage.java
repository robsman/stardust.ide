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
package org.eclipse.stardust.modeling.core.spi.triggerTypes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class ParameterMappingPage extends AbstractModelElementPropertyPage
{
   private static final CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private LabeledViewer dataViewer;
   private LabeledText dataPathEditor;
   private AccessPathBrowserComposite dataPathBrowser;

   private LabeledViewer parameterViewer;
   private LabeledText parameterPathEditor;
   private AccessPathBrowserComposite parameterPathBrowser;

   private Button[] buttons;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      final ParameterMappingType parameterMapping = (ParameterMappingType) element;

      ModelType model = ModelUtils.findContainingModel(parameterMapping);

      WidgetBindingManager binding = getWidgetBindingManager();
      
      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(parameterMapping, model.getData()),
            new Data2DataPathWidgetAdapter2(dataViewer.getViewer(), dataPathBrowser, DirectionType.OUT_LITERAL));
      
      binding.bind(dataViewer, parameterMapping, PKG.getParameterMappingType_Data());

      getWidgetBindingManager().bind(dataPathEditor, parameterMapping, PKG.getParameterMappingType_DataPath());

      bindParameter2ParameterPath();

   }

   private void bindParameter2ParameterPath()
   {
      ParameterMappingType parameterMapping = (ParameterMappingType) getModelElement();
      final TriggerType trigger = (TriggerType) parameterMapping.eContainer();
      
      EFeatureAdapter adapter = new EFeatureAdapter()
      {
         public Object toModel(EObjectAdapter binding, Object value)
         {
            if (value instanceof IIdentifiableElement)
            {
               return ((IIdentifiableElement) value).getId();
            }
            return null;
         }

         public Object fromModel(EObjectAdapter binding, Object value)
         {
            for (AccessPointType accessPoint : trigger.getAccessPoint())
            {
               if (accessPoint.getId() != null && accessPoint.getId().equals(value))
               {
                  return accessPoint;
               }
            }
            return null;
         }
      };

      getWidgetBindingManager().getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(parameterMapping, trigger.getAccessPoint()),
            new Data2DataPathWidgetAdapter2(parameterViewer.getViewer(), parameterPathBrowser, DirectionType.IN_LITERAL));
      
      getWidgetBindingManager().bind(parameterViewer, parameterMapping, PKG.getParameterMappingType_Parameter(), adapter);

      getWidgetBindingManager().bind(parameterPathEditor, parameterMapping, PKG.getParameterMappingType_ParameterPath());
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         bindParameter2ParameterPath();
         IButtonManager manager = (IButtonManager) getElement().getAdapter(IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(getModelElement(), buttons);
         }
      }
      super.setVisible(visible);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      LabelWithStatus dataLabel = FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.LB_Data);
      ComboViewer dataText = new ComboViewer(FormBuilder.createCombo(composite));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      dataText.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
      dataViewer = new LabeledViewer(dataText, dataLabel);
      
      LabelWithStatus dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), composite, Diagram_Messages.LB_DataPath);
      dataPathEditor = new LabeledText(dataPathBrowser.getMethodText(), dataPathLabel);
      
      LabelWithStatus paramLabel = FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.LB_Parameter);
      ComboViewer parameterText = new ComboViewer(FormBuilder.createCombo(composite));
      parameterText.setSorter(new ViewerSorter());
      parameterText.setContentProvider(new ArrayContentProvider());
      parameterText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      parameterText.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
      parameterViewer = new LabeledViewer(parameterText, paramLabel);

      LabelWithStatus parameterPathLabel = FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.LB_AccessPath);
      parameterPathBrowser = new AccessPathBrowserComposite(getEditor(), composite, Diagram_Messages.LB_AccessPath);
      parameterPathEditor = new LabeledText(parameterPathBrowser.getMethodText(), parameterPathLabel);

      return composite;
   }
}