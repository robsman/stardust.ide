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
package org.eclipse.stardust.modeling.project.propertypages;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.ExtensibleElementAdapter;
import org.eclipse.stardust.modeling.core.utils.ExtensibleElementValueAdapter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.project.Constants;
import org.eclipse.stardust.modeling.project.effort.EffortByKeyParameter;
import org.eclipse.stardust.modeling.project.effort.EffortEvent;
import org.eclipse.stardust.modeling.project.effort.EffortKey;
import org.eclipse.stardust.modeling.project.effort.EffortListener;
import org.eclipse.stardust.modeling.project.effort.EffortParameter;
import org.eclipse.stardust.modeling.project.effort.EffortParameterScope;
import org.eclipse.stardust.modeling.project.effort.EffortParameters;
import org.eclipse.stardust.modeling.project.effort.NamedItem;
import org.eclipse.stardust.modeling.project.effort.NamedItemList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import ag.carnot.base.CollectionUtils;

public abstract class AbstractProjectPlanningAspectPropertyPage
      extends AbstractModelElementPropertyPage implements IApplicationPropertyPage
{
   private EffortParameterScope scope;

   protected String errorMessage = null;

   private EffortParameters effortParameters;

   protected boolean inNotification;

   private static final LabelProvider namedItemLabelProvider = new LabelProvider()
   {
      public String getText(Object element)
      {
         return ((NamedItem) element).getName();
      }
   };

   public AbstractProjectPlanningAspectPropertyPage()
   {
      // errorMessage = BpmUiActivator.getDefault().hasModule(Modules.EFFORT_CALCULATION);
   }

   public void setScope(EffortParameterScope scope)
   {
      this.scope = scope;
   }

   boolean hasError()
   {
      return errorMessage != null;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing to do
   }


   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing to do
   }

   protected EffortParameters getEffortParameters()
   {
      if (effortParameters == null)
      {
         ModelType model = ModelUtils.findContainingModel(getModelElement());
         effortParameters = EffortParameters.getEffortParameters(model);
      }
      return effortParameters;
   }

   public Composite createParameterComposite(final Composite parent)
   {
      final ScrolledComposite scroller = new ScrolledComposite(parent, SWT.V_SCROLL);
      GridLayout layout = new GridLayout();
      layout.numColumns = 1;
      scroller.setLayout(layout);
      scroller.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      scroller.setExpandHorizontal(true);
      scroller.setExpandVertical(true);
      final Composite composite = FormBuilder.createComposite(scroller, 1);
      scroller.setContent(composite);
      final EffortParameters parameters = getEffortParameters();
      updateContent(composite);
      final EffortListener listener = new EffortListener()
      {
         public void handleEvent(EffortEvent event)
         {
            updateContent(composite);
            scroller.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
         }
      };
      composite.addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            parameters.removeListener(listener);
            scope.removeListener(listener);
         }
      });
      parameters.addListener(listener);
      scope.addListener(listener);
      return scroller;
   }

   void updateContent(Composite composite)
   {
      Control[] children = composite.getChildren();
      for (int i = 0; i < children.length; i++)
      {
         children[i].dispose();
      }
      if (hasError())
      {
         Label label = FormBuilder.createLabel(composite, errorMessage);
         label.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      }
      else
      {
         addControls(composite);
      }
      composite.layout();
      composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
   }

   private void addControls(Composite composite)
   {
      for (Iterator<String> iter = scope.getParameterNames(); iter.hasNext();)
      {
         String name = iter.next();
         EffortParameter parameter = scope.getParameter(name);
         if (parameter instanceof EffortByKeyParameter)
         {
            addComboEntry(composite, (EffortByKeyParameter) parameter);
         }
         else
         {
            addTextEntry(composite, parameter);
         }
      }
   }

   private void addComboEntry(final Composite composite,
         final EffortByKeyParameter parameter)
   {
      final LabeledViewer labeledViewer = FormBuilder.createComboViewer(composite,
            parameter.getName() + EffortParameters.SEPARATOR, Collections.EMPTY_LIST);
      final WidgetBindingManager wBndMgr = getWidgetBindingManager();
      final IExtensibleElement extensible = (IExtensibleElement) getModelElement();
      final ComboViewer viewer = (ComboViewer) labeledViewer.getViewer();
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(namedItemLabelProvider);
      final ExtensibleElementValueAdapter keyAdapter = new ExtensibleElementValueAdapter()
      {
         public Object fromModel(ExtensibleElementAdapter binding, Object value)
         {
            return value == null ? null : parameter.getKey((String) value);
         }

         public Object toModel(ExtensibleElementAdapter binding, Object value)
         {
            return value == null ? null : ((EffortKey) value).getName();
         }
      };
      viewer.setInput(getKeyList(viewer, parameter));
      wBndMgr.bind(labeledViewer, extensible, getAttributeName(parameter.getName()), keyAdapter);
      final EffortListener updater = new EffortListener()
      {
         public void handleEvent(EffortEvent event)
         {
            if (NamedItem.NAME_PROPERTY.equals(event.getProperty()))
            {
               wBndMgr.unbind(labeledViewer, extensible, getAttributeName((String) event.getOldValue()));
               wBndMgr.bind(labeledViewer, extensible, getAttributeName((String) event.getNewValue()), keyAdapter);
               Label label = labeledViewer.getLabel().getLabel();
               label.setText(getLabelForParameter((String) event.getNewValue()));
               composite.layout(new Control[] {label});
            }
         }
      };
      viewer.getCombo().addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            parameter.removeListener(updater);
         }
      });
      parameter.addListener(updater);
   }

   private void addTextEntry(final Composite composite,
         final EffortParameter parameter)
   {
      final LabeledText labeledText = FormBuilder.createLabeledText(composite,
            getLabelForParameter(parameter.getName()));
      labeledText.getText().addVerifyListener(VerifierFactory.doubleVerifier);
      final WidgetBindingManager wBndMgr = getWidgetBindingManager();
      final IExtensibleElement extensible = (IExtensibleElement) getModelElement();
      wBndMgr.bind(labeledText, extensible, getAttributeName(parameter.getName()));
      final EffortListener updater = new EffortListener()
      {
         public void handleEvent(EffortEvent event)
         {
            if (NamedItem.NAME_PROPERTY.equals(event.getProperty()))
            {
               wBndMgr.unbind(labeledText, extensible, getAttributeName((String) event.getOldValue()));
               wBndMgr.bind(labeledText, extensible, getAttributeName((String) event.getNewValue()));
               Label label = labeledText.getLabel().getLabel();
               label.setText(getLabelForParameter((String) event.getNewValue()));
               composite.layout(new Control[] {label});
            }
         }
      };
      labeledText.getText().addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            parameter.removeListener(updater);
         }
      });
      parameter.addListener(updater);
   }

   private String getAttributeName(String parameterName)
   {
      return Constants.SCOPE + EffortParameters.SEPARATOR + parameterName;
   }

   private String getLabelForParameter(String parameterName)
   {
      return parameterName + EffortParameters.SEPARATOR;
   }

   private List<EffortKey> getKeyList(final ComboViewer viewer, final EffortByKeyParameter parameter)
   {
      final EffortListener updater = new EffortListener()
      {
         public void handleEvent(EffortEvent event)
         {
            if (event.getSource() == parameter.getScope()
                  && event.getProperty() == NamedItemList.REMOVE_PROPERTY
                  && event.getOldValue() == parameter)
            {
               parameter.removeListener(this);
            }
            else
            {
               if (event.getSource() == parameter
                     && event.getProperty() == NamedItemList.REMOVE_PROPERTY)
               {
                  EffortKey key = (EffortKey) event.getOldValue();
                  key.removeListener(this);
               }
               ISelection selection = viewer.getSelection();
               viewer.setInput(getKeyList(this, parameter));
               viewer.setSelection(selection);
            }
         }
      };
      viewer.getCombo().addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            parameter.getScope().removeListener(updater);
            parameter.removeListener(updater);
            for (Iterator<String> i = parameter.getKeyNames(); i.hasNext();)
            {
               String keyName = i.next();
               EffortKey key = parameter.getKey(keyName);
               key.removeListener(updater);
            }
         }
      });
      // listener to check if the parameter was removed.
      parameter.getScope().addListener(updater);
      // listener to update keys in the list.
      parameter.addListener(updater);
      // listener for key name change is added in the called method.
      return getKeyList(updater, parameter);
   }

   private List<EffortKey> getKeyList(EffortListener updater, EffortByKeyParameter parameter)
   {
      List<EffortKey> keys = CollectionUtils.newList();
      for (Iterator<String> i = parameter.getKeyNames(); i.hasNext();)
      {
         String keyName = i.next();
         EffortKey key = parameter.getKey(keyName);
         keys.add(key);
         key.addListener(updater);
      }
      return keys;
   }
}
